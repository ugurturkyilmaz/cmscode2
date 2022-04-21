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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceOrderNote;
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
 * The cache model class for representing CommerceOrderNote in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceOrderNoteCacheModel
	implements CacheModel<CommerceOrderNote>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceOrderNoteCacheModel)) {
			return false;
		}

		CommerceOrderNoteCacheModel commerceOrderNoteCacheModel =
			(CommerceOrderNoteCacheModel)object;

		if ((commerceOrderNoteId ==
				commerceOrderNoteCacheModel.commerceOrderNoteId) &&
			(mvccVersion == commerceOrderNoteCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, commerceOrderNoteId);

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
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", commerceOrderNoteId=");
		sb.append(commerceOrderNoteId);
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
		sb.append(", commerceOrderId=");
		sb.append(commerceOrderId);
		sb.append(", content=");
		sb.append(content);
		sb.append(", restricted=");
		sb.append(restricted);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceOrderNote toEntityModel() {
		CommerceOrderNoteImpl commerceOrderNoteImpl =
			new CommerceOrderNoteImpl();

		commerceOrderNoteImpl.setMvccVersion(mvccVersion);

		if (externalReferenceCode == null) {
			commerceOrderNoteImpl.setExternalReferenceCode("");
		}
		else {
			commerceOrderNoteImpl.setExternalReferenceCode(
				externalReferenceCode);
		}

		commerceOrderNoteImpl.setCommerceOrderNoteId(commerceOrderNoteId);
		commerceOrderNoteImpl.setGroupId(groupId);
		commerceOrderNoteImpl.setCompanyId(companyId);
		commerceOrderNoteImpl.setUserId(userId);

		if (userName == null) {
			commerceOrderNoteImpl.setUserName("");
		}
		else {
			commerceOrderNoteImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceOrderNoteImpl.setCreateDate(null);
		}
		else {
			commerceOrderNoteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceOrderNoteImpl.setModifiedDate(null);
		}
		else {
			commerceOrderNoteImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceOrderNoteImpl.setCommerceOrderId(commerceOrderId);

		if (content == null) {
			commerceOrderNoteImpl.setContent("");
		}
		else {
			commerceOrderNoteImpl.setContent(content);
		}

		commerceOrderNoteImpl.setRestricted(restricted);

		commerceOrderNoteImpl.resetOriginalValues();

		return commerceOrderNoteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		externalReferenceCode = objectInput.readUTF();

		commerceOrderNoteId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceOrderId = objectInput.readLong();
		content = objectInput.readUTF();

		restricted = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(commerceOrderNoteId);

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

		objectOutput.writeLong(commerceOrderId);

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeBoolean(restricted);
	}

	public long mvccVersion;
	public String externalReferenceCode;
	public long commerceOrderNoteId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceOrderId;
	public String content;
	public boolean restricted;

}