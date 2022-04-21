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

package com.liferay.headless.admin.content.client.dto.v1_0;

import com.liferay.headless.admin.content.client.function.UnsafeSupplier;
import com.liferay.headless.admin.content.client.serdes.v1_0.ContentAssociationSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class ContentAssociation implements Cloneable, Serializable {

	public static ContentAssociation toDTO(String json) {
		return ContentAssociationSerDes.toDTO(json);
	}

	public String getContentSubtype() {
		return contentSubtype;
	}

	public void setContentSubtype(String contentSubtype) {
		this.contentSubtype = contentSubtype;
	}

	public void setContentSubtype(
		UnsafeSupplier<String, Exception> contentSubtypeUnsafeSupplier) {

		try {
			contentSubtype = contentSubtypeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String contentSubtype;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setContentType(
		UnsafeSupplier<String, Exception> contentTypeUnsafeSupplier) {

		try {
			contentType = contentTypeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String contentType;

	@Override
	public ContentAssociation clone() throws CloneNotSupportedException {
		return (ContentAssociation)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContentAssociation)) {
			return false;
		}

		ContentAssociation contentAssociation = (ContentAssociation)object;

		return Objects.equals(toString(), contentAssociation.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ContentAssociationSerDes.toJSON(this);
	}

}