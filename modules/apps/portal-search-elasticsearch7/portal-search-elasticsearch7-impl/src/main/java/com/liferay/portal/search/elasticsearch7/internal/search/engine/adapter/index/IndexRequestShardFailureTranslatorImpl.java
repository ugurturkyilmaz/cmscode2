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

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.search.engine.adapter.index.IndexRequestShardFailure;

import org.elasticsearch.action.ShardOperationFailedException;
import org.elasticsearch.rest.RestStatus;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(service = IndexRequestShardFailureTranslator.class)
public class IndexRequestShardFailureTranslatorImpl
	implements IndexRequestShardFailureTranslator {

	@Override
	public IndexRequestShardFailure translate(
		ShardOperationFailedException shardOperationFailedException) {

		IndexRequestShardFailure indexRequestShardFailure =
			new IndexRequestShardFailure();

		indexRequestShardFailure.setIndex(
			shardOperationFailedException.index());
		indexRequestShardFailure.setReason(
			shardOperationFailedException.reason());
		indexRequestShardFailure.setShardId(
			shardOperationFailedException.shardId());

		RestStatus restStatus = shardOperationFailedException.status();

		indexRequestShardFailure.setRestStatus(restStatus.getStatus());

		return indexRequestShardFailure;
	}

}