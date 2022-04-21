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

package com.liferay.headless.commerce.delivery.catalog.client.serdes.v1_0;

import com.liferay.headless.commerce.delivery.catalog.client.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.delivery.catalog.client.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.delivery.catalog.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public class ProductOptionSerDes {

	public static ProductOption toDTO(String json) {
		ProductOptionJSONParser productOptionJSONParser =
			new ProductOptionJSONParser();

		return productOptionJSONParser.parseToDTO(json);
	}

	public static ProductOption[] toDTOs(String json) {
		ProductOptionJSONParser productOptionJSONParser =
			new ProductOptionJSONParser();

		return productOptionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ProductOption productOption) {
		if (productOption == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (productOption.getCatalogId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"catalogId\": ");

			sb.append(productOption.getCatalogId());
		}

		if (productOption.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(productOption.getDescription()));

			sb.append("\"");
		}

		if (productOption.getFieldType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fieldType\": ");

			sb.append("\"");

			sb.append(_escape(productOption.getFieldType()));

			sb.append("\"");
		}

		if (productOption.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(productOption.getId());
		}

		if (productOption.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(productOption.getKey()));

			sb.append("\"");
		}

		if (productOption.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(productOption.getName()));

			sb.append("\"");
		}

		if (productOption.getOptionId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"optionId\": ");

			sb.append(productOption.getOptionId());
		}

		if (productOption.getPriority() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priority\": ");

			sb.append(productOption.getPriority());
		}

		if (productOption.getProductOptionValues() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productOptionValues\": ");

			sb.append("[");

			for (int i = 0; i < productOption.getProductOptionValues().length;
				 i++) {

				sb.append(
					String.valueOf(productOption.getProductOptionValues()[i]));

				if ((i + 1) < productOption.getProductOptionValues().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductOptionJSONParser productOptionJSONParser =
			new ProductOptionJSONParser();

		return productOptionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(ProductOption productOption) {
		if (productOption == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (productOption.getCatalogId() == null) {
			map.put("catalogId", null);
		}
		else {
			map.put("catalogId", String.valueOf(productOption.getCatalogId()));
		}

		if (productOption.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description", String.valueOf(productOption.getDescription()));
		}

		if (productOption.getFieldType() == null) {
			map.put("fieldType", null);
		}
		else {
			map.put("fieldType", String.valueOf(productOption.getFieldType()));
		}

		if (productOption.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(productOption.getId()));
		}

		if (productOption.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(productOption.getKey()));
		}

		if (productOption.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(productOption.getName()));
		}

		if (productOption.getOptionId() == null) {
			map.put("optionId", null);
		}
		else {
			map.put("optionId", String.valueOf(productOption.getOptionId()));
		}

		if (productOption.getPriority() == null) {
			map.put("priority", null);
		}
		else {
			map.put("priority", String.valueOf(productOption.getPriority()));
		}

		if (productOption.getProductOptionValues() == null) {
			map.put("productOptionValues", null);
		}
		else {
			map.put(
				"productOptionValues",
				String.valueOf(productOption.getProductOptionValues()));
		}

		return map;
	}

	public static class ProductOptionJSONParser
		extends BaseJSONParser<ProductOption> {

		@Override
		protected ProductOption createDTO() {
			return new ProductOption();
		}

		@Override
		protected ProductOption[] createDTOArray(int size) {
			return new ProductOption[size];
		}

		@Override
		protected void setField(
			ProductOption productOption, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "catalogId")) {
				if (jsonParserFieldValue != null) {
					productOption.setCatalogId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					productOption.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fieldType")) {
				if (jsonParserFieldValue != null) {
					productOption.setFieldType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					productOption.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					productOption.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					productOption.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "optionId")) {
				if (jsonParserFieldValue != null) {
					productOption.setOptionId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priority")) {
				if (jsonParserFieldValue != null) {
					productOption.setPriority(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "productOptionValues")) {

				if (jsonParserFieldValue != null) {
					productOption.setProductOptionValues(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ProductOptionValueSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new ProductOptionValue[size]
						));
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