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

package com.liferay.exportimport.internal.background.task;

import com.liferay.exportimport.internal.background.task.display.PortletExportImportBackgroundTaskDisplay;
import com.liferay.exportimport.kernel.exception.MissingReferenceException;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.MissingReference;
import com.liferay.exportimport.kernel.lar.MissingReferences;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleManagerUtil;
import com.liferay.exportimport.kernel.lifecycle.constants.ExportImportLifecycleConstants;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portlet.exportimport.service.http.StagingServiceHttp;

import java.io.File;
import java.io.Serializable;

import java.util.Map;

/**
 * @author Akos Thurzo
 */
public class PortletRemoteStagingBackgroundTaskExecutor
	extends BaseStagingBackgroundTaskExecutor {

	public PortletRemoteStagingBackgroundTaskExecutor() {
		setBackgroundTaskStatusMessageTranslator(
			new PortletStagingBackgroundTaskStatusMessageTranslator());
	}

	@Override
	public BackgroundTaskExecutor clone() {
		PortletRemoteStagingBackgroundTaskExecutor
			portletRemoteStagingBackgroundTaskExecutor =
				new PortletRemoteStagingBackgroundTaskExecutor();

		portletRemoteStagingBackgroundTaskExecutor.
			setBackgroundTaskStatusMessageTranslator(
				getBackgroundTaskStatusMessageTranslator());
		portletRemoteStagingBackgroundTaskExecutor.setIsolationLevel(
			getIsolationLevel());

		return portletRemoteStagingBackgroundTaskExecutor;
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		ExportImportConfiguration exportImportConfiguration =
			getExportImportConfiguration(backgroundTask);

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		File file = null;
		HttpPrincipal httpPrincipal = null;
		MissingReferences missingReferences = null;
		long stagingRequestId = 0L;

		try {
			currentThread.setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			ExportImportThreadLocal.setPortletStagingInProcess(true);

			ExportImportLifecycleManagerUtil.fireExportImportLifecycleEvent(
				ExportImportLifecycleConstants.
					EVENT_PUBLICATION_PORTLET_REMOTE_STARTED,
				ExportImportLifecycleConstants.
					PROCESS_FLAG_PORTLET_STAGING_IN_PROCESS,
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
				exportImportConfiguration);

			file = ExportImportLocalServiceUtil.exportPortletInfoAsFile(
				exportImportConfiguration);

			String checksum = FileUtil.getMD5Checksum(file);

			Map<String, Serializable> taskContextMap =
				backgroundTask.getTaskContextMap();

			httpPrincipal = (HttpPrincipal)taskContextMap.get("httpPrincipal");

			long targetGroupId = MapUtil.getLong(
				exportImportConfiguration.getSettingsMap(), "targetGroupId");

			stagingRequestId = StagingServiceHttp.createStagingRequest(
				httpPrincipal, targetGroupId, checksum);

			StagingUtil.transferFileToRemoteLive(
				file, stagingRequestId, httpPrincipal);

			markBackgroundTask(
				backgroundTask.getBackgroundTaskId(), "exported");

			missingReferences = StagingServiceHttp.publishStagingRequest(
				httpPrincipal, stagingRequestId, exportImportConfiguration);

			Map<String, MissingReference> dependencyMissingReferences =
				missingReferences.getDependencyMissingReferences();

			if (!dependencyMissingReferences.isEmpty()) {
				throw new MissingReferenceException(missingReferences);
			}

			deleteExportedChangesetEntries();

			ExportImportThreadLocal.setPortletStagingInProcess(false);

			ExportImportLifecycleManagerUtil.fireExportImportLifecycleEvent(
				ExportImportLifecycleConstants.
					EVENT_PUBLICATION_PORTLET_REMOTE_SUCCEEDED,
				ExportImportLifecycleConstants.
					PROCESS_FLAG_PORTLET_STAGING_IN_PROCESS,
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
				exportImportConfiguration);
		}
		catch (Throwable throwable) {
			ExportImportThreadLocal.setPortletStagingInProcess(false);

			ExportImportLifecycleManagerUtil.fireExportImportLifecycleEvent(
				ExportImportLifecycleConstants.
					EVENT_PUBLICATION_PORTLET_REMOTE_FAILED,
				ExportImportLifecycleConstants.
					PROCESS_FLAG_PORTLET_STAGING_IN_PROCESS,
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
				exportImportConfiguration);

			deleteTempLarOnFailure(file);

			throw new SystemException(throwable);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);

			if ((stagingRequestId > 0) && (httpPrincipal != null)) {
				try {
					StagingServiceHttp.cleanUpStagingRequest(
						httpPrincipal, stagingRequestId);
				}
				catch (PortalException portalException) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to clean up the remote live site",
							portalException);
					}
				}
			}
		}

		deleteTempLarOnSuccess(file);

		return processMissingReferences(
			backgroundTask.getBackgroundTaskId(), missingReferences);
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(
		BackgroundTask backgroundTask) {

		return new PortletExportImportBackgroundTaskDisplay(backgroundTask);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletRemoteStagingBackgroundTaskExecutor.class);

}