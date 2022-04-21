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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for UserGroupGroupRole. This utility wraps
 * <code>com.liferay.portal.service.impl.UserGroupGroupRoleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRoleLocalService
 * @generated
 */
public class UserGroupGroupRoleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.UserGroupGroupRoleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the user group group role to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserGroupGroupRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userGroupGroupRole the user group group role
	 * @return the user group group role that was added
	 */
	public static UserGroupGroupRole addUserGroupGroupRole(
		UserGroupGroupRole userGroupGroupRole) {

		return getService().addUserGroupGroupRole(userGroupGroupRole);
	}

	public static void addUserGroupGroupRoles(
		long userGroupId, long groupId, long[] roleIds) {

		getService().addUserGroupGroupRoles(userGroupId, groupId, roleIds);
	}

	public static void addUserGroupGroupRoles(
		long[] userGroupIds, long groupId, long roleId) {

		getService().addUserGroupGroupRoles(userGroupIds, groupId, roleId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new user group group role with the primary key. Does not add the user group group role to the database.
	 *
	 * @param userGroupGroupRoleId the primary key for the new user group group role
	 * @return the new user group group role
	 */
	public static UserGroupGroupRole createUserGroupGroupRole(
		long userGroupGroupRoleId) {

		return getService().createUserGroupGroupRole(userGroupGroupRoleId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user group group role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserGroupGroupRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userGroupGroupRoleId the primary key of the user group group role
	 * @return the user group group role that was removed
	 * @throws PortalException if a user group group role with the primary key could not be found
	 */
	public static UserGroupGroupRole deleteUserGroupGroupRole(
			long userGroupGroupRoleId)
		throws PortalException {

		return getService().deleteUserGroupGroupRole(userGroupGroupRoleId);
	}

	/**
	 * Deletes the user group group role from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserGroupGroupRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userGroupGroupRole the user group group role
	 * @return the user group group role that was removed
	 */
	public static UserGroupGroupRole deleteUserGroupGroupRole(
		UserGroupGroupRole userGroupGroupRole) {

		return getService().deleteUserGroupGroupRole(userGroupGroupRole);
	}

	public static void deleteUserGroupGroupRoles(long groupId, int roleType) {
		getService().deleteUserGroupGroupRoles(groupId, roleType);
	}

	public static void deleteUserGroupGroupRoles(
		long userGroupId, long groupId, long[] roleIds) {

		getService().deleteUserGroupGroupRoles(userGroupId, groupId, roleIds);
	}

	public static void deleteUserGroupGroupRoles(
		long userGroupId, long[] groupIds) {

		getService().deleteUserGroupGroupRoles(userGroupId, groupIds);
	}

	public static void deleteUserGroupGroupRoles(
		long[] userGroupIds, long groupId) {

		getService().deleteUserGroupGroupRoles(userGroupIds, groupId);
	}

	public static void deleteUserGroupGroupRoles(
		long[] userGroupIds, long groupId, long roleId) {

		getService().deleteUserGroupGroupRoles(userGroupIds, groupId, roleId);
	}

	public static void deleteUserGroupGroupRolesByGroupId(long groupId) {
		getService().deleteUserGroupGroupRolesByGroupId(groupId);
	}

	public static void deleteUserGroupGroupRolesByRoleId(long roleId) {
		getService().deleteUserGroupGroupRolesByRoleId(roleId);
	}

	public static void deleteUserGroupGroupRolesByUserGroupId(
		long userGroupId) {

		getService().deleteUserGroupGroupRolesByUserGroupId(userGroupId);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserGroupGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserGroupGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static UserGroupGroupRole fetchUserGroupGroupRole(
		long userGroupGroupRoleId) {

		return getService().fetchUserGroupGroupRole(userGroupGroupRoleId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user group group role with the primary key.
	 *
	 * @param userGroupGroupRoleId the primary key of the user group group role
	 * @return the user group group role
	 * @throws PortalException if a user group group role with the primary key could not be found
	 */
	public static UserGroupGroupRole getUserGroupGroupRole(
			long userGroupGroupRoleId)
		throws PortalException {

		return getService().getUserGroupGroupRole(userGroupGroupRoleId);
	}

	/**
	 * Returns a range of all the user group group roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserGroupGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user group group roles
	 * @param end the upper bound of the range of user group group roles (not inclusive)
	 * @return the range of user group group roles
	 */
	public static List<UserGroupGroupRole> getUserGroupGroupRoles(
		int start, int end) {

		return getService().getUserGroupGroupRoles(start, end);
	}

	public static List<UserGroupGroupRole> getUserGroupGroupRoles(
		long userGroupId) {

		return getService().getUserGroupGroupRoles(userGroupId);
	}

	public static List<UserGroupGroupRole> getUserGroupGroupRoles(
		long userGroupId, long groupId) {

		return getService().getUserGroupGroupRoles(userGroupId, groupId);
	}

	public static List<UserGroupGroupRole> getUserGroupGroupRolesByGroupAndRole(
		long groupId, long roleId) {

		return getService().getUserGroupGroupRolesByGroupAndRole(
			groupId, roleId);
	}

	public static List<UserGroupGroupRole> getUserGroupGroupRolesByUser(
		long userId) {

		return getService().getUserGroupGroupRolesByUser(userId);
	}

	public static List<UserGroupGroupRole> getUserGroupGroupRolesByUser(
		long userId, long groupId) {

		return getService().getUserGroupGroupRolesByUser(userId, groupId);
	}

	/**
	 * Returns the number of user group group roles.
	 *
	 * @return the number of user group group roles
	 */
	public static int getUserGroupGroupRolesCount() {
		return getService().getUserGroupGroupRolesCount();
	}

	public static boolean hasUserGroupGroupRole(
		long userGroupId, long groupId, long roleId) {

		return getService().hasUserGroupGroupRole(userGroupId, groupId, roleId);
	}

	public static boolean hasUserGroupGroupRole(
			long userGroupId, long groupId, String roleName)
		throws PortalException {

		return getService().hasUserGroupGroupRole(
			userGroupId, groupId, roleName);
	}

	/**
	 * Updates the user group group role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserGroupGroupRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userGroupGroupRole the user group group role
	 * @return the user group group role that was updated
	 */
	public static UserGroupGroupRole updateUserGroupGroupRole(
		UserGroupGroupRole userGroupGroupRole) {

		return getService().updateUserGroupGroupRole(userGroupGroupRole);
	}

	public static UserGroupGroupRoleLocalService getService() {
		return _service;
	}

	private static volatile UserGroupGroupRoleLocalService _service;

}