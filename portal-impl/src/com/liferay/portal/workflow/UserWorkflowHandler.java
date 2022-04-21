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

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.audit.AuditRequestThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Michael C. Han
 */
@OSGiBeanProperties
public class UserWorkflowHandler extends BaseWorkflowHandler<User> {

	@Override
	public String getClassName() {
		return User.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		return ResourceActionsUtil.getModelResource(locale, getClassName());
	}

	@Override
	public boolean isScopeable() {
		return false;
	}

	@Override
	public User updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		long userId = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		User user = UserLocalServiceUtil.getUser(userId);

		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			WorkflowConstants.CONTEXT_SERVICE_CONTEXT);

		if (((user.getStatus() == WorkflowConstants.STATUS_DRAFT) ||
			 (user.getStatus() == WorkflowConstants.STATUS_PENDING)) &&
			(status == WorkflowConstants.STATUS_APPROVED)) {

			UserLocalServiceUtil.completeUserRegistration(user, serviceContext);

			_updateAuditRequestThreadLocal(workflowContext);
		}

		return UserLocalServiceUtil.updateStatus(
			userId, status, serviceContext);
	}

	private void _updateAuditRequestThreadLocal(
		Map<String, Serializable> workflowContext) {

		AuditRequestThreadLocal auditRequestThreadLocal =
			AuditRequestThreadLocal.getAuditThreadLocal();

		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			WorkflowConstants.CONTEXT_SERVICE_CONTEXT);

		auditRequestThreadLocal.setClientHost(serviceContext.getRemoteHost());
		auditRequestThreadLocal.setClientIP(serviceContext.getRemoteAddr());

		long userId = GetterUtil.getLong(
			(String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

		if (userId != 0) {
			auditRequestThreadLocal.setRealUserId(userId);
		}

		HttpServletRequest httpServletRequest =
			PortalUtil.getOriginalServletRequest(serviceContext.getRequest());

		if (httpServletRequest == null) {
			return;
		}

		auditRequestThreadLocal.setServerName(
			httpServletRequest.getServerName());
		auditRequestThreadLocal.setServerPort(
			httpServletRequest.getServerPort());

		HttpSession httpSession = httpServletRequest.getSession();

		if (httpSession == null) {
			return;
		}

		auditRequestThreadLocal.setSessionID(httpSession.getId());
	}

}