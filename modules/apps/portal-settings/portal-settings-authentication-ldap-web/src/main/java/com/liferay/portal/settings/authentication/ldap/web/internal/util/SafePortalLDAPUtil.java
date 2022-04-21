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

package com.liferay.portal.settings.authentication.ldap.web.internal.util;

import com.liferay.portal.security.ldap.SafePortalLDAP;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Edward C. Han
 */
@Component(immediate = true, service = {})
public class SafePortalLDAPUtil {

	public static SafePortalLDAP getSafePortalLDAP() {
		return _safePortalLDAP;
	}

	@Reference(policyOption = ReferencePolicyOption.GREEDY, unbind = "-")
	protected void setPortalLDAP(SafePortalLDAP safePortalLDAP) {
		_safePortalLDAP = safePortalLDAP;
	}

	private static SafePortalLDAP _safePortalLDAP;

}