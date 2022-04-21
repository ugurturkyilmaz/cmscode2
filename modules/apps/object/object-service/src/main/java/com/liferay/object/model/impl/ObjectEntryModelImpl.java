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

package com.liferay.object.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectEntryModel;
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
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the ObjectEntry service. Represents a row in the &quot;ObjectEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ObjectEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ObjectEntryImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectEntryImpl
 * @generated
 */
@JSON(strict = true)
public class ObjectEntryModelImpl
	extends BaseModelImpl<ObjectEntry> implements ObjectEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a object entry model instance should use the <code>ObjectEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "ObjectEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"objectEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"externalReferenceCode", Types.VARCHAR},
		{"objectDefinitionId", Types.BIGINT},
		{"lastPublishDate", Types.TIMESTAMP}, {"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT}, {"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("objectEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("objectDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ObjectEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,objectEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,externalReferenceCode VARCHAR(75) null,objectDefinitionId LONG,lastPublishDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table ObjectEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY objectEntry.objectEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ObjectEntry.objectEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OBJECTDEFINITIONID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OBJECTENTRYID_COLUMN_BITMASK = 64L;

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

	public ObjectEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _objectEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setObjectEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _objectEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ObjectEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ObjectEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ObjectEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ObjectEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ObjectEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ObjectEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ObjectEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ObjectEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ObjectEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ObjectEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ObjectEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ObjectEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ObjectEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ObjectEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ObjectEntry, Object>>();
		Map<String, BiConsumer<ObjectEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ObjectEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ObjectEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ObjectEntry, Long>)ObjectEntry::setMvccVersion);
		attributeGetterFunctions.put("uuid", ObjectEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<ObjectEntry, String>)ObjectEntry::setUuid);
		attributeGetterFunctions.put(
			"objectEntryId", ObjectEntry::getObjectEntryId);
		attributeSetterBiConsumers.put(
			"objectEntryId",
			(BiConsumer<ObjectEntry, Long>)ObjectEntry::setObjectEntryId);
		attributeGetterFunctions.put("groupId", ObjectEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<ObjectEntry, Long>)ObjectEntry::setGroupId);
		attributeGetterFunctions.put("companyId", ObjectEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ObjectEntry, Long>)ObjectEntry::setCompanyId);
		attributeGetterFunctions.put("userId", ObjectEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<ObjectEntry, Long>)ObjectEntry::setUserId);
		attributeGetterFunctions.put("userName", ObjectEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<ObjectEntry, String>)ObjectEntry::setUserName);
		attributeGetterFunctions.put("createDate", ObjectEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<ObjectEntry, Date>)ObjectEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", ObjectEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<ObjectEntry, Date>)ObjectEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"externalReferenceCode", ObjectEntry::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<ObjectEntry, String>)
				ObjectEntry::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"objectDefinitionId", ObjectEntry::getObjectDefinitionId);
		attributeSetterBiConsumers.put(
			"objectDefinitionId",
			(BiConsumer<ObjectEntry, Long>)ObjectEntry::setObjectDefinitionId);
		attributeGetterFunctions.put(
			"lastPublishDate", ObjectEntry::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<ObjectEntry, Date>)ObjectEntry::setLastPublishDate);
		attributeGetterFunctions.put("status", ObjectEntry::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<ObjectEntry, Integer>)ObjectEntry::setStatus);
		attributeGetterFunctions.put(
			"statusByUserId", ObjectEntry::getStatusByUserId);
		attributeSetterBiConsumers.put(
			"statusByUserId",
			(BiConsumer<ObjectEntry, Long>)ObjectEntry::setStatusByUserId);
		attributeGetterFunctions.put(
			"statusByUserName", ObjectEntry::getStatusByUserName);
		attributeSetterBiConsumers.put(
			"statusByUserName",
			(BiConsumer<ObjectEntry, String>)ObjectEntry::setStatusByUserName);
		attributeGetterFunctions.put("statusDate", ObjectEntry::getStatusDate);
		attributeSetterBiConsumers.put(
			"statusDate",
			(BiConsumer<ObjectEntry, Date>)ObjectEntry::setStatusDate);

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
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getObjectEntryId() {
		return _objectEntryId;
	}

	@Override
	public void setObjectEntryId(long objectEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_objectEntryId = objectEntryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
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
	public long getObjectDefinitionId() {
		return _objectDefinitionId;
	}

	@Override
	public void setObjectDefinitionId(long objectDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_objectDefinitionId = objectDefinitionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalObjectDefinitionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("objectDefinitionId"));
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

	public Map<String, Serializable> getValues() {
		return null;
	}

	public void setValues(Map<String, Serializable> values) {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(ObjectEntry.class.getName()));
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
			getCompanyId(), ObjectEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ObjectEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ObjectEntry>
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
		ObjectEntryImpl objectEntryImpl = new ObjectEntryImpl();

		objectEntryImpl.setMvccVersion(getMvccVersion());
		objectEntryImpl.setUuid(getUuid());
		objectEntryImpl.setObjectEntryId(getObjectEntryId());
		objectEntryImpl.setGroupId(getGroupId());
		objectEntryImpl.setCompanyId(getCompanyId());
		objectEntryImpl.setUserId(getUserId());
		objectEntryImpl.setUserName(getUserName());
		objectEntryImpl.setCreateDate(getCreateDate());
		objectEntryImpl.setModifiedDate(getModifiedDate());
		objectEntryImpl.setExternalReferenceCode(getExternalReferenceCode());
		objectEntryImpl.setObjectDefinitionId(getObjectDefinitionId());
		objectEntryImpl.setLastPublishDate(getLastPublishDate());
		objectEntryImpl.setStatus(getStatus());
		objectEntryImpl.setStatusByUserId(getStatusByUserId());
		objectEntryImpl.setStatusByUserName(getStatusByUserName());
		objectEntryImpl.setStatusDate(getStatusDate());

		objectEntryImpl.resetOriginalValues();

		return objectEntryImpl;
	}

	@Override
	public ObjectEntry cloneWithOriginalValues() {
		ObjectEntryImpl objectEntryImpl = new ObjectEntryImpl();

		objectEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		objectEntryImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		objectEntryImpl.setObjectEntryId(
			this.<Long>getColumnOriginalValue("objectEntryId"));
		objectEntryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		objectEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		objectEntryImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		objectEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		objectEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		objectEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		objectEntryImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		objectEntryImpl.setObjectDefinitionId(
			this.<Long>getColumnOriginalValue("objectDefinitionId"));
		objectEntryImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));
		objectEntryImpl.setStatus(
			this.<Integer>getColumnOriginalValue("status"));
		objectEntryImpl.setStatusByUserId(
			this.<Long>getColumnOriginalValue("statusByUserId"));
		objectEntryImpl.setStatusByUserName(
			this.<String>getColumnOriginalValue("statusByUserName"));
		objectEntryImpl.setStatusDate(
			this.<Date>getColumnOriginalValue("statusDate"));

		return objectEntryImpl;
	}

	@Override
	public int compareTo(ObjectEntry objectEntry) {
		int value = 0;

		if (getObjectEntryId() < objectEntry.getObjectEntryId()) {
			value = -1;
		}
		else if (getObjectEntryId() > objectEntry.getObjectEntryId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(object instanceof ObjectEntry)) {
			return false;
		}

		ObjectEntry objectEntry = (ObjectEntry)object;

		long primaryKey = objectEntry.getPrimaryKey();

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

		setValues(null);

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<ObjectEntry> toCacheModel() {
		ObjectEntryCacheModel objectEntryCacheModel =
			new ObjectEntryCacheModel();

		objectEntryCacheModel.mvccVersion = getMvccVersion();

		objectEntryCacheModel.uuid = getUuid();

		String uuid = objectEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			objectEntryCacheModel.uuid = null;
		}

		objectEntryCacheModel.objectEntryId = getObjectEntryId();

		objectEntryCacheModel.groupId = getGroupId();

		objectEntryCacheModel.companyId = getCompanyId();

		objectEntryCacheModel.userId = getUserId();

		objectEntryCacheModel.userName = getUserName();

		String userName = objectEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			objectEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			objectEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			objectEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			objectEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			objectEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		objectEntryCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			objectEntryCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			objectEntryCacheModel.externalReferenceCode = null;
		}

		objectEntryCacheModel.objectDefinitionId = getObjectDefinitionId();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			objectEntryCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			objectEntryCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		objectEntryCacheModel.status = getStatus();

		objectEntryCacheModel.statusByUserId = getStatusByUserId();

		objectEntryCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = objectEntryCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			objectEntryCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			objectEntryCacheModel.statusDate = statusDate.getTime();
		}
		else {
			objectEntryCacheModel.statusDate = Long.MIN_VALUE;
		}

		setValues(null);

		objectEntryCacheModel._values = getValues();

		return objectEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ObjectEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ObjectEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ObjectEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((ObjectEntry)this);

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
		Map<String, Function<ObjectEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ObjectEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ObjectEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ObjectEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ObjectEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ObjectEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private long _objectEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _externalReferenceCode;
	private long _objectDefinitionId;
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<ObjectEntry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ObjectEntry)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("objectEntryId", _objectEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("objectDefinitionId", _objectDefinitionId);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
		_columnOriginalValues.put("status", _status);
		_columnOriginalValues.put("statusByUserId", _statusByUserId);
		_columnOriginalValues.put("statusByUserName", _statusByUserName);
		_columnOriginalValues.put("statusDate", _statusDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

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

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("objectEntryId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("externalReferenceCode", 512L);

		columnBitmasks.put("objectDefinitionId", 1024L);

		columnBitmasks.put("lastPublishDate", 2048L);

		columnBitmasks.put("status", 4096L);

		columnBitmasks.put("statusByUserId", 8192L);

		columnBitmasks.put("statusByUserName", 16384L);

		columnBitmasks.put("statusDate", 32768L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ObjectEntry _escapedModel;

}