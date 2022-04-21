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
long templateId = ParamUtil.getLong(request, "templateId");

long classNameId = ParamUtil.getLong(request, "classNameId");
long classPK = ParamUtil.getLong(request, "classPK");

DDMStructure structure = null;

long structureClassNameId = PortalUtil.getClassNameId(DDMStructure.class);

if ((classPK > 0) && (structureClassNameId == classNameId)) {
	structure = DDMStructureLocalServiceUtil.getStructure(classPK);
}
%>

<liferay-util:include page="/navigation_bar.jsp" servletContext="<%= application %>" />

<clay:management-toolbar
	clearResultsURL="<%= ddmDisplayContext.getClearResultsURL() %>"
	creationMenu="<%= ddmDisplayContext.getTemplateCreationMenu() %>"
	disabled="<%= ddmDisplayContext.isDisabledManagementBar(DDMWebKeys.DYNAMIC_DATA_MAPPING_TEMPLATE) %>"
	filterDropdownItems="<%= ddmDisplayContext.getFilterItemsDropdownItems() %>"
	itemsTotal="<%= ddmDisplayContext.getTotalItems(DDMWebKeys.DYNAMIC_DATA_MAPPING_TEMPLATE) %>"
	searchActionURL="<%= ddmDisplayContext.getSelectTemplateSearchActionURL() %>"
	searchFormName="searchForm"
	searchInputAutoFocus="<%= true %>"
	selectable="<%= false %>"
	sortingOrder="<%= ddmDisplayContext.getOrderByType() %>"
	sortingURL="<%= ddmDisplayContext.getSortingURL() %>"
/>

<aui:form action="<%= ddmDisplayContext.getSelectTemplateSearchActionURL() %>" method="post" name="selectTemplateFm">
	<clay:container-fluid>
		<liferay-ui:search-container
			searchContainer="<%= ddmDisplayContext.getTemplateSearch() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.dynamic.data.mapping.model.DDMTemplate"
				keyProperty="templateId"
				modelVar="template"
			>
				<liferay-ui:search-container-column-text
					name="id"
					value="<%= String.valueOf(template.getTemplateId()) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand"
					name="name"
				>
					<c:choose>
						<c:when test="<%= template.getTemplateId() != templateId %>">

							<%
							Map<String, Object> data = new HashMap<>();

							if (ddmDisplay.isShowConfirmSelectTemplate()) {
								data.put("confirm-selection", Boolean.TRUE.toString());
								data.put("confirm-selection-message", ddmDisplay.getConfirmSelectTemplateMessage(locale));
							}

							data.put("ddmtemplateid", template.getTemplateId());
							data.put("ddmtemplatekey", template.getTemplateKey());
							data.put("description", template.getDescription(locale));
							data.put("imageurl", template.getTemplateImageURL(themeDisplay));
							data.put("name", template.getName(locale));
							%>

							<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
								<%= HtmlUtil.escape(template.getName(locale)) %>
							</aui:a>
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(template.getName(locale)) %>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-jsp
					cssClass="table-cell-expand"
					name="description"
					path="/template_description.jsp"
				/>

				<liferay-ui:search-container-column-date
					name="modified-date"
					value="<%= template.getModifiedDate() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</clay:container-fluid>
</aui:form>