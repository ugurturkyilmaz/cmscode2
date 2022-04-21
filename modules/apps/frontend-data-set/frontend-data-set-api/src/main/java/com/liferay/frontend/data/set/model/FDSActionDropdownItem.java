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

package com.liferay.frontend.data.set.model;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;

/**
 * @author Marco Leo
 */
public class FDSActionDropdownItem extends DropdownItem {

	public FDSActionDropdownItem(
		String href, String icon, String id, String label, String method,
		String permissionKey, String target) {

		setHref(href);
		setIcon(icon);
		setId(id);
		setLabel(label);
		setMethod(method);
		setPermissionKey(permissionKey);
		setTarget(target);
	}

	public FDSActionDropdownItem(
		String confirmationMessage, String href, String icon, String id,
		String label, String method, String permissionKey, String target) {

		this(href, icon, id, label, method, permissionKey, target);

		setConfirmationMessage(confirmationMessage);
	}

	public void setConfirmationMessage(String confirmationMessage) {
		putData("confirmationMessage", confirmationMessage);
	}

	public void setId(String id) {
		putData("id", id);
	}

	public void setMethod(String method) {
		putData("method", method);
	}

	public void setPermissionKey(String permissionKey) {
		putData("permissionKey", permissionKey);
	}

}