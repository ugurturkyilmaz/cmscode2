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
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.source.formatter.check.comparator.ElementComparator;
import com.liferay.source.formatter.check.util.SourceUtil;

import java.io.File;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * @author Hugo Huijser
 */
public class XMLSuppressionsFileCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws DocumentException {

		if (!fileName.endsWith("-suppressions.xml")) {
			return content;
		}

		int x = absolutePath.lastIndexOf(CharPool.SLASH);

		String fileLocation = absolutePath.substring(0, x + 1);

		Document document = SourceUtil.readXML(content);

		Element rootElement = document.getRootElement();

		SuppressElementComparator suppressElementComparator =
			new SuppressElementComparator();

		checkElementOrder(
			fileName, rootElement, "suppress", null, suppressElementComparator);

		Element checkstyleElement = rootElement.element("checkstyle");

		checkElementOrder(
			fileName, checkstyleElement, "suppress", "checkstyle",
			suppressElementComparator);

		content = _formatFilesPropertyValue(
			fileName, content, fileLocation, checkstyleElement);

		Element sourceCheckElement = rootElement.element("source-check");

		checkElementOrder(
			fileName, sourceCheckElement, "suppress", "source-check",
			suppressElementComparator);

		return _formatFilesPropertyValue(
			fileName, content, fileLocation, sourceCheckElement);
	}

	private void _addMessage(
		String fileName, String content, String value, String expectedName,
		String type) {

		int x = content.indexOf("\"" + value + "\"");

		if (x != -1) {
			x = getLineNumber(content, x);
		}

		addMessage(
			fileName,
			StringBundler.concat(
				"Incorrect value '", value, "'. Relative path to file (from ",
				"location of suppressions file) expected for value of ",
				"property 'files'. ", type, " '", expectedName,
				"' does not exist."),
			x);
	}

	private String _formatFilesPropertyValue(
		String fileName, String content, String fileLocation, Element element) {

		if (element == null) {
			return content;
		}

		for (Element suppressElement :
				(List<Element>)element.elements("suppress")) {

			String originalValue = suppressElement.attributeValue("files");

			if (originalValue == null) {
				continue;
			}

			String value = StringUtil.replace(originalValue, "\\.", ".");

			int x = value.indexOf(CharPool.STAR);

			if (x == -1) {
				String expectedFileName = fileLocation + value;

				File file = new File(expectedFileName);

				if (!file.exists()) {
					String relativeValue = _getRelativeValue(
						originalValue, fileLocation);

					if (relativeValue != null) {
						return StringUtil.replace(
							content, "\"" + originalValue + "\"",
							"\"" + relativeValue + "\"");
					}

					_addMessage(
						fileName, content, originalValue, expectedFileName,
						"File");
				}

				continue;
			}

			int y = value.lastIndexOf(CharPool.SLASH, x);

			if (y == -1) {
				continue;
			}

			String directoryValue = value.substring(0, y + 1);

			String expectedFolderName = fileLocation + directoryValue;

			File file = new File(expectedFolderName);

			if (!file.exists()) {
				String relativeValue = _getRelativeValue(
					originalValue, fileLocation, directoryValue);

				if (relativeValue != null) {
					return StringUtil.replace(
						content, "\"" + originalValue + "\"",
						"\"" + relativeValue + "\"");
				}

				_addMessage(
					fileName, content, originalValue, expectedFolderName,
					"Directory");
			}
		}

		return content;
	}

	private String _getRelativeValue(
		String originalvalue, String fileLocation) {

		int x = -1;

		while (true) {
			x = originalvalue.indexOf(CharPool.SLASH, x + 1);

			if (x == -1) {
				return null;
			}

			String s = originalvalue.substring(0, x + 1);

			if (!fileLocation.endsWith(s)) {
				continue;
			}

			String relativeValue = originalvalue.substring(x + 1);

			File file = new File(
				fileLocation + StringUtil.replace(relativeValue, "\\.", "."));

			if (file.exists()) {
				return relativeValue;
			}
		}
	}

	private String _getRelativeValue(
		String originalValue, String fileLocation, String directoryValue) {

		int x = -1;

		while (true) {
			x = directoryValue.indexOf(CharPool.SLASH, x + 1);

			if (x == -1) {
				return null;
			}

			String s = directoryValue.substring(0, x + 1);

			if (!fileLocation.endsWith(s)) {
				continue;
			}

			String relativeValue = directoryValue.substring(x + 1);

			if (Validator.isNotNull(relativeValue)) {
				File file = new File(fileLocation + relativeValue);

				if (!file.exists()) {
					continue;
				}
			}

			return StringUtil.replaceFirst(
				originalValue, directoryValue, relativeValue);
		}
	}

	private class SuppressElementComparator extends ElementComparator {

		@Override
		public String getElementName(Element suppressElement) {
			StringBundler sb = new StringBundler(3);

			sb.append(suppressElement.attributeValue("checks"));
			sb.append(StringPool.POUND);
			sb.append(suppressElement.attributeValue("files"));

			return sb.toString();
		}

	}

}