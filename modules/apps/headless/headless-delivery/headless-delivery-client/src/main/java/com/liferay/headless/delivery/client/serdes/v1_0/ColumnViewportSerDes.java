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

import com.liferay.headless.delivery.client.dto.v1_0.ColumnViewport;
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
public class ColumnViewportSerDes {

	public static ColumnViewport toDTO(String json) {
		ColumnViewportJSONParser columnViewportJSONParser =
			new ColumnViewportJSONParser();

		return columnViewportJSONParser.parseToDTO(json);
	}

	public static ColumnViewport[] toDTOs(String json) {
		ColumnViewportJSONParser columnViewportJSONParser =
			new ColumnViewportJSONParser();

		return columnViewportJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ColumnViewport columnViewport) {
		if (columnViewport == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (columnViewport.getColumnViewportDefinition() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"columnViewportDefinition\": ");

			sb.append(
				String.valueOf(columnViewport.getColumnViewportDefinition()));
		}

		if (columnViewport.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(columnViewport.getId()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ColumnViewportJSONParser columnViewportJSONParser =
			new ColumnViewportJSONParser();

		return columnViewportJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(ColumnViewport columnViewport) {
		if (columnViewport == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (columnViewport.getColumnViewportDefinition() == null) {
			map.put("columnViewportDefinition", null);
		}
		else {
			map.put(
				"columnViewportDefinition",
				String.valueOf(columnViewport.getColumnViewportDefinition()));
		}

		if (columnViewport.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(columnViewport.getId()));
		}

		return map;
	}

	public static class ColumnViewportJSONParser
		extends BaseJSONParser<ColumnViewport> {

		@Override
		protected ColumnViewport createDTO() {
			return new ColumnViewport();
		}

		@Override
		protected ColumnViewport[] createDTOArray(int size) {
			return new ColumnViewport[size];
		}

		@Override
		protected void setField(
			ColumnViewport columnViewport, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(
					jsonParserFieldName, "columnViewportDefinition")) {

				if (jsonParserFieldValue != null) {
					columnViewport.setColumnViewportDefinition(
						ColumnViewportDefinitionSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					columnViewport.setId((String)jsonParserFieldValue);
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