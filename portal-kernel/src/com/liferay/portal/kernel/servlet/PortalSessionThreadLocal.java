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

import com.liferay.petra.lang.CentralizedThreadLocal;

import javax.servlet.http.HttpSession;

/**
 * @author Shuyang Zhou
 */
public class PortalSessionThreadLocal {

	public static HttpSession getHttpSession() {
		String sessionId = _sessionId.get();

		if (sessionId == null) {
			return null;
		}

		return PortalSessionContext.get(sessionId);
	}

	public static void setHttpSession(HttpSession httpSession) {
		_sessionId.set(httpSession.getId());
	}

	private static final ThreadLocal<String> _sessionId =
		new CentralizedThreadLocal<>(
			PortalSessionThreadLocal.class + "._sessionId");

}