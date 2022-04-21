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

package com.liferay.portlet.asset.service.persistence.impl;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.persistence.AssetVocabularyFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.asset.model.impl.AssetVocabularyImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Juan Fernández
 */
public class AssetVocabularyFinderImpl
	extends AssetVocabularyFinderBaseImpl implements AssetVocabularyFinder {

	public static final String COUNT_BY_G_N =
		AssetVocabularyFinder.class.getName() + ".countByG_N";

	public static final String FIND_BY_G_N =
		AssetVocabularyFinder.class.getName() + ".findByG_N";

	@Override
	public int countByG_N(long groupId, String name) {
		return doCountByG_N(groupId, name, false);
	}

	@Override
	public int filterCountByG_N(long groupId, String name) {
		return doCountByG_N(groupId, name, true);
	}

	@Override
	public List<AssetVocabulary> filterFindByG_N(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetVocabulary> orderByComparator) {

		return doFindByG_N(groupId, name, start, end, orderByComparator, true);
	}

	@Override
	public List<AssetVocabulary> findByG_N(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetVocabulary> orderByComparator) {

		return doFindByG_N(groupId, name, start, end, orderByComparator, false);
	}

	protected int doCountByG_N(
		long groupId, String name, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_N);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, AssetVocabulary.class.getName(),
					"AssetVocabulary.vocabularyId", groupId);
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);
			queryPos.add(name);
			queryPos.add(name);

			Iterator<Long> iterator = sqlQuery.iterate();

			if (iterator.hasNext()) {
				Long count = iterator.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<AssetVocabulary> doFindByG_N(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetVocabulary> orderByComparator,
		boolean inlineSQLHelper) {

		name = StringUtil.toLowerCase(name.trim());

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_N);

			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, AssetVocabulary.class.getName(),
					"AssetVocabulary.vocabularyId", groupId);
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity("AssetVocabulary", AssetVocabularyImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);
			queryPos.add(name);
			queryPos.add(name);

			return (List<AssetVocabulary>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

}