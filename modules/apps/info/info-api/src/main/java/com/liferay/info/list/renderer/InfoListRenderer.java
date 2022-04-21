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

package com.liferay.info.list.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.type.Keyed;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jorge Ferrer
 */
public interface InfoListRenderer<T> extends Keyed {

	public default List<InfoItemRenderer<?>> getAvailableInfoItemRenderers() {
		return Collections.emptyList();
	}

	public default String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}

	public void render(
		List<T> list, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

	public default void render(
		List<T> list, InfoListRendererContext infoListRendererContext) {

		render(
			list, infoListRendererContext.getHttpServletRequest(),
			infoListRendererContext.getHttpServletResponse());
	}

}