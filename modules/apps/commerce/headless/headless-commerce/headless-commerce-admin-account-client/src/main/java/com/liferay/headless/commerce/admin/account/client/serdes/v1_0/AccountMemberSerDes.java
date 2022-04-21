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

import com.liferay.headless.commerce.admin.account.client.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.client.dto.v1_0.AccountRole;
import com.liferay.headless.commerce.admin.account.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class AccountMemberSerDes {

	public static AccountMember toDTO(String json) {
		AccountMemberJSONParser accountMemberJSONParser =
			new AccountMemberJSONParser();

		return accountMemberJSONParser.parseToDTO(json);
	}

	public static AccountMember[] toDTOs(String json) {
		AccountMemberJSONParser accountMemberJSONParser =
			new AccountMemberJSONParser();

		return accountMemberJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AccountMember accountMember) {
		if (accountMember == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (accountMember.getAccountId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountId\": ");

			sb.append(accountMember.getAccountId());
		}

		if (accountMember.getAccountRoles() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountRoles\": ");

			sb.append("[");

			for (int i = 0; i < accountMember.getAccountRoles().length; i++) {
				sb.append(String.valueOf(accountMember.getAccountRoles()[i]));

				if ((i + 1) < accountMember.getAccountRoles().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (accountMember.getEmail() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"email\": ");

			sb.append("\"");

			sb.append(_escape(accountMember.getEmail()));

			sb.append("\"");
		}

		if (accountMember.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(accountMember.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (accountMember.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(accountMember.getName()));

			sb.append("\"");
		}

		if (accountMember.getUserExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(accountMember.getUserExternalReferenceCode()));

			sb.append("\"");
		}

		if (accountMember.getUserId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(accountMember.getUserId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AccountMemberJSONParser accountMemberJSONParser =
			new AccountMemberJSONParser();

		return accountMemberJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AccountMember accountMember) {
		if (accountMember == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (accountMember.getAccountId() == null) {
			map.put("accountId", null);
		}
		else {
			map.put("accountId", String.valueOf(accountMember.getAccountId()));
		}

		if (accountMember.getAccountRoles() == null) {
			map.put("accountRoles", null);
		}
		else {
			map.put(
				"accountRoles",
				String.valueOf(accountMember.getAccountRoles()));
		}

		if (accountMember.getEmail() == null) {
			map.put("email", null);
		}
		else {
			map.put("email", String.valueOf(accountMember.getEmail()));
		}

		if (accountMember.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(accountMember.getExternalReferenceCode()));
		}

		if (accountMember.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(accountMember.getName()));
		}

		if (accountMember.getUserExternalReferenceCode() == null) {
			map.put("userExternalReferenceCode", null);
		}
		else {
			map.put(
				"userExternalReferenceCode",
				String.valueOf(accountMember.getUserExternalReferenceCode()));
		}

		if (accountMember.getUserId() == null) {
			map.put("userId", null);
		}
		else {
			map.put("userId", String.valueOf(accountMember.getUserId()));
		}

		return map;
	}

	public static class AccountMemberJSONParser
		extends BaseJSONParser<AccountMember> {

		@Override
		protected AccountMember createDTO() {
			return new AccountMember();
		}

		@Override
		protected AccountMember[] createDTOArray(int size) {
			return new AccountMember[size];
		}

		@Override
		protected void setField(
			AccountMember accountMember, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "accountId")) {
				if (jsonParserFieldValue != null) {
					accountMember.setAccountId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "accountRoles")) {
				if (jsonParserFieldValue != null) {
					accountMember.setAccountRoles(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> AccountRoleSerDes.toDTO((String)object)
						).toArray(
							size -> new AccountRole[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "email")) {
				if (jsonParserFieldValue != null) {
					accountMember.setEmail((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					accountMember.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					accountMember.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "userExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					accountMember.setUserExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userId")) {
				if (jsonParserFieldValue != null) {
					accountMember.setUserId(
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