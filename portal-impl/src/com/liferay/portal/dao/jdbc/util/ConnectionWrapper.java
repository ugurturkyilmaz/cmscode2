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

package com.liferay.portal.dao.jdbc.util;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.sql.DataSource;

/**
 * @author Alberto Chaparro
 */
public class ConnectionWrapper implements Connection {

	public ConnectionWrapper(Connection connection) {
		_connection = connection;
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		_connection.abort(executor);
	}

	@Override
	public void clearWarnings() throws SQLException {
		_connection.clearWarnings();
	}

	@Override
	public void close() throws SQLException {
		_connection.close();
	}

	@Override
	public void commit() throws SQLException {
		_connection.commit();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
		throws SQLException {

		return _connection.createArrayOf(typeName, elements);
	}

	@Override
	public Blob createBlob() throws SQLException {
		return _connection.createBlob();
	}

	@Override
	public Clob createClob() throws SQLException {
		return _connection.createClob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return _connection.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return _connection.createSQLXML();
	}

	@Override
	public Statement createStatement() throws SQLException {
		return _connection.createStatement();
	}

	@Override
	public Statement createStatement(
			int resultSetType, int resultSetConcurrency)
		throws SQLException {

		return _connection.createStatement(resultSetType, resultSetConcurrency);
	}

	@Override
	public Statement createStatement(
			int resultSetType, int resultSetConcurrency,
			int resultSetHoldability)
		throws SQLException {

		return _connection.createStatement(
			resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
		throws SQLException {

		return _connection.createStruct(typeName, attributes);
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return _connection.getAutoCommit();
	}

	@Override
	public String getCatalog() throws SQLException {
		return _connection.getCatalog();
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return _connection.getClientInfo();
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return _connection.getClientInfo(name);
	}

	@Override
	public int getHoldability() throws SQLException {
		return _connection.getHoldability();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return _connection.getMetaData();
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return _connection.getNetworkTimeout();
	}

	@Override
	public String getSchema() throws SQLException {
		return _connection.getSchema();
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return _connection.getTransactionIsolation();
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return _connection.getTypeMap();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return _connection.getWarnings();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return _connection.isClosed();
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return _connection.isReadOnly();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return _connection.isValid(timeout);
	}

	@Override
	public boolean isWrapperFor(Class<?> clazz) throws SQLException {

		// JDK 6

		return DataSource.class.equals(clazz);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return _connection.nativeSQL(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return _connection.prepareCall(sql);
	}

	@Override
	public CallableStatement prepareCall(
			String sql, int resultSetType, int resultSetConcurrency)
		throws SQLException {

		return _connection.prepareCall(
			sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(
			String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability)
		throws SQLException {

		return _connection.prepareCall(
			sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return _connection.prepareStatement(sql);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
		throws SQLException {

		return _connection.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(
			String sql, int resultSetType, int resultSetConcurrency)
		throws SQLException {

		return _connection.prepareStatement(
			sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(
			String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability)
		throws SQLException {

		return _connection.prepareStatement(
			sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
		throws SQLException {

		return _connection.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
		throws SQLException {

		return _connection.prepareStatement(sql, columnNames);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		_connection.releaseSavepoint(savepoint);
	}

	@Override
	public void rollback() throws SQLException {
		_connection.rollback();
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		_connection.rollback(savepoint);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		_connection.setAutoCommit(autoCommit);
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		_connection.setCatalog(catalog);
	}

	@Override
	public void setClientInfo(Properties properties)
		throws SQLClientInfoException {

		_connection.setClientInfo(properties);
	}

	@Override
	public void setClientInfo(String name, String value)
		throws SQLClientInfoException {

		_connection.setClientInfo(name, value);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		_connection.setHoldability(holdability);
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds)
		throws SQLException {

		_connection.setNetworkTimeout(executor, milliseconds);
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		_connection.setReadOnly(readOnly);
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return _connection.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return _connection.setSavepoint(name);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		_connection.setSchema(schema);
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		_connection.setTransactionIsolation(level);
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		_connection.setTypeMap(map);
	}

	@Override
	public <T> T unwrap(Class<T> clazz) throws SQLException {

		// JDK 6

		if (!Connection.class.equals(clazz)) {
			throw new SQLException("Invalid class " + clazz);
		}

		return (T)this;
	}

	private volatile Connection _connection;

}