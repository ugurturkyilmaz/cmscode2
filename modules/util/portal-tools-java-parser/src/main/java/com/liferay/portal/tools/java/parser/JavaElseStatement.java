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

package com.liferay.portal.tools.java.parser;

import com.liferay.petra.string.StringBundler;

/**
 * @author Hugo Huijser
 */
public class JavaElseStatement extends BaseJavaTerm {

	public void setJavaIfStatement(JavaIfStatement javaIfStatement) {
		_javaIfStatement = javaIfStatement;
	}

	@Override
	public String toString(
		String indent, String prefix, String suffix, int maxLineLength) {

		if (_javaIfStatement == null) {
			return StringBundler.concat(indent, prefix, "else", suffix);
		}

		return _javaIfStatement.toString(
			indent, "else ", suffix, maxLineLength);
	}

	private JavaIfStatement _javaIfStatement;

}