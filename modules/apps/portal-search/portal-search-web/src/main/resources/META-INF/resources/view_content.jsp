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

<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.search.web.internal.result.display.context.SearchResultContentDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.result.display.context.builder.SearchResultContentDisplayContextBuilder" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
portletDisplay.setShowBackIcon(false);

SearchResultContentDisplayContextBuilder searchResultContentDisplayContextBuilder = new SearchResultContentDisplayContextBuilder();

searchResultContentDisplayContextBuilder.setAssetEntryId(ParamUtil.getLong(request, "assetEntryId"));
searchResultContentDisplayContextBuilder.setLocale(locale);
searchResultContentDisplayContextBuilder.setPermissionChecker(permissionChecker);
searchResultContentDisplayContextBuilder.setPortal(PortalUtil.getPortal());
searchResultContentDisplayContextBuilder.setRenderRequest(renderRequest);
searchResultContentDisplayContextBuilder.setRenderResponse(renderResponse);
searchResultContentDisplayContextBuilder.setType(ParamUtil.getString(request, "type"));

SearchResultContentDisplayContext searchResultContentDisplayContext = searchResultContentDisplayContextBuilder.build();
%>

<liferay-ui:success key='<%= portletDisplay.getId() + "requestProcessed" %>' message="your-request-completed-successfully" />

<c:if test="<%= searchResultContentDisplayContext.isVisible() %>">
	<div class="mb-2">
		<h4 class="component-title">
			<span class="asset-title d-inline">
				<%= HtmlUtil.escape(searchResultContentDisplayContext.getHeaderTitle()) %>
			</span>

			<c:if test="<%= searchResultContentDisplayContext.hasEditPermission() %>">
				<span class="d-inline-flex">
					<liferay-ui:icon
						cssClass="visible-interaction"
						icon="pencil"
						label="<%= false %>"
						markupView="lexicon"
						message='<%= LanguageUtil.format(request, "edit-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(searchResultContentDisplayContext.getIconEditTarget())}, false) %>'
						method="get"
						url="<%= searchResultContentDisplayContext.getIconURLString() %>"
					/>
				</span>
			</c:if>
		</h4>
	</div>

	<liferay-asset:asset-display
		assetEntry="<%= searchResultContentDisplayContext.getAssetEntry() %>"
		assetRenderer="<%= searchResultContentDisplayContext.getAssetRenderer() %>"
		assetRendererFactory="<%= searchResultContentDisplayContext.getAssetRendererFactory() %>"
		showExtraInfo="<%= searchResultContentDisplayContext.isShowExtraInfo() %>"
	/>
</c:if>