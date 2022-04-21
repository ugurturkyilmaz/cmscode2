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

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Peter Yoo
 */
@RunWith(Parameterized.class)
public class GitHubMessageTest extends BuildTest {

	@Parameterized.Parameters(name = "{0}")
	public static Collection<String[]> sampleData() {
		return Arrays.asList(
			new String[][] {
				{
					"test-jenkins-acceptance-pullrequest_passed", "117",
					"test-jenkins-acceptance-pullrequest", "test-1-17"
				},
				{
					"test-plugins-acceptance-pullrequest(ee-6.2.x)_passed",
					"66", "test-plugins-acceptance-pullrequest(ee-6.2.x)",
					"test-1-8"
				},
				{
					"test-portal-acceptance-pullrequest(7.0.x)" +
						"_unresolved-req-failure",
					"103", "test-portal-acceptance-pullrequest(7.0.x)",
					"test-1-14"
				},
				{
					"test-portal-acceptance-pullrequest(ee-6.2.x)_passed",
					"337", "test-portal-acceptance-pullrequest(ee-6.2.x)",
					"test-1-17"
				},
				{
					"test-portal-acceptance-pullrequest(master)" +
						"_generic-failure",
					"1375", "test-portal-acceptance-pullrequest(master)",
					"test-1-1"
				},
				{
					"test-portal-acceptance-pullrequest(master)" +
						"_modules-compile-failure",
					"999", "test-portal-acceptance-pullrequest(master)",
					"test-1-21"
				},
				{
					"test-portal-acceptance-pullrequest(master)_passed", "446",
					"test-portal-acceptance-pullrequest(master)", "test-1-8"
				},
				{
					"test-portal-acceptance-pullrequest(master)" +
						"_poshi-test-failure",
					"1268", "test-portal-acceptance-pullrequest(master)",
					"test-1-9"
				},
				{
					"test-portal-acceptance-pullrequest(master)" +
						"_semantic_versioning_failure",
					"2003", "test-portal-acceptance-pullrequest(master)",
					"test-1-3"
				},
				{
					"test-portal-source-format_source-format-failure", "587",
					"test-portal-source-format", "test-1-22"
				}
			});
	}

	public GitHubMessageTest(
		String sampleKey, String buildNumber, String jobName, String hostName) {

		_sampleKey = sampleKey;
		_buildNumber = buildNumber;
		_jobName = jobName;
		_hostName = hostName;
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		downloadSample(_sampleKey, _buildNumber, _jobName, _hostName);
	}

	@Test
	public void testExpectedGitHubMessage() throws Exception {
		expectedMessageGenerator = new ExpectedMessageGenerator() {

			@Override
			public String getMessage(TestSample testSample) throws Exception {
				Build build = BuildFactory.newBuildFromArchive(
					testSample.getSampleDirName());

				build.setCompareToUpstream(false);

				return Dom4JUtil.format(build.getGitHubMessageElement(), true);
			}

		};

		assertSamples();
	}

	@Override
	protected File getExpectedMessageFile(TestSample testSample) {
		return new File(
			testSample.getSampleDir(), "expected-github-message.html");
	}

	private final String _buildNumber;
	private final String _hostName;
	private final String _jobName;
	private final String _sampleKey;

}