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

package com.liferay.document.library.internal.upgrade.v1_1_2;

import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alberto Chaparro
 */
public class DLFileEntryTypeUpgradeProcess extends UpgradeProcess {

	public DLFileEntryTypeUpgradeProcess(
		ResourceLocalService resourceLocalService) {

		_resourceLocalService = resourceLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"select fileEntryTypeId, companyId, userId from " +
					"DLFileEntryType where fileEntryTypeKey in ('IMAGE " +
						"GALLERY IMAGE', 'Image Gallery Image')");
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long fileEntryTypeId = resultSet.getLong("fileEntryTypeId");
				long companyId = resultSet.getLong("companyId");
				long userId = resultSet.getLong("userId");

				_resourceLocalService.addResources(
					companyId, 0, userId,
					"com.liferay.document.library.kernel.model.DLFileEntryType",
					String.valueOf(fileEntryTypeId), false, false, true);
			}
		}
	}

	private final ResourceLocalService _resourceLocalService;

}