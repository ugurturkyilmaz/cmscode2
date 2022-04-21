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

package com.liferay.trash.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.trash.model.TrashEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrashEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrashEntryCacheModel
	implements CacheModel<TrashEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TrashEntryCacheModel)) {
			return false;
		}

		TrashEntryCacheModel trashEntryCacheModel =
			(TrashEntryCacheModel)object;

		if ((entryId == trashEntryCacheModel.entryId) &&
			(mvccVersion == trashEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, entryId);

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
		StringBundler sb = new StringBundler(27);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", ctCollectionId=");
		sb.append(ctCollectionId);
		sb.append(", entryId=");
		sb.append(entryId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", systemEventSetKey=");
		sb.append(systemEventSetKey);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TrashEntry toEntityModel() {
		TrashEntryImpl trashEntryImpl = new TrashEntryImpl();

		trashEntryImpl.setMvccVersion(mvccVersion);
		trashEntryImpl.setCtCollectionId(ctCollectionId);
		trashEntryImpl.setEntryId(entryId);
		trashEntryImpl.setGroupId(groupId);
		trashEntryImpl.setCompanyId(companyId);
		trashEntryImpl.setUserId(userId);

		if (userName == null) {
			trashEntryImpl.setUserName("");
		}
		else {
			trashEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			trashEntryImpl.setCreateDate(null);
		}
		else {
			trashEntryImpl.setCreateDate(new Date(createDate));
		}

		trashEntryImpl.setClassNameId(classNameId);
		trashEntryImpl.setClassPK(classPK);
		trashEntryImpl.setSystemEventSetKey(systemEventSetKey);

		if (typeSettings == null) {
			trashEntryImpl.setTypeSettings("");
		}
		else {
			trashEntryImpl.setTypeSettings(typeSettings);
		}

		trashEntryImpl.setStatus(status);

		trashEntryImpl.resetOriginalValues();

		return trashEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		ctCollectionId = objectInput.readLong();

		entryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		systemEventSetKey = objectInput.readLong();
		typeSettings = (String)objectInput.readObject();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(ctCollectionId);

		objectOutput.writeLong(entryId);

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

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(systemEventSetKey);

		if (typeSettings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(typeSettings);
		}

		objectOutput.writeInt(status);
	}

	public long mvccVersion;
	public long ctCollectionId;
	public long entryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long classNameId;
	public long classPK;
	public long systemEventSetKey;
	public String typeSettings;
	public int status;

}