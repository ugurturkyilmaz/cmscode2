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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchUADPartialEntryException;
import com.liferay.portal.tools.service.builder.test.model.UADPartialEntry;
import com.liferay.portal.tools.service.builder.test.service.UADPartialEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.UADPartialEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.UADPartialEntryUtil;

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
public class UADPartialEntryPersistenceTest {

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
		_persistence = UADPartialEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UADPartialEntry> iterator = _uadPartialEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UADPartialEntry uadPartialEntry = _persistence.create(pk);

		Assert.assertNotNull(uadPartialEntry);

		Assert.assertEquals(uadPartialEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UADPartialEntry newUADPartialEntry = addUADPartialEntry();

		_persistence.remove(newUADPartialEntry);

		UADPartialEntry existingUADPartialEntry =
			_persistence.fetchByPrimaryKey(newUADPartialEntry.getPrimaryKey());

		Assert.assertNull(existingUADPartialEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUADPartialEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UADPartialEntry newUADPartialEntry = _persistence.create(pk);

		newUADPartialEntry.setUserId(RandomTestUtil.nextLong());

		newUADPartialEntry.setUserName(RandomTestUtil.randomString());

		newUADPartialEntry.setMessage(RandomTestUtil.randomString());

		_uadPartialEntries.add(_persistence.update(newUADPartialEntry));

		UADPartialEntry existingUADPartialEntry = _persistence.findByPrimaryKey(
			newUADPartialEntry.getPrimaryKey());

		Assert.assertEquals(
			existingUADPartialEntry.getUadPartialEntryId(),
			newUADPartialEntry.getUadPartialEntryId());
		Assert.assertEquals(
			existingUADPartialEntry.getUserId(),
			newUADPartialEntry.getUserId());
		Assert.assertEquals(
			existingUADPartialEntry.getUserName(),
			newUADPartialEntry.getUserName());
		Assert.assertEquals(
			existingUADPartialEntry.getMessage(),
			newUADPartialEntry.getMessage());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UADPartialEntry newUADPartialEntry = addUADPartialEntry();

		UADPartialEntry existingUADPartialEntry = _persistence.findByPrimaryKey(
			newUADPartialEntry.getPrimaryKey());

		Assert.assertEquals(existingUADPartialEntry, newUADPartialEntry);
	}

	@Test(expected = NoSuchUADPartialEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<UADPartialEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"UADPartialEntry", "uadPartialEntryId", true, "userId", true,
			"userName", true, "message", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UADPartialEntry newUADPartialEntry = addUADPartialEntry();

		UADPartialEntry existingUADPartialEntry =
			_persistence.fetchByPrimaryKey(newUADPartialEntry.getPrimaryKey());

		Assert.assertEquals(existingUADPartialEntry, newUADPartialEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UADPartialEntry missingUADPartialEntry = _persistence.fetchByPrimaryKey(
			pk);

		Assert.assertNull(missingUADPartialEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		UADPartialEntry newUADPartialEntry1 = addUADPartialEntry();
		UADPartialEntry newUADPartialEntry2 = addUADPartialEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUADPartialEntry1.getPrimaryKey());
		primaryKeys.add(newUADPartialEntry2.getPrimaryKey());

		Map<Serializable, UADPartialEntry> uadPartialEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, uadPartialEntries.size());
		Assert.assertEquals(
			newUADPartialEntry1,
			uadPartialEntries.get(newUADPartialEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newUADPartialEntry2,
			uadPartialEntries.get(newUADPartialEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UADPartialEntry> uadPartialEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(uadPartialEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		UADPartialEntry newUADPartialEntry = addUADPartialEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUADPartialEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UADPartialEntry> uadPartialEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, uadPartialEntries.size());
		Assert.assertEquals(
			newUADPartialEntry,
			uadPartialEntries.get(newUADPartialEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UADPartialEntry> uadPartialEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(uadPartialEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		UADPartialEntry newUADPartialEntry = addUADPartialEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUADPartialEntry.getPrimaryKey());

		Map<Serializable, UADPartialEntry> uadPartialEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, uadPartialEntries.size());
		Assert.assertEquals(
			newUADPartialEntry,
			uadPartialEntries.get(newUADPartialEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			UADPartialEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<UADPartialEntry>() {

				@Override
				public void performAction(UADPartialEntry uadPartialEntry) {
					Assert.assertNotNull(uadPartialEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		UADPartialEntry newUADPartialEntry = addUADPartialEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UADPartialEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"uadPartialEntryId",
				newUADPartialEntry.getUadPartialEntryId()));

		List<UADPartialEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		UADPartialEntry existingUADPartialEntry = result.get(0);

		Assert.assertEquals(existingUADPartialEntry, newUADPartialEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UADPartialEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"uadPartialEntryId", RandomTestUtil.nextLong()));

		List<UADPartialEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		UADPartialEntry newUADPartialEntry = addUADPartialEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UADPartialEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("uadPartialEntryId"));

		Object newUadPartialEntryId = newUADPartialEntry.getUadPartialEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"uadPartialEntryId", new Object[] {newUadPartialEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUadPartialEntryId = result.get(0);

		Assert.assertEquals(existingUadPartialEntryId, newUadPartialEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UADPartialEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("uadPartialEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"uadPartialEntryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected UADPartialEntry addUADPartialEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UADPartialEntry uadPartialEntry = _persistence.create(pk);

		uadPartialEntry.setUserId(RandomTestUtil.nextLong());

		uadPartialEntry.setUserName(RandomTestUtil.randomString());

		uadPartialEntry.setMessage(RandomTestUtil.randomString());

		_uadPartialEntries.add(_persistence.update(uadPartialEntry));

		return uadPartialEntry;
	}

	private List<UADPartialEntry> _uadPartialEntries =
		new ArrayList<UADPartialEntry>();
	private UADPartialEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}