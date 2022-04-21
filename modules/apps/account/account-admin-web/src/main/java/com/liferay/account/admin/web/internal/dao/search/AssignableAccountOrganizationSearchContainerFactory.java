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

package com.liferay.account.admin.web.internal.dao.search;

import com.liferay.account.constants.AccountPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Pei-Jung Lan
 */
public class AssignableAccountOrganizationSearchContainerFactory {

	public static SearchContainer<Organization> create(
			long accountEntryId, LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		SearchContainer<Organization> searchContainer = new SearchContainer(
			liferayPortletRequest,
			PortletURLUtil.getCurrent(
				liferayPortletRequest, liferayPortletResponse),
			null, "no-organizations-were-found");

		searchContainer.setId("organizations");
		searchContainer.setOrderByCol(
			SearchOrderByUtil.getOrderByCol(
				liferayPortletRequest, AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
				"assignable-account-organization-order-by-col", "name"));
		searchContainer.setOrderByType(
			SearchOrderByUtil.getOrderByType(
				liferayPortletRequest, AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
				"assignable-account-organization-order-by-type", "asc"));

		String keywords = ParamUtil.getString(
			liferayPortletRequest, "keywords", null);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		searchContainer.setResultsAndTotal(
			OrganizationLocalServiceUtil.searchOrganizations(
				themeDisplay.getCompanyId(),
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, keywords,
				null, searchContainer.getStart(), searchContainer.getEnd(),
				SortFactoryUtil.getSort(
					Organization.class, searchContainer.getOrderByCol(),
					searchContainer.getOrderByType())));

		searchContainer.setRowChecker(
			new SelectAccountOrganizationRowChecker(
				liferayPortletResponse, accountEntryId));

		return searchContainer;
	}

}