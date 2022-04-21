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

import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ConcurrentModificationException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Samuel Trong Tran
 */
public abstract class BasePublicationsDisplayContext {

	public BasePublicationsDisplayContext(
		HttpServletRequest httpServletRequest) {

		_httpServletRequest = httpServletRequest;

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			httpServletRequest);
	}

	public String getDisplayStyle() {
		if (_displayStyle != null) {
			return _displayStyle;
		}

		String displayStyle = ParamUtil.getString(
			_httpServletRequest, "displayStyle");

		if (Validator.isNull(displayStyle)) {
			displayStyle = _portalPreferences.getValue(
				CTPortletKeys.PUBLICATIONS,
				getPortalPreferencesPrefix() + "-display-style", "list");
		}

		try {
			_portalPreferences.setValue(
				CTPortletKeys.PUBLICATIONS,
				getPortalPreferencesPrefix() + "-display-style", displayStyle);
		}
		catch (ConcurrentModificationException
					concurrentModificationException) {

			log.error(
				concurrentModificationException,
				concurrentModificationException);
		}

		_displayStyle = displayStyle;

		return _displayStyle;
	}

	protected abstract String getDefaultOrderByCol();

	protected String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = SearchOrderByUtil.getOrderByCol(
			_httpServletRequest, CTPortletKeys.PUBLICATIONS,
			getPortalPreferencesPrefix() + "-order-by-col",
			getDefaultOrderByCol());

		return _orderByCol;
	}

	protected String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = SearchOrderByUtil.getOrderByType(
			_httpServletRequest, CTPortletKeys.PUBLICATIONS,
			getPortalPreferencesPrefix() + "-order-by-type", "desc");

		return _orderByType;
	}

	protected abstract String getPortalPreferencesPrefix();

	protected static final Log log = LogFactoryUtil.getLog(
		BasePublicationsDisplayContext.class);

	private String _displayStyle;
	private final HttpServletRequest _httpServletRequest;
	private String _orderByCol;
	private String _orderByType;
	private final PortalPreferences _portalPreferences;

}