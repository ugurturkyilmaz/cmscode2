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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.ToolsUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class BNDStylingCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

		content = StringUtil.replace(
			content, new String[] {"/\n", "/,\\\n", " \\\n"},
			new String[] {"\n", ",\\\n", "\\\n"});

		content = _fixIncorrectIndent(content);

		content = _fixIncorrectLineBreak(content);
		content = _fixTrailingSemiColon(content);

		content = _formatMultipleValuesOnSingleLine(content);
		content = _formatSingleValueOnMultipleLines(content);

		if (!absolutePath.endsWith("/app.bnd")) {
			content = _removeNoValueDefinitionKey(content);
		}

		return content;
	}

	private String _fixIncorrectIndent(String content) {
		Matcher matcher = _incorrectIndentPattern.matcher(content);

		if (matcher.find()) {
			return StringUtil.replaceFirst(
				content, matcher.group(1), StringPool.TAB, matcher.start());
		}

		return content;
	}

	private String _fixIncorrectLineBreak(String content) {
		Matcher matcher = _incorrectLineBreakPattern.matcher(content);

		if (matcher.find()) {
			return matcher.replaceAll("$1$2$3\\\\\n$2\t$4");
		}

		return content;
	}

	private String _fixTrailingSemiColon(String content) {
		Matcher matcher = _trailingSemiColonPattern.matcher(content);

		if (matcher.find()) {
			return StringUtil.replaceFirst(
				content, StringPool.SEMICOLON, StringPool.BLANK,
				matcher.start());
		}

		return content;
	}

	private String _formatMultipleValuesOnSingleLine(String content) {
		Matcher matcher = _multipleValuesOnSingleLinePattern.matcher(content);

		while (matcher.find()) {
			if (ToolsUtil.isInsideQuotes(content, matcher.start())) {
				continue;
			}

			int x = content.lastIndexOf(CharPool.NEW_LINE, matcher.start());

			String s = content.substring(x + 1, matcher.start());

			if (s.contains("-Description:") ||
				s.contains("Liferay-Versions:")) {

				continue;
			}

			content = StringUtil.insert(content, "\\\n\t", matcher.start() + 1);

			if (s.startsWith(StringPool.TAB)) {
				return content;
			}

			x = content.indexOf(": ", x + 1);

			if ((x == -1) || (x > matcher.start())) {
				continue;
			}

			return StringUtil.replaceFirst(
				content, StringPool.SPACE, "\\\n\t", x);
		}

		return content;
	}

	private String _formatSingleValueOnMultipleLines(String content) {
		Matcher matcher = _singleValueOnMultipleLinesPattern.matcher(content);

		if (matcher.find()) {
			content = StringUtil.replaceFirst(
				content, matcher.group(1), StringPool.SPACE, matcher.start());
		}

		return content;
	}

	private String _removeNoValueDefinitionKey(String content) {
		Matcher matcher = _noValueDefinitionKeyPattern.matcher(content);

		if (matcher.find()) {
			content = StringUtil.removeSubstring(content, matcher.group(2));
		}

		return content;
	}

	private static final Pattern _incorrectIndentPattern = Pattern.compile(
		"\n[^\t].*:\\\\\n(\t{2,})[^\t]");
	private static final Pattern _incorrectLineBreakPattern = Pattern.compile(
		"(\\A|[^\\\\]\n)(\t*)([-\\w]+:)\\s*(.*,\\\\(\n|\\Z))");
	private static final Pattern _multipleValuesOnSingleLinePattern =
		Pattern.compile(",(?!\\\\(\n|\\Z)).");
	private static final Pattern _noValueDefinitionKeyPattern = Pattern.compile(
		"(\\A|\n)(.*:\\s*(\n|\\Z))");
	private static final Pattern _singleValueOnMultipleLinesPattern =
		Pattern.compile("\n.*:(\\\\\n\t).*(\n[^\t]|\\Z)");
	private static final Pattern _trailingSemiColonPattern = Pattern.compile(
		";(\n|\\Z)");

}