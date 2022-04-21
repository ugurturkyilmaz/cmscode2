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

package com.liferay.document.library.kernel.service;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DLFileVersionService}.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileVersionService
 * @generated
 */
public class DLFileVersionServiceWrapper
	implements DLFileVersionService, ServiceWrapper<DLFileVersionService> {

	public DLFileVersionServiceWrapper() {
		this(null);
	}

	public DLFileVersionServiceWrapper(
		DLFileVersionService dlFileVersionService) {

		_dlFileVersionService = dlFileVersionService;
	}

	@Override
	public DLFileVersion getFileVersion(long fileVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileVersionService.getFileVersion(fileVersionId);
	}

	@Override
	public java.util.List<DLFileVersion> getFileVersions(
			long fileEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileVersionService.getFileVersions(fileEntryId, status);
	}

	@Override
	public int getFileVersionsCount(long fileEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileVersionService.getFileVersionsCount(fileEntryId, status);
	}

	@Override
	public DLFileVersion getLatestFileVersion(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileVersionService.getLatestFileVersion(fileEntryId);
	}

	@Override
	public DLFileVersion getLatestFileVersion(
			long fileEntryId, boolean excludeWorkingCopy)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileVersionService.getLatestFileVersion(
			fileEntryId, excludeWorkingCopy);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dlFileVersionService.getOSGiServiceIdentifier();
	}

	@Override
	public DLFileVersionService getWrappedService() {
		return _dlFileVersionService;
	}

	@Override
	public void setWrappedService(DLFileVersionService dlFileVersionService) {
		_dlFileVersionService = dlFileVersionService;
	}

	private DLFileVersionService _dlFileVersionService;

}