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

import {act, cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import {Autocomplete} from '../../../../src/main/resources/META-INF/resources/js/shared/components/autocomplete/Autocomplete.es';

import '@testing-library/jest-dom/extend-expect';

const items = [
	{id: 1, name: '0test test0'},
	{id: 2, name: '1test test1'},
	{id: 3, name: '2test test2'},
];

describe('The Autocomplete component should', () => {
	let container;

	const onChange = jest.fn();
	const onSelect = jest.fn();

	afterEach(cleanup);

	beforeEach(async () => {
		const autocomplete = render(
			<Autocomplete
				items={items}
				onChange={onChange}
				onSelect={onSelect}
			/>
		);

		container = autocomplete.container;

		await act(async () => {
			jest.runAllTimers();
		});
	});

	it('Show the dropdown list on focus input', async () => {
		const autocompleteInput = container.querySelector('input.form-control');
		const dropDownList = document.querySelector('#dropDownList');
		const dropDownListItems = document.querySelectorAll('.dropdown-item');

		const dropDown = dropDownList.parentNode;

		expect(dropDown).not.toHaveClass('show');

		fireEvent.focus(autocompleteInput);

		await act(async () => {
			jest.runAllTimers();
		});

		expect(dropDown).toHaveClass('show');

		fireEvent.mouseDown(dropDownListItems[0]);

		await act(async () => {
			jest.runAllTimers();
		});

		expect(autocompleteInput.value).toBe('0test test0');
		expect(dropDown).not.toHaveClass('show');

		fireEvent.focus(autocompleteInput);

		await act(async () => {
			jest.runAllTimers();
		});

		expect(dropDown).toHaveClass('show');

		fireEvent.change(autocompleteInput, {target: {value: 'test'}});
		fireEvent.blur(autocompleteInput);

		await act(async () => {
			jest.runAllTimers();
		});

		expect(autocompleteInput.value).toBe('');
		expect(dropDown).not.toHaveClass('show');
	});

	it('Render its items list and select any option', async () => {
		const autocompleteInput = container.querySelector('input.form-control');
		const dropDownListItems = document.querySelectorAll('.dropdown-item');

		fireEvent.focus(autocompleteInput);
		await act(async () => {
			jest.runAllTimers();
		});

		expect(dropDownListItems[0]).toHaveTextContent('0test test0');
		expect(dropDownListItems[1]).toHaveTextContent('1test test1');
		expect(dropDownListItems[2]).toHaveTextContent('2test test2');
		expect(dropDownListItems[0]).not.toHaveClass('active');
		expect(dropDownListItems[1]).not.toHaveClass('active');
		expect(dropDownListItems[2]).not.toHaveClass('active');

		fireEvent.mouseOver(dropDownListItems[2]);

		await act(async () => {
			jest.runAllTimers();
		});

		expect(dropDownListItems[0]).not.toHaveClass('active');
		expect(dropDownListItems[1]).not.toHaveClass('active');
		expect(dropDownListItems[2]).toHaveClass('active');

		fireEvent.mouseOver(dropDownListItems[0]);

		await act(async () => {
			jest.runAllTimers();
		});

		expect(dropDownListItems[0]).toHaveClass('active');
		expect(dropDownListItems[1]).not.toHaveClass('active');
		expect(dropDownListItems[2]).not.toHaveClass('active');

		fireEvent.keyDown(autocompleteInput, {keyCode: 40});

		await act(async () => {
			jest.runAllTimers();
		});

		expect(dropDownListItems[0]).not.toHaveClass('active');
		expect(dropDownListItems[1]).toHaveClass('active');
		expect(dropDownListItems[2]).not.toHaveClass('active');

		fireEvent.keyDown(autocompleteInput, {keyCode: 40});

		await act(async () => {
			jest.runAllTimers();
		});

		expect(dropDownListItems[0]).not.toHaveClass('active');
		expect(dropDownListItems[1]).not.toHaveClass('active');
		expect(dropDownListItems[2]).toHaveClass('active');

		fireEvent.keyDown(autocompleteInput, {keyCode: 38});

		await act(async () => {
			jest.runAllTimers();
		});

		expect(dropDownListItems[0]).not.toHaveClass('active');
		expect(dropDownListItems[1]).toHaveClass('active');
		expect(dropDownListItems[2]).not.toHaveClass('active');

		fireEvent.keyDown(autocompleteInput, {keyCode: 13});

		await act(async () => {
			jest.runAllTimers();
		});

		expect(onSelect).toHaveBeenCalledWith(items[1]);
		expect(autocompleteInput.value).toBe('1test test1');
	});

	it('Fire onChange handler function on change its text and clear input onBlur without select any option', async () => {
		const autocompleteInput = container.querySelector('input.form-control');

		fireEvent.focus(autocompleteInput);

		await act(async () => {
			jest.runAllTimers();
		});

		fireEvent.change(autocompleteInput, {target: {value: '0te'}});

		await act(async () => {
			jest.runAllTimers();
		});

		expect(onChange).toHaveBeenCalled();

		fireEvent.blur(autocompleteInput);

		await act(async () => {
			jest.runAllTimers();
		});

		expect(autocompleteInput.value).toBe('');
	});
});

describe('The Autocomplete component with children should', () => {
	let getByText;

	afterEach(cleanup);

	beforeEach(async () => {
		const autocomplete = render(
			<Autocomplete items={items}>
				<span>Mock child</span>
			</Autocomplete>
		);

		getByText = autocomplete.getByText;

		await act(async () => {
			jest.runAllTimers();
		});
	});

	it('Render the children', () => {
		const mockChild = getByText('Mock child');

		expect(mockChild).toBeTruthy();
	});
});

describe('The Autocomplete component should be render with no items', () => {
	let getByText;

	afterEach(cleanup);

	beforeEach(async () => {
		const autocomplete = render(<Autocomplete items={[]} />);

		getByText = autocomplete.getByText;

		await act(async () => {
			jest.runAllTimers();
		});
	});

	it('Render with "no results were found" message', () => {
		const dropDownEmpty = getByText('no-results-were-found');

		expect(dropDownEmpty).toBeTruthy();
	});
});
