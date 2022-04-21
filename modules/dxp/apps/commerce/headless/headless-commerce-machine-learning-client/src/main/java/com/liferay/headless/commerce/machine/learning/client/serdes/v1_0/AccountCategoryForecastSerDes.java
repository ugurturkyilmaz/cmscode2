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

package com.liferay.headless.commerce.machine.learning.client.serdes.v1_0;

import com.liferay.headless.commerce.machine.learning.client.dto.v1_0.AccountCategoryForecast;
import com.liferay.headless.commerce.machine.learning.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Riccardo Ferrari
 * @generated
 */
@Generated("")
public class AccountCategoryForecastSerDes {

	public static AccountCategoryForecast toDTO(String json) {
		AccountCategoryForecastJSONParser accountCategoryForecastJSONParser =
			new AccountCategoryForecastJSONParser();

		return accountCategoryForecastJSONParser.parseToDTO(json);
	}

	public static AccountCategoryForecast[] toDTOs(String json) {
		AccountCategoryForecastJSONParser accountCategoryForecastJSONParser =
			new AccountCategoryForecastJSONParser();

		return accountCategoryForecastJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		AccountCategoryForecast accountCategoryForecast) {

		if (accountCategoryForecast == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (accountCategoryForecast.getAccount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"account\": ");

			sb.append(accountCategoryForecast.getAccount());
		}

		if (accountCategoryForecast.getActual() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actual\": ");

			sb.append(accountCategoryForecast.getActual());
		}

		if (accountCategoryForecast.getCategory() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"category\": ");

			sb.append(accountCategoryForecast.getCategory());
		}

		if (accountCategoryForecast.getCategoryTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"categoryTitle\": ");

			sb.append("\"");

			sb.append(_escape(accountCategoryForecast.getCategoryTitle()));

			sb.append("\"");
		}

		if (accountCategoryForecast.getForecast() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"forecast\": ");

			sb.append(accountCategoryForecast.getForecast());
		}

		if (accountCategoryForecast.getForecastLowerBound() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"forecastLowerBound\": ");

			sb.append(accountCategoryForecast.getForecastLowerBound());
		}

		if (accountCategoryForecast.getForecastUpperBound() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"forecastUpperBound\": ");

			sb.append(accountCategoryForecast.getForecastUpperBound());
		}

		if (accountCategoryForecast.getTimestamp() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"timestamp\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					accountCategoryForecast.getTimestamp()));

			sb.append("\"");
		}

		if (accountCategoryForecast.getUnit() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"unit\": ");

			sb.append("\"");

			sb.append(_escape(accountCategoryForecast.getUnit()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AccountCategoryForecastJSONParser accountCategoryForecastJSONParser =
			new AccountCategoryForecastJSONParser();

		return accountCategoryForecastJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		AccountCategoryForecast accountCategoryForecast) {

		if (accountCategoryForecast == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (accountCategoryForecast.getAccount() == null) {
			map.put("account", null);
		}
		else {
			map.put(
				"account",
				String.valueOf(accountCategoryForecast.getAccount()));
		}

		if (accountCategoryForecast.getActual() == null) {
			map.put("actual", null);
		}
		else {
			map.put(
				"actual", String.valueOf(accountCategoryForecast.getActual()));
		}

		if (accountCategoryForecast.getCategory() == null) {
			map.put("category", null);
		}
		else {
			map.put(
				"category",
				String.valueOf(accountCategoryForecast.getCategory()));
		}

		if (accountCategoryForecast.getCategoryTitle() == null) {
			map.put("categoryTitle", null);
		}
		else {
			map.put(
				"categoryTitle",
				String.valueOf(accountCategoryForecast.getCategoryTitle()));
		}

		if (accountCategoryForecast.getForecast() == null) {
			map.put("forecast", null);
		}
		else {
			map.put(
				"forecast",
				String.valueOf(accountCategoryForecast.getForecast()));
		}

		if (accountCategoryForecast.getForecastLowerBound() == null) {
			map.put("forecastLowerBound", null);
		}
		else {
			map.put(
				"forecastLowerBound",
				String.valueOf(
					accountCategoryForecast.getForecastLowerBound()));
		}

		if (accountCategoryForecast.getForecastUpperBound() == null) {
			map.put("forecastUpperBound", null);
		}
		else {
			map.put(
				"forecastUpperBound",
				String.valueOf(
					accountCategoryForecast.getForecastUpperBound()));
		}

		if (accountCategoryForecast.getTimestamp() == null) {
			map.put("timestamp", null);
		}
		else {
			map.put(
				"timestamp",
				liferayToJSONDateFormat.format(
					accountCategoryForecast.getTimestamp()));
		}

		if (accountCategoryForecast.getUnit() == null) {
			map.put("unit", null);
		}
		else {
			map.put("unit", String.valueOf(accountCategoryForecast.getUnit()));
		}

		return map;
	}

	public static class AccountCategoryForecastJSONParser
		extends BaseJSONParser<AccountCategoryForecast> {

		@Override
		protected AccountCategoryForecast createDTO() {
			return new AccountCategoryForecast();
		}

		@Override
		protected AccountCategoryForecast[] createDTOArray(int size) {
			return new AccountCategoryForecast[size];
		}

		@Override
		protected void setField(
			AccountCategoryForecast accountCategoryForecast,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "account")) {
				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setAccount(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "actual")) {
				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setActual(
						Float.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "category")) {
				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setCategory(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "categoryTitle")) {
				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setCategoryTitle(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "forecast")) {
				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setForecast(
						Float.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "forecastLowerBound")) {

				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setForecastLowerBound(
						Float.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "forecastUpperBound")) {

				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setForecastUpperBound(
						Float.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "timestamp")) {
				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setTimestamp(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "unit")) {
				if (jsonParserFieldValue != null) {
					accountCategoryForecast.setUnit(
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