/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.analytics.reports.web.internal.model;

import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

/**
 * @author David Arques
 */
public enum TimeSpan {

	LAST_7_DAYS("last-7-days", 7), LAST_30_DAYS("last-30-days", 30),
	TODAY("today", 1);

	public static String defaultTimeSpanKey() {
		return LAST_7_DAYS.getKey();
	}

	public static TimeSpan of(String key) {
		if (Validator.isNull(key)) {
			throw new IllegalArgumentException("Time span key is null");
		}

		for (TimeSpan timeSpan : values()) {
			if (Objects.equals(key, timeSpan.getKey())) {
				return timeSpan;
			}
		}

		throw new IllegalArgumentException("Invalid time span key " + key);
	}

	public int getDays() {
		return _days;
	}

	public String getKey() {
		return _key;
	}

	public TimeRange toTimeRange(int timeSpanOffset) {
		if (timeSpanOffset < 0) {
			throw new IllegalArgumentException("Time span offset is negative");
		}

		return TimeRange.of(this, timeSpanOffset);
	}

	private TimeSpan(String key, int days) {
		_key = key;
		_days = days;
	}

	private final int _days;
	private final String _key;

}