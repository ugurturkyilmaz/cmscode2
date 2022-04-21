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

package com.liferay.portal.remote.cors.client.test;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessException;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringUtil;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

/**
 * @author Preston Crary
 */
public class AllowRestrictedHeadersCallable
	implements ProcessCallable<String[]> {

	public AllowRestrictedHeadersCallable(
		String url, String origin, String method, boolean authenticate) {

		_url = url;
		_origin = origin;
		_method = method;
		_authenticate = authenticate;
	}

	@Override
	public String[] call() throws ProcessException {
		try {
			URL url = new URL(_url);

			HttpURLConnection httpURLConnection =
				(HttpURLConnection)url.openConnection();

			httpURLConnection.setRequestProperty("Origin", _origin);
			httpURLConnection.setRequestProperty(
				_ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET);
			httpURLConnection.setRequestMethod(_method);

			if (_authenticate) {
				String encodedUserNameAndPassword = Base64.encode(
					"test@liferay.com:test".getBytes());

				httpURLConnection.setRequestProperty(
					"Authorization", "Basic " + encodedUserNameAndPassword);
			}

			Map<String, List<String>> headerFields =
				httpURLConnection.getHeaderFields();

			return new String[] {
				StringUtil.merge(
					headerFields.get(_ACCESS_CONTROL_ALLOW_ORIGIN)),
				StringUtil.read(httpURLConnection.getInputStream()),
				String.valueOf(httpURLConnection.getResponseCode())
			};
		}
		catch (Exception exception) {
			throw new ProcessException(exception);
		}
	}

	private static final String _ACCESS_CONTROL_ALLOW_ORIGIN =
		"Access-Control-Allow-Origin";

	private static final String _ACCESS_CONTROL_REQUEST_METHOD =
		"Access-Control-Request-Method";

	private static final long serialVersionUID = 1L;

	private final boolean _authenticate;
	private final String _method;
	private final String _origin;
	private final String _url;

}