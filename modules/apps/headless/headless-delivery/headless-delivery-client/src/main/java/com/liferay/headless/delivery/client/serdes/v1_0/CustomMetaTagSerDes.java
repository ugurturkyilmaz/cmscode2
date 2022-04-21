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

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.CustomMetaTag;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

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
public class CustomMetaTagSerDes {

	public static CustomMetaTag toDTO(String json) {
		CustomMetaTagJSONParser customMetaTagJSONParser =
			new CustomMetaTagJSONParser();

		return customMetaTagJSONParser.parseToDTO(json);
	}

	public static CustomMetaTag[] toDTOs(String json) {
		CustomMetaTagJSONParser customMetaTagJSONParser =
			new CustomMetaTagJSONParser();

		return customMetaTagJSONParser.parseToDTOs(json);
	}

	public static String toJSON(CustomMetaTag customMetaTag) {
		if (customMetaTag == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (customMetaTag.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(customMetaTag.getKey()));

			sb.append("\"");
		}

		if (customMetaTag.getValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value\": ");

			sb.append("\"");

			sb.append(_escape(customMetaTag.getValue()));

			sb.append("\"");
		}

		if (customMetaTag.getValue_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value_i18n\": ");

			sb.append(_toJSON(customMetaTag.getValue_i18n()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CustomMetaTagJSONParser customMetaTagJSONParser =
			new CustomMetaTagJSONParser();

		return customMetaTagJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(CustomMetaTag customMetaTag) {
		if (customMetaTag == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (customMetaTag.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(customMetaTag.getKey()));
		}

		if (customMetaTag.getValue() == null) {
			map.put("value", null);
		}
		else {
			map.put("value", String.valueOf(customMetaTag.getValue()));
		}

		if (customMetaTag.getValue_i18n() == null) {
			map.put("value_i18n", null);
		}
		else {
			map.put(
				"value_i18n", String.valueOf(customMetaTag.getValue_i18n()));
		}

		return map;
	}

	public static class CustomMetaTagJSONParser
		extends BaseJSONParser<CustomMetaTag> {

		@Override
		protected CustomMetaTag createDTO() {
			return new CustomMetaTag();
		}

		@Override
		protected CustomMetaTag[] createDTOArray(int size) {
			return new CustomMetaTag[size];
		}

		@Override
		protected void setField(
			CustomMetaTag customMetaTag, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					customMetaTag.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				if (jsonParserFieldValue != null) {
					customMetaTag.setValue((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value_i18n")) {
				if (jsonParserFieldValue != null) {
					customMetaTag.setValue_i18n(
						(Map)CustomMetaTagSerDes.toMap(
							(String)jsonParserFieldValue));
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