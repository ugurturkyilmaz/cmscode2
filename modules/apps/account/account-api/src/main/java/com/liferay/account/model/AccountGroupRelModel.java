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

package com.liferay.account.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AccountGroupRel service. Represents a row in the &quot;AccountGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.account.model.impl.AccountGroupRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.account.model.impl.AccountGroupRelImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountGroupRel
 * @generated
 */
@ProviderType
public interface AccountGroupRelModel
	extends AttachedModel, AuditedModel, BaseModel<AccountGroupRel>, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a account group rel model instance should use the {@link AccountGroupRel} interface instead.
	 */

	/**
	 * Returns the primary key of this account group rel.
	 *
	 * @return the primary key of this account group rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this account group rel.
	 *
	 * @param primaryKey the primary key of this account group rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this account group rel.
	 *
	 * @return the mvcc version of this account group rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this account group rel.
	 *
	 * @param mvccVersion the mvcc version of this account group rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the account group rel ID of this account group rel.
	 *
	 * @return the account group rel ID of this account group rel
	 */
	public long getAccountGroupRelId();

	/**
	 * Sets the account group rel ID of this account group rel.
	 *
	 * @param accountGroupRelId the account group rel ID of this account group rel
	 */
	public void setAccountGroupRelId(long accountGroupRelId);

	/**
	 * Returns the company ID of this account group rel.
	 *
	 * @return the company ID of this account group rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this account group rel.
	 *
	 * @param companyId the company ID of this account group rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this account group rel.
	 *
	 * @return the user ID of this account group rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this account group rel.
	 *
	 * @param userId the user ID of this account group rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this account group rel.
	 *
	 * @return the user uuid of this account group rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this account group rel.
	 *
	 * @param userUuid the user uuid of this account group rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this account group rel.
	 *
	 * @return the user name of this account group rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this account group rel.
	 *
	 * @param userName the user name of this account group rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this account group rel.
	 *
	 * @return the create date of this account group rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this account group rel.
	 *
	 * @param createDate the create date of this account group rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this account group rel.
	 *
	 * @return the modified date of this account group rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this account group rel.
	 *
	 * @param modifiedDate the modified date of this account group rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the account group ID of this account group rel.
	 *
	 * @return the account group ID of this account group rel
	 */
	public long getAccountGroupId();

	/**
	 * Sets the account group ID of this account group rel.
	 *
	 * @param accountGroupId the account group ID of this account group rel
	 */
	public void setAccountGroupId(long accountGroupId);

	/**
	 * Returns the fully qualified class name of this account group rel.
	 *
	 * @return the fully qualified class name of this account group rel
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this account group rel.
	 *
	 * @return the class name ID of this account group rel
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this account group rel.
	 *
	 * @param classNameId the class name ID of this account group rel
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this account group rel.
	 *
	 * @return the class pk of this account group rel
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this account group rel.
	 *
	 * @param classPK the class pk of this account group rel
	 */
	@Override
	public void setClassPK(long classPK);

	@Override
	public AccountGroupRel cloneWithOriginalValues();

}