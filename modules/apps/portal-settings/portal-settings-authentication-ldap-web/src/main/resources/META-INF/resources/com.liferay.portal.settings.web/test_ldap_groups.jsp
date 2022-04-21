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

<%@ include file="/com.liferay.portal.settings.web/init.jsp" %>

<%
long ldapServerId = ParamUtil.getLong(request, "ldapServerId");

String baseProviderURL = ParamUtil.getString(request, "baseProviderURL");
String baseDN = ParamUtil.getString(request, "baseDN");
String principal = ParamUtil.getString(request, "principal");

String credentials = request.getParameter("credentials");

if (credentials.equals(Portal.TEMP_OBFUSCATION_VALUE)) {
	LDAPServerConfiguration ldapServerConfiguration = ldapServerConfigurationProvider.getConfiguration(themeDisplay.getCompanyId(), ldapServerId);

	credentials = ldapServerConfiguration.securityCredential();
}

SafePortalLDAP safePortalLDAP = SafePortalLDAPUtil.getSafePortalLDAP();

SafeLdapContext safeLdapContext = safePortalLDAP.getSafeLdapContext(themeDisplay.getCompanyId(), baseProviderURL, principal, credentials);

if (safeLdapContext == null) {
%>

	<liferay-ui:message key="liferay-has-failed-to-connect-to-the-ldap-server" />

<%
	return;
}

if (Validator.isNull(ParamUtil.getString(request, "groupMappingGroupName")) || Validator.isNull(ParamUtil.getString(request, "groupMappingUser"))) {
%>

	<liferay-ui:message key="please-map-each-of-the-group-properties-group-name-and-user-to-an-ldap-attribute" />

<%
	return;
}

LDAPFilterValidator ldapFilterValidator = LDAPFilterValidatorUtil.getLDAPFilterValidator();

String groupFilter = ParamUtil.getString(request, "importGroupSearchFilter");

if (!ldapFilterValidator.isValid(groupFilter)) {
%>

	<liferay-ui:message key="please-enter-a-valid-ldap-search-filter" />

<%
	return;
}

SafeLdapFilter groupSafeLdapFilter = SafeLdapFilterFactory.fromUnsafeFilter(groupFilter, ldapFilterValidator);

String groupMappingsParam = "groupName=" + ParamUtil.getString(request, "groupMappingGroupName") + "\ndescription=" + ParamUtil.getString(request, "groupMappingDescription") + "\nuser=" + ParamUtil.getString(request, "groupMappingUser");

Properties groupMappings = PropertiesUtil.load(groupMappingsParam);

String[] attributeIds = StringUtil.split(StringUtil.merge(groupMappings.values()));

List<SearchResult> searchResults = new ArrayList<SearchResult>();

try {
	safePortalLDAP.getGroups(themeDisplay.getCompanyId(), safeLdapContext, new byte[0], 20, SafeLdapNameFactory.fromUnsafe(baseDN), groupSafeLdapFilter, attributeIds, searchResults);
}
catch (InvalidNameException | NameNotFoundException exception) {
%>

	<liferay-ui:message key="please-enter-a-valid-ldap-base-dn" />

<%
	return;
}
%>

<liferay-ui:message key="test-ldap-groups" />

<br /><br />

<liferay-ui:message key="a-subset-of-groups-has-been-displayed-for-you-to-review" />

<br /><br />

<table class="lfr-table" width="100%">

	<%
	boolean showMissingAttributeMessage = false;

	int counter = 0;

	for (SearchResult searchResult : searchResults) {
		Attributes attributes = searchResult.getAttributes();

		String name = LDAPUtil.getAttributeString(attributes, groupMappings.getProperty("groupName"));
		String description = LDAPUtil.getAttributeString(attributes, groupMappings.getProperty("description"));
		Attribute attribute = attributes.get(groupMappings.getProperty("user"));

		if (Validator.isNull(name)) {
			showMissingAttributeMessage = true;
		}

		if (attribute != null) {
			SafeLdapFilter safeLdapFilter = groupSafeLdapFilter.and(SafeLdapFilterConstraints.eq(groupMappings.getProperty("groupName"), name));

			attribute = safePortalLDAP.getMultivaluedAttribute(themeDisplay.getCompanyId(), safeLdapContext, SafeLdapNameFactory.fromUnsafe(baseDN), safeLdapFilter, attribute);
		}
	%>

		<c:if test="<%= counter == 0 %>">
			<col width="5%" />
			<col width="25%" />
			<col width="60%" />
			<col width="15%" />

			<tr>
				<th>
					#
				</th>
				<th>
					<liferay-ui:message key="name" />
				</th>
				<th>
					<liferay-ui:message key="description" />
				</th>
				<th>
					<liferay-ui:message key="members" />
				</th>
			</tr>
		</c:if>

		<%
		counter++;
		%>

		<tr>
			<td>
				<%= counter %>
			</td>
			<td>
				<%= HtmlUtil.escape(name) %>
			</td>
			<td>
				<%= HtmlUtil.escape(description) %>
			</td>
			<td>
				<%= (attribute == null) ? "0" : String.valueOf(attribute.size()) %>
			</td>
		</tr>

	<%
	}
	%>

	<c:if test="<%= counter == 0 %>">
		<tr>
			<td colspan="4">
				<liferay-ui:message key="no-groups-were-found" />
			</td>
		</tr>
	</c:if>
</table>

<c:if test="<%= showMissingAttributeMessage %>">
	<div class="alert alert-info">
		<liferay-ui:message key="the-above-results-include-groups-which-are-missing-the-required-attributes-(group-name-and-user).-these-groups-will-not-be-imported-until-these-attributes-are-filled-in" />
	</div>
</c:if>

<%
if (safeLdapContext != null) {
	safeLdapContext.close();
}
%>