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

package com.liferay.commerce.pricing.service;

import com.liferay.commerce.pricing.model.CommercePriceModifierRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommercePriceModifierRel. This utility wraps
 * <code>com.liferay.commerce.pricing.service.impl.CommercePriceModifierRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Riccardo Alberti
 * @see CommercePriceModifierRelService
 * @generated
 */
public class CommercePriceModifierRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.pricing.service.impl.CommercePriceModifierRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommercePriceModifierRel addCommercePriceModifierRel(
			long commercePriceModifierId, String className, long classPK,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommercePriceModifierRel(
			commercePriceModifierId, className, classPK, serviceContext);
	}

	public static void deleteCommercePriceModifierRel(
			long commercePriceModifierRelId)
		throws PortalException {

		getService().deleteCommercePriceModifierRel(commercePriceModifierRelId);
	}

	public static CommercePriceModifierRel fetchCommercePriceModifierRel(
			long commercePriceModifierId, String className, long classPK)
		throws PortalException {

		return getService().fetchCommercePriceModifierRel(
			commercePriceModifierId, className, classPK);
	}

	public static List<CommercePriceModifierRel>
		getCategoriesCommercePriceModifierRels(
			long commercePriceModifierId, String name, int start, int end) {

		return getService().getCategoriesCommercePriceModifierRels(
			commercePriceModifierId, name, start, end);
	}

	public static int getCategoriesCommercePriceModifierRelsCount(
		long commercePriceModifierId, String name) {

		return getService().getCategoriesCommercePriceModifierRelsCount(
			commercePriceModifierId, name);
	}

	public static long[] getClassPKs(
			long commercePriceModifierRelId, String className)
		throws PortalException {

		return getService().getClassPKs(commercePriceModifierRelId, className);
	}

	public static CommercePriceModifierRel getCommercePriceModifierRel(
			long commercePriceModifierRelId)
		throws PortalException {

		return getService().getCommercePriceModifierRel(
			commercePriceModifierRelId);
	}

	public static List<CommercePriceModifierRel> getCommercePriceModifierRels(
			long commercePriceModifierRelId, String className)
		throws PortalException {

		return getService().getCommercePriceModifierRels(
			commercePriceModifierRelId, className);
	}

	public static List<CommercePriceModifierRel> getCommercePriceModifierRels(
			long commercePriceModifierRelId, String className, int start,
			int end,
			OrderByComparator<CommercePriceModifierRel> orderByComparator)
		throws PortalException {

		return getService().getCommercePriceModifierRels(
			commercePriceModifierRelId, className, start, end,
			orderByComparator);
	}

	public static int getCommercePriceModifierRelsCount(
			long commercePriceModifierRelId, String className)
		throws PortalException {

		return getService().getCommercePriceModifierRelsCount(
			commercePriceModifierRelId, className);
	}

	public static List<CommercePriceModifierRel> getCommercePriceModifiersRels(
		String className, long classPK) {

		return getService().getCommercePriceModifiersRels(className, classPK);
	}

	public static List<CommercePriceModifierRel>
		getCommercePricingClassesCommercePriceModifierRels(
			long commercePriceModifierId, String title, int start, int end) {

		return getService().getCommercePricingClassesCommercePriceModifierRels(
			commercePriceModifierId, title, start, end);
	}

	public static int getCommercePricingClassesCommercePriceModifierRelsCount(
		long commercePriceModifierId, String title) {

		return getService().
			getCommercePricingClassesCommercePriceModifierRelsCount(
				commercePriceModifierId, title);
	}

	public static List<CommercePriceModifierRel>
		getCPDefinitionsCommercePriceModifierRels(
			long commercePriceModifierId, String name, String languageId,
			int start, int end) {

		return getService().getCPDefinitionsCommercePriceModifierRels(
			commercePriceModifierId, name, languageId, start, end);
	}

	public static int getCPDefinitionsCommercePriceModifierRelsCount(
		long commercePriceModifierId, String name, String languageId) {

		return getService().getCPDefinitionsCommercePriceModifierRelsCount(
			commercePriceModifierId, name, languageId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommercePriceModifierRelService getService() {
		return _service;
	}

	private static volatile CommercePriceModifierRelService _service;

}