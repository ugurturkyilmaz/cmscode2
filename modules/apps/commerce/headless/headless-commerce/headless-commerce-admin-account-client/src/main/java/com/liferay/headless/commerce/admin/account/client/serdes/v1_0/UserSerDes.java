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

package com.liferay.headless.commerce.admin.account.client.serdes.v1_0;

import com.liferay.headless.commerce.admin.account.client.dto.v1_0.User;
import com.liferay.headless.commerce.admin.account.client.json.BaseJSONParser;

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
public class UserSerDes {

	public static User toDTO(String json) {
		UserJSONParser userJSONParser = new UserJSONParser();

		return userJSONParser.parseToDTO(json);
	}

	public static User[] toDTOs(String json) {
		UserJSONParser userJSONParser = new UserJSONParser();

		return userJSONParser.parseToDTOs(json);
	}

	public static String toJSON(User user) {
		if (user == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (user.getEmail() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"email\": ");

			sb.append("\"");

			sb.append(_escape(user.getEmail()));

			sb.append("\"");
		}

		if (user.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(user.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (user.getFirstName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"firstName\": ");

			sb.append("\"");

			sb.append(_escape(user.getFirstName()));

			sb.append("\"");
		}

		if (user.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(user.getId());
		}

		if (user.getJobTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"jobTitle\": ");

			sb.append("\"");

			sb.append(_escape(user.getJobTitle()));

			sb.append("\"");
		}

		if (user.getLastName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastName\": ");

			sb.append("\"");

			sb.append(_escape(user.getLastName()));

			sb.append("\"");
		}

		if (user.getMale() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"male\": ");

			sb.append(user.getMale());
		}

		if (user.getMiddleName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"middleName\": ");

			sb.append("\"");

			sb.append(_escape(user.getMiddleName()));

			sb.append("\"");
		}

		if (user.getRoles() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roles\": ");

			sb.append("[");

			for (int i = 0; i < user.getRoles().length; i++) {
				sb.append("\"");

				sb.append(_escape(user.getRoles()[i]));

				sb.append("\"");

				if ((i + 1) < user.getRoles().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		UserJSONParser userJSONParser = new UserJSONParser();

		return userJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(User user) {
		if (user == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (user.getEmail() == null) {
			map.put("email", null);
		}
		else {
			map.put("email", String.valueOf(user.getEmail()));
		}

		if (user.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(user.getExternalReferenceCode()));
		}

		if (user.getFirstName() == null) {
			map.put("firstName", null);
		}
		else {
			map.put("firstName", String.valueOf(user.getFirstName()));
		}

		if (user.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(user.getId()));
		}

		if (user.getJobTitle() == null) {
			map.put("jobTitle", null);
		}
		else {
			map.put("jobTitle", String.valueOf(user.getJobTitle()));
		}

		if (user.getLastName() == null) {
			map.put("lastName", null);
		}
		else {
			map.put("lastName", String.valueOf(user.getLastName()));
		}

		if (user.getMale() == null) {
			map.put("male", null);
		}
		else {
			map.put("male", String.valueOf(user.getMale()));
		}

		if (user.getMiddleName() == null) {
			map.put("middleName", null);
		}
		else {
			map.put("middleName", String.valueOf(user.getMiddleName()));
		}

		if (user.getRoles() == null) {
			map.put("roles", null);
		}
		else {
			map.put("roles", String.valueOf(user.getRoles()));
		}

		return map;
	}

	public static class UserJSONParser extends BaseJSONParser<User> {

		@Override
		protected User createDTO() {
			return new User();
		}

		@Override
		protected User[] createDTOArray(int size) {
			return new User[size];
		}

		@Override
		protected void setField(
			User user, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "email")) {
				if (jsonParserFieldValue != null) {
					user.setEmail((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					user.setExternalReferenceCode((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "firstName")) {
				if (jsonParserFieldValue != null) {
					user.setFirstName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					user.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "jobTitle")) {
				if (jsonParserFieldValue != null) {
					user.setJobTitle((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lastName")) {
				if (jsonParserFieldValue != null) {
					user.setLastName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "male")) {
				if (jsonParserFieldValue != null) {
					user.setMale((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "middleName")) {
				if (jsonParserFieldValue != null) {
					user.setMiddleName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roles")) {
				if (jsonParserFieldValue != null) {
					user.setRoles(toStrings((Object[])jsonParserFieldValue));
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