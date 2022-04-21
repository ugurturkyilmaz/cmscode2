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

package com.liferay.headless.admin.user.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.headless.admin.user.client.dto.v1_0.SegmentUser;
import com.liferay.headless.admin.user.client.pagination.Page;
import com.liferay.headless.admin.user.client.problem.Problem;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.SynchronousMailTestRule;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.test.util.SegmentsTestUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Javier Gamarra
 */
@RunWith(Arquillian.class)
public class SegmentUserResourceTest extends BaseSegmentUserResourceTestCase {

	@ClassRule
	@Rule
	public static final SynchronousMailTestRule synchronousMailTestRule =
		SynchronousMailTestRule.INSTANCE;

	@Test
	public void testGetSegmentUserAccountsEmptyPage() throws Exception {
		_filterString = "(contains(emailAddress, 'invalid'))";

		Page<SegmentUser> page = segmentUserResource.getSegmentUserAccountsPage(
			testGetSegmentUserAccountsPage_getSegmentId(), null);

		Assert.assertEquals(0, page.getTotalCount());
	}

	@Override
	@Test
	public void testGetSegmentUserAccountsPage() throws Exception {
		Page<SegmentUser> page = segmentUserResource.getSegmentUserAccountsPage(
			testGetSegmentUserAccountsPage_getSegmentId(), null);

		long totalCount = page.getTotalCount();

		Long segmentId = testGetSegmentUserAccountsPage_getSegmentId();

		testGetSegmentUserAccountsPage_addSegmentUser(
			segmentId, randomSegmentUser());
		testGetSegmentUserAccountsPage_addSegmentUser(
			segmentId, randomSegmentUser());

		page = segmentUserResource.getSegmentUserAccountsPage(segmentId, null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertValid(page);
	}

	@Test(expected = Problem.ProblemException.class)
	public void testGetSegmentUserAccountsPageWithNonexistingSegmentId()
		throws Exception {

		segmentUserResource.getSegmentUserAccountsPage(
			RandomTestUtil.randomLong(), null);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	@Override
	protected SegmentUser randomSegmentUser() {
		return new SegmentUser() {
			{
				emailAddress = RandomTestUtil.randomString() + "@liferay.com";
				id = RandomTestUtil.randomLong();
				name = RandomTestUtil.randomString();
			}
		};
	}

	@Override
	protected SegmentUser testGetSegmentUserAccountsPage_addSegmentUser(
			Long segmentId, SegmentUser segmentUser)
		throws Exception {

		User user = UserTestUtil.addUser(
			PortalUtil.getDefaultCompanyId(), UserConstants.USER_ID_DEFAULT,
			null, segmentUser.getEmailAddress(), StringPool.BLANK,
			LocaleUtil.getDefault(), segmentUser.getName(),
			segmentUser.getName(), null, new ServiceContext());

		_users.add(user);

		return _toSegmentUser(user);
	}

	@Override
	protected Long testGetSegmentUserAccountsPage_getSegmentId()
		throws Exception {

		SegmentsEntry segmentsEntry = SegmentsTestUtil.addSegmentsEntry(
			testGroup.getGroupId(),
			JSONUtil.put(
				"criteria",
				JSONUtil.put(
					"user",
					JSONUtil.put(
						"conjunction", "and"
					).put(
						"filterString", _filterString
					))
			).toString(),
			User.class.getName());

		return segmentsEntry.getSegmentsEntryId();
	}

	private SegmentUser _toSegmentUser(User user) {
		return new SegmentUser() {
			{
				emailAddress = user.getEmailAddress();
				id = user.getUserId();
				name = user.getFullName();
			}
		};
	}

	private String _filterString =
		"(contains(emailAddress, 'liferay') and (not (emailAddress eq " +
			"'test@liferay.com')))";

	@DeleteAfterTestRun
	private final List<User> _users = new ArrayList<>();

}