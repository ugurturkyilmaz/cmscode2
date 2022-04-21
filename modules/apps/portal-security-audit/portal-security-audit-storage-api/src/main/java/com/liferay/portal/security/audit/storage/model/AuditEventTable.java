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

package com.liferay.portal.security.audit.storage.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Audit_AuditEvent&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEvent
 * @generated
 */
public class AuditEventTable extends BaseTable<AuditEventTable> {

	public static final AuditEventTable INSTANCE = new AuditEventTable();

	public final Column<AuditEventTable, Long> auditEventId = createColumn(
		"auditEventId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AuditEventTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> eventType = createColumn(
		"eventType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> className = createColumn(
		"className", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> classPK = createColumn(
		"classPK", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> message = createColumn(
		"message", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> clientHost = createColumn(
		"clientHost", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> clientIP = createColumn(
		"clientIP", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> serverName = createColumn(
		"serverName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, Integer> serverPort = createColumn(
		"serverPort", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, String> sessionID = createColumn(
		"sessionID", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AuditEventTable, Clob> additionalInfo = createColumn(
		"additionalInfo", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private AuditEventTable() {
		super("Audit_AuditEvent", AuditEventTable::new);
	}

}