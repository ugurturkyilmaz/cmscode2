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

package com.liferay.item.selector.web.internal.util;

import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alejandro Tardín
 */
public class ItemSelectorKeyUtil {

	public static String getItemSelectorCriterionKey(
		Class<? extends ItemSelectorCriterion> clazz) {

		return _getKey(clazz, ItemSelectorCriterion.class.getSimpleName());
	}

	public static String getItemSelectorReturnTypeKey(
		Class<? extends ItemSelectorReturnType> clazz) {

		return _getKey(clazz, ItemSelectorReturnType.class.getSimpleName());
	}

	private static String _getKey(Class<?> clazz, String suffix) {
		String key = _itemSelectorKeysMap.get(clazz.getName());

		if (key == null) {
			key = StringUtil.lowerCase(
				StringUtil.removeSubstring(clazz.getSimpleName(), suffix));

			if (_itemSelectorKeysMap.containsValue(key)) {
				key = clazz.getName();
			}

			String oldKey = _itemSelectorKeysMap.putIfAbsent(
				clazz.getName(), key);

			if (oldKey != null) {
				key = oldKey;
			}
		}

		return key;
	}

	private static final Map<String, String> _itemSelectorKeysMap =
		new ConcurrentHashMap<>();

}