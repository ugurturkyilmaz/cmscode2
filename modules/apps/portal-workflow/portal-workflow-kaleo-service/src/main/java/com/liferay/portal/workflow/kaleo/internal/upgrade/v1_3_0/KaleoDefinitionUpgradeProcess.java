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

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Leonardo Barros
 */
public class KaleoDefinitionUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"select kaleoDefinitionId, content from KaleoDefinition " +
					"where content like '%WorkflowConstants.toStatus(%'");
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long kaleoDefinitionId = resultSet.getLong(1);

				String content = resultSet.getString(2);

				content = StringUtil.replace(
					content, "WorkflowConstants.toStatus(",
					"WorkflowConstants.getLabelStatus(");

				_updateContent(kaleoDefinitionId, content);
			}
		}
	}

	private void _updateContent(long kaleoDefinitionId, String content)
		throws Exception {

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"update KaleoDefinition set content = ? where " +
					"kaleoDefinitionId = ?")) {

			preparedStatement.setString(1, content);
			preparedStatement.setLong(2, kaleoDefinitionId);

			preparedStatement.executeUpdate();
		}
	}

}