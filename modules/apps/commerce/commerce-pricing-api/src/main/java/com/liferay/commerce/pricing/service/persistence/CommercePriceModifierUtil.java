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

package com.liferay.commerce.pricing.service.persistence;

import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the commerce price modifier service. This utility wraps <code>com.liferay.commerce.pricing.service.persistence.impl.CommercePriceModifierPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommercePriceModifierPersistence
 * @generated
 */
public class CommercePriceModifierUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CommercePriceModifier commercePriceModifier) {
		getPersistence().clearCache(commercePriceModifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CommercePriceModifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommercePriceModifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommercePriceModifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommercePriceModifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommercePriceModifier update(
		CommercePriceModifier commercePriceModifier) {

		return getPersistence().update(commercePriceModifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommercePriceModifier update(
		CommercePriceModifier commercePriceModifier,
		ServiceContext serviceContext) {

		return getPersistence().update(commercePriceModifier, serviceContext);
	}

	/**
	 * Returns all the commerce price modifiers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the commerce price modifiers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByUuid_First(
			String uuid,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByUuid_First(
		String uuid,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByUuid_Last(
			String uuid,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByUuid_Last(
		String uuid,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where uuid = &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByUuid_PrevAndNext(
			long commercePriceModifierId, String uuid,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByUuid_PrevAndNext(
			commercePriceModifierId, uuid, orderByComparator);
	}

	/**
	 * Removes all the commerce price modifiers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of commerce price modifiers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the commerce price modifier where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchPriceModifierException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce price modifier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce price modifier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the commerce price modifier where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the commerce price modifier that was removed
	 */
	public static CommercePriceModifier removeByUUID_G(
			String uuid, long groupId)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of commerce price modifiers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the commerce price modifiers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce price modifiers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByUuid_C_PrevAndNext(
			long commercePriceModifierId, String uuid, long companyId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByUuid_C_PrevAndNext(
			commercePriceModifierId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the commerce price modifiers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of commerce price modifiers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the commerce price modifiers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the commerce price modifiers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByCompanyId_First(
			long companyId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where companyId = &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByCompanyId_PrevAndNext(
			long commercePriceModifierId, long companyId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByCompanyId_PrevAndNext(
			commercePriceModifierId, companyId, orderByComparator);
	}

	/**
	 * Removes all the commerce price modifiers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of commerce price modifiers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the commerce price modifiers where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByCommercePriceListId(
		long commercePriceListId) {

		return getPersistence().findByCommercePriceListId(commercePriceListId);
	}

	/**
	 * Returns a range of all the commerce price modifiers where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByCommercePriceListId(
		long commercePriceListId, int start, int end) {

		return getPersistence().findByCommercePriceListId(
			commercePriceListId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByCommercePriceListId(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByCommercePriceListId(
			commercePriceListId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByCommercePriceListId(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCommercePriceListId(
			commercePriceListId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByCommercePriceListId_First(
			long commercePriceListId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByCommercePriceListId_First(
			commercePriceListId, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByCommercePriceListId_First(
		long commercePriceListId,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByCommercePriceListId_First(
			commercePriceListId, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByCommercePriceListId_Last(
			long commercePriceListId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByCommercePriceListId_Last(
			commercePriceListId, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByCommercePriceListId_Last(
		long commercePriceListId,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByCommercePriceListId_Last(
			commercePriceListId, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByCommercePriceListId_PrevAndNext(
			long commercePriceModifierId, long commercePriceListId,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByCommercePriceListId_PrevAndNext(
			commercePriceModifierId, commercePriceListId, orderByComparator);
	}

	/**
	 * Removes all the commerce price modifiers where commercePriceListId = &#63; from the database.
	 *
	 * @param commercePriceListId the commerce price list ID
	 */
	public static void removeByCommercePriceListId(long commercePriceListId) {
		getPersistence().removeByCommercePriceListId(commercePriceListId);
	}

	/**
	 * Returns the number of commerce price modifiers where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByCommercePriceListId(long commercePriceListId) {
		return getPersistence().countByCommercePriceListId(commercePriceListId);
	}

	/**
	 * Returns all the commerce price modifiers where companyId = &#63; and target = &#63;.
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByC_T(
		long companyId, String target) {

		return getPersistence().findByC_T(companyId, target);
	}

	/**
	 * Returns a range of all the commerce price modifiers where companyId = &#63; and target = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByC_T(
		long companyId, String target, int start, int end) {

		return getPersistence().findByC_T(companyId, target, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where companyId = &#63; and target = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByC_T(
		long companyId, String target, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByC_T(
			companyId, target, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where companyId = &#63; and target = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByC_T(
		long companyId, String target, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_T(
			companyId, target, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where companyId = &#63; and target = &#63;.
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByC_T_First(
			long companyId, String target,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByC_T_First(
			companyId, target, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where companyId = &#63; and target = &#63;.
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByC_T_First(
		long companyId, String target,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByC_T_First(
			companyId, target, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where companyId = &#63; and target = &#63;.
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByC_T_Last(
			long companyId, String target,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByC_T_Last(
			companyId, target, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where companyId = &#63; and target = &#63;.
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByC_T_Last(
		long companyId, String target,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByC_T_Last(
			companyId, target, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where companyId = &#63; and target = &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param companyId the company ID
	 * @param target the target
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByC_T_PrevAndNext(
			long commercePriceModifierId, long companyId, String target,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByC_T_PrevAndNext(
			commercePriceModifierId, companyId, target, orderByComparator);
	}

	/**
	 * Removes all the commerce price modifiers where companyId = &#63; and target = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param target the target
	 */
	public static void removeByC_T(long companyId, String target) {
		getPersistence().removeByC_T(companyId, target);
	}

	/**
	 * Returns the number of commerce price modifiers where companyId = &#63; and target = &#63;.
	 *
	 * @param companyId the company ID
	 * @param target the target
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByC_T(long companyId, String target) {
		return getPersistence().countByC_T(companyId, target);
	}

	/**
	 * Returns all the commerce price modifiers where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByLtD_S(
		Date displayDate, int status) {

		return getPersistence().findByLtD_S(displayDate, status);
	}

	/**
	 * Returns a range of all the commerce price modifiers where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByLtD_S(
		Date displayDate, int status, int start, int end) {

		return getPersistence().findByLtD_S(displayDate, status, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByLtD_S(
			displayDate, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLtD_S(
			displayDate, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByLtD_S_First(
			Date displayDate, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByLtD_S_First(
			displayDate, status, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByLtD_S_First(
		Date displayDate, int status,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByLtD_S_First(
			displayDate, status, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByLtD_S_Last(
			Date displayDate, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByLtD_S_Last(
			displayDate, status, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByLtD_S_Last(
		Date displayDate, int status,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByLtD_S_Last(
			displayDate, status, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByLtD_S_PrevAndNext(
			long commercePriceModifierId, Date displayDate, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByLtD_S_PrevAndNext(
			commercePriceModifierId, displayDate, status, orderByComparator);
	}

	/**
	 * Removes all the commerce price modifiers where displayDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 */
	public static void removeByLtD_S(Date displayDate, int status) {
		getPersistence().removeByLtD_S(displayDate, status);
	}

	/**
	 * Returns the number of commerce price modifiers where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByLtD_S(Date displayDate, int status) {
		return getPersistence().countByLtD_S(displayDate, status);
	}

	/**
	 * Returns all the commerce price modifiers where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByLtE_S(
		Date expirationDate, int status) {

		return getPersistence().findByLtE_S(expirationDate, status);
	}

	/**
	 * Returns a range of all the commerce price modifiers where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByLtE_S(
		Date expirationDate, int status, int start, int end) {

		return getPersistence().findByLtE_S(expirationDate, status, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByLtE_S(
		Date expirationDate, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByLtE_S(
			expirationDate, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByLtE_S(
		Date expirationDate, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLtE_S(
			expirationDate, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByLtE_S_First(
			Date expirationDate, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByLtE_S_First(
			expirationDate, status, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByLtE_S_First(
		Date expirationDate, int status,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByLtE_S_First(
			expirationDate, status, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByLtE_S_Last(
			Date expirationDate, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByLtE_S_Last(
			expirationDate, status, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByLtE_S_Last(
		Date expirationDate, int status,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByLtE_S_Last(
			expirationDate, status, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByLtE_S_PrevAndNext(
			long commercePriceModifierId, Date expirationDate, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByLtE_S_PrevAndNext(
			commercePriceModifierId, expirationDate, status, orderByComparator);
	}

	/**
	 * Removes all the commerce price modifiers where expirationDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 */
	public static void removeByLtE_S(Date expirationDate, int status) {
		getPersistence().removeByLtE_S(expirationDate, status);
	}

	/**
	 * Returns the number of commerce price modifiers where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByLtE_S(Date expirationDate, int status) {
		return getPersistence().countByLtE_S(expirationDate, status);
	}

	/**
	 * Returns all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_S(
		long groupId, long companyId, int status) {

		return getPersistence().findByG_C_S(groupId, companyId, status);
	}

	/**
	 * Returns a range of all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_S(
		long groupId, long companyId, int status, int start, int end) {

		return getPersistence().findByG_C_S(
			groupId, companyId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_S(
		long groupId, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByG_C_S(
			groupId, companyId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_S(
		long groupId, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_S(
			groupId, companyId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByG_C_S_First(
			long groupId, long companyId, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByG_C_S_First(
			groupId, companyId, status, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByG_C_S_First(
		long groupId, long companyId, int status,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByG_C_S_First(
			groupId, companyId, status, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByG_C_S_Last(
			long groupId, long companyId, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByG_C_S_Last(
			groupId, companyId, status, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByG_C_S_Last(
		long groupId, long companyId, int status,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByG_C_S_Last(
			groupId, companyId, status, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByG_C_S_PrevAndNext(
			long commercePriceModifierId, long groupId, long companyId,
			int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByG_C_S_PrevAndNext(
			commercePriceModifierId, groupId, companyId, status,
			orderByComparator);
	}

	/**
	 * Returns all the commerce price modifiers where groupId = any &#63; and companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_S(
		long[] groupIds, long companyId, int status) {

		return getPersistence().findByG_C_S(groupIds, companyId, status);
	}

	/**
	 * Returns a range of all the commerce price modifiers where groupId = any &#63; and companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_S(
		long[] groupIds, long companyId, int status, int start, int end) {

		return getPersistence().findByG_C_S(
			groupIds, companyId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where groupId = any &#63; and companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_S(
		long[] groupIds, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByG_C_S(
			groupIds, companyId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_S(
		long[] groupIds, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_S(
			groupIds, companyId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 */
	public static void removeByG_C_S(long groupId, long companyId, int status) {
		getPersistence().removeByG_C_S(groupId, companyId, status);
	}

	/**
	 * Returns the number of commerce price modifiers where groupId = &#63; and companyId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByG_C_S(long groupId, long companyId, int status) {
		return getPersistence().countByG_C_S(groupId, companyId, status);
	}

	/**
	 * Returns the number of commerce price modifiers where groupId = any &#63; and companyId = &#63; and status = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByG_C_S(
		long[] groupIds, long companyId, int status) {

		return getPersistence().countByG_C_S(groupIds, companyId, status);
	}

	/**
	 * Returns all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_NotS(
		long groupId, long companyId, int status) {

		return getPersistence().findByG_C_NotS(groupId, companyId, status);
	}

	/**
	 * Returns a range of all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_NotS(
		long groupId, long companyId, int status, int start, int end) {

		return getPersistence().findByG_C_NotS(
			groupId, companyId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_NotS(
		long groupId, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByG_C_NotS(
			groupId, companyId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_NotS(
		long groupId, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_NotS(
			groupId, companyId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByG_C_NotS_First(
			long groupId, long companyId, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByG_C_NotS_First(
			groupId, companyId, status, orderByComparator);
	}

	/**
	 * Returns the first commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByG_C_NotS_First(
		long groupId, long companyId, int status,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByG_C_NotS_First(
			groupId, companyId, status, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByG_C_NotS_Last(
			long groupId, long companyId, int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByG_C_NotS_Last(
			groupId, companyId, status, orderByComparator);
	}

	/**
	 * Returns the last commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByG_C_NotS_Last(
		long groupId, long companyId, int status,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().fetchByG_C_NotS_Last(
			groupId, companyId, status, orderByComparator);
	}

	/**
	 * Returns the commerce price modifiers before and after the current commerce price modifier in the ordered set where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * @param commercePriceModifierId the primary key of the current commerce price modifier
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier[] findByG_C_NotS_PrevAndNext(
			long commercePriceModifierId, long groupId, long companyId,
			int status,
			OrderByComparator<CommercePriceModifier> orderByComparator)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByG_C_NotS_PrevAndNext(
			commercePriceModifierId, groupId, companyId, status,
			orderByComparator);
	}

	/**
	 * Returns all the commerce price modifiers where groupId = any &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_NotS(
		long[] groupIds, long companyId, int status) {

		return getPersistence().findByG_C_NotS(groupIds, companyId, status);
	}

	/**
	 * Returns a range of all the commerce price modifiers where groupId = any &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_NotS(
		long[] groupIds, long companyId, int status, int start, int end) {

		return getPersistence().findByG_C_NotS(
			groupIds, companyId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where groupId = any &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_NotS(
		long[] groupIds, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findByG_C_NotS(
			groupIds, companyId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status &ne; &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price modifiers
	 */
	public static List<CommercePriceModifier> findByG_C_NotS(
		long[] groupIds, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_NotS(
			groupIds, companyId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the commerce price modifiers where groupId = &#63; and companyId = &#63; and status &ne; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 */
	public static void removeByG_C_NotS(
		long groupId, long companyId, int status) {

		getPersistence().removeByG_C_NotS(groupId, companyId, status);
	}

	/**
	 * Returns the number of commerce price modifiers where groupId = &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByG_C_NotS(
		long groupId, long companyId, int status) {

		return getPersistence().countByG_C_NotS(groupId, companyId, status);
	}

	/**
	 * Returns the number of commerce price modifiers where groupId = any &#63; and companyId = &#63; and status &ne; &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByG_C_NotS(
		long[] groupIds, long companyId, int status) {

		return getPersistence().countByG_C_NotS(groupIds, companyId, status);
	}

	/**
	 * Returns the commerce price modifier where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchPriceModifierException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce price modifier
	 * @throws NoSuchPriceModifierException if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier findByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce price modifier where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().fetchByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce price modifier where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	public static CommercePriceModifier fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		return getPersistence().fetchByC_ERC(
			companyId, externalReferenceCode, useFinderCache);
	}

	/**
	 * Removes the commerce price modifier where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce price modifier that was removed
	 */
	public static CommercePriceModifier removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().removeByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the number of commerce price modifiers where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce price modifiers
	 */
	public static int countByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().countByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Caches the commerce price modifier in the entity cache if it is enabled.
	 *
	 * @param commercePriceModifier the commerce price modifier
	 */
	public static void cacheResult(
		CommercePriceModifier commercePriceModifier) {

		getPersistence().cacheResult(commercePriceModifier);
	}

	/**
	 * Caches the commerce price modifiers in the entity cache if it is enabled.
	 *
	 * @param commercePriceModifiers the commerce price modifiers
	 */
	public static void cacheResult(
		List<CommercePriceModifier> commercePriceModifiers) {

		getPersistence().cacheResult(commercePriceModifiers);
	}

	/**
	 * Creates a new commerce price modifier with the primary key. Does not add the commerce price modifier to the database.
	 *
	 * @param commercePriceModifierId the primary key for the new commerce price modifier
	 * @return the new commerce price modifier
	 */
	public static CommercePriceModifier create(long commercePriceModifierId) {
		return getPersistence().create(commercePriceModifierId);
	}

	/**
	 * Removes the commerce price modifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceModifierId the primary key of the commerce price modifier
	 * @return the commerce price modifier that was removed
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier remove(long commercePriceModifierId)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().remove(commercePriceModifierId);
	}

	public static CommercePriceModifier updateImpl(
		CommercePriceModifier commercePriceModifier) {

		return getPersistence().updateImpl(commercePriceModifier);
	}

	/**
	 * Returns the commerce price modifier with the primary key or throws a <code>NoSuchPriceModifierException</code> if it could not be found.
	 *
	 * @param commercePriceModifierId the primary key of the commerce price modifier
	 * @return the commerce price modifier
	 * @throws NoSuchPriceModifierException if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier findByPrimaryKey(
			long commercePriceModifierId)
		throws com.liferay.commerce.pricing.exception.
			NoSuchPriceModifierException {

		return getPersistence().findByPrimaryKey(commercePriceModifierId);
	}

	/**
	 * Returns the commerce price modifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commercePriceModifierId the primary key of the commerce price modifier
	 * @return the commerce price modifier, or <code>null</code> if a commerce price modifier with the primary key could not be found
	 */
	public static CommercePriceModifier fetchByPrimaryKey(
		long commercePriceModifierId) {

		return getPersistence().fetchByPrimaryKey(commercePriceModifierId);
	}

	/**
	 * Returns all the commerce price modifiers.
	 *
	 * @return the commerce price modifiers
	 */
	public static List<CommercePriceModifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce price modifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of commerce price modifiers
	 */
	public static List<CommercePriceModifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce price modifiers
	 */
	public static List<CommercePriceModifier> findAll(
		int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price modifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce price modifiers
	 */
	public static List<CommercePriceModifier> findAll(
		int start, int end,
		OrderByComparator<CommercePriceModifier> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce price modifiers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce price modifiers.
	 *
	 * @return the number of commerce price modifiers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommercePriceModifierPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CommercePriceModifierPersistence _persistence;

}