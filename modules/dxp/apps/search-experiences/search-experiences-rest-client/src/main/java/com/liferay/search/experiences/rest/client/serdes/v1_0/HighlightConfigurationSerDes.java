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

import com.liferay.search.experiences.rest.client.dto.v1_0.HighlightConfiguration;
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
public class HighlightConfigurationSerDes {

	public static HighlightConfiguration toDTO(String json) {
		HighlightConfigurationJSONParser highlightConfigurationJSONParser =
			new HighlightConfigurationJSONParser();

		return highlightConfigurationJSONParser.parseToDTO(json);
	}

	public static HighlightConfiguration[] toDTOs(String json) {
		HighlightConfigurationJSONParser highlightConfigurationJSONParser =
			new HighlightConfigurationJSONParser();

		return highlightConfigurationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(HighlightConfiguration highlightConfiguration) {
		if (highlightConfiguration == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (highlightConfiguration.getFields() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fields\": ");

			sb.append(_toJSON(highlightConfiguration.getFields()));
		}

		if (highlightConfiguration.getFragment_size() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragment_size\": ");

			sb.append(highlightConfiguration.getFragment_size());
		}

		if (highlightConfiguration.getNumber_of_fragments() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"number_of_fragments\": ");

			sb.append(highlightConfiguration.getNumber_of_fragments());
		}

		if (highlightConfiguration.getPost_tags() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"post_tags\": ");

			sb.append("[");

			for (int i = 0; i < highlightConfiguration.getPost_tags().length;
				 i++) {

				sb.append("\"");

				sb.append(_escape(highlightConfiguration.getPost_tags()[i]));

				sb.append("\"");

				if ((i + 1) < highlightConfiguration.getPost_tags().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (highlightConfiguration.getPre_tags() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pre_tags\": ");

			sb.append("[");

			for (int i = 0; i < highlightConfiguration.getPre_tags().length;
				 i++) {

				sb.append("\"");

				sb.append(_escape(highlightConfiguration.getPre_tags()[i]));

				sb.append("\"");

				if ((i + 1) < highlightConfiguration.getPre_tags().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (highlightConfiguration.getRequire_field_match() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"require_field_match\": ");

			sb.append(highlightConfiguration.getRequire_field_match());
		}

		if (highlightConfiguration.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(highlightConfiguration.getType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		HighlightConfigurationJSONParser highlightConfigurationJSONParser =
			new HighlightConfigurationJSONParser();

		return highlightConfigurationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		HighlightConfiguration highlightConfiguration) {

		if (highlightConfiguration == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (highlightConfiguration.getFields() == null) {
			map.put("fields", null);
		}
		else {
			map.put(
				"fields", String.valueOf(highlightConfiguration.getFields()));
		}

		if (highlightConfiguration.getFragment_size() == null) {
			map.put("fragment_size", null);
		}
		else {
			map.put(
				"fragment_size",
				String.valueOf(highlightConfiguration.getFragment_size()));
		}

		if (highlightConfiguration.getNumber_of_fragments() == null) {
			map.put("number_of_fragments", null);
		}
		else {
			map.put(
				"number_of_fragments",
				String.valueOf(
					highlightConfiguration.getNumber_of_fragments()));
		}

		if (highlightConfiguration.getPost_tags() == null) {
			map.put("post_tags", null);
		}
		else {
			map.put(
				"post_tags",
				String.valueOf(highlightConfiguration.getPost_tags()));
		}

		if (highlightConfiguration.getPre_tags() == null) {
			map.put("pre_tags", null);
		}
		else {
			map.put(
				"pre_tags",
				String.valueOf(highlightConfiguration.getPre_tags()));
		}

		if (highlightConfiguration.getRequire_field_match() == null) {
			map.put("require_field_match", null);
		}
		else {
			map.put(
				"require_field_match",
				String.valueOf(
					highlightConfiguration.getRequire_field_match()));
		}

		if (highlightConfiguration.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(highlightConfiguration.getType()));
		}

		return map;
	}

	public static class HighlightConfigurationJSONParser
		extends BaseJSONParser<HighlightConfiguration> {

		@Override
		protected HighlightConfiguration createDTO() {
			return new HighlightConfiguration();
		}

		@Override
		protected HighlightConfiguration[] createDTOArray(int size) {
			return new HighlightConfiguration[size];
		}

		@Override
		protected void setField(
			HighlightConfiguration highlightConfiguration,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "fields")) {
				if (jsonParserFieldValue != null) {
					highlightConfiguration.setFields(
						(Map)HighlightConfigurationSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fragment_size")) {
				if (jsonParserFieldValue != null) {
					highlightConfiguration.setFragment_size(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "number_of_fragments")) {

				if (jsonParserFieldValue != null) {
					highlightConfiguration.setNumber_of_fragments(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "post_tags")) {
				if (jsonParserFieldValue != null) {
					highlightConfiguration.setPost_tags(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "pre_tags")) {
				if (jsonParserFieldValue != null) {
					highlightConfiguration.setPre_tags(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "require_field_match")) {

				if (jsonParserFieldValue != null) {
					highlightConfiguration.setRequire_field_match(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					highlightConfiguration.setType(
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