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

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for CPDefinitionOptionValueRel. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPDefinitionOptionValueRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPDefinitionOptionValueRelService
 * @generated
 */
public class CPDefinitionOptionValueRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionOptionValueRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CPDefinitionOptionValueRel addCPDefinitionOptionValueRel(
			long cpDefinitionOptionRelId, Map<java.util.Locale, String> nameMap,
			double priority, String key,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCPDefinitionOptionValueRel(
			cpDefinitionOptionRelId, nameMap, priority, key, serviceContext);
	}

	public static CPDefinitionOptionValueRel deleteCPDefinitionOptionValueRel(
			long cpDefinitionOptionValueRelId)
		throws PortalException {

		return getService().deleteCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRelId);
	}

	public static CPDefinitionOptionValueRel fetchCPDefinitionOptionValueRel(
			long cpDefinitionOptionValueRelId)
		throws PortalException {

		return getService().fetchCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRelId);
	}

	public static CPDefinitionOptionValueRel fetchCPDefinitionOptionValueRel(
			long cpDefinitionOptionRelId, String key)
		throws PortalException {

		return getService().fetchCPDefinitionOptionValueRel(
			cpDefinitionOptionRelId, key);
	}

	public static CPDefinitionOptionValueRel getCPDefinitionOptionValueRel(
			long cpDefinitionOptionValueRelId)
		throws PortalException {

		return getService().getCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRelId);
	}

	public static List<CPDefinitionOptionValueRel>
			getCPDefinitionOptionValueRels(
				long cpDefinitionOptionRelId, int start, int end)
		throws PortalException {

		return getService().getCPDefinitionOptionValueRels(
			cpDefinitionOptionRelId, start, end);
	}

	public static List<CPDefinitionOptionValueRel>
			getCPDefinitionOptionValueRels(
				long cpDefinitionOptionRelId, int start, int end,
				OrderByComparator<CPDefinitionOptionValueRel> orderByComparator)
		throws PortalException {

		return getService().getCPDefinitionOptionValueRels(
			cpDefinitionOptionRelId, start, end, orderByComparator);
	}

	public static List<CPDefinitionOptionValueRel>
			getCPDefinitionOptionValueRels(
				long groupId, String key, int start, int end)
		throws PortalException {

		return getService().getCPDefinitionOptionValueRels(
			groupId, key, start, end);
	}

	public static int getCPDefinitionOptionValueRelsCount(
			long cpDefinitionOptionRelId)
		throws PortalException {

		return getService().getCPDefinitionOptionValueRelsCount(
			cpDefinitionOptionRelId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CPDefinitionOptionValueRel
			resetCPInstanceCPDefinitionOptionValueRel(
				long cpDefinitionOptionValueRelId)
		throws PortalException {

		return getService().resetCPInstanceCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRelId);
	}

	/**
	 * @param companyId
	 * @param groupId
	 * @param cpDefinitionOptionRelId
	 * @param keywords
	 * @param start
	 * @param end
	 * @param sort
	 * @return
	 * @throws PortalException
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #searchCPDefinitionOptionValueRels(long, long, long, String,
	 int, int, Sort[])}
	 */
	@Deprecated
	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<CPDefinitionOptionValueRel> searchCPDefinitionOptionValueRels(
				long companyId, long groupId, long cpDefinitionOptionRelId,
				String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws PortalException {

		return getService().searchCPDefinitionOptionValueRels(
			companyId, groupId, cpDefinitionOptionRelId, keywords, start, end,
			sort);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<CPDefinitionOptionValueRel> searchCPDefinitionOptionValueRels(
				long companyId, long groupId, long cpDefinitionOptionRelId,
				String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort[] sorts)
			throws PortalException {

		return getService().searchCPDefinitionOptionValueRels(
			companyId, groupId, cpDefinitionOptionRelId, keywords, start, end,
			sorts);
	}

	public static int searchCPDefinitionOptionValueRelsCount(
			long companyId, long groupId, long cpDefinitionOptionRelId,
			String keywords)
		throws PortalException {

		return getService().searchCPDefinitionOptionValueRelsCount(
			companyId, groupId, cpDefinitionOptionRelId, keywords);
	}

	/**
	 * @param cpDefinitionOptionValueRelId
	 * @param nameMap
	 * @param priority
	 * @param key
	 * @param cpInstanceId
	 * @param quantity
	 * @param price
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @deprecated As of Athanasius (7.3.x), use {@link
	 #updateCPDefinitionOptionValueRel(long, Map, double, String,
	 long, int, boolean, BigDecimal, ServiceContext)}
	 */
	@Deprecated
	public static CPDefinitionOptionValueRel updateCPDefinitionOptionValueRel(
			long cpDefinitionOptionValueRelId,
			Map<java.util.Locale, String> nameMap, double priority, String key,
			long cpInstanceId, int quantity, java.math.BigDecimal price,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRelId, nameMap, priority, key, cpInstanceId,
			quantity, price, serviceContext);
	}

	public static CPDefinitionOptionValueRel updateCPDefinitionOptionValueRel(
			long cpDefinitionOptionValueRelId,
			Map<java.util.Locale, String> nameMap, double priority, String key,
			long cpInstanceId, int quantity, boolean preselected,
			java.math.BigDecimal price,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRelId, nameMap, priority, key, cpInstanceId,
			quantity, preselected, price, serviceContext);
	}

	/**
	 * @param cpDefinitionOptionValueRelId
	 * @param nameMap
	 * @param priority
	 * @param key
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @deprecated As of Athanasius (7.3.x), use {@link
	 #updateCPDefinitionOptionValueRel(long, Map, double, String,
	 long, int, boolean, BigDecimal, ServiceContext)}
	 */
	@Deprecated
	public static CPDefinitionOptionValueRel updateCPDefinitionOptionValueRel(
			long cpDefinitionOptionValueRelId,
			Map<java.util.Locale, String> nameMap, double priority, String key,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRelId, nameMap, priority, key,
			serviceContext);
	}

	public static CPDefinitionOptionValueRel
			updateCPDefinitionOptionValueRelPreselected(
				long cpDefinitionOptionValueRelId, boolean preselected)
		throws PortalException {

		return getService().updateCPDefinitionOptionValueRelPreselected(
			cpDefinitionOptionValueRelId, preselected);
	}

	public static CPDefinitionOptionValueRelService getService() {
		return _service;
	}

	private static volatile CPDefinitionOptionValueRelService _service;

}