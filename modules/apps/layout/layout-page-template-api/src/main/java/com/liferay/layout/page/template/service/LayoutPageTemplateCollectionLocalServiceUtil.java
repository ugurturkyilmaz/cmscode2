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

package com.liferay.layout.page.template.service;

import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for LayoutPageTemplateCollection. This utility wraps
 * <code>com.liferay.layout.page.template.service.impl.LayoutPageTemplateCollectionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateCollectionLocalService
 * @generated
 */
public class LayoutPageTemplateCollectionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.layout.page.template.service.impl.LayoutPageTemplateCollectionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the layout page template collection to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutPageTemplateCollectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutPageTemplateCollection the layout page template collection
	 * @return the layout page template collection that was added
	 */
	public static LayoutPageTemplateCollection addLayoutPageTemplateCollection(
		LayoutPageTemplateCollection layoutPageTemplateCollection) {

		return getService().addLayoutPageTemplateCollection(
			layoutPageTemplateCollection);
	}

	public static LayoutPageTemplateCollection addLayoutPageTemplateCollection(
			long userId, long groupId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addLayoutPageTemplateCollection(
			userId, groupId, name, description, serviceContext);
	}

	/**
	 * Creates a new layout page template collection with the primary key. Does not add the layout page template collection to the database.
	 *
	 * @param layoutPageTemplateCollectionId the primary key for the new layout page template collection
	 * @return the new layout page template collection
	 */
	public static LayoutPageTemplateCollection
		createLayoutPageTemplateCollection(
			long layoutPageTemplateCollectionId) {

		return getService().createLayoutPageTemplateCollection(
			layoutPageTemplateCollectionId);
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
	 * Deletes the layout page template collection from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutPageTemplateCollectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutPageTemplateCollection the layout page template collection
	 * @return the layout page template collection that was removed
	 * @throws PortalException
	 */
	public static LayoutPageTemplateCollection
			deleteLayoutPageTemplateCollection(
				LayoutPageTemplateCollection layoutPageTemplateCollection)
		throws PortalException {

		return getService().deleteLayoutPageTemplateCollection(
			layoutPageTemplateCollection);
	}

	/**
	 * Deletes the layout page template collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutPageTemplateCollectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutPageTemplateCollectionId the primary key of the layout page template collection
	 * @return the layout page template collection that was removed
	 * @throws PortalException if a layout page template collection with the primary key could not be found
	 */
	public static LayoutPageTemplateCollection
			deleteLayoutPageTemplateCollection(
				long layoutPageTemplateCollectionId)
		throws PortalException {

		return getService().deleteLayoutPageTemplateCollection(
			layoutPageTemplateCollectionId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateCollectionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateCollectionModelImpl</code>.
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

	public static LayoutPageTemplateCollection
		fetchLayoutPageTemplateCollection(long layoutPageTemplateCollectionId) {

		return getService().fetchLayoutPageTemplateCollection(
			layoutPageTemplateCollectionId);
	}

	public static LayoutPageTemplateCollection
		fetchLayoutPageTemplateCollection(
			long groupId, String layoutPageTemplateCollectionKey) {

		return getService().fetchLayoutPageTemplateCollection(
			groupId, layoutPageTemplateCollectionKey);
	}

	/**
	 * Returns the layout page template collection matching the UUID and group.
	 *
	 * @param uuid the layout page template collection's UUID
	 * @param groupId the primary key of the group
	 * @return the matching layout page template collection, or <code>null</code> if a matching layout page template collection could not be found
	 */
	public static LayoutPageTemplateCollection
		fetchLayoutPageTemplateCollectionByUuidAndGroupId(
			String uuid, long groupId) {

		return getService().fetchLayoutPageTemplateCollectionByUuidAndGroupId(
			uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	 * Returns the layout page template collection with the primary key.
	 *
	 * @param layoutPageTemplateCollectionId the primary key of the layout page template collection
	 * @return the layout page template collection
	 * @throws PortalException if a layout page template collection with the primary key could not be found
	 */
	public static LayoutPageTemplateCollection getLayoutPageTemplateCollection(
			long layoutPageTemplateCollectionId)
		throws PortalException {

		return getService().getLayoutPageTemplateCollection(
			layoutPageTemplateCollectionId);
	}

	/**
	 * Returns the layout page template collection matching the UUID and group.
	 *
	 * @param uuid the layout page template collection's UUID
	 * @param groupId the primary key of the group
	 * @return the matching layout page template collection
	 * @throws PortalException if a matching layout page template collection could not be found
	 */
	public static LayoutPageTemplateCollection
			getLayoutPageTemplateCollectionByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return getService().getLayoutPageTemplateCollectionByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the layout page template collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page template collections
	 * @param end the upper bound of the range of layout page template collections (not inclusive)
	 * @return the range of layout page template collections
	 */
	public static List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollections(int start, int end) {

		return getService().getLayoutPageTemplateCollections(start, end);
	}

	public static List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollections(long groupId, int start, int end) {

		return getService().getLayoutPageTemplateCollections(
			groupId, start, end);
	}

	public static List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollections(
			long groupId, int start, int end,
			OrderByComparator<LayoutPageTemplateCollection> orderByComparator) {

		return getService().getLayoutPageTemplateCollections(
			groupId, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollections(
			long groupId, String name, int start, int end,
			OrderByComparator<LayoutPageTemplateCollection> orderByComparator) {

		return getService().getLayoutPageTemplateCollections(
			groupId, name, start, end, orderByComparator);
	}

	/**
	 * Returns all the layout page template collections matching the UUID and company.
	 *
	 * @param uuid the UUID of the layout page template collections
	 * @param companyId the primary key of the company
	 * @return the matching layout page template collections, or an empty list if no matches were found
	 */
	public static List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollectionsByUuidAndCompanyId(
			String uuid, long companyId) {

		return getService().getLayoutPageTemplateCollectionsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of layout page template collections matching the UUID and company.
	 *
	 * @param uuid the UUID of the layout page template collections
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of layout page template collections
	 * @param end the upper bound of the range of layout page template collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching layout page template collections, or an empty list if no matches were found
	 */
	public static List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollectionsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<LayoutPageTemplateCollection> orderByComparator) {

		return getService().getLayoutPageTemplateCollectionsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of layout page template collections.
	 *
	 * @return the number of layout page template collections
	 */
	public static int getLayoutPageTemplateCollectionsCount() {
		return getService().getLayoutPageTemplateCollectionsCount();
	}

	public static int getLayoutPageTemplateCollectionsCount(long groupId) {
		return getService().getLayoutPageTemplateCollectionsCount(groupId);
	}

	public static int getLayoutPageTemplateCollectionsCount(
		long groupId, String name) {

		return getService().getLayoutPageTemplateCollectionsCount(
			groupId, name);
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
	 * Updates the layout page template collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutPageTemplateCollectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutPageTemplateCollection the layout page template collection
	 * @return the layout page template collection that was updated
	 */
	public static LayoutPageTemplateCollection
		updateLayoutPageTemplateCollection(
			LayoutPageTemplateCollection layoutPageTemplateCollection) {

		return getService().updateLayoutPageTemplateCollection(
			layoutPageTemplateCollection);
	}

	public static LayoutPageTemplateCollection
			updateLayoutPageTemplateCollection(
				long layoutPageTemplateCollectionId, String name,
				String description)
		throws PortalException {

		return getService().updateLayoutPageTemplateCollection(
			layoutPageTemplateCollectionId, name, description);
	}

	public static LayoutPageTemplateCollectionLocalService getService() {
		return _service;
	}

	private static volatile LayoutPageTemplateCollectionLocalService _service;

}