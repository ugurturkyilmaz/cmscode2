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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDMDataProviderInstance ddmDataProviderInstance = (DDMDataProviderInstance)row.getObject();
%>

<div class="clamp-container">
	<h4 class="text-truncate">
		<aui:a href="<%= (String)request.getAttribute(WebKeys.SEARCH_ENTRY_HREF) %>">
			<%= HtmlUtil.escape(ddmDataProviderInstance.getName(locale)) %>
		</aui:a>
	</h4>

	<h5 class="text-default">
		<div class="text-truncate">
			<%= HtmlUtil.escape(ddmDataProviderInstance.getDescription(locale)) %>
		</div>
	</h5>

	<h5 class="text-default">
		<span class="data-provider-instance-id">
			<liferay-ui:message key="id" />: <%= ddmDataProviderInstance.getDataProviderInstanceId() %>
		</span>
		<span class="data-provider-instance-modified-date">

			<%
			DateSearchEntry dateSearchEntry = new DateSearchEntry();

			dateSearchEntry.setDate(ddmDataProviderInstance.getModifiedDate());
			%>

			<liferay-ui:message key="modified-date" />: <%= dateSearchEntry.getName(request) %>
		</span>
	</h5>
</div>