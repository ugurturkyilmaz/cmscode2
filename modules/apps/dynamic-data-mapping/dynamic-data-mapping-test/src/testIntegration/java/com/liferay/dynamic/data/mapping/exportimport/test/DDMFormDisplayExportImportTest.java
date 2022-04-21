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

package com.liferay.dynamic.data.mapping.exportimport.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormInstanceRecordTestUtil;
import com.liferay.exportimport.test.util.lar.BasePortletExportImportTestCase;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pedro Queiroz
 * @author Tamas Molnar
 */
@RunWith(Arquillian.class)
@Sync
public class DDMFormDisplayExportImportTest
	extends BasePortletExportImportTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Override
	public String getPortletId() {
		return DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM;
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		UserTestUtil.setUser(TestPropsValues.getUser());
	}

	@Test
	public void testExportImport() throws Exception {
		DDMFormInstanceRecord ddmFormInstanceRecord =
			DDMFormInstanceRecordTestUtil.
				addDDMFormInstanceRecordWithRandomValues(
					group, TestPropsValues.getUserId());

		DDMFormInstanceRecord importedDDMFormInstanceRecord =
			DDMFormInstanceRecordLocalServiceUtil.
				fetchDDMFormInstanceRecordByUuidAndGroupId(
					ddmFormInstanceRecord.getUuid(),
					importedGroup.getGroupId());

		Assert.assertNull(importedDDMFormInstanceRecord);

		DDMFormInstance ddmFormInstance =
			ddmFormInstanceRecord.getFormInstance();

		PortletPreferences importedPortletPreferences =
			getImportedPortletPreferences(
				HashMapBuilder.put(
					"formInstanceId",
					new String[] {
						String.valueOf(ddmFormInstance.getFormInstanceId())
					}
				).put(
					"groupId",
					new String[] {String.valueOf(ddmFormInstance.getGroupId())}
				).build(),
				false);

		DDMFormInstance importedDDMFormInstance =
			DDMFormInstanceLocalServiceUtil.
				fetchDDMFormInstanceByUuidAndGroupId(
					ddmFormInstance.getUuid(), importedGroup.getGroupId());

		Assert.assertNotNull(importedDDMFormInstance);

		Assert.assertEquals(
			String.valueOf(importedDDMFormInstance.getFormInstanceId()),
			importedPortletPreferences.getValue(
				"formInstanceId", StringPool.BLANK));
		Assert.assertEquals(
			String.valueOf(importedDDMFormInstance.getGroupId()),
			importedPortletPreferences.getValue("groupId", StringPool.BLANK));
	}

	@Override
	@Test
	public void testExportImportAssetLinks() throws Exception {
	}

}