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

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Eduardo García
 */
public class UpgradeEmailNotificationPreferences extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_PASSWORD_RESET_BODY,
			"adminEmailPasswordResetBody");
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_PASSWORD_RESET_SUBJECT,
			"adminEmailPasswordResetSubject");
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_PASSWORD_SENT_BODY,
			"adminEmailPasswordSentBody");
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_PASSWORD_SENT_SUBJECT,
			"adminEmailPasswordSentSubject");
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_USER_ADDED_BODY, "adminEmailUserAddedBody");
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_USER_ADDED_NO_PASSWORD_BODY,
			"adminEmailUserAddedNoPasswordBody");
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_USER_ADDED_SUBJECT,
			"adminEmailUserAddedSubject");
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_VERIFICATION_BODY,
			"adminEmailVerificationBody");
		_updatePreferences(
			PropsKeys.ADMIN_EMAIL_VERIFICATION_SUBJECT,
			"adminEmailVerificationSubject");
	}

	private void _updatePreferences(String oldValue, String newValue)
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			try {
				runSQL(
					StringBundler.concat(
						"update PortalPreferences set preferences = ",
						"replace(preferences, '", oldValue, "', '", newValue,
						"') where preferences like '%", oldValue, "%'"));
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					_log.debug(exception);
				}

				try (PreparedStatement preparedStatement1 =
						connection.prepareStatement(
							StringBundler.concat(
								"select portalPreferencesId, preferences from ",
								"PortalPreferences where preferences like '%",
								oldValue, "%'"));
					PreparedStatement preparedStatement2 =
						connection.prepareStatement(
							"update PortalPreferences set preferences = ? " +
								"where portalPreferencesId = ?");
					ResultSet resultSet = preparedStatement1.executeQuery()) {

					while (resultSet.next()) {
						preparedStatement2.setString(
							1,
							StringUtil.replace(
								resultSet.getString("preferences"), oldValue,
								newValue));
						preparedStatement2.setLong(
							2, resultSet.getLong("portalPreferencesId"));

						preparedStatement2.executeUpdate();
					}
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeEmailNotificationPreferences.class);

}