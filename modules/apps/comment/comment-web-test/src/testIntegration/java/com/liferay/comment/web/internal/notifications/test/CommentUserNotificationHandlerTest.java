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

package com.liferay.comment.web.internal.notifications.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.message.boards.model.MBDiscussion;
import com.liferay.message.boards.service.MBDiscussionLocalServiceUtil;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationFeedEntry;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.UserNotificationEventImpl;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adolfo Pérez
 */
@RunWith(Arquillian.class)
public class CommentUserNotificationHandlerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testGetBody() throws Exception {
		_commentManager.addDiscussion(
			TestPropsValues.getUserId(), TestPropsValues.getGroupId(),
			User.class.getName(), TestPropsValues.getUserId(),
			StringUtil.randomString());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				TestPropsValues.getGroupId());

		MBDiscussion discussion = MBDiscussionLocalServiceUtil.getDiscussion(
			User.class.getName(), TestPropsValues.getUserId());

		String content = "#63;";

		UserNotificationEvent userNotificationEvent =
			new UserNotificationEventImpl();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		userNotificationEvent.setPayload(
			jsonObject.put(
				"className", MBDiscussion.class.getName()
			).put(
				"classPK", discussion.getDiscussionId()
			).put(
				"entryTitle", content
			).put(
				"fullName", StringUtil.randomString()
			).put(
				"notificationType",
				UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY
			).toString());

		UserNotificationFeedEntry userNotificationFeedEntry =
			_userNotificationHandler.interpret(
				userNotificationEvent, serviceContext);

		String body = userNotificationFeedEntry.getBody();

		Assert.assertTrue(
			String.format("%s should contain %s", body, content),
			body.contains(content));
	}

	@Inject
	private CommentManager _commentManager;

	@Inject(
		filter = "javax.portlet.name=com_liferay_comment_web_portlet_CommentPortlet"
	)
	private UserNotificationHandler _userNotificationHandler;

}