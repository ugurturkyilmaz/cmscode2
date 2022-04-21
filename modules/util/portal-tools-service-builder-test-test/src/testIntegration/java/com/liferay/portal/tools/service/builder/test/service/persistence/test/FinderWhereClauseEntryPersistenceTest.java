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
import com.liferay.portal.tools.service.builder.test.exception.NoSuchFinderWhereClauseEntryException;
import com.liferay.portal.tools.service.builder.test.model.FinderWhereClauseEntry;
import com.liferay.portal.tools.service.builder.test.service.FinderWhereClauseEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.FinderWhereClauseEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.FinderWhereClauseEntryUtil;

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
public class FinderWhereClauseEntryPersistenceTest {

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
		_persistence = FinderWhereClauseEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FinderWhereClauseEntry> iterator =
			_finderWhereClauseEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinderWhereClauseEntry finderWhereClauseEntry = _persistence.create(pk);

		Assert.assertNotNull(finderWhereClauseEntry);

		Assert.assertEquals(finderWhereClauseEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FinderWhereClauseEntry newFinderWhereClauseEntry =
			addFinderWhereClauseEntry();

		_persistence.remove(newFinderWhereClauseEntry);

		FinderWhereClauseEntry existingFinderWhereClauseEntry =
			_persistence.fetchByPrimaryKey(
				newFinderWhereClauseEntry.getPrimaryKey());

		Assert.assertNull(existingFinderWhereClauseEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFinderWhereClauseEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinderWhereClauseEntry newFinderWhereClauseEntry = _persistence.create(
			pk);

		newFinderWhereClauseEntry.setName(RandomTestUtil.randomString());

		newFinderWhereClauseEntry.setNickname(RandomTestUtil.randomString());

		_finderWhereClauseEntries.add(
			_persistence.update(newFinderWhereClauseEntry));

		FinderWhereClauseEntry existingFinderWhereClauseEntry =
			_persistence.findByPrimaryKey(
				newFinderWhereClauseEntry.getPrimaryKey());

		Assert.assertEquals(
			existingFinderWhereClauseEntry.getFinderWhereClauseEntryId(),
			newFinderWhereClauseEntry.getFinderWhereClauseEntryId());
		Assert.assertEquals(
			existingFinderWhereClauseEntry.getName(),
			newFinderWhereClauseEntry.getName());
		Assert.assertEquals(
			existingFinderWhereClauseEntry.getNickname(),
			newFinderWhereClauseEntry.getNickname());
	}

	@Test
	public void testCountByName_Nickname() throws Exception {
		_persistence.countByName_Nickname("");

		_persistence.countByName_Nickname("null");

		_persistence.countByName_Nickname((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FinderWhereClauseEntry newFinderWhereClauseEntry =
			addFinderWhereClauseEntry();

		FinderWhereClauseEntry existingFinderWhereClauseEntry =
			_persistence.findByPrimaryKey(
				newFinderWhereClauseEntry.getPrimaryKey());

		Assert.assertEquals(
			existingFinderWhereClauseEntry, newFinderWhereClauseEntry);
	}

	@Test(expected = NoSuchFinderWhereClauseEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<FinderWhereClauseEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"FinderWhereClauseEntry", "finderWhereClauseEntryId", true, "name",
			true, "nickname", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FinderWhereClauseEntry newFinderWhereClauseEntry =
			addFinderWhereClauseEntry();

		FinderWhereClauseEntry existingFinderWhereClauseEntry =
			_persistence.fetchByPrimaryKey(
				newFinderWhereClauseEntry.getPrimaryKey());

		Assert.assertEquals(
			existingFinderWhereClauseEntry, newFinderWhereClauseEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinderWhereClauseEntry missingFinderWhereClauseEntry =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFinderWhereClauseEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		FinderWhereClauseEntry newFinderWhereClauseEntry1 =
			addFinderWhereClauseEntry();
		FinderWhereClauseEntry newFinderWhereClauseEntry2 =
			addFinderWhereClauseEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinderWhereClauseEntry1.getPrimaryKey());
		primaryKeys.add(newFinderWhereClauseEntry2.getPrimaryKey());

		Map<Serializable, FinderWhereClauseEntry> finderWhereClauseEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, finderWhereClauseEntries.size());
		Assert.assertEquals(
			newFinderWhereClauseEntry1,
			finderWhereClauseEntries.get(
				newFinderWhereClauseEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newFinderWhereClauseEntry2,
			finderWhereClauseEntries.get(
				newFinderWhereClauseEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FinderWhereClauseEntry> finderWhereClauseEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(finderWhereClauseEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		FinderWhereClauseEntry newFinderWhereClauseEntry =
			addFinderWhereClauseEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinderWhereClauseEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FinderWhereClauseEntry> finderWhereClauseEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, finderWhereClauseEntries.size());
		Assert.assertEquals(
			newFinderWhereClauseEntry,
			finderWhereClauseEntries.get(
				newFinderWhereClauseEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FinderWhereClauseEntry> finderWhereClauseEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(finderWhereClauseEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		FinderWhereClauseEntry newFinderWhereClauseEntry =
			addFinderWhereClauseEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinderWhereClauseEntry.getPrimaryKey());

		Map<Serializable, FinderWhereClauseEntry> finderWhereClauseEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, finderWhereClauseEntries.size());
		Assert.assertEquals(
			newFinderWhereClauseEntry,
			finderWhereClauseEntries.get(
				newFinderWhereClauseEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			FinderWhereClauseEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<FinderWhereClauseEntry>() {

				@Override
				public void performAction(
					FinderWhereClauseEntry finderWhereClauseEntry) {

					Assert.assertNotNull(finderWhereClauseEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		FinderWhereClauseEntry newFinderWhereClauseEntry =
			addFinderWhereClauseEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinderWhereClauseEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"finderWhereClauseEntryId",
				newFinderWhereClauseEntry.getFinderWhereClauseEntryId()));

		List<FinderWhereClauseEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		FinderWhereClauseEntry existingFinderWhereClauseEntry = result.get(0);

		Assert.assertEquals(
			existingFinderWhereClauseEntry, newFinderWhereClauseEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinderWhereClauseEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"finderWhereClauseEntryId", RandomTestUtil.nextLong()));

		List<FinderWhereClauseEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		FinderWhereClauseEntry newFinderWhereClauseEntry =
			addFinderWhereClauseEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinderWhereClauseEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("finderWhereClauseEntryId"));

		Object newFinderWhereClauseEntryId =
			newFinderWhereClauseEntry.getFinderWhereClauseEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"finderWhereClauseEntryId",
				new Object[] {newFinderWhereClauseEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFinderWhereClauseEntryId = result.get(0);

		Assert.assertEquals(
			existingFinderWhereClauseEntryId, newFinderWhereClauseEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinderWhereClauseEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("finderWhereClauseEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"finderWhereClauseEntryId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected FinderWhereClauseEntry addFinderWhereClauseEntry()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		FinderWhereClauseEntry finderWhereClauseEntry = _persistence.create(pk);

		finderWhereClauseEntry.setName(RandomTestUtil.randomString());

		finderWhereClauseEntry.setNickname(RandomTestUtil.randomString());

		_finderWhereClauseEntries.add(
			_persistence.update(finderWhereClauseEntry));

		return finderWhereClauseEntry;
	}

	private List<FinderWhereClauseEntry> _finderWhereClauseEntries =
		new ArrayList<FinderWhereClauseEntry>();
	private FinderWhereClauseEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}