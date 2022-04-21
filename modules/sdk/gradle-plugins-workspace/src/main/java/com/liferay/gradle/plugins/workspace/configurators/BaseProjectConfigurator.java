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

package com.liferay.gradle.plugins.workspace.configurators;

import com.liferay.gradle.plugins.LiferayBasePlugin;
import com.liferay.gradle.plugins.extensions.AppServer;
import com.liferay.gradle.plugins.extensions.LiferayExtension;
import com.liferay.gradle.plugins.extensions.TomcatAppServer;
import com.liferay.gradle.plugins.workspace.ProjectConfigurator;
import com.liferay.gradle.plugins.workspace.WorkspaceExtension;
import com.liferay.gradle.plugins.workspace.WorkspacePlugin;
import com.liferay.gradle.plugins.workspace.internal.util.GradleUtil;
import com.liferay.gradle.util.Validator;

import java.io.File;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

import org.gradle.api.GradleException;
import org.gradle.api.NamedDomainObjectCollection;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.initialization.Settings;
import org.gradle.api.tasks.Copy;

/**
 * @author Andrea Di Giorgi
 * @author Gregory Amerson
 */
public abstract class BaseProjectConfigurator implements ProjectConfigurator {

	public BaseProjectConfigurator(Settings settings) {
		String defaultRootDirNames = GradleUtil.getProperty(
			settings, getDefaultRootDirPropertyName(), (String)null);
		File rootDir = settings.getRootDir();

		if (Validator.isNotNull(defaultRootDirNames)) {
			if (defaultRootDirNames.equals("*")) {
				_defaultRootDirs = Collections.singleton(rootDir);
			}
			else {
				_defaultRootDirs = new HashSet<>();

				for (String dirName : defaultRootDirNames.split("\\s*,\\s*")) {
					File dir = new File(rootDir, dirName);

					_defaultRootDirs.add(dir);
				}
			}
		}
		else {
			_defaultRootDirs = Collections.singleton(rootDir);
		}
	}

	@Override
	public void configureRootProject(
		Project project, WorkspaceExtension workspaceExtension) {
	}

	@Override
	public Iterable<File> getDefaultRootDirs() {
		return _defaultRootDirs;
	}

	@Override
	public Iterable<File> getProjectDirs(File rootDir) {
		try {
			if (!rootDir.exists()) {
				return Collections.emptySet();
			}

			return doGetProjectDirs(rootDir);
		}
		catch (Exception exception) {
			throw new GradleException(
				"Unable to get project directories from " + rootDir, exception);
		}
	}

	protected Copy addTaskDockerDeploy(
		Project project, Object sourcePath,
		WorkspaceExtension workspaceExtension) {

		Copy copy = GradleUtil.addTask(
			project, RootProjectConfigurator.DOCKER_DEPLOY_TASK_NAME,
			Copy.class);

		copy.from(sourcePath);

		final File dockerDir = workspaceExtension.getDockerDir();

		copy.into(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return new File(dockerDir, "deploy");
				}

			});

		copy.setDescription(
			"Assembles the project and deploys it to the Liferay Docker " +
				"container.");

		copy.setGroup(RootProjectConfigurator.DOCKER_GROUP);

		Task deployTask = GradleUtil.getTask(
			project, LiferayBasePlugin.DEPLOY_TASK_NAME);

		deployTask.finalizedBy(copy);

		Task buildDockerImageTask = GradleUtil.getTask(
			project.getRootProject(),
			RootProjectConfigurator.BUILD_DOCKER_IMAGE_TASK_NAME);

		buildDockerImageTask.dependsOn(deployTask);

		return copy;
	}

	protected void configureLiferay(
		Project project, WorkspaceExtension workspaceExtension) {

		LiferayExtension liferayExtension = GradleUtil.getExtension(
			project, LiferayExtension.class);

		liferayExtension.setAppServerParentDir(workspaceExtension.getHomeDir());

		String version = GradleUtil.getProperty(
			project, "app.server.tomcat.version", (String)null);

		File dir = workspaceExtension.getHomeDir();

		if ((version == null) && dir.exists()) {
			for (String fileName : dir.list()) {
				if (fileName.startsWith("tomcat-")) {
					version = fileName.substring(fileName.indexOf("-") + 1);

					NamedDomainObjectCollection<AppServer>
						namedDomainObjectCollection =
							liferayExtension.getAppServers();

					TomcatAppServer tomcatAppServer =
						(TomcatAppServer)namedDomainObjectCollection.getByName(
							"tomcat");

					tomcatAppServer.setVersion(version);
				}
			}
		}
	}

	protected abstract Iterable<File> doGetProjectDirs(File rootDir)
		throws Exception;

	protected String getDefaultRootDirName() {
		return getName();
	}

	protected String getDefaultRootDirPropertyName() {
		return WorkspacePlugin.PROPERTY_PREFIX + getName() + ".dir";
	}

	protected boolean isExcludedDirName(String dirName) {
		if (dirName == null) {
			return false;
		}

		if (dirName.equals(".gradle") || dirName.equals("build") ||
			dirName.equals("build_gradle") || dirName.equals("dist") ||
			dirName.equals("gradle") || dirName.equals("lxc") ||
			dirName.equals("node_modules") ||
			dirName.equals("node_modules_cache") || dirName.equals("src")) {

			return true;
		}

		return false;
	}

	private final Set<File> _defaultRootDirs;

}