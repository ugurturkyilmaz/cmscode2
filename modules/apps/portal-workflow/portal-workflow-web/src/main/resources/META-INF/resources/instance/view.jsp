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

<%@ include file="/instance/init.jsp" %>

<%
DateSearchEntry dateSearchEntry = new DateSearchEntry();

String displayStyle = workflowInstanceViewDisplayContext.getDisplayStyle();
%>

<aui:form action="<%= workflowInstanceViewDisplayContext.getViewPortletURL() %>" method="post" name="fm">
	<liferay-util:include page="/instance/toolbar.jsp" servletContext="<%= application %>" />
</aui:form>

<clay:container-fluid
	cssClass="workflow-instance-container"
>
	<%@ include file="/instance/workflow_instance.jspf" %>
</clay:container-fluid>