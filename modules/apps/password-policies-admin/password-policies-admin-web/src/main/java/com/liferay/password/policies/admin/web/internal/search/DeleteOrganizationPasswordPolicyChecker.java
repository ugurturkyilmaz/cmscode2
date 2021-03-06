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

package com.liferay.password.policies.admin.web.internal.search;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.PasswordPolicyRel;
import com.liferay.portal.kernel.service.PasswordPolicyRelLocalServiceUtil;

import javax.portlet.RenderResponse;

/**
 * @author Scott Lee
 */
public class DeleteOrganizationPasswordPolicyChecker
	extends EmptyOnClickRowChecker {

	public DeleteOrganizationPasswordPolicyChecker(
		RenderResponse renderResponse, PasswordPolicy passwordPolicy) {

		super(renderResponse);

		_passwordPolicy = passwordPolicy;
	}

	@Override
	public boolean isDisabled(Object object) {
		Organization organization = (Organization)object;

		try {
			PasswordPolicyRel passwordPolicyRel =
				PasswordPolicyRelLocalServiceUtil.fetchPasswordPolicyRel(
					Organization.class.getName(),
					organization.getOrganizationId());

			if ((passwordPolicyRel != null) &&
				(passwordPolicyRel.getPasswordPolicyId() !=
					_passwordPolicy.getPasswordPolicyId())) {

				return true;
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeleteOrganizationPasswordPolicyChecker.class);

	private final PasswordPolicy _passwordPolicy;

}