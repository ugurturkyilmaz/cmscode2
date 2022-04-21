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

package com.liferay.style.book.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.style.book.model.StyleBookEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StyleBookEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class StyleBookEntryCacheModel
	implements CacheModel<StyleBookEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof StyleBookEntryCacheModel)) {
			return false;
		}

		StyleBookEntryCacheModel styleBookEntryCacheModel =
			(StyleBookEntryCacheModel)object;

		if ((styleBookEntryId == styleBookEntryCacheModel.styleBookEntryId) &&
			(mvccVersion == styleBookEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, styleBookEntryId);

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
		sb.append(", headId=");
		sb.append(headId);
		sb.append(", styleBookEntryId=");
		sb.append(styleBookEntryId);
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
		sb.append(", defaultStyleBookEntry=");
		sb.append(defaultStyleBookEntry);
		sb.append(", frontendTokensValues=");
		sb.append(frontendTokensValues);
		sb.append(", name=");
		sb.append(name);
		sb.append(", previewFileEntryId=");
		sb.append(previewFileEntryId);
		sb.append(", styleBookEntryKey=");
		sb.append(styleBookEntryKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StyleBookEntry toEntityModel() {
		StyleBookEntryImpl styleBookEntryImpl = new StyleBookEntryImpl();

		styleBookEntryImpl.setMvccVersion(mvccVersion);
		styleBookEntryImpl.setCtCollectionId(ctCollectionId);

		if (uuid == null) {
			styleBookEntryImpl.setUuid("");
		}
		else {
			styleBookEntryImpl.setUuid(uuid);
		}

		styleBookEntryImpl.setHeadId(headId);
		styleBookEntryImpl.setHead(head);
		styleBookEntryImpl.setStyleBookEntryId(styleBookEntryId);
		styleBookEntryImpl.setGroupId(groupId);
		styleBookEntryImpl.setCompanyId(companyId);
		styleBookEntryImpl.setUserId(userId);

		if (userName == null) {
			styleBookEntryImpl.setUserName("");
		}
		else {
			styleBookEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			styleBookEntryImpl.setCreateDate(null);
		}
		else {
			styleBookEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			styleBookEntryImpl.setModifiedDate(null);
		}
		else {
			styleBookEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		styleBookEntryImpl.setDefaultStyleBookEntry(defaultStyleBookEntry);

		if (frontendTokensValues == null) {
			styleBookEntryImpl.setFrontendTokensValues("");
		}
		else {
			styleBookEntryImpl.setFrontendTokensValues(frontendTokensValues);
		}

		if (name == null) {
			styleBookEntryImpl.setName("");
		}
		else {
			styleBookEntryImpl.setName(name);
		}

		styleBookEntryImpl.setPreviewFileEntryId(previewFileEntryId);

		if (styleBookEntryKey == null) {
			styleBookEntryImpl.setStyleBookEntryKey("");
		}
		else {
			styleBookEntryImpl.setStyleBookEntryKey(styleBookEntryKey);
		}

		styleBookEntryImpl.resetOriginalValues();

		return styleBookEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		ctCollectionId = objectInput.readLong();
		uuid = objectInput.readUTF();

		headId = objectInput.readLong();

		head = objectInput.readBoolean();

		styleBookEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		defaultStyleBookEntry = objectInput.readBoolean();
		frontendTokensValues = (String)objectInput.readObject();
		name = objectInput.readUTF();

		previewFileEntryId = objectInput.readLong();
		styleBookEntryKey = objectInput.readUTF();
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

		objectOutput.writeLong(headId);

		objectOutput.writeBoolean(head);

		objectOutput.writeLong(styleBookEntryId);

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

		objectOutput.writeBoolean(defaultStyleBookEntry);

		if (frontendTokensValues == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(frontendTokensValues);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(previewFileEntryId);

		if (styleBookEntryKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(styleBookEntryKey);
		}
	}

	public long mvccVersion;
	public long ctCollectionId;
	public String uuid;
	public long headId;
	public boolean head;
	public long styleBookEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public boolean defaultStyleBookEntry;
	public String frontendTokensValues;
	public String name;
	public long previewFileEntryId;
	public String styleBookEntryKey;

}