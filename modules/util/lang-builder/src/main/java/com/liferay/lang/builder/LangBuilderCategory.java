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

package com.liferay.lang.builder;

/**
 * @author Hugo Huijser
 */
public enum LangBuilderCategory {

	ACTION_NAMES("Action Names", "action.", 5),
	CATEGORY_TITLES("Category Titles", "category.", 3),
	COUNTRIES("Country", "country.", 7), CURRENCIES("Currency", "currency.", 8),
	LANGUAGE_SETTINGS("Language Settings", "lang.", 1),
	LANGUAGES("Language", "language.", 9), MESSAGES("Messages", "", 6),
	MODEL_RESOURCES("Model Resources", "model.resource.", 4),
	PORLET_INFORMATION("Portlet Descriptions and Titles", "javax.portlet.", 2);

	public String getDescription() {
		return _description;
	}

	public int getIndex() {
		return _index;
	}

	public String getPrefix() {
		return _prefix;
	}

	private LangBuilderCategory(String description, String prefix, int index) {
		_description = description;
		_prefix = prefix;
		_index = index;
	}

	private final String _description;
	private final int _index;
	private final String _prefix;

}