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

package com.liferay.talend.runtime;

import com.liferay.talend.properties.batch.LiferayBatchFileProperties;
import com.liferay.talend.runtime.writer.LiferayBatchFileWriteOperation;

import java.io.File;

import java.util.Collections;
import java.util.List;

import org.apache.avro.Schema;

import org.talend.components.api.component.runtime.Sink;
import org.talend.components.api.component.runtime.WriteOperation;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.daikon.NamedThing;
import org.talend.daikon.properties.ValidationResult;

/**
 * @author Igor Beslic
 */
public class LiferayBatchFileSink extends LiferaySourceOrSink implements Sink {

	@Override
	public WriteOperation<?> createWriteOperation() {
		return new LiferayBatchFileWriteOperation(this);
	}

	@Override
	public Schema getEndpointSchema(
		RuntimeContainer runtimeContainer, String schemaName) {

		return null;
	}

	public LiferayBatchFileProperties getLiferayBatchFileProperties() {
		return _liferayBatchFileProperties;
	}

	@Override
	public List<NamedThing> getSchemaNames(RuntimeContainer runtimeContainer) {
		return Collections.emptyList();
	}

	@Override
	public ValidationResult initialize(
		RuntimeContainer runtimeContainer,
		ComponentProperties componentProperties) {

		_liferayBatchFileProperties =
			(LiferayBatchFileProperties)componentProperties;

		return ValidationResult.OK;
	}

	@Override
	public ValidationResult validate(RuntimeContainer runtimeContainer) {
		String batchFilePath = _liferayBatchFileProperties.getBatchFilePath();

		if ((batchFilePath == null) || batchFilePath.isEmpty()) {
			return _getErrorValidationResult(
				"Please set a valid value for \"Bulk File Path\" field");
		}

		File file = new File(batchFilePath);

		File parentFile = file.getParentFile();

		if (!parentFile.exists()) {
			try {
				parentFile.mkdirs();
			}
			catch (SecurityException securityException) {
				return _getErrorValidationResult(
					"Unable to create batch file due security settings for " +
						parentFile.getAbsolutePath());
			}
		}

		if (!parentFile.canWrite()) {
			return _getErrorValidationResult(
				"Please set write permissions for file path " +
					file.getAbsolutePath());
		}

		return ValidationResult.OK;
	}

	@Override
	protected String getLiferayConnectionPropertiesPath() {
		return "resource." + super.getLiferayConnectionPropertiesPath();
	}

	private ValidationResult _getErrorValidationResult(String message) {
		return new ValidationResult(ValidationResult.Result.ERROR, message);
	}

	private LiferayBatchFileProperties _liferayBatchFileProperties;

}