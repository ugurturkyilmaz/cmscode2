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

import com.liferay.search.experiences.rest.client.dto.v1_0.SXPParameterContributorDefinition;
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
public class SXPParameterContributorDefinitionSerDes {

	public static SXPParameterContributorDefinition toDTO(String json) {
		SXPParameterContributorDefinitionJSONParser
			sxpParameterContributorDefinitionJSONParser =
				new SXPParameterContributorDefinitionJSONParser();

		return sxpParameterContributorDefinitionJSONParser.parseToDTO(json);
	}

	public static SXPParameterContributorDefinition[] toDTOs(String json) {
		SXPParameterContributorDefinitionJSONParser
			sxpParameterContributorDefinitionJSONParser =
				new SXPParameterContributorDefinitionJSONParser();

		return sxpParameterContributorDefinitionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		SXPParameterContributorDefinition sxpParameterContributorDefinition) {

		if (sxpParameterContributorDefinition == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (sxpParameterContributorDefinition.getClassName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"className\": ");

			sb.append("\"");

			sb.append(
				_escape(sxpParameterContributorDefinition.getClassName()));

			sb.append("\"");
		}

		if (sxpParameterContributorDefinition.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(
				_escape(sxpParameterContributorDefinition.getDescription()));

			sb.append("\"");
		}

		if (sxpParameterContributorDefinition.getTemplateVariable() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"templateVariable\": ");

			sb.append("\"");

			sb.append(
				_escape(
					sxpParameterContributorDefinition.getTemplateVariable()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SXPParameterContributorDefinitionJSONParser
			sxpParameterContributorDefinitionJSONParser =
				new SXPParameterContributorDefinitionJSONParser();

		return sxpParameterContributorDefinitionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		SXPParameterContributorDefinition sxpParameterContributorDefinition) {

		if (sxpParameterContributorDefinition == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (sxpParameterContributorDefinition.getClassName() == null) {
			map.put("className", null);
		}
		else {
			map.put(
				"className",
				String.valueOf(
					sxpParameterContributorDefinition.getClassName()));
		}

		if (sxpParameterContributorDefinition.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description",
				String.valueOf(
					sxpParameterContributorDefinition.getDescription()));
		}

		if (sxpParameterContributorDefinition.getTemplateVariable() == null) {
			map.put("templateVariable", null);
		}
		else {
			map.put(
				"templateVariable",
				String.valueOf(
					sxpParameterContributorDefinition.getTemplateVariable()));
		}

		return map;
	}

	public static class SXPParameterContributorDefinitionJSONParser
		extends BaseJSONParser<SXPParameterContributorDefinition> {

		@Override
		protected SXPParameterContributorDefinition createDTO() {
			return new SXPParameterContributorDefinition();
		}

		@Override
		protected SXPParameterContributorDefinition[] createDTOArray(int size) {
			return new SXPParameterContributorDefinition[size];
		}

		@Override
		protected void setField(
			SXPParameterContributorDefinition sxpParameterContributorDefinition,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "className")) {
				if (jsonParserFieldValue != null) {
					sxpParameterContributorDefinition.setClassName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					sxpParameterContributorDefinition.setDescription(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "templateVariable")) {
				if (jsonParserFieldValue != null) {
					sxpParameterContributorDefinition.setTemplateVariable(
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