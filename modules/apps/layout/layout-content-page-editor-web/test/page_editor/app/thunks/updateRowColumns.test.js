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

import updateRowColumns from '../../../../src/main/resources/META-INF/resources/page_editor/app/actions/updateRowColumns';
import LayoutService from '../../../../src/main/resources/META-INF/resources/page_editor/app/services/LayoutService';
import updateRowColumnsThunk from '../../../../src/main/resources/META-INF/resources/page_editor/app/thunks/updateRowColumns';

jest.mock(
	'../../../../src/main/resources/META-INF/resources/page_editor/app/config',
	() => ({config: {}})
);

jest.mock(
	'../../../../src/main/resources/META-INF/resources/page_editor/app/actions/updateRowColumns',
	() => jest.fn()
);

jest.mock(
	'../../../../src/main/resources/META-INF/resources/page_editor/app/services/LayoutService',
	() => ({updateRowColumns: jest.fn()})
);

describe('updateRowColumns', () => {
	beforeEach(() => {
		LayoutService.updateRowColumns.mockClear();
		updateRowColumns.mockClear();

		LayoutService.updateRowColumns.mockImplementation(() =>
			Promise.resolve()
		);

		LayoutService.updateRowColumns.mockImplementation(() =>
			Promise.resolve({
				layoutData: {
					items: {},
					version: 1,
				},
			})
		);
	});

	const runThunk = () =>
		updateRowColumnsThunk({
			itemId: '0',
			numberOfColumns: 6,
			segmentsExperienceId: '0',
		})(() => {});

	it('calls LayoutService.updateRowColumns with the given information', () => {
		runThunk();

		expect(LayoutService.updateRowColumns).toHaveBeenCalledWith(
			expect.objectContaining({
				itemId: '0',
				numberOfColumns: 6,
				segmentsExperienceId: '0',
			})
		);
	});

	it('dispatch updateRowColumns action when the promise is resolved', async () => {
		await runThunk();

		expect(updateRowColumns).toHaveBeenCalledWith({
			itemId: '0',
			layoutData: {
				items: {},
				version: 1,
			},
		});
	});
});
