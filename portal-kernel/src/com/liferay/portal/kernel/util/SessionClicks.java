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

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;

import java.util.ConcurrentModificationException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class SessionClicks {

	public static String get(
		HttpServletRequest httpServletRequest, String key,
		String defaultValue) {

		return get(httpServletRequest, _DEFAULT_NAMESPACE, key, defaultValue);
	}

	public static String get(
		HttpServletRequest httpServletRequest, String namespace, String key,
		String defaultValue) {

		try {
			PortalPreferences portalPreferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					httpServletRequest);

			return portalPreferences.getValue(namespace, key, defaultValue);
		}
		catch (Exception exception) {
			_log.error(exception);

			return null;
		}
	}

	public static String get(
		HttpSession httpSession, String key, String defaultValue) {

		return get(httpSession, _DEFAULT_NAMESPACE, key, defaultValue);
	}

	public static String get(
		HttpSession httpSession, String namespace, String key,
		String defaultValue) {

		String sessionKey = StringBundler.concat(
			namespace, StringPool.COLON, key);

		return GetterUtil.getString(
			httpSession.getAttribute(sessionKey), defaultValue);
	}

	public static void put(
		HttpServletRequest httpServletRequest, String key, String value) {

		put(httpServletRequest, _DEFAULT_NAMESPACE, key, value);
	}

	public static void put(
		HttpServletRequest httpServletRequest, String namespace, String key,
		String value) {

		if (!_isValidKeyValue(key, value)) {
			return;
		}

		while (true) {
			try {
				PortalPreferences portalPreferences =
					PortletPreferencesFactoryUtil.getPortalPreferences(
						httpServletRequest);

				int size = portalPreferences.size();

				if (_isValidSize(size, key, value)) {
					portalPreferences.setValue(namespace, key, value);
				}

				break;
			}
			catch (ConcurrentModificationException
						concurrentModificationException) {

				if (_log.isDebugEnabled()) {
					_log.debug(concurrentModificationException);
				}
			}
			catch (Exception exception) {
				_log.error(exception);

				break;
			}
		}
	}

	public static void put(HttpSession httpSession, String key, String value) {
		put(httpSession, _DEFAULT_NAMESPACE, key, value);
	}

	public static void put(
		HttpSession httpSession, String namespace, String key, String value) {

		if (!_isValidKeyValue(key, value)) {
			return;
		}

		Enumeration<String> enumeration = httpSession.getAttributeNames();

		int size = 0;

		while (enumeration.hasMoreElements()) {
			enumeration.nextElement();

			size++;
		}

		if (_isValidSize(size, key, value)) {
			String sessionKey = StringBundler.concat(
				namespace, StringPool.COLON, key);

			httpSession.setAttribute(sessionKey, value);
		}
	}

	private static boolean _isValidKeyValue(String key, String value) {
		if ((key.length() <= _SESSION_CLICKS_MAX_SIZE_TERMS) &&
			(value.length() <= _SESSION_CLICKS_MAX_SIZE_TERMS)) {

			return true;
		}

		if (_log.isWarnEnabled()) {
			_log.warn(
				StringBundler.concat(
					"Session clicks has attempted to exceed the maximum size ",
					"allowed for keys or values with key \"", key,
					"\" and value \"", value, "\""));
		}

		return false;
	}

	private static boolean _isValidSize(int size, String key, String value) {
		if (size < _SESSION_CLICKS_MAX_ALLOWED_VALUES) {
			return true;
		}

		if (_log.isWarnEnabled()) {
			_log.warn(
				StringBundler.concat(
					"Session clicks has attempted to exceed the maximum ",
					"number of allowed values with key \"", key,
					"\" and value \"", value, "\""));
		}

		return false;
	}

	private static final String _DEFAULT_NAMESPACE =
		SessionClicks.class.getName();

	private static final int _SESSION_CLICKS_MAX_ALLOWED_VALUES =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.SESSION_CLICKS_MAX_ALLOWED_VALUES));

	private static final int _SESSION_CLICKS_MAX_SIZE_TERMS =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.SESSION_CLICKS_MAX_SIZE_TERMS));

	private static final Log _log = LogFactoryUtil.getLog(SessionClicks.class);

}