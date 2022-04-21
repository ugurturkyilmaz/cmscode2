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
String className = ParamUtil.getString(request, "className", Layout.class.getName());
long classPK = ParamUtil.getLong(request, "classPK", layout.getPlid());

AssetEntry assetEntry = (AssetEntry)request.getAttribute(WebKeys.LAYOUT_ASSET_ENTRY);

if ((assetEntry != null) && layout.isTypeAssetDisplay()) {
	className = assetEntry.getClassName();
	classPK = assetEntry.getClassPK();
}
%>

<c:if test="<%= LayoutPermissionUtil.contains(permissionChecker, layout, ActionKeys.VIEW) %>">
	<liferay-comment:discussion
		className="<%= className %>"
		classPK="<%= classPK %>"
		formName="fm"
		redirect="<%= currentURL %>"
		userId="<%= user.getUserId() %>"
	/>
</c:if>