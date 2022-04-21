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
DepotAdminMembershipsDisplayContext depotAdminMembershipsDisplayContext = (DepotAdminMembershipsDisplayContext)request.getAttribute(DepotAdminMembershipsDisplayContext.class.getName());

currentURLObj.setParameter("historyKey", liferayPortletResponse.getNamespace() + "asset-libraries");
%>

<aui:input name="<%= ActionRequest.ACTION_NAME %>" type="hidden" value="/depot/update_memberships" />

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="asset-libraries"
/>

<liferay-ui:membership-policy-error />

<clay:content-row
	containerElement="h3"
	cssClass="sheet-subtitle"
>
	<clay:content-col
		expand="<%= true %>"
	>
		<span class="heading-text"><%= depotAdminMembershipsDisplayContext.getLabel() %></span>
	</clay:content-col>

	<c:if test="<%= depotAdminMembershipsDisplayContext.isSelectable() %>">
		<clay:content-col>
			<span class="heading-end">
				<liferay-ui:icon
					cssClass="modify-link"
					id="selectDepotGroupLink"
					label="<%= true %>"
					linkCssClass="btn btn-secondary btn-sm"
					message="select"
					url="javascript:;"
				/>
			</span>
		</clay:content-col>
	</c:if>
</clay:content-row>

<liferay-util:buffer
	var="removeDepotGroupIcon"
>
	<liferay-ui:icon
		icon="times-circle"
		markupView="lexicon"
		message="remove"
	/>
</liferay-util:buffer>

<aui:input name="addDepotGroupIds" type="hidden" />
<aui:input name="deleteDepotGroupIds" type="hidden" />

<liferay-ui:search-container
	compactEmptyResultsMessage="<%= true %>"
	cssClass="lfr-search-container-repositories"
	curParam="depotsCur"
	emptyResultsMessage="this-user-does-not-belong-to-an-asset-library"
	headerNames="name,roles,null"
	id="depotGroupsSearchContainer"
	iteratorURL="<%= currentURLObj %>"
	total="<%= depotAdminMembershipsDisplayContext.getDepotGroupsCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= depotAdminMembershipsDisplayContext.getDepotGroups(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Group"
		escapedModel="<%= true %>"
		keyProperty="groupId"
		modelVar="group"
		rowIdProperty="friendlyURL"
	>
		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand"
			name="name"
			value="<%= HtmlUtil.escape(group.getName(locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand"
			name="roles"
			value="<%= HtmlUtil.escape(depotAdminMembershipsDisplayContext.getRoles(group)) %>"
		/>

		<c:if test="<%= depotAdminMembershipsDisplayContext.isDeletable() %>">
			<liferay-ui:search-container-column-text>
				<a class="modify-link" data-rowId="<%= group.getGroupId() %>" href="javascript:;"><%= removeDepotGroupIcon %></a>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<c:if test="<%= depotAdminMembershipsDisplayContext.isSelectable() %>">
	<aui:script use="liferay-search-container">
		var AArray = A.Array;

		var addDepotGroupIds = [];
		var deleteDepotGroupIds = [];

		var searchContainer = Liferay.SearchContainer.get(
			'<portlet:namespace />depotGroupsSearchContainer'
		);

		var searchContainerContentBox = searchContainer.get('contentBox');

		var selectDepotGroupLink = document.getElementById(
			'<portlet:namespace />selectDepotGroupLink'
		);

		selectDepotGroupLink.addEventListener('click', (event) => {
			Liferay.Util.openSelectionModal({
				onSelect: function (selectedItem) {
					if (selectedItem) {
						var itemValue = JSON.parse(selectedItem.value);

						var rowColumns = [];

						rowColumns.push(itemValue.title);
						rowColumns.push('');
						rowColumns.push(
							'<a class="modify-link" data-rowId="' +
								itemValue.classPK +
								'" href="javascript:;"><%= UnicodeFormatter.toString(removeDepotGroupIcon) %></a>'
						);

						var searchContainerData = searchContainer.getData();

						var itemsValues = searchContainerData.split(',');

						if (!itemsValues.includes(itemValue.classPK)) {
							searchContainer.addRow(rowColumns, itemValue.classPK);

							searchContainer.updateDataStore();
						}

						addDepotGroupIds.push(itemValue.classPK);

						AArray.removeItem(deleteDepotGroupIds, itemValue.classPK);

						document.<portlet:namespace />fm.<portlet:namespace />addDepotGroupIds.value = addDepotGroupIds.join(
							','
						);
						document.<portlet:namespace />fm.<portlet:namespace />deleteDepotGroupIds.value = deleteDepotGroupIds.join(
							','
						);
					}
				},
				selectEventName:
					'<%= depotAdminMembershipsDisplayContext.getItemSelectorEventName() %>',
				title:
					'<liferay-ui:message arguments="asset-library" key="select-x" />',
				url: '<%= depotAdminMembershipsDisplayContext.getItemSelectorURL() %>',
			});
		});

		var handleOnModifyLink = searchContainerContentBox.delegate(
			'click',
			(event) => {
				var link = event.currentTarget;

				var rowId = link.attr('data-rowId');
				var tr = link.ancestor('tr');

				var selectGroup = Liferay.Util.getWindow(
					'<portlet:namespace />selectGroup'
				);

				if (selectGroup) {
					var selectButton = selectGroup.iframe.node
						.get('contentWindow.document')
						.one('.selector-button[data-entityid="' + rowId + '"]');

					Liferay.Util.toggleDisabled(selectButton, false);
				}

				searchContainer.deleteRow(tr, rowId);

				AArray.removeItem(addDepotGroupIds, event.rowId);

				deleteDepotGroupIds.push(rowId);

				document.<portlet:namespace />fm.<portlet:namespace />addDepotGroupIds.value = addDepotGroupIds.join(
					','
				);
				document.<portlet:namespace />fm.<portlet:namespace />deleteDepotGroupIds.value = deleteDepotGroupIds.join(
					','
				);
			},
			'.modify-link'
		);

		var onDestroyPortlet = function (event) {
			if (event.portletId === '<%= portletDisplay.getId() %>') {
				Liferay.detach(handleOnModifyLink);

				Liferay.detach('destroyPortlet', onDestroyPortlet);
			}
		};

		Liferay.on('destroyPortlet', onDestroyPortlet);
	</aui:script>
</c:if>