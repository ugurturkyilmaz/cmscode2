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

import ClayLayout from '@clayui/layout';
import ClayTabs from '@clayui/tabs';
import React, {useMemo} from 'react';

import {PagesVisitor} from '../../utils/visitors.es';
import {EVENT_TYPES} from '../actions/eventTypes.es';
import {useForm} from '../hooks/useForm.es';

export function Tabs({activePage, pages}) {
	const dispatch = useForm();

	const isAutocompleteVisible = useMemo(() => {
		let hasVisibleFields = true;

		const visitor = new PagesVisitor(pages);

		visitor.mapFields((field) => {
			if (field.fieldName === 'autocomplete' && !field.visible) {
				hasVisibleFields = false;
			}
		});

		return hasVisibleFields;
	}, [pages]);

	return (
		<nav className="component-tbar ddm-form-tabs mb-3 tbar">
			<ClayLayout.ContainerFluid className="pr-0">
				<ClayTabs>
					{pages.map((page, index) => (
						<ClayTabs.Item
							active={index === activePage}
							className={
								!isAutocompleteVisible && index === 2 && 'hide'
							}
							disabled={!page.enabled}
							key={index}
							onClick={() =>
								dispatch({
									payload: {activePage: index},
									type: EVENT_TYPES.PAGE.CHANGE,
								})
							}
						>
							<span className="navbar-text-truncate">
								{page.title}
							</span>
						</ClayTabs.Item>
					))}
				</ClayTabs>
			</ClayLayout.ContainerFluid>
		</nav>
	);
}
