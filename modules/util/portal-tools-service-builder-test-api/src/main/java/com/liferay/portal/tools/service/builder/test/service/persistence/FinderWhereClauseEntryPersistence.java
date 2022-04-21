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

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchFinderWhereClauseEntryException;
import com.liferay.portal.tools.service.builder.test.model.FinderWhereClauseEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the finder where clause entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinderWhereClauseEntryUtil
 * @generated
 */
@ProviderType
public interface FinderWhereClauseEntryPersistence
	extends BasePersistence<FinderWhereClauseEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FinderWhereClauseEntryUtil} to access the finder where clause entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the finder where clause entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching finder where clause entries
	 */
	public java.util.List<FinderWhereClauseEntry> findByName_Nickname(
		String name);

	/**
	 * Returns a range of all the finder where clause entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinderWhereClauseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of finder where clause entries
	 * @param end the upper bound of the range of finder where clause entries (not inclusive)
	 * @return the range of matching finder where clause entries
	 */
	public java.util.List<FinderWhereClauseEntry> findByName_Nickname(
		String name, int start, int end);

	/**
	 * Returns an ordered range of all the finder where clause entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinderWhereClauseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of finder where clause entries
	 * @param end the upper bound of the range of finder where clause entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching finder where clause entries
	 */
	public java.util.List<FinderWhereClauseEntry> findByName_Nickname(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinderWhereClauseEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the finder where clause entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinderWhereClauseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of finder where clause entries
	 * @param end the upper bound of the range of finder where clause entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching finder where clause entries
	 */
	public java.util.List<FinderWhereClauseEntry> findByName_Nickname(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinderWhereClauseEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first finder where clause entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching finder where clause entry
	 * @throws NoSuchFinderWhereClauseEntryException if a matching finder where clause entry could not be found
	 */
	public FinderWhereClauseEntry findByName_Nickname_First(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator
				<FinderWhereClauseEntry> orderByComparator)
		throws NoSuchFinderWhereClauseEntryException;

	/**
	 * Returns the first finder where clause entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching finder where clause entry, or <code>null</code> if a matching finder where clause entry could not be found
	 */
	public FinderWhereClauseEntry fetchByName_Nickname_First(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<FinderWhereClauseEntry>
			orderByComparator);

	/**
	 * Returns the last finder where clause entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching finder where clause entry
	 * @throws NoSuchFinderWhereClauseEntryException if a matching finder where clause entry could not be found
	 */
	public FinderWhereClauseEntry findByName_Nickname_Last(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator
				<FinderWhereClauseEntry> orderByComparator)
		throws NoSuchFinderWhereClauseEntryException;

	/**
	 * Returns the last finder where clause entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching finder where clause entry, or <code>null</code> if a matching finder where clause entry could not be found
	 */
	public FinderWhereClauseEntry fetchByName_Nickname_Last(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<FinderWhereClauseEntry>
			orderByComparator);

	/**
	 * Returns the finder where clause entries before and after the current finder where clause entry in the ordered set where name = &#63;.
	 *
	 * @param finderWhereClauseEntryId the primary key of the current finder where clause entry
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next finder where clause entry
	 * @throws NoSuchFinderWhereClauseEntryException if a finder where clause entry with the primary key could not be found
	 */
	public FinderWhereClauseEntry[] findByName_Nickname_PrevAndNext(
			long finderWhereClauseEntryId, String name,
			com.liferay.portal.kernel.util.OrderByComparator
				<FinderWhereClauseEntry> orderByComparator)
		throws NoSuchFinderWhereClauseEntryException;

	/**
	 * Removes all the finder where clause entries where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	public void removeByName_Nickname(String name);

	/**
	 * Returns the number of finder where clause entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching finder where clause entries
	 */
	public int countByName_Nickname(String name);

	/**
	 * Caches the finder where clause entry in the entity cache if it is enabled.
	 *
	 * @param finderWhereClauseEntry the finder where clause entry
	 */
	public void cacheResult(FinderWhereClauseEntry finderWhereClauseEntry);

	/**
	 * Caches the finder where clause entries in the entity cache if it is enabled.
	 *
	 * @param finderWhereClauseEntries the finder where clause entries
	 */
	public void cacheResult(
		java.util.List<FinderWhereClauseEntry> finderWhereClauseEntries);

	/**
	 * Creates a new finder where clause entry with the primary key. Does not add the finder where clause entry to the database.
	 *
	 * @param finderWhereClauseEntryId the primary key for the new finder where clause entry
	 * @return the new finder where clause entry
	 */
	public FinderWhereClauseEntry create(long finderWhereClauseEntryId);

	/**
	 * Removes the finder where clause entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param finderWhereClauseEntryId the primary key of the finder where clause entry
	 * @return the finder where clause entry that was removed
	 * @throws NoSuchFinderWhereClauseEntryException if a finder where clause entry with the primary key could not be found
	 */
	public FinderWhereClauseEntry remove(long finderWhereClauseEntryId)
		throws NoSuchFinderWhereClauseEntryException;

	public FinderWhereClauseEntry updateImpl(
		FinderWhereClauseEntry finderWhereClauseEntry);

	/**
	 * Returns the finder where clause entry with the primary key or throws a <code>NoSuchFinderWhereClauseEntryException</code> if it could not be found.
	 *
	 * @param finderWhereClauseEntryId the primary key of the finder where clause entry
	 * @return the finder where clause entry
	 * @throws NoSuchFinderWhereClauseEntryException if a finder where clause entry with the primary key could not be found
	 */
	public FinderWhereClauseEntry findByPrimaryKey(
			long finderWhereClauseEntryId)
		throws NoSuchFinderWhereClauseEntryException;

	/**
	 * Returns the finder where clause entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param finderWhereClauseEntryId the primary key of the finder where clause entry
	 * @return the finder where clause entry, or <code>null</code> if a finder where clause entry with the primary key could not be found
	 */
	public FinderWhereClauseEntry fetchByPrimaryKey(
		long finderWhereClauseEntryId);

	/**
	 * Returns all the finder where clause entries.
	 *
	 * @return the finder where clause entries
	 */
	public java.util.List<FinderWhereClauseEntry> findAll();

	/**
	 * Returns a range of all the finder where clause entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinderWhereClauseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finder where clause entries
	 * @param end the upper bound of the range of finder where clause entries (not inclusive)
	 * @return the range of finder where clause entries
	 */
	public java.util.List<FinderWhereClauseEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the finder where clause entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinderWhereClauseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finder where clause entries
	 * @param end the upper bound of the range of finder where clause entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of finder where clause entries
	 */
	public java.util.List<FinderWhereClauseEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinderWhereClauseEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the finder where clause entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinderWhereClauseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finder where clause entries
	 * @param end the upper bound of the range of finder where clause entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of finder where clause entries
	 */
	public java.util.List<FinderWhereClauseEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinderWhereClauseEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the finder where clause entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of finder where clause entries.
	 *
	 * @return the number of finder where clause entries
	 */
	public int countAll();

}