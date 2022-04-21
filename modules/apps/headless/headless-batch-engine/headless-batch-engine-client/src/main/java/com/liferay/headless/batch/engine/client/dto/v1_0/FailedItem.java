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

package com.liferay.headless.batch.engine.client.dto.v1_0;

import com.liferay.headless.batch.engine.client.function.UnsafeSupplier;
import com.liferay.headless.batch.engine.client.serdes.v1_0.FailedItemSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Ivica Cardic
 * @generated
 */
@Generated("")
public class FailedItem implements Cloneable, Serializable {

	public static FailedItem toDTO(String json) {
		return FailedItemSerDes.toDTO(json);
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setItem(UnsafeSupplier<String, Exception> itemUnsafeSupplier) {
		try {
			item = itemUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String item;

	public Integer getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(Integer itemIndex) {
		this.itemIndex = itemIndex;
	}

	public void setItemIndex(
		UnsafeSupplier<Integer, Exception> itemIndexUnsafeSupplier) {

		try {
			itemIndex = itemIndexUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer itemIndex;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessage(
		UnsafeSupplier<String, Exception> messageUnsafeSupplier) {

		try {
			message = messageUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String message;

	@Override
	public FailedItem clone() throws CloneNotSupportedException {
		return (FailedItem)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FailedItem)) {
			return false;
		}

		FailedItem failedItem = (FailedItem)object;

		return Objects.equals(toString(), failedItem.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return FailedItemSerDes.toJSON(this);
	}

}