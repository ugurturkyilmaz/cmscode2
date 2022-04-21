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

import java.io.IOException;

/**
 * @author Hugo Huijser
 */
public class XMLWhitespaceCheck extends WhitespaceCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		content = StringUtil.replace(content, "\"/>\n", "\" />\n");

		content = content.replaceAll("([\n\t]<\\!--) (<)", "$1$2");

		content = StringUtil.replace(content, "> -->\n", ">-->\n");

		return super.doProcess(fileName, absolutePath, content);
	}

	@Override
	protected String formatDoubleSpace(String line) {
		if (line.contains("<contains") || line.contains("<replacetoken") ||
			line.contains("<replacevalue")) {

			return line;
		}

		return super.formatDoubleSpace(line);
	}

	@Override
	protected boolean isAllowLeadingSpaces(
		String fileName, String absolutePath) {

		if (fileName.startsWith(getBaseDirName() + "build") ||
			fileName.contains("/build")) {

			return true;
		}

		return false;
	}

}