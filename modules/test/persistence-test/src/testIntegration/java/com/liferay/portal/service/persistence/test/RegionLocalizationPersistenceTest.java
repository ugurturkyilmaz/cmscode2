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

package com.liferay.portal.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchRegionLocalizationException;
import com.liferay.portal.kernel.model.RegionLocalization;
import com.liferay.portal.kernel.service.persistence.RegionLocalizationPersistence;
import com.liferay.portal.kernel.service.persistence.RegionLocalizationUtil;
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
public class RegionLocalizationPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = RegionLocalizationUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RegionLocalization> iterator = _regionLocalizations.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RegionLocalization regionLocalization = _persistence.create(pk);

		Assert.assertNotNull(regionLocalization);

		Assert.assertEquals(regionLocalization.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RegionLocalization newRegionLocalization = addRegionLocalization();

		_persistence.remove(newRegionLocalization);

		RegionLocalization existingRegionLocalization =
			_persistence.fetchByPrimaryKey(
				newRegionLocalization.getPrimaryKey());

		Assert.assertNull(existingRegionLocalization);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRegionLocalization();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RegionLocalization newRegionLocalization = _persistence.create(pk);

		newRegionLocalization.setMvccVersion(RandomTestUtil.nextLong());

		newRegionLocalization.setCompanyId(RandomTestUtil.nextLong());

		newRegionLocalization.setRegionId(RandomTestUtil.nextLong());

		newRegionLocalization.setLanguageId(RandomTestUtil.randomString());

		newRegionLocalization.setTitle(RandomTestUtil.randomString());

		_regionLocalizations.add(_persistence.update(newRegionLocalization));

		RegionLocalization existingRegionLocalization =
			_persistence.findByPrimaryKey(
				newRegionLocalization.getPrimaryKey());

		Assert.assertEquals(
			existingRegionLocalization.getMvccVersion(),
			newRegionLocalization.getMvccVersion());
		Assert.assertEquals(
			existingRegionLocalization.getRegionLocalizationId(),
			newRegionLocalization.getRegionLocalizationId());
		Assert.assertEquals(
			existingRegionLocalization.getCompanyId(),
			newRegionLocalization.getCompanyId());
		Assert.assertEquals(
			existingRegionLocalization.getRegionId(),
			newRegionLocalization.getRegionId());
		Assert.assertEquals(
			existingRegionLocalization.getLanguageId(),
			newRegionLocalization.getLanguageId());
		Assert.assertEquals(
			existingRegionLocalization.getTitle(),
			newRegionLocalization.getTitle());
	}

	@Test
	public void testCountByRegionId() throws Exception {
		_persistence.countByRegionId(RandomTestUtil.nextLong());

		_persistence.countByRegionId(0L);
	}

	@Test
	public void testCountByRegionId_LanguageId() throws Exception {
		_persistence.countByRegionId_LanguageId(RandomTestUtil.nextLong(), "");

		_persistence.countByRegionId_LanguageId(0L, "null");

		_persistence.countByRegionId_LanguageId(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RegionLocalization newRegionLocalization = addRegionLocalization();

		RegionLocalization existingRegionLocalization =
			_persistence.findByPrimaryKey(
				newRegionLocalization.getPrimaryKey());

		Assert.assertEquals(existingRegionLocalization, newRegionLocalization);
	}

	@Test(expected = NoSuchRegionLocalizationException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<RegionLocalization> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"RegionLocalization", "mvccVersion", true, "regionLocalizationId",
			true, "companyId", true, "regionId", true, "languageId", true,
			"title", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RegionLocalization newRegionLocalization = addRegionLocalization();

		RegionLocalization existingRegionLocalization =
			_persistence.fetchByPrimaryKey(
				newRegionLocalization.getPrimaryKey());

		Assert.assertEquals(existingRegionLocalization, newRegionLocalization);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RegionLocalization missingRegionLocalization =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRegionLocalization);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		RegionLocalization newRegionLocalization1 = addRegionLocalization();
		RegionLocalization newRegionLocalization2 = addRegionLocalization();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRegionLocalization1.getPrimaryKey());
		primaryKeys.add(newRegionLocalization2.getPrimaryKey());

		Map<Serializable, RegionLocalization> regionLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, regionLocalizations.size());
		Assert.assertEquals(
			newRegionLocalization1,
			regionLocalizations.get(newRegionLocalization1.getPrimaryKey()));
		Assert.assertEquals(
			newRegionLocalization2,
			regionLocalizations.get(newRegionLocalization2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RegionLocalization> regionLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(regionLocalizations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		RegionLocalization newRegionLocalization = addRegionLocalization();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRegionLocalization.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RegionLocalization> regionLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, regionLocalizations.size());
		Assert.assertEquals(
			newRegionLocalization,
			regionLocalizations.get(newRegionLocalization.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RegionLocalization> regionLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(regionLocalizations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		RegionLocalization newRegionLocalization = addRegionLocalization();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRegionLocalization.getPrimaryKey());

		Map<Serializable, RegionLocalization> regionLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, regionLocalizations.size());
		Assert.assertEquals(
			newRegionLocalization,
			regionLocalizations.get(newRegionLocalization.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		RegionLocalization newRegionLocalization = addRegionLocalization();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RegionLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"regionLocalizationId",
				newRegionLocalization.getRegionLocalizationId()));

		List<RegionLocalization> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		RegionLocalization existingRegionLocalization = result.get(0);

		Assert.assertEquals(existingRegionLocalization, newRegionLocalization);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RegionLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"regionLocalizationId", RandomTestUtil.nextLong()));

		List<RegionLocalization> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		RegionLocalization newRegionLocalization = addRegionLocalization();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RegionLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("regionLocalizationId"));

		Object newRegionLocalizationId =
			newRegionLocalization.getRegionLocalizationId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"regionLocalizationId",
				new Object[] {newRegionLocalizationId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRegionLocalizationId = result.get(0);

		Assert.assertEquals(
			existingRegionLocalizationId, newRegionLocalizationId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RegionLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("regionLocalizationId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"regionLocalizationId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		RegionLocalization newRegionLocalization = addRegionLocalization();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newRegionLocalization.getPrimaryKey()));
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

		RegionLocalization newRegionLocalization = addRegionLocalization();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RegionLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"regionLocalizationId",
				newRegionLocalization.getRegionLocalizationId()));

		List<RegionLocalization> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(RegionLocalization regionLocalization) {
		Assert.assertEquals(
			Long.valueOf(regionLocalization.getRegionId()),
			ReflectionTestUtil.<Long>invoke(
				regionLocalization, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "regionId"));
		Assert.assertEquals(
			regionLocalization.getLanguageId(),
			ReflectionTestUtil.invoke(
				regionLocalization, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "languageId"));
	}

	protected RegionLocalization addRegionLocalization() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RegionLocalization regionLocalization = _persistence.create(pk);

		regionLocalization.setMvccVersion(RandomTestUtil.nextLong());

		regionLocalization.setCompanyId(RandomTestUtil.nextLong());

		regionLocalization.setRegionId(RandomTestUtil.nextLong());

		regionLocalization.setLanguageId(RandomTestUtil.randomString());

		regionLocalization.setTitle(RandomTestUtil.randomString());

		_regionLocalizations.add(_persistence.update(regionLocalization));

		return regionLocalization;
	}

	private List<RegionLocalization> _regionLocalizations =
		new ArrayList<RegionLocalization>();
	private RegionLocalizationPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}