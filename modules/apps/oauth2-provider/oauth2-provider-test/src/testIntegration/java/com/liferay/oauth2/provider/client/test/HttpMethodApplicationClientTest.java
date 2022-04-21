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

package com.liferay.oauth2.provider.client.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.oauth2.provider.internal.test.TestApplication;
import com.liferay.oauth2.provider.internal.test.TestHeadHandlingApplication;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Arrays;
import java.util.Collections;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.BundleActivator;

/**
 * @author Carlos Sierra Andrés
 */
@RunWith(Arquillian.class)
public class HttpMethodApplicationClientTest extends BaseClientTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void test() throws Exception {
		WebTarget webTarget = getWebTarget("/methods");

		Invocation.Builder builder = authorize(
			webTarget.request(), getToken("oauthTestApplicationAfter"));

		Assert.assertEquals("get", builder.get(String.class));

		Response response = builder.post(
			Entity.entity("post", MediaType.TEXT_PLAIN_TYPE));

		Assert.assertEquals("post", response.readEntity(String.class));

		builder = authorize(
			webTarget.request(), getToken("oauthTestApplicationBefore"));

		try (LogCapture logCapture = LoggerTestUtil.configureLog4JLogger(
				"portal_web.docroot.errors.code_jsp", LoggerTestUtil.WARN)) {

			response = builder.get();

			Assert.assertEquals(403, response.getStatus());

			builder = authorize(
				webTarget.request(), getToken("oauthTestApplicationWrong"));

			response = builder.get();

			Assert.assertEquals(403, response.getStatus());
		}
	}

	@Test
	public void testIgnoredMethods() throws Exception {
		WebTarget webTarget = getWebTarget("/methods");

		Invocation.Builder builder = authorize(
			webTarget.request(), getToken("oauthTestApplicationAfter"));

		Response response = builder.head();

		Assert.assertEquals(200, response.getStatus());

		webTarget = getWebTarget("/methods-with-ignore-missing-scopes-empty");

		builder = authorize(
			webTarget.request(), getToken("oauthTestApplicationAfter"));

		response = builder.head();

		Assert.assertEquals(403, response.getStatus());

		webTarget = getWebTarget("/methods-with-head");

		builder = authorize(
			webTarget.request(), getToken("oauthTestApplicationAfter"));

		response = builder.head();

		Assert.assertEquals(403, response.getStatus());

		builder = authorize(
			webTarget.request(), getToken("oauthTestApplicationWithHead"));

		response = builder.head();

		Assert.assertEquals(200, response.getStatus());

		builder = authorize(
			webTarget.request(), getToken("oauthTestApplicationWithHead"));

		response = builder.method("CUSTOM");

		Assert.assertEquals(403, response.getStatus());
	}

	public static class MethodApplicationTestPreparatorBundleActivator
		extends BaseTestPreparatorBundleActivator {

		@Override
		protected void prepareTest() throws Exception {
			long defaultCompanyId = PortalUtil.getDefaultCompanyId();

			User user = UserTestUtil.getAdminUser(defaultCompanyId);

			createOAuth2Application(
				defaultCompanyId, user, "oauthTestApplicationBefore",
				Arrays.asList("GET", "POST"));

			registerJaxRsApplication(new TestApplication(), "methods", null);

			registerJaxRsApplication(
				new TestHeadHandlingApplication(), "methods-with-head", null);

			registerJaxRsApplication(
				new TestApplication(),
				"methods-with-ignore-missing-scopes-empty",
				HashMapDictionaryBuilder.<String, Object>put(
					"ignore.missing.scopes", ""
				).build());

			createOAuth2Application(
				defaultCompanyId, user, "oauthTestApplicationAfter",
				Arrays.asList("GET", "POST"));

			createOAuth2Application(
				defaultCompanyId, user, "oauthTestApplicationWithHead",
				Arrays.asList("HEAD"));

			createOAuth2Application(
				defaultCompanyId, user, "oauthTestApplicationWrong",
				Collections.singletonList("everything"));
		}

	}

	@Override
	protected BundleActivator getBundleActivator() {
		return new MethodApplicationTestPreparatorBundleActivator();
	}

}