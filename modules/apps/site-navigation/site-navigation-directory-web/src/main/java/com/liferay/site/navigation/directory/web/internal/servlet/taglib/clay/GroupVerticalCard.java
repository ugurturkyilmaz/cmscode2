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

package com.liferay.site.navigation.directory.web.internal.servlet.taglib.clay;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.VerticalCard;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;

/**
 * @author Eudaldo Alonso
 */
public class GroupVerticalCard implements VerticalCard {

	public GroupVerticalCard(Group group, RenderRequest renderRequest) {
		_group = group;

		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	@Override
	public String getHref() {
		if (_group.getGroupId() != _themeDisplay.getScopeGroupId()) {
			return _group.getDisplayURL(_themeDisplay);
		}

		return null;
	}

	@Override
	public String getIcon() {
		return "sites";
	}

	@Override
	public String getImageSrc() {
		return _group.getLogoURL(_themeDisplay, false);
	}

	@Override
	public String getSubtitle() {
		return _group.getDescription(_themeDisplay.getLocale());
	}

	@Override
	public String getTitle() {
		try {
			return HtmlUtil.escape(
				_group.getDescriptiveName(_themeDisplay.getLocale()));
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return HtmlUtil.escape(_group.getName(_themeDisplay.getLocale()));
	}

	@Override
	public Boolean isFlushVertical() {
		return true;
	}

	@Override
	public boolean isSelectable() {
		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GroupVerticalCard.class);

	private final Group _group;
	private final ThemeDisplay _themeDisplay;

}