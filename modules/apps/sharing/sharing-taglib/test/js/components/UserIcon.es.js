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

import UserIcon from '../../../src/main/resources/META-INF/resources/collaborators/js/components/UserIcon.es';

describe('UserIcon', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {asFragment} = render(
			<UserIcon fullName="Jane Smith" userId="1000" />
		);

		expect(asFragment()).toMatchSnapshot();
	});
});
