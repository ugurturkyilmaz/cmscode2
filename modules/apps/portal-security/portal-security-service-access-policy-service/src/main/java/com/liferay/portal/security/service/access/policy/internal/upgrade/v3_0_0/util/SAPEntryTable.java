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

package com.liferay.portal.security.service.access.policy.internal.upgrade.v3_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class SAPEntryTable {

	public static final String TABLE_NAME = "SAPEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"sapEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"allowedServiceSignatures", Types.VARCHAR},
		{"defaultSAPEntry", Types.BOOLEAN},
		{"enabled", Types.BOOLEAN},
		{"name", Types.VARCHAR},
		{"title", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("sapEntryId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("allowedServiceSignatures", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("defaultSAPEntry", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("enabled", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE = "create table SAPEntry (uuid_ VARCHAR(75) null,sapEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,allowedServiceSignatures STRING null,defaultSAPEntry BOOLEAN,enabled BOOLEAN,name VARCHAR(75) null,title STRING null)";

	public static final String TABLE_SQL_DROP = "drop table SAPEntry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_6D669D6F on SAPEntry (companyId, defaultSAPEntry)",
		"create index IX_90740311 on SAPEntry (companyId, name[$COLUMN_LENGTH:75$])",
		"create index IX_AAAEBA0A on SAPEntry (uuid_[$COLUMN_LENGTH:75$], companyId)"
	};

}