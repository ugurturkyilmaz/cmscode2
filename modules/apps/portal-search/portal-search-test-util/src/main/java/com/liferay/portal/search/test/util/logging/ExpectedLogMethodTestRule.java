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

package com.liferay.portal.search.test.util.logging;

import com.liferay.portal.kernel.test.rule.MethodTestRule;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import org.junit.Assert;
import org.junit.runner.Description;

/**
 * @author André de Oliveira
 */
public class ExpectedLogMethodTestRule extends MethodTestRule<Void> {

	public static final ExpectedLogMethodTestRule INSTANCE =
		new ExpectedLogMethodTestRule();

	public void verify() {
		if (!_matcherBuilder.isAnythingExpected()) {
			return;
		}

		Assert.assertThat(getlogEntries(), _matcherBuilder.build());
	}

	@Override
	protected void afterMethod(
		Description description, Void unused, Object target) {

		ExpectedLog expectedLog = description.getAnnotation(ExpectedLog.class);

		if (expectedLog == null) {
			return;
		}

		try {
			verify();
		}
		finally {
			closeCaptureHandler();
		}
	}

	@Override
	protected Void beforeMethod(Description description, Object target) {
		ExpectedLog expectedLog = description.getAnnotation(ExpectedLog.class);

		if (expectedLog == null) {
			return null;
		}

		_matcherBuilder.clear();

		Class<?> clazz = expectedLog.expectedClass();

		ExpectedLog.Level level = expectedLog.expectedLevel();

		_configure(clazz.getName(), Level.parse(level.name()));

		_matcherBuilder.add(
			LogOutputMatcher.hasMessage(
				CoreMatchers.containsString(expectedLog.expectedLog())));

		return null;
	}

	protected void closeCaptureHandler() {
		if (_logCapture == null) {
			return;
		}

		_logCapture.close();

		_logCapture = null;
	}

	protected List<LogEntry> getlogEntries() {
		if (_logCapture != null) {
			return _logCapture.getLogEntries();
		}

		return Collections.emptyList();
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
	 */
	@Deprecated
	protected List<LogRecord> getLogRecords() {
		return Collections.emptyList();
	}

	protected void openCaptureHandler(String name, Level level) {
		_logCapture = LoggerTestUtil.configureJDKLogger(name, level);
	}

	protected static class LogOutputMatcher<T extends List<LogEntry>>
		extends TypeSafeMatcher<T> {

		@Factory
		public static <T extends List<LogEntry>> Matcher<T> hasMessage(
			Matcher<String> matcher) {

			return new LogOutputMatcher<>(matcher);
		}

		public LogOutputMatcher(Matcher<String> matcher) {
			this.matcher = matcher;
		}

		@Override
		public void describeTo(org.hamcrest.Description description) {
			description.appendText("log output with message ");

			description.appendDescriptionOf(matcher);
		}

		@Override
		protected void describeMismatchSafely(
			T logRecords, org.hamcrest.Description description) {

			description.appendText("log output ");

			matcher.describeMismatch(toString(logRecords), description);
		}

		@Override
		protected boolean matchesSafely(T logEntries) {
			if (matcher.matches(toString(logEntries))) {
				return true;
			}

			return false;
		}

		protected String toString(T logEntries) {
			Stream<LogEntry> stream = logEntries.stream();

			return stream.map(
				LogEntry::getMessage
			).collect(
				Collectors.joining()
			);
		}

		protected final Matcher<String> matcher;

	}

	protected class MatcherBuilder<T> {

		protected void add(Matcher<T> matcher) {
			matchers.add(matcher);
		}

		protected Matcher<?> build() {
			if (matchers.size() == 1) {
				return matchers.get(0);
			}

			return CoreMatchers.allOf(new ArrayList<>((List)matchers));
		}

		protected void clear() {
			matchers.clear();
		}

		protected boolean isAnythingExpected() {
			if (matchers.isEmpty()) {
				return false;
			}

			return true;
		}

		protected final List<Matcher<T>> matchers = new ArrayList<>();

	}

	private void _configure(String name, Level level) {
		if ((name == null) || (level == null)) {
			return;
		}

		closeCaptureHandler();

		openCaptureHandler(name, level);
	}

	private LogCapture _logCapture;
	private final MatcherBuilder _matcherBuilder = new MatcherBuilder();

}