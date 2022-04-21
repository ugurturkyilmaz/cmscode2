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

package com.liferay.portal.search.searcher.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Dictionary;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Bryan Engler
 */
@RunWith(Arquillian.class)
public class SearchResponseGetResponseStringTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		setUpElasticsearchConfiguration();

		_companyId = TestPropsValues.getCompanyId();
	}

	@After
	public void tearDown() throws Exception {
		tearDownElasticsearchConfiguration();
	}

	@Ignore
	@Test
	public void testGetResponseStringContainsException() {
		try (LogCapture logCapture = LoggerTestUtil.configureLog4JLogger(
				"com.liferay.portal.search.elasticsearch7.internal." +
					"ElasticsearchIndexSearcher",
				LoggerTestUtil.ERROR)) {

			SearchResponse searchResponse = _searcher.search(
				_searchRequestBuilderFactory.builder(
				).companyId(
					_companyId
				).emptySearchEnabled(
					true
				).query(
					_queries.string("t/est")
				).build());

			String responseString = searchResponse.getResponseString();

			Assert.assertNotNull(responseString);

			List<LogEntry> logEntries = logCapture.getLogEntries();

			LogEntry logEntry = logEntries.get(0);

			String logEntryMessage = logEntry.getMessage();

			Assert.assertTrue(
				responseString + " -> " + logEntryMessage,
				responseString.startsWith(logEntryMessage));
		}
	}

	@Ignore
	@Test
	public void testGetResponseStringContainsResponse() {
		SearchResponse searchResponse = _searcher.search(
			_searchRequestBuilderFactory.builder(
			).companyId(
				_companyId
			).emptySearchEnabled(
				true
			).includeResponseString(
				true
			).query(
				_queries.string("test")
			).build());

		Assert.assertNotNull(searchResponse.getResponseString());

		Assert.assertNotEquals(
			StringPool.BLANK, searchResponse.getResponseString());
	}

	protected void setUpElasticsearchConfiguration() throws Exception {
		_configuration = configurationAdmin.getConfiguration(
			ElasticsearchConfiguration.class.getName(), StringPool.QUESTION);

		_properties = _configuration.getProperties();

		_configuration.update(
			HashMapDictionaryBuilder.<String, Object>put(
				"logExceptionsOnly", true
			).put(
				"productionModeEnabled", true
			).build());

		Thread.sleep(5000);
	}

	protected void tearDownElasticsearchConfiguration() throws Exception {
		_configuration.update(_properties);

		Thread.sleep(5000);
	}

	@Inject
	protected static ConfigurationAdmin configurationAdmin;

	private long _companyId;
	private Configuration _configuration;
	private Dictionary<String, Object> _properties;

	@Inject
	private Queries _queries;

	@Inject
	private Searcher _searcher;

	@Inject
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

}