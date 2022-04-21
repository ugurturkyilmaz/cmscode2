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

package com.liferay.info.item;

import com.liferay.info.localized.InfoLocalizedValue;

import java.util.Locale;

/**
 * @author Jorge Ferrer
 */
public class InfoItemClassDetails {

	public InfoItemClassDetails(String className) {
		this(className, InfoLocalizedValue.modelResource(className));
	}

	public InfoItemClassDetails(
		String className, InfoLocalizedValue<String> labelInfoLocalizedValue) {

		_className = className;
		_labelInfoLocalizedValue = labelInfoLocalizedValue;
	}

	public String getClassName() {
		return _className;
	}

	public String getLabel(Locale locale) {
		return _labelInfoLocalizedValue.getValue(locale);
	}

	public InfoLocalizedValue<String> getLabelInfoLocalizedValue() {
		return _labelInfoLocalizedValue;
	}

	private final String _className;
	private final InfoLocalizedValue<String> _labelInfoLocalizedValue;

}