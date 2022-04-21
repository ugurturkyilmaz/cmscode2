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
LayoutItemSelectorViewDisplayContext layoutItemSelectorViewDisplayContext = (LayoutItemSelectorViewDisplayContext)request.getAttribute(LayoutsItemSelectorWebKeys.LAYOUT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);
%>

<c:if test="<%= layoutItemSelectorViewDisplayContext.isShowBreadcrumb() %>">
	<div class="container-fluid container-fluid-max-xl mt-2 pl-3">
		<liferay-site-navigation:breadcrumb
			breadcrumbEntries="<%= layoutItemSelectorViewDisplayContext.getPortletBreadcrumbEntries() %>"
		/>
	</div>
</c:if>

<liferay-layout:select-layout
	checkDisplayPage="<%= layoutItemSelectorViewDisplayContext.isCheckDisplayPage() %>"
	enableCurrentPage="<%= layoutItemSelectorViewDisplayContext.isEnableCurrentPage() %>"
	followURLOnTitleClick="<%= layoutItemSelectorViewDisplayContext.isFollowURLOnTitleClick() %>"
	itemSelectorReturnType="<%= layoutItemSelectorViewDisplayContext.getItemSelectedReturnType() %>"
	itemSelectorSaveEvent="<%= layoutItemSelectorViewDisplayContext.getItemSelectedEventName() %>"
	multiSelection="<%= layoutItemSelectorViewDisplayContext.isMultiSelection() %>"
	namespace="<%= liferayPortletResponse.getNamespace() %>"
	privateLayout="<%= layoutItemSelectorViewDisplayContext.isPrivateLayout() %>"
	showDraftLayouts="<%= layoutItemSelectorViewDisplayContext.isShowDraftPages() %>"
	showHiddenLayouts="<%= layoutItemSelectorViewDisplayContext.isShowHiddenPages() %>"
/>