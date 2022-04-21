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

/**
 * @author Michael C. Han
 */
public class SnapshotDetails {

	public SnapshotDetails(String snapshotName, String snapshotUuid) {
		_snapshotName = snapshotName;
		_snapshotUuid = snapshotUuid;
	}

	public String[] getIndexNames() {
		return _indexNames;
	}

	public SnapshotState getSnapshotState() {
		return _snapshotState;
	}

	public int getSuccessfulShards() {
		return _successfulShards;
	}

	public int getTotalShards() {
		return _totalShards;
	}

	public void setIndexNames(String[] indexNames) {
		_indexNames = indexNames;
	}

	public void setSnapshotState(SnapshotState snapshotState) {
		_snapshotState = snapshotState;
	}

	public void setSuccessfulShards(int successfulShards) {
		_successfulShards = successfulShards;
	}

	public void setTotalShards(int totalShards) {
		_totalShards = totalShards;
	}

	private String[] _indexNames;
	private final String _snapshotName;
	private SnapshotState _snapshotState;
	private final String _snapshotUuid;
	private int _successfulShards;
	private int _totalShards;

}