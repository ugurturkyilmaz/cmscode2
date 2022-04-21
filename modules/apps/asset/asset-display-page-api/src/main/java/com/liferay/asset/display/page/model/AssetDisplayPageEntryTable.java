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

package com.liferay.asset.display.page.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;AssetDisplayPageEntry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see AssetDisplayPageEntry
 * @generated
 */
public class AssetDisplayPageEntryTable
	extends BaseTable<AssetDisplayPageEntryTable> {

	public static final AssetDisplayPageEntryTable INSTANCE =
		new AssetDisplayPageEntryTable();

	public final Column<AssetDisplayPageEntryTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<AssetDisplayPageEntryTable, Long> ctCollectionId =
		createColumn(
			"ctCollectionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AssetDisplayPageEntryTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Long>
		assetDisplayPageEntryId = createColumn(
			"assetDisplayPageEntryId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<AssetDisplayPageEntryTable, Long> groupId =
		createColumn("groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, String> userName =
		createColumn(
			"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Long> classNameId =
		createColumn(
			"classNameId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Long> classPK =
		createColumn("classPK", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Long>
		layoutPageTemplateEntryId = createColumn(
			"layoutPageTemplateEntryId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Integer> type =
		createColumn(
			"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<AssetDisplayPageEntryTable, Long> plid = createColumn(
		"plid", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private AssetDisplayPageEntryTable() {
		super("AssetDisplayPageEntry", AssetDisplayPageEntryTable::new);
	}

}