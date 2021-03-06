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

package com.liferay.portal.spring.extender.internal.loader;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.configuration.ServiceComponentConfiguration;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import org.osgi.framework.Bundle;

/**
 * @author Miguel Pastor
 */
public class ModuleResourceLoader implements ServiceComponentConfiguration {

	public ModuleResourceLoader(Bundle bundle) {
		_bundle = bundle;
	}

	@Override
	public InputStream getHibernateInputStream() {
		return _getInputStream("/META-INF/module-hbm.xml");
	}

	@Override
	public InputStream getModelHintsExtInputStream() {
		return _getInputStream("/META-INF/portlet-model-hints-ext.xml");
	}

	@Override
	public InputStream getModelHintsInputStream() {
		return _getInputStream("/META-INF/portlet-model-hints.xml");
	}

	@Override
	public String getServletContextName() {
		return _bundle.getSymbolicName();
	}

	@Override
	public InputStream getSQLIndexesInputStream() {
		return _getInputStream("/META-INF/sql/indexes.sql");
	}

	@Override
	public InputStream getSQLSequencesInputStream() {
		return _getInputStream("/META-INF/sql/sequences.sql");
	}

	@Override
	public InputStream getSQLTablesInputStream() {
		return _getInputStream("/META-INF/sql/tables.sql");
	}

	private InputStream _getInputStream(String location) {
		URL url = _bundle.getResource(location);

		if (url == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to find " + location);
			}

			return null;
		}

		InputStream inputStream = null;

		try {
			inputStream = url.openStream();
		}
		catch (IOException ioException) {
			_log.error("Unable to read " + location, ioException);
		}

		return inputStream;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ModuleResourceLoader.class);

	private final Bundle _bundle;

}