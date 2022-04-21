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

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.impl.CommerceOrderImpl;
import com.liferay.commerce.service.persistence.CommerceOrderFinder;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderFinderImpl
	extends CommerceOrderFinderBaseImpl implements CommerceOrderFinder {

	public static final String FIND_BY_G_O =
		CommerceOrderFinder.class.getName() + ".findByG_O";

	public static final String FIND_BY_G_U_C_O_S =
		CommerceOrderFinder.class.getName() + ".findByG_U_C_O_S";

	public static final String
		GET_SHIPPED_COMMERCE_ORDERS_BY_COMMERCE_SHIPMENT_ID =
			CommerceOrderFinder.class.getName() +
				".getShippedCommerceOrdersByCommerceShipmentId";

	@Override
	public CommerceOrder fetchByG_U_C_O_S_First(
		long groupId, long userId, long commerceAccountId, int orderStatus) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_G_U_C_O_S);

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity(
				CommerceOrderImpl.TABLE_NAME, CommerceOrderImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);
			queryPos.add(commerceAccountId);
			queryPos.add(orderStatus);
			queryPos.add(userId);

			List<CommerceOrder> commerceOrders =
				(List<CommerceOrder>)QueryUtil.list(
					sqlQuery, getDialect(), 0, 1);

			if (!commerceOrders.isEmpty()) {
				return commerceOrders.get(0);
			}

			return null;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommerceOrder> findByG_O(long groupId, int[] orderStatuses) {
		return doFindByG_O(
			groupId, orderStatuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<CommerceOrder> findByG_O(
		long groupId, int[] orderStatuses, int start, int end) {

		return doFindByG_O(groupId, orderStatuses, start, end);
	}

	@Override
	public List<CommerceOrder> getShippedCommerceOrdersByCommerceShipmentId(
		long shipmentId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(),
				GET_SHIPPED_COMMERCE_ORDERS_BY_COMMERCE_SHIPMENT_ID);

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity("CommerceOrder", CommerceOrderImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(shipmentId);

			return (List<CommerceOrder>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<CommerceOrder> doFindByG_O(
		long groupId, int[] orderStatuses, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_G_O);

			sql = replaceOrderStatus(sql, orderStatuses);

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity("CommerceOrder", CommerceOrderImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			return (List<CommerceOrder>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected String replaceOrderStatus(String sql, int[] orderStatuses) {
		StringBundler sb = new StringBundler(orderStatuses.length);

		for (int i = 0; i < orderStatuses.length; i++) {
			sb.append(orderStatuses[i]);

			if (i != (orderStatuses.length - 1)) {
				sb.append(", ");
			}
		}

		return StringUtil.replace(sql, "[$ORDER_STATUS$]", sb.toString());
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}