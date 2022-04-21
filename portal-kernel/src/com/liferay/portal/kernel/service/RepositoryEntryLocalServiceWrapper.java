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

package com.liferay.portal.kernel.service;

import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.RepositoryEntry;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;

/**
 * Provides a wrapper for {@link RepositoryEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryEntryLocalService
 * @generated
 */
public class RepositoryEntryLocalServiceWrapper
	implements RepositoryEntryLocalService,
			   ServiceWrapper<RepositoryEntryLocalService> {

	public RepositoryEntryLocalServiceWrapper() {
		this(null);
	}

	public RepositoryEntryLocalServiceWrapper(
		RepositoryEntryLocalService repositoryEntryLocalService) {

		_repositoryEntryLocalService = repositoryEntryLocalService;
	}

	@Override
	public RepositoryEntry addRepositoryEntry(
			long userId, long groupId, long repositoryId, String mappedId,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.addRepositoryEntry(
			userId, groupId, repositoryId, mappedId, serviceContext);
	}

	/**
	 * Adds the repository entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RepositoryEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param repositoryEntry the repository entry
	 * @return the repository entry that was added
	 */
	@Override
	public RepositoryEntry addRepositoryEntry(RepositoryEntry repositoryEntry) {
		return _repositoryEntryLocalService.addRepositoryEntry(repositoryEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new repository entry with the primary key. Does not add the repository entry to the database.
	 *
	 * @param repositoryEntryId the primary key for the new repository entry
	 * @return the new repository entry
	 */
	@Override
	public RepositoryEntry createRepositoryEntry(long repositoryEntryId) {
		return _repositoryEntryLocalService.createRepositoryEntry(
			repositoryEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public void deleteRepositoryEntries(
			long repositoryId, Iterable<String> mappedIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_repositoryEntryLocalService.deleteRepositoryEntries(
			repositoryId, mappedIds);
	}

	/**
	 * Deletes the repository entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RepositoryEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param repositoryEntryId the primary key of the repository entry
	 * @return the repository entry that was removed
	 * @throws PortalException if a repository entry with the primary key could not be found
	 */
	@Override
	public RepositoryEntry deleteRepositoryEntry(long repositoryEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.deleteRepositoryEntry(
			repositoryEntryId);
	}

	@Override
	public void deleteRepositoryEntry(long repositoryId, String mappedId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_repositoryEntryLocalService.deleteRepositoryEntry(
			repositoryId, mappedId);
	}

	/**
	 * Deletes the repository entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RepositoryEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param repositoryEntry the repository entry
	 * @return the repository entry that was removed
	 */
	@Override
	public RepositoryEntry deleteRepositoryEntry(
		RepositoryEntry repositoryEntry) {

		return _repositoryEntryLocalService.deleteRepositoryEntry(
			repositoryEntry);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _repositoryEntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _repositoryEntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _repositoryEntryLocalService.dynamicQuery();
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

		return _repositoryEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RepositoryEntryModelImpl</code>.
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

		return _repositoryEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RepositoryEntryModelImpl</code>.
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

		return _repositoryEntryLocalService.dynamicQuery(
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

		return _repositoryEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _repositoryEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public RepositoryEntry fetchRepositoryEntry(long repositoryEntryId) {
		return _repositoryEntryLocalService.fetchRepositoryEntry(
			repositoryEntryId);
	}

	/**
	 * Returns the repository entry matching the UUID and group.
	 *
	 * @param uuid the repository entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching repository entry, or <code>null</code> if a matching repository entry could not be found
	 */
	@Override
	public RepositoryEntry fetchRepositoryEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return _repositoryEntryLocalService.
			fetchRepositoryEntryByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _repositoryEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _repositoryEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _repositoryEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _repositoryEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the repository entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RepositoryEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of repository entries
	 * @param end the upper bound of the range of repository entries (not inclusive)
	 * @return the range of repository entries
	 */
	@Override
	public java.util.List<RepositoryEntry> getRepositoryEntries(
		int start, int end) {

		return _repositoryEntryLocalService.getRepositoryEntries(start, end);
	}

	@Override
	public java.util.List<RepositoryEntry> getRepositoryEntries(
		long repositoryId) {

		return _repositoryEntryLocalService.getRepositoryEntries(repositoryId);
	}

	/**
	 * Returns all the repository entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the repository entries
	 * @param companyId the primary key of the company
	 * @return the matching repository entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<RepositoryEntry>
		getRepositoryEntriesByUuidAndCompanyId(String uuid, long companyId) {

		return _repositoryEntryLocalService.
			getRepositoryEntriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of repository entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the repository entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of repository entries
	 * @param end the upper bound of the range of repository entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching repository entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<RepositoryEntry>
		getRepositoryEntriesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<RepositoryEntry>
				orderByComparator) {

		return _repositoryEntryLocalService.
			getRepositoryEntriesByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of repository entries.
	 *
	 * @return the number of repository entries
	 */
	@Override
	public int getRepositoryEntriesCount() {
		return _repositoryEntryLocalService.getRepositoryEntriesCount();
	}

	/**
	 * Returns the repository entry with the primary key.
	 *
	 * @param repositoryEntryId the primary key of the repository entry
	 * @return the repository entry
	 * @throws PortalException if a repository entry with the primary key could not be found
	 */
	@Override
	public RepositoryEntry getRepositoryEntry(long repositoryEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.getRepositoryEntry(
			repositoryEntryId);
	}

	@Override
	public RepositoryEntry getRepositoryEntry(
			long userId, long groupId, long repositoryId, String objectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.getRepositoryEntry(
			userId, groupId, repositoryId, objectId);
	}

	@Override
	public RepositoryEntry getRepositoryEntry(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.getRepositoryEntry(uuid, groupId);
	}

	/**
	 * Returns the repository entry matching the UUID and group.
	 *
	 * @param uuid the repository entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching repository entry
	 * @throws PortalException if a matching repository entry could not be found
	 */
	@Override
	public RepositoryEntry getRepositoryEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.getRepositoryEntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public RepositoryEntry updateRepositoryEntry(
			long repositoryEntryId, String mappedId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryEntryLocalService.updateRepositoryEntry(
			repositoryEntryId, mappedId);
	}

	/**
	 * Updates the repository entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RepositoryEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param repositoryEntry the repository entry
	 * @return the repository entry that was updated
	 */
	@Override
	public RepositoryEntry updateRepositoryEntry(
		RepositoryEntry repositoryEntry) {

		return _repositoryEntryLocalService.updateRepositoryEntry(
			repositoryEntry);
	}

	@Override
	public CTPersistence<RepositoryEntry> getCTPersistence() {
		return _repositoryEntryLocalService.getCTPersistence();
	}

	@Override
	public Class<RepositoryEntry> getModelClass() {
		return _repositoryEntryLocalService.getModelClass();
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<RepositoryEntry>, R, E>
				updateUnsafeFunction)
		throws E {

		return _repositoryEntryLocalService.updateWithUnsafeFunction(
			updateUnsafeFunction);
	}

	@Override
	public RepositoryEntryLocalService getWrappedService() {
		return _repositoryEntryLocalService;
	}

	@Override
	public void setWrappedService(
		RepositoryEntryLocalService repositoryEntryLocalService) {

		_repositoryEntryLocalService = repositoryEntryLocalService;
	}

	private RepositoryEntryLocalService _repositoryEntryLocalService;

}