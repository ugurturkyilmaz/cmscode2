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

package com.liferay.headless.admin.user.dto.v1_0;

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
	description = "The organization's postal information (country and region).",
	value = "Location"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Location")
public class Location implements Serializable {

	public static Location toDTO(String json) {
		return ObjectMapperUtil.readValue(Location.class, json);
	}

	public static Location unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Location.class, json);
	}

	@Schema(
		description = "The organization's country. This follows the [`addressCountry`](https://schema.org/addressCountry) specification."
	)
	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	@JsonIgnore
	public void setAddressCountry(
		UnsafeSupplier<String, Exception> addressCountryUnsafeSupplier) {

		try {
			addressCountry = addressCountryUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The organization's country. This follows the [`addressCountry`](https://schema.org/addressCountry) specification."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String addressCountry;

	@Schema
	@Valid
	public Map<String, String> getAddressCountry_i18n() {
		return addressCountry_i18n;
	}

	public void setAddressCountry_i18n(
		Map<String, String> addressCountry_i18n) {

		this.addressCountry_i18n = addressCountry_i18n;
	}

	@JsonIgnore
	public void setAddressCountry_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			addressCountry_i18nUnsafeSupplier) {

		try {
			addressCountry_i18n = addressCountry_i18nUnsafeSupplier.get();
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
	protected Map<String, String> addressCountry_i18n;

	@Schema(
		description = "The organization's region. This follows the [`addressRegion`](https://schema.org/addressRegion) specification."
	)
	public String getAddressRegion() {
		return addressRegion;
	}

	public void setAddressRegion(String addressRegion) {
		this.addressRegion = addressRegion;
	}

	@JsonIgnore
	public void setAddressRegion(
		UnsafeSupplier<String, Exception> addressRegionUnsafeSupplier) {

		try {
			addressRegion = addressRegionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The organization's region. This follows the [`addressRegion`](https://schema.org/addressRegion) specification."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String addressRegion;

	@Schema(description = "The location's ID.")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The location's ID.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long id;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Location)) {
			return false;
		}

		Location location = (Location)object;

		return Objects.equals(toString(), location.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (addressCountry != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"addressCountry\": ");

			sb.append("\"");

			sb.append(_escape(addressCountry));

			sb.append("\"");
		}

		if (addressCountry_i18n != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"addressCountry_i18n\": ");

			sb.append(_toJSON(addressCountry_i18n));
		}

		if (addressRegion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"addressRegion\": ");

			sb.append("\"");

			sb.append(_escape(addressRegion));

			sb.append("\"");
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.admin.user.dto.v1_0.Location",
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