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

package com.liferay.commerce.discount.service.persistence.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.model.impl.CommerceDiscountRelImpl;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountRelFinder;
import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Iterator;
import java.util.List;

/**
 * @author Riccardo Alberti
 */
public class CommerceDiscountRelFinderImpl
	extends CommerceDiscountRelFinderBaseImpl
	implements CommerceDiscountRelFinder {

	public static final String COUNT_CATEGORIES_BY_COMMERCE_DISCOUNT_ID =
		CommerceDiscountRelFinder.class.getName() +
			".countCategoriesByCommerceDiscountId";

	public static final String COUNT_CP_DEFINITIONS_BY_COMMERCE_DISCOUNT_ID =
		CommerceDiscountRelFinder.class.getName() +
			".countCPDefinitionsByCommerceDiscountId";

	public static final String COUNT_PRICING_CLASSES_BY_COMMERCE_DISCOUNT_ID =
		CommerceDiscountRelFinder.class.getName() +
			".countPricingClassesByCommerceDiscountId";

	public static final String FIND_CATEGORIES_BY_COMMERCE_DISCOUNT_ID =
		CommerceDiscountRelFinder.class.getName() +
			".findCategoriesByCommerceDiscountId";

	public static final String FIND_CP_DEFINITIONS_BY_COMMERCE_DISCOUNT_ID =
		CommerceDiscountRelFinder.class.getName() +
			".findCPDefinitionsByCommerceDiscountId";

	public static final String FIND_PRICING_CLASSES_BY_COMMERCE_DISCOUNT_ID =
		CommerceDiscountRelFinder.class.getName() +
			".findPricingClassesByCommerceDiscountId";

	@Override
	public int countCategoriesByCommerceDiscountId(
		long commerceDiscountId, String name) {

		return countCategoriesByCommerceDiscountId(
			commerceDiscountId, name, false);
	}

	@Override
	public int countCategoriesByCommerceDiscountId(
		long commerceDiscountId, String name, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), COUNT_CATEGORIES_BY_COMMERCE_DISCOUNT_ID);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommerceDiscount.class.getName(),
					"CommerceDiscount.commerceDiscountId", null, null,
					new long[] {0}, null);
			}

			String[] keywords = _customSQL.keywords(name, true);

			if (Validator.isNotNull(name)) {
				sql = _customSQL.replaceKeywords(
					sql, "(LOWER(AssetCategory.name)", StringPool.LIKE, true,
					keywords);
				sql = _customSQL.replaceAndOperator(sql, false);
			}
			else {
				sql = StringUtil.removeSubstring(
					sql,
					" AND (LOWER(AssetCategory.name) LIKE ? " +
						"[$AND_OR_NULL_CHECK$])");
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(commerceDiscountId);
			queryPos.add(
				PortalUtil.getClassNameId(AssetCategory.class.getName()));

			if (Validator.isNotNull(name)) {
				queryPos.add(keywords, 2);
			}

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

	@Override
	public int countCPDefinitionsByCommerceDiscountId(
		long commerceDiscountId, String name, String languageId) {

		return countCPDefinitionsByCommerceDiscountId(
			commerceDiscountId, name, languageId, false);
	}

	@Override
	public int countCPDefinitionsByCommerceDiscountId(
		long commerceDiscountId, String name, String languageId,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), COUNT_CP_DEFINITIONS_BY_COMMERCE_DISCOUNT_ID);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommerceDiscount.class.getName(),
					"CommerceDiscount.commerceDiscountId", null, null,
					new long[] {0}, null);
			}

			String[] keywords = _customSQL.keywords(name, true);

			if (Validator.isNotNull(name)) {
				sql = _customSQL.replaceKeywords(
					sql, "(LOWER(CPDefinitionLocalization.name)",
					StringPool.LIKE, true, keywords);
				sql = _customSQL.replaceAndOperator(sql, false);
			}
			else {
				sql = StringUtil.removeSubstring(
					sql,
					" AND (LOWER(CPDefinitionLocalization.name) LIKE ? " +
						"[$AND_OR_NULL_CHECK$])");
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(languageId);
			queryPos.add(commerceDiscountId);
			queryPos.add(
				PortalUtil.getClassNameId(CPDefinition.class.getName()));

			if (Validator.isNotNull(name)) {
				queryPos.add(keywords, 2);
			}

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

	@Override
	public int countPricingClassesByCommerceDiscountId(
		long commerceDiscountId, String title) {

		return countPricingClassesByCommerceDiscountId(
			commerceDiscountId, title, false);
	}

	@Override
	public int countPricingClassesByCommerceDiscountId(
		long commerceDiscountId, String title, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), COUNT_PRICING_CLASSES_BY_COMMERCE_DISCOUNT_ID);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommerceDiscount.class.getName(),
					"CommerceDiscount.commerceDiscountId", null, null,
					new long[] {0}, null);
			}

			String[] keywords = _customSQL.keywords(title, true);

			if (Validator.isNotNull(title)) {
				sql = _customSQL.replaceKeywords(
					sql, "(LOWER(CommercePricingClass.title)", StringPool.LIKE,
					true, keywords);
				sql = _customSQL.replaceAndOperator(sql, false);
			}
			else {
				sql = StringUtil.removeSubstring(
					sql,
					" AND (LOWER(CommercePricingClass.title) LIKE ? " +
						"[$AND_OR_NULL_CHECK$])");
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(commerceDiscountId);
			queryPos.add(
				PortalUtil.getClassNameId(
					CommercePricingClass.class.getName()));

			if (Validator.isNotNull(title)) {
				queryPos.add(keywords, 2);
			}

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

	@Override
	public List<CommerceDiscountRel> findCategoriesByCommerceDiscountId(
		long commerceDiscountId, String name, int start, int end) {

		return findCategoriesByCommerceDiscountId(
			commerceDiscountId, name, start, end, false);
	}

	@Override
	public List<CommerceDiscountRel> findCategoriesByCommerceDiscountId(
		long commerceDiscountId, String name, int start, int end,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String[] keywords = _customSQL.keywords(name, true);

			String sql = _customSQL.get(
				getClass(), FIND_CATEGORIES_BY_COMMERCE_DISCOUNT_ID);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommerceDiscount.class.getName(),
					"CommerceDiscount.commerceDiscountId", null, null,
					new long[] {0}, null);
			}

			if (Validator.isNotNull(name)) {
				sql = _customSQL.replaceKeywords(
					sql, "(LOWER(AssetCategory.name)", StringPool.LIKE, true,
					keywords);
				sql = _customSQL.replaceAndOperator(sql, false);
			}
			else {
				sql = StringUtil.removeSubstring(
					sql,
					" AND (LOWER(AssetCategory.name) LIKE ? " +
						"[$AND_OR_NULL_CHECK$])");
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity(
				CommerceDiscountRelImpl.TABLE_NAME,
				CommerceDiscountRelImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(commerceDiscountId);
			queryPos.add(
				PortalUtil.getClassNameId(AssetCategory.class.getName()));

			if (Validator.isNotNull(name)) {
				queryPos.add(keywords, 2);
			}

			return (List<CommerceDiscountRel>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommerceDiscountRel> findCPDefinitionsByCommerceDiscountId(
		long commerceDiscountId, String name, String languageId, int start,
		int end) {

		return findCPDefinitionsByCommerceDiscountId(
			commerceDiscountId, name, languageId, start, end, false);
	}

	@Override
	public List<CommerceDiscountRel> findCPDefinitionsByCommerceDiscountId(
		long commerceDiscountId, String name, String languageId, int start,
		int end, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String[] keywords = _customSQL.keywords(name, true);

			String sql = _customSQL.get(
				getClass(), FIND_CP_DEFINITIONS_BY_COMMERCE_DISCOUNT_ID);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommerceDiscount.class.getName(),
					"CommerceDiscount.commerceDiscountId", null, null,
					new long[] {0}, null);
			}

			if (Validator.isNotNull(name)) {
				sql = _customSQL.replaceKeywords(
					sql, "(LOWER(CPDefinitionLocalization.name)",
					StringPool.LIKE, true, keywords);
				sql = _customSQL.replaceAndOperator(sql, false);
			}
			else {
				sql = StringUtil.removeSubstring(
					sql,
					" AND (LOWER(CPDefinitionLocalization.name) LIKE ? " +
						"[$AND_OR_NULL_CHECK$])");
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity(
				CommerceDiscountRelImpl.TABLE_NAME,
				CommerceDiscountRelImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(languageId);
			queryPos.add(commerceDiscountId);
			queryPos.add(
				PortalUtil.getClassNameId(CPDefinition.class.getName()));

			if (Validator.isNotNull(name)) {
				queryPos.add(keywords, 2);
			}

			return (List<CommerceDiscountRel>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommerceDiscountRel> findPricingClassesByCommerceDiscountId(
		long commerceDiscountId, String title, int start, int end) {

		return findPricingClassesByCommerceDiscountId(
			commerceDiscountId, title, start, end, false);
	}

	@Override
	public List<CommerceDiscountRel> findPricingClassesByCommerceDiscountId(
		long commerceDiscountId, String title, int start, int end,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String[] keywords = _customSQL.keywords(title, true);

			String sql = _customSQL.get(
				getClass(), FIND_PRICING_CLASSES_BY_COMMERCE_DISCOUNT_ID);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommerceDiscount.class.getName(),
					"CommerceDiscount.commerceDiscountId", null, null,
					new long[] {0}, null);
			}

			if (Validator.isNotNull(title)) {
				sql = _customSQL.replaceKeywords(
					sql, "(LOWER(CommercePricingClass.title)", StringPool.LIKE,
					true, keywords);
				sql = _customSQL.replaceAndOperator(sql, false);
			}
			else {
				sql = StringUtil.removeSubstring(
					sql,
					" AND (LOWER(CommercePricingClass.title) LIKE ? " +
						"[$AND_OR_NULL_CHECK$])");
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity(
				CommerceDiscountRelImpl.TABLE_NAME,
				CommerceDiscountRelImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(commerceDiscountId);
			queryPos.add(
				PortalUtil.getClassNameId(
					CommercePricingClass.class.getName()));

			if (Validator.isNotNull(title)) {
				queryPos.add(keywords, 2);
			}

			return (List<CommerceDiscountRel>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}