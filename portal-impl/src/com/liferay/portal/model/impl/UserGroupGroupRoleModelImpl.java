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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupGroupRoleModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

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
 * The base model implementation for the UserGroupGroupRole service. Represents a row in the &quot;UserGroupGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserGroupGroupRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserGroupGroupRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRoleImpl
 * @generated
 */
@JSON(strict = true)
public class UserGroupGroupRoleModelImpl
	extends BaseModelImpl<UserGroupGroupRole>
	implements UserGroupGroupRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user group group role model instance should use the <code>UserGroupGroupRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "UserGroupGroupRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"userGroupGroupRoleId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userGroupId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"roleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userGroupGroupRoleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table UserGroupGroupRole (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,userGroupGroupRoleId LONG not null,companyId LONG,userGroupId LONG,groupId LONG,roleId LONG,primary key (userGroupGroupRoleId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table UserGroupGroupRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userGroupGroupRole.userGroupGroupRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY UserGroupGroupRole.userGroupGroupRoleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ROLEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERGROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERGROUPGROUPROLEID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.UserGroupGroupRole"));

	public UserGroupGroupRoleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userGroupGroupRoleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserGroupGroupRoleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userGroupGroupRoleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserGroupGroupRole.class;
	}

	@Override
	public String getModelClassName() {
		return UserGroupGroupRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserGroupGroupRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserGroupGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserGroupGroupRole)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserGroupGroupRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserGroupGroupRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserGroupGroupRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserGroupGroupRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserGroupGroupRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<UserGroupGroupRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserGroupGroupRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserGroupGroupRole, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<UserGroupGroupRole, Object>>();
		Map<String, BiConsumer<UserGroupGroupRole, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<UserGroupGroupRole, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", UserGroupGroupRole::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", UserGroupGroupRole::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setCtCollectionId);
		attributeGetterFunctions.put(
			"userGroupGroupRoleId",
			UserGroupGroupRole::getUserGroupGroupRoleId);
		attributeSetterBiConsumers.put(
			"userGroupGroupRoleId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setUserGroupGroupRoleId);
		attributeGetterFunctions.put(
			"companyId", UserGroupGroupRole::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setCompanyId);
		attributeGetterFunctions.put(
			"userGroupId", UserGroupGroupRole::getUserGroupId);
		attributeSetterBiConsumers.put(
			"userGroupId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setUserGroupId);
		attributeGetterFunctions.put("groupId", UserGroupGroupRole::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setGroupId);
		attributeGetterFunctions.put("roleId", UserGroupGroupRole::getRoleId);
		attributeSetterBiConsumers.put(
			"roleId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setRoleId);

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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	@JSON
	@Override
	public long getUserGroupGroupRoleId() {
		return _userGroupGroupRoleId;
	}

	@Override
	public void setUserGroupGroupRoleId(long userGroupGroupRoleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userGroupGroupRoleId = userGroupGroupRoleId;
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

	@JSON
	@Override
	public long getUserGroupId() {
		return _userGroupId;
	}

	@Override
	public void setUserGroupId(long userGroupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userGroupId = userGroupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserGroupId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("userGroupId"));
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
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_roleId = roleId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRoleId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("roleId"));
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
			getCompanyId(), UserGroupGroupRole.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserGroupGroupRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserGroupGroupRole>
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
		UserGroupGroupRoleImpl userGroupGroupRoleImpl =
			new UserGroupGroupRoleImpl();

		userGroupGroupRoleImpl.setMvccVersion(getMvccVersion());
		userGroupGroupRoleImpl.setCtCollectionId(getCtCollectionId());
		userGroupGroupRoleImpl.setUserGroupGroupRoleId(
			getUserGroupGroupRoleId());
		userGroupGroupRoleImpl.setCompanyId(getCompanyId());
		userGroupGroupRoleImpl.setUserGroupId(getUserGroupId());
		userGroupGroupRoleImpl.setGroupId(getGroupId());
		userGroupGroupRoleImpl.setRoleId(getRoleId());

		userGroupGroupRoleImpl.resetOriginalValues();

		return userGroupGroupRoleImpl;
	}

	@Override
	public UserGroupGroupRole cloneWithOriginalValues() {
		UserGroupGroupRoleImpl userGroupGroupRoleImpl =
			new UserGroupGroupRoleImpl();

		userGroupGroupRoleImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		userGroupGroupRoleImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		userGroupGroupRoleImpl.setUserGroupGroupRoleId(
			this.<Long>getColumnOriginalValue("userGroupGroupRoleId"));
		userGroupGroupRoleImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		userGroupGroupRoleImpl.setUserGroupId(
			this.<Long>getColumnOriginalValue("userGroupId"));
		userGroupGroupRoleImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		userGroupGroupRoleImpl.setRoleId(
			this.<Long>getColumnOriginalValue("roleId"));

		return userGroupGroupRoleImpl;
	}

	@Override
	public int compareTo(UserGroupGroupRole userGroupGroupRole) {
		long primaryKey = userGroupGroupRole.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserGroupGroupRole)) {
			return false;
		}

		UserGroupGroupRole userGroupGroupRole = (UserGroupGroupRole)object;

		long primaryKey = userGroupGroupRole.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<UserGroupGroupRole> toCacheModel() {
		UserGroupGroupRoleCacheModel userGroupGroupRoleCacheModel =
			new UserGroupGroupRoleCacheModel();

		userGroupGroupRoleCacheModel.mvccVersion = getMvccVersion();

		userGroupGroupRoleCacheModel.ctCollectionId = getCtCollectionId();

		userGroupGroupRoleCacheModel.userGroupGroupRoleId =
			getUserGroupGroupRoleId();

		userGroupGroupRoleCacheModel.companyId = getCompanyId();

		userGroupGroupRoleCacheModel.userGroupId = getUserGroupId();

		userGroupGroupRoleCacheModel.groupId = getGroupId();

		userGroupGroupRoleCacheModel.roleId = getRoleId();

		return userGroupGroupRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserGroupGroupRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserGroupGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(UserGroupGroupRole)this);

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
		Map<String, Function<UserGroupGroupRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserGroupGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserGroupGroupRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserGroupGroupRole>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					UserGroupGroupRole.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _userGroupGroupRoleId;
	private long _companyId;
	private long _userGroupId;
	private long _groupId;
	private long _roleId;

	public <T> T getColumnValue(String columnName) {
		Function<UserGroupGroupRole, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserGroupGroupRole)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put(
			"userGroupGroupRoleId", _userGroupGroupRoleId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userGroupId", _userGroupId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("roleId", _roleId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("userGroupGroupRoleId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userGroupId", 16L);

		columnBitmasks.put("groupId", 32L);

		columnBitmasks.put("roleId", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private UserGroupGroupRole _escapedModel;

}