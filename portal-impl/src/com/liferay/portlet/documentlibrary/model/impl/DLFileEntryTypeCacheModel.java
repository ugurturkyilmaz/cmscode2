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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DLFileEntryType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLFileEntryTypeCacheModel
	implements CacheModel<DLFileEntryType>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DLFileEntryTypeCacheModel)) {
			return false;
		}

		DLFileEntryTypeCacheModel dlFileEntryTypeCacheModel =
			(DLFileEntryTypeCacheModel)object;

		if ((fileEntryTypeId == dlFileEntryTypeCacheModel.fileEntryTypeId) &&
			(mvccVersion == dlFileEntryTypeCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, fileEntryTypeId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", ctCollectionId=");
		sb.append(ctCollectionId);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", fileEntryTypeId=");
		sb.append(fileEntryTypeId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", dataDefinitionId=");
		sb.append(dataDefinitionId);
		sb.append(", fileEntryTypeKey=");
		sb.append(fileEntryTypeKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", scope=");
		sb.append(scope);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DLFileEntryType toEntityModel() {
		DLFileEntryTypeImpl dlFileEntryTypeImpl = new DLFileEntryTypeImpl();

		dlFileEntryTypeImpl.setMvccVersion(mvccVersion);
		dlFileEntryTypeImpl.setCtCollectionId(ctCollectionId);

		if (uuid == null) {
			dlFileEntryTypeImpl.setUuid("");
		}
		else {
			dlFileEntryTypeImpl.setUuid(uuid);
		}

		dlFileEntryTypeImpl.setFileEntryTypeId(fileEntryTypeId);
		dlFileEntryTypeImpl.setGroupId(groupId);
		dlFileEntryTypeImpl.setCompanyId(companyId);
		dlFileEntryTypeImpl.setUserId(userId);

		if (userName == null) {
			dlFileEntryTypeImpl.setUserName("");
		}
		else {
			dlFileEntryTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dlFileEntryTypeImpl.setCreateDate(null);
		}
		else {
			dlFileEntryTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dlFileEntryTypeImpl.setModifiedDate(null);
		}
		else {
			dlFileEntryTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		dlFileEntryTypeImpl.setDataDefinitionId(dataDefinitionId);

		if (fileEntryTypeKey == null) {
			dlFileEntryTypeImpl.setFileEntryTypeKey("");
		}
		else {
			dlFileEntryTypeImpl.setFileEntryTypeKey(fileEntryTypeKey);
		}

		if (name == null) {
			dlFileEntryTypeImpl.setName("");
		}
		else {
			dlFileEntryTypeImpl.setName(name);
		}

		if (description == null) {
			dlFileEntryTypeImpl.setDescription("");
		}
		else {
			dlFileEntryTypeImpl.setDescription(description);
		}

		dlFileEntryTypeImpl.setScope(scope);

		if (lastPublishDate == Long.MIN_VALUE) {
			dlFileEntryTypeImpl.setLastPublishDate(null);
		}
		else {
			dlFileEntryTypeImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		dlFileEntryTypeImpl.resetOriginalValues();

		return dlFileEntryTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		ctCollectionId = objectInput.readLong();
		uuid = objectInput.readUTF();

		fileEntryTypeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dataDefinitionId = objectInput.readLong();
		fileEntryTypeKey = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		scope = objectInput.readInt();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(ctCollectionId);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(fileEntryTypeId);

		objectOutput.writeLong(groupId);

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

		objectOutput.writeLong(dataDefinitionId);

		if (fileEntryTypeKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileEntryTypeKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(scope);
		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public long ctCollectionId;
	public String uuid;
	public long fileEntryTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dataDefinitionId;
	public String fileEntryTypeKey;
	public String name;
	public String description;
	public int scope;
	public long lastPublishDate;

}