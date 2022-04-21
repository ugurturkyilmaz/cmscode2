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
DepotAdminSelectRoleDisplayContext depotAdminSelectRoleDisplayContext = (DepotAdminSelectRoleDisplayContext)request.getAttribute(DepotAdminWebKeys.DEPOT_ADMIN_SELECT_ROLE_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= (DepotAdminSelectRoleManagementToolbarDisplayContext)request.getAttribute(DepotAdminWebKeys.DEPOT_ADMIN_SELECT_ROLE_MANAGEMENT_TOOLBAL_DISPLAY_CONTEXT) %>"
/>

<aui:form action="<%= depotAdminSelectRoleDisplayContext.getPortletURL() %>" cssClass="container-fluid container-fluid-max-xl container-form-lg" method="post" name="selectDepotRoleFm">
	<c:choose>
		<c:when test="<%= depotAdminSelectRoleDisplayContext.isStep1() %>">

			<%
			DepotAdminSelectRoleDisplayContext.Step1 step1 = (DepotAdminSelectRoleDisplayContext.Step1)depotAdminSelectRoleDisplayContext.getStep();
			%>

			<aui:input name="groupId" type="hidden" />

			<div class="alert alert-info">
				<liferay-ui:message key="please-select-an-asset-library-to-which-you-will-assign-an-asset-library-role" />
			</div>

			<liferay-ui:search-container
				searchContainer="<%= step1.getSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.portal.kernel.model.Group"
					escapedModel="<%= true %>"
					keyProperty="groupId"
					modelVar="group"
					rowIdProperty="friendlyURL"
				>
					<liferay-ui:search-container-column-text
						name="name"
						value="<%= HtmlUtil.escape(group.getDescriptiveName(locale)) %>"
					/>

					<liferay-ui:search-container-column-text
						name="type"
						value=""
					/>

					<liferay-ui:search-container-column-text>
						<aui:button cssClass="group-selector-button" data='<%= Collections.singletonMap("groupid", group.getGroupId()) %>' value="choose" />
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>

			<aui:script require="frontend-js-web/liferay/delegate/delegate.es as delegateModule">
				var form = document.<portlet:namespace />selectDepotRoleFm;

				var delegate = delegateModule.default;

				delegate(form, 'click', '.group-selector-button', (event) => {
					Liferay.Util.postForm(form, {
						data: {
							groupId: event.delegateTarget.dataset.groupid,
						},

						url: '<%= step1.getSelectRolePortletURL() %>',
					});
				});
			</aui:script>
		</c:when>
		<c:when test="<%= depotAdminSelectRoleDisplayContext.isStep2() %>">

			<%
			DepotAdminSelectRoleDisplayContext.Step2 step2 = (DepotAdminSelectRoleDisplayContext.Step2)depotAdminSelectRoleDisplayContext.getStep();
			%>

			<div class="breadcrumbs">
				<%= step2.getBreadCrumbs() %>
			</div>

			<liferay-ui:search-container
				headerNames="name"
				searchContainer="<%= step2.getSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.portal.kernel.model.Role"
					keyProperty="roleId"
					modelVar="role"
				>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand table-cell-minw-200"
						name="title"
						value="<%= HtmlUtil.escape(role.getTitle(locale)) %>"
					/>

					<liferay-ui:search-container-column-text>
						<c:if test="<%= step2.isRoleAllowed(role) %>">
							<aui:button cssClass="selector-button" data="<%= step2.getData(role) %>" disabled="<%= step2.isDisabled(role) %>" value="choose" />
						</c:if>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</c:when>
	</c:choose>
</aui:form>