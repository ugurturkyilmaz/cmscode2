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

package com.liferay.oauth2.provider.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.oauth2.provider.model.OAuth2Authorization;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;

import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Tomas Polesovsky
 */
public class OAuth2AuthorizationsManagementToolbarDisplayContext
	extends BaseOAuth2ManagementToolbarDisplayContext {

	public OAuth2AuthorizationsManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		PortletURL currentURLObj) {

		super(
			liferayPortletRequest.getHttpServletRequest(),
			liferayPortletRequest, liferayPortletResponse, currentURLObj);
	}

	public List<DropdownItem> getActionDropdownItems() {
		return DropdownItemListBuilder.add(
			dropdownItem -> {
				dropdownItem.putData("action", "revokeOAuth2Authorizations");
				dropdownItem.setIcon("trash");
				dropdownItem.setLabel(
					LanguageUtil.get(
						httpServletRequest, "revoke-authorizations"));
				dropdownItem.setQuickAction(true);
			}
		).build();
	}

	public List<DropdownItem> getFilterDropdownItems() {
		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(_getOrderByDropdownItems());
				dropdownGroupItem.setLabel(
					LanguageUtil.get(httpServletRequest, "order-by"));
			}
		).build();
	}

	public OrderByComparator<OAuth2Authorization> getOrderByComparator() {
		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		String columnName = "createDate";

		for (String orderByColumn : _ORDER_BY_COLUMNS) {
			if (orderByCol.equals(orderByColumn)) {
				columnName = orderByColumn;
			}
		}

		return OrderByComparatorFactoryUtil.create(
			"OAuth2Authorization", columnName, orderByType.equals("asc"));
	}

	private List<DropdownItem> _getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				for (String orderByCol : _ORDER_BY_COLUMNS) {
					add(
						dropdownItem -> {
							dropdownItem.setActive(
								orderByCol.equals(getOrderByCol()));
							dropdownItem.setHref(
								getCurrentSortingURL(), "orderByCol",
								orderByCol);
							dropdownItem.setLabel(
								LanguageUtil.get(
									httpServletRequest, orderByCol));
						});
				}
			}
		};
	}

	private static final String[] _ORDER_BY_COLUMNS = {
		"createDate", "userId", "userName", "accessTokenCreateDate",
		"accessTokenExpirationDate", "refreshTokenCreateDate",
		"refreshTokenExpirationDate", "remoteIPInfo"
	};

}