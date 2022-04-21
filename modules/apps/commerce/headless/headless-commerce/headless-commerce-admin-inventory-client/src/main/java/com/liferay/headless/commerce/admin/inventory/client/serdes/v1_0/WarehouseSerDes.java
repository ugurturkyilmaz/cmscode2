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

package com.liferay.headless.commerce.admin.inventory.client.serdes.v1_0;

import com.liferay.headless.commerce.admin.inventory.client.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.inventory.client.dto.v1_0.WarehouseItem;
import com.liferay.headless.commerce.admin.inventory.client.json.BaseJSONParser;

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
public class WarehouseSerDes {

	public static Warehouse toDTO(String json) {
		WarehouseJSONParser warehouseJSONParser = new WarehouseJSONParser();

		return warehouseJSONParser.parseToDTO(json);
	}

	public static Warehouse[] toDTOs(String json) {
		WarehouseJSONParser warehouseJSONParser = new WarehouseJSONParser();

		return warehouseJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Warehouse warehouse) {
		if (warehouse == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (warehouse.getActive() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(warehouse.getActive());
		}

		if (warehouse.getCity() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"city\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getCity()));

			sb.append("\"");
		}

		if (warehouse.getCountryISOCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryISOCode\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getCountryISOCode()));

			sb.append("\"");
		}

		if (warehouse.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getDescription()));

			sb.append("\"");
		}

		if (warehouse.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (warehouse.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(warehouse.getId());
		}

		if (warehouse.getLatitude() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"latitude\": ");

			sb.append(warehouse.getLatitude());
		}

		if (warehouse.getLongitude() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"longitude\": ");

			sb.append(warehouse.getLongitude());
		}

		if (warehouse.getMvccVersion() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"mvccVersion\": ");

			sb.append(warehouse.getMvccVersion());
		}

		if (warehouse.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getName()));

			sb.append("\"");
		}

		if (warehouse.getRegionISOCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"regionISOCode\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getRegionISOCode()));

			sb.append("\"");
		}

		if (warehouse.getStreet1() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"street1\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getStreet1()));

			sb.append("\"");
		}

		if (warehouse.getStreet2() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"street2\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getStreet2()));

			sb.append("\"");
		}

		if (warehouse.getStreet3() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"street3\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getStreet3()));

			sb.append("\"");
		}

		if (warehouse.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getType()));

			sb.append("\"");
		}

		if (warehouse.getWarehouseItems() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"warehouseItems\": ");

			sb.append("[");

			for (int i = 0; i < warehouse.getWarehouseItems().length; i++) {
				sb.append(String.valueOf(warehouse.getWarehouseItems()[i]));

				if ((i + 1) < warehouse.getWarehouseItems().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (warehouse.getZip() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"zip\": ");

			sb.append("\"");

			sb.append(_escape(warehouse.getZip()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		WarehouseJSONParser warehouseJSONParser = new WarehouseJSONParser();

		return warehouseJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Warehouse warehouse) {
		if (warehouse == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (warehouse.getActive() == null) {
			map.put("active", null);
		}
		else {
			map.put("active", String.valueOf(warehouse.getActive()));
		}

		if (warehouse.getCity() == null) {
			map.put("city", null);
		}
		else {
			map.put("city", String.valueOf(warehouse.getCity()));
		}

		if (warehouse.getCountryISOCode() == null) {
			map.put("countryISOCode", null);
		}
		else {
			map.put(
				"countryISOCode",
				String.valueOf(warehouse.getCountryISOCode()));
		}

		if (warehouse.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put("description", String.valueOf(warehouse.getDescription()));
		}

		if (warehouse.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(warehouse.getExternalReferenceCode()));
		}

		if (warehouse.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(warehouse.getId()));
		}

		if (warehouse.getLatitude() == null) {
			map.put("latitude", null);
		}
		else {
			map.put("latitude", String.valueOf(warehouse.getLatitude()));
		}

		if (warehouse.getLongitude() == null) {
			map.put("longitude", null);
		}
		else {
			map.put("longitude", String.valueOf(warehouse.getLongitude()));
		}

		if (warehouse.getMvccVersion() == null) {
			map.put("mvccVersion", null);
		}
		else {
			map.put("mvccVersion", String.valueOf(warehouse.getMvccVersion()));
		}

		if (warehouse.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(warehouse.getName()));
		}

		if (warehouse.getRegionISOCode() == null) {
			map.put("regionISOCode", null);
		}
		else {
			map.put(
				"regionISOCode", String.valueOf(warehouse.getRegionISOCode()));
		}

		if (warehouse.getStreet1() == null) {
			map.put("street1", null);
		}
		else {
			map.put("street1", String.valueOf(warehouse.getStreet1()));
		}

		if (warehouse.getStreet2() == null) {
			map.put("street2", null);
		}
		else {
			map.put("street2", String.valueOf(warehouse.getStreet2()));
		}

		if (warehouse.getStreet3() == null) {
			map.put("street3", null);
		}
		else {
			map.put("street3", String.valueOf(warehouse.getStreet3()));
		}

		if (warehouse.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(warehouse.getType()));
		}

		if (warehouse.getWarehouseItems() == null) {
			map.put("warehouseItems", null);
		}
		else {
			map.put(
				"warehouseItems",
				String.valueOf(warehouse.getWarehouseItems()));
		}

		if (warehouse.getZip() == null) {
			map.put("zip", null);
		}
		else {
			map.put("zip", String.valueOf(warehouse.getZip()));
		}

		return map;
	}

	public static class WarehouseJSONParser extends BaseJSONParser<Warehouse> {

		@Override
		protected Warehouse createDTO() {
			return new Warehouse();
		}

		@Override
		protected Warehouse[] createDTOArray(int size) {
			return new Warehouse[size];
		}

		@Override
		protected void setField(
			Warehouse warehouse, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "active")) {
				if (jsonParserFieldValue != null) {
					warehouse.setActive((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "city")) {
				if (jsonParserFieldValue != null) {
					warehouse.setCity((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "countryISOCode")) {
				if (jsonParserFieldValue != null) {
					warehouse.setCountryISOCode((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					warehouse.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					warehouse.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					warehouse.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "latitude")) {
				if (jsonParserFieldValue != null) {
					warehouse.setLatitude(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "longitude")) {
				if (jsonParserFieldValue != null) {
					warehouse.setLongitude(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "mvccVersion")) {
				if (jsonParserFieldValue != null) {
					warehouse.setMvccVersion(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					warehouse.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "regionISOCode")) {
				if (jsonParserFieldValue != null) {
					warehouse.setRegionISOCode((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "street1")) {
				if (jsonParserFieldValue != null) {
					warehouse.setStreet1((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "street2")) {
				if (jsonParserFieldValue != null) {
					warehouse.setStreet2((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "street3")) {
				if (jsonParserFieldValue != null) {
					warehouse.setStreet3((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					warehouse.setType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "warehouseItems")) {
				if (jsonParserFieldValue != null) {
					warehouse.setWarehouseItems(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> WarehouseItemSerDes.toDTO((String)object)
						).toArray(
							size -> new WarehouseItem[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "zip")) {
				if (jsonParserFieldValue != null) {
					warehouse.setZip((String)jsonParserFieldValue);
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