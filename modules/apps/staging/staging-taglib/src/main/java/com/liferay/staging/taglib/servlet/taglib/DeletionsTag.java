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

package com.liferay.staging.taglib.servlet.taglib;

import com.liferay.staging.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Levente Hudák
 */
public class DeletionsTag extends IncludeTag {

	public String getCmd() {
		return _cmd;
	}

	public long getExportImportConfigurationId() {
		return _exportImportConfigurationId;
	}

	public boolean isDisableInputs() {
		return _disableInputs;
	}

	public void setCmd(String cmd) {
		_cmd = cmd;
	}

	public void setDisableInputs(boolean disableInputs) {
		_disableInputs = disableInputs;
	}

	public void setExportImportConfigurationId(
		long exportImportConfigurationId) {

		_exportImportConfigurationId = exportImportConfigurationId;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cmd = null;
		_disableInputs = false;
		_exportImportConfigurationId = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute("liferay-staging:deletions:cmd", _cmd);
		httpServletRequest.setAttribute(
			"liferay-staging:deletions:disableInputs", _disableInputs);
		httpServletRequest.setAttribute(
			"liferay-staging:deletions:exportImportConfigurationId",
			_exportImportConfigurationId);
	}

	private static final String _PAGE = "/deletions/page.jsp";

	private String _cmd;
	private boolean _disableInputs;
	private long _exportImportConfigurationId;

}