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

package com.liferay.data.engine.rest.resource.v2_0.test.util;

import com.liferay.data.engine.rest.client.dto.v2_0.DataDefinition;
import com.liferay.dynamic.data.lists.constants.DDLRecordSetConstants;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author Marcela Cunha
 */
public class DataRecordCollectionTestUtil {

	public static DDLRecordSet addRecordSet(
			DataDefinition dataDefinition, Group group,
			ResourceLocalService resourceLocalService)
		throws Exception {

		return addRecordSet(
			dataDefinition.getId(), dataDefinition.getDataDefinitionKey(),
			group, resourceLocalService,
			DDLRecordSetConstants.SCOPE_DATA_ENGINE);
	}

	public static DDLRecordSet addRecordSet(
			DDMStructure ddmStructure, Group group,
			ResourceLocalService resourceLocalService)
		throws Exception {

		return addRecordSet(
			ddmStructure.getStructureId(), ddmStructure.getStructureKey(),
			group, resourceLocalService,
			DDLRecordSetConstants.SCOPE_DATA_ENGINE);
	}

	public static DDLRecordSet addRecordSet(
			long dataDefinitionId, String dataDefinitionKey, Group group,
			ResourceLocalService resourceLocalService, int scope)
		throws Exception {

		Map<Locale, String> nameMap = HashMapBuilder.put(
			LocaleUtil.US, RandomTestUtil.randomString()
		).build();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		DDLRecordSet ddlRecordSet = DDLRecordSetLocalServiceUtil.addRecordSet(
			TestPropsValues.getUserId(), group.getGroupId(), dataDefinitionId,
			dataDefinitionKey, nameMap, null,
			DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT, scope,
			serviceContext);

		resourceLocalService.addModelResources(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			TestPropsValues.getUserId(), _getResourceName(ddlRecordSet),
			ddlRecordSet.getRecordSetId(),
			serviceContext.getModelPermissions());

		return ddlRecordSet;
	}

	private static String _getResourceName(DDLRecordSet ddlRecordSet)
		throws Exception {

		DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();

		return ResourceActionsUtil.getCompositeModelName(
			PortalUtil.getClassName(ddmStructure.getClassNameId()),
			DDLRecordSet.class.getName());
	}

}