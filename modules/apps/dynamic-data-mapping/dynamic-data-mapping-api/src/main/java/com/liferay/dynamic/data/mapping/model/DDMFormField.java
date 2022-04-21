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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * @author Pablo Carvalho
 * @author Marcellus Tavares
 */
public class DDMFormField implements Serializable {

	public DDMFormField() {
		_ddmFormFieldRules = new ArrayList<>();
		_nestedDDMFormFields = new ArrayList<>();
		_properties = new LinkedHashMap<>();
	}

	public DDMFormField(DDMFormField ddmFormField) {
		_properties = new LinkedHashMap<>(ddmFormField._properties);

		setDDMFormFieldOptions(
			new DDMFormFieldOptions(ddmFormField.getDDMFormFieldOptions()));

		_ddmFormFieldRules = new ArrayList<>(
			ddmFormField._ddmFormFieldRules.size());

		for (DDMFormFieldRule ddmFormFieldRule :
				ddmFormField._ddmFormFieldRules) {

			addDDMFormFieldRule(new DDMFormFieldRule(ddmFormFieldRule));
		}

		DDMFormFieldValidation ddmFormFieldValidation =
			ddmFormField.getDDMFormFieldValidation();

		if (ddmFormFieldValidation != null) {
			setDDMFormFieldValidation(
				new DDMFormFieldValidation(ddmFormFieldValidation));
		}

		setFieldReference(ddmFormField.getFieldReference());
		setLabel(new LocalizedValue(ddmFormField.getLabel()));
		setPredefinedValue(
			new LocalizedValue(ddmFormField.getPredefinedValue()));
		setStyle(new LocalizedValue(ddmFormField.getStyle()));
		setTip(new LocalizedValue(ddmFormField.getTip()));

		_nestedDDMFormFields = new ArrayList<>(
			ddmFormField._nestedDDMFormFields.size());

		for (DDMFormField nestedDDMFormField :
				ddmFormField._nestedDDMFormFields) {

			addNestedDDMFormField(new DDMFormField(nestedDDMFormField));
		}
	}

	public DDMFormField(String name, String type) {
		_ddmFormFieldRules = new ArrayList<>();
		_nestedDDMFormFields = new ArrayList<>();
		_properties = new LinkedHashMap<>();

		setName(name);
		setType(type);

		Locale locale = LocaleUtil.getDefault();

		setDDMFormFieldOptions(new DDMFormFieldOptions(locale));

		setFieldReference(name);

		setLabel(new LocalizedValue(locale));
		setPredefinedValue(new LocalizedValue(locale));
		setStyle(new LocalizedValue(locale));
		setTip(new LocalizedValue(locale));
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void addDDMFormFieldRule(DDMFormFieldRule ddmFormFieldRule) {
		_ddmFormFieldRules.add(ddmFormFieldRule);
	}

	public void addNestedDDMFormField(DDMFormField nestedDDMFormField) {
		nestedDDMFormField.setDDMForm(_ddmForm);

		_nestedDDMFormFields.add(nestedDDMFormField);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFormField)) {
			return false;
		}

		DDMFormField ddmFormField = (DDMFormField)object;

		if (Objects.equals(_properties, ddmFormField._properties) &&
			Objects.equals(
				_nestedDDMFormFields, ddmFormField._nestedDDMFormFields)) {

			return true;
		}

