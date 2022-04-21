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
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.asset.kernel.service.AssetVocabularyService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.document.DocumentBuilderFactory;
import com.liferay.portal.search.model.uid.UIDFactory;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.search.test.util.FieldValuesAssert;
import com.liferay.portal.search.test.util.IndexedFieldsFixture;
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.users.admin.test.util.search.GroupBlueprint;
import com.liferay.users.admin.test.util.search.GroupSearchFixture;
import com.liferay.users.admin.test.util.search.UserSearchFixture;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Fabiano Nazar
 * @author Luan Maoski
 * @author Lucas Marques
 */
@RunWith(Arquillian.class)
public class AssetCategoryIndexerIndexedFieldsTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		GroupSearchFixture groupSearchFixture = new GroupSearchFixture();

		Group group = groupSearchFixture.addGroup(new GroupBlueprint());

		AssetVocabularyFixture assetVocabularyFixture =
			new AssetVocabularyFixture(assetVocabularyService, group);

		AssetCategoryFixture assetCategoryFixture = new AssetCategoryFixture(
			assetCategoryService, assetVocabularyFixture, group);

		_assetCategoryFixture = assetCategoryFixture;

		_assetCategories = assetCategoryFixture.getAssetCategories();

		_assetVocabularies = assetVocabularyFixture.getAssetVocabularies();

		_group = group;

		_groups = groupSearchFixture.getGroups();
		_indexedFieldsFixture = new IndexedFieldsFixture(
			resourcePermissionLocalService, uidFactory, documentBuilderFactory);
	}

	@Test
	public void testIndexedFields() throws Exception {
		Locale locale = LocaleUtil.JAPAN;

		setTestLocale(locale);

		AssetCategory assetCategory = _assetCategoryFixture.createAssetCategory(
			"新しい商品");

		String searchTerm = "新しい";

		assertFieldValues(
			_expectedFieldValues(assetCategory), locale, searchTerm);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertFieldValues(
		Map<String, String> map, Locale locale, String searchTerm) {

		FieldValuesAssert.assertFieldValues(
			map, name -> !name.equals("score"),
			searcher.search(
				searchRequestBuilderFactory.builder(
				).companyId(
					_group.getCompanyId()
				).groupIds(
					_group.getGroupId()
				).locale(
					locale
				).fields(
					StringPool.STAR
				).modelIndexerClasses(
					AssetCategory.class
				).queryString(
					searchTerm
				).build()));
	}

	protected void setTestLocale(Locale locale) throws Exception {
		_assetCategoryFixture.updateDisplaySettings(locale);

		LocaleThreadLocal.setDefaultLocale(locale);
	}

	@Inject(
		filter = "indexer.class.name=com.liferay.asset.kernel.model.AssetCategory"
	)
	protected static Indexer<AssetCategory> indexer;

	@Inject
	protected AssetCategoryService assetCategoryService;

	@Inject
	protected AssetVocabularyService assetVocabularyService;

	@Inject
	protected DocumentBuilderFactory documentBuilderFactory;

	@Inject
	protected ResourcePermissionLocalService resourcePermissionLocalService;

	@Inject
	protected Searcher searcher;

	@Inject
	protected SearchRequestBuilderFactory searchRequestBuilderFactory;

	@Inject
	protected UIDFactory uidFactory;

	protected UserSearchFixture userSearchFixture;

	private Map<String, String> _expectedFieldValues(
			AssetCategory assetCategory)
		throws Exception {

		Map<String, String> map = HashMapBuilder.put(
			Field.ASSET_CATEGORY_ID,
			String.valueOf(assetCategory.getCategoryId())
		).put(
			Field.ASSET_CATEGORY_TITLE,
			StringUtil.lowerCase(assetCategory.getName())
		).put(
			Field.ASSET_VOCABULARY_ID,
			String.valueOf(assetCategory.getVocabularyId())
		).put(
			Field.COMPANY_ID, String.valueOf(assetCategory.getCompanyId())
		).put(
			Field.ENTRY_CLASS_NAME, AssetCategory.class.getName()
		).put(
			Field.ENTRY_CLASS_PK, String.valueOf(assetCategory.getCategoryId())
		).put(
			Field.GROUP_ID, String.valueOf(assetCategory.getGroupId())
		).put(
			Field.NAME, assetCategory.getName()
		).put(
			Field.SCOPE_GROUP_ID, String.valueOf(assetCategory.getGroupId())
		).put(
			Field.STAGING_GROUP, String.valueOf(_group.isStagingGroup())
		).put(
			Field.TITLE, assetCategory.getName()
		).put(
			Field.USER_ID, String.valueOf(assetCategory.getUserId())
		).put(
			Field.USER_NAME, StringUtil.lowerCase(assetCategory.getUserName())
		).put(
			"assetCategoryTitle_ja_JP",
			StringUtil.lowerCase(assetCategory.getName())
		).put(
			"name_sortable", StringUtil.lowerCase(assetCategory.getName())
		).put(
			"parentCategoryId",
			String.valueOf(assetCategory.getParentCategoryId())
		).put(
			"title_ja_JP", assetCategory.getName()
		).put(
			"title_sortable", StringUtil.lowerCase(assetCategory.getName())
		).put(
			"treePath", assetCategory.getTreePath()
		).build();

		_indexedFieldsFixture.populateUID(assetCategory, map);

		_populateDates(assetCategory, map);
		_populateRoles(assetCategory, map);
		_populateTitles(assetCategory.getName(), map);

		return map;
	}

	private void _populateDates(
		AssetCategory assetCategory, Map<String, String> map) {

		_indexedFieldsFixture.populateDate(
			Field.CREATE_DATE, assetCategory.getCreateDate(), map);
		_indexedFieldsFixture.populateDate(
			Field.MODIFIED_DATE, assetCategory.getModifiedDate(), map);
	}

	private void _populateRoles(
			AssetCategory assetCategory, Map<String, String> map)
		throws Exception {

		_indexedFieldsFixture.populateRoleIdFields(
			assetCategory.getCompanyId(), AssetCategory.class.getName(),
			assetCategory.getCategoryId(), assetCategory.getGroupId(), null,
			map);
	}

	private void _populateTitles(String title, Map<String, String> map) {
		map.put("localized_title", title);

		for (Locale locale : LanguageUtil.getAvailableLocales()) {
			StringBundler sb = new StringBundler(5);

			sb.append("localized_title_");
			sb.append(locale.getLanguage());
			sb.append("_");
			sb.append(locale.getCountry());

			map.put(sb.toString(), title);

			sb.append("_sortable");

			map.put(sb.toString(), title);
		}
	}

	@DeleteAfterTestRun
	private List<AssetCategory> _assetCategories;

	private AssetCategoryFixture _assetCategoryFixture;

	@DeleteAfterTestRun
	private List<AssetVocabulary> _assetVocabularies;

	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	private IndexedFieldsFixture _indexedFieldsFixture;

}