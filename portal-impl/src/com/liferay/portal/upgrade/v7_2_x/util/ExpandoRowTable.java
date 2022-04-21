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

package com.liferay.portal.upgrade.v7_2_x.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class ExpandoRowTable {

	public static final String TABLE_NAME = "ExpandoRow";

	public static final Object[][] TABLE_COLUMNS = {
		{"rowId_", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"modifiedDate", Types.TIMESTAMP}, {"tableId", Types.BIGINT},
		{"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("rowId_", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("tableId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);

}
	public static final String TABLE_SQL_CREATE =
"create table ExpandoRow (rowId_ LONG not null primary key,companyId LONG,modifiedDate DATE null,tableId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP = "drop table ExpandoRow";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_49EB3118 on ExpandoRow (classPK)",
		"create unique index IX_81EFBFF5 on ExpandoRow (tableId, classPK)"
	};

}