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

import java.util.Date;

/**
 * The table class for the &quot;Website&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Website
 * @generated
 */
public class WebsiteTable extends BaseTable<WebsiteTable> {

	public static final WebsiteTable INSTANCE = new WebsiteTable();

	public final Column<WebsiteTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<WebsiteTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Long> websiteId = createColumn(
		"websiteId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<WebsiteTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Long> classNameId = createColumn(
		"classNameId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Long> classPK = createColumn(
		"classPK", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, String> url = createColumn(
		"url", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Long> typeId = createColumn(
		"typeId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Boolean> primary = createColumn(
		"primary_", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<WebsiteTable, Date> lastPublishDate = createColumn(
		"lastPublishDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private WebsiteTable() {
		super("Website", WebsiteTable::new);
	}

}