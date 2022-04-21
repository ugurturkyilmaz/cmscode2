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
	description = "Deprecated as of Athanasius (7.3.x), replaced by rowViewports",
	value = "RowViewportConfig"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "RowViewportConfig")
public class RowViewportConfig implements Serializable {

	public static RowViewportConfig toDTO(String json) {
		return ObjectMapperUtil.readValue(RowViewportConfig.class, json);
	}

	public static RowViewportConfig unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(RowViewportConfig.class, json);
	}

	@Schema
	@Valid
	public LandscapeMobile getLandscapeMobile() {
		return landscapeMobile;
	}

	public void setLandscapeMobile(LandscapeMobile landscapeMobile) {
		this.landscapeMobile = landscapeMobile;
	}

	@JsonIgnore
	public void setLandscapeMobile(
		UnsafeSupplier<LandscapeMobile, Exception>
			landscapeMobileUnsafeSupplier) {

		try {
			landscapeMobile = landscapeMobileUnsafeSupplier.get();
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
	protected LandscapeMobile landscapeMobile;

	@Schema
	@Valid
	public PortraitMobile getPortraitMobile() {
		return portraitMobile;
	}

	public void setPortraitMobile(PortraitMobile portraitMobile) {
		this.portraitMobile = portraitMobile;
	}

	@JsonIgnore
	public void setPortraitMobile(
		UnsafeSupplier<PortraitMobile, Exception>
			portraitMobileUnsafeSupplier) {

		try {
			portraitMobile = portraitMobileUnsafeSupplier.get();
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
	protected PortraitMobile portraitMobile;

	@Schema
	@Valid
	public Tablet getTablet() {
		return tablet;
	}

	public void setTablet(Tablet tablet) {
		this.tablet = tablet;
	}

	@JsonIgnore
	public void setTablet(
		UnsafeSupplier<Tablet, Exception> tabletUnsafeSupplier) {

		try {
			tablet = tabletUnsafeSupplier.get();
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
	protected Tablet tablet;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RowViewportConfig)) {
			return false;
		}

		RowViewportConfig rowViewportConfig = (RowViewportConfig)object;

		return Objects.equals(toString(), rowViewportConfig.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (landscapeMobile != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"landscapeMobile\": ");

			sb.append(String.valueOf(landscapeMobile));
		}

		if (portraitMobile != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"portraitMobile\": ");

			sb.append(String.valueOf(portraitMobile));
		}

		if (tablet != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"tablet\": ");

			sb.append(String.valueOf(tablet));
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.delivery.dto.v1_0.RowViewportConfig",
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