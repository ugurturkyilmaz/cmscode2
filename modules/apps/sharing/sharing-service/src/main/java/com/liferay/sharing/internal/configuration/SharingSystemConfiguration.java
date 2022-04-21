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

package com.liferay.sharing.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Adolfo Pérez
 */
@ExtendedObjectClassDefinition(category = "sharing")
@Meta.OCD(
	id = "com.liferay.sharing.internal.configuration.SharingSystemConfiguration",
	localization = "content/Language", name = "sharing-configuration-name"
)
public interface SharingSystemConfiguration {

	/**
	 * Sets the interval in minutes of how often {@code
	 * com.liferay.sharing.internal.messaging.DeleteExpiredSharingEntriesMessageListener}
	 * checks for expired sharing entries.
	 */
	@Meta.AD(
		deflt = "60",
		description = "expired-sharing-entries-check-interval-key-description",
		name = "expired-sharing-entries-check-interval", required = false
	)
	public int expiredSharingEntriesCheckInterval();

	/**
	 * Enables sharing.
	 *
	 * @review
	 */
	@Meta.AD(deflt = "true", name = "enabled", required = false)
	public boolean enabled();

}