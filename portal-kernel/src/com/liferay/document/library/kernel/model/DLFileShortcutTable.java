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

package com.liferay.document.library.kernel.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;DLFileShortcut&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileShortcut
 * @generated
 */
public class DLFileShortcutTable extends BaseTable<DLFileShortcutTable> {

	public static final DLFileShortcutTable INSTANCE =
		new DLFileShortcutTable();

	public final Column<DLFileShortcutTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<DLFileShortcutTable, Long> ctCollectionId =
		createColumn(
			"ctCollectionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<DLFileShortcutTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Long> fileShortcutId =
		createColumn(
			"fileShortcutId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<DLFileShortcutTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Long> repositoryId = createColumn(
		"repositoryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Long> folderId = createColumn(
		"folderId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Long> toFileEntryId = createColumn(
		"toFileEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, String> treePath = createColumn(
		"treePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Boolean> active = createColumn(
		"active_", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Date> lastPublishDate =
		createColumn(
			"lastPublishDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Long> statusByUserId =
		createColumn(
			"statusByUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, String> statusByUserName =
		createColumn(
			"statusByUserName", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<DLFileShortcutTable, Date> statusDate = createColumn(
		"statusDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private DLFileShortcutTable() {
		super("DLFileShortcut", DLFileShortcutTable::new);
	}

}