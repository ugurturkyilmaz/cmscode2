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
SearchContainer<AccountUserDisplay> accountUsersDisplaySearchContainer = AccountUserDisplaySearchContainerFactory.create(liferayPortletRequest, liferayPortletResponse);

AccountUsersAdminManagementToolbarDisplayContext accountUsersAdminManagementToolbarDisplayContext = new AccountUsersAdminManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, accountUsersDisplaySearchContainer);
%>

<style type="text/css">
	.lfr-search-container-wrapper .text-muted a {
		color: #a7a9bc;
	}

	.table thead th {
		color: #6b6c7e !important;
		font-style: normal !important;
	}
</style>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= accountUsersAdminManagementToolbarDisplayContext %>"
	propsTransformer="account_users_admin/js/AccountUsersAdminManagementToolbarPropsTransformer"
/>

<clay:container-fluid>
	<aui:form method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="accountUserIds" type="hidden" />

		<liferay-ui:search-container
			searchContainer="<%= accountUsersDisplaySearchContainer %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.account.admin.web.internal.display.AccountUserDisplay"
				keyProperty="userId"
				modelVar="accountUserDisplay"
			>

				<%
				row.setData(
					HashMapBuilder.<String, Object>put(
						"actions", StringUtil.merge(accountUsersAdminManagementToolbarDisplayContext.getAvailableActions(accountUserDisplay))
					).build());
				%>

				<portlet:renderURL var="rowURL">
					<portlet:param name="p_u_i_d" value="<%= String.valueOf(accountUserDisplay.getUserId()) %>" />
					<portlet:param name="mvcPath" value="/account_users_admin/edit_account_user.jsp" />
				</portlet:renderURL>

				<%
				if (!UserPermissionUtil.contains(permissionChecker, accountUserDisplay.getUserId(), ActionKeys.UPDATE) && !AccountPermission.contains(permissionChecker, AccountPortletKeys.ACCOUNT_USERS_ADMIN, AccountActionKeys.ASSIGN_ACCOUNTS)) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand-small table-cell-minw-150"
					href="<%= rowURL %>"
					name="name"
					value="<%= HtmlUtil.escape(accountUserDisplay.getName()) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand-small table-cell-minw-150"
					href="<%= rowURL %>"
					name="email"
					property="emailAddress"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand-small table-cell-minw-150"
					href="<%= rowURL %>"
					name="job-title"
					value="<%= HtmlUtil.escape(accountUserDisplay.getJobTitle()) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass='<%= "table-cell-expand-small table-cell-minw-150 " + accountUserDisplay.getAccountEntryNamesStyle() %>'
					href="<%= rowURL %>"
					name="accounts"
					value="<%= HtmlUtil.escape(accountUserDisplay.getAccountEntryNamesString(request)) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand"
					name="status"
				>
					<clay:label
						displayType="<%= accountUserDisplay.getStatusLabelStyle() %>"
						label="<%= accountUserDisplay.getStatusLabel() %>"
					/>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-jsp
					path="/account_users_admin/account_user_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</clay:container-fluid>