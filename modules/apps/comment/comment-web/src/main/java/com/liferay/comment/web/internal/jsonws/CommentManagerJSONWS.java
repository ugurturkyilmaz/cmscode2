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

package com.liferay.comment.web.internal.jsonws;

import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.comment.Discussion;
import com.liferay.portal.kernel.comment.DiscussionComment;
import com.liferay.portal.kernel.comment.DiscussionCommentIterator;
import com.liferay.portal.kernel.comment.DiscussionPermission;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionCheckerUtil;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@AccessControlled
@Component(
	immediate = true,
	property = {
		"json.web.service.context.name=comment",
		"json.web.service.context.path=Comment"
	},
	service = CommentManagerJSONWS.class
)
@JSONWebService
public class CommentManagerJSONWS extends BaseServiceImpl {

	public long addComment(
			long groupId, String className, long classPK, String body)
		throws PortalException {

		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(getPermissionChecker());

		long companyId = _getCompanyId(groupId);

		discussionPermission.checkAddPermission(
			companyId, groupId, className, classPK);

		return _commentManager.addComment(
			getUserId(), groupId, className, classPK, body,
			_createServiceContextFunction(companyId));
	}

	public void deleteComment(long commentId) throws PortalException {
		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(getPermissionChecker());

		discussionPermission.checkDeletePermission(commentId);

		_commentManager.deleteComment(commentId);
	}

	public List<CommentJSONWS> getComments(long commentId, int start, int end)
		throws PortalException {

		DiscussionComment discussionComment =
			_commentManager.fetchDiscussionComment(getUserId(), commentId);

		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(getPermissionChecker());

		discussionPermission.checkViewPermission(
			_getCompanyId(discussionComment.getGroupId()),
			discussionComment.getGroupId(), discussionComment.getClassName(),
			discussionComment.getClassPK());

		return getComments(discussionComment, start, end);
	}

	public List<CommentJSONWS> getComments(
			long groupId, String className, long classPK, int start, int end)
		throws PortalException {

		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(getPermissionChecker());

		discussionPermission.checkViewPermission(
			_getCompanyId(groupId), groupId, className, classPK);

		Discussion discussion = _commentManager.getDiscussion(
			getUserId(), groupId, className, classPK,
			_createServiceContextFunction());

		return getComments(discussion.getRootDiscussionComment(), start, end);
	}

	public int getCommentsCount(long groupId, String className, long classPK)
		throws PortalException {

		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(getPermissionChecker());

		discussionPermission.checkViewPermission(
			_getCompanyId(groupId), groupId, className, classPK);

		return _commentManager.getCommentsCount(className, classPK);
	}

	public boolean hasDiscussion(long groupId, String className, long classPK)
		throws PortalException {

		BaseModelPermissionCheckerUtil.containsBaseModelPermission(
			getPermissionChecker(), groupId, className, classPK,
			ActionKeys.VIEW);

		return _commentManager.hasDiscussion(className, classPK);
	}

	public void subscribeDiscussion(
			long groupId, String className, long classPK)
		throws PortalException {

		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(getPermissionChecker());

		discussionPermission.checkSubscribePermission(
			_getCompanyId(groupId), groupId, className, classPK);

		_commentManager.subscribeDiscussion(
			getUserId(), groupId, className, classPK);
	}

	public void unsubscribeDiscussion(
			long groupId, String className, long classPK)
		throws PortalException {

		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(getPermissionChecker());

		discussionPermission.checkSubscribePermission(
			_getCompanyId(groupId), groupId, className, classPK);

		_commentManager.unsubscribeDiscussion(getUserId(), className, classPK);
	}

	public long updateComment(
			String className, long classPK, long commentId, String subject,
			String body)
		throws PortalException {

		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(getPermissionChecker());

		discussionPermission.checkUpdatePermission(commentId);

		return _commentManager.updateComment(
			getUserId(), className, classPK, commentId, subject, body,
			_createServiceContextFunction(WorkflowConstants.ACTION_PUBLISH));
	}

	protected List<CommentJSONWS> getComments(
		DiscussionComment discussionComment, int start, int end) {

		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		DiscussionCommentIterator threadDiscussionCommentIterator =
			discussionComment.getThreadDiscussionCommentIterator(start);

		if (end == QueryUtil.ALL_POS) {
			return _getAllComments(threadDiscussionCommentIterator);
		}

		int commentsCount = end - start;

		if (commentsCount <= 0) {
			return Collections.emptyList();
		}

		List<CommentJSONWS> commentJSONWSs = new ArrayList<>(commentsCount);

		while (threadDiscussionCommentIterator.hasNext() &&
			   (commentsCount > 0)) {

			CommentJSONWS commentJSONWS = new CommentJSONWS(
				threadDiscussionCommentIterator.next());

			commentJSONWSs.add(commentJSONWS);

			commentsCount--;
		}

		return commentJSONWSs;
	}

	protected String getUserName() throws PortalException {
		User user = getUser();

		return user.getFullName();
	}

	private Function<String, ServiceContext> _createServiceContextFunction() {
		return new Function<String, ServiceContext>() {

			@Override
			public ServiceContext apply(String className) {
				return new ServiceContext();
			}

		};
	}

	private Function<String, ServiceContext> _createServiceContextFunction(
		final int workflowAction) {

		return new Function<String, ServiceContext>() {

			@Override
			public ServiceContext apply(String className) {
				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setWorkflowAction(workflowAction);

				return serviceContext;
			}

		};
	}

	private Function<String, ServiceContext> _createServiceContextFunction(
		final long companyId) {

		return new Function<String, ServiceContext>() {

			@Override
			public ServiceContext apply(String className) {
				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCompanyId(companyId);

				return serviceContext;
			}

		};
	}

	private List<CommentJSONWS> _getAllComments(
		DiscussionCommentIterator threadDiscussionCommentIterator) {

		List<CommentJSONWS> commentJSONWSs = new ArrayList<>();

		while (threadDiscussionCommentIterator.hasNext()) {
			CommentJSONWS commentJSONWS = new CommentJSONWS(
				threadDiscussionCommentIterator.next());

			commentJSONWSs.add(commentJSONWS);
		}

		return commentJSONWSs;
	}

	private long _getCompanyId(long groupId) throws PortalException {
		Group group = _groupLocalService.getGroup(groupId);

		return group.getCompanyId();
	}

	@Reference
	private CommentManager _commentManager;

	@Reference
	private GroupLocalService _groupLocalService;

}