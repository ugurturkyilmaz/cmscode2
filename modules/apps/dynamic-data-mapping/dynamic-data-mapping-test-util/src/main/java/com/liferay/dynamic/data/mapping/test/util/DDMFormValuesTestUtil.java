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

package com.liferay.dynamic.data.mapping.test.util;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class DDMFormValuesTestUtil {

	public static Set<Locale> createAvailableLocales(Locale... locales) {
		Set<Locale> availableLocales = new LinkedHashSet<>();

		for (Locale locale : locales) {
			availableLocales.add(locale);
		}

		return availableLocales;
	}

	public static DDMFormFieldValue createDDMFormFieldValue(
		String instanceId, String name, Value value) {

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setFieldReference(name);
		ddmFormFieldValue.setInstanceId(instanceId);
		ddmFormFieldValue.setName(name);
		ddmFormFieldValue.setValue(value);

		return ddmFormFieldValue;
	}

	public static DDMFormFieldValue createDDMFormFieldValue(
		String name, Value value) {

		return createDDMFormFieldValue(StringUtil.randomString(), name, value);
	}

	public static DDMFormFieldValue createDDMFormFieldValueWithReference(
		String name, String reference, Value value) {

		DDMFormFieldValue ddmFormFieldValue = createDDMFormFieldValue(
			StringUtil.randomString(), name, value);

		ddmFormFieldValue.setFieldReference(reference);

		return ddmFormFieldValue;
	}

	public static DDMFormValues createDDMFormValues(DDMForm ddmForm) {
		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.setAvailableLocales(ddmForm.getAvailableLocales());
		ddmFormValues.setDefaultLocale(ddmForm.getDefaultLocale());

		return ddmFormValues;
	}

	public static DDMFormValues createDDMFormValues(
		DDMForm ddmForm, Set<Locale> availableLocales, Locale defaultLocale) {

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.setAvailableLocales(availableLocales);
		ddmFormValues.setDefaultLocale(defaultLocale);

		return ddmFormValues;
	}

	public static DDMFormValues createDDMFormValuesWithDefaultValues(
		DDMForm ddmForm) {

		DDMFormValues ddmFormValues = createDDMFormValues(ddmForm);

		for (DDMFormField ddmFormField : ddmForm.getDDMFormFields()) {
			ddmFormValues.addDDMFormFieldValue(
				createUnlocalizedDDMFormFieldValue(
					ddmFormField.getName(), StringPool.BLANK));
		}

		return ddmFormValues;
	}

	public static DDMFormValues createDDMFormValuesWithRandomValues(
		DDMForm ddmForm) {

		DDMFormValues ddmFormValues = createDDMFormValues(ddmForm);

		for (DDMFormField ddmFormField : ddmForm.getDDMFormFields()) {
			if (ddmFormField.isLocalizable()) {
				ddmFormValues.addDDMFormFieldValue(
					createLocalizedDDMFormFieldValue(
						ddmFormField.getName(), RandomTestUtil.randomString()));
			}
			else {
				ddmFormValues.addDDMFormFieldValue(
					createUnlocalizedDDMFormFieldValue(
						ddmFormField.getName(), RandomTestUtil.randomString()));
			}
		}

		return ddmFormValues;
	}

	public static DDMFormFieldValue createLocalizedDDMFormFieldValue(
		String name, String enValue) {

		Value localizedValue = new LocalizedValue(LocaleUtil.US);

		localizedValue.addString(LocaleUtil.US, enValue);

		return createDDMFormFieldValue(name, localizedValue);
	}

	public static LocalizedValue createLocalizedValue(
		String enValue, Locale defaultLocale) {

		LocalizedValue localizedValue = new LocalizedValue(defaultLocale);

		localizedValue.addString(LocaleUtil.US, enValue);

		return localizedValue;
	}

	public static LocalizedValue createLocalizedValue(
		String enValue, String ptValue, Locale defaultLocale) {

		LocalizedValue localizedValue = new LocalizedValue(defaultLocale);

		localizedValue.addString(LocaleUtil.BRAZIL, ptValue);
		localizedValue.addString(LocaleUtil.US, enValue);

		return localizedValue;
	}

	public static DDMFormFieldValue createUnlocalizedDDMFormFieldValue(
		String name, String value) {

		return createDDMFormFieldValue(name, new UnlocalizedValue(value));
	}

}