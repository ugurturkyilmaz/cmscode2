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
%>

<ul class="list-group show-quick-actions-on-hover">
	<c:if test="<%= Validator.isNotNull(assetEntryResult.getTitle()) %>">
		<li class="list-group-header">
			<p class="h3 list-group-header-title"><%= assetEntryResult.getTitle() %></p>
		</li>
	</c:if>

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

		request.setAttribute("view.jsp-assetEntry", assetEntry);
		request.setAttribute("view.jsp-assetRenderer", assetRenderer);

		Map<String, Object> fragmentsEditorData = HashMapBuilder.<String, Object>put(
			"fragments-editor-item-id", PortalUtil.getClassNameId(assetRenderer.getClassName()) + "-" + assetRenderer.getClassPK()
		).put(
			"fragments-editor-item-type", "fragments-editor-mapped-item"
		).build();
	%>

		<li class="list-group-item list-group-item-flex <%= ((previewClassNameId == assetEntry.getClassNameId()) && (previewClassPK == assetEntry.getClassPK())) ? "active" : StringPool.BLANK %>" <%= AUIUtil.buildData(fragmentsEditorData) %>>
			<c:if test="<%= assetPublisherDisplayContext.isShowAuthor() %>">
				<clay:content-col>
					<span class="inline-item">
						<liferay-ui:user-portrait
							userId="<%= assetEntry.getUserId() %>"
						/>
					</span>
				</clay:content-col>
			</c:if>

			<clay:content-col
				expand="<%= true %>"
			>
				<p class="h4 list-group-title text-truncate">
					<span class="asset-anchor lfr-asset-anchor" id="<%= assetEntry.getEntryId() %>"></span>

					<aui:a href="<%= assetPublisherHelper.getAssetViewURL(liferayPortletRequest, liferayPortletResponse, assetRenderer, assetEntry, assetPublisherDisplayContext.isAssetLinkBehaviorViewInPortlet()) %>"> <%= HtmlUtil.escape(assetEntry.getTitle(locale)) %>
					</aui:a>
				</p>

				<%
				Date displayDate = assetPublisherDisplayContext.isShowCreateDate() ? assetEntry.getCreateDate() : null;

				if (assetPublisherDisplayContext.isShowPublishDate() && (assetEntry.getPublishDate() != null)) {
					displayDate = assetEntry.getPublishDate();
				}
				else if (assetPublisherDisplayContext.isShowModifiedDate() && (assetEntry.getModifiedDate() != null)) {
					displayDate = assetEntry.getModifiedDate();
				}
				%>

				<c:if test="<%= displayDate != null %>">
					<p class="list-group-subtitle text-truncate">
						<%= dateFormatDateTime.format(displayDate) %>
					</p>
				</c:if>

				<c:if test="<%= assetPublisherDisplayContext.isShowCategories() || assetPublisherDisplayContext.isShowTags() %>">
					<div class="list-group-detail">
						<c:if test="<%= assetPublisherDisplayContext.isShowCategories() %>">
							<liferay-asset:asset-categories-summary
								className="<%= assetEntry.getClassName() %>"
								classPK="<%= assetEntry.getClassPK() %>"
								displayStyle="simple-category"
								portletURL="<%= renderResponse.createRenderURL() %>"
							/>
						</c:if>

						<c:if test="<%= assetPublisherDisplayContext.isShowTags() %>">
							<liferay-asset:asset-tags-summary
								className="<%= assetEntry.getClassName() %>"
								classPK="<%= assetEntry.getClassPK() %>"
								portletURL="<%= renderResponse.createRenderURL() %>"
							/>
						</c:if>
					</div>
				</c:if>
			</clay:content-col>

			<clay:content-col>
				<liferay-util:include page="/asset_actions.jsp" servletContext="<%= application %>" />
			</clay:content-col>
		</li>

	<%
	}
	%>

</ul>

<%!
private static final Log _log = LogFactoryUtil.getLog("com_liferay_asset_publisher_web.view_asset_entries_title_list_jsp");
%>