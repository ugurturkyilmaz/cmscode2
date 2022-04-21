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

package com.liferay.bean.portlet.cdi.extension.internal.scope;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.annotation.Annotation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Typed;

import javax.inject.Named;

import javax.portlet.ActionParameters;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ClientDataRequest;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.HeaderRequest;
import javax.portlet.HeaderResponse;
import javax.portlet.MimeResponse;
import javax.portlet.MutableRenderParameters;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderParameters;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceParameters;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.StateAwareResponse;
import javax.portlet.WindowState;
import javax.portlet.annotations.ContextPath;
import javax.portlet.annotations.Namespace;
import javax.portlet.annotations.PortletName;
import javax.portlet.annotations.PortletRequestScoped;
import javax.portlet.annotations.WindowId;

import javax.servlet.http.Cookie;

/**
 * @author Neil Griffin
 */
public class JSR362CDIBeanProducer {

	@Named("actionParams")
	@PortletRequestScoped
	@Produces
	@Typed(ActionParameters.class)
	public ActionParameters getActionParameters() {
		ActionRequest actionRequest = getActionRequest();

		if (actionRequest == null) {
			return null;
		}

		return actionRequest.getActionParameters();
	}

	@Named("actionRequest")
	@PortletRequestScoped
	@Produces
	@Typed(ActionRequest.class)
	public ActionRequest getActionRequest() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest instanceof ActionRequest) {
			return (ActionRequest)portletRequest;
		}

		return null;
	}

	@Named("actionResponse")
	@PortletRequestScoped
	@Produces
	@Typed(ActionResponse.class)
	public ActionResponse getActionResponse() {
		PortletResponse portletResponse = getPortletResponse();

		if (portletResponse instanceof ActionResponse) {
			return (ActionResponse)portletResponse;
		}

		return null;
	}

	@Named("clientDataRequest")
	@PortletRequestScoped
	@Produces
	@Typed(ClientDataRequest.class)
	public ClientDataRequest getClientDataRequest() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest instanceof ClientDataRequest) {
			return (ClientDataRequest)portletRequest;
		}

		return null;
	}

	@ContextPath
	@Dependent
	@Named("contextPath")
	@Produces
	public String getContextPath() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			_log.error(
				new IllegalStateException(
					_getDependentStringErrorMessage(ContextPath.class)));

			return null;
		}

		return portletRequest.getContextPath();
	}

	@Named("cookies")
	@PortletRequestScoped
	@Produces
	public List<Cookie> getCookies() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			return null;
		}

		Cookie[] cookies = portletRequest.getCookies();

		if (cookies == null) {
			return null;
		}

		return Arrays.asList(cookies);
	}

	@Named("eventRequest")
	@PortletRequestScoped
	@Produces
	@Typed(EventRequest.class)
	public EventRequest getEventRequest() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest instanceof EventRequest) {
			return (EventRequest)portletRequest;
		}

		return null;
	}

	@Named("eventResponse")
	@PortletRequestScoped
	@Produces
	@Typed(EventResponse.class)
	public EventResponse getEventResponse() {
		PortletResponse portletResponse = getPortletResponse();

		if (portletResponse instanceof EventResponse) {
			return (EventResponse)portletResponse;
		}

		return null;
	}

	@Named("headerRequest")
	@PortletRequestScoped
	@Produces
	@Typed(HeaderRequest.class)
	public HeaderRequest getHeaderRequest() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest instanceof HeaderRequest) {
			return (HeaderRequest)portletRequest;
		}

		return null;
	}

	@Named("headerResponse")
	@PortletRequestScoped
	@Produces
	@Typed(HeaderResponse.class)
	public HeaderResponse getHeaderResponse() {
		PortletResponse portletResponse = getPortletResponse();

		if (portletResponse instanceof HeaderResponse) {
			return (HeaderResponse)portletResponse;
		}

		return null;
	}

	@Named("locales")
	@PortletRequestScoped
	@Produces
	public List<Locale> getLocales() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			return null;
		}

		return Collections.list(portletRequest.getLocales());
	}

	@Named("mimeResponse")
	@PortletRequestScoped
	@Produces
	@Typed(MimeResponse.class)
	public MimeResponse getMimeResponse() {
		PortletResponse portletResponse = getPortletResponse();

		if (portletResponse instanceof MimeResponse) {
			return (MimeResponse)portletResponse;
		}

		return null;
	}

	@Named("mutableRenderParams")
	@PortletRequestScoped
	@Produces
	@Typed(MutableRenderParameters.class)
	public MutableRenderParameters getMutableRenderParameters() {
		StateAwareResponse stateAwareResponse = getStateAwareResponse();

		if (stateAwareResponse == null) {
			return null;
		}

		return stateAwareResponse.getRenderParameters();
	}

	@Dependent
	@Named("namespace")
	@Namespace
	@Produces
	public String getNamespace() {
		PortletResponse portletResponse = getPortletResponse();

		if (portletResponse == null) {
			_log.error(
				new IllegalStateException(
					_getDependentStringErrorMessage(Namespace.class)));

			return null;
		}

		return portletResponse.getNamespace();
	}

	@Named("portletConfig")
	@PortletRequestScoped
	@Produces
	public PortletConfig getPortletConfig() {
		ScopedBeanManager scopedBeanManager =
			ScopedBeanManagerThreadLocal.getCurrentScopedBeanManager();

		if (scopedBeanManager == null) {
			return null;
		}

		return scopedBeanManager.getPortletConfig();
	}

	@Named("portletContext")
	@PortletRequestScoped
	@Produces
	public PortletContext getPortletContext() {
		PortletConfig portletConfig = getPortletConfig();

		if (portletConfig == null) {
			return null;
		}

		return portletConfig.getPortletContext();
	}

	@Named("portletMode")
	@PortletRequestScoped
	@Produces
	public PortletMode getPortletMode() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			return null;
		}

		return portletRequest.getPortletMode();
	}

	@Dependent
	@Named("portletName")
	@PortletName
	@Produces
	public String getPortletName() {
		PortletConfig portletConfig = getPortletConfig();

		if (portletConfig == null) {
			_log.error(
				new IllegalStateException(
					_getDependentStringErrorMessage(PortletName.class)));

			return null;
		}

		return portletConfig.getPortletName();
	}

	@Named("portletPreferences")
	@PortletRequestScoped
	@Produces
	public PortletPreferences getPortletPreferences() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			return null;
		}

		return portletRequest.getPreferences();
	}

	@Named("portletRequest")
	@PortletRequestScoped
	@Produces
	public PortletRequest getPortletRequest() {
		ScopedBeanManager scopedBeanManager =
			ScopedBeanManagerThreadLocal.getCurrentScopedBeanManager();

		if (scopedBeanManager == null) {
			return null;
		}

		return scopedBeanManager.getPortletRequest();
	}

	@Named("portletResponse")
	@PortletRequestScoped
	@Produces
	public PortletResponse getPortletResponse() {
		ScopedBeanManager scopedBeanManager =
			ScopedBeanManagerThreadLocal.getCurrentScopedBeanManager();

		if (scopedBeanManager == null) {
			return null;
		}

		return scopedBeanManager.getPortletResponse();
	}

	@Named("portletSession")
	@PortletRequestScoped
	@Produces
	public PortletSession getPortletSession() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			return null;
		}

		return portletRequest.getPortletSession();
	}

	@Named("renderParams")
	@PortletRequestScoped
	@Produces
	@Typed(RenderParameters.class)
	public RenderParameters getRenderParameters() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			return null;
		}

		return portletRequest.getRenderParameters();
	}

	@Named("renderRequest")
	@PortletRequestScoped
	@Produces
	@Typed(RenderRequest.class)
	public RenderRequest getRenderRequest() {
		PortletRequest portletRequest = getPortletRequest();

		if ((portletRequest instanceof RenderRequest) &&
			!(portletRequest instanceof HeaderRequest)) {

			return (RenderRequest)portletRequest;
		}

		return null;
	}

	@Named("renderResponse")
	@PortletRequestScoped
	@Produces
	@Typed(RenderResponse.class)
	public RenderResponse getRenderResponse() {
		PortletResponse portletResponse = getPortletResponse();

		if (portletResponse instanceof RenderResponse) {
			return (RenderResponse)portletResponse;
		}

		return null;
	}

	@Named("resourceParams")
	@PortletRequestScoped
	@Produces
	@Typed(ResourceParameters.class)
	public ResourceParameters getResourceParameters() {
		ResourceRequest resourceRequest = getResourceRequest();

		if (resourceRequest == null) {
			return null;
		}

		return resourceRequest.getResourceParameters();
	}

	@Named("resourceRequest")
	@PortletRequestScoped
	@Produces
	@Typed(ResourceRequest.class)
	public ResourceRequest getResourceRequest() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest instanceof ResourceRequest) {
			return (ResourceRequest)portletRequest;
		}

		return null;
	}

	@Named("resourceResponse")
	@PortletRequestScoped
	@Produces
	@Typed(ResourceResponse.class)
	public ResourceResponse getResourceResponse() {
		PortletResponse portletResponse = getPortletResponse();

		if (portletResponse instanceof ResourceResponse) {
			return (ResourceResponse)portletResponse;
		}

		return null;
	}

	@Named("stateAwareResponse")
	@PortletRequestScoped
	@Produces
	@Typed(StateAwareResponse.class)
	public StateAwareResponse getStateAwareResponse() {
		PortletResponse portletResponse = getPortletResponse();

		if (portletResponse instanceof StateAwareResponse) {
			return (StateAwareResponse)portletResponse;
		}

		return null;
	}

	@Dependent
	@Named("windowId")
	@Produces
	@WindowId
	public String getWindowID() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			_log.error(
				new IllegalStateException(
					_getDependentStringErrorMessage(WindowId.class)));

			return null;
		}

		return portletRequest.getWindowID();
	}

	@Named("windowState")
	@PortletRequestScoped
	@Produces
	@Typed(WindowState.class)
	public WindowState getWindowState() {
		PortletRequest portletRequest = getPortletRequest();

		if (portletRequest == null) {
			return null;
		}

		return portletRequest.getWindowState();
	}

	private String _getDependentStringErrorMessage(
		Class<? extends Annotation> annotationClass) {

		return StringBundler.concat(
			"Unable to @Inject ", annotationClass, " into field because it is ",
			"a @Dependent String that can only be injected during a request. ",
			"Annotate the parent class with @PortletRequestScoped instead of ",
			"@ApplicationScoped.");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JSR362CDIBeanProducer.class);

}