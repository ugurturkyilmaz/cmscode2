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

import com.liferay.dynamic.data.mapping.model.DDMTemplateVersion;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDMTemplateVersionService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplateVersionService
 * @generated
 */
public class DDMTemplateVersionServiceWrapper
	implements DDMTemplateVersionService,
			   ServiceWrapper<DDMTemplateVersionService> {

	public DDMTemplateVersionServiceWrapper() {
		this(null);
	}

	public DDMTemplateVersionServiceWrapper(
		DDMTemplateVersionService ddmTemplateVersionService) {

		_ddmTemplateVersionService = ddmTemplateVersionService;
	}

	@Override
	public DDMTemplateVersion getLatestTemplateVersion(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateVersionService.getLatestTemplateVersion(templateId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddmTemplateVersionService.getOSGiServiceIdentifier();
	}

	@Override
	public DDMTemplateVersion getTemplateVersion(long templateVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateVersionService.getTemplateVersion(templateVersionId);
	}

	@Override
	public java.util.List<DDMTemplateVersion> getTemplateVersions(
			long templateId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
				orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateVersionService.getTemplateVersions(
			templateId, start, end, orderByComparator);
	}

	@Override
	public int getTemplateVersionsCount(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateVersionService.getTemplateVersionsCount(templateId);
	}

	@Override
	public DDMTemplateVersionService getWrappedService() {
		return _ddmTemplateVersionService;
	}

	@Override
	public void setWrappedService(
		DDMTemplateVersionService ddmTemplateVersionService) {

		_ddmTemplateVersionService = ddmTemplateVersionService;
	}

	private DDMTemplateVersionService _ddmTemplateVersionService;

}