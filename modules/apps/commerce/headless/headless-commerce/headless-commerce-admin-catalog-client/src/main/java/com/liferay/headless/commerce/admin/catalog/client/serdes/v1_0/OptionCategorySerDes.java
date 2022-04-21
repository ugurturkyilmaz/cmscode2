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

package com.liferay.headless.commerce.admin.catalog.client.serdes.v1_0;

import com.liferay.headless.commerce.admin.catalog.client.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public class OptionCategorySerDes {

	public static OptionCategory toDTO(String json) {
		OptionCategoryJSONParser optionCategoryJSONParser =
			new OptionCategoryJSONParser();

		return optionCategoryJSONParser.parseToDTO(json);
	}

	public static OptionCategory[] toDTOs(String json) {
		OptionCategoryJSONParser optionCategoryJSONParser =
			new OptionCategoryJSONParser();

		return optionCategoryJSONParser.parseToDTOs(json);
	}

	public static String toJSON(OptionCategory optionCategory) {
		if (optionCategory == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (optionCategory.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append(_toJSON(optionCategory.getDescription()));
		}

		if (optionCategory.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(optionCategory.getId());
		}

		if (optionCategory.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(optionCategory.getKey()));

			sb.append("\"");
		}

		if (optionCategory.getPriority() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priority\": ");

			sb.append(optionCategory.getPriority());
		}

		if (optionCategory.getTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title\": ");

			sb.append(_toJSON(optionCategory.getTitle()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		OptionCategoryJSONParser optionCategoryJSONParser =
			new OptionCategoryJSONParser();

		return optionCategoryJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(OptionCategory optionCategory) {
		if (optionCategory == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (optionCategory.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description", String.valueOf(optionCategory.getDescription()));
		}

		if (optionCategory.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(optionCategory.getId()));
		}

		if (optionCategory.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(optionCategory.getKey()));
		}

		if (optionCategory.getPriority() == null) {
			map.put("priority", null);
		}
		else {
			map.put("priority", String.valueOf(optionCategory.getPriority()));
		}

		if (optionCategory.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(optionCategory.getTitle()));
		}

		return map;
	}

	public static class OptionCategoryJSONParser
		extends BaseJSONParser<OptionCategory> {

		@Override
		protected OptionCategory createDTO() {
			return new OptionCategory();
		}

		@Override
		protected OptionCategory[] createDTOArray(int size) {
			return new OptionCategory[size];
		}

		@Override
		protected void setField(
			OptionCategory optionCategory, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					optionCategory.setDescription(
						(Map)OptionCategorySerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					optionCategory.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					optionCategory.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priority")) {
				if (jsonParserFieldValue != null) {
					optionCategory.setPriority(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					optionCategory.setTitle(
						(Map)OptionCategorySerDes.toMap(
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