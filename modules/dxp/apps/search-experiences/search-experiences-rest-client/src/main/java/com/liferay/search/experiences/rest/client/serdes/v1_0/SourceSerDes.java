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

import com.liferay.search.experiences.rest.client.dto.v1_0.Source;
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
public class SourceSerDes {

	public static Source toDTO(String json) {
		SourceJSONParser sourceJSONParser = new SourceJSONParser();

		return sourceJSONParser.parseToDTO(json);
	}

	public static Source[] toDTOs(String json) {
		SourceJSONParser sourceJSONParser = new SourceJSONParser();

		return sourceJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Source source) {
		if (source == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (source.getExcludes() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"excludes\": ");

			sb.append("[");

			for (int i = 0; i < source.getExcludes().length; i++) {
				sb.append("\"");

				sb.append(_escape(source.getExcludes()[i]));

				sb.append("\"");

				if ((i + 1) < source.getExcludes().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (source.getFetchSource() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fetchSource\": ");

			sb.append(source.getFetchSource());
		}

		if (source.getIncludes() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"includes\": ");

			sb.append("[");

			for (int i = 0; i < source.getIncludes().length; i++) {
				sb.append("\"");

				sb.append(_escape(source.getIncludes()[i]));

				sb.append("\"");

				if ((i + 1) < source.getIncludes().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SourceJSONParser sourceJSONParser = new SourceJSONParser();

		return sourceJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Source source) {
		if (source == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (source.getExcludes() == null) {
			map.put("excludes", null);
		}
		else {
			map.put("excludes", String.valueOf(source.getExcludes()));
		}

		if (source.getFetchSource() == null) {
			map.put("fetchSource", null);
		}
		else {
			map.put("fetchSource", String.valueOf(source.getFetchSource()));
		}

		if (source.getIncludes() == null) {
			map.put("includes", null);
		}
		else {
			map.put("includes", String.valueOf(source.getIncludes()));
		}

		return map;
	}

	public static class SourceJSONParser extends BaseJSONParser<Source> {

		@Override
		protected Source createDTO() {
			return new Source();
		}

		@Override
		protected Source[] createDTOArray(int size) {
			return new Source[size];
		}

		@Override
		protected void setField(
			Source source, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "excludes")) {
				if (jsonParserFieldValue != null) {
					source.setExcludes(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fetchSource")) {
				if (jsonParserFieldValue != null) {
					source.setFetchSource((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "includes")) {
				if (jsonParserFieldValue != null) {
					source.setIncludes(
						toStrings((Object[])jsonParserFieldValue));
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