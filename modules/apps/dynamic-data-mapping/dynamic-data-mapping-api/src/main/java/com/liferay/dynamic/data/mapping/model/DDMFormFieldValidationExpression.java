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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.dynamic.data.mapping.form.validation.util.DateParameterUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.lang.HashUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Objects;

/**
 * @author Leonardo Barros
 */
public class DDMFormFieldValidationExpression {

	public DDMFormFieldValidationExpression() {
	}

	public DDMFormFieldValidationExpression(
		DDMFormFieldValidationExpression ddmFormFieldValidationExpression) {

		_name = ddmFormFieldValidationExpression._name;
		_value = ddmFormFieldValidationExpression._value;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFormFieldValidationExpression)) {
			return false;
		}

		DDMFormFieldValidationExpression ddmFormFieldValidationExpression =
			(DDMFormFieldValidationExpression)object;

		if (Objects.equals(_name, ddmFormFieldValidationExpression._name)) {
			return true;
		}

		return false;
	}

	public String getExpression(
		DDMFormValues ddmFormValues, String parameter, String timeZoneId) {

		if (_name == null) {
			return StringUtil.replace(_value, "{parameter}", parameter);
		}
		else if (_name.equals("dateRange")) {
			String partialExpression = StringUtil.replaceLast(
				_value, "{parameter}",
				DateParameterUtil.getParameter(
					ddmFormValues, "endsOn", parameter, timeZoneId));

			return StringUtil.replace(
				partialExpression, "{parameter}",
				DateParameterUtil.getParameter(
					ddmFormValues, "startsFrom", parameter, timeZoneId));
		}
		else if (_name.equals("futureDates")) {
			return StringUtil.replace(
				_value, "{parameter}",
				DateParameterUtil.getParameter(
					ddmFormValues, "startsFrom", parameter, timeZoneId));
		}
		else if (_name.equals("pastDates")) {
			return StringUtil.replace(
				_value, "{parameter}",
				DateParameterUtil.getParameter(
					ddmFormValues, "endsOn", parameter, timeZoneId));
		}

		return StringUtil.replace(_value, "{parameter}", parameter);
	}

	public String getName() {
		return _name;
	}

	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, _name);
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _name;
	private String _value;

}