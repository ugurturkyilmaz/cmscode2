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
CommerceInventoryWarehousesDisplayContext commerceInventoryWarehousesDisplayContext = (CommerceInventoryWarehousesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

List<CommerceChannel> commerceChannels = commerceInventoryWarehousesDisplayContext.getCommerceChannels();
long[] commerceChannelIds = commerceInventoryWarehousesDisplayContext.getCommerceChannelRelCommerceChannelIds();
%>

<c:choose>
	<c:when test="<%= commerceChannels.isEmpty() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-channels" />
		</div>
	</c:when>
	<c:otherwise>
		<liferay-ui:error-marker
			key="<%= WebKeys.ERROR_SECTION %>"
			value="channels"
		/>

		<aui:fieldset>

			<%
			for (CommerceChannel commerceChannel : commerceChannels) {
			%>

				<aui:input checked="<%= ArrayUtil.contains(commerceChannelIds, commerceChannel.getCommerceChannelId()) %>" cssClass="channel-id-input" label="<%= HtmlUtil.escape(commerceChannel.getName()) %>" name='<%= "commerceChannelId_" + commerceChannel.getCommerceChannelId() %>' type="checkbox" value="<%= commerceChannel.getCommerceChannelId() %>" />

			<%
			}
			%>

		</aui:fieldset>
	</c:otherwise>
</c:choose>

<liferay-frontend:component
	module="js/warehouseChannels"
/>