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
Contact selContact = (Contact)request.getAttribute("user.selContact");
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="sms"
/>

<aui:model-context bean="<%= selContact %>" model="<%= Contact.class %>" />

<c:choose>
	<c:when test="<%= selContact != null %>">
		<liferay-ui:error exception="<%= UserSmsException.MustBeEmailAddress.class %>" message="please-enter-a-sms-id-that-is-a-valid-email-address" />

		<aui:input label="sms" name="smsSn" />
	</c:when>
	<c:otherwise>
		<clay:alert
			message="this-section-will-be-editable-after-creating-the-user"
		/>
	</c:otherwise>
</c:choose>