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
public class SystemEventTable {

	public static final String TABLE_NAME = "SystemEvent";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"systemEventId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"classUuid", Types.VARCHAR},
		{"referrerClassNameId", Types.BIGINT},
		{"parentSystemEventId", Types.BIGINT},
		{"systemEventSetKey", Types.BIGINT}, {"type_", Types.INTEGER},
		{"extraData", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);

TABLE_COLUMNS_MAP.put("systemEventId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);

TABLE_COLUMNS_MAP.put("classUuid", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("referrerClassNameId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("parentSystemEventId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("systemEventSetKey", Types.BIGINT);

TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);

TABLE_COLUMNS_MAP.put("extraData", Types.CLOB);

}
	public static final String TABLE_SQL_CREATE =
"create table SystemEvent (mvccVersion LONG default 0 not null,systemEventId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,classNameId LONG,classPK LONG,classUuid VARCHAR(75) null,referrerClassNameId LONG,parentSystemEventId LONG,systemEventSetKey LONG,type_ INTEGER,extraData TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table SystemEvent";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_FFCBB747 on SystemEvent (groupId, classNameId, classPK, type_)",
		"create index IX_A19C89FF on SystemEvent (groupId, systemEventSetKey)"
	};

}