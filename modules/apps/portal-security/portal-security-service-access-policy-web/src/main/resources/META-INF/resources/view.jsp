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
String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

boolean orderByAsc = false;

String orderByType = ParamUtil.getString(request, "orderByType", "asc");

if (orderByType.equals("asc")) {
	orderByAsc = true;
}

OrderByComparator<SAPEntry> orderByComparator = new SAPEntryNameComparator(orderByAsc);

int sapEntriesCount = SAPEntryServiceUtil.getCompanySAPEntriesCount(company.getCompanyId());

PortletURL portletURL = renderResponse.createRenderURL();
%>

<clay:management-toolbar
	creationMenu='<%=
		new JSPCreationMenu(pageContext) {
			{
				addPrimaryDropdownItem(dropdownItem -> dropdownItem.setHref(renderResponse.createRenderURL(), "mvcPath", "/edit_entry.jsp", "redirect", PortalUtil.getCurrentURL(httpServletRequest)));
			}
		}
	%>'
	disabled="<%= sapEntriesCount == 0 %>"
	selectable="<%= false %>"
	showCreationMenu="<%= SAPPermission.contains(permissionChecker, SAPActionKeys.ACTION_ADD_SAP_ENTRY) %>"
	showSearch="<%= false %>"
	sortingOrder="<%= orderByType %>"
	sortingURL='<%=
		PortletURLBuilder.createRenderURL(
			renderResponse
		).setParameter(
			"displayStyle", displayStyle
		).setParameter(
			"orderByType", orderByAsc ? "desc" : "asc"
		).buildString()
	%>'
/>

<clay:container-fluid>
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-service-access-policies"
		iteratorURL="<%= portletURL %>"
		total="<%= sapEntriesCount %>"
	>
		<liferay-ui:search-container-results
			results="<%= SAPEntryServiceUtil.getCompanySAPEntries(company.getCompanyId(), searchContainer.getStart(), searchContainer.getEnd(), orderByComparator) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.security.service.access.policy.model.SAPEntry"
			escapedModel="<%= true %>"
			keyProperty="sapEntryId"
			modelVar="sapEntry"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcPath" value="/edit_entry.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="sapEntryId" value="<%= String.valueOf(sapEntry.getSapEntryId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				href="<%= rowURL %>"
				name="name"
			>
				<strong><%= sapEntry.getName() %></strong>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name="description"
				value="<%= sapEntry.getTitle(locale) %>"
			/>

			<liferay-ui:search-container-column-text
				name="enabled"
				value='<%= LanguageUtil.get(request, sapEntry.isEnabled() ? "yes" : "no") %>'
			/>

			<liferay-ui:search-container-column-text
				name="default"
				value='<%= LanguageUtil.get(request, sapEntry.isDefaultSAPEntry() ? "yes" : "no") %>'
			/>

			<liferay-ui:search-container-column-jsp
				path="/entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</clay:container-fluid>