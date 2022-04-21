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

package com.liferay.jenkins.results.parser;

import java.io.File;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class QAWebsitesWorkspaceGitRepository
	extends BaseWorkspaceGitRepository {

	@Override
	public void writePropertiesFiles() {
		_writeTestPropertiesFile();
	}

	protected QAWebsitesWorkspaceGitRepository(JSONObject jsonObject) {
		super(jsonObject);
	}

	protected QAWebsitesWorkspaceGitRepository(
		PullRequest pullRequest, String upstreamBranchName) {

		super(pullRequest, upstreamBranchName);
	}

	protected QAWebsitesWorkspaceGitRepository(
		RemoteGitRef remoteGitRef, String upstreamBranchName) {

		super(remoteGitRef, upstreamBranchName);
	}

	private void _writeTestPropertiesFile() {
		JenkinsResultsParserUtil.writePropertiesFile(
			new File(
				getDirectory(),
				JenkinsResultsParserUtil.combine(
					"test.", System.getenv("HOSTNAME"), ".properties")),
			getProperties("qa.websites.test.properties"), true);
	}

}