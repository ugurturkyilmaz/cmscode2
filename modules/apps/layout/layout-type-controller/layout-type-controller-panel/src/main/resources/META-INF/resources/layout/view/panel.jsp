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

<%@ include file="/layout/init.jsp" %>

<c:choose>
	<c:when test="<%= !themeDisplay.isStatePopUp() %>">
		<clay:container-fluid
			cssClass="lfr-panel-page"
			id="main-content"
		>
			<clay:row>

				<%
				String panelBodyCssClass = "panel-page-body";

				if (!layoutTypePortlet.hasStateMax()) {
					panelBodyCssClass += " panel-page-frontpage";
				}
				else {
					panelBodyCssClass += " panel-page-application";
				}
				%>

				<clay:col
					cssClass="panel-page-menu"
					md="3"
				>

					<%
					PortletCategory portletCategory = (PortletCategory)WebAppPool.get(company.getCompanyId(), WebKeys.PORTLET_CATEGORY);

					portletCategory = PortletCategoryUtil.getRelevantPortletCategory(permissionChecker, user.getCompanyId(), layout, portletCategory, layoutTypePortlet);

					List<PortletCategory> portletCategories = ListUtil.fromCollection(portletCategory.getCategories());

					portletCategories = ListUtil.sort(portletCategories, new PortletCategoryComparator(locale));

					for (PortletCategory curPortletCategory : portletCategories) {
					%>

						<c:if test="<%= !curPortletCategory.isHidden() %>">

							<%
							request.setAttribute(WebKeys.PORTLET_CATEGORY, curPortletCategory);
							%>

							<liferay-util:include page="/layout/view/view_category.jsp" servletContext="<%= application %>" />
						</c:if>

					<%
					}
					%>

				</clay:col>

				<clay:col
					cssClass="<%= panelBodyCssClass %>"
					md="9"
				>
					<%@ include file="/layout/view/panel_description.jspf" %>
				</clay:col>
			</clay:row>
		</clay:container-fluid>
	</c:when>
	<c:otherwise>
		<%@ include file="/layout/view/panel_description.jspf" %>
	</c:otherwise>
</c:choose>

<liferay-ui:layout-common />