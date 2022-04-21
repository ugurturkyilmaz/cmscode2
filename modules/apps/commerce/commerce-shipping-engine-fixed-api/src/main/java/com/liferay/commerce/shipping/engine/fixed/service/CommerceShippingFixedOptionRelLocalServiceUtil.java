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

package com.liferay.commerce.shipping.engine.fixed.service;

import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionRel;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CommerceShippingFixedOptionRel. This utility wraps
 * <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingFixedOptionRelLocalService
 * @generated
 */
public class CommerceShippingFixedOptionRelLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce shipping fixed option rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceShippingFixedOptionRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceShippingFixedOptionRel the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel that was added
	 */
	public static CommerceShippingFixedOptionRel
		addCommerceShippingFixedOptionRel(
			CommerceShippingFixedOptionRel commerceShippingFixedOptionRel) {

		return getService().addCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionRel);
	}

	public static CommerceShippingFixedOptionRel
			addCommerceShippingFixedOptionRel(
				long userId, long groupId, long commerceShippingMethodId,
				long commerceShippingFixedOptionId,
				long commerceInventoryWarehouseId, long countryId,
				long regionId, String zip, double weightFrom, double weightTo,
				java.math.BigDecimal fixedPrice,
				java.math.BigDecimal rateUnitWeightPrice, double ratePercentage)
		throws PortalException {

		return getService().addCommerceShippingFixedOptionRel(
			userId, groupId, commerceShippingMethodId,
			commerceShippingFixedOptionId, commerceInventoryWarehouseId,
			countryId, regionId, zip, weightFrom, weightTo, fixedPrice,
			rateUnitWeightPrice, ratePercentage);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public static CommerceShippingFixedOptionRel
			addCommerceShippingFixedOptionRel(
				long commerceShippingMethodId,
				long commerceShippingFixedOptionId,
				long commerceInventoryWarehouseId, long countryId,
				long regionId, String zip, double weightFrom, double weightTo,
				java.math.BigDecimal fixedPrice,
				java.math.BigDecimal rateUnitWeightPrice, double ratePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceShippingFixedOptionRel(
			commerceShippingMethodId, commerceShippingFixedOptionId,
			commerceInventoryWarehouseId, countryId, regionId, zip, weightFrom,
			weightTo, fixedPrice, rateUnitWeightPrice, ratePercentage,
			serviceContext);
	}

	/**
	 * Creates a new commerce shipping fixed option rel with the primary key. Does not add the commerce shipping fixed option rel to the database.
	 *
	 * @param commerceShippingFixedOptionRelId the primary key for the new commerce shipping fixed option rel
	 * @return the new commerce shipping fixed option rel
	 */
	public static CommerceShippingFixedOptionRel
		createCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionRelId) {

		return getService().createCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionRelId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the commerce shipping fixed option rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceShippingFixedOptionRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceShippingFixedOptionRel the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel that was removed
	 */
	public static CommerceShippingFixedOptionRel
		deleteCommerceShippingFixedOptionRel(
			CommerceShippingFixedOptionRel commerceShippingFixedOptionRel) {

		return getService().deleteCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionRel);
	}

	/**
	 * Deletes the commerce shipping fixed option rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceShippingFixedOptionRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceShippingFixedOptionRelId the primary key of the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel that was removed
	 * @throws PortalException if a commerce shipping fixed option rel with the primary key could not be found
	 */
	public static CommerceShippingFixedOptionRel
			deleteCommerceShippingFixedOptionRel(
				long commerceShippingFixedOptionRelId)
		throws PortalException {

		return getService().deleteCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionRelId);
	}

	public static void deleteCommerceShippingFixedOptionRels(
		long commerceShippingFixedOptionId) {

		getService().deleteCommerceShippingFixedOptionRels(
			commerceShippingFixedOptionId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionRelModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionRelModelImpl</code>.
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

	public static CommerceShippingFixedOptionRel
		fetchCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionRelId) {

		return getService().fetchCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionRelId);
	}

	public static CommerceShippingFixedOptionRel
		fetchCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionId, long countryId, long regionId,
			String zip, double weight) {

		return getService().fetchCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionId, countryId, regionId, zip, weight);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce shipping fixed option rel with the primary key.
	 *
	 * @param commerceShippingFixedOptionRelId the primary key of the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel
	 * @throws PortalException if a commerce shipping fixed option rel with the primary key could not be found
	 */
	public static CommerceShippingFixedOptionRel
			getCommerceShippingFixedOptionRel(
				long commerceShippingFixedOptionRelId)
		throws PortalException {

		return getService().getCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionRelId);
	}

	/**
	 * Returns a range of all the commerce shipping fixed option rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce shipping fixed option rels
	 * @param end the upper bound of the range of commerce shipping fixed option rels (not inclusive)
	 * @return the range of commerce shipping fixed option rels
	 */
	public static List<CommerceShippingFixedOptionRel>
		getCommerceShippingFixedOptionRels(int start, int end) {

		return getService().getCommerceShippingFixedOptionRels(start, end);
	}

	public static List<CommerceShippingFixedOptionRel>
		getCommerceShippingFixedOptionRels(
			long commerceShippingFixedOptionId, int start, int end) {

		return getService().getCommerceShippingFixedOptionRels(
			commerceShippingFixedOptionId, start, end);
	}

	public static List<CommerceShippingFixedOptionRel>
		getCommerceShippingFixedOptionRels(
			long commerceShippingFixedOptionId, int start, int end,
			OrderByComparator<CommerceShippingFixedOptionRel>
				orderByComparator) {

		return getService().getCommerceShippingFixedOptionRels(
			commerceShippingFixedOptionId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce shipping fixed option rels.
	 *
	 * @return the number of commerce shipping fixed option rels
	 */
	public static int getCommerceShippingFixedOptionRelsCount() {
		return getService().getCommerceShippingFixedOptionRelsCount();
	}

	public static int getCommerceShippingFixedOptionRelsCount(
		long commerceShippingFixedOptionId) {

		return getService().getCommerceShippingFixedOptionRelsCount(
			commerceShippingFixedOptionId);
	}

	public static List<CommerceShippingFixedOptionRel>
		getCommerceShippingMethodFixedOptionRels(
			long commerceShippingMethodId, int start, int end) {

		return getService().getCommerceShippingMethodFixedOptionRels(
			commerceShippingMethodId, start, end);
	}

	public static List<CommerceShippingFixedOptionRel>
		getCommerceShippingMethodFixedOptionRels(
			long commerceShippingMethodId, int start, int end,
			OrderByComparator<CommerceShippingFixedOptionRel>
				orderByComparator) {

		return getService().getCommerceShippingMethodFixedOptionRels(
			commerceShippingMethodId, start, end, orderByComparator);
	}

	public static int getCommerceShippingMethodFixedOptionRelsCount(
		long commerceShippingMethodId) {

		return getService().getCommerceShippingMethodFixedOptionRelsCount(
			commerceShippingMethodId);
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
	 * Updates the commerce shipping fixed option rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceShippingFixedOptionRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceShippingFixedOptionRel the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel that was updated
	 */
	public static CommerceShippingFixedOptionRel
		updateCommerceShippingFixedOptionRel(
			CommerceShippingFixedOptionRel commerceShippingFixedOptionRel) {

		return getService().updateCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionRel);
	}

	public static CommerceShippingFixedOptionRel
			updateCommerceShippingFixedOptionRel(
				long commerceShippingFixedOptionRelId,
				long commerceInventoryWarehouseId, long countryId,
				long regionId, String zip, double weightFrom, double weightTo,
				java.math.BigDecimal fixedPrice,
				java.math.BigDecimal rateUnitWeightPrice, double ratePercentage)
		throws PortalException {

		return getService().updateCommerceShippingFixedOptionRel(
			commerceShippingFixedOptionRelId, commerceInventoryWarehouseId,
			countryId, regionId, zip, weightFrom, weightTo, fixedPrice,
			rateUnitWeightPrice, ratePercentage);
	}

	public static CommerceShippingFixedOptionRelLocalService getService() {
		return _service;
	}

	private static volatile CommerceShippingFixedOptionRelLocalService _service;

}