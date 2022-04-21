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

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.layout.admin.web.internal.product.navigation.control.menu.InformationMessagesProductNavigationControlMenuEntry;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.sites.kernel.util.SitesUtil;

import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Víctor Galán
 */
public class LayoutInformationMessagesDisplayContext {

	public LayoutInformationMessagesDisplayContext(
		HttpServletRequest httpServletRequest) {

		_httpServletRequest = httpServletRequest;
	}

	public Map<String, Object> getData() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		boolean showLinkedLayoutMessage = GetterUtil.getBoolean(
			_httpServletRequest.getAttribute(
				InformationMessagesProductNavigationControlMenuEntry.
					INFORMATION_MESSAGES_LINKED_LAYOUT));

		return HashMapBuilder.<String, Object>put(
			"linkedLayoutMessage",
			() -> {
				if (!showLinkedLayoutMessage) {
					return null;
				}

				ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
					"content.Language", themeDisplay.getLocale(), getClass());

				String message =
					"this-page-is-linked-to-a-site-template-which-does-not-" +
						"allow-modifications-to-it";

				Layout layout = themeDisplay.getLayout();

				Group group = themeDisplay.getScopeGroup();

				if (layout.isLayoutPrototypeLinkActive() &&
					!group.hasStagingGroup()) {

					message = "this-page-is-linked-to-a-page-template";
				}
				else if (SitesUtil.isUserGroupLayout(layout)) {
					message = "this-page-belongs-to-a-user-group";
				}

				return LanguageUtil.get(resourceBundle, message);
			}
		).put(
			"portletNamespace",
			PortalUtil.getPortletNamespace(LayoutAdminPortletKeys.GROUP_PAGES)
		).put(
			"resetPrototypeURL",
			() -> PortletURLBuilder.create(
				PortletURLFactoryUtil.create(
					_httpServletRequest, LayoutAdminPortletKeys.GROUP_PAGES,
					PortletRequest.ACTION_PHASE)
			).setActionName(
				"/layout_admin/reset_prototype"
			).setRedirect(
				PortalUtil.getLayoutURL(themeDisplay)
			).setParameter(
				"groupId", themeDisplay.getSiteGroupId()
			).buildString()
		).put(
			"showLinkedLayoutMessage", showLinkedLayoutMessage
		).put(
			"showModifiedLayoutMessage",
			GetterUtil.getBoolean(
				_httpServletRequest.getAttribute(
					InformationMessagesProductNavigationControlMenuEntry.
						INFORMATION_MESSAGES_MODIFIED_LAYOUT))
		).build();
	}

	private final HttpServletRequest _httpServletRequest;

}