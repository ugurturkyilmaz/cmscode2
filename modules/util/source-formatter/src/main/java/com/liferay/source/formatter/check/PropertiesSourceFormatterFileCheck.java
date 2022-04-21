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

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.source.formatter.check.comparator.PropertyValueComparator;
import com.liferay.source.formatter.check.util.SourceUtil;

import java.io.File;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author Hugo Huijser
 */
public class PropertiesSourceFormatterFileCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (absolutePath.endsWith("/source-formatter.properties")) {
			content = _fixCheckProperties(content);

			return _formatSourceFormatterProperties(fileName, content);
		}

		return content;
	}

	private String _fixCheckProperties(String content) throws Exception {
		Matcher matcher = _checkPropertyPattern.matcher(content);

		while (matcher.find()) {
			List<String> checkNames = null;

			if (Objects.equals(matcher.group(1), "checkstyle")) {
				checkNames = _getCheckstyleCheckNames();
			}
			else {
				checkNames = _getSourceCheckCheckNames();
			}

			String match = matcher.group(2);

			String formattedMatch = StringUtil.removeChar(
				match, CharPool.PERIOD);

			for (String checkName : checkNames) {
				if (StringUtil.equalsIgnoreCase(checkName, formattedMatch)) {
					return StringUtil.replaceFirst(
						content, match, checkName, matcher.start());
				}
			}
		}

		return content;
	}

	private String _formatSourceFormatterProperties(
			String fileName, String content)
		throws Exception {

		Properties properties = new Properties();

		properties.load(new StringReader(content));

		Enumeration<String> enumeration =
			(Enumeration<String>)properties.propertyNames();

		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();

			String value = properties.getProperty(key);

			if (Validator.isNull(value)) {
				continue;
			}

			List<String> propertyValues = ListUtil.fromString(
				value, StringPool.COMMA);

			if (propertyValues.size() > 1) {
				content = _sortPropertyValues(content, key, propertyValues);
			}

			if (!key.endsWith("excludes") && !key.endsWith("FileNames")) {
				continue;
			}

			for (String propertyFileName : propertyValues) {
				if (propertyFileName.contains(StringPool.STAR) ||
					propertyFileName.endsWith("-ext.properties") ||
					(isPortalSource() && !_hasPrivateAppsDir() &&
					 isModulesApp(propertyFileName, true))) {

					continue;
				}

				int pos = propertyFileName.indexOf(CharPool.AT);

				if (pos != -1) {
					propertyFileName = propertyFileName.substring(0, pos);
				}

				File file = getFile(propertyFileName, getMaxDirLevel());

				if (file == null) {
					addMessage(
						fileName,
						"Property value '" + propertyFileName +
							"' points to file that does not exist");
				}
			}
		}

		return content;
	}

	private List<String> _getCheckstyleCheckNames() throws Exception {
		List<String> checkstyleCheckNames = new ArrayList<>();

		checkstyleCheckNames.addAll(
			_getCheckstyleCheckNames(_getRootElement("checkstyle.xml")));

		return checkstyleCheckNames;
	}

	private List<String> _getCheckstyleCheckNames(Element moduleElement) {
		List<String> checkstyleCheckNames = new ArrayList<>();

		String checkName = moduleElement.attributeValue("name");

		int x = checkName.lastIndexOf(CharPool.PERIOD);

		if (x != -1) {
			checkstyleCheckNames.add(checkName.substring(x + 1));
		}
		else {
			checkstyleCheckNames.add(checkName);
		}

		for (Element childModuleElement :
				(List<Element>)moduleElement.elements("module")) {

			checkstyleCheckNames.addAll(
				_getCheckstyleCheckNames(childModuleElement));
		}

		return checkstyleCheckNames;
	}

	private Element _getRootElement(String fileName) throws Exception {
		ClassLoader classLoader =
			PropertiesSourceFormatterFileCheck.class.getClassLoader();

		String content = StringUtil.read(
			classLoader.getResourceAsStream(fileName));

		Document document = SourceUtil.readXML(content);

		return document.getRootElement();
	}

	private List<String> _getSourceCheckCheckNames() throws Exception {
		List<String> sourceCheckCheckNames = new ArrayList<>();

		Element rootElement = _getRootElement("sourcechecks.xml");

		for (Element sourceProcessorElement :
				(List<Element>)rootElement.elements("source-processor")) {

			for (Element checkElement :
					(List<Element>)sourceProcessorElement.elements("check")) {

				sourceCheckCheckNames.add(checkElement.attributeValue("name"));
			}
		}

		return sourceCheckCheckNames;
	}

	private synchronized boolean _hasPrivateAppsDir() {
		if (_hasPrivateAppsDir != null) {
			return _hasPrivateAppsDir;
		}

		_hasPrivateAppsDir = false;

		if (isPortalSource()) {
			return _hasPrivateAppsDir;
		}

		File dxpAppsDir = getFile("modules/dxp/apps", getMaxDirLevel());

		if (dxpAppsDir != null) {
			_hasPrivateAppsDir = true;

			return _hasPrivateAppsDir;
		}

		File privateAppsDir = getFile("modules/private/apps", getMaxDirLevel());

		if (privateAppsDir != null) {
			_hasPrivateAppsDir = true;
		}

		return _hasPrivateAppsDir;
	}

	private String _sortPropertyValues(
		String content, String propertyKey, List<String> propertyValues) {

		PropertyValueComparator comparator = new PropertyValueComparator();

		for (int i = 0; i < (propertyValues.size() - 1); i++) {
			String propertyValue = propertyValues.get(i);
			String nextPropertyValue = propertyValues.get(i + 1);

			if (comparator.compare(propertyValue, nextPropertyValue) > 0) {
				return _swapValues(
					content, propertyKey, propertyValue, nextPropertyValue);
			}
		}

		return content;
	}

	private String _swapValues(String content, int x, String s1, String s2) {
		while (true) {
			x = content.indexOf(s1, x + 1);

			if (x == -1) {
				return content;
			}

			char c = content.charAt(x - 1);

			if (!Character.isWhitespace(c) && (c != CharPool.EQUAL) &&
				(c != CharPool.COMMA)) {

				continue;
			}

			if ((x + s1.length()) < content.length()) {
				c = content.charAt(x + s1.length());

				if (!Character.isWhitespace(c) && (c != CharPool.COMMA)) {
					continue;
				}
			}

			return StringUtil.replaceFirst(content, s1, s2, x);
		}
	}

	private String _swapValues(
		String content, String propertyKey, String propertyValue,
		String nextPropertyValue) {

		int x = -1;

		while (true) {
			x = content.indexOf(propertyKey + "=");

			if (x == -1) {
				return content;
			}

			if ((x == 0) || Character.isWhitespace(content.charAt(x - 1))) {
				break;
			}
		}

		content = _swapValues(content, x, nextPropertyValue, propertyValue);
		content = _swapValues(content, x, propertyValue, nextPropertyValue);

		return content;
	}

	private static final Pattern _checkPropertyPattern = Pattern.compile(
		"\n\\s*#?(checkstyle|source\\.check)\\.(.*\\.check)\\.");

	private Boolean _hasPrivateAppsDir;

}