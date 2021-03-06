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

import FilterDisplay from '../../../../src/main/resources/META-INF/resources/js/components/list/FilterDisplay.es';

import '@testing-library/jest-dom/extend-expect';

function renderTestFilterDisplay(props) {
	return render(
		<FilterDisplay
			onClear={jest.fn()}
			searchBarTerm="example"
			totalResultsCount={250}
			{...props}
		/>
	);
}

describe('FilterDisplay', () => {
	it('renders', () => {
		const {container} = renderTestFilterDisplay();

		expect(container).not.toBeNull();
	});

	it('displays the total results count', () => {
		const {getByText} = renderTestFilterDisplay();

		expect(getByText('250', {exact: false})).toBeInTheDocument();
	});

	it('displays the search bar term', () => {
		const {getByText} = renderTestFilterDisplay();

		expect(getByText('example', {exact: false})).toBeInTheDocument();
	});

	it('calls the onClear function when clicking on Clear', () => {
		const onClear = jest.fn();

		const {getByText} = renderTestFilterDisplay({onClear});

		fireEvent.click(getByText('clear'));

		expect(onClear).toHaveBeenCalledTimes(1);
	});
});
