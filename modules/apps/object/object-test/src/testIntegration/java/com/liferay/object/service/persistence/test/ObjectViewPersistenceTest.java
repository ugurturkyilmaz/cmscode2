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

package com.liferay.object.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.exception.NoSuchObjectViewException;
import com.liferay.object.model.ObjectView;
import com.liferay.object.service.ObjectViewLocalServiceUtil;
import com.liferay.object.service.persistence.ObjectViewPersistence;
import com.liferay.object.service.persistence.ObjectViewUtil;
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
public class ObjectViewPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.object.service"));

	@Before
	public void setUp() {
		_persistence = ObjectViewUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ObjectView> iterator = _objectViews.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ObjectView objectView = _persistence.create(pk);

		Assert.assertNotNull(objectView);

		Assert.assertEquals(objectView.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ObjectView newObjectView = addObjectView();

		_persistence.remove(newObjectView);

		ObjectView existingObjectView = _persistence.fetchByPrimaryKey(
			newObjectView.getPrimaryKey());

		Assert.assertNull(existingObjectView);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addObjectView();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ObjectView newObjectView = _persistence.create(pk);

		newObjectView.setMvccVersion(RandomTestUtil.nextLong());

		newObjectView.setUuid(RandomTestUtil.randomString());

		newObjectView.setCompanyId(RandomTestUtil.nextLong());

		newObjectView.setUserId(RandomTestUtil.nextLong());

		newObjectView.setUserName(RandomTestUtil.randomString());

		newObjectView.setCreateDate(RandomTestUtil.nextDate());

		newObjectView.setModifiedDate(RandomTestUtil.nextDate());

		newObjectView.setObjectDefinitionId(RandomTestUtil.nextLong());

		newObjectView.setDefaultObjectView(RandomTestUtil.randomBoolean());

		newObjectView.setName(RandomTestUtil.randomString());

		_objectViews.add(_persistence.update(newObjectView));

		ObjectView existingObjectView = _persistence.findByPrimaryKey(
			newObjectView.getPrimaryKey());

		Assert.assertEquals(
			existingObjectView.getMvccVersion(),
			newObjectView.getMvccVersion());
		Assert.assertEquals(
			existingObjectView.getUuid(), newObjectView.getUuid());
		Assert.assertEquals(
			existingObjectView.getObjectViewId(),
			newObjectView.getObjectViewId());
		Assert.assertEquals(
			existingObjectView.getCompanyId(), newObjectView.getCompanyId());
		Assert.assertEquals(
			existingObjectView.getUserId(), newObjectView.getUserId());
		Assert.assertEquals(
			existingObjectView.getUserName(), newObjectView.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingObjectView.getCreateDate()),
			Time.getShortTimestamp(newObjectView.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingObjectView.getModifiedDate()),
			Time.getShortTimestamp(newObjectView.getModifiedDate()));
		Assert.assertEquals(
			existingObjectView.getObjectDefinitionId(),
			newObjectView.getObjectDefinitionId());
		Assert.assertEquals(
			existingObjectView.isDefaultObjectView(),
			newObjectView.isDefaultObjectView());
		Assert.assertEquals(
			existingObjectView.getName(), newObjectView.getName());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByObjectDefinitionId() throws Exception {
		_persistence.countByObjectDefinitionId(RandomTestUtil.nextLong());

		_persistence.countByObjectDefinitionId(0L);
	}

	@Test
	public void testCountByODI_DOV() throws Exception {
		_persistence.countByODI_DOV(
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByODI_DOV(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ObjectView newObjectView = addObjectView();

		ObjectView existingObjectView = _persistence.findByPrimaryKey(
			newObjectView.getPrimaryKey());

		Assert.assertEquals(existingObjectView, newObjectView);
	}

	@Test(expected = NoSuchObjectViewException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<ObjectView> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"ObjectView", "mvccVersion", true, "uuid", true, "objectViewId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "objectDefinitionId",
			true, "defaultObjectView", true, "name", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ObjectView newObjectView = addObjectView();

		ObjectView existingObjectView = _persistence.fetchByPrimaryKey(
			newObjectView.getPrimaryKey());

		Assert.assertEquals(existingObjectView, newObjectView);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ObjectView missingObjectView = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingObjectView);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		ObjectView newObjectView1 = addObjectView();
		ObjectView newObjectView2 = addObjectView();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newObjectView1.getPrimaryKey());
		primaryKeys.add(newObjectView2.getPrimaryKey());

		Map<Serializable, ObjectView> objectViews =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, objectViews.size());
		Assert.assertEquals(
			newObjectView1, objectViews.get(newObjectView1.getPrimaryKey()));
		Assert.assertEquals(
			newObjectView2, objectViews.get(newObjectView2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ObjectView> objectViews =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(objectViews.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		ObjectView newObjectView = addObjectView();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newObjectView.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ObjectView> objectViews =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, objectViews.size());
		Assert.assertEquals(
			newObjectView, objectViews.get(newObjectView.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ObjectView> objectViews =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(objectViews.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		ObjectView newObjectView = addObjectView();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newObjectView.getPrimaryKey());

		Map<Serializable, ObjectView> objectViews =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, objectViews.size());
		Assert.assertEquals(
			newObjectView, objectViews.get(newObjectView.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			ObjectViewLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<ObjectView>() {

				@Override
				public void performAction(ObjectView objectView) {
					Assert.assertNotNull(objectView);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		ObjectView newObjectView = addObjectView();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ObjectView.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"objectViewId", newObjectView.getObjectViewId()));

		List<ObjectView> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		ObjectView existingObjectView = result.get(0);

		Assert.assertEquals(existingObjectView, newObjectView);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ObjectView.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"objectViewId", RandomTestUtil.nextLong()));

		List<ObjectView> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		ObjectView newObjectView = addObjectView();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ObjectView.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("objectViewId"));

		Object newObjectViewId = newObjectView.getObjectViewId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"objectViewId", new Object[] {newObjectViewId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingObjectViewId = result.get(0);

		Assert.assertEquals(existingObjectViewId, newObjectViewId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ObjectView.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("objectViewId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"objectViewId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ObjectView addObjectView() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ObjectView objectView = _persistence.create(pk);

		objectView.setMvccVersion(RandomTestUtil.nextLong());

		objectView.setUuid(RandomTestUtil.randomString());

		objectView.setCompanyId(RandomTestUtil.nextLong());

		objectView.setUserId(RandomTestUtil.nextLong());

		objectView.setUserName(RandomTestUtil.randomString());

		objectView.setCreateDate(RandomTestUtil.nextDate());

		objectView.setModifiedDate(RandomTestUtil.nextDate());

		objectView.setObjectDefinitionId(RandomTestUtil.nextLong());

		objectView.setDefaultObjectView(RandomTestUtil.randomBoolean());

		objectView.setName(RandomTestUtil.randomString());

		_objectViews.add(_persistence.update(objectView));

		return objectView;
	}

	private List<ObjectView> _objectViews = new ArrayList<ObjectView>();
	private ObjectViewPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}