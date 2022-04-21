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

package com.liferay.gradle.plugins.wsdl.builder;

import com.liferay.gradle.util.GradleUtil;

import groovy.lang.Closure;

import java.io.File;

import org.gradle.api.Project;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Nested;
import org.gradle.api.tasks.PathSensitive;
import org.gradle.api.tasks.PathSensitivity;
import org.gradle.api.tasks.SourceTask;

/**
 * @author Andrea Di Giorgi
 */
@CacheableTask
public class BuildWSDLTask extends SourceTask {

	public void generateOptions(Closure<?> closure) {
		Project project = getProject();

		project.configure(getGenerateOptions(), closure);
	}

	@Input
	public int getAxisVersion() {
		return GradleUtil.toInteger(_axisVersion);
	}

	@Input
	@PathSensitive(PathSensitivity.RELATIVE)
	public File getDestinationDir() {
		return GradleUtil.toFile(getProject(), _destinationDir);
	}

	@Nested
	public GenerateOptions getGenerateOptions() {
		return _generateOptions;
	}

	@Input
	public boolean isBuildLibs() {
		return _buildLibs;
	}

	@Input
	public boolean isIncludeSource() {
		return _includeSource;
	}

	@Input
	public boolean isIncludeWSDLs() {
		return _includeWSDLs;
	}

	public void setAxisVersion(Object axisVersion) {
		_axisVersion = axisVersion;
	}

	public void setBuildLibs(boolean buildLibs) {
		_buildLibs = buildLibs;
	}

	public void setDestinationDir(Object destinationDir) {
		_destinationDir = destinationDir;
	}

	public void setIncludeSource(boolean includeSource) {
		_includeSource = includeSource;
	}

	public void setIncludeWSDLs(boolean includeWSDLs) {
		_includeWSDLs = includeWSDLs;
	}

	private Object _axisVersion = 1;
	private boolean _buildLibs = true;
	private Object _destinationDir;
	private final GenerateOptions _generateOptions = new GenerateOptions();
	private boolean _includeSource = true;
	private boolean _includeWSDLs = true;

}