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

import {ClayIconSpriteContext} from '@clayui/icon';
import React from 'react';
import ReactDOM from 'react-dom';

let counter = 0;

/**
 * Wrapper for ReactDOM render that automatically:
 *
 * - Provides commonly-needed context (for example, the Clay spritemap).
 * - Unmounts when portlets are destroyed based on the received
 *   `portletId` value inside `renderData`. If none is passed, the
 *   component will be automatically unmounted before the next navigation.
 *
 * @param {Function|React.Element} renderable Component, or function that returns an Element, to be rendered.
 * @param {object} renderData Data to be passed to the component as props.
 * @param {HTMLElement} container DOM node where the component is to be mounted.
 *
 * The React docs advise not to rely on the render return value, so we
 * don't propagate it.
 *
 * @see https://reactjs.org/docs/react-dom.html#render
 */
export default function render(
	renderable:
		| NonNullable<React.ReactNode>
		| NonNullable<React.ForwardRefExoticComponent<any>>
		| (() => NonNullable<React.ReactNode>),
	renderData: {
		componentId?: string;
		portletId?: string;
		[key: string]: unknown;
	},
	container: Element
) {
	if (!(window.Liferay as any).SPA || (window.Liferay as any).SPA.app) {
		const {portletId} = renderData;
		const spritemap =
			(window.Liferay as any).ThemeDisplay.getPathThemeImages() +
			'/clay/icons.svg';

		let {componentId} = renderData;

		const destroyOnNavigate = !portletId;

		if (!componentId) {
			componentId = `__UNNAMED_COMPONENT__${portletId}__${counter++}`;
		}

		(window.Liferay as any).component(
			componentId,
			{
				destroy: () => {
					container.classList.remove('lfr-tooltip-scope');

					ReactDOM.unmountComponentAtNode(container);
				},
			},
			{
				destroyOnNavigate,
				portletId,
			}
		);

		const Component: React.ElementType =
			typeof renderable === 'function' ||
			(renderable as any).$$typeof === Symbol.for('react.forward_ref')
				? (renderable as any)
				: null;

		container.classList.add('lfr-tooltip-scope');

		// eslint-disable-next-line @liferay/portal/no-react-dom-render
		ReactDOM.render(
			<ClayIconSpriteContext.Provider value={spritemap}>
				{Component ? <Component {...renderData} /> : renderable}
			</ClayIconSpriteContext.Provider>,
			container
		);
	}
	else {
		(window.Liferay as any).once('SPAReady', () => {
			render(renderable, renderData, container);
		});
	}
}
