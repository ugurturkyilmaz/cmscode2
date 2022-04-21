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

package com.liferay.template.web.internal.display.context;

import com.liferay.dynamic.data.mapping.configuration.DDMWebConfiguration;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemListBuilder;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.template.constants.TemplatePortletKeys;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;

/**
 * @author Lourdes Fernández Besada
 * @author Eudaldo Alonso
 */
public abstract class BaseTemplateDisplayContext {

	public BaseTemplateDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		this.liferayPortletRequest = liferayPortletRequest;
		this.liferayPortletResponse = liferayPortletResponse;

		_ddmWebConfiguration =
			(DDMWebConfiguration)liferayPortletRequest.getAttribute(
				DDMWebConfiguration.class.getName());
		themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<NavigationItem> getNavigationItems() {
		return NavigationItemListBuilder.add(
			navigationItem -> {
				navigationItem.setActive(
					Objects.equals(getTabs1(), "information-templates"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1",
					"information-templates");
				navigationItem.setLabel(
					LanguageUtil.get(
						themeDisplay.getLocale(), "information-templates"));
			}
		).add(
			navigationItem -> {
				navigationItem.setActive(
					Objects.equals(getTabs1(), "widget-templates"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1",
					"widget-templates");
				navigationItem.setLabel(
					LanguageUtil.get(
						themeDisplay.getLocale(), "widget-templates"));
			}
		).build();
	}

	public String getTabs1() {
		if (_tabs1 != null) {
			return _tabs1;
		}

		_tabs1 = ParamUtil.getString(
			liferayPortletRequest, "tabs1", "information-templates");

		return _tabs1;
	}

	public boolean isAddButtonEnabled() {
		if (!_ddmWebConfiguration.enableTemplateCreation() ||
			!isStagingGroup()) {

			return false;
		}

		return true;
	}

	public boolean isStagingGroup() {
		Group scopeGroup = themeDisplay.getScopeGroup();

		if (!scopeGroup.hasLocalOrRemoteStagingGroup() ||
			!scopeGroup.isStagedPortlet(TemplatePortletKeys.TEMPLATE)) {

			return true;
		}

		return false;
	}

	protected String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(liferayPortletRequest, "keywords");

		return _keywords;
	}

	protected String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			liferayPortletRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			"modified-date");

		return _orderByCol;
	}

	protected String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			liferayPortletRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM,
			"asc");

		return _orderByType;
	}

	protected PortletURL getPortletURL() {
		return PortletURLBuilder.createRenderURL(
			liferayPortletResponse
		).setTabs1(
			getTabs1()
		).buildPortletURL();
	}

	protected final LiferayPortletRequest liferayPortletRequest;
	protected final LiferayPortletResponse liferayPortletResponse;
	protected final ThemeDisplay themeDisplay;

	private final DDMWebConfiguration _ddmWebConfiguration;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private String _tabs1;

}