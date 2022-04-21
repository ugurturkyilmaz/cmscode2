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

package com.liferay.portal.workflow.metrics.rest.client.serdes.v1_0;

import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.AssigneeMetric;
import com.liferay.portal.workflow.metrics.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public class AssigneeMetricSerDes {

	public static AssigneeMetric toDTO(String json) {
		AssigneeMetricJSONParser assigneeMetricJSONParser =
			new AssigneeMetricJSONParser();

		return assigneeMetricJSONParser.parseToDTO(json);
	}

	public static AssigneeMetric[] toDTOs(String json) {
		AssigneeMetricJSONParser assigneeMetricJSONParser =
			new AssigneeMetricJSONParser();

		return assigneeMetricJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AssigneeMetric assigneeMetric) {
		if (assigneeMetric == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (assigneeMetric.getAssignee() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assignee\": ");

			sb.append(String.valueOf(assigneeMetric.getAssignee()));
		}

		if (assigneeMetric.getDurationTaskAvg() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"durationTaskAvg\": ");

			sb.append(assigneeMetric.getDurationTaskAvg());
		}

		if (assigneeMetric.getOnTimeTaskCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"onTimeTaskCount\": ");

			sb.append(assigneeMetric.getOnTimeTaskCount());
		}

		if (assigneeMetric.getOverdueTaskCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"overdueTaskCount\": ");

			sb.append(assigneeMetric.getOverdueTaskCount());
		}

		if (assigneeMetric.getTaskCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"taskCount\": ");

			sb.append(assigneeMetric.getTaskCount());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AssigneeMetricJSONParser assigneeMetricJSONParser =
			new AssigneeMetricJSONParser();

		return assigneeMetricJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AssigneeMetric assigneeMetric) {
		if (assigneeMetric == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (assigneeMetric.getAssignee() == null) {
			map.put("assignee", null);
		}
		else {
			map.put("assignee", String.valueOf(assigneeMetric.getAssignee()));
		}

		if (assigneeMetric.getDurationTaskAvg() == null) {
			map.put("durationTaskAvg", null);
		}
		else {
			map.put(
				"durationTaskAvg",
				String.valueOf(assigneeMetric.getDurationTaskAvg()));
		}

		if (assigneeMetric.getOnTimeTaskCount() == null) {
			map.put("onTimeTaskCount", null);
		}
		else {
			map.put(
				"onTimeTaskCount",
				String.valueOf(assigneeMetric.getOnTimeTaskCount()));
		}

		if (assigneeMetric.getOverdueTaskCount() == null) {
			map.put("overdueTaskCount", null);
		}
		else {
			map.put(
				"overdueTaskCount",
				String.valueOf(assigneeMetric.getOverdueTaskCount()));
		}

		if (assigneeMetric.getTaskCount() == null) {
			map.put("taskCount", null);
		}
		else {
			map.put("taskCount", String.valueOf(assigneeMetric.getTaskCount()));
		}

		return map;
	}

	public static class AssigneeMetricJSONParser
		extends BaseJSONParser<AssigneeMetric> {

		@Override
		protected AssigneeMetric createDTO() {
			return new AssigneeMetric();
		}

		@Override
		protected AssigneeMetric[] createDTOArray(int size) {
			return new AssigneeMetric[size];
		}

		@Override
		protected void setField(
			AssigneeMetric assigneeMetric, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "assignee")) {
				if (jsonParserFieldValue != null) {
					assigneeMetric.setAssignee(
						AssigneeSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "durationTaskAvg")) {
				if (jsonParserFieldValue != null) {
					assigneeMetric.setDurationTaskAvg(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "onTimeTaskCount")) {
				if (jsonParserFieldValue != null) {
					assigneeMetric.setOnTimeTaskCount(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "overdueTaskCount")) {
				if (jsonParserFieldValue != null) {
					assigneeMetric.setOverdueTaskCount(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "taskCount")) {
				if (jsonParserFieldValue != null) {
					assigneeMetric.setTaskCount(
						Long.valueOf((String)jsonParserFieldValue));
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