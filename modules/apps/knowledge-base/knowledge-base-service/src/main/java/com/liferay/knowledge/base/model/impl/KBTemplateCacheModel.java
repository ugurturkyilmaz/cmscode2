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

package com.liferay.knowledge.base.model.impl;

import com.liferay.knowledge.base.model.KBTemplate;
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
 * The cache model class for representing KBTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KBTemplateCacheModel
	implements CacheModel<KBTemplate>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KBTemplateCacheModel)) {
			return false;
		}

		KBTemplateCacheModel kbTemplateCacheModel =
			(KBTemplateCacheModel)object;

		if ((kbTemplateId == kbTemplateCacheModel.kbTemplateId) &&
			(mvccVersion == kbTemplateCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, kbTemplateId);

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
		StringBundler sb = new StringBundler(25);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", kbTemplateId=");
		sb.append(kbTemplateId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", content=");
		sb.append(content);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KBTemplate toEntityModel() {
		KBTemplateImpl kbTemplateImpl = new KBTemplateImpl();

		kbTemplateImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			kbTemplateImpl.setUuid("");
		}
		else {
			kbTemplateImpl.setUuid(uuid);
		}

		kbTemplateImpl.setKbTemplateId(kbTemplateId);
		kbTemplateImpl.setGroupId(groupId);
		kbTemplateImpl.setCompanyId(companyId);
		kbTemplateImpl.setUserId(userId);

		if (userName == null) {
			kbTemplateImpl.setUserName("");
		}
		else {
			kbTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kbTemplateImpl.setCreateDate(null);
		}
		else {
			kbTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kbTemplateImpl.setModifiedDate(null);
		}
		else {
			kbTemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			kbTemplateImpl.setTitle("");
		}
		else {
			kbTemplateImpl.setTitle(title);
		}

		if (content == null) {
			kbTemplateImpl.setContent("");
		}
		else {
			kbTemplateImpl.setContent(content);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			kbTemplateImpl.setLastPublishDate(null);
		}
		else {
			kbTemplateImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		kbTemplateImpl.resetOriginalValues();

		return kbTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		kbTemplateId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		content = (String)objectInput.readObject();
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

		objectOutput.writeLong(kbTemplateId);

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

		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public String uuid;
	public long kbTemplateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String content;
	public long lastPublishDate;

}