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
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchAddressException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.AddressPersistence;
import com.liferay.portal.kernel.service.persistence.AddressUtil;
import com.liferay.portal.kernel.test.AssertUtils;
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
public class AddressPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = AddressUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Address> iterator = _addresses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Address address = _persistence.create(pk);

		Assert.assertNotNull(address);

		Assert.assertEquals(address.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Address newAddress = addAddress();

		_persistence.remove(newAddress);

		Address existingAddress = _persistence.fetchByPrimaryKey(
			newAddress.getPrimaryKey());

		Assert.assertNull(existingAddress);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAddress();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Address newAddress = _persistence.create(pk);

		newAddress.setMvccVersion(RandomTestUtil.nextLong());

		newAddress.setUuid(RandomTestUtil.randomString());

		newAddress.setExternalReferenceCode(RandomTestUtil.randomString());

		newAddress.setCompanyId(RandomTestUtil.nextLong());

		newAddress.setUserId(RandomTestUtil.nextLong());

		newAddress.setUserName(RandomTestUtil.randomString());

		newAddress.setCreateDate(RandomTestUtil.nextDate());

		newAddress.setModifiedDate(RandomTestUtil.nextDate());

		newAddress.setClassNameId(RandomTestUtil.nextLong());

		newAddress.setClassPK(RandomTestUtil.nextLong());

		newAddress.setCountryId(RandomTestUtil.nextLong());

		newAddress.setRegionId(RandomTestUtil.nextLong());

		newAddress.setTypeId(RandomTestUtil.nextLong());

		newAddress.setCity(RandomTestUtil.randomString());

		newAddress.setDescription(RandomTestUtil.randomString());

		newAddress.setLatitude(RandomTestUtil.nextDouble());

		newAddress.setLongitude(RandomTestUtil.nextDouble());

		newAddress.setMailing(RandomTestUtil.randomBoolean());

		newAddress.setName(RandomTestUtil.randomString());

		newAddress.setPrimary(RandomTestUtil.randomBoolean());

		newAddress.setStreet1(RandomTestUtil.randomString());

		newAddress.setStreet2(RandomTestUtil.randomString());

		newAddress.setStreet3(RandomTestUtil.randomString());

		newAddress.setValidationDate(RandomTestUtil.nextDate());

		newAddress.setValidationStatus(RandomTestUtil.nextInt());

		newAddress.setZip(RandomTestUtil.randomString());

		_addresses.add(_persistence.update(newAddress));

		Address existingAddress = _persistence.findByPrimaryKey(
			newAddress.getPrimaryKey());

		Assert.assertEquals(
			existingAddress.getMvccVersion(), newAddress.getMvccVersion());
		Assert.assertEquals(existingAddress.getUuid(), newAddress.getUuid());
		Assert.assertEquals(
			existingAddress.getExternalReferenceCode(),
			newAddress.getExternalReferenceCode());
		Assert.assertEquals(
			existingAddress.getAddressId(), newAddress.getAddressId());
		Assert.assertEquals(
			existingAddress.getCompanyId(), newAddress.getCompanyId());
		Assert.assertEquals(
			existingAddress.getUserId(), newAddress.getUserId());
		Assert.assertEquals(
			existingAddress.getUserName(), newAddress.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingAddress.getCreateDate()),
			Time.getShortTimestamp(newAddress.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingAddress.getModifiedDate()),
			Time.getShortTimestamp(newAddress.getModifiedDate()));
		Assert.assertEquals(
			existingAddress.getClassNameId(), newAddress.getClassNameId());
		Assert.assertEquals(
			existingAddress.getClassPK(), newAddress.getClassPK());
		Assert.assertEquals(
			existingAddress.getCountryId(), newAddress.getCountryId());
		Assert.assertEquals(
			existingAddress.getRegionId(), newAddress.getRegionId());
		Assert.assertEquals(
			existingAddress.getTypeId(), newAddress.getTypeId());
		Assert.assertEquals(existingAddress.getCity(), newAddress.getCity());
		Assert.assertEquals(
			existingAddress.getDescription(), newAddress.getDescription());
		AssertUtils.assertEquals(
			existingAddress.getLatitude(), newAddress.getLatitude());
		AssertUtils.assertEquals(
			existingAddress.getLongitude(), newAddress.getLongitude());
		Assert.assertEquals(
			existingAddress.isMailing(), newAddress.isMailing());
		Assert.assertEquals(existingAddress.getName(), newAddress.getName());
		Assert.assertEquals(
			existingAddress.isPrimary(), newAddress.isPrimary());
		Assert.assertEquals(
			existingAddress.getStreet1(), newAddress.getStreet1());
		Assert.assertEquals(
			existingAddress.getStreet2(), newAddress.getStreet2());
		Assert.assertEquals(
			existingAddress.getStreet3(), newAddress.getStreet3());
		Assert.assertEquals(
			Time.getShortTimestamp(existingAddress.getValidationDate()),
			Time.getShortTimestamp(newAddress.getValidationDate()));
		Assert.assertEquals(
			existingAddress.getValidationStatus(),
			newAddress.getValidationStatus());
		Assert.assertEquals(existingAddress.getZip(), newAddress.getZip());
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
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByCountryId() throws Exception {
		_persistence.countByCountryId(RandomTestUtil.nextLong());

		_persistence.countByCountryId(0L);
	}

	@Test
	public void testCountByRegionId() throws Exception {
		_persistence.countByRegionId(RandomTestUtil.nextLong());

		_persistence.countByRegionId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByC_C_C() throws Exception {
		_persistence.countByC_C_C(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C_C(0L, 0L, 0L);
	}

	@Test
	public void testCountByC_C_C_T() throws Exception {
		_persistence.countByC_C_C_T(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C_C_T(0L, 0L, 0L, 0L);
	}

	@Test
	public void testCountByC_C_C_TArrayable() throws Exception {
		_persistence.countByC_C_C_T(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(),
			new long[] {RandomTestUtil.nextLong(), 0L});
	}

	@Test
	public void testCountByC_C_C_M() throws Exception {
		_persistence.countByC_C_C_M(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByC_C_C_M(0L, 0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_C_C_P() throws Exception {
		_persistence.countByC_C_C_P(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByC_C_C_P(0L, 0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Address newAddress = addAddress();

		Address existingAddress = _persistence.findByPrimaryKey(
			newAddress.getPrimaryKey());

		Assert.assertEquals(existingAddress, newAddress);
	}

	@Test(expected = NoSuchAddressException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Address> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"Address", "mvccVersion", true, "uuid", true,
			"externalReferenceCode", true, "addressId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "classNameId", true, "classPK", true,
			"countryId", true, "regionId", true, "typeId", true, "city", true,
			"description", true, "latitude", true, "longitude", true, "mailing",
			true, "name", true, "primary", true, "street1", true, "street2",
			true, "street3", true, "validationDate", true, "validationStatus",
			true, "zip", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Address newAddress = addAddress();

		Address existingAddress = _persistence.fetchByPrimaryKey(
			newAddress.getPrimaryKey());

		Assert.assertEquals(existingAddress, newAddress);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Address missingAddress = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAddress);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Address newAddress1 = addAddress();
		Address newAddress2 = addAddress();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAddress1.getPrimaryKey());
		primaryKeys.add(newAddress2.getPrimaryKey());

		Map<Serializable, Address> addresses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, addresses.size());
		Assert.assertEquals(
			newAddress1, addresses.get(newAddress1.getPrimaryKey()));
		Assert.assertEquals(
			newAddress2, addresses.get(newAddress2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Address> addresses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(addresses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Address newAddress = addAddress();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAddress.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Address> addresses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, addresses.size());
		Assert.assertEquals(
			newAddress, addresses.get(newAddress.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Address> addresses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(addresses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Address newAddress = addAddress();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAddress.getPrimaryKey());

		Map<Serializable, Address> addresses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, addresses.size());
		Assert.assertEquals(
			newAddress, addresses.get(newAddress.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			AddressLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Address>() {

				@Override
				public void performAction(Address address) {
					Assert.assertNotNull(address);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Address newAddress = addAddress();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Address.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("addressId", newAddress.getAddressId()));

		List<Address> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Address existingAddress = result.get(0);

		Assert.assertEquals(existingAddress, newAddress);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Address.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("addressId", RandomTestUtil.nextLong()));

		List<Address> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Address newAddress = addAddress();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Address.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("addressId"));

		Object newAddressId = newAddress.getAddressId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"addressId", new Object[] {newAddressId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAddressId = result.get(0);

		Assert.assertEquals(existingAddressId, newAddressId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Address.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("addressId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"addressId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Address newAddress = addAddress();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newAddress.getPrimaryKey()));
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

		Address newAddress = addAddress();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Address.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("addressId", newAddress.getAddressId()));

		List<Address> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Address address) {
		Assert.assertEquals(
			Long.valueOf(address.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				address, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "companyId"));
		Assert.assertEquals(
			address.getExternalReferenceCode(),
			ReflectionTestUtil.invoke(
				address, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "externalReferenceCode"));
	}

	protected Address addAddress() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Address address = _persistence.create(pk);

		address.setMvccVersion(RandomTestUtil.nextLong());

		address.setUuid(RandomTestUtil.randomString());

		address.setExternalReferenceCode(RandomTestUtil.randomString());

		address.setCompanyId(RandomTestUtil.nextLong());

		address.setUserId(RandomTestUtil.nextLong());

		address.setUserName(RandomTestUtil.randomString());

		address.setCreateDate(RandomTestUtil.nextDate());

		address.setModifiedDate(RandomTestUtil.nextDate());

		address.setClassNameId(RandomTestUtil.nextLong());

		address.setClassPK(RandomTestUtil.nextLong());

		address.setCountryId(RandomTestUtil.nextLong());

		address.setRegionId(RandomTestUtil.nextLong());

		address.setTypeId(RandomTestUtil.nextLong());

		address.setCity(RandomTestUtil.randomString());

		address.setDescription(RandomTestUtil.randomString());

		address.setLatitude(RandomTestUtil.nextDouble());

		address.setLongitude(RandomTestUtil.nextDouble());

		address.setMailing(RandomTestUtil.randomBoolean());

		address.setName(RandomTestUtil.randomString());

		address.setPrimary(RandomTestUtil.randomBoolean());

		address.setStreet1(RandomTestUtil.randomString());

		address.setStreet2(RandomTestUtil.randomString());

		address.setStreet3(RandomTestUtil.randomString());

		address.setValidationDate(RandomTestUtil.nextDate());

		address.setValidationStatus(RandomTestUtil.nextInt());

		address.setZip(RandomTestUtil.randomString());

		_addresses.add(_persistence.update(address));

		return address;
	}

	private List<Address> _addresses = new ArrayList<Address>();
	private AddressPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}