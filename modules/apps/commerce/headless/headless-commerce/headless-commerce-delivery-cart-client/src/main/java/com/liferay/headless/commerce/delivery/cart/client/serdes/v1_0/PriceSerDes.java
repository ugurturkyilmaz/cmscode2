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

package com.liferay.headless.commerce.delivery.cart.client.serdes.v1_0;

import com.liferay.headless.commerce.delivery.cart.client.dto.v1_0.Price;
import com.liferay.headless.commerce.delivery.cart.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public class PriceSerDes {

	public static Price toDTO(String json) {
		PriceJSONParser priceJSONParser = new PriceJSONParser();

		return priceJSONParser.parseToDTO(json);
	}

	public static Price[] toDTOs(String json) {
		PriceJSONParser priceJSONParser = new PriceJSONParser();

		return priceJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Price price) {
		if (price == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (price.getCurrency() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"currency\": ");

			sb.append("\"");

			sb.append(_escape(price.getCurrency()));

			sb.append("\"");
		}

		if (price.getDiscount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discount\": ");

			sb.append(price.getDiscount());
		}

		if (price.getDiscountFormatted() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountFormatted\": ");

			sb.append("\"");

			sb.append(_escape(price.getDiscountFormatted()));

			sb.append("\"");
		}

		if (price.getDiscountPercentage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountPercentage\": ");

			sb.append("\"");

			sb.append(_escape(price.getDiscountPercentage()));

			sb.append("\"");
		}

		if (price.getDiscountPercentageLevel1() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountPercentageLevel1\": ");

			sb.append(price.getDiscountPercentageLevel1());
		}

		if (price.getDiscountPercentageLevel2() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountPercentageLevel2\": ");

			sb.append(price.getDiscountPercentageLevel2());
		}

		if (price.getDiscountPercentageLevel3() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountPercentageLevel3\": ");

			sb.append(price.getDiscountPercentageLevel3());
		}

		if (price.getDiscountPercentageLevel4() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountPercentageLevel4\": ");

			sb.append(price.getDiscountPercentageLevel4());
		}

		if (price.getFinalPrice() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"finalPrice\": ");

			sb.append(price.getFinalPrice());
		}

		if (price.getFinalPriceFormatted() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"finalPriceFormatted\": ");

			sb.append("\"");

			sb.append(_escape(price.getFinalPriceFormatted()));

			sb.append("\"");
		}

		if (price.getPrice() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"price\": ");

			sb.append(price.getPrice());
		}

		if (price.getPriceFormatted() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priceFormatted\": ");

			sb.append("\"");

			sb.append(_escape(price.getPriceFormatted()));

			sb.append("\"");
		}

		if (price.getPromoPrice() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"promoPrice\": ");

			sb.append(price.getPromoPrice());
		}

		if (price.getPromoPriceFormatted() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"promoPriceFormatted\": ");

			sb.append("\"");

			sb.append(_escape(price.getPromoPriceFormatted()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PriceJSONParser priceJSONParser = new PriceJSONParser();

		return priceJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Price price) {
		if (price == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (price.getCurrency() == null) {
			map.put("currency", null);
		}
		else {
			map.put("currency", String.valueOf(price.getCurrency()));
		}

		if (price.getDiscount() == null) {
			map.put("discount", null);
		}
		else {
			map.put("discount", String.valueOf(price.getDiscount()));
		}

		if (price.getDiscountFormatted() == null) {
			map.put("discountFormatted", null);
		}
		else {
			map.put(
				"discountFormatted",
				String.valueOf(price.getDiscountFormatted()));
		}

		if (price.getDiscountPercentage() == null) {
			map.put("discountPercentage", null);
		}
		else {
			map.put(
				"discountPercentage",
				String.valueOf(price.getDiscountPercentage()));
		}

		if (price.getDiscountPercentageLevel1() == null) {
			map.put("discountPercentageLevel1", null);
		}
		else {
			map.put(
				"discountPercentageLevel1",
				String.valueOf(price.getDiscountPercentageLevel1()));
		}

		if (price.getDiscountPercentageLevel2() == null) {
			map.put("discountPercentageLevel2", null);
		}
		else {
			map.put(
				"discountPercentageLevel2",
				String.valueOf(price.getDiscountPercentageLevel2()));
		}

		if (price.getDiscountPercentageLevel3() == null) {
			map.put("discountPercentageLevel3", null);
		}
		else {
			map.put(
				"discountPercentageLevel3",
				String.valueOf(price.getDiscountPercentageLevel3()));
		}

		if (price.getDiscountPercentageLevel4() == null) {
			map.put("discountPercentageLevel4", null);
		}
		else {
			map.put(
				"discountPercentageLevel4",
				String.valueOf(price.getDiscountPercentageLevel4()));
		}

		if (price.getFinalPrice() == null) {
			map.put("finalPrice", null);
		}
		else {
			map.put("finalPrice", String.valueOf(price.getFinalPrice()));
		}

		if (price.getFinalPriceFormatted() == null) {
			map.put("finalPriceFormatted", null);
		}
		else {
			map.put(
				"finalPriceFormatted",
				String.valueOf(price.getFinalPriceFormatted()));
		}

		if (price.getPrice() == null) {
			map.put("price", null);
		}
		else {
			map.put("price", String.valueOf(price.getPrice()));
		}

		if (price.getPriceFormatted() == null) {
			map.put("priceFormatted", null);
		}
		else {
			map.put(
				"priceFormatted", String.valueOf(price.getPriceFormatted()));
		}

		if (price.getPromoPrice() == null) {
			map.put("promoPrice", null);
		}
		else {
			map.put("promoPrice", String.valueOf(price.getPromoPrice()));
		}

		if (price.getPromoPriceFormatted() == null) {
			map.put("promoPriceFormatted", null);
		}
		else {
			map.put(
				"promoPriceFormatted",
				String.valueOf(price.getPromoPriceFormatted()));
		}

		return map;
	}

	public static class PriceJSONParser extends BaseJSONParser<Price> {

		@Override
		protected Price createDTO() {
			return new Price();
		}

		@Override
		protected Price[] createDTOArray(int size) {
			return new Price[size];
		}

		@Override
		protected void setField(
			Price price, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "currency")) {
				if (jsonParserFieldValue != null) {
					price.setCurrency((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "discount")) {
				if (jsonParserFieldValue != null) {
					price.setDiscount(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "discountFormatted")) {
				if (jsonParserFieldValue != null) {
					price.setDiscountFormatted((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "discountPercentage")) {

				if (jsonParserFieldValue != null) {
					price.setDiscountPercentage((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "discountPercentageLevel1")) {

				if (jsonParserFieldValue != null) {
					price.setDiscountPercentageLevel1(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "discountPercentageLevel2")) {

				if (jsonParserFieldValue != null) {
					price.setDiscountPercentageLevel2(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "discountPercentageLevel3")) {

				if (jsonParserFieldValue != null) {
					price.setDiscountPercentageLevel3(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "discountPercentageLevel4")) {

				if (jsonParserFieldValue != null) {
					price.setDiscountPercentageLevel4(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "finalPrice")) {
				if (jsonParserFieldValue != null) {
					price.setFinalPrice(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "finalPriceFormatted")) {

				if (jsonParserFieldValue != null) {
					price.setFinalPriceFormatted((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "price")) {
				if (jsonParserFieldValue != null) {
					price.setPrice(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priceFormatted")) {
				if (jsonParserFieldValue != null) {
					price.setPriceFormatted((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "promoPrice")) {
				if (jsonParserFieldValue != null) {
					price.setPromoPrice(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "promoPriceFormatted")) {

				if (jsonParserFieldValue != null) {
					price.setPromoPriceFormatted((String)jsonParserFieldValue);
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