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

package com.liferay.portal.search.elasticsearch7.internal.sidecar;

import java.util.List;

/**
 * @author André de Oliveira
 */
public class DistributionImpl implements Distribution {

	public DistributionImpl(
		Distributable elasticsearchDistributable,
		List<Distributable> pluginDistributables) {

		_elasticsearchDistributable = elasticsearchDistributable;
		_pluginDistributables = pluginDistributables;
	}

	@Override
	public Distributable getElasticsearchDistributable() {
		return _elasticsearchDistributable;
	}

	@Override
	public List<Distributable> getPluginDistributables() {
		return _pluginDistributables;
	}

	private final Distributable _elasticsearchDistributable;
	private final List<Distributable> _pluginDistributables;

}