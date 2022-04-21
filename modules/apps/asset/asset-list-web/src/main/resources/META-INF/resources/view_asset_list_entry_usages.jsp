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
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

AssetListEntryUsagesDisplayContext assetListEntryUsagesDisplayContext = new AssetListEntryUsagesDisplayContext(renderRequest, renderResponse);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(assetListDisplayContext.getAssetListEntryTitle());
%>

<clay:container-fluid
	cssClass="container-form-lg"
>
	<clay:row>
		<clay:col
			lg="3"
		>
			<nav class="menubar menubar-transparent menubar-vertical-expand-lg">
				<ul class="nav nav-nested">
					<li class="nav-item">
						<strong class="text-uppercase">
							<liferay-ui:message key="usages" />
						</strong>

						<ul class="nav nav-stacked">
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "all") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											assetListEntryUsagesDisplayContext.getPortletURL()
										).setNavigation(
											"all"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getAllUsageCount() %>" key="all-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "pages") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											assetListEntryUsagesDisplayContext.getPortletURL()
										).setNavigation(
											"pages"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getPagesUsageCount() %>" key="pages-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "page-templates") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											assetListEntryUsagesDisplayContext.getPortletURL()
										).setNavigation(
											"page-templates"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getPageTemplatesUsageCount() %>" key="page-templates-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "display-page-templates") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											assetListEntryUsagesDisplayContext.getPortletURL()
										).setNavigation(
											"display-page-templates"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getDisplayPagesUsageCount() %>" key="display-page-templates-x" />
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
		</clay:col>

		<clay:col
			lg="9"
		>
			<clay:sheet
				size="full"
			>
				<h3 class="sheet-title">
					<c:choose>
						<c:when test='<%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "pages") %>'>
							<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getPagesUsageCount() %>" key="pages-x" />
						</c:when>
						<c:when test='<%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "page-templates") %>'>
							<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getPageTemplatesUsageCount() %>" key="page-templates-x" />
						</c:when>
						<c:when test='<%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "display-page-templates") %>'>
							<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getDisplayPagesUsageCount() %>" key="display-page-templates-x" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getAllUsageCount() %>" key="all-x" />
						</c:otherwise>
					</c:choose>
				</h3>

				<clay:management-toolbar
					managementToolbarDisplayContext="<%= new AssetListEntryUsagesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, assetListEntryUsagesDisplayContext.getSearchContainer()) %>"
				/>

				<liferay-ui:search-container
					id="assetListEntryUsages"
					searchContainer="<%= assetListEntryUsagesDisplayContext.getSearchContainer() %>"
				>
					<liferay-ui:search-container-row
						className="com.liferay.asset.list.model.AssetListEntryUsage"
						keyProperty="assetListEntryUsageId"
						modelVar="assetListEntryUsage"
					>
						<liferay-ui:search-container-column-text
							name="name"
							value="<%= HtmlUtil.escape(assetListEntryUsagesDisplayContext.getAssetListEntryUsageName(assetListEntryUsage)) %>"
						/>

						<liferay-ui:search-container-column-text
							name="type"
							translate="<%= true %>"
							value="<%= assetListEntryUsagesDisplayContext.getAssetListEntryUsageTypeLabel(assetListEntryUsage) %>"
						/>

						<liferay-ui:search-container-column-date
							name="modified-date"
							value="<%= assetListEntryUsage.getModifiedDate() %>"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						displayStyle="list"
						markupView="lexicon"
						searchResultCssClass="show-quick-actions-on-hover table table-autofit"
					/>
				</liferay-ui:search-container>
			</clay:sheet>
		</clay:col>
	</clay:row>
</clay:container-fluid>