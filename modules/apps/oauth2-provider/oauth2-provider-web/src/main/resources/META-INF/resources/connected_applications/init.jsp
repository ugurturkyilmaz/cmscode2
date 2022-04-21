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

<%@ page import="com.liferay.oauth2.provider.model.OAuth2Authorization" %><%@
page import="com.liferay.oauth2.provider.service.OAuth2ApplicationLocalServiceUtil" %><%@
page import="com.liferay.oauth2.provider.service.OAuth2AuthorizationServiceUtil" %><%@
page import="com.liferay.oauth2.provider.web.internal.AssignableScopes" %><%@
page import="com.liferay.oauth2.provider.web.internal.constants.OAuth2ProviderWebKeys" %><%@
page import="com.liferay.oauth2.provider.web.internal.display.context.OAuth2ConnectedApplicationsManagementToolbarDisplayContext" %><%@
page import="com.liferay.oauth2.provider.web.internal.display.context.OAuth2ConnectedApplicationsPortletDisplayContext" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.util.Date" %>

<%
OAuth2ConnectedApplicationsPortletDisplayContext oAuth2ConnectedApplicationsPortletDisplayContext = (OAuth2ConnectedApplicationsPortletDisplayContext)request.getAttribute(OAuth2ProviderWebKeys.OAUTH2_CONNECTED_APPLICATIONS_PORTLET_DISPLAY_CONTEXT);
%>