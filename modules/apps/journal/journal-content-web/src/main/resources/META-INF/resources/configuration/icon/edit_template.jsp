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
DDMTemplate ddmTemplate = journalContentDisplayContext.getDDMTemplate();
%>

<liferay-ui:icon
	data='<%=
		HashMapBuilder.<String, Object>put(
			"destroyOnHide", true
		).put(
			"id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset"
		).put(
			"title", HtmlUtil.escape(ddmTemplate.getName(locale))
		).build()
	%>'
	id="editTemplateIcon"
	message="edit-template"
	url="<%= journalContentDisplayContext.getURLEditTemplate() %>"
	useDialog="<%= true %>"
/>