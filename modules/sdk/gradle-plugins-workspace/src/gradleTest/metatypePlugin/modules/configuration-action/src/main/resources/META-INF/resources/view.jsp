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

<c:choose>
	<c:when test='<%= Validator.isNull(fontFamily) && Validator.isNull(fontColor) && (Validator.isNull(fontSize) || fontSize.equals("0")) %>'>
		<liferay-ui:message key="blade_configurationaction_portlet_BladeMessagePortlet.no-config" />
	</c:when>
	<c:otherwise>
		<p style="color: <%= fontColor %>; font-family: <%= fontFamily %>; font-size: <%= fontSize %>;">
			<liferay-ui:message key="blade_configurationaction_portlet_BladeMessagePortlet.caption" />
		</p>
	</c:otherwise>
</c:choose>