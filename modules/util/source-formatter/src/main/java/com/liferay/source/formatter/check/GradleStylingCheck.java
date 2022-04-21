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
import com.liferay.portal.kernel.util.NaturalOrderStringComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.source.formatter.check.util.SourceUtil;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alan Huang
 */
public class GradleStylingCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

		content = _sortMapKeys("transformKeys", content);

		content = _stylingCheck(content, _stylingPattern1, "$1$2 {\n\t$3\n}$4");
		content = _stylingCheck(content, _stylingPattern2, "$1$2 = $3$4");

		return content;
	}

	private String _sortMapKeys(String mapName, String content) {
		Pattern pattern = Pattern.compile(
			"\n(\t*)(" + mapName + ") = \\[([\\s\\S]*?)\\]\n");

		Matcher matcher1 = pattern.matcher(content);

		if (!matcher1.find()) {
			return content;
		}

		String match = matcher1.group(3);

		if (Validator.isNull(match)) {
			return content;
		}

		Map<String, String> map = new TreeMap<>(
			new NaturalOrderStringComparator());

		Matcher matcher2 = _mapKeyPattern.matcher(match);

		while (matcher2.find()) {
			map.put(matcher2.group(1), matcher2.group(2));
		}

		StringBundler sb = new StringBundler(map.size() * 9);

		String indent = matcher1.group(1);

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (map.size() == 1) {
				sb.append(entry.getKey());
				sb.append(": ");
				sb.append(entry.getValue());

				break;
			}

			sb.append(CharPool.NEW_LINE);
			sb.append(indent);
			sb.append(CharPool.TAB);
			sb.append(entry.getKey());
			sb.append(": ");
			sb.append(entry.getValue());
			sb.append(CharPool.COMMA);
		}

		if (map.size() > 1) {
			sb.setIndex(sb.index() - 1);
			sb.append(CharPool.NEW_LINE);
			sb.append(indent);
		}

		return StringUtil.replaceFirst(content, match, sb.toString());
	}

	private String _stylingCheck(
		String content, Pattern pattern, String replacement) {

		int[] multiLineStringsPositions = SourceUtil.getMultiLinePositions(
			content, _multiLineStringsPattern);

		Matcher matcher = pattern.matcher(content);

		while (matcher.find()) {
			if (!SourceUtil.isInsideMultiLines(
					SourceUtil.getLineNumber(content, matcher.start()),
					multiLineStringsPositions)) {

				return matcher.replaceFirst(replacement);
			}
		}

		return content;
	}

	private static final Pattern _mapKeyPattern = Pattern.compile(
		"(\".+?\") *: *(\".+?\")");
	private static final Pattern _multiLineStringsPattern = Pattern.compile(
		"(\"\"\"|''')\\\\\n.*?\\1", Pattern.DOTALL);
	private static final Pattern _stylingPattern1 = Pattern.compile(
		"(\\A|\n)(\\w+)\\.(\\w+ = \\w+)(\n|\\Z)");
	private static final Pattern _stylingPattern2 = Pattern.compile(
		"(\\A|\n)(\t*\\w+)(?! = .) *=(?!~) *(.*?)(\n|\\Z)");

}