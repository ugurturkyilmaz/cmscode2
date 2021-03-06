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

package com.liferay.flags.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FlagsEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see FlagsEntryService
 * @generated
 */
public class FlagsEntryServiceWrapper
	implements FlagsEntryService, ServiceWrapper<FlagsEntryService> {

	public FlagsEntryServiceWrapper() {
		this(null);
	}

	public FlagsEntryServiceWrapper(FlagsEntryService flagsEntryService) {
		_flagsEntryService = flagsEntryService;
	}

	@Override
	public void addEntry(
			String className, long classPK, String reporterEmailAddress,
			long reportedUserId, String contentTitle, String contentURL,
			String reason,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_flagsEntryService.addEntry(
			className, classPK, reporterEmailAddress, reportedUserId,
			contentTitle, contentURL, reason, serviceContext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _flagsEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public FlagsEntryService getWrappedService() {
		return _flagsEntryService;
	}

	@Override
	public void setWrappedService(FlagsEntryService flagsEntryService) {
		_flagsEntryService = flagsEntryService;
	}

	private FlagsEntryService _flagsEntryService;

}