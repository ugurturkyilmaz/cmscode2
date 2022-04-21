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

package com.liferay.social.kernel.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.social.kernel.exception.NoSuchActivityCounterException;
import com.liferay.social.kernel.model.SocialActivityCounter;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the social activity counter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityCounterUtil
 * @generated
 */
@ProviderType
public interface SocialActivityCounterPersistence
	extends BasePersistence<SocialActivityCounter>,
			CTPersistence<SocialActivityCounter> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialActivityCounterUtil} to access the social activity counter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the social activity counters where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByGroupId(long groupId);

	/**
	 * Returns a range of all the social activity counters where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @return the range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the social activity counters where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns an ordered range of all the social activity counters where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first social activity counter in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity counter
	 * @throws NoSuchActivityCounterException if a matching social activity counter could not be found
	 */
	public SocialActivityCounter findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the first social activity counter in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns the last social activity counter in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity counter
	 * @throws NoSuchActivityCounterException if a matching social activity counter could not be found
	 */
	public SocialActivityCounter findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the last social activity counter in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns the social activity counters before and after the current social activity counter in the ordered set where groupId = &#63;.
	 *
	 * @param activityCounterId the primary key of the current social activity counter
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity counter
	 * @throws NoSuchActivityCounterException if a social activity counter with the primary key could not be found
	 */
	public SocialActivityCounter[] findByGroupId_PrevAndNext(
			long activityCounterId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Removes all the social activity counters where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of social activity counters where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching social activity counters
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the social activity counters where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByC_C(
		long classNameId, long classPK);

	/**
	 * Returns a range of all the social activity counters where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @return the range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByC_C(
		long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the social activity counters where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns an ordered range of all the social activity counters where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first social activity counter in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity counter
	 * @throws NoSuchActivityCounterException if a matching social activity counter could not be found
	 */
	public SocialActivityCounter findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the first social activity counter in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns the last social activity counter in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity counter
	 * @throws NoSuchActivityCounterException if a matching social activity counter could not be found
	 */
	public SocialActivityCounter findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the last social activity counter in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns the social activity counters before and after the current social activity counter in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param activityCounterId the primary key of the current social activity counter
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity counter
	 * @throws NoSuchActivityCounterException if a social activity counter with the primary key could not be found
	 */
	public SocialActivityCounter[] findByC_C_PrevAndNext(
			long activityCounterId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Removes all the social activity counters where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of social activity counters where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching social activity counters
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the social activity counters where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @return the matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByG_C_C_O(
		long groupId, long classNameId, long classPK, int ownerType);

	/**
	 * Returns a range of all the social activity counters where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @return the range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByG_C_C_O(
		long groupId, long classNameId, long classPK, int ownerType, int start,
		int end);

	/**
	 * Returns an ordered range of all the social activity counters where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByG_C_C_O(
		long groupId, long classNameId, long classPK, int ownerType, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns an ordered range of all the social activity counters where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social activity counters
	 */
	public java.util.List<SocialActivityCounter> findByG_C_C_O(
		long groupId, long classNameId, long classPK, int ownerType, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first social activity counter in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity counter
	 * @throws NoSuchActivityCounterException if a matching social activity counter could not be found
	 */
	public SocialActivityCounter findByG_C_C_O_First(
			long groupId, long classNameId, long classPK, int ownerType,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the first social activity counter in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByG_C_C_O_First(
		long groupId, long classNameId, long classPK, int ownerType,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns the last social activity counter in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity counter
	 * @throws NoSuchActivityCounterException if a matching social activity counter could not be found
	 */
	public SocialActivityCounter findByG_C_C_O_Last(
			long groupId, long classNameId, long classPK, int ownerType,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the last social activity counter in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByG_C_C_O_Last(
		long groupId, long classNameId, long classPK, int ownerType,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns the social activity counters before and after the current social activity counter in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * @param activityCounterId the primary key of the current social activity counter
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity counter
	 * @throws NoSuchActivityCounterException if a social activity counter with the primary key could not be found
	 */
	public SocialActivityCounter[] findByG_C_C_O_PrevAndNext(
			long activityCounterId, long groupId, long classNameId,
			long classPK, int ownerType,
			com.liferay.portal.kernel.util.OrderByComparator
				<SocialActivityCounter> orderByComparator)
		throws NoSuchActivityCounterException;

	/**
	 * Removes all the social activity counters where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 */
	public void removeByG_C_C_O(
		long groupId, long classNameId, long classPK, int ownerType);

	/**
	 * Returns the number of social activity counters where groupId = &#63; and classNameId = &#63; and classPK = &#63; and ownerType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param ownerType the owner type
	 * @return the number of matching social activity counters
	 */
	public int countByG_C_C_O(
		long groupId, long classNameId, long classPK, int ownerType);

	/**
	 * Returns the social activity counter where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and startPeriod = &#63; or throws a <code>NoSuchActivityCounterException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param startPeriod the start period
	 * @return the matching social activity counter
	 * @throws NoSuchActivityCounterException if a matching social activity counter could not be found
	 */
	public SocialActivityCounter findByG_C_C_N_O_S(
			long groupId, long classNameId, long classPK, String name,
			int ownerType, int startPeriod)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the social activity counter where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and startPeriod = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param startPeriod the start period
	 * @return the matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByG_C_C_N_O_S(
		long groupId, long classNameId, long classPK, String name,
		int ownerType, int startPeriod);

	/**
	 * Returns the social activity counter where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and startPeriod = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param startPeriod the start period
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByG_C_C_N_O_S(
		long groupId, long classNameId, long classPK, String name,
		int ownerType, int startPeriod, boolean useFinderCache);

	/**
	 * Removes the social activity counter where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and startPeriod = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param startPeriod the start period
	 * @return the social activity counter that was removed
	 */
	public SocialActivityCounter removeByG_C_C_N_O_S(
			long groupId, long classNameId, long classPK, String name,
			int ownerType, int startPeriod)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the number of social activity counters where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and startPeriod = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param startPeriod the start period
	 * @return the number of matching social activity counters
	 */
	public int countByG_C_C_N_O_S(
		long groupId, long classNameId, long classPK, String name,
		int ownerType, int startPeriod);

	/**
	 * Returns the social activity counter where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and endPeriod = &#63; or throws a <code>NoSuchActivityCounterException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param endPeriod the end period
	 * @return the matching social activity counter
	 * @throws NoSuchActivityCounterException if a matching social activity counter could not be found
	 */
	public SocialActivityCounter findByG_C_C_N_O_E(
			long groupId, long classNameId, long classPK, String name,
			int ownerType, int endPeriod)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the social activity counter where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and endPeriod = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param endPeriod the end period
	 * @return the matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByG_C_C_N_O_E(
		long groupId, long classNameId, long classPK, String name,
		int ownerType, int endPeriod);

	/**
	 * Returns the social activity counter where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and endPeriod = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param endPeriod the end period
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching social activity counter, or <code>null</code> if a matching social activity counter could not be found
	 */
	public SocialActivityCounter fetchByG_C_C_N_O_E(
		long groupId, long classNameId, long classPK, String name,
		int ownerType, int endPeriod, boolean useFinderCache);

	/**
	 * Removes the social activity counter where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and endPeriod = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param endPeriod the end period
	 * @return the social activity counter that was removed
	 */
	public SocialActivityCounter removeByG_C_C_N_O_E(
			long groupId, long classNameId, long classPK, String name,
			int ownerType, int endPeriod)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the number of social activity counters where groupId = &#63; and classNameId = &#63; and classPK = &#63; and name = &#63; and ownerType = &#63; and endPeriod = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param name the name
	 * @param ownerType the owner type
	 * @param endPeriod the end period
	 * @return the number of matching social activity counters
	 */
	public int countByG_C_C_N_O_E(
		long groupId, long classNameId, long classPK, String name,
		int ownerType, int endPeriod);

	/**
	 * Caches the social activity counter in the entity cache if it is enabled.
	 *
	 * @param socialActivityCounter the social activity counter
	 */
	public void cacheResult(SocialActivityCounter socialActivityCounter);

	/**
	 * Caches the social activity counters in the entity cache if it is enabled.
	 *
	 * @param socialActivityCounters the social activity counters
	 */
	public void cacheResult(
		java.util.List<SocialActivityCounter> socialActivityCounters);

	/**
	 * Creates a new social activity counter with the primary key. Does not add the social activity counter to the database.
	 *
	 * @param activityCounterId the primary key for the new social activity counter
	 * @return the new social activity counter
	 */
	public SocialActivityCounter create(long activityCounterId);

	/**
	 * Removes the social activity counter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityCounterId the primary key of the social activity counter
	 * @return the social activity counter that was removed
	 * @throws NoSuchActivityCounterException if a social activity counter with the primary key could not be found
	 */
	public SocialActivityCounter remove(long activityCounterId)
		throws NoSuchActivityCounterException;

	public SocialActivityCounter updateImpl(
		SocialActivityCounter socialActivityCounter);

	/**
	 * Returns the social activity counter with the primary key or throws a <code>NoSuchActivityCounterException</code> if it could not be found.
	 *
	 * @param activityCounterId the primary key of the social activity counter
	 * @return the social activity counter
	 * @throws NoSuchActivityCounterException if a social activity counter with the primary key could not be found
	 */
	public SocialActivityCounter findByPrimaryKey(long activityCounterId)
		throws NoSuchActivityCounterException;

	/**
	 * Returns the social activity counter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityCounterId the primary key of the social activity counter
	 * @return the social activity counter, or <code>null</code> if a social activity counter with the primary key could not be found
	 */
	public SocialActivityCounter fetchByPrimaryKey(long activityCounterId);

	/**
	 * Returns all the social activity counters.
	 *
	 * @return the social activity counters
	 */
	public java.util.List<SocialActivityCounter> findAll();

	/**
	 * Returns a range of all the social activity counters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @return the range of social activity counters
	 */
	public java.util.List<SocialActivityCounter> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the social activity counters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of social activity counters
	 */
	public java.util.List<SocialActivityCounter> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator);

	/**
	 * Returns an ordered range of all the social activity counters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialActivityCounterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activity counters
	 * @param end the upper bound of the range of social activity counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of social activity counters
	 */
	public java.util.List<SocialActivityCounter> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialActivityCounter>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the social activity counters from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of social activity counters.
	 *
	 * @return the number of social activity counters
	 */
	public int countAll();

}