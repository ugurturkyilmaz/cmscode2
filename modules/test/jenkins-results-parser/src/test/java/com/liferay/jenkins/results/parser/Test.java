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
import java.io.IOException;
import java.io.StringReader;

import java.net.URI;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

/**
 * @author Peter Yoo
 */
public class Test {

	@Before
	public void setUp() throws Exception {
		JenkinsResultsParserUtil.clearCache();
	}

	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();

	public interface ExpectedMessageGenerator {

		public String getMessage(TestSample testSample) throws Exception;

	}

	protected static List<File> getDependenciesDirs(
		List<String> simpleClassNames) {

		List<File> dirs = new ArrayList<>(simpleClassNames.size());

		for (String simpleClassName : simpleClassNames) {
			dirs.add(
				new File("src/test/resources/dependencies/" + simpleClassName));
		}

		return dirs;
	}

	protected void assertSample(TestSample testSample) throws Exception {
		String sampleKey = testSample.getSampleKey();

		System.out.print("Asserting sample " + sampleKey + ": ");

		String actualMessage = fixMessage(
			expectedMessageGenerator.getMessage(testSample));

		File expectedMessageFile = getExpectedMessageFile(testSample);

		if (!expectedMessageFile.exists()) {
			errorCollector.addError(
				new Throwable(
					JenkinsResultsParserUtil.combine(
						"Unable to find expected_message.html for sample '",
						sampleKey, "'. Generating file.")));

			JenkinsResultsParserUtil.write(expectedMessageFile, actualMessage);

			return;
		}

		String expectedMessage = read(expectedMessageFile);

		boolean value = expectedMessage.equals(actualMessage);

		if (value) {
			System.out.println(" PASSED");
		}
		else {
			System.out.println(" FAILED");
			System.out.println("\nActual message: \n" + actualMessage);
			System.out.println("\nExpected message: \n" + expectedMessage);

			errorCollector.addError(
				new Throwable(
					JenkinsResultsParserUtil.combine(
						"Expected message mismatch in sample '", sampleKey,
						"'.\n Expected message file: ",
						expectedMessageFile.getPath())));
		}
	}

	protected void assertSamples() throws Exception {
		for (TestSample testSample : testSamples.values()) {
			assertSample(testSample);
		}
	}

	protected void deleteFile(File file) {
		if (!file.exists()) {
			return;
		}

		if (file.isFile()) {
			file.delete();
		}
		else {
			File[] files = file.listFiles();

			for (File childFile : files) {
				deleteFile(childFile);
			}

			file.delete();
		}
	}

	protected void deleteFile(String fileName) {
		deleteFile(new File(fileName));
	}

	protected void downloadSample(
			String sampleKey, String buildNumber, String jobName,
			String hostName)
		throws Exception {

		downloadSample(sampleKey, null, buildNumber, jobName, hostName);
	}

	protected void downloadSample(
			String sampleKey, String axisVariable, String buildNumber,
			String jobName, String hostName)
		throws Exception {

		String urlString =
			"https://${hostName}.liferay.com/job/${jobName}//${buildNumber}/";

		if (axisVariable != null) {
			urlString =
				"https://${hostName}.liferay.com/job/${jobName}" +
					"/AXIS_VARIABLE=${axis}/${buildNumber}/";

			urlString = replaceToken(urlString, "axis", axisVariable);
		}

		urlString = replaceToken(urlString, "buildNumber", buildNumber);
		urlString = replaceToken(urlString, "hostName", hostName);
		urlString = replaceToken(urlString, "jobName", jobName);

		URL url = JenkinsResultsParserUtil.createURL(urlString);

		downloadSample(sampleKey, url);
	}

	protected void downloadSample(String sampleKey, URL url) throws Exception {
		if (testSamples.containsKey(sampleKey)) {
			throw new Exception("Duplicate sample key: '" + sampleKey + "'");
		}

		TestSample testSample = new TestSample(dependenciesDirs, sampleKey);

		File sampleDir = testSample.getSampleDir();

		try {
			if (!sampleDir.exists()) {
				System.out.println("Downloading sample " + sampleKey);

				downloadSample(testSample, url);
			}

			testSamples.put(sampleKey, testSample);
		}
		catch (IOException ioException) {
			deleteFile(sampleDir);

			throw ioException;
		}
	}

	protected void downloadSample(TestSample testSample, URL url)
		throws Exception {
	}

