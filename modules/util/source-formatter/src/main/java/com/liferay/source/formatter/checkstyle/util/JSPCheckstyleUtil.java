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

package com.liferay.source.formatter.checkstyle.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Peter Shin
 * @author Hugo Huijser
 */
public class JSPCheckstyleUtil {

	public static String getJavaContent(String absolutePath, String content)
		throws IOException {

		if (absolutePath.contains("src/main/resources/alloy_mvc/jsp/")) {
			return _getAlloyMVCJavaContent(content);
		}

		if (StringUtil.startsWith(StringUtil.trim(content), "<%\n")) {
			return null;
		}

		Matcher matcher = _javaSourceTagPattern.matcher(content);

		if (matcher.find()) {
			return _getJavaContent(content);
		}

		return null;
	}

	private static String _getAlloyMVCJavaContent(String content) {
		if (!content.matches("(?s)<%--.*--%>(\\s*<%@[^\\n]*)*\\s*<%!\\s.*") ||
			(StringUtil.count(content, "<%!") != 1) ||
			!content.endsWith("\n%>")) {

			return null;
		}

		String javaContent = StringUtil.replace(
			content, new String[] {"<%--", "--%>", "<%@", "<%!"},
			new String[] {"//<%--", "//--%>", "//<%@", "//<%!"});

		return StringUtil.replaceLast(javaContent, "\n%>", "");
	}

	private static String _getJavaContent(String content) throws IOException {
		StringBundler sb = new StringBundler();

		List<String> lines = CheckstyleUtil.getLines(content);

		boolean javaSource = false;

		sb.append("public class TempClass {\n");

		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);

			String trimmedLine = StringUtil.trimLeading(line);

			if (javaSource) {
				if (trimmedLine.startsWith("%>")) {
					sb.append("\t\t// PLACEHOLDER");

					javaSource = false;
				}
				else if (Validator.isNotNull(trimmedLine)) {
					sb.append("\t\t");
					sb.append(line);
				}

				sb.append("\n");

				continue;
			}

			if (i == 1) {
				sb.append("\tpublic void method() {");
			}
			else {
				Matcher matcher = _ifTagPattern.matcher(trimmedLine);

				if (matcher.find()) {
					String nextLine = StringUtil.trimLeading(lines.get(i + 1));

					if (!nextLine.equals("<%")) {
						sb.append("\t\tif (");
						sb.append(matcher.group(1));
						sb.append(") {\n");
						sb.append("\t\t}\n");

						i++;

						continue;
					}
				}

				sb.append("\t\t// PLACEHOLDER");
			}

			sb.append("\n");

			if (trimmedLine.equals("<%") || trimmedLine.endsWith("<%=")) {
				javaSource = true;
			}
		}

		sb.append("\t}\n");
		sb.append("}\n");

		return sb.toString();
	}

	private static final Pattern _ifTagPattern = Pattern.compile(
		"^<c:if test=[\"']<%= (.*) %>[\"']>$");
	private static final Pattern _javaSourceTagPattern = Pattern.compile(
		"\n\t*<%\n");

}