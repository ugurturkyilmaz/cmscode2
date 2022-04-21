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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.ImportsFormatter;
import com.liferay.source.formatter.PythonImportsFormatter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alan Huang
 */
public class PythonImportsCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		content = _formatModuleImports(content);

		content = _formatPackageImports(content);

		content = content.replaceAll(
			"(?m)^(([ \t]*)from [\\s\\S]+?)\n+^(\\2(?!from )[^\t\n])",
			"$1\n\n$3");

		if (content.endsWith(StringPool.NEW_LINE)) {
			content = content.substring(0, content.length() - 1);
		}

		return content;
	}

	private String _formatModuleImports(String content) {
		Matcher matcher = _importsPattern1.matcher(content);

		List<String> importNames = new ArrayList<>();

		int startPosition = -1;

		while (matcher.find()) {
			importNames.add(matcher.group(1));

			if (startPosition == -1) {
				startPosition = matcher.start();
			}

			content = content.replaceFirst(matcher.group(), StringPool.BLANK);
		}

		if (ListUtil.isEmpty(importNames)) {
			return content;
		}

		Collections.sort(importNames);

		StringBundler sb = new StringBundler((importNames.size() * 3) + 1);

		sb.append(StringPool.NEW_LINE);

		for (String importName : importNames) {
			sb.append(StringPool.NEW_LINE);
			sb.append("import ");
			sb.append(importName);
		}

		return StringUtil.insert(content, sb.toString(), startPosition);
	}

	private String _formatPackageImports(String content) throws IOException {
		ImportsFormatter importsFormatter = new PythonImportsFormatter();

		return importsFormatter.format(content, _importsPattern2);
	}

	private static final Pattern _importsPattern1 = Pattern.compile(
		"\n+[ \t]*import (.*)");
	private static final Pattern _importsPattern2 = Pattern.compile(
		"(^[ \t]*from (.*) import (.+(\\\\\n[\\s\\S]+?)*)(?<!\\\\)(\n|\\Z)+)+",
		Pattern.MULTILINE);

}