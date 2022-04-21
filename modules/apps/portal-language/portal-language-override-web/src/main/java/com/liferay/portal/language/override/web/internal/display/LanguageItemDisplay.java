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

package com.liferay.portal.language.override.web.internal.display;

import com.liferay.petra.string.StringPool;

/**
 * @author Drew Brokke
 */
public class LanguageItemDisplay {

	public LanguageItemDisplay(String key, String value) {
		_key = key;
		_value = value;
	}

	public String getKey() {
		return _key;
	}

	public String getOverrideLanguageIdsString() {
		return _overrideLanguageIdsString;
	}

	public String getValue() {
		return _value;
	}

	public boolean isOverride() {
		return _override;
	}

	public boolean isOverrideSelectedLanguageId() {
		return _overrideSelectedLanguageId;
	}

	public void setOverride(boolean override) {
		_override = override;
	}

	public void setOverrideLanguageIdsString(String overrideLanguageIdsString) {
		_overrideLanguageIdsString = overrideLanguageIdsString;
	}

	public void setOverrideSelectedLanguageId(
		boolean overrideSelectedLanguageId) {

		_overrideSelectedLanguageId = overrideSelectedLanguageId;
	}

	private final String _key;
	private boolean _override;
	private String _overrideLanguageIdsString = StringPool.BLANK;
	private boolean _overrideSelectedLanguageId;
	private final String _value;

}