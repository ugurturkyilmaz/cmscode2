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

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for CPTaxCategory. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPTaxCategoryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CPTaxCategoryLocalService
 * @generated
 */
public class CPTaxCategoryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPTaxCategoryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cp tax category to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPTaxCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpTaxCategory the cp tax category
	 * @return the cp tax category that was added
	 */
	public static CPTaxCategory addCPTaxCategory(CPTaxCategory cpTaxCategory) {
		return getService().addCPTaxCategory(cpTaxCategory);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 #addCPTaxCategory(String, Map, Map, ServiceContext)}
	 */
	@Deprecated
	public static CPTaxCategory addCPTaxCategory(
			Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCPTaxCategory(
			nameMap, descriptionMap, serviceContext);
	}

	public static CPTaxCategory addCPTaxCategory(
			String externalReferenceCode, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCPTaxCategory(
			externalReferenceCode, nameMap, descriptionMap, serviceContext);
	}

	public static int countCPTaxCategoriesByCompanyId(
		long companyId, String keyword) {

		return getService().countCPTaxCategoriesByCompanyId(companyId, keyword);
	}

	/**
	 * Creates a new cp tax category with the primary key. Does not add the cp tax category to the database.
	 *
	 * @param CPTaxCategoryId the primary key for the new cp tax category
	 * @return the new cp tax category
	 */
	public static CPTaxCategory createCPTaxCategory(long CPTaxCategoryId) {
		return getService().createCPTaxCategory(CPTaxCategoryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteCPTaxCategories(long companyId) {
		getService().deleteCPTaxCategories(companyId);
	}

	/**
	 * Deletes the cp tax category from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPTaxCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpTaxCategory the cp tax category
	 * @return the cp tax category that was removed
	 * @throws PortalException
	 */
	public static CPTaxCategory deleteCPTaxCategory(CPTaxCategory cpTaxCategory)
		throws PortalException {

		return getService().deleteCPTaxCategory(cpTaxCategory);
	}

	/**
	 * Deletes the cp tax category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPTaxCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param CPTaxCategoryId the primary key of the cp tax category
	 * @return the cp tax category that was removed
	 * @throws PortalException if a cp tax category with the primary key could not be found
	 */
	public static CPTaxCategory deleteCPTaxCategory(long CPTaxCategoryId)
		throws PortalException {

		return getService().deleteCPTaxCategory(CPTaxCategoryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static CPTaxCategory fetchCPTaxCategory(long CPTaxCategoryId) {
		return getService().fetchCPTaxCategory(CPTaxCategoryId);
	}

	/**
	 * Returns the cp tax category with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the cp tax category's external reference code
	 * @return the matching cp tax category, or <code>null</code> if a matching cp tax category could not be found
	 */
	public static CPTaxCategory fetchCPTaxCategoryByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return getService().fetchCPTaxCategoryByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchCPTaxCategoryByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	public static CPTaxCategory fetchCPTaxCategoryByReferenceCode(
		long companyId, String externalReferenceCode) {

		return getService().fetchCPTaxCategoryByReferenceCode(
			companyId, externalReferenceCode);
	}

	public static List<CPTaxCategory> findCPTaxCategoriesByCompanyId(
		long companyId, String keyword, int start, int end) {

		return getService().findCPTaxCategoriesByCompanyId(
			companyId, keyword, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the cp tax categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp tax categories
	 * @param end the upper bound of the range of cp tax categories (not inclusive)
	 * @return the range of cp tax categories
	 */
	public static List<CPTaxCategory> getCPTaxCategories(int start, int end) {
		return getService().getCPTaxCategories(start, end);
	}

	public static List<CPTaxCategory> getCPTaxCategories(long companyId) {
		return getService().getCPTaxCategories(companyId);
	}

	public static List<CPTaxCategory> getCPTaxCategories(
		long companyId, int start, int end,
		OrderByComparator<CPTaxCategory> orderByComparator) {

		return getService().getCPTaxCategories(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of cp tax categories.
	 *
	 * @return the number of cp tax categories
	 */
	public static int getCPTaxCategoriesCount() {
		return getService().getCPTaxCategoriesCount();
	}

	public static int getCPTaxCategoriesCount(long companyId) {
		return getService().getCPTaxCategoriesCount(companyId);
	}

	/**
	 * Returns the cp tax category with the primary key.
	 *
	 * @param CPTaxCategoryId the primary key of the cp tax category
	 * @return the cp tax category
	 * @throws PortalException if a cp tax category with the primary key could not be found
	 */
	public static CPTaxCategory getCPTaxCategory(long CPTaxCategoryId)
		throws PortalException {

		return getService().getCPTaxCategory(CPTaxCategoryId);
	}

	/**
	 * Returns the cp tax category with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the cp tax category's external reference code
	 * @return the matching cp tax category
	 * @throws PortalException if a matching cp tax category could not be found
	 */
	public static CPTaxCategory getCPTaxCategoryByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return getService().getCPTaxCategoryByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the cp tax category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPTaxCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpTaxCategory the cp tax category
	 * @return the cp tax category that was updated
	 */
	public static CPTaxCategory updateCPTaxCategory(
		CPTaxCategory cpTaxCategory) {

		return getService().updateCPTaxCategory(cpTaxCategory);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 #updateCPTaxCategory(String, long, Map, Map)}
	 */
	@Deprecated
	public static CPTaxCategory updateCPTaxCategory(
			long cpTaxCategoryId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap)
		throws PortalException {

		return getService().updateCPTaxCategory(
			cpTaxCategoryId, nameMap, descriptionMap);
	}

	public static CPTaxCategory updateCPTaxCategory(
			String externalReferenceCode, long cpTaxCategoryId,
			Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap)
		throws PortalException {

		return getService().updateCPTaxCategory(
			externalReferenceCode, cpTaxCategoryId, nameMap, descriptionMap);
	}

	public static CPTaxCategoryLocalService getService() {
		return _service;
	}

	private static volatile CPTaxCategoryLocalService _service;

}