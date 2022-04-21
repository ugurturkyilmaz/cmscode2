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

package com.liferay.journal.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;JournalFeed&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFeed
 * @generated
 */
public class JournalFeedTable extends BaseTable<JournalFeedTable> {

	public static final JournalFeedTable INSTANCE = new JournalFeedTable();

	public final Column<JournalFeedTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<JournalFeedTable, Long> ctCollectionId = createColumn(
		"ctCollectionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<JournalFeedTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<JournalFeedTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> feedId = createColumn(
		"feedId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> DDMStructureKey =
		createColumn(
			"DDMStructureKey", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> DDMTemplateKey = createColumn(
		"DDMTemplateKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> DDMRendererTemplateKey =
		createColumn(
			"DDMRendererTemplateKey", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, Integer> delta = createColumn(
		"delta", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> orderByCol = createColumn(
		"orderByCol", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> orderByType = createColumn(
		"orderByType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> targetLayoutFriendlyUrl =
		createColumn(
			"targetLayoutFriendlyUrl", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> targetPortletId =
		createColumn(
			"targetPortletId", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> contentField = createColumn(
		"contentField", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, String> feedFormat = createColumn(
		"feedFormat", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, Double> feedVersion = createColumn(
		"feedVersion", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<JournalFeedTable, Date> lastPublishDate = createColumn(
		"lastPublishDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private JournalFeedTable() {
		super("JournalFeed", JournalFeedTable::new);
	}

}