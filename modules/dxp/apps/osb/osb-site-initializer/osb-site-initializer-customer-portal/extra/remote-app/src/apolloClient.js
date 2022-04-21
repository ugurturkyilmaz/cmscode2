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

import {ApolloClient, InMemoryCache} from '@apollo/client';
import {Liferay} from './common/services/liferay';
import {API_BASE_URL} from './common/utils/constants';

const client = new ApolloClient({
	cache: new InMemoryCache(),
	headers: {
		'x-csrf-token': Liferay.authToken,
	},
	uri: `${API_BASE_URL}/o/graphql`,
});

export default client;
