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

import com.liferay.source.formatter.check.util.SourceUtil;

import java.util.List;

/**
 * @author Alan Huang
 */
public class JSONDeprecatedPackagesCheck extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

		if (!absolutePath.endsWith("/package.json")) {
			return content;
		}

		List<String> deprecatedPackageNames = getAttributeValues(
			_DEPRECATED_PACKAGE_NAMES_KEY, absolutePath);

		for (String deprecatedPackageName : deprecatedPackageNames) {
			int x = -1;

			while (true) {
				x = content.indexOf(
					"\"" + deprecatedPackageName + "\":", x + 1);

				if (x == -1) {
					break;
				}

				addMessage(
					fileName,
					"Do not use deprecated package '" + deprecatedPackageName +
						"'",
					SourceUtil.getLineNumber(content, x));
			}
		}

		return content;
	}

	private static final String _DEPRECATED_PACKAGE_NAMES_KEY =
		"deprecatedPackageNames";

}