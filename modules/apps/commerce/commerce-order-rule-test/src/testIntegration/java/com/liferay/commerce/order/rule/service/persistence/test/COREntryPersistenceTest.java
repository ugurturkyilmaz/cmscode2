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

package com.liferay.commerce.order.rule.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.order.rule.exception.NoSuchCOREntryException;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.service.COREntryLocalServiceUtil;
import com.liferay.commerce.order.rule.service.persistence.COREntryPersistence;
import com.liferay.commerce.order.rule.service.persistence.COREntryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
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
public class COREntryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.order.rule.service"));

	@Before
	public void setUp() {
		_persistence = COREntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<COREntry> iterator = _corEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		COREntry corEntry = _persistence.create(pk);

		Assert.assertNotNull(corEntry);

		Assert.assertEquals(corEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		COREntry newCOREntry = addCOREntry();

		_persistence.remove(newCOREntry);

		COREntry existingCOREntry = _persistence.fetchByPrimaryKey(
			newCOREntry.getPrimaryKey());

		Assert.assertNull(existingCOREntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCOREntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		COREntry newCOREntry = _persistence.create(pk);

		newCOREntry.setMvccVersion(RandomTestUtil.nextLong());

		newCOREntry.setExternalReferenceCode(RandomTestUtil.randomString());

		newCOREntry.setCompanyId(RandomTestUtil.nextLong());

		newCOREntry.setUserId(RandomTestUtil.nextLong());

		newCOREntry.setUserName(RandomTestUtil.randomString());

		newCOREntry.setCreateDate(RandomTestUtil.nextDate());

		newCOREntry.setModifiedDate(RandomTestUtil.nextDate());

		newCOREntry.setActive(RandomTestUtil.randomBoolean());

		newCOREntry.setDescription(RandomTestUtil.randomString());

		newCOREntry.setDisplayDate(RandomTestUtil.nextDate());

		newCOREntry.setExpirationDate(RandomTestUtil.nextDate());

		newCOREntry.setName(RandomTestUtil.randomString());

		newCOREntry.setPriority(RandomTestUtil.nextInt());

		newCOREntry.setType(RandomTestUtil.randomString());

		newCOREntry.setTypeSettings(RandomTestUtil.randomString());

		newCOREntry.setLastPublishDate(RandomTestUtil.nextDate());

		newCOREntry.setStatus(RandomTestUtil.nextInt());

		newCOREntry.setStatusByUserId(RandomTestUtil.nextLong());

		newCOREntry.setStatusByUserName(RandomTestUtil.randomString());

		newCOREntry.setStatusDate(RandomTestUtil.nextDate());

		_corEntries.add(_persistence.update(newCOREntry));

		COREntry existingCOREntry = _persistence.findByPrimaryKey(
			newCOREntry.getPrimaryKey());

		Assert.assertEquals(
			existingCOREntry.getMvccVersion(), newCOREntry.getMvccVersion());
		Assert.assertEquals(
			existingCOREntry.getExternalReferenceCode(),
			newCOREntry.getExternalReferenceCode());
		Assert.assertEquals(
			existingCOREntry.getCOREntryId(), newCOREntry.getCOREntryId());
		Assert.assertEquals(
			existingCOREntry.getCompanyId(), newCOREntry.getCompanyId());
		Assert.assertEquals(
			existingCOREntry.getUserId(), newCOREntry.getUserId());
		Assert.assertEquals(
			existingCOREntry.getUserName(), newCOREntry.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCOREntry.getCreateDate()),
			Time.getShortTimestamp(newCOREntry.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingCOREntry.getModifiedDate()),
			Time.getShortTimestamp(newCOREntry.getModifiedDate()));
		Assert.assertEquals(
			existingCOREntry.isActive(), newCOREntry.isActive());
		Assert.assertEquals(
			existingCOREntry.getDescription(), newCOREntry.getDescription());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCOREntry.getDisplayDate()),
			Time.getShortTimestamp(newCOREntry.getDisplayDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingCOREntry.getExpirationDate()),
			Time.getShortTimestamp(newCOREntry.getExpirationDate()));
		Assert.assertEquals(existingCOREntry.getName(), newCOREntry.getName());
		Assert.assertEquals(
			existingCOREntry.getPriority(), newCOREntry.getPriority());
		Assert.assertEquals(existingCOREntry.getType(), newCOREntry.getType());
		Assert.assertEquals(
			existingCOREntry.getTypeSettings(), newCOREntry.getTypeSettings());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCOREntry.getLastPublishDate()),
			Time.getShortTimestamp(newCOREntry.getLastPublishDate()));
		Assert.assertEquals(
			existingCOREntry.getStatus(), newCOREntry.getStatus());
		Assert.assertEquals(
			existingCOREntry.getStatusByUserId(),
			newCOREntry.getStatusByUserId());
		Assert.assertEquals(
			existingCOREntry.getStatusByUserName(),
			newCOREntry.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCOREntry.getStatusDate()),
			Time.getShortTimestamp(newCOREntry.getStatusDate()));
	}

	@Test
	public void testCountByC_A() throws Exception {
		_persistence.countByC_A(
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByC_A(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_LikeType() throws Exception {
		_persistence.countByC_LikeType(RandomTestUtil.nextLong(), "");

		_persistence.countByC_LikeType(0L, "null");

		_persistence.countByC_LikeType(0L, (String)null);
	}

	@Test
	public void testCountByLtD_S() throws Exception {
		_persistence.countByLtD_S(
			RandomTestUtil.nextDate(), RandomTestUtil.nextInt());

		_persistence.countByLtD_S(RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByLtE_S() throws Exception {
		_persistence.countByLtE_S(
			RandomTestUtil.nextDate(), RandomTestUtil.nextInt());

		_persistence.countByLtE_S(RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByC_A_LikeType() throws Exception {
		_persistence.countByC_A_LikeType(
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean(), "");

		_persistence.countByC_A_LikeType(
			0L, RandomTestUtil.randomBoolean(), "null");

		_persistence.countByC_A_LikeType(
			0L, RandomTestUtil.randomBoolean(), (String)null);
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		COREntry newCOREntry = addCOREntry();

		COREntry existingCOREntry = _persistence.findByPrimaryKey(
			newCOREntry.getPrimaryKey());

		Assert.assertEquals(existingCOREntry, newCOREntry);
	}

	@Test(expected = NoSuchCOREntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<COREntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"COREntry", "mvccVersion", true, "externalReferenceCode", true,
			"COREntryId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true, "active", true,
			"description", true, "displayDate", true, "expirationDate", true,
			"name", true, "priority", true, "type", true, "lastPublishDate",
			true, "status", true, "statusByUserId", true, "statusByUserName",
			true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		COREntry newCOREntry = addCOREntry();

		COREntry existingCOREntry = _persistence.fetchByPrimaryKey(
			newCOREntry.getPrimaryKey());

		Assert.assertEquals(existingCOREntry, newCOREntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		COREntry missingCOREntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCOREntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		COREntry newCOREntry1 = addCOREntry();
		COREntry newCOREntry2 = addCOREntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCOREntry1.getPrimaryKey());
		primaryKeys.add(newCOREntry2.getPrimaryKey());

		Map<Serializable, COREntry> corEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, corEntries.size());
		Assert.assertEquals(
			newCOREntry1, corEntries.get(newCOREntry1.getPrimaryKey()));
		Assert.assertEquals(
			newCOREntry2, corEntries.get(newCOREntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, COREntry> corEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(corEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		COREntry newCOREntry = addCOREntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCOREntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, COREntry> corEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, corEntries.size());
		Assert.assertEquals(
			newCOREntry, corEntries.get(newCOREntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, COREntry> corEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(corEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		COREntry newCOREntry = addCOREntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCOREntry.getPrimaryKey());

		Map<Serializable, COREntry> corEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, corEntries.size());
		Assert.assertEquals(
			newCOREntry, corEntries.get(newCOREntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			COREntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<COREntry>() {

				@Override
				public void performAction(COREntry corEntry) {
					Assert.assertNotNull(corEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		COREntry newCOREntry = addCOREntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			COREntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"COREntryId", newCOREntry.getCOREntryId()));

		List<COREntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		COREntry existingCOREntry = result.get(0);

		Assert.assertEquals(existingCOREntry, newCOREntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			COREntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"COREntryId", RandomTestUtil.nextLong()));

		List<COREntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		COREntry newCOREntry = addCOREntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			COREntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("COREntryId"));

		Object newCOREntryId = newCOREntry.getCOREntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"COREntryId", new Object[] {newCOREntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCOREntryId = result.get(0);

		Assert.assertEquals(existingCOREntryId, newCOREntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			COREntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("COREntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"COREntryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		COREntry newCOREntry = addCOREntry();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newCOREntry.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		COREntry newCOREntry = addCOREntry();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			COREntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"COREntryId", newCOREntry.getCOREntryId()));

		List<COREntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(COREntry corEntry) {
		Assert.assertEquals(
			Long.valueOf(corEntry.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				corEntry, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "companyId"));
		Assert.assertEquals(
			corEntry.getExternalReferenceCode(),
			ReflectionTestUtil.invoke(
				corEntry, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "externalReferenceCode"));
	}

	protected COREntry addCOREntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		COREntry corEntry = _persistence.create(pk);

		corEntry.setMvccVersion(RandomTestUtil.nextLong());

		corEntry.setExternalReferenceCode(RandomTestUtil.randomString());

		corEntry.setCompanyId(RandomTestUtil.nextLong());

		corEntry.setUserId(RandomTestUtil.nextLong());

		corEntry.setUserName(RandomTestUtil.randomString());

		corEntry.setCreateDate(RandomTestUtil.nextDate());

		corEntry.setModifiedDate(RandomTestUtil.nextDate());

		corEntry.setActive(RandomTestUtil.randomBoolean());

		corEntry.setDescription(RandomTestUtil.randomString());

		corEntry.setDisplayDate(RandomTestUtil.nextDate());

		corEntry.setExpirationDate(RandomTestUtil.nextDate());

		corEntry.setName(RandomTestUtil.randomString());

		corEntry.setPriority(RandomTestUtil.nextInt());

		corEntry.setType(RandomTestUtil.randomString());

		corEntry.setTypeSettings(RandomTestUtil.randomString());

		corEntry.setLastPublishDate(RandomTestUtil.nextDate());

		corEntry.setStatus(RandomTestUtil.nextInt());

		corEntry.setStatusByUserId(RandomTestUtil.nextLong());

		corEntry.setStatusByUserName(RandomTestUtil.randomString());

		corEntry.setStatusDate(RandomTestUtil.nextDate());

		_corEntries.add(_persistence.update(corEntry));

		return corEntry;
	}

	private List<COREntry> _corEntries = new ArrayList<COREntry>();
	private COREntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}