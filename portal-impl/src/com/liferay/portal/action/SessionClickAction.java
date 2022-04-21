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

package com.liferay.portal.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionClicks;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.struts.Action;
import com.liferay.portal.struts.model.ActionForward;
import com.liferay.portal.struts.model.ActionMapping;

import java.util.Enumeration;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class SessionClickAction implements Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		try {
			AuthTokenUtil.checkCSRFToken(
				httpServletRequest, SessionClickAction.class.getName());

			HttpSession httpSession = httpServletRequest.getSession();

			Enumeration<String> enumeration =
				httpServletRequest.getParameterNames();

			boolean useHttpSession = ParamUtil.getBoolean(
				httpServletRequest, "useHttpSession");

			while (enumeration.hasMoreElements()) {
				String name = enumeration.nextElement();

				if (!StringUtil.equals(name, "cmd") &&
					!StringUtil.equals(name, "doAsUserId") &&
					!StringUtil.equals(name, "p_auth")) {

					String value = ParamUtil.getString(
						httpServletRequest, name);

					if (useHttpSession) {
						SessionClicks.put(httpSession, name, value);
					}
					else {
						SessionClicks.put(httpServletRequest, name, value);
					}
				}
			}

			String value = getValue(httpServletRequest);

			if (value != null) {
				String cmd = ParamUtil.getString(
					httpServletRequest, Constants.CMD);

				if (StringUtil.equals(cmd, "get")) {
					httpServletResponse.setContentType(ContentTypes.TEXT_PLAIN);
				}
				else {
					httpServletResponse.setContentType(
						ContentTypes.APPLICATION_JSON);
				}

				ServletOutputStream servletOutputStream =
					httpServletResponse.getOutputStream();

				servletOutputStream.print(value);
			}

			return null;
		}
		catch (Exception exception) {
			PortalUtil.sendError(
				exception, httpServletRequest, httpServletResponse);

			return null;
		}
	}

	protected String getValue(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();

		String cmd = ParamUtil.getString(httpServletRequest, Constants.CMD);

		boolean useHttpSession = ParamUtil.getBoolean(
			httpServletRequest, "useHttpSession");

		if (StringUtil.equals(cmd, "get")) {
			String key = ParamUtil.getString(httpServletRequest, "key");
			String value = StringPool.BLANK;

			if (useHttpSession) {
				value = SessionClicks.get(httpSession, key, cmd);
			}
			else {
				value = SessionClicks.get(httpServletRequest, key, cmd);
			}

			return value;
		}
		else if (StringUtil.equals(cmd, "getAll")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String[] keys = httpServletRequest.getParameterValues("key");

			for (String key : keys) {
				String value = StringPool.BLANK;

				if (useHttpSession) {
					value = SessionClicks.get(httpSession, key, cmd);
				}
				else {
					value = SessionClicks.get(httpServletRequest, key, cmd);
				}

				jsonObject.put(key, value);
			}

			return jsonObject.toString();
		}

		return null;
	}

}