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

import com.liferay.commerce.model.CommerceAvailabilityEstimate;
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
 * The cache model class for representing CommerceAvailabilityEstimate in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceAvailabilityEstimateCacheModel
	implements CacheModel<CommerceAvailabilityEstimate>, Externalizable,
			   MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceAvailabilityEstimateCacheModel)) {
			return false;
		}

		CommerceAvailabilityEstimateCacheModel
			commerceAvailabilityEstimateCacheModel =
				(CommerceAvailabilityEstimateCacheModel)object;

		if ((commerceAvailabilityEstimateId ==
				commerceAvailabilityEstimateCacheModel.
					commerceAvailabilityEstimateId) &&
			(mvccVersion ==
				commerceAvailabilityEstimateCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, commerceAvailabilityEstimateId);

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
		StringBundler sb = new StringBundler(23);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", commerceAvailabilityEstimateId=");
		sb.append(commerceAvailabilityEstimateId);
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
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceAvailabilityEstimate toEntityModel() {
		CommerceAvailabilityEstimateImpl commerceAvailabilityEstimateImpl =
			new CommerceAvailabilityEstimateImpl();

		commerceAvailabilityEstimateImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			commerceAvailabilityEstimateImpl.setUuid("");
		}
		else {
			commerceAvailabilityEstimateImpl.setUuid(uuid);
		}

		commerceAvailabilityEstimateImpl.setCommerceAvailabilityEstimateId(
			commerceAvailabilityEstimateId);
		commerceAvailabilityEstimateImpl.setCompanyId(companyId);
		commerceAvailabilityEstimateImpl.setUserId(userId);

		if (userName == null) {
			commerceAvailabilityEstimateImpl.setUserName("");
		}
		else {
			commerceAvailabilityEstimateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceAvailabilityEstimateImpl.setCreateDate(null);
		}
		else {
			commerceAvailabilityEstimateImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceAvailabilityEstimateImpl.setModifiedDate(null);
		}
		else {
			commerceAvailabilityEstimateImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		if (title == null) {
			commerceAvailabilityEstimateImpl.setTitle("");
		}
		else {
			commerceAvailabilityEstimateImpl.setTitle(title);
		}

		commerceAvailabilityEstimateImpl.setPriority(priority);

		if (lastPublishDate == Long.MIN_VALUE) {
			commerceAvailabilityEstimateImpl.setLastPublishDate(null);
		}
		else {
			commerceAvailabilityEstimateImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		commerceAvailabilityEstimateImpl.resetOriginalValues();

		return commerceAvailabilityEstimateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		commerceAvailabilityEstimateId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();

		priority = objectInput.readDouble();
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

		objectOutput.writeLong(commerceAvailabilityEstimateId);

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

		objectOutput.writeDouble(priority);
		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public String uuid;
	public long commerceAvailabilityEstimateId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public double priority;
	public long lastPublishDate;

}