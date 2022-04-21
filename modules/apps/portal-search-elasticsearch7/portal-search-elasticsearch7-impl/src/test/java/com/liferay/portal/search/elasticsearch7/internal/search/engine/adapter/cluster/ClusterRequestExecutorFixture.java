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

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.cluster;

import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.cluster.ClusterRequestExecutor;

/**
 * @author Dylan Rebelak
 */
public class ClusterRequestExecutorFixture {

	public ClusterRequestExecutor getClusterRequestExecutor() {
		return _clusterRequestExecutor;
	}

	public void setUp() {
		ClusterHealthStatusTranslator clusterHealthStatusTranslator =
			new ClusterHealthStatusTranslatorImpl();

		_clusterRequestExecutor = new ElasticsearchClusterRequestExecutor() {
			{
				setHealthClusterRequestExecutor(
					_createHealthClusterRequestExecutor(
						clusterHealthStatusTranslator,
						_elasticsearchClientResolver));
				setStateClusterRequestExecutor(
					_createStateClusterRequestExecutor(
						_elasticsearchClientResolver));
				setStatsClusterRequestExecutor(
					_createStatsClusterRequestExecutor(
						clusterHealthStatusTranslator,
						_elasticsearchClientResolver));
			}
		};
	}

	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private HealthClusterRequestExecutor _createHealthClusterRequestExecutor(
		ClusterHealthStatusTranslator clusterHealthStatusTranslator,
		ElasticsearchClientResolver elasticsearchClientResolver) {

		return new HealthClusterRequestExecutorImpl() {
			{
				setClusterHealthStatusTranslator(clusterHealthStatusTranslator);
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	private StateClusterRequestExecutor _createStateClusterRequestExecutor(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		return new StateClusterRequestExecutorImpl() {
			{
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	private StatsClusterRequestExecutor _createStatsClusterRequestExecutor(
		ClusterHealthStatusTranslator clusterHealthStatusTranslator,
		ElasticsearchClientResolver elasticsearchClientResolver) {

		return new StatsClusterRequestExecutorImpl() {
			{
				setClusterHealthStatusTranslator(clusterHealthStatusTranslator);
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	private ClusterRequestExecutor _clusterRequestExecutor;
	private ElasticsearchClientResolver _elasticsearchClientResolver;

}