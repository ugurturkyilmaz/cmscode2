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

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceVersion;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDMFormInstanceVersionService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceVersionService
 * @generated
 */
public class DDMFormInstanceVersionServiceWrapper
	implements DDMFormInstanceVersionService,
			   ServiceWrapper<DDMFormInstanceVersionService> {

	public DDMFormInstanceVersionServiceWrapper() {
		this(null);
	}

	public DDMFormInstanceVersionServiceWrapper(
		DDMFormInstanceVersionService ddmFormInstanceVersionService) {

		_ddmFormInstanceVersionService = ddmFormInstanceVersionService;
	}

	@Override
	public DDMFormInstanceVersion getFormInstanceVersion(
			long ddmFormInstanceVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceVersionService.getFormInstanceVersion(
			ddmFormInstanceVersionId);
	}

	@Override
	public java.util.List<DDMFormInstanceVersion> getFormInstanceVersions(
			long ddmFormInstanceId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<DDMFormInstanceVersion> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceVersionService.getFormInstanceVersions(
			ddmFormInstanceId, start, end, orderByComparator);
	}

	@Override
	public int getFormInstanceVersionsCount(long ddmFormInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceVersionService.getFormInstanceVersionsCount(
			ddmFormInstanceId);
	}

	@Override
	public DDMFormInstanceVersion getLatestFormInstanceVersion(
			long ddmFormInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceVersionService.getLatestFormInstanceVersion(
			ddmFormInstanceId);
	}

	@Override
	public DDMFormInstanceVersion getLatestFormInstanceVersion(
			long ddmFormInstanceId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceVersionService.getLatestFormInstanceVersion(
			ddmFormInstanceId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddmFormInstanceVersionService.getOSGiServiceIdentifier();
	}

	@Override
	public DDMFormInstanceVersionService getWrappedService() {
		return _ddmFormInstanceVersionService;
	}

	@Override
	public void setWrappedService(
		DDMFormInstanceVersionService ddmFormInstanceVersionService) {

		_ddmFormInstanceVersionService = ddmFormInstanceVersionService;
	}

	private DDMFormInstanceVersionService _ddmFormInstanceVersionService;

}