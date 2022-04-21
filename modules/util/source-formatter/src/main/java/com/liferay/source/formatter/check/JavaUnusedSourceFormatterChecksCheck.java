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

import com.liferay.petra.string.StringPool;
import com.liferay.source.formatter.check.util.SourceUtil;
import com.liferay.source.formatter.parser.JavaClass;
import com.liferay.source.formatter.parser.JavaMethod;
import com.liferay.source.formatter.parser.JavaTerm;
import com.liferay.source.formatter.util.FileUtil;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * @author Hugo Huijser
 */
public class JavaUnusedSourceFormatterChecksCheck extends BaseJavaTermCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, JavaTerm javaTerm,
			String fileContent)
		throws DocumentException, IOException {

		JavaClass javaClass = (JavaClass)javaTerm;

		if (!javaClass.isAbstract()) {
			_checkConfiguration(
				fileName, absolutePath, javaClass,
				"com.liferay.source.formatter.check", "doProcess",
				"sourcechecks.xml", "check");
			_checkConfiguration(
				fileName, absolutePath, javaClass,
				"com.liferay.source.formatter.checkstyle.check", "doVisitToken",
				"checkstyle.xml", "module");
		}

		return javaTerm.getContent();
	}

	@Override
	protected String[] getCheckableJavaTermNames() {
		return new String[] {JAVA_CLASS};
	}

	private List<String> _addCheckNames(
		List<String> checkNames, Element element, String checkElementName) {

		if (checkElementName.equals(element.getName())) {
			checkNames.add(element.attributeValue("name"));
		}

		for (Element childElement : (List<Element>)element.elements()) {
			checkNames = _addCheckNames(
				checkNames, childElement, checkElementName);
		}

		return checkNames;
	}

	private void _checkConfiguration(
			String fileName, String absolutePath, JavaClass javaClass,
			String packageName, String methodName, String configurationFileName,
			String checkElementName)
		throws DocumentException, IOException {

		if (!packageName.equals(javaClass.getPackageName())) {
			return;
		}

		for (JavaTerm javaTerm : javaClass.getChildJavaTerms()) {
			if (!(javaTerm instanceof JavaMethod) ||
				!methodName.equals(javaTerm.getName())) {

				continue;
			}

			List<String> checkNames = _getCheckNames(
				absolutePath, configurationFileName, checkElementName);

			if (!checkNames.isEmpty() &&
				!checkNames.contains(javaClass.getName()) &&
				!checkNames.contains(javaClass.getName(true))) {

				addMessage(
					fileName,
					"Check is not configured in '" + configurationFileName +
						"'");
			}
		}
	}

	private synchronized List<String> _getCheckNames(
			String absolutePath, String configurationFileName,
			String checkElementName)
		throws DocumentException, IOException {

		List<String> checkNames = _checkNamesMap.get(configurationFileName);

		if (checkNames != null) {
			return checkNames;
		}

		checkNames = new ArrayList<>();

		String path = absolutePath;

		while (true) {
			int pos = path.lastIndexOf(StringPool.SLASH);

			if (pos == -1) {
				_checkNamesMap.put(configurationFileName, checkNames);

				return checkNames;
			}

			path = path.substring(0, pos);

			File file = new File(
				path + "/main/resources/" + configurationFileName);

			if (!file.exists()) {
				continue;
			}

			Document document = SourceUtil.readXML(FileUtil.read(file));

			checkNames = _addCheckNames(
				checkNames, document.getRootElement(), checkElementName);

			_checkNamesMap.put(configurationFileName, checkNames);

			return checkNames;
		}
	}

	private final Map<String, List<String>> _checkNamesMap = new HashMap<>();

}