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

package com.liferay.calendar.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalService;
import com.liferay.calendar.service.CalendarService;
import com.liferay.calendar.test.util.CalendarStagingTestUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.context.ContextUserReplace;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class CalendarServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@After
	public void tearDown() {
		CalendarStagingTestUtil.cleanUp();
	}

	@Test
	public void testIsManageableFromGroup() throws Exception {
		_liveGroup = GroupTestUtil.addGroup();

		GroupTestUtil.enableLocalStaging(_liveGroup);

		_notStagedGroup = GroupTestUtil.addGroup();

		Group stagingGroup = _liveGroup.getStagingGroup();

		_adminUser = UserTestUtil.addOmniAdminUser();

		try (ContextUserReplace contextUserReplace = new ContextUserReplace(
				_adminUser)) {

			Calendar notStagedCalendar = getGroupCalendar(_notStagedGroup);

			assertManageableFromGroup(notStagedCalendar, _notStagedGroup);
			assertManageableFromGroup(notStagedCalendar, _liveGroup);
			assertManageableFromGroup(notStagedCalendar, stagingGroup);

			Calendar liveCalendar = getGroupCalendar(_liveGroup);

			assertNotManageableFromGroup(liveCalendar, _notStagedGroup);
			assertNotManageableFromGroup(liveCalendar, _liveGroup);
			assertNotManageableFromGroup(liveCalendar, stagingGroup);

			Calendar stagingCalendar = getGroupCalendar(stagingGroup);

			assertNotManageableFromGroup(stagingCalendar, _notStagedGroup);
			assertNotManageableFromGroup(stagingCalendar, _liveGroup);
			assertManageableFromGroup(stagingCalendar, stagingGroup);
		}
	}

	protected void assertManageableFromGroup(Calendar calendar, Group group)
		throws PortalException {

		Assert.assertTrue(
			_calendarService.isManageableFromGroup(
				calendar.getCalendarId(), group.getGroupId()));
	}

	protected void assertNotManageableFromGroup(Calendar calendar, Group group)
		throws PortalException {

		Assert.assertFalse(
			_calendarService.isManageableFromGroup(
				calendar.getCalendarId(), group.getGroupId()));
	}

	protected Calendar getGroupCalendar(Group group) throws Exception {
		CalendarResource calendarResource =
			_calendarResourceLocalService.fetchCalendarResource(
				PortalUtil.getClassNameId(Group.class), group.getGroupId());

		if (calendarResource == null) {
			Map<Locale, String> nameMap = HashMapBuilder.put(
				LocaleUtil.getSiteDefault(), group.getDescriptiveName()
			).build();

			Map<Locale, String> descriptionMap = new HashMap<>();

			calendarResource =
				_calendarResourceLocalService.addCalendarResource(
					group.getCreatorUserId(), group.getGroupId(),
					PortalUtil.getClassNameId(Group.class), group.getGroupId(),
					null, null, nameMap, descriptionMap, true,
					new ServiceContext());
		}

		return calendarResource.getDefaultCalendar();
	}

	private User _adminUser;

	@Inject
	private CalendarResourceLocalService _calendarResourceLocalService;

	@Inject
	private CalendarService _calendarService;

	private Group _liveGroup;
	private Group _notStagedGroup;

}