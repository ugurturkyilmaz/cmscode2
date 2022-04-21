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

package com.liferay.mail.reader.model.impl;

import com.liferay.mail.reader.model.Folder;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Folder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FolderCacheModel implements CacheModel<Folder>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FolderCacheModel)) {
			return false;
		}

		FolderCacheModel folderCacheModel = (FolderCacheModel)object;

		if (folderId == folderCacheModel.folderId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, folderId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{folderId=");
		sb.append(folderId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", fullName=");
		sb.append(fullName);
		sb.append(", displayName=");
		sb.append(displayName);
		sb.append(", remoteMessageCount=");
		sb.append(remoteMessageCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Folder toEntityModel() {
		FolderImpl folderImpl = new FolderImpl();

		folderImpl.setFolderId(folderId);
		folderImpl.setCompanyId(companyId);
		folderImpl.setUserId(userId);

		if (userName == null) {
			folderImpl.setUserName("");
		}
		else {
			folderImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			folderImpl.setCreateDate(null);
		}
		else {
			folderImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			folderImpl.setModifiedDate(null);
		}
		else {
			folderImpl.setModifiedDate(new Date(modifiedDate));
		}

		folderImpl.setAccountId(accountId);

		if (fullName == null) {
			folderImpl.setFullName("");
		}
		else {
			folderImpl.setFullName(fullName);
		}

		if (displayName == null) {
			folderImpl.setDisplayName("");
		}
		else {
			folderImpl.setDisplayName(displayName);
		}

		folderImpl.setRemoteMessageCount(remoteMessageCount);

		folderImpl.resetOriginalValues();

		return folderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		folderId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountId = objectInput.readLong();
		fullName = objectInput.readUTF();
		displayName = objectInput.readUTF();

		remoteMessageCount = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(folderId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountId);

		if (fullName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullName);
		}

		if (displayName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(displayName);
		}

		objectOutput.writeInt(remoteMessageCount);
	}

	public long folderId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountId;
	public String fullName;
	public String displayName;
	public int remoteMessageCount;

}