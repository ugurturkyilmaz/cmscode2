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

package com.liferay.portal.kernel.security.permission;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Raymond Augé
 * @see    InlineSQLHelper
 */
public class InlineSQLHelperUtil {

	public static <T extends Table<T>> Predicate getPermissionWherePredicate(
		Class<?> modelClass, Column<T, Long> classPKColumn, long... groupIds) {

		return _inlineSQLPermission.getPermissionWherePredicate(
			modelClass, classPKColumn, groupIds);
	}

	public static boolean isEnabled() {
		return _inlineSQLPermission.isEnabled();
	}

	public static boolean isEnabled(long groupId) {
		return _inlineSQLPermission.isEnabled(groupId);
	}

	public static boolean isEnabled(long companyId, long groupId) {
		return _inlineSQLPermission.isEnabled(companyId, groupId);
	}

	public static boolean isEnabled(long[] groupIds) {
		return _inlineSQLPermission.isEnabled(groupIds);
	}

	public static <T extends Table<T>> DSLQuery replacePermissionCheck(
		DSLQuery dslQuery, Class<?> modelClass, Column<T, Long> classPKColumn,
		long... groupIds) {

		return _inlineSQLPermission.replacePermissionCheck(
			dslQuery, modelClass, classPKColumn, groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, groupId);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId,
		String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, groupId, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds,
		String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, groupIds, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupId);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId, String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupId, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds, String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIds, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String groupIdField, long[] groupIds, String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIdField, groupIds,
			bridgeJoin);
	}

	private static volatile InlineSQLHelper _inlineSQLPermission =
		ServiceProxyFactory.newServiceTrackedInstance(
			InlineSQLHelper.class, InlineSQLHelperUtil.class,
			"_inlineSQLPermission", false);

}