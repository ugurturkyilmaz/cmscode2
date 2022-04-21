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

<%@ include file="/bookmarks/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

BookmarksEntry entry = (BookmarksEntry)request.getAttribute(BookmarksWebKeys.BOOKMARKS_ENTRY);

long entryId = BeanParamUtil.getLong(entry, request, "entryId");

long folderId = BeanParamUtil.getLong(entry, request, "folderId");

if (entry != null) {
	BookmarksUtil.addPortletBreadcrumbEntries(entry, request, renderResponse);

	if (!layout.isTypeControlPanel()) {
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "edit"), currentURL);
	}
}
else {
	BookmarksUtil.addPortletBreadcrumbEntries(folderId, request, renderResponse);

	if (!layout.isTypeControlPanel()) {
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "add-bookmark"), currentURL);
	}
}

boolean showFolderSelector = ParamUtil.getBoolean(request, "showFolderSelector");

String headerTitle = (entry == null) ? LanguageUtil.get(request, "add-bookmark") : LanguageUtil.format(request, "edit-x", entry.getName(), false);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(headerTitle);
%>

<clay:container-fluid
	cssClass="container-form-lg"
>
	<portlet:actionURL name="/bookmarks/edit_entry" var="editEntryURL">
		<portlet:param name="mvcRenderCommandName" value="/bookmarks/edit_entry" />
	</portlet:actionURL>

	<aui:form action="<%= editEntryURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + liferayPortletResponse.getNamespace() + "saveEntry();" %>'>
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
		<aui:input name="portletResource" type="hidden" value='<%= ParamUtil.getString(request, "portletResource") %>' />
		<aui:input name="referringPortletResource" type="hidden" value="<%= referringPortletResource %>" />
		<aui:input name="entryId" type="hidden" value="<%= entryId %>" />
		<aui:input name="folderId" type="hidden" value="<%= folderId %>" />
		<aui:input name="showFolderSelector" type="hidden" value="<%= showFolderSelector %>" />

		<div class="lfr-form-content">
			<liferay-ui:error exception="<%= EntryURLException.class %>" message="please-enter-a-valid-url" />
			<liferay-ui:error exception="<%= NoSuchFolderException.class %>" message="please-enter-a-valid-folder" />

			<liferay-asset:asset-categories-error />

			<liferay-asset:asset-tags-error />

			<aui:model-context bean="<%= entry %>" model="<%= BookmarksEntry.class %>" />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<c:if test="<%= showFolderSelector %>">

						<%
						String folderName = StringPool.BLANK;

						if (folderId > 0) {
							BookmarksFolder folder = BookmarksFolderServiceUtil.getFolder(folderId);

							folderId = folder.getFolderId();
							folderName = folder.getName();
						}
						%>

						<div class="form-group">
							<aui:input label="folder" name="folderName" type="resource" value="<%= folderName %>" />

							<aui:button name="selectFolderButton" value="select" />

							<aui:script>
								var <portlet:namespace />selectFolderButton = document.getElementById(
									'<portlet:namespace />selectFolderButton'
								);

								if (<portlet:namespace />selectFolderButton) {
									<portlet:namespace />selectFolderButton.addEventListener(
										'click',
										(event) => {
											Liferay.Util.openSelectionModal({
												onSelect: function (event) {
													var folderData = {
														idString: 'folderId',
														idValue: event.entityid,
														nameString: 'folderName',
														nameValue: event.entityname,
													};

													Liferay.Util.selectFolder(
														folderData,
														'<portlet:namespace />'
													);
												},
												selectEventName: '<portlet:namespace />selectFolder',
												title:
													'<liferay-ui:message arguments="folder" key="select-x" />',
												url:
													'<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcRenderCommandName" value="/bookmarks/select_folder" /></liferay-portlet:renderURL>',
											});
										}
									);
								}
							</aui:script>

							<%
							String taglibRemoveFolder = "Liferay.Util.removeEntitySelection('folderId', 'folderName', this, '" + liferayPortletResponse.getNamespace() + "');";
							%>

							<aui:button disabled="<%= folderId <= 0 %>" name="removeFolderButton" onClick="<%= taglibRemoveFolder %>" value="remove" />
						</div>
					</c:if>

					<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) %>" name="name" />

					<aui:input name="url" />

					<aui:input name="description" />
				</aui:fieldset>

				<liferay-expando:custom-attributes-available
					className="<%= BookmarksEntry.class.getName() %>"
				>
					<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="custom-fields">
						<liferay-expando:custom-attribute-list
							className="<%= BookmarksEntry.class.getName() %>"
							classPK="<%= entryId %>"
							editable="<%= true %>"
							label="<%= true %>"
						/>
					</aui:fieldset>
				</liferay-expando:custom-attributes-available>

				<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="categorization">
					<liferay-asset:asset-categories-selector
						className="<%= BookmarksEntry.class.getName() %>"
						classPK="<%= entryId %>"
						visibilityTypes="<%= AssetVocabularyConstants.VISIBILITY_TYPES %>"
					/>

					<liferay-asset:asset-tags-selector
						className="<%= BookmarksEntry.class.getName() %>"
						classPK="<%= entryId %>"
					/>
				</aui:fieldset>

				<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="related-assets">
					<liferay-asset:input-asset-links
						className="<%= BookmarksEntry.class.getName() %>"
						classPK="<%= entryId %>"
					/>
				</aui:fieldset>

				<c:if test="<%= entry == null %>">
					<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="permissions">
						<liferay-ui:input-permissions
							modelName="<%= BookmarksEntry.class.getName() %>"
						/>
					</aui:fieldset>
				</c:if>

				<div class="sheet-footer">
					<aui:button type="submit" />

					<aui:button href="<%= redirect %>" type="cancel" />
				</div>
			</aui:fieldset-group>
		</div>
	</aui:form>
</clay:container-fluid>

<aui:script>
	function <portlet:namespace />saveEntry() {
		var form = document.getElementById('<portlet:namespace />fm');

		if (form) {
			var cmd = form.querySelector(
				'#<portlet:namespace /><%= Constants.CMD %>'
			);

			if (cmd) {
				cmd.setAttribute(
					'value',
					'<%= (entry == null) ? Constants.ADD : Constants.UPDATE %>'
				);

				submitForm(form);
			}
		}
	}
</aui:script>