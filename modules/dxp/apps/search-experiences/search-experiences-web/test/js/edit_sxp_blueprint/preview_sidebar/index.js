/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {fireEvent, render} from '@testing-library/react';
import React from 'react';

import PreviewSidebar from '../../../../src/main/resources/META-INF/resources/sxp_blueprint_admin/js/edit_sxp_blueprint/preview_sidebar/index';
import {transformToSearchPreviewHits} from '../../../../src/main/resources/META-INF/resources/sxp_blueprint_admin/js/utils/utils';
import {mockSearchResults} from '../../mocks/data';

import '@testing-library/jest-dom/extend-expect';

jest.mock(
	'../../../../src/main/resources/META-INF/resources/sxp_blueprint_admin/js/shared/CodeMirrorEditor',
	() => ({onChange, value}) => (
		<textarea aria-label="text-area" onChange={onChange} value={value} />
	)
);

Liferay.ThemeDisplay.getDefaultLanguageId = () => 'en_US';

const SEARCH_RESULTS = mockSearchResults();
const SEARCH_HITS = transformToSearchPreviewHits(SEARCH_RESULTS);

function renderPreviewSidebar(props) {
	return render(
		<PreviewSidebar
			loading={false}
			onFetchResults={jest.fn()}
			visible={true}
			{...props}
		/>
	);
}

describe('PreviewSidebar', () => {
	global.URL.createObjectURL = jest.fn();

	it('renders the preview', () => {
		const {container} = renderPreviewSidebar();

		expect(container).not.toBeNull();
	});

	it('renders the introduction', () => {
		const {getByText} = renderPreviewSidebar();

		getByText('perform-a-search-to-preview-your-blueprints-search-results');
	});

	it('renders the loading icon', () => {
		const {container} = renderPreviewSidebar({
			hits: SEARCH_HITS,
			loading: true,
			responseString: SEARCH_RESULTS.responseString,
			totalHits: SEARCH_RESULTS.searchHits.totalHits,
		});

		container.querySelector('.loading-animation');
	});

	it('renders the titles for the search results', () => {
		const {getByText} = renderPreviewSidebar({
			hits: SEARCH_HITS,
			responseString: SEARCH_RESULTS.responseString,
			totalHits: SEARCH_RESULTS.searchHits.totalHits,
		});

		SEARCH_HITS.forEach(({documentFields}) => {
			getByText(documentFields.assetTitle);
		});
	});

	it('expands the result when clicked on', () => {
		const {getAllByLabelText, queryAllByText} = renderPreviewSidebar({
			hits: SEARCH_HITS,
			responseString: SEARCH_RESULTS.responseString,
			totalHits: SEARCH_RESULTS.searchHits.totalHits,
		});

		fireEvent.click(getAllByLabelText('expand')[0]);

		Object.entries(SEARCH_HITS[0].documentFields).forEach(([, value]) => {
			queryAllByText(value);
		});
	});

	it('renders warning messages', () => {
		const errors = [
			{
				msg: 'invalid',
				severity: 'error',
			},
			{
				msg: 'missing text',
				severity: 'error',
			},
		];

		const {getByText} = renderPreviewSidebar({
			errors,
			loading: false,
		});

		errors.map((error) => getByText(error.msg));
	});
});
