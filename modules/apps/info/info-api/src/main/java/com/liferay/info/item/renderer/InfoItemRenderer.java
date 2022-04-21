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

package com.liferay.info.item.renderer;

import com.liferay.info.type.Keyed;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jorge Ferrer
 */
public interface InfoItemRenderer<T> extends Keyed {

	public default String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}

	public default boolean isAvailable() {
		return true;
	}

	public void render(
		T t, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

}