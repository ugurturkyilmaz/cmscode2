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

package com.liferay.analytics.settings.web.internal.search;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.model.Organization;

import java.util.Set;

import javax.portlet.RenderResponse;

/**
 * @author Geyson Silva
 */
public class OrganizationChecker extends EmptyOnClickRowChecker {

	public OrganizationChecker(RenderResponse renderResponse, Set<String> ids) {
		super(renderResponse);

		setRowIds("syncedOrganizationIds");

		_ids = ids;
	}

	@Override
	public boolean isChecked(Object object) {
		Organization organization = (Organization)object;

		return _ids.contains(String.valueOf(organization.getOrganizationId()));
	}

	private final Set<String> _ids;

}