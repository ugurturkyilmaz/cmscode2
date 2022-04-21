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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Map;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Task;

/**
 * @author Cesar Polanco
 * @author Michael Hashimoto
 */
public class AntUtil {

	public static void callMacrodef(
		Project project, String name, Map<String, String> attributes) {

		callMacrodef(project, name, attributes, null);
	}

	public static void callMacrodef(
		Project project, String name, Map<String, String> attributes,
		String text) {

		Task task = project.createTask(name);

		RuntimeConfigurable runtimeConfigurable =
			task.getRuntimeConfigurableWrapper();

		if (text != null) {
			runtimeConfigurable.addText(text);
		}

		for (Map.Entry<String, String> attribute : attributes.entrySet()) {
			runtimeConfigurable.setAttribute(
				attribute.getKey(), attribute.getValue());
		}

		task.setRuntimeConfigurableWrapper(runtimeConfigurable);

		task.perform();
	}

	public static void callTarget(
			File baseDir, String buildFileName, String targetName)
		throws AntException {

		callTarget(baseDir, buildFileName, targetName, null);
	}

	public static void callTarget(
			File baseDir, String buildFileName, String targetName,
			Map<String, String> parameters)
		throws AntException {

		callTarget(baseDir, buildFileName, targetName, parameters, null);
	}

	public static void callTarget(
			File baseDir, String buildFileName, String targetName,
			Map<String, String> parameters, Map<String, String> envVariables)
		throws AntException {

		callTarget(
			baseDir, buildFileName, targetName, parameters, envVariables, null);
	}

	public static void callTarget(
			File baseDir, String buildFileName, String targetName,
			Map<String, String> parameters, Map<String, String> envVariables,
			File antLibDir)
		throws AntException {

		String[] bashCommands = new String[3];

		if (JenkinsResultsParserUtil.isWindows()) {
			bashCommands[0] = "cmd";
			bashCommands[1] = "/c";
		}
		else {
			bashCommands[0] = "/bin/sh";
			bashCommands[1] = "-c";
		}

		StringBuilder sb = new StringBuilder();

		if (envVariables != null) {
			for (Map.Entry<String, String> envVariable :
					envVariables.entrySet()) {

				sb.append("export ");
				sb.append(envVariable.getKey());
				sb.append("=");

				String value = envVariable.getValue();

				value = value.trim();

				value = value.replaceAll("\"", "\\\\\"");

				sb.append("\"");
				sb.append(value);
				sb.append("\"");

				sb.append(" ; ");
			}
		}

		sb.append("ant");

		if (parameters != null) {
			for (Map.Entry<String, String> parameter : parameters.entrySet()) {
				sb.append(" -D");
				sb.append(parameter.getKey());
				sb.append("=");

				String value = parameter.getValue();

				value = value.trim();

				value = value.replaceAll("\"", "\\\\\"");

				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			}
		}

		if (buildFileName != null) {
			sb.append(" -f ");
			sb.append(buildFileName);
		}

		if ((antLibDir != null) && antLibDir.exists()) {
			sb.append(" -lib ");
			sb.append(JenkinsResultsParserUtil.getCanonicalPath(antLibDir));
		}

		if (targetName != null) {
			sb.append(" ");
			sb.append(targetName);
		}

		System.out.println(sb.toString());

		bashCommands[2] = sb.toString();

		try {
			ProcessBuilder processBuilder = new ProcessBuilder(bashCommands);

			if (baseDir == null) {
				baseDir = new File(".");
			}

			processBuilder.directory(baseDir.getAbsoluteFile());

			final Process process = processBuilder.start();

			Thread thread = new Thread() {

				@Override
				public void run() {
					try (BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(process.getInputStream()))) {

						String line = bufferedReader.readLine();

						while (line != null) {
							System.out.println(line);

							line = bufferedReader.readLine();
						}
					}
					catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}

			};

			thread.start();

			process.waitFor();

			int exitValue = process.exitValue();

			if (exitValue != 0) {
				System.out.println(
					JenkinsResultsParserUtil.readInputStream(
						process.getErrorStream(), true));

				throw new AntException();
			}
		}
		catch (InterruptedException | IOException exception) {
			exception.printStackTrace();

			throw new AntException(exception);
		}
	}

}