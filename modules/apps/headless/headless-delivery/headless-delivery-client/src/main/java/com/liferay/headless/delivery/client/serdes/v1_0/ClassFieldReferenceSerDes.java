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

import com.liferay.headless.delivery.client.dto.v1_0.ClassFieldReference;
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
public class ClassFieldReferenceSerDes {

	public static ClassFieldReference toDTO(String json) {
		ClassFieldReferenceJSONParser classFieldReferenceJSONParser =
			new ClassFieldReferenceJSONParser();

		return classFieldReferenceJSONParser.parseToDTO(json);
	}

	public static ClassFieldReference[] toDTOs(String json) {
		ClassFieldReferenceJSONParser classFieldReferenceJSONParser =
			new ClassFieldReferenceJSONParser();

		return classFieldReferenceJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ClassFieldReference classFieldReference) {
		if (classFieldReference == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (classFieldReference.getClassName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"className\": ");

			sb.append("\"");

			sb.append(_escape(classFieldReference.getClassName()));

			sb.append("\"");
		}

		if (classFieldReference.getFieldName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fieldName\": ");

			sb.append("\"");

			sb.append(_escape(classFieldReference.getFieldName()));

			sb.append("\"");
		}

		if (classFieldReference.getFieldValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fieldValue\": ");

			sb.append("\"");

			sb.append(_escape(classFieldReference.getFieldValue()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ClassFieldReferenceJSONParser classFieldReferenceJSONParser =
			new ClassFieldReferenceJSONParser();

		return classFieldReferenceJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ClassFieldReference classFieldReference) {

		if (classFieldReference == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (classFieldReference.getClassName() == null) {
			map.put("className", null);
		}
		else {
			map.put(
				"className",
				String.valueOf(classFieldReference.getClassName()));
		}

		if (classFieldReference.getFieldName() == null) {
			map.put("fieldName", null);
		}
		else {
			map.put(
				"fieldName",
				String.valueOf(classFieldReference.getFieldName()));
		}

		if (classFieldReference.getFieldValue() == null) {
			map.put("fieldValue", null);
		}
		else {
			map.put(
				"fieldValue",
				String.valueOf(classFieldReference.getFieldValue()));
		}

		return map;
	}

	public static class ClassFieldReferenceJSONParser
		extends BaseJSONParser<ClassFieldReference> {

		@Override
		protected ClassFieldReference createDTO() {
			return new ClassFieldReference();
		}

		@Override
		protected ClassFieldReference[] createDTOArray(int size) {
			return new ClassFieldReference[size];
		}

		@Override
		protected void setField(
			ClassFieldReference classFieldReference, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "className")) {
				if (jsonParserFieldValue != null) {
					classFieldReference.setClassName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fieldName")) {
				if (jsonParserFieldValue != null) {
					classFieldReference.setFieldName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fieldValue")) {
				if (jsonParserFieldValue != null) {
					classFieldReference.setFieldValue(
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