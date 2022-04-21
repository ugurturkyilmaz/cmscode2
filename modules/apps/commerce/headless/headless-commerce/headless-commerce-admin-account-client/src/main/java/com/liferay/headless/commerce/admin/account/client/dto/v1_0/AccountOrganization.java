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

package com.liferay.headless.commerce.admin.account.client.dto.v1_0;

import com.liferay.headless.commerce.admin.account.client.function.UnsafeSupplier;
import com.liferay.headless.commerce.admin.account.client.serdes.v1_0.AccountOrganizationSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class AccountOrganization implements Cloneable, Serializable {

	public static AccountOrganization toDTO(String json) {
		return AccountOrganizationSerDes.toDTO(json);
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	public String getOrganizationExternalReferenceCode() {
		return organizationExternalReferenceCode;
	}

	public void setOrganizationExternalReferenceCode(
		String organizationExternalReferenceCode) {

		this.organizationExternalReferenceCode =
			organizationExternalReferenceCode;
	}

	public void setOrganizationExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			organizationExternalReferenceCodeUnsafeSupplier) {

		try {
			organizationExternalReferenceCode =
				organizationExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String organizationExternalReferenceCode;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public void setOrganizationId(
		UnsafeSupplier<Long, Exception> organizationIdUnsafeSupplier) {

		try {
			organizationId = organizationIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long organizationId;

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public void setTreePath(
		UnsafeSupplier<String, Exception> treePathUnsafeSupplier) {

		try {
			treePath = treePathUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String treePath;

	@Override
	public AccountOrganization clone() throws CloneNotSupportedException {
		return (AccountOrganization)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountOrganization)) {
			return false;
		}

		AccountOrganization accountOrganization = (AccountOrganization)object;

		return Objects.equals(toString(), accountOrganization.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AccountOrganizationSerDes.toJSON(this);
	}

}