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

package com.liferay.project.templates.layout.template;

import com.liferay.maven.executor.MavenExecutor;
import com.liferay.project.templates.BaseProjectTemplatesTestCase;
import com.liferay.project.templates.extensions.util.Validator;
import com.liferay.project.templates.util.FileTestUtil;

import java.io.File;

import java.net.URI;

import java.util.Arrays;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Lawrence Lee
 */
@RunWith(Parameterized.class)
public class ProjectTemplatesLayoutTemplatesTest
	implements BaseProjectTemplatesTestCase {

	@ClassRule
	public static final MavenExecutor mavenExecutor = new MavenExecutor();

	@Parameterized.Parameters(name = "Testcase-{index}: testing {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(
			new Object[][] {
				{"7.0.6-2"}, {"7.1.3-1"}, {"7.2.1-1"}, {"7.3.7"}, {"7.4.1-1"}
			});
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		String gradleDistribution = System.getProperty("gradle.distribution");

		if (Validator.isNull(gradleDistribution)) {
			Properties properties = FileTestUtil.readProperties(
				"gradle-wrapper/gradle/wrapper/gradle-wrapper.properties");

			gradleDistribution = properties.getProperty("distributionUrl");
		}

		Assert.assertTrue(gradleDistribution.contains(GRADLE_WRAPPER_VERSION));

		_gradleDistribution = URI.create(gradleDistribution);
	}

	public ProjectTemplatesLayoutTemplatesTest(String liferayVersion) {
		_liferayVersion = liferayVersion;
	}

	@Test
	public void testBuildTemplateLayoutTemplate() throws Exception {
		String template = "layout-template";
		String name = "foo";

		File gradleWorkspaceDir = buildWorkspace(
			temporaryFolder, "gradle", "gradleWS", _liferayVersion,
			mavenExecutor);

		File gradleWorkspaceModulesDir = new File(
			gradleWorkspaceDir, "modules");

		File gradleProjectDir = buildTemplateWithGradle(
			gradleWorkspaceModulesDir, template, name, "--liferay-version",
			_liferayVersion);

		if (_liferayVersion.startsWith("7.0")) {
			testContains(
				gradleProjectDir,
				"src/main/webapp/WEB-INF/liferay-layout-templates.xml",
				"liferay-layout-templates_7_0_0.dtd");
		}
		else if (_liferayVersion.startsWith("7.1")) {
			testContains(
				gradleProjectDir,
				"src/main/webapp/WEB-INF/liferay-layout-templates.xml",
				"liferay-layout-templates_7_1_0.dtd");
		}
		else if (_liferayVersion.startsWith("7.2")) {
			testContains(
				gradleProjectDir,
				"src/main/webapp/WEB-INF/liferay-layout-templates.xml",
				"liferay-layout-templates_7_2_0.dtd");
		}
		else if (_liferayVersion.startsWith("7.3")) {
			testContains(
				gradleProjectDir,
				"src/main/webapp/WEB-INF/liferay-layout-templates.xml",
				"liferay-layout-templates_7_3_0.dtd");
		}
		else if (_liferayVersion.startsWith("7.4")) {
			testContains(
				gradleProjectDir,
				"src/main/webapp/WEB-INF/liferay-layout-templates.xml",
				"liferay-layout-templates_7_4_0.dtd");
		}

		testExists(gradleProjectDir, "src/main/webapp/foo.png");

		testContains(
			gradleProjectDir, "src/main/webapp/foo.ftl", "class=\"foo\"");
		testContains(
			gradleProjectDir,
			"src/main/webapp/WEB-INF/liferay-layout-templates.xml",
			"<layout-template id=\"foo\" name=\"foo\">",
			"<template-path>/foo.ftl</template-path>",
			"<thumbnail-path>/foo.png</thumbnail-path>");
		testContains(
			gradleProjectDir,
			"src/main/webapp/WEB-INF/liferay-plugin-package.properties",
			"name=foo");

		testNotContains(gradleProjectDir, "build.gradle", "version: \"[0-9].*");

		File mavenWorkspaceDir = buildWorkspace(
			temporaryFolder, "maven", "mavenWS", _liferayVersion,
			mavenExecutor);

		File mavenModulesDir = new File(mavenWorkspaceDir, "modules");

		File mavenProjectDir = buildTemplateWithMaven(
			mavenModulesDir, mavenModulesDir, template, name, "com.test",
			mavenExecutor, "-DliferayVersion=" + _liferayVersion);

		createNewFiles(
			"src/main/resources/.gitkeep", gradleProjectDir, mavenProjectDir);

		if (isBuildProjects()) {
			File gradleOutputDir = new File(gradleProjectDir, "build/libs");
			File mavenOutputDir = new File(mavenProjectDir, "target");

			buildProjects(
				_gradleDistribution, mavenExecutor, gradleWorkspaceDir,
				mavenProjectDir, gradleOutputDir, mavenOutputDir,
				":modules:" + name + GRADLE_TASK_PATH_BUILD);
		}
	}

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private static URI _gradleDistribution;

	private final String _liferayVersion;

}