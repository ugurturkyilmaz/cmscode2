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
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDataLimitEntryException;
import com.liferay.portal.tools.service.builder.test.model.DataLimitEntry;
import com.liferay.portal.tools.service.builder.test.service.DataLimitEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.DataLimitEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.DataLimitEntryUtil;

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
public class DataLimitEntryPersistenceTest {

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
		_persistence = DataLimitEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DataLimitEntry> iterator = _dataLimitEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DataLimitEntry dataLimitEntry = _persistence.create(pk);

		Assert.assertNotNull(dataLimitEntry);

		Assert.assertEquals(dataLimitEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DataLimitEntry newDataLimitEntry = addDataLimitEntry();

		_persistence.remove(newDataLimitEntry);

		DataLimitEntry existingDataLimitEntry = _persistence.fetchByPrimaryKey(
			newDataLimitEntry.getPrimaryKey());

		Assert.assertNull(existingDataLimitEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDataLimitEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DataLimitEntry newDataLimitEntry = _persistence.create(pk);

		newDataLimitEntry.setCompanyId(RandomTestUtil.nextLong());

		newDataLimitEntry.setUserId(RandomTestUtil.nextLong());

		newDataLimitEntry.setUserName(RandomTestUtil.randomString());

		newDataLimitEntry.setCreateDate(RandomTestUtil.nextDate());

		newDataLimitEntry.setModifiedDate(RandomTestUtil.nextDate());

		_dataLimitEntries.add(_persistence.update(newDataLimitEntry));

		DataLimitEntry existingDataLimitEntry = _persistence.findByPrimaryKey(
			newDataLimitEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDataLimitEntry.getDataLimitEntryId(),
			newDataLimitEntry.getDataLimitEntryId());
		Assert.assertEquals(
			existingDataLimitEntry.getCompanyId(),
			newDataLimitEntry.getCompanyId());
		Assert.assertEquals(
			existingDataLimitEntry.getUserId(), newDataLimitEntry.getUserId());
		Assert.assertEquals(
			existingDataLimitEntry.getUserName(),
			newDataLimitEntry.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingDataLimitEntry.getCreateDate()),
			Time.getShortTimestamp(newDataLimitEntry.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingDataLimitEntry.getModifiedDate()),
			Time.getShortTimestamp(newDataLimitEntry.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DataLimitEntry newDataLimitEntry = addDataLimitEntry();

		DataLimitEntry existingDataLimitEntry = _persistence.findByPrimaryKey(
			newDataLimitEntry.getPrimaryKey());

		Assert.assertEquals(existingDataLimitEntry, newDataLimitEntry);
	}

	@Test(expected = NoSuchDataLimitEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DataLimitEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"DataLimitEntry", "dataLimitEntryId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DataLimitEntry newDataLimitEntry = addDataLimitEntry();

		DataLimitEntry existingDataLimitEntry = _persistence.fetchByPrimaryKey(
			newDataLimitEntry.getPrimaryKey());

		Assert.assertEquals(existingDataLimitEntry, newDataLimitEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DataLimitEntry missingDataLimitEntry = _persistence.fetchByPrimaryKey(
			pk);

		Assert.assertNull(missingDataLimitEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DataLimitEntry newDataLimitEntry1 = addDataLimitEntry();
		DataLimitEntry newDataLimitEntry2 = addDataLimitEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDataLimitEntry1.getPrimaryKey());
		primaryKeys.add(newDataLimitEntry2.getPrimaryKey());

		Map<Serializable, DataLimitEntry> dataLimitEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, dataLimitEntries.size());
		Assert.assertEquals(
			newDataLimitEntry1,
			dataLimitEntries.get(newDataLimitEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newDataLimitEntry2,
			dataLimitEntries.get(newDataLimitEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DataLimitEntry> dataLimitEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dataLimitEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DataLimitEntry newDataLimitEntry = addDataLimitEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDataLimitEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DataLimitEntry> dataLimitEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dataLimitEntries.size());
		Assert.assertEquals(
			newDataLimitEntry,
			dataLimitEntries.get(newDataLimitEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DataLimitEntry> dataLimitEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dataLimitEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DataLimitEntry newDataLimitEntry = addDataLimitEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDataLimitEntry.getPrimaryKey());

		Map<Serializable, DataLimitEntry> dataLimitEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dataLimitEntries.size());
		Assert.assertEquals(
			newDataLimitEntry,
			dataLimitEntries.get(newDataLimitEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DataLimitEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DataLimitEntry>() {

				@Override
				public void performAction(DataLimitEntry dataLimitEntry) {
					Assert.assertNotNull(dataLimitEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DataLimitEntry newDataLimitEntry = addDataLimitEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DataLimitEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"dataLimitEntryId", newDataLimitEntry.getDataLimitEntryId()));

		List<DataLimitEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		DataLimitEntry existingDataLimitEntry = result.get(0);

		Assert.assertEquals(existingDataLimitEntry, newDataLimitEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DataLimitEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"dataLimitEntryId", RandomTestUtil.nextLong()));

		List<DataLimitEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DataLimitEntry newDataLimitEntry = addDataLimitEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DataLimitEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("dataLimitEntryId"));

		Object newDataLimitEntryId = newDataLimitEntry.getDataLimitEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"dataLimitEntryId", new Object[] {newDataLimitEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDataLimitEntryId = result.get(0);

		Assert.assertEquals(existingDataLimitEntryId, newDataLimitEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DataLimitEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("dataLimitEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"dataLimitEntryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DataLimitEntry addDataLimitEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DataLimitEntry dataLimitEntry = _persistence.create(pk);

		dataLimitEntry.setCompanyId(RandomTestUtil.nextLong());

		dataLimitEntry.setUserId(RandomTestUtil.nextLong());

		dataLimitEntry.setUserName(RandomTestUtil.randomString());

		dataLimitEntry.setCreateDate(RandomTestUtil.nextDate());

		dataLimitEntry.setModifiedDate(RandomTestUtil.nextDate());

		_dataLimitEntries.add(_persistence.update(dataLimitEntry));

		return dataLimitEntry;
	}

	private List<DataLimitEntry> _dataLimitEntries =
		new ArrayList<DataLimitEntry>();
	private DataLimitEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}