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
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Hugo Huijser
 */
public class JSONUtilCheck extends BaseChainedMethodCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.ASSIGN, TokenTypes.METHOD_CALL};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		if (detailAST.getType() == TokenTypes.METHOD_CALL) {
			_checkChainedPutCalls(detailAST);
			_checkStringValueOfCalls(detailAST);

			return;
		}

		DetailAST parentDetailAST = detailAST.getParent();

		if ((parentDetailAST.getType() != TokenTypes.EXPR) &&
			(parentDetailAST.getType() != TokenTypes.VARIABLE_DEF)) {

			return;
		}

		DetailAST nextSiblingDetailAST = parentDetailAST.getNextSibling();

		if ((nextSiblingDetailAST == null) ||
			(nextSiblingDetailAST.getType() != TokenTypes.SEMI)) {

			return;
		}

		DetailAST methodCallDetailAST = _getMethodCallDetailAST(
			detailAST, parentDetailAST);

		if (methodCallDetailAST == null) {
			return;
		}

		DetailAST elistDetailAST = methodCallDetailAST.findFirstToken(
			TokenTypes.ELIST);

		if (elistDetailAST.getChildCount() > 0) {
			return;
		}

		DetailAST firstChildDetailAST = methodCallDetailAST.getFirstChild();

		FullIdent fullIdent1 = FullIdent.createFullIdent(firstChildDetailAST);

		String methodName = fullIdent1.getText();

		if (!methodName.equals("JSONFactoryUtil.createJSONArray") &&
			!methodName.equals("JSONFactoryUtil.createJSONObject")) {

			return;
		}

		String variableName = getVariableName(detailAST, parentDetailAST);

		if (variableName == null) {
			return;
		}

		while (true) {
			nextSiblingDetailAST = nextSiblingDetailAST.getNextSibling();

			if (nextSiblingDetailAST == null) {
				return;
			}

			FullIdent fullIdent2 = getMethodCallFullIdent(
				nextSiblingDetailAST, variableName, "put");

			if (fullIdent2 != null) {
				log(
					detailAST, _MSG_USE_JSON_UTIL_PUT, methodName,
					fullIdent1.getLineNo(), variableName + ".put",
					fullIdent2.getLineNo(), "JSONUtil.put");
			}

			if (containsVariableName(
					nextSiblingDetailAST, variableName, true)) {

				return;
			}
		}
	}

	private void _checkChainedPutCalls(DetailAST methodCallDetailAST) {
		DetailAST firstChildDetailAST = methodCallDetailAST.getFirstChild();

		if (firstChildDetailAST.getType() != TokenTypes.DOT) {
			return;
		}

		FullIdent fullIdent = FullIdent.createFullIdent(firstChildDetailAST);

		if (!Objects.equals(fullIdent.getText(), "JSONUtil.put")) {
			return;
		}

		DetailAST elistDetailAST = methodCallDetailAST.findFirstToken(
			TokenTypes.ELIST);

		if (elistDetailAST.getChildCount() != 1) {
			return;
		}

		DetailAST parentDetailAST = methodCallDetailAST.getParent();

		if (parentDetailAST.getType() != TokenTypes.DOT) {
			return;
		}

		parentDetailAST = parentDetailAST.getParent();

		if (parentDetailAST.getType() != TokenTypes.METHOD_CALL) {
			return;
		}

		DetailAST nextSiblingDetailAST = methodCallDetailAST.getNextSibling();

		if ((nextSiblingDetailAST.getType() == TokenTypes.IDENT) &&
			Objects.equals(nextSiblingDetailAST.getText(), "put")) {

			log(methodCallDetailAST, _MSG_USE_JSON_UTIL_PUT_ALL);
		}
	}

	private void _checkStringValueOfCalls(DetailAST detailAST) {
		DetailAST firstChildDetailAST = detailAST.getFirstChild();

		if (firstChildDetailAST.getType() != TokenTypes.DOT) {
			return;
		}

		FullIdent fullIdent = FullIdent.createFullIdent(firstChildDetailAST);

		if (!Objects.equals(fullIdent.getText(), "String.valueOf")) {
			return;
		}

		DetailAST elistDetailAST = detailAST.findFirstToken(TokenTypes.ELIST);

		if (elistDetailAST == null) {
			return;
		}

		firstChildDetailAST = elistDetailAST.getFirstChild();

		if ((firstChildDetailAST == null) ||
			(firstChildDetailAST.getType() != TokenTypes.EXPR)) {

			return;
		}

		firstChildDetailAST = firstChildDetailAST.getFirstChild();

		if ((firstChildDetailAST == null) ||
			(firstChildDetailAST.getType() != TokenTypes.METHOD_CALL)) {

			return;
		}

		List<DetailAST> methodCallDetailASTList = getAllChildTokens(
			firstChildDetailAST, true, TokenTypes.METHOD_CALL);

		if (methodCallDetailASTList.isEmpty()) {
			firstChildDetailAST = firstChildDetailAST.getFirstChild();

			if (firstChildDetailAST.getType() != TokenTypes.DOT) {
				return;
			}

			fullIdent = FullIdent.createFullIdent(firstChildDetailAST);

			if (Objects.equals(fullIdent.getText(), "JSONUtil.put") ||
				Objects.equals(fullIdent.getText(), "JSONUtil.putAll")) {

				log(detailAST, _MSG_USE_JSON_UTIL_TO_STRING);
			}

			return;
		}

		List<String> chainedMethodNames = new ArrayList<>();

		for (DetailAST methodCallDetailAST : methodCallDetailASTList) {
			DetailAST dotDetailAST = methodCallDetailAST.findFirstToken(
				TokenTypes.DOT);

			if (dotDetailAST != null) {
				List<DetailAST> childMethodCallDetailASTList =
					getAllChildTokens(
						dotDetailAST, false, TokenTypes.METHOD_CALL);

				if (!childMethodCallDetailASTList.isEmpty()) {
					continue;
				}
			}

			BaseCheck.ChainInformation chainInformation = getChainInformation(
				methodCallDetailAST);

			chainedMethodNames = chainInformation.getMethodNames();
		}

		for (String chainedMethodName : chainedMethodNames) {
			if (!chainedMethodName.equals("put") &&
				!chainedMethodName.equals("putAll")) {

				return;
			}
		}

		DetailAST methodCallDetailAST = methodCallDetailASTList.get(
			methodCallDetailASTList.size() - 1);

		firstChildDetailAST = methodCallDetailAST.getFirstChild();

		if (firstChildDetailAST.getType() != TokenTypes.DOT) {
			return;
		}

		fullIdent = FullIdent.createFullIdent(firstChildDetailAST);

		String methodCall = fullIdent.getText();

		if (methodCall.startsWith("JSONUtil.")) {
			log(detailAST, _MSG_USE_JSON_UTIL_TO_STRING);
		}
	}

	private DetailAST _getMethodCallDetailAST(
		DetailAST assignDetailAST, DetailAST parentDetailAST) {

		DetailAST firstChildDetailAST = assignDetailAST.getFirstChild();

		DetailAST assignValueDetailAST = null;

		if (parentDetailAST.getType() == TokenTypes.EXPR) {
			assignValueDetailAST = firstChildDetailAST.getNextSibling();
		}
		else {
			assignValueDetailAST = firstChildDetailAST.getFirstChild();
		}

		if ((assignValueDetailAST != null) &&
			(assignValueDetailAST.getType() == TokenTypes.METHOD_CALL)) {

			return assignValueDetailAST;
		}

		return null;
	}

	private static final String _MSG_USE_JSON_UTIL_PUT = "json.util.put.use";

	private static final String _MSG_USE_JSON_UTIL_PUT_ALL =
		"json.util.put.all.use";

	private static final String _MSG_USE_JSON_UTIL_TO_STRING =
		"json.util.to.string.use";

}