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

package com.liferay.fragment.service.persistence;

import com.liferay.fragment.model.FragmentEntryVersion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the fragment entry version service. This utility wraps <code>com.liferay.fragment.service.persistence.impl.FragmentEntryVersionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryVersionPersistence
 * @generated
 */
public class FragmentEntryVersionUtil {

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
	public static void clearCache(FragmentEntryVersion fragmentEntryVersion) {
		getPersistence().clearCache(fragmentEntryVersion);
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
	public static Map<Serializable, FragmentEntryVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FragmentEntryVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FragmentEntryVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FragmentEntryVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FragmentEntryVersion update(
		FragmentEntryVersion fragmentEntryVersion) {

		return getPersistence().update(fragmentEntryVersion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FragmentEntryVersion update(
		FragmentEntryVersion fragmentEntryVersion,
		ServiceContext serviceContext) {

		return getPersistence().update(fragmentEntryVersion, serviceContext);
	}

	/**
	 * Returns all the fragment entry versions where fragmentEntryId = &#63;.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentEntryId(
		long fragmentEntryId) {

		return getPersistence().findByFragmentEntryId(fragmentEntryId);
	}

	/**
	 * Returns a range of all the fragment entry versions where fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentEntryId(
		long fragmentEntryId, int start, int end) {

		return getPersistence().findByFragmentEntryId(
			fragmentEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentEntryId(
		long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByFragmentEntryId(
			fragmentEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentEntryId(
		long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFragmentEntryId(
			fragmentEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where fragmentEntryId = &#63;.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByFragmentEntryId_First(
			long fragmentEntryId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentEntryId_First(
			fragmentEntryId, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where fragmentEntryId = &#63;.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByFragmentEntryId_First(
		long fragmentEntryId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByFragmentEntryId_First(
			fragmentEntryId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where fragmentEntryId = &#63;.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByFragmentEntryId_Last(
			long fragmentEntryId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentEntryId_Last(
			fragmentEntryId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where fragmentEntryId = &#63;.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByFragmentEntryId_Last(
		long fragmentEntryId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByFragmentEntryId_Last(
			fragmentEntryId, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where fragmentEntryId = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByFragmentEntryId_PrevAndNext(
			long fragmentEntryVersionId, long fragmentEntryId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentEntryId_PrevAndNext(
			fragmentEntryVersionId, fragmentEntryId, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where fragmentEntryId = &#63; from the database.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 */
	public static void removeByFragmentEntryId(long fragmentEntryId) {
		getPersistence().removeByFragmentEntryId(fragmentEntryId);
	}

	/**
	 * Returns the number of fragment entry versions where fragmentEntryId = &#63;.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @return the number of matching fragment entry versions
	 */
	public static int countByFragmentEntryId(long fragmentEntryId) {
		return getPersistence().countByFragmentEntryId(fragmentEntryId);
	}

	/**
	 * Returns the fragment entry version where fragmentEntryId = &#63; and version = &#63; or throws a <code>NoSuchEntryVersionException</code> if it could not be found.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param version the version
	 * @return the matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByFragmentEntryId_Version(
			long fragmentEntryId, int version)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentEntryId_Version(
			fragmentEntryId, version);
	}

	/**
	 * Returns the fragment entry version where fragmentEntryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param version the version
	 * @return the matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByFragmentEntryId_Version(
		long fragmentEntryId, int version) {

		return getPersistence().fetchByFragmentEntryId_Version(
			fragmentEntryId, version);
	}

	/**
	 * Returns the fragment entry version where fragmentEntryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByFragmentEntryId_Version(
		long fragmentEntryId, int version, boolean useFinderCache) {

		return getPersistence().fetchByFragmentEntryId_Version(
			fragmentEntryId, version, useFinderCache);
	}

	/**
	 * Removes the fragment entry version where fragmentEntryId = &#63; and version = &#63; from the database.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param version the version
	 * @return the fragment entry version that was removed
	 */
	public static FragmentEntryVersion removeByFragmentEntryId_Version(
			long fragmentEntryId, int version)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().removeByFragmentEntryId_Version(
			fragmentEntryId, version);
	}

	/**
	 * Returns the number of fragment entry versions where fragmentEntryId = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryId the fragment entry ID
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByFragmentEntryId_Version(
		long fragmentEntryId, int version) {

		return getPersistence().countByFragmentEntryId_Version(
			fragmentEntryId, version);
	}

	/**
	 * Returns all the fragment entry versions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the fragment entry versions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUuid_First(
			String uuid,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUuid_First(
		String uuid,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUuid_Last(
			String uuid,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUuid_Last(
		String uuid,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where uuid = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByUuid_PrevAndNext(
			long fragmentEntryVersionId, String uuid,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_PrevAndNext(
			fragmentEntryVersionId, uuid, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of fragment entry versions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fragment entry versions
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the fragment entry versions where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_Version(
		String uuid, int version) {

		return getPersistence().findByUuid_Version(uuid, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where uuid = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_Version(
		String uuid, int version, int start, int end) {

		return getPersistence().findByUuid_Version(uuid, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_Version(
		String uuid, int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByUuid_Version(
			uuid, version, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_Version(
		String uuid, int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_Version(
			uuid, version, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUuid_Version_First(
			String uuid, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_Version_First(
			uuid, version, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUuid_Version_First(
		String uuid, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUuid_Version_First(
			uuid, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUuid_Version_Last(
			String uuid, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_Version_Last(
			uuid, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUuid_Version_Last(
		String uuid, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUuid_Version_Last(
			uuid, version, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where uuid = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param uuid the uuid
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByUuid_Version_PrevAndNext(
			long fragmentEntryVersionId, String uuid, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_Version_PrevAndNext(
			fragmentEntryVersionId, uuid, version, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where uuid = &#63; and version = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 */
	public static void removeByUuid_Version(String uuid, int version) {
		getPersistence().removeByUuid_Version(uuid, version);
	}

	/**
	 * Returns the number of fragment entry versions where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByUuid_Version(String uuid, int version) {
		return getPersistence().countByUuid_Version(uuid, version);
	}

	/**
	 * Returns all the fragment entry versions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUUID_G(
		String uuid, long groupId) {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the fragment entry versions where uuid = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUUID_G(
		String uuid, long groupId, int start, int end) {

		return getPersistence().findByUUID_G(uuid, groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUUID_G(
		String uuid, long groupId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByUUID_G(
			uuid, groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUUID_G(
		String uuid, long groupId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUUID_G(
			uuid, groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUUID_G_First(
			String uuid, long groupId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUUID_G_First(
			uuid, groupId, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUUID_G_First(
		String uuid, long groupId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUUID_G_First(
			uuid, groupId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUUID_G_Last(
			String uuid, long groupId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUUID_G_Last(
			uuid, groupId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUUID_G_Last(
		String uuid, long groupId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUUID_G_Last(
			uuid, groupId, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where uuid = &#63; and groupId = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByUUID_G_PrevAndNext(
			long fragmentEntryVersionId, String uuid, long groupId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUUID_G_PrevAndNext(
			fragmentEntryVersionId, uuid, groupId, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 */
	public static void removeByUUID_G(String uuid, long groupId) {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of fragment entry versions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fragment entry versions
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the fragment entry version where uuid = &#63; and groupId = &#63; and version = &#63; or throws a <code>NoSuchEntryVersionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @return the matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUUID_G_Version(
			String uuid, long groupId, int version)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUUID_G_Version(uuid, groupId, version);
	}

	/**
	 * Returns the fragment entry version where uuid = &#63; and groupId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @return the matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUUID_G_Version(
		String uuid, long groupId, int version) {

		return getPersistence().fetchByUUID_G_Version(uuid, groupId, version);
	}

	/**
	 * Returns the fragment entry version where uuid = &#63; and groupId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUUID_G_Version(
		String uuid, long groupId, int version, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G_Version(
			uuid, groupId, version, useFinderCache);
	}

	/**
	 * Removes the fragment entry version where uuid = &#63; and groupId = &#63; and version = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @return the fragment entry version that was removed
	 */
	public static FragmentEntryVersion removeByUUID_G_Version(
			String uuid, long groupId, int version)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().removeByUUID_G_Version(uuid, groupId, version);
	}

