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

package com.liferay.mail.reader.service.impl;

import com.liferay.mail.reader.model.Folder;
import com.liferay.mail.reader.service.MessageLocalService;
import com.liferay.mail.reader.service.base.FolderLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.systemevent.SystemEvent;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Scott Lee
 */
@Component(
	property = "model.class.name=com.liferay.mail.reader.model.Folder",
	service = AopService.class
)
public class FolderLocalServiceImpl extends FolderLocalServiceBaseImpl {

	@Override
	public Folder addFolder(
			long userId, long accountId, String fullName, String displayName,
			int remoteMessageCount)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date date = new Date();

		long folderId = counterLocalService.increment();

		Folder folder = folderPersistence.create(folderId);

		folder.setCompanyId(user.getCompanyId());
		folder.setUserId(user.getUserId());
		folder.setUserName(user.getFullName());
		folder.setCreateDate(date);
		folder.setModifiedDate(date);
		folder.setAccountId(accountId);
		folder.setFullName(fullName);
		folder.setDisplayName(displayName);
		folder.setRemoteMessageCount(remoteMessageCount);

		return folderPersistence.update(folder);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Folder deleteFolder(Folder folder) throws PortalException {

		// Folder

		folderPersistence.remove(folder);

		// Messages

		_messageLocalService.deleteMessages(folder.getFolderId());

		// Indexer

		Indexer<Folder> indexer = _indexerRegistry.getIndexer(Folder.class);

		indexer.delete(folder);

		return folder;
	}

	@Override
	public Folder deleteFolder(long folderId) throws PortalException {
		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		return deleteFolder(folder);
	}

	@Override
	public void deleteFolders(long accountId) throws PortalException {
		List<Folder> folders = folderPersistence.findByAccountId(accountId);

		for (Folder folder : folders) {
			deleteFolder(folder);
		}
	}

	@Override
	public Folder getFolder(long accountId, String fullName)
		throws PortalException {

		return folderPersistence.findByA_F(accountId, fullName);
	}

	@Override
	public List<Folder> getFolders(long accountId) {
		return folderPersistence.findByAccountId(accountId);
	}

	@Override
	public int getLocalPageCount(long folderId, int messagesPerPage) {
		int localMessageCount = messagePersistence.countByFolderId(folderId);

		return (int)Math.ceil(localMessageCount / (double)messagesPerPage);
	}

	@Override
	public int getPercentDownloaded(long folderId) throws PortalException {
		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		int remoteMessageCount = folder.getRemoteMessageCount();

		if (remoteMessageCount == 0) {
			return 100;
		}

		int localMessageCount = messagePersistence.countByFolderId(folderId);

		return (int)((localMessageCount / (double)remoteMessageCount) * 100);
	}

	@Override
	public int getRemotePageCount(long folderId, int messagesPerPage)
		throws PortalException {

		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		int remoteMessageCount = folder.getRemoteMessageCount();

		return (int)Math.ceil(remoteMessageCount / (double)messagesPerPage);
	}

	@Override
	public Folder updateFolder(
			long folderId, String fullName, String displayName,
			int remoteMessageCount)
		throws PortalException {

		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		folder.setModifiedDate(new Date());
		folder.setFullName(fullName);
		folder.setDisplayName(displayName);
		folder.setRemoteMessageCount(remoteMessageCount);

		return folderPersistence.update(folder);
	}

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private MessageLocalService _messageLocalService;

}