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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;

import java.util.Objects;

/**
 * @author Michael C. Han
 */
public enum NotificationType {

	EMAIL("email"),
	/**
	 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
	 */
	@Deprecated
	IM("im"),
	/**
	 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
	 */
	@Deprecated
	PRIVATE_MESSAGE("private-message"),
	PUSH_NOTIFICATION("push-notification"),
	USER_NOTIFICATION("user-notification");

	public static NotificationType parse(String value)
		throws KaleoDefinitionValidationException {

		if (Objects.equals(EMAIL.getValue(), value)) {
			return EMAIL;
		}
		else if (Objects.equals(PUSH_NOTIFICATION.getValue(), value)) {
			return PUSH_NOTIFICATION;
		}
		else if (Objects.equals(USER_NOTIFICATION.getValue(), value)) {
			return USER_NOTIFICATION;
		}

		throw new KaleoDefinitionValidationException.InvalidNotificationType(
			value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private NotificationType(String value) {
		_value = value;
	}

	private final String _value;

}