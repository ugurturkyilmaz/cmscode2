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
CommerceInventoryDisplayContext commerceInventoryDisplayContext = (CommerceInventoryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:data-set-display
	creationMenu="<%= commerceInventoryDisplayContext.getInventoryItemCreationMenu() %>"
	dataProviderKey="<%= CommerceInventoryDataSetConstants.COMMERCE_DATA_SET_KEY_INVENTORY_ITEMS %>"
	id="<%= CommerceInventoryDataSetConstants.COMMERCE_DATA_SET_KEY_INVENTORY_ITEMS %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= liferayPortletResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= commerceInventoryDisplayContext.getPortletURL() %>"
	style="fluid"
/>