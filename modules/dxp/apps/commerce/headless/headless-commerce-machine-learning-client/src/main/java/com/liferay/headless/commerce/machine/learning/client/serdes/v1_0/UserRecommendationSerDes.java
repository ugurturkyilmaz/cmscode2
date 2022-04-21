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

import com.liferay.headless.commerce.machine.learning.client.dto.v1_0.UserRecommendation;
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
public class UserRecommendationSerDes {

	public static UserRecommendation toDTO(String json) {
		UserRecommendationJSONParser userRecommendationJSONParser =
			new UserRecommendationJSONParser();

		return userRecommendationJSONParser.parseToDTO(json);
	}

	public static UserRecommendation[] toDTOs(String json) {
		UserRecommendationJSONParser userRecommendationJSONParser =
			new UserRecommendationJSONParser();

		return userRecommendationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(UserRecommendation userRecommendation) {
		if (userRecommendation == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (userRecommendation.getAssetCategoryIds() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assetCategoryIds\": ");

			sb.append("[");

			for (int i = 0; i < userRecommendation.getAssetCategoryIds().length;
				 i++) {

				sb.append(userRecommendation.getAssetCategoryIds()[i]);

				if ((i + 1) < userRecommendation.getAssetCategoryIds().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (userRecommendation.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					userRecommendation.getCreateDate()));

			sb.append("\"");
		}

		if (userRecommendation.getJobId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"jobId\": ");

			sb.append("\"");

			sb.append(_escape(userRecommendation.getJobId()));

			sb.append("\"");
		}

		if (userRecommendation.getProductId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productId\": ");

			sb.append(userRecommendation.getProductId());
		}

		if (userRecommendation.getRank() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"rank\": ");

			sb.append(userRecommendation.getRank());
		}

		if (userRecommendation.getRecommendedProductId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedProductId\": ");

			sb.append(userRecommendation.getRecommendedProductId());
		}

		if (userRecommendation.getScore() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"score\": ");

			sb.append(userRecommendation.getScore());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		UserRecommendationJSONParser userRecommendationJSONParser =
			new UserRecommendationJSONParser();

		return userRecommendationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		UserRecommendation userRecommendation) {

		if (userRecommendation == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (userRecommendation.getAssetCategoryIds() == null) {
			map.put("assetCategoryIds", null);
		}
		else {
			map.put(
				"assetCategoryIds",
				String.valueOf(userRecommendation.getAssetCategoryIds()));
		}

		if (userRecommendation.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(
					userRecommendation.getCreateDate()));
		}

		if (userRecommendation.getJobId() == null) {
			map.put("jobId", null);
		}
		else {
			map.put("jobId", String.valueOf(userRecommendation.getJobId()));
		}

		if (userRecommendation.getProductId() == null) {
			map.put("productId", null);
		}
		else {
			map.put(
				"productId", String.valueOf(userRecommendation.getProductId()));
		}

		if (userRecommendation.getRank() == null) {
			map.put("rank", null);
		}
		else {
			map.put("rank", String.valueOf(userRecommendation.getRank()));
		}

		if (userRecommendation.getRecommendedProductId() == null) {
			map.put("recommendedProductId", null);
		}
		else {
			map.put(
				"recommendedProductId",
				String.valueOf(userRecommendation.getRecommendedProductId()));
		}

		if (userRecommendation.getScore() == null) {
			map.put("score", null);
		}
		else {
			map.put("score", String.valueOf(userRecommendation.getScore()));
		}

		return map;
	}

	public static class UserRecommendationJSONParser
		extends BaseJSONParser<UserRecommendation> {

		@Override
		protected UserRecommendation createDTO() {
			return new UserRecommendation();
		}

		@Override
		protected UserRecommendation[] createDTOArray(int size) {
			return new UserRecommendation[size];
		}

		@Override
		protected void setField(
			UserRecommendation userRecommendation, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "assetCategoryIds")) {
				if (jsonParserFieldValue != null) {
					userRecommendation.setAssetCategoryIds(
						toLongs((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					userRecommendation.setCreateDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "jobId")) {
				if (jsonParserFieldValue != null) {
					userRecommendation.setJobId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productId")) {
				if (jsonParserFieldValue != null) {
					userRecommendation.setProductId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "rank")) {
				if (jsonParserFieldValue != null) {
					userRecommendation.setRank(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "recommendedProductId")) {

				if (jsonParserFieldValue != null) {
					userRecommendation.setRecommendedProductId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "score")) {
				if (jsonParserFieldValue != null) {
					userRecommendation.setScore(
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