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

package com.liferay.exportimport.kernel.service;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.InputStream;

/**
 * Provides the local service utility for ExportImport. This utility wraps
 * <code>com.liferay.portlet.exportimport.service.impl.ExportImportLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ExportImportLocalService
 * @generated
 */
public class ExportImportLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.exportimport.service.impl.ExportImportLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static java.io.File exportLayoutsAsFile(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws PortalException {

		return getService().exportLayoutsAsFile(exportImportConfiguration);
	}

	public static long exportLayoutsAsFileInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws PortalException {

		return getService().exportLayoutsAsFileInBackground(
			userId, exportImportConfiguration);
	}

	public static long exportLayoutsAsFileInBackground(
			long userId, long exportImportConfigurationId)
		throws PortalException {

		return getService().exportLayoutsAsFileInBackground(
			userId, exportImportConfigurationId);
	}

	public static java.io.File exportPortletInfoAsFile(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws PortalException {

		return getService().exportPortletInfoAsFile(exportImportConfiguration);
	}

	public static long exportPortletInfoAsFileInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws PortalException {

		return getService().exportPortletInfoAsFileInBackground(
			userId, exportImportConfiguration);
	}

	public static long exportPortletInfoAsFileInBackground(
			long userId, long exportImportConfigurationId)
		throws PortalException {

		return getService().exportPortletInfoAsFileInBackground(
			userId, exportImportConfigurationId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void importLayouts(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws PortalException {

		getService().importLayouts(exportImportConfiguration, file);
	}

	public static void importLayouts(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			InputStream inputStream)
		throws PortalException {

		getService().importLayouts(exportImportConfiguration, inputStream);
	}

	public static void importLayoutsDataDeletions(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws PortalException {

		getService().importLayoutsDataDeletions(
			exportImportConfiguration, file);
	}

	public static long importLayoutSetPrototypeInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws PortalException {

		return getService().importLayoutSetPrototypeInBackground(
			userId, exportImportConfiguration, file);
	}

	public static long importLayoutsInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws PortalException {

		return getService().importLayoutsInBackground(
			userId, exportImportConfiguration, file);
	}

	public static long importLayoutsInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			InputStream inputStream)
		throws PortalException {

		return getService().importLayoutsInBackground(
			userId, exportImportConfiguration, inputStream);
	}

	public static long importLayoutsInBackground(
			long userId, long exportImportConfigurationId, java.io.File file)
		throws PortalException {

		return getService().importLayoutsInBackground(
			userId, exportImportConfigurationId, file);
	}

	public static long importLayoutsInBackground(
			long userId, long exportImportConfigurationId,
			InputStream inputStream)
		throws PortalException {

		return getService().importLayoutsInBackground(
			userId, exportImportConfigurationId, inputStream);
	}

	public static void importPortletDataDeletions(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws PortalException {

		getService().importPortletDataDeletions(
			exportImportConfiguration, file);
	}

	public static void importPortletInfo(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws PortalException {

		getService().importPortletInfo(exportImportConfiguration, file);
	}

	public static void importPortletInfo(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			InputStream inputStream)
		throws PortalException {

		getService().importPortletInfo(exportImportConfiguration, inputStream);
	}

	public static long importPortletInfoInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws PortalException {

		return getService().importPortletInfoInBackground(
			userId, exportImportConfiguration, file);
	}

	public static long importPortletInfoInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			InputStream inputStream)
		throws PortalException {

		return getService().importPortletInfoInBackground(
			userId, exportImportConfiguration, inputStream);
	}

	public static long importPortletInfoInBackground(
			long userId, long exportImportConfigurationId, java.io.File file)
		throws PortalException {

		return getService().importPortletInfoInBackground(
			userId, exportImportConfigurationId, file);
	}

	public static long importPortletInfoInBackground(
			long userId, long exportImportConfigurationId,
			InputStream inputStream)
		throws PortalException {

		return getService().importPortletInfoInBackground(
			userId, exportImportConfigurationId, inputStream);
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportLayoutsFile(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.File file)
		throws PortalException {

		return getService().validateImportLayoutsFile(
			exportImportConfiguration, file);
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportLayoutsFile(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				InputStream inputStream)
		throws PortalException {

		return getService().validateImportLayoutsFile(
			exportImportConfiguration, inputStream);
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportPortletInfo(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.File file)
		throws PortalException {

		return getService().validateImportPortletInfo(
			exportImportConfiguration, file);
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportPortletInfo(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				InputStream inputStream)
		throws PortalException {

		return getService().validateImportPortletInfo(
			exportImportConfiguration, inputStream);
	}

	public static ExportImportLocalService getService() {
		return _service;
	}

	private static volatile ExportImportLocalService _service;

}