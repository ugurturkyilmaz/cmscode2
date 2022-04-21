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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PortalImpl;

import java.security.Principal;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortalContext;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderParameters;
import javax.portlet.WindowState;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

/**
 * @author Alicia García García
 */
public class MultiSessionErrorsTest {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws Exception {
		PortalUtil portalUtil = new PortalUtil();

		portalUtil.setPortal(new PortalImpl());
	}

	@Test
	public void testClearByHttpServletRequest() {
		String key = RandomTestUtil.randomString();

		SessionErrors.add(_mockHttpServletRequest, key);

		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);

		Assert.assertFalse(MultiSessionErrors.isEmpty(portletRequest));

		MultiSessionErrors.clear(portletRequest);

		Assert.assertTrue(MultiSessionErrors.isEmpty(portletRequest));
	}

	@Test
	public void testClearByPortletRequest() {
		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);
		String key = RandomTestUtil.randomString();

		SessionErrors.add(portletRequest, key);

		Assert.assertFalse(MultiSessionErrors.isEmpty(portletRequest));

		MultiSessionErrors.clear(portletRequest);

		Assert.assertTrue(MultiSessionErrors.isEmpty(portletRequest));
	}

	@Test
	public void testContainsByHttpServletRequest() {
		String key = RandomTestUtil.randomString();

		SessionErrors.add(_mockHttpServletRequest, key);

		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);

		Assert.assertTrue(MultiSessionErrors.contains(portletRequest, key));
	}

	@Test
	public void testContainsByPortletRequest() {
		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);
		String key = RandomTestUtil.randomString();

		SessionErrors.add(portletRequest, key);

		Assert.assertTrue(MultiSessionErrors.contains(portletRequest, key));
	}

	@Test
	public void testGet() {
		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);

		Assert.assertNull(
			MultiSessionErrors.get(
				portletRequest, RandomTestUtil.randomString()));
	}

	@Test
	public void testGetByHttpServletRequest() {
		String key = RandomTestUtil.randomString();

		SessionErrors.add(_mockHttpServletRequest, key);

		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);

		Assert.assertEquals(key, MultiSessionErrors.get(portletRequest, key));
	}

	@Test
	public void testGetByPortletRequest() {
		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);
		String key = RandomTestUtil.randomString();

		SessionErrors.add(portletRequest, key);

		Assert.assertEquals(key, MultiSessionErrors.get(portletRequest, key));
	}

	@Test
	public void testIsEmpty() {
		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);

		Assert.assertTrue(MultiSessionErrors.isEmpty(portletRequest));
	}

	@Test
	public void testIsEmptyByHttpServletRequest() {
		SessionErrors.add(
			_mockHttpServletRequest, RandomTestUtil.randomString());

		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);

		Assert.assertFalse(MultiSessionErrors.isEmpty(portletRequest));
	}

	@Test
	public void testIsEmptyByPortletRequest() {
		PortletRequest portletRequest = new MockLiferayPortletRequest(
			_mockHttpServletRequest);

		SessionErrors.add(portletRequest, RandomTestUtil.randomString());

		Assert.assertFalse(MultiSessionErrors.isEmpty(portletRequest));
	}

	private final MockHttpServletRequest _mockHttpServletRequest =
		new MockHttpServletRequest() {
			{
				setSession(new MockHttpSession());
			}
		};

	private class MockLiferayPortletRequest implements LiferayPortletRequest {

		public MockLiferayPortletRequest(
			HttpServletRequest httpServletRequest) {

			_httpServletRequest = httpServletRequest;
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
		public Object getAttribute(String name) {
			return null;
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			return null;
		}

		@Override
		public String getAuthType() {
			return null;
		}

		@Override
		public String getContextPath() {
			return null;
		}

		@Override
		public Cookie[] getCookies() {
			return new Cookie[0];
		}

		@Override
		public HttpServletRequest getHttpServletRequest() {
			return _httpServletRequest;
		}

		@Override
		public String getLifecycle() {
			return null;
		}

		@Override
		public Locale getLocale() {
			return null;
		}

		@Override
		public Enumeration<Locale> getLocales() {
			return null;
		}

		@Override
		public HttpServletRequest getOriginalHttpServletRequest() {
			return _httpServletRequest;
		}

		@Override
		public String getParameter(String name) {
			return null;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			return null;
		}

		@Override
		public Enumeration<String> getParameterNames() {
			return null;
		}

		@Override
		public String[] getParameterValues(String name) {
			return new String[0];
		}

		@Override
		public long getPlid() {
			return 0;
		}

		@Override
		public PortalContext getPortalContext() {
			return null;
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
		public PortletMode getPortletMode() {
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
		public PortletSession getPortletSession() {
			return null;
		}

		@Override
		public PortletSession getPortletSession(boolean create) {
			return null;
		}

		@Override
		public PortletPreferences getPreferences() {
			return null;
		}

		@Override
		public Map<String, String[]> getPrivateParameterMap() {
			return null;
		}

		@Override
		public Enumeration<String> getProperties(String name) {
			return null;
		}

		@Override
		public String getProperty(String name) {
			return null;
		}

		@Override
		public Enumeration<String> getPropertyNames() {
			return null;
		}

		@Override
		public Map<String, String[]> getPublicParameterMap() {
			return null;
		}

		@Override
		public String getRemoteUser() {
			return null;
		}

		@Override
		public RenderParameters getRenderParameters() {
			return null;
		}

		@Override
		public String getRequestedSessionId() {
			return null;
		}

		@Override
		public String getResponseContentType() {
			return null;
		}

		@Override
		public Enumeration<String> getResponseContentTypes() {
			return null;
		}

		@Override
		public String getScheme() {
			return null;
		}

		@Override
		public String getServerName() {
			return null;
		}

		@Override
		public int getServerPort() {
			return 0;
		}

		@Override
		public String getUserAgent() {
			return null;
		}

		@Override
		public Principal getUserPrincipal() {
			return null;
		}

		@Override
		public String getWindowID() {
			return null;
		}

		@Override
		public WindowState getWindowState() {
			return null;
		}

		@Override
		public void invalidateSession() {
		}

		@Override
		public boolean isPortletModeAllowed(PortletMode portletMode) {
			return false;
		}

		@Override
		public boolean isRequestedSessionIdValid() {
			return false;
		}

		@Override
		public boolean isSecure() {
			return false;
		}

		@Override
		public boolean isUserInRole(String role) {
			return false;
		}

		@Override
		public boolean isWindowStateAllowed(WindowState windowState) {
			return false;
		}

		@Override
		public void removeAttribute(String name) {
		}

		@Override
		public void setAttribute(String name, Object value) {
		}

		@Override
		public void setPortletRequestDispatcherRequest(
			HttpServletRequest httpServletRequest) {
		}

		private final HttpServletRequest _httpServletRequest;

	}

}