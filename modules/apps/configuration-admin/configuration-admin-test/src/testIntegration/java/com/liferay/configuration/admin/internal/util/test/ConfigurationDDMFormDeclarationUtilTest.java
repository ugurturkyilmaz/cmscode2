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

package com.liferay.configuration.admin.internal.util.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.configuration.admin.definition.ConfigurationDDMFormDeclaration;
import com.liferay.osgi.util.service.OSGiServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.lang.reflect.Method;

import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Pei-Jung Lan
 */
@RunWith(Arquillian.class)
public class ConfigurationDDMFormDeclarationUtilTest {

	@Before
	public void setUp() throws Exception {
		_bundle = FrameworkUtil.getBundle(
			ConfigurationDDMFormDeclarationUtilTest.class);

		_bundleContext = _bundle.getBundleContext();

		_configuration = OSGiServiceUtil.callService(
			_bundleContext, ConfigurationAdmin.class,
			configurationAdmin -> configurationAdmin.createFactoryConfiguration(
				"test.pid", StringPool.QUESTION));

		ConfigurationDDMFormDeclaration configurationDDMFormDeclaration =
			() -> TestConfigurationForm.class;

		_serviceRegistration = _registerConfigurationDDMFormDeclaration(
			configurationDDMFormDeclaration, _configuration.getPid());

		Bundle bundle = null;

		for (Bundle installedBundle : _bundleContext.getBundles()) {
			if (Objects.equals(
					installedBundle.getSymbolicName(),
					"com.liferay.configuration.admin.web")) {

				bundle = installedBundle;

				break;
			}
		}

		if (bundle == null) {
			throw new IllegalStateException(
				"com.liferay.configuration.admin.web bundle not found");
		}

		Class<?> clazz = bundle.loadClass(
			"com.liferay.configuration.admin.web.internal.util." +
				"ConfigurationDDMFormDeclarationUtil");

		_method = clazz.getDeclaredMethod(
			"getConfigurationDDMFormClass", String.class);
	}

	@After
	public void tearDown() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	@Test
	public void testGetConfigurationFormClassFromPid() throws Exception {
		Assert.assertEquals(
			TestConfigurationForm.class,
			_method.invoke(null, _configuration.getPid()));
	}

	private ServiceRegistration<ConfigurationDDMFormDeclaration>
		_registerConfigurationDDMFormDeclaration(
			ConfigurationDDMFormDeclaration configurationDDMFormDeclaration,
			String configurationPid) {

		return _bundleContext.registerService(
			ConfigurationDDMFormDeclaration.class,
			configurationDDMFormDeclaration,
			HashMapDictionaryBuilder.<String, Object>put(
				"configurationPid", configurationPid
			).build());
	}

	private Bundle _bundle;
	private BundleContext _bundleContext;
	private Configuration _configuration;
	private Method _method;
	private ServiceRegistration<ConfigurationDDMFormDeclaration>
		_serviceRegistration;

	private class TestConfigurationForm {
	}

}