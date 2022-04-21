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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michael Hashimoto
 */
public class PullRequestPluginsTopLevelBuild
	extends PluginsTopLevelBuild implements PullRequestBuild {

	public PullRequestPluginsTopLevelBuild(
		String url, TopLevelBuild topLevelBuild) {

		super(url, topLevelBuild);

		StringBuilder sb = new StringBuilder();

		sb.append("https://github.com/");
		sb.append(getParameterValue("GITHUB_RECEIVER_USERNAME"));
		sb.append("/liferay-plugins");

		String branchName = getBranchName();

		if (!branchName.equals("master")) {
			sb.append("-ee");
		}

		sb.append("/pull/");
		sb.append(getParameterValue("GITHUB_PULL_REQUEST_NUMBER"));

		if (isFromArchive()) {
			_pullRequest = null;

			return;
		}

		_pullRequest = PullRequestFactory.newPullRequest(sb.toString());
	}

	@Override
	public String getBranchName() {
		String jobName = getJobName();

		return jobName.substring(
			jobName.indexOf("(") + 1, jobName.indexOf(")"));
	}

	@Override
	public String getPluginName() {
		for (Build downstreamBuild : getDownstreamBuilds(null)) {
			String jobVariant = downstreamBuild.getParameterValue(
				"JOB_VARIANT");

			if (jobVariant == null) {
				continue;
			}

			Matcher matcher = _pattern.matcher(jobVariant);

			if (!matcher.find()) {
				continue;
			}

			return matcher.group("pluginName");
		}

		return null;
	}

	@Override
	public PullRequest getPullRequest() {
		return _pullRequest;
	}

	@Override
	public String getTestSuiteName() {
		String ciTestSuite = getParameterValue("CI_TEST_SUITE");

		if (JenkinsResultsParserUtil.isNullOrEmpty(ciTestSuite)) {
			ciTestSuite = "default";
		}

		return ciTestSuite;
	}

	@Override
	public Workspace getWorkspace() {
		PullRequest pullRequest = getPullRequest();

		Workspace workspace = WorkspaceFactory.newWorkspace(
			pullRequest.getGitRepositoryName(),
			pullRequest.getUpstreamRemoteGitBranchName(), getJobName());

		if (workspace instanceof PortalWorkspace) {
			PortalWorkspace portalWorkspace = (PortalWorkspace)workspace;

			portalWorkspace.setBuildProfile(getBuildProfile());
		}

		WorkspaceGitRepository workspaceGitRepository =
			workspace.getPrimaryWorkspaceGitRepository();

		workspaceGitRepository.setGitHubURL(pullRequest.getHtmlURL());

		String senderBranchSHA = _getSenderBranchSHA();

		if (JenkinsResultsParserUtil.isSHA(senderBranchSHA)) {
			workspaceGitRepository.setSenderBranchSHA(senderBranchSHA);
		}

		String upstreamBranchSHA = _getUpstreamBranchSHA();

		if (JenkinsResultsParserUtil.isSHA(upstreamBranchSHA)) {
			workspaceGitRepository.setBaseBranchSHA(upstreamBranchSHA);
		}

		return workspace;
	}

	private String _getSenderBranchSHA() {
		String senderBranchSHA = getParameterValue("GITHUB_SENDER_BRANCH_SHA");

		if (JenkinsResultsParserUtil.isSHA(senderBranchSHA)) {
			return senderBranchSHA;
		}

		return null;
	}

	private String _getUpstreamBranchSHA() {
		String upstreamBranchSHA = getParameterValue(
			"GITHUB_UPSTREAM_BRANCH_SHA");

		if (JenkinsResultsParserUtil.isSHA(upstreamBranchSHA)) {
			return upstreamBranchSHA;
		}

		return null;
	}

	private static final Pattern _pattern = Pattern.compile(
		"[^/]*functional[^/]*/(?<pluginName>[^/]+)/\\d+");

	private final PullRequest _pullRequest;

}