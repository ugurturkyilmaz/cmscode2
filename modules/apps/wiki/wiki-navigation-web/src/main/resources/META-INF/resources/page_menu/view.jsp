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

<%@ include file="/page_menu/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();
%>

<c:choose>
	<c:when test="<%= wikiPage != null %>">
		<liferay-ui:panel-container
			extended="<%= true %>"
			id="pageMenu"
			persistState="<%= true %>"
		>

			<%
			List<MenuItem> menuItems = MenuItem.fromWikiPage(wikiPage, portletURL);

			for (MenuItem menuItem : menuItems) {
				String name = menuItem.getName();
			%>

				<c:choose>
					<c:when test="<%= Validator.isNotNull(name) %>">
						<liferay-ui:panel
							collapsible="<%= true %>"
							extended="<%= true %>"
							id='<%= "pageMenu_" + name %>'
							persistState="<%= true %>"
							title="<%= name %>"
						>
							<%= _buildPageMenuLinksHTML(menuItem.getChildren()) %>
						</liferay-ui:panel>
					</c:when>
					<c:otherwise>
						<%= _buildPageMenuLinksHTML(menuItem.getChildren()) %>
					</c:otherwise>
				</c:choose>

			<%
			}
			%>

		</liferay-ui:panel-container>

		<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) && WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.UPDATE) %>">
			<br />

			<liferay-portlet:renderURL portletName="<%= WikiPortletKeys.WIKI %>" var="editURL">
				<portlet:param name="struts_action" value="/wiki/edit_page" />
				<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
				<portlet:param name="title" value="<%= HtmlUtil.unescape(wikiPage.getTitle()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:icon
				icon="pencil"
				label="<%= true %>"
				markupView="lexicon"
				message="edit"
				url="<%= editURL %>"
			/>
		</c:if>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>

<%!
private String _buildPageMenuLinksHTML(List<MenuItem> menuItems) {
	StringBuilder sb = new StringBuilder();

	sb.append("<ul class=\"page-menu\">");

	for (MenuItem menuItem : menuItems) {
		String name = menuItem.getName();

		sb.append("<li>");
		sb.append("<a href=\"");
		sb.append(menuItem.getURL());
		sb.append("\"");

		if (menuItem.getExternalURL()) {
			sb.append(" target=\"_blank\"");
		}

		sb.append(">");
		sb.append(name);
		sb.append("</a>");
		sb.append("</li>");
	}

	sb.append("</ul>");

	return sb.toString();
}
%>