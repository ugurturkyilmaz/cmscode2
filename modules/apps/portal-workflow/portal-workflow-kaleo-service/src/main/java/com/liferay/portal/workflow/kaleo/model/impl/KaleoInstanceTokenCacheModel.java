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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoInstanceToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoInstanceTokenCacheModel
	implements CacheModel<KaleoInstanceToken>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KaleoInstanceTokenCacheModel)) {
			return false;
		}

		KaleoInstanceTokenCacheModel kaleoInstanceTokenCacheModel =
			(KaleoInstanceTokenCacheModel)object;

		if ((kaleoInstanceTokenId ==
				kaleoInstanceTokenCacheModel.kaleoInstanceTokenId) &&
			(mvccVersion == kaleoInstanceTokenCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, kaleoInstanceTokenId);

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
		sb.append(", kaleoInstanceTokenId=");
		sb.append(kaleoInstanceTokenId);
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
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
		sb.append(", kaleoDefinitionVersionId=");
		sb.append(kaleoDefinitionVersionId);
		sb.append(", kaleoInstanceId=");
		sb.append(kaleoInstanceId);
		sb.append(", parentKaleoInstanceTokenId=");
		sb.append(parentKaleoInstanceTokenId);
		sb.append(", currentKaleoNodeId=");
		sb.append(currentKaleoNodeId);
		sb.append(", currentKaleoNodeName=");
		sb.append(currentKaleoNodeName);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", completed=");
		sb.append(completed);
		sb.append(", completionDate=");
		sb.append(completionDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoInstanceToken toEntityModel() {
		KaleoInstanceTokenImpl kaleoInstanceTokenImpl =
			new KaleoInstanceTokenImpl();

		kaleoInstanceTokenImpl.setMvccVersion(mvccVersion);
		kaleoInstanceTokenImpl.setKaleoInstanceTokenId(kaleoInstanceTokenId);
		kaleoInstanceTokenImpl.setGroupId(groupId);
		kaleoInstanceTokenImpl.setCompanyId(companyId);
		kaleoInstanceTokenImpl.setUserId(userId);

		if (userName == null) {
			kaleoInstanceTokenImpl.setUserName("");
		}
		else {
			kaleoInstanceTokenImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoInstanceTokenImpl.setCreateDate(null);
		}
		else {
			kaleoInstanceTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoInstanceTokenImpl.setModifiedDate(null);
		}
		else {
			kaleoInstanceTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoInstanceTokenImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoInstanceTokenImpl.setKaleoDefinitionVersionId(
			kaleoDefinitionVersionId);
		kaleoInstanceTokenImpl.setKaleoInstanceId(kaleoInstanceId);
		kaleoInstanceTokenImpl.setParentKaleoInstanceTokenId(
			parentKaleoInstanceTokenId);
		kaleoInstanceTokenImpl.setCurrentKaleoNodeId(currentKaleoNodeId);

		if (currentKaleoNodeName == null) {
			kaleoInstanceTokenImpl.setCurrentKaleoNodeName("");
		}
		else {
			kaleoInstanceTokenImpl.setCurrentKaleoNodeName(
				currentKaleoNodeName);
		}

		if (className == null) {
			kaleoInstanceTokenImpl.setClassName("");
		}
		else {
			kaleoInstanceTokenImpl.setClassName(className);
		}

		kaleoInstanceTokenImpl.setClassPK(classPK);
		kaleoInstanceTokenImpl.setCompleted(completed);

		if (completionDate == Long.MIN_VALUE) {
			kaleoInstanceTokenImpl.setCompletionDate(null);
		}
		else {
			kaleoInstanceTokenImpl.setCompletionDate(new Date(completionDate));
		}

		kaleoInstanceTokenImpl.resetOriginalValues();

		return kaleoInstanceTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		kaleoInstanceTokenId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		kaleoDefinitionId = objectInput.readLong();

		kaleoDefinitionVersionId = objectInput.readLong();

		kaleoInstanceId = objectInput.readLong();

		parentKaleoInstanceTokenId = objectInput.readLong();

		currentKaleoNodeId = objectInput.readLong();
		currentKaleoNodeName = objectInput.readUTF();
		className = objectInput.readUTF();

		classPK = objectInput.readLong();

		completed = objectInput.readBoolean();
		completionDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(kaleoInstanceTokenId);

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

		objectOutput.writeLong(kaleoDefinitionId);

		objectOutput.writeLong(kaleoDefinitionVersionId);

		objectOutput.writeLong(kaleoInstanceId);

		objectOutput.writeLong(parentKaleoInstanceTokenId);

		objectOutput.writeLong(currentKaleoNodeId);

		if (currentKaleoNodeName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(currentKaleoNodeName);
		}

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		objectOutput.writeLong(classPK);

		objectOutput.writeBoolean(completed);
		objectOutput.writeLong(completionDate);
	}

	public long mvccVersion;
	public long kaleoInstanceTokenId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoDefinitionVersionId;
	public long kaleoInstanceId;
	public long parentKaleoInstanceTokenId;
	public long currentKaleoNodeId;
	public String currentKaleoNodeName;
	public String className;
	public long classPK;
	public boolean completed;
	public long completionDate;

}