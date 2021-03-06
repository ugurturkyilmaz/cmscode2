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

package com.liferay.portal.kernel.poller;

import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class PollerHeader implements Serializable {

	public PollerHeader(
		long companyId, long userId, long browserKey,
		Map<String, Boolean> portletIdsMap, boolean startPolling) {

		_companyId = companyId;
		_userId = userId;
		_browserKey = browserKey;
		_portletIdsMap = portletIdsMap;
		_startPolling = startPolling;

		_timestamp = System.currentTimeMillis();
	}

	public long getBrowserKey() {
		return _browserKey;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Map<String, Boolean> getPortletIdsMap() {
		return _portletIdsMap;
	}

	public long getTimestamp() {
		return _timestamp;
	}

	public long getUserId() {
		return _userId;
	}

	public boolean isStartPolling() {
		return _startPolling;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{_browserKey=", _browserKey, ", companyId=", _companyId,
			", portletIdsMap=", _portletIdsMap, ", startPolling=",
			_startPolling, ", timestamp=", _timestamp, ", userId=", _userId,
			"}");
	}

	private static final long serialVersionUID = 1L;

	private final long _browserKey;
	private final long _companyId;
	private final Map<String, Boolean> _portletIdsMap;
	private final boolean _startPolling;
	private final long _timestamp;
	private final long _userId;

}