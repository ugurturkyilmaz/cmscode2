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

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.DefaultDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.spi.converter.serializer.SPIDDMFormRuleSerializerContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;

/**
 * @author Leonardo Barros
 */
public class DefaultDDMFormRuleActionSerializerTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testSerializeSetEnabled() {
		DefaultDDMFormRuleAction defaultDDMFormRuleAction =
			new DefaultDDMFormRuleAction("enable", "field0");

		DefaultDDMFormRuleActionSerializer defaultDDMFormRuleActionSerializer =
			new DefaultDDMFormRuleActionSerializer(defaultDDMFormRuleAction);

		String result = defaultDDMFormRuleActionSerializer.serialize(
			_spiDDMFormRuleSerializerContext);

		Assert.assertEquals("setEnabled('field0', true)", result);
	}

	@Test
	public void testSerializeSetInvalid() {
		DefaultDDMFormRuleAction defaultDDMFormRuleAction =
			new DefaultDDMFormRuleAction("invalidate", "field0");

		DefaultDDMFormRuleActionSerializer defaultDDMFormRuleActionSerializer =
			new DefaultDDMFormRuleActionSerializer(defaultDDMFormRuleAction);

		String result = defaultDDMFormRuleActionSerializer.serialize(
			_spiDDMFormRuleSerializerContext);

		Assert.assertEquals("setInvalid('field0', true)", result);
	}

	@Test
	public void testSerializeSetRequired() {
		DefaultDDMFormRuleAction defaultDDMFormRuleAction =
			new DefaultDDMFormRuleAction("require", "field0");

		DefaultDDMFormRuleActionSerializer defaultDDMFormRuleActionSerializer =
			new DefaultDDMFormRuleActionSerializer(defaultDDMFormRuleAction);

		String result = defaultDDMFormRuleActionSerializer.serialize(
			_spiDDMFormRuleSerializerContext);

		Assert.assertEquals("setRequired('field0', true)", result);
	}

	@Test
	public void testSerializeSetVisible() {
		DefaultDDMFormRuleAction defaultDDMFormRuleAction =
			new DefaultDDMFormRuleAction("show", "field0");

		DefaultDDMFormRuleActionSerializer defaultDDMFormRuleActionSerializer =
			new DefaultDDMFormRuleActionSerializer(defaultDDMFormRuleAction);

		String result = defaultDDMFormRuleActionSerializer.serialize(
			_spiDDMFormRuleSerializerContext);

		Assert.assertEquals("setVisible('field0', true)", result);
	}

	@Test
	public void testSerializeWithEmptyTarget() {
		DefaultDDMFormRuleAction defaultDDMFormRuleAction =
			new DefaultDDMFormRuleAction("show", StringPool.BLANK);

		DefaultDDMFormRuleActionSerializer defaultDDMFormRuleActionSerializer =
			new DefaultDDMFormRuleActionSerializer(defaultDDMFormRuleAction);

		String result = defaultDDMFormRuleActionSerializer.serialize(
			_spiDDMFormRuleSerializerContext);

		Assert.assertNull(result);
	}

	@Mock
	private SPIDDMFormRuleSerializerContext _spiDDMFormRuleSerializerContext;

}