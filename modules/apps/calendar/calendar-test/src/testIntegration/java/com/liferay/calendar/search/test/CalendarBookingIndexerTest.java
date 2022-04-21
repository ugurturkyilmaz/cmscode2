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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.test.util.HitsAssert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class CalendarBookingIndexerTest extends BaseCalendarIndexerTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setIndexerClass(CalendarBooking.class);
	}

	@Test
	public void testBasicSearch() throws Exception {
		String title = RandomTestUtil.randomString();

		addCalendarBooking(
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, title);
				}
			});

		searchOnlyOne(title, LocaleUtil.US);
	}

	@Test
	public void testMultiLocale() throws Exception {
		String originalTitle = "entity title";
		String translatedTitle = "entitas neve";

		addCalendarBooking(
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, originalTitle);
					put(LocaleUtil.HUNGARY, translatedTitle);
				}
			});

		searchOnlyOne("nev", LocaleUtil.HUNGARY);
	}

	@Test
	public void testTrash() throws Exception {
		String title = RandomTestUtil.randomString();

		CalendarBooking calendarBooking = addCalendarBooking(
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, title);
				}
			});

		calendarBookingLocalService.moveCalendarBookingToTrash(
			TestPropsValues.getUserId(), calendarBooking);

		HitsAssert.assertNoHits(search(getSearchContext(title, LocaleUtil.US)));

		HitsAssert.assertOnlyOne(
			search(withStatusInTrash(getSearchContext(title, LocaleUtil.US))));
	}

	protected CalendarBooking addCalendarBooking(
		LocalizedValuesMap titleLocalizedValuesMap) {

		try {
			ServiceContext serviceContext = getServiceContext();

			Calendar calendar = addCalendar(
				new LocalizedValuesMap() {
					{
						put(
							LocaleUtil.getSiteDefault(),
							RandomTestUtil.randomString());
					}
				},
				new LocalizedValuesMap(), serviceContext);

			return addCalendarBooking(
				titleLocalizedValuesMap, calendar, serviceContext);
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	protected SearchContext withStatusInTrash(SearchContext searchContext) {
		searchContext.setAttribute(
			Field.STATUS, new int[] {WorkflowConstants.STATUS_IN_TRASH});

		return searchContext;
	}

}