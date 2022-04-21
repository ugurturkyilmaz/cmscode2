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

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public class AppendCheck extends BaseStringConcatenationCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		List<DetailAST> methodCallDetailASTList = getMethodCalls(
			detailAST, "append");

		for (int i = 0; i < methodCallDetailASTList.size(); i++) {
			DetailAST methodCallDetailAST = methodCallDetailASTList.get(i);

			String variableName = getVariableName(methodCallDetailAST);

			String variableTypeName = getVariableTypeName(
				methodCallDetailAST, variableName, false);

			if (!variableTypeName.equals("StringBundler")) {
				continue;
			}

			DetailAST parameterDetailAST = getParameterDetailAST(
				methodCallDetailAST);

			if (parameterDetailAST == null) {
				continue;
			}

			_checkPlusOperator(parameterDetailAST);

			if ((parameterDetailAST.getType() != TokenTypes.STRING_LITERAL) ||
				_containsMethodCall(
					detailAST, variableName, "setIndex", "setStringAt")) {

				continue;
			}

			if (i < (methodCallDetailASTList.size() - 1)) {
				DetailAST nextMethodCallDetailAST = methodCallDetailASTList.get(
					i + 1);

				if (!variableName.equals(
						getVariableName(nextMethodCallDetailAST)) ||
					(getEndLineNumber(methodCallDetailAST) !=
						(getStartLineNumber(nextMethodCallDetailAST) - 1))) {

					continue;
				}

				DetailAST nextParameterDetailAST = getParameterDetailAST(
					nextMethodCallDetailAST);

				if (nextParameterDetailAST != null) {
					if (nextParameterDetailAST.getType() ==
							TokenTypes.STRING_LITERAL) {

						_checkLiteralStrings(
							methodCallDetailAST, nextMethodCallDetailAST,
							parameterDetailAST.getText(),
							nextParameterDetailAST.getText());
					}
					else {
						checkCombineOperand(
							parameterDetailAST, nextParameterDetailAST);
					}
				}
			}

			if (i == 0) {
				continue;
			}

			DetailAST previousMethodCallDetailAST = methodCallDetailASTList.get(
				i - 1);

			if (!variableName.equals(
					getVariableName(previousMethodCallDetailAST)) ||
				(getEndLineNumber(previousMethodCallDetailAST) !=
					(getStartLineNumber(methodCallDetailAST) - 1))) {

				continue;
			}

			DetailAST previousParameterDetailAST = getParameterDetailAST(
				previousMethodCallDetailAST);

			if ((previousParameterDetailAST != null) &&
				(previousParameterDetailAST.getType() !=
					TokenTypes.STRING_LITERAL)) {

				checkCombineOperand(
					parameterDetailAST, previousParameterDetailAST);
			}
		}
	}

	private void _checkLiteralStrings(
		DetailAST methodCallDetailAST, DetailAST nextMethodCallDetailAST,
		String literalStringValue, String nextLiteralStringValue) {

		literalStringValue = literalStringValue.substring(
			1, literalStringValue.length() - 1);

		if (literalStringValue.endsWith("\\n")) {
			return;
		}

		nextLiteralStringValue = nextLiteralStringValue.substring(
			1, nextLiteralStringValue.length() - 1);

		checkLiteralStringStartAndEndCharacter(
			literalStringValue, nextLiteralStringValue,
			methodCallDetailAST.getLineNo());

		if ((_hasIncorrectLineBreaks(methodCallDetailAST) |
			 _hasIncorrectLineBreaks(nextMethodCallDetailAST)) ||
			literalStringValue.startsWith("<") ||
			literalStringValue.endsWith(">") ||
			nextLiteralStringValue.startsWith("<") ||
			nextLiteralStringValue.endsWith(">")) {

			return;
		}

		String line = getLine(methodCallDetailAST.getLineNo() - 1);

		int lineLength = CommonUtil.lengthExpandedTabs(
			line, line.length(), getTabWidth());

		if ((lineLength + nextLiteralStringValue.length()) <=
				getMaxLineLength()) {

			log(
				nextMethodCallDetailAST, MSG_COMBINE_LITERAL_STRINGS,
				literalStringValue, nextLiteralStringValue);
		}
		else {
			int pos = getStringBreakPos(
				literalStringValue, nextLiteralStringValue,
				getMaxLineLength() - lineLength);

			if (pos != -1) {
				log(
					nextMethodCallDetailAST, MSG_MOVE_LITERAL_STRING,
					nextLiteralStringValue.substring(0, pos + 1), "previous");
			}
		}

		checkLiteralStringBreaks(
			nextMethodCallDetailAST, line,
			getLine(methodCallDetailAST.getLineNo()), literalStringValue,
			nextLiteralStringValue);
	}

	private void _checkPlusOperator(DetailAST parameterDetailAST) {
		if (parameterDetailAST.getType() != TokenTypes.PLUS) {
			return;
		}

		List<DetailAST> literalStringDetailASTList = getAllChildTokens(
			parameterDetailAST, true, TokenTypes.STRING_LITERAL);

		if (!literalStringDetailASTList.isEmpty()) {
			log(parameterDetailAST, MSG_INCORRECT_PLUS);
		}
	}

	private boolean _containsMethodCall(
		DetailAST detailAST, String variableName, String... methodNames) {

		for (String methodName : methodNames) {
			List<DetailAST> methodCallDetailASTList = getMethodCalls(
				detailAST, variableName, methodName);

			if (!methodCallDetailASTList.isEmpty()) {
				return true;
			}
		}

		return false;
	}

	private boolean _hasIncorrectLineBreaks(DetailAST methodCallDetailAST) {
		if (getStartLineNumber(methodCallDetailAST) != getEndLineNumber(
				methodCallDetailAST)) {

			log(methodCallDetailAST, _MSG_INCORRECT_LINE_BREAK);

			return true;
		}

		return false;
	}

	private static final String _MSG_INCORRECT_LINE_BREAK =
		"line.break.incorrect";

}