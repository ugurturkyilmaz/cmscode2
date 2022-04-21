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

package com.liferay.data.engine.rest.dto.v2_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jeyvison Nascimento
 * @generated
 */
@Generated("")
@GraphQLName("DataDefinitionFieldLink")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "DataDefinitionFieldLink")
public class DataDefinitionFieldLink implements Serializable {

	public static DataDefinitionFieldLink toDTO(String json) {
		return ObjectMapperUtil.readValue(DataDefinitionFieldLink.class, json);
	}

	public static DataDefinitionFieldLink unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			DataDefinitionFieldLink.class, json);
	}

	@Schema
	@Valid
	public DataDefinition getDataDefinition() {
		return dataDefinition;
	}

	public void setDataDefinition(DataDefinition dataDefinition) {
		this.dataDefinition = dataDefinition;
	}

	@JsonIgnore
	public void setDataDefinition(
		UnsafeSupplier<DataDefinition, Exception>
			dataDefinitionUnsafeSupplier) {

		try {
			dataDefinition = dataDefinitionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected DataDefinition dataDefinition;

	@Schema
	@Valid
	public DataLayout[] getDataLayouts() {
		return dataLayouts;
	}

	public void setDataLayouts(DataLayout[] dataLayouts) {
		this.dataLayouts = dataLayouts;
	}

	@JsonIgnore
	public void setDataLayouts(
		UnsafeSupplier<DataLayout[], Exception> dataLayoutsUnsafeSupplier) {

		try {
			dataLayouts = dataLayoutsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected DataLayout[] dataLayouts;

	@Schema
	@Valid
	public DataListView[] getDataListViews() {
		return dataListViews;
	}

	public void setDataListViews(DataListView[] dataListViews) {
		this.dataListViews = dataListViews;
	}

	@JsonIgnore
	public void setDataListViews(
		UnsafeSupplier<DataListView[], Exception> dataListViewsUnsafeSupplier) {

		try {
			dataListViews = dataListViewsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected DataListView[] dataListViews;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DataDefinitionFieldLink)) {
			return false;
		}

		DataDefinitionFieldLink dataDefinitionFieldLink =
			(DataDefinitionFieldLink)object;

		return Objects.equals(toString(), dataDefinitionFieldLink.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (dataDefinition != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dataDefinition\": ");

			sb.append(String.valueOf(dataDefinition));
		}

		if (dataLayouts != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dataLayouts\": ");

			sb.append("[");

			for (int i = 0; i < dataLayouts.length; i++) {
				sb.append(String.valueOf(dataLayouts[i]));

				if ((i + 1) < dataLayouts.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (dataListViews != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dataListViews\": ");

			sb.append("[");

			for (int i = 0; i < dataListViews.length; i++) {
				sb.append(String.valueOf(dataListViews[i]));

				if ((i + 1) < dataListViews.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.data.engine.rest.dto.v2_0.DataDefinitionFieldLink",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
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
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

}