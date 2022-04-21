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

import {useEventListener} from '@liferay/frontend-js-react-web';
import classNames from 'classnames';
import React, {useCallback, useContext} from 'react';

import {EVENT_TYPES} from '../actions/eventTypes.es';
import {useForm} from '../hooks/useForm.es';
import {ParentFieldContext} from './Field/ParentFieldContext.es';

const RESIZE_DIRECTIONS = {
	LEFT: 'left',
	RIGHT: 'right',
};

const MAX_COLUMNS = 12;

const ResizableColumn = ({
	children,
	currentLoc,
	disabled,
	instanceId,
	onResizing,
	resizeInfoRef,
	rowRef,
}) => {
	const {loc = []} = useContext(ParentFieldContext);

	const dispatch = useForm();

	const handleMouseDown = (event, direction) => {
		event.preventDefault();
		event.stopPropagation();

		resizeInfoRef.current = {
			...resizeInfoRef.current,
			direction,
			instanceId,
		};

		onResizing(true);
	};

	const handleMouseMove = useCallback(
		(event) => {
			if (
				resizeInfoRef.current &&
				resizeInfoRef.current.instanceId === instanceId
			) {
				let column = Math.floor(
					((event.clientX -
						rowRef.current?.getBoundingClientRect().left) *
						(MAX_COLUMNS * 10)) /
						rowRef.current?.clientWidth /
						10
				);

				if (column > MAX_COLUMNS - 1) {
					column = MAX_COLUMNS - 1;
				}

				if (
					column >= 0 &&
					column !== resizeInfoRef.current.lastColumnValue
				) {
					resizeInfoRef.current = {
						...resizeInfoRef.current,
						lastColumnValue: column,
					};

					dispatch({
						payload: {
							column,
							direction: resizeInfoRef.current.direction,
							loc: [...loc, currentLoc],
						},
						type: EVENT_TYPES.DND.RESIZE,
					});
				}
			}
		},
		[currentLoc, dispatch, instanceId, loc, resizeInfoRef, rowRef]
	);

	const handleMouseUp = useCallback(() => {
		onResizing(false);

		resizeInfoRef.current = null;
	}, [onResizing, resizeInfoRef]);

	useEventListener('mousemove', handleMouseMove, false, document.body);

	useEventListener('mouseup', handleMouseUp, {passive: true}, document.body);

	return (
		<>
			<div
				className={classNames(
					'ddm-resize-handle ddm-resize-handle-left',
					{
						hide: disabled,
					}
				)}
				onMouseDown={(event) =>
					handleMouseDown(event, RESIZE_DIRECTIONS.LEFT)
				}
			/>
			{children}
			<div
				className={classNames(
					'ddm-resize-handle ddm-resize-handle-right',
					{
						hide: disabled,
					}
				)}
				onMouseDown={(event) =>
					handleMouseDown(event, RESIZE_DIRECTIONS.RIGHT)
				}
			/>
		</>
	);
};

ResizableColumn.displayName = 'ResizableColumn';

export default ResizableColumn;
