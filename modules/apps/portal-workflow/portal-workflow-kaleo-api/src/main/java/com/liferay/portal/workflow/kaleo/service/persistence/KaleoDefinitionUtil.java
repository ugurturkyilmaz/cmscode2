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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the kaleo definition service. This utility wraps <code>com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoDefinitionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionPersistence
 * @generated
 */
public class KaleoDefinitionUtil {

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
	public static void clearCache(KaleoDefinition kaleoDefinition) {
		getPersistence().clearCache(kaleoDefinition);
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
	public static Map<Serializable, KaleoDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static KaleoDefinition update(KaleoDefinition kaleoDefinition) {
		return getPersistence().update(kaleoDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static KaleoDefinition update(
		KaleoDefinition kaleoDefinition, ServiceContext serviceContext) {

		return getPersistence().update(kaleoDefinition, serviceContext);
	}

	/**
	 * Returns all the kaleo definitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the kaleo definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByCompanyId_First(
			long companyId,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByCompanyId_First(
		long companyId, OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByCompanyId_Last(
			long companyId,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByCompanyId_Last(
		long companyId, OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	public static KaleoDefinition[] findByCompanyId_PrevAndNext(
			long kaleoDefinitionId, long companyId,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByCompanyId_PrevAndNext(
			kaleoDefinitionId, companyId, orderByComparator);
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo definitions
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; or throws a <code>NoSuchDefinitionException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_N(long companyId, String name)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_N(companyId, name);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_N(long companyId, String name) {
		return getPersistence().fetchByC_N(companyId, name);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_N(
		long companyId, String name, boolean useFinderCache) {

		return getPersistence().fetchByC_N(companyId, name, useFinderCache);
	}

	/**
	 * Removes the kaleo definition where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the kaleo definition that was removed
	 */
	public static KaleoDefinition removeByC_N(long companyId, String name)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().removeByC_N(companyId, name);
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching kaleo definitions
	 */
	public static int countByC_N(long companyId, String name) {
		return getPersistence().countByC_N(companyId, name);
	}

	/**
	 * Returns all the kaleo definitions where companyId = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @return the matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_S(
		long companyId, String scope) {

		return getPersistence().findByC_S(companyId, scope);
	}

	/**
	 * Returns a range of all the kaleo definitions where companyId = &#63; and scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_S(
		long companyId, String scope, int start, int end) {

		return getPersistence().findByC_S(companyId, scope, start, end);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_S(
		long companyId, String scope, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().findByC_S(
			companyId, scope, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_S(
		long companyId, String scope, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_S(
			companyId, scope, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_S_First(
			long companyId, String scope,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_S_First(
			companyId, scope, orderByComparator);
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_S_First(
		long companyId, String scope,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().fetchByC_S_First(
			companyId, scope, orderByComparator);
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_S_Last(
			long companyId, String scope,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_S_Last(
			companyId, scope, orderByComparator);
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_S_Last(
		long companyId, String scope,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().fetchByC_S_Last(
			companyId, scope, orderByComparator);
	}

	/**
	 * Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and scope = &#63;.
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	public static KaleoDefinition[] findByC_S_PrevAndNext(
			long kaleoDefinitionId, long companyId, String scope,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_S_PrevAndNext(
			kaleoDefinitionId, companyId, scope, orderByComparator);
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and scope = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 */
	public static void removeByC_S(long companyId, String scope) {
		getPersistence().removeByC_S(companyId, scope);
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @return the number of matching kaleo definitions
	 */
	public static int countByC_S(long companyId, String scope) {
		return getPersistence().countByC_S(companyId, scope);
	}

	/**
	 * Returns all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_A(
		long companyId, boolean active) {

		return getPersistence().findByC_A(companyId, active);
	}

	/**
	 * Returns a range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_A(
		long companyId, boolean active, int start, int end) {

		return getPersistence().findByC_A(companyId, active, start, end);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_A(
		long companyId, boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().findByC_A(
			companyId, active, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_A(
		long companyId, boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_A(
			companyId, active, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_A_First(
			long companyId, boolean active,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_A_First(
			companyId, active, orderByComparator);
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_A_First(
		long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().fetchByC_A_First(
			companyId, active, orderByComparator);
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_A_Last(
			long companyId, boolean active,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_A_Last(
			companyId, active, orderByComparator);
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_A_Last(
		long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().fetchByC_A_Last(
			companyId, active, orderByComparator);
	}

	/**
	 * Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	public static KaleoDefinition[] findByC_A_PrevAndNext(
			long kaleoDefinitionId, long companyId, boolean active,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_A_PrevAndNext(
			kaleoDefinitionId, companyId, active, orderByComparator);
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 */
	public static void removeByC_A(long companyId, boolean active) {
		getPersistence().removeByC_A(companyId, active);
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the number of matching kaleo definitions
	 */
	public static int countByC_A(long companyId, boolean active) {
		return getPersistence().countByC_A(companyId, active);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or throws a <code>NoSuchDefinitionException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_N_V(
			long companyId, String name, int version)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_N_V(companyId, name, version);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_N_V(
		long companyId, String name, int version) {

		return getPersistence().fetchByC_N_V(companyId, name, version);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_N_V(
		long companyId, String name, int version, boolean useFinderCache) {

		return getPersistence().fetchByC_N_V(
			companyId, name, version, useFinderCache);
	}

	/**
	 * Removes the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the kaleo definition that was removed
	 */
	public static KaleoDefinition removeByC_N_V(
			long companyId, String name, int version)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().removeByC_N_V(companyId, name, version);
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the number of matching kaleo definitions
	 */
	public static int countByC_N_V(long companyId, String name, int version) {
		return getPersistence().countByC_N_V(companyId, name, version);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and active = &#63; or throws a <code>NoSuchDefinitionException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @return the matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_N_A(
			long companyId, String name, boolean active)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_N_A(companyId, name, active);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and active = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_N_A(
		long companyId, String name, boolean active) {

		return getPersistence().fetchByC_N_A(companyId, name, active);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and active = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_N_A(
		long companyId, String name, boolean active, boolean useFinderCache) {

		return getPersistence().fetchByC_N_A(
			companyId, name, active, useFinderCache);
	}

	/**
	 * Removes the kaleo definition where companyId = &#63; and name = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @return the kaleo definition that was removed
	 */
	public static KaleoDefinition removeByC_N_A(
			long companyId, String name, boolean active)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().removeByC_N_A(companyId, name, active);
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @return the number of matching kaleo definitions
	 */
	public static int countByC_N_A(
		long companyId, String name, boolean active) {

		return getPersistence().countByC_N_A(companyId, name, active);
	}

	/**
	 * Returns all the kaleo definitions where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @return the matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_S_A(
		long companyId, String scope, boolean active) {

		return getPersistence().findByC_S_A(companyId, scope, active);
	}

	/**
	 * Returns a range of all the kaleo definitions where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_S_A(
		long companyId, String scope, boolean active, int start, int end) {

		return getPersistence().findByC_S_A(
			companyId, scope, active, start, end);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_S_A(
		long companyId, String scope, boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().findByC_S_A(
			companyId, scope, active, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo definitions
	 */
	public static List<KaleoDefinition> findByC_S_A(
		long companyId, String scope, boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_S_A(
			companyId, scope, active, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_S_A_First(
			long companyId, String scope, boolean active,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_S_A_First(
			companyId, scope, active, orderByComparator);
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_S_A_First(
		long companyId, String scope, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().fetchByC_S_A_First(
			companyId, scope, active, orderByComparator);
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition findByC_S_A_Last(
			long companyId, String scope, boolean active,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_S_A_Last(
			companyId, scope, active, orderByComparator);
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	public static KaleoDefinition fetchByC_S_A_Last(
		long companyId, String scope, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().fetchByC_S_A_Last(
			companyId, scope, active, orderByComparator);
	}

	/**
	 * Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	public static KaleoDefinition[] findByC_S_A_PrevAndNext(
			long kaleoDefinitionId, long companyId, String scope,
			boolean active,
			OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByC_S_A_PrevAndNext(
			kaleoDefinitionId, companyId, scope, active, orderByComparator);
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and scope = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 */
	public static void removeByC_S_A(
		long companyId, String scope, boolean active) {

		getPersistence().removeByC_S_A(companyId, scope, active);
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and scope = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param active the active
	 * @return the number of matching kaleo definitions
	 */
	public static int countByC_S_A(
		long companyId, String scope, boolean active) {

		return getPersistence().countByC_S_A(companyId, scope, active);
	}

	/**
	 * Caches the kaleo definition in the entity cache if it is enabled.
	 *
	 * @param kaleoDefinition the kaleo definition
	 */
	public static void cacheResult(KaleoDefinition kaleoDefinition) {
		getPersistence().cacheResult(kaleoDefinition);
	}

	/**
	 * Caches the kaleo definitions in the entity cache if it is enabled.
	 *
	 * @param kaleoDefinitions the kaleo definitions
	 */
	public static void cacheResult(List<KaleoDefinition> kaleoDefinitions) {
		getPersistence().cacheResult(kaleoDefinitions);
	}

	/**
	 * Creates a new kaleo definition with the primary key. Does not add the kaleo definition to the database.
	 *
	 * @param kaleoDefinitionId the primary key for the new kaleo definition
	 * @return the new kaleo definition
	 */
	public static KaleoDefinition create(long kaleoDefinitionId) {
		return getPersistence().create(kaleoDefinitionId);
	}

	/**
	 * Removes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition
	 * @return the kaleo definition that was removed
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	public static KaleoDefinition remove(long kaleoDefinitionId)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().remove(kaleoDefinitionId);
	}

	public static KaleoDefinition updateImpl(KaleoDefinition kaleoDefinition) {
		return getPersistence().updateImpl(kaleoDefinition);
	}

	/**
	 * Returns the kaleo definition with the primary key or throws a <code>NoSuchDefinitionException</code> if it could not be found.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition
	 * @return the kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	public static KaleoDefinition findByPrimaryKey(long kaleoDefinitionId)
		throws com.liferay.portal.workflow.kaleo.exception.
			NoSuchDefinitionException {

		return getPersistence().findByPrimaryKey(kaleoDefinitionId);
	}

	/**
	 * Returns the kaleo definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition
	 * @return the kaleo definition, or <code>null</code> if a kaleo definition with the primary key could not be found
	 */
	public static KaleoDefinition fetchByPrimaryKey(long kaleoDefinitionId) {
		return getPersistence().fetchByPrimaryKey(kaleoDefinitionId);
	}

	/**
	 * Returns all the kaleo definitions.
	 *
	 * @return the kaleo definitions
	 */
	public static List<KaleoDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the kaleo definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of kaleo definitions
	 */
	public static List<KaleoDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo definitions
	 */
	public static List<KaleoDefinition> findAll(
		int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kaleo definitions
	 */
	public static List<KaleoDefinition> findAll(
		int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the kaleo definitions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of kaleo definitions.
	 *
	 * @return the number of kaleo definitions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KaleoDefinitionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile KaleoDefinitionPersistence _persistence;

}