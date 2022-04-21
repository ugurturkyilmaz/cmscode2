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

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.check.util.GradleSourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Peter Shin
 */
public class GradleProvidedDependenciesCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

		if (!absolutePath.endsWith("/build.gradle")) {
			return content;
		}

		String scope = _getScope(content);

		for (String block : _getBlocks(content)) {
			content = _format(content, block, scope);
		}

		return StringUtil.replace(
			content,
			new String[] {"configurations.provided", "extendsFrom provided"},
			new String[] {"configurations." + scope, "extendsFrom " + scope});
	}

	private String _format(String content, String dependencies, String scope) {
		String newDependencies = dependencies.replaceAll(
			"\\bprovided\\b", scope);

		return StringUtil.replace(content, dependencies, newDependencies);
	}

	private List<String> _getBlocks(String content) {
		List<String> blocks = new ArrayList<>();

		Matcher matcher = _blocksPattern.matcher(content);

		while (matcher.find()) {
			blocks.add(matcher.group());
		}

		return blocks;
	}

	private String _getScope(String content) {
		if (GradleSourceUtil.isSpringBootExecutable(content)) {
			return "compile";
		}

		return "compileOnly";
	}

	private static final Pattern _blocksPattern = Pattern.compile(
		"^(configurations|dependencies)\\s*(\\{\\s*\\}|\\{.*?\\n\\})",
		Pattern.DOTALL | Pattern.MULTILINE);

}