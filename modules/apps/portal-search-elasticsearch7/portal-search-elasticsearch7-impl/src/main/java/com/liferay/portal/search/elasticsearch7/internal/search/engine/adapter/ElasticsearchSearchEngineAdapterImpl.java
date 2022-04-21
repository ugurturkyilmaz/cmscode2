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

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter;

import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.query.QueryTranslator;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.ccr.CCRRequest;
import com.liferay.portal.search.engine.adapter.ccr.CCRRequestExecutor;
import com.liferay.portal.search.engine.adapter.ccr.CCRResponse;
import com.liferay.portal.search.engine.adapter.cluster.ClusterRequest;
import com.liferay.portal.search.engine.adapter.cluster.ClusterRequestExecutor;
import com.liferay.portal.search.engine.adapter.cluster.ClusterResponse;
import com.liferay.portal.search.engine.adapter.document.DocumentRequest;
import com.liferay.portal.search.engine.adapter.document.DocumentRequestExecutor;
import com.liferay.portal.search.engine.adapter.document.DocumentResponse;
import com.liferay.portal.search.engine.adapter.index.IndexRequest;
import com.liferay.portal.search.engine.adapter.index.IndexRequestExecutor;
import com.liferay.portal.search.engine.adapter.index.IndexResponse;
import com.liferay.portal.search.engine.adapter.search.SearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.engine.adapter.search.SearchResponse;
import com.liferay.portal.search.engine.adapter.snapshot.SnapshotRequest;
import com.liferay.portal.search.engine.adapter.snapshot.SnapshotRequestExecutor;
import com.liferay.portal.search.engine.adapter.snapshot.SnapshotResponse;

import org.elasticsearch.index.query.QueryBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true, property = "search.engine.impl=Elasticsearch",
	service = SearchEngineAdapter.class
)
public class ElasticsearchSearchEngineAdapterImpl
	implements SearchEngineAdapter {

	@Override
	public <T extends CCRResponse> T execute(CCRRequest<T> ccrRequest) {
		try {
			return ccrRequest.accept(_ccrRequestExecutor);
		}
		catch (RuntimeException runtimeException) {
			throw _getRuntimeException(runtimeException);
		}
	}

	@Override
	public <T extends ClusterResponse> T execute(
		ClusterRequest<T> clusterRequest) {

		try {
			return _clusterRequestExecutor.execute(clusterRequest);
		}
		catch (RuntimeException runtimeException) {
			throw _getRuntimeException(runtimeException);
		}
	}

	@Override
	public <S extends DocumentResponse> S execute(
		DocumentRequest<S> documentRequest) {

		try {
			return documentRequest.accept(_documentRequestExecutor);
		}
		catch (RuntimeException runtimeException) {
			throw _getRuntimeException(runtimeException);
		}
	}

	@Override
	public <U extends IndexResponse> U execute(IndexRequest<U> indexRequest) {
		try {
			return indexRequest.accept(_indexRequestExecutor);
		}
		catch (RuntimeException runtimeException) {
			throw _getRuntimeException(runtimeException);
		}
	}

	@Override
	public <V extends SearchResponse> V execute(
		SearchRequest<V> searchRequest) {

		try {
			return searchRequest.accept(_searchRequestExecutor);
		}
		catch (RuntimeException runtimeException) {
			throw _getRuntimeException(runtimeException);
		}
	}

	@Override
	public <W extends SnapshotResponse> W execute(
		SnapshotRequest<W> snapshotRequest) {

		try {
			return snapshotRequest.accept(_snapshotRequestExecutor);
		}
		catch (RuntimeException runtimeException) {
			throw _getRuntimeException(runtimeException);
		}
	}

	@Override
	public String getQueryString(Query query) {
		try {
			QueryBuilder queryBuilder = _queryTranslator.translate(query, null);

			return queryBuilder.toString();
		}
		catch (RuntimeException runtimeException) {
			throw _getRuntimeException(runtimeException);
		}
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)", unbind = "-")
	protected void setCCRRequestExecutor(
		CCRRequestExecutor ccrRequestExecutor) {

		_ccrRequestExecutor = ccrRequestExecutor;
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)", unbind = "-")
	protected void setClusterRequestExecutor(
		ClusterRequestExecutor clusterRequestExecutor) {

		_clusterRequestExecutor = clusterRequestExecutor;
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)", unbind = "-")
	protected void setDocumentRequestExecutor(
		DocumentRequestExecutor documentRequestExecutor) {

		_documentRequestExecutor = documentRequestExecutor;
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)", unbind = "-")
	protected void setIndexRequestExecutor(
		IndexRequestExecutor indexRequestExecutor) {

		_indexRequestExecutor = indexRequestExecutor;
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)", unbind = "-")
	protected void setQueryTranslator(
		QueryTranslator<QueryBuilder> queryTranslator) {

		_queryTranslator = queryTranslator;
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)", unbind = "-")
	protected void setSearchRequestExecutor(
		SearchRequestExecutor searchRequestExecutor) {

		_searchRequestExecutor = searchRequestExecutor;
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)", unbind = "-")
	protected void setSnapshotRequestExecutor(
		SnapshotRequestExecutor snapshotRequestExecutor) {

		_snapshotRequestExecutor = snapshotRequestExecutor;
	}

	protected void setThrowOriginalExceptions(boolean throwOriginalExceptions) {
		_throwOriginalExceptions = throwOriginalExceptions;
	}

	private RuntimeException _getRuntimeException(
		RuntimeException runtimeException1) {

		if (_throwOriginalExceptions) {
			return runtimeException1;
		}

		Class<?> clazz = runtimeException1.getClass();

		String name = clazz.getName();

		if (name.startsWith("org.elasticsearch")) {
			RuntimeException runtimeException2 = new RuntimeException(
				name + ": " + runtimeException1.toString());

			runtimeException2.setStackTrace(runtimeException1.getStackTrace());

			for (Throwable throwable : runtimeException1.getSuppressed()) {
				runtimeException2.addSuppressed(throwable);
			}

			return runtimeException2;
		}

		return runtimeException1;
	}

	private CCRRequestExecutor _ccrRequestExecutor;
	private ClusterRequestExecutor _clusterRequestExecutor;
	private DocumentRequestExecutor _documentRequestExecutor;
	private IndexRequestExecutor _indexRequestExecutor;
	private QueryTranslator<QueryBuilder> _queryTranslator;
	private SearchRequestExecutor _searchRequestExecutor;
	private SnapshotRequestExecutor _snapshotRequestExecutor;
	private boolean _throwOriginalExceptions;

}