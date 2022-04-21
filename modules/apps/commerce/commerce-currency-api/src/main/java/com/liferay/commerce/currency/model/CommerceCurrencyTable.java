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

package com.liferay.commerce.currency.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.math.BigDecimal;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;CommerceCurrency&quot; database table.
 *
 * @author Andrea Di Giorgi
 * @see CommerceCurrency
 * @generated
 */
public class CommerceCurrencyTable extends BaseTable<CommerceCurrencyTable> {

	public static final CommerceCurrencyTable INSTANCE =
		new CommerceCurrencyTable();

	public final Column<CommerceCurrencyTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<CommerceCurrencyTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Long> commerceCurrencyId =
		createColumn(
			"commerceCurrencyId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<CommerceCurrencyTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, String> code = createColumn(
		"code_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, String> symbol = createColumn(
		"symbol", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, BigDecimal> rate = createColumn(
		"rate", BigDecimal.class, Types.DECIMAL, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, String> formatPattern =
		createColumn(
			"formatPattern", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Integer> maxFractionDigits =
		createColumn(
			"maxFractionDigits", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Integer> minFractionDigits =
		createColumn(
			"minFractionDigits", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, String> roundingMode =
		createColumn(
			"roundingMode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Boolean> primary = createColumn(
		"primary_", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Double> priority = createColumn(
		"priority", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Boolean> active = createColumn(
		"active_", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<CommerceCurrencyTable, Date> lastPublishDate =
		createColumn(
			"lastPublishDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);

	private CommerceCurrencyTable() {
		super("CommerceCurrency", CommerceCurrencyTable::new);
	}

}