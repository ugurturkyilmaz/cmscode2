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

package com.liferay.source.formatter.check;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Properties;

/**
 * @author Peter Shin
 * @author Hugo Huijser
 */
public class PropertiesLanguageKeysCheck extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		if (!fileName.endsWith("/content/Language.properties")) {
			return content;
		}

		StringBundler sb = new StringBundler();

		Properties portalLanguageProperties = _getPortalLanguageProperties(
			absolutePath);

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new UnsyncStringReader(content))) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				String[] array = line.split("=", 2);

				if (array.length < 2) {
					sb.append(line);
					sb.append("\n");

					continue;
				}

				String key = array[0];
				String value = array[1];

				if ((portalLanguageProperties != null) &&
					!absolutePath.contains("/modules/dxp/apps/") &&
					!absolutePath.contains("/modules/private/apps/")) {

					String portalLanguageValue =
						portalLanguageProperties.getProperty(key);

					if (value.equals(portalLanguageValue)) {
						continue;
					}
				}

				if (!_isAllowedLanguageKey(key, absolutePath) &&
					value.matches("(?s).*<([a-zA-Z0-9]+)[^>]*>.*?<\\/\\1>.*")) {

					addMessage(
						fileName, "Remove HTML markup for '" + key + "'",
						getLineNumber(content, content.indexOf(line)));
				}

				sb.append(line);
				sb.append("\n");
			}
		}

		content = sb.toString();

		if (content.endsWith("\n")) {
			content = content.substring(0, content.length() - 1);
		}

		return content;
	}

	private Properties _getPortalLanguageProperties(String absolutePath)
		throws IOException {

		String portalLanguagePropertiesFileName = getAttributeValue(
			_PORTAL_LANGUAGE_PROPERTIES_FILE_NAME, absolutePath);

		if (absolutePath.endsWith(portalLanguagePropertiesFileName)) {
			return null;
		}

		if (_portalLanguageProperties != null) {
			return _portalLanguageProperties;
		}

		Properties portalLanguageProperties = new Properties();

		InputStream inputStream = getPortalInputStream(
			_PORTAL_LANGUAGE_PROPERTIES_FILE_NAME, absolutePath);

		if (inputStream != null) {
			portalLanguageProperties.load(inputStream);
		}

		_portalLanguageProperties = portalLanguageProperties;

		return _portalLanguageProperties;
	}

	private boolean _isAllowedLanguageKey(String key, String absolutePath) {
		String s = key.replaceAll("[^\\w.-]", StringPool.BLANK);

		List<String> allowedLanguageKeys = getAttributeValues(
			_ALLOWED_LANGUAGE_KEYS_KEY, absolutePath);

		for (String allowedLanguageKey : allowedLanguageKeys) {
			if (s.equals(allowedLanguageKey)) {
				return true;
			}
		}

		return false;
	}

	private static final String _ALLOWED_LANGUAGE_KEYS_KEY =
		"allowedLanguageKeys";

	private static final String _PORTAL_LANGUAGE_PROPERTIES_FILE_NAME =
		"portalLanguagePropertiesFileName";

	private Properties _portalLanguageProperties;

}