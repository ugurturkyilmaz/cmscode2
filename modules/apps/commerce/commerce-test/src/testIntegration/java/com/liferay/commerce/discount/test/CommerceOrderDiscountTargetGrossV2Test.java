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

package com.liferay.commerce.discount.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.discount.test.util.CommerceDiscountTestUtil;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.test.util.CommercePriceEntryTestUtil;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.tax.model.CommerceTaxMethod;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.commerce.test.util.CommerceTaxTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.commerce.test.util.context.TestCommerceContext;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luca Pellizzon
 */
@RunWith(Arquillian.class)
public class CommerceOrderDiscountTargetGrossV2Test {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule = new AggregateTestRule(
		new LiferayIntegrationTestRule(),
		PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();

		_commerceAccount =
			_commerceAccountLocalService.getPersonalCommerceAccount(
				_user.getUserId());

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getCompanyId());

		_commerceChannel = CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), _commerceCurrency.getCode());

		_commerceChannel.setDiscountsTargetNetPrice(false);

		_commerceChannelLocalService.updateCommerceChannel(_commerceChannel);

		_commerceTaxMethod = CommerceTaxTestUtil.addCommerceByAddressTaxMethod(
			_user.getUserId(), _commerceChannel.getGroupId(), true);
	}

	@After
	public void tearDown() throws Exception {
		for (CommerceOrder commerceOrder : _commerceOrders) {
			_commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
		}
	}

	@Test
	public void testDiscountWithCouponGreaterThanCartValue() throws Exception {
		frutillaRule.scenario(
			"Discount on total is applied to the order only if consistent"
		).given(
			"An order with some order items"
		).and(
			"A discount coupon on the total price"
		).when(
			"I try to get the final price of the order"
		).then(
			"The final price will be calculated with the discount"
		);

		long cpTaxCategoryId = CommerceTaxTestUtil.addTaxCategoryId(
			_user.getGroupId());

		double rate = Double.valueOf(RandomTestUtil.randomInt(0, 40));

		BigDecimal taxRate = BigDecimal.valueOf(rate);

		CommerceTaxTestUtil.setCommerceMethodTaxRate(
			_user.getUserId(), _commerceChannel.getGroupId(), cpTaxCategoryId,
			_commerceTaxMethod.getCommerceTaxMethodId(), rate);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		commerceOrder.setCommerceCurrencyId(
			_commerceCurrency.getCommerceCurrencyId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CommerceCatalog catalog =
			_commerceCatalogLocalService.addCommerceCatalog(
				null, RandomTestUtil.randomString(),
				_commerceCurrency.getCode(), LocaleUtil.US.getDisplayLanguage(),
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		CPInstance cpInstanceDiscount =
			CPTestUtil.addCPInstanceWithRandomSkuFromCatalog(
				catalog.getGroupId());

		CPDefinition cpDefinition = cpInstanceDiscount.getCPDefinition();

		cpDefinition.setCPTaxCategoryId(cpTaxCategoryId);
		cpDefinition.setTaxExempt(false);

		_cpDefinitionLocalService.updateCPDefinition(cpDefinition);

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.fetchCatalogBaseCommercePriceList(
				catalog.getGroupId());

		CommercePriceEntryTestUtil.addCommercePriceEntry(
			"", cpDefinition.getCProductId(),
			cpInstanceDiscount.getCPInstanceUuid(),
			commercePriceList.getCommercePriceListId(),
			BigDecimal.valueOf(0.9));

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		int quantity = 10;
		int orderedQuantity = 1;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstanceDiscount.getSku(), quantity);

		String couponCode = StringUtil.randomString();

		CommerceDiscountTestUtil.addCouponDiscount(
			_user.getGroupId(), 1, couponCode,
			CommerceDiscountConstants.TARGET_TOTAL, null);

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, _commerceChannel, _user, _group,
			_commerceAccount, commerceOrder);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstanceDiscount.getCPInstanceId(), orderedQuantity,
			commerceContext);

		commerceOrder = _commerceOrderLocalService.applyCouponCode(
			commerceOrder.getCommerceOrderId(), couponCode, commerceContext);

		CommerceMoney totalCommerceMoney =
			_commerceOrderPriceCalculation.getTotal(
				commerceOrder, commerceContext);

		BigDecimal totalPrice = totalCommerceMoney.getPrice();

		BigDecimal priceWithTaxAmount =
			CommerceTaxTestUtil.getPriceWithTaxAmount(
				BigDecimal.valueOf(0.9), taxRate,
				RoundingMode.valueOf(_commerceCurrency.getRoundingMode()));

		BigDecimal discountedTotal = BigDecimal.ZERO;

		if (priceWithTaxAmount.compareTo(BigDecimal.ONE) > 0) {
			discountedTotal = priceWithTaxAmount.subtract(BigDecimal.ONE);
		}

		Assert.assertEquals(
			discountedTotal.stripTrailingZeros(),
			totalPrice.stripTrailingZeros());
	}

	@Test
	public void testMultiTargetDiscounts() throws Exception {
		frutillaRule.scenario(
			"Discounts on multiple targets shall be applied on the order"
		).given(
			"An order with some order items"
		).and(
			"A discount on one product and a discount on the total price"
		).when(
			"I try to get the final price of the order"
		).then(
			"The final price will be calculated with the discounts"
		);

		long cpTaxCategoryId = CommerceTaxTestUtil.addTaxCategoryId(
			_user.getGroupId());

		double rate = Double.valueOf(RandomTestUtil.randomInt(0, 40));

		BigDecimal taxRate = BigDecimal.valueOf(rate);

		CommerceTaxTestUtil.setCommerceMethodTaxRate(
			_user.getUserId(), _commerceChannel.getGroupId(), cpTaxCategoryId,
			_commerceTaxMethod.getCommerceTaxMethodId(), rate);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		commerceOrder.setCommerceCurrencyId(
			_commerceCurrency.getCommerceCurrencyId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CommerceCatalog catalog =
			_commerceCatalogLocalService.addCommerceCatalog(
				null, RandomTestUtil.randomString(),
				_commerceCurrency.getCode(), LocaleUtil.US.getDisplayLanguage(),
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		CPInstance cpInstanceDiscount =
			CPTestUtil.addCPInstanceWithRandomSkuFromCatalog(
				catalog.getGroupId());

		CPDefinition cpDefinition = cpInstanceDiscount.getCPDefinition();

		cpDefinition.setCPTaxCategoryId(cpTaxCategoryId);
		cpDefinition.setTaxExempt(false);

		_cpDefinitionLocalService.updateCPDefinition(cpDefinition);

		CPInstance cpInstancePlain =
			CPTestUtil.addCPInstanceWithRandomSkuFromCatalog(
				catalog.getGroupId());

		CPDefinition cpDefinitionPlan = cpInstancePlain.getCPDefinition();

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.fetchCatalogBaseCommercePriceList(
				catalog.getGroupId());

		CommercePriceEntry commercePriceEntryDiscount =
			CommercePriceEntryTestUtil.addCommercePriceEntry(
				"", cpDefinition.getCProductId(),
				cpInstanceDiscount.getCPInstanceUuid(),
				commercePriceList.getCommercePriceListId(),
				BigDecimal.valueOf(25));

		CommercePriceEntry commercePriceEntryPlain =
			CommercePriceEntryTestUtil.addCommercePriceEntry(
				"", cpDefinitionPlan.getCProductId(),
				cpInstancePlain.getCPInstanceUuid(),
				commercePriceList.getCommercePriceListId(),
				BigDecimal.valueOf(10));

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		int quantity = 10;
		int orderedQuantity = 1;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstanceDiscount.getSku(), quantity);

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstancePlain.getSku(), quantity);

		CommerceDiscount commerceDiscount1 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_user.getGroupId(), 10,
				CommerceDiscountConstants.TARGET_PRODUCTS,
				cpDefinition.getCPDefinitionId());

		CommerceDiscount commerceDiscount2 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_user.getGroupId(), 10, CommerceDiscountConstants.TARGET_TOTAL,
				null);

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, _commerceChannel, _user, _group,
			_commerceAccount, commerceOrder);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstanceDiscount.getCPInstanceId(), orderedQuantity,
			commerceContext);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstancePlain.getCPInstanceId(), orderedQuantity,
			commerceContext);

		CommerceMoney totalCommerceMoney =
			_commerceOrderPriceCalculation.getTotal(
				commerceOrder, commerceContext);
		CommerceMoney subtotalCommerceMoney =
			_commerceOrderPriceCalculation.getSubtotal(
				commerceOrder, commerceContext);
		CommerceMoney taxValueCommerceMoney =
			_commerceOrderPriceCalculation.getTaxValue(
				commerceOrder, commerceContext);

		BigDecimal prod1Price = commercePriceEntryDiscount.getPrice();
		BigDecimal prod2Price = commercePriceEntryPlain.getPrice();

		BigDecimal prod1TotalPrice = prod1Price.multiply(
			BigDecimal.valueOf(orderedQuantity));
		BigDecimal prod2TotalPrice = prod2Price.multiply(
			BigDecimal.valueOf(orderedQuantity));

		BigDecimal discount1Level1 = commerceDiscount1.getLevel1();
		BigDecimal discount2Level1 = commerceDiscount2.getLevel1();

		BigDecimal prod1TotalPriceWithTaxAmount =
			CommerceTaxTestUtil.getPriceWithTaxAmount(
				prod1TotalPrice, taxRate,
				RoundingMode.valueOf(_commerceCurrency.getRoundingMode()));

		BigDecimal prod1DiscountedPrice =
			CommerceTaxTestUtil.getPriceWithoutTaxAmount(
				prod1TotalPriceWithTaxAmount.subtract(discount1Level1), taxRate,
				RoundingMode.valueOf(_commerceCurrency.getRoundingMode()));

		BigDecimal expectedSubtotal = prod1DiscountedPrice.add(prod2TotalPrice);

		BigDecimal expectedSubtotalWithTaxAmount = expectedSubtotal.add(
			taxValueCommerceMoney.getPrice());

		BigDecimal expectedTotal = expectedSubtotalWithTaxAmount.subtract(
			discount2Level1);

		BigDecimal subtotalPrice = subtotalCommerceMoney.getPrice();
		BigDecimal totalPrice = totalCommerceMoney.getPrice();

		Assert.assertEquals(
			expectedSubtotal.stripTrailingZeros(),
			subtotalPrice.stripTrailingZeros());
		Assert.assertEquals(
			expectedTotal.stripTrailingZeros(),
			totalPrice.stripTrailingZeros());
	}

	@Test
	public void testMultiTargetDiscountsWithCoupon() throws Exception {
		frutillaRule.scenario(
			"Discounts on multiple targets shall be applied on the order"
		).given(
			"An order with some order items"
		).and(
			"A discount on one product and a discount coupon on the total price"
		).when(
			"I try to get the final price of the order"
		).then(
			"The final price will be calculated with the discounts"
		);

		long cpTaxCategoryId = CommerceTaxTestUtil.addTaxCategoryId(
			_user.getGroupId());

		double rate = Double.valueOf(RandomTestUtil.randomInt(0, 40));

		BigDecimal taxRate = BigDecimal.valueOf(rate);

		CommerceTaxTestUtil.setCommerceMethodTaxRate(
			_user.getUserId(), _commerceChannel.getGroupId(), cpTaxCategoryId,
			_commerceTaxMethod.getCommerceTaxMethodId(), rate);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		commerceOrder.setCommerceCurrencyId(
			_commerceCurrency.getCommerceCurrencyId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CommerceCatalog catalog =
			_commerceCatalogLocalService.addCommerceCatalog(
				null, RandomTestUtil.randomString(),
				_commerceCurrency.getCode(), LocaleUtil.US.getDisplayLanguage(),
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		CPInstance cpInstanceDiscount =
			CPTestUtil.addCPInstanceWithRandomSkuFromCatalog(
				catalog.getGroupId());

		CPDefinition cpDefinition = cpInstanceDiscount.getCPDefinition();

		cpDefinition.setCPTaxCategoryId(cpTaxCategoryId);
		cpDefinition.setTaxExempt(false);

		_cpDefinitionLocalService.updateCPDefinition(cpDefinition);

		CPInstance cpInstancePlain =
			CPTestUtil.addCPInstanceWithRandomSkuFromCatalog(
				catalog.getGroupId());

		CPDefinition cpDefinitionPlan = cpInstancePlain.getCPDefinition();

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.fetchCatalogBaseCommercePriceList(
				catalog.getGroupId());

		CommercePriceEntry commercePriceEntryDiscount =
			CommercePriceEntryTestUtil.addCommercePriceEntry(
				"", cpDefinition.getCProductId(),
				cpInstanceDiscount.getCPInstanceUuid(),
				commercePriceList.getCommercePriceListId(),
				BigDecimal.valueOf(25));

		CommercePriceEntry commercePriceEntryPlain =
			CommercePriceEntryTestUtil.addCommercePriceEntry(
				"", cpDefinitionPlan.getCProductId(),
				cpInstancePlain.getCPInstanceUuid(),
				commercePriceList.getCommercePriceListId(),
				BigDecimal.valueOf(10));

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		int quantity = 10;
		int orderedQuantity = 1;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstanceDiscount.getSku(), quantity);

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstancePlain.getSku(), quantity);

		CommerceDiscount commerceDiscount1 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_user.getGroupId(), 10,
				CommerceDiscountConstants.TARGET_PRODUCTS,
				cpDefinition.getCPDefinitionId());

		String couponCode = StringUtil.randomString();

		CommerceDiscount commerceDiscount2 =
			CommerceDiscountTestUtil.addCouponDiscount(
				_user.getGroupId(), 10, couponCode,
				CommerceDiscountConstants.TARGET_TOTAL, null);

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, _commerceChannel, _user, _group,
			_commerceAccount, commerceOrder);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstanceDiscount.getCPInstanceId(), orderedQuantity,
			commerceContext);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstancePlain.getCPInstanceId(), orderedQuantity,
			commerceContext);

		commerceOrder = _commerceOrderLocalService.applyCouponCode(
			commerceOrder.getCommerceOrderId(), couponCode, commerceContext);

		CommerceMoney totalCommerceMoney =
			_commerceOrderPriceCalculation.getTotal(
				commerceOrder, commerceContext);
		CommerceMoney subtotalCommerceMoney =
			_commerceOrderPriceCalculation.getSubtotal(
				commerceOrder, commerceContext);
		CommerceMoney taxValueCommerceMoney =
			_commerceOrderPriceCalculation.getTaxValue(
				commerceOrder, commerceContext);

		BigDecimal prod1Price = commercePriceEntryDiscount.getPrice();
		BigDecimal prod2Price = commercePriceEntryPlain.getPrice();

		BigDecimal prod1TotalPrice = prod1Price.multiply(
			BigDecimal.valueOf(orderedQuantity));
		BigDecimal prod2TotalPrice = prod2Price.multiply(
			BigDecimal.valueOf(orderedQuantity));

		BigDecimal discount1Level1 = commerceDiscount1.getLevel1();
		BigDecimal discount2Level1 = commerceDiscount2.getLevel1();

		BigDecimal prod1TotalPriceWithTaxAmount =
			CommerceTaxTestUtil.getPriceWithTaxAmount(
				prod1TotalPrice, taxRate,
				RoundingMode.valueOf(_commerceCurrency.getRoundingMode()));

		BigDecimal prod1DiscountedPrice =
			CommerceTaxTestUtil.getPriceWithoutTaxAmount(
				prod1TotalPriceWithTaxAmount.subtract(discount1Level1), taxRate,
				RoundingMode.valueOf(_commerceCurrency.getRoundingMode()));

		BigDecimal expectedSubtotal = prod1DiscountedPrice.add(prod2TotalPrice);

		BigDecimal expectedSubtotalWithTaxAmount = expectedSubtotal.add(
			taxValueCommerceMoney.getPrice());

		BigDecimal expectedTotal = expectedSubtotalWithTaxAmount.subtract(
			discount2Level1);

		BigDecimal subtotalPrice = subtotalCommerceMoney.getPrice();
		BigDecimal totalPrice = totalCommerceMoney.getPrice();

		Assert.assertEquals(
			expectedSubtotal.stripTrailingZeros(),
			subtotalPrice.stripTrailingZeros());
		Assert.assertEquals(
			expectedTotal.stripTrailingZeros(),
			totalPrice.stripTrailingZeros());
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private static User _user;

	private CommerceAccount _commerceAccount;

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Inject
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	@DeleteAfterTestRun
	private CommerceChannel _commerceChannel;

	@Inject
	private CommerceChannelLocalService _commerceChannelLocalService;

	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceDiscountLocalService _commerceDiscountLocalService;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Inject
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	private final List<CommerceOrder> _commerceOrders = new ArrayList<>();

	@Inject
	private CommercePriceListLocalService _commercePriceListLocalService;

	@DeleteAfterTestRun
	private CommerceTaxMethod _commerceTaxMethod;

	@Inject
	private CPDefinitionLocalService _cpDefinitionLocalService;

	private Group _group;

	@Inject
	private UserLocalService _userLocalService;

}