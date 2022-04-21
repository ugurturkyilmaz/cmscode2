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

package com.liferay.bookmarks.service;

import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for BookmarksFolder. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksFolderServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface BookmarksFolderService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.bookmarks.service.impl.BookmarksFolderServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the bookmarks folder remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link BookmarksFolderServiceUtil} if injection and service tracking are not available.
	 */
	public BookmarksFolder addFolder(
			long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteFolder(long folderId) throws PortalException;

	public void deleteFolder(long folderId, boolean includeTrashedEntries)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BookmarksFolder getFolder(long folderId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getFolderIds(long groupId, long folderId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BookmarksFolder> getFolders(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BookmarksFolder> getFolders(long groupId, long parentFolderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BookmarksFolder> getFolders(
		long groupId, long parentFolderId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BookmarksFolder> getFolders(
		long groupId, long parentFolderId, int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Object> getFoldersAndEntries(long groupId, long folderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Object> getFoldersAndEntries(
		long groupId, long folderId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Object> getFoldersAndEntries(
		long groupId, long folderId, int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersAndEntriesCount(long groupId, long folderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersAndEntriesCount(
		long groupId, long folderId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersCount(long groupId, long parentFolderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersCount(long groupId, long parentFolderId, int status);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getSubfolderIds(
		List<Long> folderIds, long groupId, long folderId, boolean recurse);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getSubfolderIds(
		long groupId, long folderId, boolean recurse);

	public void mergeFolders(long folderId, long parentFolderId)
		throws PortalException;

	public BookmarksFolder moveFolder(long folderId, long parentFolderId)
		throws PortalException;

	public BookmarksFolder moveFolderFromTrash(
			long folderId, long parentFolderId)
		throws PortalException;

	public BookmarksFolder moveFolderToTrash(long folderId)
		throws PortalException;

	public void restoreFolderFromTrash(long folderId) throws PortalException;

	public void subscribeFolder(long groupId, long folderId)
		throws PortalException;

	public void unsubscribeFolder(long groupId, long folderId)
		throws PortalException;

	public BookmarksFolder updateFolder(
			long folderId, long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException;

}