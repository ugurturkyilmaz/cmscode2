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

package com.liferay.search.experiences.internal.blueprint.highlight;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.search.highlight.FieldConfigBuilderFactory;
import com.liferay.portal.search.highlight.Highlight;
import com.liferay.portal.search.highlight.HighlightBuilder;
import com.liferay.portal.search.highlight.HighlightBuilderFactory;
import com.liferay.search.experiences.rest.dto.v1_0.HighlightConfiguration;
import com.liferay.search.experiences.rest.dto.v1_0.HighlightField;

import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class HighlightConverter {

	public HighlightConverter(
		FieldConfigBuilderFactory fieldConfigBuilderFactory,
		HighlightBuilderFactory highlightBuilderFactory) {

		_fieldConfigBuilderFactory = fieldConfigBuilderFactory;
		_highlightBuilderFactory = highlightBuilderFactory;
	}

	public Highlight toHighlight(
		HighlightConfiguration highlightConfiguration) {

		HighlightBuilder highlightBuilder = _highlightBuilderFactory.builder();

		Map<String, HighlightField> highlightFields =
			highlightConfiguration.getFields();

		MapUtil.isNotEmptyForEach(
			highlightFields,
			(name, highlightField) -> highlightBuilder.addFieldConfig(
				_fieldConfigBuilderFactory.builder(
					name
				).fragmentOffset(
					highlightField.getFragment_offset()
				).fragmentSize(
					highlightField.getFragment_size()
				).numFragments(
					highlightField.getNumber_of_fragments()
				).build()));

		highlightBuilder.fragmentSize(
			highlightConfiguration.getFragment_size()
		).highlighterType(
			highlightConfiguration.getType()
		).numOfFragments(
			highlightConfiguration.getNumber_of_fragments()
		).postTags(
			highlightConfiguration.getPost_tags()
		).preTags(
			highlightConfiguration.getPre_tags()
		).requireFieldMatch(
			highlightConfiguration.getRequire_field_match()
		);

		return highlightBuilder.build();
	}

	public Highlight toHighlight(JSONObject jsonObject) {
		if (jsonObject == null) {
			return null;
		}

		HighlightConfiguration highlightConfiguration =
			HighlightConfiguration.toDTO(jsonObject.toString());

		return toHighlight(highlightConfiguration);
	}

	private final FieldConfigBuilderFactory _fieldConfigBuilderFactory;
	private final HighlightBuilderFactory _highlightBuilderFactory;

}