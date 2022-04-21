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
import com.liferay.source.formatter.check.util.JavaSourceUtil;
import com.liferay.source.formatter.parser.JavaClass;
import com.liferay.source.formatter.parser.JavaConstructor;
import com.liferay.source.formatter.parser.JavaTerm;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JavaStaticMethodCheck extends BaseJavaTermCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, JavaTerm javaTerm,
		String fileContent) {

		String content = javaTerm.getContent();

		JavaClass javaClass = javaTerm.getParentJavaClass();

		if (javaClass.isAnonymous()) {
			return content;
		}

		if (javaTerm.isStatic() && javaTerm.isFinal() &&
			isAttributeValue("checkFinal", absolutePath)) {

			int x = content.indexOf("\t" + javaTerm.getAccessModifier());

			if (x != -1) {
				return StringUtil.replaceFirst(
					content, "final", StringPool.BLANK, x);
			}
		}

		String methodName = javaTerm.getName();

		Pattern pattern = Pattern.compile(
			StringBundler.concat(
				"\\W", javaClass.getName(), "\\s*(\\.|::)\\s*", methodName,
				"\\W"));

		Matcher matcher = pattern.matcher(fileContent);

		if (matcher.find()) {
			return content;
		}

		if (javaTerm.isPrivate() && javaTerm.isStatic() &&
			!_staticRequired(
				javaTerm,
				Pattern.compile("(\\W|\\A)" + methodName + "(\\W|\\Z)"))) {

			int x = content.indexOf("\t" + javaTerm.getAccessModifier());

			if (x != -1) {
				return StringUtil.replaceFirst(
					content, "static", StringPool.BLANK, x);
			}
		}

		return content;
	}

	@Override
	protected String[] getCheckableJavaTermNames() {
		return new String[] {JAVA_METHOD};
	}

	private boolean _staticRequired(JavaTerm javaTerm, Pattern pattern) {
		JavaClass javaClass = javaTerm.getParentJavaClass();

		if (javaClass == null) {
			return false;
		}

		for (JavaTerm childJavaTerm : javaClass.getChildJavaTerms()) {
			if (childJavaTerm.isStatic() &&
				(childJavaTerm.getLineNumber() != javaTerm.getLineNumber())) {

				Matcher matcher1 = pattern.matcher(childJavaTerm.getContent());

				if (matcher1.find()) {
					return true;
				}
			}

			if (!(childJavaTerm instanceof JavaConstructor)) {
				continue;
			}

			String content = childJavaTerm.getContent();

			Matcher matcher2 = _superThisPattern.matcher(
				childJavaTerm.getContent());

			while (matcher2.find()) {
				List<String> parameters = JavaSourceUtil.getParameterList(
					content.substring(matcher2.start()));

				for (String parameter : parameters) {
					Matcher matcher3 = pattern.matcher(parameter);

					if (matcher3.find()) {
						return true;
					}
				}
			}
		}

		return _staticRequired(javaClass, pattern);
	}

	private static final Pattern _superThisPattern = Pattern.compile(
		"\\W(super|this)\\(");

}