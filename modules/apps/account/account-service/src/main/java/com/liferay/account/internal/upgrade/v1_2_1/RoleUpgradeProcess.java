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

package com.liferay.account.internal.upgrade.v1_2_1;

import com.liferay.account.role.AccountRole;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Drew Brokke
 */
public class RoleUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		int newType = RoleConstants.TYPE_ACCOUNT;
		int oldType = RoleConstants.TYPE_PROVIDER;

		String className = StringUtil.quote(
			AccountRole.class.getName(), StringPool.APOSTROPHE);

		runSQL(
			StringBundler.concat(
				"update Role_ set type_ = ", newType,
				" where classNameId = (select ClassName_.classNameId from ",
				"ClassName_ where ClassName_.value = ", className,
				") and type_ = ", oldType));
	}

}