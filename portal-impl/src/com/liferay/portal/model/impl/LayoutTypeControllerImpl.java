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

package com.liferay.portal.model.impl;

import com.liferay.petra.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.servlet.DirectRequestDispatcherFactoryUtil;
import com.liferay.portal.kernel.servlet.PipingServletResponse;
import com.liferay.portal.kernel.servlet.TransferHeadersHelperUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.struts.StrutsUtil;
import com.liferay.portal.util.PropsUtil;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public class LayoutTypeControllerImpl implements LayoutTypeController {

	public LayoutTypeControllerImpl(String type) {
		_type = type;

		Filter filter = new Filter(type);

		_browsable = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.LAYOUT_BROWSABLE, filter), true);
		_configurationActionDelete = StringUtil.split(
			GetterUtil.getString(
				PropsUtil.get(
					PropsKeys.LAYOUT_CONFIGURATION_ACTION_DELETE, filter)));
		_configurationActionUpdate = StringUtil.split(
			GetterUtil.getString(
				PropsUtil.get(
					PropsKeys.LAYOUT_CONFIGURATION_ACTION_UPDATE, filter)));
		_editPage = GetterUtil.getString(
			PropsUtil.get(PropsKeys.LAYOUT_EDIT_PAGE, filter));
		_firstPageable = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.LAYOUT_FIRST_PAGEABLE, filter));
		_fullPageDisplayable = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.FULL_PAGE_DISPLAYABLE, filter));
		_parentable = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.LAYOUT_PARENTABLE, filter), true);
		_sitemapable = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.LAYOUT_SITEMAPABLE, filter), true);
		_url = GetterUtil.getString(
			PropsUtil.get(PropsKeys.LAYOUT_URL, filter));
		_urlFriendliable = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.LAYOUT_URL_FRIENDLIABLE, filter), true);

		String viewPage = GetterUtil.getString(
			PropsUtil.get(PropsKeys.LAYOUT_VIEW_PAGE, filter));

		_viewPage = StrutsUtil.TEXT_HTML_DIR + viewPage;
	}

	@Override
	public String[] getConfigurationActionDelete() {
		return _configurationActionDelete;
	}

	@Override
	public String[] getConfigurationActionUpdate() {
		return _configurationActionUpdate;
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public String getURL() {
		return _url;
	}

	public String getViewPath(String portletId) {

		// Manually check the p_p_id. See LEP-1724.

		if (Validator.isNull(portletId)) {
			return _viewPage;
		}

		return StrutsUtil.TEXT_HTML_DIR + "/portal/layout/view/portlet.jsp";
	}

	@Override
	public String includeEditContent(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Layout layout)
		throws Exception {

		httpServletRequest.setAttribute(WebKeys.SEL_LAYOUT, layout);

		ServletContext servletContext =
			(ServletContext)httpServletRequest.getAttribute(WebKeys.CTX);

		RequestDispatcher requestDispatcher =
			TransferHeadersHelperUtil.getTransferHeadersRequestDispatcher(
				DirectRequestDispatcherFactoryUtil.getRequestDispatcher(
					servletContext, getEditPage()));

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);

		requestDispatcher.include(httpServletRequest, pipingServletResponse);

		return unsyncStringWriter.toString();
	}

	@Override
	public boolean includeLayoutContent(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Layout layout)
		throws Exception {

		ServletContext servletContext =
			(ServletContext)httpServletRequest.getAttribute(WebKeys.CTX);

		String portletId = ParamUtil.getString(httpServletRequest, "p_p_id");

		String path = getViewPath(portletId);

		RequestDispatcher requestDispatcher =
			TransferHeadersHelperUtil.getTransferHeadersRequestDispatcher(
				DirectRequestDispatcherFactoryUtil.getRequestDispatcher(
					servletContext, path));

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);

		String contentType = pipingServletResponse.getContentType();

		requestDispatcher.include(httpServletRequest, pipingServletResponse);

		if (contentType != null) {
			httpServletResponse.setContentType(contentType);
		}

		httpServletRequest.setAttribute(
			WebKeys.LAYOUT_CONTENT, unsyncStringWriter.getStringBundler());

		return false;
	}

	@Override
	public boolean isBrowsable() {
		return _browsable;
	}

	@Override
	public boolean isCheckLayoutViewPermission() {
		return true;
	}

	@Override
	public boolean isFirstPageable() {
		return _firstPageable;
	}

	@Override
	public boolean isFullPageDisplayable() {
		return _fullPageDisplayable;
	}

	@Override
	public boolean isInstanceable() {
		return true;
	}

	@Override
	public boolean isParentable() {
		return _parentable;
	}

	@Override
	public boolean isSitemapable() {
		return _sitemapable;
	}

	@Override
	public boolean isURLFriendliable() {
		return _urlFriendliable;
	}

	@Override
	public boolean isWorkflowEnabled() {
		return true;
	}

	@Override
	public boolean matches(
		HttpServletRequest httpServletRequest, String friendlyURL,
		Layout layout) {

		try {
			Map<Locale, String> friendlyURLMap = layout.getFriendlyURLMap();

			Collection<String> values = friendlyURLMap.values();

			return values.contains(friendlyURL);
		}
		catch (SystemException systemException) {
			throw new RuntimeException(systemException);
		}
	}

	protected String getEditPage() {
		return StrutsUtil.TEXT_HTML_DIR + _editPage;
	}

	private final boolean _browsable;
	private final String[] _configurationActionDelete;
	private final String[] _configurationActionUpdate;
	private final String _editPage;
	private final boolean _firstPageable;
	private final boolean _fullPageDisplayable;
	private final boolean _parentable;
	private final boolean _sitemapable;
	private final String _type;
	private final String _url;
	private final boolean _urlFriendliable;
	private final String _viewPage;

}