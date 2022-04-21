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

package com.liferay.portal.search.highlight;

import com.liferay.portal.search.query.Query;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Michael C. Han
 * @author André de Oliveira
 */
@ProviderType
public interface FieldConfig {

	public char[] getBoundaryChars();

	public Integer getBoundaryMaxScan();

	public String getBoundaryScannerLocale();

	public String getBoundaryScannerType();

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getFieldName()}
	 */
	@Deprecated
	public String getField();

	public String getFieldName();

	public Boolean getForceSource();

	public String getFragmenter();

	public Integer getFragmentOffset();

	public Integer getFragmentSize();

	public String getHighlighterType();

	public Boolean getHighlightFilter();

	public Query getHighlightQuery();

	public String[] getMatchedFields();

	public Integer getNoMatchSize();

	public Integer getNumFragments();

	public String getOrder();

	public Integer getPhraseLimit();

	public String[] getPostTags();

	public String[] getPreTags();

	public Boolean getRequireFieldMatch();

}