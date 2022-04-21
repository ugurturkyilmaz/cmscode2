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
taglib uri="http://liferay.com/tld/react" prefix="react" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.item.selector.ItemSelector" %><%@
page import="com.liferay.item.selector.criteria.UUIDItemSelectorReturnType" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.model.Group" %><%@
page import="com.liferay.portal.kernel.model.role.RoleConstants" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.roles.item.selector.RoleItemSelectorCriterion" %><%@
page import="com.liferay.segments.exception.NoSuchEntryException" %><%@
page import="com.liferay.segments.exception.RequiredSegmentsEntryException" %><%@
page import="com.liferay.segments.exception.SegmentsEntryCriteriaException" %><%@
page import="com.liferay.segments.exception.SegmentsEntryKeyException" %><%@
page import="com.liferay.segments.exception.SegmentsEntryNameException" %><%@
page import="com.liferay.segments.model.SegmentsEntry" %><%@
page import="com.liferay.segments.source.provider.SegmentsSourceDetailsProvider" %><%@
page import="com.liferay.segments.web.internal.constants.SegmentsWebKeys" %><%@
page import="com.liferay.segments.web.internal.display.context.EditSegmentsEntryDisplayContext" %><%@
page import="com.liferay.segments.web.internal.display.context.PreviewSegmentsEntryUsersDisplayContext" %><%@
page import="com.liferay.segments.web.internal.display.context.SegmentsDisplayContext" %><%@
page import="com.liferay.segments.web.internal.display.context.SegmentsExperienceSelectorDisplayContext" %><%@
page import="com.liferay.segments.web.internal.display.context.SelectSegmentsEntryDisplayContext" %><%@
page import="com.liferay.segments.web.internal.security.permission.resource.SegmentsEntryPermission" %><%@
page import="com.liferay.segments.web.internal.util.SegmentsSourceDetailsProviderUtil" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />