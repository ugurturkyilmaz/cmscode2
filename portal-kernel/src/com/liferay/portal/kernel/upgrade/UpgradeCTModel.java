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

package com.liferay.portal.kernel.upgrade;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.db.DBInspector;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * @author     Preston Crary
 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
 *             CTModelUpgradeProcess}
 */
@Deprecated
public class UpgradeCTModel extends UpgradeProcess {

	public UpgradeCTModel(String... tableNames) {
		if (tableNames.length == 0) {
			throw new IllegalArgumentException("Table names is empty");
		}

		_tableNames = tableNames;
	}

	@Override
	protected void doUpgrade() throws Exception {
		DatabaseMetaData databaseMetaData = connection.getMetaData();

		DBInspector dbInspector = new DBInspector(connection);

		for (String tableName : _tableNames) {
			try (LoggingTimer loggingTimer = new LoggingTimer(
					UpgradeCTModel.class, tableName)) {

				_upgradeCTModel(databaseMetaData, dbInspector, tableName);
			}
		}
	}

	private void _upgradeCTModel(
			DatabaseMetaData databaseMetaData, DBInspector dbInspector,
			String tableName)
		throws Exception {

		String normalizedTableName = dbInspector.normalizeName(
			tableName, databaseMetaData);

		try (ResultSet resultSet = databaseMetaData.getColumns(
				dbInspector.getCatalog(), dbInspector.getSchema(),
				normalizedTableName,
				dbInspector.normalizeName(
					"ctCollectionId", databaseMetaData))) {

			if (resultSet.next()) {
				return;
			}
		}

		String[] primaryKeyColumnNames = getPrimaryKeyColumnNames(
			connection, tableName);

		if (primaryKeyColumnNames.length == 0) {
			throw new UpgradeException(
				"No primary key column found for " + normalizedTableName);
		}
		else if (primaryKeyColumnNames.length > 2) {
			throw new UpgradeException(
				"Too many primary key columns to upgrade " +
					normalizedTableName);
		}

		String primaryKeyColumnName1 = primaryKeyColumnNames[0];

		String primaryKeyColumnName2 = null;

		if (primaryKeyColumnNames.length == 2) {
			primaryKeyColumnName2 = primaryKeyColumnNames[1];
		}

		runSQL(
			StringBundler.concat(
				"alter table ", normalizedTableName,
				" add ctCollectionId LONG default 0 not null"));

		// Assume table is a mapping table

		if (primaryKeyColumnName2 != null) {
			runSQL(
				StringBundler.concat(
					"alter table ", normalizedTableName,
					" add ctChangeType BOOLEAN default null"));
		}

		removePrimaryKey(tableName);

		StringBundler sb = new StringBundler(7);

		sb.append("alter table ");
		sb.append(normalizedTableName);
		sb.append(" add primary key (");
		sb.append(primaryKeyColumnName1);

		if (primaryKeyColumnName2 != null) {
			sb.append(", ");
			sb.append(primaryKeyColumnName2);
		}

		sb.append(", ctCollectionId)");

		runSQL(sb.toString());
	}

	private final String[] _tableNames;

}