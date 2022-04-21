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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.CountryConstants;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
@JSON(strict = true)
public class CountryImpl extends CountryBaseImpl {

	@Override
	public String getName(Locale locale) {
		String name = LanguageUtil.get(
			locale, CountryConstants.NAME_PREFIX + getName());

		if (!name.startsWith(CountryConstants.NAME_PREFIX)) {
			return name;
		}

		return getName();
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		return getName(getLocale(_nameCurrentLanguageId));
	}

	@Override
	public String getTitle(Locale locale) {
		return getTitle(LanguageUtil.getLanguageId(locale));
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		String title = super.getTitle(languageId, useDefault);

		if (Validator.isNotNull(title)) {
			return title;
		}

		return getName(getLocale(languageId));
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	private String _nameCurrentLanguageId;

}