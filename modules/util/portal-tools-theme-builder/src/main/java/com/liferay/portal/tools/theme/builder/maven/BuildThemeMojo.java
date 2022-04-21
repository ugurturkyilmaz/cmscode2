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

package com.liferay.portal.tools.theme.builder.maven;

import com.liferay.portal.tools.theme.builder.ThemeBuilder;
import com.liferay.portal.tools.theme.builder.ThemeBuilderArgs;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.project.MavenProject;

import org.codehaus.plexus.component.repository.ComponentDependency;

import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.resolution.ArtifactResult;

/**
 * Build a theme.
 *
 * @author Andrea Di Giorgi
 * @goal build
 */
public class BuildThemeMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException {
		boolean themeStyledArtifactPresent = false;
		boolean themeUnstyledArtifactPresent = false;

		try {
			for (Dependency dependency : _project.getDependencies()) {
				String artifactId = dependency.getArtifactId();

				if (artifactId.equals("com.liferay.frontend.theme.styled") &&
					(_themeBuilderArgs.getParentDir() == null) &&
					ThemeBuilder.STYLED.equals(
						_themeBuilderArgs.getParentName())) {

					Artifact artifact = _resolveArtifact(dependency);

					if (artifact != null) {
						_themeBuilderArgs.setParentDir(artifact.getFile());
					}

					themeStyledArtifactPresent = true;
				}
				else if (artifactId.equals(
							"com.liferay.frontend.theme.unstyled") &&
						 (_themeBuilderArgs.getUnstyledDir() == null)) {

					Artifact artifact = _resolveArtifact(dependency);

					if (artifact != null) {
						_themeBuilderArgs.setUnstyledDir(artifact.getFile());
					}

					themeUnstyledArtifactPresent = true;
				}
			}

			if (!themeStyledArtifactPresent &&
				(_themeBuilderArgs.getParentDir() == null)) {

				for (ComponentDependency componentDependency :
						_pluginDescriptor.getDependencies()) {

					String artifactId = componentDependency.getArtifactId();

					if (!artifactId.equals(
							"com.liferay.frontend.theme.styled") ||
						!ThemeBuilder.STYLED.equals(
							_themeBuilderArgs.getParentName())) {

						continue;
					}

					Artifact artifact = _resolveArtifact(componentDependency);

					if (artifact != null) {
						_themeBuilderArgs.setParentDir(artifact.getFile());

						break;
					}
				}
			}

			if (!themeUnstyledArtifactPresent &&
				(_themeBuilderArgs.getUnstyledDir() == null)) {

				for (ComponentDependency componentDependency :
						_pluginDescriptor.getDependencies()) {

					String artifactId = componentDependency.getArtifactId();

					if (!artifactId.equals(
							"com.liferay.frontend.theme.unstyled")) {

						continue;
					}

					Artifact artifact = _resolveArtifact(componentDependency);

					if (artifact != null) {
						_themeBuilderArgs.setUnstyledDir(artifact.getFile());

						break;
					}
				}
			}

			ThemeBuilder themeBuilder = new ThemeBuilder(_themeBuilderArgs);

			themeBuilder.build();
		}
		catch (Exception exception) {
			throw new MojoExecutionException(exception.getMessage(), exception);
		}
	}

	/**
	 * @parameter default-value="${maven.war.src}"
	 */
	public void setDiffsDir(File diffsDir) {
		_themeBuilderArgs.setDiffsDir(diffsDir);
	}

	/**
	 * @parameter default-value="${project.artifactId}"
	 */
	public void setName(String name) {
		_themeBuilderArgs.setName(name);
	}

	/**
	 * @parameter default-value="${project.build.directory}/${project.build.finalName}"
	 */
	public void setOutputDir(File outputDir) {
		_themeBuilderArgs.setOutputDir(outputDir);
	}

	/**
	 * @parameter
	 */
	public void setParentDir(File parentDir) {
		_themeBuilderArgs.setParentDir(parentDir);
	}

	/**
	 * @parameter
	 */
	public void setParentName(String parentName) {
		_themeBuilderArgs.setParentName(parentName);
	}

	/**
	 * @parameter default-value="ftl"
	 */
	public void setTemplateExtension(String templateExtension) {
		_themeBuilderArgs.setTemplateExtension(templateExtension);
	}

	/**
	 * @parameter
	 */
	public void setUnstyledDir(File unstyledDir) {
		_themeBuilderArgs.setUnstyledDir(unstyledDir);
	}

	private Artifact _resolveArtifact(Artifact artifact)
		throws ArtifactResolutionException {

		ArtifactRequest artifactRequest = new ArtifactRequest();

		artifactRequest.setArtifact(artifact);

		List<RemoteRepository> repositories = new ArrayList<>();

		repositories.addAll(_project.getRemotePluginRepositories());
		repositories.addAll(_project.getRemoteProjectRepositories());

		artifactRequest.setRepositories(repositories);

		ArtifactResult artifactResult = _repositorySystem.resolveArtifact(
			_repositorySystemSession, artifactRequest);

		return artifactResult.getArtifact();
	}

	private Artifact _resolveArtifact(ComponentDependency componentDependency)
		throws ArtifactResolutionException {

		Artifact artifact = new DefaultArtifact(
			componentDependency.getGroupId(),
			componentDependency.getArtifactId(), componentDependency.getType(),
			componentDependency.getVersion());

		return _resolveArtifact(artifact);
	}

	private Artifact _resolveArtifact(Dependency dependency)
		throws ArtifactResolutionException {

		Artifact artifact = new DefaultArtifact(
			dependency.getGroupId(), dependency.getArtifactId(),
			dependency.getType(), dependency.getVersion());

		return _resolveArtifact(artifact);
	}

	/**
	 * @parameter default-value="${plugin}"
	 * @readonly
	 * @required
	 */
	private PluginDescriptor _pluginDescriptor;

	/**
	 * @parameter property="project"
	 * @required
	 * @readonly
	 */
	private MavenProject _project;

	/**
	 * @component
	 */
	private RepositorySystem _repositorySystem;

	/**
	 * @parameter property="repositorySystemSession"
	 * @readonly
	 * @required
	 */
	private RepositorySystemSession _repositorySystemSession;

	private final ThemeBuilderArgs _themeBuilderArgs = new ThemeBuilderArgs();

}