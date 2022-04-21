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

import {createMemoryHistory} from 'history';
import React, {cloneElement, useMemo, useState} from 'react';
import {Route, Router} from 'react-router-dom';

import {AppContext} from '../../src/main/resources/META-INF/resources/js/components/AppContext.es';
import {FilterContextProvider} from '../../src/main/resources/META-INF/resources/js/shared/components/filter/FilterContext.es';

const withParamsMock = (...components) => ({
	history,
	location: {search: query},
	match: {params: routeParams},
}) => {
	return components.map((component, key) => {
		if (routeParams.sort) {
			routeParams.sort = decodeURIComponent(routeParams.sort);
		}

		return cloneElement(component, {
			...routeParams,
			history,
			key,
			query,
			routeParams,
		});
	});
};

const MockRouter = ({
	children,
	initialPath = '/1/20/title%3Aasc',
	initialReindexStatuses = [],
	isAmPm,
	path = '/:page/:pageSize/:sort',
	query = '?backPath=%2F',
	userId = '1',
	userName = 'Test Test',
	withoutRouterProps,
}) => {
	const [title, setTitle] = useState(null);
	const [reindexStatuses, setReindexStatuses] = useState(
		initialReindexStatuses
	);
	const [fetchDateModified, setFetchDateModified] = useState(false);

	const contextState = useMemo(
		() => ({
			defaultDelta: 20,
			deltaValues: [5, 10, 20, 30, 50, 75],
			fetchDateModified,
			isAmPm,
			maxPages: 3,
			portletNamespace: 'workflow',
			reindexStatuses,
			setFetchDateModified,
			setReindexStatuses,
			setTitle,
			title,
			userId,
			userName,
		}),
		// eslint-disable-next-line react-hooks/exhaustive-deps
		[reindexStatuses, title]
	);

	const initialEntries = useMemo(
		() => [{pathname: initialPath, search: query}],
		[initialPath, query]
	);

	const history = useMemo(
		() => createMemoryHistory({initialEntries, keyLength: 0}),
		[initialEntries]
	);

	const component = withoutRouterProps
		? () => cloneElement(children)
		: withParamsMock(children);

	return (
		<Router history={history}>
			<AppContext.Provider value={contextState}>
				<FilterContextProvider>
					<Route path={path} render={component} />
				</FilterContextProvider>
			</AppContext.Provider>
		</Router>
	);
};

export {MockRouter};
