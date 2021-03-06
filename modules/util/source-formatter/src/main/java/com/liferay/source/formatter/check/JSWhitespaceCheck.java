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
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

/**
 * @author Hugo Huijser
 */
public class JSWhitespaceCheck extends WhitespaceCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		content = StringUtil.replace(
			content,
			new String[] {
				StringPool.TAB + "else{", StringPool.TAB + "for(",
				StringPool.TAB + "if(", StringPool.TAB + "while(",
				" function (", "){\n", "= new Array();", "= new Object();"
			},
			new String[] {
				StringPool.TAB + "else {", StringPool.TAB + "for (",
				StringPool.TAB + "if (", StringPool.TAB + "while (",
				" function(", ") {\n", "= [];", "= {};"
			});

		return super.doProcess(fileName, absolutePath, content);
	}

}