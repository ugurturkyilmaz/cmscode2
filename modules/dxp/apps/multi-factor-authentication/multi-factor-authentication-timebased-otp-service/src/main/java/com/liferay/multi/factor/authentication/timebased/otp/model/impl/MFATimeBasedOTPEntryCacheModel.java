/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.multi.factor.authentication.timebased.otp.model.impl;

import com.liferay.multi.factor.authentication.timebased.otp.model.MFATimeBasedOTPEntry;
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
 * The cache model class for representing MFATimeBasedOTPEntry in entity cache.
 *
 * @author Arthur Chan
 * @generated
 */
public class MFATimeBasedOTPEntryCacheModel
	implements CacheModel<MFATimeBasedOTPEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MFATimeBasedOTPEntryCacheModel)) {
			return false;
		}

		MFATimeBasedOTPEntryCacheModel mfaTimeBasedOTPEntryCacheModel =
			(MFATimeBasedOTPEntryCacheModel)object;

		if ((mfaTimeBasedOTPEntryId ==
				mfaTimeBasedOTPEntryCacheModel.mfaTimeBasedOTPEntryId) &&
			(mvccVersion == mfaTimeBasedOTPEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, mfaTimeBasedOTPEntryId);

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
		StringBundler sb = new StringBundler(27);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", mfaTimeBasedOTPEntryId=");
		sb.append(mfaTimeBasedOTPEntryId);
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
		sb.append(", failedAttempts=");
		sb.append(failedAttempts);
		sb.append(", lastFailDate=");
		sb.append(lastFailDate);
		sb.append(", lastFailIP=");
		sb.append(lastFailIP);
		sb.append(", lastSuccessDate=");
		sb.append(lastSuccessDate);
		sb.append(", lastSuccessIP=");
		sb.append(lastSuccessIP);
		sb.append(", sharedSecret=");
		sb.append(sharedSecret);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MFATimeBasedOTPEntry toEntityModel() {
		MFATimeBasedOTPEntryImpl mfaTimeBasedOTPEntryImpl =
			new MFATimeBasedOTPEntryImpl();

		mfaTimeBasedOTPEntryImpl.setMvccVersion(mvccVersion);
		mfaTimeBasedOTPEntryImpl.setMfaTimeBasedOTPEntryId(
			mfaTimeBasedOTPEntryId);
		mfaTimeBasedOTPEntryImpl.setCompanyId(companyId);
		mfaTimeBasedOTPEntryImpl.setUserId(userId);

		if (userName == null) {
			mfaTimeBasedOTPEntryImpl.setUserName("");
		}
		else {
			mfaTimeBasedOTPEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mfaTimeBasedOTPEntryImpl.setCreateDate(null);
		}
		else {
			mfaTimeBasedOTPEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mfaTimeBasedOTPEntryImpl.setModifiedDate(null);
		}
		else {
			mfaTimeBasedOTPEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		mfaTimeBasedOTPEntryImpl.setFailedAttempts(failedAttempts);

		if (lastFailDate == Long.MIN_VALUE) {
			mfaTimeBasedOTPEntryImpl.setLastFailDate(null);
		}
		else {
			mfaTimeBasedOTPEntryImpl.setLastFailDate(new Date(lastFailDate));
		}

		if (lastFailIP == null) {
			mfaTimeBasedOTPEntryImpl.setLastFailIP("");
		}
		else {
			mfaTimeBasedOTPEntryImpl.setLastFailIP(lastFailIP);
		}

		if (lastSuccessDate == Long.MIN_VALUE) {
			mfaTimeBasedOTPEntryImpl.setLastSuccessDate(null);
		}
		else {
			mfaTimeBasedOTPEntryImpl.setLastSuccessDate(
				new Date(lastSuccessDate));
		}

		if (lastSuccessIP == null) {
			mfaTimeBasedOTPEntryImpl.setLastSuccessIP("");
		}
		else {
			mfaTimeBasedOTPEntryImpl.setLastSuccessIP(lastSuccessIP);
		}

		if (sharedSecret == null) {
			mfaTimeBasedOTPEntryImpl.setSharedSecret("");
		}
		else {
			mfaTimeBasedOTPEntryImpl.setSharedSecret(sharedSecret);
		}

		mfaTimeBasedOTPEntryImpl.resetOriginalValues();

		return mfaTimeBasedOTPEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		mfaTimeBasedOTPEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		failedAttempts = objectInput.readInt();
		lastFailDate = objectInput.readLong();
		lastFailIP = objectInput.readUTF();
		lastSuccessDate = objectInput.readLong();
		lastSuccessIP = objectInput.readUTF();
		sharedSecret = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(mfaTimeBasedOTPEntryId);

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

		objectOutput.writeInt(failedAttempts);
		objectOutput.writeLong(lastFailDate);

		if (lastFailIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastFailIP);
		}

		objectOutput.writeLong(lastSuccessDate);

		if (lastSuccessIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastSuccessIP);
		}

		if (sharedSecret == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sharedSecret);
		}
	}

	public long mvccVersion;
	public long mfaTimeBasedOTPEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int failedAttempts;
	public long lastFailDate;
	public String lastFailIP;
	public long lastSuccessDate;
	public String lastSuccessIP;
	public String sharedSecret;

}