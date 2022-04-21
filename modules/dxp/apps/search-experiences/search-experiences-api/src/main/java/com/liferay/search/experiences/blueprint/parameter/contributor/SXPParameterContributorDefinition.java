/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.blueprint.parameter.contributor;

/**
 * @author Petteri Karttunen
 */
public class SXPParameterContributorDefinition {

	public SXPParameterContributorDefinition(
		Class<?> clazz, String languageKey, String name) {

		_languageKey = languageKey;

		_className = clazz.getName();

		_templateVariable = "${" + name + "}";
	}

	public String getClassName() {
		return _className;
	}

	public String getLanguageKey() {
		return _languageKey;
	}

	public String getTemplateVariable() {
		return _templateVariable;
	}

	private final String _className;
	private final String _languageKey;
	private final String _templateVariable;

}