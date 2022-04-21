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

package com.liferay.commerce.address.web.internal.display.context;

import com.liferay.commerce.address.web.internal.portlet.action.helper.ActionHelper;
import com.liferay.commerce.address.web.internal.servlet.taglib.ui.constants.CommerceCountryScreenNavigationConstants;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public abstract class BaseCommerceCountriesDisplayContext<T> {

	public BaseCommerceCountriesDisplayContext(
		ActionHelper actionHelper,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		this.actionHelper = actionHelper;
		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;

		_portletResourcePermission = portletResourcePermission;

		_defaultOrderByCol = "priority";
		_defaultOrderByType = "asc";
	}

	public Country getCountry() throws PortalException {
		if (_country != null) {
			return _country;
		}

		_country = actionHelper.getCountry(renderRequest);

		return _country;
	}

	public long getCountryId() throws PortalException {
		Country country = getCountry();

		if (country == null) {
			return 0;
		}

		return country.getCountryId();
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			renderRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			_defaultOrderByCol);
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			renderRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM,
			_defaultOrderByType);
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = renderResponse.createRenderURL();

		if (getCountryId() > 0) {
			portletURL.setParameter(
				"countryId", String.valueOf(getCountryId()));
		}

		String delta = ParamUtil.getString(renderRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		portletURL.setParameter("navigation", getNavigation());
		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(renderResponse);
		}

		return _rowChecker;
	}

	public String getScreenNavigationCategoryKey() {
		return CommerceCountryScreenNavigationConstants.
			CATEGORY_KEY_COMMERCE_COUNTRY_DETAILS;
	}

	public abstract SearchContainer<T> getSearchContainer()
		throws PortalException;

	public String getSelectedScreenNavigationCategoryKey() {
		return ParamUtil.getString(
			renderRequest, "screenNavigationCategoryKey",
			getScreenNavigationCategoryKey());
	}

	public boolean hasPermission(String actionId) {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return PortalPermissionUtil.contains(
			themeDisplay.getPermissionChecker(), actionId);
	}

	public void setDefaultOrderByCol(String defaultOrderByCol) {
		_defaultOrderByCol = defaultOrderByCol;
	}

	public void setDefaultOrderByType(String defaultOrderByType) {
		_defaultOrderByType = defaultOrderByType;
	}

	protected String getNavigation() {
		return ParamUtil.getString(renderRequest, "navigation", "active");
	}

	protected final ActionHelper actionHelper;
	protected final RenderRequest renderRequest;
	protected final RenderResponse renderResponse;
	protected SearchContainer<T> searchContainer;

	private Country _country;
	private String _defaultOrderByCol;
	private String _defaultOrderByType;
	private final PortletResourcePermission _portletResourcePermission;
	private RowChecker _rowChecker;

}