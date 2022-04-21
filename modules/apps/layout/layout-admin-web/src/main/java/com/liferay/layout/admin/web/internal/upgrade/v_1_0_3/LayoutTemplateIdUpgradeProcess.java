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

package com.liferay.layout.admin.web.internal.upgrade.v_1_0_3;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.db.DBTypeToSQLMap;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Sam Ziemer
 */
public class LayoutTemplateIdUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_updateLayoutTemplateId();
	}

	private void _updateLayoutTemplateId() throws Exception {
		DBTypeToSQLMap dbTypeToSQLMap = new DBTypeToSQLMap(
			StringBundler.concat(
				"update Layout set typeSettings = REPLACE(typeSettings, ",
				"'layout-template-id=1_2_1_columns\n', ",
				"'layout-template-id=1_2_1_columns_i\n')"));

		dbTypeToSQLMap.add(
			DBType.SYBASE,
			StringBundler.concat(
				"update Layout set typeSettings = ",
				"REPLACE(CAST_TEXT(typeSettings), ",
				"'layout-template-id=1_2_1_columns\n', ",
				"'layout-template-id=1_2_1_columns_i\n')"));

		runSQL(dbTypeToSQLMap);
	}

}