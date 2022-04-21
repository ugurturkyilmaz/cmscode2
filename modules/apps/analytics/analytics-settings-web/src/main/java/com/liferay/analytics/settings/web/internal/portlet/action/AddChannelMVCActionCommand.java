/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.analytics.settings.web.internal.portlet.action;

import com.liferay.analytics.settings.web.internal.util.AnalyticsSettingsUtil;
import com.liferay.configuration.admin.constants.ConfigurationAdminPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.CompanyService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.portlet.ActionRequest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André Miranda
 */
@Component(
	property = {
		"javax.portlet.name=" + ConfigurationAdminPortletKeys.INSTANCE_SETTINGS,
		"mvc.command.name=/analytics_settings/add_channel"
	},
	service = MVCActionCommand.class
)
public class AddChannelMVCActionCommand extends BaseAnalyticsMVCActionCommand {

	@Override
	protected void updateConfigurationProperties(
			ActionRequest actionRequest,
			Dictionary<String, Object> configurationProperties)
		throws Exception {

		String[] selectedGroupIds = ParamUtil.getStringValues(
			actionRequest, "rowIds");

		if (ArrayUtil.isEmpty(selectedGroupIds)) {
			return;
		}

		Set<String> liferayAnalyticsGroupIds = _mergeSyncedGroupIds(
			actionRequest, selectedGroupIds);

		_updateCompanyPreferences(actionRequest, liferayAnalyticsGroupIds);

		configurationProperties.put(
			"syncedGroupIds", liferayAnalyticsGroupIds.toArray(new String[0]));

		_notifyAnalyticsCloudCreateChannels(
			actionRequest, ParamUtil.getString(actionRequest, "channelType"),
			selectedGroupIds);

		_notifyAnalyticsCloudSitesSelected(
			actionRequest, liferayAnalyticsGroupIds);
	}

	private JSONObject _buildGroupJSONObject(
		Group group, ThemeDisplay themeDisplay) {

		JSONObject groupJSONObject = JSONUtil.put(
			"id", String.valueOf(group.getGroupId()));

		try {
			return groupJSONObject.put(
				"name", group.getDescriptiveName(themeDisplay.getLocale()));
		}
		catch (PortalException portalException) {
			_log.error(portalException);

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", themeDisplay.getLocale(), getClass());

			return groupJSONObject.put(
				"name", LanguageUtil.get(resourceBundle, "unknown"));
		}
	}

	private Set<String> _mergeSyncedGroupIds(
		ActionRequest actionRequest, String[] selectedGroupIds) {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Set<String> liferayAnalyticsGroupIds = SetUtil.fromArray(
			PrefsPropsUtil.getStringArray(
				themeDisplay.getCompanyId(), "liferayAnalyticsGroupIds",
				StringPool.COMMA));

		Collections.addAll(liferayAnalyticsGroupIds, selectedGroupIds);

		return liferayAnalyticsGroupIds;
	}

	private void _notifyAnalyticsCloudCreateChannels(
			ActionRequest actionRequest, String channelType,
			String[] selectedGroupIds)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!AnalyticsSettingsUtil.isAnalyticsEnabled(
				themeDisplay.getCompanyId())) {

			return;
		}

		Stream<String> stream = Arrays.stream(selectedGroupIds);

		HttpResponse httpResponse = AnalyticsSettingsUtil.doPost(
			JSONUtil.put(
				"channelType", channelType
			).put(
				"dataSourceId",
				AnalyticsSettingsUtil.getDataSourceId(
					themeDisplay.getCompanyId())
			).put(
				"groups",
				JSONUtil.toJSONArray(
					stream.map(
						Long::valueOf
					).map(
						groupLocalService::fetchGroup
					).filter(
						Objects::nonNull
					).collect(
						Collectors.toList()
					),
					group -> _buildGroupJSONObject(group, themeDisplay))
			),
			themeDisplay.getCompanyId(), "api/1.0/channels");

		StatusLine statusLine = httpResponse.getStatusLine();

		if (statusLine.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
			checkResponse(themeDisplay.getCompanyId(), httpResponse);

			return;
		}

		if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
			throw new PortalException(
				"Unable to create channels: " +
					EntityUtils.toString(httpResponse.getEntity()));
		}

		_updateTypeSettingsProperties(
			EntityUtils.toString(httpResponse.getEntity()));
	}

	private void _notifyAnalyticsCloudSitesSelected(
			ActionRequest actionRequest, Set<String> liferayAnalyticsGroupIds)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!AnalyticsSettingsUtil.isAnalyticsEnabled(
				themeDisplay.getCompanyId())) {

			return;
		}

		boolean sitesSelected = true;

		if (liferayAnalyticsGroupIds.isEmpty()) {
			sitesSelected = false;
		}

		HttpResponse httpResponse = AnalyticsSettingsUtil.doPut(
			JSONUtil.put("sitesSelected", sitesSelected),
			themeDisplay.getCompanyId(),
			String.format(
				"api/1.0/data-sources/%s/details",
				AnalyticsSettingsUtil.getDataSourceId(
					themeDisplay.getCompanyId())));

		StatusLine statusLine = httpResponse.getStatusLine();

		if (statusLine.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
			checkResponse(themeDisplay.getCompanyId(), httpResponse);

			return;
		}

		if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
			throw new PortalException(
				"Unable to update data source details: " +
					EntityUtils.toString(httpResponse.getEntity()));
		}
	}

	private Set<String> _updateCompanyPreferences(
			ActionRequest actionRequest, Set<String> liferayAnalyticsGroupIds)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_companyService.updatePreferences(
			themeDisplay.getCompanyId(),
			UnicodePropertiesBuilder.create(
				true
			).put(
				"liferayAnalyticsGroupIds",
				StringUtil.merge(liferayAnalyticsGroupIds, StringPool.COMMA)
			).build());

		return liferayAnalyticsGroupIds;
	}

	private void _updateTypeSettingsProperties(String json) throws Exception {
		JSONArray channelsJSONArray = JSONFactoryUtil.createJSONArray(json);

		for (int i = 0; i < channelsJSONArray.length(); i++) {
			JSONObject channelJSONObject = channelsJSONArray.getJSONObject(i);

			String channelId = channelJSONObject.getString("id");

			JSONArray dataSourcesJSONArray = channelJSONObject.getJSONArray(
				"dataSources");

			Stream<JSONObject> dataSourcesStream = StreamSupport.stream(
				dataSourcesJSONArray.spliterator(), false);

			dataSourcesStream.flatMap(
				dataSourceJSONObject -> {
					JSONArray groupIdsJSONArray =
						dataSourceJSONObject.getJSONArray("groupIds");

					return StreamSupport.stream(
						groupIdsJSONArray.spliterator(), false);
				}
			).map(
				String::valueOf
			).forEach(
				groupId -> {
					Group group = groupLocalService.fetchGroup(
						GetterUtil.getLong(groupId));

					UnicodeProperties typeSettingsUnicodeProperties =
						group.getTypeSettingsProperties();

					typeSettingsUnicodeProperties.put(
						"analyticsChannelId", channelId);

					group.setTypeSettingsProperties(
						typeSettingsUnicodeProperties);

					groupLocalService.updateGroup(group);
				}
			);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddChannelMVCActionCommand.class);

	@Reference
	private CompanyService _companyService;

	@Reference
	private Portal _portal;

}