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

package com.liferay.oauth2.provider.internal.test;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.core.Application;

/**
 * @author Víctor Galán
 */
public class TestPreviewURLApplication extends Application {

	public TestPreviewURLApplication(String previewURL) {
		_previewURL = previewURL;
	}

	@GET
	public String getPreviewURL() {
		return _previewURL;
	}

	@Override
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	private final String _previewURL;

}