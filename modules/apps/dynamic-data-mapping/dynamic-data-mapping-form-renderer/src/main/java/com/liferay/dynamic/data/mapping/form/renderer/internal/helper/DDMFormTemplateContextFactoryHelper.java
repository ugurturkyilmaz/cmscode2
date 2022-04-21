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

package com.liferay.dynamic.data.mapping.form.renderer.internal.helper;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMFormRule;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Leonardo Barros
 * @author Rafael Praxedes
 */
public class DDMFormTemplateContextFactoryHelper {

	public Set<String> getEvaluableDDMFormFieldNames(
		DDMForm ddmForm, DDMFormLayout ddmFormLayout) {

		Set<String> expressionParameterNames = new HashSet<>();

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		Set<String> ddmFormFieldNames = ddmFormFieldsMap.keySet();

		List<DDMFormRule> ddmFormRules = ddmFormLayout.getDDMFormRules();

		if (ListUtil.isEmpty(ddmFormRules)) {
			ddmFormRules = ddmForm.getDDMFormRules();
		}

		expressionParameterNames.addAll(
			_getParameterNamesByDDMFormRules(ddmFormRules));

		for (DDMFormField ddmFormField : ddmFormFieldsMap.values()) {
			if (_isDDMFormFieldEvaluable(ddmFormField)) {
				expressionParameterNames.add(ddmFormField.getName());
			}

			expressionParameterNames.addAll(
				_getParameterNamesByExpression(
					ddmFormField.getVisibilityExpression()));
		}

		ddmFormFieldNames.retainAll(expressionParameterNames);

		return ddmFormFieldNames;
	}

	private Set<String> _getParameterNamesByDDMFormRules(
		List<DDMFormRule> ddmFormRules) {

		Set<String> parameterNames = new HashSet<>();

		for (DDMFormRule ddmFormRule : ddmFormRules) {
			parameterNames.addAll(
				_getParameterNamesByExpression(ddmFormRule.getCondition()));

			for (String action : ddmFormRule.getActions()) {
				parameterNames.addAll(_getParameterNamesByExpression(action));
			}
		}

		return parameterNames;
	}

	private Set<String> _getParameterNamesByExpression(String expression) {
		if (Validator.isNull(expression)) {
			return Collections.emptySet();
		}

		Set<String> parameterNames = new HashSet<>();

		Matcher matcher = _pattern.matcher(expression);

		while (matcher.find()) {
			parameterNames.add(matcher.group(1));
		}

		return parameterNames;
	}

	private boolean _isDDMFormFieldEvaluable(DDMFormField ddmFormField) {
		if (Objects.equals(ddmFormField.getType(), "object-relationship") ||
			GetterUtil.getBoolean(ddmFormField.getProperty("inputMask")) ||
			GetterUtil.getBoolean(
				ddmFormField.getProperty("requireConfirmation")) ||
			GetterUtil.getBoolean(ddmFormField.getProperty("required"))) {

			return true;
		}

		DDMFormFieldValidation ddmFormFieldValidation =
			ddmFormField.getDDMFormFieldValidation();

		if ((ddmFormFieldValidation != null) &&
			(ddmFormFieldValidation.getDDMFormFieldValidationExpression() !=
				null)) {

			return true;
		}

		return false;
	}

	private static final Pattern _pattern = Pattern.compile(
		"'?([\\w]+)'?\\s*[,\\)]\\s*");

}