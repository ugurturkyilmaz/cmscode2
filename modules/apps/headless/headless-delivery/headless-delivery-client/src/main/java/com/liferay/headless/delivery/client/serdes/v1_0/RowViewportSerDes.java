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

import com.liferay.headless.delivery.client.dto.v1_0.RowViewport;
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
public class RowViewportSerDes {

	public static RowViewport toDTO(String json) {
		RowViewportJSONParser rowViewportJSONParser =
			new RowViewportJSONParser();

		return rowViewportJSONParser.parseToDTO(json);
	}

	public static RowViewport[] toDTOs(String json) {
		RowViewportJSONParser rowViewportJSONParser =
			new RowViewportJSONParser();

		return rowViewportJSONParser.parseToDTOs(json);
	}

	public static String toJSON(RowViewport rowViewport) {
		if (rowViewport == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (rowViewport.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(rowViewport.getId()));

			sb.append("\"");
		}

		if (rowViewport.getRowViewportDefinition() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"rowViewportDefinition\": ");

			sb.append(String.valueOf(rowViewport.getRowViewportDefinition()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		RowViewportJSONParser rowViewportJSONParser =
			new RowViewportJSONParser();

		return rowViewportJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(RowViewport rowViewport) {
		if (rowViewport == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (rowViewport.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(rowViewport.getId()));
		}

		if (rowViewport.getRowViewportDefinition() == null) {
			map.put("rowViewportDefinition", null);
		}
		else {
			map.put(
				"rowViewportDefinition",
				String.valueOf(rowViewport.getRowViewportDefinition()));
		}

		return map;
	}

	public static class RowViewportJSONParser
		extends BaseJSONParser<RowViewport> {

		@Override
		protected RowViewport createDTO() {
			return new RowViewport();
		}

		@Override
		protected RowViewport[] createDTOArray(int size) {
			return new RowViewport[size];
		}

		@Override
		protected void setField(
			RowViewport rowViewport, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					rowViewport.setId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "rowViewportDefinition")) {

				if (jsonParserFieldValue != null) {
					rowViewport.setRowViewportDefinition(
						RowViewportDefinitionSerDes.toDTO(
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