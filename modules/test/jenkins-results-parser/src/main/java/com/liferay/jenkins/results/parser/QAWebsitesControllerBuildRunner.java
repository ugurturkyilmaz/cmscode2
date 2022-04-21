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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class QAWebsitesControllerBuildRunner
	extends BaseBuildRunner<BuildData> {

	@Override
	public Workspace getWorkspace() {
		if (_workspace != null) {
			return _workspace;
		}

		_workspace = WorkspaceFactory.newWorkspace();

		return _workspace;
	}

	@Override
	public void run() {
		retirePreviousBuilds();

		if (_allowConcurrentBuilds() || _expirePreviousBuild()) {
			super.run();

			return;
		}

		BuildData buildData = getBuildData();

		if (_previousBuildHasExistingInvocation()) {
			buildData.setBuildDescription(
				"<strong>SKIPPED</strong> - Job was already invoked");

			super.updateBuildDescription();

			return;
		}

		if (_previousBuildHasRunningInvocation()) {
			buildData.setBuildDescription(
				"<strong>SKIPPED</strong> - Job is already running");

			super.updateBuildDescription();

			return;
		}

		invokeBuild();

		keepJenkinsBuild(true);
	}

	@Override
	public void tearDown() {
	}

	protected QAWebsitesControllerBuildRunner(BuildData buildData) {
		super(buildData);
	}

	protected String getJobInvocationURL() {
		BuildData buildData = getBuildData();

		String invocationMasterHostname = buildData.getBuildParameter(
			"INVOCATION_MASTER_HOSTNAME");

		if (JenkinsResultsParserUtil.isNullOrEmpty(invocationMasterHostname)) {
			String cohortName = buildData.getCohortName();

			String mostAvailableMasterURL =
				JenkinsResultsParserUtil.getMostAvailableMasterURL(
					"http://" + cohortName + ".liferay.com", 1);

			invocationMasterHostname = mostAvailableMasterURL.replaceAll(
				"https?://([^\\.]+)(.liferay.com.*)?", "\1");
		}

		String jobName = buildData.getJobName();

		return JenkinsResultsParserUtil.combine(
			"http://", invocationMasterHostname, "/job/",
			jobName.replaceAll("-controller\\(.*\\)", ""));
	}

	protected void invokeBuild() {
		StringBuilder sb = new StringBuilder();

		String jobInvocationURL = getJobInvocationURL();

		sb.append(jobInvocationURL);

		sb.append("/buildWithParameters?");
		sb.append("token=");

		try {
			sb.append(
				JenkinsResultsParserUtil.getBuildProperty(
					"jenkins.authentication.token"));
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}

		BuildData buildData = getBuildData();

		Map<String, String> invocationParameters = new HashMap<>();

		invocationParameters.put(
			"CONTROLLER_BUILD_URL", buildData.getBuildURL());
		invocationParameters.put(
			"JENKINS_GITHUB_BRANCH_NAME",
			_getGitHubBranchName("JENKINS_GITHUB_URL"));
		invocationParameters.put(
			"JENKINS_GITHUB_BRANCH_USERNAME",
			_getGitHubBranchUsername("JENKINS_GITHUB_URL"));
		invocationParameters.put(
			"TEST_QA_WEBSITES_BRANCH_NAME",
			_getGitHubBranchName("QA_WEBSITES_GITHUB_URL"));
		invocationParameters.put(
			"TEST_QA_WEBSITES_BRANCH_USERNAME",
			_getGitHubBranchUsername("QA_WEBSITES_GITHUB_URL"));

		Map<String, String> buildParameters = buildData.getBuildParameters();

		for (Map.Entry<String, String> buildParameter :
				buildParameters.entrySet()) {

			String buildParameterValue = buildParameter.getValue();

			if (JenkinsResultsParserUtil.isNullOrEmpty(buildParameterValue)) {
				continue;
			}

			invocationParameters.put(
				buildParameter.getKey(), buildParameterValue);
		}

		invocationParameters.putAll(buildData.getBuildParameters());

		for (Map.Entry<String, String> invocationParameter :
				invocationParameters.entrySet()) {

			String invocationParameterValue = invocationParameter.getValue();

			if (JenkinsResultsParserUtil.isNullOrEmpty(
					invocationParameterValue)) {

				continue;
			}

			sb.append("&");
			sb.append(invocationParameter.getKey());
			sb.append("=");
			sb.append(invocationParameterValue);
		}

		try {
			System.out.println(sb.toString());

			JenkinsResultsParserUtil.toString(sb.toString());

			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(JenkinsResultsParserUtil.getRemoteURL(jobInvocationURL));
			sb.append("\"><strong>IN QUEUE</strong></a>");

			buildData.setBuildDescription(sb.toString());

			updateBuildDescription();
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private boolean _allowConcurrentBuilds() {
		String allowConcurrentBuildsString = System.getenv(
			"ALLOW_CONCURRENT_BUILDS");

		if (allowConcurrentBuildsString == null) {
			return false;
		}

		allowConcurrentBuildsString = allowConcurrentBuildsString.toLowerCase();
		allowConcurrentBuildsString = allowConcurrentBuildsString.trim();

		if (!allowConcurrentBuildsString.equals("true")) {
			return false;
		}

		return true;
	}

	private boolean _expirePreviousBuild() {
		for (JSONObject previousBuildJSONObject :
				getPreviousBuildJSONObjects()) {

			String description = previousBuildJSONObject.optString(
				"description", "");

			if (!description.contains("IN PROGRESS") &&
				!description.contains("IN QUEUE")) {

				continue;
			}

			long timestamp = previousBuildJSONObject.optLong("timestamp", 0);

			if (timestamp == 0) {
				continue;
			}

			long inProgressBuildDuration =
				JenkinsResultsParserUtil.getCurrentTimeMillis() - timestamp;

			System.out.println(
				JenkinsResultsParserUtil.combine(
					"In progress build started ",
					JenkinsResultsParserUtil.toDurationString(
						inProgressBuildDuration),
					" ago"));

			if (inProgressBuildDuration < _getControllerBuildTimeout()) {
				return false;
			}

			Matcher matcher = _buildURLPattern.matcher(
				previousBuildJSONObject.getString("url"));

			if (!matcher.find()) {
				return false;
			}

			description = description.replace("IN PROGRESS", "EXPIRE");
			description = description.replace("IN QUEUE", "EXPIRE");

			JenkinsResultsParserUtil.updateBuildDescription(
				description, previousBuildJSONObject.getInt("number"),
				matcher.group("jobName"), matcher.group("masterHostname"));

			return true;
		}

		return false;
	}

	private long _getControllerBuildTimeout() {
		try {
			BuildData buildData = getBuildData();

			String controllerBuildTimeout =
				JenkinsResultsParserUtil.getProperty(
					JenkinsResultsParserUtil.getBuildProperties(),
					"controller.build.timeout", buildData.getJobName());

			if (!JenkinsResultsParserUtil.isNullOrEmpty(
					controllerBuildTimeout)) {

				return Long.parseLong(controllerBuildTimeout) * 1000;
			}

			return _CONTROLLER_BUILD_TIMEOUT_DEFAULT;
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private String _getGitHubBranchName(String parameterName) {
		BuildData buildData = getBuildData();

		String gitHubURL = buildData.getBuildParameter(parameterName);

		if (JenkinsResultsParserUtil.isNullOrEmpty(gitHubURL)) {
			return null;
		}

		Matcher matcher = _gitHubURLPattern.matcher(gitHubURL);

		if (!matcher.find()) {
			return null;
		}

		return matcher.group("branch");
	}

	private String _getGitHubBranchUsername(String parameterName) {
		BuildData buildData = getBuildData();

		String gitHubURL = buildData.getBuildParameter(parameterName);

		if (JenkinsResultsParserUtil.isNullOrEmpty(gitHubURL)) {
			return null;
		}

		Matcher matcher = _gitHubURLPattern.matcher(gitHubURL);

		if (!matcher.find()) {
			return null;
		}

		return matcher.group("username");
	}

	private boolean _previousBuildHasExistingInvocation() {
		for (JSONObject previousBuildJSONObject :
				getPreviousBuildJSONObjects()) {

			String description = previousBuildJSONObject.optString(
				"description", "");

			if (description.contains("IN QUEUE")) {
				return true;
			}
		}

		return false;
	}

	private boolean _previousBuildHasRunningInvocation() {
		for (JSONObject previousBuildJSONObject :
				getPreviousBuildJSONObjects()) {

			String description = previousBuildJSONObject.optString(
				"description", "");

			if (!description.contains("IN PROGRESS")) {
				continue;
			}

			Matcher buildURLMatcher = _buildDescriptionPattern.matcher(
				description);

			if (!buildURLMatcher.find()) {
				continue;
			}

			String buildURL = buildURLMatcher.group("buildURL");

			try {
				JSONObject jsonObject = JenkinsResultsParserUtil.toJSONObject(
					JenkinsResultsParserUtil.getLocalURL(
						buildURL + "/api/json?tree=result"));

				Object result = jsonObject.get("result");

				if (result.equals(JSONObject.NULL)) {
					return true;
				}

				JSONObject injectedEnvVarsJSONObject =
					JenkinsResultsParserUtil.toJSONObject(
						JenkinsResultsParserUtil.getLocalURL(
							previousBuildJSONObject.getString("url") +
								"/injectedEnvVars/api/json"));

				JSONObject envMapJSONObject =
					injectedEnvVarsJSONObject.getJSONObject("envMap");

				StringBuilder sb = new StringBuilder();

				sb.append("<strong style=\"color: red\">FAILURE</strong> - ");
				sb.append(buildURLMatcher.group());

				JenkinsResultsParserUtil.updateBuildDescription(
					sb.toString(),
					Integer.valueOf(envMapJSONObject.getString("BUILD_NUMBER")),
					envMapJSONObject.getString("JOB_NAME"),
					envMapJSONObject.getString("HOSTNAME"));
			}
			catch (IOException ioException) {
				throw new RuntimeException(ioException);
			}
		}

		return false;
	}

	private static final Integer _CONTROLLER_BUILD_TIMEOUT_DEFAULT =
		1000 * 60 * 60 * 24;

	private static final Pattern _buildDescriptionPattern = Pattern.compile(
		"<a href=\"(?<buildURL>[^\"]+)\">Build URL</a>");
	private static final Pattern _buildURLPattern = Pattern.compile(
		"https://(?<masterHostname>test-\\d+-\\d+)\\.?.*/job/" +
			"(?<jobName>[^/]+)/(?<buildNumber>\\d+)/?");
	private static final Pattern _gitHubURLPattern = Pattern.compile(
		"https://github.com/(?<username>[^/]+)/(?<repository>[^/]+)/" +
			"(commits|tree)/(?<branch>[^/]+)");

	private Workspace _workspace;

}