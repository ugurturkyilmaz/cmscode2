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

package com.liferay.portal.security.auth.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.AuthFailure;
import com.liferay.portal.kernel.security.auth.Authenticator;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.auth.AuthPipeline;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Philip Jones
 */
@RunWith(Arquillian.class)
public class AuthPipelineTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		Bundle bundle = FrameworkUtil.getBundle(AuthPipelineTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		_authFailureServiceRegistration = bundleContext.registerService(
			AuthFailure.class,
			(AuthFailure)ProxyUtil.newProxyInstance(
				AuthFailure.class.getClassLoader(),
				new Class<?>[] {AuthFailure.class},
				(proxy, method, args) -> {
					_calledAuthFailure = true;

					return null;
				}),
			HashMapDictionaryBuilder.<String, Object>put(
				"key", new String[] {"auth.failure", "auth.max.failures"}
			).put(
				"service.ranking", Integer.MAX_VALUE
			).build());

		_authenticatorServiceRegistration = bundleContext.registerService(
			Authenticator.class,
			(Authenticator)ProxyUtil.newProxyInstance(
				Authenticator.class.getClassLoader(),
				new Class<?>[] {Authenticator.class},
				(proxy, method, args) -> {
					_calledAuthenticator = true;

					return Authenticator.SUCCESS;
				}),
			HashMapDictionaryBuilder.<String, Object>put(
				"key", "auth.pipeline.pre"
			).put(
				"service.ranking", Integer.MAX_VALUE
			).build());
	}

	@AfterClass
	public static void tearDownClass() {
		_authFailureServiceRegistration.unregister();
		_authenticatorServiceRegistration.unregister();
	}

	@Before
	public void setUp() {
		_calledAuthenticator = false;
		_calledAuthFailure = false;
	}

	@Test
	public void testAuthenticateByEmailAddress() throws AuthException {
		AuthPipeline.authenticateByEmailAddress(
			"auth.pipeline.pre", 0, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), null, null);

		Assert.assertTrue(_calledAuthenticator);
	}

	@Test
	public void testAuthenticateByScreenName() throws AuthException {
		AuthPipeline.authenticateByScreenName(
			"auth.pipeline.pre", 0, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), null, null);

		Assert.assertTrue(_calledAuthenticator);
	}

	@Test
	public void testAuthenticateByUserId() throws AuthException {
		AuthPipeline.authenticateByUserId(
			"auth.pipeline.pre", 0, RandomTestUtil.randomLong(),
			RandomTestUtil.randomString(), null, null);

		Assert.assertTrue(_calledAuthenticator);
	}

	@Test
	public void testOnFailureByScreenName() {
		try {
			AuthPipeline.onFailureByScreenName(
				"auth.failure", 0, RandomTestUtil.randomString(), null, null);
		}
		catch (AuthException authException) {
			if (_log.isDebugEnabled()) {
				_log.debug(authException);
			}
		}

		Assert.assertTrue(_calledAuthFailure);
	}

	@Test
	public void testOnFailureByUserId() {
		try {
			AuthPipeline.onFailureByUserId(
				"auth.failure", 0, RandomTestUtil.randomLong(), null, null);
		}
		catch (AuthException authException) {
			if (_log.isDebugEnabled()) {
				_log.debug(authException);
			}
		}

		Assert.assertTrue(_calledAuthFailure);
	}

	@Test
	public void testOnMaxFailuresByEmailAddress() {
		try {
			AuthPipeline.onMaxFailuresByEmailAddress(
				"auth.max.failures", 0, RandomTestUtil.randomString(), null,
				null);
		}
		catch (AuthException authException) {
			if (_log.isDebugEnabled()) {
				_log.debug(authException);
			}
		}

		Assert.assertTrue(_calledAuthFailure);
	}

	@Test
	public void testOnMaxFailuresByScreenName() {
		try {
			AuthPipeline.onMaxFailuresByScreenName(
				"auth.max.failures", 0, RandomTestUtil.randomString(), null,
				null);
		}
		catch (AuthException authException) {
			if (_log.isDebugEnabled()) {
				_log.debug(authException);
			}
		}

		Assert.assertTrue(_calledAuthFailure);
	}

	@Test
	public void testOnMaxFailuresByUserId() {
		try {
			AuthPipeline.onMaxFailuresByUserId(
				"auth.max.failures", 0, RandomTestUtil.randomLong(), null,
				null);
		}
		catch (AuthException authException) {
			if (_log.isDebugEnabled()) {
				_log.debug(authException);
			}
		}

		Assert.assertTrue(_calledAuthFailure);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AuthPipelineTest.class);

	private static ServiceRegistration<Authenticator>
		_authenticatorServiceRegistration;
	private static ServiceRegistration<AuthFailure>
		_authFailureServiceRegistration;
	private static boolean _calledAuthenticator;
	private static boolean _calledAuthFailure;

}