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

package com.liferay.portal.tools.soy.builder.commands;

import com.liferay.petra.string.StringBundler;

import java.io.File;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Andrea Di Giorgi
 */
@RunWith(Parameterized.class)
public class ReplaceSoyTranslationCommandTest extends BaseSoyCommandTestCase {

	@Parameterized.Parameters(name = "{0}")
	public static String[] getTestDirNames() {
		return new String[] {"metal_cli", "metal_cli_2"};
	}

	public ReplaceSoyTranslationCommandTest(String testDirName) {
		_testDirName = StringBundler.concat(
			"com/liferay/portal/tools/soy/builder/commands/dependencies",
			"/replace_soy_translation/", testDirName, "/");
	}

	@Override
	protected String getTestDirName() {
		return _testDirName;
	}

	@Override
	protected String[] getTestFileNames() {
		return new String[] {
			"footer.soy.js", "header.soy.js", "navigation.soy.js", "view.soy.js"
		};
	}

	@Override
	protected void testSoy(File dir) throws Exception {
		ReplaceSoyTranslationCommand replaceSoyTranslationCommand =
			new ReplaceSoyTranslationCommand();

		replaceSoyTranslationCommand.setDir(dir);

		replaceSoyTranslationCommand.execute();
	}

	private final String _testDirName;

}