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

package com.liferay.portal.kernel.util;

import java.text.DateFormat;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class DateFormatFactoryUtil {

	public static DateFormat getDate(Locale locale) {
		return _fastDateFormatFactory.getDate(locale);
	}

	public static DateFormat getDate(Locale locale, TimeZone timeZone) {
		return _fastDateFormatFactory.getDate(locale, timeZone);
	}

	public static DateFormat getDate(TimeZone timeZone) {
		return _fastDateFormatFactory.getDate(timeZone);
	}

	public static DateFormatFactory getDateFormatFactory() {
		return _fastDateFormatFactory;
	}

	public static DateFormat getDateTime(Locale locale) {
		return _fastDateFormatFactory.getDateTime(locale);
	}

	public static DateFormat getDateTime(Locale locale, TimeZone timeZone) {
		return _fastDateFormatFactory.getDateTime(locale, timeZone);
	}

	public static DateFormat getDateTime(TimeZone timeZone) {
		return _fastDateFormatFactory.getDateTime(timeZone);
	}

	public static DateFormat getSimpleDateFormat(String pattern) {
		return _fastDateFormatFactory.getSimpleDateFormat(pattern);
	}

	public static DateFormat getSimpleDateFormat(
		String pattern, Locale locale) {

		return _fastDateFormatFactory.getSimpleDateFormat(pattern, locale);
	}

	public static DateFormat getSimpleDateFormat(
		String pattern, Locale locale, TimeZone timeZone) {

		return _fastDateFormatFactory.getSimpleDateFormat(
			pattern, locale, timeZone);
	}

	public static DateFormat getSimpleDateFormat(
		String pattern, TimeZone timeZone) {

		return _fastDateFormatFactory.getSimpleDateFormat(pattern, timeZone);
	}

	public static DateFormat getTime(Locale locale) {
		return _fastDateFormatFactory.getTime(locale);
	}

	public static DateFormat getTime(Locale locale, TimeZone timeZone) {
		return _fastDateFormatFactory.getTime(locale, timeZone);
	}

	public static DateFormat getTime(TimeZone timeZone) {
		return _fastDateFormatFactory.getTime(timeZone);
	}

	public void setDateFormatFactory(DateFormatFactory fastDateFormatFactory) {
		_fastDateFormatFactory = fastDateFormatFactory;
	}

	private static DateFormatFactory _fastDateFormatFactory;

}