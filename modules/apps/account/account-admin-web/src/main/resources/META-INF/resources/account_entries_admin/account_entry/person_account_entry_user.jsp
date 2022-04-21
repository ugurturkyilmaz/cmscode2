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
AccountEntryDisplay accountEntryDisplay = (AccountEntryDisplay)request.getAttribute(AccountWebKeys.ACCOUNT_ENTRY_DISPLAY);
%>

<liferay-util:buffer
	var="removeUserIcon"
>
	<a class="float-right remove-user-link" href="javascript:;">
		<liferay-ui:icon
			icon="times-circle"
			markupView="lexicon"
			message="remove"
		/>
	</a>
</liferay-util:buffer>

<clay:sheet-section
	id='<%= liferayPortletResponse.getNamespace() + "personAccountUserContainer" %>'
>
	<clay:content-row
		containerElement="h3"
		cssClass="sheet-subtitle"
	>
		<clay:content-col
			containerElement="span"
			expand="<%= true %>"
		>
			<span class="heading-text"><liferay-ui:message key="user" /></span>
		</clay:content-col>

		<clay:content-col
			containerElement="span"
		>
			<span class="heading-end">
				<liferay-ui:icon
					id="selectUserButton"
					label="<%= true %>"
					linkCssClass="btn btn-secondary btn-sm"
					message="select"
					url="javascript:;"
				/>
			</span>
		</clay:content-col>
	</clay:content-row>

	<%
	Optional<User> personAccountEntryUserOptional = accountEntryDisplay.getPersonAccountEntryUserOptional();
	%>

	<aui:input
		name="personAccountEntryUserId"
		type="hidden"
		value="<%=
			String.valueOf(
				personAccountEntryUserOptional.map(
					User::getUserId
				).orElse(
					0L
				))
		%>"
	/>

	<liferay-ui:search-container
		compactEmptyResultsMessage="<%= true %>"
		emptyResultsMessage="assign-a-user-to-this-person-account"
		headerNames="name,email-address,job-title,null"
		id="personAccountEntryUserSearchContainer"
		total="<%= 1 %>"
	>
		<liferay-ui:search-container-results
			results="<%=
				personAccountEntryUserOptional.map(
					Collections::singletonList
				).orElse(
					Collections.emptyList()
				)
			%>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name="name"
				property="fullName"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name="email-address"
				property="emailAddress"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name="job-title"
				property="jobTitle"
			/>

			<liferay-ui:search-container-column-text>
				<%= removeUserIcon %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>
</clay:sheet-section>

<portlet:renderURL var="selectUserURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/account_entries_admin/select_account_users.jsp" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryDisplay.getAccountEntryId()) %>" />
	<portlet:param name="eventName" value='<%= liferayPortletResponse.getNamespace() + "selectPersonAccountEntryUser" %>' />
	<portlet:param name="navigation" value="all-users" />
	<portlet:param name="showCreateButton" value="<%= Boolean.TRUE.toString() %>" />
	<portlet:param name="showFilter" value="<%= Boolean.FALSE.toString() %>" />
	<portlet:param name="singleSelect" value="<%= Boolean.TRUE.toString() %>" />
</portlet:renderURL>

<liferay-frontend:component
	componentId="PersonAccountEntryEventHandler"
	context='<%=
		HashMapBuilder.<String, Object>put(
			"container", "#personAccountUserContainer"
		).put(
			"removeUserIconMarkup", removeUserIcon
		).put(
			"removeUserLinkSelector", ".remove-user-link"
		).put(
			"searchContainer", "personAccountEntryUserSearchContainer"
		).put(
			"selectUserButton", "#selectUserButton"
		).put(
			"selectUserEventName", "selectPersonAccountEntryUser"
		).put(
			"selectUserURL", selectUserURL.toString()
		).put(
			"userIdInput", "#personAccountEntryUserId"
		).build()
	%>'
	module="account_entries_admin/js/PersonAccountEntryEventHandler.es"
/>