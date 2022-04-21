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

package com.liferay.site.navigation.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;SiteNavigationMenuItem&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SiteNavigationMenuItem
 * @generated
 */
public class SiteNavigationMenuItemTable
	extends BaseTable<SiteNavigationMenuItemTable> {

	public static final SiteNavigationMenuItemTable INSTANCE =
		new SiteNavigationMenuItemTable();

	public final Column<SiteNavigationMenuItemTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<SiteNavigationMenuItemTable, Long> ctCollectionId =
		createColumn(
			"ctCollectionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SiteNavigationMenuItemTable, String> uuid =
		createColumn("uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Long>
		siteNavigationMenuItemId = createColumn(
			"siteNavigationMenuItemId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<SiteNavigationMenuItemTable, Long> groupId =
		createColumn("groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Long> userId =
		createColumn("userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, String> userName =
		createColumn(
			"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Long>
		siteNavigationMenuId = createColumn(
			"siteNavigationMenuId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Long>
		parentSiteNavigationMenuItemId = createColumn(
			"parentSiteNavigationMenuItemId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, String> name =
		createColumn("name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, String> type =
		createColumn("type_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Clob> typeSettings =
		createColumn(
			"typeSettings", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Integer> order =
		createColumn(
			"order_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SiteNavigationMenuItemTable, Date> lastPublishDate =
		createColumn(
			"lastPublishDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);

	private SiteNavigationMenuItemTable() {
		super("SiteNavigationMenuItem", SiteNavigationMenuItemTable::new);
	}

}