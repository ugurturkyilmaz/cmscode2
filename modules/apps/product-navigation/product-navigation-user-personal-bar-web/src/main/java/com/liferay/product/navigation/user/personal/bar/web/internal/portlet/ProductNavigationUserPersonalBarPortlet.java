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

package com.liferay.product.navigation.user.personal.bar.web.internal.portlet;

import com.liferay.application.list.PanelAppRegistry;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.user.personal.bar.web.internal.constants.ProductNavigationUserPersonalBarPortletKeys;
import com.liferay.product.navigation.user.personal.bar.web.internal.constants.ProductNavigationUserPersonalBarWebKeys;
import com.liferay.site.util.RecentGroupManager;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-user-personal-bar",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=false",
		"javax.portlet.display-name=User Personal Bar",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ProductNavigationUserPersonalBarPortletKeys.PRODUCT_NAVIGATION_USER_PERSONAL_BAR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ProductNavigationUserPersonalBarPortlet extends MVCPortlet {

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		if (!user.isDefaultUser()) {
			renderRequest.setAttribute(
				ProductNavigationUserPersonalBarWebKeys.NOTIFICATIONS_COUNT,
				_getNotificationsCount(themeDisplay));
		}

		_recentGroupManager.addRecentGroup(
			_portal.getHttpServletRequest(renderRequest),
			themeDisplay.getScopeGroupId());

		super.doDispatch(renderRequest, renderResponse);
	}

	@Reference(unbind = "-")
	protected void setPanelAppRegistry(PanelAppRegistry panelAppRegistry) {
		_panelAppRegistry = panelAppRegistry;
	}

	@Reference(unbind = "-")
	protected void setPanelCategoryRegistry(
		PanelCategoryRegistry panelCategoryRegistry) {

		_panelCategoryRegistry = panelCategoryRegistry;
	}

	private int _getNotificationsCount(ThemeDisplay themeDisplay) {
		if (_userNotificationEventLocalService == null) {
			return 0;
		}

		return _userNotificationEventLocalService.
			getUserNotificationEventsCount(
				themeDisplay.getUserId(),
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false);
	}

	private PanelAppRegistry _panelAppRegistry;
	private PanelCategoryRegistry _panelCategoryRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private RecentGroupManager _recentGroupManager;

	@Reference
	private UserNotificationEventLocalService
		_userNotificationEventLocalService;

}