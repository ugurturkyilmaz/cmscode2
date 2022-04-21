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

import com.liferay.search.experiences.rest.client.dto.v1_0.SearchableAssetNameDisplay;
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
public class SearchableAssetNameDisplaySerDes {

	public static SearchableAssetNameDisplay toDTO(String json) {
		SearchableAssetNameDisplayJSONParser
			searchableAssetNameDisplayJSONParser =
				new SearchableAssetNameDisplayJSONParser();

		return searchableAssetNameDisplayJSONParser.parseToDTO(json);
	}

	public static SearchableAssetNameDisplay[] toDTOs(String json) {
		SearchableAssetNameDisplayJSONParser
			searchableAssetNameDisplayJSONParser =
				new SearchableAssetNameDisplayJSONParser();

		return searchableAssetNameDisplayJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		SearchableAssetNameDisplay searchableAssetNameDisplay) {

		if (searchableAssetNameDisplay == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (searchableAssetNameDisplay.getClassName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"className\": ");

			sb.append("\"");

			sb.append(_escape(searchableAssetNameDisplay.getClassName()));

			sb.append("\"");
		}

		if (searchableAssetNameDisplay.getDisplayName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"displayName\": ");

			sb.append("\"");

			sb.append(_escape(searchableAssetNameDisplay.getDisplayName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SearchableAssetNameDisplayJSONParser
			searchableAssetNameDisplayJSONParser =
				new SearchableAssetNameDisplayJSONParser();

		return searchableAssetNameDisplayJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		SearchableAssetNameDisplay searchableAssetNameDisplay) {

		if (searchableAssetNameDisplay == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (searchableAssetNameDisplay.getClassName() == null) {
			map.put("className", null);
		}
		else {
			map.put(
				"className",
				String.valueOf(searchableAssetNameDisplay.getClassName()));
		}

		if (searchableAssetNameDisplay.getDisplayName() == null) {
			map.put("displayName", null);
		}
		else {
			map.put(
				"displayName",
				String.valueOf(searchableAssetNameDisplay.getDisplayName()));
		}

		return map;
	}

	public static class SearchableAssetNameDisplayJSONParser
		extends BaseJSONParser<SearchableAssetNameDisplay> {

		@Override
		protected SearchableAssetNameDisplay createDTO() {
			return new SearchableAssetNameDisplay();
		}

		@Override
		protected SearchableAssetNameDisplay[] createDTOArray(int size) {
			return new SearchableAssetNameDisplay[size];
		}

		@Override
		protected void setField(
			SearchableAssetNameDisplay searchableAssetNameDisplay,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "className")) {
				if (jsonParserFieldValue != null) {
					searchableAssetNameDisplay.setClassName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "displayName")) {
				if (jsonParserFieldValue != null) {
					searchableAssetNameDisplay.setDisplayName(
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