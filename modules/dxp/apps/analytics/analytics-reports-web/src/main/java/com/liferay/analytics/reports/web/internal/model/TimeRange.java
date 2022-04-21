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

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Adolfo Pérez
 * @author David Arques
 */
public class TimeRange {

	public static TimeRange of(TimeSpan timeSpan, int timeSpanOffset) {
		if (timeSpan == null) {
			throw new IllegalArgumentException("Time span is null");
		}

		if (timeSpanOffset < 0) {
			throw new IllegalArgumentException("Time span offset is negative");
		}

		if (timeSpan == TimeSpan.TODAY) {
			return new TimeRange(true, timeSpan, timeSpanOffset);
		}

		return new TimeRange(false, timeSpan, timeSpanOffset);
	}

	public LocalDate getEndLocalDate() {
		LocalDateTime localDateTime = LocalDateTime.now(_clock);

		if (!_includeToday) {
			localDateTime = localDateTime.minusDays(1);
		}

		localDateTime = localDateTime.minusDays(_getOffsetDays());

		return localDateTime.toLocalDate();
	}

	public LocalDate getStartLocalDate() {
		LocalDate localDate = getEndLocalDate();

		return localDate.minusDays(_timeSpan.getDays() - 1);
	}

	public TimeSpan getTimeSpan() {
		return _timeSpan;
	}

	public int getTimeSpanOffset() {
		return _timeSpanOffset;
	}

	private TimeRange(
		boolean includeToday, TimeSpan timeSpan, int timeSpanOffset) {

		_includeToday = includeToday;
		_timeSpan = timeSpan;
		_timeSpanOffset = timeSpanOffset;

		_clock = Clock.systemUTC();
	}

	private int _getOffsetDays() {
		if (_timeSpanOffset == 0) {
			return 0;
		}

		if (_timeSpan.getDays() == 0) {
			return _timeSpanOffset;
		}

		if (_includeToday) {
			return (_timeSpan.getDays() * _timeSpanOffset) - 1;
		}

		return _timeSpan.getDays() * _timeSpanOffset;
	}

	private final Clock _clock;
	private final boolean _includeToday;
	private final TimeSpan _timeSpan;
	private final int _timeSpanOffset;

}