	protected void downloadSampleURL(File dir, URL url, String urlSuffix)
		throws Exception {

		String urlString = url + urlSuffix;

		if (urlString.endsWith("json")) {
			urlString += "?pretty";
		}

		urlSuffix = JenkinsResultsParserUtil.fixFileName(urlSuffix);

		JenkinsResultsParserUtil.write(
			new File(dir, urlSuffix),
			JenkinsResultsParserUtil.toString(
				JenkinsResultsParserUtil.getLocalURL(urlString)));
	}

	protected String fixMessage(String message) {
		if (message.contains(JenkinsResultsParserUtil.urlDependenciesFile)) {
			message = message.replace(
				JenkinsResultsParserUtil.urlDependenciesFile,
				"${dependencies.url}");
		}

		if (message.contains(JenkinsResultsParserUtil.urlDependenciesHttp)) {
			message = message.replace(
				JenkinsResultsParserUtil.urlDependenciesHttp,
				"${dependencies.url}");
		}

		return message.replaceAll("[^\\S\\r\\n]+\n", "\n");
	}

	protected String formatXML(String xml)
		throws DocumentException, IOException {

		SAXReader saxReader = new SAXReader();

		for (String[] xmlReplacement : _XML_REPLACEMENTS) {
			xml = xml.replace(xmlReplacement[0], xmlReplacement[1]);
		}

		Document document = null;

		try {
			document = saxReader.read(new StringReader(xml));
		}
		catch (DocumentException documentException1) {
			DocumentException documentException2 = new DocumentException(
				documentException1.getMessage() + "\n" + xml);

			documentException2.setStackTrace(
				documentException1.getStackTrace());

			throw documentException2;
		}

		String formattedXML = Dom4JUtil.format(document.getRootElement());

		for (String[] xmlReplacement : _XML_REPLACEMENTS) {
			formattedXML = formattedXML.replace(
				xmlReplacement[1], xmlReplacement[0]);
		}

		return formattedXML;
	}

	protected File getExpectedMessageFile(TestSample testSample) {
		return new File(testSample.getSampleDir(), "expected-message.html");
	}

	protected String getMismatchMessage(
		String expectedValue, String actualValue, String valueName) {

		return JenkinsResultsParserUtil.combine(
			"The expected ", valueName, " value ", expectedValue,
			", Did not match the actual ", valueName, " value ", actualValue,
			".");
	}

	protected List<String> getSimpleClassNames() {
		if (_simpleClassNames == null) {
			_simpleClassNames = new ArrayList<>();

			Class<?> clazz = getClass();

			String simpleName = clazz.getSimpleName();

			while (!simpleName.equals("Object")) {
				_simpleClassNames.add(simpleName);

				clazz = clazz.getSuperclass();

				simpleName = clazz.getSimpleName();
			}
		}

		return _simpleClassNames;
	}

	protected String read(File file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file.toURI())));
	}

	protected String read(File dir, String fileName) throws IOException {
		return read(new File(dir, fileName));
	}

	protected String replaceToken(String string, String token, String value) {
		if (string == null) {
			return string;
		}

		return string.replace("${" + token + "}", value);
	}

	protected void testEquals(String expected, String actual) {
		if (!((expected == null) ^ (actual == null))) {
			if ((expected != null) && !expected.equals(actual)) {
				errorCollector.addError(
					new Throwable(
						JenkinsResultsParserUtil.combine(
							"String mismatch\nExpected:", expected, "\nActual:",
							actual)));
			}
		}
		else {
			errorCollector.addError(
				new Throwable(
					JenkinsResultsParserUtil.combine(
						"String mismatch\nExpected:", expected, "\nActual:",
						actual)));
		}
	}

	protected String toURLString(File file) throws Exception {
		URI uri = file.toURI();

		String urlString = String.valueOf(uri.toURL());

		File dependenciesDir = dependenciesDirs.get(0);

		String path = dependenciesDir.getPath();

		int x =
			path.indexOf("src/test/resources/dependencies/") +
				"src/test/resources/dependencies/".length();

		path = path.substring(x);

		return urlString.replace(
			"file:" +
				JenkinsResultsParserUtil.getCanonicalPath(dependenciesDir),
			"${dependencies.url}/" + path);
	}

	protected List<File> dependenciesDirs = getDependenciesDirs(
		getSimpleClassNames());
	protected ExpectedMessageGenerator expectedMessageGenerator;
	protected Map<String, TestSample> testSamples = new HashMap<>();

	private static final String[][] _XML_REPLACEMENTS = {
		{"<pre>", "<pre><![CDATA["}, {"</pre>", "]]></pre>"},
		{"&raquo;", "[raquo]"}
	};

	private List<String> _simpleClassNames;

}