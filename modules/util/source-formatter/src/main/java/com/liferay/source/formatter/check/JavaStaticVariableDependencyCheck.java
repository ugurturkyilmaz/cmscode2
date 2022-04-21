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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.ToolsUtil;
import com.liferay.source.formatter.parser.JavaClass;
import com.liferay.source.formatter.parser.JavaTerm;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JavaStaticVariableDependencyCheck extends BaseJavaTermCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, JavaTerm javaTerm,
		String fileContent) {

		String classContent = javaTerm.getContent();

		JavaClass javaClass = (JavaClass)javaTerm;

		List<JavaTerm> childJavaTerms = javaClass.getChildJavaTerms();

		for (int i = 0; i < childJavaTerms.size(); i++) {
			JavaTerm childJavaTerm = childJavaTerms.get(i);

			if (childJavaTerm.isJavaVariable() && childJavaTerm.isStatic()) {
				classContent = _formatStaticVariableDependencies(
					classContent, javaClass.getName(), childJavaTerm,
					childJavaTerms, i);
			}
		}

		return classContent;
	}

	@Override
	protected String[] getCheckableJavaTermNames() {
		return new String[] {JAVA_CLASS};
	}

	private String _formatStaticVariableDependencies(
		String classContent, String className, JavaTerm javaTerm,
		List<JavaTerm> childJavaTerms, int index) {

		String variableName = javaTerm.getName();

		Pattern pattern = Pattern.compile(
			StringBundler.concat("[^\\w.\\s]\\s*(", variableName, ")\\W"));

		for (int i = 0; i < index; i++) {
			JavaTerm siblingJavaTerm = childJavaTerms.get(i);

			if (!siblingJavaTerm.isJavaVariable() ||
				!siblingJavaTerm.isStatic()) {

				continue;
			}

			String siblingVariableContent = siblingJavaTerm.getContent();

			Matcher matcher = pattern.matcher(siblingVariableContent);

			while (matcher.find()) {
				int pos = matcher.start(1);

				if (ToolsUtil.isInsideQuotes(siblingVariableContent, pos)) {
					continue;
				}

				String s = siblingVariableContent.substring(0, pos);

				if (getLevel(s, "{", "}") != 0) {
					continue;
				}

				String line = StringUtil.trim(
					getLine(
						siblingVariableContent,
						getLineNumber(siblingVariableContent, pos)));

				if (line.startsWith(StringPool.STAR)) {
					continue;
				}

				String newVariableContent = StringUtil.replaceFirst(
					siblingVariableContent, variableName,
					StringBundler.concat(
						className, StringPool.PERIOD, variableName),
					pos);

				return StringUtil.replaceFirst(
					classContent, siblingVariableContent, newVariableContent);
			}
		}

		pattern = Pattern.compile(
			StringBundler.concat(
				"(\\W)(", className, "\\.\\s*)(", variableName, "\\W)"));

		for (int i = index + 1; i < childJavaTerms.size(); i++) {
			JavaTerm siblingJavaTerm = childJavaTerms.get(i);

			if (!siblingJavaTerm.isJavaVariable() ||
				!siblingJavaTerm.isStatic()) {

				continue;
			}

			String siblingVariableContent = siblingJavaTerm.getContent();

			Matcher matcher = pattern.matcher(siblingVariableContent);

			if (matcher.find()) {
				return StringUtil.replaceFirst(
					classContent, siblingVariableContent,
					matcher.replaceAll("$1$3"));
			}
		}

		return classContent;
	}

}