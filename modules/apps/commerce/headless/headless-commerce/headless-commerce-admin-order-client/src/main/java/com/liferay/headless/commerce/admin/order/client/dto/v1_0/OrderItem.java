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

package com.liferay.headless.commerce.admin.order.client.dto.v1_0;

import com.liferay.headless.commerce.admin.order.client.function.UnsafeSupplier;
import com.liferay.headless.commerce.admin.order.client.serdes.v1_0.OrderItemSerDes;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class OrderItem implements Cloneable, Serializable {

	public static OrderItem toDTO(String json) {
		return OrderItemSerDes.toDTO(json);
	}

	public Long getBookedQuantityId() {
		return bookedQuantityId;
	}

	public void setBookedQuantityId(Long bookedQuantityId) {
		this.bookedQuantityId = bookedQuantityId;
	}

	public void setBookedQuantityId(
		UnsafeSupplier<Long, Exception> bookedQuantityIdUnsafeSupplier) {

		try {
			bookedQuantityId = bookedQuantityIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long bookedQuantityId;

	public Map<String, ?> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, ?> customFields) {
		this.customFields = customFields;
	}

	public void setCustomFields(
		UnsafeSupplier<Map<String, ?>, Exception> customFieldsUnsafeSupplier) {

		try {
			customFields = customFieldsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, ?> customFields;

	public BigDecimal getDecimalQuantity() {
		return decimalQuantity;
	}

	public void setDecimalQuantity(BigDecimal decimalQuantity) {
		this.decimalQuantity = decimalQuantity;
	}

	public void setDecimalQuantity(
		UnsafeSupplier<BigDecimal, Exception> decimalQuantityUnsafeSupplier) {

		try {
			decimalQuantity = decimalQuantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal decimalQuantity;

	public String getDeliveryGroup() {
		return deliveryGroup;
	}

	public void setDeliveryGroup(String deliveryGroup) {
		this.deliveryGroup = deliveryGroup;
	}

	public void setDeliveryGroup(
		UnsafeSupplier<String, Exception> deliveryGroupUnsafeSupplier) {

		try {
			deliveryGroup = deliveryGroupUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String deliveryGroup;

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public void setDiscountAmount(
		UnsafeSupplier<BigDecimal, Exception> discountAmountUnsafeSupplier) {

		try {
			discountAmount = discountAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountAmount;

	public BigDecimal getDiscountPercentageLevel1() {
		return discountPercentageLevel1;
	}

	public void setDiscountPercentageLevel1(
		BigDecimal discountPercentageLevel1) {

		this.discountPercentageLevel1 = discountPercentageLevel1;
	}

	public void setDiscountPercentageLevel1(
		UnsafeSupplier<BigDecimal, Exception>
			discountPercentageLevel1UnsafeSupplier) {

		try {
			discountPercentageLevel1 =
				discountPercentageLevel1UnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountPercentageLevel1;

	public BigDecimal getDiscountPercentageLevel1WithTaxAmount() {
		return discountPercentageLevel1WithTaxAmount;
	}

	public void setDiscountPercentageLevel1WithTaxAmount(
		BigDecimal discountPercentageLevel1WithTaxAmount) {

		this.discountPercentageLevel1WithTaxAmount =
			discountPercentageLevel1WithTaxAmount;
	}

	public void setDiscountPercentageLevel1WithTaxAmount(
		UnsafeSupplier<BigDecimal, Exception>
			discountPercentageLevel1WithTaxAmountUnsafeSupplier) {

		try {
			discountPercentageLevel1WithTaxAmount =
				discountPercentageLevel1WithTaxAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountPercentageLevel1WithTaxAmount;

	public BigDecimal getDiscountPercentageLevel2() {
		return discountPercentageLevel2;
	}

	public void setDiscountPercentageLevel2(
		BigDecimal discountPercentageLevel2) {

		this.discountPercentageLevel2 = discountPercentageLevel2;
	}

	public void setDiscountPercentageLevel2(
		UnsafeSupplier<BigDecimal, Exception>
			discountPercentageLevel2UnsafeSupplier) {

		try {
			discountPercentageLevel2 =
				discountPercentageLevel2UnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountPercentageLevel2;

	public BigDecimal getDiscountPercentageLevel2WithTaxAmount() {
		return discountPercentageLevel2WithTaxAmount;
	}

	public void setDiscountPercentageLevel2WithTaxAmount(
		BigDecimal discountPercentageLevel2WithTaxAmount) {

		this.discountPercentageLevel2WithTaxAmount =
			discountPercentageLevel2WithTaxAmount;
	}

	public void setDiscountPercentageLevel2WithTaxAmount(
		UnsafeSupplier<BigDecimal, Exception>
			discountPercentageLevel2WithTaxAmountUnsafeSupplier) {

		try {
			discountPercentageLevel2WithTaxAmount =
				discountPercentageLevel2WithTaxAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountPercentageLevel2WithTaxAmount;

	public BigDecimal getDiscountPercentageLevel3() {
		return discountPercentageLevel3;
	}

	public void setDiscountPercentageLevel3(
		BigDecimal discountPercentageLevel3) {

		this.discountPercentageLevel3 = discountPercentageLevel3;
	}

	public void setDiscountPercentageLevel3(
		UnsafeSupplier<BigDecimal, Exception>
			discountPercentageLevel3UnsafeSupplier) {

		try {
			discountPercentageLevel3 =
				discountPercentageLevel3UnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountPercentageLevel3;

	public BigDecimal getDiscountPercentageLevel3WithTaxAmount() {
		return discountPercentageLevel3WithTaxAmount;
	}

	public void setDiscountPercentageLevel3WithTaxAmount(
		BigDecimal discountPercentageLevel3WithTaxAmount) {

		this.discountPercentageLevel3WithTaxAmount =
			discountPercentageLevel3WithTaxAmount;
	}

	public void setDiscountPercentageLevel3WithTaxAmount(
		UnsafeSupplier<BigDecimal, Exception>
			discountPercentageLevel3WithTaxAmountUnsafeSupplier) {

		try {
			discountPercentageLevel3WithTaxAmount =
				discountPercentageLevel3WithTaxAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountPercentageLevel3WithTaxAmount;

	public BigDecimal getDiscountPercentageLevel4() {
		return discountPercentageLevel4;
	}

	public void setDiscountPercentageLevel4(
		BigDecimal discountPercentageLevel4) {

		this.discountPercentageLevel4 = discountPercentageLevel4;
	}

	public void setDiscountPercentageLevel4(
		UnsafeSupplier<BigDecimal, Exception>
			discountPercentageLevel4UnsafeSupplier) {

		try {
			discountPercentageLevel4 =
				discountPercentageLevel4UnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountPercentageLevel4;

	public BigDecimal getDiscountPercentageLevel4WithTaxAmount() {
		return discountPercentageLevel4WithTaxAmount;
	}

	public void setDiscountPercentageLevel4WithTaxAmount(
		BigDecimal discountPercentageLevel4WithTaxAmount) {

		this.discountPercentageLevel4WithTaxAmount =
			discountPercentageLevel4WithTaxAmount;
	}

	public void setDiscountPercentageLevel4WithTaxAmount(
		UnsafeSupplier<BigDecimal, Exception>
			discountPercentageLevel4WithTaxAmountUnsafeSupplier) {

		try {
			discountPercentageLevel4WithTaxAmount =
				discountPercentageLevel4WithTaxAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountPercentageLevel4WithTaxAmount;

	public BigDecimal getDiscountWithTaxAmount() {
		return discountWithTaxAmount;
	}

	public void setDiscountWithTaxAmount(BigDecimal discountWithTaxAmount) {
		this.discountWithTaxAmount = discountWithTaxAmount;
	}

	public void setDiscountWithTaxAmount(
		UnsafeSupplier<BigDecimal, Exception>
			discountWithTaxAmountUnsafeSupplier) {

		try {
			discountWithTaxAmount = discountWithTaxAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal discountWithTaxAmount;

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	public void setExternalReferenceCode(
		UnsafeSupplier<String, Exception> externalReferenceCodeUnsafeSupplier) {

		try {
			externalReferenceCode = externalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String externalReferenceCode;

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void setFinalPrice(
		UnsafeSupplier<BigDecimal, Exception> finalPriceUnsafeSupplier) {

		try {
			finalPrice = finalPriceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal finalPrice;

	public BigDecimal getFinalPriceWithTaxAmount() {
		return finalPriceWithTaxAmount;
	}

	public void setFinalPriceWithTaxAmount(BigDecimal finalPriceWithTaxAmount) {
		this.finalPriceWithTaxAmount = finalPriceWithTaxAmount;
	}

	public void setFinalPriceWithTaxAmount(
		UnsafeSupplier<BigDecimal, Exception>
			finalPriceWithTaxAmountUnsafeSupplier) {

		try {
			finalPriceWithTaxAmount =
				finalPriceWithTaxAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal finalPriceWithTaxAmount;

	public String getFormattedQuantity() {
		return formattedQuantity;
	}

	public void setFormattedQuantity(String formattedQuantity) {
		this.formattedQuantity = formattedQuantity;
	}

	public void setFormattedQuantity(
		UnsafeSupplier<String, Exception> formattedQuantityUnsafeSupplier) {

		try {
			formattedQuantity = formattedQuantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String formattedQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public Map<String, String> getName() {
		return name;
	}

	public void setName(Map<String, String> name) {
		this.name = name;
	}

	public void setName(
		UnsafeSupplier<Map<String, String>, Exception> nameUnsafeSupplier) {

		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> name;

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public void setOptions(
		UnsafeSupplier<String, Exception> optionsUnsafeSupplier) {

		try {
			options = optionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String options;

	public String getOrderExternalReferenceCode() {
		return orderExternalReferenceCode;
	}

	public void setOrderExternalReferenceCode(
		String orderExternalReferenceCode) {

		this.orderExternalReferenceCode = orderExternalReferenceCode;
	}

	public void setOrderExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			orderExternalReferenceCodeUnsafeSupplier) {

		try {
			orderExternalReferenceCode =
				orderExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String orderExternalReferenceCode;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setOrderId(
		UnsafeSupplier<Long, Exception> orderIdUnsafeSupplier) {

		try {
			orderId = orderIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long orderId;

	public String getPrintedNote() {
		return printedNote;
	}

	public void setPrintedNote(String printedNote) {
		this.printedNote = printedNote;
	}

	public void setPrintedNote(
		UnsafeSupplier<String, Exception> printedNoteUnsafeSupplier) {

		try {
			printedNote = printedNoteUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String printedNote;

	public BigDecimal getPromoPrice() {
		return promoPrice;
	}

	public void setPromoPrice(BigDecimal promoPrice) {
		this.promoPrice = promoPrice;
	}

	public void setPromoPrice(
		UnsafeSupplier<BigDecimal, Exception> promoPriceUnsafeSupplier) {

		try {
			promoPrice = promoPriceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal promoPrice;

	public BigDecimal getPromoPriceWithTaxAmount() {
		return promoPriceWithTaxAmount;
	}

	public void setPromoPriceWithTaxAmount(BigDecimal promoPriceWithTaxAmount) {
		this.promoPriceWithTaxAmount = promoPriceWithTaxAmount;
	}

	public void setPromoPriceWithTaxAmount(
		UnsafeSupplier<BigDecimal, Exception>
			promoPriceWithTaxAmountUnsafeSupplier) {

		try {
			promoPriceWithTaxAmount =
				promoPriceWithTaxAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal promoPriceWithTaxAmount;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setQuantity(
		UnsafeSupplier<Integer, Exception> quantityUnsafeSupplier) {

		try {
			quantity = quantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer quantity;

	public Date getRequestedDeliveryDate() {
		return requestedDeliveryDate;
	}

	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}

	public void setRequestedDeliveryDate(
		UnsafeSupplier<Date, Exception> requestedDeliveryDateUnsafeSupplier) {

		try {
			requestedDeliveryDate = requestedDeliveryDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date requestedDeliveryDate;

	public Integer getShippedQuantity() {
		return shippedQuantity;
	}

	public void setShippedQuantity(Integer shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}

	public void setShippedQuantity(
		UnsafeSupplier<Integer, Exception> shippedQuantityUnsafeSupplier) {

		try {
			shippedQuantity = shippedQuantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer shippedQuantity;

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public void setShippingAddress(
		UnsafeSupplier<ShippingAddress, Exception>
			shippingAddressUnsafeSupplier) {

		try {
			shippingAddress = shippingAddressUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ShippingAddress shippingAddress;

	public Long getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public void setShippingAddressId(
		UnsafeSupplier<Long, Exception> shippingAddressIdUnsafeSupplier) {

		try {
			shippingAddressId = shippingAddressIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long shippingAddressId;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setSku(UnsafeSupplier<String, Exception> skuUnsafeSupplier) {
		try {
			sku = skuUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String sku;

	public String getSkuExternalReferenceCode() {
		return skuExternalReferenceCode;
	}

	public void setSkuExternalReferenceCode(String skuExternalReferenceCode) {
		this.skuExternalReferenceCode = skuExternalReferenceCode;
	}

	public void setSkuExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			skuExternalReferenceCodeUnsafeSupplier) {

		try {
			skuExternalReferenceCode =
				skuExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String skuExternalReferenceCode;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public void setSkuId(UnsafeSupplier<Long, Exception> skuIdUnsafeSupplier) {
		try {
			skuId = skuIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long skuId;

	public Boolean getSubscription() {
		return subscription;
	}

	public void setSubscription(Boolean subscription) {
		this.subscription = subscription;
	}

	public void setSubscription(
		UnsafeSupplier<Boolean, Exception> subscriptionUnsafeSupplier) {

		try {
			subscription = subscriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean subscription;

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public void setUnitOfMeasure(
		UnsafeSupplier<String, Exception> unitOfMeasureUnsafeSupplier) {

		try {
			unitOfMeasure = unitOfMeasureUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String unitOfMeasure;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setUnitPrice(
		UnsafeSupplier<BigDecimal, Exception> unitPriceUnsafeSupplier) {

		try {
			unitPrice = unitPriceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal unitPrice;

	public BigDecimal getUnitPriceWithTaxAmount() {
		return unitPriceWithTaxAmount;
	}

	public void setUnitPriceWithTaxAmount(BigDecimal unitPriceWithTaxAmount) {
		this.unitPriceWithTaxAmount = unitPriceWithTaxAmount;
	}

	public void setUnitPriceWithTaxAmount(
		UnsafeSupplier<BigDecimal, Exception>
			unitPriceWithTaxAmountUnsafeSupplier) {

		try {
			unitPriceWithTaxAmount = unitPriceWithTaxAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal unitPriceWithTaxAmount;

	@Override
	public OrderItem clone() throws CloneNotSupportedException {
		return (OrderItem)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OrderItem)) {
			return false;
		}

		OrderItem orderItem = (OrderItem)object;

		return Objects.equals(toString(), orderItem.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return OrderItemSerDes.toJSON(this);
	}

}