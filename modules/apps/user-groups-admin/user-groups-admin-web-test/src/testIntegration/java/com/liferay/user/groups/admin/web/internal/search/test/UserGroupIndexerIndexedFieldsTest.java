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

package com.liferay.user.groups.admin.web.internal.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.document.DocumentBuilderFactory;
import com.liferay.portal.search.model.uid.UIDFactory;
import com.liferay.portal.search.test.util.ExpandoTableSearchFixture;
import com.liferay.portal.search.test.util.FieldValuesAssert;
import com.liferay.portal.search.test.util.IndexedFieldsFixture;
import com.liferay.portal.search.test.util.IndexerFixture;
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.users.admin.test.util.search.UserSearchFixture;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Fabiano Nazar
 * @author Luan Maoski
 */
@RunWith(Arquillian.class)
public class UserGroupIndexerIndexedFieldsTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_setUpExpandoTableSearchFixture();

		setUpUserSearchFixture();

		_setUpIndexedFieldsFixture();

		setUpUserGroupIndexerFixture();

		setUpUserGroupFixture();
	}

	@Test
	public void testIndexedFields() throws Exception {
		UserGroup userGroup = userGroupFixture.createUserGroup();

		String searchTerm = userGroup.getName();

		Document document = userGroupIndexerFixture.searchOnlyOne(searchTerm);

		indexedFieldsFixture.postProcessDocument(document);

		FieldValuesAssert.assertFieldValues(
			_expectedFieldValues(userGroup), document, searchTerm);
	}

	@Test
	public void testIndexedFieldsWithExpando() throws Exception {
		String expandoColumnName = "expandoColumnName";

		String expandoColumnObs = "expandoColumnObs";

		expandoTableSearchFixture.addExpandoColumn(
			UserGroup.class, ExpandoColumnConstants.INDEX_TYPE_KEYWORD,
			expandoColumnObs, expandoColumnName);

		UserGroup userGroup = userGroupFixture.createUserGroup(
			HashMapBuilder.<String, Serializable>put(
				expandoColumnName, "Software Developer"
			).put(
				expandoColumnObs, "Software Engineer"
			).build());

		String searchTerm = userGroup.getName();

		Document document = userGroupIndexerFixture.searchOnlyOne(searchTerm);

		indexedFieldsFixture.postProcessDocument(document);

		FieldValuesAssert.assertFieldValues(
			_expectedFieldValuesWithExpando(userGroup), document, searchTerm);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void setUpUserGroupFixture() {
		userGroupFixture = new UserGroupFixture(_group, userGroupLocalService);

		_userGroups = userGroupFixture.getUserGroups();
	}

	protected void setUpUserGroupIndexerFixture() {
		userGroupIndexerFixture = new IndexerFixture<>(UserGroup.class);
	}

	protected void setUpUserSearchFixture() throws Exception {
		userSearchFixture = new UserSearchFixture();

		userSearchFixture.setUp();

		_group = userSearchFixture.addGroup();

		_groups = userSearchFixture.getGroups();

		_users = userSearchFixture.getUsers();
	}

	@Inject
	protected ClassNameLocalService classNameLocalService;

	@Inject
	protected DocumentBuilderFactory documentBuilderFactory;

	@Inject
	protected ExpandoColumnLocalService expandoColumnLocalService;

	@Inject
	protected ExpandoTableLocalService expandoTableLocalService;

	protected ExpandoTableSearchFixture expandoTableSearchFixture;
	protected IndexedFieldsFixture indexedFieldsFixture;

	@Inject
	protected ResourcePermissionLocalService resourcePermissionLocalService;

	@Inject
	protected UIDFactory uidFactory;

	protected UserGroupFixture userGroupFixture;
	protected IndexerFixture<UserGroup> userGroupIndexerFixture;

	@Inject
	protected UserGroupLocalService userGroupLocalService;

	protected UserSearchFixture userSearchFixture;

	private Map<String, String> _expectedFieldValues(UserGroup userGroup)
		throws Exception {

		Map<String, String> map = HashMapBuilder.put(
			Field.COMPANY_ID, String.valueOf(userGroup.getCompanyId())
		).put(
			Field.DESCRIPTION, userGroup.getDescription()
		).put(
			Field.ENTRY_CLASS_NAME, UserGroup.class.getName()
		).put(
			Field.ENTRY_CLASS_PK, String.valueOf(userGroup.getUserGroupId())
		).put(
			Field.NAME, userGroup.getName()
		).put(
			Field.USER_GROUP_ID, String.valueOf(userGroup.getUserGroupId())
		).put(
			Field.USER_ID, String.valueOf(userGroup.getUserId())
		).put(
			Field.USER_NAME, StringUtil.lowerCase(userGroup.getUserName())
		).put(
			"name_sortable", StringUtil.lowerCase(userGroup.getName())
		).build();

		indexedFieldsFixture.populateUID(userGroup, map);

		_populateDates(userGroup, map);
		_populateRoles(userGroup, map);

		return map;
	}

	private Map<String, String> _expectedFieldValuesWithExpando(
			UserGroup userGroup)
		throws Exception {

		Map<String, String> expectedFieldValues = _expectedFieldValues(
			userGroup);

		expectedFieldValues.put(
			"expando__keyword__custom_fields__expandoColumnName",
			"Software Developer");
		expectedFieldValues.put(
			"expando__keyword__custom_fields__expandoColumnObs",
			"Software Engineer");

		return expectedFieldValues;
	}

	private void _populateDates(UserGroup userGroup, Map<String, String> map) {
		indexedFieldsFixture.populateDate(
			Field.CREATE_DATE, userGroup.getCreateDate(), map);
		indexedFieldsFixture.populateDate(
			Field.MODIFIED_DATE, userGroup.getModifiedDate(), map);
	}

	private void _populateRoles(UserGroup userGroup, Map<String, String> map)
		throws Exception {

		indexedFieldsFixture.populateRoleIdFields(
			userGroup.getCompanyId(), UserGroup.class.getName(),
			userGroup.getUserGroupId(), userGroup.getGroupId(), null, map);
	}

	private void _setUpExpandoTableSearchFixture() {
		expandoTableSearchFixture = new ExpandoTableSearchFixture(
			classNameLocalService, expandoColumnLocalService,
			expandoTableLocalService);

		_expandoColumns = expandoTableSearchFixture.getExpandoColumns();

		_expandoTables = expandoTableSearchFixture.getExpandoTables();
	}

	private void _setUpIndexedFieldsFixture() {
		indexedFieldsFixture = new IndexedFieldsFixture(
			resourcePermissionLocalService, uidFactory, documentBuilderFactory);
	}

	@DeleteAfterTestRun
	private List<ExpandoColumn> _expandoColumns;

	@DeleteAfterTestRun
	private List<ExpandoTable> _expandoTables;

	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	@DeleteAfterTestRun
	private List<UserGroup> _userGroups;

	@DeleteAfterTestRun
	private List<User> _users;

}