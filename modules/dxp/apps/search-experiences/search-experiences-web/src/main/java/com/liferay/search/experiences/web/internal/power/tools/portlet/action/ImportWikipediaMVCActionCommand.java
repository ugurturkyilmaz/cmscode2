/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.web.internal.power.tools.portlet.action;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.search.experiences.constants.SXPPortletKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 * @author André de Oliveira
 * @author Brian Wing Shun Chan
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"javax.portlet.name=" + SXPPortletKeys.SXP_POWER_TOOLS,
		"mvc.command.name=/sxp_power_tools/import_wikipedia"
	},
	service = MVCActionCommand.class
)
public class ImportWikipediaMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Set<String> importedWikipediaPageTitles = new HashSet<>();

		_import(
			actionRequest, importedWikipediaPageTitles,
			ParamUtil.getString(actionRequest, "wikipediaLanguageCode", "en"),
			Arrays.asList(
				ParamUtil.getStringValues(
					actionRequest, "wikipediaPageTitles")));
	}

	private String[] _getAssetTagNames(JSONObject jsonObject) {
		List<String> assetTagNames = new ArrayList<>();

		JSONArray categoriesJSONArray = jsonObject.getJSONArray("categories");

		for (int i = 0; i < categoriesJSONArray.length(); i++) {
			JSONObject categoryJSONObject = categoriesJSONArray.getJSONObject(
				i);

			if (!categoryJSONObject.has("hidden")) {
				assetTagNames.add(categoryJSONObject.getString("*"));
			}
		}

		return assetTagNames.toArray(new String[0]);
	}

	private void _import(
			ActionRequest actionRequest,
			Set<String> importedWikipediaPageTitles,
			String wikipediaLanguageCode, List<String> wikipediaPageTitles)
		throws Exception {

		List<String> linkedWikipediaPageTitles = new ArrayList<>();

		for (String wikipediaPageTitle : wikipediaPageTitles) {
			String json = _http.URLtoString(
				StringBundler.concat(
					"https://", wikipediaLanguageCode,
					".wikipedia.org/w/api.php?action=parse&format=json&page=",
					URLCodec.encodeURL(wikipediaPageTitle)));

			JSONObject jsonObject = jsonFactory.createJSONObject(json);

			JSONObject parseJSONObject = jsonObject.getJSONObject("parse");

			String title = parseJSONObject.getString("title");

			if (importedWikipediaPageTitles.contains(title)) {
				continue;
			}

			JSONArray linksJSONArray = jsonObject.getJSONArray("links");

			for (int i = 0; i < linksJSONArray.length(); i++) {
				JSONObject linkJSONObject = linksJSONArray.getJSONObject(i);

				if (linkJSONObject.getInt("ns") == 0) {
					linkedWikipediaPageTitles.add(
						linkJSONObject.getString("*"));
				}
			}

			JSONObject textJSONObject = parseJSONObject.getJSONObject("text");

			addJournalArticle(
				actionRequest, _getAssetTagNames(parseJSONObject),
				textJSONObject.getString("*"), title);

			importedWikipediaPageTitles.add(title);
		}

		_import(
			actionRequest, importedWikipediaPageTitles, wikipediaLanguageCode,
			linkedWikipediaPageTitles);
	}

	@Reference
	private Http _http;

}