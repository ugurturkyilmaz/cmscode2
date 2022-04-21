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

package com.liferay.dynamic.data.mapping.form.field.type.internal.numeric;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueEditingAware;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueLocalizer;
import com.liferay.dynamic.data.mapping.form.field.type.constants.DDMFormFieldTypeConstants;
import com.liferay.dynamic.data.mapping.util.NumberUtil;
import com.liferay.dynamic.data.mapping.util.NumericDDMFormFieldUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rodrigo Paulino
 */
@Component(
	immediate = true,
	property = "ddm.form.field.type.name=" + DDMFormFieldTypeConstants.NUMERIC,
	service = DDMFormFieldValueLocalizer.class
)
public class NumericDDMFormFieldValueLocalizer
	implements DDMFormFieldValueEditingAware, DDMFormFieldValueLocalizer {

	@Override
	public boolean isEditingFieldValue() {
		return _editingFieldValue;
	}

	@Override
	public String localize(String value, Locale locale) {
		try {
			DecimalFormat defaultDecimalFormat =
				NumericDDMFormFieldUtil.getDecimalFormat(LocaleUtil.US);

			DecimalFormat decimalFormat =
				NumericDDMFormFieldUtil.getDecimalFormat(locale);

			Number number = null;

			if (value.indexOf(StringPool.PERIOD) != -1) {
				number = GetterUtil.getNumber(
					defaultDecimalFormat.parse(value));
			}
			else {
				number = GetterUtil.getNumber(decimalFormat.parse(value));
			}

			String formattedNumber = decimalFormat.format(number);

			if (!value.equals(formattedNumber)) {
				number = defaultDecimalFormat.parse(value);

				formattedNumber = decimalFormat.format(number);

				if (isEditingFieldValue() && _endsWithDecimalSeparator(value)) {
					formattedNumber = formattedNumber.concat(
						String.valueOf(value.charAt(value.length() - 1)));
				}
				else if (!NumberUtil.hasDecimalSeparator(formattedNumber) &&
						 NumberUtil.hasDecimalSeparator(value) &&
						 !_endsWithDecimalSeparator(value)) {

					DecimalFormatSymbols decimalFormatSymbols =
						decimalFormat.getDecimalFormatSymbols();

					formattedNumber = StringBundler.concat(
						formattedNumber,
						decimalFormatSymbols.getDecimalSeparator(),
						value.substring(
							NumberUtil.getDecimalSeparatorIndex(value) + 1));
				}
			}

			return formattedNumber;
		}
		catch (ParseException parseException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to parse number for locale " + locale,
					parseException);
			}
		}

		return value;
	}

	@Override
	public void setEditingFieldValue(boolean editingFieldValue) {
		_editingFieldValue = editingFieldValue;
	}

	private final boolean _endsWithDecimalSeparator(String value) {
		if (StringUtil.endsWith(value, StringPool.COMMA) ||
			StringUtil.endsWith(value, StringPool.PERIOD)) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		NumericDDMFormFieldValueLocalizer.class);

	private boolean _editingFieldValue;

}