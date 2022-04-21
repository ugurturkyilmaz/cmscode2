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

import {cleanup, render} from '@testing-library/react';
import React from 'react';
import {wrapInTestContext} from 'react-dnd-test-utils';

import {default as Component} from '../../../../src/main/resources/META-INF/resources/js/components/criteria_builder/CriteriaGroup.es';

const connectDnd = jest.fn((element) => element);

const CriteriaGroup = wrapInTestContext(Component);

describe('CriteriaGroup', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {asFragment} = render(
			<CriteriaGroup connectDragPreview={connectDnd} propertyKey="user" />
		);

		expect(asFragment()).toMatchSnapshot();
	});
});
