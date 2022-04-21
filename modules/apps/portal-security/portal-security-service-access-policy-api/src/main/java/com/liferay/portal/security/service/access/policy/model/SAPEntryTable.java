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

package com.liferay.portal.security.service.access.policy.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;SAPEntry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SAPEntry
 * @generated
 */
public class SAPEntryTable extends BaseTable<SAPEntryTable> {

	public static final SAPEntryTable INSTANCE = new SAPEntryTable();

	public final Column<SAPEntryTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, Long> sapEntryId = createColumn(
		"sapEntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SAPEntryTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, String> allowedServiceSignatures =
		createColumn(
			"allowedServiceSignatures", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, Boolean> defaultSAPEntry = createColumn(
		"defaultSAPEntry", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, Boolean> enabled = createColumn(
		"enabled", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SAPEntryTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private SAPEntryTable() {
		super("SAPEntry", SAPEntryTable::new);
	}

}