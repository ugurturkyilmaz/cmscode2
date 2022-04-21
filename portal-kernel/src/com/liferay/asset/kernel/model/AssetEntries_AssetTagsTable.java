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

package com.liferay.asset.kernel.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;AssetEntries_AssetTags&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntry
 * @see AssetTag
 * @generated
 */
public class AssetEntries_AssetTagsTable
	extends BaseTable<AssetEntries_AssetTagsTable> {

	public static final AssetEntries_AssetTagsTable INSTANCE =
		new AssetEntries_AssetTagsTable();

	public final Column<AssetEntries_AssetTagsTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AssetEntries_AssetTagsTable, Long> entryId =
		createColumn("entryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AssetEntries_AssetTagsTable, Long> tagId = createColumn(
		"tagId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AssetEntries_AssetTagsTable, Long> ctCollectionId =
		createColumn(
			"ctCollectionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AssetEntries_AssetTagsTable, Boolean> ctChangeType =
		createColumn(
			"ctChangeType", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private AssetEntries_AssetTagsTable() {
		super("AssetEntries_AssetTags", AssetEntries_AssetTagsTable::new);
	}

}