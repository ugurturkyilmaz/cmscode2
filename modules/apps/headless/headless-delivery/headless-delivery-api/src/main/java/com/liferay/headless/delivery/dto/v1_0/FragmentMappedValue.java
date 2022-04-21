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

package com.liferay.headless.delivery.dto.v1_0;

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
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Represents a fragment mapped value.",
	value = "FragmentMappedValue"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "FragmentMappedValue")
public class FragmentMappedValue implements Serializable {

	public static FragmentMappedValue toDTO(String json) {
		return ObjectMapperUtil.readValue(FragmentMappedValue.class, json);
	}

	public static FragmentMappedValue unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			FragmentMappedValue.class, json);
	}

	@Schema(description = "The default value of the fragment mapped value.")
	@Valid
	public FragmentInlineValue getDefaultFragmentInlineValue() {
		return defaultFragmentInlineValue;
	}

	public void setDefaultFragmentInlineValue(
		FragmentInlineValue defaultFragmentInlineValue) {

		this.defaultFragmentInlineValue = defaultFragmentInlineValue;
	}

	@JsonIgnore
	public void setDefaultFragmentInlineValue(
		UnsafeSupplier<FragmentInlineValue, Exception>
			defaultFragmentInlineValueUnsafeSupplier) {

		try {
			defaultFragmentInlineValue =
				defaultFragmentInlineValueUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The default value of the fragment mapped value."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected FragmentInlineValue defaultFragmentInlineValue;

	@Schema(
		deprecated = true,
		description = "Deprecated as of Athanasius (7.3.x), replaced by defaultFragmentInlineValue"
	)
	@Valid
	public DefaultValue getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(DefaultValue defaultValue) {
		this.defaultValue = defaultValue;
	}

	@JsonIgnore
	public void setDefaultValue(
		UnsafeSupplier<DefaultValue, Exception> defaultValueUnsafeSupplier) {

		try {
			defaultValue = defaultValueUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Deprecated
	@GraphQLField(
		description = "Deprecated as of Athanasius (7.3.x), replaced by defaultFragmentInlineValue"
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected DefaultValue defaultValue;

	@Schema(description = "The mapping of the fragment mapped value.")
	@Valid
	public Mapping getMapping() {
		return mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	@JsonIgnore
	public void setMapping(
		UnsafeSupplier<Mapping, Exception> mappingUnsafeSupplier) {

		try {
			mapping = mappingUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The mapping of the fragment mapped value.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Mapping mapping;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FragmentMappedValue)) {
			return false;
		}

		FragmentMappedValue fragmentMappedValue = (FragmentMappedValue)object;

		return Objects.equals(toString(), fragmentMappedValue.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (defaultFragmentInlineValue != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultFragmentInlineValue\": ");

			sb.append(String.valueOf(defaultFragmentInlineValue));
		}

		if (defaultValue != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultValue\": ");

			sb.append(String.valueOf(defaultValue));
		}

		if (mapping != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"mapping\": ");

			sb.append(String.valueOf(mapping));
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.delivery.dto.v1_0.FragmentMappedValue",
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