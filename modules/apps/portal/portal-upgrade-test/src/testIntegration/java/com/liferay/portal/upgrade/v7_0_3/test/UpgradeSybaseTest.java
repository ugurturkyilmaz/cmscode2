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

package com.liferay.portal.upgrade.v7_0_3.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.AssumeTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.test.BaseUpgradeDBColumnSizeTestCase;
import com.liferay.portal.upgrade.v7_0_3.UpgradeSybase;

import org.junit.Assume;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Preston Crary
 */
@RunWith(Arquillian.class)
public class UpgradeSybaseTest extends BaseUpgradeDBColumnSizeTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new AssumeTestRule("assume"), new LiferayIntegrationTestRule());

	public static void assume() {
		DB db = DBManagerUtil.getDB();

		Assume.assumeTrue(db.getDBType() == DBType.SYBASE);
	}

	@Override
	protected String getCreateTestTableSQL() {
		return "create table TestTable (testTableId decimal(20, 0) not null " +
			"primary key, testValue varchar(1000) null)";
	}

	@Override
	protected int getInitialSize() {
		return 1000;
	}

	@Override
	protected String getTypeName() {
		return "varchar";
	}

	@Override
	protected UpgradeSybase getUpgradeProcess() {
		return new UpgradeSybase();
	}

}