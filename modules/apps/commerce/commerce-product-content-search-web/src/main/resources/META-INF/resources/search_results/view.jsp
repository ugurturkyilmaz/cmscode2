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
CPSearchResultsDisplayContext cpSearchResultsDisplayContext = (CPSearchResultsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CPCatalogEntry> cpCatalogEntrySearchContainer = cpSearchResultsDisplayContext.getSearchContainer();
%>

<c:choose>
	<c:when test="<%= !cpSearchResultsDisplayContext.hasCommerceChannel() %>">
		<div class="alert alert-info mx-auto">
			<liferay-ui:message key="this-site-does-not-have-a-channel" />
		</div>
	</c:when>
	<c:when test="<%= cpSearchResultsDisplayContext.isSelectionStyleADT() %>">
		<liferay-ddm:template-renderer
			className="<%= CPSearchResultsPortlet.class.getName() %>"
			contextObjects='<%=
				HashMapBuilder.<String, Object>put(
					"cpContentHelper", request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER)
				).put(
					"cpSearchResultsDisplayContext", cpSearchResultsDisplayContext
				).build()
			%>'
			displayStyle="<%= cpSearchResultsDisplayContext.getDisplayStyle() %>"
			displayStyleGroupId="<%= cpSearchResultsDisplayContext.getDisplayStyleGroupId() %>"
			entries="<%= cpCatalogEntrySearchContainer.getResults() %>"
		/>

		<c:if test="<%= cpSearchResultsDisplayContext.isPaginate() %>">
			<aui:form useNamespace="<%= false %>">
				<liferay-ui:search-paginator
					markupView="lexicon"
					searchContainer="<%= cpCatalogEntrySearchContainer %>"
				/>
			</aui:form>
		</c:if>
	</c:when>
	<c:when test="<%= cpSearchResultsDisplayContext.isSelectionStyleCustomRenderer() %>">
		<liferay-commerce-product:product-list-renderer
			CPDataSourceResult="<%= cpSearchResultsDisplayContext.getCPDataSourceResult() %>"
			entryKeys="<%= cpSearchResultsDisplayContext.getCPContentListEntryRendererKeys() %>"
			key="<%= cpSearchResultsDisplayContext.getCPContentListRendererKey() %>"
		/>

		<c:if test="<%= cpSearchResultsDisplayContext.isPaginate() %>">
			<aui:form useNamespace="<%= false %>">
				<liferay-ui:search-paginator
					markupView="lexicon"
					searchContainer="<%= cpCatalogEntrySearchContainer %>"
				/>
			</aui:form>
		</c:if>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>