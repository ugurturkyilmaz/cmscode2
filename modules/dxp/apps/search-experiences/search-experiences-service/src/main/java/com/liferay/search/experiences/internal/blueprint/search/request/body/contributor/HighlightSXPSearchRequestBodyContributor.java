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

package com.liferay.search.experiences.internal.blueprint.search.request.body.contributor;

import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.search.experiences.internal.blueprint.highlight.HighlightConverter;
import com.liferay.search.experiences.internal.blueprint.parameter.SXPParameterData;
import com.liferay.search.experiences.rest.dto.v1_0.Configuration;
import com.liferay.search.experiences.rest.dto.v1_0.HighlightConfiguration;

/**
 * @author Petteri Karttunen
 */
public class HighlightSXPSearchRequestBodyContributor
	implements SXPSearchRequestBodyContributor {

	public HighlightSXPSearchRequestBodyContributor(
		HighlightConverter highlightConverter) {

		_highlightConverter = highlightConverter;
	}

	@Override
	public void contribute(
		Configuration configuration, SearchRequestBuilder searchRequestBuilder,
		SXPParameterData sxpParameterData) {

		HighlightConfiguration highlightConfiguration =
			configuration.getHighlightConfiguration();

		if ((highlightConfiguration == null) ||
			(highlightConfiguration.getFields() == null)) {

			return;
		}

		searchRequestBuilder.highlight(
			_highlightConverter.toHighlight(highlightConfiguration));
	}

	@Override
	public String getName() {
		return "highlightConfiguration";
	}

	private final HighlightConverter _highlightConverter;

}