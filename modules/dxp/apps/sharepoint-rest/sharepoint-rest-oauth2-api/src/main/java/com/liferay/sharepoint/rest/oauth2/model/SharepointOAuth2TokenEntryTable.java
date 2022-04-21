/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.sharepoint.rest.oauth2.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;SharepointOAuth2TokenEntry&quot; database table.
 *
 * @author Adolfo Pérez
 * @see SharepointOAuth2TokenEntry
 * @generated
 */
public class SharepointOAuth2TokenEntryTable
	extends BaseTable<SharepointOAuth2TokenEntryTable> {

	public static final SharepointOAuth2TokenEntryTable INSTANCE =
		new SharepointOAuth2TokenEntryTable();

	public final Column<SharepointOAuth2TokenEntryTable, Long>
		sharepointOAuth2TokenEntryId = createColumn(
			"sharepointOAuth2TokenEntryId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<SharepointOAuth2TokenEntryTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SharepointOAuth2TokenEntryTable, Long> userId =
		createColumn("userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SharepointOAuth2TokenEntryTable, String> userName =
		createColumn(
			"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SharepointOAuth2TokenEntryTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SharepointOAuth2TokenEntryTable, Clob> accessToken =
		createColumn(
			"accessToken", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<SharepointOAuth2TokenEntryTable, String>
		configurationPid = createColumn(
			"configurationPid", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<SharepointOAuth2TokenEntryTable, Date> expirationDate =
		createColumn(
			"expirationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SharepointOAuth2TokenEntryTable, Clob> refreshToken =
		createColumn(
			"refreshToken", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private SharepointOAuth2TokenEntryTable() {
		super(
			"SharepointOAuth2TokenEntry", SharepointOAuth2TokenEntryTable::new);
	}

}