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

package com.liferay.commerce.price.list.service;

import com.liferay.commerce.price.list.model.CommercePriceListCommerceAccountGroupRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommercePriceListCommerceAccountGroupRel. This utility wraps
 * <code>com.liferay.commerce.price.list.service.impl.CommercePriceListCommerceAccountGroupRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListCommerceAccountGroupRelService
 * @generated
 */
public class CommercePriceListCommerceAccountGroupRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListCommerceAccountGroupRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommercePriceListCommerceAccountGroupRel
			addCommercePriceListCommerceAccountGroupRel(
				long commercePriceListId, long commerceAccountGroupId,
				int order,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommercePriceListCommerceAccountGroupRel(
			commercePriceListId, commerceAccountGroupId, order, serviceContext);
	}

	public static void
			deleteCommercePriceListAccountGroupRelsByCommercePriceListId(
				long commercePriceListId)
		throws PortalException {

		getService().
			deleteCommercePriceListAccountGroupRelsByCommercePriceListId(
				commercePriceListId);
	}

	public static void deleteCommercePriceListCommerceAccountGroupRel(
			long commercePriceListCommerceAccountGroupRelId)
		throws PortalException {

		getService().deleteCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId);
	}

	public static CommercePriceListCommerceAccountGroupRel
			fetchCommercePriceListCommerceAccountGroupRel(
				long commercePriceListId, long commerceAccountGroupId)
		throws PortalException {

		return getService().fetchCommercePriceListCommerceAccountGroupRel(
			commercePriceListId, commerceAccountGroupId);
	}

	public static CommercePriceListCommerceAccountGroupRel
			getCommercePriceListCommerceAccountGroupRel(
				long commercePriceListCommerceAccoungGroupRelId)
		throws PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccoungGroupRelId);
	}

	public static List<CommercePriceListCommerceAccountGroupRel>
			getCommercePriceListCommerceAccountGroupRels(
				long commercePriceListId)
		throws PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRels(
			commercePriceListId);
	}

	public static List<CommercePriceListCommerceAccountGroupRel>
			getCommercePriceListCommerceAccountGroupRels(
				long commercePriceListId, int start, int end,
				OrderByComparator<CommercePriceListCommerceAccountGroupRel>
					orderByComparator)
		throws PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRels(
			commercePriceListId, start, end, orderByComparator);
	}

	public static List<CommercePriceListCommerceAccountGroupRel>
		getCommercePriceListCommerceAccountGroupRels(
			long commercePriceListId, String name, int start, int end) {

		return getService().getCommercePriceListCommerceAccountGroupRels(
			commercePriceListId, name, start, end);
	}

	public static int getCommercePriceListCommerceAccountGroupRelsCount(
			long commercePriceListId)
		throws PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRelsCount(
			commercePriceListId);
	}

	public static int getCommercePriceListCommerceAccountGroupRelsCount(
		long commercePriceListId, String name) {

		return getService().getCommercePriceListCommerceAccountGroupRelsCount(
			commercePriceListId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommercePriceListCommerceAccountGroupRel
			updateCommercePriceListCommerceAccountGroupRel(
				long commercePriceListCommerceAccountGroupRelId, int order,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId, order, serviceContext);
	}

	public static CommercePriceListCommerceAccountGroupRelService getService() {
		return _service;
	}

	private static volatile CommercePriceListCommerceAccountGroupRelService
		_service;

}