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

package com.liferay.talend.avro;

import com.liferay.talend.common.schema.constants.RejectSchemaConstants;
import com.liferay.talend.exception.BaseComponentException;

import java.io.IOException;

import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.IndexedRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.talend.components.api.component.runtime.Result;

/**
 * @author Igor Beslic
 */
public class RejectHandler extends IndexedRecordHandler {

	public RejectHandler(
		Boolean dieOnError, List<IndexedRecord> failedIndexedRecords,
		Schema rejectSchema, Result result) {

		_dieOnError = dieOnError;
		_failedIndexedRecords = failedIndexedRecords;
		_rejectSchema = rejectSchema;
		_result = result;
	}

	public void clearFailedIndexedRecords() {
		_failedIndexedRecords.clear();
	}

	public List<IndexedRecord> getFailedIndexedRecords() {
		return _failedIndexedRecords;
	}

	public void reject(IndexedRecord failedIndexedRecord, Exception exception)
		throws IOException {

		if (_dieOnError) {
			throw new IOException(exception);
		}

		_result.rejectCount++;

		if (failedIndexedRecord == null) {
			_logger.error("Unable to reject null instance of indexed record");

			return;
		}

		Schema failedIndexedRecordSchema = failedIndexedRecord.getSchema();

		List<Schema.Field> failedIndexedRecordSchemaFields =
			failedIndexedRecordSchema.getFields();

		IndexedRecord rejectIndexedRecord = new GenericData.Record(
			_rejectSchema);

		for (Schema.Field field : failedIndexedRecordSchemaFields) {
			updateField(
				_rejectSchema.getField(field.name()),
				failedIndexedRecord.get(field.pos()), rejectIndexedRecord);
		}

		updateField(
			_rejectSchema.getField(RejectSchemaConstants.FIELD_ERROR_MESSAGE),
			exception.getMessage(), rejectIndexedRecord);
		updateField(
			_rejectSchema.getField(RejectSchemaConstants.FIELD_ERROR_CODE),
			_getHttpStatus(exception), rejectIndexedRecord);

		_failedIndexedRecords.add(rejectIndexedRecord);
	}

	private int _getHttpStatus(Exception exception) {
		if (!(exception instanceof BaseComponentException)) {
			return 0;
		}

		BaseComponentException baseComponentException =
			(BaseComponentException)exception;

		return baseComponentException.getHttpStatus();
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		RejectHandler.class);

	private final Boolean _dieOnError;
	private final List<IndexedRecord> _failedIndexedRecords;
	private final Schema _rejectSchema;
	private final Result _result;

}