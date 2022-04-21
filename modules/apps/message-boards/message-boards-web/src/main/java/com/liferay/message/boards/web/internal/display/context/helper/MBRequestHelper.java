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

package com.liferay.message.boards.web.internal.display.context.helper;

import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.display.context.helper.BaseRequestHelper;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Iván Zaera
 */
public class MBRequestHelper extends BaseRequestHelper {

	public MBRequestHelper(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
	}

	public MBCategory getCategory() {
		if (_category == null) {
			HttpServletRequest httpServletRequest = getRequest();

			_category = (MBCategory)httpServletRequest.getAttribute(
				WebKeys.MESSAGE_BOARDS_CATEGORY);
		}

		return _category;
	}

	public long getParentCategoryId() {
		if (_parentCategoryId == null) {
			_parentCategoryId = BeanParamUtil.getLong(
				getCategory(), getRequest(), "parentCategoryId",
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
		}

		return _parentCategoryId;
	}

	private MBCategory _category;
	private Long _parentCategoryId;

}