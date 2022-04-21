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

package com.liferay.search.experiences.validator;

import com.liferay.search.experiences.exception.SXPElementElementDefinitionJSONException;
import com.liferay.search.experiences.exception.SXPElementTitleException;

import java.util.Locale;
import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public interface SXPElementValidator {

	public void validate(String elementDefinitionJSON, int type)
		throws SXPElementElementDefinitionJSONException;

	public void validate(
			String elementDefinitionJSON, Map<Locale, String> titleMap,
			int type)
		throws SXPElementElementDefinitionJSONException,
			   SXPElementTitleException;

}