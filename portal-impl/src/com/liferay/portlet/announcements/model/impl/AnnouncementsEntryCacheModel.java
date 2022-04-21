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

package com.liferay.portlet.announcements.model.impl;

import com.liferay.announcements.kernel.model.AnnouncementsEntry;
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
 * The cache model class for representing AnnouncementsEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AnnouncementsEntryCacheModel
	implements CacheModel<AnnouncementsEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AnnouncementsEntryCacheModel)) {
			return false;
		}

		AnnouncementsEntryCacheModel announcementsEntryCacheModel =
			(AnnouncementsEntryCacheModel)object;

		if ((entryId == announcementsEntryCacheModel.entryId) &&
			(mvccVersion == announcementsEntryCacheModel.mvccVersion)) {

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
		StringBundler sb = new StringBundler(37);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", entryId=");
		sb.append(entryId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", title=");
		sb.append(title);
		sb.append(", content=");
		sb.append(content);
		sb.append(", url=");
		sb.append(url);
		sb.append(", type=");
		sb.append(type);
		sb.append(", displayDate=");
		sb.append(displayDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", alert=");
		sb.append(alert);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AnnouncementsEntry toEntityModel() {
		AnnouncementsEntryImpl announcementsEntryImpl =
			new AnnouncementsEntryImpl();

		announcementsEntryImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			announcementsEntryImpl.setUuid("");
		}
		else {
			announcementsEntryImpl.setUuid(uuid);
		}

		announcementsEntryImpl.setEntryId(entryId);
		announcementsEntryImpl.setCompanyId(companyId);
		announcementsEntryImpl.setUserId(userId);

		if (userName == null) {
			announcementsEntryImpl.setUserName("");
		}
		else {
			announcementsEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			announcementsEntryImpl.setCreateDate(null);
		}
		else {
			announcementsEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			announcementsEntryImpl.setModifiedDate(null);
		}
		else {
			announcementsEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		announcementsEntryImpl.setClassNameId(classNameId);
		announcementsEntryImpl.setClassPK(classPK);

		if (title == null) {
			announcementsEntryImpl.setTitle("");
		}
		else {
			announcementsEntryImpl.setTitle(title);
		}

		if (content == null) {
			announcementsEntryImpl.setContent("");
		}
		else {
			announcementsEntryImpl.setContent(content);
		}

		if (url == null) {
			announcementsEntryImpl.setUrl("");
		}
		else {
			announcementsEntryImpl.setUrl(url);
		}

		if (type == null) {
			announcementsEntryImpl.setType("");
		}
		else {
			announcementsEntryImpl.setType(type);
		}

		if (displayDate == Long.MIN_VALUE) {
			announcementsEntryImpl.setDisplayDate(null);
		}
		else {
			announcementsEntryImpl.setDisplayDate(new Date(displayDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			announcementsEntryImpl.setExpirationDate(null);
		}
		else {
			announcementsEntryImpl.setExpirationDate(new Date(expirationDate));
		}

		announcementsEntryImpl.setPriority(priority);
		announcementsEntryImpl.setAlert(alert);

		announcementsEntryImpl.resetOriginalValues();

		return announcementsEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		entryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		title = objectInput.readUTF();
		content = (String)objectInput.readObject();
		url = objectInput.readUTF();
		type = objectInput.readUTF();
		displayDate = objectInput.readLong();
		expirationDate = objectInput.readLong();

		priority = objectInput.readInt();

		alert = objectInput.readBoolean();
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

		objectOutput.writeLong(entryId);

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

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (content == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(content);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeLong(displayDate);
		objectOutput.writeLong(expirationDate);

		objectOutput.writeInt(priority);

		objectOutput.writeBoolean(alert);
	}

	public long mvccVersion;
	public String uuid;
	public long entryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String title;
	public String content;
	public String url;
	public String type;
	public long displayDate;
	public long expirationDate;
	public int priority;
	public boolean alert;

}