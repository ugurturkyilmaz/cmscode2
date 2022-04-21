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
SiteNavigationMenuItemSelectorViewDisplayContext siteNavigationMenuItemSelectorViewDisplayContext = (SiteNavigationMenuItemSelectorViewDisplayContext)request.getAttribute(SiteNavigationItemSelectorWebKeys.SITE_NAVIGATION_MENU_ITEM_SELECTOR_DISPLAY_CONTEXT);

String displayStyle = siteNavigationMenuItemSelectorViewDisplayContext.getDisplayStyle();
%>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= new SiteNavigationMenuItemSelectorViewManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, siteNavigationMenuItemSelectorViewDisplayContext) %>"
/>

<aui:form action="<%= siteNavigationMenuItemSelectorViewDisplayContext.getPortletURL() %>" cssClass="container-fluid container-fluid-max-xl" name="selectSiteNavigationMenuFm">
	<liferay-ui:search-container
		searchContainer="<%= siteNavigationMenuItemSelectorViewDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.site.navigation.model.SiteNavigationMenu"
			keyProperty="siteNavigationMenuId"
			modelVar="siteNavigationMenu"
		>

			<%
			String name = siteNavigationMenu.getName();

			if (siteNavigationMenu.getGroupId() != scopeGroupId) {
				Group group = GroupLocalServiceUtil.getGroup(siteNavigationMenu.getGroupId());

				name = StringUtil.appendParentheticalSuffix(name, group.getDescriptiveName(locale));
			}

			Map<String, Object> data = HashMapBuilder.<String, Object>put(
				"id", siteNavigationMenu.getSiteNavigationMenuId()
			).put(
				"name", name
			).build();
			%>

			<c:choose>
				<c:when test='<%= displayStyle.equals("descriptive") %>'>
					<liferay-ui:search-container-column-user
						showDetails="<%= false %>"
						userId="<%= siteNavigationMenu.getUserId() %>"
					/>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<h4>
							<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
								<%= HtmlUtil.escape(name) %>
							</aui:a>
						</h4>

						<%
						Date createDate = siteNavigationMenu.getCreateDate();

						String createDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
						%>

						<h5 class="text-default">
							<liferay-ui:message arguments="<%= new String[] {siteNavigationMenu.getUserName(), createDateDescription} %>" key="x-created-x-ago" />
						</h5>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:otherwise>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand"
						name="title"
					>
						<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
							<%= HtmlUtil.escape(name) %>
						</aui:a>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="author"
						property="userName"
					/>

					<liferay-ui:search-container-column-date
						name="create-date"
						property="createDate"
					/>
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= displayStyle %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>