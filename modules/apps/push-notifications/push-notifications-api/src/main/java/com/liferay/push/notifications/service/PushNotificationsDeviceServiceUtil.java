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

package com.liferay.push.notifications.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.push.notifications.model.PushNotificationsDevice;

import java.util.List;

/**
 * Provides the remote service utility for PushNotificationsDevice. This utility wraps
 * <code>com.liferay.push.notifications.service.impl.PushNotificationsDeviceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceService
 * @generated
 */
public class PushNotificationsDeviceServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.push.notifications.service.impl.PushNotificationsDeviceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static PushNotificationsDevice addPushNotificationsDevice(
			String token, String platform)
		throws PortalException {

		return getService().addPushNotificationsDevice(token, platform);
	}

	public static PushNotificationsDevice deletePushNotificationsDevice(
			long pushNotificationsDeviceId)
		throws PortalException {

		return getService().deletePushNotificationsDevice(
			pushNotificationsDeviceId);
	}

	public static PushNotificationsDevice deletePushNotificationsDevice(
			String token)
		throws PortalException {

		return getService().deletePushNotificationsDevice(token);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void sendPushNotification(long[] toUserIds, String payload)
		throws PortalException {

		getService().sendPushNotification(toUserIds, payload);
	}

	public static void sendPushNotification(
			String platform, List<String> tokens, String payload)
		throws PortalException {

		getService().sendPushNotification(platform, tokens, payload);
	}

	public static PushNotificationsDeviceService getService() {
		return _service;
	}

	private static volatile PushNotificationsDeviceService _service;

}