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

package com.liferay.petra.url.pattern.mapper.internal;

import com.liferay.petra.url.pattern.mapper.URLPatternMapper;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Map;

import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author Arthur Chan
 */
public class DynamicSizeTrieURLPatternMapperPerformanceTest
	extends BaseURLPatternMapperPerformanceTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Override
	protected URLPatternMapper<Integer> createURLPatternMapper(
		Map<String, Integer> values) {

		return new DynamicSizeTrieURLPatternMapper<>(values);
	}

	@Override
	protected int testConsumeValuesExpectedTime() {
		return 710;
	}

	@Override
	protected int testConsumeValuesOrderedExpectedTime() {
		return 1200;
	}

	@Override
	protected int testGetValueExpectedTime() {
		return 680;
	}

	@Override
	protected int testGetValuesExpectedTime() {
		return 1300;
	}

}