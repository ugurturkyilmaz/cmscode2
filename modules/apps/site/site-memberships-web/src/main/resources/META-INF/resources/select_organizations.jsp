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
SelectOrganizationsDisplayContext selectOrganizationsDisplayContext = new SelectOrganizationsDisplayContext(request, renderRequest, renderResponse);
%>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= new SelectOrganizationsManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, selectOrganizationsDisplayContext) %>"
/>

<aui:form cssClass="container-fluid container-fluid-max-xl" name="fm">
	<liferay-ui:search-container
		id="organizations"
		searchContainer="<%= selectOrganizationsDisplayContext.getOrganizationSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Organization"
			escapedModel="<%= true %>"
			keyProperty="organizationId"
			modelVar="organization"
		>

			<%
			String displayStyle = selectOrganizationsDisplayContext.getDisplayStyle();

			boolean selectOrganizations = true;
			%>

			<%@ include file="/organization_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= selectOrganizationsDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>