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

package com.liferay.source.formatter.check;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.source.formatter.check.util.SourceUtil;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author Alan Huang
 */
public class PropertiesArchivedModulesCheck extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		if (!fileName.endsWith("/test.properties")) {
			return content;
		}

		List<String> archivedModuleDirectoryNames =
			_getArchivedModuleDirectoryNames();

		Properties properties = new Properties();

		properties.load(new StringReader(content));

		Enumeration<String> enumeration =
			(Enumeration<String>)properties.propertyNames();

		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();

			if (!key.contains("test.batch.class.names.includes")) {
				continue;
			}

			String value = properties.getProperty(key);

			if (Validator.isNull(value)) {
				continue;
			}

			List<String> propertyValues = ListUtil.fromString(
				value, StringPool.COMMA);

			for (String propertyValue : propertyValues) {
				if (!propertyValue.startsWith("**/")) {
					continue;
				}

				int x = propertyValue.indexOf(CharPool.SLASH, 3);

				if (x == -1) {
					continue;
				}

				String moduleName = propertyValue.substring(3, x);

				if (archivedModuleDirectoryNames.contains(moduleName)) {
					addMessage(
						fileName,
						StringBundler.concat(
							"Remove dependency '", moduleName,
							"' in property '", key,
							"', since it points to an archived module"));
				}
			}
		}

		return content;
	}

	private synchronized List<String> _getArchivedModuleDirectoryNames()
		throws IOException {

		if (_archivedModuleDirectoryNames != null) {
			return _archivedModuleDirectoryNames;
		}

		_archivedModuleDirectoryNames = new ArrayList<>();

		File modulesDir = new File(getPortalDir(), "modules");

		Files.walkFileTree(
			modulesDir.toPath(), EnumSet.noneOf(FileVisitOption.class), 15,
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(
					Path dirPath, BasicFileAttributes basicFileAttributes) {

					String moduleDirectoryName = _getModuleDirectoryName(
						dirPath);

					if (moduleDirectoryName == null) {
						return FileVisitResult.CONTINUE;
					}

					_archivedModuleDirectoryNames.add(moduleDirectoryName);

					return FileVisitResult.SKIP_SUBTREE;
				}

			});

		return _archivedModuleDirectoryNames;
	}

	private String _getModuleDirectoryName(Path dirPath) {
		String absolutePath = SourceUtil.getAbsolutePath(dirPath);

		int x = absolutePath.indexOf("/modules/apps/archived/");

		if (x == -1) {
			return null;
		}

		String directoryPath = absolutePath.substring(x + 23);

		if (!directoryPath.contains(StringPool.SLASH)) {
			return directoryPath;
		}

		return null;
	}

	private List<String> _archivedModuleDirectoryNames;

}