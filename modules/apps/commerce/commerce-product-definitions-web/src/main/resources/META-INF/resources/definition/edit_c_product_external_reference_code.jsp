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

CProduct cProduct = cpDefinitionsDisplayContext.getCProduct();
%>

<portlet:actionURL name="/cp_definitions/edit_c_product_external_reference_code" var="editCProductExternalReferenceCodeURL" />

<commerce-ui:modal-content>
	<aui:form action="<%= editCProductExternalReferenceCodeURL %>" cssClass="container-fluid container-fluid-max-xl p-0" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="cProductId" type="hidden" value="<%= cProduct.getCProductId() %>" />

		<aui:model-context bean="<%= cProduct %>" model="<%= CProduct.class %>" />

		<aui:input name="externalReferenceCode" type="text" value="<%= cProduct.getExternalReferenceCode() %>" wrapperCssClass="form-group-item" />
	</aui:form>
</commerce-ui:modal-content>