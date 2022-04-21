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

package com.liferay.journal.service;

import com.liferay.journal.model.JournalFolder;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JournalFolderService}.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFolderService
 * @generated
 */
public class JournalFolderServiceWrapper
	implements JournalFolderService, ServiceWrapper<JournalFolderService> {

	public JournalFolderServiceWrapper() {
		this(null);
	}

	public JournalFolderServiceWrapper(
		JournalFolderService journalFolderService) {

		_journalFolderService = journalFolderService;
	}

	@Override
	public JournalFolder addFolder(
			long groupId, long parentFolderId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.addFolder(
			groupId, parentFolderId, name, description, serviceContext);
	}

	@Override
	public void deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_journalFolderService.deleteFolder(folderId);
	}

	@Override
	public void deleteFolder(long folderId, boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {

		_journalFolderService.deleteFolder(folderId, includeTrashedEntries);
	}

	@Override
	public JournalFolder fetchFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.fetchFolder(folderId);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
			getDDMStructures(
				long[] groupIds, long folderId, int restrictionType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.getDDMStructures(
			groupIds, folderId, restrictionType);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
			getDDMStructures(
				long[] groupIds, long folderId, int restrictionType,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.dynamic.data.mapping.model.DDMStructure>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.getDDMStructures(
			groupIds, folderId, restrictionType, orderByComparator);
	}

	@Override
	public JournalFolder getFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.getFolder(folderId);
	}

	@Override
	public java.util.List<Long> getFolderIds(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.getFolderIds(groupId, folderId);
	}

	@Override
	public java.util.List<JournalFolder> getFolders(long groupId) {
		return _journalFolderService.getFolders(groupId);
	}

	@Override
	public java.util.List<JournalFolder> getFolders(
		long groupId, long parentFolderId) {

		return _journalFolderService.getFolders(groupId, parentFolderId);
	}

	@Override
	public java.util.List<JournalFolder> getFolders(
		long groupId, long parentFolderId, int status) {

		return _journalFolderService.getFolders(
			groupId, parentFolderId, status);
	}

	@Override
	public java.util.List<JournalFolder> getFolders(
		long groupId, long parentFolderId, int start, int end) {

		return _journalFolderService.getFolders(
			groupId, parentFolderId, start, end);
	}

	@Override
	public java.util.List<JournalFolder> getFolders(
		long groupId, long parentFolderId, int status, int start, int end) {

		return _journalFolderService.getFolders(
			groupId, parentFolderId, status, start, end);
	}

	@Override
	public java.util.List<Object> getFoldersAndArticles(
		long groupId, long folderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<?> orderByComparator) {

		return _journalFolderService.getFoldersAndArticles(
			groupId, folderId, status, start, end, orderByComparator);
	}

	@Override
	public java.util.List<Object> getFoldersAndArticles(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<?> orderByComparator) {

		return _journalFolderService.getFoldersAndArticles(
			groupId, folderId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<Object> getFoldersAndArticles(
		long groupId, long userId, long folderId, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<?> orderByComparator) {

		return _journalFolderService.getFoldersAndArticles(
			groupId, userId, folderId, status, start, end, orderByComparator);
	}

	@Override
	public java.util.List<Object> getFoldersAndArticles(
		long groupId, long userId, long folderId, int status,
		java.util.Locale locale, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<?> orderByComparator) {

		return _journalFolderService.getFoldersAndArticles(
			groupId, userId, folderId, status, locale, start, end,
			orderByComparator);
	}

	@Override
	public int getFoldersAndArticlesCount(
		long groupId, java.util.List<Long> folderIds, int status) {

		return _journalFolderService.getFoldersAndArticlesCount(
			groupId, folderIds, status);
	}

	@Override
	public int getFoldersAndArticlesCount(long groupId, long folderId) {
		return _journalFolderService.getFoldersAndArticlesCount(
			groupId, folderId);
	}

	@Override
	public int getFoldersAndArticlesCount(
		long groupId, long folderId, int status) {

		return _journalFolderService.getFoldersAndArticlesCount(
			groupId, folderId, status);
	}

	@Override
	public int getFoldersAndArticlesCount(
		long groupId, long userId, long folderId, int status) {

		return _journalFolderService.getFoldersAndArticlesCount(
			groupId, userId, folderId, status);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId) {
		return _journalFolderService.getFoldersCount(groupId, parentFolderId);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId, int status) {
		return _journalFolderService.getFoldersCount(
			groupId, parentFolderId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _journalFolderService.getOSGiServiceIdentifier();
	}

	@Override
	public void getSubfolderIds(
		java.util.List<Long> folderIds, long groupId, long folderId,
		boolean recurse) {

		_journalFolderService.getSubfolderIds(
			folderIds, groupId, folderId, recurse);
	}

	@Override
	public java.util.List<Long> getSubfolderIds(
		long groupId, long folderId, boolean recurse) {

		return _journalFolderService.getSubfolderIds(
			groupId, folderId, recurse);
	}

	@Override
	public JournalFolder moveFolder(
			long folderId, long parentFolderId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.moveFolder(
			folderId, parentFolderId, serviceContext);
	}

	@Override
	public JournalFolder moveFolderFromTrash(
			long folderId, long parentFolderId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.moveFolderFromTrash(
			folderId, parentFolderId, serviceContext);
	}

	@Override
	public JournalFolder moveFolderToTrash(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.moveFolderToTrash(folderId);
	}

	@Override
	public void restoreFolderFromTrash(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_journalFolderService.restoreFolderFromTrash(folderId);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
			searchDDMStructures(
				long companyId, long[] groupIds, long folderId,
				int restrictionType, String keywords, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.dynamic.data.mapping.model.DDMStructure>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.searchDDMStructures(
			companyId, groupIds, folderId, restrictionType, keywords, start,
			end, orderByComparator);
	}

	@Override
	public void subscribe(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_journalFolderService.subscribe(groupId, folderId);
	}

	@Override
	public void unsubscribe(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_journalFolderService.unsubscribe(groupId, folderId);
	}

	@Override
	public JournalFolder updateFolder(
			long groupId, long folderId, long parentFolderId, String name,
			String description, boolean mergeWithParentFolder,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.updateFolder(
			groupId, folderId, parentFolderId, name, description,
			mergeWithParentFolder, serviceContext);
	}

	@Override
	public JournalFolder updateFolder(
			long groupId, long folderId, long parentFolderId, String name,
			String description, long[] ddmStructureIds, int restrictionType,
			boolean mergeWithParentFolder,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _journalFolderService.updateFolder(
			groupId, folderId, parentFolderId, name, description,
			ddmStructureIds, restrictionType, mergeWithParentFolder,
			serviceContext);
	}

	@Override
	public JournalFolderService getWrappedService() {
		return _journalFolderService;
	}

	@Override
	public void setWrappedService(JournalFolderService journalFolderService) {
		_journalFolderService = journalFolderService;
	}

	private JournalFolderService _journalFolderService;

}