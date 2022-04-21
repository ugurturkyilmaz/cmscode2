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

import com.liferay.headless.delivery.client.dto.v1_0.ClassNameReference;
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
public class ClassNameReferenceSerDes {

	public static ClassNameReference toDTO(String json) {
		ClassNameReferenceJSONParser classNameReferenceJSONParser =
			new ClassNameReferenceJSONParser();

		return classNameReferenceJSONParser.parseToDTO(json);
	}

	public static ClassNameReference[] toDTOs(String json) {
		ClassNameReferenceJSONParser classNameReferenceJSONParser =
			new ClassNameReferenceJSONParser();

		return classNameReferenceJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ClassNameReference classNameReference) {
		if (classNameReference == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (classNameReference.getClassName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"className\": ");

			sb.append("\"");

			sb.append(_escape(classNameReference.getClassName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ClassNameReferenceJSONParser classNameReferenceJSONParser =
			new ClassNameReferenceJSONParser();

		return classNameReferenceJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ClassNameReference classNameReference) {

		if (classNameReference == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (classNameReference.getClassName() == null) {
			map.put("className", null);
		}
		else {
			map.put(
				"className", String.valueOf(classNameReference.getClassName()));
		}

		return map;
	}

	public static class ClassNameReferenceJSONParser
		extends BaseJSONParser<ClassNameReference> {

		@Override
		protected ClassNameReference createDTO() {
			return new ClassNameReference();
		}

		@Override
		protected ClassNameReference[] createDTOArray(int size) {
			return new ClassNameReference[size];
		}

		@Override
		protected void setField(
			ClassNameReference classNameReference, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "className")) {
				if (jsonParserFieldValue != null) {
					classNameReference.setClassName(
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