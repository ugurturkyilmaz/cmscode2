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

package com.liferay.arquillian.extension.junit.bridge.server;

import com.liferay.arquillian.extension.junit.bridge.constants.Headers;
import com.liferay.arquillian.extension.junit.bridge.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Shuyang Zhou
 */
public class TestBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Bundle testBundle = bundleContext.getBundle();

		URL url = testBundle.getResource("/META-INF/MANIFEST.MF");

		Manifest manifest = new Manifest();

		try (InputStream inputStream = url.openStream()) {
			manifest.read(inputStream);
		}
		catch (IOException ioException) {
			throw new IllegalArgumentException(
				"Unable to read test manifest", ioException);
		}

		Attributes attributes = manifest.getMainAttributes();

		String reportServerHostName = attributes.getValue(
			Headers.TEST_BRIDGE_REPORT_SERVER_HOST_NAME);
		int reportServerPort = Integer.parseInt(
			attributes.getValue(Headers.TEST_BRIDGE_REPORT_SERVER_PORT));

		Map<String, List<String>> filteredMethodNamesMap = new HashMap<>();

		for (String filteredMethodNamesEntry :
				StringUtil.split(
					attributes.getValue(
						Headers.TEST_BRIDGE_FILTERED_METHOD_NAMES),
					';')) {

			int index = filteredMethodNamesEntry.indexOf(':');

			filteredMethodNamesMap.put(
				filteredMethodNamesEntry.substring(0, index),
				StringUtil.split(
					filteredMethodNamesEntry.substring(index + 1), ','));
		}

		long passCode = Long.parseLong(
			attributes.getValue(Headers.TEST_BRIDGE_PASS_CODE));

		Bundle systemBundle = bundleContext.getBundle(0);

		BundleContext systemBundleContext = systemBundle.getBundleContext();

		systemBundleContext.addBundleListener(
			new TestBundleListener(
				systemBundleContext, testBundle, filteredMethodNamesMap,
				reportServerHostName, reportServerPort, passCode));
	}

	@Override
	public void stop(BundleContext bundleContext) {
	}

}