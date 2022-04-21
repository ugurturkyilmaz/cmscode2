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

package com.liferay.commerce.discount.service;

import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceDiscountRule. This utility wraps
 * <code>com.liferay.commerce.discount.service.impl.CommerceDiscountRuleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceDiscountRuleService
 * @generated
 */
public class CommerceDiscountRuleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.discount.service.impl.CommerceDiscountRuleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceDiscountRule addCommerceDiscountRule(
			long commerceDiscountId, String type, String typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceDiscountRule(
			commerceDiscountId, type, typeSettings, serviceContext);
	}

	public static CommerceDiscountRule addCommerceDiscountRule(
			long commerceDiscountId, String name, String type,
			String typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceDiscountRule(
			commerceDiscountId, name, type, typeSettings, serviceContext);
	}

	public static void deleteCommerceDiscountRule(long commerceDiscountRuleId)
		throws PortalException {

		getService().deleteCommerceDiscountRule(commerceDiscountRuleId);
	}

	public static CommerceDiscountRule fetchCommerceDiscountRule(
			long commerceDiscountRuleId)
		throws PortalException {

		return getService().fetchCommerceDiscountRule(commerceDiscountRuleId);
	}

	public static CommerceDiscountRule getCommerceDiscountRule(
			long commerceDiscountRuleId)
		throws PortalException {

		return getService().getCommerceDiscountRule(commerceDiscountRuleId);
	}

	public static List<CommerceDiscountRule> getCommerceDiscountRules(
			long commerceDiscountId, int start, int end,
			OrderByComparator<CommerceDiscountRule> orderByComparator)
		throws PortalException {

		return getService().getCommerceDiscountRules(
			commerceDiscountId, start, end, orderByComparator);
	}

	public static List<CommerceDiscountRule> getCommerceDiscountRules(
			long commerceDiscountId, String name, int start, int end)
		throws PortalException {

		return getService().getCommerceDiscountRules(
			commerceDiscountId, name, start, end);
	}

	public static int getCommerceDiscountRulesCount(long commerceDiscountId)
		throws PortalException {

		return getService().getCommerceDiscountRulesCount(commerceDiscountId);
	}

	public static int getCommerceDiscountRulesCount(
			long commerceDiscountId, String name)
		throws PortalException {

		return getService().getCommerceDiscountRulesCount(
			commerceDiscountId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceDiscountRule updateCommerceDiscountRule(
			long commerceDiscountRuleId, String type, String typeSettings)
		throws PortalException {

		return getService().updateCommerceDiscountRule(
			commerceDiscountRuleId, type, typeSettings);
	}

	public static CommerceDiscountRule updateCommerceDiscountRule(
			long commerceDiscountRuleId, String name, String type,
			String typeSettings)
		throws PortalException {

		return getService().updateCommerceDiscountRule(
			commerceDiscountRuleId, name, type, typeSettings);
	}

	public static CommerceDiscountRuleService getService() {
		return _service;
	}

	private static volatile CommerceDiscountRuleService _service;

}