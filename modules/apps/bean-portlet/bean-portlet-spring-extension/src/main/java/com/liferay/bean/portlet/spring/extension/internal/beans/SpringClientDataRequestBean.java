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

package com.liferay.bean.portlet.spring.extension.internal.beans;

import com.liferay.bean.portlet.spring.extension.internal.scope.SpringScopedBeanManager;
import com.liferay.bean.portlet.spring.extension.internal.scope.SpringScopedBeanManagerThreadLocal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.security.Principal;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.annotation.Priority;

import javax.portlet.ClientDataRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderParameters;
import javax.portlet.RenderState;
import javax.portlet.WindowState;
import javax.portlet.filter.ClientDataRequestWrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.Part;

/**
 * @author Neil Griffin
 */
@ManagedBean("clientDataRequest")

// When the developer uses "@Inject ClientDataRequest", Spring must be able to
// disambiguate between ClientDataRequest, ActionRequest, and ResourceRequest.
// This is accomplished with @Priority. However, Spring only knows how to apply
// the @Priority annotation at the class-level for a class that represents a
// single bean. In other words, Spring does not know how to apply the @Priority
// annotation for a class like JSR362SpringBeanProducer that produces multiple
// types of beans via producer methods annotated with @Bean.

@Priority(2)

// In order to support unwrapping, it is necessary for this bean to extend
// ClientDataRequestWrapper. However, ClientDataRequestWrapper is designed in
// such a way that it requires the wrapped instance to be specified via the
// constructor. Since the instance is obtained from a request-based ThreadLocal,
// it is not possible to pass the instance via the constructor. Therefore each
// of the methods of PortletRequestWrapper and ClientDataRequestWrapper are
// overridden in this class.
public class SpringClientDataRequestBean extends ClientDataRequestWrapper {

	public SpringClientDataRequestBean() {

		// The superclass constructor requires a non-null instance or else
		// it will throw IllegalArgumentException.

		super(DummyClientDataRequest.INSTANCE);
	}

	@Override
	public Object getAttribute(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getAttributeNames();
	}

	@Override
	public String getAuthType() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getAuthType();
	}

	@Override
	public String getCharacterEncoding() {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getCharacterEncoding();
	}

	@Override
	public int getContentLength() {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getContentLength();
	}

	@Override
	public long getContentLengthLong() {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getContentLengthLong();
	}

	@Override
	public String getContentType() {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getContentType();
	}

	@Override
	public String getContextPath() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getContextPath();
	}

	@Override
	public Cookie[] getCookies() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getCookies();
	}

	@Override
	public Locale getLocale() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getLocale();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getLocales();
	}

	@Override
	public String getMethod() {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getMethod();
	}

	@Override
	@SuppressWarnings("deprecation")
	public String getParameter(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getParameter(name);
	}

	@Override
	@SuppressWarnings("deprecation")
	public Map<String, String[]> getParameterMap() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getParameterMap();
	}

	@Override
	@SuppressWarnings("deprecation")
	public Enumeration<String> getParameterNames() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getParameterNames();
	}

	@Override
	@SuppressWarnings("deprecation")
	public String[] getParameterValues(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getParameterValues(name);
	}

	@Override
	public Part getPart(String name) throws IOException, PortletException {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getPart(name);
	}

	@Override
	public Collection<Part> getParts() throws IOException, PortletException {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getParts();
	}

	@Override
	public PortalContext getPortalContext() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortalContext();
	}

	@Override
	public PortletContext getPortletContext() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortletContext();
	}

	@Override
	public InputStream getPortletInputStream() throws IOException {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getPortletInputStream();
	}

	@Override
	public PortletMode getPortletMode() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortletMode();
	}

	@Override
	public PortletSession getPortletSession() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortletSession();
	}

	@Override
	public PortletSession getPortletSession(boolean create) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortletSession(create);
	}

	@Override
	public PortletPreferences getPreferences() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPreferences();
	}

	@Override
	@SuppressWarnings("deprecation")
	public Map<String, String[]> getPrivateParameterMap() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPrivateParameterMap();
	}

	@Override
	public Enumeration<String> getProperties(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getProperties(name);
	}

	@Override
	public String getProperty(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getProperty(name);
	}

	@Override
	public Enumeration<String> getPropertyNames() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPropertyNames();
	}

	@Override
	@SuppressWarnings("deprecation")
	public Map<String, String[]> getPublicParameterMap() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPublicParameterMap();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		ClientDataRequest clientDataRequest = getRequest();

		return clientDataRequest.getReader();
	}

	@Override
	public String getRemoteUser() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getRemoteUser();
	}

	@Override
	public RenderParameters getRenderParameters() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getRenderParameters();
	}

	@Override
	public ClientDataRequest getRequest() {
		SpringScopedBeanManager springScopedBeanManager =
			SpringScopedBeanManagerThreadLocal.getCurrentScopedBeanManager();

		return (ClientDataRequest)springScopedBeanManager.getPortletRequest();
	}

	@Override
	public String getRequestedSessionId() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getRequestedSessionId();
	}

	@Override
	public String getResponseContentType() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getResponseContentType();
	}

	@Override
	public Enumeration<String> getResponseContentTypes() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getResponseContentTypes();
	}

	@Override
	public String getScheme() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getScheme();
	}

	@Override
	public String getServerName() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getServerName();
	}

	@Override
	public int getServerPort() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getServerPort();
	}

	@Override
	public String getUserAgent() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getUserAgent();
	}

	@Override
	public Principal getUserPrincipal() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getUserPrincipal();
	}

	@Override
	public String getWindowID() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getWindowID();
	}

	@Override
	public WindowState getWindowState() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getWindowState();
	}

	@Override
	public RenderState getWrapped() {
		return getRequest();
	}

	@Override
	public boolean isPortletModeAllowed(PortletMode portletMode) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isPortletModeAllowed(portletMode);
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isRequestedSessionIdValid();
	}

	@Override
	public boolean isSecure() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isUserInRole(role);
	}

	@Override
	public boolean isWindowStateAllowed(WindowState windowState) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isWindowStateAllowed(windowState);
	}

	@Override
	public void removeAttribute(String name) {
		PortletRequest portletRequest = getRequest();

		portletRequest.removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		PortletRequest portletRequest = getRequest();

		portletRequest.setAttribute(name, value);
	}

	@Override
	public void setCharacterEncoding(String encoding)
		throws UnsupportedEncodingException {

		ClientDataRequest clientDataRequest = getRequest();

		clientDataRequest.setCharacterEncoding(encoding);
	}

	@Override
	public void setRequest(ClientDataRequest clientDataRequest) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setWrapped(RenderState renderState) {
		throw new UnsupportedOperationException();
	}

}