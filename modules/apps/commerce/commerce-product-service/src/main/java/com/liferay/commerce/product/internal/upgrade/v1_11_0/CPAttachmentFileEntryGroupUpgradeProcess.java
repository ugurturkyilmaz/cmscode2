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

package com.liferay.commerce.product.internal.upgrade.v1_11_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.exception.NoSuchClassNameException;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 */
public class CPAttachmentFileEntryGroupUpgradeProcess extends UpgradeProcess {

	public CPAttachmentFileEntryGroupUpgradeProcess(
		AssetCategoryLocalService assetCategoryLocalService,
		ClassNameLocalService classNameLocalService) {

		_assetCategoryLocalService = assetCategoryLocalService;
		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(
				"select classNameId, classPK from CPAttachmentFileEntry")) {

			long cpDefinitionClassNameId = _getCPDefinitionClassNameId();
			long assetCategoryClassNameId =
				_classNameLocalService.getClassNameId(AssetCategory.class);

			String updateCPAttachmentFileEntrySQL =
				"update CPAttachmentFileEntry set groupId = ? where " +
					"classNameId = ? and classPK = ?";

			try (PreparedStatement preparedStatement =
					AutoBatchPreparedStatementUtil.concurrentAutoBatch(
						connection, updateCPAttachmentFileEntrySQL)) {

				while (resultSet.next()) {
					long classNameId = resultSet.getLong("classNameId");
					long classPK = resultSet.getLong("classPK");

					long groupId;

					if (classNameId == assetCategoryClassNameId) {
						AssetCategory assetCategory =
							_assetCategoryLocalService.getAssetCategory(
								classPK);

						groupId = assetCategory.getGroupId();
					}
					else if (classNameId == cpDefinitionClassNameId) {
						groupId = _getGroupIdFromCPDefinition(classPK);
					}
					else {
						continue;
					}

					preparedStatement.setLong(1, groupId);
					preparedStatement.setLong(2, classNameId);
					preparedStatement.setLong(3, classPK);

					preparedStatement.addBatch();
				}

				preparedStatement.executeBatch();
			}
		}
	}

	private long _getCPDefinitionClassNameId() throws Exception {
		try (Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(
				"select classNameId from ClassName_ where value = " +
					"'com.liferay.commerce.product.model.CPDefinition'")) {

			if (resultSet.next()) {
				return resultSet.getLong("classNameId");
			}
		}

		throw new NoSuchClassNameException();
	}

	private long _getGroupIdFromCPDefinition(long cpDefinitionId)
		throws Exception {

		try (Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(
				"select groupId from CPDefinition where cpDefinitionId = " +
					cpDefinitionId)) {

			if (resultSet.next()) {
				return resultSet.getLong("groupId");
			}
		}

		throw new NoSuchGroupException();
	}

	private final AssetCategoryLocalService _assetCategoryLocalService;
	private final ClassNameLocalService _classNameLocalService;

}