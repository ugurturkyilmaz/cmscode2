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
taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.search.web.internal.custom.filter.configuration.CustomFilterPortletInstanceConfiguration" %><%@
page import="com.liferay.portal.search.web.internal.custom.filter.display.context.CustomFilterDisplayContext" %>

<%@ page import="java.util.ArrayList" %>

<portlet:defineObjects />

<%
CustomFilterDisplayContext customFilterDisplayContext = (CustomFilterDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));

CustomFilterPortletInstanceConfiguration customFilterPortletInstanceConfiguration = customFilterDisplayContext.getCustomFilterPortletInstanceConfiguration();
%>

<c:if test="<%= !customFilterDisplayContext.isRenderNothing() %>">
	<aui:form action="<%= customFilterDisplayContext.getSearchURL() %>" method="get" name="fm">
		<liferay-ddm:template-renderer
			className="<%= CustomFilterDisplayContext.class.getName() %>"
			contextObjects='<%=
				HashMapBuilder.<String, Object>put(
					"customFilterDisplayContext", customFilterDisplayContext
				).put(
					"namespace", liferayPortletResponse.getNamespace()
				).build()
			%>'
			displayStyle="<%= customFilterPortletInstanceConfiguration.displayStyle() %>"
			displayStyleGroupId="<%= customFilterDisplayContext.getDisplayStyleGroupId() %>"
			entries="<%= new ArrayList<CustomFilterDisplayContext>() %>"
		>
			<liferay-ui:panel-container
				extended="<%= true %>"
				id='<%= liferayPortletResponse.getNamespace() + "filterCustomPanelContainer" %>'
				markupView="lexicon"
				persistState="<%= true %>"
			>
				<liferay-ui:panel
					collapsible="<%= true %>"
					cssClass="search-facet"
					id='<%= liferayPortletResponse.getNamespace() + "filterCustomPanel" %>'
					markupView="lexicon"
					persistState="<%= true %>"
					title="<%= HtmlUtil.escapeAttribute(customFilterDisplayContext.getHeading()) %>"
				>
					<aui:input cssClass="custom-filter-value-input" data-qa-id="customFilterValueInput" disabled="<%= customFilterDisplayContext.isImmutable() %>" id="<%= liferayPortletResponse.getNamespace() + StringUtil.randomId() %>" label="" name="<%= HtmlUtil.escapeAttribute(customFilterDisplayContext.getParameterName()) %>" useNamespace="<%= false %>" value="<%= HtmlUtil.escapeAttribute(customFilterDisplayContext.getFilterValue()) %>" />

					<clay:button
						aria-label='<%= LanguageUtil.get(request, "apply") %>'
						cssClass="custom-filter-apply-button"
						disabled="<%= customFilterDisplayContext.isImmutable() %>"
						displayType="secondary"
						label="apply"
						small="<%= true %>"
						type="submit"
					/>
				</liferay-ui:panel>
			</liferay-ui:panel-container>
		</liferay-ddm:template-renderer>
	</aui:form>

	<aui:script use="liferay-search-custom-filter">
		new Liferay.Search.CustomFilter(A.one('#<portlet:namespace />fm'));
	</aui:script>
</c:if>