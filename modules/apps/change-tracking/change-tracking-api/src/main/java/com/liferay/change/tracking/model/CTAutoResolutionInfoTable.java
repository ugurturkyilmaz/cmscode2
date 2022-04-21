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

package com.liferay.change.tracking.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;CTAutoResolutionInfo&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see CTAutoResolutionInfo
 * @generated
 */
public class CTAutoResolutionInfoTable
	extends BaseTable<CTAutoResolutionInfoTable> {

	public static final CTAutoResolutionInfoTable INSTANCE =
		new CTAutoResolutionInfoTable();

	public final Column<CTAutoResolutionInfoTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<CTAutoResolutionInfoTable, Long>
		ctAutoResolutionInfoId = createColumn(
			"ctAutoResolutionInfoId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<CTAutoResolutionInfoTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CTAutoResolutionInfoTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CTAutoResolutionInfoTable, Long> ctCollectionId =
		createColumn(
			"ctCollectionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CTAutoResolutionInfoTable, Long> modelClassNameId =
		createColumn(
			"modelClassNameId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CTAutoResolutionInfoTable, Long> sourceModelClassPK =
		createColumn(
			"sourceModelClassPK", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<CTAutoResolutionInfoTable, Long> targetModelClassPK =
		createColumn(
			"targetModelClassPK", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<CTAutoResolutionInfoTable, String> conflictIdentifier =
		createColumn(
			"conflictIdentifier", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);

	private CTAutoResolutionInfoTable() {
		super("CTAutoResolutionInfo", CTAutoResolutionInfoTable::new);
	}

}