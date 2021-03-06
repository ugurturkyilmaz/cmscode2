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

package com.liferay.portal.lpkg.deployer.override.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

/**
 * @author Matthew Tambara
 */
@RunWith(Arquillian.class)
public class LPKGRevertOverrideVerifyTest {

	@Test
	public void testRevertOverriddenLPKGs() throws Exception {
		Bundle testBundle = FrameworkUtil.getBundle(
			LPKGRevertOverrideVerifyTest.class);

		BundleContext bundleContext = testBundle.getBundleContext();

		Properties properties = new Properties();

		Path path = Paths.get(PropsValues.LIFERAY_HOME, "/overrides");

		Assert.assertTrue(Files.exists(path));

		properties.load(Files.newBufferedReader(path));

		Map<String, String> jars = new HashMap<>();

		List<String> statics = new ArrayList<>();

		List<String> wars = new ArrayList<>();

		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String symbolicName = (String)entry.getKey();

			if (symbolicName.startsWith("static.")) {
				statics.add(symbolicName.substring(7));
			}
			else if (symbolicName.startsWith("war.")) {
				wars.add(symbolicName.substring(4));
			}
			else {
				jars.put((String)entry.getKey(), (String)entry.getValue());
			}
		}

		for (Bundle bundle : bundleContext.getBundles()) {
			String symbolicName = bundle.getSymbolicName();

			String versionString = jars.remove(symbolicName);

			if (Validator.isNotNull(versionString)) {
				Version version = new Version(versionString);

				Assert.assertEquals(
					"JAR not successfully reverted: " + symbolicName,
					new Version(
						version.getMajor(), version.getMinor(),
						version.getMicro() - 1, version.getQualifier()),
					bundle.getVersion());
			}
			else if (statics.remove(symbolicName)) {
				String location = bundle.getLocation();

				Assert.assertTrue(
					"Static JAR not successfully reverted: " + symbolicName,
					location.contains("protocol=lpkg"));
			}
			else {
				String location = bundle.getLocation();

				if (location.contains("protocol=lpkg")) {
					wars.remove(symbolicName);
				}
			}
		}

		List<Map.Entry<String, String>> leftoverEntries = new ArrayList<>();

		leftoverEntries.addAll(jars.entrySet());

		for (Map.Entry<String, String> entry : leftoverEntries) {
			if (entry.getValue() == null) {
				leftoverEntries.remove(entry);
			}
		}

		Collections.sort(
			leftoverEntries,
			new Comparator<Map.Entry>() {

				@Override
				public int compare(Map.Entry entry1, Map.Entry entry2) {
					String entrySymbolicName = (String)entry1.getKey();

					return entrySymbolicName.compareTo((String)entry2.getKey());
				}

			});

		Assert.assertTrue(
			"JARs not reverted: " + jars.entrySet(), jars.isEmpty());
		Assert.assertTrue(
			"Static JARs not reverted: " + statics, statics.isEmpty());
		Assert.assertTrue("WARs not reverted: " + wars, wars.isEmpty());

		Files.delete(Paths.get(PropsValues.LIFERAY_HOME, "/overrides"));
	}

}