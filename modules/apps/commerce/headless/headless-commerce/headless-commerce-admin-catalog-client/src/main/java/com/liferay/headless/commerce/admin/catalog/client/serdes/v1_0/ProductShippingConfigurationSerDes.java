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

package com.liferay.headless.commerce.admin.catalog.client.serdes.v1_0;

import com.liferay.headless.commerce.admin.catalog.client.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.client.json.BaseJSONParser;

import java.math.BigDecimal;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public class ProductShippingConfigurationSerDes {

	public static ProductShippingConfiguration toDTO(String json) {
		ProductShippingConfigurationJSONParser
			productShippingConfigurationJSONParser =
				new ProductShippingConfigurationJSONParser();

		return productShippingConfigurationJSONParser.parseToDTO(json);
	}

	public static ProductShippingConfiguration[] toDTOs(String json) {
		ProductShippingConfigurationJSONParser
			productShippingConfigurationJSONParser =
				new ProductShippingConfigurationJSONParser();

		return productShippingConfigurationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		ProductShippingConfiguration productShippingConfiguration) {

		if (productShippingConfiguration == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (productShippingConfiguration.getDepth() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"depth\": ");

			sb.append(productShippingConfiguration.getDepth());
		}

		if (productShippingConfiguration.getFreeShipping() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"freeShipping\": ");

			sb.append(productShippingConfiguration.getFreeShipping());
		}

		if (productShippingConfiguration.getHeight() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"height\": ");

			sb.append(productShippingConfiguration.getHeight());
		}

		if (productShippingConfiguration.getShippable() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippable\": ");

			sb.append(productShippingConfiguration.getShippable());
		}

		if (productShippingConfiguration.getShippingExtraPrice() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingExtraPrice\": ");

			sb.append(productShippingConfiguration.getShippingExtraPrice());
		}

		if (productShippingConfiguration.getShippingSeparately() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingSeparately\": ");

			sb.append(productShippingConfiguration.getShippingSeparately());
		}

		if (productShippingConfiguration.getWeight() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"weight\": ");

			sb.append(productShippingConfiguration.getWeight());
		}

		if (productShippingConfiguration.getWidth() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"width\": ");

			sb.append(productShippingConfiguration.getWidth());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductShippingConfigurationJSONParser
			productShippingConfigurationJSONParser =
				new ProductShippingConfigurationJSONParser();

		return productShippingConfigurationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ProductShippingConfiguration productShippingConfiguration) {

		if (productShippingConfiguration == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (productShippingConfiguration.getDepth() == null) {
			map.put("depth", null);
		}
		else {
			map.put(
				"depth",
				String.valueOf(productShippingConfiguration.getDepth()));
		}

		if (productShippingConfiguration.getFreeShipping() == null) {
			map.put("freeShipping", null);
		}
		else {
			map.put(
				"freeShipping",
				String.valueOf(productShippingConfiguration.getFreeShipping()));
		}

		if (productShippingConfiguration.getHeight() == null) {
			map.put("height", null);
		}
		else {
			map.put(
				"height",
				String.valueOf(productShippingConfiguration.getHeight()));
		}

		if (productShippingConfiguration.getShippable() == null) {
			map.put("shippable", null);
		}
		else {
			map.put(
				"shippable",
				String.valueOf(productShippingConfiguration.getShippable()));
		}

		if (productShippingConfiguration.getShippingExtraPrice() == null) {
			map.put("shippingExtraPrice", null);
		}
		else {
			map.put(
				"shippingExtraPrice",
				String.valueOf(
					productShippingConfiguration.getShippingExtraPrice()));
		}

		if (productShippingConfiguration.getShippingSeparately() == null) {
			map.put("shippingSeparately", null);
		}
		else {
			map.put(
				"shippingSeparately",
				String.valueOf(
					productShippingConfiguration.getShippingSeparately()));
		}

		if (productShippingConfiguration.getWeight() == null) {
			map.put("weight", null);
		}
		else {
			map.put(
				"weight",
				String.valueOf(productShippingConfiguration.getWeight()));
		}

		if (productShippingConfiguration.getWidth() == null) {
			map.put("width", null);
		}
		else {
			map.put(
				"width",
				String.valueOf(productShippingConfiguration.getWidth()));
		}

		return map;
	}

	public static class ProductShippingConfigurationJSONParser
		extends BaseJSONParser<ProductShippingConfiguration> {

		@Override
		protected ProductShippingConfiguration createDTO() {
			return new ProductShippingConfiguration();
		}

		@Override
		protected ProductShippingConfiguration[] createDTOArray(int size) {
			return new ProductShippingConfiguration[size];
		}

		@Override
		protected void setField(
			ProductShippingConfiguration productShippingConfiguration,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "depth")) {
				if (jsonParserFieldValue != null) {
					productShippingConfiguration.setDepth(
						new BigDecimal((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "freeShipping")) {
				if (jsonParserFieldValue != null) {
					productShippingConfiguration.setFreeShipping(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "height")) {
				if (jsonParserFieldValue != null) {
					productShippingConfiguration.setHeight(
						new BigDecimal((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "shippable")) {
				if (jsonParserFieldValue != null) {
					productShippingConfiguration.setShippable(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "shippingExtraPrice")) {

				if (jsonParserFieldValue != null) {
					productShippingConfiguration.setShippingExtraPrice(
						new BigDecimal((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "shippingSeparately")) {

				if (jsonParserFieldValue != null) {
					productShippingConfiguration.setShippingSeparately(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "weight")) {
				if (jsonParserFieldValue != null) {
					productShippingConfiguration.setWeight(
						new BigDecimal((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "width")) {
				if (jsonParserFieldValue != null) {
					productShippingConfiguration.setWidth(
						new BigDecimal((String)jsonParserFieldValue));
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