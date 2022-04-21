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

<%@ taglib uri="http://liferay.com/tld/react" prefix="react" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.search.admin.web.internal.constants.SearchAdminWebKeys" %><%@
page import="com.liferay.portal.search.admin.web.internal.display.context.FieldMappingsDisplayContext" %>

<liferay-theme:defineObjects />

<%
FieldMappingsDisplayContext fieldMappingsDisplayContext = (FieldMappingsDisplayContext)request.getAttribute(SearchAdminWebKeys.FIELD_MAPPINGS_DISPLAY_CONTEXT);
%>

<div>
	<react:component
		module="js/FieldMappings.es"
		props="<%= fieldMappingsDisplayContext.getData() %>"
	/>
</div>