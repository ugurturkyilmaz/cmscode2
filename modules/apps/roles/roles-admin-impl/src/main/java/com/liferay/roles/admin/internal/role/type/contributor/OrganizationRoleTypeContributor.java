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

package com.liferay.roles.admin.internal.role.type.contributor;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.util.PropsValues;
import com.liferay.roles.admin.role.type.contributor.RoleTypeContributor;

import java.util.Locale;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(
	immediate = true, property = "service.ranking:Integer=300",
	service = RoleTypeContributor.class
)
public class OrganizationRoleTypeContributor implements RoleTypeContributor {

	@Override
	public String[] getExcludedRoleNames() {
		return new String[] {
			RoleConstants.ORGANIZATION_ADMINISTRATOR,
			RoleConstants.ORGANIZATION_OWNER
		};
	}

	@Override
	public String getIcon() {
		return "community";
	}

	@Override
	public String getName() {
		return "organization";
	}

	@Override
	public String[] getSubtypes() {
		return PropsValues.ROLES_ORGANIZATION_SUBTYPES;
	}

	@Override
	public String getTabTitle(Locale locale) {
		return "organization-roles";
	}

	@Override
	public String getTitle(Locale locale) {
		return "organization-role";
	}

	@Override
	public int getType() {
		return RoleConstants.TYPE_ORGANIZATION;
	}

	@Override
	public boolean isAllowAssignMembers(Role role) {
		return false;
	}

	@Override
	public boolean isAllowDelete(Role role) {
		if (role == null) {
			return false;
		}

		return !_portal.isSystemRole(role.getName());
	}

	@Override
	public boolean isAutomaticallyAssigned(Role role) {
		if (Objects.equals(RoleConstants.ORGANIZATION_USER, role.getName())) {
			return true;
		}

		return false;
	}

	@Reference
	private Portal _portal;

}