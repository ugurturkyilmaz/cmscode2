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

package com.liferay.social.activity.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.social.activity.service.test.util.SocialActivityTestUtil;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.model.SocialActivityCounter;
import com.liferay.social.kernel.model.SocialActivityCounterConstants;
import com.liferay.social.kernel.model.SocialActivityLimit;
import com.liferay.social.kernel.service.SocialActivityCounterLocalServiceUtil;
import com.liferay.social.kernel.service.SocialActivitySettingLocalServiceUtil;
import com.liferay.social.kernel.util.SocialCounterPeriodUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@RunWith(Arquillian.class)
public class SocialActivityCounterLocalServiceTest
	extends BaseSocialActivityTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		SocialActivitySettingLocalServiceUtil.updateActivitySetting(
			group.getGroupId(), TEST_MODEL, true);
	}

	@Test
	public void testAddActivity() throws Exception {
		SocialActivityTestUtil.addActivity(creatorUser, group, assetEntry, 1);

		SocialActivityCounter contribution =
			SocialActivityTestUtil.getActivityCounter(
				group.getGroupId(),
				SocialActivityCounterConstants.NAME_CONTRIBUTION, creatorUser);

		Assert.assertNull(contribution);

		SocialActivityCounter participation =
			SocialActivityTestUtil.getActivityCounter(
				group.getGroupId(),
				SocialActivityCounterConstants.NAME_PARTICIPATION, creatorUser);

		Assert.assertEquals(2, participation.getCurrentValue());

		SocialActivityTestUtil.addActivity(actorUser, group, assetEntry, 2);

		contribution = SocialActivityTestUtil.getActivityCounter(
			group.getGroupId(),
			SocialActivityCounterConstants.NAME_CONTRIBUTION, creatorUser);

		Assert.assertNotNull(contribution);
		Assert.assertEquals(1, contribution.getCurrentValue());

		participation = SocialActivityTestUtil.getActivityCounter(
			group.getGroupId(),
			SocialActivityCounterConstants.NAME_PARTICIPATION, actorUser);

		Assert.assertNotNull(participation);
		Assert.assertEquals(1, participation.getCurrentValue());

		SocialActivityLimit activityLimit =
			SocialActivityTestUtil.getActivityLimit(
				group.getGroupId(), actorUser, assetEntry, 2,
				SocialActivityCounterConstants.NAME_PARTICIPATION);

		Assert.assertNotNull(activityLimit);
		Assert.assertEquals(1, activityLimit.getCount());

		SocialActivityTestUtil.addActivity(actorUser, group, assetEntry, 2);

		activityLimit = SocialActivityTestUtil.getActivityLimit(
			group.getGroupId(), actorUser, assetEntry, 2,
			SocialActivityCounterConstants.NAME_PARTICIPATION);

		Assert.assertNotNull(activityLimit);
		Assert.assertEquals(2, activityLimit.getCount());
	}

	@Test
	public void testAddThenRevokeVote() throws Exception {
		SocialActivityTestUtil.addActivity(creatorUser, group, assetEntry, 1);

		SocialActivityTestUtil.addActivity(
			actorUser, group, assetEntry,
			SocialActivityConstants.TYPE_ADD_VOTE);

		SocialActivityCounter contribution =
			SocialActivityTestUtil.getActivityCounter(
				group.getGroupId(),
				SocialActivityCounterConstants.NAME_CONTRIBUTION, creatorUser);

		Assert.assertNotNull(contribution);
		Assert.assertEquals(5, contribution.getCurrentValue());

		SocialActivityTestUtil.addActivity(
			actorUser, group, assetEntry,
			SocialActivityConstants.TYPE_REVOKE_VOTE);

		contribution = SocialActivityTestUtil.getActivityCounter(
			group.getGroupId(),
			SocialActivityCounterConstants.NAME_CONTRIBUTION, creatorUser);

		Assert.assertNotNull(contribution);
		Assert.assertEquals(0, contribution.getCurrentValue());
	}

	@Test
	public void testToggleActivities() throws Exception {
		SocialActivityTestUtil.addActivity(creatorUser, group, assetEntry, 1);

		SocialActivityTestUtil.addActivity(actorUser, group, assetEntry, 2);

		SocialActivityCounter contribution =
			SocialActivityTestUtil.getActivityCounter(
				group.getGroupId(),
				SocialActivityCounterConstants.NAME_CONTRIBUTION, creatorUser);

		Assert.assertNotNull(contribution);
		Assert.assertEquals(1, contribution.getCurrentValue());

		List<SocialActivityCounter> counters =
			SocialActivityCounterLocalServiceUtil.getPeriodActivityCounters(
				group.getGroupId(), "asset.test.2",
				SocialCounterPeriodUtil.getStartPeriod(), -1);

		Assert.assertEquals(counters.toString(), 1, counters.size());

		SocialActivityCounterLocalServiceUtil.disableActivityCounters(
			assetEntry.getClassName(), assetEntry.getClassPK());

		contribution = SocialActivityTestUtil.getActivityCounter(
			group.getGroupId(),
			SocialActivityCounterConstants.NAME_CONTRIBUTION, creatorUser);

		Assert.assertNotNull(contribution);
		Assert.assertEquals(0, contribution.getCurrentValue());

		SocialActivityCounter counter =
			SocialActivityTestUtil.getActivityCounter(
				group.getGroupId(), "asset.test.2", assetEntry);

		Assert.assertNotNull(counter);
		Assert.assertFalse(counter.isActive());

		counters =
			SocialActivityCounterLocalServiceUtil.getPeriodActivityCounters(
				group.getGroupId(), "asset.test.2",
				SocialCounterPeriodUtil.getStartPeriod(), -1);

		Assert.assertEquals(counters.toString(), 0, counters.size());

		SocialActivityCounterLocalServiceUtil.enableActivityCounters(
			assetEntry.getClassName(), assetEntry.getClassPK());

		contribution = SocialActivityTestUtil.getActivityCounter(
			group.getGroupId(),
			SocialActivityCounterConstants.NAME_CONTRIBUTION, creatorUser);

		Assert.assertNotNull(contribution);
		Assert.assertEquals(1, contribution.getCurrentValue());

		counter = SocialActivityTestUtil.getActivityCounter(
			group.getGroupId(), "asset.test.2", assetEntry);

		Assert.assertNotNull(counter);
		Assert.assertTrue(counter.isActive());

		counters =
			SocialActivityCounterLocalServiceUtil.getPeriodActivityCounters(
				group.getGroupId(), "asset.test.2",
				SocialCounterPeriodUtil.getStartPeriod(), -1);

		Assert.assertEquals(counters.toString(), 1, counters.size());
	}

}