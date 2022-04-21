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

package com.liferay.oauth2.provider.rest.internal.endpoint;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true,
	property = {
		"liferay.auth.verifier=false", "liferay.cors.annotation=true",
		"liferay.jackson=false", "liferay.oauth2=false",
		"oauth2.scope.checker.type=none", "osgi.jaxrs.application.base=/oauth2",
		"osgi.jaxrs.name=Liferay.OAuth2.Application"
	},
	service = Application.class
)
public class OAuth2EndpointApplication extends Application {
}