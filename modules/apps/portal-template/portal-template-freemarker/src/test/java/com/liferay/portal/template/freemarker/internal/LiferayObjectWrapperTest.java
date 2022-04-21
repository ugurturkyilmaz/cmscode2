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

package com.liferay.portal.template.freemarker.internal;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.SwappableSecurityManager;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.test.util.ReflectionUtilTestUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import freemarker.ext.beans.EnumerationModel;
import freemarker.ext.beans.ResourceBundleModel;
import freemarker.ext.beans.StringModel;
import freemarker.ext.dom.NodeModel;
import freemarker.ext.util.ModelFactory;

import freemarker.template.TemplateModel;
import freemarker.template.Version;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Xiangyue Cai
 */
public class LiferayObjectWrapperTest extends BaseObjectWrapperTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Test
	public void testConstructor() {
		Field cacheClassNamesField = ReflectionTestUtil.getAndSetFieldValue(
			LiferayObjectWrapper.class, "_cacheClassNamesField", null);

		try {
			new LiferayObjectWrapper();

			Assert.fail("NullPointerException was not thrown");
		}
		catch (Exception exception) {
			Assert.assertSame(NullPointerException.class, exception.getClass());
		}
		finally {
			ReflectionTestUtil.setFieldValue(
				LiferayObjectWrapper.class, "_cacheClassNamesField",
				cacheClassNamesField);
		}
	}

	@Test
	public void testHandleUnknownType() throws Exception {
		LiferayObjectWrapper liferayObjectWrapper = new LiferayObjectWrapper();

		// 1. Handle Enumeration

		Enumeration<String> enumeration = Collections.enumeration(
			Collections.singletonList("testElement"));

		assertTemplateModel(
			"testElement", enumerationModel -> enumerationModel.next(),
			EnumerationModel.class.cast(
				liferayObjectWrapper.handleUnknownType(enumeration)));

		_assertModelFactoryCache(
			"_ENUMERATION_MODEL_FACTORY", enumeration.getClass());

		// 2. Handle Node

		Node node = (Node)ProxyUtil.newProxyInstance(
			LiferayObjectWrapper.class.getClassLoader(),
			new Class<?>[] {Node.class, Element.class},
			(proxy, method, args) -> {
				String methodName = method.getName();

				if (methodName.equals("getNodeType")) {
					return Node.ELEMENT_NODE;
				}

				return null;
			});

		TemplateModel templateModel = liferayObjectWrapper.handleUnknownType(
			node);

		Assert.assertTrue(
			"org.w3c.dom.Node should be handled as NodeModel",
			templateModel instanceof NodeModel);

		NodeModel nodeModel = (NodeModel)templateModel;

		Assert.assertSame(node, nodeModel.getNode());
		Assert.assertEquals("element", nodeModel.getNodeType());

		_assertModelFactoryCache("_NODE_MODEL_FACTORY", node.getClass());

		// 3. Handle ResourceBundle

		ResourceBundle resourceBundle = new ResourceBundle() {

			@Override
			public Enumeration<String> getKeys() {
				return null;
			}

			@Override
			protected Object handleGetObject(String key) {
				return key;
			}

		};

		assertTemplateModel(
			resourceBundle.toString(),
			resourceBundleModel -> resourceBundleModel.getBundle(),
			ResourceBundleModel.class.cast(
				liferayObjectWrapper.handleUnknownType(resourceBundle)));

		_assertModelFactoryCache(
			"_RESOURCE_BUNDLE_MODEL_FACTORY", resourceBundle.getClass());

		// 4. Handle Version

		assertTemplateModel(
			"1.0", stringModel -> stringModel.getAsString(),
			StringModel.class.cast(
				liferayObjectWrapper.handleUnknownType(
					liferayObjectWrapper.handleUnknownType(
						new Version("1.0")))));

		_assertModelFactoryCache("_STRING_MODEL_FACTORY", Version.class);
	}

	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testInitializationFailure() throws Exception {
		SecurityException securityException = new SecurityException();

		try (SwappableSecurityManager swappableSecurityManager =
				ReflectionUtilTestUtil.throwForSuppressAccessChecks(
					securityException)) {

			Class.forName(
				"com.liferay.portal.template.freemarker.internal." +
					"LiferayObjectWrapper");

			Assert.fail("ExceptionInInitializerError was not thrown");
		}
		catch (ExceptionInInitializerError eiie) {
			Assert.assertSame(securityException, eiie.getCause());
		}
	}

	@Test
	public void testWrap() throws Exception {
		testWrap(new LiferayObjectWrapper());
	}

	private void _assertModelFactoryCache(
		String modelFactoryFieldName, Class<?> clazz) {

		Map<Class<?>, ModelFactory> modelFactories =
			ReflectionTestUtil.getFieldValue(
				LiferayObjectWrapper.class, "_modelFactories");

		Assert.assertSame(
			ReflectionTestUtil.getFieldValue(
				LiferayObjectWrapper.class, modelFactoryFieldName),
			modelFactories.get(clazz));
	}

}