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

import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel;
import com.liferay.commerce.pricing.service.base.CommercePricingClassCPDefinitionRelServiceBaseImpl;
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
public class CommercePricingClassCPDefinitionRelServiceImpl
	extends CommercePricingClassCPDefinitionRelServiceBaseImpl {

	@Override
	public CommercePricingClassCPDefinitionRel
			addCommercePricingClassCPDefinitionRel(
				long commercePricingClassId, long cpDefinitionId,
				ServiceContext serviceContext)
		throws PortalException {

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(), commercePricingClassId, ActionKeys.UPDATE);

		return commercePricingClassCPDefinitionRelLocalService.
			addCommercePricingClassCPDefinitionRel(
				commercePricingClassId, cpDefinitionId, serviceContext);
	}

	@Override
	public CommercePricingClassCPDefinitionRel
			deleteCommercePricingClassCPDefinitionRel(
				CommercePricingClassCPDefinitionRel
					commercePricingClassCPDefinitionRel)
		throws PortalException {

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(),
			commercePricingClassCPDefinitionRel.getCommercePricingClassId(),
			ActionKeys.UPDATE);

		return commercePricingClassCPDefinitionRelLocalService.
			deleteCommercePricingClassCPDefinitionRel(
				commercePricingClassCPDefinitionRel);
	}

	@Override
	public CommercePricingClassCPDefinitionRel
			deleteCommercePricingClassCPDefinitionRel(
				long commercePricingClassCPDefinitionRelId)
		throws PortalException {

		CommercePricingClassCPDefinitionRel
			commercePricingClassCPDefinitionRel =
				commercePricingClassCPDefinitionRelLocalService.
					getCommercePricingClassCPDefinitionRel(
						commercePricingClassCPDefinitionRelId);

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(),
			commercePricingClassCPDefinitionRel.getCommercePricingClassId(),
			ActionKeys.UPDATE);

		return commercePricingClassCPDefinitionRelLocalService.
			deleteCommercePricingClassCPDefinitionRel(
				commercePricingClassCPDefinitionRelId);
	}

	@Override
	public CommercePricingClassCPDefinitionRel
			fetchCommercePricingClassCPDefinitionRel(
				long commercePricingClassId, long cpDefinitionId)
		throws PortalException {

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(), commercePricingClassId, ActionKeys.VIEW);

		return commercePricingClassCPDefinitionRelLocalService.
			fetchCommercePricingClassCPDefinitionRel(
				commercePricingClassId, cpDefinitionId);
	}

	@Override
	public CommercePricingClassCPDefinitionRel
			getCommercePricingClassCPDefinitionRel(
				long commercePricingClassCPDefinitionRelId)
		throws PortalException {

		CommercePricingClassCPDefinitionRel
			commercePricingClassCPDefinitionRel =
				commercePricingClassCPDefinitionRelLocalService.
					getCommercePricingClassCPDefinitionRel(
						commercePricingClassCPDefinitionRelId);

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(),
			commercePricingClassCPDefinitionRel.getCommercePricingClassId(),
			ActionKeys.VIEW);

		return commercePricingClassCPDefinitionRelLocalService.
			fetchCommercePricingClassCPDefinitionRel(
				commercePricingClassCPDefinitionRelId);
	}

	@Override
	public List<CommercePricingClassCPDefinitionRel>
			getCommercePricingClassCPDefinitionRelByClassId(
				long commercePricingClassId)
		throws PortalException {

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(), commercePricingClassId, ActionKeys.VIEW);

		return commercePricingClassCPDefinitionRelLocalService.
			getCommercePricingClassCPDefinitionRels(commercePricingClassId);
	}

	@Override
	public List<CommercePricingClassCPDefinitionRel>
			getCommercePricingClassCPDefinitionRels(
				long commercePricingClassId, int start, int end,
				OrderByComparator<CommercePricingClassCPDefinitionRel>
					orderByComparator)
		throws PortalException {

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(), commercePricingClassId, ActionKeys.VIEW);

		return commercePricingClassCPDefinitionRelLocalService.
			getCommercePricingClassCPDefinitionRels(
				commercePricingClassId, start, end, orderByComparator);
	}

	@Override
	public int getCommercePricingClassCPDefinitionRelsCount(
			long commercePricingClassId)
		throws PortalException {

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(), commercePricingClassId, ActionKeys.VIEW);

		return commercePricingClassCPDefinitionRelLocalService.
			getCommercePricingClassCPDefinitionRelsCount(
				commercePricingClassId);
	}

	@Override
	public int getCommercePricingClassCPDefinitionRelsCount(
		long commercePricingClassId, String name, String languageId) {

		return commercePricingClassCPDefinitionRelFinder.
			countByCommercePricingClassId(
				commercePricingClassId, name, languageId, true);
	}

	@Override
	public long[] getCPDefinitionIds(long commercePricingClassId)
		throws PortalException {

		_commercePricingClassResourcePermission.check(
			getPermissionChecker(), commercePricingClassId, ActionKeys.VIEW);

		return commercePricingClassCPDefinitionRelLocalService.
			getCPDefinitionIds(commercePricingClassId);
	}

	@Override
	public List<CommercePricingClassCPDefinitionRel>
			searchByCommercePricingClassId(
				long commercePricingClassId, String name, String languageId,
				int start, int end)
		throws PortalException {

		return commercePricingClassCPDefinitionRelFinder.
			findByCommercePricingClassId(
				commercePricingClassId, name, languageId, start, end, true);
	}

	private static volatile ModelResourcePermission<CommercePricingClass>
		_commercePricingClassResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommercePricingClassCPDefinitionRelServiceImpl.class,
				"_commercePricingClassResourcePermission",
				CommercePricingClass.class);

}