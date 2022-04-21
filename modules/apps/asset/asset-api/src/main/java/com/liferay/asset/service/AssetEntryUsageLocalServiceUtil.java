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

package com.liferay.asset.service;

import com.liferay.asset.model.AssetEntryUsage;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AssetEntryUsage. This utility wraps
 * <code>com.liferay.asset.service.impl.AssetEntryUsageLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryUsageLocalService
 * @deprecated As of Mueller (7.2.x), replaced by {@link
 com.liferay.layout.service.impl.LayoutClassedModelUsageLocalServiceImpl}
 * @generated
 */
@Deprecated
public class AssetEntryUsageLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.asset.service.impl.AssetEntryUsageLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the asset entry usage to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntryUsageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntryUsage the asset entry usage
	 * @return the asset entry usage that was added
	 */
	public static AssetEntryUsage addAssetEntryUsage(
		AssetEntryUsage assetEntryUsage) {

		return getService().addAssetEntryUsage(assetEntryUsage);
	}

	public static AssetEntryUsage addAssetEntryUsage(
		long groupId, long assetEntryId, long containerType,
		String containerKey, long plid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().addAssetEntryUsage(
			groupId, assetEntryId, containerType, containerKey, plid,
			serviceContext);
	}

	public static AssetEntryUsage addDefaultAssetEntryUsage(
		long groupId, long assetEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().addDefaultAssetEntryUsage(
			groupId, assetEntryId, serviceContext);
	}

	/**
	 * Creates a new asset entry usage with the primary key. Does not add the asset entry usage to the database.
	 *
	 * @param assetEntryUsageId the primary key for the new asset entry usage
	 * @return the new asset entry usage
	 */
	public static AssetEntryUsage createAssetEntryUsage(
		long assetEntryUsageId) {

		return getService().createAssetEntryUsage(assetEntryUsageId);
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
	 * Deletes the asset entry usage from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntryUsageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntryUsage the asset entry usage
	 * @return the asset entry usage that was removed
	 */
	public static AssetEntryUsage deleteAssetEntryUsage(
		AssetEntryUsage assetEntryUsage) {

		return getService().deleteAssetEntryUsage(assetEntryUsage);
	}

	/**
	 * Deletes the asset entry usage with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntryUsageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntryUsageId the primary key of the asset entry usage
	 * @return the asset entry usage that was removed
	 * @throws PortalException if a asset entry usage with the primary key could not be found
	 */
	public static AssetEntryUsage deleteAssetEntryUsage(long assetEntryUsageId)
		throws PortalException {

		return getService().deleteAssetEntryUsage(assetEntryUsageId);
	}

	public static void deleteAssetEntryUsages(long assetEntryId) {
		getService().deleteAssetEntryUsages(assetEntryId);
	}

	public static void deleteAssetEntryUsages(
		long containerType, String containerKey, long plid) {

		getService().deleteAssetEntryUsages(containerType, containerKey, plid);
	}

	public static void deleteAssetEntryUsagesByPlid(long plid) {
		getService().deleteAssetEntryUsagesByPlid(plid);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.model.impl.AssetEntryUsageModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.model.impl.AssetEntryUsageModelImpl</code>.
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

	public static AssetEntryUsage fetchAssetEntryUsage(long assetEntryUsageId) {
		return getService().fetchAssetEntryUsage(assetEntryUsageId);
	}

	public static AssetEntryUsage fetchAssetEntryUsage(
		long assetEntryId, long containerType, String containerKey, long plid) {

		return getService().fetchAssetEntryUsage(
			assetEntryId, containerType, containerKey, plid);
	}

	/**
	 * Returns the asset entry usage matching the UUID and group.
	 *
	 * @param uuid the asset entry usage's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset entry usage, or <code>null</code> if a matching asset entry usage could not be found
	 */
	public static AssetEntryUsage fetchAssetEntryUsageByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchAssetEntryUsageByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the asset entry usage with the primary key.
	 *
	 * @param assetEntryUsageId the primary key of the asset entry usage
	 * @return the asset entry usage
	 * @throws PortalException if a asset entry usage with the primary key could not be found
	 */
	public static AssetEntryUsage getAssetEntryUsage(long assetEntryUsageId)
		throws PortalException {

		return getService().getAssetEntryUsage(assetEntryUsageId);
	}

	/**
	 * Returns the asset entry usage matching the UUID and group.
	 *
	 * @param uuid the asset entry usage's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset entry usage
	 * @throws PortalException if a matching asset entry usage could not be found
	 */
	public static AssetEntryUsage getAssetEntryUsageByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getAssetEntryUsageByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the asset entry usages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.model.impl.AssetEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry usages
	 * @param end the upper bound of the range of asset entry usages (not inclusive)
	 * @return the range of asset entry usages
	 */
	public static List<AssetEntryUsage> getAssetEntryUsages(
		int start, int end) {

		return getService().getAssetEntryUsages(start, end);
	}

	public static List<AssetEntryUsage> getAssetEntryUsages(long assetEntryId) {
		return getService().getAssetEntryUsages(assetEntryId);
	}

	public static List<AssetEntryUsage> getAssetEntryUsages(
		long assetEntryId, int type, int start, int end,
		OrderByComparator<AssetEntryUsage> orderByComparator) {

		return getService().getAssetEntryUsages(
			assetEntryId, type, start, end, orderByComparator);
	}

	public static List<AssetEntryUsage> getAssetEntryUsages(
		long assetEntryId, int start, int end,
		OrderByComparator<AssetEntryUsage> orderByComparator) {

		return getService().getAssetEntryUsages(
			assetEntryId, start, end, orderByComparator);
	}

	public static List<AssetEntryUsage> getAssetEntryUsagesByPlid(long plid) {
		return getService().getAssetEntryUsagesByPlid(plid);
	}

	/**
	 * Returns all the asset entry usages matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset entry usages
	 * @param companyId the primary key of the company
	 * @return the matching asset entry usages, or an empty list if no matches were found
	 */
	public static List<AssetEntryUsage> getAssetEntryUsagesByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getAssetEntryUsagesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of asset entry usages matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset entry usages
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of asset entry usages
	 * @param end the upper bound of the range of asset entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching asset entry usages, or an empty list if no matches were found
	 */
	public static List<AssetEntryUsage> getAssetEntryUsagesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetEntryUsage> orderByComparator) {

		return getService().getAssetEntryUsagesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of asset entry usages.
	 *
	 * @return the number of asset entry usages
	 */
	public static int getAssetEntryUsagesCount() {
		return getService().getAssetEntryUsagesCount();
	}

	public static int getAssetEntryUsagesCount(long assetEntryId) {
		return getService().getAssetEntryUsagesCount(assetEntryId);
	}

	public static int getAssetEntryUsagesCount(long assetEntryId, int type) {
		return getService().getAssetEntryUsagesCount(assetEntryId, type);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
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

	public static int getUniqueAssetEntryUsagesCount(long assetEntryId) {
		return getService().getUniqueAssetEntryUsagesCount(assetEntryId);
	}

	public static boolean hasDefaultAssetEntryUsage(long assetEntryId) {
		return getService().hasDefaultAssetEntryUsage(assetEntryId);
	}

	/**
	 * Updates the asset entry usage in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntryUsageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntryUsage the asset entry usage
	 * @return the asset entry usage that was updated
	 */
	public static AssetEntryUsage updateAssetEntryUsage(
		AssetEntryUsage assetEntryUsage) {

		return getService().updateAssetEntryUsage(assetEntryUsage);
	}

	public static AssetEntryUsageLocalService getService() {
		return _service;
	}

	private static volatile AssetEntryUsageLocalService _service;

}