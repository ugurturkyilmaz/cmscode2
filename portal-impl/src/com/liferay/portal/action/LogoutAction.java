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

import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.struts.Action;
import com.liferay.portal.struts.constants.ActionConstants;
import com.liferay.portal.struts.model.ActionForward;
import com.liferay.portal.struts.model.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class LogoutAction implements Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		try {
			AuthenticatedSessionManagerUtil.logout(
				httpServletRequest, httpServletResponse);

			httpServletRequest.setAttribute(WebKeys.LOGOUT, Boolean.TRUE);

			return actionMapping.getActionForward(
				ActionConstants.COMMON_REFERER);
		}
		catch (Exception exception) {
			PortalUtil.sendError(
				exception, httpServletRequest, httpServletResponse);

			return null;
		}
	}

}