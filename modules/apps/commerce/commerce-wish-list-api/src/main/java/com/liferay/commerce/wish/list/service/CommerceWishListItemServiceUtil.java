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

package com.liferay.commerce.wish.list.service;

import com.liferay.commerce.wish.list.model.CommerceWishListItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceWishListItem. This utility wraps
 * <code>com.liferay.commerce.wish.list.service.impl.CommerceWishListItemServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Andrea Di Giorgi
 * @see CommerceWishListItemService
 * @generated
 */
public class CommerceWishListItemServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.wish.list.service.impl.CommerceWishListItemServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceWishListItem addCommerceWishListItem(
			long commerceAccountId, long commerceWishListId, long cProductId,
			String cpInstanceUuid, String json,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceWishListItem(
			commerceAccountId, commerceWishListId, cProductId, cpInstanceUuid,
			json, serviceContext);
	}

	public static void deleteCommerceWishListItem(long commerceWishListItemId)
		throws PortalException {

		getService().deleteCommerceWishListItem(commerceWishListItemId);
	}

	public static CommerceWishListItem getCommerceWishListItem(
			long commerceWishListItemId)
		throws PortalException {

		return getService().getCommerceWishListItem(commerceWishListItemId);
	}

	public static CommerceWishListItem getCommerceWishListItem(
			long commerceWishListId, String cpInstanceUuid, long cProductId)
		throws PortalException {

		return getService().getCommerceWishListItem(
			commerceWishListId, cpInstanceUuid, cProductId);
	}

	public static int getCommerceWishListItemByContainsCPInstanceCount(
			long commerceWishListId, String cpInstanceUuid)
		throws PortalException {

		return getService().getCommerceWishListItemByContainsCPInstanceCount(
			commerceWishListId, cpInstanceUuid);
	}

	public static int getCommerceWishListItemByContainsCProductCount(
			long commerceWishListId, long cProductId)
		throws PortalException {

		return getService().getCommerceWishListItemByContainsCProductCount(
			commerceWishListId, cProductId);
	}

	public static List<CommerceWishListItem> getCommerceWishListItems(
			long commerceWishListId, int start, int end,
			OrderByComparator<CommerceWishListItem> orderByComparator)
		throws PortalException {

		return getService().getCommerceWishListItems(
			commerceWishListId, start, end, orderByComparator);
	}

	public static int getCommerceWishListItemsCount(long commerceWishListId)
		throws PortalException {

		return getService().getCommerceWishListItemsCount(commerceWishListId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceWishListItemService getService() {
		return _service;
	}

	private static volatile CommerceWishListItemService _service;

}