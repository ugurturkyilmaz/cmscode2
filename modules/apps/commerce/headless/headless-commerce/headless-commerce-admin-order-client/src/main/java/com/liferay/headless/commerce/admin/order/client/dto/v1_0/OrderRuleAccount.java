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
import com.liferay.headless.commerce.admin.order.client.serdes.v1_0.OrderRuleAccountSerDes;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class OrderRuleAccount implements Cloneable, Serializable {

	public static OrderRuleAccount toDTO(String json) {
		return OrderRuleAccountSerDes.toDTO(json);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAccount(
		UnsafeSupplier<Account, Exception> accountUnsafeSupplier) {

		try {
			account = accountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Account account;

	public String getAccountExternalReferenceCode() {
		return accountExternalReferenceCode;
	}

	public void setAccountExternalReferenceCode(
		String accountExternalReferenceCode) {

		this.accountExternalReferenceCode = accountExternalReferenceCode;
	}

	public void setAccountExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			accountExternalReferenceCodeUnsafeSupplier) {

		try {
			accountExternalReferenceCode =
				accountExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String accountExternalReferenceCode;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAccountId(
		UnsafeSupplier<Long, Exception> accountIdUnsafeSupplier) {

		try {
			accountId = accountIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long accountId;

	public Map<String, Map<String, String>> getActions() {
		return actions;
	}

	public void setActions(Map<String, Map<String, String>> actions) {
		this.actions = actions;
	}

	public void setActions(
		UnsafeSupplier<Map<String, Map<String, String>>, Exception>
			actionsUnsafeSupplier) {

		try {
			actions = actionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, Map<String, String>> actions;

	public Long getOrderRuleAccountId() {
		return orderRuleAccountId;
	}

	public void setOrderRuleAccountId(Long orderRuleAccountId) {
		this.orderRuleAccountId = orderRuleAccountId;
	}

	public void setOrderRuleAccountId(
		UnsafeSupplier<Long, Exception> orderRuleAccountIdUnsafeSupplier) {

		try {
			orderRuleAccountId = orderRuleAccountIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long orderRuleAccountId;

	public String getOrderRuleExternalReferenceCode() {
		return orderRuleExternalReferenceCode;
	}

	public void setOrderRuleExternalReferenceCode(
		String orderRuleExternalReferenceCode) {

		this.orderRuleExternalReferenceCode = orderRuleExternalReferenceCode;
	}

	public void setOrderRuleExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			orderRuleExternalReferenceCodeUnsafeSupplier) {

		try {
			orderRuleExternalReferenceCode =
				orderRuleExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String orderRuleExternalReferenceCode;

	public Long getOrderRuleId() {
		return orderRuleId;
	}

	public void setOrderRuleId(Long orderRuleId) {
		this.orderRuleId = orderRuleId;
	}

	public void setOrderRuleId(
		UnsafeSupplier<Long, Exception> orderRuleIdUnsafeSupplier) {

		try {
			orderRuleId = orderRuleIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long orderRuleId;

	@Override
	public OrderRuleAccount clone() throws CloneNotSupportedException {
		return (OrderRuleAccount)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OrderRuleAccount)) {
			return false;
		}

		OrderRuleAccount orderRuleAccount = (OrderRuleAccount)object;

		return Objects.equals(toString(), orderRuleAccount.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return OrderRuleAccountSerDes.toJSON(this);
	}

}