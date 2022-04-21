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

package com.liferay.arquillian.extension.junit.bridge.client;

import aQute.bnd.build.Project;
import aQute.bnd.build.ProjectBuilder;
import aQute.bnd.build.Workspace;
import aQute.bnd.osgi.Jar;

import com.liferay.arquillian.extension.junit.bridge.constants.Headers;
import com.liferay.arquillian.extension.junit.bridge.server.TestBundleActivator;
import com.liferay.arquillian.extension.junit.bridge.util.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.security.CodeSource;
import java.security.ProtectionDomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.regex.Pattern;

import org.osgi.framework.Constants;

/**
 * @author Shuyang Zhou
 */
public class BndBundleUtil {

	public static byte[] createBundle(
			Map<String, List<String>> filteredMethodNamesMap,
			String hostAddress, int port, long passCode)
		throws Exception {

		File buildDir = new File(System.getProperty("user.dir"));

		try (Workspace workspace = new Workspace(buildDir);
			Project project = new Project(workspace, buildDir);
			ProjectBuilder projectBuilder = _createProjectBuilder(
				project, filteredMethodNamesMap, hostAddress, port, passCode);
			Jar jar = projectBuilder.build()) {

			Manifest manifest = jar.getManifest();

			Attributes attributes = manifest.getMainAttributes();

			attributes.putValue(
				"Import-Package",
				StringUtil.merge(
					Arrays.asList(
						_versionPattern.split(
							attributes.getValue("Import-Package"))),
					","));

			if (Boolean.valueOf(
					System.getProperty("liferay.arquillian.copy.jar"))) {

				String symbolicName = attributes.getValue(
					Constants.BUNDLE_SYMBOLICNAME);

				Path path = Paths.get(
					buildDir.toString(), symbolicName.concat(".jar"));

				Files.deleteIfExists(path);

				Files.createFile(path);

				jar.write(path.toFile());
			}

			try (ByteArrayOutputStream byteArrayOutputStream =
					new ByteArrayOutputStream()) {

				jar.write(byteArrayOutputStream);

				return byteArrayOutputStream.toByteArray();
			}
		}
	}

	private static ProjectBuilder _createProjectBuilder(
			Project project, Map<String, List<String>> filteredMethodNamesMap,
			String hostAddress, int port, long passCode)
		throws Exception {

		if (filteredMethodNamesMap != null) {
			StringBuilder sb = new StringBuilder();

			for (Map.Entry<String, List<String>> entry :
					filteredMethodNamesMap.entrySet()) {

				sb.append(entry.getKey());
				sb.append(":");

				for (String methodName : entry.getValue()) {
					sb.append(methodName);
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);
				sb.append(";");
			}

			sb.setLength(sb.length() - 1);

			project.setProperty(
				Headers.TEST_BRIDGE_FILTERED_METHOD_NAMES, sb.toString());
		}

		project.setProperty(
			Headers.TEST_BRIDGE_REPORT_SERVER_HOST_NAME, hostAddress);
		project.setProperty(
			Headers.TEST_BRIDGE_REPORT_SERVER_PORT, String.valueOf(port));
		project.setProperty(
			Headers.TEST_BRIDGE_PASS_CODE, String.valueOf(passCode));
		project.setProperty(
			"Bundle-Activator", TestBundleActivator.class.getCanonicalName());

		Set<String> importPackages = new LinkedHashSet<>();

		importPackages.add("!aQute.bnd.build");
		importPackages.add("!aQute.bnd.osgi");

		String importPackageString = project.getProperty("Import-Package");

		if (importPackageString != null) {
			importPackages.addAll(StringUtil.split(importPackageString));
		}

		importPackages.add("*");

		project.setProperty(
			"Import-Package", StringUtil.merge(importPackages, ","));

		Set<String> includeResources = new LinkedHashSet<>();

		includeResources.add("test-classes/integration");

		ProtectionDomain protectionDomain =
			BndBundleUtil.class.getProtectionDomain();

		CodeSource codeSource = protectionDomain.getCodeSource();

		URL url = codeSource.getLocation();

		File file = new File(url.toURI());

		includeResources.add("@" + file.getName());

		String includeResourceString = project.getProperty("-includeresource");

		if (includeResourceString != null) {
			includeResources.addAll(StringUtil.split(includeResourceString));
		}

		project.setProperty(
			"-includeresource", StringUtil.merge(includeResources, ","));

		ProjectBuilder projectBuilder = new ProjectBuilder(project);

		projectBuilder.addClasspath(_getClassPathFiles());

		return projectBuilder;
	}

	private static List<File> _getClassPathFiles() {
		List<File> classPathFiles = new ArrayList<>();

		List<String> fileNames = StringUtil.split(
			System.getProperty("java.class.path"), File.pathSeparatorChar);

		for (String fileName : fileNames) {
			File file = new File(fileName);

			int length = fileName.length();

			if (file.isDirectory() ||
				fileName.regionMatches(true, length - 4, ".zip", 0, 4) ||
				fileName.regionMatches(true, length - 4, ".jar", 0, 4)) {

				classPathFiles.add(file);
			}
		}

		return classPathFiles;
	}

	private static final Pattern _versionPattern = Pattern.compile(
		";version=\"[\\[\\]\\(\\)0-9.,]*\",");

}