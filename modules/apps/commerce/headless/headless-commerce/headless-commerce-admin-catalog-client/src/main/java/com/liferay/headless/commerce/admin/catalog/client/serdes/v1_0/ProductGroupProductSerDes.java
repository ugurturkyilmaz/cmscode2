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

import com.liferay.headless.commerce.admin.catalog.client.dto.v1_0.ProductGroupProduct;
import com.liferay.headless.commerce.admin.catalog.client.json.BaseJSONParser;

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
public class ProductGroupProductSerDes {

	public static ProductGroupProduct toDTO(String json) {
		ProductGroupProductJSONParser productGroupProductJSONParser =
			new ProductGroupProductJSONParser();

		return productGroupProductJSONParser.parseToDTO(json);
	}

	public static ProductGroupProduct[] toDTOs(String json) {
		ProductGroupProductJSONParser productGroupProductJSONParser =
			new ProductGroupProductJSONParser();

		return productGroupProductJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ProductGroupProduct productGroupProduct) {
		if (productGroupProduct == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (productGroupProduct.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(productGroupProduct.getId());
		}

		if (productGroupProduct.getProductExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(
				_escape(productGroupProduct.getProductExternalReferenceCode()));

			sb.append("\"");
		}

		if (productGroupProduct.getProductGroupExternalReferenceCode() !=
				null) {

			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productGroupExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(
				_escape(
					productGroupProduct.
						getProductGroupExternalReferenceCode()));

			sb.append("\"");
		}

		if (productGroupProduct.getProductGroupId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productGroupId\": ");

			sb.append(productGroupProduct.getProductGroupId());
		}

		if (productGroupProduct.getProductId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productId\": ");

			sb.append(productGroupProduct.getProductId());
		}

		if (productGroupProduct.getProductName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productName\": ");

			sb.append("\"");

			sb.append(_escape(productGroupProduct.getProductName()));

			sb.append("\"");
		}

		if (productGroupProduct.getSku() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sku\": ");

			sb.append("\"");

			sb.append(_escape(productGroupProduct.getSku()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductGroupProductJSONParser productGroupProductJSONParser =
			new ProductGroupProductJSONParser();

		return productGroupProductJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ProductGroupProduct productGroupProduct) {

		if (productGroupProduct == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (productGroupProduct.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(productGroupProduct.getId()));
		}

		if (productGroupProduct.getProductExternalReferenceCode() == null) {
			map.put("productExternalReferenceCode", null);
		}
		else {
			map.put(
				"productExternalReferenceCode",
				String.valueOf(
					productGroupProduct.getProductExternalReferenceCode()));
		}

		if (productGroupProduct.getProductGroupExternalReferenceCode() ==
				null) {

			map.put("productGroupExternalReferenceCode", null);
		}
		else {
			map.put(
				"productGroupExternalReferenceCode",
				String.valueOf(
					productGroupProduct.
						getProductGroupExternalReferenceCode()));
		}

		if (productGroupProduct.getProductGroupId() == null) {
			map.put("productGroupId", null);
		}
		else {
			map.put(
				"productGroupId",
				String.valueOf(productGroupProduct.getProductGroupId()));
		}

		if (productGroupProduct.getProductId() == null) {
			map.put("productId", null);
		}
		else {
			map.put(
				"productId",
				String.valueOf(productGroupProduct.getProductId()));
		}

		if (productGroupProduct.getProductName() == null) {
			map.put("productName", null);
		}
		else {
			map.put(
				"productName",
				String.valueOf(productGroupProduct.getProductName()));
		}

		if (productGroupProduct.getSku() == null) {
			map.put("sku", null);
		}
		else {
			map.put("sku", String.valueOf(productGroupProduct.getSku()));
		}

		return map;
	}

	public static class ProductGroupProductJSONParser
		extends BaseJSONParser<ProductGroupProduct> {

		@Override
		protected ProductGroupProduct createDTO() {
			return new ProductGroupProduct();
		}

		@Override
		protected ProductGroupProduct[] createDTOArray(int size) {
			return new ProductGroupProduct[size];
		}

		@Override
		protected void setField(
			ProductGroupProduct productGroupProduct, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					productGroupProduct.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "productExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					productGroupProduct.setProductExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"productGroupExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					productGroupProduct.setProductGroupExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productGroupId")) {
				if (jsonParserFieldValue != null) {
					productGroupProduct.setProductGroupId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productId")) {
				if (jsonParserFieldValue != null) {
					productGroupProduct.setProductId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productName")) {
				if (jsonParserFieldValue != null) {
					productGroupProduct.setProductName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "sku")) {
				if (jsonParserFieldValue != null) {
					productGroupProduct.setSku((String)jsonParserFieldValue);
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