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

import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.membershippolicy.MembershipPolicyException;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + UsersAdminPortletKeys.MY_ORGANIZATIONS,
		"javax.portlet.name=" + UsersAdminPortletKeys.USERS_ADMIN,
		"mvc.command.name=/users_admin/edit_organization_assignments"
	},
	service = MVCActionCommand.class
)
public class EditOrganizationAssignmentsMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			_updateOrganizationUsers(actionRequest);

			String redirect = ParamUtil.getString(
				actionRequest, "assignmentsRedirect");

			sendRedirect(actionRequest, actionResponse, redirect);
		}
		catch (Exception exception) {
			if (exception instanceof MembershipPolicyException) {
				SessionErrors.add(
					actionRequest, exception.getClass(), exception);
			}
			else if (exception instanceof NoSuchOrganizationException ||
					 exception instanceof PrincipalException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw exception;
			}
		}
	}

	@Reference(unbind = "-")
	protected void setUserService(UserService userService) {
		_userService = userService;
	}

	private void _updateOrganizationUsers(ActionRequest actionRequest)
		throws Exception {

		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationId");

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		try (SafeCloseable safeCloseable =
				ProxyModeThreadLocal.setWithSafeCloseable(true)) {

			_userService.addOrganizationUsers(organizationId, addUserIds);
			_userService.unsetOrganizationUsers(organizationId, removeUserIds);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Organization.class.getName(), actionRequest);

			long[] removeOrganizationIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "removeOrganizationIds"),
				0L);

			for (long removeOrganizationId : removeOrganizationIds) {
				Organization organization =
					_organizationService.getOrganization(removeOrganizationId);

				Group organizationGroup = organization.getGroup();

				_organizationService.updateOrganization(
					removeOrganizationId,
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
					organization.getName(), organization.getType(),
					organization.getRegionId(), organization.getCountryId(),
					organization.getStatusId(), organization.getComments(),
					organizationGroup.isSite(), serviceContext);
			}
		}
	}

	@Reference
	private OrganizationService _organizationService;

	private UserService _userService;

}