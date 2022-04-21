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

package com.liferay.headless.admin.workflow.client.serdes.v1_0;

import com.liferay.headless.admin.workflow.client.dto.v1_0.WorkflowInstanceSubmit;
import com.liferay.headless.admin.workflow.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class WorkflowInstanceSubmitSerDes {

	public static WorkflowInstanceSubmit toDTO(String json) {
		WorkflowInstanceSubmitJSONParser workflowInstanceSubmitJSONParser =
			new WorkflowInstanceSubmitJSONParser();

		return workflowInstanceSubmitJSONParser.parseToDTO(json);
	}

	public static WorkflowInstanceSubmit[] toDTOs(String json) {
		WorkflowInstanceSubmitJSONParser workflowInstanceSubmitJSONParser =
			new WorkflowInstanceSubmitJSONParser();

		return workflowInstanceSubmitJSONParser.parseToDTOs(json);
	}

	public static String toJSON(WorkflowInstanceSubmit workflowInstanceSubmit) {
		if (workflowInstanceSubmit == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (workflowInstanceSubmit.getContext() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"context\": ");

			sb.append(_toJSON(workflowInstanceSubmit.getContext()));
		}

		if (workflowInstanceSubmit.getSiteId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteId\": ");

			sb.append(workflowInstanceSubmit.getSiteId());
		}

		if (workflowInstanceSubmit.getTransitionName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"transitionName\": ");

			sb.append("\"");

			sb.append(_escape(workflowInstanceSubmit.getTransitionName()));

			sb.append("\"");
		}

		if (workflowInstanceSubmit.getWorkflowDefinitionName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowDefinitionName\": ");

			sb.append("\"");

			sb.append(
				_escape(workflowInstanceSubmit.getWorkflowDefinitionName()));

			sb.append("\"");
		}

		if (workflowInstanceSubmit.getWorkflowDefinitionVersion() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowDefinitionVersion\": ");

			sb.append("\"");

			sb.append(
				_escape(workflowInstanceSubmit.getWorkflowDefinitionVersion()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		WorkflowInstanceSubmitJSONParser workflowInstanceSubmitJSONParser =
			new WorkflowInstanceSubmitJSONParser();

		return workflowInstanceSubmitJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		WorkflowInstanceSubmit workflowInstanceSubmit) {

		if (workflowInstanceSubmit == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (workflowInstanceSubmit.getContext() == null) {
			map.put("context", null);
		}
		else {
			map.put(
				"context", String.valueOf(workflowInstanceSubmit.getContext()));
		}

		if (workflowInstanceSubmit.getSiteId() == null) {
			map.put("siteId", null);
		}
		else {
			map.put(
				"siteId", String.valueOf(workflowInstanceSubmit.getSiteId()));
		}

		if (workflowInstanceSubmit.getTransitionName() == null) {
			map.put("transitionName", null);
		}
		else {
			map.put(
				"transitionName",
				String.valueOf(workflowInstanceSubmit.getTransitionName()));
		}

		if (workflowInstanceSubmit.getWorkflowDefinitionName() == null) {
			map.put("workflowDefinitionName", null);
		}
		else {
			map.put(
				"workflowDefinitionName",
				String.valueOf(
					workflowInstanceSubmit.getWorkflowDefinitionName()));
		}

		if (workflowInstanceSubmit.getWorkflowDefinitionVersion() == null) {
			map.put("workflowDefinitionVersion", null);
		}
		else {
			map.put(
				"workflowDefinitionVersion",
				String.valueOf(
					workflowInstanceSubmit.getWorkflowDefinitionVersion()));
		}

		return map;
	}

	public static class WorkflowInstanceSubmitJSONParser
		extends BaseJSONParser<WorkflowInstanceSubmit> {

		@Override
		protected WorkflowInstanceSubmit createDTO() {
			return new WorkflowInstanceSubmit();
		}

		@Override
		protected WorkflowInstanceSubmit[] createDTOArray(int size) {
			return new WorkflowInstanceSubmit[size];
		}

		@Override
		protected void setField(
			WorkflowInstanceSubmit workflowInstanceSubmit,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "context")) {
				if (jsonParserFieldValue != null) {
					workflowInstanceSubmit.setContext(
						(Map)WorkflowInstanceSubmitSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "siteId")) {
				if (jsonParserFieldValue != null) {
					workflowInstanceSubmit.setSiteId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "transitionName")) {
				if (jsonParserFieldValue != null) {
					workflowInstanceSubmit.setTransitionName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "workflowDefinitionName")) {

				if (jsonParserFieldValue != null) {
					workflowInstanceSubmit.setWorkflowDefinitionName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "workflowDefinitionVersion")) {

				if (jsonParserFieldValue != null) {
					workflowInstanceSubmit.setWorkflowDefinitionVersion(
						(String)jsonParserFieldValue);
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