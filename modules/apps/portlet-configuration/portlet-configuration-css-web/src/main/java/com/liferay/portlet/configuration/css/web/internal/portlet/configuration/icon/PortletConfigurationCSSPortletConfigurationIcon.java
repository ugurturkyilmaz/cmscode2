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

package com.liferay.portlet.configuration.css.web.internal.portlet.configuration.icon;

import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.configuration.css.web.internal.constants.PortletConfigurationCSSPortletKeys;

import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = PortletConfigurationIcon.class)
public class PortletConfigurationCSSPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getLocale(portletRequest), getClass());

		return LanguageUtil.get(resourceBundle, "look-and-feel-configuration");
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		try {
			return PortletURLBuilder.create(
				PortletURLFactoryUtil.create(
					portletRequest,
					PortletConfigurationCSSPortletKeys.
						PORTLET_CONFIGURATION_CSS,
					PortletRequest.RENDER_PHASE)
			).setMVCPath(
				"/view.jsp"
			).setPortletResource(
				() -> {
					ThemeDisplay themeDisplay =
						(ThemeDisplay)portletRequest.getAttribute(
							WebKeys.THEME_DISPLAY);

					PortletDisplay portletDisplay =
						themeDisplay.getPortletDisplay();

					return portletDisplay.getId();
				}
			).setWindowState(
				LiferayWindowState.POP_UP
			).buildString();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return StringPool.BLANK;
	}

	@Override
	public double getWeight() {
		return 16.0;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (isEmbeddedPersonalApplicationLayout(themeDisplay.getLayout())) {
			return false;
		}

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.isShowPortletCssIcon();
	}

	@Override
	public boolean isToolTip() {
		return false;
	}

	@Override
	public boolean isUseDialog() {
		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletConfigurationCSSPortletConfigurationIcon.class);

}