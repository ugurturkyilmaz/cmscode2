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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.base.CPTaxCategoryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 */
public class CPTaxCategoryServiceImpl extends CPTaxCategoryServiceBaseImpl {

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #addCPTaxCategory(String, Map, Map, ServiceContext)}
	 */
	@Deprecated
	@Override
	public CPTaxCategory addCPTaxCategory(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.addCPTaxCategory(
			nameMap, descriptionMap, serviceContext);
	}

	@Override
	public CPTaxCategory addCPTaxCategory(
			String externalReferenceCode, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.addCPTaxCategory(
			externalReferenceCode, nameMap, descriptionMap, serviceContext);
	}

	@Override
	public int countCPTaxCategoriesByCompanyId(long companyId, String keyword)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.countCPTaxCategoriesByCompanyId(
			companyId, keyword);
	}

	@Override
	public void deleteCPTaxCategory(long cpTaxCategoryId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		cpTaxCategoryLocalService.deleteCPTaxCategory(cpTaxCategoryId);
	}

	@Override
	public List<CPTaxCategory> findCPTaxCategoriesByCompanyId(
			long companyId, String keyword, int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.findCPTaxCategoriesByCompanyId(
			companyId, keyword, start, end);
	}

	@Override
	public List<CPTaxCategory> getCPTaxCategories(long companyId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.getCPTaxCategories(companyId);
	}

	@Override
	public List<CPTaxCategory> getCPTaxCategories(
			long companyId, int start, int end,
			OrderByComparator<CPTaxCategory> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.getCPTaxCategories(
			companyId, start, end, orderByComparator);
	}

	@Override
	public int getCPTaxCategoriesCount(long companyId) throws PortalException {
		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.getCPTaxCategoriesCount(companyId);
	}

	@Override
	public CPTaxCategory getCPTaxCategory(long cpTaxCategoryId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.getCPTaxCategory(cpTaxCategoryId);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #updateCPTaxCategory(String, long, Map, Map)}
	 */
	@Deprecated
	@Override
	public CPTaxCategory updateCPTaxCategory(
			long cpTaxCategoryId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.updateCPTaxCategory(
			cpTaxCategoryId, nameMap, descriptionMap);
	}

	@Override
	public CPTaxCategory updateCPTaxCategory(
			String externalReferenceCode, long cpTaxCategoryId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.updateCPTaxCategory(
			externalReferenceCode, cpTaxCategoryId, nameMap, descriptionMap);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CPTaxCategoryServiceImpl.class, "_portletResourcePermission",
				CPConstants.RESOURCE_NAME_TAX);

}