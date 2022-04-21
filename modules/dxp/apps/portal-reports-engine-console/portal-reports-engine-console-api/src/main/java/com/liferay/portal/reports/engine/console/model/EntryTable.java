/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.reports.engine.console.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Reports_Entry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Entry
 * @generated
 */
public class EntryTable extends BaseTable<EntryTable> {

	public static final EntryTable INSTANCE = new EntryTable();

	public final Column<EntryTable, Long> entryId = createColumn(
		"entryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EntryTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Long> definitionId = createColumn(
		"definitionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> format = createColumn(
		"format", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Boolean> scheduleRequest = createColumn(
		"scheduleRequest", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Date> startDate = createColumn(
		"startDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Boolean> repeating = createColumn(
		"repeating", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> recurrence = createColumn(
		"recurrence", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> emailNotifications = createColumn(
		"emailNotifications", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> emailDelivery = createColumn(
		"emailDelivery", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> portletId = createColumn(
		"portletId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> pageURL = createColumn(
		"pageURL", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, Clob> reportParameters = createColumn(
		"reportParameters", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> errorMessage = createColumn(
		"errorMessage", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private EntryTable() {
		super("Reports_Entry", EntryTable::new);
	}

}