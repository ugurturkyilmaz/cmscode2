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

package com.liferay.journal.web.internal.servlet.taglib.clay;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.VerticalCard;
import com.liferay.journal.web.internal.display.context.JournalSelectDDMTemplateDisplayContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.Map;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class JournalSelectDDMTemplateVerticalCard implements VerticalCard {

	public JournalSelectDDMTemplateVerticalCard(
		BaseModel<?> baseModel, RenderRequest renderRequest,
		JournalSelectDDMTemplateDisplayContext
			journalSelectDDMTemplateDisplayContext) {

		_journalSelectDDMTemplateDisplayContext =
			journalSelectDDMTemplateDisplayContext;

		_ddmTemplate = (DDMTemplate)baseModel;
		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
	}

	@Override
	public String getCssClass() {
		if (_ddmTemplate.getTemplateId() !=
				_journalSelectDDMTemplateDisplayContext.getDDMTemplateId()) {

			return "card-interactive card-interactive-secondary " +
				"selector-button";
		}

		return StringPool.BLANK;
	}

	@Override
	public Map<String, String> getDynamicAttributes() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return HashMapBuilder.put(
			"data-ddmtemplateid", String.valueOf(_ddmTemplate.getTemplateId())
		).put(
			"data-ddmtemplatekey", _ddmTemplate.getTemplateKey()
		).put(
			"data-description",
			_ddmTemplate.getDescription(themeDisplay.getLocale())
		).put(
			"data-imageurl", _ddmTemplate.getTemplateImageURL(themeDisplay)
		).put(
			"data-name", _ddmTemplate.getName(themeDisplay.getLocale())
		).build();
	}

	@Override
	public String getIcon() {
		return "page-template";
	}

	@Override
	public String getImageSrc() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return HtmlUtil.escapeAttribute(
			_ddmTemplate.getTemplateImageURL(themeDisplay));
	}

	@Override
	public String getSubtitle() {
		Date createDate = _ddmTemplate.getModifiedDate();

		String modifiedDateDescription = LanguageUtil.getTimeDescription(
			_httpServletRequest,
			System.currentTimeMillis() - createDate.getTime(), true);

		return LanguageUtil.format(
			_httpServletRequest, "modified-x-ago", modifiedDateDescription);
	}

	@Override
	public String getTitle() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return HtmlUtil.escape(_ddmTemplate.getName(themeDisplay.getLocale()));
	}

	@Override
	public boolean isSelectable() {
		return false;
	}

	private final DDMTemplate _ddmTemplate;
	private final HttpServletRequest _httpServletRequest;
	private final JournalSelectDDMTemplateDisplayContext
		_journalSelectDDMTemplateDisplayContext;

}