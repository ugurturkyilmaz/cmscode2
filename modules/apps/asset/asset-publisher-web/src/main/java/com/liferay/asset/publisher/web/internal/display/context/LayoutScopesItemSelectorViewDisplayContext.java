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

package com.liferay.asset.publisher.web.internal.display.context;

import com.liferay.asset.publisher.util.AssetPublisherHelper;
import com.liferay.item.selector.criteria.group.criterion.GroupItemSelectorCriterion;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.usersadmin.search.GroupSearch;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class LayoutScopesItemSelectorViewDisplayContext
	extends BaseItemSelectorViewDisplayContext {

	public LayoutScopesItemSelectorViewDisplayContext(
		HttpServletRequest httpServletRequest,
		AssetPublisherHelper assetPublisherHelper,
		GroupItemSelectorCriterion groupItemSelectorCriterion,
		String itemSelectedEventName, PortletURL portletURL) {

		super(
			httpServletRequest, assetPublisherHelper,
			groupItemSelectorCriterion, itemSelectedEventName, portletURL);
	}

	@Override
	public long getGroupId() {
		long groupId = super.getGroupId();

		if (groupId <= 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			return themeDisplay.getScopeGroupId();
		}

		return groupId;
	}

	@Override
	public GroupSearch getGroupSearch() throws Exception {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long groupId = getGroupId();

		GroupSearch groupSearch = new GroupSearch(
			getPortletRequest(), getPortletURL());

		groupSearch.setResultsAndTotal(
			() -> _filterLayoutGroups(
				GroupLocalServiceUtil.getGroups(
					themeDisplay.getCompanyId(), Layout.class.getName(),
					groupId, groupSearch.getStart(), groupSearch.getEnd()),
				_isPrivateLayout()),
			GroupLocalServiceUtil.getGroupsCount(
				themeDisplay.getCompanyId(), Layout.class.getName(), groupId));

		return groupSearch;
	}

	@Override
	public boolean isShowSearch() {
		return false;
	}

	private List<Group> _filterLayoutGroups(
			List<Group> groups, Boolean privateLayout)
		throws Exception {

		if (privateLayout == null) {
			return groups;
		}

		List<Group> filteredGroups = new ArrayList<>();

		for (Group group : groups) {
			if (!group.isLayout()) {
				continue;
			}

			Layout layout = LayoutLocalServiceUtil.getLayout(
				group.getClassPK());

			if (layout.isPrivateLayout() == privateLayout) {
				filteredGroups.add(group);
			}
		}

		return filteredGroups;
	}

	private Boolean _isPrivateLayout() {
		if (_privateLayout != null) {
			return _privateLayout;
		}

		GroupItemSelectorCriterion groupItemSelectorCriterion =
			getGroupItemSelectorCriterion();

		_privateLayout = groupItemSelectorCriterion.isPrivateLayout();

		return _privateLayout;
	}

	private Boolean _privateLayout;

}