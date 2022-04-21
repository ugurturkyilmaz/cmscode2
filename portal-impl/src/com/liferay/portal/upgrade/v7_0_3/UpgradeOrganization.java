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

package com.liferay.portal.upgrade.v7_0_3;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.util.PropsUtil;

import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class UpgradeOrganization extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateOrganizationsType();
	}

	protected List<String> getOrganizationTypes() {
		List<String> organizationsTypes = ListUtil.fromArray(
			PropsUtil.getArray("organizations.types"));

		if (ListUtil.isEmpty(organizationsTypes)) {
			organizationsTypes.add("organization");
		}

		return organizationsTypes;
	}

	protected void updateOrganizationsType() throws Exception {
		String organizationTypesString = ListUtil.toString(
			getOrganizationTypes(), StringPool.NULL, "', '");

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL(
				StringBundler.concat(
					"update Organization_ set type_ = 'organization' where ",
					"type_ not in ('", organizationTypesString, "')"));
		}
	}

}