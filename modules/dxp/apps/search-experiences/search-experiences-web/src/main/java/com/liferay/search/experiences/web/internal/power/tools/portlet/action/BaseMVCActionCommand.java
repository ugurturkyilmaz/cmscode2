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

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 * @author André de Oliveira
 * @author Brian Wing Shun Chan
 */
public abstract class BaseMVCActionCommand
	extends com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand {

	protected JournalArticle addJournalArticle(
			ActionRequest actionRequest, String[] assetTagNames, String content,
			String title)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			JournalArticle.class.getName(), actionRequest);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAssetTagNames(assetTagNames);

		return journalArticleLocalService.addArticle(
			themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), 0,
			HashMapBuilder.put(
				themeDisplay.getLocale(), title
			).build(),
			HashMapBuilder.put(
				themeDisplay.getLocale(),
				StringUtil.shorten(HtmlUtil.stripHtml(content), 500)
			).build(),
			_toXML(content, themeDisplay.getLanguageId()), "BASIC-WEB-CONTENT",
			"BASIC-WEB-CONTENT", serviceContext);
	}

	@Reference
	protected JournalArticleLocalService journalArticleLocalService;

	@Reference
	protected JSONFactory jsonFactory;

	private String _toXML(String content, String languageId) {
		StringBundler sb = new StringBundler(18);

		sb.append("<root available-locales=\"en_US\" default-locale=\"");
		sb.append(languageId);
		sb.append("\"><dynamic-element instance-id=\"");

		String key = PwdGenerator.KEY1 + PwdGenerator.KEY2 + PwdGenerator.KEY3;

		for (int i = 0; i < 8; i++) {
			int pos = (int)Math.floor(Math.random() * key.length());

			sb.append(key.charAt(pos));
		}

		sb.append("\" index-type=\"text\" name=\"content\" ");
		sb.append("type=\"text_area\"><dynamic-content language-id=\"");
		sb.append(languageId);
		sb.append("\"><![CDATA[");
		sb.append(StringUtil.shorten(content, _ELASTICSEARCH_FIELD_SIZE_LIMIT));
		sb.append("]]></dynamic-content></dynamic-element></root>");

		return sb.toString();
	}

	private static final int _ELASTICSEARCH_FIELD_SIZE_LIMIT = 32000;

}