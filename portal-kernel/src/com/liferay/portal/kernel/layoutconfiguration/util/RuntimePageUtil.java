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

package com.liferay.portal.kernel.layoutconfiguration.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.layoutconfiguration.util.xml.RuntimeLogic;
import com.liferay.portal.kernel.model.LayoutTemplate;
import com.liferay.portal.kernel.template.TemplateResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class RuntimePageUtil {

	public static LayoutTemplate getLayoutTemplate(String velocityTemplateId) {
		return _runtimePage.getLayoutTemplate(velocityTemplateId);
	}

	public static StringBundler getProcessedTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String portletId,
			TemplateResource templateResource)
		throws Exception {

		return _runtimePage.getProcessedTemplate(
			httpServletRequest, httpServletResponse, portletId,
			templateResource);
	}

	public static RuntimePage getRuntimePage() {
		return _runtimePage;
	}

	public static void processTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String portletId,
			TemplateResource templateResource)
		throws Exception {

		_runtimePage.processTemplate(
			httpServletRequest, httpServletResponse, portletId,
			templateResource);
	}

	public static void processTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String portletId,
			TemplateResource templateResource, String langType)
		throws Exception {

		_runtimePage.processTemplate(
			httpServletRequest, httpServletResponse, portletId,
			templateResource, langType);
	}

	public static void processTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			TemplateResource templateResource)
		throws Exception {

		_runtimePage.processTemplate(
			httpServletRequest, httpServletResponse, templateResource);
	}

	public static void processTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			TemplateResource templateResource, String langType)
		throws Exception {

		_runtimePage.processTemplate(
			httpServletRequest, httpServletResponse, templateResource,
			langType);
	}

	public static String processXML(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String content)
		throws Exception {

		return _runtimePage.processXML(
			httpServletRequest, httpServletResponse, content);
	}

	public static String processXML(
			HttpServletRequest httpServletRequest, String content,
			RuntimeLogic runtimeLogic)
		throws Exception {

		return _runtimePage.processXML(
			httpServletRequest, content, runtimeLogic);
	}

	public void setRuntimePage(RuntimePage runtimePage) {
		_runtimePage = runtimePage;
	}

	private static RuntimePage _runtimePage;

}