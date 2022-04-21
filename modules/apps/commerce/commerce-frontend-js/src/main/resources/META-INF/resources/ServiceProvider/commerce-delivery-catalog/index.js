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

import {composeAPI} from '../composeAPI';
import * as v1 from './v1.0/index';

const BASE_ENDPOINT = '/o/headless-commerce-delivery-catalog/';

const APIs = {
	v1,
};

export default function main(version) {
	return composeAPI(version, APIs, BASE_ENDPOINT);
}
