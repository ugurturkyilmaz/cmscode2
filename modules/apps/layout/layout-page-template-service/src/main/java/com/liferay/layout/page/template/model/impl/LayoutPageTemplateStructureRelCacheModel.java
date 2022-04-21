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

package com.liferay.layout.page.template.model.impl;

import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
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
 * The cache model class for representing LayoutPageTemplateStructureRel in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LayoutPageTemplateStructureRelCacheModel
	implements CacheModel<LayoutPageTemplateStructureRel>, Externalizable,
			   MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LayoutPageTemplateStructureRelCacheModel)) {
			return false;
		}

		LayoutPageTemplateStructureRelCacheModel
			layoutPageTemplateStructureRelCacheModel =
				(LayoutPageTemplateStructureRelCacheModel)object;

		if ((layoutPageTemplateStructureRelId ==
				layoutPageTemplateStructureRelCacheModel.
					layoutPageTemplateStructureRelId) &&
			(mvccVersion ==
				layoutPageTemplateStructureRelCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, layoutPageTemplateStructureRelId);

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
		StringBundler sb = new StringBundler(37);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", ctCollectionId=");
		sb.append(ctCollectionId);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", layoutPageTemplateStructureRelId=");
		sb.append(layoutPageTemplateStructureRelId);
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
		sb.append(", layoutPageTemplateStructureId=");
		sb.append(layoutPageTemplateStructureId);
		sb.append(", segmentsExperienceId=");
		sb.append(segmentsExperienceId);
		sb.append(", data=");
		sb.append(data);
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
	public LayoutPageTemplateStructureRel toEntityModel() {
		LayoutPageTemplateStructureRelImpl layoutPageTemplateStructureRelImpl =
			new LayoutPageTemplateStructureRelImpl();

		layoutPageTemplateStructureRelImpl.setMvccVersion(mvccVersion);
		layoutPageTemplateStructureRelImpl.setCtCollectionId(ctCollectionId);

		if (uuid == null) {
			layoutPageTemplateStructureRelImpl.setUuid("");
		}
		else {
			layoutPageTemplateStructureRelImpl.setUuid(uuid);
		}

		layoutPageTemplateStructureRelImpl.setLayoutPageTemplateStructureRelId(
			layoutPageTemplateStructureRelId);
		layoutPageTemplateStructureRelImpl.setGroupId(groupId);
		layoutPageTemplateStructureRelImpl.setCompanyId(companyId);
		layoutPageTemplateStructureRelImpl.setUserId(userId);

		if (userName == null) {
			layoutPageTemplateStructureRelImpl.setUserName("");
		}
		else {
			layoutPageTemplateStructureRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			layoutPageTemplateStructureRelImpl.setCreateDate(null);
		}
		else {
			layoutPageTemplateStructureRelImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			layoutPageTemplateStructureRelImpl.setModifiedDate(null);
		}
		else {
			layoutPageTemplateStructureRelImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		layoutPageTemplateStructureRelImpl.setLayoutPageTemplateStructureId(
			layoutPageTemplateStructureId);
		layoutPageTemplateStructureRelImpl.setSegmentsExperienceId(
			segmentsExperienceId);

		if (data == null) {
			layoutPageTemplateStructureRelImpl.setData("");
		}
		else {
			layoutPageTemplateStructureRelImpl.setData(data);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			layoutPageTemplateStructureRelImpl.setLastPublishDate(null);
		}
		else {
			layoutPageTemplateStructureRelImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		layoutPageTemplateStructureRelImpl.setStatus(status);
		layoutPageTemplateStructureRelImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			layoutPageTemplateStructureRelImpl.setStatusByUserName("");
		}
		else {
			layoutPageTemplateStructureRelImpl.setStatusByUserName(
				statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			layoutPageTemplateStructureRelImpl.setStatusDate(null);
		}
		else {
			layoutPageTemplateStructureRelImpl.setStatusDate(
				new Date(statusDate));
		}

		layoutPageTemplateStructureRelImpl.resetOriginalValues();

		return layoutPageTemplateStructureRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		ctCollectionId = objectInput.readLong();
		uuid = objectInput.readUTF();

		layoutPageTemplateStructureRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		layoutPageTemplateStructureId = objectInput.readLong();

		segmentsExperienceId = objectInput.readLong();
		data = (String)objectInput.readObject();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(layoutPageTemplateStructureRelId);

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

		objectOutput.writeLong(layoutPageTemplateStructureId);

		objectOutput.writeLong(segmentsExperienceId);

		if (data == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(data);
		}

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
	}

	public long mvccVersion;
	public long ctCollectionId;
	public String uuid;
	public long layoutPageTemplateStructureRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long layoutPageTemplateStructureId;
	public long segmentsExperienceId;
	public String data;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}