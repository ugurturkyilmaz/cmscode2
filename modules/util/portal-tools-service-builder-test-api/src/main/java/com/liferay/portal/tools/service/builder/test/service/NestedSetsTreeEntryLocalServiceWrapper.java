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

package com.liferay.portal.tools.service.builder.test.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NestedSetsTreeEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NestedSetsTreeEntryLocalService
 * @generated
 */
public class NestedSetsTreeEntryLocalServiceWrapper
	implements NestedSetsTreeEntryLocalService,
			   ServiceWrapper<NestedSetsTreeEntryLocalService> {

	public NestedSetsTreeEntryLocalServiceWrapper() {
		this(null);
	}

	public NestedSetsTreeEntryLocalServiceWrapper(
		NestedSetsTreeEntryLocalService nestedSetsTreeEntryLocalService) {

		_nestedSetsTreeEntryLocalService = nestedSetsTreeEntryLocalService;
	}

	@Override
	public
		com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry
			addNestedSetsTreeEntry(long groupId) {

		return _nestedSetsTreeEntryLocalService.addNestedSetsTreeEntry(groupId);
	}

	/**
	 * Adds the nested sets tree entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NestedSetsTreeEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nestedSetsTreeEntry the nested sets tree entry
	 * @return the nested sets tree entry that was added
	 */
	@Override
	public
		com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry
			addNestedSetsTreeEntry(
				com.liferay.portal.tools.service.builder.test.model.
					NestedSetsTreeEntry nestedSetsTreeEntry) {

		return _nestedSetsTreeEntryLocalService.addNestedSetsTreeEntry(
			nestedSetsTreeEntry);
	}

	/**
	 * Creates a new nested sets tree entry with the primary key. Does not add the nested sets tree entry to the database.
	 *
	 * @param nestedSetsTreeEntryId the primary key for the new nested sets tree entry
	 * @return the new nested sets tree entry
	 */
	@Override
	public
		com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry
			createNestedSetsTreeEntry(long nestedSetsTreeEntryId) {

		return _nestedSetsTreeEntryLocalService.createNestedSetsTreeEntry(
			nestedSetsTreeEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _nestedSetsTreeEntryLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the nested sets tree entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NestedSetsTreeEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nestedSetsTreeEntryId the primary key of the nested sets tree entry
	 * @return the nested sets tree entry that was removed
	 * @throws PortalException if a nested sets tree entry with the primary key could not be found
	 */
	@Override
	public
		com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry
				deleteNestedSetsTreeEntry(long nestedSetsTreeEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _nestedSetsTreeEntryLocalService.deleteNestedSetsTreeEntry(
			nestedSetsTreeEntryId);
	}

	/**
	 * Deletes the nested sets tree entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NestedSetsTreeEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nestedSetsTreeEntry the nested sets tree entry
	 * @return the nested sets tree entry that was removed
	 */
	@Override
	public
		com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry
			deleteNestedSetsTreeEntry(
				com.liferay.portal.tools.service.builder.test.model.
					NestedSetsTreeEntry nestedSetsTreeEntry) {

		return _nestedSetsTreeEntryLocalService.deleteNestedSetsTreeEntry(
			nestedSetsTreeEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _nestedSetsTreeEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _nestedSetsTreeEntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _nestedSetsTreeEntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nestedSetsTreeEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _nestedSetsTreeEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.NestedSetsTreeEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _nestedSetsTreeEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.NestedSetsTreeEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _nestedSetsTreeEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _nestedSetsTreeEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _nestedSetsTreeEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public
		com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry
			fetchNestedSetsTreeEntry(long nestedSetsTreeEntryId) {

		return _nestedSetsTreeEntryLocalService.fetchNestedSetsTreeEntry(
			nestedSetsTreeEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _nestedSetsTreeEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _nestedSetsTreeEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the nested sets tree entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.NestedSetsTreeEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of nested sets tree entries
	 * @param end the upper bound of the range of nested sets tree entries (not inclusive)
	 * @return the range of nested sets tree entries
	 */
	@Override
	public java.util.List
		<com.liferay.portal.tools.service.builder.test.model.
			NestedSetsTreeEntry> getNestedSetsTreeEntries(int start, int end) {

		return _nestedSetsTreeEntryLocalService.getNestedSetsTreeEntries(
			start, end);
	}

	/**
	 * Returns the number of nested sets tree entries.
	 *
	 * @return the number of nested sets tree entries
	 */
	@Override
	public int getNestedSetsTreeEntriesCount() {
		return _nestedSetsTreeEntryLocalService.getNestedSetsTreeEntriesCount();
	}

	/**
	 * Returns the nested sets tree entry with the primary key.
	 *
	 * @param nestedSetsTreeEntryId the primary key of the nested sets tree entry
	 * @return the nested sets tree entry
	 * @throws PortalException if a nested sets tree entry with the primary key could not be found
	 */
	@Override
	public
		com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry
				getNestedSetsTreeEntry(long nestedSetsTreeEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _nestedSetsTreeEntryLocalService.getNestedSetsTreeEntry(
			nestedSetsTreeEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _nestedSetsTreeEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _nestedSetsTreeEntryLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the nested sets tree entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NestedSetsTreeEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nestedSetsTreeEntry the nested sets tree entry
	 * @return the nested sets tree entry that was updated
	 */
	@Override
	public
		com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry
			updateNestedSetsTreeEntry(
				com.liferay.portal.tools.service.builder.test.model.
					NestedSetsTreeEntry nestedSetsTreeEntry) {

		return _nestedSetsTreeEntryLocalService.updateNestedSetsTreeEntry(
			nestedSetsTreeEntry);
	}

	@Override
	public NestedSetsTreeEntryLocalService getWrappedService() {
		return _nestedSetsTreeEntryLocalService;
	}

	@Override
	public void setWrappedService(
		NestedSetsTreeEntryLocalService nestedSetsTreeEntryLocalService) {

		_nestedSetsTreeEntryLocalService = nestedSetsTreeEntryLocalService;
	}

	private NestedSetsTreeEntryLocalService _nestedSetsTreeEntryLocalService;

}