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

package com.liferay.dynamic.data.mapping.util;

import com.liferay.dynamic.data.mapping.form.field.type.constants.DDMFormFieldTypeConstants;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReport;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Stream;

/**
 * @author Bruno Farache
 */
public class DDMFormReportDataUtil {

	public static JSONArray getFieldsJSONArray(
			DDMFormInstanceReport ddmFormInstanceReport)
		throws PortalException {

		JSONArray fieldsJSONArray = JSONFactoryUtil.createJSONArray();

		if (ddmFormInstanceReport == null) {
			return fieldsJSONArray;
		}

		DDMFormInstance ddmFormInstance =
			ddmFormInstanceReport.getFormInstance();

		DDMForm ddmForm = ddmFormInstance.getDDMForm();

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		Set<String> set = ddmFormFieldsMap.keySet();

		Stream<String> stream = set.stream();

		stream.map(
			ddmFormFieldName -> ddmFormFieldsMap.get(ddmFormFieldName)
		).filter(
			ddmFormField -> !StringUtil.equals(
				ddmFormField.getType(), "fieldset")
		).forEach(
			ddmFormField -> fieldsJSONArray.put(
				JSONUtil.put(
					"columns",
					_getPropertyLabelsJSONObject(ddmFormField, "columns")
				).put(
					"label", _getLabel(ddmFormField)
				).put(
					"name", ddmFormField.getName()
				).put(
					"options",
					_getDDMFormFieldOptionLabelsJSONObject(
						ddmFormField.getDDMFormFieldOptions())
				).put(
					"rows", _getPropertyLabelsJSONObject(ddmFormField, "rows")
				).put(
					"type", ddmFormField.getType()
				))
		);

		return fieldsJSONArray;
	}

	public static JSONArray getFieldValuesJSONArray(
			List<DDMFormInstanceRecord> ddmFormInstanceRecords,
			String fieldName)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (DDMFormInstanceRecord ddmFormInstanceRecord :
				ddmFormInstanceRecords) {

			DDMFormValues ddmFormValues =
				ddmFormInstanceRecord.getDDMFormValues();

			Map<String, List<DDMFormFieldValue>> ddmFormFieldValuesMap =
				ddmFormValues.getDDMFormFieldValuesMap(true);

			List<DDMFormFieldValue> ddmFormFieldValues =
				ddmFormFieldValuesMap.get(fieldName);

			if (ddmFormFieldValues == null) {
				continue;
			}

			ddmFormFieldValues.forEach(
				ddmFormFieldValue -> {
					Value value = ddmFormFieldValue.getValue();

					jsonArray.put(value.getString(value.getDefaultLocale()));
				});
		}

		return jsonArray;
	}

	public static String getLastModifiedDate(
		DDMFormInstanceReport ddmFormInstanceReport, Locale locale,
		TimeZone timeZone) {

		if (ddmFormInstanceReport == null) {
			return StringPool.BLANK;
		}

		String languageKey = "the-last-entry-was-sent-on-x";

		Date modifiedDate = ddmFormInstanceReport.getModifiedDate();

		int daysBetween = DateUtil.getDaysBetween(
			new Date(modifiedDate.getTime()), new Date(), timeZone);

		if (daysBetween < 2) {
			languageKey = "the-last-entry-was-sent-x";
		}

		String relativeTimeDescription = StringUtil.removeSubstring(
			Time.getRelativeTimeDescription(modifiedDate, locale, timeZone),
			StringPool.PERIOD);

		if (daysBetween < 2) {
			relativeTimeDescription = StringUtil.toLowerCase(
				relativeTimeDescription);
		}

		return LanguageUtil.format(
			locale, languageKey, relativeTimeDescription, false);
	}

	public static int getTotalItems(DDMFormInstanceReport ddmFormInstanceReport)
		throws PortalException {

		if (ddmFormInstanceReport == null) {
			return 0;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			ddmFormInstanceReport.getData());

		return jsonObject.getInt("totalItems");
	}

	private static JSONObject _getDDMFormFieldOptionLabelsJSONObject(
		DDMFormFieldOptions ddmFormFieldOptions) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int index = 0;

		for (String optionValue : ddmFormFieldOptions.getOptionsValues()) {
			jsonObject.put(
				optionValue,
				JSONUtil.put(
					"index", index++
				).put(
					"value",
					_getValue(ddmFormFieldOptions.getOptionLabels(optionValue))
				));
		}

		return jsonObject;
	}

	private static String _getLabel(DDMFormField ddmFormField) {
		if (StringUtil.equals(
				ddmFormField.getType(),
				DDMFormFieldTypeConstants.SEARCH_LOCATION)) {

			JSONObject jsonObject = JSONUtil.put(
				"place", _getValue(ddmFormField.getLabel()));

			LocalizedValue visibleFields =
				(LocalizedValue)ddmFormField.getProperty("visibleFields");

			Stream.of(
				StringUtil.split(
					StringUtil.removeChars(
						GetterUtil.getString(
							visibleFields.getString(
								visibleFields.getDefaultLocale())),
						CharPool.CLOSE_BRACKET, CharPool.OPEN_BRACKET,
						CharPool.QUOTE))
			).map(
				String::trim
			).forEach(
				visibleField -> jsonObject.put(
					visibleField,
					LanguageUtil.get(
						ResourceBundleUtil.getModuleAndPortalResourceBundle(
							visibleFields.getDefaultLocale(),
							DDMFormReportDataUtil.class),
						visibleField))
			);

			return jsonObject.toString();
		}

		return _getValue(ddmFormField.getLabel());
	}

	private static JSONObject _getPropertyLabelsJSONObject(
		DDMFormField ddmFormField, String propertyName) {

		Object property = ddmFormField.getProperty(propertyName);

		if (property instanceof DDMFormFieldOptions) {
			DDMFormFieldOptions ddmFormFieldOptions =
				(DDMFormFieldOptions)property;

			return _getDDMFormFieldOptionLabelsJSONObject(ddmFormFieldOptions);
		}

		return JSONFactoryUtil.createJSONObject();
	}

	private static String _getValue(Value value) {
		return value.getString(value.getDefaultLocale());
	}

}