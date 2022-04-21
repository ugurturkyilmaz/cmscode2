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

package com.liferay.gradle.plugins.go.tasks;

import com.liferay.gradle.plugins.go.GoExtension;
import com.liferay.gradle.plugins.go.GoPlugin;
import com.liferay.gradle.plugins.go.internal.GoExecutor;
import com.liferay.gradle.util.GradleUtil;

import java.io.File;

import java.util.List;
import java.util.Map;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.TaskAction;

/**
 * @author Peter Shin
 */
@CacheableTask
public class ExecuteGoTask extends DefaultTask {

	public ExecuteGoTask() {
		_goExecutor = new GoExecutor(getProject());

		dependsOn(GoPlugin.DOWNLOAD_GO_TASK_NAME);

		GoExtension goExtension = GradleUtil.getExtension(
			getProject(), GoExtension.class);

		setGoDir(goExtension.getGoDir());
		setWorkingDir(goExtension.getWorkingDir());
	}

	public ExecuteGoTask args(Iterable<?> args) {
		_goExecutor.args(args);

		return this;
	}

	public ExecuteGoTask args(Object... args) {
		_goExecutor.args(args);

		return this;
	}

	public ExecuteGoTask environment(Map<?, ?> environment) {
		_goExecutor.environment(environment);

		return this;
	}

	public ExecuteGoTask environment(Object key, Object value) {
		_goExecutor.environment(key, value);

		return this;
	}

	@TaskAction
	public void executeGo() throws Exception {
		_goExecutor.execute();
	}

	public List<Object> getArgs() {
		return _goExecutor.getArgs();
	}

	public String getCommand() {
		return _goExecutor.getCommand();
	}

	public Map<?, ?> getEnvironment() {
		return _goExecutor.getEnvironment();
	}

	public File getGoDir() {
		return _goExecutor.getGoDir();
	}

	public File getWorkingDir() {
		return _goExecutor.getWorkingDir();
	}

	public boolean isInheritProxy() {
		return _goExecutor.isInheritProxy();
	}

	public boolean isUseGradleExec() {
		return _goExecutor.isUseGradleExec();
	}

	public void setArgs(Iterable<?> args) {
		_goExecutor.setArgs(args);
	}

	public void setArgs(Object... args) {
		_goExecutor.setArgs(args);
	}

	public void setCommand(Object command) {
		_goExecutor.setCommand(command);
	}

	public void setEnvironment(Map<?, ?> environment) {
		_goExecutor.setEnvironment(environment);
	}

	public void setGoDir(Object goDir) {
		_goExecutor.setGoDir(goDir);
	}

	public void setInheritProxy(boolean inheritProxy) {
		_goExecutor.setInheritProxy(inheritProxy);
	}

	public void setUseGradleExec(boolean useGradleExec) {
		_goExecutor.setUseGradleExec(useGradleExec);
	}

	public void setWorkingDir(Object workingDir) {
		_goExecutor.setWorkingDir(workingDir);
	}

	private final GoExecutor _goExecutor;

}