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

package com.liferay.commerce.internal.upgrade.v4_10_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Luca Pellizzon
 */
public class CommerceOrderItemUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn("CommerceOrderItem", "deliveryMaxSubscriptionCycles", "LONG");

		addColumn("CommerceOrderItem", "deliverySubscriptionLength", "INTEGER");

		addColumn(
			"CommerceOrderItem", "deliverySubscriptionType", "VARCHAR(75)");

		addColumn(
			"CommerceOrderItem", "deliverySubTypeSettings", "VARCHAR(75)");

		addColumn("CommerceOrderItem", "depth", "DOUBLE");

		addColumn("CommerceOrderItem", "freeShipping", "BOOLEAN");

		addColumn("CommerceOrderItem", "height", "DOUBLE");

		addColumn("CommerceOrderItem", "maxSubscriptionCycles", "LONG");

		addColumn("CommerceOrderItem", "shipSeparately", "BOOLEAN");

		addColumn("CommerceOrderItem", "shippable", "BOOLEAN");

		addColumn("CommerceOrderItem", "shippingExtraPrice", "DOUBLE");

		addColumn("CommerceOrderItem", "subscriptionLength", "INTEGER");

		addColumn("CommerceOrderItem", "subscriptionType", "VARCHAR(75)");

		addColumn(
			"CommerceOrderItem", "subscriptionTypeSettings", "VARCHAR(75)");

		addColumn("CommerceOrderItem", "weight", "DOUBLE");

		addColumn("CommerceOrderItem", "width", "DOUBLE");

		String updateCommerceOrderItemSQL = StringBundler.concat(
			"update CommerceOrderItem SET shippable = ?, freeShipping = ?, ",
			"shipSeparately = ?, shippingExtraPrice = ?, width = ?, height = ",
			"?, depth = ?, weight = ?, subscriptionLength = ?, ",
			"subscriptionType = ?, subscriptionTypeSettings = ?, ",
			"maxSubscriptionCycles = ?, deliverySubscriptionLength = ?, ",
			"deliverySubscriptionType = ?, deliverySubTypeSettings = ?, ",
			"deliveryMaxSubscriptionCycles = ? where CPInstanceId = ? and ",
			"commerceOrderItemId = ?");
		String getCPInstanceSQL = StringBundler.concat(
			"select  CPInstance.CPInstanceId, CPDefinition.shippable, ",
			"CPDefinition.freeShipping, CPDefinition.shipSeparately, ",
			"CPDefinition.shippingExtraPrice, CPDefinition.width, ",
			"CPDefinition.height, CPDefinition.depth, CPDefinition.weight, ",
			"CPDefinition.subscriptionLength, CPDefinition.subscriptionType, ",
			"CPDefinition.subscriptionTypeSettings, ",
			"CPDefinition.maxSubscriptionCycles, ",
			"CPDefinition.deliverySubscriptionLength, ",
			"CPDefinition.deliverySubscriptionType, ",
			"CPDefinition.deliverySubTypeSettings, ",
			"CPDefinition.deliveryMaxSubscriptionCycles, ",
			"CPInstance.overrideSubscriptionInfo, CPInstance.width, ",
			"CPInstance.height, CPInstance.depth, CPInstance.weight, ",
			"CPInstance.subscriptionLength, CPInstance.subscriptionType, ",
			"CPInstance.subscriptionTypeSettings, ",
			"CPInstance.maxSubscriptionCycles, ",
			"CPInstance.deliverySubscriptionLength, ",
			"CPInstance.deliverySubscriptionType, ",
			"CPInstance.deliverySubTypeSettings, ",
			"CPInstance.deliveryMaxSubscriptionCycles, ",
			"CommerceOrderItem.commerceOrderItemId from CPInstance join ",
			"CPDefinition on CPInstance.CPDefinitionId = ",
			"CPDefinition.CPDefinitionId join CommerceOrderItem on ",
			"CPInstance.CPInstanceId = CommerceOrderItem.CPInstanceId join ",
			"CommerceOrder on CommerceOrder.commerceOrderId = ",
			"CommerceOrderItem.commerceOrderId and CommerceOrder.orderStatus ",
			"= 2");

		try (PreparedStatement preparedStatement1 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCommerceOrderItemSQL);
			Statement s = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = s.executeQuery(getCPInstanceSQL)) {

			while (resultSet.next()) {
				long cpInstanceId = resultSet.getLong(1);
				boolean shippable = resultSet.getBoolean(2);
				boolean freeShipping = resultSet.getBoolean(3);
				boolean shipSeparately = resultSet.getBoolean(4);
				double shippingExtraPrice = resultSet.getDouble(5);

				double width = resultSet.getDouble(19);

				if (width <= 0) {
					width = resultSet.getDouble(6);
				}

				double height = resultSet.getDouble(20);

				if (height <= 0) {
					height = resultSet.getDouble(7);
				}

				double depth = resultSet.getDouble(21);

				if (depth <= 0) {
					depth = resultSet.getDouble(8);
				}

				double weight = resultSet.getDouble(22);

				if (weight <= 0) {
					weight = resultSet.getDouble(9);
				}

				int subscriptionLength = resultSet.getInt(10);
				String subscriptionType = resultSet.getString(11);
				String subscriptionTypeSettings = resultSet.getString(12);
				long maxSubscriptionCycles = resultSet.getLong(13);
				int deliverySubscriptionLength = resultSet.getInt(14);
				String deliverySubscriptionType = resultSet.getString(15);
				String deliverySubscriptionTypeSettings = resultSet.getString(
					16);
				long deliveryMaxSubscriptionCycles = resultSet.getLong(17);

				boolean overrideSubscription = resultSet.getBoolean(18);

				if (overrideSubscription) {
					subscriptionLength = resultSet.getInt(23);
					subscriptionType = resultSet.getString(24);
					subscriptionTypeSettings = resultSet.getString(25);
					maxSubscriptionCycles = resultSet.getLong(26);
					deliverySubscriptionLength = resultSet.getInt(27);
					deliverySubscriptionType = resultSet.getString(28);
					deliverySubscriptionTypeSettings = resultSet.getString(29);
					deliveryMaxSubscriptionCycles = resultSet.getLong(30);
				}

				long commerceOrderItemId = resultSet.getLong(31);

				preparedStatement1.setBoolean(1, shippable);
				preparedStatement1.setBoolean(2, freeShipping);
				preparedStatement1.setBoolean(3, shipSeparately);
				preparedStatement1.setDouble(4, shippingExtraPrice);
				preparedStatement1.setDouble(5, width);
				preparedStatement1.setDouble(6, height);
				preparedStatement1.setDouble(7, depth);
				preparedStatement1.setDouble(8, weight);
				preparedStatement1.setInt(9, subscriptionLength);
				preparedStatement1.setString(10, subscriptionType);
				preparedStatement1.setString(11, subscriptionTypeSettings);
				preparedStatement1.setLong(12, maxSubscriptionCycles);
				preparedStatement1.setInt(13, deliverySubscriptionLength);
				preparedStatement1.setString(14, deliverySubscriptionType);
				preparedStatement1.setString(
					15, deliverySubscriptionTypeSettings);
				preparedStatement1.setLong(16, deliveryMaxSubscriptionCycles);
				preparedStatement1.setLong(17, cpInstanceId);
				preparedStatement1.setLong(18, commerceOrderItemId);

				preparedStatement1.addBatch();
			}

			preparedStatement1.executeBatch();
		}
	}

}