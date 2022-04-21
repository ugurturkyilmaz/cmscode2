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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class HeaderTag extends IncludeTag {

	public String getBackLabel() {
		return _backLabel;
	}

	public String getBackURL() {
		return _backURL;
	}

	public String getCssClass() {
		return _cssClass;
	}

	public String getTitle() {
		return _title;
	}

	public boolean isEscapeXml() {
		return _escapeXml;
	}

	public boolean isLocalizeTitle() {
		return _localizeTitle;
	}

	public boolean isShowBackURL() {
		return _showBackURL;
	}

	public void setBackLabel(String backLabel) {
		_backLabel = backLabel;
	}

	public void setBackURL(String backURL) {
		if (Objects.equals(backURL, "javascript:history.go(-1)")) {
			backURL += ";";
		}

		_backURL = backURL;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setEscapeXml(boolean escapeXml) {
		_escapeXml = escapeXml;
	}

	public void setLocalizeTitle(boolean localizeTitle) {
		_localizeTitle = localizeTitle;
	}

	public void setShowBackURL(boolean showBackURL) {
		_showBackURL = showBackURL;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_backLabel = null;
		_backURL = null;
		_cssClass = null;
		_escapeXml = true;
		_localizeTitle = true;
		_showBackURL = true;
		_title = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-ui:header:backLabel", _backLabel);

		String redirect = PortalUtil.escapeRedirect(
			ParamUtil.getString(httpServletRequest, "redirect"));

		if (Validator.isNull(_backURL) && Validator.isNotNull(redirect)) {
			httpServletRequest.setAttribute(
				"liferay-ui:header:backURL", redirect);
		}
		else if (Validator.isNotNull(_backURL) &&
				 !_backURL.equals("javascript:history.go(-1);")) {

			httpServletRequest.setAttribute(
				"liferay-ui:header:backURL",
				PortalUtil.escapeRedirect(_backURL));
		}
		else {
			httpServletRequest.setAttribute(
				"liferay-ui:header:backURL", _backURL);
		}

		httpServletRequest.setAttribute(
			"liferay-ui:header:cssClass", _cssClass);
		httpServletRequest.setAttribute(
			"liferay-ui:header:escapeXml", String.valueOf(_escapeXml));
		httpServletRequest.setAttribute(
			"liferay-ui:header:localizeTitle", String.valueOf(_localizeTitle));
		httpServletRequest.setAttribute(
			"liferay-ui:header:showBackURL", String.valueOf(_showBackURL));
		httpServletRequest.setAttribute("liferay-ui:header:title", _title);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _PAGE = "/html/taglib/ui/header/page.jsp";

	private String _backLabel;
	private String _backURL;
	private String _cssClass;
	private boolean _escapeXml = true;
	private boolean _localizeTitle = true;
	private boolean _showBackURL = true;
	private String _title;

}