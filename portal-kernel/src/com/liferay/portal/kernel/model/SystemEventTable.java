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

package com.liferay.portal.kernel.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;SystemEvent&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SystemEvent
 * @generated
 */
public class SystemEventTable extends BaseTable<SystemEventTable> {

	public static final SystemEventTable INSTANCE = new SystemEventTable();

	public final Column<SystemEventTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<SystemEventTable, Long> ctCollectionId = createColumn(
		"ctCollectionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SystemEventTable, Long> systemEventId = createColumn(
		"systemEventId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SystemEventTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Long> classNameId = createColumn(
		"classNameId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Long> classPK = createColumn(
		"classPK", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, String> classUuid = createColumn(
		"classUuid", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Long> referrerClassNameId =
		createColumn(
			"referrerClassNameId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Long> parentSystemEventId =
		createColumn(
			"parentSystemEventId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Long> systemEventSetKey =
		createColumn(
			"systemEventSetKey", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SystemEventTable, Clob> extraData = createColumn(
		"extraData", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private SystemEventTable() {
		super("SystemEvent", SystemEventTable::new);
	}

}