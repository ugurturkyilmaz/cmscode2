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

package com.liferay.message.boards.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.test.util.FieldValuesAssert;
import com.liferay.portal.search.test.util.IndexedFieldsFixture;
import com.liferay.portal.search.test.util.IndexerFixture;
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.users.admin.test.util.search.UserSearchFixture;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luan Maoski
 */
@RunWith(Arquillian.class)
@Sync
public class MBThreadIndexerIndexedFieldsTest {

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
		setUpMBThreadIndexerFixture();
		setUpMBFixture();
	}

	@Test
	public void testIndexedFields() throws Exception {
		MBMessage mbMessage = mbFixture.createMBMessageWithCategory(
			RandomTestUtil.randomString());

		MBThread mbThread = mbMessage.getThread();

		String searchTerm = mbThread.getUserName();

		Document document = mbThreadIndexerFixture.searchOnlyOne(
			_user.getUserId(), searchTerm, LocaleUtil.US);

		indexedFieldsFixture.postProcessDocument(document);

		FieldValuesAssert.assertFieldValues(
			_expectedFieldValues(mbThread, mbMessage), document, searchTerm);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void setUpIndexedFieldsFixture() {
		indexedFieldsFixture = new IndexedFieldsFixture(
			resourcePermissionLocalService);
	}

	protected void setUpMBFixture() {
		mbFixture = new MBFixture(_group, _user);

		_mbThreads = mbFixture.getMbThreads();

		_mbCategories = mbFixture.getMbCategories();

		_mbMessages = mbFixture.getMbMessages();
	}

	protected void setUpMBThreadIndexerFixture() {
		mbThreadIndexerFixture = new IndexerFixture<>(MBThread.class);
	}

	protected void setUpUserSearchFixture() throws Exception {
		userSearchFixture = new UserSearchFixture();

		userSearchFixture.setUp();

		_group = userSearchFixture.addGroup();

		_groups = userSearchFixture.getGroups();

		_user = userSearchFixture.addUser(
			RandomTestUtil.randomString(), _group);

		_users = userSearchFixture.getUsers();
	}

	protected IndexedFieldsFixture indexedFieldsFixture;
	protected MBFixture mbFixture;
	protected IndexerFixture<MBThread> mbThreadIndexerFixture;

	@Inject
	protected ResourcePermissionLocalService resourcePermissionLocalService;

	protected UserSearchFixture userSearchFixture;

	private Map<String, String> _expectedFieldValues(
			MBThread mbThread, MBMessage mbMessage)
		throws Exception {

		Map<String, String> map = HashMapBuilder.put(
			Field.COMPANY_ID, String.valueOf(mbThread.getCompanyId())
		).put(
			Field.ENTRY_CLASS_NAME, MBThread.class.getName()
		).put(
			Field.ENTRY_CLASS_PK, String.valueOf(mbThread.getThreadId())
		).put(
			Field.GROUP_ID, String.valueOf(mbThread.getGroupId())
		).put(
			Field.SCOPE_GROUP_ID, String.valueOf(mbThread.getGroupId())
		).put(
			Field.STAGING_GROUP, String.valueOf(_group.isStagingGroup())
		).put(
			Field.STATUS, String.valueOf(mbThread.getStatus())
		).put(
			Field.USER_ID, String.valueOf(mbThread.getUserId())
		).put(
			Field.USER_NAME, StringUtil.lowerCase(mbThread.getUserName())
		).put(
			"discussion", "false"
		).put(
			"lastPostDate",
			() -> {
				Date lastPostDate = mbThread.getLastPostDate();

				return String.valueOf(lastPostDate.getTime());
			}
		).put(
			"participantUserIds",
			String.valueOf(_getValues(mbThread.getParticipantUserIds()))
		).build();

		indexedFieldsFixture.populateUID(
			MBThread.class.getName(), mbThread.getThreadId(), map);

		_populateDates(mbThread, mbMessage, map);
		_populateRoles(mbThread, map);

		return map;
	}

	private String _getValues(long[] longValues) {
		String[] stringArray = ArrayUtil.toStringArray(longValues);

		return _getValues(stringArray);
	}

	private String _getValues(String[] stringArray) {
		String values = StringUtils.join(stringArray, ", ");

		if (stringArray.length > 1) {
			values = '[' + values + ']';
		}

		return values;
	}

	private void _populateDates(
		MBThread mbThread, MBMessage mbMessage, Map<String, String> map) {

		indexedFieldsFixture.populateDate(
			Field.MODIFIED_DATE, mbMessage.getModifiedDate(), map);
		indexedFieldsFixture.populateDate(
			Field.CREATE_DATE, mbThread.getCreateDate(), map);
	}

	private void _populateRoles(MBThread mbThread, Map<String, String> map)
		throws Exception {

		indexedFieldsFixture.populateRoleIdFields(
			mbThread.getCompanyId(), MBThread.class.getName(),
			mbThread.getThreadId(), mbThread.getGroupId(), null, map);
	}

	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	@DeleteAfterTestRun
	private List<MBCategory> _mbCategories;

	@DeleteAfterTestRun
	private List<MBMessage> _mbMessages;

	@DeleteAfterTestRun
	private List<MBThread> _mbThreads;

	private User _user;

	@DeleteAfterTestRun
	private List<User> _users;

}