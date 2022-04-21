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

package com.liferay.portal.osgi.web.portlet.container.upload.servlet.request.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.servlet.ServletInputStreamAdapter;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.osgi.web.portlet.container.test.util.PortletContainerTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upload.LiferayInputStream;
import com.liferay.portal.upload.LiferayServletRequest;
import com.liferay.portal.upload.UploadServletRequestImpl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@RunWith(Arquillian.class)
public class UploadServletRequestWhenGettingInputStreamTest {

	@ClassRule
	@Rule
	public static final TestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_fileNameParameter = RandomTestUtil.randomString();
	}

	@Test
	public void testShouldNotReturnLiferayInputStream() throws Exception {
		LiferayServletRequest liferayServletRequest =
			PortletContainerTestUtil.getMultipartRequest(
				_fileNameParameter, _BYTES);

		UploadServletRequestImpl uploadServletRequestImpl =
			new UploadServletRequestImpl(
				(HttpServletRequest)liferayServletRequest.getRequest());

		ServletInputStream servletInputStream =
			uploadServletRequestImpl.getInputStream();

		Assert.assertFalse(servletInputStream instanceof LiferayInputStream);

		uploadServletRequestImpl = new UploadServletRequestImpl(
			(HttpServletRequest)liferayServletRequest.getRequest(),
			new HashMap<String, FileItem[]>(),
			new HashMap<String, List<String>>());

		servletInputStream = uploadServletRequestImpl.getInputStream();

		Assert.assertFalse(servletInputStream instanceof LiferayInputStream);
	}

	@Test
	public void testShouldReturnServletInputStreamAdapter() throws Exception {
		LiferayServletRequest liferayServletRequest =
			PortletContainerTestUtil.getMultipartRequest(
				_fileNameParameter, _BYTES);

		UploadServletRequestImpl uploadServletRequestImpl =
			new UploadServletRequestImpl(
				(HttpServletRequest)liferayServletRequest.getRequest());

		ServletInputStream servletInputStream =
			uploadServletRequestImpl.getInputStream();

		Assert.assertTrue(
			servletInputStream instanceof ServletInputStreamAdapter);
	}

	private static final byte[] _BYTES =
		"Enterprise. Open Source. For Life.".getBytes();

	private static String _fileNameParameter;

}