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

package com.liferay.expando.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Enumeration;

/**
 * @author Albert Lee
 */
public class ExpandoAttributesUtil {

	public static boolean hasVisibleAttributes(long companyId, Class<?> clazz) {
		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			companyId, clazz.getName());

		Enumeration<String> enumeration = expandoBridge.getAttributeNames();

		while (enumeration.hasMoreElements()) {
			String attributeName = enumeration.nextElement();

			UnicodeProperties unicodeProperties =
				expandoBridge.getAttributeProperties(attributeName);

			if (!GetterUtil.getBoolean(
					unicodeProperties.get(
						ExpandoColumnConstants.PROPERTY_HIDDEN))) {

				return true;
			}
		}

		return false;
	}

}