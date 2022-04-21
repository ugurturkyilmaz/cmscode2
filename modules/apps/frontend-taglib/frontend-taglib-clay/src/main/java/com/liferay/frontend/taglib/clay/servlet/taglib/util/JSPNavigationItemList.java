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

package com.liferay.frontend.taglib.clay.servlet.taglib.util;

import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public class JSPNavigationItemList extends NavigationItemList {

	public JSPNavigationItemList(PageContext pageContext) {
		currentURL = (String)pageContext.findAttribute("currentURL");
		httpServletRequest = (HttpServletRequest)pageContext.getRequest();
		renderResponse = (RenderResponse)pageContext.findAttribute(
			"renderResponse");
		themeDisplay = (ThemeDisplay)pageContext.findAttribute("themeDisplay");

		request = httpServletRequest;
	}

	protected String currentURL;
	protected HttpServletRequest httpServletRequest;
	protected RenderResponse renderResponse;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #httpServletRequest}
	 */
	@Deprecated
	protected HttpServletRequest request;

	protected ThemeDisplay themeDisplay;

}