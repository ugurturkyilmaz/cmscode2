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

<%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem" %>

<%
List<DropdownItem> actionDropdownItems = (List<DropdownItem>)request.getAttribute("liferay-frontend:empty-result-message:actionDropdownItems");
Map<String, Object> additionalProps = (Map<String, Object>)request.getAttribute("liferay-frontend:empty-result-message:additionalProps");
String animationTypeCssClass = GetterUtil.getString((String)request.getAttribute("liferay-frontend:empty-result-message:animationTypeCssClass"));
String buttonCssClass = GetterUtil.getString((String)request.getAttribute("liferay-frontend:empty-result-message:buttonCssClass"));
String buttonPropsTransformer = GetterUtil.getString((String)request.getAttribute("liferay-frontend:empty-result-message:buttonPropsTransformer"));
String description = (String)request.getAttribute("liferay-frontend:empty-result-message:description");
String elementType = (String)request.getAttribute("liferay-frontend:empty-result-message:elementType");
String propsTransformer = (String)request.getAttribute("liferay-frontend:empty-result-message:propsTransformer");
ServletContext propsTransformerServletContext = (ServletContext)request.getAttribute("liferay-frontend:empty-result-message:propsTransformerServletContext");
String title = (String)request.getAttribute("liferay-frontend:empty-result-message:title");
%>