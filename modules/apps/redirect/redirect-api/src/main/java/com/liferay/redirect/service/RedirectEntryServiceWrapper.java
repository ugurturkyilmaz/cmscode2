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

package com.liferay.redirect.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RedirectEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see RedirectEntryService
 * @generated
 */
public class RedirectEntryServiceWrapper
	implements RedirectEntryService, ServiceWrapper<RedirectEntryService> {

	public RedirectEntryServiceWrapper() {
		this(null);
	}

	public RedirectEntryServiceWrapper(
		RedirectEntryService redirectEntryService) {

		_redirectEntryService = redirectEntryService;
	}

	@Override
	public com.liferay.redirect.model.RedirectEntry addRedirectEntry(
			long groupId, String destinationURL, java.util.Date expirationDate,
			boolean permanent, String sourceURL,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectEntryService.addRedirectEntry(
			groupId, destinationURL, expirationDate, permanent, sourceURL,
			serviceContext);
	}

	@Override
	public com.liferay.redirect.model.RedirectEntry addRedirectEntry(
			long groupId, String destinationURL, java.util.Date expirationDate,
			String groupBaseURL, boolean permanent, String sourceURL,
			boolean updateChainedRedirectEntries,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectEntryService.addRedirectEntry(
			groupId, destinationURL, expirationDate, groupBaseURL, permanent,
			sourceURL, updateChainedRedirectEntries, serviceContext);
	}

	@Override
	public com.liferay.redirect.model.RedirectEntry deleteRedirectEntry(
			long redirectEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectEntryService.deleteRedirectEntry(redirectEntryId);
	}

	@Override
	public com.liferay.redirect.model.RedirectEntry fetchRedirectEntry(
			long redirectEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectEntryService.fetchRedirectEntry(redirectEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _redirectEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.redirect.model.RedirectEntry>
			getRedirectEntries(
				long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.redirect.model.RedirectEntry>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectEntryService.getRedirectEntries(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getRedirectEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectEntryService.getRedirectEntriesCount(groupId);
	}

	@Override
	public com.liferay.redirect.model.RedirectEntry updateRedirectEntry(
			long redirectEntryId, String destinationURL,
			java.util.Date expirationDate, boolean permanent, String sourceURL)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectEntryService.updateRedirectEntry(
			redirectEntryId, destinationURL, expirationDate, permanent,
			sourceURL);
	}

	@Override
	public com.liferay.redirect.model.RedirectEntry updateRedirectEntry(
			long redirectEntryId, String destinationURL,
			java.util.Date expirationDate, String groupBaseURL,
			boolean permanent, String sourceURL,
			boolean updateChainedRedirectEntries)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectEntryService.updateRedirectEntry(
			redirectEntryId, destinationURL, expirationDate, groupBaseURL,
			permanent, sourceURL, updateChainedRedirectEntries);
	}

	@Override
	public RedirectEntryService getWrappedService() {
		return _redirectEntryService;
	}

	@Override
	public void setWrappedService(RedirectEntryService redirectEntryService) {
		_redirectEntryService = redirectEntryService;
	}

	private RedirectEntryService _redirectEntryService;

}