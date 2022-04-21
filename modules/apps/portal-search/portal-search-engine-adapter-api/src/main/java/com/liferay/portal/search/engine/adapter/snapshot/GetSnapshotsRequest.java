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

package com.liferay.portal.search.engine.adapter.snapshot;

import com.liferay.portal.search.engine.adapter.ccr.CrossClusterRequest;

/**
 * @author Michael C. Han
 */
public class GetSnapshotsRequest
	extends CrossClusterRequest
	implements SnapshotRequest<GetSnapshotsResponse> {

	public GetSnapshotsRequest(String repositoryName) {
		_repositoryName = repositoryName;
	}

	@Override
	public GetSnapshotsResponse accept(
		SnapshotRequestExecutor snapshotRequestExecutor) {

		return snapshotRequestExecutor.executeSnapshotRequest(this);
	}

	public String getRepositoryName() {
		return _repositoryName;
	}

	public String[] getSnapshotNames() {
		return _snapshotNames;
	}

	public boolean isIgnoreUnavailable() {
		return _ignoreUnavailable;
	}

	public boolean isVerbose() {
		return _verbose;
	}

	public void setIgnoreUnavailable(boolean ignoreUnavailable) {
		_ignoreUnavailable = ignoreUnavailable;
	}

	public void setSnapshotNames(String... snapshotNames) {
		_snapshotNames = snapshotNames;
	}

	public void setVerbose(boolean verbose) {
		_verbose = verbose;
	}

	private boolean _ignoreUnavailable = true;
	private final String _repositoryName;
	private String[] _snapshotNames;
	private boolean _verbose;

}