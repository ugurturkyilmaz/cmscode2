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

package com.liferay.portal.configuration.persistence.internal.upgrade.v1_0_0;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.file.install.constants.FileInstallConstants;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.io.File;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Dictionary;

import org.apache.felix.cm.file.ConfigurationHandler;

/**
 * @author Sam Ziemer
 */
public class UpgradeConfigurationPid extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable("Configuration_")) {
			return;
		}

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				"select * from Configuration_");
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update Configuration_ set configurationId = ?, " +
						"dictionary = ? where configurationId = ?");
			ResultSet resultSet = preparedStatement1.executeQuery()) {

			while (resultSet.next()) {
				String dictionaryString = resultSet.getString("dictionary");

				if (Validator.isNull(dictionaryString)) {
					continue;
				}

				Dictionary<String, String> dictionary =
					ConfigurationHandler.read(
						new UnsyncByteArrayInputStream(
							dictionaryString.getBytes(StringPool.UTF8)));

				String serviceFactoryPid = dictionary.get("service.factoryPid");

				if (serviceFactoryPid == null) {
					continue;
				}

				String currentConfigurationId = resultSet.getString(
					"configurationId");

				String felixFileInstallFilename = dictionary.get(
					FileInstallConstants.FELIX_FILE_INSTALL_FILENAME);

				int serviceFactoryPidEndIndex = serviceFactoryPid.length();

				String suffix = currentConfigurationId.substring(
					serviceFactoryPidEndIndex + 1);

				if (felixFileInstallFilename != null) {
					File file = new File(
						PropsValues.MODULE_FRAMEWORK_CONFIGS_DIR,
						felixFileInstallFilename);

					if (file.exists()) {
						suffix = felixFileInstallFilename.substring(
							serviceFactoryPidEndIndex + 1,
							felixFileInstallFilename.lastIndexOf(
								CharPool.PERIOD));
					}
					else {
						dictionary.remove(
							FileInstallConstants.FELIX_FILE_INSTALL_FILENAME);
					}
				}

				String updatedConfigurationId = StringBundler.concat(
					serviceFactoryPid, CharPool.TILDE, suffix);

				dictionary.put("service.pid", updatedConfigurationId);

				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
					new UnsyncByteArrayOutputStream();

				ConfigurationHandler.write(
					unsyncByteArrayOutputStream, dictionary);

				preparedStatement2.setString(1, updatedConfigurationId);
				preparedStatement2.setString(
					2, unsyncByteArrayOutputStream.toString());
				preparedStatement2.setString(3, currentConfigurationId);

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

}