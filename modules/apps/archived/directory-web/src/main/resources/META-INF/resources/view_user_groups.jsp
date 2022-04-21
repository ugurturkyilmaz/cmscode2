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
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

UserGroupSearch userGroupSearch = new UserGroupSearch(renderRequest, portletURL);

UserGroupDisplayTerms searchTerms = (UserGroupDisplayTerms)userGroupSearch.getSearchTerms();

LinkedHashMap<String, Object> userGroupParams = new LinkedHashMap<String, Object>();

if (portletName.equals(PortletKeys.MY_SITES_DIRECTORY)) {
	List<Group> groups = GroupLocalServiceUtil.search(
		user.getCompanyId(),
		LinkedHashMapBuilder.<String, Object>put(
			"inherit", Boolean.FALSE
		).put(
			"site", Boolean.TRUE
		).put(
			"usersGroups", user.getUserId()
		).build(),
		QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	userGroupParams.put(UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_GROUPS, SitesUtil.filterGroups(groups, PropsValues.MY_SITES_DIRECTORY_SITE_EXCLUDES));
}
else if (portletName.equals(PortletKeys.SITE_MEMBERS_DIRECTORY)) {
	userGroupParams.put(UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_GROUPS, Long.valueOf(themeDisplay.getScopeGroupId()));
}

String keywords = searchTerms.getKeywords();

if (Validator.isNotNull(keywords)) {
	userGroupParams.put("expandoAttributes", keywords);
}

long companyId = company.getCompanyId();

userGroupSearch.setResultsAndTotal(() -> UserGroupServiceUtil.search(companyId, keywords, userGroupParams, userGroupSearch.getStart(), userGroupSearch.getEnd(), userGroupSearch.getOrderByComparator()), UserGroupServiceUtil.searchCount(companyId, keywords, userGroupParams));
%>

<aui:input disabled="<%= true %>" name="userGroupsRedirect" type="hidden" value="<%= portletURL.toString() %>" />

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= portletURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= userGroupSearch.getOrderByCol() %>"
			orderByType="<%= userGroupSearch.getOrderByType() %>"
			orderColumns='<%= new String[] {"name"} %>'
			portletURL="<%= portletURL %>"
		/>

		<c:if test='<%= ParamUtil.getBoolean(request, "showSearch", true) %>'>
			<li>
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</li>
		</c:if>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:search-container
		searchContainer="<%= userGroupSearch %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.UserGroup"
			escapedModel="<%= true %>"
			keyProperty="userGroupId"
			modelVar="userGroup"
		>
			<liferay-ui:search-container-column-text
				name="name"
				orderable="<%= true %>"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				name="description"
				orderable="<%= true %>"
				property="description"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/user_group_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>