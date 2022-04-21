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

package com.liferay.jenkins.results.parser.testray;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class TestrayCase {

	public TestrayCase(TestrayProject testrayProject, JSONObject jsonObject) {
		_testrayProject = testrayProject;
		_jsonObject = jsonObject;
	}

	public String getComponent() {
		JSONObject mainComponentJSONObject = _jsonObject.getJSONObject(
			"mainComponent");

		return mainComponentJSONObject.getString("name");
	}

	public String getID() {
		return _jsonObject.optString("testrayCaseId");
	}

	public String getName() {
		return _jsonObject.optString("name");
	}

	public int getPriority() {
		return _jsonObject.getInt("priority");
	}

	public String getTeamName() {
		return _jsonObject.getString("testrayTeamName");
	}

	public String getType() {
		return _jsonObject.getString("testrayCaseTypeName");
	}

	private final JSONObject _jsonObject;
	private final TestrayProject _testrayProject;

}