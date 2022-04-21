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

package com.liferay.depot.roles.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.depot.test.util.DepotTestUtil;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserGroupTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alicia García
 */
@RunWith(Arquillian.class)
public class DepotRoleLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testGetAssigneesTotal() throws Exception {
		DepotTestUtil.withDepotUser(
			(user, role) -> {
				DepotEntry depotEntry = _addDepotEntry("name", "description");

				_userGroupRoleLocalService.addUserGroupRoles(
					user.getUserId(), depotEntry.getGroupId(),
					new long[] {role.getRoleId()});

				Assert.assertEquals(
					1, _roleLocalService.getAssigneesTotal(role.getRoleId()));
			});
	}

	@Test
	public void testGetAssigneesTotalWithUserGroup() throws Exception {
		DepotTestUtil.withDepotUser(
			(user, role) -> {
				DepotEntry depotEntry = _addDepotEntry("name", "description");

				_userGroup = UserGroupTestUtil.addUserGroup();

				_groupLocalService.addUserGroup(
					user.getUserId(), depotEntry.getGroup());

				_groupLocalService.addUserGroupGroup(
					_userGroup.getUserGroupId(), depotEntry.getGroup());

				_userGroupGroupRoleLocalService.addUserGroupGroupRoles(
					_userGroup.getGroupId(), depotEntry.getGroupId(),
					new long[] {role.getRoleId()});

				_userGroupRoleLocalService.addUserGroupRoles(
					user.getUserId(), depotEntry.getGroupId(),
					new long[] {role.getRoleId()});

				Assert.assertEquals(
					2, _roleLocalService.getAssigneesTotal(role.getRoleId()));
			});
	}

	private DepotEntry _addDepotEntry(String name, String description)
		throws Exception {

		DepotEntry depotEntry = _depotEntryLocalService.addDepotEntry(
			HashMapBuilder.put(
				LocaleUtil.getDefault(), name
			).build(),
			HashMapBuilder.put(
				LocaleUtil.getDefault(), description
			).build(),
			ServiceContextTestUtil.getServiceContext());

		_depotEntries.add(depotEntry);

		return depotEntry;
	}

	@DeleteAfterTestRun
	private final List<DepotEntry> _depotEntries = new ArrayList<>();

	@Inject
	private DepotEntryLocalService _depotEntryLocalService;

	@Inject
	private GroupLocalService _groupLocalService;

	@Inject
	private RoleLocalService _roleLocalService;

	@DeleteAfterTestRun
	private UserGroup _userGroup;

	@Inject
	private UserGroupGroupRoleLocalService _userGroupGroupRoleLocalService;

	@Inject
	private UserGroupRoleLocalService _userGroupRoleLocalService;

}