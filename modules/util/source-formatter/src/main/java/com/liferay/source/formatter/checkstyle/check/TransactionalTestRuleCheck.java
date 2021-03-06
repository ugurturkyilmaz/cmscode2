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

/**
 * @author Hugo Huijser
 */
public class TransactionalTestRuleCheck extends BaseCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.IMPORT};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		String line = getLine(detailAST.getLineNo());

		if (!line.contains(
				"import com.liferay.portal.test.rule.TransactionalTestRule;")) {

			return;
		}

		String absolutePath = getAbsolutePath();

		if (absolutePath.endsWith("StagedModelDataHandlerTest.java")) {
			log(detailAST, _MSG_INVALID_IMPORT);
		}
	}

	private static final String _MSG_INVALID_IMPORT = "import.invalid";

}