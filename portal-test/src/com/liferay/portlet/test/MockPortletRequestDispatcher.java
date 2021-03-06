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

package com.liferay.portlet.test;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author David Arques
 * @see com.liferay.portlet.internal.PortletRequestDispatcherImpl
 */
public class MockPortletRequestDispatcher implements PortletRequestDispatcher {

	@Override
	public void forward(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws IOException, PortletException {
	}

	@Override
	public void include(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws IOException, PortletException {
	}

	@Override
	public void include(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
	}

}