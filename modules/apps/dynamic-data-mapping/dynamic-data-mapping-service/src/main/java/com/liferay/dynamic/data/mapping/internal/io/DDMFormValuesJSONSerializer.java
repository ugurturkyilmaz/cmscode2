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

package com.liferay.dynamic.data.mapping.internal.io;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueJSONSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "ddm.form.values.serializer.type=json",
	service = DDMFormValuesSerializer.class
)
public class DDMFormValuesJSONSerializer implements DDMFormValuesSerializer {

	@Override
	public DDMFormValuesSerializerSerializeResponse serialize(
		DDMFormValuesSerializerSerializeRequest
			ddmFormValuesSerializerSerializeRequest) {

		DDMFormValues ddmFormValues =
			ddmFormValuesSerializerSerializeRequest.getDDMFormValues();

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		addAvailableLanguageIds(
			jsonObject, ddmFormValues.getAvailableLocales());
		addDefaultLanguageId(jsonObject, ddmFormValues.getDefaultLocale());

		DDMForm ddmForm = ddmFormValues.getDDMForm();

		_addFieldValues(
			jsonObject, ddmForm.getDDMFormFieldsMap(true),
			ddmFormValues.getDDMFormFieldValues());

		DDMFormValuesSerializerSerializeResponse.Builder builder =
			DDMFormValuesSerializerSerializeResponse.Builder.newBuilder(
				jsonObject.toString());

		return builder.build();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, DDMFormFieldValueJSONSerializer.class,
			"ddm.form.field.type.name");
	}

	protected void addAvailableLanguageIds(
		JSONObject jsonObject, Set<Locale> availableLocales) {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (Locale availableLocale : availableLocales) {
			jsonArray.put(LocaleUtil.toLanguageId(availableLocale));
		}

		jsonObject.put("availableLanguageIds", jsonArray);
	}

	protected void addDefaultLanguageId(
		JSONObject jsonObject, Locale defaultLocale) {

		jsonObject.put(
			"defaultLanguageId", LocaleUtil.toLanguageId(defaultLocale));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	protected JSONObject toJSONObject(
		Map<String, DDMFormField> ddmFormFieldsMap,
		DDMFormFieldValue ddmFormFieldValue) {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put(
			"fieldReference", ddmFormFieldValue.getFieldReference()
		).put(
			"instanceId", ddmFormFieldValue.getInstanceId()
		).put(
			"name", ddmFormFieldValue.getName()
		);

		_addNestedFieldValues(
			jsonObject, ddmFormFieldsMap,
			ddmFormFieldValue.getNestedDDMFormFieldValues());

		_addValue(
			jsonObject, ddmFormFieldsMap.get(ddmFormFieldValue.getName()),
			ddmFormFieldValue);

		return jsonObject;
	}

	protected JSONObject toJSONObject(Value value) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		for (Locale availableLocale : value.getAvailableLocales()) {
			jsonObject.put(
				LocaleUtil.toLanguageId(availableLocale),
				value.getString(availableLocale));
		}

		return jsonObject;
	}

	private void _addFieldValues(
		JSONObject jsonObject, Map<String, DDMFormField> ddmFormFieldsMap,
		List<DDMFormFieldValue> ddmFormFieldValues) {

		jsonObject.put(
			"fieldValues", _toJSONArray(ddmFormFieldsMap, ddmFormFieldValues));
	}

	private void _addNestedFieldValues(
		JSONObject jsonObject, Map<String, DDMFormField> ddmFormFieldsMap,
		List<DDMFormFieldValue> nestedDDMFormFieldValues) {

		if (nestedDDMFormFieldValues.isEmpty()) {
			return;
		}

		jsonObject.put(
			"nestedFieldValues",
			_toJSONArray(ddmFormFieldsMap, nestedDDMFormFieldValues));
	}

	private void _addValue(
		JSONObject jsonObject, DDMFormField ddmFormField,
		DDMFormFieldValue ddmFormFieldValue) {

		Value value = ddmFormFieldValue.getValue();

		if (value == null) {
			return;
		}

		DDMFormFieldValueJSONSerializer ddmFormFieldValueJSONSerializer =
			_getDDMFormFieldValueJSONSerializer(ddmFormField);

		if (ddmFormFieldValueJSONSerializer != null) {
			jsonObject.put(
				"value",
				ddmFormFieldValueJSONSerializer.serialize(ddmFormField, value));
		}
		else if (value.isLocalized()) {
			jsonObject.put("value", toJSONObject(value));
		}
		else {
			jsonObject.put("value", value.getString(LocaleUtil.ROOT));
		}
	}

	private DDMFormFieldValueJSONSerializer _getDDMFormFieldValueJSONSerializer(
		DDMFormField ddmFormField) {

		if (ddmFormField == null) {
			return null;
		}

		return _serviceTrackerMap.getService(ddmFormField.getType());
	}

	private JSONArray _toJSONArray(
		Map<String, DDMFormField> ddmFormFieldsMap,
		List<DDMFormFieldValue> ddmFormFieldValues) {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (DDMFormFieldValue ddmFormFieldValue : ddmFormFieldValues) {
			jsonArray.put(toJSONObject(ddmFormFieldsMap, ddmFormFieldValue));
		}

		return jsonArray;
	}

	private JSONFactory _jsonFactory;
	private ServiceTrackerMap<String, DDMFormFieldValueJSONSerializer>
		_serviceTrackerMap;

}