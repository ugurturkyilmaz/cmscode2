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

import com.liferay.layout.admin.web.internal.util.LayoutPageTemplatePortletUtil;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.LayoutTypeControllerTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class SelectLayoutPageTemplateEntryDisplayContext {

	public SelectLayoutPageTemplateEntryDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_httpServletRequest = httpServletRequest;
		_liferayPortletResponse = liferayPortletResponse;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public String getBackURL() {
		if (_backURL != null) {
			return _backURL;
		}

		String backURL = ParamUtil.getString(_httpServletRequest, "backURL");

		if (Validator.isNull(backURL)) {
			backURL = getRedirect();
		}

		_backURL = backURL;

		return _backURL;
	}

	public Map<String, Object> getComponentContext() {
		return HashMapBuilder.<String, Object>put(
			"eventName",
			() -> {
				String eventName = ParamUtil.getString(
					_httpServletRequest, "eventName",
					_liferayPortletResponse.getNamespace() +
						"selectMasterLayout");

				return HtmlUtil.escape(eventName);
			}
		).put(
			"selector", ".select-master-layout-option"
		).build();
	}

	public List<LayoutPageTemplateEntry> getGlobalLayoutPageTemplateEntries() {
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator =
			LayoutPageTemplatePortletUtil.
				getLayoutPageTemplateEntryOrderByComparator("name", "asc");

		return LayoutPageTemplateEntryServiceUtil.getLayoutPageTemplateEntries(
			_themeDisplay.getCompanyGroupId(),
			LayoutPageTemplateEntryTypeConstants.TYPE_WIDGET_PAGE,
			WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, orderByComparator);
	}

	public int getGlobalLayoutPageTemplateEntriesCount() {
		return LayoutPageTemplateEntryServiceUtil.
			getLayoutPageTemplateEntriesCount(
				_themeDisplay.getCompanyGroupId(),
				LayoutPageTemplateEntryTypeConstants.TYPE_WIDGET_PAGE,
				WorkflowConstants.STATUS_APPROVED);
	}

	public long getLayoutPageTemplateCollectionId() {
		if (_layoutPageTemplateCollectionId != null) {
			return _layoutPageTemplateCollectionId;
		}

		_layoutPageTemplateCollectionId = ParamUtil.getLong(
			_httpServletRequest, "layoutPageTemplateCollectionId");

		return _layoutPageTemplateCollectionId;
	}

	public List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		int start, int end) {

		return LayoutPageTemplateEntryServiceUtil.getLayoutPageTemplateEntries(
			_themeDisplay.getScopeGroupId(),
			getLayoutPageTemplateCollectionId(),
			WorkflowConstants.STATUS_APPROVED, start, end);
	}

	public int getLayoutPageTemplateEntriesCount() {
		return LayoutPageTemplateEntryServiceUtil.
			getLayoutPageTemplateEntriesCount(
				_themeDisplay.getScopeGroupId(),
				getLayoutPageTemplateCollectionId(),
				WorkflowConstants.STATUS_APPROVED);
	}

	public List<LayoutPageTemplateEntry> getMasterLayoutPageTemplateEntries() {
		List<LayoutPageTemplateEntry> masterLayoutPageTemplateEntries =
			new ArrayList<>();

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateEntryLocalServiceUtil.
				createLayoutPageTemplateEntry(0);

		layoutPageTemplateEntry.setName(
			LanguageUtil.get(_httpServletRequest, "blank"));
		layoutPageTemplateEntry.setStatus(WorkflowConstants.STATUS_APPROVED);

		masterLayoutPageTemplateEntries.add(layoutPageTemplateEntry);

		Group scopeGroup = _themeDisplay.getScopeGroup();

		long scopeGroupId = _themeDisplay.getScopeGroupId();

		if (scopeGroup.isLayoutPrototype()) {
			LayoutPageTemplateEntry layoutPrototypeLayoutPageTemplateEntry =
				LayoutPageTemplateEntryLocalServiceUtil.
					fetchFirstLayoutPageTemplateEntry(scopeGroup.getClassPK());

			scopeGroupId = layoutPrototypeLayoutPageTemplateEntry.getGroupId();
		}

		masterLayoutPageTemplateEntries.addAll(
			LayoutPageTemplateEntryServiceUtil.getLayoutPageTemplateEntries(
				scopeGroupId,
				LayoutPageTemplateEntryTypeConstants.TYPE_MASTER_LAYOUT,
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null));

		return masterLayoutPageTemplateEntries;
	}

	public String getRedirect() {
		if (_redirect != null) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		return _redirect;
	}

	public String getSelectedTab() {
		if (_selectedTab != null) {
			return _selectedTab;
		}

		_selectedTab = ParamUtil.getString(
			_httpServletRequest, "selectedTab", "basic-templates");

		return _selectedTab;
	}

	public List<String> getTypes() {
		if (_types != null) {
			return _types;
		}

		_types = ListUtil.filter(
			ListUtil.fromArray(LayoutTypeControllerTracker.getTypes()),
			type -> {
				LayoutTypeController layoutTypeController =
					LayoutTypeControllerTracker.getLayoutTypeController(type);

				return layoutTypeController.isInstanceable() &&
					   !layoutTypeController.isPrimaryType();
			});

		return _types;
	}

	public int getTypesCount() {
		List<String> types = getTypes();

		return types.size();
	}

	public boolean isBasicTemplates() {
		if ((getLayoutPageTemplateCollectionId() != 0) ||
			!Objects.equals(getSelectedTab(), "basic-templates")) {

			return false;
		}

		return true;
	}

	public boolean isContentPages() {
		if (getLayoutPageTemplateCollectionId() != 0) {
			return true;
		}

		return false;
	}

	public boolean isGlobalTemplates() {
		if ((getLayoutPageTemplateCollectionId() != 0) ||
			!Objects.equals(getSelectedTab(), "global-templates")) {

			return false;
		}

		return true;
	}

	private String _backURL;
	private final HttpServletRequest _httpServletRequest;
	private Long _layoutPageTemplateCollectionId;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _redirect;
	private String _selectedTab;
	private final ThemeDisplay _themeDisplay;
	private List<String> _types;

}