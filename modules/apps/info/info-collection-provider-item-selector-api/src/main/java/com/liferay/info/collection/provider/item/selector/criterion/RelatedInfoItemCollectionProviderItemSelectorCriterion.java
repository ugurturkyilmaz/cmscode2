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

package com.liferay.info.collection.provider.item.selector.criterion;

import com.liferay.item.selector.BaseItemSelectorCriterion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Víctor Galán
 */
public class RelatedInfoItemCollectionProviderItemSelectorCriterion
	extends BaseItemSelectorCriterion {

	public List<String> getSourceItemTypes() {
		return _sourceItemTypes;
	}

	public void setSourceItemTypes(List<String> sourceItemTypes) {
		_sourceItemTypes = sourceItemTypes;
	}

	private List<String> _sourceItemTypes = new ArrayList<>();

}