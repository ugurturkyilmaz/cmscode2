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

import java.sql.Types;

/**
 * The table class for the &quot;RecentLayoutSetBranch&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSetBranch
 * @generated
 */
public class RecentLayoutSetBranchTable
	extends BaseTable<RecentLayoutSetBranchTable> {

	public static final RecentLayoutSetBranchTable INSTANCE =
		new RecentLayoutSetBranchTable();

	public final Column<RecentLayoutSetBranchTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<RecentLayoutSetBranchTable, Long>
		recentLayoutSetBranchId = createColumn(
			"recentLayoutSetBranchId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<RecentLayoutSetBranchTable, Long> groupId =
		createColumn("groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RecentLayoutSetBranchTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RecentLayoutSetBranchTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RecentLayoutSetBranchTable, Long> layoutSetBranchId =
		createColumn(
			"layoutSetBranchId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RecentLayoutSetBranchTable, Long> layoutSetId =
		createColumn(
			"layoutSetId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private RecentLayoutSetBranchTable() {
		super("RecentLayoutSetBranch", RecentLayoutSetBranchTable::new);
	}

}