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

package com.liferay.headless.commerce.admin.pricing.client.serdes.v2_0;

import com.liferay.headless.commerce.admin.pricing.client.dto.v2_0.TierPrice;
import com.liferay.headless.commerce.admin.pricing.client.json.BaseJSONParser;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
public class TierPriceSerDes {

	public static TierPrice toDTO(String json) {
		TierPriceJSONParser tierPriceJSONParser = new TierPriceJSONParser();

		return tierPriceJSONParser.parseToDTO(json);
	}

	public static TierPrice[] toDTOs(String json) {
		TierPriceJSONParser tierPriceJSONParser = new TierPriceJSONParser();

		return tierPriceJSONParser.parseToDTOs(json);
	}

	public static String toJSON(TierPrice tierPrice) {
		if (tierPrice == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (tierPrice.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(tierPrice.getActions()));
		}

		if (tierPrice.getActive() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(tierPrice.getActive());
		}

		if (tierPrice.getCustomFields() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customFields\": ");

			sb.append(_toJSON(tierPrice.getCustomFields()));
		}

		if (tierPrice.getDiscountDiscovery() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountDiscovery\": ");

			sb.append(tierPrice.getDiscountDiscovery());
		}

		if (tierPrice.getDiscountLevel1() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountLevel1\": ");

			sb.append(tierPrice.getDiscountLevel1());
		}

		if (tierPrice.getDiscountLevel2() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountLevel2\": ");

			sb.append(tierPrice.getDiscountLevel2());
		}

		if (tierPrice.getDiscountLevel3() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountLevel3\": ");

			sb.append(tierPrice.getDiscountLevel3());
		}

		if (tierPrice.getDiscountLevel4() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"discountLevel4\": ");

			sb.append(tierPrice.getDiscountLevel4());
		}

		if (tierPrice.getDisplayDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"displayDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(tierPrice.getDisplayDate()));

			sb.append("\"");
		}

		if (tierPrice.getExpirationDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"expirationDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(tierPrice.getExpirationDate()));

			sb.append("\"");
		}

		if (tierPrice.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(tierPrice.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (tierPrice.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(tierPrice.getId());
		}

		if (tierPrice.getMinimumQuantity() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"minimumQuantity\": ");

			sb.append(tierPrice.getMinimumQuantity());
		}

		if (tierPrice.getNeverExpire() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"neverExpire\": ");

			sb.append(tierPrice.getNeverExpire());
		}

		if (tierPrice.getPrice() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"price\": ");

			sb.append(tierPrice.getPrice());
		}

		if (tierPrice.getPriceEntryExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priceEntryExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(tierPrice.getPriceEntryExternalReferenceCode()));

			sb.append("\"");
		}

		if (tierPrice.getPriceEntryId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priceEntryId\": ");

			sb.append(tierPrice.getPriceEntryId());
		}

		if (tierPrice.getPriceFormatted() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priceFormatted\": ");

			sb.append("\"");

			sb.append(_escape(tierPrice.getPriceFormatted()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TierPriceJSONParser tierPriceJSONParser = new TierPriceJSONParser();

		return tierPriceJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(TierPrice tierPrice) {
		if (tierPrice == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (tierPrice.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(tierPrice.getActions()));
		}

		if (tierPrice.getActive() == null) {
			map.put("active", null);
		}
		else {
			map.put("active", String.valueOf(tierPrice.getActive()));
		}

		if (tierPrice.getCustomFields() == null) {
			map.put("customFields", null);
		}
		else {
			map.put(
				"customFields", String.valueOf(tierPrice.getCustomFields()));
		}

		if (tierPrice.getDiscountDiscovery() == null) {
			map.put("discountDiscovery", null);
		}
		else {
			map.put(
				"discountDiscovery",
				String.valueOf(tierPrice.getDiscountDiscovery()));
		}

		if (tierPrice.getDiscountLevel1() == null) {
			map.put("discountLevel1", null);
		}
		else {
			map.put(
				"discountLevel1",
				String.valueOf(tierPrice.getDiscountLevel1()));
		}

		if (tierPrice.getDiscountLevel2() == null) {
			map.put("discountLevel2", null);
		}
		else {
			map.put(
				"discountLevel2",
				String.valueOf(tierPrice.getDiscountLevel2()));
		}

		if (tierPrice.getDiscountLevel3() == null) {
			map.put("discountLevel3", null);
		}
		else {
			map.put(
				"discountLevel3",
				String.valueOf(tierPrice.getDiscountLevel3()));
		}

		if (tierPrice.getDiscountLevel4() == null) {
			map.put("discountLevel4", null);
		}
		else {
			map.put(
				"discountLevel4",
				String.valueOf(tierPrice.getDiscountLevel4()));
		}

		if (tierPrice.getDisplayDate() == null) {
			map.put("displayDate", null);
		}
		else {
			map.put(
				"displayDate",
				liferayToJSONDateFormat.format(tierPrice.getDisplayDate()));
		}

		if (tierPrice.getExpirationDate() == null) {
			map.put("expirationDate", null);
		}
		else {
			map.put(
				"expirationDate",
				liferayToJSONDateFormat.format(tierPrice.getExpirationDate()));
		}

		if (tierPrice.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(tierPrice.getExternalReferenceCode()));
		}

		if (tierPrice.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(tierPrice.getId()));
		}

		if (tierPrice.getMinimumQuantity() == null) {
			map.put("minimumQuantity", null);
		}
		else {
			map.put(
				"minimumQuantity",
				String.valueOf(tierPrice.getMinimumQuantity()));
		}

		if (tierPrice.getNeverExpire() == null) {
			map.put("neverExpire", null);
		}
		else {
			map.put("neverExpire", String.valueOf(tierPrice.getNeverExpire()));
		}

		if (tierPrice.getPrice() == null) {
			map.put("price", null);
		}
		else {
			map.put("price", String.valueOf(tierPrice.getPrice()));
		}

		if (tierPrice.getPriceEntryExternalReferenceCode() == null) {
			map.put("priceEntryExternalReferenceCode", null);
		}
		else {
			map.put(
				"priceEntryExternalReferenceCode",
				String.valueOf(tierPrice.getPriceEntryExternalReferenceCode()));
		}

		if (tierPrice.getPriceEntryId() == null) {
			map.put("priceEntryId", null);
		}
		else {
			map.put(
				"priceEntryId", String.valueOf(tierPrice.getPriceEntryId()));
		}

		if (tierPrice.getPriceFormatted() == null) {
			map.put("priceFormatted", null);
		}
		else {
			map.put(
				"priceFormatted",
				String.valueOf(tierPrice.getPriceFormatted()));
		}

		return map;
	}

	public static class TierPriceJSONParser extends BaseJSONParser<TierPrice> {

		@Override
		protected TierPrice createDTO() {
			return new TierPrice();
		}

		@Override
		protected TierPrice[] createDTOArray(int size) {
			return new TierPrice[size];
		}

		@Override
		protected void setField(
			TierPrice tierPrice, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setActions(
						(Map)TierPriceSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "active")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setActive((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "customFields")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setCustomFields(
						(Map)TierPriceSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "discountDiscovery")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setDiscountDiscovery(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "discountLevel1")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setDiscountLevel1(
						new BigDecimal((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "discountLevel2")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setDiscountLevel2(
						new BigDecimal((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "discountLevel3")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setDiscountLevel3(
						new BigDecimal((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "discountLevel4")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setDiscountLevel4(
						new BigDecimal((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "displayDate")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setDisplayDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "expirationDate")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setExpirationDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					tierPrice.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "minimumQuantity")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setMinimumQuantity(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "neverExpire")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setNeverExpire((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "price")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setPrice(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"priceEntryExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					tierPrice.setPriceEntryExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priceEntryId")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setPriceEntryId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priceFormatted")) {
				if (jsonParserFieldValue != null) {
					tierPrice.setPriceFormatted((String)jsonParserFieldValue);
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