<%--
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
--%>

<%@ include file="/discussion/init.jsp" %>

<%
int index = GetterUtil.getInteger(request.getAttribute("liferay-comment:discussion:index"));
int initialIndex = GetterUtil.getInteger(request.getAttribute("liferay-comment:discussion:index"));
String originalNamespace = ParamUtil.getString(request, "namespace");
String randomNamespace = GetterUtil.getString(request.getAttribute("liferay-comment:discussion:randomNamespace"));
int rootIndexPage = GetterUtil.getInteger(request.getAttribute("liferay-comment:discussion:rootIndexPage"));

DiscussionRequestHelper discussionRequestHelper = new DiscussionRequestHelper(request);
DiscussionTaglibHelper discussionTaglibHelper = new DiscussionTaglibHelper(request);

Discussion discussion = CommentManagerUtil.getDiscussion(discussionTaglibHelper.getUserId(), discussionRequestHelper.getScopeGroupId(), discussionTaglibHelper.getClassName(), discussionTaglibHelper.getClassPK(), new ServiceContextFunction(request));

DiscussionComment rootDiscussionComment = (discussion == null) ? null : discussion.getRootDiscussionComment();

DiscussionCommentIterator discussionCommentIterator = (rootDiscussionComment == null) ? null : rootDiscussionComment.getThreadDiscussionCommentIterator(rootIndexPage - 1);
%>

<c:if test="<%= discussionCommentIterator != null %>">

	<%
	while (discussionCommentIterator.hasNext()) {
		rootIndexPage = discussionCommentIterator.getIndexPage();

		if (index >= (initialIndex + PropsValues.DISCUSSION_COMMENTS_DELTA_VALUE)) {
			break;
		}

		request.setAttribute("aui:form:portletNamespace", originalNamespace + randomNamespace);
		request.setAttribute("liferay-comment:discussion:depth", 0);
		request.setAttribute("liferay-comment:discussion:discussion", discussion);
		request.setAttribute("liferay-comment:discussion:discussionComment", discussionCommentIterator.next());
	%>

		<liferay-util:include page="/discussion/view_message_thread.jsp" servletContext="<%= application %>" />

	<%
		index = GetterUtil.getInteger(request.getAttribute("liferay-comment:discussion:index"));
	}
	%>

</c:if>

<liferay-frontend:component
	context='<%=
		HashMapBuilder.<String, Object>put(
			"hideMoreComments", (rootDiscussionComment != null) && (discussion.getDiscussionCommentsCount() <= index)
		).put(
			"index", index
		).put(
			"originalNamespace", originalNamespace
		).put(
			"randomNamespace", randomNamespace
		).put(
			"rootIndexPage", rootIndexPage
		).build()
	%>'
	module="discussion/js/PageResources"
/>