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
OrganizationScreenNavigationDisplayContext organizationScreenNavigationDisplayContext = (OrganizationScreenNavigationDisplayContext)request.getAttribute(UsersAdminWebKeys.ORGANIZATION_SCREEN_NAVIGATION_DISPLAY_CONTEXT);

long organizationId = organizationScreenNavigationDisplayContext.getOrganizationId();

long orgLaborId = ParamUtil.getLong(request, "orgLaborId");
%>

<liferay-ui:icon-menu
	direction="right-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-ui:icon
		message="edit"
		url='<%=
			PortletURLBuilder.createRenderURL(
				liferayPortletResponse
			).setMVCPath(
				"/organization/edit_opening_hours.jsp"
			).setRedirect(
				currentURL
			).setParameter(
				"className", Organization.class.getName()
			).setParameter(
				"classPK", organizationId
			).setParameter(
				"primaryKey", orgLaborId
			).buildString()
		%>'
	/>

	<portlet:actionURL name="/users_admin/update_contact_information" var="removeOpeningHoursURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="className" value="<%= Organization.class.getName() %>" />
		<portlet:param name="classPK" value="<%= String.valueOf(organizationId) %>" />
		<portlet:param name="listType" value="<%= ListTypeConstants.ORGANIZATION_SERVICE %>" />
		<portlet:param name="primaryKey" value="<%= String.valueOf(orgLaborId) %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		message="remove"
		url="<%= removeOpeningHoursURL %>"
	/>
</liferay-ui:icon-menu>