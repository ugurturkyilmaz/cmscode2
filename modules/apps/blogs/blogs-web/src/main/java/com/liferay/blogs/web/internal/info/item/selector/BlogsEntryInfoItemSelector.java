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

package com.liferay.blogs.web.internal.info.item.selector;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.info.item.selector.InfoItemSelector;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletMode;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(service = InfoItemSelector.class)
public class BlogsEntryInfoItemSelector
	implements InfoItemSelector<BlogsEntry> {

	@Override
	public PortletURL getInfoItemSelectorPortletURL(
			HttpServletRequest httpServletRequest)
		throws Exception {

		PortletURL infoItemSelectorPortletURL =
			PortletProviderUtil.getPortletURL(
				httpServletRequest, BlogsEntry.class.getName(),
				PortletProvider.Action.BROWSE);

		if (infoItemSelectorPortletURL == null) {
			return null;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		infoItemSelectorPortletURL.setParameter(
			"groupId", String.valueOf(themeDisplay.getScopeGroupId()));
		infoItemSelectorPortletURL.setParameter(
			"selectedGroupIds", String.valueOf(themeDisplay.getScopeGroupId()));

		infoItemSelectorPortletURL.setParameter(
			"typeSelection", BlogsEntry.class.getName());
		infoItemSelectorPortletURL.setParameter(
			"showNonindexable", String.valueOf(Boolean.TRUE));
		infoItemSelectorPortletURL.setParameter(
			"showScheduled", String.valueOf(Boolean.TRUE));
		infoItemSelectorPortletURL.setPortletMode(PortletMode.VIEW);
		infoItemSelectorPortletURL.setWindowState(LiferayWindowState.POP_UP);

		return infoItemSelectorPortletURL;
	}

}