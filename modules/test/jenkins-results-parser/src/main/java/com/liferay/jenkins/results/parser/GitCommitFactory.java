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

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class GitCommitFactory {

	public static GitHubRemoteGitCommit newGitHubRemoteGitCommit(
		String gitHubUsername, String gitRepositoryName, String sha) {

		String gitHubCommitURL = _getGitHubCommitURL(
			gitHubUsername, gitRepositoryName, sha);

		if (_gitHubRemoteGitCommits.containsKey(gitHubCommitURL)) {
			return _gitHubRemoteGitCommits.get(gitHubCommitURL);
		}

		try {
			return newGitHubRemoteGitCommit(
				gitHubUsername, gitRepositoryName, sha,
				JenkinsResultsParserUtil.toJSONObject(gitHubCommitURL));
		}
		catch (IOException ioException) {
			throw new RuntimeException(
				"Unable to get commit details", ioException);
		}
	}

	public static GitHubRemoteGitCommit newGitHubRemoteGitCommit(
		String gitHubUsername, String gitRepositoryName, String sha,
		JSONObject jsonObject) {

		JSONObject commitJSONObject = jsonObject.getJSONObject("commit");

		String message = commitJSONObject.getString("message");

		JSONObject committerJSONObject = commitJSONObject.getJSONObject(
			"committer");

		JSONArray filesJSONArray = jsonObject.optJSONArray("files");

		List<String> fileNames = null;

		if (filesJSONArray != null) {
			fileNames = new ArrayList<>(filesJSONArray.length());

			for (int i = 0; i < filesJSONArray.length(); i++) {
				JSONObject fileJSONObject = filesJSONArray.getJSONObject(i);

				fileNames.add(fileJSONObject.getString("filename"));
			}
		}

		try {
			DateFormat gitHubDateFormat =
				JenkinsResultsParserUtil.getGitHubDateFormat();

			Date date = gitHubDateFormat.parse(
				committerJSONObject.getString("date"));

			GitHubRemoteGitCommit remoteGitCommit = new GitHubRemoteGitCommit(
				committerJSONObject.getString("email"), gitHubUsername,
				gitRepositoryName, message, fileNames,
				jsonObject.getString("sha"), _getGitCommitType(message),
				date.getTime());

			_gitHubRemoteGitCommits.put(
				jsonObject.getString("url"), remoteGitCommit);

			return remoteGitCommit;
		}
		catch (ParseException parseException) {
			throw new RuntimeException(parseException);
		}
	}

	public static LocalGitCommit newLocalGitCommit(
		String emailAddress, GitWorkingDirectory gitWorkingDirectory,
		String message, String sha, long commitTime) {

		return new DefaultLocalGitCommit(
			emailAddress, gitWorkingDirectory, message, sha,
			_getGitCommitType(message), commitTime);
	}

	private static GitCommit.Type _getGitCommitType(String message) {
		if (message.startsWith("archive:ignore")) {
			return GitCommit.Type.LEGACY_ARCHIVE;
		}

		return GitCommit.Type.MANUAL;
	}

	private static String _getGitHubCommitURL(
		String gitHubUsername, String gitRepositoryName, String sha) {

		return JenkinsResultsParserUtil.getGitHubApiUrl(
			gitRepositoryName, gitHubUsername, "commits/" + sha);
	}

	private static final Map<String, GitHubRemoteGitCommit>
		_gitHubRemoteGitCommits = new HashMap<>();

}