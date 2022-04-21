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
PortletPreferences companyPortletPreferences = PrefsPropsUtil.getPreferences(company.getCompanyId());

CompanyPortletRatingsDefinitionDisplayContext companyPortletRatingsDefinitionDisplayContext = new CompanyPortletRatingsDefinitionDisplayContext(companyPortletPreferences, request);
%>

<p><liferay-ui:message key="select-the-default-ratings-type-for-the-following-applications" /></p>

<aui:fieldset id='<%= liferayPortletResponse.getNamespace() + "ratingsSettingsContainer" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<%
	Map<String, Map<String, RatingsType>> companyRatingsTypeMaps = companyPortletRatingsDefinitionDisplayContext.getCompanyRatingsTypeMaps();

	for (Map.Entry<String, Map<String, RatingsType>> entry : companyRatingsTypeMaps.entrySet()) {
		Portlet portlet = PortletLocalServiceUtil.getPortletById(entry.getKey());
	%>

		<p>
			<strong><%= PortalUtil.getPortletTitle(portlet, application, locale) %></strong>
		</p>

		<%
		Map<String, RatingsType> ratingsTypeMap = companyRatingsTypeMaps.get(entry.getKey());

		Set<String> classNames = ratingsTypeMap.keySet();

		for (String className : classNames) {
			String propertyKey = RatingsDataTransformerUtil.getPropertyKey(className);

			RatingsType ratingsType = ratingsTypeMap.get(className);
		%>

			<aui:select label="<%= (classNames.size() > 1) ? ResourceActionsUtil.getModelResource(locale, className) : StringPool.BLANK %>" name='<%= "settings--" + propertyKey + "--" %>'>

				<%
				for (RatingsType curRatingsType : RatingsType.values()) {
				%>

					<aui:option label="<%= LanguageUtil.get(request, curRatingsType.getValue()) %>" selected="<%= Objects.equals(ratingsType, curRatingsType) %>" value="<%= curRatingsType.getValue() %>" />

				<%
				}
				%>

			</aui:select>

	<%
		}
	}
	%>

</aui:fieldset>

<aui:script use="aui-base">
	var ratingsSettingsContainer = A.one(
		'#<portlet:namespace />ratingsSettingsContainer'
	);

	var ratingsTypeChanged = false;

	ratingsSettingsContainer.delegate(
		'change',
		(event) => {
			ratingsTypeChanged = true;
		},
		'select'
	);

	var form = A.one('#<portlet:namespace />fm');

	form.on('submit', (event) => {
		if (
			ratingsTypeChanged &&
			!confirm(
				'<%= UnicodeLanguageUtil.get(request, "existing-ratings-data-values-will-be-adapted-to-match-the-new-ratings-type-even-though-it-may-not-be-accurate") %>'
			)
		) {
			event.preventDefault();

			event.stopImmediatePropagation();
		}
	});
</aui:script>