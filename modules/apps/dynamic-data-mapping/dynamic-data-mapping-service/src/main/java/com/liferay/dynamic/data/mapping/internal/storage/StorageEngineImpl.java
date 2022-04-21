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

package com.liferay.dynamic.data.mapping.internal.storage;

import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStorageLinkLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageAdapter;
import com.liferay.dynamic.data.mapping.storage.StorageAdapterRegistry;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo Lundgren
 */
@Component(immediate = true, service = StorageEngine.class)
public class StorageEngineImpl implements StorageEngine {

	@Override
	public long create(
			long companyId, long ddmStructureId, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws StorageException {

		StorageAdapter storageAdapter = _getStructureStorageAdapter(
			ddmStructureId);

		return storageAdapter.create(
			companyId, ddmStructureId, ddmFormValues, serviceContext);
	}

	@Override
	public void deleteByClass(long classPK) throws StorageException {
		StorageAdapter storageAdapter = _getClassStorageAdapter(classPK);

		if (storageAdapter != null) {
			storageAdapter.deleteByClass(classPK);
		}
	}

	@Override
	public void deleteByDDMStructure(long ddmStructureId)
		throws StorageException {

		StorageAdapter storageAdapter = _getStructureStorageAdapter(
			ddmStructureId);

		storageAdapter.deleteByDDMStructure(ddmStructureId);
	}

	@Override
	public DDMFormValues getDDMFormValues(long classPK)
		throws StorageException {

		StorageAdapter storageAdapter = _getClassStorageAdapter(classPK);

		if (storageAdapter == null) {
			return null;
		}

		return storageAdapter.getDDMFormValues(classPK);
	}

	@Override
	public String getStorageType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(
			long classPK, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws StorageException {

		StorageAdapter storageAdapter = _getClassStorageAdapter(classPK);

		if (storageAdapter != null) {
			storageAdapter.update(classPK, ddmFormValues, serviceContext);
		}
	}

	protected StorageAdapter getStorageAdapter(String storageType) {
		StorageAdapter storageAdapter =
			_storageAdapterRegistry.getStorageAdapter(storageType);

		if (storageAdapter != null) {
			return storageAdapter;
		}

		return _storageAdapterRegistry.getDefaultStorageAdapter();
	}

	@Reference(unbind = "-")
	protected void setDDMStorageLinkLocalService(
		DDMStorageLinkLocalService ddmStorageLinkLocalService) {

		_ddmStorageLinkLocalService = ddmStorageLinkLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setStorageAdapterRegistry(
		StorageAdapterRegistry storageAdapterRegistry) {

		_storageAdapterRegistry = storageAdapterRegistry;
	}

	private StorageAdapter _getClassStorageAdapter(long classPK)
		throws StorageException {

		try {
			DDMStorageLink ddmStorageLink =
				_ddmStorageLinkLocalService.fetchClassStorageLink(classPK);

			if (ddmStorageLink == null) {
				return null;
			}

			return getStorageAdapter(ddmStorageLink.getStorageType());
		}
		catch (NoSuchStructureException noSuchStructureException) {
			if (_log.isDebugEnabled()) {
				_log.debug(noSuchStructureException);
			}

			return _storageAdapterRegistry.getDefaultStorageAdapter();
		}
		catch (StorageException storageException) {
			throw storageException;
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	private StorageAdapter _getStructureStorageAdapter(long ddmStructureId)
		throws StorageException {

		try {
			DDMStructure ddmStructure =
				_ddmStructureLocalService.getDDMStructure(ddmStructureId);

			return getStorageAdapter(ddmStructure.getStorageType());
		}
		catch (NoSuchStructureException noSuchStructureException) {
			if (_log.isDebugEnabled()) {
				_log.debug(noSuchStructureException);
			}

			return _storageAdapterRegistry.getDefaultStorageAdapter();
		}
		catch (StorageException storageException) {
			throw storageException;
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		StorageEngineImpl.class);

	private DDMStorageLinkLocalService _ddmStorageLinkLocalService;
	private DDMStructureLocalService _ddmStructureLocalService;
	private StorageAdapterRegistry _storageAdapterRegistry;

}