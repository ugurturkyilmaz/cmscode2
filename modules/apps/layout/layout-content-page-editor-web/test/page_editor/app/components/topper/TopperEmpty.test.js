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

import '@testing-library/jest-dom/extend-expect';
import {cleanup, render} from '@testing-library/react';
import React from 'react';
import {DndProvider} from 'react-dnd';
import {HTML5Backend} from 'react-dnd-html5-backend';

import Row from '../../../../../src/main/resources/META-INF/resources/page_editor/app/components/layout-data-items/Row';
import TopperEmpty from '../../../../../src/main/resources/META-INF/resources/page_editor/app/components/topper/Topper';
import {LAYOUT_DATA_ITEM_TYPES} from '../../../../../src/main/resources/META-INF/resources/page_editor/app/config/constants/layoutDataItemTypes';
import {VIEWPORT_SIZES} from '../../../../../src/main/resources/META-INF/resources/page_editor/app/config/constants/viewportSizes';
import {ControlsProvider} from '../../../../../src/main/resources/META-INF/resources/page_editor/app/contexts/ControlsContext';
import {StoreAPIContextProvider} from '../../../../../src/main/resources/META-INF/resources/page_editor/app/contexts/StoreContext';

jest.mock(
	'../../../../../src/main/resources/META-INF/resources/page_editor/app/config',
	() => ({
		config: {
			commonStyles: [
				{
					styles: [
						{
							defaultValue: 'left',
							name: 'textAlign',
						},
					],
				},
			],
			frontendTokens: {},
		},
	})
);

const renderTopperEmpty = ({
	hasUpdatePermissions = true,
	lockedExperience = false,
} = {}) => {
	const row = {
		children: [],
		config: {
			styles: {},
		},
		itemId: 'row',
		parentId: null,
		type: LAYOUT_DATA_ITEM_TYPES.row,
	};

	const layoutData = {
		items: {row},
	};

	return render(
		<DndProvider backend={HTML5Backend}>
			<ControlsProvider>
				<StoreAPIContextProvider
					getState={() => ({
						layoutData,
						permissions: {
							LOCKED_SEGMENTS_EXPERIMENT: lockedExperience,
							UPDATE: hasUpdatePermissions,
						},
						selectedViewportSize: VIEWPORT_SIZES.desktop,
					})}
				>
					<TopperEmpty item={row} layoutData={layoutData}>
						<Row item={row} layoutData={layoutData}></Row>
					</TopperEmpty>
				</StoreAPIContextProvider>
			</ControlsProvider>
		</DndProvider>
	);
};

describe('TopperEmpty', () => {
	afterEach(cleanup);

	it('does not render TopperEmpty if user has no permissions', () => {
		const {baseElement} = renderTopperEmpty({hasUpdatePermissions: false});

		expect(baseElement.querySelector('.page-editor__topper')).toBe(null);
	});

	it('renders empty TopperEmpty if user has permissions', () => {
		const {baseElement} = renderTopperEmpty({});

		expect(
			baseElement.querySelector('.page-editor__topper')
		).toBeInTheDocument();
	});
});
