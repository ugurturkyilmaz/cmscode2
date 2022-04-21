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
import com.liferay.portal.kernel.json.JSONArray;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pablo Carvalho
 */
public class DDMForm implements Serializable {

	public DDMForm() {
		_availableLocales = new LinkedHashSet<>();
		_ddmFormFields = new ArrayList<>();
		_ddmFormRules = new ArrayList<>();
		_ddmFormSuccessPageSettings = new DDMFormSuccessPageSettings();
	}

	public DDMForm(DDMForm ddmForm) {
		_availableLocales = new LinkedHashSet<>(ddmForm._availableLocales);
		_defaultLocale = ddmForm._defaultLocale;
		_definitionSchemaVersion = ddmForm._definitionSchemaVersion;

		_ddmFormFields = new ArrayList<>(ddmForm._ddmFormFields.size());

		for (DDMFormField ddmFormField : ddmForm._ddmFormFields) {
			addDDMFormField(new DDMFormField(ddmFormField));
		}

		_ddmFormRules = new ArrayList<>(ddmForm._ddmFormRules.size());

		for (DDMFormRule ddmFormRule : ddmForm._ddmFormRules) {
			addDDMFormRule(new DDMFormRule(ddmFormRule));
		}

		_ddmFormSuccessPageSettings = new DDMFormSuccessPageSettings(
			ddmForm.getDDMFormSuccessPageSettings());
	}

	public void addAvailableLocale(Locale locale) {
		_availableLocales.add(locale);
	}

	public void addDDMFormField(DDMFormField ddmFormField) {
		ddmFormField.setDDMForm(this);

		_ddmFormFields.add(ddmFormField);
	}

	public void addDDMFormRule(DDMFormRule ddmFormRule) {
		_ddmFormRules.add(ddmFormRule);
	}

