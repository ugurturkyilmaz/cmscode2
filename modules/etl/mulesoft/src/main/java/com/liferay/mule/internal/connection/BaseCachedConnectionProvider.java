/**
 * Copyright (c) 2000-2021 Liferay, Inc. All rights reserved.
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

package com.liferay.mule.internal.connection;

import com.liferay.mule.internal.connection.config.LiferayProxyConfig;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import org.slf4j.Logger;

/**
 * @author Matija Petanjek
 */
public abstract class BaseCachedConnectionProvider
	implements CachedConnectionProvider<LiferayConnection> {

	@Override
	public void disconnect(LiferayConnection liferayConnection) {
		getLogger().debug("Closing connection to Liferay Portal instance");

		liferayConnection.invalidate();
	}

	@Override
	public ConnectionValidationResult validate(
		LiferayConnection liferayConnection) {

		try {
			HttpResponse httpResponse =
				liferayConnection.getOpenAPISpecHttpResponse();

			int statusCode = httpResponse.getStatusCode();

			if ((statusCode >= 200) && (statusCode < 300)) {
				return ConnectionValidationResult.success();
			}

			return ConnectionValidationResult.failure(
				String.format(
					"%s (%d)", httpResponse.getReasonPhrase(),
					httpResponse.getStatusCode()),
				new ConnectionException(
					"Unable to connect to Liferay instance"));
		}
		catch (IOException | TimeoutException exception) {
			return ConnectionValidationResult.failure(
				exception.getMessage(), exception);
		}
	}

	protected abstract Logger getLogger();

	@Inject
	protected HttpService httpService;

	@ParameterGroup(name = "Proxy config")
	protected LiferayProxyConfig liferayProxyConfig;

}