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

package com.liferay.portal.remote.cors.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Tomas Polesovsky
 */
@ExtendedObjectClassDefinition(
	category = "security-tools",
	factoryInstanceLabelAttribute = "configuration.name",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	description = "portal-cors-configuration-description", factory = true,
	id = "com.liferay.portal.remote.cors.configuration.PortalCORSConfiguration",
	localization = "content/Language", name = "portal-cors-configuration-name"
)
public interface PortalCORSConfiguration {

	@Meta.AD(deflt = "true", name = "enabled", required = false)
	public boolean enabled();

	@Meta.AD(
		description = "portal-cors-name-description", id = "configuration.name",
		name = "portal-cors-name", required = false
	)
	public String name();

	@Meta.AD(
		deflt = "/api/jsonws/*|/documents/*|/image/*|/o/api/*|/o/graphql",
		description = "cors-configuration-filter-mapping-url-pattern-description",
		id = "filter.mapping.url.pattern",
		name = "cors-configuration-filter-mapping-url-pattern", required = false
	)
	public String[] filterMappingURLPatterns();

	@Meta.AD(
		deflt = "Access-Control-Allow-Credentials: true|Access-Control-Allow-Headers: *|Access-Control-Allow-Methods: *|Access-Control-Allow-Origin: *",
		description = "cors-configuration-cors-headers-description",
		id = "headers", name = "cors-configuration-cors-headers",
		required = false
	)
	public String[] headers();

}