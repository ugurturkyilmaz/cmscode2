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

package com.liferay.sharing.servlet.taglib.ui;

import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.sharing.model.SharingEntry;

import java.util.Collection;

/**
 * @author Adolfo Pérez
 */
public interface SharingEntryMenuItemContributor {

	public Collection<MenuItem> getSharingEntryMenuItems(
		SharingEntry sharingEntry, ThemeDisplay themeDisplay);

}