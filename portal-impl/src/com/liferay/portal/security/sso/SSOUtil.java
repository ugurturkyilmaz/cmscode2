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

package com.liferay.portal.security.sso;

import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;
import com.liferay.portal.kernel.security.sso.SSO;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Raymond Augé
 */
public class SSOUtil {

	public static String getSessionExpirationRedirectURL(
		long companyId, String sessionExpirationRedirectURL) {

		String ssoSessionExpirationRedirectURL =
			_getSessionExpirationRedirectURL(companyId);

		if ((_ssos.size() == 0) ||
			Validator.isNull(ssoSessionExpirationRedirectURL)) {

			return sessionExpirationRedirectURL;
		}

		return ssoSessionExpirationRedirectURL;
	}

	public static String getSignInURL(long companyId, String signInURL) {
		if (_ssos.size() == 0) {
			return null;
		}

		return _getSignInURL(companyId, signInURL);
	}

	public static boolean isLoginRedirectRequired(long companyId) {
		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LOGIN_DIALOG_DISABLED,
				PropsValues.LOGIN_DIALOG_DISABLED)) {

			return true;
		}

		if (_ssos.size() == 0) {
			return false;
		}

		return _isLoginRedirectRequired(companyId);
	}

	public static boolean isRedirectRequired(long companyId) {
		if (_ssos.size() == 0) {
			return false;
		}

		return _isRedirectRequired(companyId);
	}

	public static boolean isSessionRedirectOnExpire(long companyId) {
		boolean sessionRedirectOnExpire =
			PropsValues.SESSION_TIMEOUT_REDIRECT_ON_EXPIRE;

		if ((_ssos.size() == 0) || sessionRedirectOnExpire) {
			return sessionRedirectOnExpire;
		}

		return _isSessionRedirectOnExpire(companyId);
	}

	private static String _getSessionExpirationRedirectURL(long companyId) {
		for (SSO sso : _ssos) {
			String sessionExpirationRedirectURL =
				sso.getSessionExpirationRedirectUrl(companyId);

			if (sessionExpirationRedirectURL != null) {
				return sessionExpirationRedirectURL;
			}
		}

		return null;
	}

	private static String _getSignInURL(
		long companyId, String defaultSignInURL) {

		for (SSO sso : _ssos) {
			String signInURL = sso.getSignInURL(companyId, defaultSignInURL);

			if (signInURL != null) {
				return signInURL;
			}
		}

		return null;
	}

	private static boolean _isLoginRedirectRequired(long companyId) {
		for (SSO sso : _ssos) {
			if (sso.isLoginRedirectRequired(companyId)) {
				return true;
			}
		}

		return false;
	}

	private static boolean _isRedirectRequired(long companyId) {
		for (SSO sso : _ssos) {
			if (sso.isRedirectRequired(companyId)) {
				return true;
			}
		}

		return false;
	}

	private static boolean _isSessionRedirectOnExpire(long companyId) {
		for (SSO sso : _ssos) {
			if (sso.isSessionRedirectOnExpire(companyId)) {
				return true;
			}
		}

		return false;
	}

	private SSOUtil() {
	}

	private static final ServiceTrackerList<SSO> _ssos =
		ServiceTrackerListFactory.open(
			SystemBundleUtil.getBundleContext(), SSO.class);

}