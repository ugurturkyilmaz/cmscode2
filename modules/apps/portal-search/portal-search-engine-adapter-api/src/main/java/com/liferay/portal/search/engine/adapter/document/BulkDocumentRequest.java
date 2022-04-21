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

package com.liferay.portal.search.engine.adapter.document;

import com.liferay.portal.search.engine.adapter.ccr.CrossClusterRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class BulkDocumentRequest
	extends CrossClusterRequest
	implements DocumentRequest<BulkDocumentResponse> {

	@Override
	public BulkDocumentResponse accept(
		DocumentRequestExecutor documentRequestExecutor) {

		return documentRequestExecutor.executeBulkDocumentRequest(this);
	}

	public void addBulkableDocumentRequest(
		BulkableDocumentRequest<?> bulkableDocumentRequest) {

		_bulkableDocumentRequests.add(bulkableDocumentRequest);
	}

	public List<BulkableDocumentRequest<?>> getBulkableDocumentRequests() {
		return _bulkableDocumentRequests;
	}

	public boolean isRefresh() {
		return _refresh;
	}

	public void setRefresh(boolean refresh) {
		_refresh = refresh;
	}

	private final List<BulkableDocumentRequest<?>> _bulkableDocumentRequests =
		new ArrayList<>();
	private boolean _refresh;

}