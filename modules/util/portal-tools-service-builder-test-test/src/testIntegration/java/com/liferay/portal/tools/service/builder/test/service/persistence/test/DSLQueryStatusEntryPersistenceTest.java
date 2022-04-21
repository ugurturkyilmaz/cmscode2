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

package com.liferay.portal.tools.service.builder.test.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDSLQueryStatusEntryException;
import com.liferay.portal.tools.service.builder.test.model.DSLQueryStatusEntry;
import com.liferay.portal.tools.service.builder.test.service.DSLQueryStatusEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.DSLQueryStatusEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.DSLQueryStatusEntryUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class DSLQueryStatusEntryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.portal.tools.service.builder.test.service"));

	@Before
	public void setUp() {
		_persistence = DSLQueryStatusEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DSLQueryStatusEntry> iterator =
			_dslQueryStatusEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DSLQueryStatusEntry dslQueryStatusEntry = _persistence.create(pk);

		Assert.assertNotNull(dslQueryStatusEntry);

		Assert.assertEquals(dslQueryStatusEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DSLQueryStatusEntry newDSLQueryStatusEntry = addDSLQueryStatusEntry();

		_persistence.remove(newDSLQueryStatusEntry);

		DSLQueryStatusEntry existingDSLQueryStatusEntry =
			_persistence.fetchByPrimaryKey(
				newDSLQueryStatusEntry.getPrimaryKey());

		Assert.assertNull(existingDSLQueryStatusEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDSLQueryStatusEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DSLQueryStatusEntry newDSLQueryStatusEntry = _persistence.create(pk);

		newDSLQueryStatusEntry.setDslQueryEntryId(RandomTestUtil.nextLong());

		newDSLQueryStatusEntry.setStatus(RandomTestUtil.randomString());

		newDSLQueryStatusEntry.setStatusDate(RandomTestUtil.nextDate());

		_dslQueryStatusEntries.add(_persistence.update(newDSLQueryStatusEntry));

		DSLQueryStatusEntry existingDSLQueryStatusEntry =
			_persistence.findByPrimaryKey(
				newDSLQueryStatusEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDSLQueryStatusEntry.getDslQueryStatusEntryId(),
			newDSLQueryStatusEntry.getDslQueryStatusEntryId());
		Assert.assertEquals(
			existingDSLQueryStatusEntry.getDslQueryEntryId(),
			newDSLQueryStatusEntry.getDslQueryEntryId());
		Assert.assertEquals(
			existingDSLQueryStatusEntry.getStatus(),
			newDSLQueryStatusEntry.getStatus());
		Assert.assertEquals(
			Time.getShortTimestamp(existingDSLQueryStatusEntry.getStatusDate()),
			Time.getShortTimestamp(newDSLQueryStatusEntry.getStatusDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DSLQueryStatusEntry newDSLQueryStatusEntry = addDSLQueryStatusEntry();

		DSLQueryStatusEntry existingDSLQueryStatusEntry =
			_persistence.findByPrimaryKey(
				newDSLQueryStatusEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDSLQueryStatusEntry, newDSLQueryStatusEntry);
	}

	@Test(expected = NoSuchDSLQueryStatusEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DSLQueryStatusEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"DSLQueryStatusEntry", "dslQueryStatusEntryId", true,
			"dslQueryEntryId", true, "status", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DSLQueryStatusEntry newDSLQueryStatusEntry = addDSLQueryStatusEntry();

		DSLQueryStatusEntry existingDSLQueryStatusEntry =
			_persistence.fetchByPrimaryKey(
				newDSLQueryStatusEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDSLQueryStatusEntry, newDSLQueryStatusEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DSLQueryStatusEntry missingDSLQueryStatusEntry =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDSLQueryStatusEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DSLQueryStatusEntry newDSLQueryStatusEntry1 = addDSLQueryStatusEntry();
		DSLQueryStatusEntry newDSLQueryStatusEntry2 = addDSLQueryStatusEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDSLQueryStatusEntry1.getPrimaryKey());
		primaryKeys.add(newDSLQueryStatusEntry2.getPrimaryKey());

		Map<Serializable, DSLQueryStatusEntry> dslQueryStatusEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, dslQueryStatusEntries.size());
		Assert.assertEquals(
			newDSLQueryStatusEntry1,
			dslQueryStatusEntries.get(newDSLQueryStatusEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newDSLQueryStatusEntry2,
			dslQueryStatusEntries.get(newDSLQueryStatusEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DSLQueryStatusEntry> dslQueryStatusEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dslQueryStatusEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DSLQueryStatusEntry newDSLQueryStatusEntry = addDSLQueryStatusEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDSLQueryStatusEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DSLQueryStatusEntry> dslQueryStatusEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dslQueryStatusEntries.size());
		Assert.assertEquals(
			newDSLQueryStatusEntry,
			dslQueryStatusEntries.get(newDSLQueryStatusEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DSLQueryStatusEntry> dslQueryStatusEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dslQueryStatusEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DSLQueryStatusEntry newDSLQueryStatusEntry = addDSLQueryStatusEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDSLQueryStatusEntry.getPrimaryKey());

		Map<Serializable, DSLQueryStatusEntry> dslQueryStatusEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dslQueryStatusEntries.size());
		Assert.assertEquals(
			newDSLQueryStatusEntry,
			dslQueryStatusEntries.get(newDSLQueryStatusEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DSLQueryStatusEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<DSLQueryStatusEntry>() {

				@Override
				public void performAction(
					DSLQueryStatusEntry dslQueryStatusEntry) {

					Assert.assertNotNull(dslQueryStatusEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DSLQueryStatusEntry newDSLQueryStatusEntry = addDSLQueryStatusEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DSLQueryStatusEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"dslQueryStatusEntryId",
				newDSLQueryStatusEntry.getDslQueryStatusEntryId()));

		List<DSLQueryStatusEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		DSLQueryStatusEntry existingDSLQueryStatusEntry = result.get(0);

		Assert.assertEquals(
			existingDSLQueryStatusEntry, newDSLQueryStatusEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DSLQueryStatusEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"dslQueryStatusEntryId", RandomTestUtil.nextLong()));

		List<DSLQueryStatusEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DSLQueryStatusEntry newDSLQueryStatusEntry = addDSLQueryStatusEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DSLQueryStatusEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("dslQueryStatusEntryId"));

		Object newDslQueryStatusEntryId =
			newDSLQueryStatusEntry.getDslQueryStatusEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"dslQueryStatusEntryId",
				new Object[] {newDslQueryStatusEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDslQueryStatusEntryId = result.get(0);

		Assert.assertEquals(
			existingDslQueryStatusEntryId, newDslQueryStatusEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DSLQueryStatusEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("dslQueryStatusEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"dslQueryStatusEntryId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DSLQueryStatusEntry addDSLQueryStatusEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DSLQueryStatusEntry dslQueryStatusEntry = _persistence.create(pk);

		dslQueryStatusEntry.setDslQueryEntryId(RandomTestUtil.nextLong());

		dslQueryStatusEntry.setStatus(RandomTestUtil.randomString());

		dslQueryStatusEntry.setStatusDate(RandomTestUtil.nextDate());

		_dslQueryStatusEntries.add(_persistence.update(dslQueryStatusEntry));

		return dslQueryStatusEntry;
	}

	private List<DSLQueryStatusEntry> _dslQueryStatusEntries =
		new ArrayList<DSLQueryStatusEntry>();
	private DSLQueryStatusEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}