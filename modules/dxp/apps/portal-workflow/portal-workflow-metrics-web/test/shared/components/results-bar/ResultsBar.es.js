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

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import ResultsBar from '../../../../src/main/resources/META-INF/resources/js/shared/components/results-bar/ResultsBar.es';
import {MockRouter} from '../../../mock/MockRouter.es';

describe('The ResultsBar component should', () => {
	const mockProps = {
		filterKeys: ['taskNames'],
		filters: [
			{
				items: [
					{active: true, key: 'review', name: 'Review'},
					{active: true, key: 'update', name: 'Update'},
				],
				key: 'taskNames',
				name: 'Process Step',
				pinned: false,
			},
		],
		page: 1,
		pageSize: 20,
		sort: 'overdueTaskCount:asc',
	};

	afterEach(cleanup);

	test('Render with search value "test" and total count "1"', () => {
		const {getByText} = render(
			<MockRouter>
				<ResultsBar>
					<ResultsBar.TotalCount totalCount={1} />

					<ResultsBar.Clear {...mockProps} />
				</ResultsBar>
			</MockRouter>
		);

		const totalCount = getByText('x-result-for-x');
		expect(totalCount).toBeTruthy();
	});

	test('Render with search value "test" and with 2 selected filter item', async () => {
		const {container, getByText} = render(
			<MockRouter query="?search=test">
				<ResultsBar>
					<ResultsBar.TotalCount search="test" totalCount={2} />

					<ResultsBar.FilterItems {...mockProps} />

					<ResultsBar.Clear {...mockProps} />
				</ResultsBar>
			</MockRouter>
		);

		const {
			filters: [{items}],
		} = mockProps;

		const removeFilter = container.querySelectorAll('.lexicon-icon-times');
		const totalCount = getByText('x-results-for-x');
		const clearAll = getByText('clear-all');

		expect(removeFilter.length).toBe(2);

		expect(totalCount).toBeTruthy();
		expect(items[0].active).toBe(true);
		expect(items[1].active).toBe(true);

		fireEvent.click(removeFilter[0].parentNode);

		expect(items[0].active).toBe(false);
		expect(items[1].active).toBe(true);

		fireEvent.click(clearAll);

		expect(items[0].active).toBe(false);
		expect(items[1].active).toBe(false);
	});
});
