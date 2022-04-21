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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.exception.NoSuchTransitionException;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the kaleo transition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTransitionUtil
 * @generated
 */
@ProviderType
public interface KaleoTransitionPersistence
	extends BasePersistence<KaleoTransition> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTransitionUtil} to access the kaleo transition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the kaleo transitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the kaleo transitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the kaleo transitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns an ordered range of all the kaleo transitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	public KaleoTransition findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Returns the first kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns the last kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	public KaleoTransition findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Returns the last kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	public KaleoTransition[] findByCompanyId_PrevAndNext(
			long kaleoTransitionId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Removes all the kaleo transitions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of kaleo transitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo transitions
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the kaleo transitions where kaleoDefinitionVersionId = &#63;.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @return the matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByKaleoDefinitionVersionId(
		long kaleoDefinitionVersionId);

	/**
	 * Returns a range of all the kaleo transitions where kaleoDefinitionVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByKaleoDefinitionVersionId(
		long kaleoDefinitionVersionId, int start, int end);

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoDefinitionVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByKaleoDefinitionVersionId(
		long kaleoDefinitionVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoDefinitionVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByKaleoDefinitionVersionId(
		long kaleoDefinitionVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoDefinitionVersionId = &#63;.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	public KaleoTransition findByKaleoDefinitionVersionId_First(
			long kaleoDefinitionVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoDefinitionVersionId = &#63;.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByKaleoDefinitionVersionId_First(
		long kaleoDefinitionVersionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoDefinitionVersionId = &#63;.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	public KaleoTransition findByKaleoDefinitionVersionId_Last(
			long kaleoDefinitionVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoDefinitionVersionId = &#63;.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByKaleoDefinitionVersionId_Last(
		long kaleoDefinitionVersionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoDefinitionVersionId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	public KaleoTransition[] findByKaleoDefinitionVersionId_PrevAndNext(
			long kaleoTransitionId, long kaleoDefinitionVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Removes all the kaleo transitions where kaleoDefinitionVersionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 */
	public void removeByKaleoDefinitionVersionId(long kaleoDefinitionVersionId);

	/**
	 * Returns the number of kaleo transitions where kaleoDefinitionVersionId = &#63;.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID
	 * @return the number of matching kaleo transitions
	 */
	public int countByKaleoDefinitionVersionId(long kaleoDefinitionVersionId);

	/**
	 * Returns all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByKaleoNodeId(long kaleoNodeId);

	/**
	 * Returns a range of all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end);

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo transitions
	 */
	public java.util.List<KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	public KaleoTransition findByKaleoNodeId_First(
			long kaleoNodeId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByKaleoNodeId_First(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	public KaleoTransition findByKaleoNodeId_Last(
			long kaleoNodeId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByKaleoNodeId_Last(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	public KaleoTransition[] findByKaleoNodeId_PrevAndNext(
			long kaleoTransitionId, long kaleoNodeId,
			com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
				orderByComparator)
		throws NoSuchTransitionException;

	/**
	 * Removes all the kaleo transitions where kaleoNodeId = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 */
	public void removeByKaleoNodeId(long kaleoNodeId);

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the number of matching kaleo transitions
	 */
	public int countByKaleoNodeId(long kaleoNodeId);

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or throws a <code>NoSuchTransitionException</code> if it could not be found.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	public KaleoTransition findByKNI_N(long kaleoNodeId, String name)
		throws NoSuchTransitionException;

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByKNI_N(long kaleoNodeId, String name);

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByKNI_N(
		long kaleoNodeId, String name, boolean useFinderCache);

	/**
	 * Removes the kaleo transition where kaleoNodeId = &#63; and name = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the kaleo transition that was removed
	 */
	public KaleoTransition removeByKNI_N(long kaleoNodeId, String name)
		throws NoSuchTransitionException;

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63; and name = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the number of matching kaleo transitions
	 */
	public int countByKNI_N(long kaleoNodeId, String name);

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or throws a <code>NoSuchTransitionException</code> if it could not be found.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	public KaleoTransition findByKNI_DT(
			long kaleoNodeId, boolean defaultTransition)
		throws NoSuchTransitionException;

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition);

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	public KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition, boolean useFinderCache);

	/**
	 * Removes the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the kaleo transition that was removed
	 */
	public KaleoTransition removeByKNI_DT(
			long kaleoNodeId, boolean defaultTransition)
		throws NoSuchTransitionException;

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63; and defaultTransition = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the number of matching kaleo transitions
	 */
	public int countByKNI_DT(long kaleoNodeId, boolean defaultTransition);

	/**
	 * Caches the kaleo transition in the entity cache if it is enabled.
	 *
	 * @param kaleoTransition the kaleo transition
	 */
	public void cacheResult(KaleoTransition kaleoTransition);

	/**
	 * Caches the kaleo transitions in the entity cache if it is enabled.
	 *
	 * @param kaleoTransitions the kaleo transitions
	 */
	public void cacheResult(java.util.List<KaleoTransition> kaleoTransitions);

	/**
	 * Creates a new kaleo transition with the primary key. Does not add the kaleo transition to the database.
	 *
	 * @param kaleoTransitionId the primary key for the new kaleo transition
	 * @return the new kaleo transition
	 */
	public KaleoTransition create(long kaleoTransitionId);

	/**
	 * Removes the kaleo transition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition that was removed
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	public KaleoTransition remove(long kaleoTransitionId)
		throws NoSuchTransitionException;

	public KaleoTransition updateImpl(KaleoTransition kaleoTransition);

	/**
	 * Returns the kaleo transition with the primary key or throws a <code>NoSuchTransitionException</code> if it could not be found.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	public KaleoTransition findByPrimaryKey(long kaleoTransitionId)
		throws NoSuchTransitionException;

	/**
	 * Returns the kaleo transition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition, or <code>null</code> if a kaleo transition with the primary key could not be found
	 */
	public KaleoTransition fetchByPrimaryKey(long kaleoTransitionId);

	/**
	 * Returns all the kaleo transitions.
	 *
	 * @return the kaleo transitions
	 */
	public java.util.List<KaleoTransition> findAll();

	/**
	 * Returns a range of all the kaleo transitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of kaleo transitions
	 */
	public java.util.List<KaleoTransition> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the kaleo transitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo transitions
	 */
	public java.util.List<KaleoTransition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator);

	/**
	 * Returns an ordered range of all the kaleo transitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kaleo transitions
	 */
	public java.util.List<KaleoTransition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTransition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the kaleo transitions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of kaleo transitions.
	 *
	 * @return the number of kaleo transitions
	 */
	public int countAll();

}