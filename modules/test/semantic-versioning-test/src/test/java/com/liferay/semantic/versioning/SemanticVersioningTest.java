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

package com.liferay.semantic.versioning;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Matthew Tambara
 */
@RunWith(Parameterized.class)
public class SemanticVersioningTest {

	@Parameterized.Parameters(name = "{0}")
	public static List<String> sampleData() {
		String modulesTestClassGroup = System.getProperty(
			"modules.test.class.group");

		Assert.assertFalse(
			"Missing system property \"modules.test.class.group\"",
			Validator.isNull(modulesTestClassGroup));

		Assert.assertFalse(
			"Missing system property \"project.dir\"",
			Validator.isNull(System.getProperty("project.dir")));

		modulesTestClassGroup = StringUtil.replace(
			modulesTestClassGroup, CharPool.COLON, CharPool.SLASH);
		modulesTestClassGroup = StringUtil.replace(
			modulesTestClassGroup, "/baseline", StringPool.BLANK);

		return Arrays.asList(
			StringUtil.split(modulesTestClassGroup, CharPool.SPACE));
	}

	public SemanticVersioningTest(String module) {
		_module = module;
	}

	@Test
	public void testSemanticVersioning() throws IOException {
		Path baselineLogPath = Paths.get(
			System.getProperty("project.dir"), "modules", _module, "build",
			"reports", "baseline", "baseline.log");

		boolean exist = Files.exists(baselineLogPath);

		String message = "";

		if (exist) {
			message = new String(Files.readAllBytes(baselineLogPath));
		}

		Assert.assertFalse(message, exist);
	}

	private final String _module;

}