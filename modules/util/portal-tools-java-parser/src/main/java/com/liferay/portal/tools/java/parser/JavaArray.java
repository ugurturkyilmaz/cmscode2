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
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hugo Huijser
 */
public class JavaArray extends BaseJavaExpression {

	public void addValueJavaExpression(JavaExpression valueJavaExpression) {
		_valueJavaExpressions.add(valueJavaExpression);
	}

	public void setBreakJavaValueExpressions(
		boolean breakJavaValueExpressions) {

		_breakJavaValueExpressions = breakJavaValueExpressions;
	}

	@Override
	protected String getString(
		String indent, String prefix, String suffix, int maxLineLength,
		boolean forceLineBreak) {

		StringBundler sb = new StringBundler();

		sb.append(indent);

		indent = "\t" + indent;

		sb.append(prefix);
		sb.append("{");

		if (!forceLineBreak &&
			appendSingleLine(
				sb, _valueJavaExpressions, "", "}" + suffix, maxLineLength)) {

			return sb.toString();
		}

		if (!_valueJavaExpressions.isEmpty()) {
			appendNewLine(
				sb, _valueJavaExpressions, ", ", indent, "", "", maxLineLength,
				_breakJavaValueExpressions);

			sb.append("\n");
			sb.append(StringUtil.replaceFirst(indent, "\t", ""));
		}

		sb.append("}");
		sb.append(suffix);

		return sb.toString();
	}

	private boolean _breakJavaValueExpressions = true;
	private final List<JavaExpression> _valueJavaExpressions =
		new ArrayList<>();

}