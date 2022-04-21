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

import com.liferay.account.admin.web.internal.security.permission.resource.AccountEntryPermission;
import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.constants.AccountPortletKeys;
import com.liferay.account.retriever.AccountOrganizationRetriever;
import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pei-Jung Lan
 */
@Component(service = {})
public class AccountOrganizationSearchContainerFactory {

	public static SearchContainer<Organization> create(
			long accountEntryId, LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		String emptyResultsMessage =
			"there-are-no-organizations-associated-with-this-account";

		int count =
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsCount(accountEntryId);

		if (count > 0) {
			emptyResultsMessage = "no-organizations-were-found";
		}

		SearchContainer<Organization> searchContainer = new SearchContainer(
			liferayPortletRequest,
			PortletURLUtil.getCurrent(
				liferayPortletRequest, liferayPortletResponse),
			null, emptyResultsMessage);

		searchContainer.setId("accountOrganizations");

		String orderByCol = SearchOrderByUtil.getOrderByCol(
			liferayPortletRequest, AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
			"organization-order-by-col", "name");

		if (orderByCol.equals("id")) {
			orderByCol = "organizationId";
		}

		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByType(
			SearchOrderByUtil.getOrderByType(
				liferayPortletRequest, AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
				"organization-order-by-type", "asc"));

		String keywords = ParamUtil.getString(
			liferayPortletRequest, "keywords", null);

		searchContainer.setResultsAndTotal(
			_accountOrganizationRetriever.searchAccountOrganizations(
				accountEntryId, keywords, searchContainer.getStart(),
				searchContainer.getDelta(), searchContainer.getOrderByCol(),
				Objects.equals(searchContainer.getOrderByType(), "desc")));

		if (AccountEntryPermission.contains(
				PermissionCheckerFactoryUtil.create(
					PortalUtil.getUser(liferayPortletRequest)),
				accountEntryId, AccountActionKeys.MANAGE_ORGANIZATIONS)) {

			searchContainer.setRowChecker(
				new EmptyOnClickRowChecker(liferayPortletResponse));
		}

		return searchContainer;
	}

	@Reference(unbind = "-")
	protected void setAccountEntryOrganizationRelLocalService(
		AccountEntryOrganizationRelLocalService
			accountEntryOrganizationRelLocalService) {

		_accountEntryOrganizationRelLocalService =
			accountEntryOrganizationRelLocalService;
	}

	@Reference(unbind = "-")
	protected void setAccountOrganizationRetriever(
		AccountOrganizationRetriever accountOrganizationRetriever) {

		_accountOrganizationRetriever = accountOrganizationRetriever;
	}

	private static AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;
	private static AccountOrganizationRetriever _accountOrganizationRetriever;

}