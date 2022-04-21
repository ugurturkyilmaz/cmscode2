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

<liferay-ui:search-container
	id="assigneesSearch"
	searchContainer='<%= (SearchContainer)request.getAttribute("edit_role_assignments.jsp-searchContainer") %>'
	var="segmentsEntrySearchContainer"
>
	<liferay-ui:search-container-row
		className="com.liferay.segments.model.SegmentsEntry"
		keyProperty="segmentsEntryId"
		modelVar="segmentsEntry"
	>
		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand table-title"
			name="name"
			value="<%= HtmlUtil.escape(segmentsEntry.getName(locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand-smallest table-cell-minw-150"
			name="active"
			translate="<%= true %>"
			value='<%= segmentsEntry.getActive() ? "yes" : "no" %>'
		/>

		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand-smallest table-cell-minw-150"
			name="source"
			value="<%= segmentsEntry.getSource() %>"
		/>

		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand-smallest table-cell-minw-150"
			name="scope"
			value="<%= HtmlUtil.escape(SegmentsEntryDisplayContext.getGroupDescriptiveName(segmentsEntry, locale)) %>"
		/>

		<liferay-ui:search-container-column-date
			cssClass="table-cell-expand-smallest table-cell-minw-150 table-cell-ws-nowrap"
			name="create-date"
			value="<%= segmentsEntry.getCreateDate() %>"
		/>

		<c:choose>
			<c:when test='<%= Objects.equals(ParamUtil.getString(request, "tabs3"), "current") %>'>
				<portlet:renderURL var="viewMembersURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/view_segments_entry_users.jsp" />
					<portlet:param name="segmentsEntryId" value="<%= String.valueOf(segmentsEntry.getSegmentsEntryId()) %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand-smallest table-cell-minw-150"
					name="members"
				>
					<liferay-ui:icon
						label="<%= true %>"
						message="<%= String.valueOf(SegmentsEntryDisplayContext.getSegmentsEntryUsersCount(segmentsEntry.getSegmentsEntryId())) %>"
						onClick='<%= liferayPortletResponse.getNamespace() + "openViewMembersDialog(event);" %>'
						url="<%= viewMembersURL %>"
					/>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu
						direction="left-side"
						icon="<%= StringPool.BLANK %>"
						markupView="lexicon"
						message="<%= StringPool.BLANK %>"
						showWhenSingleIcon="<%= true %>"
					>
						<liferay-ui:icon
							message="view-members"
							onClick='<%= liferayPortletResponse.getNamespace() + "openViewMembersDialog(event);" %>'
							url="<%= viewMembersURL %>"
						/>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
			</c:when>
			<c:otherwise>
				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand-smallest table-cell-minw-150"
					name="members"
					value="<%= String.valueOf(SegmentsEntryDisplayContext.getSegmentsEntryUsersCount(segmentsEntry.getSegmentsEntryId())) %>"
				/>
			</c:otherwise>
		</c:choose>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<aui:script>
	function <portlet:namespace />openViewMembersDialog(event) {
		Liferay.Util.openInDialog(event, {
			dialog: {
				constrain: true,
				destroyOnHide: true,
				height: 768,
				modal: true,
				width: 600,
			},
			uri: event.currentTarget.href,
			title: '<liferay-ui:message key="members" />',
		});
	}
</aui:script>