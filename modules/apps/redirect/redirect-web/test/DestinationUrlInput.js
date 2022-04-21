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

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import DestinationUrlInput from '../src/main/resources/META-INF/resources/js/DestinationUrlInput';

const defaultProps = {
	namespace: '_portlet_namespace_',
};

const renderComponent = (props = defaultProps) =>
	render(<DestinationUrlInput {...props} />);

describe('DestinationUrlInput', () => {
	afterEach(cleanup);

	it('renders an input element', () => {
		const {getByLabelText} = renderComponent();

		expect(getByLabelText('destination-url')).toBeTruthy();
	});

	it('check url button is disabled if url is empty', () => {
		const {getByTitle} = renderComponent();

		const checkButton = getByTitle('check-url');

		expect(checkButton).toHaveProperty('disabled', true);
	});

	it('check url button is enabled with a valid url', () => {
		const {getByLabelText, getByTitle} = renderComponent();

		const inputElement = getByLabelText('destination-url');

		fireEvent.change(inputElement, {target: {value: '/test'}});

		const checkButton = getByTitle('check-url');

		expect(checkButton.disabled).toBe(true);

		fireEvent.change(inputElement, {target: {value: 'www.test.com'}});

		expect(checkButton.disabled).toBe(false);
	});

	it('window open is called with correct url', () => {
		global.open = jest.fn();

		const testingUrl = 'http://test.com';

		const {getByTitle} = renderComponent({
			initialDestinationUrl: testingUrl,
			...defaultProps,
		});

		const checkButton = getByTitle('check-url');

		fireEvent.click(checkButton);

		expect(global.open).toBeCalledWith(testingUrl, '_blank');
	});

	it('window open appends the http protocol it not present in the url', () => {
		global.open = jest.fn();

		const testingUrl = 'www.test.com';

		const {getByTitle} = renderComponent({
			initialDestinationUrl: testingUrl,
			...defaultProps,
		});

		const checkButton = getByTitle('check-url');

		fireEvent.click(checkButton);

		const finalUrl = 'http://' + testingUrl;

		expect(global.open).toBeCalledWith(finalUrl, '_blank');
	});
});
