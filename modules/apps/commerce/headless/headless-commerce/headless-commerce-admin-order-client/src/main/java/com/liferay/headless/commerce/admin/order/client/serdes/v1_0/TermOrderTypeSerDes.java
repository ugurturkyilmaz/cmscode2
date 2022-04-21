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

package com.liferay.headless.commerce.admin.order.client.serdes.v1_0;

import com.liferay.headless.commerce.admin.order.client.dto.v1_0.TermOrderType;
import com.liferay.headless.commerce.admin.order.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class TermOrderTypeSerDes {

	public static TermOrderType toDTO(String json) {
		TermOrderTypeJSONParser termOrderTypeJSONParser =
			new TermOrderTypeJSONParser();

		return termOrderTypeJSONParser.parseToDTO(json);
	}

	public static TermOrderType[] toDTOs(String json) {
		TermOrderTypeJSONParser termOrderTypeJSONParser =
			new TermOrderTypeJSONParser();

		return termOrderTypeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(TermOrderType termOrderType) {
		if (termOrderType == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (termOrderType.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(termOrderType.getActions()));
		}

		if (termOrderType.getOrderType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderType\": ");

			sb.append(String.valueOf(termOrderType.getOrderType()));
		}

		if (termOrderType.getOrderTypeExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderTypeExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(
				_escape(termOrderType.getOrderTypeExternalReferenceCode()));

			sb.append("\"");
		}

		if (termOrderType.getOrderTypeId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderTypeId\": ");

			sb.append(termOrderType.getOrderTypeId());
		}

		if (termOrderType.getTermExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"termExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(termOrderType.getTermExternalReferenceCode()));

			sb.append("\"");
		}

		if (termOrderType.getTermId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"termId\": ");

			sb.append(termOrderType.getTermId());
		}

		if (termOrderType.getTermOrderTypeId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"termOrderTypeId\": ");

			sb.append(termOrderType.getTermOrderTypeId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TermOrderTypeJSONParser termOrderTypeJSONParser =
			new TermOrderTypeJSONParser();

		return termOrderTypeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(TermOrderType termOrderType) {
		if (termOrderType == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (termOrderType.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(termOrderType.getActions()));
		}

		if (termOrderType.getOrderType() == null) {
			map.put("orderType", null);
		}
		else {
			map.put("orderType", String.valueOf(termOrderType.getOrderType()));
		}

		if (termOrderType.getOrderTypeExternalReferenceCode() == null) {
			map.put("orderTypeExternalReferenceCode", null);
		}
		else {
			map.put(
				"orderTypeExternalReferenceCode",
				String.valueOf(
					termOrderType.getOrderTypeExternalReferenceCode()));
		}

		if (termOrderType.getOrderTypeId() == null) {
			map.put("orderTypeId", null);
		}
		else {
			map.put(
				"orderTypeId", String.valueOf(termOrderType.getOrderTypeId()));
		}

		if (termOrderType.getTermExternalReferenceCode() == null) {
			map.put("termExternalReferenceCode", null);
		}
		else {
			map.put(
				"termExternalReferenceCode",
				String.valueOf(termOrderType.getTermExternalReferenceCode()));
		}

		if (termOrderType.getTermId() == null) {
			map.put("termId", null);
		}
		else {
			map.put("termId", String.valueOf(termOrderType.getTermId()));
		}

		if (termOrderType.getTermOrderTypeId() == null) {
			map.put("termOrderTypeId", null);
		}
		else {
			map.put(
				"termOrderTypeId",
				String.valueOf(termOrderType.getTermOrderTypeId()));
		}

		return map;
	}

	public static class TermOrderTypeJSONParser
		extends BaseJSONParser<TermOrderType> {

		@Override
		protected TermOrderType createDTO() {
			return new TermOrderType();
		}

		@Override
		protected TermOrderType[] createDTOArray(int size) {
			return new TermOrderType[size];
		}

		@Override
		protected void setField(
			TermOrderType termOrderType, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					termOrderType.setActions(
						(Map)TermOrderTypeSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "orderType")) {
				if (jsonParserFieldValue != null) {
					termOrderType.setOrderType(
						OrderTypeSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"orderTypeExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					termOrderType.setOrderTypeExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "orderTypeId")) {
				if (jsonParserFieldValue != null) {
					termOrderType.setOrderTypeId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "termExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					termOrderType.setTermExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "termId")) {
				if (jsonParserFieldValue != null) {
					termOrderType.setTermId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "termOrderTypeId")) {
				if (jsonParserFieldValue != null) {
					termOrderType.setTermOrderTypeId(
						Long.valueOf((String)jsonParserFieldValue));
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