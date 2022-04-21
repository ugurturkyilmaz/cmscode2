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

package com.liferay.message.boards.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;MBMailingList&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MBMailingList
 * @generated
 */
public class MBMailingListTable extends BaseTable<MBMailingListTable> {

	public static final MBMailingListTable INSTANCE = new MBMailingListTable();

	public final Column<MBMailingListTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<MBMailingListTable, Long> ctCollectionId = createColumn(
		"ctCollectionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<MBMailingListTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Long> mailingListId = createColumn(
		"mailingListId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<MBMailingListTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Long> categoryId = createColumn(
		"categoryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> emailAddress = createColumn(
		"emailAddress", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> inProtocol = createColumn(
		"inProtocol", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> inServerName = createColumn(
		"inServerName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Integer> inServerPort =
		createColumn(
			"inServerPort", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Boolean> inUseSSL = createColumn(
		"inUseSSL", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> inUserName = createColumn(
		"inUserName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> inPassword = createColumn(
		"inPassword", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Integer> inReadInterval =
		createColumn(
			"inReadInterval", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> outEmailAddress =
		createColumn(
			"outEmailAddress", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Boolean> outCustom = createColumn(
		"outCustom", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> outServerName =
		createColumn(
			"outServerName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Integer> outServerPort =
		createColumn(
			"outServerPort", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Boolean> outUseSSL = createColumn(
		"outUseSSL", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> outUserName = createColumn(
		"outUserName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, String> outPassword = createColumn(
		"outPassword", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Boolean> allowAnonymous =
		createColumn(
			"allowAnonymous", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<MBMailingListTable, Boolean> active = createColumn(
		"active_", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private MBMailingListTable() {
		super("MBMailingList", MBMailingListTable::new);
	}

}