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

package com.liferay.batch.planner.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.batch.planner.exception.NoSuchMappingException;
import com.liferay.batch.planner.model.BatchPlannerMapping;
import com.liferay.batch.planner.service.BatchPlannerMappingLocalServiceUtil;
import com.liferay.batch.planner.service.persistence.BatchPlannerMappingPersistence;
import com.liferay.batch.planner.service.persistence.BatchPlannerMappingUtil;
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
public class BatchPlannerMappingPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.batch.planner.service"));

	@Before
	public void setUp() {
		_persistence = BatchPlannerMappingUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<BatchPlannerMapping> iterator =
			_batchPlannerMappings.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BatchPlannerMapping batchPlannerMapping = _persistence.create(pk);

		Assert.assertNotNull(batchPlannerMapping);

		Assert.assertEquals(batchPlannerMapping.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		_persistence.remove(newBatchPlannerMapping);

		BatchPlannerMapping existingBatchPlannerMapping =
			_persistence.fetchByPrimaryKey(
				newBatchPlannerMapping.getPrimaryKey());

		Assert.assertNull(existingBatchPlannerMapping);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBatchPlannerMapping();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BatchPlannerMapping newBatchPlannerMapping = _persistence.create(pk);

		newBatchPlannerMapping.setMvccVersion(RandomTestUtil.nextLong());

		newBatchPlannerMapping.setCompanyId(RandomTestUtil.nextLong());

		newBatchPlannerMapping.setUserId(RandomTestUtil.nextLong());

		newBatchPlannerMapping.setUserName(RandomTestUtil.randomString());

		newBatchPlannerMapping.setCreateDate(RandomTestUtil.nextDate());

		newBatchPlannerMapping.setModifiedDate(RandomTestUtil.nextDate());

		newBatchPlannerMapping.setBatchPlannerPlanId(RandomTestUtil.nextLong());

		newBatchPlannerMapping.setExternalFieldName(
			RandomTestUtil.randomString());

		newBatchPlannerMapping.setExternalFieldType(
			RandomTestUtil.randomString());

		newBatchPlannerMapping.setInternalFieldName(
			RandomTestUtil.randomString());

		newBatchPlannerMapping.setInternalFieldType(
			RandomTestUtil.randomString());

		newBatchPlannerMapping.setScript(RandomTestUtil.randomString());

		_batchPlannerMappings.add(_persistence.update(newBatchPlannerMapping));

		BatchPlannerMapping existingBatchPlannerMapping =
			_persistence.findByPrimaryKey(
				newBatchPlannerMapping.getPrimaryKey());

		Assert.assertEquals(
			existingBatchPlannerMapping.getMvccVersion(),
			newBatchPlannerMapping.getMvccVersion());
		Assert.assertEquals(
			existingBatchPlannerMapping.getBatchPlannerMappingId(),
			newBatchPlannerMapping.getBatchPlannerMappingId());
		Assert.assertEquals(
			existingBatchPlannerMapping.getCompanyId(),
			newBatchPlannerMapping.getCompanyId());
		Assert.assertEquals(
			existingBatchPlannerMapping.getUserId(),
			newBatchPlannerMapping.getUserId());
		Assert.assertEquals(
			existingBatchPlannerMapping.getUserName(),
			newBatchPlannerMapping.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingBatchPlannerMapping.getCreateDate()),
			Time.getShortTimestamp(newBatchPlannerMapping.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingBatchPlannerMapping.getModifiedDate()),
			Time.getShortTimestamp(newBatchPlannerMapping.getModifiedDate()));
		Assert.assertEquals(
			existingBatchPlannerMapping.getBatchPlannerPlanId(),
			newBatchPlannerMapping.getBatchPlannerPlanId());
		Assert.assertEquals(
			existingBatchPlannerMapping.getExternalFieldName(),
			newBatchPlannerMapping.getExternalFieldName());
		Assert.assertEquals(
			existingBatchPlannerMapping.getExternalFieldType(),
			newBatchPlannerMapping.getExternalFieldType());
		Assert.assertEquals(
			existingBatchPlannerMapping.getInternalFieldName(),
			newBatchPlannerMapping.getInternalFieldName());
		Assert.assertEquals(
			existingBatchPlannerMapping.getInternalFieldType(),
			newBatchPlannerMapping.getInternalFieldType());
		Assert.assertEquals(
			existingBatchPlannerMapping.getScript(),
			newBatchPlannerMapping.getScript());
	}

	@Test
	public void testCountByBatchPlannerPlanId() throws Exception {
		_persistence.countByBatchPlannerPlanId(RandomTestUtil.nextLong());

		_persistence.countByBatchPlannerPlanId(0L);
	}

	@Test
	public void testCountByBPPI_EFN_IFN() throws Exception {
		_persistence.countByBPPI_EFN_IFN(RandomTestUtil.nextLong(), "", "");

		_persistence.countByBPPI_EFN_IFN(0L, "null", "null");

		_persistence.countByBPPI_EFN_IFN(0L, (String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		BatchPlannerMapping existingBatchPlannerMapping =
			_persistence.findByPrimaryKey(
				newBatchPlannerMapping.getPrimaryKey());

		Assert.assertEquals(
			existingBatchPlannerMapping, newBatchPlannerMapping);
	}

	@Test(expected = NoSuchMappingException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<BatchPlannerMapping> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"BatchPlannerMapping", "mvccVersion", true, "batchPlannerMappingId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "batchPlannerPlanId",
			true, "externalFieldName", true, "externalFieldType", true,
			"internalFieldName", true, "internalFieldType", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		BatchPlannerMapping existingBatchPlannerMapping =
			_persistence.fetchByPrimaryKey(
				newBatchPlannerMapping.getPrimaryKey());

		Assert.assertEquals(
			existingBatchPlannerMapping, newBatchPlannerMapping);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BatchPlannerMapping missingBatchPlannerMapping =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBatchPlannerMapping);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		BatchPlannerMapping newBatchPlannerMapping1 = addBatchPlannerMapping();
		BatchPlannerMapping newBatchPlannerMapping2 = addBatchPlannerMapping();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBatchPlannerMapping1.getPrimaryKey());
		primaryKeys.add(newBatchPlannerMapping2.getPrimaryKey());

		Map<Serializable, BatchPlannerMapping> batchPlannerMappings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, batchPlannerMappings.size());
		Assert.assertEquals(
			newBatchPlannerMapping1,
			batchPlannerMappings.get(newBatchPlannerMapping1.getPrimaryKey()));
		Assert.assertEquals(
			newBatchPlannerMapping2,
			batchPlannerMappings.get(newBatchPlannerMapping2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, BatchPlannerMapping> batchPlannerMappings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(batchPlannerMappings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBatchPlannerMapping.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, BatchPlannerMapping> batchPlannerMappings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, batchPlannerMappings.size());
		Assert.assertEquals(
			newBatchPlannerMapping,
			batchPlannerMappings.get(newBatchPlannerMapping.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, BatchPlannerMapping> batchPlannerMappings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(batchPlannerMappings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBatchPlannerMapping.getPrimaryKey());

		Map<Serializable, BatchPlannerMapping> batchPlannerMappings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, batchPlannerMappings.size());
		Assert.assertEquals(
			newBatchPlannerMapping,
			batchPlannerMappings.get(newBatchPlannerMapping.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			BatchPlannerMappingLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<BatchPlannerMapping>() {

				@Override
				public void performAction(
					BatchPlannerMapping batchPlannerMapping) {

					Assert.assertNotNull(batchPlannerMapping);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BatchPlannerMapping.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"batchPlannerMappingId",
				newBatchPlannerMapping.getBatchPlannerMappingId()));

		List<BatchPlannerMapping> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		BatchPlannerMapping existingBatchPlannerMapping = result.get(0);

		Assert.assertEquals(
			existingBatchPlannerMapping, newBatchPlannerMapping);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BatchPlannerMapping.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"batchPlannerMappingId", RandomTestUtil.nextLong()));

		List<BatchPlannerMapping> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BatchPlannerMapping.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("batchPlannerMappingId"));

		Object newBatchPlannerMappingId =
			newBatchPlannerMapping.getBatchPlannerMappingId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"batchPlannerMappingId",
				new Object[] {newBatchPlannerMappingId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingBatchPlannerMappingId = result.get(0);

		Assert.assertEquals(
			existingBatchPlannerMappingId, newBatchPlannerMappingId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BatchPlannerMapping.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("batchPlannerMappingId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"batchPlannerMappingId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newBatchPlannerMapping.getPrimaryKey()));
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

		BatchPlannerMapping newBatchPlannerMapping = addBatchPlannerMapping();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BatchPlannerMapping.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"batchPlannerMappingId",
				newBatchPlannerMapping.getBatchPlannerMappingId()));

		List<BatchPlannerMapping> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(
		BatchPlannerMapping batchPlannerMapping) {

		Assert.assertEquals(
			Long.valueOf(batchPlannerMapping.getBatchPlannerPlanId()),
			ReflectionTestUtil.<Long>invoke(
				batchPlannerMapping, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "batchPlannerPlanId"));
		Assert.assertEquals(
			batchPlannerMapping.getExternalFieldName(),
			ReflectionTestUtil.invoke(
				batchPlannerMapping, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "externalFieldName"));
		Assert.assertEquals(
			batchPlannerMapping.getInternalFieldName(),
			ReflectionTestUtil.invoke(
				batchPlannerMapping, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "internalFieldName"));
	}

	protected BatchPlannerMapping addBatchPlannerMapping() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BatchPlannerMapping batchPlannerMapping = _persistence.create(pk);

		batchPlannerMapping.setMvccVersion(RandomTestUtil.nextLong());

		batchPlannerMapping.setCompanyId(RandomTestUtil.nextLong());

		batchPlannerMapping.setUserId(RandomTestUtil.nextLong());

		batchPlannerMapping.setUserName(RandomTestUtil.randomString());

		batchPlannerMapping.setCreateDate(RandomTestUtil.nextDate());

		batchPlannerMapping.setModifiedDate(RandomTestUtil.nextDate());

		batchPlannerMapping.setBatchPlannerPlanId(RandomTestUtil.nextLong());

		batchPlannerMapping.setExternalFieldName(RandomTestUtil.randomString());

		batchPlannerMapping.setExternalFieldType(RandomTestUtil.randomString());

		batchPlannerMapping.setInternalFieldName(RandomTestUtil.randomString());

		batchPlannerMapping.setInternalFieldType(RandomTestUtil.randomString());

		batchPlannerMapping.setScript(RandomTestUtil.randomString());

		_batchPlannerMappings.add(_persistence.update(batchPlannerMapping));

		return batchPlannerMapping;
	}

	private List<BatchPlannerMapping> _batchPlannerMappings =
		new ArrayList<BatchPlannerMapping>();
	private BatchPlannerMappingPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}