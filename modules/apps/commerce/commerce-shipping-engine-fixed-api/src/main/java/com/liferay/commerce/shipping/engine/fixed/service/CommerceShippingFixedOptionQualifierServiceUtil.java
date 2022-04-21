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

package com.liferay.commerce.shipping.engine.fixed.service;

import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionQualifier;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceShippingFixedOptionQualifier. This utility wraps
 * <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionQualifierServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingFixedOptionQualifierService
 * @generated
 */
public class CommerceShippingFixedOptionQualifierServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionQualifierServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceShippingFixedOptionQualifier
			addCommerceShippingFixedOptionQualifier(
				String className, long classPK,
				long commerceShippingFixedOptionId)
		throws PortalException {

		return getService().addCommerceShippingFixedOptionQualifier(
			className, classPK, commerceShippingFixedOptionId);
	}

	public static void deleteCommerceShippingFixedOptionQualifier(
			long commerceShippingFixedOptionQualifierId)
		throws PortalException {

		getService().deleteCommerceShippingFixedOptionQualifier(
			commerceShippingFixedOptionQualifierId);
	}

	public static void deleteCommerceShippingFixedOptionQualifiers(
			long commerceShippingFixedOptionId)
		throws PortalException {

		getService().deleteCommerceShippingFixedOptionQualifiers(
			commerceShippingFixedOptionId);
	}

	public static void deleteCommerceShippingFixedOptionQualifiers(
			String className, long commerceShippingFixedOptionId)
		throws PortalException {

		getService().deleteCommerceShippingFixedOptionQualifiers(
			className, commerceShippingFixedOptionId);
	}

	public static CommerceShippingFixedOptionQualifier
			fetchCommerceShippingFixedOptionQualifier(
				String className, long classPK,
				long commerceShippingFixedOptionId)
		throws PortalException {

		return getService().fetchCommerceShippingFixedOptionQualifier(
			className, classPK, commerceShippingFixedOptionId);
	}

	public static List<CommerceShippingFixedOptionQualifier>
			getCommerceOrderTypeCommerceShippingFixedOptionQualifiers(
				long commerceShippingFixedOptionId, String keywords, int start,
				int end)
		throws PortalException {

		return getService().
			getCommerceOrderTypeCommerceShippingFixedOptionQualifiers(
				commerceShippingFixedOptionId, keywords, start, end);
	}

	public static int
			getCommerceOrderTypeCommerceShippingFixedOptionQualifiersCount(
				long commerceShippingFixedOptionId, String keywords)
		throws PortalException {

		return getService().
			getCommerceOrderTypeCommerceShippingFixedOptionQualifiersCount(
				commerceShippingFixedOptionId, keywords);
	}

	public static CommerceShippingFixedOptionQualifier
			getCommerceShippingFixedOptionQualifier(
				long commerceShippingFixedOptionQualifierId)
		throws PortalException {

		return getService().getCommerceShippingFixedOptionQualifier(
			commerceShippingFixedOptionQualifierId);
	}

	public static List<CommerceShippingFixedOptionQualifier>
			getCommerceShippingFixedOptionQualifiers(
				long commerceShippingFixedOptionId)
		throws PortalException {

		return getService().getCommerceShippingFixedOptionQualifiers(
			commerceShippingFixedOptionId);
	}

	public static List<CommerceShippingFixedOptionQualifier>
			getCommerceShippingFixedOptionQualifiers(
				long commerceShippingFixedOptionId, int start, int end,
				OrderByComparator<CommerceShippingFixedOptionQualifier>
					orderByComparator)
		throws PortalException {

		return getService().getCommerceShippingFixedOptionQualifiers(
			commerceShippingFixedOptionId, start, end, orderByComparator);
	}

	public static int getCommerceShippingFixedOptionQualifiersCount(
			long commerceShippingFixedOptionId)
		throws PortalException {

		return getService().getCommerceShippingFixedOptionQualifiersCount(
			commerceShippingFixedOptionId);
	}

	public static List<CommerceShippingFixedOptionQualifier>
			getCommerceTermEntryCommerceShippingFixedOptionQualifiers(
				long commerceShippingFixedOptionId, String keywords, int start,
				int end)
		throws PortalException {

		return getService().
			getCommerceTermEntryCommerceShippingFixedOptionQualifiers(
				commerceShippingFixedOptionId, keywords, start, end);
	}

	public static int
			getCommerceTermEntryCommerceShippingFixedOptionQualifiersCount(
				long commerceShippingFixedOptionId, String keywords)
		throws PortalException {

		return getService().
			getCommerceTermEntryCommerceShippingFixedOptionQualifiersCount(
				commerceShippingFixedOptionId, keywords);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceShippingFixedOptionQualifierService getService() {
		return _service;
	}

	private static volatile CommerceShippingFixedOptionQualifierService
		_service;

}