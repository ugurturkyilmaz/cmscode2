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

package com.liferay.exportimport.internal.lifecycle;

import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEvent;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleListener;
import com.liferay.exportimport.kernel.lifecycle.constants.ExportImportLifecycleConstants;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.exportimport.kernel.staging.StagingURLHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.exportimport.service.http.StagingServiceHttp;

import java.io.Serializable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = ExportImportLifecycleListener.class)
public class EventRemotePropagatorExportImportLifecycleListener
	implements ExportImportLifecycleListener {

	@Override
	public boolean isParallel() {
		return false;
	}

	@Override
	public void onExportImportLifecycleEvent(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		if (_eventNeedsToBePropagated(exportImportLifecycleEvent)) {
			_propagateEvent(exportImportLifecycleEvent);
		}
	}

	@Activate
	protected void activate() {
		_propagatedEventTypes.add(
			ExportImportLifecycleConstants.
				EVENT_PUBLICATION_LAYOUT_REMOTE_FAILED);
		_propagatedEventTypes.add(
			ExportImportLifecycleConstants.
				EVENT_PUBLICATION_LAYOUT_REMOTE_STARTED);
		_propagatedEventTypes.add(
			ExportImportLifecycleConstants.
				EVENT_PUBLICATION_LAYOUT_REMOTE_SUCCEEDED);
	}

	private boolean _eventNeedsToBePropagated(
		ExportImportLifecycleEvent exportImportLifecycleEvent) {

		if (!_propagatedEventTypes.contains(
				exportImportLifecycleEvent.getCode())) {

			return false;
		}

		Optional<ExportImportConfiguration> exportImportConfigurationOptional =
			_getExportImportConfiguration(exportImportLifecycleEvent);

		Optional<Map<String, Serializable>> settingsMapOptional =
			exportImportConfigurationOptional.map(
				exportImportConfiguration ->
					exportImportConfiguration.getSettingsMap());

		long sourceGroupId = GetterUtil.getLong(
			settingsMapOptional.map(
				settingsMap -> settingsMap.get("sourceGroupId")
			).orElse(
				GroupConstants.ANY_PARENT_GROUP_ID
			));

		Group sourceGroup = _groupLocalService.fetchGroup(sourceGroupId);

		if ((sourceGroup == null) || !sourceGroup.isStagedRemotely()) {
			return false;
		}

		long targetGroupId = GetterUtil.getLong(
			settingsMapOptional.map(
				settingsMap -> settingsMap.get("targetGroupId")
			).orElse(
				GroupConstants.ANY_PARENT_GROUP_ID
			));

		Group targetGroup = _groupLocalService.fetchGroup(targetGroupId);

		UnicodeProperties typeSettingsUnicodeProperties =
			sourceGroup.getTypeSettingsProperties();

		String remoteGroupUUID = typeSettingsUnicodeProperties.getProperty(
			"remoteGroupUUID");

		// If the target group can be found and the UUID's also match, then we
		// must not propagate the event because it means remote staging is
		// configured between two sites on the same portal instance

		if (Validator.isNotNull(remoteGroupUUID) && (targetGroup != null) &&
			StringUtil.equals(remoteGroupUUID, targetGroup.getUuid())) {

			return false;
		}

		String remoteAddress = GetterUtil.getString(
			settingsMapOptional.map(
				settingsMap -> settingsMap.get("remoteAddress")
			).orElse(
				StringPool.BLANK
			));

		return !remoteAddress.equals("localhost");
	}

	private Optional<ExportImportConfiguration> _getExportImportConfiguration(
		ExportImportLifecycleEvent exportImportLifecycleEvent) {

		List<Serializable> attributes =
			exportImportLifecycleEvent.getAttributes();

		return Optional.ofNullable(
			(ExportImportConfiguration)attributes.get(0));
	}

	private Optional<HttpPrincipal> _getHttpPrincipal(
		ExportImportLifecycleEvent exportImportLifecycleEvent) {

		Optional<ExportImportConfiguration> exportImportConfigurationOptional =
			_getExportImportConfiguration(exportImportLifecycleEvent);

		return exportImportConfigurationOptional.map(
			exportImportConfiguration ->
				exportImportConfiguration.getSettingsMap()
		).map(
			settingsMap -> MapUtil.getLong(settingsMap, "userId")
		).map(
			userId -> _userLocalService.fetchUser(userId)
		).flatMap(
			user -> _getHttpPrincipal(
				user, _getRemoteURL(exportImportLifecycleEvent))
		);
	}

	private Optional<HttpPrincipal> _getHttpPrincipal(
		User user, String remoteURL) {

		HttpPrincipal httpPrincipal = null;

		try {
			httpPrincipal = new HttpPrincipal(
				remoteURL, user.getLogin(), user.getPassword(),
				user.isPasswordEncrypted());
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to generate HttpPrincipal for user " +
						user.getFullName(),
					portalException);
			}
		}

		return Optional.ofNullable(httpPrincipal);
	}

	private String _getRemoteURL(
		ExportImportLifecycleEvent exportImportLifecycleEvent) {

		List<Serializable> attributes =
			exportImportLifecycleEvent.getAttributes();

		ExportImportConfiguration exportImportConfiguration =
			(ExportImportConfiguration)attributes.get(0);

		return _stagingURLHelper.buildRemoteURL(exportImportConfiguration);
	}

	private void _propagateEvent(
		ExportImportLifecycleEvent exportImportLifecycleEvent) {

		_getHttpPrincipal(
			exportImportLifecycleEvent
		).ifPresent(
			httpPrincipal -> {
				try {
					StagingServiceHttp.propagateExportImportLifecycleEvent(
						httpPrincipal, exportImportLifecycleEvent.getCode(),
						exportImportLifecycleEvent.getProcessFlag(),
						exportImportLifecycleEvent.getProcessId(),
						exportImportLifecycleEvent.getAttributes());
				}
				catch (PortalException portalException) {
					_log.error(
						"Unable to propagate staging lifecycle event to the " +
							"remote live site",
						portalException);
				}
			}
		);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EventRemotePropagatorExportImportLifecycleListener.class);

	@Reference
	private GroupLocalService _groupLocalService;

	private final Set<Integer> _propagatedEventTypes = new HashSet<>();

	@Reference
	private Staging _staging;

	@Reference
	private StagingURLHelper _stagingURLHelper;

	@Reference
	private UserLocalService _userLocalService;

}