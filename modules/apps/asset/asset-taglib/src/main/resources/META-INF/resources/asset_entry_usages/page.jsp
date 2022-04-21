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

<%@ include file="/asset_entry_usages/init.jsp" %>

<%
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-asset:asset-entry-usages:classPK"));
String className = GetterUtil.getString(request.getAttribute("liferay-asset:asset-entry-usages:className"));

AssetEntryUsagesDisplayContext assetEntryUsagesDisplayContext = new AssetEntryUsagesDisplayContext(renderRequest, renderResponse, className, classPK);
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
									class="nav-link <%= Objects.equals(assetEntryUsagesDisplayContext.getNavigation(), "all") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											assetEntryUsagesDisplayContext.getPortletURL()
										).setNavigation(
											"all"
										).setParameter(
											"resetCur", true
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= assetEntryUsagesDisplayContext.getAllUsageCount() %>" key="all-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(assetEntryUsagesDisplayContext.getNavigation(), "pages") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											assetEntryUsagesDisplayContext.getPortletURL()
										).setNavigation(
											"pages"
										).setParameter(
											"resetCur", true
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= assetEntryUsagesDisplayContext.getPagesUsageCount() %>" key="pages-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(assetEntryUsagesDisplayContext.getNavigation(), "page-templates") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											assetEntryUsagesDisplayContext.getPortletURL()
										).setNavigation(
											"page-templates"
										).setParameter(
											"resetCur", true
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= assetEntryUsagesDisplayContext.getPageTemplatesUsageCount() %>" key="page-templates-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(assetEntryUsagesDisplayContext.getNavigation(), "display-page-templates") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											assetEntryUsagesDisplayContext.getPortletURL()
										).setNavigation(
											"display-page-templates"
										).setParameter(
											"resetCur", true
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= assetEntryUsagesDisplayContext.getDisplayPagesUsageCount() %>" key="display-page-templates-x" />
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
						<c:when test='<%= Objects.equals(assetEntryUsagesDisplayContext.getNavigation(), "pages") %>'>
							<liferay-ui:message arguments="<%= assetEntryUsagesDisplayContext.getPagesUsageCount() %>" key="pages-x" />
						</c:when>
						<c:when test='<%= Objects.equals(assetEntryUsagesDisplayContext.getNavigation(), "page-templates") %>'>
							<liferay-ui:message arguments="<%= assetEntryUsagesDisplayContext.getPageTemplatesUsageCount() %>" key="page-templates-x" />
						</c:when>
						<c:when test='<%= Objects.equals(assetEntryUsagesDisplayContext.getNavigation(), "display-page-templates") %>'>
							<liferay-ui:message arguments="<%= assetEntryUsagesDisplayContext.getDisplayPagesUsageCount() %>" key="display-page-templates-x" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message arguments="<%= assetEntryUsagesDisplayContext.getAllUsageCount() %>" key="all-x" />
						</c:otherwise>
					</c:choose>
				</h3>

				<clay:management-toolbar
					managementToolbarDisplayContext="<%= new AssetEntryUsagesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, assetEntryUsagesDisplayContext.getSearchContainer()) %>"
				/>

				<liferay-ui:search-container
					id="assetEntryUsages"
					searchContainer="<%= assetEntryUsagesDisplayContext.getSearchContainer() %>"
				>
					<liferay-ui:search-container-row
						className="com.liferay.asset.model.AssetEntryUsage"
						keyProperty="assetEntryUsageId"
						modelVar="assetEntryUsage"
					>
						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand table-cell-minw-200 table-title"
							name="name"
							value="<%= assetEntryUsagesDisplayContext.getAssetEntryUsageName(assetEntryUsage) %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
							name="type"
							translate="<%= true %>"
							value="<%= assetEntryUsagesDisplayContext.getAssetEntryUsageTypeLabel(assetEntryUsage) %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand"
							name="where"
							translate="<%= true %>"
							value="<%= assetEntryUsagesDisplayContext.getAssetEntryUsageWhereLabel(assetEntryUsage) %>"
						/>

						<liferay-ui:search-container-column-date
							cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
							name="modified-date"
							value="<%= assetEntryUsage.getModifiedDate() %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smallest table-column-text-center"
						>

							<%
							List<DropdownItem> dropdownItems = assetEntryUsagesDisplayContext.getAssetEntryUsageActionDropdownItems(assetEntryUsage);
							%>

							<c:if test="<%= ListUtil.isNotEmpty(dropdownItems) %>">
								<clay:dropdown-actions
									dropdownItems="<%= dropdownItems %>"
								/>
							</c:if>
						</liferay-ui:search-container-column-text>
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