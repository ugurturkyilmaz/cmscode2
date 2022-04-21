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

<%@ include file="/admin/asset/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<%= kbArticle.getContent() %>

<%
List<FileEntry> attachmentsFileEntries = new ArrayList<FileEntry>();

if (kbArticle != null) {
	attachmentsFileEntries = kbArticle.getAttachmentsFileEntries();
}
%>

<c:if test="<%= !attachmentsFileEntries.isEmpty() %>">
	<div class="kb-attachments">
		<h5><liferay-ui:message key="attachments" /></h5>

		<clay:row>

			<%
			for (FileEntry fileEntry : attachmentsFileEntries) {
			%>

				<clay:col
					md="4"
				>
					<liferay-frontend:horizontal-card
						text="<%= fileEntry.getTitle() %>"
						url='<%= PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, fileEntry, "status=" + WorkflowConstants.STATUS_APPROVED) %>'
					>
						<liferay-frontend:horizontal-card-col>
							<liferay-document-library:mime-type-sticker
								fileVersion="<%= fileEntry.getFileVersion() %>"
							/>
						</liferay-frontend:horizontal-card-col>
					</liferay-frontend:horizontal-card>
				</clay:col>

			<%
			}
			%>

		</clay:row>
	</div>
</c:if>