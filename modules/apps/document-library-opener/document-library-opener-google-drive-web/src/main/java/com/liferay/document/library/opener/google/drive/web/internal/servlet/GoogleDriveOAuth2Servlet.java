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

package com.liferay.document.library.opener.google.drive.web.internal.servlet;

import com.google.api.client.auth.oauth2.TokenResponseException;

import com.liferay.document.library.opener.google.drive.web.internal.DLOpenerGoogleDriveManager;
import com.liferay.document.library.opener.google.drive.web.internal.constants.DLOpenerGoogleDriveWebConstants;
import com.liferay.document.library.opener.google.drive.web.internal.oauth.OAuth2StateUtil;
import com.liferay.document.library.opener.oauth.OAuth2State;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Optional;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"osgi.http.whiteboard.servlet.name=com.liferay.document.library.opener.google.drive.web.internal.servlet.GoogleDriveOAuth2Servlet",
		"osgi.http.whiteboard.servlet.pattern=" + DLOpenerGoogleDriveWebConstants.GOOGLE_DRIVE_SERVLET_PATH,
		"servlet.init.httpMethods=GET,POST"
	},
	service = Servlet.class
)
public class GoogleDriveOAuth2Servlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		Optional<OAuth2State> oAuth2StateOptional =
			OAuth2StateUtil.getOAuth2StateOptional(
				_portal.getOriginalServletRequest(httpServletRequest));

		OAuth2State oAuth2State = oAuth2StateOptional.orElseThrow(
			() -> new IllegalStateException(
				"Authorization oAuth2State not initialized"));

		if (!OAuth2StateUtil.isValid(oAuth2State, httpServletRequest)) {
			OAuth2StateUtil.cleanUp(httpServletRequest);

			httpServletResponse.sendRedirect(oAuth2State.getFailureURL());
		}
		else {
			_requestAuthorizationToken(
				httpServletRequest, httpServletResponse, oAuth2State);
		}
	}

	@Override
	protected void doPost(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		doGet(httpServletRequest, httpServletResponse);
	}

	private void _requestAuthorizationToken(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, OAuth2State oAuth2State)
		throws IOException {

		String code = ParamUtil.getString(httpServletRequest, "code");

		if (Validator.isNull(code)) {
			OAuth2StateUtil.cleanUp(httpServletRequest);
			httpServletResponse.sendRedirect(oAuth2State.getFailureURL());
		}
		else {
			try {
				_dlOpenerGoogleDriveManager.requestAuthorizationToken(
					_portal.getCompanyId(httpServletRequest),
					oAuth2State.getUserId(), code,
					OAuth2StateUtil.getRedirectURI(
						_portal.getPortalURL(httpServletRequest)));

				OAuth2StateUtil.cleanUp(httpServletRequest);

				httpServletResponse.sendRedirect(oAuth2State.getSuccessURL());
			}
			catch (TokenResponseException tokenResponseException) {
				if (_log.isDebugEnabled()) {
					_log.debug(tokenResponseException);
				}

				OAuth2StateUtil.cleanUp(httpServletRequest);

				SessionErrors.add(httpServletRequest, "externalServiceFailed");

				httpServletResponse.sendRedirect(oAuth2State.getFailureURL());
			}
			catch (PortalException portalException) {
				throw new IOException(portalException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GoogleDriveOAuth2Servlet.class);

	private static final long serialVersionUID = 7759897747401129852L;

	@Reference
	private DLOpenerGoogleDriveManager _dlOpenerGoogleDriveManager;

	@Reference
	private Portal _portal;

}