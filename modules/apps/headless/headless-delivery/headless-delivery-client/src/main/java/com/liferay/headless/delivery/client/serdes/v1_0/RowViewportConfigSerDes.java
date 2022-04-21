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

import com.liferay.headless.delivery.client.dto.v1_0.RowViewportConfig;
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
public class RowViewportConfigSerDes {

	public static RowViewportConfig toDTO(String json) {
		RowViewportConfigJSONParser rowViewportConfigJSONParser =
			new RowViewportConfigJSONParser();

		return rowViewportConfigJSONParser.parseToDTO(json);
	}

	public static RowViewportConfig[] toDTOs(String json) {
		RowViewportConfigJSONParser rowViewportConfigJSONParser =
			new RowViewportConfigJSONParser();

		return rowViewportConfigJSONParser.parseToDTOs(json);
	}

	public static String toJSON(RowViewportConfig rowViewportConfig) {
		if (rowViewportConfig == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (rowViewportConfig.getLandscapeMobile() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"landscapeMobile\": ");

			sb.append(String.valueOf(rowViewportConfig.getLandscapeMobile()));
		}

		if (rowViewportConfig.getPortraitMobile() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"portraitMobile\": ");

			sb.append(String.valueOf(rowViewportConfig.getPortraitMobile()));
		}

		if (rowViewportConfig.getTablet() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"tablet\": ");

			sb.append(String.valueOf(rowViewportConfig.getTablet()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		RowViewportConfigJSONParser rowViewportConfigJSONParser =
			new RowViewportConfigJSONParser();

		return rowViewportConfigJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		RowViewportConfig rowViewportConfig) {

		if (rowViewportConfig == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (rowViewportConfig.getLandscapeMobile() == null) {
			map.put("landscapeMobile", null);
		}
		else {
			map.put(
				"landscapeMobile",
				String.valueOf(rowViewportConfig.getLandscapeMobile()));
		}

		if (rowViewportConfig.getPortraitMobile() == null) {
			map.put("portraitMobile", null);
		}
		else {
			map.put(
				"portraitMobile",
				String.valueOf(rowViewportConfig.getPortraitMobile()));
		}

		if (rowViewportConfig.getTablet() == null) {
			map.put("tablet", null);
		}
		else {
			map.put("tablet", String.valueOf(rowViewportConfig.getTablet()));
		}

		return map;
	}

	public static class RowViewportConfigJSONParser
		extends BaseJSONParser<RowViewportConfig> {

		@Override
		protected RowViewportConfig createDTO() {
			return new RowViewportConfig();
		}

		@Override
		protected RowViewportConfig[] createDTOArray(int size) {
			return new RowViewportConfig[size];
		}

		@Override
		protected void setField(
			RowViewportConfig rowViewportConfig, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "landscapeMobile")) {
				if (jsonParserFieldValue != null) {
					rowViewportConfig.setLandscapeMobile(
						LandscapeMobileSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "portraitMobile")) {
				if (jsonParserFieldValue != null) {
					rowViewportConfig.setPortraitMobile(
						PortraitMobileSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "tablet")) {
				if (jsonParserFieldValue != null) {
					rowViewportConfig.setTablet(
						TabletSerDes.toDTO((String)jsonParserFieldValue));
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