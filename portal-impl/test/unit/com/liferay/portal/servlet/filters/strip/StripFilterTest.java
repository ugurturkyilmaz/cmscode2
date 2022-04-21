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

package com.liferay.portal.servlet.filters.strip;

import com.liferay.portal.cache.key.HashCodeHexStringCacheKeyGenerator;
import com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.minifier.MinifierUtil;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.tools.ToolDependencies;
import com.liferay.portal.util.PropsUtil;

import java.io.StringWriter;

import java.nio.CharBuffer;

import java.util.List;
import java.util.logging.Level;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 * @author Miguel Pastor
 */
public class StripFilterTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() {
		ToolDependencies.wireCaches();

		CacheKeyGeneratorUtil cacheKeyGeneratorUtil =
			new CacheKeyGeneratorUtil();

		cacheKeyGeneratorUtil.setDefaultCacheKeyGenerator(
			new HashCodeHexStringCacheKeyGenerator());

		_minifierEnabled = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.MINIFIER_ENABLED));

		PropsUtil.set(PropsKeys.MINIFIER_ENABLED, "true");
	}

	@AfterClass
	public static void tearDownClass() {
		PropsUtil.set(
			PropsKeys.MINIFIER_ENABLED, String.valueOf(_minifierEnabled));
	}

	@Test
	public void testHasMarker() {
		StripFilter stripFilter = new StripFilter();

		// Marker is longer than buffer's remaining

		CharBuffer charBuffer = CharBuffer.wrap("abcdef");

		charBuffer.position(2);
		charBuffer.limit(4);

		char[] marker = "cdef".toCharArray();

		Assert.assertFalse(stripFilter.hasMarker(charBuffer, marker));

		Assert.assertEquals(2, charBuffer.position());

		// No match

		charBuffer = CharBuffer.wrap("abcdef");
		marker = "abce".toCharArray();

		Assert.assertFalse(stripFilter.hasMarker(charBuffer, marker));

		Assert.assertEquals(0, charBuffer.position());

		// Exact match

		charBuffer = CharBuffer.wrap("abcdef");
		marker = "abcd".toCharArray();

		Assert.assertTrue(stripFilter.hasMarker(charBuffer, marker));

		Assert.assertEquals(0, charBuffer.position());

		// Match ignore case

		charBuffer = CharBuffer.wrap("aBcDef");
		marker = "abcd".toCharArray();

		Assert.assertTrue(stripFilter.hasMarker(charBuffer, marker));

		Assert.assertEquals(0, charBuffer.position());
	}

	@Test
	public void testProcessCSS() throws Exception {
		StripFilter stripFilter = new StripFilter();

		char[] styleOpenTag = "style type=\"text/css\">".toCharArray();

		// Missing close tag

		CharBuffer charBuffer = CharBuffer.wrap("style type=\"text/css\">abc");

		StringWriter stringWriter = new StringWriter();

		try (LogCapture logCapture = LoggerTestUtil.configureJDKLogger(
				StripFilter.class.getName(), Level.WARNING)) {

			stripFilter.processCSS(
				null, null, charBuffer, stringWriter, styleOpenTag);

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals("Missing </style>", logEntry.getMessage());
		}

		Assert.assertEquals(
			"style type=\"text/css\">", stringWriter.toString());
		Assert.assertEquals(22, charBuffer.position());

		// Empty tag

		charBuffer = CharBuffer.wrap("style type=\"text/css\"></style>");
		stringWriter = new StringWriter();

		stripFilter.processCSS(
			null, null, charBuffer, stringWriter, styleOpenTag);

		Assert.assertEquals(
			"style type=\"text/css\"></style>", stringWriter.toString());

		Assert.assertEquals(30, charBuffer.position());

		// Minifier spaces

		charBuffer = CharBuffer.wrap("style type=\"text/css\"> \r\t\n</style>");
		stringWriter = new StringWriter();

		stripFilter.processCSS(
			null, null, charBuffer, stringWriter, styleOpenTag);

		Assert.assertEquals(
			"style type=\"text/css\"></style>", stringWriter.toString());

		Assert.assertEquals(34, charBuffer.position());

		// Minifier code

		String code =
			".a{ position: relative; outline: none; overflow: hidden; " +
				"text-align: left /* Force default alignment */ }";

		String minifiedCode = MinifierUtil.minifyCss(code);

		charBuffer = CharBuffer.wrap(
			"style type=\"text/css\">" + code + "</style>");

		stringWriter = new StringWriter();

		stripFilter.processCSS(
			null, null, charBuffer, stringWriter, styleOpenTag);

		Assert.assertEquals(
			"style type=\"text/css\">" + minifiedCode + "</style>",
			stringWriter.toString());

		Assert.assertEquals(code.length() + 30, charBuffer.position());

		// Minifier code with trailing spaces

		charBuffer = CharBuffer.wrap(
			"style type=\"text/css\">" + code + "</style> \r\t\n");
		stringWriter = new StringWriter();

		stripFilter.processCSS(
			null, null, charBuffer, stringWriter, styleOpenTag);

		Assert.assertEquals(
			"style type=\"text/css\">" + minifiedCode + "</style> ",
			stringWriter.toString());

		Assert.assertEquals(code.length() + 34, charBuffer.position());
	}

	@Test
	public void testProcessJavaScript() throws Exception {
		StripFilter stripFilter = new StripFilter();

		// Missing close tag

		CharBuffer charBuffer = CharBuffer.wrap("script>abc");

		StringWriter stringWriter = new StringWriter();

		try (LogCapture logCapture = LoggerTestUtil.configureJDKLogger(
				StripFilter.class.getName(), Level.WARNING)) {

			stripFilter.processJavaScript(
				"test.js", charBuffer, stringWriter, "script".toCharArray());

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals("Missing </script>", logEntry.getMessage());

			Assert.assertEquals("script>", stringWriter.toString());
		}

		Assert.assertEquals(7, charBuffer.position());

		// Empty tag

		charBuffer = CharBuffer.wrap("script></script>");
		stringWriter = new StringWriter();

		stripFilter.processJavaScript(
			"test.js", charBuffer, stringWriter, "script".toCharArray());

		Assert.assertEquals("script></script>", stringWriter.toString());

		Assert.assertEquals(16, charBuffer.position());
	}

	@Test
	public void testProcessPre() throws Exception {
		StripFilter stripFilter = new StripFilter();

		// Missing close tag

		CharBuffer charBuffer = CharBuffer.wrap("pre>abcde");

		StringWriter stringWriter = new StringWriter();

		try (LogCapture logCapture = LoggerTestUtil.configureJDKLogger(
				StripFilter.class.getName(), Level.WARNING)) {

			stripFilter.processPre(charBuffer, stringWriter);

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals("Missing </pre>", logEntry.getMessage());

			Assert.assertEquals("pre", stringWriter.toString());
			Assert.assertEquals(3, charBuffer.position());
		}

		// Without trailing spaces

		charBuffer = CharBuffer.wrap("pre>a b </pre>");

		stringWriter = new StringWriter();

		stripFilter.processPre(charBuffer, stringWriter);

		Assert.assertEquals("pre>a b </pre>", stringWriter.toString());

		Assert.assertEquals(14, charBuffer.position());

		// With trailing spaces

		charBuffer = CharBuffer.wrap("pre>a b </pre> \r\n\tc");

		stringWriter = new StringWriter();

		stripFilter.processPre(charBuffer, stringWriter);

		Assert.assertEquals("pre>a b </pre> ", stringWriter.toString());

		Assert.assertEquals(18, charBuffer.position());
	}

	@Test
	public void testProcessTextArea() throws Exception {
		StripFilter stripFilter = new StripFilter();

		// Missing close tag

		CharBuffer charBuffer = CharBuffer.wrap("textarea >abcde");

		StringWriter stringWriter = new StringWriter();

		try (LogCapture logCapture = LoggerTestUtil.configureJDKLogger(
				StripFilter.class.getName(), Level.WARNING)) {

			stripFilter.processTextArea(charBuffer, stringWriter);

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals("Missing </textArea>", logEntry.getMessage());

			Assert.assertEquals("textarea ", stringWriter.toString());
			Assert.assertEquals(9, charBuffer.position());
		}

		// Without trailing spaces

		charBuffer = CharBuffer.wrap("textarea >a b </textarea>");

		stringWriter = new StringWriter();

		stripFilter.processTextArea(charBuffer, stringWriter);

		Assert.assertEquals(
			"textarea >a b </textarea>", stringWriter.toString());

		Assert.assertEquals(25, charBuffer.position());

		// With trailing spaces

		charBuffer = CharBuffer.wrap("textarea >a b </textarea> \r\n\tc");

		stringWriter = new StringWriter();

		stripFilter.processTextArea(charBuffer, stringWriter);

		Assert.assertEquals(
			"textarea >a b </textarea> ", stringWriter.toString());

		Assert.assertEquals(29, charBuffer.position());
	}

	@Test
	public void testSkipWhiteSpace() throws Exception {
		StripFilter stripFilter = new StripFilter();

		// Empty buffer

		CharBuffer charBuffer = CharBuffer.allocate(0);

		StringWriter stringWriter = new StringWriter();

		stripFilter.skipWhiteSpace(charBuffer, stringWriter, true);

		Assert.assertEquals("", stringWriter.toString());

		Assert.assertEquals(0, charBuffer.position());

		// No leading space

		charBuffer = CharBuffer.wrap("abc \t\r\n");
		stringWriter = new StringWriter();

		stripFilter.skipWhiteSpace(charBuffer, stringWriter, true);

		Assert.assertEquals("", stringWriter.toString());

		Assert.assertEquals(0, charBuffer.position());

		// Single leading space

		charBuffer = CharBuffer.wrap(" ");
		stringWriter = new StringWriter();

		stripFilter.skipWhiteSpace(charBuffer, stringWriter, true);

		Assert.assertEquals(" ", stringWriter.toString());

		Assert.assertEquals(1, charBuffer.position());

		charBuffer = CharBuffer.wrap("\t");
		stringWriter = new StringWriter();

		stripFilter.skipWhiteSpace(charBuffer, stringWriter, true);

		Assert.assertEquals(" ", stringWriter.toString());

		Assert.assertEquals(1, charBuffer.position());

		charBuffer = CharBuffer.wrap("\r");
		stringWriter = new StringWriter();

		stripFilter.skipWhiteSpace(charBuffer, stringWriter, true);

		Assert.assertEquals(" ", stringWriter.toString());

		Assert.assertEquals(1, charBuffer.position());

		charBuffer = CharBuffer.wrap("\n");
		stringWriter = new StringWriter();

		stripFilter.skipWhiteSpace(charBuffer, stringWriter, true);

		Assert.assertEquals(" ", stringWriter.toString());

		Assert.assertEquals(1, charBuffer.position());

		// Multiple leading spaces

		charBuffer = CharBuffer.wrap(" \t\r\n");
		stringWriter = new StringWriter();

		stripFilter.skipWhiteSpace(charBuffer, stringWriter, true);

		Assert.assertEquals(" ", stringWriter.toString());

		Assert.assertEquals(4, charBuffer.position());
	}

	private static boolean _minifierEnabled;

}