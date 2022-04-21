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

import com.liferay.talend.BaseTestCase;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.JsonObject;

/**
 * @author Igor Beslic
 */
public class LiferayFixedResponseContentSource extends LiferaySource {

	public LiferayFixedResponseContentSource(JsonObject jsonObject) {
		_jsonObject = jsonObject;
	}

	@Override
	public Optional<JsonObject> doGetRequest(String resourceURL) {
		if (_jsonObject != null) {
			return Optional.of(_jsonObject);
		}

		return Optional.of(
			_baseTestCase.readObject(_getResponseContentFileName(resourceURL)));
	}

	public void setBaseTestCase(BaseTestCase baseTestCase) {
		_baseTestCase = baseTestCase;
	}

	private String _getResponseContentFileName(String resourceURL) {
		Matcher matcher = _resourceURLPattern.matcher(resourceURL);

		if (!matcher.matches()) {
			throw new UnsupportedOperationException(
				"Unable to extract file name from given resource URL " +
					resourceURL);
		}

		String fileName = matcher.group(2);
		String fileNumber = matcher.group(3);

		StringBuilder sb = new StringBuilder();

		sb.append(fileName.replace('-', '_'));
		sb.append("_");
		sb.append(fileNumber.substring(fileNumber.indexOf("=") + 1));
		sb.append(".json");

		return sb.toString();
	}

	private static final Pattern _resourceURLPattern = Pattern.compile(
		"/o/.+/v\\d+(.\\d+)*/([^/\\s]+)/.+\\?.*(page=\\d+).*");

	private BaseTestCase _baseTestCase;
	private final JsonObject _jsonObject;

}