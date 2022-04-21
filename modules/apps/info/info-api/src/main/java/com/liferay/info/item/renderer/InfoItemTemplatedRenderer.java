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

import com.liferay.info.item.renderer.template.InfoItemRendererTemplate;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eudaldo Alonso
 */
public interface InfoItemTemplatedRenderer<T> extends InfoItemRenderer<T> {

	public default List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		String className, String classTypeKey, Locale locale) {

		return Collections.emptyList();
	}

	public List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		T t, Locale locale);

	public default String getInfoItemRendererTemplatesGroupLabel(
		String className, String classTypeKey, Locale locale) {

		return getLabel(locale);
	}

	public default String getInfoItemRendererTemplatesGroupLabel(
		T t, Locale locale) {

		return getLabel(locale);
	}

	@Override
	public default void render(
		T t, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		List<InfoItemRendererTemplate> infoItemRendererTemplates =
			getInfoItemRendererTemplates(t, LocaleUtil.getMostRelevantLocale());

		if (ListUtil.isEmpty(infoItemRendererTemplates)) {
			return;
		}

		InfoItemRendererTemplate infoItemRendererTemplate =
			infoItemRendererTemplates.get(0);

		render(
			t, infoItemRendererTemplate.getTemplateKey(), httpServletRequest,
			httpServletResponse);
	}

	public void render(
		T t, String templateKey, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

}