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

package com.liferay.commerce.product.item.selector.web.internal.search;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionItemSelectorChecker extends EmptyOnClickRowChecker {

	public CPDefinitionItemSelectorChecker(
		RenderResponse renderResponse, long[] checkedCPDefinitionIds,
		long disabledCPDefinitionId) {

		super(renderResponse);

		_checkedCPDefinitionIds = SetUtil.fromArray(checkedCPDefinitionIds);
		_disabledCPDefinitionId = disabledCPDefinitionId;
	}

	@Override
	public boolean isChecked(Object object) {
		CPDefinition cpDefinition = (CPDefinition)object;

		return _checkedCPDefinitionIds.contains(
			cpDefinition.getCPDefinitionId());
	}

	@Override
	public boolean isDisabled(Object object) {
		CPDefinition cpDefinition = (CPDefinition)object;

		boolean cpDefinitionDisabled = false;

		if (_disabledCPDefinitionId == cpDefinition.getCPDefinitionId()) {
			cpDefinitionDisabled = true;
		}

		if (isChecked(object) || cpDefinitionDisabled) {
			return true;
		}

		return false;
	}

	private final Set<Long> _checkedCPDefinitionIds;
	private final Long _disabledCPDefinitionId;

}