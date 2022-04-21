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

import AJAX from '../AJAX/index';
import CommerceCookie from '../cookies';
import * as Events from '../eventsDefinitions';
import DDMFormHandler from '../forms/DDMFormHandler';
import * as FormUtils from '../forms/index';
import * as BaseUtils from '../index';

export default {
	AJAX,
	BaseUtils,
	CommerceCookie,
	Events,
	FormUtils: {
		...FormUtils,
		DDMFormHandler,
	},
};
