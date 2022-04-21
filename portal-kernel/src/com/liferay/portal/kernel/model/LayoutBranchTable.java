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
 * The table class for the &quot;LayoutBranch&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranch
 * @generated
 */
public class LayoutBranchTable extends BaseTable<LayoutBranchTable> {

	public static final LayoutBranchTable INSTANCE = new LayoutBranchTable();

	public final Column<LayoutBranchTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<LayoutBranchTable, Long> layoutBranchId = createColumn(
		"layoutBranchId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<LayoutBranchTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LayoutBranchTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LayoutBranchTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LayoutBranchTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<LayoutBranchTable, Long> layoutSetBranchId =
		createColumn(
			"layoutSetBranchId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LayoutBranchTable, Long> plid = createColumn(
		"plid", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LayoutBranchTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<LayoutBranchTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<LayoutBranchTable, Boolean> master = createColumn(
		"master", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private LayoutBranchTable() {
		super("LayoutBranch", LayoutBranchTable::new);
	}

}