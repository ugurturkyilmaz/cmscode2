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

package com.liferay.change.tracking.web.internal.display.context;

import com.liferay.change.tracking.model.CTProcess;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemListBuilder;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Samuel Trong Tran
 */
public class ViewHistoryManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewHistoryManagementToolbarDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		SearchContainer<CTProcess> searchContainer,
		ViewHistoryDisplayContext viewHistoryDisplayContext) {

		super(
			httpServletRequest, liferayPortletRequest, liferayPortletResponse,
			searchContainer);

		_viewHistoryDisplayContext = viewHistoryDisplayContext;
	}

	@Override
	public String getClearResultsURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setKeywords(
			StringPool.BLANK
		).buildString();
	}

	@Override
	public List<LabelItem> getFilterLabelItems() {
		return LabelItemListBuilder.add(
			() -> Objects.equals(
				_viewHistoryDisplayContext.getFilterByStatus(), "published"),
			labelItem -> _createLabelItem("published", labelItem)
		).add(
			() -> Objects.equals(
				_viewHistoryDisplayContext.getFilterByStatus(), "failed"),
			labelItem -> _createLabelItem("failed", labelItem)
		).add(
			() -> Objects.equals(
				_viewHistoryDisplayContext.getFilterByStatus(), "in-progress"),
			labelItem -> _createLabelItem("in-progress", labelItem)
		).build();
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

	@Override
	protected String getDisplayStyle() {
		return _viewHistoryDisplayContext.getDisplayStyle();
	}

	@Override
	protected String[] getDisplayViews() {
		return new String[] {"list", "descriptive"};
	}

	@Override
	protected List<DropdownItem> getFilterNavigationDropdownItems() {
		return DropdownItemListBuilder.add(
			dropdownItem -> _createDropdownItem(dropdownItem, "all")
		).add(
			dropdownItem -> _createDropdownItem(dropdownItem, "published")
		).add(
			dropdownItem -> _createDropdownItem(dropdownItem, "failed")
		).add(
			dropdownItem -> _createDropdownItem(dropdownItem, "in-progress")
		).build();
	}

	@Override
	protected String getFilterNavigationDropdownItemsLabel() {
		return LanguageUtil.get(httpServletRequest, "filter-by-status");
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"published-date", "name"};
	}

	private void _createDropdownItem(DropdownItem dropdownItem, String label) {
		dropdownItem.setActive(
			Objects.equals(
				_viewHistoryDisplayContext.getFilterByStatus(), label));
		dropdownItem.setHref(getPortletURL(), "status", label);
		dropdownItem.setLabel(LanguageUtil.get(httpServletRequest, label));
	}

	private void _createLabelItem(String label, LabelItem labelItem) {
		PortletURL portletURL = null;

		try {
			portletURL = PortletURLUtil.clone(
				currentURLObj, liferayPortletResponse);
		}
		catch (PortletException portletException) {
			throw new SystemException(portletException);
		}

		portletURL.setParameter("status", (String)null);

		labelItem.putData("removeLabelURL", portletURL.toString());

		labelItem.setCloseable(true);
		labelItem.setLabel(
			LanguageUtil.get(httpServletRequest, "status") + ": " +
				LanguageUtil.get(httpServletRequest, label));
	}

	private final ViewHistoryDisplayContext _viewHistoryDisplayContext;

}