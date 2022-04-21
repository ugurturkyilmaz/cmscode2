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

package com.liferay.headless.commerce.machine.learning.client.serdes.v1_0;

import com.liferay.headless.commerce.machine.learning.client.dto.v1_0.FrequentPatternRecommendation;
import com.liferay.headless.commerce.machine.learning.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Riccardo Ferrari
 * @generated
 */
@Generated("")
public class FrequentPatternRecommendationSerDes {

	public static FrequentPatternRecommendation toDTO(String json) {
		FrequentPatternRecommendationJSONParser
			frequentPatternRecommendationJSONParser =
				new FrequentPatternRecommendationJSONParser();

		return frequentPatternRecommendationJSONParser.parseToDTO(json);
	}

	public static FrequentPatternRecommendation[] toDTOs(String json) {
		FrequentPatternRecommendationJSONParser
			frequentPatternRecommendationJSONParser =
				new FrequentPatternRecommendationJSONParser();

		return frequentPatternRecommendationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		FrequentPatternRecommendation frequentPatternRecommendation) {

		if (frequentPatternRecommendation == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (frequentPatternRecommendation.getAntecedentIds() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"antecedentIds\": ");

			sb.append("[");

			for (int i = 0;
				 i < frequentPatternRecommendation.getAntecedentIds().length;
				 i++) {

				sb.append(frequentPatternRecommendation.getAntecedentIds()[i]);

				if ((i + 1) <
						frequentPatternRecommendation.
							getAntecedentIds().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (frequentPatternRecommendation.getAntecedentIdsLength() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"antecedentIdsLength\": ");

			sb.append(frequentPatternRecommendation.getAntecedentIdsLength());
		}

		if (frequentPatternRecommendation.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					frequentPatternRecommendation.getCreateDate()));

			sb.append("\"");
		}

		if (frequentPatternRecommendation.getJobId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"jobId\": ");

			sb.append("\"");

			sb.append(_escape(frequentPatternRecommendation.getJobId()));

			sb.append("\"");
		}

		if (frequentPatternRecommendation.getRecommendedProductId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedProductId\": ");

			sb.append(frequentPatternRecommendation.getRecommendedProductId());
		}

		if (frequentPatternRecommendation.getScore() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"score\": ");

			sb.append(frequentPatternRecommendation.getScore());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FrequentPatternRecommendationJSONParser
			frequentPatternRecommendationJSONParser =
				new FrequentPatternRecommendationJSONParser();

		return frequentPatternRecommendationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		FrequentPatternRecommendation frequentPatternRecommendation) {

		if (frequentPatternRecommendation == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (frequentPatternRecommendation.getAntecedentIds() == null) {
			map.put("antecedentIds", null);
		}
		else {
			map.put(
				"antecedentIds",
				String.valueOf(
					frequentPatternRecommendation.getAntecedentIds()));
		}

		if (frequentPatternRecommendation.getAntecedentIdsLength() == null) {
			map.put("antecedentIdsLength", null);
		}
		else {
			map.put(
				"antecedentIdsLength",
				String.valueOf(
					frequentPatternRecommendation.getAntecedentIdsLength()));
		}

		if (frequentPatternRecommendation.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(
					frequentPatternRecommendation.getCreateDate()));
		}

		if (frequentPatternRecommendation.getJobId() == null) {
			map.put("jobId", null);
		}
		else {
			map.put(
				"jobId",
				String.valueOf(frequentPatternRecommendation.getJobId()));
		}

		if (frequentPatternRecommendation.getRecommendedProductId() == null) {
			map.put("recommendedProductId", null);
		}
		else {
			map.put(
				"recommendedProductId",
				String.valueOf(
					frequentPatternRecommendation.getRecommendedProductId()));
		}

		if (frequentPatternRecommendation.getScore() == null) {
			map.put("score", null);
		}
		else {
			map.put(
				"score",
				String.valueOf(frequentPatternRecommendation.getScore()));
		}

		return map;
	}

	public static class FrequentPatternRecommendationJSONParser
		extends BaseJSONParser<FrequentPatternRecommendation> {

		@Override
		protected FrequentPatternRecommendation createDTO() {
			return new FrequentPatternRecommendation();
		}

		@Override
		protected FrequentPatternRecommendation[] createDTOArray(int size) {
			return new FrequentPatternRecommendation[size];
		}

		@Override
		protected void setField(
			FrequentPatternRecommendation frequentPatternRecommendation,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "antecedentIds")) {
				if (jsonParserFieldValue != null) {
					frequentPatternRecommendation.setAntecedentIds(
						toLongs((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "antecedentIdsLength")) {

				if (jsonParserFieldValue != null) {
					frequentPatternRecommendation.setAntecedentIdsLength(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					frequentPatternRecommendation.setCreateDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "jobId")) {
				if (jsonParserFieldValue != null) {
					frequentPatternRecommendation.setJobId(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "recommendedProductId")) {

				if (jsonParserFieldValue != null) {
					frequentPatternRecommendation.setRecommendedProductId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "score")) {
				if (jsonParserFieldValue != null) {
					frequentPatternRecommendation.setScore(
						Float.valueOf((String)jsonParserFieldValue));
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