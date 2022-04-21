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

import React from 'react';

import useIsMounted from './useIsMounted';

const {useRef} = React;

/**
 * "Middleware" hook intended to wrap `useReducer` that enables you to dispatch
 * thunks (ie. functions that dispatch actions) as well as plain actions (ie.
 * objects).
 */
export default function useThunk<R extends React.Reducer<any, any>>([
	state,
	dispatch,
]: [React.ReducerState<R>, React.Dispatch<React.ReducerAction<R>>]) {
	const isMounted = useIsMounted();

	// Use a ref to ensure our `dispatch` is stable across renders, just
	// like the React-provided `dispatch` that we're wrapping.

	const thunkDispatchRef = useRef((action: any) => {
		if (isMounted()) {
			if (typeof action === 'function') {
				return action((payload: any) => {
					if (isMounted()) {
						dispatch(payload);
					}
				});
			}
			else {
				dispatch(action);
			}
		}
	});

	return [state, thunkDispatchRef.current];
}
