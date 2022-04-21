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

package com.liferay.headless.admin.content.client.serdes.v1_0;

import com.liferay.headless.admin.content.client.dto.v1_0.OpenGraphSettingsMapping;
import com.liferay.headless.admin.content.client.json.BaseJSONParser;

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
public class OpenGraphSettingsMappingSerDes {

	public static OpenGraphSettingsMapping toDTO(String json) {
		OpenGraphSettingsMappingJSONParser openGraphSettingsMappingJSONParser =
			new OpenGraphSettingsMappingJSONParser();

		return openGraphSettingsMappingJSONParser.parseToDTO(json);
	}

	public static OpenGraphSettingsMapping[] toDTOs(String json) {
		OpenGraphSettingsMappingJSONParser openGraphSettingsMappingJSONParser =
			new OpenGraphSettingsMappingJSONParser();

		return openGraphSettingsMappingJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		OpenGraphSettingsMapping openGraphSettingsMapping) {

		if (openGraphSettingsMapping == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (openGraphSettingsMapping.getDescriptionMappingFieldKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"descriptionMappingFieldKey\": ");

			sb.append("\"");

			sb.append(
				_escape(
					openGraphSettingsMapping.getDescriptionMappingFieldKey()));

			sb.append("\"");
		}

		if (openGraphSettingsMapping.getImageAltMappingFieldKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"imageAltMappingFieldKey\": ");

			sb.append("\"");

			sb.append(
				_escape(openGraphSettingsMapping.getImageAltMappingFieldKey()));

			sb.append("\"");
		}

		if (openGraphSettingsMapping.getImageMappingFieldKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"imageMappingFieldKey\": ");

			sb.append("\"");

			sb.append(
				_escape(openGraphSettingsMapping.getImageMappingFieldKey()));

			sb.append("\"");
		}

		if (openGraphSettingsMapping.getTitleMappingFieldKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"titleMappingFieldKey\": ");

			sb.append("\"");

			sb.append(
				_escape(openGraphSettingsMapping.getTitleMappingFieldKey()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		OpenGraphSettingsMappingJSONParser openGraphSettingsMappingJSONParser =
			new OpenGraphSettingsMappingJSONParser();

		return openGraphSettingsMappingJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		OpenGraphSettingsMapping openGraphSettingsMapping) {

		if (openGraphSettingsMapping == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (openGraphSettingsMapping.getDescriptionMappingFieldKey() == null) {
			map.put("descriptionMappingFieldKey", null);
		}
		else {
			map.put(
				"descriptionMappingFieldKey",
				String.valueOf(
					openGraphSettingsMapping.getDescriptionMappingFieldKey()));
		}

		if (openGraphSettingsMapping.getImageAltMappingFieldKey() == null) {
			map.put("imageAltMappingFieldKey", null);
		}
		else {
			map.put(
				"imageAltMappingFieldKey",
				String.valueOf(
					openGraphSettingsMapping.getImageAltMappingFieldKey()));
		}

		if (openGraphSettingsMapping.getImageMappingFieldKey() == null) {
			map.put("imageMappingFieldKey", null);
		}
		else {
			map.put(
				"imageMappingFieldKey",
				String.valueOf(
					openGraphSettingsMapping.getImageMappingFieldKey()));
		}

		if (openGraphSettingsMapping.getTitleMappingFieldKey() == null) {
			map.put("titleMappingFieldKey", null);
		}
		else {
			map.put(
				"titleMappingFieldKey",
				String.valueOf(
					openGraphSettingsMapping.getTitleMappingFieldKey()));
		}

		return map;
	}

	public static class OpenGraphSettingsMappingJSONParser
		extends BaseJSONParser<OpenGraphSettingsMapping> {

		@Override
		protected OpenGraphSettingsMapping createDTO() {
			return new OpenGraphSettingsMapping();
		}

		@Override
		protected OpenGraphSettingsMapping[] createDTOArray(int size) {
			return new OpenGraphSettingsMapping[size];
		}

		@Override
		protected void setField(
			OpenGraphSettingsMapping openGraphSettingsMapping,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(
					jsonParserFieldName, "descriptionMappingFieldKey")) {

				if (jsonParserFieldValue != null) {
					openGraphSettingsMapping.setDescriptionMappingFieldKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "imageAltMappingFieldKey")) {

				if (jsonParserFieldValue != null) {
					openGraphSettingsMapping.setImageAltMappingFieldKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "imageMappingFieldKey")) {

				if (jsonParserFieldValue != null) {
					openGraphSettingsMapping.setImageMappingFieldKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "titleMappingFieldKey")) {

				if (jsonParserFieldValue != null) {
					openGraphSettingsMapping.setTitleMappingFieldKey(
						(String)jsonParserFieldValue);
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