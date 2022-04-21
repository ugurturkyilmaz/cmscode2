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

package com.liferay.commerce.order.rule.model.impl;

import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.model.COREntryModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the COREntry service. Represents a row in the &quot;COREntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>COREntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link COREntryImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see COREntryImpl
 * @generated
 */
@JSON(strict = true)
public class COREntryModelImpl
	extends BaseModelImpl<COREntry> implements COREntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cor entry model instance should use the <code>COREntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "COREntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"externalReferenceCode", Types.VARCHAR},
		{"COREntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"active_", Types.BOOLEAN}, {"description", Types.VARCHAR},
		{"displayDate", Types.TIMESTAMP}, {"expirationDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"priority", Types.INTEGER},
		{"type_", Types.VARCHAR}, {"typeSettings", Types.CLOB},
		{"lastPublishDate", Types.TIMESTAMP}, {"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT}, {"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COREntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("displayDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table COREntry (mvccVersion LONG default 0 not null,externalReferenceCode VARCHAR(75) null,COREntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,active_ BOOLEAN,description VARCHAR(75) null,displayDate DATE null,expirationDate DATE null,name VARCHAR(75) null,priority INTEGER,type_ VARCHAR(75) null,typeSettings TEXT null,lastPublishDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table COREntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY corEntry.priority DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY COREntry.priority DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DISPLAYDATE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXPIRATIONDATE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PRIORITY_COLUMN_BITMASK = 128L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public COREntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _COREntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCOREntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _COREntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return COREntry.class;
	}

	@Override
	public String getModelClassName() {
		return COREntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<COREntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<COREntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<COREntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((COREntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<COREntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<COREntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(COREntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<COREntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<COREntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<COREntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<COREntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<COREntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<COREntry, Object>>();
		Map<String, BiConsumer<COREntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<COREntry, ?>>();

		attributeGetterFunctions.put("mvccVersion", COREntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<COREntry, Long>)COREntry::setMvccVersion);
		attributeGetterFunctions.put(
			"externalReferenceCode", COREntry::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<COREntry, String>)COREntry::setExternalReferenceCode);
		attributeGetterFunctions.put("COREntryId", COREntry::getCOREntryId);
		attributeSetterBiConsumers.put(
			"COREntryId", (BiConsumer<COREntry, Long>)COREntry::setCOREntryId);
		attributeGetterFunctions.put("companyId", COREntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<COREntry, Long>)COREntry::setCompanyId);
		attributeGetterFunctions.put("userId", COREntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<COREntry, Long>)COREntry::setUserId);
		attributeGetterFunctions.put("userName", COREntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<COREntry, String>)COREntry::setUserName);
		attributeGetterFunctions.put("createDate", COREntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<COREntry, Date>)COREntry::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", COREntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<COREntry, Date>)COREntry::setModifiedDate);
		attributeGetterFunctions.put("active", COREntry::getActive);
		attributeSetterBiConsumers.put(
			"active", (BiConsumer<COREntry, Boolean>)COREntry::setActive);
		attributeGetterFunctions.put("description", COREntry::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<COREntry, String>)COREntry::setDescription);
		attributeGetterFunctions.put("displayDate", COREntry::getDisplayDate);
		attributeSetterBiConsumers.put(
			"displayDate",
			(BiConsumer<COREntry, Date>)COREntry::setDisplayDate);
		attributeGetterFunctions.put(
			"expirationDate", COREntry::getExpirationDate);
		attributeSetterBiConsumers.put(
			"expirationDate",
			(BiConsumer<COREntry, Date>)COREntry::setExpirationDate);
		attributeGetterFunctions.put("name", COREntry::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<COREntry, String>)COREntry::setName);
		attributeGetterFunctions.put("priority", COREntry::getPriority);
		attributeSetterBiConsumers.put(
			"priority", (BiConsumer<COREntry, Integer>)COREntry::setPriority);
		attributeGetterFunctions.put("type", COREntry::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<COREntry, String>)COREntry::setType);
		attributeGetterFunctions.put("typeSettings", COREntry::getTypeSettings);
		attributeSetterBiConsumers.put(
			"typeSettings",
			(BiConsumer<COREntry, String>)COREntry::setTypeSettings);
		attributeGetterFunctions.put(
			"lastPublishDate", COREntry::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<COREntry, Date>)COREntry::setLastPublishDate);
		attributeGetterFunctions.put("status", COREntry::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<COREntry, Integer>)COREntry::setStatus);
		attributeGetterFunctions.put(
			"statusByUserId", COREntry::getStatusByUserId);
		attributeSetterBiConsumers.put(
			"statusByUserId",
			(BiConsumer<COREntry, Long>)COREntry::setStatusByUserId);
		attributeGetterFunctions.put(
			"statusByUserName", COREntry::getStatusByUserName);
		attributeSetterBiConsumers.put(
			"statusByUserName",
			(BiConsumer<COREntry, String>)COREntry::setStatusByUserName);
		attributeGetterFunctions.put("statusDate", COREntry::getStatusDate);
		attributeSetterBiConsumers.put(
			"statusDate", (BiConsumer<COREntry, Date>)COREntry::setStatusDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@JSON
	@Override
	public long getCOREntryId() {
		return _COREntryId;
	}

	@Override
	public void setCOREntryId(long COREntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_COREntryId = COREntryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_active = active;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalActive() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("active_"));
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@JSON
	@Override
	public Date getDisplayDate() {
		return _displayDate;
	}

	@Override
	public void setDisplayDate(Date displayDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_displayDate = displayDate;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalDisplayDate() {
		return getColumnOriginalValue("displayDate");
	}

	@JSON
	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expirationDate = expirationDate;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalExpirationDate() {
		return getColumnOriginalValue("expirationDate");
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@JSON
	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalType() {
		return getColumnOriginalValue("type_");
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_typeSettings = typeSettings;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalStatus() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("status"));
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusDate = statusDate;
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), COREntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public COREntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, COREntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		COREntryImpl corEntryImpl = new COREntryImpl();

		corEntryImpl.setMvccVersion(getMvccVersion());
		corEntryImpl.setExternalReferenceCode(getExternalReferenceCode());
		corEntryImpl.setCOREntryId(getCOREntryId());
		corEntryImpl.setCompanyId(getCompanyId());
		corEntryImpl.setUserId(getUserId());
		corEntryImpl.setUserName(getUserName());
		corEntryImpl.setCreateDate(getCreateDate());
		corEntryImpl.setModifiedDate(getModifiedDate());
		corEntryImpl.setActive(isActive());
		corEntryImpl.setDescription(getDescription());
		corEntryImpl.setDisplayDate(getDisplayDate());
		corEntryImpl.setExpirationDate(getExpirationDate());
		corEntryImpl.setName(getName());
		corEntryImpl.setPriority(getPriority());
		corEntryImpl.setType(getType());
		corEntryImpl.setTypeSettings(getTypeSettings());
		corEntryImpl.setLastPublishDate(getLastPublishDate());
		corEntryImpl.setStatus(getStatus());
		corEntryImpl.setStatusByUserId(getStatusByUserId());
		corEntryImpl.setStatusByUserName(getStatusByUserName());
		corEntryImpl.setStatusDate(getStatusDate());

		corEntryImpl.resetOriginalValues();

		return corEntryImpl;
	}

	@Override
	public COREntry cloneWithOriginalValues() {
		COREntryImpl corEntryImpl = new COREntryImpl();

		corEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		corEntryImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		corEntryImpl.setCOREntryId(
			this.<Long>getColumnOriginalValue("COREntryId"));
		corEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		corEntryImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		corEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		corEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		corEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		corEntryImpl.setActive(this.<Boolean>getColumnOriginalValue("active_"));
		corEntryImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		corEntryImpl.setDisplayDate(
			this.<Date>getColumnOriginalValue("displayDate"));
		corEntryImpl.setExpirationDate(
			this.<Date>getColumnOriginalValue("expirationDate"));
		corEntryImpl.setName(this.<String>getColumnOriginalValue("name"));
		corEntryImpl.setPriority(
			this.<Integer>getColumnOriginalValue("priority"));
		corEntryImpl.setType(this.<String>getColumnOriginalValue("type_"));
		corEntryImpl.setTypeSettings(
			this.<String>getColumnOriginalValue("typeSettings"));
		corEntryImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));
		corEntryImpl.setStatus(this.<Integer>getColumnOriginalValue("status"));
		corEntryImpl.setStatusByUserId(
			this.<Long>getColumnOriginalValue("statusByUserId"));
		corEntryImpl.setStatusByUserName(
			this.<String>getColumnOriginalValue("statusByUserName"));
		corEntryImpl.setStatusDate(
			this.<Date>getColumnOriginalValue("statusDate"));

		return corEntryImpl;
	}

	@Override
	public int compareTo(COREntry corEntry) {
		int value = 0;

		if (getPriority() < corEntry.getPriority()) {
			value = -1;
		}
		else if (getPriority() > corEntry.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof COREntry)) {
			return false;
		}

		COREntry corEntry = (COREntry)object;

		long primaryKey = corEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<COREntry> toCacheModel() {
		COREntryCacheModel corEntryCacheModel = new COREntryCacheModel();

		corEntryCacheModel.mvccVersion = getMvccVersion();

		corEntryCacheModel.externalReferenceCode = getExternalReferenceCode();

		String externalReferenceCode = corEntryCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			corEntryCacheModel.externalReferenceCode = null;
		}

		corEntryCacheModel.COREntryId = getCOREntryId();

		corEntryCacheModel.companyId = getCompanyId();

		corEntryCacheModel.userId = getUserId();

		corEntryCacheModel.userName = getUserName();

		String userName = corEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			corEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			corEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			corEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			corEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			corEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		corEntryCacheModel.active = isActive();

		corEntryCacheModel.description = getDescription();

		String description = corEntryCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			corEntryCacheModel.description = null;
		}

		Date displayDate = getDisplayDate();

		if (displayDate != null) {
			corEntryCacheModel.displayDate = displayDate.getTime();
		}
		else {
			corEntryCacheModel.displayDate = Long.MIN_VALUE;
		}

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			corEntryCacheModel.expirationDate = expirationDate.getTime();
		}
		else {
			corEntryCacheModel.expirationDate = Long.MIN_VALUE;
		}

		corEntryCacheModel.name = getName();

		String name = corEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			corEntryCacheModel.name = null;
		}

		corEntryCacheModel.priority = getPriority();

		corEntryCacheModel.type = getType();

		String type = corEntryCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			corEntryCacheModel.type = null;
		}

		corEntryCacheModel.typeSettings = getTypeSettings();

		String typeSettings = corEntryCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			corEntryCacheModel.typeSettings = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			corEntryCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			corEntryCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		corEntryCacheModel.status = getStatus();

		corEntryCacheModel.statusByUserId = getStatusByUserId();

		corEntryCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = corEntryCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			corEntryCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			corEntryCacheModel.statusDate = statusDate.getTime();
		}
		else {
			corEntryCacheModel.statusDate = Long.MIN_VALUE;
		}

		return corEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<COREntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<COREntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<COREntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((COREntry)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<COREntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<COREntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<COREntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((COREntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, COREntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					COREntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _externalReferenceCode;
	private long _COREntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _active;
	private String _description;
	private Date _displayDate;
	private Date _expirationDate;
	private String _name;
	private int _priority;
	private String _type;
	private String _typeSettings;
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<COREntry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((COREntry)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("COREntryId", _COREntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("active_", _active);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("displayDate", _displayDate);
		_columnOriginalValues.put("expirationDate", _expirationDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("priority", _priority);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("typeSettings", _typeSettings);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
		_columnOriginalValues.put("status", _status);
		_columnOriginalValues.put("statusByUserId", _statusByUserId);
		_columnOriginalValues.put("statusByUserName", _statusByUserName);
		_columnOriginalValues.put("statusDate", _statusDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("active_", "active");
		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("externalReferenceCode", 2L);

		columnBitmasks.put("COREntryId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("active_", 256L);

		columnBitmasks.put("description", 512L);

		columnBitmasks.put("displayDate", 1024L);

		columnBitmasks.put("expirationDate", 2048L);

		columnBitmasks.put("name", 4096L);

		columnBitmasks.put("priority", 8192L);

		columnBitmasks.put("type_", 16384L);

		columnBitmasks.put("typeSettings", 32768L);

		columnBitmasks.put("lastPublishDate", 65536L);

		columnBitmasks.put("status", 131072L);

		columnBitmasks.put("statusByUserId", 262144L);

		columnBitmasks.put("statusByUserName", 524288L);

		columnBitmasks.put("statusDate", 1048576L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private COREntry _escapedModel;

}