	public boolean allowInvalidAvailableLocalesForProperty() {
		return _allowInvalidAvailableLocalesForProperty;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMForm)) {
			return false;
		}

		DDMForm ddmForm = (DDMForm)object;

		if (Objects.equals(_availableLocales, ddmForm._availableLocales) &&
			Objects.equals(_defaultLocale, ddmForm._defaultLocale) &&
			Objects.equals(_ddmFormFields, ddmForm._ddmFormFields) &&
			Objects.equals(_ddmFormRules, ddmForm._ddmFormRules) &&
			Objects.equals(
				_ddmFormSuccessPageSettings,
				ddmForm._ddmFormSuccessPageSettings) &&
			Objects.equals(
				_definitionSchemaVersion, ddmForm._definitionSchemaVersion)) {

			return true;
		}

		return false;
	}

	public Set<Locale> getAvailableLocales() {
		return _availableLocales;
	}

	public List<DDMFormField> getDDMFormFields() {
		return _ddmFormFields;
	}

	public Map<String, DDMFormField> getDDMFormFieldsMap(
		boolean includeNestedDDMFormFields) {

		Map<String, DDMFormField> ddmFormFieldsMap = new LinkedHashMap<>();

		for (DDMFormField ddmFormField : _ddmFormFields) {
			ddmFormFieldsMap.put(ddmFormField.getName(), ddmFormField);

			if (includeNestedDDMFormFields) {
				ddmFormFieldsMap.putAll(
					ddmFormField.getNestedDDMFormFieldsMap());
			}
		}

		return ddmFormFieldsMap;
	}

	public Map<String, DDMFormField> getDDMFormFieldsReferencesMap(
		boolean includeNestedDDMFormFields) {

		Map<String, DDMFormField> ddmFormFieldsReferencesMap =
			new LinkedHashMap<>();

		for (DDMFormField ddmFormField : _ddmFormFields) {
			ddmFormFieldsReferencesMap.put(
				ddmFormField.getFieldReference(), ddmFormField);

			if (includeNestedDDMFormFields) {
				ddmFormFieldsReferencesMap.putAll(
					ddmFormField.getNestedDDMFormFieldsReferencesMap());
			}
		}

		return ddmFormFieldsReferencesMap;
	}

	public List<DDMFormRule> getDDMFormRules() {
		return _ddmFormRules;
	}

	public DDMFormSuccessPageSettings getDDMFormSuccessPageSettings() {
		return _ddmFormSuccessPageSettings;
	}

	public Locale getDefaultLocale() {
		return _defaultLocale;
	}

	public String getDefinitionSchemaVersion() {
		return _definitionSchemaVersion;
	}

	public Map<String, DDMFormField> getNontransientDDMFormFieldsMap(
		boolean includeNestedDDMFormFields) {

		Map<String, DDMFormField> ddmFormFieldsMap = new LinkedHashMap<>();

		for (DDMFormField ddmFormField : _ddmFormFields) {
			if (!ddmFormField.isTransient()) {
				ddmFormFieldsMap.put(ddmFormField.getName(), ddmFormField);
			}

			if (includeNestedDDMFormFields) {
				ddmFormFieldsMap.putAll(
					ddmFormField.getNontransientNestedDDMFormFieldsMap());
			}
		}

		return ddmFormFieldsMap;
	}

	public Map<String, DDMFormField> getNontransientDDMFormFieldsReferencesMap(
		boolean includeNestedDDMFormFields) {

		Map<String, DDMFormField> ddmFormFieldsReferencesMap =
			new LinkedHashMap<>();

		for (DDMFormField ddmFormField : _ddmFormFields) {
			if (!ddmFormField.isTransient()) {
				ddmFormFieldsReferencesMap.put(
					ddmFormField.getFieldReference(), ddmFormField);
			}

			if (includeNestedDDMFormFields) {
				ddmFormFieldsReferencesMap.putAll(
					ddmFormField.
						getNontransientNestedDDMFormFieldsReferencesMap());
			}
		}

		return ddmFormFieldsReferencesMap;
	}

	public JSONArray getObjectFieldsJSONArray() {
		return _objectFieldsJSONArray;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _availableLocales);

		hash = HashUtil.hash(hash, _defaultLocale);
		hash = HashUtil.hash(hash, _ddmFormFields);
		hash = HashUtil.hash(hash, _ddmFormRules);
		hash = HashUtil.hash(hash, _ddmFormSuccessPageSettings);

		return HashUtil.hash(hash, _definitionSchemaVersion);
	}

	public void setAllowInvalidAvailableLocalesForProperty(
		boolean allowInvalidAvailableLocalesForProperty) {

		_allowInvalidAvailableLocalesForProperty =
			allowInvalidAvailableLocalesForProperty;
	}

	public void setAvailableLocales(Set<Locale> availableLocales) {
		_availableLocales = availableLocales;
	}

	public void setDDMFormFields(List<DDMFormField> ddmFormFields) {
		for (DDMFormField ddmFormField : ddmFormFields) {
			ddmFormField.setDDMForm(this);
		}

		_ddmFormFields = ddmFormFields;
	}

	public void setDDMFormRules(List<DDMFormRule> ddmFormRules) {
		_ddmFormRules = ddmFormRules;
	}

	public void setDDMFormSuccessPageSettings(
		DDMFormSuccessPageSettings ddmFormSuccessPageSettings) {

		_ddmFormSuccessPageSettings = new DDMFormSuccessPageSettings(
			ddmFormSuccessPageSettings);
	}

	public void setDefaultLocale(Locale defaultLocale) {
		_defaultLocale = defaultLocale;
	}

	public void setDefinitionSchemaVersion(String definitionSchemaVersion) {
		_definitionSchemaVersion = definitionSchemaVersion;
	}

	public void setObjectFieldsJSONArray(JSONArray objectFieldsJSONArray) {
		_objectFieldsJSONArray = objectFieldsJSONArray;
	}

	private boolean _allowInvalidAvailableLocalesForProperty;
	private Set<Locale> _availableLocales;
	private List<DDMFormField> _ddmFormFields;
	private List<DDMFormRule> _ddmFormRules;
	private DDMFormSuccessPageSettings _ddmFormSuccessPageSettings;
	private Locale _defaultLocale;
	private String _definitionSchemaVersion;
	private JSONArray _objectFieldsJSONArray;

}