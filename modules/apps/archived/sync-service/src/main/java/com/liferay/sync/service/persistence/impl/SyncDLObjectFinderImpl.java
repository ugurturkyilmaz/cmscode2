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

package com.liferay.sync.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.impl.SyncDLObjectImpl;
import com.liferay.sync.service.persistence.SyncDLObjectFinder;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
@Component(service = SyncDLObjectFinder.class)
public class SyncDLObjectFinderImpl
	extends SyncDLObjectFinderBaseImpl implements SyncDLObjectFinder {

	public static final String FIND_BY_MODIFIED_TIME =
		SyncDLObjectFinder.class.getName() + ".findByModifiedTime";

	public static final String FIND_BY_TYPE_PKS =
		SyncDLObjectFinder.class.getName() + ".findByTypePKs";

	@Override
	public List<Long> filterFindByR_U_T(
		long groupId, long userId, long[] typePKs) {

		if (ArrayUtil.isEmpty(typePKs)) {
			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_TYPE_PKS);

			sql = StringUtil.replace(
				sql, new String[] {"[$TYPE_PKS$]", "[$ROLE_IDS_OR_OWNER_ID$]"},
				new String[] {
					getTypePKsSQL(typePKs), getRoleOwnerIdsSQL(groupId, userId)
				});

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar("primKey", Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(CompanyThreadLocal.getCompanyId());
			queryPos.add(ResourceConstants.SCOPE_INDIVIDUAL);

			return (List<Long>)sqlQuery.list();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<SyncDLObject> findByModifiedTime(
		long modifiedTime, long repositoryId, long parentFolderId, String type,
		int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_MODIFIED_TIME);

			if (modifiedTime <= 0) {
				sql = StringUtil.removeSubstring(
					sql, "(SyncDLObject.modifiedTime > ?) AND");
			}

			if (parentFolderId == 0) {
				sql = StringUtil.removeSubstring(
					sql, "AND (SyncDLObject.treePath LIKE ?)");
			}

			if (type == null) {
				sql = StringUtil.removeSubstring(
					sql, "AND (SyncDLObject.type_ = ?)");
			}

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
				sql = _customSQL.removeOrderBy(sql);
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity("SyncDLObject", SyncDLObjectImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (modifiedTime > 0) {
				queryPos.add(modifiedTime);
			}

			queryPos.add(repositoryId);

			if (parentFolderId != 0) {
				queryPos.add("%/" + parentFolderId + "/%");
			}

			if (type != null) {
				queryPos.add(type);
			}

			return (List<SyncDLObject>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getRoleOwnerIdsSQL(long groupId, long userId) {
		StringBundler sb = new StringBundler(8);

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		long[] roleIds = permissionChecker.getRoleIds(userId, groupId);

		sb.append(StringPool.OPEN_PARENTHESIS);

		if (roleIds.length != 0) {
			sb.append("roleId IN (");
			sb.append(StringUtil.merge(roleIds));
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(WHERE_OR);
		}

		sb.append("ownerId = ");
		sb.append(userId);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getTypePKsSQL(long[] typePKs) {
		StringBundler sb = new StringBundler((typePKs.length * 4) + 1);

		sb.append("primKey IN (");

		for (int i = 0; i < typePKs.length; i++) {
			sb.append("CAST_TEXT(");
			sb.append(StringUtil.trim(String.valueOf(typePKs[i])));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			if ((i + 1) != typePKs.length) {
				sb.append(StringPool.COMMA);
			}
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	@Reference
	private CustomSQL _customSQL;

}