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
 * The table class for the &quot;CountryLocalization&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see CountryLocalization
 * @generated
 */
public class CountryLocalizationTable
	extends BaseTable<CountryLocalizationTable> {

	public static final CountryLocalizationTable INSTANCE =
		new CountryLocalizationTable();

	public final Column<CountryLocalizationTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<CountryLocalizationTable, Long> countryLocalizationId =
		createColumn(
			"countryLocalizationId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<CountryLocalizationTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CountryLocalizationTable, Long> countryId =
		createColumn(
			"countryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CountryLocalizationTable, String> languageId =
		createColumn(
			"languageId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CountryLocalizationTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private CountryLocalizationTable() {
		super("CountryLocalization", CountryLocalizationTable::new);
	}

}