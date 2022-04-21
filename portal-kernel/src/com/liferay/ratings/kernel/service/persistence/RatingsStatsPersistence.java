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

package com.liferay.ratings.kernel.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.ratings.kernel.exception.NoSuchStatsException;
import com.liferay.ratings.kernel.model.RatingsStats;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ratings stats service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RatingsStatsUtil
 * @generated
 */
@ProviderType
public interface RatingsStatsPersistence
	extends BasePersistence<RatingsStats>, CTPersistence<RatingsStats> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RatingsStatsUtil} to access the ratings stats persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the ratings statses where classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RatingsStatsModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @return the matching ratings statses
	 */
	public java.util.List<RatingsStats> findByC_C(
		long classNameId, long[] classPKs);

	/**
	 * Returns a range of all the ratings statses where classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RatingsStatsModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param start the lower bound of the range of ratings statses
	 * @param end the upper bound of the range of ratings statses (not inclusive)
	 * @return the range of matching ratings statses
	 */
	public java.util.List<RatingsStats> findByC_C(
		long classNameId, long[] classPKs, int start, int end);

	/**
	 * Returns an ordered range of all the ratings statses where classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RatingsStatsModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param start the lower bound of the range of ratings statses
	 * @param end the upper bound of the range of ratings statses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ratings statses
	 */
	public java.util.List<RatingsStats> findByC_C(
		long classNameId, long[] classPKs, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RatingsStats>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ratings statses where classNameId = &#63; and classPK = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RatingsStatsModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ratings statses
	 * @param end the upper bound of the range of ratings statses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ratings statses
	 */
	public java.util.List<RatingsStats> findByC_C(
		long classNameId, long[] classPKs, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RatingsStats>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the ratings stats where classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchStatsException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ratings stats
	 * @throws NoSuchStatsException if a matching ratings stats could not be found
	 */
	public RatingsStats findByC_C(long classNameId, long classPK)
		throws NoSuchStatsException;

	/**
	 * Returns the ratings stats where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ratings stats, or <code>null</code> if a matching ratings stats could not be found
	 */
	public RatingsStats fetchByC_C(long classNameId, long classPK);

	/**
	 * Returns the ratings stats where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ratings stats, or <code>null</code> if a matching ratings stats could not be found
	 */
	public RatingsStats fetchByC_C(
		long classNameId, long classPK, boolean useFinderCache);

	/**
	 * Removes the ratings stats where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the ratings stats that was removed
	 */
	public RatingsStats removeByC_C(long classNameId, long classPK)
		throws NoSuchStatsException;

	/**
	 * Returns the number of ratings statses where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching ratings statses
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of ratings statses where classNameId = &#63; and classPK = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @return the number of matching ratings statses
	 */
	public int countByC_C(long classNameId, long[] classPKs);

	/**
	 * Caches the ratings stats in the entity cache if it is enabled.
	 *
	 * @param ratingsStats the ratings stats
	 */
	public void cacheResult(RatingsStats ratingsStats);

	/**
	 * Caches the ratings statses in the entity cache if it is enabled.
	 *
	 * @param ratingsStatses the ratings statses
	 */
	public void cacheResult(java.util.List<RatingsStats> ratingsStatses);

	/**
	 * Creates a new ratings stats with the primary key. Does not add the ratings stats to the database.
	 *
	 * @param statsId the primary key for the new ratings stats
	 * @return the new ratings stats
	 */
	public RatingsStats create(long statsId);

	/**
	 * Removes the ratings stats with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param statsId the primary key of the ratings stats
	 * @return the ratings stats that was removed
	 * @throws NoSuchStatsException if a ratings stats with the primary key could not be found
	 */
	public RatingsStats remove(long statsId) throws NoSuchStatsException;

	public RatingsStats updateImpl(RatingsStats ratingsStats);

	/**
	 * Returns the ratings stats with the primary key or throws a <code>NoSuchStatsException</code> if it could not be found.
	 *
	 * @param statsId the primary key of the ratings stats
	 * @return the ratings stats
	 * @throws NoSuchStatsException if a ratings stats with the primary key could not be found
	 */
	public RatingsStats findByPrimaryKey(long statsId)
		throws NoSuchStatsException;

	/**
	 * Returns the ratings stats with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param statsId the primary key of the ratings stats
	 * @return the ratings stats, or <code>null</code> if a ratings stats with the primary key could not be found
	 */
	public RatingsStats fetchByPrimaryKey(long statsId);

	/**
	 * Returns all the ratings statses.
	 *
	 * @return the ratings statses
	 */
	public java.util.List<RatingsStats> findAll();

	/**
	 * Returns a range of all the ratings statses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RatingsStatsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ratings statses
	 * @param end the upper bound of the range of ratings statses (not inclusive)
	 * @return the range of ratings statses
	 */
	public java.util.List<RatingsStats> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the ratings statses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RatingsStatsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ratings statses
	 * @param end the upper bound of the range of ratings statses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ratings statses
	 */
	public java.util.List<RatingsStats> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RatingsStats>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ratings statses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RatingsStatsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ratings statses
	 * @param end the upper bound of the range of ratings statses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ratings statses
	 */
	public java.util.List<RatingsStats> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RatingsStats>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ratings statses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ratings statses.
	 *
	 * @return the number of ratings statses
	 */
	public int countAll();

}