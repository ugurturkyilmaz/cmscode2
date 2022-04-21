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

package com.liferay.search.experiences.rest.dto.v1_0;

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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@Generated("")
@GraphQLName("HighlightField")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "HighlightField")
public class HighlightField implements Serializable {

	public static HighlightField toDTO(String json) {
		return ObjectMapperUtil.readValue(HighlightField.class, json);
	}

	public static HighlightField unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(HighlightField.class, json);
	}

	@Schema
	public Integer getFragment_offset() {
		return fragment_offset;
	}

	public void setFragment_offset(Integer fragment_offset) {
		this.fragment_offset = fragment_offset;
	}

	@JsonIgnore
	public void setFragment_offset(
		UnsafeSupplier<Integer, Exception> fragment_offsetUnsafeSupplier) {

		try {
			fragment_offset = fragment_offsetUnsafeSupplier.get();
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
	protected Integer fragment_offset;

	@Schema
	public Integer getFragment_size() {
		return fragment_size;
	}

	public void setFragment_size(Integer fragment_size) {
		this.fragment_size = fragment_size;
	}

	@JsonIgnore
	public void setFragment_size(
		UnsafeSupplier<Integer, Exception> fragment_sizeUnsafeSupplier) {

		try {
			fragment_size = fragment_sizeUnsafeSupplier.get();
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
	protected Integer fragment_size;

	@Schema
	public Integer getNumber_of_fragments() {
		return number_of_fragments;
	}

	public void setNumber_of_fragments(Integer number_of_fragments) {
		this.number_of_fragments = number_of_fragments;
	}

	@JsonIgnore
	public void setNumber_of_fragments(
		UnsafeSupplier<Integer, Exception> number_of_fragmentsUnsafeSupplier) {

		try {
			number_of_fragments = number_of_fragmentsUnsafeSupplier.get();
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
	protected Integer number_of_fragments;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HighlightField)) {
			return false;
		}

		HighlightField highlightField = (HighlightField)object;

		return Objects.equals(toString(), highlightField.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (fragment_offset != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragment_offset\": ");

			sb.append(fragment_offset);
		}

		if (fragment_size != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragment_size\": ");

			sb.append(fragment_size);
		}

		if (number_of_fragments != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"number_of_fragments\": ");

			sb.append(number_of_fragments);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.search.experiences.rest.dto.v1_0.HighlightField",
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