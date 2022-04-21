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

package com.liferay.object.model.impl;

import com.liferay.object.model.ObjectEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;
import java.util.Map;

/**
 * The cache model class for representing ObjectEntry in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class ObjectEntryCacheModel
	implements CacheModel<ObjectEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ObjectEntryCacheModel)) {
			return false;
		}

		ObjectEntryCacheModel objectEntryCacheModel =
			(ObjectEntryCacheModel)object;

		if ((objectEntryId == objectEntryCacheModel.objectEntryId) &&
			(mvccVersion == objectEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, objectEntryId);

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
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", objectEntryId=");
		sb.append(objectEntryId);
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
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", objectDefinitionId=");
		sb.append(objectDefinitionId);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ObjectEntry toEntityModel() {
		ObjectEntryImpl objectEntryImpl = new ObjectEntryImpl();

		objectEntryImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			objectEntryImpl.setUuid("");
		}
		else {
			objectEntryImpl.setUuid(uuid);
		}

		objectEntryImpl.setObjectEntryId(objectEntryId);
		objectEntryImpl.setGroupId(groupId);
		objectEntryImpl.setCompanyId(companyId);
		objectEntryImpl.setUserId(userId);

		if (userName == null) {
			objectEntryImpl.setUserName("");
		}
		else {
			objectEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			objectEntryImpl.setCreateDate(null);
		}
		else {
			objectEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			objectEntryImpl.setModifiedDate(null);
		}
		else {
			objectEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (externalReferenceCode == null) {
			objectEntryImpl.setExternalReferenceCode("");
		}
		else {
			objectEntryImpl.setExternalReferenceCode(externalReferenceCode);
		}

		objectEntryImpl.setObjectDefinitionId(objectDefinitionId);

		if (lastPublishDate == Long.MIN_VALUE) {
			objectEntryImpl.setLastPublishDate(null);
		}
		else {
			objectEntryImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		objectEntryImpl.setStatus(status);
		objectEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			objectEntryImpl.setStatusByUserName("");
		}
		else {
			objectEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			objectEntryImpl.setStatusDate(null);
		}
		else {
			objectEntryImpl.setStatusDate(new Date(statusDate));
		}

		objectEntryImpl.resetOriginalValues();

		objectEntryImpl.setValues(_values);

		return objectEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		objectEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		externalReferenceCode = objectInput.readUTF();

		objectDefinitionId = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();

		_values = (Map)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(objectEntryId);

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

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(objectDefinitionId);
		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		objectOutput.writeObject(_values);
	}

	public long mvccVersion;
	public String uuid;
	public long objectEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String externalReferenceCode;
	public long objectDefinitionId;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public Map _values;

}