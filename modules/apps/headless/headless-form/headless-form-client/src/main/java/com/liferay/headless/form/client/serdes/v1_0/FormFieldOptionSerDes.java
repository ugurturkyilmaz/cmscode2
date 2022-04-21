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

package com.liferay.headless.form.client.serdes.v1_0;

import com.liferay.headless.form.client.dto.v1_0.FormFieldOption;
import com.liferay.headless.form.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class FormFieldOptionSerDes {

	public static FormFieldOption toDTO(String json) {
		FormFieldOptionJSONParser formFieldOptionJSONParser =
			new FormFieldOptionJSONParser();

		return formFieldOptionJSONParser.parseToDTO(json);
	}

	public static FormFieldOption[] toDTOs(String json) {
		FormFieldOptionJSONParser formFieldOptionJSONParser =
			new FormFieldOptionJSONParser();

		return formFieldOptionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(FormFieldOption formFieldOption) {
		if (formFieldOption == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (formFieldOption.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(formFieldOption.getId());
		}

		if (formFieldOption.getLabel() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"label\": ");

			sb.append("\"");

			sb.append(_escape(formFieldOption.getLabel()));

			sb.append("\"");
		}

		if (formFieldOption.getLabel_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"label_i18n\": ");

			sb.append(_toJSON(formFieldOption.getLabel_i18n()));
		}

		if (formFieldOption.getValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value\": ");

			sb.append("\"");

			sb.append(_escape(formFieldOption.getValue()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FormFieldOptionJSONParser formFieldOptionJSONParser =
			new FormFieldOptionJSONParser();

		return formFieldOptionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(FormFieldOption formFieldOption) {
		if (formFieldOption == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (formFieldOption.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(formFieldOption.getId()));
		}

		if (formFieldOption.getLabel() == null) {
			map.put("label", null);
		}
		else {
			map.put("label", String.valueOf(formFieldOption.getLabel()));
		}

		if (formFieldOption.getLabel_i18n() == null) {
			map.put("label_i18n", null);
		}
		else {
			map.put(
				"label_i18n", String.valueOf(formFieldOption.getLabel_i18n()));
		}

		if (formFieldOption.getValue() == null) {
			map.put("value", null);
		}
		else {
			map.put("value", String.valueOf(formFieldOption.getValue()));
		}

		return map;
	}

	public static class FormFieldOptionJSONParser
		extends BaseJSONParser<FormFieldOption> {

		@Override
		protected FormFieldOption createDTO() {
			return new FormFieldOption();
		}

		@Override
		protected FormFieldOption[] createDTOArray(int size) {
			return new FormFieldOption[size];
		}

		@Override
		protected void setField(
			FormFieldOption formFieldOption, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					formFieldOption.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "label")) {
				if (jsonParserFieldValue != null) {
					formFieldOption.setLabel((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "label_i18n")) {
				if (jsonParserFieldValue != null) {
					formFieldOption.setLabel_i18n(
						(Map)FormFieldOptionSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				if (jsonParserFieldValue != null) {
					formFieldOption.setValue((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}