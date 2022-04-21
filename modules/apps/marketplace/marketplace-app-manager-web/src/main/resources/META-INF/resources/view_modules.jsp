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
String app = ParamUtil.getString(request, "app");

ViewModulesManagementToolbarDisplayContext viewModulesManagementToolbarDisplayContext = new ViewModulesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse);

AppDisplay appDisplay = viewModulesManagementToolbarDisplayContext.getAppDisplay();

SearchContainer<Object> searchContainer = viewModulesManagementToolbarDisplayContext.getSearchContainer();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(
	PortletURLBuilder.createRenderURL(
		renderResponse
	).setMVCPath(
		"/view.jsp"
	).buildString());

renderResponse.setTitle(appDisplay.getDisplayTitle());

MarketplaceAppManagerUtil.addPortletBreadcrumbEntry(appDisplay, request, renderResponse);
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/view_modules.jsp" />
	<portlet:param name="app" value="<%= app %>" />
</portlet:renderURL>

<clay:management-toolbar
	filterDropdownItems="<%= viewModulesManagementToolbarDisplayContext.getFilterDropdownItems() %>"
	searchActionURL="<%= viewModulesManagementToolbarDisplayContext.getSearchActionURL() %>"
	searchContainerId="bundles"
	searchFormName="searchFm"
	selectable="<%= false %>"
	showSearch="<%= true %>"
	sortingOrder="<%= searchContainer.getOrderByType() %>"
	sortingURL="<%= viewModulesManagementToolbarDisplayContext.getSortingURL() %>"
/>

<clay:container-fluid>
	<liferay-ui:breadcrumb
		showCurrentGroup="<%= false %>"
		showGuestGroup="<%= false %>"
		showLayout="<%= false %>"
		showParentGroups="<%= false %>"
	/>

	<liferay-ui:search-container
		id="bundles"
		searchContainer="<%= searchContainer %>"
		var="bundleSearch"
	>
		<liferay-ui:search-container-row
			className="org.osgi.framework.Bundle"
			modelVar="bundle"
		>
			<%@ include file="/bundle_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="descriptive"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</clay:container-fluid>