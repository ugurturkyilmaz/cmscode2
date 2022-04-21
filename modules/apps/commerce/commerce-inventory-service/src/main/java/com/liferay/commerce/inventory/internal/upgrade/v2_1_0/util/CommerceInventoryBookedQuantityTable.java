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

package com.liferay.commerce.inventory.internal.upgrade.v2_1_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class CommerceInventoryBookedQuantityTable {

	public static final String TABLE_NAME = "CIBookedQuantity";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"CIBookedQuantityId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"sku", Types.VARCHAR},
		{"quantity", Types.INTEGER}, {"expirationDate", Types.TIMESTAMP},
		{"bookedNote", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);

TABLE_COLUMNS_MAP.put("CIBookedQuantityId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("sku", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);

TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("bookedNote", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE =
"create table CIBookedQuantity (mvccVersion LONG default 0 not null,CIBookedQuantityId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,sku VARCHAR(75) null,quantity INTEGER,expirationDate DATE null,bookedNote VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table CIBookedQuantity";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
	};

}