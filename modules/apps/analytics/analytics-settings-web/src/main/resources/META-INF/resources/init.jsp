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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.analytics.settings.configuration.AnalyticsConfiguration" %><%@
page import="com.liferay.analytics.settings.web.internal.constants.AnalyticsSettingsWebKeys" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.ChannelDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.ChannelManagementToolbarDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.FieldDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.FieldManagementToolbarDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.GroupDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.GroupManagementToolbarDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.OrganizationDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.OrganizationManagementToolbarDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.UserGroupDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.display.context.UserGroupManagementToolbarDisplayContext" %><%@
page import="com.liferay.analytics.settings.web.internal.search.ChannelSearch" %><%@
page import="com.liferay.analytics.settings.web.internal.user.AnalyticsUsersManager" %><%@
page import="com.liferay.petra.portlet.url.builder.PortletURLBuilder" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Group" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.SetUtil" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.util.List" %><%@
page import="java.util.Set" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<liferay-util:html-top>
	<link href="<%= PortalUtil.getStaticResourceURL(request, application.getContextPath() + "/css/main.css") %>" rel="stylesheet" type="text/css" />
</liferay-util:html-top>