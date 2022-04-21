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

package com.liferay.portal.background.task.internal;

import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskThreadLocalManagerImplTest
	extends BaseBackgroundTaskTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testDeserializeThreadLocals() {
		backgroundTaskThreadLocalManagerImpl.deserializeThreadLocals(
			HashMapBuilder.<String, Serializable>put(
				BackgroundTaskThreadLocalManagerImpl.KEY_THREAD_LOCAL_VALUES,
				initializeThreadLocalValues()
			).build());

		assertThreadLocalValues();
	}

	@Test
	public void testGetThreadLocalValues() {
		initalizeThreadLocals();

		assertThreadLocalValues(
			backgroundTaskThreadLocalManagerImpl.getThreadLocalValues());
	}

	@Test
	public void testSerializeThreadLocals() {
		initalizeThreadLocals();

		Map<String, Serializable> taskContextMap = new HashMap<>();

		backgroundTaskThreadLocalManagerImpl.serializeThreadLocals(
			taskContextMap);

		Map<String, Serializable> threadLocalValues =
			(Map<String, Serializable>)taskContextMap.get(
				BackgroundTaskThreadLocalManagerImpl.KEY_THREAD_LOCAL_VALUES);

		assertThreadLocalValues(threadLocalValues);
	}

	@Test
	public void testSetThreadLocalValues() {
		backgroundTaskThreadLocalManagerImpl.setThreadLocalValues(
			initializeThreadLocalValues());

		assertThreadLocalValues();
	}

}