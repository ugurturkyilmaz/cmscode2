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
String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(PasswordPoliciesAdminPortletKeys.PASSWORD_POLICIES_ADMIN, "display-style", "list");
}
else {
	portalPreferences.setValue(PasswordPoliciesAdminPortletKeys.PASSWORD_POLICIES_ADMIN, "display-style", displayStyle);

	request.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
}

boolean passwordPolicyEnabled = LDAPSettingsUtil.isPasswordPolicyEnabled(company.getCompanyId());

String description = LanguageUtil.get(request, "javax.portlet.description.com_liferay_password_policies_admin_web_portlet_PasswordPoliciesAdminPortlet") + " " + LanguageUtil.get(request, "when-no-password-policy-is-assigned-to-a-user,-either-explicitly-or-through-an-organization,-the-default-password-policy-is-used");

portletDisplay.setDescription(description);

ViewPasswordPoliciesManagementToolbarDisplayContext viewPasswordPoliciesManagementToolbarDisplayContext = new ViewPasswordPoliciesManagementToolbarDisplayContext(request, renderRequest, renderResponse, displayStyle);

SearchContainer<PasswordPolicy> searchContainer = viewPasswordPoliciesManagementToolbarDisplayContext.getSearchContainer();

PortletURL portletURL = viewPasswordPoliciesManagementToolbarDisplayContext.getPortletURL();
%>

<clay:management-toolbar
	actionDropdownItems="<%= viewPasswordPoliciesManagementToolbarDisplayContext.getActionDropdownItems() %>"
	additionalProps='<%=
		HashMapBuilder.<String, Object>put(
			"basePortletURL", portletURL.toString()
		).build()
	%>'
	clearResultsURL="<%= viewPasswordPoliciesManagementToolbarDisplayContext.getClearResultsURL() %>"
	creationMenu="<%= viewPasswordPoliciesManagementToolbarDisplayContext.getCreationMenu() %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	propsTransformer="js/ManagementToolbarDefaultPropsTransformer"
	searchActionURL="<%= viewPasswordPoliciesManagementToolbarDisplayContext.getSearchActionURL() %>"
	searchContainerId="passwordPolicies"
	searchFormName="searchFm"
	selectable="<%= true %>"
	showCreationMenu="<%= viewPasswordPoliciesManagementToolbarDisplayContext.showCreationMenu() %>"
	showSearch="<%= true %>"
	sortingOrder="<%= searchContainer.getOrderByType() %>"
	sortingURL="<%= viewPasswordPoliciesManagementToolbarDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= viewPasswordPoliciesManagementToolbarDisplayContext.getViewTypeItems() %>"
/>

<aui:form action="<%= portletURL %>" cssClass="container-fluid container-fluid-max-xl" method="get" name="fm">
	<aui:input name="passwordPolicyIds" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

	<div id="breadcrumb">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= false %>"
			showPortletBreadcrumb="<%= true %>"
		/>
	</div>

	<c:if test="<%= passwordPolicyEnabled %>">
		<div class="alert alert-info">
			<liferay-ui:message key="you-are-using-ldaps-password-policy" />
		</div>
	</c:if>

	<c:if test="<%= !passwordPolicyEnabled && windowState.equals(WindowState.MAXIMIZED) %>">
		<liferay-ui:search-container
			id="passwordPolicies"
			searchContainer="<%= searchContainer %>"
			var="passwordPolicySearchContainer"
		>
			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.PasswordPolicy"
				escapedModel="<%= true %>"
				keyProperty="passwordPolicyId"
				modelVar="passwordPolicy"
			>

				<%
				String rowHREF = null;

				if (passwordPolicyDisplayContext.hasPermission(ActionKeys.UPDATE, passwordPolicy.getPasswordPolicyId())) {
					PortletURL redirectURL = passwordPolicySearchContainer.getIteratorURL();

					rowHREF = PortletURLBuilder.createRenderURL(
						renderResponse
					).setMVCPath(
						"/edit_password_policy.jsp"
					).setRedirect(
						redirectURL
					).setParameter(
						"passwordPolicyId", passwordPolicy.getPasswordPolicyId()
					).buildString();
				}
				%>

				<%@ include file="/search_columns.jspf" %>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="<%= displayStyle %>"
				markupView="lexicon"
				searchContainer="<%= passwordPolicySearchContainer %>"
			/>
		</liferay-ui:search-container>
	</c:if>
</aui:form>