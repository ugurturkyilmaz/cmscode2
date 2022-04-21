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

import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Rafael Praxedes
 */
public class DDMFormTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetNontransientDDMFormFields() {
		DDMForm ddmForm = createDDMForm();

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getNontransientDDMFormFieldsMap(false);

		Assert.assertEquals(
			ddmFormFieldsMap.toString(), 1, ddmFormFieldsMap.size());

		DDMFormField ddmFormField = ddmFormFieldsMap.get("Paragraph");

		Assert.assertNull(ddmFormField);

		Assert.assertNotNull(ddmFormFieldsMap.get("Text1"));
	}

	@Test
	public void testGetNontransientDDMFormFieldsIncludingNestedFields() {
		DDMForm ddmForm = createDDMForm();

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getNontransientDDMFormFieldsMap(true);

		Assert.assertEquals(
			ddmFormFieldsMap.toString(), 2, ddmFormFieldsMap.size());

		DDMFormField ddmFormField = ddmFormFieldsMap.get("Paragraph");

		Assert.assertNull(ddmFormField);

		Assert.assertNotNull(ddmFormFieldsMap.get("Text2"));
	}

	@Test
	public void testGetNontransientDDMFormFieldsReferences() {
		DDMForm ddmForm = createDDMForm();

		Map<String, DDMFormField> ddmFormFieldsReferencesMap =
			ddmForm.getNontransientDDMFormFieldsReferencesMap(false);

		Assert.assertEquals(
			ddmFormFieldsReferencesMap.toString(), 1,
			ddmFormFieldsReferencesMap.size());

		DDMFormField ddmFormField = ddmFormFieldsReferencesMap.get("Paragraph");

		Assert.assertNull(ddmFormField);

		Assert.assertNotNull(ddmFormFieldsReferencesMap.get("Text1"));
	}

	@Test
	public void testGetNontransientDDMFormFieldsReferencesIncludingNestedFields() {
		DDMForm ddmForm = createDDMForm();

		Map<String, DDMFormField> ddmFormFieldsReferencesMap =
			ddmForm.getNontransientDDMFormFieldsReferencesMap(true);

		Assert.assertEquals(
			ddmFormFieldsReferencesMap.toString(), 2,
			ddmFormFieldsReferencesMap.size());

		DDMFormField ddmFormField = ddmFormFieldsReferencesMap.get("Paragraph");

		Assert.assertNull(ddmFormField);

		Assert.assertNotNull(ddmFormFieldsReferencesMap.get("Text2"));
	}

	protected DDMForm createDDMForm() {
		DDMForm ddmForm = DDMFormTestUtil.createDDMForm("Text1");

		DDMFormField paragraphDDMFormField = DDMFormTestUtil.createDDMFormField(
			"Paragraph", "Paragraph", "paragraph", StringPool.BLANK, false,
			false, false);

		paragraphDDMFormField.addNestedDDMFormField(
			DDMFormTestUtil.createLocalizableTextDDMFormField("Text2"));

		ddmForm.addDDMFormField(paragraphDDMFormField);

		return ddmForm;
	}

}