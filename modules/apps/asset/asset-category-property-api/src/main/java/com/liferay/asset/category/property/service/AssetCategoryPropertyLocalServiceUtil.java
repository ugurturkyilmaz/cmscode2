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

package com.liferay.asset.category.property.service;

import com.liferay.asset.category.property.model.AssetCategoryProperty;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AssetCategoryProperty. This utility wraps
 * <code>com.liferay.asset.category.property.service.impl.AssetCategoryPropertyLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryPropertyLocalService
 * @generated
 */
public class AssetCategoryPropertyLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.asset.category.property.service.impl.AssetCategoryPropertyLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the asset category property to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetCategoryPropertyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetCategoryProperty the asset category property
	 * @return the asset category property that was added
	 */
	public static AssetCategoryProperty addAssetCategoryProperty(
		AssetCategoryProperty assetCategoryProperty) {

		return getService().addAssetCategoryProperty(assetCategoryProperty);
	}

	public static AssetCategoryProperty addCategoryProperty(
			long userId, long categoryId, String key, String value)
		throws PortalException {

		return getService().addCategoryProperty(userId, categoryId, key, value);
	}

	/**
	 * Creates a new asset category property with the primary key. Does not add the asset category property to the database.
	 *
	 * @param categoryPropertyId the primary key for the new asset category property
	 * @return the new asset category property
	 */
	public static AssetCategoryProperty createAssetCategoryProperty(
		long categoryPropertyId) {

		return getService().createAssetCategoryProperty(categoryPropertyId);
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
	 * Deletes the asset category property from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetCategoryPropertyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetCategoryProperty the asset category property
	 * @return the asset category property that was removed
	 */
	public static AssetCategoryProperty deleteAssetCategoryProperty(
		AssetCategoryProperty assetCategoryProperty) {

		return getService().deleteAssetCategoryProperty(assetCategoryProperty);
	}

	/**
	 * Deletes the asset category property with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetCategoryPropertyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoryPropertyId the primary key of the asset category property
	 * @return the asset category property that was removed
	 * @throws PortalException if a asset category property with the primary key could not be found
	 */
	public static AssetCategoryProperty deleteAssetCategoryProperty(
			long categoryPropertyId)
		throws PortalException {

		return getService().deleteAssetCategoryProperty(categoryPropertyId);
	}

	public static void deleteCategoryProperties(long entryId) {
		getService().deleteCategoryProperties(entryId);
	}

	public static void deleteCategoryProperty(
		AssetCategoryProperty categoryProperty) {

		getService().deleteCategoryProperty(categoryProperty);
	}

	public static void deleteCategoryProperty(long categoryPropertyId)
		throws PortalException {

		getService().deleteCategoryProperty(categoryPropertyId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.category.property.model.impl.AssetCategoryPropertyModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.category.property.model.impl.AssetCategoryPropertyModelImpl</code>.
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

	public static AssetCategoryProperty fetchAssetCategoryProperty(
		long categoryPropertyId) {

		return getService().fetchAssetCategoryProperty(categoryPropertyId);
	}

	public static AssetCategoryProperty fetchCategoryProperty(
		long categoryId, String key) {

		return getService().fetchCategoryProperty(categoryId, key);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the asset category properties.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.category.property.model.impl.AssetCategoryPropertyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset category properties
	 * @param end the upper bound of the range of asset category properties (not inclusive)
	 * @return the range of asset category properties
	 */
	public static List<AssetCategoryProperty> getAssetCategoryProperties(
		int start, int end) {

		return getService().getAssetCategoryProperties(start, end);
	}

	/**
	 * Returns the number of asset category properties.
	 *
	 * @return the number of asset category properties
	 */
	public static int getAssetCategoryPropertiesCount() {
		return getService().getAssetCategoryPropertiesCount();
	}

	/**
	 * Returns the asset category property with the primary key.
	 *
	 * @param categoryPropertyId the primary key of the asset category property
	 * @return the asset category property
	 * @throws PortalException if a asset category property with the primary key could not be found
	 */
	public static AssetCategoryProperty getAssetCategoryProperty(
			long categoryPropertyId)
		throws PortalException {

		return getService().getAssetCategoryProperty(categoryPropertyId);
	}

	public static List<AssetCategoryProperty> getCategoryProperties() {
		return getService().getCategoryProperties();
	}

	public static List<AssetCategoryProperty> getCategoryProperties(
		long entryId) {

		return getService().getCategoryProperties(entryId);
	}

	public static AssetCategoryProperty getCategoryProperty(
			long categoryPropertyId)
		throws PortalException {

		return getService().getCategoryProperty(categoryPropertyId);
	}

	public static AssetCategoryProperty getCategoryProperty(
			long categoryId, String key)
		throws PortalException {

		return getService().getCategoryProperty(categoryId, key);
	}

	public static List<AssetCategoryProperty> getCategoryPropertyValues(
		long groupId, String key) {

		return getService().getCategoryPropertyValues(groupId, key);
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
	 * Updates the asset category property in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetCategoryPropertyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetCategoryProperty the asset category property
	 * @return the asset category property that was updated
	 */
	public static AssetCategoryProperty updateAssetCategoryProperty(
		AssetCategoryProperty assetCategoryProperty) {

		return getService().updateAssetCategoryProperty(assetCategoryProperty);
	}

	public static AssetCategoryProperty updateCategoryProperty(
			long userId, long categoryPropertyId, String key, String value)
		throws PortalException {

		return getService().updateCategoryProperty(
			userId, categoryPropertyId, key, value);
	}

	public static AssetCategoryProperty updateCategoryProperty(
			long categoryPropertyId, String key, String value)
		throws PortalException {

		return getService().updateCategoryProperty(
			categoryPropertyId, key, value);
	}

	public static AssetCategoryPropertyLocalService getService() {
		return _service;
	}

	private static volatile AssetCategoryPropertyLocalService _service;

}