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

import com.liferay.headless.delivery.client.dto.v1_0.RenderedContent;
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
public class RenderedContentSerDes {

	public static RenderedContent toDTO(String json) {
		RenderedContentJSONParser renderedContentJSONParser =
			new RenderedContentJSONParser();

		return renderedContentJSONParser.parseToDTO(json);
	}

	public static RenderedContent[] toDTOs(String json) {
		RenderedContentJSONParser renderedContentJSONParser =
			new RenderedContentJSONParser();

		return renderedContentJSONParser.parseToDTOs(json);
	}

	public static String toJSON(RenderedContent renderedContent) {
		if (renderedContent == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (renderedContent.getContentTemplateId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentTemplateId\": ");

			sb.append("\"");

			sb.append(_escape(renderedContent.getContentTemplateId()));

			sb.append("\"");
		}

		if (renderedContent.getContentTemplateName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentTemplateName\": ");

			sb.append("\"");

			sb.append(_escape(renderedContent.getContentTemplateName()));

			sb.append("\"");
		}

		if (renderedContent.getContentTemplateName_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentTemplateName_i18n\": ");

			sb.append(_toJSON(renderedContent.getContentTemplateName_i18n()));
		}

		if (renderedContent.getMarkedAsDefault() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"markedAsDefault\": ");

			sb.append(renderedContent.getMarkedAsDefault());
		}

		if (renderedContent.getRenderedContentURL() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"renderedContentURL\": ");

			sb.append("\"");

			sb.append(_escape(renderedContent.getRenderedContentURL()));

			sb.append("\"");
		}

		if (renderedContent.getRenderedContentValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"renderedContentValue\": ");

			sb.append("\"");

			sb.append(_escape(renderedContent.getRenderedContentValue()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		RenderedContentJSONParser renderedContentJSONParser =
			new RenderedContentJSONParser();

		return renderedContentJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(RenderedContent renderedContent) {
		if (renderedContent == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (renderedContent.getContentTemplateId() == null) {
			map.put("contentTemplateId", null);
		}
		else {
			map.put(
				"contentTemplateId",
				String.valueOf(renderedContent.getContentTemplateId()));
		}

		if (renderedContent.getContentTemplateName() == null) {
			map.put("contentTemplateName", null);
		}
		else {
			map.put(
				"contentTemplateName",
				String.valueOf(renderedContent.getContentTemplateName()));
		}

		if (renderedContent.getContentTemplateName_i18n() == null) {
			map.put("contentTemplateName_i18n", null);
		}
		else {
			map.put(
				"contentTemplateName_i18n",
				String.valueOf(renderedContent.getContentTemplateName_i18n()));
		}

		if (renderedContent.getMarkedAsDefault() == null) {
			map.put("markedAsDefault", null);
		}
		else {
			map.put(
				"markedAsDefault",
				String.valueOf(renderedContent.getMarkedAsDefault()));
		}

		if (renderedContent.getRenderedContentURL() == null) {
			map.put("renderedContentURL", null);
		}
		else {
			map.put(
				"renderedContentURL",
				String.valueOf(renderedContent.getRenderedContentURL()));
		}

		if (renderedContent.getRenderedContentValue() == null) {
			map.put("renderedContentValue", null);
		}
		else {
			map.put(
				"renderedContentValue",
				String.valueOf(renderedContent.getRenderedContentValue()));
		}

		return map;
	}

	public static class RenderedContentJSONParser
		extends BaseJSONParser<RenderedContent> {

		@Override
		protected RenderedContent createDTO() {
			return new RenderedContent();
		}

		@Override
		protected RenderedContent[] createDTOArray(int size) {
			return new RenderedContent[size];
		}

		@Override
		protected void setField(
			RenderedContent renderedContent, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "contentTemplateId")) {
				if (jsonParserFieldValue != null) {
					renderedContent.setContentTemplateId(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "contentTemplateName")) {

				if (jsonParserFieldValue != null) {
					renderedContent.setContentTemplateName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "contentTemplateName_i18n")) {

				if (jsonParserFieldValue != null) {
					renderedContent.setContentTemplateName_i18n(
						(Map)RenderedContentSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "markedAsDefault")) {
				if (jsonParserFieldValue != null) {
					renderedContent.setMarkedAsDefault(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "renderedContentURL")) {

				if (jsonParserFieldValue != null) {
					renderedContent.setRenderedContentURL(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "renderedContentValue")) {

				if (jsonParserFieldValue != null) {
					renderedContent.setRenderedContentValue(
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