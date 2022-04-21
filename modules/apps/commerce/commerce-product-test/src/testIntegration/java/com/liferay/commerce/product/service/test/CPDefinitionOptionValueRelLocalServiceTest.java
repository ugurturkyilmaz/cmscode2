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

package com.liferay.commerce.product.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.exception.CPDefinitionOptionValueRelPriceException;
import com.liferay.commerce.product.exception.CPDefinitionOptionValueRelQuantityException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CPOptionLocalService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.product.type.simple.constants.SimpleCPTypeConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Matija Petanjek
 * @author Alessio Antonio Rendina
 * @author Igor Beslic
 */
@RunWith(Arquillian.class)
public class CPDefinitionOptionValueRelLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getGroupId(), _user.getUserId());

		_commerceCatalog = _commerceCatalogLocalService.addCommerceCatalog(
			null, RandomTestUtil.randomString(), null,
			LocaleUtil.US.getDisplayLanguage(), _serviceContext);
	}

	@After
	public void tearDown() throws Exception {
		_serviceContext = null;

		_cpOptionLocalService.deleteCPOptions(_group.getCompanyId());
	}

	@Test
	public void testPriceContributorOptionCPDefinitionAsCPDefinitionOptionValueRel()
		throws Exception {

		frutillaRule.scenario(
			"Product bundle option value links product with chargeable option"
		).given(
			"Two products, each with one option with price type set to static"
		).when(
			"Product 1 option is not required and is not SKU contributor"
		).and(
			"Product 2 option is not required and is not SKU contributor"
		).then(
			"Product specialist can link product 1 option value to product 2"
		).and(
			"Product specialist can link product 2 option value to product 1"
		);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel1 =
			_addCPDefinitionWithOptionValue();

		CPDefinitionOptionRel cpDefinitionOptionRel1 =
			cpDefinitionOptionValueRel1.getCPDefinitionOptionRel();

		_cpDefinitionOptionRelLocalService.updateCPDefinitionOptionRel(
			cpDefinitionOptionRel1.getCPDefinitionOptionRelId(),
			cpDefinitionOptionRel1.getCPOptionId(),
			cpDefinitionOptionRel1.getNameMap(),
			cpDefinitionOptionRel1.getDescriptionMap(),
			cpDefinitionOptionRel1.getDDMFormFieldTypeName(),
			cpDefinitionOptionRel1.getPriority(),
			cpDefinitionOptionRel1.isFacetable(), false, false,
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC, _serviceContext);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel2 =
			_addCPDefinitionWithOptionValue();

		CPDefinitionOptionRel cpDefinitionOptionRel2 =
			cpDefinitionOptionValueRel2.getCPDefinitionOptionRel();

		_cpDefinitionOptionRelLocalService.updateCPDefinitionOptionRel(
			cpDefinitionOptionRel2.getCPDefinitionOptionRelId(),
			cpDefinitionOptionRel2.getCPOptionId(),
			cpDefinitionOptionRel2.getNameMap(),
			cpDefinitionOptionRel2.getDescriptionMap(),
			cpDefinitionOptionRel2.getDDMFormFieldTypeName(),
			cpDefinitionOptionRel2.getPriority(),
			cpDefinitionOptionRel2.isFacetable(), false, false,
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC, _serviceContext);

		CPInstance cpDefinitionCPInstance2 = _getCPInstance(
			cpDefinitionOptionRel2.getCPDefinitionId());

		cpDefinitionOptionValueRel1 = _updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRel1,
			cpDefinitionCPInstance2.getCPInstanceId(),
			cpDefinitionOptionValueRel1.isPreselected(), BigDecimal.TEN, 1);

		Assert.assertEquals(
			cpDefinitionOptionValueRel1.getCPInstanceUuid(),
			cpDefinitionCPInstance2.getCPInstanceUuid());

		CPInstance cpDefinitionCPInstance1 = _getCPInstance(
			cpDefinitionOptionRel1.getCPDefinitionId());

		cpDefinitionOptionValueRel2 = _updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRel2,
			cpDefinitionCPInstance1.getCPInstanceId(),
			cpDefinitionOptionValueRel2.isPreselected(), BigDecimal.ONE, 1);

		Assert.assertEquals(
			cpDefinitionOptionValueRel2.getCPInstanceUuid(),
			cpDefinitionCPInstance1.getCPInstanceUuid());
	}

	@Test
	public void testResetCPDefinitionOptionValueRelOnDelete() throws Exception {
		frutillaRule.scenario(
			"Delete a product instance which is referenced as an option " +
				"value of another product (product bundle)"
		).given(
			"A product bundle and a product instance"
		).and(
			"Product instance is referenced as an option value of the " +
				"product bundle"
		).when(
			"The referenced product instance is deleted"
		).then(
			"Product bundle's option value attributes should be reset to " +
				"default values"
		);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			_addCPDefinitionWithOptionValue();

		CPInstance cpInstance = CPTestUtil.addCPInstanceFromCatalog(
			_commerceCatalog.getGroupId());

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		_cpDefinitionOptionRelLocalService.updateCPDefinitionOptionRel(
			cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
			cpDefinitionOptionRel.getCPOptionId(),
			cpDefinitionOptionRel.getNameMap(),
			cpDefinitionOptionRel.getDescriptionMap(),
			cpDefinitionOptionRel.getDDMFormFieldTypeName(),
			cpDefinitionOptionRel.getPriority(),
			cpDefinitionOptionRel.isFacetable(),
			cpDefinitionOptionRel.isRequired(),
			cpDefinitionOptionRel.isSkuContributor(),
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC, _serviceContext);

		cpDefinitionOptionValueRel = _updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRel, cpInstance.getCPInstanceId(),
			cpDefinitionOptionValueRel.isPreselected(), BigDecimal.TEN, 1);

		Assert.assertEquals(
			cpInstance.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getCPInstanceUuid());

		CPDefinition cpInstanceCPDefinition = cpInstance.getCPDefinition();

		Assert.assertEquals(
			cpInstanceCPDefinition.getCProductId(),
			cpDefinitionOptionValueRel.getCProductId());

		Assert.assertEquals(
			BigDecimal.TEN, cpDefinitionOptionValueRel.getPrice());
		Assert.assertEquals(1, cpDefinitionOptionValueRel.getQuantity());

		_cpInstanceLocalService.deleteCPInstance(cpInstance);

		cpDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelLocalService.
				getCPDefinitionOptionValueRel(
					cpDefinitionOptionValueRel.
						getCPDefinitionOptionValueRelId());

		Assert.assertEquals(
			StringPool.BLANK, cpDefinitionOptionValueRel.getCPInstanceUuid());
		Assert.assertEquals(0, cpDefinitionOptionValueRel.getCProductId());

		Assert.assertEquals(
			CPTestUtil.stripTrailingZeros(BigDecimal.ZERO),
			CPTestUtil.stripTrailingZeros(
				cpDefinitionOptionValueRel.getPrice()));

		Assert.assertEquals(0, cpDefinitionOptionValueRel.getQuantity());
	}

	@Test
	public void testResetCPDefinitionOptionValueRelOnUpdateStatusToApproved()
		throws Exception {

		frutillaRule.scenario(
			"Update a status of APPROVED product instance which is " +
				"referenced as an option value of another product (product " +
					"bundle)"
		).given(
			"A product bundle and a product instance"
		).and(
			"Product instance is referenced as an option value of the " +
				"product bundle"
		).when(
			"The referenced product instance status stays in APPROVED state"
		).then(
			"Product bundle's option value attributes should not be changed"
		);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			_addCPDefinitionWithOptionValue();

		CPInstance cpInstance = CPTestUtil.addCPInstanceFromCatalog(
			_commerceCatalog.getGroupId());

		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, cpInstance.getStatus());

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		_cpDefinitionOptionRelLocalService.updateCPDefinitionOptionRel(
			cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
			cpDefinitionOptionRel.getCPOptionId(),
			cpDefinitionOptionRel.getNameMap(),
			cpDefinitionOptionRel.getDescriptionMap(),
			cpDefinitionOptionRel.getDDMFormFieldTypeName(),
			cpDefinitionOptionRel.getPriority(),
			cpDefinitionOptionRel.isFacetable(),
			cpDefinitionOptionRel.isRequired(),
			cpDefinitionOptionRel.isSkuContributor(),
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC, _serviceContext);

		cpDefinitionOptionValueRel = _updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRel, cpInstance.getCPInstanceId(),
			cpDefinitionOptionValueRel.isPreselected(), BigDecimal.TEN, 1);

		Assert.assertEquals(
			cpInstance.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getCPInstanceUuid());

		CPDefinition cpInstanceCPDefinition = cpInstance.getCPDefinition();

		Assert.assertEquals(
			cpInstanceCPDefinition.getCProductId(),
			cpDefinitionOptionValueRel.getCProductId());

		Assert.assertEquals(
			BigDecimal.TEN, cpDefinitionOptionValueRel.getPrice());
		Assert.assertEquals(1, cpDefinitionOptionValueRel.getQuantity());

		_cpInstanceLocalService.updateStatus(
			_serviceContext.getUserId(), cpInstance.getCPInstanceId(),
			WorkflowConstants.STATUS_APPROVED);

		cpDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelLocalService.
				getCPDefinitionOptionValueRel(
					cpDefinitionOptionValueRel.
						getCPDefinitionOptionValueRelId());

		Assert.assertEquals(
			cpInstance.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getCPInstanceUuid());

		cpInstanceCPDefinition = cpInstance.getCPDefinition();

		Assert.assertEquals(
			cpInstanceCPDefinition.getCProductId(),
			cpDefinitionOptionValueRel.getCProductId());

		Assert.assertEquals(
			CPTestUtil.stripTrailingZeros(BigDecimal.TEN),
			CPTestUtil.stripTrailingZeros(
				cpDefinitionOptionValueRel.getPrice()));
		Assert.assertEquals(1, cpDefinitionOptionValueRel.getQuantity());
	}

	@Test
	public void testResetCPDefinitionOptionValueRelOnUpdateStatusToExpired()
		throws Exception {

		frutillaRule.scenario(
			"Update a status of APPROVED product instance which is " +
				"referenced as an option value of another product (product " +
					"bundle)"
		).given(
			"A product bundle and a product instance"
		).and(
			"Product instance is referenced as an option value of the " +
				"product bundle"
		).when(
			"The referenced product instance status is changed from APPROVED " +
				"to EXPIRED"
		).then(
			"Product bundle's option value attributes should be reset to " +
				"default values"
		);

		testResetCPDefinitionOptionValueRelOnStatusChange(
			WorkflowConstants.STATUS_EXPIRED);
	}

	@Test
	public void testResetCPDefinitionOptionValueRelOnUpdateStatusToInactive()
		throws Exception {

		frutillaRule.scenario(
			"Update a status of APPROVED product instance which is " +
				"referenced as an option value of another product (product " +
					"bundle)"
		).given(
			"A product bundle and a product instance"
		).and(
			"Product instance is referenced as an option value of the " +
				"product bundle"
		).when(
			"The referenced product instance status is changed from APPROVED " +
				"to INACTIVE"
		).then(
			"Product bundle's option value attributes should be reset to " +
				"default values"
		);

		testResetCPDefinitionOptionValueRelOnStatusChange(
			WorkflowConstants.STATUS_INACTIVE);
	}

	@Test
	public void testResetCPDefinitionOptionValueRelOnUpdateStatusToScheduled()
		throws Exception {

		frutillaRule.scenario(
			"Update a status of APPROVED product instance which is " +
				"referenced as an option value of another product (product " +
					"bundle)"
		).given(
			"A product bundle and a product instance"
		).and(
			"Product instance is referenced as an option value of the " +
				"product bundle"
		).when(
			"The referenced product instance status is changed from APPROVED " +
				"to SCHEDULED"
		).then(
			"Product bundle's option value attributes should be reset to " +
				"default values"
		);

		testResetCPDefinitionOptionValueRelOnStatusChange(
			WorkflowConstants.STATUS_SCHEDULED);
	}

	@Test
	public void testUpdateDynamicPriceTypeCPDefinitionOptionValueRel()
		throws Exception {

		frutillaRule.scenario(
			"Update an option value"
		).given(
			"An option with dynamic price type set"
		).when(
			"The option value is updated"
		).then(
			"All needed cpInstance attributes is set to the option value"
		);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			_addCPDefinitionWithOptionValue();

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		_cpDefinitionOptionRelLocalService.updateCPDefinitionOptionRel(
			cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
			cpDefinitionOptionRel.getCPOptionId(),
			cpDefinitionOptionRel.getNameMap(),
			cpDefinitionOptionRel.getDescriptionMap(),
			cpDefinitionOptionRel.getDDMFormFieldTypeName(),
			cpDefinitionOptionRel.getPriority(),
			cpDefinitionOptionRel.isFacetable(),
			cpDefinitionOptionRel.isRequired(),
			cpDefinitionOptionRel.isSkuContributor(),
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_DYNAMIC, _serviceContext);

		CPInstance cpInstance = CPTestUtil.addCPInstanceFromCatalog(
			_commerceCatalog.getGroupId());

		cpDefinitionOptionValueRel = _updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRel, cpInstance.getCPInstanceId(),
			cpDefinitionOptionValueRel.isPreselected(), null, 1);

		Assert.assertEquals(
			cpInstance.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getCPInstanceUuid());

		CPDefinition cpInstanceCPDefinition = cpInstance.getCPDefinition();

		Assert.assertEquals(
			cpInstanceCPDefinition.getCProductId(),
			cpDefinitionOptionValueRel.getCProductId());
	}

	@Test
	public void testUpdateNoPriceTypeCPDefinitionOptionValueRel()
		throws Exception {

		frutillaRule.scenario(
			"Update an option value"
		).given(
			"An option with no price type set"
		).when(
			"The option value is updated"
		).then(
			"Any of cpInstance attributes is set to the option value"
		);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			_addCPDefinitionWithOptionValue();

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		Assert.assertTrue(
			Validator.isNull(cpDefinitionOptionRel.getPriceType()));

		Assert.assertTrue(
			Validator.isNull(cpDefinitionOptionValueRel.getCPInstanceUuid()));
		Assert.assertEquals(0, cpDefinitionOptionValueRel.getCProductId());
	}

	@Test
	public void testUpdatePreselectedCPDefinitionOptionValueRel()
		throws Exception {

		CPDefinition cpDefinition = CPTestUtil.addCPDefinitionFromCatalog(
			_commerceCatalog.getGroupId(), SimpleCPTypeConstants.NAME, true,
			true);

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			CPTestUtil.addCPOption(
				_commerceCatalog.getGroupId(), cpDefinition.getCPDefinitionId(),
				1, 5);

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionRels.get(0);

		Assert.assertEquals(
			"product option values count", 5,
			cpDefinitionOptionRel.getCPDefinitionOptionValueRelsCount());

		Assert.assertFalse(
			"preselected option value exists",
			_cpDefinitionOptionValueRelLocalService.
				hasPreselectedCPDefinitionOptionValueRel(
					cpDefinitionOptionRel.getCPDefinitionOptionRelId()));

		CPDefinitionOptionValueRel randomCPDefinitionOptionValueRel =
			CPTestUtil.getRandomCPDefinitionOptionValueRel(
				cpDefinition.getCPDefinitionId());

		_updateCPDefinitionOptionValueRel(
			randomCPDefinitionOptionValueRel, 0, true,
			randomCPDefinitionOptionValueRel.getPrice(),
			randomCPDefinitionOptionValueRel.getQuantity());

		Assert.assertTrue(
			"preselected option value exists",
			_cpDefinitionOptionValueRelLocalService.
				hasPreselectedCPDefinitionOptionValueRel(
					cpDefinitionOptionRel.getCPDefinitionOptionRelId()));

		CPDefinitionOptionValueRel targetCPDefinitionOptionValueRel = null;

		for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
				cpDefinitionOptionRel.getCPDefinitionOptionValueRels()) {

			long cpDefinitionOptionValueRelId1 =
				randomCPDefinitionOptionValueRel.
					getCPDefinitionOptionValueRelId();
			long cpDefinitionOptionValueRelId2 =
				cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId();

			if (cpDefinitionOptionValueRelId1 ==
					cpDefinitionOptionValueRelId2) {

				Assert.assertTrue(
					"Option value preselected",
					cpDefinitionOptionValueRel.isPreselected());

				continue;
			}

			Assert.assertFalse(
				"Option value preselected",
				cpDefinitionOptionValueRel.isPreselected());

			if (targetCPDefinitionOptionValueRel == null) {
				targetCPDefinitionOptionValueRel = cpDefinitionOptionValueRel;
			}
		}

		Assert.assertNotEquals(
			"updated option value id",
			randomCPDefinitionOptionValueRel.getCPDefinitionOptionValueRelId(),
			targetCPDefinitionOptionValueRel.getCPDefinitionOptionValueRelId());

		_cpDefinitionOptionValueRelLocalService.
			updateCPDefinitionOptionValueRelPreselected(
				targetCPDefinitionOptionValueRel.
					getCPDefinitionOptionValueRelId(),
				true);

		Assert.assertTrue(
			"preselected option value exists",
			_cpDefinitionOptionValueRelLocalService.
				hasPreselectedCPDefinitionOptionValueRel(
					cpDefinitionOptionRel.getCPDefinitionOptionRelId()));

		CPDefinitionOptionValueRel preselectedCPDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelLocalService.
				fetchPreselectedCPDefinitionOptionValueRel(
					cpDefinitionOptionRel.getCPDefinitionOptionRelId());

		Assert.assertEquals(
			"updated option value id",
			targetCPDefinitionOptionValueRel.getCPDefinitionOptionValueRelId(),
			preselectedCPDefinitionOptionValueRel.
				getCPDefinitionOptionValueRelId());

		Assert.assertTrue(
			"Option value preselected",
			preselectedCPDefinitionOptionValueRel.isPreselected());

		preselectedCPDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelLocalService.
				updateCPDefinitionOptionValueRelPreselected(
					targetCPDefinitionOptionValueRel.
						getCPDefinitionOptionValueRelId(),
					false);

		Assert.assertFalse(
			"preselected option value exists",
			_cpDefinitionOptionValueRelLocalService.
				hasPreselectedCPDefinitionOptionValueRel(
					cpDefinitionOptionRel.getCPDefinitionOptionRelId()));

		Assert.assertFalse(
			"Option value preselected",
			preselectedCPDefinitionOptionValueRel.isPreselected());
	}

	@Test(expected = CPDefinitionOptionValueRelPriceException.class)
	public void testUpdateStaticPriceTypeCPDefinitionOptionValueRelWithoutPrice()
		throws Exception {

		frutillaRule.scenario(
			"Update an option value without passing price"
		).given(
			"An option with static price type set"
		).when(
			"The option value is updated"
		).then(
			"price is required, cpInstanceUUID and cProductId are optional"
		);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			_addCPDefinitionWithOptionValue();

		CPInstance cpInstance = CPTestUtil.addCPInstanceFromCatalog(
			_commerceCatalog.getGroupId());

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		_cpDefinitionOptionRelLocalService.updateCPDefinitionOptionRel(
			cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
			cpDefinitionOptionRel.getCPOptionId(),
			cpDefinitionOptionRel.getNameMap(),
			cpDefinitionOptionRel.getDescriptionMap(),
			cpDefinitionOptionRel.getDDMFormFieldTypeName(),
			cpDefinitionOptionRel.getPriority(),
			cpDefinitionOptionRel.isFacetable(),
			cpDefinitionOptionRel.isRequired(),
			cpDefinitionOptionRel.isSkuContributor(),
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC, _serviceContext);

		_updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRel, cpInstance.getCPInstanceId(),
			cpDefinitionOptionValueRel.isPreselected(), null, 1);
	}

	@Test
	public void testUpdateStaticPriceTypeCPDefinitionOptionValueRelWithPrice()
		throws Exception {

		frutillaRule.scenario(
			"Update an option value passing price"
		).given(
			"An option with static price type set"
		).when(
			"The option value is updated"
		).then(
			"price is required, cpInstanceUUID and cProductId are optional"
		);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			_addCPDefinitionWithOptionValue();

		CPInstance cpInstance = CPTestUtil.addCPInstanceFromCatalog(
			_commerceCatalog.getGroupId());

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		_cpDefinitionOptionRelLocalService.updateCPDefinitionOptionRel(
			cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
			cpDefinitionOptionRel.getCPOptionId(),
			cpDefinitionOptionRel.getNameMap(),
			cpDefinitionOptionRel.getDescriptionMap(),
			cpDefinitionOptionRel.getDDMFormFieldTypeName(),
			cpDefinitionOptionRel.getPriority(),
			cpDefinitionOptionRel.isFacetable(),
			cpDefinitionOptionRel.isRequired(),
			cpDefinitionOptionRel.isSkuContributor(),
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC, _serviceContext);

		cpDefinitionOptionValueRel = _updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRel, cpInstance.getCPInstanceId(),
			cpDefinitionOptionValueRel.isPreselected(), BigDecimal.TEN, 1);

		Assert.assertEquals(
			BigDecimal.TEN, cpDefinitionOptionValueRel.getPrice());
		Assert.assertNotEquals(
			cpInstance.getPrice(), cpDefinitionOptionValueRel.getPrice());
	}

	@Test(expected = CPDefinitionOptionValueRelQuantityException.class)
	public void testValidateCPDefinitionOptionValueRelDynamicFail()
		throws Exception {

		frutillaRule.scenario(
			"Update an option value with non unique SKU and quantity"
		).given(
			"Dynamic product option values with SKU and quantity set"
		).when(
			"New option value is added with same SKU and quantity"
		).then(
			"An exception is thrown"
		);

		_assertValidateCPDefinitionOptionValueRelCPInstanceLinkFail(
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_DYNAMIC);
	}

	@Test(expected = CPDefinitionOptionValueRelQuantityException.class)
	public void testValidateCPDefinitionOptionValueRelStaticFail()
		throws Exception {

		frutillaRule.scenario(
			"Update an option value with non unique SKU and quantity"
		).given(
			"Static product option values with SKU and quantity set"
		).when(
			"New option value is added with same SKU and quantity"
		).then(
			"An exception is thrown"
		);

		_assertValidateCPDefinitionOptionValueRelCPInstanceLinkFail(
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC);
	}

	@Test
	public void testValidateLinkedCPDefinitionOptionValueRelSuccess()
		throws Exception {

		CPDefinition cpDefinition =
			CPTestUtil.addCPDefinitionWithChildCPDefinitions(
				_commerceCatalog.getGroupId(), 1,
				CPConstants.PRODUCT_OPTION_PRICE_TYPE_DYNAMIC);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			CPTestUtil.getRandomCPDefinitionOptionValueRel(
				cpDefinition.getCPDefinitionId());

		Assert.assertTrue(
			Validator.isNotNull(
				cpDefinitionOptionValueRel.getCPInstanceUuid()));
		Assert.assertTrue(
			"Quantity is greater than 0",
			cpDefinitionOptionValueRel.getQuantity() > 0);

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			cpDefinitionOptionRel.getCPDefinitionOptionValueRels();

		CPDefinitionOptionValueRel newCPDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelLocalService.
				addCPDefinitionOptionValueRel(
					cpDefinitionOptionValueRel.getCPDefinitionOptionRelId(),
					RandomTestUtil.randomLocaleStringMap(),
					RandomTestUtil.nextDouble(), RandomTestUtil.randomString(),
					_serviceContext);

		CPInstance cpInstance = cpDefinitionOptionValueRel.fetchCPInstance();

		newCPDefinitionOptionValueRel = _updateCPDefinitionOptionValueRel(
			newCPDefinitionOptionValueRel, cpInstance.getCPInstanceId(),
			newCPDefinitionOptionValueRel.isPreselected(), null,
			cpDefinitionOptionValueRel.getQuantity() + 10);

		Assert.assertEquals(
			"New CP definition option value quantity",
			cpDefinitionOptionValueRel.getQuantity() + 10,
			newCPDefinitionOptionValueRel.getQuantity());

		int size = cpDefinitionOptionValueRels.size();

		cpDefinitionOptionValueRels =
			cpDefinitionOptionRel.getCPDefinitionOptionValueRels();

		Assert.assertEquals(
			"Size of product option values increased by one", size + 1,
			cpDefinitionOptionValueRels.size());
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	protected void testResetCPDefinitionOptionValueRelOnStatusChange(
			int newStatus)
		throws Exception {

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			_addCPDefinitionWithOptionValue();

		CPInstance cpInstance = CPTestUtil.addCPInstanceFromCatalog(
			_commerceCatalog.getGroupId());

		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, cpInstance.getStatus());

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		_cpDefinitionOptionRelLocalService.updateCPDefinitionOptionRel(
			cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
			cpDefinitionOptionRel.getCPOptionId(),
			cpDefinitionOptionRel.getNameMap(),
			cpDefinitionOptionRel.getDescriptionMap(),
			cpDefinitionOptionRel.getDDMFormFieldTypeName(),
			cpDefinitionOptionRel.getPriority(),
			cpDefinitionOptionRel.isFacetable(),
			cpDefinitionOptionRel.isRequired(),
			cpDefinitionOptionRel.isSkuContributor(),
			CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC, _serviceContext);

		cpDefinitionOptionValueRel = _updateCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRel, cpInstance.getCPInstanceId(),
			cpDefinitionOptionValueRel.isPreselected(), BigDecimal.TEN, 1);

		Assert.assertEquals(
			cpInstance.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getCPInstanceUuid());

		CPDefinition cpInstanceCPDefinition = cpInstance.getCPDefinition();

		Assert.assertEquals(
			cpInstanceCPDefinition.getCProductId(),
			cpDefinitionOptionValueRel.getCProductId());

		Assert.assertEquals(
			BigDecimal.TEN, cpDefinitionOptionValueRel.getPrice());
		Assert.assertEquals(1, cpDefinitionOptionValueRel.getQuantity());

		_cpInstanceLocalService.updateStatus(
			_serviceContext.getUserId(), cpInstance.getCPInstanceId(),
			newStatus);

		cpDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelLocalService.
				getCPDefinitionOptionValueRel(
					cpDefinitionOptionValueRel.
						getCPDefinitionOptionValueRelId());

		Assert.assertTrue(
			Validator.isNull(cpDefinitionOptionValueRel.getCPInstanceUuid()));
		Assert.assertEquals(0, cpDefinitionOptionValueRel.getCProductId());

		Assert.assertEquals(
			CPTestUtil.stripTrailingZeros(BigDecimal.ZERO),
			CPTestUtil.stripTrailingZeros(
				cpDefinitionOptionValueRel.getPrice()));

		Assert.assertEquals(0, cpDefinitionOptionValueRel.getQuantity());
	}

	private CPDefinitionOptionValueRel _addCPDefinitionWithOptionValue()
		throws Exception {

		CPDefinition cpDefinition = CPTestUtil.addCPDefinitionFromCatalog(
			_commerceCatalog.getGroupId(), SimpleCPTypeConstants.NAME, true,
			true);

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			CPTestUtil.addCPOption(
				_commerceCatalog.getGroupId(), cpDefinition.getCPDefinitionId(),
				1, 1);

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionRels.get(0);

		return _cpDefinitionOptionValueRelLocalService.
			addCPDefinitionOptionValueRel(
				cpDefinitionOptionRel.getCPDefinitionOptionRelId(), null, 0,
				"cpInstance-option-value", _serviceContext);
	}

	private void _assertValidateCPDefinitionOptionValueRelCPInstanceLinkFail(
			String priceType)
		throws Exception {

		CPDefinition cpDefinition =
			CPTestUtil.addCPDefinitionWithChildCPDefinitions(
				_commerceCatalog.getGroupId(), 1, priceType);

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			CPTestUtil.getRandomCPDefinitionOptionValueRel(
				cpDefinition.getCPDefinitionId());

		Assert.assertTrue(
			Validator.isNotNull(
				cpDefinitionOptionValueRel.getCPInstanceUuid()));
		Assert.assertTrue(
			"Quantity is greater than 0",
			cpDefinitionOptionValueRel.getQuantity() > 0);

		CPDefinitionOptionValueRel newCPDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelLocalService.
				addCPDefinitionOptionValueRel(
					cpDefinitionOptionValueRel.getCPDefinitionOptionRelId(),
					RandomTestUtil.randomLocaleStringMap(),
					RandomTestUtil.nextDouble(), RandomTestUtil.randomString(),
					_serviceContext);

		CPInstance cpInstance = cpDefinitionOptionValueRel.fetchCPInstance();

		BigDecimal price = null;

		if (Objects.equals(
				priceType, CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC)) {

			price = BigDecimal.TEN;
		}

		_updateCPDefinitionOptionValueRel(
			newCPDefinitionOptionValueRel, cpInstance.getCPInstanceId(),
			newCPDefinitionOptionValueRel.isPreselected(), price,
			cpDefinitionOptionValueRel.getQuantity());
	}

	private CPInstance _getCPInstance(long cpDefinitionId) throws Exception {
		List<CPInstance> cpInstances =
			_cpInstanceLocalService.getCPDefinitionApprovedCPInstances(
				cpDefinitionId);

		if (!cpInstances.isEmpty()) {
			return cpInstances.get(0);
		}

		return CPTestUtil.addCPDefinitionCPInstanceWithPrice(
			cpDefinitionId, Collections.emptyMap(),
			new BigDecimal(RandomTestUtil.randomDouble()));
	}

	private CPDefinitionOptionValueRel _updateCPDefinitionOptionValueRel(
			CPDefinitionOptionValueRel cpDefinitionOptionValueRel,
			long cpInstanceId, boolean preselected, BigDecimal price,
			int quantity)
		throws Exception {

		return _cpDefinitionOptionValueRelLocalService.
			updateCPDefinitionOptionValueRel(
				cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId(),
				cpDefinitionOptionValueRel.getNameMap(),
				cpDefinitionOptionValueRel.getPriority(),
				cpDefinitionOptionValueRel.getKey(), cpInstanceId, quantity,
				preselected, price, _serviceContext);
	}

	private static User _user;

	private CommerceCatalog _commerceCatalog;

	@Inject
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	@Inject
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Inject
	private CPDefinitionOptionRelLocalService
		_cpDefinitionOptionRelLocalService;

	@Inject
	private CPDefinitionOptionValueRelLocalService
		_cpDefinitionOptionValueRelLocalService;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	@Inject
	private CPOptionLocalService _cpOptionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	private ServiceContext _serviceContext;

}