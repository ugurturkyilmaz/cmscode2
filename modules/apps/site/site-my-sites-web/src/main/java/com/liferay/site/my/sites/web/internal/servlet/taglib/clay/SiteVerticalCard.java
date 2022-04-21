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

package com.liferay.site.my.sites.web.internal.servlet.taglib.clay;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.VerticalCard;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.my.sites.web.internal.constants.MySitesWebKeys;
import com.liferay.site.my.sites.web.internal.servlet.taglib.util.SiteActionDropdownItemsProvider;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class SiteVerticalCard implements VerticalCard {

	public SiteVerticalCard(
		BaseModel<?> baseModel, int groupOrganizationsCount,
		int groupUserGroupsCount, int groupUsersCount,
		RenderRequest renderRequest, RenderResponse renderResponse,
		String tabs1) {

		_groupOrganizationsCount = groupOrganizationsCount;
		_groupUserGroupsCount = groupUserGroupsCount;
		_groupUsersCount = groupUsersCount;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_tabs1 = tabs1;

		_group = (Group)baseModel;
		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		try {
			SiteActionDropdownItemsProvider siteActionDropdownItemsProvider =
				new SiteActionDropdownItemsProvider(
					_group, _renderRequest, _renderResponse, _tabs1);

			return siteActionDropdownItemsProvider.getActionDropdownItems();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return Collections.emptyList();
	}

	@Override
	public String getDefaultEventHandler() {
		return MySitesWebKeys.SITES_DROPDOWN_DEFAULT_EVENT_HANDLER;
	}

	@Override
	public String getHref() {
		if (_group.getPublicLayoutsPageCount() > 0) {
			return _group.getDisplayURL(_themeDisplay, false);
		}

		if (Objects.equals(_tabs1, "my-sites") &&
			(_group.getPrivateLayoutsPageCount() > 0)) {

			return _group.getDisplayURL(_themeDisplay, true);
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
		StringBundler sb = new StringBundler(5);

		if (_groupUsersCount == 1) {
			sb.append(
				LanguageUtil.format(
					_httpServletRequest, "x-user", _groupUsersCount, false));
		}
		else {
			sb.append(
				LanguageUtil.format(
					_httpServletRequest, "x-users", _groupUsersCount, false));
		}

		if (_groupOrganizationsCount > 0) {
			sb.append(StringPool.COMMA + StringPool.SPACE);

			if (_groupOrganizationsCount == 1) {
				sb.append(
					LanguageUtil.format(
						_httpServletRequest, "x-organization",
						_groupOrganizationsCount, false));
			}
			else {
				sb.append(
					LanguageUtil.format(
						_httpServletRequest, "x-organizations",
						_groupOrganizationsCount, false));
			}
		}

		if (_groupUserGroupsCount > 0) {
			sb.append(StringPool.COMMA + StringPool.SPACE);

			if (_groupUserGroupsCount == 1) {
				sb.append(
					LanguageUtil.format(
						_httpServletRequest, "x-user-group",
						_groupUserGroupsCount, false));
			}
			else {
				sb.append(
					LanguageUtil.format(
						_httpServletRequest, "x-user-groups",
						_groupUserGroupsCount, false));
			}
		}

		return sb.toString();
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
	public boolean isSelectable() {
		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SiteVerticalCard.class);

	private final Group _group;
	private final int _groupOrganizationsCount;
	private final int _groupUserGroupsCount;
	private final int _groupUsersCount;
	private final HttpServletRequest _httpServletRequest;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final String _tabs1;
	private final ThemeDisplay _themeDisplay;

}