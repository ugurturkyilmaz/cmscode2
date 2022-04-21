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

<%@ include file="/process_list/init.jsp" %>

<portlet:actionURL name="/staging_processes/delete_background_tasks" var="deleteBackgroundTasksURL">
	<portlet:param name="redirect" value="<%= currentURL.toString() %>" />
</portlet:actionURL>

<%
ProcessListDisplayContext processListDisplayContext = new ProcessListDisplayContext(groupId, request, liferayPortletResponse, liveGroup);
%>

<aui:form action="<%= deleteBackgroundTasksURL %>" cssClass="<%= processListDisplayContext.getProcessListListViewCss() %>" method="get" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL.toString() %>" />
	<aui:input name="deleteBackgroundTaskIds" type="hidden" />

	<liferay-staging:process-error
		authException="<%= true %>"
		remoteExportException="<%= true %>"
		remoteOptionsException="<%= true %>"
	/>

	<liferay-ui:search-container
		id='<%= ParamUtil.getString(request, "searchContainerId") %>'
		searchContainer="<%= processListDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.backgroundtask.BackgroundTask"
			keyProperty="backgroundTaskId"
			modelVar="backgroundTask"
		>
			<c:choose>
				<c:when test='<%= Objects.equals(processListDisplayContext.getDisplayStyle(), "descriptive") %>'>
					<liferay-ui:search-container-column-text
						valign="top"
					>
						<liferay-ui:user-portrait
							userId="<%= backgroundTask.getUserId() %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<liferay-staging:process-info
							backgroundTask="<%= backgroundTask %>"
						/>

						<liferay-staging:process-message-task-details
							backgroundTaskId="<%= backgroundTask.getBackgroundTaskId() %>"
							backgroundTaskStatusMessage="<%= backgroundTask.getStatusMessage() %>"
							linkClass="background-task-status-row"
						/>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= Objects.equals(processListDisplayContext.getDisplayStyle(), "list") %>'>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand table-cell-minw-200 table-title"
						name="title"
					>
						<liferay-ui:user-portrait
							userId="<%= backgroundTask.getUserId() %>"
						/>

						<liferay-staging:process-title
							backgroundTask="<%= backgroundTask %>"
							listView="<%= true %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="background-task-status-column"
						name="status"
					>
						<liferay-staging:process-status
							backgroundTaskStatus="<%= backgroundTask.getStatus() %>"
							backgroundTaskStatusLabel="<%= backgroundTask.getStatusLabel() %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-date
						cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
						name="create-date"
						orderable="<%= true %>"
						value="<%= backgroundTask.getCreateDate() %>"
					/>

					<liferay-ui:search-container-column-date
						cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
						name="completion-date"
						orderable="<%= true %>"
						value="<%= backgroundTask.getCompletionDate() %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="background-task-status-column table-cell-expand table-cell-minw-200"
					>
						<liferay-staging:process-in-progress
							backgroundTask="<%= backgroundTask %>"
							listView="<%= true %>"
						/>
					</liferay-ui:search-container-column-text>
				</c:when>
			</c:choose>

			<liferay-ui:search-container-column-text>
				<liferay-staging:process-list-menu
					backgroundTask="<%= backgroundTask %>"
					deleteMenu="<%= deleteMenu %>"
					localPublishing="<%= processListDisplayContext.isLocalPublishing() %>"
					relaunchMenu="<%= relaunchMenu %>"
					summaryMenu="<%= summaryMenu && !(backgroundTask.getStatus() == BackgroundTaskConstants.STATUS_FAILED) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= processListDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
			resultRowSplitter="<%= resultRowSplitter %>"
		/>
	</liferay-ui:search-container>
</aui:form>

<liferay-staging:incomplete-process-message
	localPublishing="<%= processListDisplayContext.isLocalPublishing() %>"
/>