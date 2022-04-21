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

package com.liferay.portal.search.test.util.filter.groupid;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.search.internal.spi.model.query.contributor.GroupIdQueryPreFilterContributor;
import com.liferay.portal.search.test.util.DocumentsAssert;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Tibor Lipusz
 * @author André de Oliveira
 */
public abstract class BaseGroupIdQueryPreFilterContributorTestCase
	extends BaseIndexingTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		MockitoAnnotations.initMocks(this);

		Mockito.doReturn(
			Arrays.asList(INACTIVE_GROUP_ID1, INACTIVE_GROUP_ID2)
		).when(
			groupLocalService
		).getGroupIds(
			Mockito.anyLong(), Mockito.eq(false)
		);
	}

	@Test
	public void testNoEmptyClauses() throws Exception {
		Group group = Mockito.mock(Group.class);

		long groupId = 11111;

		Mockito.doReturn(
			group
		).when(
			groupLocalService
		).getGroup(
			groupId
		);

		Mockito.doReturn(
			false
		).when(
			groupLocalService
		).isLiveGroupActive(
			group
		);

		assertSearch(
			indexingTestHelper -> indexingTestHelper.define(
				searchContext -> {
					searchContext.setGroupIds(new long[] {groupId});

					BooleanFilter booleanFilter = (BooleanFilter)createFilter(
						searchContext);

					assertEmptyClauses(booleanFilter.getMustBooleanClauses());
					assertEmptyClauses(
						booleanFilter.getMustNotBooleanClauses());
					assertEmptyClauses(booleanFilter.getShouldBooleanClauses());
				}));
	}

	@Test
	public void testScopeEverythingWithInactiveGroups() {
		addDocuments(1, 2, 3, INACTIVE_GROUP_ID1, INACTIVE_GROUP_ID2);

		assertSearch(0, "[1, 2, 3]");

		Mockito.verify(
			groupLocalService, Mockito.never()
		).getActiveGroups(
			Mockito.anyLong(), Mockito.anyBoolean()
		);
	}

	@Test
	public void testScopeSingleGroup() throws Exception {
		Group group = Mockito.mock(Group.class);

		Mockito.doReturn(
			group
		).when(
			groupLocalService
		).getGroup(
			2
		);

		Mockito.doReturn(
			true
		).when(
			groupLocalService
		).isLiveGroupActive(
			group
		);

		addDocuments(1, 2, 3, INACTIVE_GROUP_ID1, INACTIVE_GROUP_ID2);

		assertSearch(2, "[2]");
	}

	protected void addDocuments(long... groupIds) {
		for (long groupId : groupIds) {
			addDocument(
				document -> {
					document.addKeyword(Field.GROUP_ID, groupId);
					document.addKeyword(Field.SCOPE_GROUP_ID, groupId);
				});
		}
	}

	protected void assertEmptyClauses(List<BooleanClause<Filter>> clauses) {
		Assert.assertEquals(clauses.toString(), 0, clauses.size());
	}

	protected void assertSearch(long scopeGroupId, String expected) {
		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.define(
					searchContext -> {
						searchContext.setGroupIds(new long[] {scopeGroupId});

						indexingTestHelper.setFilter(
							createFilter(searchContext));
					});

				indexingTestHelper.search();

				indexingTestHelper.verifyResponse(
					searchResponse ->
						DocumentsAssert.assertValuesIgnoreRelevance(
							searchResponse.getRequestString(),
							searchResponse.getDocumentsStream(), Field.GROUP_ID,
							expected));
			});
	}

	protected Filter createFilter(SearchContext searchContext) {
		GroupIdQueryPreFilterContributor contributor =
			new GroupIdQueryPreFilterContributor();

		contributor.setGroupLocalService(groupLocalService);

		BooleanFilter booleanFilter = new BooleanFilter();

		contributor.contribute(booleanFilter, searchContext);

		return booleanFilter;
	}

	protected static final long INACTIVE_GROUP_ID1 = 4L;

	protected static final long INACTIVE_GROUP_ID2 = 5L;

	@Mock
	protected GroupLocalService groupLocalService;

}