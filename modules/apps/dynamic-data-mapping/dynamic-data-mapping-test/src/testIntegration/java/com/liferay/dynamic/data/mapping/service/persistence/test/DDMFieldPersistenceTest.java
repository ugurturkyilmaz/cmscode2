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

package com.liferay.dynamic.data.mapping.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.exception.NoSuchFieldException;
import com.liferay.dynamic.data.mapping.model.DDMField;
import com.liferay.dynamic.data.mapping.service.DDMFieldLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFieldPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFieldUtil;
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
public class DDMFieldPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.dynamic.data.mapping.service"));

	@Before
	public void setUp() {
		_persistence = DDMFieldUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DDMField> iterator = _ddmFields.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMField ddmField = _persistence.create(pk);

		Assert.assertNotNull(ddmField);

		Assert.assertEquals(ddmField.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DDMField newDDMField = addDDMField();

		_persistence.remove(newDDMField);

		DDMField existingDDMField = _persistence.fetchByPrimaryKey(
			newDDMField.getPrimaryKey());

		Assert.assertNull(existingDDMField);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDDMField();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMField newDDMField = _persistence.create(pk);

		newDDMField.setMvccVersion(RandomTestUtil.nextLong());

		newDDMField.setCtCollectionId(RandomTestUtil.nextLong());

		newDDMField.setCompanyId(RandomTestUtil.nextLong());

		newDDMField.setParentFieldId(RandomTestUtil.nextLong());

		newDDMField.setStorageId(RandomTestUtil.nextLong());

		newDDMField.setStructureVersionId(RandomTestUtil.nextLong());

		newDDMField.setFieldName(RandomTestUtil.randomString());

		newDDMField.setFieldType(RandomTestUtil.randomString());

		newDDMField.setInstanceId(RandomTestUtil.randomString());

		newDDMField.setLocalizable(RandomTestUtil.randomBoolean());

		newDDMField.setPriority(RandomTestUtil.nextInt());

		_ddmFields.add(_persistence.update(newDDMField));

		DDMField existingDDMField = _persistence.findByPrimaryKey(
			newDDMField.getPrimaryKey());

		Assert.assertEquals(
			existingDDMField.getMvccVersion(), newDDMField.getMvccVersion());
		Assert.assertEquals(
			existingDDMField.getCtCollectionId(),
			newDDMField.getCtCollectionId());
		Assert.assertEquals(
			existingDDMField.getFieldId(), newDDMField.getFieldId());
		Assert.assertEquals(
			existingDDMField.getCompanyId(), newDDMField.getCompanyId());
		Assert.assertEquals(
			existingDDMField.getParentFieldId(),
			newDDMField.getParentFieldId());
		Assert.assertEquals(
			existingDDMField.getStorageId(), newDDMField.getStorageId());
		Assert.assertEquals(
			existingDDMField.getStructureVersionId(),
			newDDMField.getStructureVersionId());
		Assert.assertEquals(
			existingDDMField.getFieldName(), newDDMField.getFieldName());
		Assert.assertEquals(
			existingDDMField.getFieldType(), newDDMField.getFieldType());
		Assert.assertEquals(
			existingDDMField.getInstanceId(), newDDMField.getInstanceId());
		Assert.assertEquals(
			existingDDMField.isLocalizable(), newDDMField.isLocalizable());
		Assert.assertEquals(
			existingDDMField.getPriority(), newDDMField.getPriority());
	}

	@Test
	public void testCountByStorageId() throws Exception {
		_persistence.countByStorageId(RandomTestUtil.nextLong());

		_persistence.countByStorageId(0L);
	}

	@Test
	public void testCountByStructureVersionId() throws Exception {
		_persistence.countByStructureVersionId(RandomTestUtil.nextLong());

		_persistence.countByStructureVersionId(0L);
	}

	@Test
	public void testCountByC_F() throws Exception {
		_persistence.countByC_F(RandomTestUtil.nextLong(), "");

		_persistence.countByC_F(0L, "null");

		_persistence.countByC_F(0L, (String)null);
	}

	@Test
	public void testCountByS_I() throws Exception {
		_persistence.countByS_I(RandomTestUtil.nextLong(), "");

		_persistence.countByS_I(0L, "null");

		_persistence.countByS_I(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DDMField newDDMField = addDDMField();

		DDMField existingDDMField = _persistence.findByPrimaryKey(
			newDDMField.getPrimaryKey());

		Assert.assertEquals(existingDDMField, newDDMField);
	}

	@Test(expected = NoSuchFieldException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DDMField> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"DDMField", "mvccVersion", true, "ctCollectionId", true, "fieldId",
			true, "companyId", true, "parentFieldId", true, "storageId", true,
			"structureVersionId", true, "fieldType", true, "instanceId", true,
			"localizable", true, "priority", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DDMField newDDMField = addDDMField();

		DDMField existingDDMField = _persistence.fetchByPrimaryKey(
			newDDMField.getPrimaryKey());

		Assert.assertEquals(existingDDMField, newDDMField);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMField missingDDMField = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDDMField);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DDMField newDDMField1 = addDDMField();
		DDMField newDDMField2 = addDDMField();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMField1.getPrimaryKey());
		primaryKeys.add(newDDMField2.getPrimaryKey());

		Map<Serializable, DDMField> ddmFields = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, ddmFields.size());
		Assert.assertEquals(
			newDDMField1, ddmFields.get(newDDMField1.getPrimaryKey()));
		Assert.assertEquals(
			newDDMField2, ddmFields.get(newDDMField2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DDMField> ddmFields = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(ddmFields.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DDMField newDDMField = addDDMField();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMField.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DDMField> ddmFields = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, ddmFields.size());
		Assert.assertEquals(
			newDDMField, ddmFields.get(newDDMField.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DDMField> ddmFields = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(ddmFields.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DDMField newDDMField = addDDMField();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMField.getPrimaryKey());

		Map<Serializable, DDMField> ddmFields = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, ddmFields.size());
		Assert.assertEquals(
			newDDMField, ddmFields.get(newDDMField.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DDMFieldLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DDMField>() {

				@Override
				public void performAction(DDMField ddmField) {
					Assert.assertNotNull(ddmField);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DDMField newDDMField = addDDMField();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMField.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("fieldId", newDDMField.getFieldId()));

		List<DDMField> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DDMField existingDDMField = result.get(0);

		Assert.assertEquals(existingDDMField, newDDMField);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMField.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("fieldId", RandomTestUtil.nextLong()));

		List<DDMField> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DDMField newDDMField = addDDMField();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMField.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("fieldId"));

		Object newFieldId = newDDMField.getFieldId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("fieldId", new Object[] {newFieldId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFieldId = result.get(0);

		Assert.assertEquals(existingFieldId, newFieldId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMField.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("fieldId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fieldId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DDMField newDDMField = addDDMField();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newDDMField.getPrimaryKey()));
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

		DDMField newDDMField = addDDMField();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMField.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("fieldId", newDDMField.getFieldId()));

		List<DDMField> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(DDMField ddmField) {
		Assert.assertEquals(
			Long.valueOf(ddmField.getStorageId()),
			ReflectionTestUtil.<Long>invoke(
				ddmField, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "storageId"));
		Assert.assertEquals(
			ddmField.getInstanceId(),
			ReflectionTestUtil.invoke(
				ddmField, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "instanceId"));
	}

	protected DDMField addDDMField() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMField ddmField = _persistence.create(pk);

		ddmField.setMvccVersion(RandomTestUtil.nextLong());

		ddmField.setCtCollectionId(RandomTestUtil.nextLong());

		ddmField.setCompanyId(RandomTestUtil.nextLong());

		ddmField.setParentFieldId(RandomTestUtil.nextLong());

		ddmField.setStorageId(RandomTestUtil.nextLong());

		ddmField.setStructureVersionId(RandomTestUtil.nextLong());

		ddmField.setFieldName(RandomTestUtil.randomString());

		ddmField.setFieldType(RandomTestUtil.randomString());

		ddmField.setInstanceId(RandomTestUtil.randomString());

		ddmField.setLocalizable(RandomTestUtil.randomBoolean());

		ddmField.setPriority(RandomTestUtil.nextInt());

		_ddmFields.add(_persistence.update(ddmField));

		return ddmField;
	}

	private List<DDMField> _ddmFields = new ArrayList<DDMField>();
	private DDMFieldPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}