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

package com.liferay.headless.commerce.machine.learning.dto.v1_0;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Riccardo Ferrari
 * @generated
 */
@Generated("")
@GraphQLName("FrequentPatternRecommendation")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "FrequentPatternRecommendation")
public class FrequentPatternRecommendation implements Serializable {

	public static FrequentPatternRecommendation toDTO(String json) {
		return ObjectMapperUtil.readValue(
			FrequentPatternRecommendation.class, json);
	}

	public static FrequentPatternRecommendation unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			FrequentPatternRecommendation.class, json);
	}

	@Schema
	public Long[] getAntecedentIds() {
		return antecedentIds;
	}

	public void setAntecedentIds(Long[] antecedentIds) {
		this.antecedentIds = antecedentIds;
	}

	@JsonIgnore
	public void setAntecedentIds(
		UnsafeSupplier<Long[], Exception> antecedentIdsUnsafeSupplier) {

		try {
			antecedentIds = antecedentIdsUnsafeSupplier.get();
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
	protected Long[] antecedentIds;

	@Schema
	public Long getAntecedentIdsLength() {
		return antecedentIdsLength;
	}

	public void setAntecedentIdsLength(Long antecedentIdsLength) {
		this.antecedentIdsLength = antecedentIdsLength;
	}

	@JsonIgnore
	public void setAntecedentIdsLength(
		UnsafeSupplier<Long, Exception> antecedentIdsLengthUnsafeSupplier) {

		try {
			antecedentIdsLength = antecedentIdsLengthUnsafeSupplier.get();
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
	protected Long antecedentIdsLength;

	@Schema
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
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
	protected Date createDate;

	@Schema
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@JsonIgnore
	public void setJobId(
		UnsafeSupplier<String, Exception> jobIdUnsafeSupplier) {

		try {
			jobId = jobIdUnsafeSupplier.get();
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
	protected String jobId;

	@Schema(description = "The recommended product identifier.")
	public Long getRecommendedProductId() {
		return recommendedProductId;
	}

	public void setRecommendedProductId(Long recommendedProductId) {
		this.recommendedProductId = recommendedProductId;
	}

	@JsonIgnore
	public void setRecommendedProductId(
		UnsafeSupplier<Long, Exception> recommendedProductIdUnsafeSupplier) {

		try {
			recommendedProductId = recommendedProductIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The recommended product identifier.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long recommendedProductId;

	@Schema(description = "The recommendation score.")
	@Valid
	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@JsonIgnore
	public void setScore(UnsafeSupplier<Float, Exception> scoreUnsafeSupplier) {
		try {
			score = scoreUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The recommendation score.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Float score;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FrequentPatternRecommendation)) {
			return false;
		}

		FrequentPatternRecommendation frequentPatternRecommendation =
			(FrequentPatternRecommendation)object;

		return Objects.equals(
			toString(), frequentPatternRecommendation.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (antecedentIds != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"antecedentIds\": ");

			sb.append("[");

			for (int i = 0; i < antecedentIds.length; i++) {
				sb.append(antecedentIds[i]);

				if ((i + 1) < antecedentIds.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (antecedentIdsLength != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"antecedentIdsLength\": ");

			sb.append(antecedentIdsLength);
		}

		if (createDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(createDate));

			sb.append("\"");
		}

		if (jobId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"jobId\": ");

			sb.append("\"");

			sb.append(_escape(jobId));

			sb.append("\"");
		}

		if (recommendedProductId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedProductId\": ");

			sb.append(recommendedProductId);
		}

		if (score != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"score\": ");

			sb.append(score);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.commerce.machine.learning.dto.v1_0.FrequentPatternRecommendation",
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