	/**
	 * Returns the number of fragment entry versions where uuid = &#63; and groupId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByUUID_G_Version(
		String uuid, long groupId, int version) {

		return getPersistence().countByUUID_G_Version(uuid, groupId, version);
	}

	/**
	 * Returns all the fragment entry versions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the fragment entry versions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByUuid_C_PrevAndNext(
			long fragmentEntryVersionId, String uuid, long companyId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_C_PrevAndNext(
			fragmentEntryVersionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of fragment entry versions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fragment entry versions
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the fragment entry versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_C_Version(
		String uuid, long companyId, int version) {

		return getPersistence().findByUuid_C_Version(uuid, companyId, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_C_Version(
		String uuid, long companyId, int version, int start, int end) {

		return getPersistence().findByUuid_C_Version(
			uuid, companyId, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_C_Version(
		String uuid, long companyId, int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByUuid_C_Version(
			uuid, companyId, version, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByUuid_C_Version(
		String uuid, long companyId, int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C_Version(
			uuid, companyId, version, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUuid_C_Version_First(
			String uuid, long companyId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_C_Version_First(
			uuid, companyId, version, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUuid_C_Version_First(
		String uuid, long companyId, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUuid_C_Version_First(
			uuid, companyId, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByUuid_C_Version_Last(
			String uuid, long companyId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_C_Version_Last(
			uuid, companyId, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByUuid_C_Version_Last(
		String uuid, long companyId, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByUuid_C_Version_Last(
			uuid, companyId, version, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByUuid_C_Version_PrevAndNext(
			long fragmentEntryVersionId, String uuid, long companyId,
			int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByUuid_C_Version_PrevAndNext(
			fragmentEntryVersionId, uuid, companyId, version,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where uuid = &#63; and companyId = &#63; and version = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 */
	public static void removeByUuid_C_Version(
		String uuid, long companyId, int version) {

		getPersistence().removeByUuid_C_Version(uuid, companyId, version);
	}

