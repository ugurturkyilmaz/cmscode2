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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.service.persistence.RolePersistence;
import com.liferay.portal.kernel.service.persistence.UserGroupPersistence;
import com.liferay.portal.service.base.UserGroupGroupRoleLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brett Swaim
 */
public class UserGroupGroupRoleLocalServiceImpl
	extends UserGroupGroupRoleLocalServiceBaseImpl {

	@Override
	public void addUserGroupGroupRoles(
		long userGroupId, long groupId, long[] roleIds) {

		for (long roleId : roleIds) {
			UserGroupGroupRole userGroupGroupRole =
				userGroupGroupRolePersistence.fetchByU_G_R(
					userGroupId, groupId, roleId);

			if (userGroupGroupRole == null) {
				userGroupGroupRole = userGroupGroupRolePersistence.create(
					counterLocalService.increment(
						UserGroupGroupRole.class.getName()));

				userGroupGroupRole.setUserGroupId(userGroupId);
				userGroupGroupRole.setGroupId(groupId);
				userGroupGroupRole.setRoleId(roleId);

				userGroupGroupRolePersistence.update(userGroupGroupRole);
			}
		}
	}

	@Override
	public void addUserGroupGroupRoles(
		long[] userGroupIds, long groupId, long roleId) {

		for (long userGroupId : userGroupIds) {
			UserGroupGroupRole userGroupGroupRole =
				userGroupGroupRolePersistence.fetchByU_G_R(
					userGroupId, groupId, roleId);

			if (userGroupGroupRole == null) {
				userGroupGroupRole = userGroupGroupRolePersistence.create(
					counterLocalService.increment(
						UserGroupGroupRole.class.getName()));

				userGroupGroupRole.setUserGroupId(userGroupId);
				userGroupGroupRole.setGroupId(groupId);
				userGroupGroupRole.setRoleId(roleId);

				userGroupGroupRolePersistence.update(userGroupGroupRole);
			}
		}
	}

	@Override
	public void deleteUserGroupGroupRoles(long groupId, int roleType) {
		List<UserGroupGroupRole> userGroupGroupRoles =
			userGroupGroupRoleFinder.findByGroupRoleType(groupId, roleType);

		for (UserGroupGroupRole userGroupGroupRole : userGroupGroupRoles) {
			userGroupGroupRolePersistence.removeByG_R(
				groupId, userGroupGroupRole.getRoleId());
		}
	}

	@Override
	public void deleteUserGroupGroupRoles(
		long userGroupId, long groupId, long[] roleIds) {

		for (long roleId : roleIds) {
			UserGroupGroupRole userGroupGroupRole =
				userGroupGroupRolePersistence.fetchByU_G_R(
					userGroupId, groupId, roleId);

			if (userGroupGroupRole != null) {
				userGroupGroupRolePersistence.remove(userGroupGroupRole);
			}
		}
	}

	@Override
	public void deleteUserGroupGroupRoles(long userGroupId, long[] groupIds) {
		for (long groupId : groupIds) {
			userGroupGroupRolePersistence.removeByU_G(userGroupId, groupId);
		}
	}

	@Override
	public void deleteUserGroupGroupRoles(long[] userGroupIds, long groupId) {
		for (long userGroupId : userGroupIds) {
			userGroupGroupRolePersistence.removeByU_G(userGroupId, groupId);
		}
	}

	@Override
	public void deleteUserGroupGroupRoles(
		long[] userGroupIds, long groupId, long roleId) {

		for (long userGroupId : userGroupIds) {
			UserGroupGroupRole userGroupGroupRole =
				userGroupGroupRolePersistence.fetchByU_G_R(
					userGroupId, groupId, roleId);

			if (userGroupGroupRole != null) {
				userGroupGroupRolePersistence.remove(userGroupGroupRole);
			}
		}
	}

	@Override
	public void deleteUserGroupGroupRolesByGroupId(long groupId) {
		userGroupGroupRolePersistence.removeByGroupId(groupId);
	}

	@Override
	public void deleteUserGroupGroupRolesByRoleId(long roleId) {
		userGroupGroupRolePersistence.removeByRoleId(roleId);
	}

	@Override
	public void deleteUserGroupGroupRolesByUserGroupId(long userGroupId) {
		userGroupGroupRolePersistence.removeByUserGroupId(userGroupId);
	}

	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRoles(long userGroupId) {
		return userGroupGroupRolePersistence.findByUserGroupId(userGroupId);
	}

	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRoles(
		long userGroupId, long groupId) {

		return userGroupGroupRolePersistence.findByU_G(userGroupId, groupId);
	}

	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRolesByGroupAndRole(
		long groupId, long roleId) {

		return userGroupGroupRolePersistence.findByG_R(groupId, roleId);
	}

	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRolesByUser(long userId) {
		return userGroupGroupRoleFinder.findByUserGroupsUsers(userId);
	}

	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRolesByUser(
		long userId, long groupId) {

		return userGroupGroupRoleFinder.findByUserGroupsUsers(userId, groupId);
	}

	@Override
	public boolean hasUserGroupGroupRole(
		long userGroupId, long groupId, long roleId) {

		int count = userGroupGroupRolePersistence.countByU_G_R(
			userGroupId, groupId, roleId);

		if (count > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean hasUserGroupGroupRole(
			long userGroupId, long groupId, String roleName)
		throws PortalException {

		UserGroup userGroup = _userGroupPersistence.findByPrimaryKey(
			userGroupId);

		Role role = _rolePersistence.findByC_N(
			userGroup.getCompanyId(), roleName);

		return hasUserGroupGroupRole(userGroupId, groupId, role.getRoleId());
	}

	@BeanReference(type = RolePersistence.class)
	private RolePersistence _rolePersistence;

	@BeanReference(type = UserGroupPersistence.class)
	private UserGroupPersistence _userGroupPersistence;

}