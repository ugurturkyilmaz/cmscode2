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

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDMFormInstanceRecordService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceRecordService
 * @generated
 */
public class DDMFormInstanceRecordServiceWrapper
	implements DDMFormInstanceRecordService,
			   ServiceWrapper<DDMFormInstanceRecordService> {

	public DDMFormInstanceRecordServiceWrapper() {
		this(null);
	}

	public DDMFormInstanceRecordServiceWrapper(
		DDMFormInstanceRecordService ddmFormInstanceRecordService) {

		_ddmFormInstanceRecordService = ddmFormInstanceRecordService;
	}

	@Override
	public DDMFormInstanceRecord addFormInstanceRecord(
			long groupId, long ddmFormInstanceId,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				ddmFormValues,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordService.addFormInstanceRecord(
			groupId, ddmFormInstanceId, ddmFormValues, serviceContext);
	}

	@Override
	public void deleteFormInstanceRecord(long ddmFormInstanceRecordId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_ddmFormInstanceRecordService.deleteFormInstanceRecord(
			ddmFormInstanceRecordId);
	}

	@Override
	public DDMFormInstanceRecord getFormInstanceRecord(
			long ddmFormInstanceRecordId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordService.getFormInstanceRecord(
			ddmFormInstanceRecordId);
	}

	@Override
	public java.util.List<DDMFormInstanceRecord> getFormInstanceRecords(
			long ddmFormInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordService.getFormInstanceRecords(
			ddmFormInstanceId);
	}

	@Override
	public java.util.List<DDMFormInstanceRecord> getFormInstanceRecords(
			long ddmFormInstanceId, int status, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<DDMFormInstanceRecord> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordService.getFormInstanceRecords(
			ddmFormInstanceId, status, start, end, orderByComparator);
	}

	@Override
	public int getFormInstanceRecordsCount(long ddmFormInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordService.getFormInstanceRecordsCount(
			ddmFormInstanceId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddmFormInstanceRecordService.getOSGiServiceIdentifier();
	}

	@Override
	public void revertFormInstanceRecord(
			long ddmFormInstanceRecordId, String version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_ddmFormInstanceRecordService.revertFormInstanceRecord(
			ddmFormInstanceRecordId, version, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<DDMFormInstanceRecord> searchFormInstanceRecords(
				long ddmFormInstanceId, String[] notEmptyFields, int status,
				int start, int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordService.searchFormInstanceRecords(
			ddmFormInstanceId, notEmptyFields, status, start, end, sort);
	}

	@Override
	public DDMFormInstanceRecord updateFormInstanceRecord(
			long ddmFormInstanceRecordId, boolean majorVersion,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				ddmFormValues,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceRecordService.updateFormInstanceRecord(
			ddmFormInstanceRecordId, majorVersion, ddmFormValues,
			serviceContext);
	}

	@Override
	public DDMFormInstanceRecordService getWrappedService() {
		return _ddmFormInstanceRecordService;
	}

	@Override
	public void setWrappedService(
		DDMFormInstanceRecordService ddmFormInstanceRecordService) {

		_ddmFormInstanceRecordService = ddmFormInstanceRecordService;
	}

	private DDMFormInstanceRecordService _ddmFormInstanceRecordService;

}