		return false;
	}

	public String getDataSourceType() {
		Object propertyDataSourceType = _properties.get("dataSourceType");

		if (propertyDataSourceType == null) {
			return _DATA_SOURCE_TYPE_MANUAL;
		}

		String dataSourceType = StringPool.BLANK;

		if (propertyDataSourceType instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray)propertyDataSourceType;

			return GetterUtil.getString(
				jsonArray.get(0), _DATA_SOURCE_TYPE_MANUAL);
		}
		else if (propertyDataSourceType instanceof String) {
			dataSourceType = (String)propertyDataSourceType;

			if (dataSourceType.startsWith(StringPool.OPEN_BRACKET) &&
				dataSourceType.endsWith(StringPool.CLOSE_BRACKET)) {

				try {
					JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
						dataSourceType);

					return GetterUtil.getString(
						jsonArray.get(0), _DATA_SOURCE_TYPE_MANUAL);
				}
				catch (JSONException jsonException) {
					if (_log.isDebugEnabled()) {
						_log.debug(jsonException);
					}

					return dataSourceType;
				}
			}
		}

		return dataSourceType;
	}

	public String getDataType() {
		return MapUtil.getString(_properties, "dataType");
	}

	public DDMForm getDDMForm() {
		return _ddmForm;
	}

	public DDMFormFieldOptions getDDMFormFieldOptions() {
		DDMFormFieldOptions ddmFormFieldOptions =
			(DDMFormFieldOptions)_properties.get("options");

		String dataSourceType = getDataSourceType();

		if ((ddmFormFieldOptions != null) &&
			Validator.isNotNull(dataSourceType) &&
			!dataSourceType.equals(_DATA_SOURCE_TYPE_MANUAL)) {

			Locale defaultLocale = ddmFormFieldOptions.getDefaultLocale();

			ddmFormFieldOptions = new DDMFormFieldOptions();

			ddmFormFieldOptions.setDefaultLocale(defaultLocale);
		}

		return ddmFormFieldOptions;
	}

	public DDMFormFieldValidation getDDMFormFieldValidation() {
		Object value = _properties.get("validation");

		if (value instanceof DDMFormFieldValidation) {
			return (DDMFormFieldValidation)value;
		}

		return null;
	}

	public DDMFormLayout getDDMFormLayout() {
		return _ddmFormLayout;
	}

	public String getFieldNamespace() {
		return MapUtil.getString(_properties, "fieldNamespace");
	}

	public String getFieldReference() {
		return MapUtil.getString(_properties, "fieldReference");
	}

	public String getIndexType() {
		return MapUtil.getString(_properties, "indexType");
	}

	public LocalizedValue getLabel() {
		return (LocalizedValue)_properties.get("label");
	}

	public String getName() {
		return MapUtil.getString(_properties, "name");
	}

	public List<DDMFormField> getNestedDDMFormFields() {
		return _nestedDDMFormFields;
	}

	public Map<String, DDMFormField> getNestedDDMFormFieldsMap() {
		Map<String, DDMFormField> nestedDDMFormFieldsMap =
			new LinkedHashMap<>();

		for (DDMFormField nestedDDMFormField : _nestedDDMFormFields) {
			nestedDDMFormFieldsMap.put(
				nestedDDMFormField.getName(), nestedDDMFormField);

			nestedDDMFormFieldsMap.putAll(
				nestedDDMFormField.getNestedDDMFormFieldsMap());
		}

		return nestedDDMFormFieldsMap;
	}

	public Map<String, DDMFormField> getNestedDDMFormFieldsReferencesMap() {
		Map<String, DDMFormField> nestedDDMFormFieldsReferencesMap =
			new LinkedHashMap<>();

		for (DDMFormField nestedDDMFormField : _nestedDDMFormFields) {
			nestedDDMFormFieldsReferencesMap.put(
				nestedDDMFormField.getFieldReference(), nestedDDMFormField);

			nestedDDMFormFieldsReferencesMap.putAll(
				nestedDDMFormField.getNestedDDMFormFieldsReferencesMap());
		}

		return nestedDDMFormFieldsReferencesMap;
	}

	public Map<String, DDMFormField> getNontransientNestedDDMFormFieldsMap() {
		Map<String, DDMFormField> nestedDDMFormFieldsMap =
			new LinkedHashMap<>();

		for (DDMFormField nestedDDMFormField : _nestedDDMFormFields) {
			if (!nestedDDMFormField.isTransient()) {
				nestedDDMFormFieldsMap.put(
					nestedDDMFormField.getName(), nestedDDMFormField);
			}

			nestedDDMFormFieldsMap.putAll(
				nestedDDMFormField.getNontransientNestedDDMFormFieldsMap());
		}

		return nestedDDMFormFieldsMap;
	}

	public Map<String, DDMFormField>
		getNontransientNestedDDMFormFieldsReferencesMap() {

		Map<String, DDMFormField> nestedDDMFormFieldsReferencesMap =
			new LinkedHashMap<>();

		for (DDMFormField nestedDDMFormField : _nestedDDMFormFields) {
			if (!nestedDDMFormField.isTransient()) {
				nestedDDMFormFieldsReferencesMap.put(
					nestedDDMFormField.getFieldReference(), nestedDDMFormField);
			}

			nestedDDMFormFieldsReferencesMap.putAll(
				nestedDDMFormField.
					getNontransientNestedDDMFormFieldsReferencesMap());
		}

		return nestedDDMFormFieldsReferencesMap;
	}

	public LocalizedValue getPredefinedValue() {
		return (LocalizedValue)_properties.get("predefinedValue");
	}

	public Map<String, Object> getProperties() {
		return _properties;
	}

	public Object getProperty(String name) {
		return _properties.get(name);
	}

	public LocalizedValue getRequiredErrorMessage() {
		return (LocalizedValue)_properties.get("requiredErrorMessage");
	}

	public LocalizedValue getStyle() {
		return (LocalizedValue)_properties.get("style");
	}

	public LocalizedValue getTip() {
		return (LocalizedValue)_properties.get("tip");
	}

	public String getType() {
		return MapUtil.getString(_properties, "type");
	}

	public String getVisibilityExpression() {
		return MapUtil.getString(_properties, "visibilityExpression");
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _properties);

		return HashUtil.hash(hash, _nestedDDMFormFields);
	}

	public boolean hasProperty(String propertyKey) {
		if (_properties.containsKey(propertyKey)) {
			return true;
		}

		return false;
	}

	public boolean isLocalizable() {
		return MapUtil.getBoolean(_properties, "localizable");
	}

	public boolean isMultiple() {
		return MapUtil.getBoolean(_properties, "multiple");
	}

	public boolean isReadOnly() {
		return MapUtil.getBoolean(_properties, "readOnly");
	}

	public boolean isRepeatable() {
		return MapUtil.getBoolean(_properties, "repeatable");
	}

	public boolean isRequired() {
		return MapUtil.getBoolean(_properties, "required");
	}

	public boolean isShowLabel() {
		return MapUtil.getBoolean(_properties, "showLabel", true);
	}

	/**
	 * This method returns <code>true</code> if the DDMFormField is not supposed
	 * to hold value/data, i.e. its "dataType" property is blank or
	 * <code>null</code>. Transient fields can be considered structural fields
	 * like Liferay's native separator or fieldset fields.
	 *
	 * @return boolean
	 * @review
	 */
	public boolean isTransient() {
		if (Validator.isNull(getDataType())) {
			return true;
		}

		return false;
	}

	public boolean isVisualProperty() {
		return MapUtil.getBoolean(_properties, "visualProperty");
	}

	public void removeProperty(String propertyKey) {
		_properties.remove(propertyKey);
	}

	public void setDataType(String dataType) {
		_properties.put("dataType", dataType);
	}

	public void setDDMForm(DDMForm ddmForm) {
		for (DDMFormField nestedDDMFormField : _nestedDDMFormFields) {
			nestedDDMFormField.setDDMForm(ddmForm);
		}

		_ddmForm = ddmForm;
	}

	public void setDDMFormFieldOptions(
		DDMFormFieldOptions ddmFormFieldOptions) {

		_properties.put("options", ddmFormFieldOptions);
	}

	public void setDDMFormFieldValidation(
		DDMFormFieldValidation ddmFormFieldValidation) {

		_properties.put("validation", ddmFormFieldValidation);
	}

	public void setDDMFormLayout(DDMFormLayout ddmFormLayout) {
		_ddmFormLayout = ddmFormLayout;
	}

	public void setFieldNamespace(String fieldNamespace) {
		_properties.put("fieldNamespace", fieldNamespace);
	}

	public void setFieldReference(String fieldReference) {
		_properties.put("fieldReference", fieldReference);
	}

	public void setIndexType(String indexType) {
		_properties.put("indexType", indexType);
	}

	public void setLabel(LocalizedValue label) {
		_properties.put("label", label);
	}

	public void setLocalizable(boolean localizable) {
		_properties.put("localizable", localizable);
	}

	public void setMultiple(boolean multiple) {
		_properties.put("multiple", multiple);
	}

	public void setName(String name) {
		_properties.put("name", name);
	}

	public void setNestedDDMFormFields(List<DDMFormField> nestedDDMFormFields) {
		_nestedDDMFormFields = nestedDDMFormFields;
	}

	public void setPredefinedValue(LocalizedValue predefinedValue) {
		_properties.put("predefinedValue", predefinedValue);
	}

	public void setProperty(String name, Object value) {
		_properties.put(name, value);
	}

	public void setReadOnly(boolean readOnly) {
		_properties.put("readOnly", readOnly);
	}

	public void setRepeatable(boolean repeatable) {
		_properties.put("repeatable", repeatable);
	}

	public void setRequired(boolean required) {
		_properties.put("required", required);
	}

	public void setRequiredErrorMessage(LocalizedValue requiredErrorMessage) {
		_properties.put("requiredErrorMessage", requiredErrorMessage);
	}

	public void setShowLabel(boolean showLabel) {
		_properties.put("showLabel", showLabel);
	}

	public void setStyle(LocalizedValue style) {
		_properties.put("style", style);
	}

	public void setTip(LocalizedValue tip) {
		_properties.put("tip", tip);
	}

	public void setType(String type) {
		_properties.put("type", type);
	}

	public void setVisibilityExpression(String visibilityExpression) {
		_properties.put("visibilityExpression", visibilityExpression);
	}

	public void setVisualProperty(boolean visualProperty) {
		_properties.put("visualProperty", visualProperty);
	}

	private static final String _DATA_SOURCE_TYPE_MANUAL = "manual";

	private static final Log _log = LogFactoryUtil.getLog(DDMFormField.class);

	private DDMForm _ddmForm;
	private final List<DDMFormFieldRule> _ddmFormFieldRules;
	private DDMFormLayout _ddmFormLayout;
	private List<DDMFormField> _nestedDDMFormFields;
	private final Map<String, Object> _properties;

}