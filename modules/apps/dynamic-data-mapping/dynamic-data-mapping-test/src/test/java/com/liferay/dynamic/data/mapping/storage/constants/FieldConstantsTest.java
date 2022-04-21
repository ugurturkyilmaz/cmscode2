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

package com.liferay.dynamic.data.mapping.storage.constants;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Guilherme Camacho
 */
public class FieldConstantsTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetSerializableReturnDoubleWithLocaleBR() {
		Serializable serializable = FieldConstants.getSerializable(
			LocaleUtil.BRAZIL, LocaleUtil.BRAZIL, FieldConstants.DOUBLE, "3,0");

		Assert.assertEquals(Double.valueOf(3.0), (Double)serializable);
	}

	@Test
	public void testGetSerializableReturnDoubleWithLocaleUS() {
		Serializable serializable = FieldConstants.getSerializable(
			LocaleUtil.US, LocaleUtil.US, FieldConstants.DOUBLE, "3.0");

		Assert.assertEquals(Double.valueOf(3.0), (Double)serializable);
	}

	@Test
	public void testGetSerializableReturnIntegerWithLocaleBR() {
		Serializable serializable = FieldConstants.getSerializable(
			LocaleUtil.BRAZIL, LocaleUtil.BRAZIL, FieldConstants.INTEGER, "3");

		Assert.assertEquals(Integer.valueOf(3), (Integer)serializable);
	}

	@Test
	public void testGetSerializableReturnIntegerWithLocaleUS() {
		Serializable serializable = FieldConstants.getSerializable(
			LocaleUtil.US, LocaleUtil.US, FieldConstants.INTEGER, "3");

		Assert.assertEquals(Integer.valueOf(3), (Integer)serializable);
	}

}