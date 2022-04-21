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

package com.liferay.portal.language.override.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenuBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.language.override.web.internal.display.LanguageItemDisplay;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Drew Brokke
 */
public class ViewManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewManagementToolbarDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		SearchContainer<LanguageItemDisplay> searchContainer,
		String displayStyle, boolean hasManageLanguageOverridesPermission) {

		super(
			httpServletRequest, liferayPortletRequest, liferayPortletResponse,
			searchContainer);

		_displayStyle = displayStyle;
		_hasManageLanguageOverridesPermission =
			hasManageLanguageOverridesPermission;
	}

	@Override
	public String getClearResultsURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setKeywords(
			StringPool.BLANK
		).setNavigation(
			(String)null
		).buildString();
	}

	@Override
	public CreationMenu getCreationMenu() {
		if (!_hasManageLanguageOverridesPermission) {
			return null;
		}

		return CreationMenuBuilder.addPrimaryDropdownItem(
			dropdownItem -> {
				dropdownItem.setHref(
					getPortletURL(), "mvcPath", "/edit_plo_entry.jsp",
					"backURL", String.valueOf(getPortletURL()), "key",
					StringPool.BLANK);
				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "add-language-key"));
			}
		).build();
	}

	@Override
	public List<LabelItem> getFilterLabelItems() {
		LabelItemList labelItems = new LabelItemList();

		String navigation = getNavigation();

		if (Objects.equals("all", navigation)) {
			return labelItems;
		}

		labelItems.add(
			labelItem -> {
				labelItem.putData(
					"removeLabelURL",
					PortletURLBuilder.create(
						getPortletURL()
					).setNavigation(
						(String)null
					).buildString());
				labelItem.setDismissible(true);
				labelItem.setLabel(
					LanguageUtil.get(httpServletRequest, navigation));
			});

		return labelItems;
	}

	@Override
	public String getSearchActionURL() {
		return String.valueOf(searchContainer.getIteratorURL());
	}

	@Override
	public Boolean isDisabled() {
		return false;
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

	@Override
	protected String getDefaultDisplayStyle() {
		return "descriptive";
	}

	@Override
	protected String getDisplayStyle() {
		return _displayStyle;
	}

	@Override
	protected String[] getDisplayViews() {
		return new String[] {"list", "descriptive"};
	}

	@Override
	protected String getFilterNavigationDropdownItemsLabel() {
		return LanguageUtil.get(httpServletRequest, "filter-by-override");
	}

	@Override
	protected String[] getNavigationKeys() {
		return new String[] {"any-language", "selected-language"};
	}

	private final String _displayStyle;
	private final boolean _hasManageLanguageOverridesPermission;

}