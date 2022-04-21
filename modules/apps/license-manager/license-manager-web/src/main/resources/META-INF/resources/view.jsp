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

<aui:nav-bar cssClass="navbar-expand navbar-underline navigation-bar navigation-bar-light" markupView="lexicon">
	<aui:nav collapsible="<%= false %>" cssClass="navbar-nav">
		<aui:nav-item label="licenses" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<clay:container-fluid
	cssClass="container-form-lg"
>
	<clay:sheet
		size="fluid"
	>
		<iframe allowTransparency="true" frameborder="0" id="<portlet:namespace />iframe" scrolling="no" src="<%= themeDisplay.getPathMain() %>/portal/license?p_l_id=<%= themeDisplay.getPlid() %>&p_p_state=pop_up" style="border: none; width: 100%;"></iframe>
	</clay:sheet>
</clay:container-fluid>

<aui:script use="aui-autosize-iframe">
	var iframe = A.one('#<portlet:namespace />iframe');

	if (iframe) {
		iframe.plug(A.Plugin.AutosizeIframe);
	}
</aui:script>