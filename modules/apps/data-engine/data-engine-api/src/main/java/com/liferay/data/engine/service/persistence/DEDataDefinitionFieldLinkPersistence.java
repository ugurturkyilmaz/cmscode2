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

package com.liferay.data.engine.service.persistence;

import com.liferay.data.engine.exception.NoSuchDataDefinitionFieldLinkException;
import com.liferay.data.engine.model.DEDataDefinitionFieldLink;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the de data definition field link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DEDataDefinitionFieldLinkUtil
 * @generated
 */
@ProviderType
public interface DEDataDefinitionFieldLinkPersistence
	extends BasePersistence<DEDataDefinitionFieldLink>,
			CTPersistence<DEDataDefinitionFieldLink> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DEDataDefinitionFieldLinkUtil} to access the de data definition field link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the de data definition field links where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByUuid(String uuid);

	/**
	 * Returns a range of all the de data definition field links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the de data definition field links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first de data definition field link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the first de data definition field link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the last de data definition field link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the last de data definition field link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the de data definition field links before and after the current de data definition field link in the ordered set where uuid = &#63;.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the current de data definition field link
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink[] findByUuid_PrevAndNext(
			long deDataDefinitionFieldLinkId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Removes all the de data definition field links where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of de data definition field links where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching de data definition field links
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the de data definition field link where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchDataDefinitionFieldLinkException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByUUID_G(String uuid, long groupId)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the de data definition field link where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the de data definition field link where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the de data definition field link where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the de data definition field link that was removed
	 */
	public DEDataDefinitionFieldLink removeByUUID_G(String uuid, long groupId)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the number of de data definition field links where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching de data definition field links
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the de data definition field links where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the de data definition field links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the de data definition field links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first de data definition field link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the first de data definition field link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the last de data definition field link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the last de data definition field link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the de data definition field links before and after the current de data definition field link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the current de data definition field link
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink[] findByUuid_C_PrevAndNext(
			long deDataDefinitionFieldLinkId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Removes all the de data definition field links where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of de data definition field links where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching de data definition field links
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the de data definition field links where ddmStructureId = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMStructureId(
		long ddmStructureId);

	/**
	 * Returns a range of all the de data definition field links where ddmStructureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMStructureId(
		long ddmStructureId, int start, int end);

	/**
	 * Returns an ordered range of all the de data definition field links where ddmStructureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMStructureId(
		long ddmStructureId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where ddmStructureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMStructureId(
		long ddmStructureId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first de data definition field link in the ordered set where ddmStructureId = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByDDMStructureId_First(
			long ddmStructureId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the first de data definition field link in the ordered set where ddmStructureId = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByDDMStructureId_First(
		long ddmStructureId,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the last de data definition field link in the ordered set where ddmStructureId = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByDDMStructureId_Last(
			long ddmStructureId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the last de data definition field link in the ordered set where ddmStructureId = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByDDMStructureId_Last(
		long ddmStructureId,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the de data definition field links before and after the current de data definition field link in the ordered set where ddmStructureId = &#63;.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the current de data definition field link
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink[] findByDDMStructureId_PrevAndNext(
			long deDataDefinitionFieldLinkId, long ddmStructureId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Removes all the de data definition field links where ddmStructureId = &#63; from the database.
	 *
	 * @param ddmStructureId the ddm structure ID
	 */
	public void removeByDDMStructureId(long ddmStructureId);

	/**
	 * Returns the number of de data definition field links where ddmStructureId = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @return the number of matching de data definition field links
	 */
	public int countByDDMStructureId(long ddmStructureId);

	/**
	 * Returns all the de data definition field links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_C(
		long classNameId, long classPK);

	/**
	 * Returns a range of all the de data definition field links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_C(
		long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the de data definition field links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first de data definition field link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the first de data definition field link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the last de data definition field link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the last de data definition field link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the de data definition field links before and after the current de data definition field link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the current de data definition field link
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink[] findByC_C_PrevAndNext(
			long deDataDefinitionFieldLinkId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Removes all the de data definition field links where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of de data definition field links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching de data definition field links
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI(
		long classNameId, long ddmStructureId);

	/**
	 * Returns a range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI(
		long classNameId, long ddmStructureId, int start, int end);

	/**
	 * Returns an ordered range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI(
		long classNameId, long ddmStructureId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI(
		long classNameId, long ddmStructureId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByC_DDMSI_First(
			long classNameId, long ddmStructureId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the first de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByC_DDMSI_First(
		long classNameId, long ddmStructureId,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the last de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByC_DDMSI_Last(
			long classNameId, long ddmStructureId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the last de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByC_DDMSI_Last(
		long classNameId, long ddmStructureId,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the de data definition field links before and after the current de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the current de data definition field link
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink[] findByC_DDMSI_PrevAndNext(
			long deDataDefinitionFieldLinkId, long classNameId,
			long ddmStructureId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Removes all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 */
	public void removeByC_DDMSI(long classNameId, long ddmStructureId);

	/**
	 * Returns the number of de data definition field links where classNameId = &#63; and ddmStructureId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @return the number of matching de data definition field links
	 */
	public int countByC_DDMSI(long classNameId, long ddmStructureId);

	/**
	 * Returns all the de data definition field links where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMSI_F(
		long ddmStructureId, String fieldName);

	/**
	 * Returns a range of all the de data definition field links where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMSI_F(
		long ddmStructureId, String fieldName, int start, int end);

	/**
	 * Returns an ordered range of all the de data definition field links where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMSI_F(
		long ddmStructureId, String fieldName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMSI_F(
		long ddmStructureId, String fieldName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first de data definition field link in the ordered set where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByDDMSI_F_First(
			long ddmStructureId, String fieldName,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the first de data definition field link in the ordered set where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByDDMSI_F_First(
		long ddmStructureId, String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the last de data definition field link in the ordered set where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByDDMSI_F_Last(
			long ddmStructureId, String fieldName,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the last de data definition field link in the ordered set where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByDDMSI_F_Last(
		long ddmStructureId, String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the de data definition field links before and after the current de data definition field link in the ordered set where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the current de data definition field link
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink[] findByDDMSI_F_PrevAndNext(
			long deDataDefinitionFieldLinkId, long ddmStructureId,
			String fieldName,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns all the de data definition field links where ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMSI_F(
		long ddmStructureId, String[] fieldNames);

	/**
	 * Returns a range of all the de data definition field links where ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMSI_F(
		long ddmStructureId, String[] fieldNames, int start, int end);

	/**
	 * Returns an ordered range of all the de data definition field links where ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMSI_F(
		long ddmStructureId, String[] fieldNames, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where ddmStructureId = &#63; and fieldName = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByDDMSI_F(
		long ddmStructureId, String[] fieldNames, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the de data definition field links where ddmStructureId = &#63; and fieldName = &#63; from the database.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 */
	public void removeByDDMSI_F(long ddmStructureId, String fieldName);

	/**
	 * Returns the number of de data definition field links where ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @return the number of matching de data definition field links
	 */
	public int countByDDMSI_F(long ddmStructureId, String fieldName);

	/**
	 * Returns the number of de data definition field links where ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @return the number of matching de data definition field links
	 */
	public int countByDDMSI_F(long ddmStructureId, String[] fieldNames);

	/**
	 * Returns all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI_F(
		long classNameId, long ddmStructureId, String fieldName);

	/**
	 * Returns a range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI_F(
		long classNameId, long ddmStructureId, String fieldName, int start,
		int end);

	/**
	 * Returns an ordered range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI_F(
		long classNameId, long ddmStructureId, String fieldName, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI_F(
		long classNameId, long ddmStructureId, String fieldName, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByC_DDMSI_F_First(
			long classNameId, long ddmStructureId, String fieldName,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the first de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByC_DDMSI_F_First(
		long classNameId, long ddmStructureId, String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the last de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByC_DDMSI_F_Last(
			long classNameId, long ddmStructureId, String fieldName,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the last de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByC_DDMSI_F_Last(
		long classNameId, long ddmStructureId, String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns the de data definition field links before and after the current de data definition field link in the ordered set where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the current de data definition field link
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink[] findByC_DDMSI_F_PrevAndNext(
			long deDataDefinitionFieldLinkId, long classNameId,
			long ddmStructureId, String fieldName,
			com.liferay.portal.kernel.util.OrderByComparator
				<DEDataDefinitionFieldLink> orderByComparator)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @return the matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI_F(
		long classNameId, long ddmStructureId, String[] fieldNames);

	/**
	 * Returns a range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI_F(
		long classNameId, long ddmStructureId, String[] fieldNames, int start,
		int end);

	/**
	 * Returns an ordered range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI_F(
		long classNameId, long ddmStructureId, String[] fieldNames, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findByC_DDMSI_F(
		long classNameId, long ddmStructureId, String[] fieldNames, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 */
	public void removeByC_DDMSI_F(
		long classNameId, long ddmStructureId, String fieldName);

	/**
	 * Returns the number of de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @return the number of matching de data definition field links
	 */
	public int countByC_DDMSI_F(
		long classNameId, long ddmStructureId, String fieldName);

	/**
	 * Returns the number of de data definition field links where classNameId = &#63; and ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @return the number of matching de data definition field links
	 */
	public int countByC_DDMSI_F(
		long classNameId, long ddmStructureId, String[] fieldNames);

	/**
	 * Returns the de data definition field link where classNameId = &#63; and classPK = &#63; and ddmStructureId = &#63; and fieldName = &#63; or throws a <code>NoSuchDataDefinitionFieldLinkException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @return the matching de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink findByC_C_DDMSI_F(
			long classNameId, long classPK, long ddmStructureId,
			String fieldName)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the de data definition field link where classNameId = &#63; and classPK = &#63; and ddmStructureId = &#63; and fieldName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @return the matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByC_C_DDMSI_F(
		long classNameId, long classPK, long ddmStructureId, String fieldName);

	/**
	 * Returns the de data definition field link where classNameId = &#63; and classPK = &#63; and ddmStructureId = &#63; and fieldName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	public DEDataDefinitionFieldLink fetchByC_C_DDMSI_F(
		long classNameId, long classPK, long ddmStructureId, String fieldName,
		boolean useFinderCache);

	/**
	 * Removes the de data definition field link where classNameId = &#63; and classPK = &#63; and ddmStructureId = &#63; and fieldName = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @return the de data definition field link that was removed
	 */
	public DEDataDefinitionFieldLink removeByC_C_DDMSI_F(
			long classNameId, long classPK, long ddmStructureId,
			String fieldName)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the number of de data definition field links where classNameId = &#63; and classPK = &#63; and ddmStructureId = &#63; and fieldName = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldName the field name
	 * @return the number of matching de data definition field links
	 */
	public int countByC_C_DDMSI_F(
		long classNameId, long classPK, long ddmStructureId, String fieldName);

	/**
	 * Returns the number of de data definition field links where classNameId = &#63; and classPK = &#63; and ddmStructureId = &#63; and fieldName = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ddmStructureId the ddm structure ID
	 * @param fieldNames the field names
	 * @return the number of matching de data definition field links
	 */
	public int countByC_C_DDMSI_F(
		long classNameId, long classPK, long ddmStructureId,
		String[] fieldNames);

	/**
	 * Caches the de data definition field link in the entity cache if it is enabled.
	 *
	 * @param deDataDefinitionFieldLink the de data definition field link
	 */
	public void cacheResult(
		DEDataDefinitionFieldLink deDataDefinitionFieldLink);

	/**
	 * Caches the de data definition field links in the entity cache if it is enabled.
	 *
	 * @param deDataDefinitionFieldLinks the de data definition field links
	 */
	public void cacheResult(
		java.util.List<DEDataDefinitionFieldLink> deDataDefinitionFieldLinks);

	/**
	 * Creates a new de data definition field link with the primary key. Does not add the de data definition field link to the database.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key for the new de data definition field link
	 * @return the new de data definition field link
	 */
	public DEDataDefinitionFieldLink create(long deDataDefinitionFieldLinkId);

	/**
	 * Removes the de data definition field link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the de data definition field link
	 * @return the de data definition field link that was removed
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink remove(long deDataDefinitionFieldLinkId)
		throws NoSuchDataDefinitionFieldLinkException;

	public DEDataDefinitionFieldLink updateImpl(
		DEDataDefinitionFieldLink deDataDefinitionFieldLink);

	/**
	 * Returns the de data definition field link with the primary key or throws a <code>NoSuchDataDefinitionFieldLinkException</code> if it could not be found.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the de data definition field link
	 * @return the de data definition field link
	 * @throws NoSuchDataDefinitionFieldLinkException if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink findByPrimaryKey(
			long deDataDefinitionFieldLinkId)
		throws NoSuchDataDefinitionFieldLinkException;

	/**
	 * Returns the de data definition field link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the de data definition field link
	 * @return the de data definition field link, or <code>null</code> if a de data definition field link with the primary key could not be found
	 */
	public DEDataDefinitionFieldLink fetchByPrimaryKey(
		long deDataDefinitionFieldLinkId);

	/**
	 * Returns all the de data definition field links.
	 *
	 * @return the de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findAll();

	/**
	 * Returns a range of all the de data definition field links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findAll(
		int start, int end);

	/**
	 * Returns an ordered range of all the de data definition field links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator);

	/**
	 * Returns an ordered range of all the de data definition field links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of de data definition field links
	 */
	public java.util.List<DEDataDefinitionFieldLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DEDataDefinitionFieldLink> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the de data definition field links from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of de data definition field links.
	 *
	 * @return the number of de data definition field links
	 */
	public int countAll();

}