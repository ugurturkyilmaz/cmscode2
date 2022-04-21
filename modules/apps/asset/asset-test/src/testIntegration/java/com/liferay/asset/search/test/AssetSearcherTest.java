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

package com.liferay.asset.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.test.util.AssetTestUtil;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.asset.util.AssetSearcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eudaldo Alonso
 */
@RunWith(Arquillian.class)
public class AssetSearcherTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		AssetVocabulary publicAssetVocabulary = AssetTestUtil.addVocabulary(
			_group.getGroupId());

		_publicAssetCategory1 = AssetTestUtil.addCategory(
			_group.getGroupId(), publicAssetVocabulary.getVocabularyId());
		_publicAssetCategory2 = AssetTestUtil.addCategory(
			_group.getGroupId(), publicAssetVocabulary.getVocabularyId());

		AssetVocabulary internalAssetVocabulary = AssetTestUtil.addVocabulary(
			_group.getGroupId());

		_internalAssetCategory = AssetTestUtil.addCategory(
			_group.getGroupId(), internalAssetVocabulary.getVocabularyId());

		_addBlogsEntry(_internalAssetCategory.getCategoryId());

		_addBlogsEntry(
			_publicAssetCategory1.getCategoryId(),
			_publicAssetCategory2.getCategoryId());
		_addBlogsEntry(
			_internalAssetCategory.getCategoryId(),
			_publicAssetCategory1.getCategoryId(),
			_publicAssetCategory2.getCategoryId());
	}

	@Test
	public void testSearchAllAssetCategoryIdsIncludingInternalAssetCategories()
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setAllCategoryIds(
			new long[] {
				_internalAssetCategory.getCategoryId(),
				_publicAssetCategory1.getCategoryId(),
				_publicAssetCategory2.getCategoryId()
			});

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setIncludeInternalAssetCategories(true);

		Hits hits = assetSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 1, hits.getLength());
	}

	@Test
	public void testSearchAllAssetCategoryIdsOnlyPublicAssetCategories()
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setAllCategoryIds(
			new long[] {
				_publicAssetCategory1.getCategoryId(),
				_publicAssetCategory2.getCategoryId()
			});

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setIncludeInternalAssetCategories(false);

		Hits hits = assetSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 2, hits.getLength());
	}

	@Test
	public void testSearchAnyAssetCategoryIdsIncludingInternalAssetCategories()
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setAnyCategoryIds(
			new long[] {
				_internalAssetCategory.getCategoryId(),
				_publicAssetCategory1.getCategoryId(),
				_publicAssetCategory2.getCategoryId()
			});

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setIncludeInternalAssetCategories(true);

		Hits hits = assetSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 3, hits.getLength());
	}

	@Test
	public void testSearchAnyAssetCategoryIdsOnlyPublicAssetCategories()
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setAnyCategoryIds(
			new long[] {
				_publicAssetCategory1.getCategoryId(),
				_publicAssetCategory2.getCategoryId()
			});

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setIncludeInternalAssetCategories(false);

		Hits hits = assetSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 2, hits.getLength());
	}

	@Test
	public void testSearchNotAllAssetCategoryIdsIncludingInternalAssetCategories()
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setNotAllCategoryIds(
			new long[] {
				_internalAssetCategory.getCategoryId(),
				_publicAssetCategory1.getCategoryId(),
				_publicAssetCategory2.getCategoryId()
			});

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setIncludeInternalAssetCategories(true);

		Hits hits = assetSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 2, hits.getLength());
	}

	@Test
	public void testSearchNotAllAssetCategoryIdsOnlyPublicAssetCategories()
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setNotAllCategoryIds(
			new long[] {
				_publicAssetCategory1.getCategoryId(),
				_publicAssetCategory2.getCategoryId()
			});

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setIncludeInternalAssetCategories(false);

		Hits hits = assetSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 1, hits.getLength());
	}

	@Test
	public void testSearchNotAnyAssetCategoryIdsIncludingInternalAssetCategories()
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setNotAnyCategoryIds(
			new long[] {
				_internalAssetCategory.getCategoryId(),
				_publicAssetCategory1.getCategoryId(),
				_publicAssetCategory2.getCategoryId()
			});

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setIncludeInternalAssetCategories(true);

		Hits hits = assetSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 0, hits.getLength());
	}

	@Test
	public void testSearchNotAnyAssetCategoryIdsOnlyPublicAssetCategories()
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setNotAnyCategoryIds(
			new long[] {
				_publicAssetCategory1.getCategoryId(),
				_publicAssetCategory2.getCategoryId()
			});

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setIncludeInternalAssetCategories(false);

		Hits hits = assetSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 1, hits.getLength());
	}

	private void _addBlogsEntry(long... assetCategoryIds) throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		serviceContext.setAssetCategoryIds(assetCategoryIds);

		BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			StringPool.BLANK, StringPool.BLANK, RandomTestUtil.randomString(),
			1, 1, 1965, 0, 0, true, true, null, StringPool.BLANK, null, null,
			serviceContext);
	}

	@DeleteAfterTestRun
	private Group _group;

	private AssetCategory _internalAssetCategory;
	private AssetCategory _publicAssetCategory1;
	private AssetCategory _publicAssetCategory2;

}