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

import {openToast} from 'frontend-js-web';

export function errorToast(
	message = Liferay.Language.get('an-unexpected-error-occurred'),
	title = Liferay.Language.get('error')
) {
	openToast({
		message,
		title: `${title}`,
		type: 'danger',
	});
}

export function successToast(
	message = Liferay.Language.get('your-request-completed-successfully'),
	title = Liferay.Language.get('success')
) {
	openToast({
		message,
		title: `${title}`,
		type: 'success',
	});
}
