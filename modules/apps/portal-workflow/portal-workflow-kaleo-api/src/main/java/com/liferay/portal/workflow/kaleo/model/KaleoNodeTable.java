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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;KaleoNode&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNode
 * @generated
 */
public class KaleoNodeTable extends BaseTable<KaleoNodeTable> {

	public static final KaleoNodeTable INSTANCE = new KaleoNodeTable();

	public final Column<KaleoNodeTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<KaleoNodeTable, Long> kaleoNodeId = createColumn(
		"kaleoNodeId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<KaleoNodeTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, Long> kaleoDefinitionId = createColumn(
		"kaleoDefinitionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, Long> kaleoDefinitionVersionId =
		createColumn(
			"kaleoDefinitionVersionId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, String> label = createColumn(
		"label", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, String> metadata = createColumn(
		"metadata", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, String> type = createColumn(
		"type_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, Boolean> initial = createColumn(
		"initial_", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<KaleoNodeTable, Boolean> terminal = createColumn(
		"terminal", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private KaleoNodeTable() {
		super("KaleoNode", KaleoNodeTable::new);
	}

}