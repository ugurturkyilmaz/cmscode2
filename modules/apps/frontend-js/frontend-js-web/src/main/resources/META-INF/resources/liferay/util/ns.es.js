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

import memoize from 'lodash.memoize';

const cached = (fn) => {
	return memoize(fn, (...args) => {
		return args.length > 1
			? Array.prototype.join.call(args, '_')
			: String(args[0]);
	});
};

const nsCached = cached((namespace, str) => {
	if (typeof str !== 'undefined' && !(str.lastIndexOf(namespace, 0) === 0)) {
		str = `${namespace}${str}`;
	}

	return str;
});

export default function (namespace, object) {
	let value;

	if (typeof object !== 'object') {
		value = nsCached(namespace, object);
	}
	else {
		value = {};

		const keys = Object.keys(object);

		keys.forEach((item) => {
			const originalItem = item;

			item = nsCached(namespace, item);
			value[item] = object[originalItem];
		});
	}

	return value;
}
