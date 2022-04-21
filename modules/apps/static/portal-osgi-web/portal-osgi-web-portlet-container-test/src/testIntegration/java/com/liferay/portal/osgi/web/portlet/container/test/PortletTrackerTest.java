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

package com.liferay.portal.osgi.web.portlet.container.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.petra.io.StreamUtil;
import com.liferay.petra.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.petra.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletCategory;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.osgi.web.portlet.container.test.util.PortletContainerTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.WebAppPool;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Daniel Sanz
 */
@RunWith(Arquillian.class)
public class PortletTrackerTest extends BasePortletContainerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testLoadGetPortletsByCompany() throws Exception {
		Company company1 = CompanyTestUtil.addCompany();
		Company company2 = CompanyTestUtil.addCompany();

		PortalInstances.reload(_servletContext);

		try {
			setUpPortlet(
				_internalClassTestPortlet,
				HashMapDictionaryBuilder.<String, Object>put(
					"com.liferay.portlet.company", company1.getCompanyId()
				).put(
					"com.liferay.portlet.display-category", "company-scope"
				).build(),
				"companyPortlet", false);

			Map<String, Portlet> portlets =
				_portletLocalService.loadGetPortletsMap(
					company1.getCompanyId());

			Assert.assertTrue(
				portlets.toString(), portlets.containsKey("companyPortlet"));

			portlets = _portletLocalService.loadGetPortletsMap(
				company2.getCompanyId());

			Assert.assertFalse(
				portlets.toString(), portlets.containsKey("companyPortlet"));
		}
		finally {
			for (ServiceRegistration<?> serviceRegistration :
					serviceRegistrations) {

				serviceRegistration.unregister();
			}

			serviceRegistrations.clear();

			_companyLocalService.deleteCompany(company2);

			_companyLocalService.deleteCompany(company1);

			PortalInstances.reload(_servletContext);
		}
	}

	@Test
	public void testLoadGetPortletsByCompanyWithReload() throws Exception {
		List<Company> companies = new ArrayList<>();

		try {
			Company company1 = CompanyTestUtil.addCompany();

			companies.add(company1);

			PortalInstances.reload(_servletContext);

			setUpPortlet(
				_internalClassTestPortlet,
				HashMapDictionaryBuilder.<String, Object>put(
					"com.liferay.portlet.company", company1.getCompanyId()
				).put(
					"com.liferay.portlet.display-category", "company-scope"
				).build(),
				"companyPortlet", false);

			Company company2 = CompanyTestUtil.addCompany();

			companies.add(company2);

			PortalInstances.reload(_servletContext);

			Map<String, Portlet> portlets =
				_portletLocalService.loadGetPortletsMap(
					company1.getCompanyId());

			Assert.assertTrue(
				portlets.toString(), portlets.containsKey("companyPortlet"));

			portlets = _portletLocalService.loadGetPortletsMap(
				company2.getCompanyId());

			Assert.assertFalse(
				portlets.toString(), portlets.containsKey("companyPortlet"));
		}
		finally {
			for (ServiceRegistration<?> serviceRegistration :
					serviceRegistrations) {

				serviceRegistration.unregister();
			}

			serviceRegistrations.clear();

			_companyLocalService.forEachCompany(
				company -> _companyLocalService.deleteCompany(company),
				companies);

			PortalInstances.reload(_servletContext);
		}
	}

	@Test
	public void testPortletTrackerBundleStopCleanup() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(PortletTrackerTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		Bundle testBundle = bundleContext.installBundle(
			"test-bundle-stop-cleanup-bundle",
			_createBundle("test-bundle-stop-cleanup-bundle"));

		try {
			testBundle.start();

			Portlet testBundleTestPortlet =
				PortletLocalServiceUtil.getPortletById(
					TestBundleActivator.TEST_BUNDLE_TEST_PORTLET_NAME);

			Assert.assertNotNull(testBundleTestPortlet);
		}
		finally {
			testBundle.uninstall();
		}
	}

	@Test
	public void testPortletTrackerRegistrationCompanyScope() throws Exception {
		Company company1 = CompanyTestUtil.addCompany();
		Company company2 = CompanyTestUtil.addCompany();

		PortalInstances.reload(_servletContext);

		try {
			setUpPortlet(
				_internalClassTestPortlet,
				HashMapDictionaryBuilder.<String, Object>put(
					"com.liferay.portlet.company", company1.getCompanyId()
				).put(
					"com.liferay.portlet.display-category", "company-scope"
				).build(),
				"companyScopePortlet", false);

			PortletCategory portletCategory1 = (PortletCategory)WebAppPool.get(
				company1.getCompanyId(), WebKeys.PORTLET_CATEGORY);

			PortletCategory companyScopePortletCategory =
				portletCategory1.getCategory("company-scope");

			Set<String> portletIds =
				companyScopePortletCategory.getPortletIds();

			Assert.assertTrue(
				portletIds.toString(),
				portletIds.contains("companyScopePortlet"));

			PortletCategory portletCategory2 = (PortletCategory)WebAppPool.get(
				company2.getCompanyId(), WebKeys.PORTLET_CATEGORY);

			Assert.assertNull(portletCategory2.getCategory("company-scope"));
		}
		finally {
			for (ServiceRegistration<?> serviceRegistration :
					serviceRegistrations) {

				serviceRegistration.unregister();
			}

			serviceRegistrations.clear();

			_companyLocalService.deleteCompany(company2);

			_companyLocalService.deleteCompany(company1);

			PortalInstances.reload(_servletContext);
		}
	}

	@Test
	public void testPortletTrackerRegistrationUsingPortletClassName()
		throws Exception {

		_testPortletTrackerRegistration(
			"com_liferay_portal_osgi_web_portlet_container_test_" +
				"PortletTrackerTest_InternalClassTestPortlet");
	}

	@Test
	public void testPortletTrackerRegistrationUsingPortletNameWithDollar()
		throws Exception {

		_testPortletTrackerRegistration("dollar$portlet", "dollar_portlet");
	}

	@Test
	public void testPortletTrackerRegistrationUsingPortletNameWithDot()
		throws Exception {

		_testPortletTrackerRegistration("dot.portlet", "dot_portlet");
	}

	@Test
	public void testPortletTrackerRegistrationUsingPortletNameWithHyphen()
		throws Exception {

		_testPortletTrackerRegistration("hyphen-portlet", "hyphenportlet");
	}

	@Test
	public void testPortletTrackerRegistrationUsingPortletNameWithSpace()
		throws Exception {

		_testPortletTrackerRegistration("space portlet", "spaceportlet");
	}

	@Test
	public void testPortletTrackerRegistrationUsingSimpleName()
		throws Exception {

		_testPortletTrackerRegistration("simplename", "simplename");
	}

	public static class TestBundleActivator implements BundleActivator {

		public static final String TEST_BUNDLE_TEST_PORTLET_NAME =
			"TEST_BUNDLE_TEST_PORTLET_NAME";

		@Override
		public void start(BundleContext bundleContext) throws Exception {
			bundleContext.registerService(
				javax.portlet.Portlet.class,
				(javax.portlet.Portlet)ProxyUtil.newProxyInstance(
					javax.portlet.Portlet.class.getClassLoader(),
					new Class<?>[] {javax.portlet.Portlet.class},
					(proxy, method, args) -> method.getDefaultValue()),
				MapUtil.singletonDictionary(
					"javax.portlet.name", TEST_BUNDLE_TEST_PORTLET_NAME));
		}

		@Override
		public void stop(BundleContext bundleContext) {
		}

	}

	private InputStream _createBundle(String bundleSymbolicName)
		throws Exception {

		try (UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
				new UnsyncByteArrayOutputStream()) {

			try (JarOutputStream jarOutputStream = new JarOutputStream(
					unsyncByteArrayOutputStream)) {

				_writeManifest(
					bundleSymbolicName, jarOutputStream,
					TestBundleActivator.class);

				_writeClasses(jarOutputStream, TestBundleActivator.class);
			}

			return new UnsyncByteArrayInputStream(
				unsyncByteArrayOutputStream.unsafeGetByteArray(), 0,
				unsyncByteArrayOutputStream.size());
		}
	}

	private void _testPortletIsAvailable(String expectedPortletId)
		throws Exception {

		Portlet registeredLiferayPortlet =
			PortletLocalServiceUtil.getPortletById(expectedPortletId);

		Assert.assertNotNull(registeredLiferayPortlet);

		LayoutTestUtil.addPortletToLayout(
			TestPropsValues.getUserId(), layout, expectedPortletId, "column-1",
			new HashMap<String, String[]>());

		PortletURL portletURL = PortletURLFactoryUtil.create(
			PortletContainerTestUtil.getHttpServletRequest(group, layout),
			expectedPortletId, layout.getPlid(), PortletRequest.RENDER_PHASE);

		PortletContainerTestUtil.Response response =
			PortletContainerTestUtil.request(portletURL.toString());

		Assert.assertEquals(200, response.getCode());

		Assert.assertTrue(_internalClassTestPortlet.isCalledRender());

		_internalClassTestPortlet.reset();
	}

	private void _testPortletTrackerRegistration(String expectedPortletId)
		throws Exception {

		// Register portlet using class name

		registerService(
			javax.portlet.Portlet.class, _internalClassTestPortlet,
			new HashMapDictionary<String, Object>());

		_testPortletIsAvailable(expectedPortletId);
	}

	private void _testPortletTrackerRegistration(
			String givenPortletId, String expectedPortletId)
		throws Exception {

		// Register portlet using actual portlet ID

		setUpPortlet(
			_internalClassTestPortlet, new HashMapDictionary<String, Object>(),
			givenPortletId, false);

		_testPortletIsAvailable(expectedPortletId);
	}

	private void _writeClasses(
			JarOutputStream jarOutputStream, Class<?>... classes)
		throws IOException {

		for (Class<?> clazz : classes) {
			String className = clazz.getName();

			String path = StringUtil.replace(
				className, CharPool.PERIOD, CharPool.SLASH);

			String resourcePath = path.concat(".class");

			jarOutputStream.putNextEntry(new ZipEntry(resourcePath));

			ClassLoader classLoader = clazz.getClassLoader();

			StreamUtil.transfer(
				classLoader.getResourceAsStream(resourcePath), jarOutputStream,
				false);

			jarOutputStream.closeEntry();
		}
	}

	private void _writeManifest(
			String bundleSymbolicName, JarOutputStream jarOutputStream,
			Class<?> bundleActivatorClass)
		throws IOException {

		Manifest manifest = new Manifest();

		Attributes attributes = manifest.getMainAttributes();

		attributes.putValue(
			Constants.BUNDLE_ACTIVATOR, bundleActivatorClass.getName());
		attributes.putValue(Constants.BUNDLE_MANIFESTVERSION, "2");
		attributes.putValue(Constants.BUNDLE_SYMBOLICNAME, bundleSymbolicName);
		attributes.putValue(Constants.BUNDLE_VERSION, "1.0.0");
		attributes.putValue(
			Constants.IMPORT_PACKAGE,
			"com.liferay.portal.kernel.util,javax.portlet,org.osgi.framework");

		attributes.putValue("Manifest-Version", "2");

		jarOutputStream.putNextEntry(new ZipEntry(JarFile.MANIFEST_NAME));

		manifest.write(jarOutputStream);

		jarOutputStream.closeEntry();
	}

	@Inject
	private CompanyLocalService _companyLocalService;

	private final InternalClassTestPortlet _internalClassTestPortlet =
		new InternalClassTestPortlet();

	@Inject
	private PortletLocalService _portletLocalService;

	@Inject(filter = "original.bean=true")
	private ServletContext _servletContext;

	private class InternalClassTestPortlet extends TestPortlet {

		@Override
		public void render(
				RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

			super.render(renderRequest, renderResponse);

			PrintWriter printWriter = renderResponse.getWriter();

			Class<?> clazz = getClass();

			printWriter.write(clazz.getName());

			printWriter.write(getPortletName());
		}

	}

}