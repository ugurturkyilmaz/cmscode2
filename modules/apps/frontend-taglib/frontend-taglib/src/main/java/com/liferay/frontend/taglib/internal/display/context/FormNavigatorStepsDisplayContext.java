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

package com.liferay.frontend.taglib.internal.display.context;

import com.liferay.frontend.taglib.form.navigator.FormNavigatorEntry;
import com.liferay.frontend.taglib.form.navigator.FormNavigatorEntryProvider;
import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionClicks;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class FormNavigatorStepsDisplayContext {

	public FormNavigatorStepsDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_httpServletRequest = httpServletRequest;
		_liferayPortletResponse = liferayPortletResponse;
	}

	public String getBackURL() {
		String backURL = (String)_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:backURL");

		if (Validator.isNull(backURL)) {
			backURL = ParamUtil.getString(_httpServletRequest, "redirect");
		}

		if (Validator.isNull(backURL)) {
			backURL = String.valueOf(getPortletURL());
		}

		return backURL;
	}

	public String[] getCategoryKeys() {
		return (String[])_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:categoryKeys");
	}

	public String[] getCategoryLabels() {
		return (String[])_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:categoryLabels");
	}

	public String[][] getCategorySectionKeys() {
		return (String[][])_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:categorySectionKeys");
	}

	public String[][] getCategorySectionLabels() {
		return (String[][])_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:categorySectionLabels");
	}

	public String getCurSection() {
		String curSection = StringPool.BLANK;

		String[][] categorySectionKeys = getCategorySectionKeys();

		if (categorySectionKeys[0].length > 0) {
			curSection = categorySectionKeys[0][0];
		}

		String historyKey = ParamUtil.getString(
			_httpServletRequest, "historyKey");

		if (Validator.isNotNull(historyKey)) {
			curSection = historyKey;
		}

		return curSection;
	}

	public String getFieldSetCssClass() {
		return (String)_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator:fieldSetCssClass");
	}

	public Object getFormModelBean() {
		return _httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:formModelBean");
	}

	public String getFormName() {
		return (String)_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:formName");
	}

	public List<FormNavigatorEntry<Object>> getFormNavigatorEntries() {
		FormNavigatorEntryProvider formNavigatorEntryProvider =
			ServletContextUtil.getFormNavigatorEntryProvider();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return formNavigatorEntryProvider.getFormNavigatorEntries(
			getId(), themeDisplay.getUser(), getFormModelBean());
	}

	public List<FormNavigatorEntry<Object>> getFormNavigatorEntries(
		String categoryKey) {

		FormNavigatorEntryProvider formNavigatorEntryProvider =
			ServletContextUtil.getFormNavigatorEntryProvider();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return formNavigatorEntryProvider.getFormNavigatorEntries(
			getId(), categoryKey, themeDisplay.getUser(), getFormModelBean());
	}

	public String getHtmlBottom() {
		return (String)_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:htmlBottom");
	}

	public String getHtmlTop() {
		return (String)_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:htmlTop");
	}

	public String getId() {
		return (String)_httpServletRequest.getAttribute(
			"liferay-frontend:form-navigator-steps:id");
	}

	public PortletURL getPortletURL() {
		return _liferayPortletResponse.createRenderURL();
	}

	public String getSectionId(String name) {
		return TextFormatter.format(name, TextFormatter.M);
	}

	public String getTabs1Param() {
		String randomNamespace = PortalUtil.generateRandomKey(
			_httpServletRequest, "taglib_ui_form_navigator_init");

		return randomNamespace + "_tabs1";
	}

	public String getTabs1Value() {
		return GetterUtil.getString(
			SessionClicks.get(
				_httpServletRequest,
				_liferayPortletResponse.getNamespace() + getId(), null));
	}

	public boolean isShowButtons() {
		return GetterUtil.getBoolean(
			(String)_httpServletRequest.getAttribute(
				"liferay-frontend:form-navigator-steps:showButtons"));
	}

	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;

}