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

package com.liferay.commerce.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;CommerceAddress&quot; database table.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAddress
 * @generated
 */
public class CommerceAddressTable extends BaseTable<CommerceAddressTable> {

	public static final CommerceAddressTable INSTANCE =
		new CommerceAddressTable();

	public final Column<CommerceAddressTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<CommerceAddressTable, String> externalReferenceCode =
		createColumn(
			"externalReferenceCode", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Long> commerceAddressId =
		createColumn(
			"commerceAddressId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<CommerceAddressTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Long> classNameId = createColumn(
		"classNameId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Long> classPK = createColumn(
		"classPK", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> description =
		createColumn(
			"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> street1 = createColumn(
		"street1", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> street2 = createColumn(
		"street2", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> street3 = createColumn(
		"street3", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> city = createColumn(
		"city", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> zip = createColumn(
		"zip", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Long> regionId = createColumn(
		"regionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Long> countryId = createColumn(
		"countryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Double> latitude = createColumn(
		"latitude", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Double> longitude = createColumn(
		"longitude", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, String> phoneNumber =
		createColumn(
			"phoneNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Boolean> defaultBilling =
		createColumn(
			"defaultBilling", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Boolean> defaultShipping =
		createColumn(
			"defaultShipping", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<CommerceAddressTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private CommerceAddressTable() {
		super("CommerceAddress", CommerceAddressTable::new);
	}

}