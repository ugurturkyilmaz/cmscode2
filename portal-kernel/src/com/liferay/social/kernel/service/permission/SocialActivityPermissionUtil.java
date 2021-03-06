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

package com.liferay.social.kernel.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		_socialActivityPermission.check(permissionChecker, groupId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		return _socialActivityPermission.contains(
			permissionChecker, groupId, actionId);
	}

	public static SocialActivityPermission getSocialActivityPermission() {
		return _socialActivityPermission;
	}

	public void setSocialActivityPermission(
		SocialActivityPermission socialActivityPermission) {

		_socialActivityPermission = socialActivityPermission;
	}

	private static SocialActivityPermission _socialActivityPermission;

}