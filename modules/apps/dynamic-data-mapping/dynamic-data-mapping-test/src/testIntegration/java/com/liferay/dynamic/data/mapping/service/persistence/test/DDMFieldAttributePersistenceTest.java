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
import com.liferay.dynamic.data.mapping.exception.NoSuchFieldAttributeException;
import com.liferay.dynamic.data.mapping.model.DDMFieldAttribute;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFieldAttributePersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFieldAttributeUtil;
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
public class DDMFieldAttributePersistenceTest {

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
		_persistence = DDMFieldAttributeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DDMFieldAttribute> iterator = _ddmFieldAttributes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMFieldAttribute ddmFieldAttribute = _persistence.create(pk);

		Assert.assertNotNull(ddmFieldAttribute);

		Assert.assertEquals(ddmFieldAttribute.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		_persistence.remove(newDDMFieldAttribute);

		DDMFieldAttribute existingDDMFieldAttribute =
			_persistence.fetchByPrimaryKey(
				newDDMFieldAttribute.getPrimaryKey());

		Assert.assertNull(existingDDMFieldAttribute);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDDMFieldAttribute();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMFieldAttribute newDDMFieldAttribute = _persistence.create(pk);

		newDDMFieldAttribute.setMvccVersion(RandomTestUtil.nextLong());

		newDDMFieldAttribute.setCtCollectionId(RandomTestUtil.nextLong());

		newDDMFieldAttribute.setCompanyId(RandomTestUtil.nextLong());

		newDDMFieldAttribute.setFieldId(RandomTestUtil.nextLong());

		newDDMFieldAttribute.setStorageId(RandomTestUtil.nextLong());

		newDDMFieldAttribute.setAttributeName(RandomTestUtil.randomString());

		newDDMFieldAttribute.setLanguageId(RandomTestUtil.randomString());

		newDDMFieldAttribute.setLargeAttributeValue(
			RandomTestUtil.randomString());

		newDDMFieldAttribute.setSmallAttributeValue(
			RandomTestUtil.randomString());

		_ddmFieldAttributes.add(_persistence.update(newDDMFieldAttribute));

		DDMFieldAttribute existingDDMFieldAttribute =
			_persistence.findByPrimaryKey(newDDMFieldAttribute.getPrimaryKey());

		Assert.assertEquals(
			existingDDMFieldAttribute.getMvccVersion(),
			newDDMFieldAttribute.getMvccVersion());
		Assert.assertEquals(
			existingDDMFieldAttribute.getCtCollectionId(),
			newDDMFieldAttribute.getCtCollectionId());
		Assert.assertEquals(
			existingDDMFieldAttribute.getFieldAttributeId(),
			newDDMFieldAttribute.getFieldAttributeId());
		Assert.assertEquals(
			existingDDMFieldAttribute.getCompanyId(),
			newDDMFieldAttribute.getCompanyId());
		Assert.assertEquals(
			existingDDMFieldAttribute.getFieldId(),
			newDDMFieldAttribute.getFieldId());
		Assert.assertEquals(
			existingDDMFieldAttribute.getStorageId(),
			newDDMFieldAttribute.getStorageId());
		Assert.assertEquals(
			existingDDMFieldAttribute.getAttributeName(),
			newDDMFieldAttribute.getAttributeName());
		Assert.assertEquals(
			existingDDMFieldAttribute.getLanguageId(),
			newDDMFieldAttribute.getLanguageId());
		Assert.assertEquals(
			existingDDMFieldAttribute.getLargeAttributeValue(),
			newDDMFieldAttribute.getLargeAttributeValue());
		Assert.assertEquals(
			existingDDMFieldAttribute.getSmallAttributeValue(),
			newDDMFieldAttribute.getSmallAttributeValue());
	}

	@Test
	public void testCountByStorageId() throws Exception {
		_persistence.countByStorageId(RandomTestUtil.nextLong());

		_persistence.countByStorageId(0L);
	}

	@Test
	public void testCountByS_L() throws Exception {
		_persistence.countByS_L(RandomTestUtil.nextLong(), "");

		_persistence.countByS_L(0L, "null");

		_persistence.countByS_L(0L, (String)null);
	}

	@Test
	public void testCountByAN_SAV() throws Exception {
		_persistence.countByAN_SAV("", "");

		_persistence.countByAN_SAV("null", "null");

		_persistence.countByAN_SAV((String)null, (String)null);
	}

