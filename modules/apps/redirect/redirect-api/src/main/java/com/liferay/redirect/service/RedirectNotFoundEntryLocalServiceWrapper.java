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

package com.liferay.redirect.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RedirectNotFoundEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RedirectNotFoundEntryLocalService
 * @generated
 */
public class RedirectNotFoundEntryLocalServiceWrapper
	implements RedirectNotFoundEntryLocalService,
			   ServiceWrapper<RedirectNotFoundEntryLocalService> {

	public RedirectNotFoundEntryLocalServiceWrapper() {
		this(null);
	}

	public RedirectNotFoundEntryLocalServiceWrapper(
		RedirectNotFoundEntryLocalService redirectNotFoundEntryLocalService) {

		_redirectNotFoundEntryLocalService = redirectNotFoundEntryLocalService;
	}

	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
		addOrUpdateRedirectNotFoundEntry(
			com.liferay.portal.kernel.model.Group group, String url) {

		return _redirectNotFoundEntryLocalService.
			addOrUpdateRedirectNotFoundEntry(group, url);
	}

	/**
	 * Adds the redirect not found entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RedirectNotFoundEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param redirectNotFoundEntry the redirect not found entry
	 * @return the redirect not found entry that was added
	 */
	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
		addRedirectNotFoundEntry(
			com.liferay.redirect.model.RedirectNotFoundEntry
				redirectNotFoundEntry) {

		return _redirectNotFoundEntryLocalService.addRedirectNotFoundEntry(
			redirectNotFoundEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectNotFoundEntryLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new redirect not found entry with the primary key. Does not add the redirect not found entry to the database.
	 *
	 * @param redirectNotFoundEntryId the primary key for the new redirect not found entry
	 * @return the new redirect not found entry
	 */
	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
		createRedirectNotFoundEntry(long redirectNotFoundEntryId) {

		return _redirectNotFoundEntryLocalService.createRedirectNotFoundEntry(
			redirectNotFoundEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectNotFoundEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the redirect not found entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RedirectNotFoundEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param redirectNotFoundEntryId the primary key of the redirect not found entry
	 * @return the redirect not found entry that was removed
	 * @throws PortalException if a redirect not found entry with the primary key could not be found
	 */
	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
			deleteRedirectNotFoundEntry(long redirectNotFoundEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectNotFoundEntryLocalService.deleteRedirectNotFoundEntry(
			redirectNotFoundEntryId);
	}

	/**
	 * Deletes the redirect not found entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RedirectNotFoundEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param redirectNotFoundEntry the redirect not found entry
	 * @return the redirect not found entry that was removed
	 */
	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
		deleteRedirectNotFoundEntry(
			com.liferay.redirect.model.RedirectNotFoundEntry
				redirectNotFoundEntry) {

		return _redirectNotFoundEntryLocalService.deleteRedirectNotFoundEntry(
			redirectNotFoundEntry);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _redirectNotFoundEntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _redirectNotFoundEntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _redirectNotFoundEntryLocalService.dynamicQuery();
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

		return _redirectNotFoundEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.redirect.model.impl.RedirectNotFoundEntryModelImpl</code>.
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

		return _redirectNotFoundEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.redirect.model.impl.RedirectNotFoundEntryModelImpl</code>.
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

		return _redirectNotFoundEntryLocalService.dynamicQuery(
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

		return _redirectNotFoundEntryLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _redirectNotFoundEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
		fetchRedirectNotFoundEntry(long redirectNotFoundEntryId) {

		return _redirectNotFoundEntryLocalService.fetchRedirectNotFoundEntry(
			redirectNotFoundEntryId);
	}

	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
		fetchRedirectNotFoundEntry(long groupId, String url) {

		return _redirectNotFoundEntryLocalService.fetchRedirectNotFoundEntry(
			groupId, url);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _redirectNotFoundEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _redirectNotFoundEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _redirectNotFoundEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectNotFoundEntryLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the redirect not found entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.redirect.model.impl.RedirectNotFoundEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of redirect not found entries
	 * @param end the upper bound of the range of redirect not found entries (not inclusive)
	 * @return the range of redirect not found entries
	 */
	@Override
	public java.util.List<com.liferay.redirect.model.RedirectNotFoundEntry>
		getRedirectNotFoundEntries(int start, int end) {

		return _redirectNotFoundEntryLocalService.getRedirectNotFoundEntries(
			start, end);
	}

	@Override
	public java.util.List<com.liferay.redirect.model.RedirectNotFoundEntry>
		getRedirectNotFoundEntries(
			long groupId, Boolean ignored, java.util.Date minModifiedDate,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.redirect.model.RedirectNotFoundEntry>
					orderByComparator) {

		return _redirectNotFoundEntryLocalService.getRedirectNotFoundEntries(
			groupId, ignored, minModifiedDate, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.redirect.model.RedirectNotFoundEntry>
		getRedirectNotFoundEntries(
			long groupId, java.util.Date minModifiedDate, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.redirect.model.RedirectNotFoundEntry>
					orderByComparator) {

		return _redirectNotFoundEntryLocalService.getRedirectNotFoundEntries(
			groupId, minModifiedDate, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.redirect.model.RedirectNotFoundEntry>
		getRedirectNotFoundEntries(
			long groupId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.redirect.model.RedirectNotFoundEntry>
					orderByComparator) {

		return _redirectNotFoundEntryLocalService.getRedirectNotFoundEntries(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of redirect not found entries.
	 *
	 * @return the number of redirect not found entries
	 */
	@Override
	public int getRedirectNotFoundEntriesCount() {
		return _redirectNotFoundEntryLocalService.
			getRedirectNotFoundEntriesCount();
	}

	@Override
	public int getRedirectNotFoundEntriesCount(long groupId) {
		return _redirectNotFoundEntryLocalService.
			getRedirectNotFoundEntriesCount(groupId);
	}

	@Override
	public int getRedirectNotFoundEntriesCount(
		long groupId, Boolean ignored, java.util.Date minModifiedDate) {

		return _redirectNotFoundEntryLocalService.
			getRedirectNotFoundEntriesCount(groupId, ignored, minModifiedDate);
	}

	@Override
	public int getRedirectNotFoundEntriesCount(
		long groupId, java.util.Date minModifiedDate) {

		return _redirectNotFoundEntryLocalService.
			getRedirectNotFoundEntriesCount(groupId, minModifiedDate);
	}

	/**
	 * Returns the redirect not found entry with the primary key.
	 *
	 * @param redirectNotFoundEntryId the primary key of the redirect not found entry
	 * @return the redirect not found entry
	 * @throws PortalException if a redirect not found entry with the primary key could not be found
	 */
	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
			getRedirectNotFoundEntry(long redirectNotFoundEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectNotFoundEntryLocalService.getRedirectNotFoundEntry(
			redirectNotFoundEntryId);
	}

	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
			updateRedirectNotFoundEntry(
				long redirectNotFoundEntryId, boolean ignored)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _redirectNotFoundEntryLocalService.updateRedirectNotFoundEntry(
			redirectNotFoundEntryId, ignored);
	}

	/**
	 * Updates the redirect not found entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RedirectNotFoundEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param redirectNotFoundEntry the redirect not found entry
	 * @return the redirect not found entry that was updated
	 */
	@Override
	public com.liferay.redirect.model.RedirectNotFoundEntry
		updateRedirectNotFoundEntry(
			com.liferay.redirect.model.RedirectNotFoundEntry
				redirectNotFoundEntry) {

		return _redirectNotFoundEntryLocalService.updateRedirectNotFoundEntry(
			redirectNotFoundEntry);
	}

	@Override
	public RedirectNotFoundEntryLocalService getWrappedService() {
		return _redirectNotFoundEntryLocalService;
	}

	@Override
	public void setWrappedService(
		RedirectNotFoundEntryLocalService redirectNotFoundEntryLocalService) {

		_redirectNotFoundEntryLocalService = redirectNotFoundEntryLocalService;
	}

	private RedirectNotFoundEntryLocalService
		_redirectNotFoundEntryLocalService;

}