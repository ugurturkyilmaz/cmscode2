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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hugo Huijser
 */
public class RedundantBranchingStatementCheck extends BaseCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {
			TokenTypes.CTOR_DEF, TokenTypes.LITERAL_FOR,
			TokenTypes.LITERAL_WHILE, TokenTypes.METHOD_DEF
		};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		if ((detailAST.getType() == TokenTypes.LITERAL_FOR) ||
			(detailAST.getType() == TokenTypes.LITERAL_WHILE)) {

			_checkRedundantBranchingStatements(
				detailAST, TokenTypes.LITERAL_CONTINUE);

			return;
		}

		if (detailAST.getType() == TokenTypes.METHOD_DEF) {
			DetailAST typeDetailAST = detailAST.findFirstToken(TokenTypes.TYPE);

			DetailAST firstChildDetailAST = typeDetailAST.getFirstChild();

			if (firstChildDetailAST.getType() != TokenTypes.LITERAL_VOID) {
				return;
			}
		}

		_checkRedundantBranchingStatements(
			detailAST, TokenTypes.LITERAL_RETURN);
	}

	private void _checkRedundantBranchingStatements(
		DetailAST detailAST, int branchingStatementType) {

		List<DetailAST> lastStatementDetailASTList =
			_getLastStatementDetailASTList(
				detailAST.findFirstToken(TokenTypes.SLIST));

		for (DetailAST lastStatementDetailAST : lastStatementDetailASTList) {
			if (lastStatementDetailAST.getType() != branchingStatementType) {
				continue;
			}

			DetailAST firstChildDetailAST =
				lastStatementDetailAST.getFirstChild();

			if ((firstChildDetailAST != null) &&
				(firstChildDetailAST.getType() == TokenTypes.SEMI)) {

				log(
					lastStatementDetailAST, _MSG_REDUNDANT_BRANCHING_STATEMENT,
					lastStatementDetailAST.getText());
			}
		}
	}

	private List<DetailAST> _getLastStatementDetailASTList(
		DetailAST slistDetailAST) {

		List<DetailAST> lastStatementDetailASTList = new ArrayList<>();

		if ((slistDetailAST == null) ||
			(slistDetailAST.getType() != TokenTypes.SLIST)) {

			return lastStatementDetailASTList;
		}

		DetailAST nextSiblingDetailAST = slistDetailAST.getNextSibling();

		while (true) {
			if (nextSiblingDetailAST == null) {
				break;
			}

			if (nextSiblingDetailAST.getType() == TokenTypes.LITERAL_CATCH) {
				lastStatementDetailASTList.addAll(
					_getLastStatementDetailASTList(
						nextSiblingDetailAST.findFirstToken(TokenTypes.SLIST)));

				nextSiblingDetailAST = nextSiblingDetailAST.getNextSibling();

				continue;
			}

			if (nextSiblingDetailAST.getType() == TokenTypes.LITERAL_ELSE) {
				DetailAST firstChildDetailAST =
					nextSiblingDetailAST.getFirstChild();

				if (firstChildDetailAST.getType() == TokenTypes.LITERAL_IF) {
					lastStatementDetailASTList.addAll(
						_getLastStatementDetailASTList(
							firstChildDetailAST.findFirstToken(
								TokenTypes.SLIST)));
				}
				else {
					lastStatementDetailASTList.addAll(
						_getLastStatementDetailASTList(firstChildDetailAST));
				}
			}

			break;
		}

		DetailAST lastChildDetailAST = slistDetailAST.getLastChild();

		DetailAST previousSiblingDetailAST =
			lastChildDetailAST.getPreviousSibling();

		if (previousSiblingDetailAST == null) {
			return lastStatementDetailASTList;
		}

		if ((previousSiblingDetailAST.getType() == TokenTypes.LITERAL_IF) ||
			(previousSiblingDetailAST.getType() == TokenTypes.LITERAL_TRY)) {

			lastStatementDetailASTList.addAll(
				_getLastStatementDetailASTList(
					previousSiblingDetailAST.findFirstToken(TokenTypes.SLIST)));
		}
		else {
			lastStatementDetailASTList.add(previousSiblingDetailAST);
		}

		return lastStatementDetailASTList;
	}

	private static final String _MSG_REDUNDANT_BRANCHING_STATEMENT =
		"branching.statement.redundant";

}