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

import com.liferay.headless.commerce.admin.order.client.dto.v1_0.OrderRuleChannel;
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
public class OrderRuleChannelSerDes {

	public static OrderRuleChannel toDTO(String json) {
		OrderRuleChannelJSONParser orderRuleChannelJSONParser =
			new OrderRuleChannelJSONParser();

		return orderRuleChannelJSONParser.parseToDTO(json);
	}

	public static OrderRuleChannel[] toDTOs(String json) {
		OrderRuleChannelJSONParser orderRuleChannelJSONParser =
			new OrderRuleChannelJSONParser();

		return orderRuleChannelJSONParser.parseToDTOs(json);
	}

	public static String toJSON(OrderRuleChannel orderRuleChannel) {
		if (orderRuleChannel == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (orderRuleChannel.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(orderRuleChannel.getActions()));
		}

		if (orderRuleChannel.getChannel() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"channel\": ");

			sb.append(String.valueOf(orderRuleChannel.getChannel()));
		}

		if (orderRuleChannel.getChannelExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"channelExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(
				_escape(orderRuleChannel.getChannelExternalReferenceCode()));

			sb.append("\"");
		}

		if (orderRuleChannel.getChannelId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"channelId\": ");

			sb.append(orderRuleChannel.getChannelId());
		}

		if (orderRuleChannel.getOrderRuleChannelId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderRuleChannelId\": ");

			sb.append(orderRuleChannel.getOrderRuleChannelId());
		}

		if (orderRuleChannel.getOrderRuleExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderRuleExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(
				_escape(orderRuleChannel.getOrderRuleExternalReferenceCode()));

			sb.append("\"");
		}

		if (orderRuleChannel.getOrderRuleId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderRuleId\": ");

			sb.append(orderRuleChannel.getOrderRuleId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		OrderRuleChannelJSONParser orderRuleChannelJSONParser =
			new OrderRuleChannelJSONParser();

		return orderRuleChannelJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(OrderRuleChannel orderRuleChannel) {
		if (orderRuleChannel == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (orderRuleChannel.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(orderRuleChannel.getActions()));
		}

		if (orderRuleChannel.getChannel() == null) {
			map.put("channel", null);
		}
		else {
			map.put("channel", String.valueOf(orderRuleChannel.getChannel()));
		}

		if (orderRuleChannel.getChannelExternalReferenceCode() == null) {
			map.put("channelExternalReferenceCode", null);
		}
		else {
			map.put(
				"channelExternalReferenceCode",
				String.valueOf(
					orderRuleChannel.getChannelExternalReferenceCode()));
		}

		if (orderRuleChannel.getChannelId() == null) {
			map.put("channelId", null);
		}
		else {
			map.put(
				"channelId", String.valueOf(orderRuleChannel.getChannelId()));
		}

		if (orderRuleChannel.getOrderRuleChannelId() == null) {
			map.put("orderRuleChannelId", null);
		}
		else {
			map.put(
				"orderRuleChannelId",
				String.valueOf(orderRuleChannel.getOrderRuleChannelId()));
		}

		if (orderRuleChannel.getOrderRuleExternalReferenceCode() == null) {
			map.put("orderRuleExternalReferenceCode", null);
		}
		else {
			map.put(
				"orderRuleExternalReferenceCode",
				String.valueOf(
					orderRuleChannel.getOrderRuleExternalReferenceCode()));
		}

		if (orderRuleChannel.getOrderRuleId() == null) {
			map.put("orderRuleId", null);
		}
		else {
			map.put(
				"orderRuleId",
				String.valueOf(orderRuleChannel.getOrderRuleId()));
		}

		return map;
	}

	public static class OrderRuleChannelJSONParser
		extends BaseJSONParser<OrderRuleChannel> {

		@Override
		protected OrderRuleChannel createDTO() {
			return new OrderRuleChannel();
		}

		@Override
		protected OrderRuleChannel[] createDTOArray(int size) {
			return new OrderRuleChannel[size];
		}

		@Override
		protected void setField(
			OrderRuleChannel orderRuleChannel, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					orderRuleChannel.setActions(
						(Map)OrderRuleChannelSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "channel")) {
				if (jsonParserFieldValue != null) {
					orderRuleChannel.setChannel(
						ChannelSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "channelExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					orderRuleChannel.setChannelExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "channelId")) {
				if (jsonParserFieldValue != null) {
					orderRuleChannel.setChannelId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "orderRuleChannelId")) {

				if (jsonParserFieldValue != null) {
					orderRuleChannel.setOrderRuleChannelId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"orderRuleExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					orderRuleChannel.setOrderRuleExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "orderRuleId")) {
				if (jsonParserFieldValue != null) {
					orderRuleChannel.setOrderRuleId(
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