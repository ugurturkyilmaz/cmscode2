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

package com.liferay.project.templates.service.builder;

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
 * @author Gregory Amerson
 * @author Lawrence Lee
 */
@RunWith(Parameterized.class)
public class ProjectTemplatesServiceBuilderWorkspaceTest
	implements BaseProjectTemplatesTestCase {

	@ClassRule
	public static final MavenExecutor mavenExecutor = new MavenExecutor();

	@Parameterized.Parameters(
		name = "Testcase-{index}: testing {0}, {1}, {2}, {3}"
	)
	public static Iterable<Object[]> data() {
		return Arrays.asList(
			new Object[][] {
				{
					"spring", "guestbook", "com.liferay.docs.guestbook",
					"7.0.6-2"
				},
				{
					"spring", "guestbook", "com.liferay.docs.guestbook",
					"7.1.3-1"
				},
				{"ds", "guestbook", "com.liferay.docs.guestbook", "7.2.1-1"},
				{"ds", "guestbook", "com.liferay.docs.guestbook", "7.3.7"},
				{"ds", "guestbook", "com.liferay.docs.guestbook", "7.4.3.4"},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.0.6-2"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.1.3-1"
				},
				{
					"ds", "backend-integration", "com.liferay.docs.guestbook",
					"7.2.1-1"
				},
				{
					"ds", "backend-integration", "com.liferay.docs.guestbook",
					"7.3.7"
				},
				{
					"ds", "backend-integration", "com.liferay.docs.guestbook",
					"7.4.3.4"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.2.1-1"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.3.7"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.4.3.4"
				},
				{"spring", "sample", "com.test.sample", "7.0.6-2"},
				{"spring", "sample", "com.test.sample", "7.1.3-1"},
				{"ds", "sample", "com.test.sample", "7.2.1-1"},
				{"ds", "sample", "com.test.sample", "7.3.7"},
				{"ds", "sample", "com.test.sample", "7.4.3.4"}
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

	public ProjectTemplatesServiceBuilderWorkspaceTest(
		String dependencyInjector, String name, String packageName,
		String liferayVersion) {

		_dependencyInjector = dependencyInjector;
		_name = name;
		_packageName = packageName;
		_liferayVersion = liferayVersion;
	}

	@Test
	public void testBuildTemplateServiceBuilderWorkspace() throws Exception {
		String template = "service-builder";

		File gradleWorkspaceDir = buildWorkspace(
			temporaryFolder, "gradle", "gradleWS", _liferayVersion,
			mavenExecutor);

		if (_liferayVersion.startsWith("7.0")) {
			writeGradlePropertiesInWorkspace(
				gradleWorkspaceDir,
				"liferay.workspace.target.platform.version=7.0.6-2");
		}
		else if (_liferayVersion.startsWith("7.1")) {
			writeGradlePropertiesInWorkspace(
				gradleWorkspaceDir,
				"liferay.workspace.target.platform.version=7.1.3-1");
		}
		else if (_liferayVersion.startsWith("7.2")) {
			writeGradlePropertiesInWorkspace(
				gradleWorkspaceDir,
				"liferay.workspace.target.platform.version=7.2.1-1");
		}
		else if (_liferayVersion.startsWith("7.3")) {
			writeGradlePropertiesInWorkspace(
				gradleWorkspaceDir,
				"liferay.workspace.target.platform.version=7.3.7");
		}
		else if (_liferayVersion.startsWith("7.4")) {
			writeGradlePropertiesInWorkspace(
				gradleWorkspaceDir,
				"liferay.workspace.target.platform.version=7.4.3.4");
		}

		File gradleWorkspaceModulesDir = new File(
			gradleWorkspaceDir, "modules");

		if (_name.contains("sample")) {
			gradleWorkspaceModulesDir = new File(
				gradleWorkspaceDir, "modules/nested/path");

			Assert.assertTrue(gradleWorkspaceModulesDir.mkdirs());
		}

		File gradleProjectDir = buildTemplateWithGradle(
			gradleWorkspaceModulesDir, template, _name, "--package-name",
			_packageName, "--dependency-injector", _dependencyInjector,
			"--liferay-version", _liferayVersion);

		if (_name.contains("sample")) {
			testContains(
				gradleProjectDir, "sample-service/build.gradle",
				"compile project(\":modules:nested:path:sample:sample-api\")");
		}

		if (_dependencyInjector.equals("ds")) {
			testContains(
				gradleProjectDir, _name + "-service/service.xml",
				"dependency-injector=\"ds\"");
			testContains(
				gradleProjectDir, _name + "-service/bnd.bnd",
				"-dsannotations-options: inherit");
		}

		if (_dependencyInjector.equals("spring")) {
			testNotContains(
				gradleProjectDir, _name + "-service/bnd.bnd",
				"-dsannotations-options: inherit");
			testNotContains(
				gradleProjectDir, _name + "-service/service.xml",
				"dependency-injector=\"ds\"");
		}

		if (_liferayVersion.startsWith("7.0") ||
			_liferayVersion.startsWith("7.1")) {

			testContains(
				gradleProjectDir, _name + "-api/build.gradle",
				DEPENDENCY_PORTAL_KERNEL, "biz.aQute.bnd.annotation");
			testContains(
				gradleProjectDir, _name + "-service/build.gradle",
				DEPENDENCY_PORTAL_KERNEL, "biz.aQute.bnd.annotation");

			testNotContains(
				gradleProjectDir, _name + "-api/build.gradle",
				"org.osgi.annotation.versioning");
			testNotContains(
				gradleProjectDir, _name + "-service/build.gradle",
				"org.osgi.annotation.versioning");
		}
		else if (_liferayVersion.startsWith("7.2")) {
			testContains(
				gradleProjectDir, _name + "-api/build.gradle",
				DEPENDENCY_PORTAL_KERNEL, "org.osgi.annotation.versioning");
			testContains(
				gradleProjectDir, _name + "-service/build.gradle",
				DEPENDENCY_PORTAL_KERNEL, "org.osgi.annotation.versioning");

			testNotContains(
				gradleProjectDir, _name + "-api/build.gradle", "biz.aQute.bnd");
			testNotContains(
				gradleProjectDir, _name + "-service/build.gradle",
				"biz.aQute.bnd");
		}
		else {
			testContains(
				gradleProjectDir, _name + "-api/build.gradle",
				DEPENDENCY_RELEASE_PORTAL_API);
			testContains(
				gradleProjectDir, _name + "-service/build.gradle",
				DEPENDENCY_RELEASE_PORTAL_API);

			testNotContains(
				gradleProjectDir, _name + "-api/build.gradle", "biz.aQute.bnd");
			testNotContains(
				gradleProjectDir, _name + "-service/build.gradle",
				"biz.aQute.bnd");
		}

		File gradleUADModuleDir = new File(gradleProjectDir, _name + "-uad");

		testNotExists(gradleUADModuleDir, "bnd.bnd");

		File mavenWorkspaceDir = buildWorkspace(
			temporaryFolder, "maven", "mavenWS", _liferayVersion,
			mavenExecutor);

		File mavenModulesDir = new File(mavenWorkspaceDir, "modules");

		if (_name.contains("sample")) {
			mavenModulesDir = new File(
				mavenWorkspaceDir, "modules/nested/path");

			Assert.assertTrue(mavenModulesDir.mkdirs());
		}

		File mavenProjectDir = buildTemplateWithMaven(
			mavenModulesDir, mavenModulesDir, template, _name, "com.test",
			mavenExecutor, "-Dpackage=" + _packageName,
			"-DdependencyInjector=" + _dependencyInjector,
			"-DliferayVersion=" + _liferayVersion);

		File mavenUADModuleDir = new File(mavenProjectDir, _name + "-uad");

		testNotExists(mavenUADModuleDir, "bnd.bnd");

		if (isBuildProjects()) {
			String projectPath;

			if (_name.contains("sample")) {
				projectPath = ":modules:nested:path:sample";
			}
			else {
				projectPath = ":modules:" + _name;
			}

			testBuildTemplateServiceBuilder(
				gradleProjectDir, mavenProjectDir, gradleWorkspaceDir, _name,
				_packageName, projectPath, _gradleDistribution, mavenExecutor);
		}
	}

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private static URI _gradleDistribution;

	private final String _dependencyInjector;
	private final String _liferayVersion;
	private final String _name;
	private final String _packageName;

}