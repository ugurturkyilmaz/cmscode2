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

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class WorkspaceFactory {

	public static Workspace newWorkspace() {
		return newWorkspace("liferay-jenkins-ee", "master");
	}

	public static Workspace newWorkspace(JSONObject workspaceJSONObject) {
		String primaryRepositoryName = workspaceJSONObject.getString(
			"primary_repository_name");
		String primaryRepositoryDirName = workspaceJSONObject.getString(
			"primary_repository_dir_name");

		if (JenkinsResultsParserUtil.isNullOrEmpty(primaryRepositoryName) ||
			JenkinsResultsParserUtil.isNullOrEmpty(primaryRepositoryDirName)) {

			throw new RuntimeException("Invalid JSONObject");
		}

		Workspace workspace = _workspaces.get(primaryRepositoryDirName);

		if (workspace != null) {
			return workspace;
		}

		if (primaryRepositoryName.matches("com-liferay-.*")) {
			workspace = new SubrepositoryWorkspace(workspaceJSONObject);
		}
		else if (primaryRepositoryName.matches("liferay-plugins(-ee)?")) {
			workspace = new PluginsWorkspace(workspaceJSONObject);
		}
		else if (primaryRepositoryName.matches("liferay-portal(-ee)?")) {
			workspace = new PortalWorkspace(workspaceJSONObject);
		}
		else {
			workspace = new DefaultWorkspace(workspaceJSONObject);
		}

		_workspaces.put(primaryRepositoryDirName, workspace);

		return workspace;
	}

	public static Workspace newWorkspace(
		String repositoryName, String upstreamBranchName) {

		return newWorkspace(repositoryName, upstreamBranchName, null);
	}

	public static Workspace newWorkspace(
		String repositoryName, String upstreamBranchName, String jobName) {

		String gitDirectoryName = JenkinsResultsParserUtil.getGitDirectoryName(
			repositoryName, upstreamBranchName);

		BuildDatabase buildDatabase = BuildDatabaseUtil.getBuildDatabase();

		Workspace workspace = _workspaces.get(gitDirectoryName);

		if (workspace != null) {
			buildDatabase.putWorkspace(gitDirectoryName, workspace);

			return workspace;
		}

		if (buildDatabase.hasWorkspace(gitDirectoryName)) {
			workspace = buildDatabase.getWorkspace(gitDirectoryName);

			_workspaces.put(gitDirectoryName, workspace);

			return workspace;
		}

		if (repositoryName.matches("com-liferay-.*")) {
			workspace = new SubrepositoryWorkspace(
				repositoryName, upstreamBranchName, jobName);
		}
		else if (repositoryName.matches("liferay-plugins(-ee)?")) {
			workspace = new PluginsWorkspace(
				repositoryName, upstreamBranchName, jobName);
		}
		else if (repositoryName.matches("liferay-portal(-ee)?")) {
			workspace = new PortalWorkspace(
				repositoryName, upstreamBranchName, jobName);
		}
		else {
			workspace = new DefaultWorkspace(
				repositoryName, upstreamBranchName, jobName);
		}

		_workspaces.put(gitDirectoryName, workspace);

		buildDatabase.putWorkspace(gitDirectoryName, workspace);

		return workspace;
	}

	private static final Map<String, Workspace> _workspaces = new HashMap<>();

}