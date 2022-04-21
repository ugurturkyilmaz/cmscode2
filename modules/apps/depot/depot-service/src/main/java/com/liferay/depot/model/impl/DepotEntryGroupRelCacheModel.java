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

package com.liferay.depot.model.impl;

import com.liferay.depot.model.DepotEntryGroupRel;
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
 * The cache model class for representing DepotEntryGroupRel in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DepotEntryGroupRelCacheModel
	implements CacheModel<DepotEntryGroupRel>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DepotEntryGroupRelCacheModel)) {
			return false;
		}

		DepotEntryGroupRelCacheModel depotEntryGroupRelCacheModel =
			(DepotEntryGroupRelCacheModel)object;

		if ((depotEntryGroupRelId ==
				depotEntryGroupRelCacheModel.depotEntryGroupRelId) &&
			(mvccVersion == depotEntryGroupRelCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, depotEntryGroupRelId);

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
		StringBundler sb = new StringBundler(29);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", depotEntryGroupRelId=");
		sb.append(depotEntryGroupRelId);
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
		sb.append(", ddmStructuresAvailable=");
		sb.append(ddmStructuresAvailable);
		sb.append(", depotEntryId=");
		sb.append(depotEntryId);
		sb.append(", searchable=");
		sb.append(searchable);
		sb.append(", toGroupId=");
		sb.append(toGroupId);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DepotEntryGroupRel toEntityModel() {
		DepotEntryGroupRelImpl depotEntryGroupRelImpl =
			new DepotEntryGroupRelImpl();

		depotEntryGroupRelImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			depotEntryGroupRelImpl.setUuid("");
		}
		else {
			depotEntryGroupRelImpl.setUuid(uuid);
		}

		depotEntryGroupRelImpl.setDepotEntryGroupRelId(depotEntryGroupRelId);
		depotEntryGroupRelImpl.setGroupId(groupId);
		depotEntryGroupRelImpl.setCompanyId(companyId);
		depotEntryGroupRelImpl.setUserId(userId);

		if (userName == null) {
			depotEntryGroupRelImpl.setUserName("");
		}
		else {
			depotEntryGroupRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			depotEntryGroupRelImpl.setCreateDate(null);
		}
		else {
			depotEntryGroupRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			depotEntryGroupRelImpl.setModifiedDate(null);
		}
		else {
			depotEntryGroupRelImpl.setModifiedDate(new Date(modifiedDate));
		}

		depotEntryGroupRelImpl.setDdmStructuresAvailable(
			ddmStructuresAvailable);
		depotEntryGroupRelImpl.setDepotEntryId(depotEntryId);
		depotEntryGroupRelImpl.setSearchable(searchable);
		depotEntryGroupRelImpl.setToGroupId(toGroupId);

		if (lastPublishDate == Long.MIN_VALUE) {
			depotEntryGroupRelImpl.setLastPublishDate(null);
		}
		else {
			depotEntryGroupRelImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		depotEntryGroupRelImpl.resetOriginalValues();

		return depotEntryGroupRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		depotEntryGroupRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		ddmStructuresAvailable = objectInput.readBoolean();

		depotEntryId = objectInput.readLong();

		searchable = objectInput.readBoolean();

		toGroupId = objectInput.readLong();
		lastPublishDate = objectInput.readLong();
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

		objectOutput.writeLong(depotEntryGroupRelId);

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

		objectOutput.writeBoolean(ddmStructuresAvailable);

		objectOutput.writeLong(depotEntryId);

		objectOutput.writeBoolean(searchable);

		objectOutput.writeLong(toGroupId);
		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public String uuid;
	public long depotEntryGroupRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public boolean ddmStructuresAvailable;
	public long depotEntryId;
	public boolean searchable;
	public long toGroupId;
	public long lastPublishDate;

}