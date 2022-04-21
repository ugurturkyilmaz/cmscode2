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

package com.liferay.petra.log4j;

import com.liferay.petra.io.StreamUtil;
import com.liferay.petra.log4j.internal.Log4jConfigUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactory;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @author Brian Wing Shun Chan
 * @author Tomas Polesovsky
 */
public class Log4JUtil {

	public static void configureLog4J(ClassLoader classLoader) {
		configureLog4J(classLoader.getResource("META-INF/portal-log4j.xml"));

		try {
			Enumeration<URL> enumeration = classLoader.getResources(
				"META-INF/portal-log4j-ext.xml");

			while (enumeration.hasMoreElements()) {
				configureLog4J(enumeration.nextElement());
			}
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to load portal-log4j-ext.xml", ioException);
			}
		}
	}

	public static void configureLog4J(URL url) {
		if (url == null) {
			return;
		}

		String urlContent = null;

		try (InputStream inputStream = url.openStream()) {
			urlContent = StreamUtil.toString(inputStream, StringPool.UTF8);
		}
		catch (Exception exception) {
			_log.error(exception);

			return;
		}

		urlContent = StringUtil.replace(
			urlContent, "@liferay.home@", _getLiferayHome());

		Map<String, String> priorities = null;

		if (ServerDetector.getServerId() != null) {
			priorities = Log4jConfigUtil.configureLog4J(urlContent);
		}
		else {
			priorities = Log4jConfigUtil.configureLog4J(
				urlContent, "TEXT_FILE", "XML_FILE");
		}

		for (Map.Entry<String, String> entry : priorities.entrySet()) {
			Logger jdkLogger = Logger.getLogger(entry.getKey());

			jdkLogger.setLevel(Log4jConfigUtil.getJDKLevel(entry.getValue()));
		}
	}

	public static Map<String, String> getCustomLogSettings() {
		return new HashMap<>(_customLogSettings);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
	 */
	@Deprecated
	public static String getOriginalLevel(String className) {
		Map<String, String> priorities = Log4jConfigUtil.getPriorities();

		String logLevelString = priorities.get(className);

		if (Validator.isNull(logLevelString)) {
			return "ALL";
		}

		return logLevelString;
	}

	public static Map<String, String> getPriorities() {
		return Log4jConfigUtil.getPriorities();
	}

	public static void initLog4J(
		String serverId, String liferayHome, ClassLoader classLoader,
		LogFactory logFactory, Map<String, String> customLogSettings) {

		System.setProperty(
			ServerDetector.SYSTEM_PROPERTY_KEY_SERVER_DETECTOR_SERVER_ID,
			serverId);

		_liferayHome = _escapeXMLAttribute(liferayHome);

		configureLog4J(classLoader);

		try {
			LogFactoryUtil.setLogFactory(logFactory);
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		for (Map.Entry<String, String> entry : customLogSettings.entrySet()) {
			setLevel(entry.getKey(), entry.getValue(), false);
		}
	}

	public static void setLevel(String name, String priority, boolean custom) {
		Log4jConfigUtil.setLevel(name, priority);

		Logger jdkLogger = Logger.getLogger(name);

		jdkLogger.setLevel(Log4jConfigUtil.getJDKLevel(priority));

		if (custom) {
			_customLogSettings.put(name, priority);
		}
	}

	public static void shutdownLog4J() {
		Log4jConfigUtil.shutdownLog4J();
	}

	private static String _escapeXMLAttribute(String s) {
		return StringUtil.replace(
			s,
			new char[] {
				CharPool.AMPERSAND, CharPool.APOSTROPHE, CharPool.LESS_THAN,
				CharPool.QUOTE
			},
			new String[] {"&amp;", "&apos;", "&lt;", "&quot;"});
	}

	private static String _getLiferayHome() {
		if (_liferayHome == null) {
			_liferayHome = _escapeXMLAttribute(
				PropsUtil.get(PropsKeys.LIFERAY_HOME));
		}

		return _liferayHome;
	}

	private static final Log _log = LogFactoryUtil.getLog(Log4JUtil.class);

	private static final Map<String, String> _customLogSettings =
		new ConcurrentHashMap<>();
	private static String _liferayHome;

}