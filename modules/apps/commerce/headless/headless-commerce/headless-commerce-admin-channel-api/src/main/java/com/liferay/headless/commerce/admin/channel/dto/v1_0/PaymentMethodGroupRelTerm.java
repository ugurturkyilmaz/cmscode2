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

package com.liferay.headless.commerce.admin.channel.dto.v1_0;

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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
@GraphQLName("PaymentMethodGroupRelTerm")
@JsonFilter("Liferay.Vulcan")
@Schema(requiredProperties = {"paymentMethodGroupRelId", "termId"})
@XmlRootElement(name = "PaymentMethodGroupRelTerm")
public class PaymentMethodGroupRelTerm implements Serializable {

	public static PaymentMethodGroupRelTerm toDTO(String json) {
		return ObjectMapperUtil.readValue(
			PaymentMethodGroupRelTerm.class, json);
	}

	public static PaymentMethodGroupRelTerm unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			PaymentMethodGroupRelTerm.class, json);
	}

	@Schema
	@Valid
	public Map<String, Map<String, String>> getActions() {
		return actions;
	}

	public void setActions(Map<String, Map<String, String>> actions) {
		this.actions = actions;
	}

	@JsonIgnore
	public void setActions(
		UnsafeSupplier<Map<String, Map<String, String>>, Exception>
			actionsUnsafeSupplier) {

		try {
			actions = actionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Map<String, Map<String, String>> actions;

	@DecimalMin("0")
	@Schema
	public Long getPaymentMethodGroupRelId() {
		return paymentMethodGroupRelId;
	}

	public void setPaymentMethodGroupRelId(Long paymentMethodGroupRelId) {
		this.paymentMethodGroupRelId = paymentMethodGroupRelId;
	}

	@JsonIgnore
	public void setPaymentMethodGroupRelId(
		UnsafeSupplier<Long, Exception> paymentMethodGroupRelIdUnsafeSupplier) {

		try {
			paymentMethodGroupRelId =
				paymentMethodGroupRelIdUnsafeSupplier.get();
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
	@NotNull
	protected Long paymentMethodGroupRelId;

	@DecimalMin("0")
	@Schema
	public Long getPaymentMethodGroupRelTermId() {
		return paymentMethodGroupRelTermId;
	}

	public void setPaymentMethodGroupRelTermId(
		Long paymentMethodGroupRelTermId) {

		this.paymentMethodGroupRelTermId = paymentMethodGroupRelTermId;
	}

	@JsonIgnore
	public void setPaymentMethodGroupRelTermId(
		UnsafeSupplier<Long, Exception>
			paymentMethodGroupRelTermIdUnsafeSupplier) {

		try {
			paymentMethodGroupRelTermId =
				paymentMethodGroupRelTermIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long paymentMethodGroupRelTermId;

	@Schema
	@Valid
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	@JsonIgnore
	public void setTerm(UnsafeSupplier<Term, Exception> termUnsafeSupplier) {
		try {
			term = termUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Term term;

	@Schema
	public String getTermExternalReferenceCode() {
		return termExternalReferenceCode;
	}

	public void setTermExternalReferenceCode(String termExternalReferenceCode) {
		this.termExternalReferenceCode = termExternalReferenceCode;
	}

	@JsonIgnore
	public void setTermExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			termExternalReferenceCodeUnsafeSupplier) {

		try {
			termExternalReferenceCode =
				termExternalReferenceCodeUnsafeSupplier.get();
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
	protected String termExternalReferenceCode;

	@DecimalMin("0")
	@Schema
	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	@JsonIgnore
	public void setTermId(
		UnsafeSupplier<Long, Exception> termIdUnsafeSupplier) {

		try {
			termId = termIdUnsafeSupplier.get();
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
	@NotNull
	protected Long termId;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PaymentMethodGroupRelTerm)) {
			return false;
		}

		PaymentMethodGroupRelTerm paymentMethodGroupRelTerm =
			(PaymentMethodGroupRelTerm)object;

		return Objects.equals(toString(), paymentMethodGroupRelTerm.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (actions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(actions));
		}

		if (paymentMethodGroupRelId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"paymentMethodGroupRelId\": ");

			sb.append(paymentMethodGroupRelId);
		}

		if (paymentMethodGroupRelTermId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"paymentMethodGroupRelTermId\": ");

			sb.append(paymentMethodGroupRelTermId);
		}

		if (term != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"term\": ");

			sb.append(String.valueOf(term));
		}

		if (termExternalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"termExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(termExternalReferenceCode));

			sb.append("\"");
		}

		if (termId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"termId\": ");

			sb.append(termId);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.commerce.admin.channel.dto.v1_0.PaymentMethodGroupRelTerm",
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