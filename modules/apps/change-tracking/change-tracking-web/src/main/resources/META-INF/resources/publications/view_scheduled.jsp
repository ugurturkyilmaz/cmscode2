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

<%@ include file="/publications/init.jsp" %>

<%
ViewScheduledDisplayContext viewScheduledDisplayContext = (ViewScheduledDisplayContext)request.getAttribute(CTWebKeys.VIEW_SCHEDULED_DISPLAY_CONTEXT);

SearchContainer<CTCollection> searchContainer = viewScheduledDisplayContext.getSearchContainer();

ViewScheduledManagementToolbarDisplayContext viewScheduledManagementToolbarDisplayContext = new ViewScheduledManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, searchContainer, viewScheduledDisplayContext);

Format format = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<clay:navigation-bar
	navigationItems="<%= viewScheduledDisplayContext.getViewNavigationItems() %>"
/>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= viewScheduledManagementToolbarDisplayContext %>"
/>

<clay:container-fluid>
	<liferay-ui:search-container
		cssClass="publications-table"
		searchContainer="<%= searchContainer %>"
		var="publicationsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="com.liferay.change.tracking.model.CTCollection"
			escapedModel="<%= true %>"
			keyProperty="ctCollectionId"
			modelVar="ctCollection"
		>
			<c:choose>
				<c:when test='<%= Objects.equals(publicationsDisplayContext.getDisplayStyle(), "descriptive") %>'>
					<liferay-ui:search-container-column-text>
						<span class="lfr-portal-tooltip" title="<%= HtmlUtil.escape(ctCollection.getUserName()) %>">
							<liferay-ui:user-portrait
								userId="<%= ctCollection.getUserId() %>"
							/>
						</span>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="autofit-col-expand"
						href="<%= publicationsDisplayContext.getReviewChangesURL(ctCollection.getCtCollectionId()) %>"
					>
						<div class="publication-name <%= (publicationsDisplayContext.getCtCollectionId() == ctCollection.getCtCollectionId()) ? "font-italic" : StringPool.BLANK %>">
							<%= ctCollection.getName() %>
						</div>

						<div class="publication-description <%= (publicationsDisplayContext.getCtCollectionId() == ctCollection.getCtCollectionId()) ? "font-italic" : StringPool.BLANK %>">
							<%= ctCollection.getDescription() %>
						</div>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:otherwise>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand"
						href="<%= publicationsDisplayContext.getReviewChangesURL(ctCollection.getCtCollectionId()) %>"
						name="publication"
					>
						<div class="publication-name <%= (publicationsDisplayContext.getCtCollectionId() == ctCollection.getCtCollectionId()) ? "font-italic" : StringPool.BLANK %>">
							<%= ctCollection.getName() %>
						</div>

						<div class="publication-description <%= (publicationsDisplayContext.getCtCollectionId() == ctCollection.getCtCollectionId()) ? "font-italic" : StringPool.BLANK %>">
							<%= ctCollection.getDescription() %>
						</div>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand-smaller"
						name="publishing"
					>
						<%= format.format(viewScheduledDisplayContext.getPublishingDate(ctCollection.getCtCollectionId())) %>
					</liferay-ui:search-container-column-text>

					<%
					Date modifiedDate = ctCollection.getModifiedDate();
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand-smaller"
						name="last-modified"
					>
						<liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - modifiedDate.getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />
					</liferay-ui:search-container-column-text>

					<%
					Date createDate = ctCollection.getCreateDate();
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand-smaller"
						name="created"
					>
						<liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand-smallest text-center"
						name="owner"
					>
						<span class="lfr-portal-tooltip" title="<%= HtmlUtil.escape(ctCollection.getUserName()) %>">
							<liferay-ui:user-portrait
								userId="<%= ctCollection.getUserId() %>"
							/>
						</span>
					</liferay-ui:search-container-column-text>
				</c:otherwise>
			</c:choose>

			<liferay-ui:search-container-column-jsp
				path="/publications/scheduled_publication_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= publicationsDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
			searchContainer="<%= searchContainer %>"
		/>
	</liferay-ui:search-container>
</clay:container-fluid>