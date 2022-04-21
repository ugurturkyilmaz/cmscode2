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

package com.liferay.portal.search.elasticsearch7.internal.logging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.search.elasticsearch7.internal.ElasticsearchIndexWriter;
import com.liferay.portal.search.elasticsearch7.internal.LiferayElasticsearchIndexingFixtureFactory;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.logging.ExpectedLog;
import com.liferay.portal.search.test.util.logging.ExpectedLogMethodTestRule;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.ElasticsearchStatusException;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Bryan Engler
 */
public class ElasticsearchIndexWriterExceptionsTest
	extends BaseIndexingTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			ExpectedLogMethodTestRule.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Test
	public void testAddDocument() {
		expectedException.expect(ElasticsearchStatusException.class);
		expectedException.expectMessage(
			"type=mapper_parsing_exception, reason=failed to parse field " +
				"[expirationDate] of type [date]");

		addDocument(
			DocumentCreationHelpers.singleKeyword(
				Field.EXPIRATION_DATE, "text"));
	}

	@Test
	public void testAddDocuments() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Bulk add failed");

		List<Document> documents = new ArrayList<>();

		Document document = new DocumentImpl();

		document.addKeyword(Field.EXPIRATION_DATE, "text");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.addDocuments(createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testCommit() {
		expectedException.expect(ElasticsearchStatusException.class);
		expectedException.expectMessage(
			"type=index_not_found_exception, reason=no such index");

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.commit(searchContext);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = ElasticsearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.INFO, expectedLog = "no such index"
	)
	@Test
	public void testDeleteDocument() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.deleteDocument(searchContext, "1");
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testDeleteDocuments() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Bulk delete failed");

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		List<String> uids = new ArrayList<>();

		uids.add("1");

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.deleteDocuments(searchContext, uids);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testDeleteEntityDocuments() {
		expectedException.expect(ElasticsearchStatusException.class);
		expectedException.expectMessage(
			"type=index_not_found_exception, reason=no such index");

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.deleteEntityDocuments(searchContext, "test");
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testPartiallyUpdateDocument() {
		expectedException.expect(ElasticsearchStatusException.class);
		expectedException.expectMessage(
			"type=document_missing_exception, reason=[LiferayDocumentType]");

		Document document = new DocumentImpl();

		document.addKeyword(Field.UID, "1");

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.partiallyUpdateDocument(
				createSearchContext(), document);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testPartiallyUpdateDocuments() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Bulk partial update failed");

		Document document = new DocumentImpl();

		List<Document> documents = new ArrayList<>();

		document.addKeyword(Field.UID, "1");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.partiallyUpdateDocuments(
				createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testUpdateDocument() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Update failed");

		Document document = new DocumentImpl();

		document.addKeyword(Field.UID, "1");
		document.addKeyword(Field.EXPIRATION_DATE, "text");

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.updateDocument(createSearchContext(), document);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testUpdateDocuments() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Bulk update failed");

		List<Document> documents = new ArrayList<>();

		Document document = new DocumentImpl();

		document.addKeyword(Field.UID, "1");
		document.addKeyword(Field.EXPIRATION_DATE, "text");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.updateDocuments(createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Override
	protected IndexingFixture createIndexingFixture() {
		return LiferayElasticsearchIndexingFixtureFactory.getInstance();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ElasticsearchIndexWriterExceptionsTest.class);

}