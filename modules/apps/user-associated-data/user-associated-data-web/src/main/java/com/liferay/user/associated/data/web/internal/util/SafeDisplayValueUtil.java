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

package com.liferay.user.associated.data.web.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Drew Brokke
 */
public class SafeDisplayValueUtil {

	public static String get(Object unsafeValue) {
		if (unsafeValue == null) {
			return StringPool.BLANK;
		}

		String unsafeValueString = String.valueOf(unsafeValue);

		String escapedString = HtmlUtil.escape(unsafeValueString);

		return StringUtil.shorten(escapedString, 200);
	}

}