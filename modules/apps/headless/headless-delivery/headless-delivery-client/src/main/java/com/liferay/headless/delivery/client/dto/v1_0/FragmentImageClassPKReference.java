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

package com.liferay.headless.delivery.client.dto.v1_0;

import com.liferay.headless.delivery.client.function.UnsafeSupplier;
import com.liferay.headless.delivery.client.serdes.v1_0.FragmentImageClassPKReferenceSerDes;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class FragmentImageClassPKReference implements Cloneable, Serializable {

	public static FragmentImageClassPKReference toDTO(String json) {
		return FragmentImageClassPKReferenceSerDes.toDTO(json);
	}

	public Map<String, ClassPKReference> getClassPKReferences() {
		return classPKReferences;
	}

	public void setClassPKReferences(
		Map<String, ClassPKReference> classPKReferences) {

		this.classPKReferences = classPKReferences;
	}

	public void setClassPKReferences(
		UnsafeSupplier<Map<String, ClassPKReference>, Exception>
			classPKReferencesUnsafeSupplier) {

		try {
			classPKReferences = classPKReferencesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, ClassPKReference> classPKReferences;

	public FragmentImageConfiguration getFragmentImageConfiguration() {
		return fragmentImageConfiguration;
	}

	public void setFragmentImageConfiguration(
		FragmentImageConfiguration fragmentImageConfiguration) {

		this.fragmentImageConfiguration = fragmentImageConfiguration;
	}

	public void setFragmentImageConfiguration(
		UnsafeSupplier<FragmentImageConfiguration, Exception>
			fragmentImageConfigurationUnsafeSupplier) {

		try {
			fragmentImageConfiguration =
				fragmentImageConfigurationUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected FragmentImageConfiguration fragmentImageConfiguration;

	@Override
	public FragmentImageClassPKReference clone()
		throws CloneNotSupportedException {

		return (FragmentImageClassPKReference)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FragmentImageClassPKReference)) {
			return false;
		}

		FragmentImageClassPKReference fragmentImageClassPKReference =
			(FragmentImageClassPKReference)object;

		return Objects.equals(
			toString(), fragmentImageClassPKReference.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return FragmentImageClassPKReferenceSerDes.toJSON(this);
	}

}