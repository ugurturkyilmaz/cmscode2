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
import com.liferay.portal.tools.service.builder.test.exception.NoSuchLocalizedEntryException;
import com.liferay.portal.tools.service.builder.test.model.LocalizedEntry;
import com.liferay.portal.tools.service.builder.test.service.LocalizedEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.LocalizedEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.LocalizedEntryUtil;

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
public class LocalizedEntryPersistenceTest {

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
		_persistence = LocalizedEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LocalizedEntry> iterator = _localizedEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LocalizedEntry localizedEntry = _persistence.create(pk);

		Assert.assertNotNull(localizedEntry);

		Assert.assertEquals(localizedEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LocalizedEntry newLocalizedEntry = addLocalizedEntry();

		_persistence.remove(newLocalizedEntry);

		LocalizedEntry existingLocalizedEntry = _persistence.fetchByPrimaryKey(
			newLocalizedEntry.getPrimaryKey());

		Assert.assertNull(existingLocalizedEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLocalizedEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LocalizedEntry newLocalizedEntry = _persistence.create(pk);

		newLocalizedEntry.setDefaultLanguageId(RandomTestUtil.randomString());

		_localizedEntries.add(_persistence.update(newLocalizedEntry));

		LocalizedEntry existingLocalizedEntry = _persistence.findByPrimaryKey(
			newLocalizedEntry.getPrimaryKey());

		Assert.assertEquals(
			existingLocalizedEntry.getDefaultLanguageId(),
			newLocalizedEntry.getDefaultLanguageId());
		Assert.assertEquals(
			existingLocalizedEntry.getLocalizedEntryId(),
			newLocalizedEntry.getLocalizedEntryId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LocalizedEntry newLocalizedEntry = addLocalizedEntry();

		LocalizedEntry existingLocalizedEntry = _persistence.findByPrimaryKey(
			newLocalizedEntry.getPrimaryKey());

		Assert.assertEquals(existingLocalizedEntry, newLocalizedEntry);
	}

	@Test(expected = NoSuchLocalizedEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<LocalizedEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"LocalizedEntry", "defaultLanguageId", true, "localizedEntryId",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LocalizedEntry newLocalizedEntry = addLocalizedEntry();

		LocalizedEntry existingLocalizedEntry = _persistence.fetchByPrimaryKey(
			newLocalizedEntry.getPrimaryKey());

		Assert.assertEquals(existingLocalizedEntry, newLocalizedEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LocalizedEntry missingLocalizedEntry = _persistence.fetchByPrimaryKey(
			pk);

		Assert.assertNull(missingLocalizedEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		LocalizedEntry newLocalizedEntry1 = addLocalizedEntry();
		LocalizedEntry newLocalizedEntry2 = addLocalizedEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLocalizedEntry1.getPrimaryKey());
		primaryKeys.add(newLocalizedEntry2.getPrimaryKey());

		Map<Serializable, LocalizedEntry> localizedEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, localizedEntries.size());
		Assert.assertEquals(
			newLocalizedEntry1,
			localizedEntries.get(newLocalizedEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newLocalizedEntry2,
			localizedEntries.get(newLocalizedEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LocalizedEntry> localizedEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(localizedEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		LocalizedEntry newLocalizedEntry = addLocalizedEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLocalizedEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LocalizedEntry> localizedEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, localizedEntries.size());
		Assert.assertEquals(
			newLocalizedEntry,
			localizedEntries.get(newLocalizedEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LocalizedEntry> localizedEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(localizedEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		LocalizedEntry newLocalizedEntry = addLocalizedEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLocalizedEntry.getPrimaryKey());

		Map<Serializable, LocalizedEntry> localizedEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, localizedEntries.size());
		Assert.assertEquals(
			newLocalizedEntry,
			localizedEntries.get(newLocalizedEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			LocalizedEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<LocalizedEntry>() {

				@Override
				public void performAction(LocalizedEntry localizedEntry) {
					Assert.assertNotNull(localizedEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		LocalizedEntry newLocalizedEntry = addLocalizedEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LocalizedEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"localizedEntryId", newLocalizedEntry.getLocalizedEntryId()));

		List<LocalizedEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		LocalizedEntry existingLocalizedEntry = result.get(0);

		Assert.assertEquals(existingLocalizedEntry, newLocalizedEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LocalizedEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"localizedEntryId", RandomTestUtil.nextLong()));

		List<LocalizedEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		LocalizedEntry newLocalizedEntry = addLocalizedEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LocalizedEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("localizedEntryId"));

		Object newLocalizedEntryId = newLocalizedEntry.getLocalizedEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"localizedEntryId", new Object[] {newLocalizedEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLocalizedEntryId = result.get(0);

		Assert.assertEquals(existingLocalizedEntryId, newLocalizedEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LocalizedEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("localizedEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"localizedEntryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected LocalizedEntry addLocalizedEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LocalizedEntry localizedEntry = _persistence.create(pk);

		localizedEntry.setDefaultLanguageId(RandomTestUtil.randomString());

		_localizedEntries.add(_persistence.update(localizedEntry));

		return localizedEntry;
	}

	private List<LocalizedEntry> _localizedEntries =
		new ArrayList<LocalizedEntry>();
	private LocalizedEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}