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
boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");

PortletURL portletURL = currentURLObj;
%>

<clay:navigation-bar
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				Group group = themeDisplay.getScopeGroup();

				if (group.isPrivateLayoutsEnabled()) {
					add(
						navigationItem -> {
							navigationItem.setActive(!privateLayout);
							navigationItem.setHref(portletURL, "privateLayout", false);
							navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "public-pages"));
						});

					add(
						navigationItem -> {
							navigationItem.setActive(privateLayout);
							navigationItem.setHref(portletURL, "privateLayout", true);
							navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "private-pages"));
						});
				}
				else {
					add(
						navigationItem -> {
							navigationItem.setActive(!privateLayout);
							navigationItem.setHref(portletURL, "privateLayout", false);
							navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "pages"));
						});
				}
			}
		}
	%>'
/>

<aui:input id="groupId" name="TypeSettingsProperties--groupId--" type="hidden" value="<%= scopeGroupId %>" />

<aui:input id="layoutUuid" name="TypeSettingsProperties--layoutUuid--" type="hidden" value="" />

<aui:input id="privateLayout" name="TypeSettingsProperties--privateLayout--" type="hidden" value="<%= privateLayout %>" />

<liferay-layout:select-layout
	componentId='<%= liferayPortletResponse.getNamespace() + "selectLayout" %>'
	itemSelectorSaveEvent='<%= liferayPortletResponse.getNamespace() + "selectLayout" %>'
	multiSelection="<%= true %>"
	namespace="<%= liferayPortletResponse.getNamespace() %>"
	privateLayout="<%= privateLayout %>"
	showHiddenLayouts="<%= true %>"
/>

<aui:script>
	var layoutUuid = document.getElementById('<portlet:namespace />layoutUuid');

	if (layoutUuid) {
		Liferay.on('<portlet:namespace />selectLayout', (event) => {
			var selectedItems = event.data;

			if (selectedItems) {
				var layoutUuids = selectedItems.reduce(
					(previousValue, currentValue) => {
						return previousValue.concat([currentValue.id]);
					},
					[]
				);

				layoutUuid.value = layoutUuids.join();
			}
		});
	}
</aui:script>