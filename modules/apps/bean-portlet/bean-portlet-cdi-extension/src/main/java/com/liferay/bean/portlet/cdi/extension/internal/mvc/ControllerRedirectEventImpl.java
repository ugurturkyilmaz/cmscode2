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

package com.liferay.bean.portlet.cdi.extension.internal.mvc;

import java.net.URI;

import javax.mvc.event.ControllerRedirectEvent;

import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.UriInfo;

/**
 * @author Neil Griffin
 */
public class ControllerRedirectEventImpl
	extends BaseControllerEventImpl implements ControllerRedirectEvent {

	public ControllerRedirectEventImpl(
		URI location, ResourceInfo resourceInfo, UriInfo uriInfo) {

		super(resourceInfo, uriInfo);

		_location = location;
	}

	@Override
	public URI getLocation() {
		return _location;
	}

	private final URI _location;

}