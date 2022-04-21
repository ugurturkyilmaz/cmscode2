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

package com.liferay.mail.reader.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FolderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FolderLocalService
 * @generated
 */
public class FolderLocalServiceWrapper
	implements FolderLocalService, ServiceWrapper<FolderLocalService> {

	public FolderLocalServiceWrapper() {
		this(null);
	}

	public FolderLocalServiceWrapper(FolderLocalService folderLocalService) {
		_folderLocalService = folderLocalService;
	}

	/**
	 * Adds the folder to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param folder the folder
	 * @return the folder that was added
	 */
	@Override
	public com.liferay.mail.reader.model.Folder addFolder(
		com.liferay.mail.reader.model.Folder folder) {

		return _folderLocalService.addFolder(folder);
	}

	@Override
	public com.liferay.mail.reader.model.Folder addFolder(
			long userId, long accountId, String fullName, String displayName,
			int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.addFolder(
			userId, accountId, fullName, displayName, remoteMessageCount);
	}

	/**
	 * Creates a new folder with the primary key. Does not add the folder to the database.
	 *
	 * @param folderId the primary key for the new folder
	 * @return the new folder
	 */
	@Override
	public com.liferay.mail.reader.model.Folder createFolder(long folderId) {
		return _folderLocalService.createFolder(folderId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the folder from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param folder the folder
	 * @return the folder that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.mail.reader.model.Folder deleteFolder(
			com.liferay.mail.reader.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.deleteFolder(folder);
	}

	/**
	 * Deletes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param folderId the primary key of the folder
	 * @return the folder that was removed
	 * @throws PortalException if a folder with the primary key could not be found
	 */
	@Override
	public com.liferay.mail.reader.model.Folder deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.deleteFolder(folderId);
	}

	@Override
	public void deleteFolders(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_folderLocalService.deleteFolders(accountId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _folderLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _folderLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _folderLocalService.dynamicQuery();
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

		return _folderLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.mail.reader.model.impl.FolderModelImpl</code>.
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

		return _folderLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.mail.reader.model.impl.FolderModelImpl</code>.
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

		return _folderLocalService.dynamicQuery(
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

		return _folderLocalService.dynamicQueryCount(dynamicQuery);
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

		return _folderLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.mail.reader.model.Folder fetchFolder(long folderId) {
		return _folderLocalService.fetchFolder(folderId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _folderLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the folder with the primary key.
	 *
	 * @param folderId the primary key of the folder
	 * @return the folder
	 * @throws PortalException if a folder with the primary key could not be found
	 */
	@Override
	public com.liferay.mail.reader.model.Folder getFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.getFolder(folderId);
	}

	@Override
	public com.liferay.mail.reader.model.Folder getFolder(
			long accountId, String fullName)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.getFolder(accountId, fullName);
	}

	/**
	 * Returns a range of all the folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.mail.reader.model.impl.FolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @return the range of folders
	 */
	@Override
	public java.util.List<com.liferay.mail.reader.model.Folder> getFolders(
		int start, int end) {

		return _folderLocalService.getFolders(start, end);
	}

	@Override
	public java.util.List<com.liferay.mail.reader.model.Folder> getFolders(
		long accountId) {

		return _folderLocalService.getFolders(accountId);
	}

	/**
	 * Returns the number of folders.
	 *
	 * @return the number of folders
	 */
	@Override
	public int getFoldersCount() {
		return _folderLocalService.getFoldersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _folderLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public int getLocalPageCount(long folderId, int messagesPerPage) {
		return _folderLocalService.getLocalPageCount(folderId, messagesPerPage);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _folderLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public int getPercentDownloaded(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.getPercentDownloaded(folderId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getRemotePageCount(long folderId, int messagesPerPage)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.getRemotePageCount(
			folderId, messagesPerPage);
	}

	/**
	 * Updates the folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param folder the folder
	 * @return the folder that was updated
	 */
	@Override
	public com.liferay.mail.reader.model.Folder updateFolder(
		com.liferay.mail.reader.model.Folder folder) {

		return _folderLocalService.updateFolder(folder);
	}

	@Override
	public com.liferay.mail.reader.model.Folder updateFolder(
			long folderId, String fullName, String displayName,
			int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderLocalService.updateFolder(
			folderId, fullName, displayName, remoteMessageCount);
	}

	@Override
	public FolderLocalService getWrappedService() {
		return _folderLocalService;
	}

	@Override
	public void setWrappedService(FolderLocalService folderLocalService) {
		_folderLocalService = folderLocalService;
	}

	private FolderLocalService _folderLocalService;

}