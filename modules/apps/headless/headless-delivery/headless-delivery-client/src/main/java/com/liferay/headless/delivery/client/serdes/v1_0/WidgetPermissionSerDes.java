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

import com.liferay.headless.delivery.client.dto.v1_0.WidgetPermission;
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
public class WidgetPermissionSerDes {

	public static WidgetPermission toDTO(String json) {
		WidgetPermissionJSONParser widgetPermissionJSONParser =
			new WidgetPermissionJSONParser();

		return widgetPermissionJSONParser.parseToDTO(json);
	}

	public static WidgetPermission[] toDTOs(String json) {
		WidgetPermissionJSONParser widgetPermissionJSONParser =
			new WidgetPermissionJSONParser();

		return widgetPermissionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(WidgetPermission widgetPermission) {
		if (widgetPermission == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (widgetPermission.getActionKeys() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actionKeys\": ");

			sb.append("[");

			for (int i = 0; i < widgetPermission.getActionKeys().length; i++) {
				sb.append("\"");

				sb.append(_escape(widgetPermission.getActionKeys()[i]));

				sb.append("\"");

				if ((i + 1) < widgetPermission.getActionKeys().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (widgetPermission.getRoleKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleKey\": ");

			sb.append("\"");

			sb.append(_escape(widgetPermission.getRoleKey()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		WidgetPermissionJSONParser widgetPermissionJSONParser =
			new WidgetPermissionJSONParser();

		return widgetPermissionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(WidgetPermission widgetPermission) {
		if (widgetPermission == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (widgetPermission.getActionKeys() == null) {
			map.put("actionKeys", null);
		}
		else {
			map.put(
				"actionKeys", String.valueOf(widgetPermission.getActionKeys()));
		}

		if (widgetPermission.getRoleKey() == null) {
			map.put("roleKey", null);
		}
		else {
			map.put("roleKey", String.valueOf(widgetPermission.getRoleKey()));
		}

		return map;
	}

	public static class WidgetPermissionJSONParser
		extends BaseJSONParser<WidgetPermission> {

		@Override
		protected WidgetPermission createDTO() {
			return new WidgetPermission();
		}

		@Override
		protected WidgetPermission[] createDTOArray(int size) {
			return new WidgetPermission[size];
		}

		@Override
		protected void setField(
			WidgetPermission widgetPermission, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actionKeys")) {
				if (jsonParserFieldValue != null) {
					widgetPermission.setActionKeys(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleKey")) {
				if (jsonParserFieldValue != null) {
					widgetPermission.setRoleKey((String)jsonParserFieldValue);
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