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

package com.liferay.headless.commerce.delivery.cart.client.serdes.v1_0;

import com.liferay.headless.commerce.delivery.cart.client.dto.v1_0.Settings;
import com.liferay.headless.commerce.delivery.cart.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public class SettingsSerDes {

	public static Settings toDTO(String json) {
		SettingsJSONParser settingsJSONParser = new SettingsJSONParser();

		return settingsJSONParser.parseToDTO(json);
	}

	public static Settings[] toDTOs(String json) {
		SettingsJSONParser settingsJSONParser = new SettingsJSONParser();

		return settingsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Settings settings) {
		if (settings == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (settings.getAllowedQuantities() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"allowedQuantities\": ");

			sb.append("[");

			for (int i = 0; i < settings.getAllowedQuantities().length; i++) {
				sb.append(settings.getAllowedQuantities()[i]);

				if ((i + 1) < settings.getAllowedQuantities().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (settings.getMaxQuantity() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxQuantity\": ");

			sb.append(settings.getMaxQuantity());
		}

		if (settings.getMinQuantity() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"minQuantity\": ");

			sb.append(settings.getMinQuantity());
		}

		if (settings.getMultipleQuantity() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"multipleQuantity\": ");

			sb.append(settings.getMultipleQuantity());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SettingsJSONParser settingsJSONParser = new SettingsJSONParser();

		return settingsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Settings settings) {
		if (settings == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (settings.getAllowedQuantities() == null) {
			map.put("allowedQuantities", null);
		}
		else {
			map.put(
				"allowedQuantities",
				String.valueOf(settings.getAllowedQuantities()));
		}

		if (settings.getMaxQuantity() == null) {
			map.put("maxQuantity", null);
		}
		else {
			map.put("maxQuantity", String.valueOf(settings.getMaxQuantity()));
		}

		if (settings.getMinQuantity() == null) {
			map.put("minQuantity", null);
		}
		else {
			map.put("minQuantity", String.valueOf(settings.getMinQuantity()));
		}

		if (settings.getMultipleQuantity() == null) {
			map.put("multipleQuantity", null);
		}
		else {
			map.put(
				"multipleQuantity",
				String.valueOf(settings.getMultipleQuantity()));
		}

		return map;
	}

	public static class SettingsJSONParser extends BaseJSONParser<Settings> {

		@Override
		protected Settings createDTO() {
			return new Settings();
		}

		@Override
		protected Settings[] createDTOArray(int size) {
			return new Settings[size];
		}

		@Override
		protected void setField(
			Settings settings, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "allowedQuantities")) {
				if (jsonParserFieldValue != null) {
					settings.setAllowedQuantities(
						toIntegers((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "maxQuantity")) {
				if (jsonParserFieldValue != null) {
					settings.setMaxQuantity(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "minQuantity")) {
				if (jsonParserFieldValue != null) {
					settings.setMinQuantity(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "multipleQuantity")) {
				if (jsonParserFieldValue != null) {
					settings.setMultipleQuantity(
						Integer.valueOf((String)jsonParserFieldValue));
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