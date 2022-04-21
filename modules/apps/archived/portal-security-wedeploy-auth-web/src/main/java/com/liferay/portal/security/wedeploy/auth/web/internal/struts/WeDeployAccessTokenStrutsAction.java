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

package com.liferay.portal.security.wedeploy.auth.web.internal.struts;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.wedeploy.auth.constants.WeDeployAuthTokenConstants;
import com.liferay.portal.security.wedeploy.auth.exception.NoSuchAppException;
import com.liferay.portal.security.wedeploy.auth.exception.NoSuchTokenException;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthToken;
import com.liferay.portal.security.wedeploy.auth.service.WeDeployAuthTokenLocalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Supritha Sundaram
 */
@Component(
	immediate = true, property = "path=/portal/wedeploy/access_token",
	service = StrutsAction.class
)
public class WeDeployAccessTokenStrutsAction implements StrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);
		httpServletResponse.setHeader(
			HttpHeaders.CACHE_CONTROL,
			HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String redirectURI = ParamUtil.getString(
			httpServletRequest, "redirect_uri");
		String clientId = ParamUtil.getString(httpServletRequest, "client_id");
		String clientSecret = ParamUtil.getString(
			httpServletRequest, "client_secret");
		String authorizationToken = ParamUtil.getString(
			httpServletRequest, "code");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			WeDeployAuthToken.class.getName(), httpServletRequest);

		try {
			WeDeployAuthToken weDeployAuthToken =
				_weDeployAuthTokenLocalService.addAccessWeDeployAuthToken(
					redirectURI, clientId, clientSecret, authorizationToken,
					WeDeployAuthTokenConstants.TYPE_AUTHORIZATION,
					serviceContext);

			jsonObject.put("access_token", weDeployAuthToken.getToken());
		}
		catch (NoSuchAppException noSuchAppException) {
			if (_log.isDebugEnabled()) {
				_log.debug(noSuchAppException);
			}

			jsonObject.put(
				"error_message",
				LanguageUtil.get(
					LocaleUtil.getDefault(),
					"client-id-and-client-secret-do-not-match"));
		}
		catch (NoSuchTokenException noSuchTokenException) {
			if (_log.isDebugEnabled()) {
				_log.debug(noSuchTokenException);
			}

			jsonObject.put(
				"error_message",
				LanguageUtil.get(
					LocaleUtil.getDefault(), "request-token-does-not-match"));
		}
		catch (Exception exception) {
			_log.error(exception);

			jsonObject.put(
				"error_message",
				LanguageUtil.get(
					LocaleUtil.getDefault(),
					"an-error-occurred-while-processing-the-requested-" +
						"resource"));
		}

		ServletResponseUtil.write(httpServletResponse, jsonObject.toString());

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WeDeployAccessTokenStrutsAction.class);

	@Reference
	private WeDeployAuthTokenLocalService _weDeployAuthTokenLocalService;

}