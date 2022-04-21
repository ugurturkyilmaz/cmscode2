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

import com.liferay.search.experiences.rest.client.dto.v1_0.Parameter;
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
public class ParameterSerDes {

	public static Parameter toDTO(String json) {
		ParameterJSONParser parameterJSONParser = new ParameterJSONParser();

		return parameterJSONParser.parseToDTO(json);
	}

	public static Parameter[] toDTOs(String json) {
		ParameterJSONParser parameterJSONParser = new ParameterJSONParser();

		return parameterJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Parameter parameter) {
		if (parameter == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (parameter.getDefaultValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultValue\": ");

			if (parameter.getDefaultValue() instanceof String) {
				sb.append("\"");
				sb.append((String)parameter.getDefaultValue());
				sb.append("\"");
			}
			else {
				sb.append(parameter.getDefaultValue());
			}
		}

		if (parameter.getFormat() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"format\": ");

			sb.append("\"");

			sb.append(_escape(parameter.getFormat()));

			sb.append("\"");
		}

		if (parameter.getMax() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"max\": ");

			if (parameter.getMax() instanceof String) {
				sb.append("\"");
				sb.append((String)parameter.getMax());
				sb.append("\"");
			}
			else {
				sb.append(parameter.getMax());
			}
		}

		if (parameter.getMin() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"min\": ");

			if (parameter.getMin() instanceof String) {
				sb.append("\"");
				sb.append((String)parameter.getMin());
				sb.append("\"");
			}
			else {
				sb.append(parameter.getMin());
			}
		}

		if (parameter.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(parameter.getType());

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ParameterJSONParser parameterJSONParser = new ParameterJSONParser();

		return parameterJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Parameter parameter) {
		if (parameter == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (parameter.getDefaultValue() == null) {
			map.put("defaultValue", null);
		}
		else {
			map.put(
				"defaultValue", String.valueOf(parameter.getDefaultValue()));
		}

		if (parameter.getFormat() == null) {
			map.put("format", null);
		}
		else {
			map.put("format", String.valueOf(parameter.getFormat()));
		}

		if (parameter.getMax() == null) {
			map.put("max", null);
		}
		else {
			map.put("max", String.valueOf(parameter.getMax()));
		}

		if (parameter.getMin() == null) {
			map.put("min", null);
		}
		else {
			map.put("min", String.valueOf(parameter.getMin()));
		}

		if (parameter.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(parameter.getType()));
		}

		return map;
	}

	public static class ParameterJSONParser extends BaseJSONParser<Parameter> {

		@Override
		protected Parameter createDTO() {
			return new Parameter();
		}

		@Override
		protected Parameter[] createDTOArray(int size) {
			return new Parameter[size];
		}

		@Override
		protected void setField(
			Parameter parameter, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "defaultValue")) {
				if (jsonParserFieldValue != null) {
					parameter.setDefaultValue((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "format")) {
				if (jsonParserFieldValue != null) {
					parameter.setFormat((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "max")) {
				if (jsonParserFieldValue != null) {
					parameter.setMax((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "min")) {
				if (jsonParserFieldValue != null) {
					parameter.setMin((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					parameter.setType(
						Parameter.Type.create((String)jsonParserFieldValue));
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