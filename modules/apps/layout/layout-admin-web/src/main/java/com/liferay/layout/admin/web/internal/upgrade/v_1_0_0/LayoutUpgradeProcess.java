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

package com.liferay.layout.admin.web.internal.upgrade.v_1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jürgen Kappler
 */
public class LayoutUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_updateLayouts();
	}

	private void _updateLayout(long plid, String typeSettings)
		throws Exception {

		if (Validator.isNull(typeSettings)) {
			return;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.create(
				true
			).load(
				typeSettings
			).build();

		typeSettingsUnicodeProperties.setProperty(
			"embeddedLayoutURL", typeSettingsUnicodeProperties.remove("url"));

		_updateTypeSettings(plid, typeSettingsUnicodeProperties.toString());
	}

	private void _updateLayouts() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement preparedStatement = connection.prepareStatement(
				"select plid, typeSettings from Layout where type_ = ?")) {

			preparedStatement.setString(1, "embedded");

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					long plid = resultSet.getLong("plid");
					String typeSettings = resultSet.getString("typeSettings");

					_updateLayout(plid, typeSettings);
				}
			}
		}
	}

	private void _updateTypeSettings(long plid, String typeSettings)
		throws Exception {

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"update Layout set typeSettings = ? where plid = ?")) {

			preparedStatement.setString(1, typeSettings);
			preparedStatement.setLong(2, plid);

			preparedStatement.executeUpdate();
		}
	}

}