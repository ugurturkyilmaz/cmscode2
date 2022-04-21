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
long fragmentCollectionId = ParamUtil.getLong(request, "fragmentCollectionId");

ImportDisplayContext importDisplayContext = new ImportDisplayContext(request, renderRequest);
%>

<portlet:actionURL name="/fragment/import" var="importURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="portletResource" value="<%= portletDisplay.getId() %>" />
	<portlet:param name="fragmentCollectionId" value="<%= String.valueOf(fragmentCollectionId) %>" />
</portlet:actionURL>

<liferay-frontend:edit-form
	action="<%= importURL %>"
	enctype="multipart/form-data"
	name="fm"
>
	<liferay-frontend:edit-form-body>

		<%
		List<String> draftFragmentsImporterResultEntries = importDisplayContext.getFragmentsImporterResultEntries(FragmentsImporterResultEntry.Status.IMPORTED_DRAFT);
		%>

		<c:if test="<%= ListUtil.isNotEmpty(draftFragmentsImporterResultEntries) %>">
			<clay:alert
				dismissible="<%= true %>"
				message='<%= LanguageUtil.format(request, "the-following-fragments-have-validation-issues.-they-have-been-left-in-draft-status-x", "<strong>" + StringUtil.merge(draftFragmentsImporterResultEntries, StringPool.COMMA_AND_SPACE) + "</strong>", false) %>'
			/>
		</c:if>

		<%
		List<String> invalidFragmentsImporterResultEntries = importDisplayContext.getFragmentsImporterResultEntries(FragmentsImporterResultEntry.Status.INVALID);
		%>

		<c:if test="<%= ListUtil.isNotEmpty(invalidFragmentsImporterResultEntries) %>">
			<clay:alert
				dismissible="<%= true %>"
				displayType="warning"
				message='<%= LanguageUtil.format(request, "the-following-fragments-could-not-be-imported-x", "<strong>" + StringUtil.merge(invalidFragmentsImporterResultEntries, StringPool.COMMA_AND_SPACE) + "</strong>", false) %>'
			/>
		</c:if>

		<liferay-ui:error exception="<%= DuplicateFragmentCollectionKeyException.class %>">

			<%
			DuplicateFragmentCollectionKeyException dfcke = (DuplicateFragmentCollectionKeyException)errorException;
			%>

			<liferay-ui:message arguments='<%= "<em>" + dfcke.getMessage() + "</em>" %>' key="a-fragment-set-with-the-key-x-already-exists" />
		</liferay-ui:error>

		<liferay-ui:error exception="<%= DuplicateFragmentEntryKeyException.class %>">

			<%
			DuplicateFragmentEntryKeyException dfeke = (DuplicateFragmentEntryKeyException)errorException;
			%>

			<liferay-ui:message arguments='<%= "<em>" + dfeke.getMessage() + "</em>" %>' key="a-fragment-entry-with-the-key-x-already-exists" />
		</liferay-ui:error>

		<liferay-ui:error exception="<%= InvalidFileException.class %>" message="the-selected-file-is-not-a-valid-zip-file" />

		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset>
				<aui:input label="select-file" name="file" type="file">
					<aui:validator name="required" />

					<aui:validator name="acceptFiles">
						'zip'
					</aui:validator>
				</aui:input>

				<aui:input checked="<%= true %>" label="overwrite-existing-entries" name="overwrite" type="checkbox" />
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
</liferay-frontend:edit-form>