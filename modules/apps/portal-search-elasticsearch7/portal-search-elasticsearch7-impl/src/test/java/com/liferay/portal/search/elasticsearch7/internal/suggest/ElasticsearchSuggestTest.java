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

package com.liferay.portal.search.elasticsearch7.internal.suggest;

import com.liferay.portal.search.elasticsearch7.internal.LiferayElasticsearchIndexingFixtureFactory;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.suggest.BaseSuggestTestCase;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.elasticsearch.ElasticsearchStatusException;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author André de Oliveira
 */
public class ElasticsearchSuggestTest extends BaseSuggestTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Override
	@Test
	public void testMultipleWords() throws Exception {
		indexSuccessfulQuery("indexed this phrase");

		assertSuggest(
			"[indexef phrase, index phrasd]", "indexef   this   phrasd", 2);
	}

	@Override
	@Test
	public void testNothingToSuggest() throws Exception {
		indexSuccessfulQuery("creating the keywordSearch mapping");

		assertSuggest("[]", "nothign");
	}

	@Override
	@Test
	public void testNull() throws Exception {
		expectedException.expect(ElasticsearchStatusException.class);
		expectedException.expectMessage("all shards failed");

		indexSuccessfulQuery("creating the keywordSearch mapping");

		assertSuggest("[]", null);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Override
	protected IndexingFixture createIndexingFixture() {
		return LiferayElasticsearchIndexingFixtureFactory.getInstance();
	}

}