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
import com.liferay.headless.delivery.client.serdes.v1_0.ExperienceSerDes;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class Experience implements Cloneable, Serializable {

	public static Experience toDTO(String json) {
		return ExperienceSerDes.toDTO(json);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String key;

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

	public Map<String, String> getName_i18n() {
		return name_i18n;
	}

	public void setName_i18n(Map<String, String> name_i18n) {
		this.name_i18n = name_i18n;
	}

	public void setName_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			name_i18nUnsafeSupplier) {

		try {
			name_i18n = name_i18nUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> name_i18n;

	public Segment[] getSegments() {
		return segments;
	}

	public void setSegments(Segment[] segments) {
		this.segments = segments;
	}

	public void setSegments(
		UnsafeSupplier<Segment[], Exception> segmentsUnsafeSupplier) {

		try {
			segments = segmentsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Segment[] segments;

	@Override
	public Experience clone() throws CloneNotSupportedException {
		return (Experience)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Experience)) {
			return false;
		}

		Experience experience = (Experience)object;

		return Objects.equals(toString(), experience.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ExperienceSerDes.toJSON(this);
	}

}