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

package com.liferay.util.servlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class SessionParameters {

	public static final String KEY = SessionParameters.class.getName();

	public static final boolean USE_SESSION_PARAMETERS = GetterUtil.getBoolean(
		SystemProperties.get(SessionParameters.class.getName()), true);

	public static String get(
		HttpServletRequest httpServletRequest, String parameter) {

		return get(httpServletRequest.getSession(), parameter);
	}

	public static String get(HttpSession httpSession, String parameter) {
		if (!USE_SESSION_PARAMETERS) {
			return parameter;
		}

		Map<String, String> parameters = _getParameters(httpSession);

		String newParameter = parameters.get(parameter);

		if (newParameter == null) {
			newParameter =
				StringUtil.randomString() + StringPool.UNDERLINE + parameter;

			parameters.put(parameter, newParameter);
		}

		return newParameter;
	}

	public static String get(PortletRequest portletRequest, String parameter) {
		return get(portletRequest.getPortletSession(), parameter);
	}

	public static String get(PortletSession portletSession, String parameter) {
		if (!USE_SESSION_PARAMETERS) {
			return parameter;
		}

		Map<String, String> parameters = _getParameters(portletSession);

		String newParameter = parameters.get(parameter);

		if (newParameter == null) {
			newParameter =
				StringUtil.randomString() + StringPool.UNDERLINE + parameter;

			parameters.put(parameter, newParameter);
		}

		return newParameter;
	}

	private static Map<String, String> _getParameters(HttpSession httpSession) {
		Map<String, String> parameters = null;

		try {
			parameters = (Map<String, String>)httpSession.getAttribute(KEY);

			if (parameters == null) {
				parameters = new HashMap<>();

				httpSession.setAttribute(KEY, parameters);
			}
		}
		catch (IllegalStateException illegalStateException) {
			if (_log.isDebugEnabled()) {
				_log.debug(illegalStateException);
			}

			parameters = new HashMap<>();
		}

		return parameters;
	}

	private static Map<String, String> _getParameters(
		PortletSession portletSession) {

		Map<String, String> parameters = null;

		try {
			parameters = (Map<String, String>)portletSession.getAttribute(KEY);

			if (parameters == null) {
				parameters = new LinkedHashMap<>();

				portletSession.setAttribute(KEY, parameters);
			}
		}
		catch (IllegalStateException illegalStateException) {
			if (_log.isDebugEnabled()) {
				_log.debug(illegalStateException);
			}

			parameters = new LinkedHashMap<>();
		}

		return parameters;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SessionParameters.class);

}