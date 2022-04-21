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

package com.liferay.jenkins.results.parser.github.webhook;

import com.liferay.jenkins.results.parser.GitCommitFactory;
import com.liferay.jenkins.results.parser.GitHubRemoteGitCommit;
import com.liferay.jenkins.results.parser.GitHubRemoteGitRepository;
import com.liferay.jenkins.results.parser.GitRepositoryFactory;
import com.liferay.jenkins.results.parser.GitUtil;
import com.liferay.jenkins.results.parser.RemoteGitBranch;

import java.io.File;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * @author Peter Yoo
 */
public class PushEventPayload extends Payload {

	public PushEventPayload(JSONObject jsonObject) {
		super(jsonObject);

		gitHubRemoteGitRepository =
			(GitHubRemoteGitRepository)
				GitRepositoryFactory.getRemoteGitRepository(
					"github.com", get("repository/name"),
					get("repository/owner/login"));
	}

	public String getAfterSHA() {
		return get("after");
	}

	public String getBeforeSHA() {
		return get("before");
	}

	public GitHubRemoteGitCommit getHeadGitHubRemoteGitCommit() {
		if (headGitHubRemoteGitCommit == null) {
			headGitHubRemoteGitCommit =
				GitCommitFactory.newGitHubRemoteGitCommit(
					gitHubRemoteGitRepository.getUsername(),
					gitHubRemoteGitRepository.getName(), getAfterSHA());
		}

		return headGitHubRemoteGitCommit;
	}

	public RemoteGitBranch getPusherRemoteGitBranch() {
		if (pusherRemoteGitBranch == null) {
			pusherRemoteGitBranch = GitUtil.getRemoteGitBranch(
				getPusherBranchName(), new File("."),
				gitHubRemoteGitRepository.getRemoteURL());
		}

		return pusherRemoteGitBranch;
	}

	public GitHubRemoteGitRepository getRemoteGitRepository() {
		return gitHubRemoteGitRepository;
	}

	protected String getPusherBranchName() {
		Matcher matcher = _branchPattern.matcher(get("ref"));

		if (matcher.matches()) {
			return matcher.group(1);
		}

		return null;
	}

	protected GitHubRemoteGitRepository gitHubRemoteGitRepository;
	protected GitHubRemoteGitCommit headGitHubRemoteGitCommit;
	protected RemoteGitBranch pusherRemoteGitBranch;

	private static final Pattern _branchPattern = Pattern.compile(
		"refs/heads/(.*)");

}