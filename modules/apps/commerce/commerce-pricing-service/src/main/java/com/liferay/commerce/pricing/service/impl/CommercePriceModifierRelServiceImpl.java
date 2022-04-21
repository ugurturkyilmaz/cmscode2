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

package com.liferay.commerce.pricing.service.impl;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.model.CommercePriceModifierRel;
import com.liferay.commerce.pricing.service.base.CommercePriceModifierRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Riccardo Alberti
 */
public class CommercePriceModifierRelServiceImpl
	extends CommercePriceModifierRelServiceBaseImpl {

	@Override
	public CommercePriceModifierRel addCommercePriceModifierRel(
			long commercePriceModifierId, String className, long classPK,
			ServiceContext serviceContext)
		throws PortalException {

		CommercePriceModifier commercePriceModifier =
			commercePriceModifierLocalService.getCommercePriceModifier(
				commercePriceModifierId);

		_commercePriceListModelResourcePermission.check(
			getPermissionChecker(),
			commercePriceModifier.getCommercePriceListId(), ActionKeys.UPDATE);

		return commercePriceModifierRelLocalService.addCommercePriceModifierRel(
			commercePriceModifierId, className, classPK, serviceContext);
	}

	@Override
	public void deleteCommercePriceModifierRel(long commercePriceModifierRelId)
		throws PortalException {

		CommercePriceModifierRel commercePriceModifierRel =
			commercePriceModifierRelLocalService.getCommercePriceModifierRel(
				commercePriceModifierRelId);

		CommercePriceModifier commercePriceModifier =
			commercePriceModifierRel.getCommercePriceModifier();

		_commercePriceListModelResourcePermission.check(
			getPermissionChecker(),
			commercePriceModifier.getCommercePriceListId(), ActionKeys.UPDATE);

		commercePriceModifierRelLocalService.deleteCommercePriceModifierRel(
			commercePriceModifierRel);
	}

	@Override
	public CommercePriceModifierRel fetchCommercePriceModifierRel(
			long commercePriceModifierId, String className, long classPK)
		throws PortalException {

		CommercePriceModifierRel commercePriceModifierRel =
			commercePriceModifierRelLocalService.fetchCommercePriceModifierRel(
				commercePriceModifierId, className, classPK);

		CommercePriceModifier commercePriceModifier =
			commercePriceModifierRel.getCommercePriceModifier();

		_commercePriceListModelResourcePermission.check(
			getPermissionChecker(),
			commercePriceModifier.getCommercePriceListId(), ActionKeys.VIEW);

		return commercePriceModifierRel;
	}

	@Override
	public List<CommercePriceModifierRel>
		getCategoriesCommercePriceModifierRels(
			long commercePriceModifierId, String name, int start, int end) {

		return commercePriceModifierRelFinder.
			findCategoriesByCommercePriceModifierId(
				commercePriceModifierId, name, start, end, true);
	}

	@Override
	public int getCategoriesCommercePriceModifierRelsCount(
		long commercePriceModifierId, String name) {

		return commercePriceModifierRelFinder.
			countCategoriesByCommercePriceModifierId(
				commercePriceModifierId, name, true);
	}

	@Override
	public long[] getClassPKs(long commercePriceModifierRelId, String className)
		throws PortalException {

		CommercePriceModifierRel commercePriceModifierRel =
			commercePriceModifierRelLocalService.getCommercePriceModifierRel(
				commercePriceModifierRelId);

		CommercePriceModifier commercePriceModifier =
			commercePriceModifierRel.getCommercePriceModifier();

		_commercePriceListModelResourcePermission.check(
			getPermissionChecker(),
			commercePriceModifier.getCommercePriceListId(), ActionKeys.VIEW);

		return commercePriceModifierRelLocalService.getClassPKs(
			commercePriceModifierRelId, className);
	}

	@Override
	public CommercePriceModifierRel getCommercePriceModifierRel(
			long commercePriceModifierRelId)
		throws PortalException {

		CommercePriceModifierRel commercePriceModifierRel =
			commercePriceModifierRelLocalService.getCommercePriceModifierRel(
				commercePriceModifierRelId);

		CommercePriceModifier commercePriceModifier =
			commercePriceModifierRel.getCommercePriceModifier();

		_commercePriceListModelResourcePermission.check(
			getPermissionChecker(),
			commercePriceModifier.getCommercePriceListId(), ActionKeys.VIEW);

		return commercePriceModifierRel;
	}

	@Override
	public List<CommercePriceModifierRel> getCommercePriceModifierRels(
			long commercePriceModifierRelId, String className)
		throws PortalException {

		CommercePriceModifierRel commercePriceModifierRel =
			commercePriceModifierRelLocalService.getCommercePriceModifierRel(
				commercePriceModifierRelId);

		CommercePriceModifier commercePriceModifier =
			commercePriceModifierRel.getCommercePriceModifier();

		_commercePriceListModelResourcePermission.check(
			getPermissionChecker(),
			commercePriceModifier.getCommercePriceListId(), ActionKeys.VIEW);

		return commercePriceModifierRelLocalService.
			getCommercePriceModifierRels(commercePriceModifierRelId, className);
	}

	@Override
	public List<CommercePriceModifierRel> getCommercePriceModifierRels(
			long commercePriceModifierRelId, String className, int start,
			int end,
			OrderByComparator<CommercePriceModifierRel> orderByComparator)
		throws PortalException {

		CommercePriceModifierRel commercePriceModifierRel =
			commercePriceModifierRelLocalService.getCommercePriceModifierRel(
				commercePriceModifierRelId);

		CommercePriceModifier commercePriceModifier =
			commercePriceModifierRel.getCommercePriceModifier();

		_commercePriceListModelResourcePermission.check(
			getPermissionChecker(),
			commercePriceModifier.getCommercePriceListId(), ActionKeys.VIEW);

		return commercePriceModifierRelLocalService.
			getCommercePriceModifierRels(
				commercePriceModifierRelId, className, start, end,
				orderByComparator);
	}

	@Override
	public int getCommercePriceModifierRelsCount(
			long commercePriceModifierRelId, String className)
		throws PortalException {

		CommercePriceModifierRel commercePriceModifierRel =
			commercePriceModifierRelLocalService.getCommercePriceModifierRel(
				commercePriceModifierRelId);

		CommercePriceModifier commercePriceModifier =
			commercePriceModifierRel.getCommercePriceModifier();

		_commercePriceListModelResourcePermission.check(
			getPermissionChecker(),
			commercePriceModifier.getCommercePriceListId(), ActionKeys.VIEW);

		return commercePriceModifierRelLocalService.
			getCommercePriceModifierRelsCount(
				commercePriceModifierRelId, className);
	}

	@Override
	public List<CommercePriceModifierRel> getCommercePriceModifiersRels(
		String className, long classPK) {

		return commercePriceModifierRelPersistence.findByCN_CPK(
			classNameLocalService.getClassNameId(className), classPK);
	}

	@Override
	public List<CommercePriceModifierRel>
		getCommercePricingClassesCommercePriceModifierRels(
			long commercePriceModifierId, String title, int start, int end) {

		return commercePriceModifierRelFinder.
			findPricingClassesByCommercePriceModifierId(
				commercePriceModifierId, title, start, end, true);
	}

	@Override
	public int getCommercePricingClassesCommercePriceModifierRelsCount(
		long commercePriceModifierId, String title) {

		return commercePriceModifierRelFinder.
			countPricingClassesByCommercePriceModifierId(
				commercePriceModifierId, title, true);
	}

	@Override
	public List<CommercePriceModifierRel>
		getCPDefinitionsCommercePriceModifierRels(
			long commercePriceModifierId, String name, String languageId,
			int start, int end) {

		return commercePriceModifierRelFinder.
			findCPDefinitionsByCommercePriceModifierId(
				commercePriceModifierId, name, languageId, start, end, true);
	}

	@Override
	public int getCPDefinitionsCommercePriceModifierRelsCount(
		long commercePriceModifierId, String name, String languageId) {

		return commercePriceModifierRelFinder.
			countCPDefinitionsByCommercePriceModifierId(
				commercePriceModifierId, languageId, name, true);
	}

	private static volatile ModelResourcePermission<CommercePriceList>
		_commercePriceListModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommercePriceModifierServiceImpl.class,
				"_commercePriceListModelResourcePermission",
				CommercePriceList.class);

}