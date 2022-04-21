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

package com.liferay.source.formatter.processor;

import com.liferay.source.formatter.SourceFormatterArgs;
import com.liferay.source.formatter.util.FileUtil;
import com.liferay.source.formatter.util.ModulesPropertiesUtil;

import java.io.File;
import java.io.IOException;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public class BNDSourceProcessor extends BaseSourceProcessor {

	@Override
	protected List<String> doGetFileNames() throws IOException {
		return getFileNames(new String[0], getIncludes());
	}

	@Override
	protected String[] doGetIncludes() {
		return _INCLUDES;
	}

	@Override
	protected void postFormat() throws IOException {
		if (!_checkModulesPropertiesFile()) {
			return;
		}

		File modulesPropertiesFile = new File(
			getPortalDir(), "modules/modules.properties");

		String newContent = ModulesPropertiesUtil.getContent(getPortalDir());
		String oldContent = FileUtil.read(modulesPropertiesFile);

		if (!oldContent.equals(newContent)) {
			FileUtil.write(modulesPropertiesFile, newContent);

			System.out.println("Updated 'modules.properties'");
		}
	}

	private boolean _checkModulesPropertiesFile() {
		if (!isPortalSource()) {
			return false;
		}

		SourceFormatterArgs sourceFormatterArgs = getSourceFormatterArgs();

		List<String> fileExtensions = sourceFormatterArgs.getFileExtensions();

		if (!fileExtensions.contains("bnd")) {
			return false;
		}

		return true;
	}

	private static final String[] _INCLUDES = {"**/*.bnd"};

}