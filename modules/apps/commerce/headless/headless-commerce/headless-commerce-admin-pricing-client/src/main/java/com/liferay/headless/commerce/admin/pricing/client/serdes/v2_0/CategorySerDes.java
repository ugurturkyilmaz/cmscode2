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

package com.liferay.headless.commerce.admin.pricing.client.serdes.v2_0;

import com.liferay.headless.commerce.admin.pricing.client.dto.v2_0.Category;
import com.liferay.headless.commerce.admin.pricing.client.json.BaseJSONParser;

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
public class CategorySerDes {

	public static Category toDTO(String json) {
		CategoryJSONParser categoryJSONParser = new CategoryJSONParser();

		return categoryJSONParser.parseToDTO(json);
	}

	public static Category[] toDTOs(String json) {
		CategoryJSONParser categoryJSONParser = new CategoryJSONParser();

		return categoryJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Category category) {
		if (category == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (category.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(category.getId());
		}

		if (category.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(category.getName()));

			sb.append("\"");
		}

		if (category.getPath() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"path\": ");

			sb.append("\"");

			sb.append(_escape(category.getPath()));

			sb.append("\"");
		}

		if (category.getVocabulary() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"vocabulary\": ");

			sb.append("\"");

			sb.append(_escape(category.getVocabulary()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CategoryJSONParser categoryJSONParser = new CategoryJSONParser();

		return categoryJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Category category) {
		if (category == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (category.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(category.getId()));
		}

		if (category.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(category.getName()));
		}

		if (category.getPath() == null) {
			map.put("path", null);
		}
		else {
			map.put("path", String.valueOf(category.getPath()));
		}

		if (category.getVocabulary() == null) {
			map.put("vocabulary", null);
		}
		else {
			map.put("vocabulary", String.valueOf(category.getVocabulary()));
		}

		return map;
	}

	public static class CategoryJSONParser extends BaseJSONParser<Category> {

		@Override
		protected Category createDTO() {
			return new Category();
		}

		@Override
		protected Category[] createDTOArray(int size) {
			return new Category[size];
		}

		@Override
		protected void setField(
			Category category, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					category.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					category.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "path")) {
				if (jsonParserFieldValue != null) {
					category.setPath((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "vocabulary")) {
				if (jsonParserFieldValue != null) {
					category.setVocabulary((String)jsonParserFieldValue);
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