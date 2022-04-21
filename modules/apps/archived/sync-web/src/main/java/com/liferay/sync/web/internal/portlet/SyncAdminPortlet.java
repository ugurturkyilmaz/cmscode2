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

package com.liferay.sync.web.internal.portlet;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sync.constants.SyncConstants;
import com.liferay.sync.constants.SyncPortletKeys;
import com.liferay.sync.exception.OAuthPortletUndeployedException;
import com.liferay.sync.oauth.helper.SyncOAuthHelperUtil;
import com.liferay.sync.service.configuration.SyncServiceConfigurationKeys;
import com.liferay.sync.util.SyncHelper;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 * @author Jonathan McCann
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-sync-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Sync Connector Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SyncPortletKeys.SYNC_ADMIN_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator"
	},
	service = Portlet.class
)
public class SyncAdminPortlet extends BaseSyncPortlet {

	public void updatePreferences(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			_updatePreferences(actionRequest);
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}
	}

	public void updateSites(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String enabled = ParamUtil.getString(actionRequest, "enabled");
		String permissions = ParamUtil.getString(actionRequest, "permissions");

		long[] groupIds = ParamUtil.getLongValues(actionRequest, "groupIds");

		for (long groupId : groupIds) {
			Group group = _groupLocalService.fetchGroup(groupId);

			UnicodeProperties typeSettingsUnicodeProperties =
				group.getTypeSettingsProperties();

			if (Validator.isNotNull(enabled)) {
				typeSettingsUnicodeProperties.setProperty(
					"syncEnabled", enabled);
			}

			if (Validator.isNotNull(permissions)) {
				typeSettingsUnicodeProperties.setProperty(
					"syncSiteMemberFilePermissions", permissions);
			}

			group.setTypeSettingsProperties(typeSettingsUnicodeProperties);

			_groupLocalService.updateGroup(group);
		}
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.sync.web)(&(release.schema.version>=1.0.0)(!(release.schema.version>=2.0.0))))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

	@Reference(unbind = "-")
	protected void setSyncOAuthHelperUtil(
		SyncOAuthHelperUtil syncOAuthHelperUtil) {

		_syncOAuthHelperUtil = syncOAuthHelperUtil;
	}

	private void _updatePreferences(ActionRequest actionRequest)
		throws Exception {

		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences(
			CompanyThreadLocal.getCompanyId());

		boolean allowUserPersonalSites = ParamUtil.getBoolean(
			actionRequest, "allowUserPersonalSites");

		portletPreferences.setValue(
			SyncServiceConfigurationKeys.SYNC_ALLOW_USER_PERSONAL_SITES,
			String.valueOf(allowUserPersonalSites));

		boolean enabled = ParamUtil.getBoolean(actionRequest, "enabled");

		portletPreferences.setValue(
			SyncServiceConfigurationKeys.SYNC_SERVICES_ENABLED,
			String.valueOf(enabled));

		boolean forceSecurityMode = ParamUtil.getBoolean(
			actionRequest, "forceSecurityMode");

		portletPreferences.setValue(
			SyncServiceConfigurationKeys.SYNC_CLIENT_FORCE_SECURITY_MODE,
			String.valueOf(forceSecurityMode));

		boolean lanEnabled = ParamUtil.getBoolean(actionRequest, "lanEnabled");

		if (lanEnabled) {
			_syncHelper.enableLanSync(CompanyThreadLocal.getCompanyId());
		}

		portletPreferences.setValue(
			SyncServiceConfigurationKeys.SYNC_LAN_ENABLED,
			String.valueOf(lanEnabled));

		int maxConnections = ParamUtil.getInteger(
			actionRequest, "maxConnections");

		portletPreferences.setValue(
			SyncServiceConfigurationKeys.SYNC_CLIENT_MAX_CONNECTIONS,
			String.valueOf(maxConnections));

		int maxDownloadRate = ParamUtil.getInteger(
			actionRequest, "maxDownloadRate");

		portletPreferences.setValue(
			SyncServiceConfigurationKeys.SYNC_CLIENT_MAX_DOWNLOAD_RATE,
			String.valueOf(maxDownloadRate));

		int maxUploadRate = ParamUtil.getInteger(
			actionRequest, "maxUploadRate");

		portletPreferences.setValue(
			SyncServiceConfigurationKeys.SYNC_CLIENT_MAX_UPLOAD_RATE,
			String.valueOf(maxUploadRate));

		boolean oAuthEnabled = ParamUtil.getBoolean(
			actionRequest, "oAuthEnabled");

		portletPreferences.setValue(
			SyncConstants.SYNC_OAUTH_ENABLED, String.valueOf(oAuthEnabled));

		int pollInterval = ParamUtil.getInteger(actionRequest, "pollInterval");

		portletPreferences.setValue(
			SyncServiceConfigurationKeys.SYNC_CLIENT_POLL_INTERVAL,
			String.valueOf(pollInterval));

		portletPreferences.setValue(
			SyncConstants.SYNC_CONTEXT_MODIFIED_TIME,
			String.valueOf(System.currentTimeMillis()));

		portletPreferences.store();

		if (oAuthEnabled) {
			if (!_syncOAuthHelperUtil.isDeployed()) {
				SessionErrors.add(
					actionRequest, OAuthPortletUndeployedException.class);

				return;
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			_syncOAuthHelperUtil.enableOAuth(
				CompanyThreadLocal.getCompanyId(), serviceContext);
		}
	}

	private GroupLocalService _groupLocalService;

	@Reference
	private SyncHelper _syncHelper;

	private SyncOAuthHelperUtil _syncOAuthHelperUtil;

}