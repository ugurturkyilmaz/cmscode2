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

package com.liferay.knowledge.base.service.persistence.impl;

import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.persistence.KBArticlePersistence;
import com.liferay.knowledge.base.service.persistence.KBFolderFinder;
import com.liferay.knowledge.base.service.persistence.KBFolderPersistence;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelper;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(service = KBFolderFinder.class)
public class KBFolderFinderImpl
	extends KBFolderFinderBaseImpl implements KBFolderFinder {

	public static final String COUNT_A_BY_G_P =
		KBFolderFinder.class.getName() + ".countA_ByG_P";

	public static final String COUNT_F_BY_G_P =
		KBFolderFinder.class.getName() + ".countF_ByG_P";

	public static final String FIND_A_BY_G_P =
		KBFolderFinder.class.getName() + ".findA_ByG_P";

	public static final String FIND_A_BY_G_P_VC =
		KBFolderFinder.class.getName() + ".findA_ByG_P_VC";

	public static final String FIND_F_BY_G_P =
		KBFolderFinder.class.getName() + ".findF_ByG_P";

	@Override
	public int countF_A_ByG_P(
		long groupId, long parentResourcePrimKey,
		QueryDefinition<?> queryDefinition) {

		return doCountF_A_ByG_P(
			groupId, parentResourcePrimKey, queryDefinition, false);
	}

	@Override
	public int filterCountF_A_ByG_P(
		long groupId, long parentResourcePrimKey,
		QueryDefinition<?> queryDefinition) {

		return doCountF_A_ByG_P(
			groupId, parentResourcePrimKey, queryDefinition, true);
	}

	@Override
	public List<Object> filterFindF_A_ByG_P(
		long groupId, long parentResourcePrimKey,
		QueryDefinition<?> queryDefinition) {

		return doFindF_A_ByG_P(
			groupId, parentResourcePrimKey, queryDefinition, true);
	}

	@Override
	public List<Object> findF_A_ByG_P(
		long groupId, long parentResourcePrimKey,
		QueryDefinition<?> queryDefinition) {

		return doFindF_A_ByG_P(
			groupId, parentResourcePrimKey, queryDefinition, false);
	}

	protected int doCountF_A_ByG_P(
		long groupId, long parentResourcePrimKey,
		QueryDefinition<?> queryDefinition, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(5);

			sb.append(StringPool.OPEN_PARENTHESIS);

			String sql = _customSQL.get(
				getClass(), COUNT_A_BY_G_P, queryDefinition);

			if (inlineSQLHelper) {
				sql = _inlineSQLHelper.replacePermissionCheck(
					sql, KBArticle.class.getName(), "KBArticle.kbArticleId",
					groupId);
			}

			sb.append(sql);
			sb.append(") UNION ALL (");

			sql = _customSQL.get(getClass(), COUNT_F_BY_G_P, queryDefinition);

			if (inlineSQLHelper) {
				sql = _inlineSQLHelper.replacePermissionCheck(
					sql, KBFolder.class.getName(), "KBFolder.kbFolderId",
					groupId);
			}

			sb.append(sql);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(
				sb.toString());

			sqlQuery.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);
			queryPos.add(parentResourcePrimKey);
			queryPos.add(true);
			queryPos.add(queryDefinition.getStatus());
			queryPos.add(groupId);
			queryPos.add(parentResourcePrimKey);

			int count = 0;

			Iterator<Long> iterator = sqlQuery.iterate();

			while (iterator.hasNext()) {
				Long l = iterator.next();

				if (l != null) {
					count += l.intValue();
				}
			}

			return count;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<Object> doFindF_A_ByG_P(
		long groupId, long parentResourcePrimKey,
		QueryDefinition<?> queryDefinition, boolean inlineSQLHelper) {

		boolean orderByViewCount = false;

		OrderByComparator<?> orderByComparator =
			queryDefinition.getOrderByComparator();

		if ((orderByComparator != null) &&
			ArrayUtil.contains(
				orderByComparator.getOrderByFields(), "viewCount")) {

			orderByViewCount = true;
		}

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(5);

			sb.append("SELECT * FROM (");

			String sql = null;

			if (orderByViewCount) {
				sql = _customSQL.get(
					getClass(), FIND_A_BY_G_P_VC, queryDefinition);
			}
			else {
				sql = _customSQL.get(
					getClass(), FIND_A_BY_G_P, queryDefinition);
			}

			if (inlineSQLHelper) {
				sql = _inlineSQLHelper.replacePermissionCheck(
					sql, KBArticle.class.getName(), "KBArticle.kbArticleId",
					groupId);
			}

			sb.append(sql);
			sb.append(" UNION ALL ");

			sql = _customSQL.get(getClass(), FIND_F_BY_G_P, queryDefinition);

			if (inlineSQLHelper) {
				sql = _inlineSQLHelper.replacePermissionCheck(
					sql, KBFolder.class.getName(), "KBFolder.kbFolderId",
					groupId);
			}

			sb.append(sql);
			sb.append(") TEMP_TABLE ORDER BY modelFolder DESC");

			sql = sb.toString();

			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar("modelId", Type.LONG);
			sqlQuery.addScalar("modelFolder", Type.LONG);
			sqlQuery.addScalar("modifiedDate", Type.DATE);
			sqlQuery.addScalar("priority", Type.DOUBLE);
			sqlQuery.addScalar("title", Type.STRING);
			sqlQuery.addScalar("viewCount", Type.INTEGER);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (orderByViewCount) {
				long classNameId = _classNameLocalService.getClassNameId(
					KBArticle.class);

				queryPos.add(classNameId);

				queryPos.add(groupId);
				queryPos.add(parentResourcePrimKey);
				queryPos.add(true);
				queryPos.add(queryDefinition.getStatus());
				queryPos.add(classNameId);
			}

			queryPos.add(groupId);
			queryPos.add(parentResourcePrimKey);
			queryPos.add(true);
			queryPos.add(queryDefinition.getStatus());
			queryPos.add(groupId);
			queryPos.add(parentResourcePrimKey);

			List<Object> models = new ArrayList<>();

			Iterator<Object[]> iterator = (Iterator<Object[]>)QueryUtil.iterate(
				sqlQuery, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());

			while (iterator.hasNext()) {
				Object[] array = iterator.next();

				long modelId = (Long)array[0];
				long modelFolder = (Long)array[1];

				Object object = null;

				if (modelFolder == 1) {
					object = _kBFolderPersistence.findByPrimaryKey(modelId);
				}
				else {
					object = _kBArticlePersistence.findByPrimaryKey(modelId);
				}

				models.add(object);
			}

			return models;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CustomSQL _customSQL;

	@Reference
	private InlineSQLHelper _inlineSQLHelper;

	@Reference
	private KBArticlePersistence _kBArticlePersistence;

	@Reference
	private KBFolderPersistence _kBFolderPersistence;

}