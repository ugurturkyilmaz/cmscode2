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

package com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.CalculateDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.spi.converter.serializer.SPIDDMFormRuleActionSerializer;
import com.liferay.dynamic.data.mapping.spi.converter.serializer.SPIDDMFormRuleSerializerContext;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Leonardo Barros
 */
public class CalculateDDMFormRuleActionSerializer
	implements SPIDDMFormRuleActionSerializer {

	public CalculateDDMFormRuleActionSerializer(
		CalculateDDMFormRuleAction calculateDDMFormRuleAction) {

		_calculateDDMFormRuleAction = calculateDDMFormRuleAction;
	}

	@Override
	public String serialize(
		SPIDDMFormRuleSerializerContext spiDDMFormRuleSerializerContext) {

		if (Validator.isNull(_calculateDDMFormRuleAction.getTarget())) {
			return null;
		}

		DDMForm ddmForm = spiDDMFormRuleSerializerContext.getAttribute("form");

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		String expression = _removeBrackets(
			_calculateDDMFormRuleAction.getExpression());

		Set<String> keySet = ddmFormFieldsMap.keySet();

		Stream<String> ddmFormFieldsStream = keySet.stream();

		Set<String> ddmFormFieldNames = ddmFormFieldsStream.filter(
			ddmFormField -> expression.contains(ddmFormField)
		).collect(
			Collectors.toSet()
		);

		String newExpression = buildExpression(expression, ddmFormFieldNames);

		return String.format(
			_FUNCTION_CALL_BINARY_EXPRESSION_FORMAT, "calculate",
			_calculateDDMFormRuleAction.getTarget(), newExpression);
	}

	protected String buildExpression(
		String expression, Set<String> ddmFormFieldNames) {

		int start = Integer.MAX_VALUE;
		int end = Integer.MIN_VALUE;

		StringBuilder newExpressionSB = new StringBuilder();

		StringBundler sb = new StringBundler();

		for (int i = 0; i < expression.length(); i++) {
			char token = expression.charAt(i);

			sb.append(token);

			String compareStr = sb.toString();

			boolean match = _matchAnyField(compareStr, ddmFormFieldNames);

			if (match) {
				newExpressionSB.append(token);

				if (start == Integer.MAX_VALUE) {
					start = i;
				}

				if (i == (expression.length() - 1)) {
					end = expression.length();
				}
			}
			else {
				if (i > start) {
					_replace(expression, newExpressionSB, start, i);
				}

				newExpressionSB.append(token);

				sb = new StringBundler();

				start = Integer.MAX_VALUE;
				end = Integer.MIN_VALUE;
			}
		}

		if (end > start) {
			_replace(expression, newExpressionSB, start, end);
		}

		return newExpressionSB.toString();
	}

	private boolean _matchAnyField(
		String compareStr, Set<String> ddmFormFields) {

		for (String ddmFormField : ddmFormFields) {
			if (ddmFormField.startsWith(compareStr)) {
				return true;
			}
		}

		return false;
	}

	private String _removeBrackets(String expression) {
		return StringUtil.removeChars(
			expression, CharPool.OPEN_BRACKET, CharPool.CLOSE_BRACKET);
	}

	private void _replace(
		String expression, StringBuilder newExpressionSB, int start, int end) {

		String fieldName = expression.substring(start, end);

		String fieldNameReplacement = String.format(
			_FUNCTION_CALL_UNARY_EXPRESSION_FORMAT, "getValue", fieldName);

		int currentLength = newExpressionSB.length();

		newExpressionSB.replace(
			currentLength - fieldName.length(), currentLength,
			fieldNameReplacement);
	}

	private static final String _FUNCTION_CALL_BINARY_EXPRESSION_FORMAT =
		"%s('%s', %s)";

	private static final String _FUNCTION_CALL_UNARY_EXPRESSION_FORMAT =
		"%s('%s')";

	private final CalculateDDMFormRuleAction _calculateDDMFormRuleAction;

}