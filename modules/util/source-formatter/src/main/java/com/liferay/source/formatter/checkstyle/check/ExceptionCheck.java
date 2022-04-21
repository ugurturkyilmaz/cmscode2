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

package com.liferay.source.formatter.checkstyle.check;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Hugo Huijser
 */
public class ExceptionCheck extends BaseCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.METHOD_DEF};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		DetailAST modifiersDetailAST = detailAST.findFirstToken(
			TokenTypes.MODIFIERS);

		if ((modifiersDetailAST == null) ||
			!modifiersDetailAST.branchContains(TokenTypes.LITERAL_PRIVATE)) {

			return;
		}

		List<String> exceptionNames = _getExceptionNames(detailAST);

		if (exceptionNames.isEmpty() || exceptionNames.contains("Exception")) {
			return;
		}

		_checkException(detailAST, exceptionNames);
	}

	private void _checkException(
		DetailAST methodDefinitionDetailAST, List<String> exceptionNames) {

		DetailAST classDefinitionDetailAST = null;
		DetailAST parentDetailAST = methodDefinitionDetailAST;

		while (true) {
			parentDetailAST = getParentWithTokenType(
				parentDetailAST, TokenTypes.CLASS_DEF,
				TokenTypes.INTERFACE_DEF);

			if (parentDetailAST == null) {
				break;
			}

			classDefinitionDetailAST = parentDetailAST;
		}

		if (classDefinitionDetailAST == null) {
			return;
		}

		String methodName = getName(methodDefinitionDetailAST);

		List<DetailAST> identDetailASTList = getAllChildTokens(
			classDefinitionDetailAST, true, TokenTypes.IDENT);

		boolean calledByMethodWithException = false;

		for (DetailAST identDetailAST : identDetailASTList) {
			if (!methodName.equals(identDetailAST.getText())) {
				continue;
			}

			parentDetailAST = getParentWithTokenType(
				identDetailAST, TokenTypes.LAMBDA, TokenTypes.LITERAL_TRY,
				TokenTypes.METHOD_REF);

			if (parentDetailAST != null) {
				return;
			}

			parentDetailAST = getParentWithTokenType(
				identDetailAST, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF);

			if (parentDetailAST == null) {
				return;
			}

			DetailAST nameDetailAST = parentDetailAST.findFirstToken(
				TokenTypes.IDENT);

			if (methodName.equals(nameDetailAST.getText())) {
				continue;
			}

			List<String> methodExceptionNames = _getExceptionNames(
				parentDetailAST);

			if (methodExceptionNames.isEmpty()) {
				continue;
			}

			if ((methodExceptionNames.size() == 1) &&
				Objects.equals(methodExceptionNames.get(0), "Exception")) {

				calledByMethodWithException = true;

				continue;
			}

			return;
		}

		if (calledByMethodWithException) {
			log(
				methodDefinitionDetailAST, _MSG_UNNECESSARY_EXCEPTION,
				methodName, _getExceptionNamesString(exceptionNames));
		}
	}

	private List<String> _getExceptionNames(DetailAST detailAST) {
		DetailAST literalThrowsDetailAST = detailAST.findFirstToken(
			TokenTypes.LITERAL_THROWS);

		if (literalThrowsDetailAST == null) {
			return Collections.emptyList();
		}

		return getNames(literalThrowsDetailAST, false);
	}

	private String _getExceptionNamesString(List<String> exceptionNames) {
		StringBundler sb = new StringBundler();

		for (int i = 0; i < exceptionNames.size(); i++) {
			sb.append(StringPool.APOSTROPHE);
			sb.append(exceptionNames.get(i));
			sb.append(StringPool.APOSTROPHE);

			if (i < (exceptionNames.size() - 2)) {
				sb.append(StringPool.COMMA_AND_SPACE);
			}
			else if (i < (exceptionNames.size() - 1)) {
				sb.append(" and ");
			}
		}

		return sb.toString();
	}

	private static final String _MSG_UNNECESSARY_EXCEPTION =
		"exception.unnecessary";

}