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

package com.liferay.ratings.kernel.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.ratings.kernel.model.RatingsEntry;

/**
 * Provides a wrapper for {@link RatingsEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see RatingsEntryService
 * @generated
 */
public class RatingsEntryServiceWrapper
	implements RatingsEntryService, ServiceWrapper<RatingsEntryService> {

	public RatingsEntryServiceWrapper() {
		this(null);
	}

	public RatingsEntryServiceWrapper(RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	@Override
	public void deleteEntry(String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_ratingsEntryService.deleteEntry(className, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ratingsEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public RatingsEntry updateEntry(
			String className, long classPK, double score)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ratingsEntryService.updateEntry(className, classPK, score);
	}

	@Override
	public RatingsEntryService getWrappedService() {
		return _ratingsEntryService;
	}

	@Override
	public void setWrappedService(RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	private RatingsEntryService _ratingsEntryService;

}