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

package com.liferay.portal.deploy.hot;

import com.liferay.petra.io.StreamUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.deploy.hot.BaseHotDeployListener;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutTemplate;
import com.liferay.portal.kernel.service.LayoutTemplateLocalServiceUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Brian Myunghun Kim
 * @author Ivica Cardic
 */
public class LayoutTemplateHotDeployListener extends BaseHotDeployListener {

	@Override
	public void invokeDeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		try {
			doInvokeDeploy(hotDeployEvent);
		}
		catch (Throwable throwable) {
			throwHotDeployException(
				hotDeployEvent, "Error registering layout templates for ",
				throwable);
		}
	}

	@Override
	public void invokeUndeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		try {
			doInvokeUndeploy(hotDeployEvent);
		}
		catch (Throwable throwable) {
			throwHotDeployException(
				hotDeployEvent, "Error unregistering layout templates for ",
				throwable);
		}
	}

	protected void doInvokeDeploy(HotDeployEvent hotDeployEvent)
		throws Exception {

		ServletContext servletContext = hotDeployEvent.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + servletContextName);
		}

		String[] xmls = {
			StreamUtil.toString(
				servletContext.getResourceAsStream(
					"/WEB-INF/liferay-layout-templates.xml"))
		};

		if (xmls[0] == null) {
			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Registering layout templates for " + servletContextName);
		}

		List<LayoutTemplate> layoutTemplates =
			LayoutTemplateLocalServiceUtil.init(
				servletContextName, servletContext, xmls,
				hotDeployEvent.getPluginPackage());

		_layoutTemplates.put(servletContextName, layoutTemplates);

		servletContext.setAttribute(
			WebKeys.PLUGIN_LAYOUT_TEMPLATES, layoutTemplates);

		if (_log.isInfoEnabled()) {
			if (layoutTemplates.size() == 1) {
				_log.info(
					"1 layout template for " + servletContextName +
						" is available for use");
			}
			else {
				_log.info(
					StringBundler.concat(
						layoutTemplates.size(), " layout templates for ",
						servletContextName, " are available for use"));
			}
		}
	}

	protected void doInvokeUndeploy(HotDeployEvent hotDeployEvent)
		throws Exception {

		ServletContext servletContext = hotDeployEvent.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		if (_log.isDebugEnabled()) {
			_log.debug("Invoking undeploy for " + servletContextName);
		}

		List<LayoutTemplate> layoutTemplates = _layoutTemplates.remove(
			servletContextName);

		if (layoutTemplates == null) {
			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Unregistering layout templates for " + servletContextName);
		}

		for (LayoutTemplate layoutTemplate : layoutTemplates) {
			try {
				LayoutTemplateLocalServiceUtil.uninstallLayoutTemplate(
					layoutTemplate.getLayoutTemplateId(),
					layoutTemplate.isStandard());
			}
			catch (Exception exception) {
				_log.error(exception);
			}
		}

		if (_log.isInfoEnabled()) {
			if (layoutTemplates.size() == 1) {
				_log.info(
					"1 layout template for " + servletContextName +
						" was unregistered");
			}
			else {
				_log.info(
					StringBundler.concat(
						layoutTemplates.size(), " layout templates for ",
						servletContextName, " were unregistered"));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutTemplateHotDeployListener.class);

	private static final Map<String, List<LayoutTemplate>> _layoutTemplates =
		new HashMap<>();

}