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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.BundleActivator;

/**
 * @author Marta Medio
 */
@RunWith(Arquillian.class)
public class CORSApplicationClientTest extends BaseClientTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testCORSObtainingToken() throws Exception {
		Invocation.Builder tokenInvocationBuilder = getTokenInvocationBuilder(
			null);

		MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();

		formData.add("client_id", "oauthTestApplicationRO");
		formData.add("client_secret", "oauthTestApplicationSecret");
		formData.add("grant_type", "password");
		formData.add("password", "test");
		formData.add("username", "test@liferay.com");

		tokenInvocationBuilder.header("Origin", _TEST_CORS_URI);

		Response response = tokenInvocationBuilder.post(Entity.form(formData));

		String corsHeaderString = response.getHeaderString(
			"Access-Control-Allow-Origin");

		Assert.assertEquals(_TEST_CORS_URI, corsHeaderString);
	}

	@Test
	public void testCORSRequest() throws Exception {
		WebTarget webTarget = getJsonWebTarget("user", "get-current-user");

		String tokenString = getToken(
			"oauthTestApplicationRO", null,
			getResourceOwnerPasswordBiFunction("test@liferay.com", "test"),
			this::parseTokenString);

		Invocation.Builder invocationBuilder = authorize(
			webTarget.request(), tokenString);

		invocationBuilder.header("Origin", _TEST_CORS_URI);

		Response response = invocationBuilder.get();

		String corsHeaderString = response.getHeaderString(
			"Access-Control-Allow-Origin");

		Assert.assertEquals(_TEST_CORS_URI, corsHeaderString);
	}

	@Test
	public void testCORSRequestInvalidToken() throws Exception {
		WebTarget webTarget = getJsonWebTarget("user", "get-current-user");

		String tokenString = "wrong-token";

		Invocation.Builder invocationBuilder = authorize(
			webTarget.request(), tokenString);

		invocationBuilder.header("Origin", _TEST_CORS_URI);

		Response response = invocationBuilder.get();

		String corsHeaderString = response.getHeaderString(
			"Access-Control-Allow-Origin");

		Assert.assertEquals(_TEST_CORS_URI, corsHeaderString);

		Assert.assertEquals(403, response.getStatus());
	}

	@Test
	public void testPreflightCORSRequest() throws Exception {
		WebTarget webTarget = getJsonWebTarget("user", "get-current-user");

		String tokenString = getToken(
			"oauthTestApplicationRO", null,
			getResourceOwnerPasswordBiFunction("test@liferay.com", "test"),
			this::parseTokenString);

		Invocation.Builder invocationBuilder = authorize(
			webTarget.request(), tokenString);

		invocationBuilder.header(
			"Access-Control-Request-Method", HttpMethod.OPTIONS);
		invocationBuilder.header("Origin", _TEST_CORS_URI);

		Response response = invocationBuilder.options();

		String corsHeaderString = response.getHeaderString(
			"Access-Control-Allow-Origin");

		Assert.assertEquals(_TEST_CORS_URI, corsHeaderString);
	}

	public static class CORSApplicationTestPreparatorBundleActivator
		extends BaseTestPreparatorBundleActivator {

		@Override
		protected void prepareTest() throws Exception {
			long defaultCompanyId = PortalUtil.getDefaultCompanyId();

			User user = UserTestUtil.getAdminUser(defaultCompanyId);

			createOAuth2Application(
				defaultCompanyId, user, "oauthTestApplicationRO",
				Collections.singletonList("everything.read"));
		}

	}

	@Override
	protected BundleActivator getBundleActivator() {
		return new CORSApplicationTestPreparatorBundleActivator();
	}

	private static final String _TEST_CORS_URI = "http://test-cors.com";

}