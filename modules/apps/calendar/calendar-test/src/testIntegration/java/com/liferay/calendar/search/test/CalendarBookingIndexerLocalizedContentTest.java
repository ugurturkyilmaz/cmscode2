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

package com.liferay.calendar.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.test.util.FieldValuesAssert;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
@Sync
public class CalendarBookingIndexerLocalizedContentTest
	extends BaseCalendarIndexerTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setIndexerClass(CalendarBooking.class);
	}

	@Test
	public void testJapaneseTitle() throws Exception {
		String originalName = "entity name";
		String japaneseName = "新規作成";

		String description = StringUtil.toLowerCase(
			RandomTestUtil.randomString());

		addCalendarBooking(
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, originalName);
					put(LocaleUtil.JAPAN, japaneseName);
				}
			},
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, originalName);
					put(LocaleUtil.JAPAN, japaneseName);
				}
			},
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, description);
					put(LocaleUtil.JAPAN, description);
				}
			});

		Map<String, String> titleMap = HashMapBuilder.put(
			"title_en_US", originalName
		).put(
			"title_ja_JP", japaneseName
		).build();

		String word1 = "新規";
		String word2 = "作成";
		String prefix1 = "新";
		String prefix2 = "作";

		Stream.of(
			word1, word2, prefix1, prefix2
		).forEach(
			keywords -> assertFieldValues(
				"title", LocaleUtil.JAPAN, titleMap, keywords)
		);
	}

	@Test
	public void testJapaneseTitleFullWordOnly() throws Exception {
		String full = "新規作成";
		String partial1 = "新大阪";
		String partial2 = "作戦大成功";

		String description = StringUtil.toLowerCase(
			RandomTestUtil.randomString());

		Stream.of(
			full, partial1, partial2
		).forEach(
			title -> addCalendarBooking(
				new LocalizedValuesMap() {
					{
						put(LocaleUtil.JAPAN, title);
					}
				},
				new LocalizedValuesMap() {
					{
						put(LocaleUtil.US, description);
						put(LocaleUtil.HUNGARY, description);
					}
				},
				new LocalizedValuesMap() {
					{
						put(LocaleUtil.US, description);
						put(LocaleUtil.HUNGARY, description);
					}
				})
		);

		Map<String, String> titleMap = HashMapBuilder.put(
			"title_ja_JP", "新規作成"
		).build();

		String word1 = "新規";
		String word2 = "作成";

		Stream.of(
			word1, word2
		).forEach(
			keywords -> assertFieldValues(
				"title", LocaleUtil.JAPAN, titleMap, keywords)
		);
	}

	protected CalendarBooking addCalendarBooking(
		LocalizedValuesMap titleLocalizedValuesMap,
		LocalizedValuesMap nameLocalizedValuesMap,
		LocalizedValuesMap descriptionLocalizedValuesMap) {

		try {
			ServiceContext serviceContext = getServiceContext();

			Calendar calendar = addCalendar(
				nameLocalizedValuesMap, descriptionLocalizedValuesMap,
				serviceContext);

			return addCalendarBooking(
				titleLocalizedValuesMap, calendar, serviceContext);
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	protected void assertFieldValues(
		String prefix, Locale locale, Map<String, String> titleStrings,
		String searchTerm) {

		Document document = searchOnlyOne(searchTerm, locale);

		FieldValuesAssert.assertFieldValues(
			titleStrings, prefix, document, searchTerm);
	}

}