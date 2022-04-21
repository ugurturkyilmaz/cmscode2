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

package com.liferay.dynamic.data.mapping.validator;

import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.petra.string.StringPool;

import java.util.Locale;

/**
 * @author Marcellus Tavares
 * @author Roberto Díaz
 */
public class DDMFormValuesValidationException extends StorageException {

	public DDMFormValuesValidationException() {
	}

	public DDMFormValuesValidationException(String msg) {
		super(msg);
	}

	public DDMFormValuesValidationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public DDMFormValuesValidationException(Throwable throwable) {
		super(throwable);
	}

	public static class MustNotSetValue
		extends DDMFormValuesValidationException {

		public MustNotSetValue(LocalizedValue fieldLabel, String fieldName) {
			super(
				String.format(
					"Value should not be set for transient field name %s",
					fieldName));

			_fieldLabel = fieldLabel;
			_fieldName = fieldName;
		}

		public MustNotSetValue(String fieldName) {
			this(null, fieldName);
		}

		public LocalizedValue getFieldLabel() {
			return _fieldLabel;
		}

		public String getFieldLabelValue(Locale locale) {
			if (_fieldLabel == null) {
				return StringPool.BLANK;
			}

			return _fieldLabel.getString(locale);
		}

		public String getFieldName() {
			return _fieldName;
		}

		private final LocalizedValue _fieldLabel;
		private String _fieldName;

	}

	public static class MustSetValidAvailableLocales
		extends DDMFormValuesValidationException {

		public MustSetValidAvailableLocales(
			LocalizedValue fieldLabel, String fieldName) {

			super(
				String.format(
					"Invalid available locales set for field name %s",
					fieldName));

			_fieldLabel = fieldLabel;
			_fieldName = fieldName;
		}

		public MustSetValidAvailableLocales(String fieldName) {
			this(null, fieldName);
		}

		public LocalizedValue getFieldLabel() {
			return _fieldLabel;
		}

		public String getFieldLabelValue(Locale locale) {
			if (_fieldLabel == null) {
				return StringPool.BLANK;
			}

			return _fieldLabel.getString(locale);
		}

		public String getFieldName() {
			return _fieldName;
		}

		private final LocalizedValue _fieldLabel;
		private String _fieldName;

	}

	public static class MustSetValidDefaultLocale
		extends DDMFormValuesValidationException {

		public MustSetValidDefaultLocale(
			LocalizedValue fieldLabel, String fieldName) {

			super(
				String.format(
					"Invalid default locale set for field name %s", fieldName));

			_fieldLabel = fieldLabel;
			_fieldName = fieldName;
		}

		public MustSetValidDefaultLocale(String fieldName) {
			this(null, fieldName);
		}

		public LocalizedValue getFieldLabel() {
			return _fieldLabel;
		}

		public String getFieldLabelValue(Locale locale) {
			if (_fieldLabel == null) {
				return StringPool.BLANK;
			}

			return _fieldLabel.getString(locale);
		}

		public String getFieldName() {
			return _fieldName;
		}

		private final LocalizedValue _fieldLabel;
		private String _fieldName;

	}

	public static class MustSetValidField
		extends DDMFormValuesValidationException {

		public MustSetValidField(LocalizedValue fieldLabel, String fieldName) {
			super(
				String.format(
					"There is no field name %s defined on form", fieldName));

			_fieldLabel = fieldLabel;
			_fieldName = fieldName;
		}

		public MustSetValidField(String fieldName) {
			this(null, fieldName);
		}

		public LocalizedValue getFieldLabel() {
			return _fieldLabel;
		}

		public String getFieldLabelValue(Locale locale) {
			if (_fieldLabel == null) {
				return StringPool.BLANK;
			}

			return _fieldLabel.getString(locale);
		}

		public String getFieldName() {
			return _fieldName;
		}

		private final LocalizedValue _fieldLabel;
		private String _fieldName;

	}

	public static class MustSetValidValue
		extends DDMFormValuesValidationException {

		public MustSetValidValue(LocalizedValue fieldLabel, String fieldName) {
			super(
				String.format(
					"Invalid value set for field name %s", fieldName));

			_fieldLabel = fieldLabel;
			_fieldName = fieldName;
		}

		public MustSetValidValue(String fieldName) {
			this(null, fieldName);
		}

		public MustSetValidValue(String fieldName, Throwable throwable) {
			super(
				String.format("Invalid value set for field name %s", fieldName),
				throwable);

			_fieldName = fieldName;
		}

		public LocalizedValue getFieldLabel() {
			return _fieldLabel;
		}

		public String getFieldLabelValue(Locale locale) {
			if (_fieldLabel == null) {
				return StringPool.BLANK;
			}

			return _fieldLabel.getString(locale);
		}

		public String getFieldName() {
			return _fieldName;
		}

		private LocalizedValue _fieldLabel;
		private String _fieldName;

	}

	public static class MustSetValidValuesSize
		extends DDMFormValuesValidationException {

		public MustSetValidValuesSize(
			LocalizedValue fieldLabel, String fieldName) {

			super(
				String.format(
					"Incorrect number of values set for field name %s",
					fieldName));

			_fieldLabel = fieldLabel;
			_fieldName = fieldName;
		}

		public MustSetValidValuesSize(String fieldName) {
			this(null, fieldName);
		}

		public LocalizedValue getFieldLabel() {
			return _fieldLabel;
		}

		public String getFieldLabelValue(Locale locale) {
			if (_fieldLabel == null) {
				return StringPool.BLANK;
			}

			return _fieldLabel.getString(locale);
		}

		public String getFieldName() {
			return _fieldName;
		}

		private final LocalizedValue _fieldLabel;
		private String _fieldName;

	}

	public static class RequiredValue extends DDMFormValuesValidationException {

		public RequiredValue(LocalizedValue fieldLabel, String fieldName) {
			super(String.format("No value defined for field name", fieldName));

			_fieldLabel = fieldLabel;
			_fieldName = fieldName;
		}

		public RequiredValue(String fieldName) {
			this(null, fieldName);
		}

		public LocalizedValue getFieldLabel() {
			return _fieldLabel;
		}

		public String getFieldLabelValue(Locale locale) {
			if (_fieldLabel == null) {
				return StringPool.BLANK;
			}

			return _fieldLabel.getString(locale);
		}

		public String getFieldName() {
			return _fieldName;
		}

		private final LocalizedValue _fieldLabel;
		private final String _fieldName;

	}

}