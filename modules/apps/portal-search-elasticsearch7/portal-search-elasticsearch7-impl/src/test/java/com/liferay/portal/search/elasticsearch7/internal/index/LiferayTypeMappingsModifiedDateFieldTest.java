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

package com.liferay.portal.search.elasticsearch7.internal.index;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.elasticsearch7.internal.connection.IndexName;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;
import java.util.Date;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.ElasticsearchStatusException;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;

/**
 * @author Bryan Engler
 */
public class LiferayTypeMappingsModifiedDateFieldTest {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		Class<?> clazz = getClass();

		_liferayIndexFixture = new LiferayIndexFixture(
			clazz.getSimpleName(), new IndexName(testName.getMethodName()));

		_liferayIndexFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_liferayIndexFixture.tearDown();
	}

	@Test
	public void testDate() throws Exception {
		expectedException.expect(ElasticsearchException.class);
		expectedException.expectMessage(
			"failed to parse date field [1970-01-18T12:08:26.556Z] with " +
				"format [yyyyMMddHHmmss]");

		index(new Date(1512506556L));
	}

	@Test
	public void testLong() throws Exception {
		index(20171115050402L);

		_liferayIndexFixture.assertType("modified", "date");
	}

	@Test
	public void testLongMalformed() throws Exception {
		expectedException.expect(ElasticsearchException.class);
		expectedException.expectMessage(
			"failed to parse date field [1512506556] with format " +
				"[yyyyMMddHHmmss]");

		index(1512506556L);
	}

	@Test
	public void testString() throws Exception {
		index("20171115050402");

		_liferayIndexFixture.assertType("modified", "date");
	}

	@Test
	public void testStringMalformed() throws Exception {
		expectedException.expect(ElasticsearchException.class);
		expectedException.expectMessage(
			"failed to parse date field [2017-11-15 05:04:02] with format " +
				"[yyyyMMddHHmmss]");

		index("2017-11-15 05:04:02");
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Rule
	public TestName testName = new TestName();

	protected void index(Object value) throws Exception {
		try {
			_liferayIndexFixture.index(
				Collections.singletonMap(Field.MODIFIED_DATE, value));
		}
		catch (ElasticsearchStatusException elasticsearchStatusException) {
			throw (Exception)elasticsearchStatusException.getCause();
		}
	}

	private LiferayIndexFixture _liferayIndexFixture;

}