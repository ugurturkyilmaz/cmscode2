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

package com.liferay.portal.remote.cors.client.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import javax.ws.rs.HttpMethod;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Marta Medio
 */
@RunWith(Arquillian.class)
public class AnnotationCORSClientTest extends BaseCORSClientTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() {
		registerJaxRsApplication(
			new CORSTestApplication(), "test",
			HashMapDictionaryBuilder.<String, Object>put(
				"liferay.cors.annotation", true
			).build());
	}

	@Test
	public void testApplicationAnnotationCORSForGuestUser() throws Exception {
		assertJaxRSUrl("/test/cors-app", HttpMethod.OPTIONS, true, true);
		assertJaxRSUrl("/test/cors-app", HttpMethod.GET, true, false);
	}

	@Test
	public void testApplicationAnnotationCORSWithoutOAuth2() throws Exception {
		assertJaxRSUrl("/test/cors-app", HttpMethod.OPTIONS, true, true);
		assertJaxRSUrl("/test/cors-app", HttpMethod.GET, true, false);
	}

}