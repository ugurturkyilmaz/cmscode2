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

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SchedulerResponse schedulerResponse = (SchedulerResponse)row.getObject();

SchedulerResponseDisplayContext schedulerResponseDisplayContext = (SchedulerResponseDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

String cmd = (schedulerResponseDisplayContext.getTriggerState(schedulerResponse) == TriggerState.NORMAL) ? "pause" : "resume";
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:actionURL name="/dispatch/edit_scheduler_response" var="editScheduledTaskURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= cmd %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="jobName" value="<%= schedulerResponse.getJobName() %>" />
		<portlet:param name="groupName" value="<%= schedulerResponse.getGroupName() %>" />
		<portlet:param name="storageType" value="<%= schedulerResponse.getStorageType().toString() %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		message="<%= cmd %>"
		method="post"
		url="<%= editScheduledTaskURL %>"
	/>
</liferay-ui:icon-menu>