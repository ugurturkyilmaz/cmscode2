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

package com.liferay.layout.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.document.DocumentBuilderFactory;
import com.liferay.portal.search.model.uid.UIDFactory;
import com.liferay.portal.search.test.util.FieldValuesAssert;
import com.liferay.portal.search.test.util.IndexedFieldsFixture;
import com.liferay.portal.search.test.util.IndexerFixture;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.users.admin.test.util.search.UserSearchFixture;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Fabiano Nazar
 * @author Vagner B.C
 */
@RunWith(Arquillian.class)
public class LayoutIndexerIndexedFieldsTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		setUpUserSearchFixture();

		setUpIndexedFieldsFixture();

		setUpLayoutFixture();

		setUpLayoutIndexerFixture();

		defaultLocale = LocaleThreadLocal.getDefaultLocale();
	}

	@After
	public void tearDown() {
		LocaleThreadLocal.setDefaultLocale(defaultLocale);
	}

	@Test
	public void testIndexedFields() throws Exception {
		Locale locale = LocaleUtil.JAPAN;

		setTestLocale(locale);

		Layout layout = layoutFixture.createLayout("新しい商品");

		String searchTerm = "新しい";

		Document document = layoutIndexerFixture.searchOnlyOne(
			searchTerm, locale);

		indexedFieldsFixture.postProcessDocument(document);

		_postProcessDocument(document, layout);

		FieldValuesAssert.assertFieldValues(
			_expectedFieldValues(layout), document, searchTerm);
	}

	protected void setTestLocale(Locale locale) throws Exception {
		layoutFixture.updateDisplaySettings(locale);

		LocaleThreadLocal.setDefaultLocale(locale);
	}

	protected void setUpIndexedFieldsFixture() {
		indexedFieldsFixture = new IndexedFieldsFixture(
			resourcePermissionLocalService, uidFactory, documentBuilderFactory);
	}

	protected void setUpLayoutFixture() {
		layoutFixture = new LayoutFixture(_group);

		_layouts = layoutFixture.getLayouts();
	}

	protected void setUpLayoutIndexerFixture() {
		layoutIndexerFixture = new IndexerFixture<>(Layout.class);
	}

	protected void setUpUserSearchFixture() throws Exception {
		userSearchFixture = new UserSearchFixture();

		userSearchFixture.setUp();

		_group = userSearchFixture.addGroup();

		_groups = userSearchFixture.getGroups();

		_users = userSearchFixture.getUsers();
	}

	protected Locale defaultLocale;

	@Inject
	protected DocumentBuilderFactory documentBuilderFactory;

	protected IndexedFieldsFixture indexedFieldsFixture;
	protected LayoutFixture layoutFixture;
	protected IndexerFixture<Layout> layoutIndexerFixture;

	@Inject
	protected ResourcePermissionLocalService resourcePermissionLocalService;

	@Inject
	protected UIDFactory uidFactory;

	protected UserSearchFixture userSearchFixture;

	private Map<String, String> _expectedFieldValues(Layout layout)
		throws Exception {

		Map<String, String> map = HashMapBuilder.put(
			Field.CLASS_NAME_ID, String.valueOf(layout.getClassNameId())
		).put(
			Field.CLASS_PK, String.valueOf(layout.getClassPK())
		).put(
			Field.COMPANY_ID, String.valueOf(layout.getCompanyId())
		).put(
			Field.DEFAULT_LANGUAGE_ID, layout.getDefaultLanguageId()
		).put(
			Field.ENTRY_CLASS_NAME, Layout.class.getName()
		).put(
			Field.ENTRY_CLASS_PK, String.valueOf(layout.getPrimaryKey())
		).put(
			Field.GROUP_ID, String.valueOf(layout.getGroupId())
		).put(
			Field.SCOPE_GROUP_ID, String.valueOf(layout.getGroupId())
		).put(
			Field.STAGING_GROUP, "false"
		).put(
			Field.STATUS, String.valueOf(layout.getStatus())
		).put(
			Field.TYPE, layout.getType()
		).put(
			Field.USER_ID, String.valueOf(layout.getUserId())
		).put(
			Field.USER_NAME, StringUtil.toLowerCase(layout.getUserName())
		).put(
			"privateLayout", "false"
		).put(
			"title_ja_JP", layout.getName(LocaleUtil.JAPAN)
		).build();

		indexedFieldsFixture.populateUID(layout, map);

		_populateName(layout, map);
		_populateDates(layout, map);
		_populateRoles(layout, map);

		return map;
	}

	private void _populateDates(Layout layout, Map<String, String> map) {
		indexedFieldsFixture.populateDate(
			Field.MODIFIED_DATE, layout.getModifiedDate(), map);
		indexedFieldsFixture.populateDate(
			Field.CREATE_DATE, layout.getCreateDate(), map);
	}

	private void _populateName(Layout layout, Map<String, String> map) {
		map.put(Field.NAME, layout.getName(layout.getDefaultLanguageId()));

		for (String languageId : layout.getAvailableLanguageIds()) {
			Locale locale = LocaleUtil.fromLanguageId(languageId);

			map.put(
				LocalizationUtil.getLocalizedName(Field.NAME, languageId),
				layout.getName(locale));
		}
	}

	private void _populateRoles(Layout layout, Map<String, String> map)
		throws Exception {

		indexedFieldsFixture.populateRoleIdFields(
			layout.getCompanyId(), Layout.class.getName(),
			layout.getPrimaryKey(), layout.getGroupId(), null, map);
	}

	private void _postProcessDocument(Document document, Layout layout) {
		Set<Locale> locales = LanguageUtil.getAvailableLocales(
			layout.getGroupId());

		for (Locale locale : locales) {
			document.remove(
				LocalizationUtil.getLocalizedName(
					Field.CONTENT, LocaleUtil.toLanguageId(locale)));
		}
	}

	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	@DeleteAfterTestRun
	private List<Layout> _layouts;

	@DeleteAfterTestRun
	private List<User> _users;

}