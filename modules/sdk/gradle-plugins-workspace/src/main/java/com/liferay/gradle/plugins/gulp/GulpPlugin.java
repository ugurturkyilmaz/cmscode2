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

package com.liferay.gradle.plugins.gulp;

import com.liferay.gradle.plugins.node.NodePlugin;
import com.liferay.gradle.util.GradleUtil;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Rule;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskContainer;

/**
 * @author     David Truong
 * @author     Andrea Di Giorgi
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class GulpPlugin implements Plugin<Project> {

	public static final String EXTENSION_NAME = "gulp";

	@Override
	public void apply(Project project) {
		GradleUtil.applyPlugin(project, NodePlugin.class);

		Task npmInstallTask = GradleUtil.getTask(
			project, NodePlugin.NPM_INSTALL_TASK_NAME);

		_addTaskRuleGulp(project, npmInstallTask);
	}

	private ExecuteGulpTask _addTaskExecuteGulp(
		Project project, String taskName, Task npmInstallTask) {

		ExecuteGulpTask executeGulpTask = GradleUtil.addTask(
			project, taskName, ExecuteGulpTask.class);

		executeGulpTask.dependsOn(npmInstallTask);

		char gulpCommandFirstChar = taskName.charAt(4);

		String gulpCommand =
			Character.toLowerCase(gulpCommandFirstChar) + taskName.substring(5);

		executeGulpTask.setGulpCommand(gulpCommand);

		return executeGulpTask;
	}

	private void _addTaskRuleGulp(
		final Project project, final Task npmInstallTask) {

		TaskContainer taskContainer = project.getTasks();

		taskContainer.addRule(
			new Rule() {

				@Override
				public void apply(String taskName) {
					if (taskName.startsWith("gulp")) {
						_addTaskExecuteGulp(project, taskName, npmInstallTask);
					}
				}

				@Override
				public String getDescription() {
					return "Pattern: gulp<Task>: Executes a named Gulp task.";
				}

			});
	}

}