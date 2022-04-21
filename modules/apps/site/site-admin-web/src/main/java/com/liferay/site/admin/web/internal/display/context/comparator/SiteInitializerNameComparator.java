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

package com.liferay.site.admin.web.internal.display.context.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.site.admin.web.internal.util.SiteInitializerItem;

/**
 * @author Marco Leo
 */
public class SiteInitializerNameComparator
	extends OrderByComparator<SiteInitializerItem> {

	public SiteInitializerNameComparator() {
		this(false);
	}

	public SiteInitializerNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		SiteInitializerItem siteInitializerItem1,
		SiteInitializerItem siteInitializerItem2) {

		String name1 = siteInitializerItem1.getName();
		String name2 = siteInitializerItem2.getName();

		int value = name1.compareToIgnoreCase(name2);

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}