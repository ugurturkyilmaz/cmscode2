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

const ISSUE_TYPES = {
	aspectRatio: 'incorrect-image-aspect-ratios',
	canonicalUrl: 'invalid-canonical-url',
	contrastRatio: 'low-contrast-ratio',
	crawlableLink: 'not-all-links-are-crawlable',
	fontSize: 'illegible-font-sizes',
	hrefLang: 'invalid-hreflang',
	imgAlt: 'missing-img-alt-attributes',
	inputAlt: 'missing-input-alt-attributes',
	linkText: 'link-texts',
	metaDescription: 'missing-meta-description',
	pageIndexing: 'page-blocked-from-indexing',
	tapTarget: 'small-tap-targets',
	titleElement: 'missing-title-element',
};

export default function normalizeFailingElements(failingElements, issueType) {
	return failingElements.map((element) =>
		normalizeFailingElement(element, issueType)
	);
}

function normalizeFailingElement(failingElement, issueType) {
	switch (issueType) {
		case ISSUE_TYPES.aspectRatio:
			return {
				sections: [
					{
						label: Liferay.Language.get('source-file'),
						value: failingElement.url,
					},
					{
						label: Liferay.Language.get('displayed-aspect-ratio'),
						value: failingElement.displayedAspectRatio,
					},
					{
						label: Liferay.Language.get('actual-aspect-ratio'),
						value: failingElement.actualAspectRatio,
					},
				],
			};

		case ISSUE_TYPES.canonicalUrl:
			return {
				htmlContent: failingElement.content,
			};

		case ISSUE_TYPES.contrastRatio:
			return {
				content: failingElement.node.explanation,
				title: failingElement.node.nodeLabel,
			};

		case ISSUE_TYPES.crawlableLink:
		case ISSUE_TYPES.imgAlt:
		case ISSUE_TYPES.inputAlt:
			return {
				snippet: failingElement.node.snippet,
				title: failingElement.node.nodeLabel,
			};

		case ISSUE_TYPES.fontSize:
			return {
				sections: [
					{
						label: Liferay.Language.get('font-size'),
						value: failingElement.fontSize,
					},
				],
				snippet: failingElement.selector.snippet,
			};

		case ISSUE_TYPES.hrefLang:
			return {
				snippet: failingElement.source.snippet,
				title: failingElement.source.nodeLabel,
			};

		case ISSUE_TYPES.linkText:
			return {
				sections: [
					{
						label: Liferay.Language.get('link-text'),
						value: failingElement.text,
					},
					{
						label: Liferay.Language.get('link-destination-url'),
						value: failingElement.href,
					},
				],
			};

		case ISSUE_TYPES.metaDescription:
			return {
				htmlContent: failingElement.content,
			};

		case ISSUE_TYPES.pageIndexing:
			return {
				htmlContent: failingElement.content,
			};

		case ISSUE_TYPES.tapTarget:
			return {
				sections: [
					{
						label: Liferay.Language.get('size'),
						value: failingElement.size,
					},
					{
						label: Liferay.Language.get('overlapping-target'),
						value: failingElement.overlappingTarget.nodeLabel,
					},
				],
				snippet: failingElement.tapTarget.snippet,
				title: failingElement.tapTarget.nodeLabel,
			};

		case ISSUE_TYPES.titleElement:
			return {
				htmlContent: failingElement.content,
			};

		default:
			return {};
	}
}
