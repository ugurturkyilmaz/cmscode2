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

package com.liferay.akismet.model.impl;

import com.liferay.akismet.model.AkismetEntry;
import com.liferay.akismet.model.AkismetEntryModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the AkismetEntry service. Represents a row in the &quot;OSBCommunity_AkismetEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AkismetEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AkismetEntryImpl}.
 * </p>
 *
 * @author Jamie Sammons
 * @see AkismetEntryImpl
 * @generated
 */
public class AkismetEntryModelImpl
	extends BaseModelImpl<AkismetEntry> implements AkismetEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a akismet entry model instance should use the <code>AkismetEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "OSBCommunity_AkismetEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"akismetEntryId", Types.BIGINT}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"type_", Types.VARCHAR}, {"permalink", Types.VARCHAR},
		{"referrer", Types.VARCHAR}, {"userAgent", Types.VARCHAR},
		{"userIP", Types.VARCHAR}, {"userURL", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("akismetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("permalink", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("referrer", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userAgent", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userIP", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userURL", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OSBCommunity_AkismetEntry (akismetEntryId LONG not null primary key,modifiedDate DATE null,classNameId LONG,classPK LONG,type_ VARCHAR(75) null,permalink STRING null,referrer STRING null,userAgent STRING null,userIP VARCHAR(75) null,userURL STRING null)";

	public static final String TABLE_SQL_DROP =
		"drop table OSBCommunity_AkismetEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY akismetEntry.akismetEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OSBCommunity_AkismetEntry.akismetEntryId ASC";

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
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long AKISMETENTRYID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.akismet.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.akismet.model.AkismetEntry"));

	public AkismetEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _akismetEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAkismetEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _akismetEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AkismetEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AkismetEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AkismetEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AkismetEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AkismetEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AkismetEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AkismetEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AkismetEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AkismetEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AkismetEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AkismetEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<AkismetEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AkismetEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AkismetEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<AkismetEntry, Object>>();
		Map<String, BiConsumer<AkismetEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<AkismetEntry, ?>>();

		attributeGetterFunctions.put(
			"akismetEntryId", AkismetEntry::getAkismetEntryId);
		attributeSetterBiConsumers.put(
			"akismetEntryId",
			(BiConsumer<AkismetEntry, Long>)AkismetEntry::setAkismetEntryId);
		attributeGetterFunctions.put(
			"modifiedDate", AkismetEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AkismetEntry, Date>)AkismetEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", AkismetEntry::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<AkismetEntry, Long>)AkismetEntry::setClassNameId);
		attributeGetterFunctions.put("classPK", AkismetEntry::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<AkismetEntry, Long>)AkismetEntry::setClassPK);
		attributeGetterFunctions.put("type", AkismetEntry::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<AkismetEntry, String>)AkismetEntry::setType);
		attributeGetterFunctions.put("permalink", AkismetEntry::getPermalink);
		attributeSetterBiConsumers.put(
			"permalink",
			(BiConsumer<AkismetEntry, String>)AkismetEntry::setPermalink);
		attributeGetterFunctions.put("referrer", AkismetEntry::getReferrer);
		attributeSetterBiConsumers.put(
			"referrer",
			(BiConsumer<AkismetEntry, String>)AkismetEntry::setReferrer);
		attributeGetterFunctions.put("userAgent", AkismetEntry::getUserAgent);
		attributeSetterBiConsumers.put(
			"userAgent",
			(BiConsumer<AkismetEntry, String>)AkismetEntry::setUserAgent);
		attributeGetterFunctions.put("userIP", AkismetEntry::getUserIP);
		attributeSetterBiConsumers.put(
			"userIP",
			(BiConsumer<AkismetEntry, String>)AkismetEntry::setUserIP);
		attributeGetterFunctions.put("userURL", AkismetEntry::getUserURL);
		attributeSetterBiConsumers.put(
			"userURL",
			(BiConsumer<AkismetEntry, String>)AkismetEntry::setUserURL);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getAkismetEntryId() {
		return _akismetEntryId;
	}

	@Override
	public void setAkismetEntryId(long akismetEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_akismetEntryId = akismetEntryId;
	}

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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalModifiedDate() {
		return getColumnOriginalValue("modifiedDate");
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
	}

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

	@Override
	public String getPermalink() {
		if (_permalink == null) {
			return "";
		}
		else {
			return _permalink;
		}
	}

	@Override
	public void setPermalink(String permalink) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_permalink = permalink;
	}

	@Override
	public String getReferrer() {
		if (_referrer == null) {
			return "";
		}
		else {
			return _referrer;
		}
	}

	@Override
	public void setReferrer(String referrer) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_referrer = referrer;
	}

	@Override
	public String getUserAgent() {
		if (_userAgent == null) {
			return "";
		}
		else {
			return _userAgent;
		}
	}

	@Override
	public void setUserAgent(String userAgent) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userAgent = userAgent;
	}

	@Override
	public String getUserIP() {
		if (_userIP == null) {
			return "";
		}
		else {
			return _userIP;
		}
	}

	@Override
	public void setUserIP(String userIP) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userIP = userIP;
	}

	@Override
	public String getUserURL() {
		if (_userURL == null) {
			return "";
		}
		else {
			return _userURL;
		}
	}

	@Override
	public void setUserURL(String userURL) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userURL = userURL;
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
			0, AkismetEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AkismetEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AkismetEntry>
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
		AkismetEntryImpl akismetEntryImpl = new AkismetEntryImpl();

		akismetEntryImpl.setAkismetEntryId(getAkismetEntryId());
		akismetEntryImpl.setModifiedDate(getModifiedDate());
		akismetEntryImpl.setClassNameId(getClassNameId());
		akismetEntryImpl.setClassPK(getClassPK());
		akismetEntryImpl.setType(getType());
		akismetEntryImpl.setPermalink(getPermalink());
		akismetEntryImpl.setReferrer(getReferrer());
		akismetEntryImpl.setUserAgent(getUserAgent());
		akismetEntryImpl.setUserIP(getUserIP());
		akismetEntryImpl.setUserURL(getUserURL());

		akismetEntryImpl.resetOriginalValues();

		return akismetEntryImpl;
	}

	@Override
	public AkismetEntry cloneWithOriginalValues() {
		AkismetEntryImpl akismetEntryImpl = new AkismetEntryImpl();

		akismetEntryImpl.setAkismetEntryId(
			this.<Long>getColumnOriginalValue("akismetEntryId"));
		akismetEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		akismetEntryImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		akismetEntryImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		akismetEntryImpl.setType(this.<String>getColumnOriginalValue("type_"));
		akismetEntryImpl.setPermalink(
			this.<String>getColumnOriginalValue("permalink"));
		akismetEntryImpl.setReferrer(
			this.<String>getColumnOriginalValue("referrer"));
		akismetEntryImpl.setUserAgent(
			this.<String>getColumnOriginalValue("userAgent"));
		akismetEntryImpl.setUserIP(
			this.<String>getColumnOriginalValue("userIP"));
		akismetEntryImpl.setUserURL(
			this.<String>getColumnOriginalValue("userURL"));

		return akismetEntryImpl;
	}

	@Override
	public int compareTo(AkismetEntry akismetEntry) {
		long primaryKey = akismetEntry.getPrimaryKey();

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

		if (!(object instanceof AkismetEntry)) {
			return false;
		}

		AkismetEntry akismetEntry = (AkismetEntry)object;

		long primaryKey = akismetEntry.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<AkismetEntry> toCacheModel() {
		AkismetEntryCacheModel akismetEntryCacheModel =
			new AkismetEntryCacheModel();

		akismetEntryCacheModel.akismetEntryId = getAkismetEntryId();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			akismetEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			akismetEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		akismetEntryCacheModel.classNameId = getClassNameId();

		akismetEntryCacheModel.classPK = getClassPK();

		akismetEntryCacheModel.type = getType();

		String type = akismetEntryCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			akismetEntryCacheModel.type = null;
		}

		akismetEntryCacheModel.permalink = getPermalink();

		String permalink = akismetEntryCacheModel.permalink;

		if ((permalink != null) && (permalink.length() == 0)) {
			akismetEntryCacheModel.permalink = null;
		}

		akismetEntryCacheModel.referrer = getReferrer();

		String referrer = akismetEntryCacheModel.referrer;

		if ((referrer != null) && (referrer.length() == 0)) {
			akismetEntryCacheModel.referrer = null;
		}

		akismetEntryCacheModel.userAgent = getUserAgent();

		String userAgent = akismetEntryCacheModel.userAgent;

		if ((userAgent != null) && (userAgent.length() == 0)) {
			akismetEntryCacheModel.userAgent = null;
		}

		akismetEntryCacheModel.userIP = getUserIP();

		String userIP = akismetEntryCacheModel.userIP;

		if ((userIP != null) && (userIP.length() == 0)) {
			akismetEntryCacheModel.userIP = null;
		}

		akismetEntryCacheModel.userURL = getUserURL();

		String userURL = akismetEntryCacheModel.userURL;

		if ((userURL != null) && (userURL.length() == 0)) {
			akismetEntryCacheModel.userURL = null;
		}

		return akismetEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AkismetEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AkismetEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AkismetEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((AkismetEntry)this);

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
		Map<String, Function<AkismetEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AkismetEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AkismetEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((AkismetEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AkismetEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					AkismetEntry.class, ModelWrapper.class);

	}

	private long _akismetEntryId;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _type;
	private String _permalink;
	private String _referrer;
	private String _userAgent;
	private String _userIP;
	private String _userURL;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<AkismetEntry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AkismetEntry)this);
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

		_columnOriginalValues.put("akismetEntryId", _akismetEntryId);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("permalink", _permalink);
		_columnOriginalValues.put("referrer", _referrer);
		_columnOriginalValues.put("userAgent", _userAgent);
		_columnOriginalValues.put("userIP", _userIP);
		_columnOriginalValues.put("userURL", _userURL);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

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

		columnBitmasks.put("akismetEntryId", 1L);

		columnBitmasks.put("modifiedDate", 2L);

		columnBitmasks.put("classNameId", 4L);

		columnBitmasks.put("classPK", 8L);

		columnBitmasks.put("type_", 16L);

		columnBitmasks.put("permalink", 32L);

		columnBitmasks.put("referrer", 64L);

		columnBitmasks.put("userAgent", 128L);

		columnBitmasks.put("userIP", 256L);

		columnBitmasks.put("userURL", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AkismetEntry _escapedModel;

}