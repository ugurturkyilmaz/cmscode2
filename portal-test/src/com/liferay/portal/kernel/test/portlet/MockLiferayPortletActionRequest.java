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

package com.liferay.portal.kernel.test.portlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portletmvc4spring.test.mock.web.portlet.MockActionRequest;

import java.io.IOException;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

import javax.portlet.ActionParameters;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletResponse;
import javax.portlet.RenderParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Alicia García
 * @author Cristina González
 */
public class MockLiferayPortletActionRequest
	extends MockActionRequest implements LiferayPortletRequest {

	public MockLiferayPortletActionRequest() {
		this(new MockHttpServletRequest());
	}

	public MockLiferayPortletActionRequest(
		MockHttpServletRequest mockHttpServletRequest) {

		_mockHttpServletRequest = mockHttpServletRequest;

		_mockHttpServletRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG,
			ProxyUtil.newProxyInstance(
				LiferayPortletConfig.class.getClassLoader(),
				new Class<?>[] {LiferayPortletConfig.class},
				(proxy, method, args) -> {
					if (Objects.equals(method.getName(), "getPortletId")) {
						return "testPortlet";
					}

					return null;
				}));
	}

	@Override
	public void addParameter(String name, String value) {
		_mockHttpServletRequest.addParameter(name, value);
	}

	@Override
	public void addParameter(String name, String[] values) {
		_mockHttpServletRequest.addParameter(name, values);
	}

	@Override
	public void cleanUp() {
	}

	@Override
	public Map<String, String[]> clearRenderParameters() {
		return null;
	}

	@Override
	public void defineObjects(
		PortletConfig portletConfig, PortletResponse portletResponse) {
	}

	@Override
	public ActionParameters getActionParameters() {
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		return _mockHttpServletRequest.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return _mockHttpServletRequest.getAttributeNames();
	}

	@Override
	public long getContentLengthLong() {
		return 0;
	}

	@Override
	public HttpServletRequest getHttpServletRequest() {
		return _mockHttpServletRequest;
	}

	@Override
	public String getLifecycle() {
		return null;
	}

	@Override
	public HttpServletRequest getOriginalHttpServletRequest() {
		return null;
	}

	@Override
	public String getParameter(String name) {
		return _mockHttpServletRequest.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return _mockHttpServletRequest.getParameterMap();
	}

	@Override
	public Part getPart(String name) throws IOException, PortletException {
		return null;
	}

	@Override
	public Collection<Part> getParts() throws IOException, PortletException {
		return null;
	}

	@Override
	public long getPlid() {
		return 0;
	}

	@Override
	public Portlet getPortlet() {
		return null;
	}

	@Override
	public PortletContext getPortletContext() {
		return null;
	}

	@Override
	public String getPortletName() {
		return null;
	}

	@Override
	public HttpServletRequest getPortletRequestDispatcherRequest() {
		return null;
	}

	@Override
	public RenderParameters getRenderParameters() {
		return null;
	}

	@Override
	public String getUserAgent() {
		return null;
	}

	@Override
	public void invalidateSession() {
	}

	@Override
	public void setAttribute(String name, Object value) {
		_mockHttpServletRequest.setAttribute(name, value);
	}

	@Override
	public void setParameter(String key, String value) {
		_mockHttpServletRequest.setParameter(key, value);
	}

	@Override
	public void setParameter(String key, String[] values) {
		_mockHttpServletRequest.setParameter(key, values);
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		_mockHttpServletRequest.setParameters(parameters);
	}

	@Override
	public void setPortletRequestDispatcherRequest(
		HttpServletRequest httpServletRequest) {
	}

	private final MockHttpServletRequest _mockHttpServletRequest;

}