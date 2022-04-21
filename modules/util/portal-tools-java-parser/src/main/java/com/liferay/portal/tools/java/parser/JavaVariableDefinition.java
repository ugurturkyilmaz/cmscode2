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
import com.liferay.petra.string.StringPool;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Hugo Huijser
 */
public class JavaVariableDefinition extends BaseJavaTerm {

	public JavaVariableDefinition(
		List<JavaAnnotation> javaAnnotations, List<JavaSimpleValue> modifiers) {

		_javaAnnotations = javaAnnotations;
		_modifiers = modifiers;
	}

	public void addVariable(String name) {
		addVariable(name, null);
	}

	public void addVariable(
		String name, JavaExpression assignValueJavaExpression) {

		_variableMap.put(new JavaSimpleValue(name), assignValueJavaExpression);
	}

	public JavaExpression getAssignValueJavaExpression() {
		Set<Map.Entry<JavaSimpleValue, JavaExpression>> set =
			_variableMap.entrySet();

		Iterator<Map.Entry<JavaSimpleValue, JavaExpression>> iterator =
			set.iterator();

		Map.Entry<JavaSimpleValue, JavaExpression> entry = iterator.next();

		return entry.getValue();
	}

	public void setJavaType(JavaType javaType) {
		_javaType = javaType;
	}

	@Override
	public String toString(
		String indent, String prefix, String suffix, int maxLineLength) {

		StringBundler sb = new StringBundler();

		for (int i = 0; i < _javaAnnotations.size(); i++) {
			if (i == 0) {
				appendNewLine(
					sb, _javaAnnotations.get(i), indent, prefix, "",
					maxLineLength);

				prefix = StringPool.BLANK;
			}
			else {
				appendNewLine(
					sb, _javaAnnotations.get(i), indent, maxLineLength);
			}
		}

		if (sb.index() > 0) {
			sb.append("\n");
		}

		sb.append(indent);

		indent = "\t" + indent;

		if (!_modifiers.isEmpty()) {
			indent = append(
				sb, _modifiers, " ", indent, prefix, " ", maxLineLength);

			prefix = StringPool.BLANK;
		}

		if (_javaType != null) {
			indent = append(
				sb, _javaType, indent, prefix, " ", maxLineLength, false);

			prefix = StringPool.BLANK;
		}

		String startIndent = indent;

		Set<Map.Entry<JavaSimpleValue, JavaExpression>> set =
			_variableMap.entrySet();

		Iterator<Map.Entry<JavaSimpleValue, JavaExpression>> iterator =
			set.iterator();

		while (true) {
			Map.Entry<JavaSimpleValue, JavaExpression> entry = iterator.next();

			JavaExpression assignValueJavaExpression = entry.getValue();
			JavaSimpleValue name = entry.getKey();

			if (!iterator.hasNext()) {
				if (assignValueJavaExpression != null) {
					indent = append(
						sb, name, startIndent, prefix, " = ", maxLineLength);

					appendAssignValue(
						sb, assignValueJavaExpression, indent, suffix,
						maxLineLength, false);
				}
				else {
					append(sb, name, startIndent, "", suffix, maxLineLength);
				}

				break;
			}

			if (assignValueJavaExpression != null) {
				indent = append(
					sb, name, startIndent, prefix, " = ", maxLineLength);

				appendAssignValue(
					sb, assignValueJavaExpression, indent, ", ", maxLineLength,
					false);
			}
			else {
				append(sb, name, startIndent, "", ", ", maxLineLength);
			}
		}

		return sb.toString();
	}

	private final List<JavaAnnotation> _javaAnnotations;
	private JavaType _javaType;
	private final List<JavaSimpleValue> _modifiers;
	private final Map<JavaSimpleValue, JavaExpression> _variableMap =
		new LinkedHashMap<>();

}