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

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.model.UserGroup;

/**
 * Provides a wrapper for {@link UserGroupService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupService
 * @generated
 */
public class UserGroupServiceWrapper
	implements ServiceWrapper<UserGroupService>, UserGroupService {

	public UserGroupServiceWrapper() {
		this(null);
	}

	public UserGroupServiceWrapper(UserGroupService userGroupService) {
		_userGroupService = userGroupService;
	}

	/**
	 * Adds the user groups to the group.
	 *
	 * @param groupId the primary key of the group
	 * @param userGroupIds the primary keys of the user groups
	 */
	@Override
	public void addGroupUserGroups(long groupId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_userGroupService.addGroupUserGroups(groupId, userGroupIds);
	}

	@Override
	public UserGroup addOrUpdateUserGroup(
			String externalReferenceCode, String name, String description,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.addOrUpdateUserGroup(
			externalReferenceCode, name, description, serviceContext);
	}

	/**
	 * Adds the user groups to the team
	 *
	 * @param teamId the primary key of the team
	 * @param userGroupIds the primary keys of the user groups
	 */
	@Override
	public void addTeamUserGroups(long teamId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_userGroupService.addTeamUserGroups(teamId, userGroupIds);
	}

	/**
	 * Adds a user group.
	 *
	 * <p>
	 * This method handles the creation and bookkeeping of the user group,
	 * including its resources, metadata, and internal data structures.
	 * </p>
	 *
	 * @param name the user group's name
	 * @param description the user group's description
	 * @param serviceContext the service context to be applied (optionally
	 <code>null</code>). Can set expando bridge attributes for the
	 user group.
	 * @return the user group
	 */
	@Override
	public UserGroup addUserGroup(
			String name, String description, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.addUserGroup(
			name, description, serviceContext);
	}

	/**
	 * Deletes the user group.
	 *
	 * @param userGroupId the primary key of the user group
	 */
	@Override
	public void deleteUserGroup(long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_userGroupService.deleteUserGroup(userGroupId);
	}

	/**
	 * Fetches the user group with the primary key.
	 *
	 * @param userGroupId the primary key of the user group
	 * @return the user group with the primary key
	 */
	@Override
	public UserGroup fetchUserGroup(long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.fetchUserGroup(userGroupId);
	}

	@Override
	public UserGroup fetchUserGroupByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.fetchUserGroupByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public java.util.List<UserGroup> getGtUserGroups(
		long gtUserGroupId, long companyId, long parentUserGroupId, int size) {

		return _userGroupService.getGtUserGroups(
			gtUserGroupId, companyId, parentUserGroupId, size);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userGroupService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the user group with the primary key.
	 *
	 * @param userGroupId the primary key of the user group
	 * @return the user group with the primary key
	 */
	@Override
	public UserGroup getUserGroup(long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.getUserGroup(userGroupId);
	}

	/**
	 * Returns the user group with the name.
	 *
	 * @param name the user group's name
	 * @return the user group with the name
	 */
	@Override
	public UserGroup getUserGroup(String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.getUserGroup(name);
	}

	@Override
	public java.util.List<UserGroup> getUserGroups(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.getUserGroups(companyId);
	}

	@Override
	public java.util.List<UserGroup> getUserGroups(
		long companyId, String name, int start, int end) {

		return _userGroupService.getUserGroups(companyId, name, start, end);
	}

	@Override
	public int getUserGroupsCount(long companyId, String name) {
		return _userGroupService.getUserGroupsCount(companyId, name);
	}

	/**
	 * Returns all the user groups to which the user belongs.
	 *
	 * @param userId the primary key of the user
	 * @return the user groups to which the user belongs
	 */
	@Override
	public java.util.List<UserGroup> getUserUserGroups(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.getUserUserGroups(userId);
	}

	/**
	 * Returns an ordered range of all the user groups that match the keywords.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param companyId the primary key of the user group's company
	 * @param keywords the keywords (space separated), which may occur in the
	 user group's name or description (optionally <code>null</code>)
	 * @param params the finder params (optionally <code>null</code>). For more
	 information see {@link
	 com.liferay.portal.kernel.service.persistence.UserGroupFinder}
	 * @param start the lower bound of the range of user groups to return
	 * @param end the upper bound of the range of user groups to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the user groups
	 (optionally <code>null</code>)
	 * @return the matching user groups ordered by comparator
	 <code>orderByComparator</code>
	 * @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	 */
	@Override
	public java.util.List<UserGroup> search(
		long companyId, String keywords,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroup>
			orderByComparator) {

		return _userGroupService.search(
			companyId, keywords, params, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user groups that match the name and
	 * description.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param companyId the primary key of the user group's company
	 * @param name the user group's name (optionally <code>null</code>)
	 * @param description the user group's description (optionally
	 <code>null</code>)
	 * @param params the finder params (optionally <code>null</code>). For more
	 information see {@link
	 com.liferay.portal.kernel.service.persistence.UserGroupFinder}
	 * @param andOperator whether every field must match its keywords or just
	 one field
	 * @param start the lower bound of the range of user groups to return
	 * @param end the upper bound of the range of user groups to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the user groups
	 (optionally <code>null</code>)
	 * @return the matching user groups ordered by comparator
	 <code>orderByComparator</code>
	 * @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	 */
	@Override
	public java.util.List<UserGroup> search(
		long companyId, String name, String description,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroup>
			orderByComparator) {

		return _userGroupService.search(
			companyId, name, description, params, andOperator, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of user groups that match the keywords
	 *
	 * @param companyId the primary key of the user group's company
	 * @param keywords the keywords (space separated), which may occur in the
	 user group's name or description (optionally <code>null</code>)
	 * @param params the finder params (optionally <code>null</code>). For more
	 information see {@link
	 com.liferay.portal.kernel.service.persistence.UserGroupFinder}
	 * @return the number of matching user groups
	 * @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	 */
	@Override
	public int searchCount(
		long companyId, String keywords,
		java.util.LinkedHashMap<String, Object> params) {

		return _userGroupService.searchCount(companyId, keywords, params);
	}

	/**
	 * Returns the number of user groups that match the name and description.
	 *
	 * @param companyId the primary key of the user group's company
	 * @param name the user group's name (optionally <code>null</code>)
	 * @param description the user group's description (optionally
	 <code>null</code>)
	 * @param params the finder params (optionally <code>null</code>). For more
	 information see {@link
	 com.liferay.portal.kernel.service.persistence.UserGroupFinder}
	 * @param andOperator whether every field must match its keywords or just
	 one field
	 * @return the number of matching user groups
	 * @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	 */
	@Override
	public int searchCount(
		long companyId, String name, String description,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator) {

		return _userGroupService.searchCount(
			companyId, name, description, params, andOperator);
	}

	/**
	 * Removes the user groups from the group.
	 *
	 * @param groupId the primary key of the group
	 * @param userGroupIds the primary keys of the user groups
	 */
	@Override
	public void unsetGroupUserGroups(long groupId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_userGroupService.unsetGroupUserGroups(groupId, userGroupIds);
	}

	/**
	 * Removes the user groups from the team.
	 *
	 * @param teamId the primary key of the team
	 * @param userGroupIds the primary keys of the user groups
	 */
	@Override
	public void unsetTeamUserGroups(long teamId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_userGroupService.unsetTeamUserGroups(teamId, userGroupIds);
	}

	@Override
	public UserGroup updateExternalReferenceCode(
			UserGroup userGroup, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.updateExternalReferenceCode(
			userGroup, externalReferenceCode);
	}

	/**
	 * Updates the user group.
	 *
	 * @param userGroupId the primary key of the user group
	 * @param name the user group's name
	 * @param description the the user group's description
	 * @param serviceContext the service context to be applied (optionally
	 <code>null</code>). Can set expando bridge attributes for the
	 user group.
	 * @return the user group
	 */
	@Override
	public UserGroup updateUserGroup(
			long userGroupId, String name, String description,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userGroupService.updateUserGroup(
			userGroupId, name, description, serviceContext);
	}

	@Override
	public UserGroupService getWrappedService() {
		return _userGroupService;
	}

	@Override
	public void setWrappedService(UserGroupService userGroupService) {
		_userGroupService = userGroupService;
	}

	private UserGroupService _userGroupService;

}