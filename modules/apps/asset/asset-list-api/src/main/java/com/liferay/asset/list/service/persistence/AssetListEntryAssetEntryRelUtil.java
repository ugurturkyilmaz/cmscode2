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

import com.liferay.asset.list.model.AssetListEntryAssetEntryRel;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the asset list entry asset entry rel service. This utility wraps <code>com.liferay.asset.list.service.persistence.impl.AssetListEntryAssetEntryRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryAssetEntryRelPersistence
 * @generated
 */
public class AssetListEntryAssetEntryRelUtil {

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
	public static void clearCache(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {

		getPersistence().clearCache(assetListEntryAssetEntryRel);
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
	public static Map<Serializable, AssetListEntryAssetEntryRel>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetListEntryAssetEntryRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetListEntryAssetEntryRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetListEntryAssetEntryRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetListEntryAssetEntryRel update(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {

		return getPersistence().update(assetListEntryAssetEntryRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetListEntryAssetEntryRel update(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel,
		ServiceContext serviceContext) {

		return getPersistence().update(
			assetListEntryAssetEntryRel, serviceContext);
	}

	/**
	 * Returns all the asset list entry asset entry rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByUuid_First(
			String uuid,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByUuid_First(
		String uuid,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByUuid_Last(
			String uuid,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByUuid_Last(
		String uuid,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	public static AssetListEntryAssetEntryRel[] findByUuid_PrevAndNext(
			long assetListEntryAssetEntryRelId, String uuid,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByUuid_PrevAndNext(
			assetListEntryAssetEntryRelId, uuid, orderByComparator);
	}

	/**
	 * Removes all the asset list entry asset entry rels where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset list entry asset entry rels
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the asset list entry asset entry rel where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchEntryAssetEntryRelException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByUUID_G(
			String uuid, long groupId)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the asset list entry asset entry rel where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the asset list entry asset entry rel where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the asset list entry asset entry rel where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the asset list entry asset entry rel that was removed
	 */
	public static AssetListEntryAssetEntryRel removeByUUID_G(
			String uuid, long groupId)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching asset list entry asset entry rels
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	public static AssetListEntryAssetEntryRel[] findByUuid_C_PrevAndNext(
			long assetListEntryAssetEntryRelId, String uuid, long companyId,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByUuid_C_PrevAndNext(
			assetListEntryAssetEntryRelId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching asset list entry asset entry rels
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @return the matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByAssetListEntryId(
		long assetListEntryId) {

		return getPersistence().findByAssetListEntryId(assetListEntryId);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByAssetListEntryId(
		long assetListEntryId, int start, int end) {

		return getPersistence().findByAssetListEntryId(
			assetListEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByAssetListEntryId(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().findByAssetListEntryId(
			assetListEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByAssetListEntryId(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAssetListEntryId(
			assetListEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByAssetListEntryId_First(
			long assetListEntryId,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByAssetListEntryId_First(
			assetListEntryId, orderByComparator);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByAssetListEntryId_First(
		long assetListEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByAssetListEntryId_First(
			assetListEntryId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByAssetListEntryId_Last(
			long assetListEntryId,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByAssetListEntryId_Last(
			assetListEntryId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByAssetListEntryId_Last(
		long assetListEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByAssetListEntryId_Last(
			assetListEntryId, orderByComparator);
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	public static AssetListEntryAssetEntryRel[]
			findByAssetListEntryId_PrevAndNext(
				long assetListEntryAssetEntryRelId, long assetListEntryId,
				OrderByComparator<AssetListEntryAssetEntryRel>
					orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByAssetListEntryId_PrevAndNext(
			assetListEntryAssetEntryRelId, assetListEntryId, orderByComparator);
	}

	/**
	 * Removes all the asset list entry asset entry rels where assetListEntryId = &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 */
	public static void removeByAssetListEntryId(long assetListEntryId) {
		getPersistence().removeByAssetListEntryId(assetListEntryId);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @return the number of matching asset list entry asset entry rels
	 */
	public static int countByAssetListEntryId(long assetListEntryId) {
		return getPersistence().countByAssetListEntryId(assetListEntryId);
	}

	/**
	 * Returns all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @return the matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S(
		long assetListEntryId, long segmentsEntryId) {

		return getPersistence().findByA_S(assetListEntryId, segmentsEntryId);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S(
		long assetListEntryId, long segmentsEntryId, int start, int end) {

		return getPersistence().findByA_S(
			assetListEntryId, segmentsEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S(
		long assetListEntryId, long segmentsEntryId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().findByA_S(
			assetListEntryId, segmentsEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S(
		long assetListEntryId, long segmentsEntryId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByA_S(
			assetListEntryId, segmentsEntryId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByA_S_First(
			long assetListEntryId, long segmentsEntryId,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByA_S_First(
			assetListEntryId, segmentsEntryId, orderByComparator);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByA_S_First(
		long assetListEntryId, long segmentsEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByA_S_First(
			assetListEntryId, segmentsEntryId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByA_S_Last(
			long assetListEntryId, long segmentsEntryId,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByA_S_Last(
			assetListEntryId, segmentsEntryId, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByA_S_Last(
		long assetListEntryId, long segmentsEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByA_S_Last(
			assetListEntryId, segmentsEntryId, orderByComparator);
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	public static AssetListEntryAssetEntryRel[] findByA_S_PrevAndNext(
			long assetListEntryAssetEntryRelId, long assetListEntryId,
			long segmentsEntryId,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByA_S_PrevAndNext(
			assetListEntryAssetEntryRelId, assetListEntryId, segmentsEntryId,
			orderByComparator);
	}

	/**
	 * Returns all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @return the matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S(
		long assetListEntryId, long[] segmentsEntryIds) {

		return getPersistence().findByA_S(assetListEntryId, segmentsEntryIds);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S(
		long assetListEntryId, long[] segmentsEntryIds, int start, int end) {

		return getPersistence().findByA_S(
			assetListEntryId, segmentsEntryIds, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S(
		long assetListEntryId, long[] segmentsEntryIds, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().findByA_S(
			assetListEntryId, segmentsEntryIds, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S(
		long assetListEntryId, long[] segmentsEntryIds, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByA_S(
			assetListEntryId, segmentsEntryIds, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 */
	public static void removeByA_S(
		long assetListEntryId, long segmentsEntryId) {

		getPersistence().removeByA_S(assetListEntryId, segmentsEntryId);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @return the number of matching asset list entry asset entry rels
	 */
	public static int countByA_S(long assetListEntryId, long segmentsEntryId) {
		return getPersistence().countByA_S(assetListEntryId, segmentsEntryId);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = any &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @return the number of matching asset list entry asset entry rels
	 */
	public static int countByA_S(
		long assetListEntryId, long[] segmentsEntryIds) {

		return getPersistence().countByA_S(assetListEntryId, segmentsEntryIds);
	}

	/**
	 * Returns the asset list entry asset entry rel where assetListEntryId = &#63; and segmentsEntryId = &#63; and position = &#63; or throws a <code>NoSuchEntryAssetEntryRelException</code> if it could not be found.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @return the matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByA_S_P(
			long assetListEntryId, long segmentsEntryId, int position)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByA_S_P(
			assetListEntryId, segmentsEntryId, position);
	}

	/**
	 * Returns the asset list entry asset entry rel where assetListEntryId = &#63; and segmentsEntryId = &#63; and position = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @return the matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByA_S_P(
		long assetListEntryId, long segmentsEntryId, int position) {

		return getPersistence().fetchByA_S_P(
			assetListEntryId, segmentsEntryId, position);
	}

	/**
	 * Returns the asset list entry asset entry rel where assetListEntryId = &#63; and segmentsEntryId = &#63; and position = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByA_S_P(
		long assetListEntryId, long segmentsEntryId, int position,
		boolean useFinderCache) {

		return getPersistence().fetchByA_S_P(
			assetListEntryId, segmentsEntryId, position, useFinderCache);
	}

	/**
	 * Removes the asset list entry asset entry rel where assetListEntryId = &#63; and segmentsEntryId = &#63; and position = &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @return the asset list entry asset entry rel that was removed
	 */
	public static AssetListEntryAssetEntryRel removeByA_S_P(
			long assetListEntryId, long segmentsEntryId, int position)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().removeByA_S_P(
			assetListEntryId, segmentsEntryId, position);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63; and position = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @return the number of matching asset list entry asset entry rels
	 */
	public static int countByA_S_P(
		long assetListEntryId, long segmentsEntryId, int position) {

		return getPersistence().countByA_S_P(
			assetListEntryId, segmentsEntryId, position);
	}

	/**
	 * Returns all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @return the matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S_GtP(
		long assetListEntryId, long segmentsEntryId, int position) {

		return getPersistence().findByA_S_GtP(
			assetListEntryId, segmentsEntryId, position);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S_GtP(
		long assetListEntryId, long segmentsEntryId, int position, int start,
		int end) {

		return getPersistence().findByA_S_GtP(
			assetListEntryId, segmentsEntryId, position, start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S_GtP(
		long assetListEntryId, long segmentsEntryId, int position, int start,
		int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().findByA_S_GtP(
			assetListEntryId, segmentsEntryId, position, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findByA_S_GtP(
		long assetListEntryId, long segmentsEntryId, int position, int start,
		int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByA_S_GtP(
			assetListEntryId, segmentsEntryId, position, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByA_S_GtP_First(
			long assetListEntryId, long segmentsEntryId, int position,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByA_S_GtP_First(
			assetListEntryId, segmentsEntryId, position, orderByComparator);
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByA_S_GtP_First(
		long assetListEntryId, long segmentsEntryId, int position,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByA_S_GtP_First(
			assetListEntryId, segmentsEntryId, position, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel findByA_S_GtP_Last(
			long assetListEntryId, long segmentsEntryId, int position,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByA_S_GtP_Last(
			assetListEntryId, segmentsEntryId, position, orderByComparator);
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByA_S_GtP_Last(
		long assetListEntryId, long segmentsEntryId, int position,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().fetchByA_S_GtP_Last(
			assetListEntryId, segmentsEntryId, position, orderByComparator);
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	public static AssetListEntryAssetEntryRel[] findByA_S_GtP_PrevAndNext(
			long assetListEntryAssetEntryRelId, long assetListEntryId,
			long segmentsEntryId, int position,
			OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByA_S_GtP_PrevAndNext(
			assetListEntryAssetEntryRelId, assetListEntryId, segmentsEntryId,
			position, orderByComparator);
	}

	/**
	 * Removes all the asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 */
	public static void removeByA_S_GtP(
		long assetListEntryId, long segmentsEntryId, int position) {

		getPersistence().removeByA_S_GtP(
			assetListEntryId, segmentsEntryId, position);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where assetListEntryId = &#63; and segmentsEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param segmentsEntryId the segments entry ID
	 * @param position the position
	 * @return the number of matching asset list entry asset entry rels
	 */
	public static int countByA_S_GtP(
		long assetListEntryId, long segmentsEntryId, int position) {

		return getPersistence().countByA_S_GtP(
			assetListEntryId, segmentsEntryId, position);
	}

	/**
	 * Caches the asset list entry asset entry rel in the entity cache if it is enabled.
	 *
	 * @param assetListEntryAssetEntryRel the asset list entry asset entry rel
	 */
	public static void cacheResult(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {

		getPersistence().cacheResult(assetListEntryAssetEntryRel);
	}

	/**
	 * Caches the asset list entry asset entry rels in the entity cache if it is enabled.
	 *
	 * @param assetListEntryAssetEntryRels the asset list entry asset entry rels
	 */
	public static void cacheResult(
		List<AssetListEntryAssetEntryRel> assetListEntryAssetEntryRels) {

		getPersistence().cacheResult(assetListEntryAssetEntryRels);
	}

	/**
	 * Creates a new asset list entry asset entry rel with the primary key. Does not add the asset list entry asset entry rel to the database.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key for the new asset list entry asset entry rel
	 * @return the new asset list entry asset entry rel
	 */
	public static AssetListEntryAssetEntryRel create(
		long assetListEntryAssetEntryRelId) {

		return getPersistence().create(assetListEntryAssetEntryRelId);
	}

	/**
	 * Removes the asset list entry asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel that was removed
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	public static AssetListEntryAssetEntryRel remove(
			long assetListEntryAssetEntryRelId)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().remove(assetListEntryAssetEntryRelId);
	}

	public static AssetListEntryAssetEntryRel updateImpl(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {

		return getPersistence().updateImpl(assetListEntryAssetEntryRel);
	}

	/**
	 * Returns the asset list entry asset entry rel with the primary key or throws a <code>NoSuchEntryAssetEntryRelException</code> if it could not be found.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	public static AssetListEntryAssetEntryRel findByPrimaryKey(
			long assetListEntryAssetEntryRelId)
		throws com.liferay.asset.list.exception.
			NoSuchEntryAssetEntryRelException {

		return getPersistence().findByPrimaryKey(assetListEntryAssetEntryRelId);
	}

	/**
	 * Returns the asset list entry asset entry rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel, or <code>null</code> if a asset list entry asset entry rel with the primary key could not be found
	 */
	public static AssetListEntryAssetEntryRel fetchByPrimaryKey(
		long assetListEntryAssetEntryRelId) {

		return getPersistence().fetchByPrimaryKey(
			assetListEntryAssetEntryRelId);
	}

	/**
	 * Returns all the asset list entry asset entry rels.
	 *
	 * @return the asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findAll(
		int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetListEntryAssetEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of asset list entry asset entry rels
	 */
	public static List<AssetListEntryAssetEntryRel> findAll(
		int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the asset list entry asset entry rels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of asset list entry asset entry rels.
	 *
	 * @return the number of asset list entry asset entry rels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AssetListEntryAssetEntryRelPersistence getPersistence() {
		return _persistence;
	}

	private static volatile AssetListEntryAssetEntryRelPersistence _persistence;

}