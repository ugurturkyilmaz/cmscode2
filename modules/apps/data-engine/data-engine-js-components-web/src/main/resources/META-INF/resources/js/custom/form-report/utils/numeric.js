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

import ellipsize from './ellipsize';

const _MAX_DELIMITED_NUMBER_LENGTH = 10;

const _THOUSANDS_DELIMITER_REGEX = /\B(?=(\d{3})+(?!\d))/g;

function getDelimiter(key, defaultValue) {
	const delimiter = Liferay.Language.get(key);

	if (delimiter === key) {
		return defaultValue;
	}

	return delimiter;
}

export function formatNumber(number, delimit) {
	let formattedNumber = number.toString();

	const formattedNumberParts = formattedNumber.split('.');

	const formattedDecimal = formattedNumberParts[1];

	const formattedInteger = formattedNumberParts[0].replace(
		_THOUSANDS_DELIMITER_REGEX,
		getDelimiter('thousands-delimiter', ',')
	);

	formattedNumber =
		formattedInteger +
		(formattedDecimal && Number(formattedDecimal) !== 0
			? getDelimiter('decimal-delimiter', '.') + formattedDecimal
			: '');

	if (delimit && formattedNumber.length > _MAX_DELIMITED_NUMBER_LENGTH) {
		formattedNumber = ellipsize(
			formattedNumber,
			_MAX_DELIMITED_NUMBER_LENGTH
		);
	}

	return formattedNumber;
}
