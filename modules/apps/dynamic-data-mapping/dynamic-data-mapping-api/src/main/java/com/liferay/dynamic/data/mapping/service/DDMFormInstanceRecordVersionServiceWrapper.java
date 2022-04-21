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

package com.liferay.dynamic.data.mapping.service;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDMFormInstanceRecordVersionService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceRecordVersionService
 * @generated
 */
public class DDMFormInstanceRecordVersionServiceWrapper
	implements DDMFormInstanceRecordVersionService,
			   ServiceWrapper<DDMFormInstanceRecordVersionService> {

	public DDMFormInstanceRecordVersionServiceWrapper() {
		this(null);
	}

	public DDMFormInstanceRecordVersionServiceWrapper(
		DDMFormInstanceRecordVersionService
			ddmFormInstanceRecordVersionService) {

		_ddmFormInstanceRecordVersionService =
			ddmFormInstanceRecordVersionService;
	}

	@Override
	public DDMFormInstanceRecordVersion fetchLatestFormInstanceRecordVersion(
			long userId, long formInstanceId, String formInstanceVersion,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordVersionService.
			fetchLatestFormInstanceRecordVersion(
				userId, formInstanceId, formInstanceVersion, status);
	}

	@Override
	public DDMFormInstanceRecordVersion getFormInstanceRecordVersion(
			long ddmFormInstanceRecordVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordVersionService.
			getFormInstanceRecordVersion(ddmFormInstanceRecordVersionId);
	}

	@Override
	public DDMFormInstanceRecordVersion getFormInstanceRecordVersion(
			long ddmFormInstanceRecordId, String version)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordVersionService.
			getFormInstanceRecordVersion(ddmFormInstanceRecordId, version);
	}

	@Override
	public java.util.List<DDMFormInstanceRecordVersion>
			getFormInstanceRecordVersions(long ddmFormInstanceRecordId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordVersionService.
			getFormInstanceRecordVersions(ddmFormInstanceRecordId);
	}

	@Override
	public java.util.List<DDMFormInstanceRecordVersion>
			getFormInstanceRecordVersions(
				long ddmFormInstanceRecordId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<DDMFormInstanceRecordVersion> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordVersionService.
			getFormInstanceRecordVersions(
				ddmFormInstanceRecordId, start, end, orderByComparator);
	}

	@Override
	public int getFormInstanceRecordVersionsCount(long ddmFormInstanceRecordId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordVersionService.
			getFormInstanceRecordVersionsCount(ddmFormInstanceRecordId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddmFormInstanceRecordVersionService.getOSGiServiceIdentifier();
	}

	@Override
	public DDMFormInstanceRecordVersionService getWrappedService() {
		return _ddmFormInstanceRecordVersionService;
	}

	@Override
	public void setWrappedService(
		DDMFormInstanceRecordVersionService
			ddmFormInstanceRecordVersionService) {

		_ddmFormInstanceRecordVersionService =
			ddmFormInstanceRecordVersionService;
	}

	private DDMFormInstanceRecordVersionService
		_ddmFormInstanceRecordVersionService;

}