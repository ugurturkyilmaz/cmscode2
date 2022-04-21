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

export type Direction = 'ltr' | 'rtl';

export type FieldChangeEventHandler<T = any> = (event: {
	target: {value: T};
}) => void;

export type Locale =
	| 'ar_SA'
	| 'ca_ES'
	| 'de_DE'
	| 'en_US'
	| 'es_ES'
	| 'fi_FI'
	| 'fr_FR'
	| 'hu_HU'
	| 'nl_NL'
	| 'ja_JP'
	| 'pt_BR'
	| 'sv_SE'
	| 'zh_CN';

export type LocalizedTextKey =
	| 'choose-an-option'
	| 'days'
	| 'date'
	| 'date-fields'
	| 'decimal-places'
	| 'decimal-separator'
	| 'for-security-reasons-upload-field-repeatability-is-limited-the-limit-is-defined-in-x-system-settings-x'
	| 'input-mask-append-placeholder'
	| 'minus'
	| 'months'
	| 'operation'
	| 'plus'
	| 'prefix'
	| 'prefix-or-suffix'
	| 'quantity'
	| 'suffix'
	| 'the-maximum-length-is-10-characters'
	| 'thousands-separator'
	| 'unit'
	| 'years';

export type LocalizedValue<T> = {[key in Locale]?: T};
