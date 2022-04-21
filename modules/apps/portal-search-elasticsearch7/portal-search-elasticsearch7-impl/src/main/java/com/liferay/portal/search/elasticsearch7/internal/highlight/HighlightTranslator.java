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

package com.liferay.portal.search.elasticsearch7.internal.highlight;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.highlight.FieldConfig;
import com.liferay.portal.search.highlight.Highlight;
import com.liferay.portal.search.query.QueryTranslator;

import java.util.List;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

/**
 * @author Michael C. Han
 */
public class HighlightTranslator {

	public HighlightBuilder translate(
		Highlight highlight, QueryTranslator<QueryBuilder> queryTranslator) {

		HighlightBuilder highlightBuilder = new HighlightBuilder();

		if (ArrayUtil.isNotEmpty(highlight.getBoundaryChars())) {
			highlightBuilder.boundaryChars(highlight.getBoundaryChars());
		}

		if (highlight.getBoundaryMaxScan() != null) {
			highlightBuilder.boundaryMaxScan(highlight.getBoundaryMaxScan());
		}

		if (highlight.getBoundaryScannerLocale() != null) {
			highlightBuilder.boundaryScannerLocale(
				highlight.getBoundaryScannerLocale());
		}

		if (highlight.getBoundaryScannerType() != null) {
			highlightBuilder.boundaryScannerType(
				highlight.getBoundaryScannerType());
		}

		if (highlight.getEncoder() != null) {
			highlightBuilder.encoder(highlight.getEncoder());
		}

		List<FieldConfig> fieldConfigs = highlight.getFieldConfigs();

		Stream<FieldConfig> stream = fieldConfigs.stream();

		stream.map(
			fieldConfig -> _translate(fieldConfig, queryTranslator)
		).forEach(
			highlightBuilder::field
		);

		if (highlight.getForceSource() != null) {
			highlightBuilder.forceSource(highlight.getForceSource());
		}

		if (highlight.getFragmenter() != null) {
			highlightBuilder.fragmenter(highlight.getFragmenter());
		}

		if (highlight.getFragmentSize() != null) {
			highlightBuilder.fragmentSize(highlight.getFragmentSize());
		}

		if (highlight.getHighlighterType() != null) {
			highlightBuilder.highlighterType(highlight.getHighlighterType());
		}

		if (highlight.getHighlightFilter() != null) {
			highlightBuilder.highlightFilter(highlight.getHighlightFilter());
		}

		if (highlight.getHighlightQuery() != null) {
			highlightBuilder.highlightQuery(
				queryTranslator.translate(highlight.getHighlightQuery()));
		}

		if (highlight.getHighlighterType() != null) {
			highlightBuilder.highlighterType(highlight.getHighlighterType());
		}

		if (highlight.getNoMatchSize() != null) {
			highlightBuilder.noMatchSize(highlight.getNoMatchSize());
		}

		if (highlight.getNumOfFragments() != null) {
			highlightBuilder.numOfFragments(highlight.getNumOfFragments());
		}

		if (highlight.getOrder() != null) {
			highlightBuilder.order(highlight.getOrder());
		}

		if (highlight.getPhraseLimit() != null) {
			highlightBuilder.phraseLimit(highlight.getPhraseLimit());
		}

		if (ArrayUtil.isNotEmpty(highlight.getPreTags())) {
			highlightBuilder.preTags(highlight.getPreTags());
		}

		if (ArrayUtil.isNotEmpty(highlight.getPostTags())) {
			highlightBuilder.postTags(highlight.getPostTags());
		}

		if (highlight.getRequireFieldMatch() != null) {
			highlightBuilder.requireFieldMatch(
				highlight.getRequireFieldMatch());
		}

		if (highlight.getTagsSchema() != null) {
			highlightBuilder.tagsSchema(highlight.getTagsSchema());
		}

		if (highlight.getUseExplicitFieldOrder() != null) {
			highlightBuilder.useExplicitFieldOrder(
				highlight.getUseExplicitFieldOrder());
		}

		return highlightBuilder;
	}

	private HighlightBuilder.Field _translate(
		FieldConfig fieldConfig,
		QueryTranslator<QueryBuilder> queryTranslator) {

		HighlightBuilder.Field field = new HighlightBuilder.Field(
			fieldConfig.getFieldName());

		if (ArrayUtil.isNotEmpty(fieldConfig.getBoundaryChars())) {
			field.boundaryChars(fieldConfig.getBoundaryChars());
		}

		if (fieldConfig.getBoundaryMaxScan() != null) {
			field.boundaryMaxScan(fieldConfig.getBoundaryMaxScan());
		}

		if (fieldConfig.getBoundaryScannerLocale() != null) {
			field.boundaryScannerLocale(fieldConfig.getBoundaryScannerLocale());
		}

		if (fieldConfig.getBoundaryScannerType() != null) {
			field.boundaryScannerType(fieldConfig.getBoundaryScannerType());
		}

		if (fieldConfig.getForceSource() != null) {
			field.forceSource(fieldConfig.getForceSource());
		}

		if (fieldConfig.getFragmenter() != null) {
			field.fragmenter(fieldConfig.getFragmenter());
		}

		if (fieldConfig.getFragmentOffset() != null) {
			field.fragmentOffset(fieldConfig.getFragmentOffset());
		}

		if (fieldConfig.getFragmentSize() != null) {
			field.fragmentSize(fieldConfig.getFragmentSize());
		}

		if (fieldConfig.getHighlighterType() != null) {
			field.highlighterType(fieldConfig.getHighlighterType());
		}

		if (fieldConfig.getHighlightFilter() != null) {
			field.highlightFilter(fieldConfig.getHighlightFilter());
		}

		if (fieldConfig.getHighlightQuery() != null) {
			field.highlightQuery(
				queryTranslator.translate(fieldConfig.getHighlightQuery()));
		}

		if (ArrayUtil.isNotEmpty(fieldConfig.getMatchedFields())) {
			field.matchedFields(fieldConfig.getMatchedFields());
		}

		if (fieldConfig.getNoMatchSize() != null) {
			field.noMatchSize(fieldConfig.getNoMatchSize());
		}

		if (fieldConfig.getNumFragments() != null) {
			field.numOfFragments(fieldConfig.getNumFragments());
		}

		if (fieldConfig.getOrder() != null) {
			field.order(fieldConfig.getOrder());
		}

		if (fieldConfig.getPhraseLimit() != null) {
			field.phraseLimit(fieldConfig.getPhraseLimit());
		}

		if (ArrayUtil.isNotEmpty(fieldConfig.getPostTags())) {
			field.postTags(fieldConfig.getPostTags());
		}

		if (ArrayUtil.isNotEmpty(fieldConfig.getPreTags())) {
			field.preTags(fieldConfig.getPreTags());
		}

		if (fieldConfig.getRequireFieldMatch() != null) {
			field.requireFieldMatch(fieldConfig.getRequireFieldMatch());
		}

		return field;
	}

}