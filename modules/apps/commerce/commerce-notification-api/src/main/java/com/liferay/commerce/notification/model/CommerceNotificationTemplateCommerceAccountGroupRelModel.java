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

package com.liferay.commerce.notification.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceNotificationTemplateCommerceAccountGroupRel service. Represents a row in the &quot;CNTemplateCAccountGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.notification.model.impl.CommerceNotificationTemplateCommerceAccountGroupRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.notification.model.impl.CommerceNotificationTemplateCommerceAccountGroupRelImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceNotificationTemplateCommerceAccountGroupRel
 * @generated
 */
@ProviderType
public interface CommerceNotificationTemplateCommerceAccountGroupRelModel
	extends BaseModel<CommerceNotificationTemplateCommerceAccountGroupRel>,
			GroupedModel, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce notification template commerce account group rel model instance should use the {@link CommerceNotificationTemplateCommerceAccountGroupRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce notification template commerce account group rel.
	 *
	 * @return the primary key of this commerce notification template commerce account group rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce notification template commerce account group rel.
	 *
	 * @param primaryKey the primary key of this commerce notification template commerce account group rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce notification template commerce account group rel.
	 *
	 * @return the mvcc version of this commerce notification template commerce account group rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce notification template commerce account group rel.
	 *
	 * @param mvccVersion the mvcc version of this commerce notification template commerce account group rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the commerce notification template commerce account group rel ID of this commerce notification template commerce account group rel.
	 *
	 * @return the commerce notification template commerce account group rel ID of this commerce notification template commerce account group rel
	 */
	public long getCommerceNotificationTemplateCommerceAccountGroupRelId();

	/**
	 * Sets the commerce notification template commerce account group rel ID of this commerce notification template commerce account group rel.
	 *
	 * @param commerceNotificationTemplateCommerceAccountGroupRelId the commerce notification template commerce account group rel ID of this commerce notification template commerce account group rel
	 */
	public void setCommerceNotificationTemplateCommerceAccountGroupRelId(
		long commerceNotificationTemplateCommerceAccountGroupRelId);

	/**
	 * Returns the group ID of this commerce notification template commerce account group rel.
	 *
	 * @return the group ID of this commerce notification template commerce account group rel
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce notification template commerce account group rel.
	 *
	 * @param groupId the group ID of this commerce notification template commerce account group rel
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce notification template commerce account group rel.
	 *
	 * @return the company ID of this commerce notification template commerce account group rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce notification template commerce account group rel.
	 *
	 * @param companyId the company ID of this commerce notification template commerce account group rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce notification template commerce account group rel.
	 *
	 * @return the user ID of this commerce notification template commerce account group rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce notification template commerce account group rel.
	 *
	 * @param userId the user ID of this commerce notification template commerce account group rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce notification template commerce account group rel.
	 *
	 * @return the user uuid of this commerce notification template commerce account group rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce notification template commerce account group rel.
	 *
	 * @param userUuid the user uuid of this commerce notification template commerce account group rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce notification template commerce account group rel.
	 *
	 * @return the user name of this commerce notification template commerce account group rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce notification template commerce account group rel.
	 *
	 * @param userName the user name of this commerce notification template commerce account group rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce notification template commerce account group rel.
	 *
	 * @return the create date of this commerce notification template commerce account group rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce notification template commerce account group rel.
	 *
	 * @param createDate the create date of this commerce notification template commerce account group rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce notification template commerce account group rel.
	 *
	 * @return the modified date of this commerce notification template commerce account group rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce notification template commerce account group rel.
	 *
	 * @param modifiedDate the modified date of this commerce notification template commerce account group rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce notification template ID of this commerce notification template commerce account group rel.
	 *
	 * @return the commerce notification template ID of this commerce notification template commerce account group rel
	 */
	public long getCommerceNotificationTemplateId();

	/**
	 * Sets the commerce notification template ID of this commerce notification template commerce account group rel.
	 *
	 * @param commerceNotificationTemplateId the commerce notification template ID of this commerce notification template commerce account group rel
	 */
	public void setCommerceNotificationTemplateId(
		long commerceNotificationTemplateId);

	/**
	 * Returns the commerce account group ID of this commerce notification template commerce account group rel.
	 *
	 * @return the commerce account group ID of this commerce notification template commerce account group rel
	 */
	public long getCommerceAccountGroupId();

	/**
	 * Sets the commerce account group ID of this commerce notification template commerce account group rel.
	 *
	 * @param commerceAccountGroupId the commerce account group ID of this commerce notification template commerce account group rel
	 */
	public void setCommerceAccountGroupId(long commerceAccountGroupId);

	@Override
	public CommerceNotificationTemplateCommerceAccountGroupRel
		cloneWithOriginalValues();

}