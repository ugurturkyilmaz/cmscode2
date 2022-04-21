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

package com.liferay.account.internal.search.spi.model.permission;

import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.model.AccountEntry;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.OrganizationPermission;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.search.spi.model.permission.SearchPermissionFilterContributor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(immediate = true, service = SearchPermissionFilterContributor.class)
public class AccountEntrySearchPermissionFilterContributor
	implements SearchPermissionFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, long companyId, long[] groupIds,
		long userId, PermissionChecker permissionChecker, String className) {

		if (!className.equals(AccountEntry.class.getName())) {
			return;
		}

		_addAccountUserIdsFilters(booleanFilter, userId);
		_addOrganizationIdsFilter(
			booleanFilter, companyId, userId, permissionChecker);
	}

	private void _addAccountUserIdsFilters(
		BooleanFilter booleanFilter, long userId) {

		TermsFilter accountUserIdsTermsFilter = new TermsFilter(
			"accountUserIds");

		accountUserIdsTermsFilter.addValue(String.valueOf(userId));

		booleanFilter.add(accountUserIdsTermsFilter, BooleanClauseOccur.SHOULD);
	}

	private void _addOrganizationIdsFilter(
		BooleanFilter booleanFilter, long companyId, long userId,
		PermissionChecker permissionChecker) {

		TermsFilter organizationIdsTermsFilter = new TermsFilter(
			"organizationIds");

		try {
			BaseModelSearchResult<Organization> baseModelSearchResult =
				_organizationLocalService.searchOrganizations(
					companyId, OrganizationConstants.ANY_PARENT_ORGANIZATION_ID,
					null,
					LinkedHashMapBuilder.<String, Object>put(
						"accountsOrgsTree",
						() -> {
							User user = _userLocalService.getUser(userId);

							return ListUtil.filter(
								user.getOrganizations(true),
								organization -> _hasManageAccountsPermission(
									permissionChecker, organization));
						}
					).build(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (Organization organization :
					baseModelSearchResult.getBaseModels()) {

				organizationIdsTermsFilter.addValue(
					String.valueOf(organization.getOrganizationId()));
			}
		}
		catch (PortalException portalException) {
			_log.error(portalException);
		}

		if (!organizationIdsTermsFilter.isEmpty()) {
			booleanFilter.add(
				organizationIdsTermsFilter, BooleanClauseOccur.SHOULD);
		}
	}

	private boolean _hasManageAccountsPermission(
		PermissionChecker permissionChecker, Organization organization) {

		try {
			_organizationPermission.check(
				permissionChecker, organization,
				AccountActionKeys.MANAGE_ACCOUNTS);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountEntrySearchPermissionFilterContributor.class);

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private OrganizationPermission _organizationPermission;

	@Reference
	private UserLocalService _userLocalService;

}