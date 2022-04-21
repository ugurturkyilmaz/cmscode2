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
long previewClassNameId = ParamUtil.getLong(request, "previewClassNameId");
long previewClassPK = ParamUtil.getLong(request, "previewClassPK");
int previewType = ParamUtil.getInteger(request, "previewType");

AssetEntryResult assetEntryResult = (AssetEntryResult)request.getAttribute("view.jsp-assetEntryResult");

Group stageableGroup = themeDisplay.getScopeGroup();

if (stageableGroup.isLayout()) {
	stageableGroup = layout.getGroup();
}
%>

<clay:sheet>
	<c:if test="<%= Validator.isNotNull(assetEntryResult.getTitle()) %>">
		<clay:sheet-header>
			<p class="h4 sheet-title">
				<%= assetEntryResult.getTitle() %>
			</p>
		</clay:sheet-header>
	</c:if>

	<clay:sheet-section>
		<div class="table-responsive">
			<table class="table table-autofit">
				<thead>
					<tr>
						<th class="table-cell-expand table-title">
							<liferay-ui:message key="title" />
						</th>

						<%
						for (String metadataField : assetPublisherDisplayContext.getMetadataFields()) {
						%>

							<th class="table-cell-expand">
								<liferay-ui:message key="<%= metadataField %>" />
							</th>

						<%
						}
						%>

						<c:if test="<%= !stageableGroup.hasStagingGroup() %>">
							<th></th>
						</c:if>
					</tr>
				</thead>

				<tbody>

					<%
					for (AssetEntry assetEntry : assetEntryResult.getAssetEntries()) {
						AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassNameId(assetEntry.getClassNameId());

						if (assetRendererFactory == null) {
							continue;
						}

						AssetRenderer<?> assetRenderer = null;

						try {
							if ((previewClassNameId == assetEntry.getClassNameId()) && (previewClassPK == assetEntry.getClassPK())) {
								assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK(), previewType);
							}
							else {
								assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK());
							}
						}
						catch (Exception e) {
							if (_log.isWarnEnabled()) {
								_log.warn(e);
							}
						}

						if ((assetRenderer == null) || (!assetRenderer.isDisplayable() && (previewClassPK <= 0))) {
							continue;
						}

						String viewURL = assetPublisherHelper.getAssetViewURL(liferayPortletRequest, liferayPortletResponse, assetRenderer, assetEntry, assetPublisherDisplayContext.isAssetLinkBehaviorViewInPortlet());

						request.setAttribute("view.jsp-assetEntry", assetEntry);
						request.setAttribute("view.jsp-assetRenderer", assetRenderer);

						Map<String, Object> fragmentsEditorData = HashMapBuilder.<String, Object>put(
							"fragments-editor-item-id", PortalUtil.getClassNameId(assetRenderer.getClassName()) + "-" + assetRenderer.getClassPK()
						).put(
							"fragments-editor-item-type", "fragments-editor-mapped-item"
						).build();

						String title = assetRenderer.getTitle(LocaleUtil.fromLanguageId(LanguageUtil.getLanguageId(request)));
					%>

						<tr class="<%= ((previewClassNameId == assetEntry.getClassNameId()) && (previewClassPK == assetEntry.getClassPK())) ? "table-active" : StringPool.BLANK %>" <%= AUIUtil.buildData(fragmentsEditorData) %>>
							<td class="table-cell-expand table-title">
								<span class="asset-anchor lfr-asset-anchor" id="<%= assetEntry.getEntryId() %>"></span>

								<c:choose>
									<c:when test="<%= Validator.isNotNull(viewURL) %>">
										<a class="text-truncate-inline" href="<%= viewURL %>">
											<span class="text-truncate"><%= HtmlUtil.escape(title) %></span>
										</a>
									</c:when>
									<c:otherwise>
										<span class="text-truncate-inline">
											<span class="text-truncate"><%= HtmlUtil.escape(title) %></span>
										</span>
									</c:otherwise>
								</c:choose>
							</td>

							<%
							for (String metadataField : assetPublisherDisplayContext.getMetadataFields()) {
							%>

								<c:choose>
									<c:when test='<%= Objects.equals(metadataField, "author") %>'>
										<td class="table-cell-expand">
											<%= HtmlUtil.escape(PortalUtil.getUserName(assetRenderer.getUserId(), assetRenderer.getUserName())) %>
										</td>
									</c:when>
									<c:when test='<%= Objects.equals(metadataField, "categories") %>'>
										<td class="table-cell-expand">
											<liferay-asset:asset-categories-summary
												className="<%= assetEntry.getClassName() %>"
												classPK="<%= assetEntry.getClassPK() %>"
												displayStyle="simple-category"
												portletURL="<%= renderResponse.createRenderURL() %>"
											/>
										</td>
									</c:when>
									<c:when test='<%= Objects.equals(metadataField, "tags") %>'>
										<td class="table-cell-expand">
											<liferay-asset:asset-tags-summary
												className="<%= assetEntry.getClassName() %>"
												classPK="<%= assetEntry.getClassPK() %>"
												portletURL="<%= renderResponse.createRenderURL() %>"
											/>
										</td>
									</c:when>
									<c:otherwise>

										<%
										String value = null;

										if (Objects.equals(metadataField, "create-date")) {
											value = dateFormatDate.format(assetEntry.getCreateDate());
										}
										else if (Objects.equals(metadataField, "modified-date")) {
											value = dateFormatDate.format(assetEntry.getModifiedDate());
										}
										else if (Objects.equals(metadataField, "publish-date")) {
											if (assetEntry.getPublishDate() == null) {
												value = StringPool.BLANK;
											}
											else {
												value = dateFormatDate.format(assetEntry.getPublishDate());
											}
										}
										else if (Objects.equals(metadataField, "expiration-date")) {
											if (assetEntry.getExpirationDate() == null) {
												value = StringPool.BLANK;
											}
											else {
												value = dateFormatDate.format(assetEntry.getExpirationDate());
											}
										}
										else if (Objects.equals(metadataField, "priority")) {
											value = String.valueOf(assetEntry.getPriority());
										}
										else if (Objects.equals(metadataField, "view-count")) {
											value = String.valueOf(assetEntry.getViewCount());
										}
										%>

										<td class="table-cell-expand-smallest">
											<liferay-ui:message key="<%= value %>" />
										</td>
									</c:otherwise>
								</c:choose>

							<%
							}
							%>

							<c:if test="<%= !stageableGroup.hasStagingGroup() %>">
								<td>
									<span class="table-action-link">
										<liferay-util:include page="/asset_actions.jsp" servletContext="<%= application %>" />
									</span>
								</td>
							</c:if>
						</tr>

					<%
					}
					%>

				</tbody>
			</table>
		</div>
	</clay:sheet-section>
</clay:sheet>

<%!
private static final Log _log = LogFactoryUtil.getLog("com_liferay_asset_publisher_web.view_asset_entries_table_jsp");
%>