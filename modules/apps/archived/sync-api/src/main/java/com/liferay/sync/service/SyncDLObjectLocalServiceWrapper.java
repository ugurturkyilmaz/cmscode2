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

package com.liferay.sync.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncDLObjectLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectLocalService
 * @generated
 */
public class SyncDLObjectLocalServiceWrapper
	implements ServiceWrapper<SyncDLObjectLocalService>,
			   SyncDLObjectLocalService {

	public SyncDLObjectLocalServiceWrapper() {
		this(null);
	}

	public SyncDLObjectLocalServiceWrapper(
		SyncDLObjectLocalService syncDLObjectLocalService) {

		_syncDLObjectLocalService = syncDLObjectLocalService;
	}

	@Override
	public com.liferay.sync.model.SyncDLObject addSyncDLObject(
			long companyId, long userId, String userName, long modifiedTime,
			long repositoryId, long parentFolderId, String treePath,
			String name, String extension, String mimeType, String description,
			String changeLog, String extraSettings, String version,
			long versionId, long size, String checksum, String event,
			String lanTokenKey, java.util.Date lockExpirationDate,
			long lockUserId, String lockUserName, String type, long typePK,
			String typeUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _syncDLObjectLocalService.addSyncDLObject(
			companyId, userId, userName, modifiedTime, repositoryId,
			parentFolderId, treePath, name, extension, mimeType, description,
			changeLog, extraSettings, version, versionId, size, checksum, event,
			lanTokenKey, lockExpirationDate, lockUserId, lockUserName, type,
			typePK, typeUuid);
	}

	/**
	 * Adds the sync dl object to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SyncDLObjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param syncDLObject the sync dl object
	 * @return the sync dl object that was added
	 */
	@Override
	public com.liferay.sync.model.SyncDLObject addSyncDLObject(
		com.liferay.sync.model.SyncDLObject syncDLObject) {

		return _syncDLObjectLocalService.addSyncDLObject(syncDLObject);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _syncDLObjectLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new sync dl object with the primary key. Does not add the sync dl object to the database.
	 *
	 * @param syncDLObjectId the primary key for the new sync dl object
	 * @return the new sync dl object
	 */
	@Override
	public com.liferay.sync.model.SyncDLObject createSyncDLObject(
		long syncDLObjectId) {

		return _syncDLObjectLocalService.createSyncDLObject(syncDLObjectId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _syncDLObjectLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the sync dl object with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SyncDLObjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param syncDLObjectId the primary key of the sync dl object
	 * @return the sync dl object that was removed
	 * @throws PortalException if a sync dl object with the primary key could not be found
	 */
	@Override
	public com.liferay.sync.model.SyncDLObject deleteSyncDLObject(
			long syncDLObjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _syncDLObjectLocalService.deleteSyncDLObject(syncDLObjectId);
	}

	/**
	 * Deletes the sync dl object from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SyncDLObjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param syncDLObject the sync dl object
	 * @return the sync dl object that was removed
	 */
	@Override
	public com.liferay.sync.model.SyncDLObject deleteSyncDLObject(
		com.liferay.sync.model.SyncDLObject syncDLObject) {

		return _syncDLObjectLocalService.deleteSyncDLObject(syncDLObject);
	}

	@Override
	public void deleteSyncDLObjects(String version, String type) {
		_syncDLObjectLocalService.deleteSyncDLObjects(version, type);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _syncDLObjectLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _syncDLObjectLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _syncDLObjectLocalService.dynamicQuery();
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

		return _syncDLObjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDLObjectModelImpl</code>.
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

		return _syncDLObjectLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDLObjectModelImpl</code>.
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

		return _syncDLObjectLocalService.dynamicQuery(
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

		return _syncDLObjectLocalService.dynamicQueryCount(dynamicQuery);
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

		return _syncDLObjectLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject fetchSyncDLObject(
		long syncDLObjectId) {

		return _syncDLObjectLocalService.fetchSyncDLObject(syncDLObjectId);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject fetchSyncDLObject(
		String type, long typePK) {

		return _syncDLObjectLocalService.fetchSyncDLObject(type, typePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _syncDLObjectLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _syncDLObjectLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public long getLatestModifiedTime() {
		return _syncDLObjectLocalService.getLatestModifiedTime();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _syncDLObjectLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _syncDLObjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the sync dl object with the primary key.
	 *
	 * @param syncDLObjectId the primary key of the sync dl object
	 * @return the sync dl object
	 * @throws PortalException if a sync dl object with the primary key could not be found
	 */
	@Override
	public com.liferay.sync.model.SyncDLObject getSyncDLObject(
			long syncDLObjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _syncDLObjectLocalService.getSyncDLObject(syncDLObjectId);
	}

	/**
	 * Returns a range of all the sync dl objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDLObjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync dl objects
	 * @param end the upper bound of the range of sync dl objects (not inclusive)
	 * @return the range of sync dl objects
	 */
	@Override
	public java.util.List<com.liferay.sync.model.SyncDLObject> getSyncDLObjects(
		int start, int end) {

		return _syncDLObjectLocalService.getSyncDLObjects(start, end);
	}

	@Override
	public java.util.List<com.liferay.sync.model.SyncDLObject> getSyncDLObjects(
		long repositoryId, long parentFolderId) {

		return _syncDLObjectLocalService.getSyncDLObjects(
			repositoryId, parentFolderId);
	}

	/**
	 * Returns the number of sync dl objects.
	 *
	 * @return the number of sync dl objects
	 */
	@Override
	public int getSyncDLObjectsCount() {
		return _syncDLObjectLocalService.getSyncDLObjectsCount();
	}

	@Override
	public void moveSyncDLObjects(
			com.liferay.sync.model.SyncDLObject parentSyncDLObject)
		throws com.liferay.portal.kernel.exception.PortalException {

		_syncDLObjectLocalService.moveSyncDLObjects(parentSyncDLObject);
	}

	@Override
	public void restoreSyncDLObjects(
			com.liferay.sync.model.SyncDLObject parentSyncDLObject)
		throws com.liferay.portal.kernel.exception.PortalException {

		_syncDLObjectLocalService.restoreSyncDLObjects(parentSyncDLObject);
	}

	@Override
	public void trashSyncDLObjects(
			com.liferay.sync.model.SyncDLObject parentSyncDLObject)
		throws com.liferay.portal.kernel.exception.PortalException {

		_syncDLObjectLocalService.trashSyncDLObjects(parentSyncDLObject);
	}

	/**
	 * Updates the sync dl object in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SyncDLObjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param syncDLObject the sync dl object
	 * @return the sync dl object that was updated
	 */
	@Override
	public com.liferay.sync.model.SyncDLObject updateSyncDLObject(
		com.liferay.sync.model.SyncDLObject syncDLObject) {

		return _syncDLObjectLocalService.updateSyncDLObject(syncDLObject);
	}

	@Override
	public SyncDLObjectLocalService getWrappedService() {
		return _syncDLObjectLocalService;
	}

	@Override
	public void setWrappedService(
		SyncDLObjectLocalService syncDLObjectLocalService) {

		_syncDLObjectLocalService = syncDLObjectLocalService;
	}

	private SyncDLObjectLocalService _syncDLObjectLocalService;

}