	@Test
	public void testCountByF_AN_L() throws Exception {
		_persistence.countByF_AN_L(RandomTestUtil.nextLong(), "", "");

		_persistence.countByF_AN_L(0L, "null", "null");

		_persistence.countByF_AN_L(0L, (String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		DDMFieldAttribute existingDDMFieldAttribute =
			_persistence.findByPrimaryKey(newDDMFieldAttribute.getPrimaryKey());

		Assert.assertEquals(existingDDMFieldAttribute, newDDMFieldAttribute);
	}

	@Test(expected = NoSuchFieldAttributeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DDMFieldAttribute> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"DDMFieldAttribute", "mvccVersion", true, "ctCollectionId", true,
			"fieldAttributeId", true, "companyId", true, "fieldId", true,
			"storageId", true, "attributeName", true, "languageId", true,
			"smallAttributeValue", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		DDMFieldAttribute existingDDMFieldAttribute =
			_persistence.fetchByPrimaryKey(
				newDDMFieldAttribute.getPrimaryKey());

		Assert.assertEquals(existingDDMFieldAttribute, newDDMFieldAttribute);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMFieldAttribute missingDDMFieldAttribute =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDDMFieldAttribute);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DDMFieldAttribute newDDMFieldAttribute1 = addDDMFieldAttribute();
		DDMFieldAttribute newDDMFieldAttribute2 = addDDMFieldAttribute();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMFieldAttribute1.getPrimaryKey());
		primaryKeys.add(newDDMFieldAttribute2.getPrimaryKey());

		Map<Serializable, DDMFieldAttribute> ddmFieldAttributes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ddmFieldAttributes.size());
		Assert.assertEquals(
			newDDMFieldAttribute1,
			ddmFieldAttributes.get(newDDMFieldAttribute1.getPrimaryKey()));
		Assert.assertEquals(
			newDDMFieldAttribute2,
			ddmFieldAttributes.get(newDDMFieldAttribute2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DDMFieldAttribute> ddmFieldAttributes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ddmFieldAttributes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMFieldAttribute.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DDMFieldAttribute> ddmFieldAttributes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ddmFieldAttributes.size());
		Assert.assertEquals(
			newDDMFieldAttribute,
			ddmFieldAttributes.get(newDDMFieldAttribute.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DDMFieldAttribute> ddmFieldAttributes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ddmFieldAttributes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMFieldAttribute.getPrimaryKey());

		Map<Serializable, DDMFieldAttribute> ddmFieldAttributes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ddmFieldAttributes.size());
		Assert.assertEquals(
			newDDMFieldAttribute,
			ddmFieldAttributes.get(newDDMFieldAttribute.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFieldAttribute.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fieldAttributeId",
				newDDMFieldAttribute.getFieldAttributeId()));

		List<DDMFieldAttribute> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		DDMFieldAttribute existingDDMFieldAttribute = result.get(0);

		Assert.assertEquals(existingDDMFieldAttribute, newDDMFieldAttribute);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFieldAttribute.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fieldAttributeId", RandomTestUtil.nextLong()));

		List<DDMFieldAttribute> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFieldAttribute.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("fieldAttributeId"));

		Object newFieldAttributeId = newDDMFieldAttribute.getFieldAttributeId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fieldAttributeId", new Object[] {newFieldAttributeId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFieldAttributeId = result.get(0);

		Assert.assertEquals(existingFieldAttributeId, newFieldAttributeId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFieldAttribute.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("fieldAttributeId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fieldAttributeId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newDDMFieldAttribute.getPrimaryKey()));
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

		DDMFieldAttribute newDDMFieldAttribute = addDDMFieldAttribute();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFieldAttribute.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fieldAttributeId",
				newDDMFieldAttribute.getFieldAttributeId()));

		List<DDMFieldAttribute> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(DDMFieldAttribute ddmFieldAttribute) {
		Assert.assertEquals(
			Long.valueOf(ddmFieldAttribute.getFieldId()),
			ReflectionTestUtil.<Long>invoke(
				ddmFieldAttribute, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "fieldId"));
		Assert.assertEquals(
			ddmFieldAttribute.getAttributeName(),
			ReflectionTestUtil.invoke(
				ddmFieldAttribute, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "attributeName"));
		Assert.assertEquals(
			ddmFieldAttribute.getLanguageId(),
			ReflectionTestUtil.invoke(
				ddmFieldAttribute, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "languageId"));
	}

	protected DDMFieldAttribute addDDMFieldAttribute() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMFieldAttribute ddmFieldAttribute = _persistence.create(pk);

		ddmFieldAttribute.setMvccVersion(RandomTestUtil.nextLong());

		ddmFieldAttribute.setCtCollectionId(RandomTestUtil.nextLong());

		ddmFieldAttribute.setCompanyId(RandomTestUtil.nextLong());

		ddmFieldAttribute.setFieldId(RandomTestUtil.nextLong());

		ddmFieldAttribute.setStorageId(RandomTestUtil.nextLong());

		ddmFieldAttribute.setAttributeName(RandomTestUtil.randomString());

		ddmFieldAttribute.setLanguageId(RandomTestUtil.randomString());

		ddmFieldAttribute.setLargeAttributeValue(RandomTestUtil.randomString());

		ddmFieldAttribute.setSmallAttributeValue(RandomTestUtil.randomString());

		_ddmFieldAttributes.add(_persistence.update(ddmFieldAttribute));

		return ddmFieldAttribute;
	}

	private List<DDMFieldAttribute> _ddmFieldAttributes =
		new ArrayList<DDMFieldAttribute>();
	private DDMFieldAttributePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}