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

package com.liferay.expando.kernel.service;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ExpandoColumnService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoColumnService
 * @generated
 */
public class ExpandoColumnServiceWrapper
	implements ExpandoColumnService, ServiceWrapper<ExpandoColumnService> {

	public ExpandoColumnServiceWrapper() {
		this(null);
	}

	public ExpandoColumnServiceWrapper(
		ExpandoColumnService expandoColumnService) {

		_expandoColumnService = expandoColumnService;
	}

	@Override
	public ExpandoColumn addColumn(long tableId, String name, int type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expandoColumnService.addColumn(tableId, name, type);
	}

	@Override
	public ExpandoColumn addColumn(
			long tableId, String name, int type, Object defaultData)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expandoColumnService.addColumn(
			tableId, name, type, defaultData);
	}

	@Override
	public void deleteColumn(long columnId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_expandoColumnService.deleteColumn(columnId);
	}

	@Override
	public ExpandoColumn fetchExpandoColumn(long columnId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expandoColumnService.fetchExpandoColumn(columnId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _expandoColumnService.getOSGiServiceIdentifier();
	}

	@Override
	public ExpandoColumn updateColumn(long columnId, String name, int type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expandoColumnService.updateColumn(columnId, name, type);
	}

	@Override
	public ExpandoColumn updateColumn(
			long columnId, String name, int type, Object defaultData)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expandoColumnService.updateColumn(
			columnId, name, type, defaultData);
	}

	@Override
	public ExpandoColumn updateTypeSettings(long columnId, String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expandoColumnService.updateTypeSettings(columnId, typeSettings);
	}

	@Override
	public ExpandoColumnService getWrappedService() {
		return _expandoColumnService;
	}

	@Override
	public void setWrappedService(ExpandoColumnService expandoColumnService) {
		_expandoColumnService = expandoColumnService;
	}

	private ExpandoColumnService _expandoColumnService;

}