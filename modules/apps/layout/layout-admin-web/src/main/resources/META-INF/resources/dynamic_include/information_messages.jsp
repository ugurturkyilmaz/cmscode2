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

<%@ include file="/dynamic_include/init.jsp" %>

<%
LayoutInformationMessagesDisplayContext layoutInformationMessagesDisplayContext = new LayoutInformationMessagesDisplayContext(request);
%>

<li class="control-menu-nav-item">
	<liferay-ui:icon
		cssClass="control-menu-icon icon-monospaced"
		icon="information-live"
		label="<%= false %>"
		markupView="lexicon"
		message="additional-information"
	/>

	<react:component
		data="<%= layoutInformationMessagesDisplayContext.getData() %>"
		module="js/dynamic_include/InformationMessages"
	/>
</li>