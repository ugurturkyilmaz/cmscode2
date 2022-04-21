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

package com.liferay.screens.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScreensAssetEntryService}.
 *
 * @author José Manuel Navarro
 * @see ScreensAssetEntryService
 * @generated
 */
public class ScreensAssetEntryServiceWrapper
	implements ScreensAssetEntryService,
			   ServiceWrapper<ScreensAssetEntryService> {

	public ScreensAssetEntryServiceWrapper() {
		this(null);
	}

	public ScreensAssetEntryServiceWrapper(
		ScreensAssetEntryService screensAssetEntryService) {

		_screensAssetEntryService = screensAssetEntryService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getAssetEntries(
			com.liferay.asset.kernel.service.persistence.AssetEntryQuery
				assetEntryQuery,
			java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensAssetEntryService.getAssetEntries(
			assetEntryQuery, locale);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getAssetEntries(
			long companyId, long groupId, String portletItemName,
			java.util.Locale locale, int max)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensAssetEntryService.getAssetEntries(
			companyId, groupId, portletItemName, locale, max);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getAssetEntry(
			long entryId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensAssetEntryService.getAssetEntry(entryId, locale);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getAssetEntry(
			String className, long classPK, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensAssetEntryService.getAssetEntry(
			className, classPK, locale);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _screensAssetEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public ScreensAssetEntryService getWrappedService() {
		return _screensAssetEntryService;
	}

	@Override
	public void setWrappedService(
		ScreensAssetEntryService screensAssetEntryService) {

		_screensAssetEntryService = screensAssetEntryService;
	}

	private ScreensAssetEntryService _screensAssetEntryService;

}