	/**
	 * Returns the number of fragment entry versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByUuid_C_Version(
		String uuid, long companyId, int version) {

		return getPersistence().countByUuid_C_Version(uuid, companyId, version);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByGroupId_First(
			long groupId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByGroupId_First(
		long groupId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByGroupId_Last(
			long groupId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByGroupId_Last(
		long groupId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByGroupId_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByGroupId_PrevAndNext(
			fragmentEntryVersionId, groupId, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fragment entry versions
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByGroupId_Version(
		long groupId, int version) {

		return getPersistence().findByGroupId_Version(groupId, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end) {

		return getPersistence().findByGroupId_Version(
			groupId, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByGroupId_Version(
			groupId, version, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId_Version(
			groupId, version, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByGroupId_Version_First(
			long groupId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByGroupId_Version_First(
			groupId, version, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByGroupId_Version_First(
		long groupId, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByGroupId_Version_First(
			groupId, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByGroupId_Version_Last(
			long groupId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByGroupId_Version_Last(
			groupId, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByGroupId_Version_Last(
		long groupId, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByGroupId_Version_Last(
			groupId, version, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByGroupId_Version_PrevAndNext(
			long fragmentEntryVersionId, long groupId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByGroupId_Version_PrevAndNext(
			fragmentEntryVersionId, groupId, version, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 */
	public static void removeByGroupId_Version(long groupId, int version) {
		getPersistence().removeByGroupId_Version(groupId, version);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByGroupId_Version(long groupId, int version) {
		return getPersistence().countByGroupId_Version(groupId, version);
	}

	/**
	 * Returns all the fragment entry versions where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentCollectionId(
		long fragmentCollectionId) {

		return getPersistence().findByFragmentCollectionId(
			fragmentCollectionId);
	}

	/**
	 * Returns a range of all the fragment entry versions where fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentCollectionId(
		long fragmentCollectionId, int start, int end) {

		return getPersistence().findByFragmentCollectionId(
			fragmentCollectionId, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentCollectionId(
		long fragmentCollectionId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByFragmentCollectionId(
			fragmentCollectionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentCollectionId(
		long fragmentCollectionId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFragmentCollectionId(
			fragmentCollectionId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByFragmentCollectionId_First(
			long fragmentCollectionId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentCollectionId_First(
			fragmentCollectionId, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByFragmentCollectionId_First(
		long fragmentCollectionId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByFragmentCollectionId_First(
			fragmentCollectionId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByFragmentCollectionId_Last(
			long fragmentCollectionId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentCollectionId_Last(
			fragmentCollectionId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByFragmentCollectionId_Last(
		long fragmentCollectionId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByFragmentCollectionId_Last(
			fragmentCollectionId, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByFragmentCollectionId_PrevAndNext(
			long fragmentEntryVersionId, long fragmentCollectionId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentCollectionId_PrevAndNext(
			fragmentEntryVersionId, fragmentCollectionId, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where fragmentCollectionId = &#63; from the database.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 */
	public static void removeByFragmentCollectionId(long fragmentCollectionId) {
		getPersistence().removeByFragmentCollectionId(fragmentCollectionId);
	}

	/**
	 * Returns the number of fragment entry versions where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @return the number of matching fragment entry versions
	 */
	public static int countByFragmentCollectionId(long fragmentCollectionId) {
		return getPersistence().countByFragmentCollectionId(
			fragmentCollectionId);
	}

	/**
	 * Returns all the fragment entry versions where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentCollectionId_Version(
		long fragmentCollectionId, int version) {

		return getPersistence().findByFragmentCollectionId_Version(
			fragmentCollectionId, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentCollectionId_Version(
		long fragmentCollectionId, int version, int start, int end) {

		return getPersistence().findByFragmentCollectionId_Version(
			fragmentCollectionId, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentCollectionId_Version(
		long fragmentCollectionId, int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByFragmentCollectionId_Version(
			fragmentCollectionId, version, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByFragmentCollectionId_Version(
		long fragmentCollectionId, int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFragmentCollectionId_Version(
			fragmentCollectionId, version, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByFragmentCollectionId_Version_First(
			long fragmentCollectionId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentCollectionId_Version_First(
			fragmentCollectionId, version, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion
		fetchByFragmentCollectionId_Version_First(
			long fragmentCollectionId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByFragmentCollectionId_Version_First(
			fragmentCollectionId, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByFragmentCollectionId_Version_Last(
			long fragmentCollectionId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentCollectionId_Version_Last(
			fragmentCollectionId, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByFragmentCollectionId_Version_Last(
		long fragmentCollectionId, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByFragmentCollectionId_Version_Last(
			fragmentCollectionId, version, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[]
			findByFragmentCollectionId_Version_PrevAndNext(
				long fragmentEntryVersionId, long fragmentCollectionId,
				int version,
				OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByFragmentCollectionId_Version_PrevAndNext(
			fragmentEntryVersionId, fragmentCollectionId, version,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where fragmentCollectionId = &#63; and version = &#63; from the database.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 */
	public static void removeByFragmentCollectionId_Version(
		long fragmentCollectionId, int version) {

		getPersistence().removeByFragmentCollectionId_Version(
			fragmentCollectionId, version);
	}

	/**
	 * Returns the number of fragment entry versions where fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByFragmentCollectionId_Version(
		long fragmentCollectionId, int version) {

		return getPersistence().countByFragmentCollectionId_Version(
			fragmentCollectionId, version);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI(
		long groupId, long fragmentCollectionId) {

		return getPersistence().findByG_FCI(groupId, fragmentCollectionId);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI(
		long groupId, long fragmentCollectionId, int start, int end) {

		return getPersistence().findByG_FCI(
			groupId, fragmentCollectionId, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI(
		long groupId, long fragmentCollectionId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI(
			groupId, fragmentCollectionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI(
		long groupId, long fragmentCollectionId, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI(
			groupId, fragmentCollectionId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_First(
			long groupId, long fragmentCollectionId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_First(
			groupId, fragmentCollectionId, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_First(
		long groupId, long fragmentCollectionId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_First(
			groupId, fragmentCollectionId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_Last(
			long groupId, long fragmentCollectionId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_Last(
			groupId, fragmentCollectionId, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_Last(
		long groupId, long fragmentCollectionId,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_Last(
			groupId, fragmentCollectionId, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 */
	public static void removeByG_FCI(long groupId, long fragmentCollectionId) {
		getPersistence().removeByG_FCI(groupId, fragmentCollectionId);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI(long groupId, long fragmentCollectionId) {
		return getPersistence().countByG_FCI(groupId, fragmentCollectionId);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_Version(
		long groupId, long fragmentCollectionId, int version) {

		return getPersistence().findByG_FCI_Version(
			groupId, fragmentCollectionId, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_Version(
		long groupId, long fragmentCollectionId, int version, int start,
		int end) {

		return getPersistence().findByG_FCI_Version(
			groupId, fragmentCollectionId, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_Version(
		long groupId, long fragmentCollectionId, int version, int start,
		int end, OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_Version(
			groupId, fragmentCollectionId, version, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_Version(
		long groupId, long fragmentCollectionId, int version, int start,
		int end, OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_Version(
			groupId, fragmentCollectionId, version, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_Version_First(
			long groupId, long fragmentCollectionId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_Version_First(
			groupId, fragmentCollectionId, version, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_Version_First(
		long groupId, long fragmentCollectionId, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_Version_First(
			groupId, fragmentCollectionId, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_Version_Last(
			long groupId, long fragmentCollectionId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_Version_Last(
			groupId, fragmentCollectionId, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_Version_Last(
		long groupId, long fragmentCollectionId, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_Version_Last(
			groupId, fragmentCollectionId, version, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_Version_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_Version_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, version,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 */
	public static void removeByG_FCI_Version(
		long groupId, long fragmentCollectionId, int version) {

		getPersistence().removeByG_FCI_Version(
			groupId, fragmentCollectionId, version);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_Version(
		long groupId, long fragmentCollectionId, int version) {

		return getPersistence().countByG_FCI_Version(
			groupId, fragmentCollectionId, version);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FEK(
		long groupId, String fragmentEntryKey) {

		return getPersistence().findByG_FEK(groupId, fragmentEntryKey);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FEK(
		long groupId, String fragmentEntryKey, int start, int end) {

		return getPersistence().findByG_FEK(
			groupId, fragmentEntryKey, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FEK(
		long groupId, String fragmentEntryKey, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FEK(
			groupId, fragmentEntryKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FEK(
		long groupId, String fragmentEntryKey, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FEK(
			groupId, fragmentEntryKey, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FEK_First(
			long groupId, String fragmentEntryKey,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FEK_First(
			groupId, fragmentEntryKey, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FEK_First(
		long groupId, String fragmentEntryKey,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FEK_First(
			groupId, fragmentEntryKey, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FEK_Last(
			long groupId, String fragmentEntryKey,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FEK_Last(
			groupId, fragmentEntryKey, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FEK_Last(
		long groupId, String fragmentEntryKey,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FEK_Last(
			groupId, fragmentEntryKey, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FEK_PrevAndNext(
			long fragmentEntryVersionId, long groupId, String fragmentEntryKey,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FEK_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentEntryKey,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentEntryKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 */
	public static void removeByG_FEK(long groupId, String fragmentEntryKey) {
		getPersistence().removeByG_FEK(groupId, fragmentEntryKey);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentEntryKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FEK(long groupId, String fragmentEntryKey) {
		return getPersistence().countByG_FEK(groupId, fragmentEntryKey);
	}

	/**
	 * Returns the fragment entry version where groupId = &#63; and fragmentEntryKey = &#63; and version = &#63; or throws a <code>NoSuchEntryVersionException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param version the version
	 * @return the matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FEK_Version(
			long groupId, String fragmentEntryKey, int version)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FEK_Version(
			groupId, fragmentEntryKey, version);
	}

	/**
	 * Returns the fragment entry version where groupId = &#63; and fragmentEntryKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param version the version
	 * @return the matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FEK_Version(
		long groupId, String fragmentEntryKey, int version) {

		return getPersistence().fetchByG_FEK_Version(
			groupId, fragmentEntryKey, version);
	}

	/**
	 * Returns the fragment entry version where groupId = &#63; and fragmentEntryKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FEK_Version(
		long groupId, String fragmentEntryKey, int version,
		boolean useFinderCache) {

		return getPersistence().fetchByG_FEK_Version(
			groupId, fragmentEntryKey, version, useFinderCache);
	}

	/**
	 * Removes the fragment entry version where groupId = &#63; and fragmentEntryKey = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param version the version
	 * @return the fragment entry version that was removed
	 */
	public static FragmentEntryVersion removeByG_FEK_Version(
			long groupId, String fragmentEntryKey, int version)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().removeByG_FEK_Version(
			groupId, fragmentEntryKey, version);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentEntryKey = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryKey the fragment entry key
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FEK_Version(
		long groupId, String fragmentEntryKey, int version) {

		return getPersistence().countByG_FEK_Version(
			groupId, fragmentEntryKey, version);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name) {

		return getPersistence().findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name, int start,
		int end) {

		return getPersistence().findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name, int start,
		int end, OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name, int start,
		int end, OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_LikeN_First(
			long groupId, long fragmentCollectionId, String name,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_First(
			groupId, fragmentCollectionId, name, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_LikeN_First(
		long groupId, long fragmentCollectionId, String name,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_LikeN_First(
			groupId, fragmentCollectionId, name, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_LikeN_Last(
			long groupId, long fragmentCollectionId, String name,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_Last(
			groupId, fragmentCollectionId, name, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_LikeN_Last(
		long groupId, long fragmentCollectionId, String name,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_LikeN_Last(
			groupId, fragmentCollectionId, name, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_LikeN_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, String name,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, name,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 */
	public static void removeByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name) {

		getPersistence().removeByG_FCI_LikeN(
			groupId, fragmentCollectionId, name);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name) {

		return getPersistence().countByG_FCI_LikeN(
			groupId, fragmentCollectionId, name);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_Version(
		long groupId, long fragmentCollectionId, String name, int version) {

		return getPersistence().findByG_FCI_LikeN_Version(
			groupId, fragmentCollectionId, name, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_Version(
		long groupId, long fragmentCollectionId, String name, int version,
		int start, int end) {

		return getPersistence().findByG_FCI_LikeN_Version(
			groupId, fragmentCollectionId, name, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_Version(
		long groupId, long fragmentCollectionId, String name, int version,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_LikeN_Version(
			groupId, fragmentCollectionId, name, version, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_Version(
		long groupId, long fragmentCollectionId, String name, int version,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_LikeN_Version(
			groupId, fragmentCollectionId, name, version, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_LikeN_Version_First(
			long groupId, long fragmentCollectionId, String name, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_Version_First(
			groupId, fragmentCollectionId, name, version, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_LikeN_Version_First(
		long groupId, long fragmentCollectionId, String name, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_LikeN_Version_First(
			groupId, fragmentCollectionId, name, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_LikeN_Version_Last(
			long groupId, long fragmentCollectionId, String name, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_Version_Last(
			groupId, fragmentCollectionId, name, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_LikeN_Version_Last(
		long groupId, long fragmentCollectionId, String name, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_LikeN_Version_Last(
			groupId, fragmentCollectionId, name, version, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_LikeN_Version_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, String name, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_Version_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, name,
			version, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 */
	public static void removeByG_FCI_LikeN_Version(
		long groupId, long fragmentCollectionId, String name, int version) {

		getPersistence().removeByG_FCI_LikeN_Version(
			groupId, fragmentCollectionId, name, version);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_LikeN_Version(
		long groupId, long fragmentCollectionId, String name, int version) {

		return getPersistence().countByG_FCI_LikeN_Version(
			groupId, fragmentCollectionId, name, version);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T(
		long groupId, long fragmentCollectionId, int type) {

		return getPersistence().findByG_FCI_T(
			groupId, fragmentCollectionId, type);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T(
		long groupId, long fragmentCollectionId, int type, int start, int end) {

		return getPersistence().findByG_FCI_T(
			groupId, fragmentCollectionId, type, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T(
		long groupId, long fragmentCollectionId, int type, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_T(
			groupId, fragmentCollectionId, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T(
		long groupId, long fragmentCollectionId, int type, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_T(
			groupId, fragmentCollectionId, type, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_T_First(
			long groupId, long fragmentCollectionId, int type,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_First(
			groupId, fragmentCollectionId, type, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_T_First(
		long groupId, long fragmentCollectionId, int type,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_T_First(
			groupId, fragmentCollectionId, type, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_T_Last(
			long groupId, long fragmentCollectionId, int type,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_Last(
			groupId, fragmentCollectionId, type, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_T_Last(
		long groupId, long fragmentCollectionId, int type,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_T_Last(
			groupId, fragmentCollectionId, type, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_T_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, int type,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, type,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 */
	public static void removeByG_FCI_T(
		long groupId, long fragmentCollectionId, int type) {

		getPersistence().removeByG_FCI_T(groupId, fragmentCollectionId, type);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_T(
		long groupId, long fragmentCollectionId, int type) {

		return getPersistence().countByG_FCI_T(
			groupId, fragmentCollectionId, type);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_Version(
		long groupId, long fragmentCollectionId, int type, int version) {

		return getPersistence().findByG_FCI_T_Version(
			groupId, fragmentCollectionId, type, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_Version(
		long groupId, long fragmentCollectionId, int type, int version,
		int start, int end) {

		return getPersistence().findByG_FCI_T_Version(
			groupId, fragmentCollectionId, type, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_Version(
		long groupId, long fragmentCollectionId, int type, int version,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_T_Version(
			groupId, fragmentCollectionId, type, version, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_Version(
		long groupId, long fragmentCollectionId, int type, int version,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_T_Version(
			groupId, fragmentCollectionId, type, version, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_T_Version_First(
			long groupId, long fragmentCollectionId, int type, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_Version_First(
			groupId, fragmentCollectionId, type, version, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_T_Version_First(
		long groupId, long fragmentCollectionId, int type, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_T_Version_First(
			groupId, fragmentCollectionId, type, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_T_Version_Last(
			long groupId, long fragmentCollectionId, int type, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_Version_Last(
			groupId, fragmentCollectionId, type, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_T_Version_Last(
		long groupId, long fragmentCollectionId, int type, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_T_Version_Last(
			groupId, fragmentCollectionId, type, version, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_T_Version_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, int type, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_Version_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, type,
			version, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 */
	public static void removeByG_FCI_T_Version(
		long groupId, long fragmentCollectionId, int type, int version) {

		getPersistence().removeByG_FCI_T_Version(
			groupId, fragmentCollectionId, type, version);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_T_Version(
		long groupId, long fragmentCollectionId, int type, int version) {

		return getPersistence().countByG_FCI_T_Version(
			groupId, fragmentCollectionId, type, version);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_S(
		long groupId, long fragmentCollectionId, int status) {

		return getPersistence().findByG_FCI_S(
			groupId, fragmentCollectionId, status);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_S(
		long groupId, long fragmentCollectionId, int status, int start,
		int end) {

		return getPersistence().findByG_FCI_S(
			groupId, fragmentCollectionId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_S(
		long groupId, long fragmentCollectionId, int status, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_S(
			groupId, fragmentCollectionId, status, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_S(
		long groupId, long fragmentCollectionId, int status, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_S(
			groupId, fragmentCollectionId, status, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_S_First(
			long groupId, long fragmentCollectionId, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_S_First(
			groupId, fragmentCollectionId, status, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_S_First(
		long groupId, long fragmentCollectionId, int status,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_S_First(
			groupId, fragmentCollectionId, status, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_S_Last(
			long groupId, long fragmentCollectionId, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_S_Last(
			groupId, fragmentCollectionId, status, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_S_Last(
		long groupId, long fragmentCollectionId, int status,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_S_Last(
			groupId, fragmentCollectionId, status, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_S_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_S_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, status,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 */
	public static void removeByG_FCI_S(
		long groupId, long fragmentCollectionId, int status) {

		getPersistence().removeByG_FCI_S(groupId, fragmentCollectionId, status);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_S(
		long groupId, long fragmentCollectionId, int status) {

		return getPersistence().countByG_FCI_S(
			groupId, fragmentCollectionId, status);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_S_Version(
		long groupId, long fragmentCollectionId, int status, int version) {

		return getPersistence().findByG_FCI_S_Version(
			groupId, fragmentCollectionId, status, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_S_Version(
		long groupId, long fragmentCollectionId, int status, int version,
		int start, int end) {

		return getPersistence().findByG_FCI_S_Version(
			groupId, fragmentCollectionId, status, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_S_Version(
		long groupId, long fragmentCollectionId, int status, int version,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_S_Version(
			groupId, fragmentCollectionId, status, version, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_S_Version(
		long groupId, long fragmentCollectionId, int status, int version,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_S_Version(
			groupId, fragmentCollectionId, status, version, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_S_Version_First(
			long groupId, long fragmentCollectionId, int status, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_S_Version_First(
			groupId, fragmentCollectionId, status, version, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_S_Version_First(
		long groupId, long fragmentCollectionId, int status, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_S_Version_First(
			groupId, fragmentCollectionId, status, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_S_Version_Last(
			long groupId, long fragmentCollectionId, int status, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_S_Version_Last(
			groupId, fragmentCollectionId, status, version, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_S_Version_Last(
		long groupId, long fragmentCollectionId, int status, int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_S_Version_Last(
			groupId, fragmentCollectionId, status, version, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_S_Version_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, int status, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_S_Version_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, status,
			version, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 */
	public static void removeByG_FCI_S_Version(
		long groupId, long fragmentCollectionId, int status, int version) {

		getPersistence().removeByG_FCI_S_Version(
			groupId, fragmentCollectionId, status, version);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_S_Version(
		long groupId, long fragmentCollectionId, int status, int version) {

		return getPersistence().countByG_FCI_S_Version(
			groupId, fragmentCollectionId, status, version);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status) {

		return getPersistence().findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status,
		int start, int end) {

		return getPersistence().findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_LikeN_S_First(
			long groupId, long fragmentCollectionId, String name, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_S_First(
			groupId, fragmentCollectionId, name, status, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_LikeN_S_First(
		long groupId, long fragmentCollectionId, String name, int status,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_LikeN_S_First(
			groupId, fragmentCollectionId, name, status, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_LikeN_S_Last(
			long groupId, long fragmentCollectionId, String name, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_S_Last(
			groupId, fragmentCollectionId, name, status, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_LikeN_S_Last(
		long groupId, long fragmentCollectionId, String name, int status,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_LikeN_S_Last(
			groupId, fragmentCollectionId, name, status, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_LikeN_S_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, String name, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_S_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, name, status,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 */
	public static void removeByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status) {

		getPersistence().removeByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status) {

		return getPersistence().countByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_S_Version(
		long groupId, long fragmentCollectionId, String name, int status,
		int version) {

		return getPersistence().findByG_FCI_LikeN_S_Version(
			groupId, fragmentCollectionId, name, status, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_S_Version(
		long groupId, long fragmentCollectionId, String name, int status,
		int version, int start, int end) {

		return getPersistence().findByG_FCI_LikeN_S_Version(
			groupId, fragmentCollectionId, name, status, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_S_Version(
		long groupId, long fragmentCollectionId, String name, int status,
		int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_LikeN_S_Version(
			groupId, fragmentCollectionId, name, status, version, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_LikeN_S_Version(
		long groupId, long fragmentCollectionId, String name, int status,
		int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_LikeN_S_Version(
			groupId, fragmentCollectionId, name, status, version, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_LikeN_S_Version_First(
			long groupId, long fragmentCollectionId, String name, int status,
			int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_S_Version_First(
			groupId, fragmentCollectionId, name, status, version,
			orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_LikeN_S_Version_First(
		long groupId, long fragmentCollectionId, String name, int status,
		int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_LikeN_S_Version_First(
			groupId, fragmentCollectionId, name, status, version,
			orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_LikeN_S_Version_Last(
			long groupId, long fragmentCollectionId, String name, int status,
			int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_S_Version_Last(
			groupId, fragmentCollectionId, name, status, version,
			orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_LikeN_S_Version_Last(
		long groupId, long fragmentCollectionId, String name, int status,
		int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_LikeN_S_Version_Last(
			groupId, fragmentCollectionId, name, status, version,
			orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[]
			findByG_FCI_LikeN_S_Version_PrevAndNext(
				long fragmentEntryVersionId, long groupId,
				long fragmentCollectionId, String name, int status, int version,
				OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_LikeN_S_Version_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, name, status,
			version, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 */
	public static void removeByG_FCI_LikeN_S_Version(
		long groupId, long fragmentCollectionId, String name, int status,
		int version) {

		getPersistence().removeByG_FCI_LikeN_S_Version(
			groupId, fragmentCollectionId, name, status, version);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and name = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_LikeN_S_Version(
		long groupId, long fragmentCollectionId, String name, int status,
		int version) {

		return getPersistence().countByG_FCI_LikeN_S_Version(
			groupId, fragmentCollectionId, name, status, version);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_S(
		long groupId, long fragmentCollectionId, int type, int status) {

		return getPersistence().findByG_FCI_T_S(
			groupId, fragmentCollectionId, type, status);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_S(
		long groupId, long fragmentCollectionId, int type, int status,
		int start, int end) {

		return getPersistence().findByG_FCI_T_S(
			groupId, fragmentCollectionId, type, status, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_S(
		long groupId, long fragmentCollectionId, int type, int status,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_T_S(
			groupId, fragmentCollectionId, type, status, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_S(
		long groupId, long fragmentCollectionId, int type, int status,
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_T_S(
			groupId, fragmentCollectionId, type, status, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_T_S_First(
			long groupId, long fragmentCollectionId, int type, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_S_First(
			groupId, fragmentCollectionId, type, status, orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_T_S_First(
		long groupId, long fragmentCollectionId, int type, int status,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_T_S_First(
			groupId, fragmentCollectionId, type, status, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_T_S_Last(
			long groupId, long fragmentCollectionId, int type, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_S_Last(
			groupId, fragmentCollectionId, type, status, orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_T_S_Last(
		long groupId, long fragmentCollectionId, int type, int status,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_T_S_Last(
			groupId, fragmentCollectionId, type, status, orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_T_S_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, int type, int status,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_S_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, type, status,
			orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 */
	public static void removeByG_FCI_T_S(
		long groupId, long fragmentCollectionId, int type, int status) {

		getPersistence().removeByG_FCI_T_S(
			groupId, fragmentCollectionId, type, status);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_T_S(
		long groupId, long fragmentCollectionId, int type, int status) {

		return getPersistence().countByG_FCI_T_S(
			groupId, fragmentCollectionId, type, status);
	}

	/**
	 * Returns all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @return the matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_S_Version(
		long groupId, long fragmentCollectionId, int type, int status,
		int version) {

		return getPersistence().findByG_FCI_T_S_Version(
			groupId, fragmentCollectionId, type, status, version);
	}

	/**
	 * Returns a range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_S_Version(
		long groupId, long fragmentCollectionId, int type, int status,
		int version, int start, int end) {

		return getPersistence().findByG_FCI_T_S_Version(
			groupId, fragmentCollectionId, type, status, version, start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_S_Version(
		long groupId, long fragmentCollectionId, int type, int status,
		int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findByG_FCI_T_S_Version(
			groupId, fragmentCollectionId, type, status, version, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment entry versions
	 */
	public static List<FragmentEntryVersion> findByG_FCI_T_S_Version(
		long groupId, long fragmentCollectionId, int type, int status,
		int version, int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_FCI_T_S_Version(
			groupId, fragmentCollectionId, type, status, version, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_T_S_Version_First(
			long groupId, long fragmentCollectionId, int type, int status,
			int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_S_Version_First(
			groupId, fragmentCollectionId, type, status, version,
			orderByComparator);
	}

	/**
	 * Returns the first fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_T_S_Version_First(
		long groupId, long fragmentCollectionId, int type, int status,
		int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_T_S_Version_First(
			groupId, fragmentCollectionId, type, status, version,
			orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version
	 * @throws NoSuchEntryVersionException if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion findByG_FCI_T_S_Version_Last(
			long groupId, long fragmentCollectionId, int type, int status,
			int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_S_Version_Last(
			groupId, fragmentCollectionId, type, status, version,
			orderByComparator);
	}

	/**
	 * Returns the last fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry version, or <code>null</code> if a matching fragment entry version could not be found
	 */
	public static FragmentEntryVersion fetchByG_FCI_T_S_Version_Last(
		long groupId, long fragmentCollectionId, int type, int status,
		int version,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().fetchByG_FCI_T_S_Version_Last(
			groupId, fragmentCollectionId, type, status, version,
			orderByComparator);
	}

	/**
	 * Returns the fragment entry versions before and after the current fragment entry version in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param fragmentEntryVersionId the primary key of the current fragment entry version
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion[] findByG_FCI_T_S_Version_PrevAndNext(
			long fragmentEntryVersionId, long groupId,
			long fragmentCollectionId, int type, int status, int version,
			OrderByComparator<FragmentEntryVersion> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByG_FCI_T_S_Version_PrevAndNext(
			fragmentEntryVersionId, groupId, fragmentCollectionId, type, status,
			version, orderByComparator);
	}

	/**
	 * Removes all the fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 */
	public static void removeByG_FCI_T_S_Version(
		long groupId, long fragmentCollectionId, int type, int status,
		int version) {

		getPersistence().removeByG_FCI_T_S_Version(
			groupId, fragmentCollectionId, type, status, version);
	}

	/**
	 * Returns the number of fragment entry versions where groupId = &#63; and fragmentCollectionId = &#63; and type = &#63; and status = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param type the type
	 * @param status the status
	 * @param version the version
	 * @return the number of matching fragment entry versions
	 */
	public static int countByG_FCI_T_S_Version(
		long groupId, long fragmentCollectionId, int type, int status,
		int version) {

		return getPersistence().countByG_FCI_T_S_Version(
			groupId, fragmentCollectionId, type, status, version);
	}

	/**
	 * Caches the fragment entry version in the entity cache if it is enabled.
	 *
	 * @param fragmentEntryVersion the fragment entry version
	 */
	public static void cacheResult(FragmentEntryVersion fragmentEntryVersion) {
		getPersistence().cacheResult(fragmentEntryVersion);
	}

	/**
	 * Caches the fragment entry versions in the entity cache if it is enabled.
	 *
	 * @param fragmentEntryVersions the fragment entry versions
	 */
	public static void cacheResult(
		List<FragmentEntryVersion> fragmentEntryVersions) {

		getPersistence().cacheResult(fragmentEntryVersions);
	}

	/**
	 * Creates a new fragment entry version with the primary key. Does not add the fragment entry version to the database.
	 *
	 * @param fragmentEntryVersionId the primary key for the new fragment entry version
	 * @return the new fragment entry version
	 */
	public static FragmentEntryVersion create(long fragmentEntryVersionId) {
		return getPersistence().create(fragmentEntryVersionId);
	}

	/**
	 * Removes the fragment entry version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentEntryVersionId the primary key of the fragment entry version
	 * @return the fragment entry version that was removed
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion remove(long fragmentEntryVersionId)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().remove(fragmentEntryVersionId);
	}

	public static FragmentEntryVersion updateImpl(
		FragmentEntryVersion fragmentEntryVersion) {

		return getPersistence().updateImpl(fragmentEntryVersion);
	}

	/**
	 * Returns the fragment entry version with the primary key or throws a <code>NoSuchEntryVersionException</code> if it could not be found.
	 *
	 * @param fragmentEntryVersionId the primary key of the fragment entry version
	 * @return the fragment entry version
	 * @throws NoSuchEntryVersionException if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion findByPrimaryKey(
			long fragmentEntryVersionId)
		throws com.liferay.fragment.exception.NoSuchEntryVersionException {

		return getPersistence().findByPrimaryKey(fragmentEntryVersionId);
	}

	/**
	 * Returns the fragment entry version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fragmentEntryVersionId the primary key of the fragment entry version
	 * @return the fragment entry version, or <code>null</code> if a fragment entry version with the primary key could not be found
	 */
	public static FragmentEntryVersion fetchByPrimaryKey(
		long fragmentEntryVersionId) {

		return getPersistence().fetchByPrimaryKey(fragmentEntryVersionId);
	}

	/**
	 * Returns all the fragment entry versions.
	 *
	 * @return the fragment entry versions
	 */
	public static List<FragmentEntryVersion> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the fragment entry versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @return the range of fragment entry versions
	 */
	public static List<FragmentEntryVersion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fragment entry versions
	 */
	public static List<FragmentEntryVersion> findAll(
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fragment entry versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentEntryVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment entry versions
	 * @param end the upper bound of the range of fragment entry versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of fragment entry versions
	 */
	public static List<FragmentEntryVersion> findAll(
		int start, int end,
		OrderByComparator<FragmentEntryVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the fragment entry versions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of fragment entry versions.
	 *
	 * @return the number of fragment entry versions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FragmentEntryVersionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile FragmentEntryVersionPersistence _persistence;

}