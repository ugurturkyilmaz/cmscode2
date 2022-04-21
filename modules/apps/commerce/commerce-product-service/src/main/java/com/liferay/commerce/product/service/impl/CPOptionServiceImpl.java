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
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.base.CPOptionServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPOptionServiceImpl extends CPOptionServiceBaseImpl {

	@Override
	public CPOption addCPOption(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String ddmFormFieldTypeName, boolean facetable, boolean required,
			boolean skuContributor, String key, ServiceContext serviceContext)
		throws PortalException {

		PortletResourcePermission portletResourcePermission =
			_cpOptionModelResourcePermission.getPortletResourcePermission();

		portletResourcePermission.check(
			getPermissionChecker(), null,
			CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION);

		return cpOptionLocalService.addCPOption(
			null, getUserId(), nameMap, descriptionMap, ddmFormFieldTypeName,
			facetable, required, skuContributor, key, serviceContext);
	}

	@Override
	public CPOption addOrUpdateCPOption(
			String externalReferenceCode, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String ddmFormFieldTypeName,
			boolean facetable, boolean required, boolean skuContributor,
			String key, ServiceContext serviceContext)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchByExternalReferenceCode(
			externalReferenceCode, serviceContext.getCompanyId());

		if (cpOption == null) {
			PortletResourcePermission portletResourcePermission =
				_cpOptionModelResourcePermission.getPortletResourcePermission();

			portletResourcePermission.check(
				getPermissionChecker(), null,
				CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION);
		}

		return cpOptionLocalService.addOrUpdateCPOption(
			externalReferenceCode, getUserId(), nameMap, descriptionMap,
			ddmFormFieldTypeName, facetable, required, skuContributor, key,
			serviceContext);
	}

	@Override
	public void deleteCPOption(long cpOptionId) throws PortalException {
		_cpOptionModelResourcePermission.check(
			getPermissionChecker(), cpOptionId, ActionKeys.DELETE);

		cpOptionLocalService.deleteCPOption(cpOptionId);
	}

	@Override
	public CPOption fetchByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchByExternalReferenceCode(
			externalReferenceCode, companyId);

		if (cpOption != null) {
			_cpOptionModelResourcePermission.check(
				getPermissionChecker(), cpOption, ActionKeys.VIEW);
		}

		return cpOption;
	}

	@Override
	public CPOption fetchCPOption(long cpOptionId) throws PortalException {
		CPOption cpOption = cpOptionLocalService.fetchCPOption(cpOptionId);

		if (cpOption != null) {
			_cpOptionModelResourcePermission.check(
				getPermissionChecker(), cpOption, ActionKeys.VIEW);
		}

		return cpOption;
	}

	@Override
	public CPOption fetchCPOption(long companyId, String key)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchCPOption(companyId, key);

		if (cpOption != null) {
			_cpOptionModelResourcePermission.check(
				getPermissionChecker(), cpOption, ActionKeys.VIEW);
		}

		return cpOption;
	}

	@Override
	public List<CPOption> findCPOptionByCompanyId(
			long companyId, int start, int end,
			OrderByComparator<CPOption> orderByComparator)
		throws PortalException {

		return cpOptionLocalService.findCPOptionByCompanyId(
			companyId, start, end, orderByComparator);
	}

	@Override
	public CPOption getCPOption(long cpOptionId) throws PortalException {
		_cpOptionModelResourcePermission.check(
			getPermissionChecker(), cpOptionId, ActionKeys.VIEW);

		return cpOptionLocalService.getCPOption(cpOptionId);
	}

	@Override
	public BaseModelSearchResult<CPOption> searchCPOptions(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		return cpOptionLocalService.searchCPOptions(
			companyId, keywords, start, end, sort);
	}

	@Override
	public CPOption updateCPOption(
			long cpOptionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String ddmFormFieldTypeName,
			boolean facetable, boolean required, boolean skuContributor,
			String key, ServiceContext serviceContext)
		throws PortalException {

		_cpOptionModelResourcePermission.check(
			getPermissionChecker(), cpOptionId, ActionKeys.UPDATE);

		return cpOptionLocalService.updateCPOption(
			cpOptionId, nameMap, descriptionMap, ddmFormFieldTypeName,
			facetable, required, skuContributor, key, serviceContext);
	}

	@Override
	public CPOption updateCPOptionExternalReferenceCode(
			String externalReferenceCode, long cpOptionId)
		throws PortalException {

		_cpOptionModelResourcePermission.check(
			getPermissionChecker(), cpOptionId, ActionKeys.UPDATE);

		return cpOptionLocalService.updateCPOptionExternalReferenceCode(
			externalReferenceCode, cpOptionId);
	}

	private static volatile ModelResourcePermission<CPOption>
		_cpOptionModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPOptionServiceImpl.class, "_cpOptionModelResourcePermission",
				CPOption.class);

}