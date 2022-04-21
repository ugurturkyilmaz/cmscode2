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

package com.liferay.asset.list.service.persistence;

import com.liferay.asset.list.model.AssetListEntryUsage;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the asset list entry usage service. This utility wraps <code>com.liferay.asset.list.service.persistence.impl.AssetListEntryUsagePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryUsagePersistence
 * @generated
 */
public class AssetListEntryUsageUtil {

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
	public static void clearCache(AssetListEntryUsage assetListEntryUsage) {
		getPersistence().clearCache(assetListEntryUsage);
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
	public static Map<Serializable, AssetListEntryUsage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetListEntryUsage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetListEntryUsage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetListEntryUsage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetListEntryUsage update(
		AssetListEntryUsage assetListEntryUsage) {

		return getPersistence().update(assetListEntryUsage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetListEntryUsage update(
		AssetListEntryUsage assetListEntryUsage,
		ServiceContext serviceContext) {

		return getPersistence().update(assetListEntryUsage, serviceContext);
	}

	/**
	 * Returns all the asset list entry usages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the asset list entry usages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByUuid_First(
			String uuid,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByUuid_First(
		String uuid, OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByUuid_Last(
			String uuid,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByUuid_Last(
		String uuid, OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the asset list entry usages before and after the current asset list entry usage in the ordered set where uuid = &#63;.
	 *
	 * @param assetListEntryUsageId the primary key of the current asset list entry usage
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage[] findByUuid_PrevAndNext(
			long assetListEntryUsageId, String uuid,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByUuid_PrevAndNext(
			assetListEntryUsageId, uuid, orderByComparator);
	}

	/**
	 * Removes all the asset list entry usages where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of asset list entry usages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset list entry usages
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the asset list entry usage where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchEntryUsageException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByUUID_G(String uuid, long groupId)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the asset list entry usage where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the asset list entry usage where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the asset list entry usage where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the asset list entry usage that was removed
	 */
	public static AssetListEntryUsage removeByUUID_G(String uuid, long groupId)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of asset list entry usages where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching asset list entry usages
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the asset list entry usages where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the asset list entry usages where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the asset list entry usages before and after the current asset list entry usage in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param assetListEntryUsageId the primary key of the current asset list entry usage
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage[] findByUuid_C_PrevAndNext(
			long assetListEntryUsageId, String uuid, long companyId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByUuid_C_PrevAndNext(
			assetListEntryUsageId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the asset list entry usages where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of asset list entry usages where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching asset list entry usages
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the asset list entry usages where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @return the matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByAssetListEntryId(
		long assetListEntryId) {

		return getPersistence().findByAssetListEntryId(assetListEntryId);
	}

	/**
	 * Returns a range of all the asset list entry usages where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByAssetListEntryId(
		long assetListEntryId, int start, int end) {

		return getPersistence().findByAssetListEntryId(
			assetListEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByAssetListEntryId(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findByAssetListEntryId(
			assetListEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByAssetListEntryId(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAssetListEntryId(
			assetListEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByAssetListEntryId_First(
			long assetListEntryId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByAssetListEntryId_First(
			assetListEntryId, orderByComparator);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByAssetListEntryId_First(
		long assetListEntryId,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByAssetListEntryId_First(
			assetListEntryId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByAssetListEntryId_Last(
			long assetListEntryId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByAssetListEntryId_Last(
			assetListEntryId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByAssetListEntryId_Last(
		long assetListEntryId,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByAssetListEntryId_Last(
			assetListEntryId, orderByComparator);
	}

	/**
	 * Returns the asset list entry usages before and after the current asset list entry usage in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryUsageId the primary key of the current asset list entry usage
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage[] findByAssetListEntryId_PrevAndNext(
			long assetListEntryUsageId, long assetListEntryId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByAssetListEntryId_PrevAndNext(
			assetListEntryUsageId, assetListEntryId, orderByComparator);
	}

	/**
	 * Removes all the asset list entry usages where assetListEntryId = &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 */
	public static void removeByAssetListEntryId(long assetListEntryId) {
		getPersistence().removeByAssetListEntryId(assetListEntryId);
	}

	/**
	 * Returns the number of asset list entry usages where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @return the number of matching asset list entry usages
	 */
	public static int countByAssetListEntryId(long assetListEntryId) {
		return getPersistence().countByAssetListEntryId(assetListEntryId);
	}

	/**
	 * Returns all the asset list entry usages where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByPlid(long plid) {
		return getPersistence().findByPlid(plid);
	}

	/**
	 * Returns a range of all the asset list entry usages where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByPlid(
		long plid, int start, int end) {

		return getPersistence().findByPlid(plid, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByPlid(
		long plid, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findByPlid(plid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByPlid(
		long plid, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPlid(
			plid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByPlid_First(
			long plid, OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByPlid_First(plid, orderByComparator);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByPlid_First(
		long plid, OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByPlid_First(plid, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByPlid_Last(
			long plid, OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByPlid_Last(plid, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByPlid_Last(
		long plid, OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByPlid_Last(plid, orderByComparator);
	}

	/**
	 * Returns the asset list entry usages before and after the current asset list entry usage in the ordered set where plid = &#63;.
	 *
	 * @param assetListEntryUsageId the primary key of the current asset list entry usage
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage[] findByPlid_PrevAndNext(
			long assetListEntryUsageId, long plid,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByPlid_PrevAndNext(
			assetListEntryUsageId, plid, orderByComparator);
	}

	/**
	 * Removes all the asset list entry usages where plid = &#63; from the database.
	 *
	 * @param plid the plid
	 */
	public static void removeByPlid(long plid) {
		getPersistence().removeByPlid(plid);
	}

	/**
	 * Returns the number of asset list entry usages where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the number of matching asset list entry usages
	 */
	public static int countByPlid(long plid) {
		return getPersistence().countByPlid(plid);
	}

	/**
	 * Returns all the asset list entry usages where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @return the matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByA_C(
		long assetListEntryId, long classNameId) {

		return getPersistence().findByA_C(assetListEntryId, classNameId);
	}

	/**
	 * Returns a range of all the asset list entry usages where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByA_C(
		long assetListEntryId, long classNameId, int start, int end) {

		return getPersistence().findByA_C(
			assetListEntryId, classNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByA_C(
		long assetListEntryId, long classNameId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findByA_C(
			assetListEntryId, classNameId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByA_C(
		long assetListEntryId, long classNameId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByA_C(
			assetListEntryId, classNameId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByA_C_First(
			long assetListEntryId, long classNameId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByA_C_First(
			assetListEntryId, classNameId, orderByComparator);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByA_C_First(
		long assetListEntryId, long classNameId,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByA_C_First(
			assetListEntryId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByA_C_Last(
			long assetListEntryId, long classNameId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByA_C_Last(
			assetListEntryId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByA_C_Last(
		long assetListEntryId, long classNameId,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByA_C_Last(
			assetListEntryId, classNameId, orderByComparator);
	}

	/**
	 * Returns the asset list entry usages before and after the current asset list entry usage in the ordered set where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetListEntryUsageId the primary key of the current asset list entry usage
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage[] findByA_C_PrevAndNext(
			long assetListEntryUsageId, long assetListEntryId, long classNameId,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByA_C_PrevAndNext(
			assetListEntryUsageId, assetListEntryId, classNameId,
			orderByComparator);
	}

	/**
	 * Removes all the asset list entry usages where assetListEntryId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 */
	public static void removeByA_C(long assetListEntryId, long classNameId) {
		getPersistence().removeByA_C(assetListEntryId, classNameId);
	}

	/**
	 * Returns the number of asset list entry usages where assetListEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param classNameId the class name ID
	 * @return the number of matching asset list entry usages
	 */
	public static int countByA_C(long assetListEntryId, long classNameId) {
		return getPersistence().countByA_C(assetListEntryId, classNameId);
	}

	/**
	 * Returns all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @return the matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByG_C_K(
		long groupId, long classNameId, String key) {

		return getPersistence().findByG_C_K(groupId, classNameId, key);
	}

	/**
	 * Returns a range of all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByG_C_K(
		long groupId, long classNameId, String key, int start, int end) {

		return getPersistence().findByG_C_K(
			groupId, classNameId, key, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByG_C_K(
		long groupId, long classNameId, String key, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findByG_C_K(
			groupId, classNameId, key, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByG_C_K(
		long groupId, long classNameId, String key, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_K(
			groupId, classNameId, key, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByG_C_K_First(
			long groupId, long classNameId, String key,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByG_C_K_First(
			groupId, classNameId, key, orderByComparator);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByG_C_K_First(
		long groupId, long classNameId, String key,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByG_C_K_First(
			groupId, classNameId, key, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByG_C_K_Last(
			long groupId, long classNameId, String key,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByG_C_K_Last(
			groupId, classNameId, key, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByG_C_K_Last(
		long groupId, long classNameId, String key,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByG_C_K_Last(
			groupId, classNameId, key, orderByComparator);
	}

	/**
	 * Returns the asset list entry usages before and after the current asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * @param assetListEntryUsageId the primary key of the current asset list entry usage
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage[] findByG_C_K_PrevAndNext(
			long assetListEntryUsageId, long groupId, long classNameId,
			String key,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByG_C_K_PrevAndNext(
			assetListEntryUsageId, groupId, classNameId, key,
			orderByComparator);
	}

	/**
	 * Removes all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 */
	public static void removeByG_C_K(
		long groupId, long classNameId, String key) {

		getPersistence().removeByG_C_K(groupId, classNameId, key);
	}

	/**
	 * Returns the number of asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @return the number of matching asset list entry usages
	 */
	public static int countByG_C_K(long groupId, long classNameId, String key) {
		return getPersistence().countByG_C_K(groupId, classNameId, key);
	}

	/**
	 * Returns all the asset list entry usages where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @return the matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByCK_CT_P(
		String containerKey, long containerType, long plid) {

		return getPersistence().findByCK_CT_P(
			containerKey, containerType, plid);
	}

	/**
	 * Returns a range of all the asset list entry usages where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByCK_CT_P(
		String containerKey, long containerType, long plid, int start,
		int end) {

		return getPersistence().findByCK_CT_P(
			containerKey, containerType, plid, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByCK_CT_P(
		String containerKey, long containerType, long plid, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findByCK_CT_P(
			containerKey, containerType, plid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByCK_CT_P(
		String containerKey, long containerType, long plid, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCK_CT_P(
			containerKey, containerType, plid, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByCK_CT_P_First(
			String containerKey, long containerType, long plid,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByCK_CT_P_First(
			containerKey, containerType, plid, orderByComparator);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByCK_CT_P_First(
		String containerKey, long containerType, long plid,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByCK_CT_P_First(
			containerKey, containerType, plid, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByCK_CT_P_Last(
			String containerKey, long containerType, long plid,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByCK_CT_P_Last(
			containerKey, containerType, plid, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByCK_CT_P_Last(
		String containerKey, long containerType, long plid,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByCK_CT_P_Last(
			containerKey, containerType, plid, orderByComparator);
	}

	/**
	 * Returns the asset list entry usages before and after the current asset list entry usage in the ordered set where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * @param assetListEntryUsageId the primary key of the current asset list entry usage
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage[] findByCK_CT_P_PrevAndNext(
			long assetListEntryUsageId, String containerKey, long containerType,
			long plid, OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByCK_CT_P_PrevAndNext(
			assetListEntryUsageId, containerKey, containerType, plid,
			orderByComparator);
	}

	/**
	 * Removes all the asset list entry usages where containerKey = &#63; and containerType = &#63; and plid = &#63; from the database.
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 */
	public static void removeByCK_CT_P(
		String containerKey, long containerType, long plid) {

		getPersistence().removeByCK_CT_P(containerKey, containerType, plid);
	}

	/**
	 * Returns the number of asset list entry usages where containerKey = &#63; and containerType = &#63; and plid = &#63;.
	 *
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param plid the plid
	 * @return the number of matching asset list entry usages
	 */
	public static int countByCK_CT_P(
		String containerKey, long containerType, long plid) {

		return getPersistence().countByCK_CT_P(
			containerKey, containerType, plid);
	}

	/**
	 * Returns all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @return the matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByG_C_K_T(
		long groupId, long classNameId, String key, int type) {

		return getPersistence().findByG_C_K_T(groupId, classNameId, key, type);
	}

	/**
	 * Returns a range of all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByG_C_K_T(
		long groupId, long classNameId, String key, int type, int start,
		int end) {

		return getPersistence().findByG_C_K_T(
			groupId, classNameId, key, type, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByG_C_K_T(
		long groupId, long classNameId, String key, int type, int start,
		int end, OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findByG_C_K_T(
			groupId, classNameId, key, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry usages
	 */
	public static List<AssetListEntryUsage> findByG_C_K_T(
		long groupId, long classNameId, String key, int type, int start,
		int end, OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_K_T(
			groupId, classNameId, key, type, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByG_C_K_T_First(
			long groupId, long classNameId, String key, int type,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByG_C_K_T_First(
			groupId, classNameId, key, type, orderByComparator);
	}

	/**
	 * Returns the first asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByG_C_K_T_First(
		long groupId, long classNameId, String key, int type,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByG_C_K_T_First(
			groupId, classNameId, key, type, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByG_C_K_T_Last(
			long groupId, long classNameId, String key, int type,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByG_C_K_T_Last(
			groupId, classNameId, key, type, orderByComparator);
	}

	/**
	 * Returns the last asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByG_C_K_T_Last(
		long groupId, long classNameId, String key, int type,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().fetchByG_C_K_T_Last(
			groupId, classNameId, key, type, orderByComparator);
	}

	/**
	 * Returns the asset list entry usages before and after the current asset list entry usage in the ordered set where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * @param assetListEntryUsageId the primary key of the current asset list entry usage
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage[] findByG_C_K_T_PrevAndNext(
			long assetListEntryUsageId, long groupId, long classNameId,
			String key, int type,
			OrderByComparator<AssetListEntryUsage> orderByComparator)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByG_C_K_T_PrevAndNext(
			assetListEntryUsageId, groupId, classNameId, key, type,
			orderByComparator);
	}

	/**
	 * Removes all the asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 */
	public static void removeByG_C_K_T(
		long groupId, long classNameId, String key, int type) {

		getPersistence().removeByG_C_K_T(groupId, classNameId, key, type);
	}

	/**
	 * Returns the number of asset list entry usages where groupId = &#63; and classNameId = &#63; and key = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param key the key
	 * @param type the type
	 * @return the number of matching asset list entry usages
	 */
	public static int countByG_C_K_T(
		long groupId, long classNameId, String key, int type) {

		return getPersistence().countByG_C_K_T(groupId, classNameId, key, type);
	}

	/**
	 * Returns the asset list entry usage where groupId = &#63; and classNameId = &#63; and containerKey = &#63; and containerType = &#63; and key = &#63; and plid = &#63; or throws a <code>NoSuchEntryUsageException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param key the key
	 * @param plid the plid
	 * @return the matching asset list entry usage
	 * @throws NoSuchEntryUsageException if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage findByG_C_CK_CT_K_P(
			long groupId, long classNameId, String containerKey,
			long containerType, String key, long plid)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByG_C_CK_CT_K_P(
			groupId, classNameId, containerKey, containerType, key, plid);
	}

	/**
	 * Returns the asset list entry usage where groupId = &#63; and classNameId = &#63; and containerKey = &#63; and containerType = &#63; and key = &#63; and plid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param key the key
	 * @param plid the plid
	 * @return the matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByG_C_CK_CT_K_P(
		long groupId, long classNameId, String containerKey, long containerType,
		String key, long plid) {

		return getPersistence().fetchByG_C_CK_CT_K_P(
			groupId, classNameId, containerKey, containerType, key, plid);
	}

	/**
	 * Returns the asset list entry usage where groupId = &#63; and classNameId = &#63; and containerKey = &#63; and containerType = &#63; and key = &#63; and plid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param key the key
	 * @param plid the plid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	public static AssetListEntryUsage fetchByG_C_CK_CT_K_P(
		long groupId, long classNameId, String containerKey, long containerType,
		String key, long plid, boolean useFinderCache) {

		return getPersistence().fetchByG_C_CK_CT_K_P(
			groupId, classNameId, containerKey, containerType, key, plid,
			useFinderCache);
	}

	/**
	 * Removes the asset list entry usage where groupId = &#63; and classNameId = &#63; and containerKey = &#63; and containerType = &#63; and key = &#63; and plid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param key the key
	 * @param plid the plid
	 * @return the asset list entry usage that was removed
	 */
	public static AssetListEntryUsage removeByG_C_CK_CT_K_P(
			long groupId, long classNameId, String containerKey,
			long containerType, String key, long plid)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().removeByG_C_CK_CT_K_P(
			groupId, classNameId, containerKey, containerType, key, plid);
	}

	/**
	 * Returns the number of asset list entry usages where groupId = &#63; and classNameId = &#63; and containerKey = &#63; and containerType = &#63; and key = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param containerKey the container key
	 * @param containerType the container type
	 * @param key the key
	 * @param plid the plid
	 * @return the number of matching asset list entry usages
	 */
	public static int countByG_C_CK_CT_K_P(
		long groupId, long classNameId, String containerKey, long containerType,
		String key, long plid) {

		return getPersistence().countByG_C_CK_CT_K_P(
			groupId, classNameId, containerKey, containerType, key, plid);
	}

	/**
	 * Caches the asset list entry usage in the entity cache if it is enabled.
	 *
	 * @param assetListEntryUsage the asset list entry usage
	 */
	public static void cacheResult(AssetListEntryUsage assetListEntryUsage) {
		getPersistence().cacheResult(assetListEntryUsage);
	}

	/**
	 * Caches the asset list entry usages in the entity cache if it is enabled.
	 *
	 * @param assetListEntryUsages the asset list entry usages
	 */
	public static void cacheResult(
		List<AssetListEntryUsage> assetListEntryUsages) {

		getPersistence().cacheResult(assetListEntryUsages);
	}

	/**
	 * Creates a new asset list entry usage with the primary key. Does not add the asset list entry usage to the database.
	 *
	 * @param assetListEntryUsageId the primary key for the new asset list entry usage
	 * @return the new asset list entry usage
	 */
	public static AssetListEntryUsage create(long assetListEntryUsageId) {
		return getPersistence().create(assetListEntryUsageId);
	}

	/**
	 * Removes the asset list entry usage with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetListEntryUsageId the primary key of the asset list entry usage
	 * @return the asset list entry usage that was removed
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage remove(long assetListEntryUsageId)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().remove(assetListEntryUsageId);
	}

	public static AssetListEntryUsage updateImpl(
		AssetListEntryUsage assetListEntryUsage) {

		return getPersistence().updateImpl(assetListEntryUsage);
	}

	/**
	 * Returns the asset list entry usage with the primary key or throws a <code>NoSuchEntryUsageException</code> if it could not be found.
	 *
	 * @param assetListEntryUsageId the primary key of the asset list entry usage
	 * @return the asset list entry usage
	 * @throws NoSuchEntryUsageException if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage findByPrimaryKey(
			long assetListEntryUsageId)
		throws com.liferay.asset.list.exception.NoSuchEntryUsageException {

		return getPersistence().findByPrimaryKey(assetListEntryUsageId);
	}

	/**
	 * Returns the asset list entry usage with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetListEntryUsageId the primary key of the asset list entry usage
	 * @return the asset list entry usage, or <code>null</code> if a asset list entry usage with the primary key could not be found
	 */
	public static AssetListEntryUsage fetchByPrimaryKey(
		long assetListEntryUsageId) {

		return getPersistence().fetchByPrimaryKey(assetListEntryUsageId);
	}

	/**
	 * Returns all the asset list entry usages.
	 *
	 * @return the asset list entry usages
	 */
	public static List<AssetListEntryUsage> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the asset list entry usages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of asset list entry usages
	 */
	public static List<AssetListEntryUsage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset list entry usages
	 */
	public static List<AssetListEntryUsage> findAll(
		int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry usages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of asset list entry usages
	 */
	public static List<AssetListEntryUsage> findAll(
		int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the asset list entry usages from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of asset list entry usages.
	 *
	 * @return the number of asset list entry usages
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AssetListEntryUsagePersistence getPersistence() {
		return _persistence;
	}

	private static volatile AssetListEntryUsagePersistence _persistence;

}