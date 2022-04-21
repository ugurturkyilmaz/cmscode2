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

package com.liferay.users.admin.web.internal.portlet.action;

import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;

import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gavin Wan
 * @author Pei-Jung Lan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + UsersAdminPortletKeys.USERS_ADMIN,
		"mvc.command.name=/users_admin/get_users_count"
	},
	service = MVCResourceCommand.class
)
public class GetUsersCountMVCResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			PrintWriter printWriter = resourceResponse.getWriter();

			printWriter.write(_getText(resourceRequest));

			return false;
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private int _getOrganizationUsersCount(
			long companyId, long[] organizationIds, int status)
		throws Exception {

		int count = 0;

		for (long organizationId : organizationIds) {
			count += _userLocalService.searchCount(
				companyId, null, status,
				LinkedHashMapBuilder.<String, Object>put(
					"usersOrgs", organizationId
				).build());
		}

		return count;
	}

	private String _getText(ResourceRequest resourceRequest) throws Exception {
		HttpServletRequest httpServletRequest =
			_portal.getOriginalServletRequest(
				_portal.getHttpServletRequest(resourceRequest));

		long companyId = _portal.getCompanyId(httpServletRequest);

		String className = ParamUtil.getString(httpServletRequest, "className");
		long[] ids = StringUtil.split(
			ParamUtil.getString(httpServletRequest, "ids"), 0L);
		int status = ParamUtil.getInteger(httpServletRequest, "status");

		int count = 0;

		if (className.equals(Organization.class.getName())) {
			count = _getOrganizationUsersCount(companyId, ids, status);
		}
		else if (className.equals(UserGroup.class.getName())) {
			count = _getUserGroupUsersCount(companyId, ids, status);
		}

		return String.valueOf(count);
	}

	private int _getUserGroupUsersCount(
			long companyId, long[] userGroupIds, int status)
		throws Exception {

		int count = 0;

		for (long userGroupId : userGroupIds) {
			count += _userLocalService.searchCount(
				companyId, null, status,
				LinkedHashMapBuilder.<String, Object>put(
					"usersUserGroups", userGroupId
				).build());
		}

		return count;
	}

	@Reference
	private Portal _portal;

	private UserLocalService _userLocalService;

}