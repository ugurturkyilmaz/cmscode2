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

package com.liferay.sync.security.internal.auth.verifier;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.http.HttpAuthManagerUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthorizationHeader;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.jsontoken.Checker;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Signer;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;

import org.joda.time.Instant;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
@Component(
	immediate = true,
	property = "auth.verifier.SyncAuthVerifier.urls.includes=/api/jsonws/sync.syncdevice/*,/api/jsonws/sync.syncdlobject/*",
	service = AuthVerifier.class
)
public class SyncAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return SyncAuthVerifier.class.getSimpleName();
	}

	public String getUserId(String tokenString) {
		try {
			JsonTokenParser jsonTokenParser = _getJsonTokenParser();

			JsonToken jsonToken = jsonTokenParser.verifyAndDeserialize(
				tokenString);

			JsonPrimitive userIdJsonPrimitive = jsonToken.getParamAsPrimitive(
				"userId");

			if (userIdJsonPrimitive == null) {
				return null;
			}

			long userId = userIdJsonPrimitive.getAsLong();

			User user = _userLocalService.fetchUser(userId);

			Date passwordModifiedDate = user.getPasswordModifiedDate();

			if (passwordModifiedDate != null) {
				Instant instant = jsonToken.getIssuedAt();

				if (instant.isBefore(passwordModifiedDate.getTime())) {
					return null;
				}
			}

			return String.valueOf(userId);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		HttpServletRequest httpServletRequest =
			accessControlContext.getRequest();

		String uri = (String)httpServletRequest.getAttribute(
			WebKeys.INVOKER_FILTER_URI);

		if (uri.startsWith("/download/")) {
			String contextPath = httpServletRequest.getContextPath();

			if (!contextPath.equals("/o/sync")) {
				return authVerifierResult;
			}
		}

		String token = httpServletRequest.getHeader(_TOKEN_HEADER);

		if (Validator.isNotNull(token)) {
			String userIdString = getUserId(token);

			if (userIdString != null) {
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(Long.valueOf(userIdString));

				return authVerifierResult;
			}
		}

		HttpAuthorizationHeader httpAuthorizationHeader =
			HttpAuthManagerUtil.parse(httpServletRequest);

		if (httpAuthorizationHeader == null) {

			// SYNC-1463

			Map<String, Object> settings = accessControlContext.getSettings();

			settings.remove("basic_auth");

			return authVerifierResult;
		}

		if (!StringUtil.equalsIgnoreCase(
				httpAuthorizationHeader.getScheme(),
				HttpAuthorizationHeader.SCHEME_BASIC)) {

			return authVerifierResult;
		}

		try {
			long userId = HttpAuthManagerUtil.getBasicUserId(
				httpServletRequest);

			if (userId > 0) {
				token = _createToken(userId);

				if (token != null) {
					HttpServletResponse httpServletResponse =
						accessControlContext.getResponse();

					httpServletResponse.addHeader(_TOKEN_HEADER, token);
				}
			}
			else {
				userId = _userLocalService.getDefaultUserId(
					_portal.getCompanyId(httpServletRequest));
			}

			authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
			authVerifierResult.setUserId(userId);

			return authVerifierResult;
		}
		catch (Exception exception) {
			throw new AuthException(exception);
		}
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private String _createToken(long userId) {
		Signer signer = null;

		try {
			signer = _getSigner();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}

		JsonToken jsonToken = new JsonToken(signer);

		Instant instant = new Instant();

		jsonToken.setExpiration(instant.plus(_EXPIRATION));
		jsonToken.setIssuedAt(instant);

		JsonObject payloadJsonObject = jsonToken.getPayloadAsJsonObject();

		payloadJsonObject.addProperty("userId", userId);

		try {
			return jsonToken.serializeAndSign();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
	}

	private JsonTokenParser _getJsonTokenParser() throws Exception {
		if (_jsonTokenParser != null) {
			return _jsonTokenParser;
		}

		final Verifier verifier = new HmacSHA256Verifier(_SECRET.getBytes());

		VerifierProvider verifierProvider = new VerifierProvider() {

			@Override
			public List<Verifier> findVerifier(String signerId, String keyId) {
				return Lists.newArrayList(verifier);
			}

		};

		VerifierProviders verifyProviders = new VerifierProviders();

		verifyProviders.setVerifierProvider(
			SignatureAlgorithm.HS256, verifierProvider);

		Checker checker = new Checker() {

			@Override
			public void check(JsonObject jsonObject) {
			}

		};

		_jsonTokenParser = new JsonTokenParser(verifyProviders, checker);

		return _jsonTokenParser;
	}

	private Signer _getSigner() {
		if (_signer != null) {
			return _signer;
		}

		try {
			_signer = new HmacSHA256Signer(null, null, _SECRET.getBytes());

			return _signer;
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
	}

	private static final long _EXPIRATION = 3600000;

	private static final String _SECRET = PwdGenerator.getPassword();

	private static final String _TOKEN_HEADER = "Sync-JWT";

	private static final Log _log = LogFactoryUtil.getLog(
		SyncAuthVerifier.class);

	private static JsonTokenParser _jsonTokenParser;
	private static Signer _signer;

	@Reference
	private Portal _portal;

	private UserLocalService _userLocalService;

}