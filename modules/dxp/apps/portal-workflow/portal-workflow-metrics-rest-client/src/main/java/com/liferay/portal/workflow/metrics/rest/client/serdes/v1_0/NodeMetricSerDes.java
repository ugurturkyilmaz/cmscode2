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

import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.NodeMetric;
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
public class NodeMetricSerDes {

	public static NodeMetric toDTO(String json) {
		NodeMetricJSONParser nodeMetricJSONParser = new NodeMetricJSONParser();

		return nodeMetricJSONParser.parseToDTO(json);
	}

	public static NodeMetric[] toDTOs(String json) {
		NodeMetricJSONParser nodeMetricJSONParser = new NodeMetricJSONParser();

		return nodeMetricJSONParser.parseToDTOs(json);
	}

	public static String toJSON(NodeMetric nodeMetric) {
		if (nodeMetric == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (nodeMetric.getBreachedInstanceCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"breachedInstanceCount\": ");

			sb.append(nodeMetric.getBreachedInstanceCount());
		}

		if (nodeMetric.getBreachedInstancePercentage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"breachedInstancePercentage\": ");

			sb.append(nodeMetric.getBreachedInstancePercentage());
		}

		if (nodeMetric.getDurationAvg() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"durationAvg\": ");

			sb.append(nodeMetric.getDurationAvg());
		}

		if (nodeMetric.getInstanceCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"instanceCount\": ");

			sb.append(nodeMetric.getInstanceCount());
		}

		if (nodeMetric.getNode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"node\": ");

			sb.append(String.valueOf(nodeMetric.getNode()));
		}

		if (nodeMetric.getOnTimeInstanceCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"onTimeInstanceCount\": ");

			sb.append(nodeMetric.getOnTimeInstanceCount());
		}

		if (nodeMetric.getOverdueInstanceCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"overdueInstanceCount\": ");

			sb.append(nodeMetric.getOverdueInstanceCount());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		NodeMetricJSONParser nodeMetricJSONParser = new NodeMetricJSONParser();

		return nodeMetricJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(NodeMetric nodeMetric) {
		if (nodeMetric == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (nodeMetric.getBreachedInstanceCount() == null) {
			map.put("breachedInstanceCount", null);
		}
		else {
			map.put(
				"breachedInstanceCount",
				String.valueOf(nodeMetric.getBreachedInstanceCount()));
		}

		if (nodeMetric.getBreachedInstancePercentage() == null) {
			map.put("breachedInstancePercentage", null);
		}
		else {
			map.put(
				"breachedInstancePercentage",
				String.valueOf(nodeMetric.getBreachedInstancePercentage()));
		}

		if (nodeMetric.getDurationAvg() == null) {
			map.put("durationAvg", null);
		}
		else {
			map.put("durationAvg", String.valueOf(nodeMetric.getDurationAvg()));
		}

		if (nodeMetric.getInstanceCount() == null) {
			map.put("instanceCount", null);
		}
		else {
			map.put(
				"instanceCount", String.valueOf(nodeMetric.getInstanceCount()));
		}

		if (nodeMetric.getNode() == null) {
			map.put("node", null);
		}
		else {
			map.put("node", String.valueOf(nodeMetric.getNode()));
		}

		if (nodeMetric.getOnTimeInstanceCount() == null) {
			map.put("onTimeInstanceCount", null);
		}
		else {
			map.put(
				"onTimeInstanceCount",
				String.valueOf(nodeMetric.getOnTimeInstanceCount()));
		}

		if (nodeMetric.getOverdueInstanceCount() == null) {
			map.put("overdueInstanceCount", null);
		}
		else {
			map.put(
				"overdueInstanceCount",
				String.valueOf(nodeMetric.getOverdueInstanceCount()));
		}

		return map;
	}

	public static class NodeMetricJSONParser
		extends BaseJSONParser<NodeMetric> {

		@Override
		protected NodeMetric createDTO() {
			return new NodeMetric();
		}

		@Override
		protected NodeMetric[] createDTOArray(int size) {
			return new NodeMetric[size];
		}

		@Override
		protected void setField(
			NodeMetric nodeMetric, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "breachedInstanceCount")) {
				if (jsonParserFieldValue != null) {
					nodeMetric.setBreachedInstanceCount(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "breachedInstancePercentage")) {

				if (jsonParserFieldValue != null) {
					nodeMetric.setBreachedInstancePercentage(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "durationAvg")) {
				if (jsonParserFieldValue != null) {
					nodeMetric.setDurationAvg(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "instanceCount")) {
				if (jsonParserFieldValue != null) {
					nodeMetric.setInstanceCount(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "node")) {
				if (jsonParserFieldValue != null) {
					nodeMetric.setNode(
						NodeSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "onTimeInstanceCount")) {

				if (jsonParserFieldValue != null) {
					nodeMetric.setOnTimeInstanceCount(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "overdueInstanceCount")) {

				if (jsonParserFieldValue != null) {
					nodeMetric.setOverdueInstanceCount(
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