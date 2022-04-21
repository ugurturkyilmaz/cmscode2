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
CPDefinitionsDisplayContext cpDefinitionsDisplayContext = (CPDefinitionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionsDisplayContext.getCPDefinition();
CProduct cProduct = cpDefinitionsDisplayContext.getCProduct();
PortletURL portletURL = cpDefinitionsDisplayContext.getEditProductDefinitionURL();

String headerTitle = LanguageUtil.get(request, "add-product");

if (cpDefinition != null) {
	headerTitle = cpDefinition.getName(languageId);
}

request.setAttribute("view.jsp-cpDefinition", cpDefinition);
request.setAttribute("view.jsp-cpType", cpDefinitionsDisplayContext.getCPType());
request.setAttribute("view.jsp-portletURL", portletURL);
request.setAttribute("view.jsp-showSearch", false);

portletDisplay.setShowBackIcon(true);

if (Validator.isNull(redirect)) {
	portletDisplay.setURLBack(String.valueOf(renderResponse.createRenderURL()));
}
else {
	portletDisplay.setURLBack(redirect);
}
%>

<liferay-portlet:renderURL var="editCProductExternalReferenceCodeURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="/cp_definitions/edit_c_product_external_reference_code" />
	<portlet:param name="cpDefinitionId" value="<%= String.valueOf(cpDefinitionsDisplayContext.getCPDefinitionId()) %>" />
</liferay-portlet:renderURL>

<commerce-ui:header
	actions="<%= cpDefinitionsDisplayContext.getHeaderActionModels() %>"
	bean="<%= cpDefinition %>"
	beanIdLabel="id"
	dropdownItems="<%= cpDefinitionsDisplayContext.getDropdownItems() %>"
	externalReferenceCode="<%= (cProduct == null) ? StringPool.BLANK : cProduct.getExternalReferenceCode() %>"
	externalReferenceCodeEditUrl="<%= (cProduct == null) ? StringPool.BLANK : editCProductExternalReferenceCodeURL %>"
	model="<%= CPDefinition.class %>"
	thumbnailUrl="<%= cpDefinitionsDisplayContext.getCPDefinitionThumbnailURL() %>"
	title="<%= headerTitle %>"
	version="<%= (cpDefinition == null) ? StringPool.BLANK : String.valueOf(cpDefinition.getVersion()) %>"
	wrapperCssClasses="side-panel-top-anchor"
/>

<liferay-frontend:screen-navigation
	containerWrapperCssClass="container"
	key="<%= CPDefinitionScreenNavigationConstants.SCREEN_NAVIGATION_KEY_CP_DEFINITION_GENERAL %>"
	modelBean="<%= cpDefinition %>"
	portletURL="<%= currentURLObj %>"
/>

<aui:script>
	document
		.getElementById('<portlet:namespace />publishButton')
		.addEventListener('click', (e) => {
			e.preventDefault();

			var form = document.getElementById('<portlet:namespace />fm');

			if (!form) {
				throw new Error('Form with id: <portlet:namespace />fm not found!');
			}

			var workflowActionInput = document.getElementById(
				'<portlet:namespace />workflowAction'
			);

			if (workflowActionInput) {
				workflowActionInput.value =
					'<%= WorkflowConstants.ACTION_PUBLISH %>';
			}

			submitForm(form);
		});
</aui:script>