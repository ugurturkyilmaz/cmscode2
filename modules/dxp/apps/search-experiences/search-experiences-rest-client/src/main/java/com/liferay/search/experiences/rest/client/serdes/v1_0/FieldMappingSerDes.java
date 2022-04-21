/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.rest.client.serdes.v1_0;

import com.liferay.search.experiences.rest.client.dto.v1_0.FieldMapping;
import com.liferay.search.experiences.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@Generated("")
public class FieldMappingSerDes {

	public static FieldMapping toDTO(String json) {
		FieldMappingJSONParser fieldMappingJSONParser =
			new FieldMappingJSONParser();

		return fieldMappingJSONParser.parseToDTO(json);
	}

	public static FieldMapping[] toDTOs(String json) {
		FieldMappingJSONParser fieldMappingJSONParser =
			new FieldMappingJSONParser();

		return fieldMappingJSONParser.parseToDTOs(json);
	}

	public static String toJSON(FieldMapping fieldMapping) {
		if (fieldMapping == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (fieldMapping.getBoost() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"boost\": ");

			sb.append(fieldMapping.getBoost());
		}

		if (fieldMapping.getField() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"field\": ");

			sb.append("\"");

			sb.append(_escape(fieldMapping.getField()));

			sb.append("\"");
		}

		if (fieldMapping.getLocale() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"locale\": ");

			sb.append("\"");

			sb.append(_escape(fieldMapping.getLocale()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FieldMappingJSONParser fieldMappingJSONParser =
			new FieldMappingJSONParser();

		return fieldMappingJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(FieldMapping fieldMapping) {
		if (fieldMapping == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (fieldMapping.getBoost() == null) {
			map.put("boost", null);
		}
		else {
			map.put("boost", String.valueOf(fieldMapping.getBoost()));
		}

		if (fieldMapping.getField() == null) {
			map.put("field", null);
		}
		else {
			map.put("field", String.valueOf(fieldMapping.getField()));
		}

		if (fieldMapping.getLocale() == null) {
			map.put("locale", null);
		}
		else {
			map.put("locale", String.valueOf(fieldMapping.getLocale()));
		}

		return map;
	}

	public static class FieldMappingJSONParser
		extends BaseJSONParser<FieldMapping> {

		@Override
		protected FieldMapping createDTO() {
			return new FieldMapping();
		}

		@Override
		protected FieldMapping[] createDTOArray(int size) {
			return new FieldMapping[size];
		}

		@Override
		protected void setField(
			FieldMapping fieldMapping, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "boost")) {
				if (jsonParserFieldValue != null) {
					fieldMapping.setBoost(
						Float.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "field")) {
				if (jsonParserFieldValue != null) {
					fieldMapping.setField((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "locale")) {
				if (jsonParserFieldValue != null) {
					fieldMapping.setLocale((String)jsonParserFieldValue);
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