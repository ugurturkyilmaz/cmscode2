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

package com.liferay.dynamic.data.mapping.form.web.internal.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Pedro Queiroz
 */
public class DDMFormWebConfigurationTest {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testCreateDefaultDDMFormWebConfiguration() {
		DDMFormWebConfiguration ddmFormWebConfiguration =
			ConfigurableUtil.createConfigurable(
				DDMFormWebConfiguration.class, new HashMapDictionary<>());

		Assert.assertEquals(1, ddmFormWebConfiguration.autosaveInterval());
		Assert.assertEquals(
			"enabled-with-warning", ddmFormWebConfiguration.csvExport());
		Assert.assertEquals(
			"list", ddmFormWebConfiguration.defaultDisplayView());
		Assert.assertEquals(
			"doc, docx, jpeg, jpg, pdf, png, ppt, pptx, tiff, txt, xls, xlsx",
			ddmFormWebConfiguration.guestUploadFileExtensions());
		Assert.assertEquals(
			25, ddmFormWebConfiguration.guestUploadMaximumFileSize());
		Assert.assertEquals(
			5, ddmFormWebConfiguration.guestUploadMaximumSubmissions());
		Assert.assertEquals(
			5, ddmFormWebConfiguration.maximumRepetitionsForUploadFields());
	}

}