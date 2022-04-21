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
FragmentEntryLinkDisplayContext fragmentEntryLinkDisplayContext = new FragmentEntryLinkDisplayContext(renderRequest, renderResponse);

FragmentEntry fragmentEntry = fragmentEntryLinkDisplayContext.getFragmentEntry();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(fragmentEntryLinkDisplayContext.getRedirect());

renderResponse.setTitle(LanguageUtil.format(request, "usages-and-propagation-x", fragmentEntry.getName()));
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
									class="nav-link <%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "all") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											fragmentEntryLinkDisplayContext.getPortletURL()
										).setNavigation(
											"all"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getAllUsageCount() %>" key="all-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "pages") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											fragmentEntryLinkDisplayContext.getPortletURL()
										).setNavigation(
											"pages"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getPagesUsageCount() %>" key="pages-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "master-pages") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											fragmentEntryLinkDisplayContext.getPortletURL()
										).setNavigation(
											"master-pages"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getMasterPagesUsageCount() %>" key="master-pages-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "page-templates") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											fragmentEntryLinkDisplayContext.getPortletURL()
										).setNavigation(
											"page-templates"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getPageTemplatesUsageCount() %>" key="page-templates-x" />
								</a>
							</li>
							<li class="nav-item">
								<a
									class="nav-link <%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "display-page-templates") ? "active" : StringPool.BLANK %>"
									href="<%=
										PortletURLBuilder.create(
											fragmentEntryLinkDisplayContext.getPortletURL()
										).setNavigation(
											"display-page-templates"
										).buildString()
									%>"
								>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getDisplayPagesUsageCount() %>" key="display-page-templates-x" />
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
				<h2 class="sheet-title">
					<clay:content-row
						verticalAlign="center"
					>
						<clay:content-col>
							<c:choose>
								<c:when test='<%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "pages") %>'>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getPagesUsageCount() %>" key="pages-x" />
								</c:when>
								<c:when test='<%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "master-pages") %>'>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getMasterPagesUsageCount() %>" key="master-pages-x" />
								</c:when>
								<c:when test='<%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "page-templates") %>'>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getPageTemplatesUsageCount() %>" key="page-templates-x" />
								</c:when>
								<c:when test='<%= Objects.equals(fragmentEntryLinkDisplayContext.getNavigation(), "display-page-templates") %>'>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getDisplayPagesUsageCount() %>" key="display-page-templates-x" />
								</c:when>
								<c:otherwise>
									<liferay-ui:message arguments="<%= fragmentEntryLinkDisplayContext.getAllUsageCount() %>" key="all-x" />
								</c:otherwise>
							</c:choose>
						</clay:content-col>
					</clay:content-row>
				</h2>

				<clay:management-toolbar
					managementToolbarDisplayContext="<%= new FragmentEntryUsageManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, fragmentEntryLinkDisplayContext.getSearchContainer()) %>"
					propsTransformer="js/FragmentEntryUsagesManagementToolbarPropsTransformer"
				/>

				<portlet:actionURL name="/fragment/propagate_fragment_entry_changes" var="propagateFragmentEntryChangesURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:actionURL>

				<aui:form action="<%= propagateFragmentEntryChangesURL %>" name="fm">
					<liferay-ui:search-container
						searchContainer="<%= fragmentEntryLinkDisplayContext.getSearchContainer() %>"
					>
						<liferay-ui:search-container-row
							className="com.liferay.fragment.model.FragmentEntryLink"
							keyProperty="fragmentEntryLinkId"
							modelVar="fragmentEntryLink"
						>
							<liferay-ui:search-container-column-text
								name="name"
								value="<%= HtmlUtil.escape(fragmentEntryLinkDisplayContext.getFragmentEntryLinkName(fragmentEntryLink)) %>"
							/>

							<liferay-ui:search-container-column-text
								name="type"
								translate="<%= true %>"
								value="<%= fragmentEntryLinkDisplayContext.getFragmentEntryLinkTypeLabel(fragmentEntryLink) %>"
							/>

							<liferay-ui:search-container-column-text
								name="using"
							>
								<span class="label <%= fragmentEntryLink.isLatestVersion() ? "label-success" : "label-info" %>">
									<liferay-ui:message key='<%= fragmentEntryLink.isLatestVersion() ? "latest-version" : "a-previous-version" %>' />
								</span>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-date
								name="last-propagation"
								value="<%= fragmentEntryLink.getModifiedDate() %>"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							displayStyle="list"
							markupView="lexicon"
							searchResultCssClass="show-quick-actions-on-hover table table-autofit"
						/>
					</liferay-ui:search-container>
				</aui:form>
			</clay:sheet>
		</clay:col>
	</clay:row>
</clay:container-fluid>