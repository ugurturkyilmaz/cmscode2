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

package com.liferay.bean.portlet.spring.extension.internal.mvc;

import com.liferay.bean.portlet.extension.ViewRenderer;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.ManagedBean;

import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ParamError;
import javax.mvc.engine.ViewEngine;
import javax.mvc.engine.ViewEngineException;

import javax.portlet.MimeResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import javax.ws.rs.core.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author Neil Griffin
 */
@ManagedBean("viewRenderer")
public class ViewRendererMVCImpl
	implements ApplicationContextAware, ViewRenderer {

	@Override
	public void render(
			MimeResponse mimeResponse, PortletConfig portletConfig,
			PortletRequest portletRequest)
		throws PortletException {

		Map<String, Object> modelMap = _models.asMap();

		for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
			portletRequest.setAttribute(entry.getKey(), entry.getValue());
		}

		String viewName = (String)portletRequest.getAttribute(VIEW_NAME);

		if (viewName == null) {
			@SuppressWarnings("deprecation")
			String redirectedView = portletRequest.getParameter(
				REDIRECTED_VIEW);

			if (redirectedView != null) {
				PortletSession portletSession =
					portletRequest.getPortletSession(true);

				viewName = (String)portletSession.getAttribute(VIEW_NAME);

				if (viewName != null) {
					portletSession.removeAttribute(VIEW_NAME);
					portletRequest.setAttribute(VIEW_NAME, viewName);
				}
			}
		}

		if (viewName != null) {
			if (!viewName.contains(".")) {
				String defaultViewExtension =
					(String)_configuration.getProperty(
						ConfigurationImpl.DEFAULT_VIEW_EXTENSION);

				viewName = StringBundler.concat(
					viewName, ".", defaultViewExtension);
			}

			Map<String, ViewEngine> beansOfType =
				_applicationContext.getBeansOfType(ViewEngine.class);

			List<ViewEngine> viewEngines = new ArrayList<>(
				beansOfType.values());

			Collections.sort(viewEngines, new ViewEnginePriorityComparator());

			ViewEngine supportingViewEngine = null;

			for (ViewEngine viewEngine : viewEngines) {
				if (viewEngine.supports(viewName)) {
					supportingViewEngine = viewEngine;

					break;
				}
			}

			if (supportingViewEngine == null) {
				throw new PortletException(
					new ViewEngineException(
						"No ViewEngine found that supports " + viewName));
			}

			try {
				_applicationEventPublisher.publishEvent(
					new BeforeProcessViewEventImpl(
						this, viewName, supportingViewEngine.getClass()));

				supportingViewEngine.processView(
					new ViewEngineContextImpl(
						_configuration, mimeResponse, _models, portletRequest));

				_applicationEventPublisher.publishEvent(
					new AfterProcessViewEventImpl(
						this, viewName, supportingViewEngine.getClass()));
			}
			catch (ViewEngineException viewEngineException) {
				throw new PortletException(viewEngineException);
			}
		}

		MutableBindingResult mutableBindingResult =
			(MutableBindingResult)_bindingResult;

		if ((mutableBindingResult != null) &&
			!mutableBindingResult.isConsulted()) {

			Set<ParamError> allErrors = mutableBindingResult.getAllErrors();

			for (ParamError paramError : allErrors) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						StringBundler.concat(
							"A BindingResult error was not processed for ",
							paramError.getParamName(), ": ",
							paramError.getMessage()));
				}
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		_applicationContext = applicationContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewRendererMVCImpl.class);

	private ApplicationContext _applicationContext;

	@Autowired
	private ApplicationEventPublisher _applicationEventPublisher;

	@Autowired
	private BindingResult _bindingResult;

	@Autowired
	private Configuration _configuration;

	@Autowired
	private Models _models;

	private static class ViewEnginePriorityComparator
		extends DescendingPriorityComparator<ViewEngine> {

		private ViewEnginePriorityComparator() {

			// The Javadoc for javax.mvc.engine.ViewEngine states "View engines
			// can be decorated with javax.annotation.Priority to indicate their
			// priority; otherwise the priority is assumed to be
			// ViewEngine.PRIORITY_APPLICATION."

			super(ViewEngine.PRIORITY_APPLICATION);
		}

	}

}