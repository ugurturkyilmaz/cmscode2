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

package com.liferay.commerce.product.type.virtual.web.internal.frontend.taglib.form.navigator;

import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.web.internal.servlet.taglib.ui.constants.CPDefinitionVirtualSettingFormNavigatorConstants;
import com.liferay.frontend.taglib.form.navigator.BaseJSPFormNavigatorEntry;
import com.liferay.frontend.taglib.form.navigator.FormNavigatorEntry;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, property = "form.navigator.entry.order:Integer=90",
	service = FormNavigatorEntry.class
)
public class CPDefinitionVirtualSettingDetailsBaseInfoFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<CPDefinitionVirtualSetting> {

	@Override
	public String getCategoryKey() {
		return CPDefinitionVirtualSettingFormNavigatorConstants.
			CATEGORY_KEY_CP_DEFINITION_VIRTUAL_SETTING_DETAILS;
	}

	@Override
	public String getFormNavigatorId() {
		return CPDefinitionVirtualSettingFormNavigatorConstants.
			FORM_NAVIGATOR_ID_CP_DEFINITION_VIRTUAL_SETTING;
	}

	@Override
	public String getKey() {
		return "base-information";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "base-information");
	}

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		RequestDispatcher requestDispatcher =
			_cpDefinitionVirtualSettingServletContext.getRequestDispatcher(
				getJspPath());

		try {
			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (ServletException servletException) {
			throw new IOException(servletException);
		}
	}

	@Override
	protected String getJspPath() {
		return "/definition_virtual_setting/base_information.jsp";
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.type.virtual.web)"
	)
	private ServletContext _cpDefinitionVirtualSettingServletContext;

}