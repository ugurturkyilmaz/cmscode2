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

package com.liferay.portal.upgrade.v7_4_x;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.util.PropsValues;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Drew Brokke
 */
public class UpgradeRegion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQLTemplate("update-7.3.0-7.4.0-region.sql", false);

		long companyId = 0;
		long userId = 0;
		String languageId = LocaleUtil.toLanguageId(LocaleUtil.US);

		String sql = StringBundler.concat(
			"select User_.companyId, User_.userId, User_.languageId from ",
			"User_ join Company on User_.companyId = Company.companyId where ",
			"User_.defaultUser = [$TRUE$] and Company.webId = ",
			StringUtil.quote(PropsValues.COMPANY_DEFAULT_WEB_ID));

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				SQLTransformer.transform(sql));
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				companyId = resultSet.getLong(1);
				userId = resultSet.getLong(2);
				languageId = resultSet.getString(3);
			}
		}

		runSQL(
			"update Region set defaultLanguageId = " +
				StringUtil.quote(languageId) +
					" where defaultLanguageId is null");

		if (companyId > 0) {
			runSQL(
				"update Region set companyId = " + companyId +
					" where companyId is null");
		}

		if (userId > 0) {
			runSQL(
				"update Region set userId = " + userId +
					" where userId is null");
		}

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			try (PreparedStatement preparedStatement1 =
					connection.prepareStatement(
						"select regionId from Region where uuid_ is null");
				PreparedStatement preparedStatement2 =
					AutoBatchPreparedStatementUtil.autoBatch(
						connection.prepareStatement(
							"update Region set uuid_ = ? where regionId = ?"));
				ResultSet resultSet = preparedStatement1.executeQuery()) {

				while (resultSet.next()) {
					preparedStatement2.setString(1, PortalUUIDUtil.generate());
					preparedStatement2.setLong(2, resultSet.getLong(1));

					preparedStatement2.addBatch();
				}

				preparedStatement2.executeBatch();
			}
		}
	}

}