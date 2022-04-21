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

package com.liferay.change.tracking.internal;

import com.liferay.change.tracking.closure.CTClosure;
import com.liferay.change.tracking.internal.closure.CTClosureImpl;
import com.liferay.change.tracking.internal.closure.Edge;
import com.liferay.change.tracking.internal.closure.GraphUtil;
import com.liferay.change.tracking.internal.closure.Node;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Preston Crary
 */
public class CTEnclosureUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Test
	public void testConstructor() {
		new CTEnclosureUtil();
	}

	@Test
	public void testEnclosureUtil() {
		Node node1 = new Node(1, 1);
		Node node2 = new Node(1, 2);
		Node node3 = new Node(2, 3);
		Node node4 = new Node(2, 4);
		Node node5 = new Node(3, 5);
		Node node6 = new Node(4, 6);

		Set<Node> nodes = new HashSet<>(
			Arrays.asList(node1, node2, node3, node4, node5));

		CTClosure ctClosure = new CTClosureImpl(
			1,
			GraphUtil.getNodeMap(
				nodes,
				HashMapBuilder.<Node, Collection<Edge>>put(
					node1,
					Arrays.asList(
						new Edge(node1, node3), new Edge(node1, node4))
				).put(
					node2,
					Arrays.asList(
						new Edge(node2, node4), new Edge(node2, node5))
				).put(
					node3, Collections.singleton(new Edge(node3, node6))
				).put(
					node4, Collections.singleton(new Edge(node4, node6))
				).build()));

		Map<Long, Set<Long>> enclosureMap = CTEnclosureUtil.getEnclosureMap(
			ctClosure, node3.getClassNameId(), node3.getPrimaryKey());

		Assert.assertEquals(
			Collections.singleton(node3.getPrimaryKey()),
			enclosureMap.remove(node3.getClassNameId()));

		Assert.assertEquals(
			Collections.singleton(node6.getPrimaryKey()),
			enclosureMap.remove(node6.getClassNameId()));

		Assert.assertTrue(enclosureMap.toString(), enclosureMap.isEmpty());

		enclosureMap = CTEnclosureUtil.getEnclosureMap(
			ctClosure, node3.getClassNameId(), node3.getPrimaryKey());

		Assert.assertEquals(
			new HashSet<>(
				Arrays.asList(
					new AbstractMap.SimpleImmutableEntry<>(
						node1.getClassNameId(), node1.getPrimaryKey()),
					new AbstractMap.SimpleImmutableEntry<>(
						node2.getClassNameId(), node2.getPrimaryKey()),
					new AbstractMap.SimpleImmutableEntry<>(
						node4.getClassNameId(), node4.getPrimaryKey()))),
			CTEnclosureUtil.getEnclosureParentEntries(ctClosure, enclosureMap));

		enclosureMap = CTEnclosureUtil.getEnclosureMap(
			ctClosure, node4.getClassNameId(), node4.getPrimaryKey());

		Assert.assertEquals(
			Collections.singleton(node4.getPrimaryKey()),
			enclosureMap.remove(node4.getClassNameId()));

		Assert.assertEquals(
			Collections.singleton(node6.getPrimaryKey()),
			enclosureMap.remove(node6.getClassNameId()));

		Assert.assertTrue(enclosureMap.toString(), enclosureMap.isEmpty());

		enclosureMap = CTEnclosureUtil.getEnclosureMap(
			ctClosure, node4.getClassNameId(), node4.getPrimaryKey());

		Assert.assertEquals(
			new HashSet<>(
				Arrays.asList(
					new AbstractMap.SimpleImmutableEntry<>(
						node1.getClassNameId(), node1.getPrimaryKey()),
					new AbstractMap.SimpleImmutableEntry<>(
						node2.getClassNameId(), node2.getPrimaryKey()),
					new AbstractMap.SimpleImmutableEntry<>(
						node3.getClassNameId(), node3.getPrimaryKey()))),
			CTEnclosureUtil.getEnclosureParentEntries(ctClosure, enclosureMap));

		enclosureMap = CTEnclosureUtil.getEnclosureMap(
			ctClosure, node1.getClassNameId(), node1.getPrimaryKey());

		Assert.assertEquals(
			Collections.singleton(node1.getPrimaryKey()),
			enclosureMap.remove(node1.getClassNameId()));

		Assert.assertEquals(
			new HashSet<>(
				Arrays.asList(node3.getPrimaryKey(), node4.getPrimaryKey())),
			enclosureMap.remove(node3.getClassNameId()));

		Assert.assertEquals(
			Collections.singleton(node6.getPrimaryKey()),
			enclosureMap.remove(node6.getClassNameId()));

		Assert.assertTrue(enclosureMap.toString(), enclosureMap.isEmpty());

		enclosureMap = CTEnclosureUtil.getEnclosureMap(
			ctClosure, node1.getClassNameId(), node1.getPrimaryKey());

		Assert.assertEquals(
			Collections.singleton(
				new AbstractMap.SimpleImmutableEntry<>(
					node2.getClassNameId(), node2.getPrimaryKey())),
			CTEnclosureUtil.getEnclosureParentEntries(ctClosure, enclosureMap));
	}

}