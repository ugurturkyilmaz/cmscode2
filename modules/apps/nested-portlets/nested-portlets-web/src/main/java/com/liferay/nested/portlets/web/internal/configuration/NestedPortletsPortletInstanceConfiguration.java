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

package com.liferay.nested.portlets.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Juergen Kappler
 */
@ExtendedObjectClassDefinition(
	category = "display-content",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.nested.portlets.web.internal.configuration.NestedPortletsPortletInstanceConfiguration",
	localization = "content/Language",
	name = "nested-portlets-portlet-instance-configuration-name"
)
public interface NestedPortletsPortletInstanceConfiguration {

	@Meta.AD(
		deflt = "2_columns_i", id = "layout.template.default",
		name = "layout-template-id", required = false
	)
	public String layoutTemplateId();

	@Meta.AD(
		deflt = "1_column|1_column_dynamic", id = "layout.template.unsupported",
		name = "layout-templates-unsupported", required = false
	)
	public String[] layoutTemplatesUnsupported();

}