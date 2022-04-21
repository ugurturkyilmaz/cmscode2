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

package com.liferay.invitation.invite.members.model.impl;

import com.liferay.invitation.invite.members.model.MemberRequest;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MemberRequest in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MemberRequestCacheModel
	implements CacheModel<MemberRequest>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MemberRequestCacheModel)) {
			return false;
		}

		MemberRequestCacheModel memberRequestCacheModel =
			(MemberRequestCacheModel)object;

		if (memberRequestId == memberRequestCacheModel.memberRequestId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, memberRequestId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{memberRequestId=");
		sb.append(memberRequestId);
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
		sb.append(", key=");
		sb.append(key);
		sb.append(", receiverUserId=");
		sb.append(receiverUserId);
		sb.append(", invitedRoleId=");
		sb.append(invitedRoleId);
		sb.append(", invitedTeamId=");
		sb.append(invitedTeamId);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MemberRequest toEntityModel() {
		MemberRequestImpl memberRequestImpl = new MemberRequestImpl();

		memberRequestImpl.setMemberRequestId(memberRequestId);
		memberRequestImpl.setGroupId(groupId);
		memberRequestImpl.setCompanyId(companyId);
		memberRequestImpl.setUserId(userId);

		if (userName == null) {
			memberRequestImpl.setUserName("");
		}
		else {
			memberRequestImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			memberRequestImpl.setCreateDate(null);
		}
		else {
			memberRequestImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			memberRequestImpl.setModifiedDate(null);
		}
		else {
			memberRequestImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (key == null) {
			memberRequestImpl.setKey("");
		}
		else {
			memberRequestImpl.setKey(key);
		}

		memberRequestImpl.setReceiverUserId(receiverUserId);
		memberRequestImpl.setInvitedRoleId(invitedRoleId);
		memberRequestImpl.setInvitedTeamId(invitedTeamId);
		memberRequestImpl.setStatus(status);

		memberRequestImpl.resetOriginalValues();

		return memberRequestImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		memberRequestId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		key = objectInput.readUTF();

		receiverUserId = objectInput.readLong();

		invitedRoleId = objectInput.readLong();

		invitedTeamId = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(memberRequestId);

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

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		objectOutput.writeLong(receiverUserId);

		objectOutput.writeLong(invitedRoleId);

		objectOutput.writeLong(invitedTeamId);

		objectOutput.writeInt(status);
	}

	public long memberRequestId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String key;
	public long receiverUserId;
	public long invitedRoleId;
	public long invitedTeamId;
	public int status;

}