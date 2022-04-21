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

package com.liferay.portal.kernel.servlet.taglib.aui;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Iván Zaera Avellón
 */
public class VariableUtil {

	public static String generateVariable(String module) {
		return generateVariable(module, null);
	}

	public static String generateVariable(
		String module, Set<String> usedVariables) {

		StringBundler sb = new StringBundler(module.length());

		char c = module.charAt(0);

		boolean modified = true;

		if (((CharPool.LOWER_CASE_A <= c) && (c <= CharPool.LOWER_CASE_Z)) ||
			(c == CharPool.UNDERLINE)) {

			sb.append(c);

			modified = false;
		}
		else if ((CharPool.UPPER_CASE_A <= c) && (c <= CharPool.UPPER_CASE_Z)) {
			sb.append((char)(c + 32));
		}
		else {
			sb.append(CharPool.UNDERLINE);
		}

		boolean startNewWord = false;

		for (int i = 1; i < module.length(); i++) {
			c = module.charAt(i);

			if ((CharPool.LOWER_CASE_A <= c) && (c <= CharPool.LOWER_CASE_Z)) {
				if (startNewWord) {
					sb.append((char)(c - 32));

					startNewWord = false;
				}
				else {
					sb.append(c);
				}
			}
			else if (((CharPool.UPPER_CASE_A <= c) &&
					  (c <= CharPool.UPPER_CASE_Z)) ||
					 ((CharPool.NUMBER_0 <= c) && (c <= CharPool.NUMBER_9)) ||
					 (c == CharPool.UNDERLINE)) {

				sb.append(c);

				startNewWord = false;
			}
			else {
				modified = true;

				startNewWord = true;
			}
		}

		String safeName = module;

		if (modified) {
			safeName = sb.toString();

			module = safeName;
		}

		if (_reservedWords.contains(module)) {
			module = StringPool.UNDERLINE + module;
		}

		if (usedVariables != null) {
			int i = 1;

			while (!usedVariables.add(module)) {
				module = safeName.concat(String.valueOf(i++));
			}
		}

		return module;
	}

	private static final Set<String> _reservedWords = new HashSet<>(
		Arrays.asList(
			"arguments", "await", "break", "case", "catch", "class", "const",
			"continue", "debugger", "default", "delete", "do", "else", "enum",
			"eval", "export", "extends", "false", "finally", "for", "function",
			"if", "implements", "import", "in", "instanceof", "interface",
			"let", "new", "null", "package", "private", "protected", "public",
			"return", "static", "super", "switch", "this", "throw", "true",
			"try", "typeof", "var", "void", "while", "with", "yield"));

}