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

package com.liferay.object.service.impl;

import com.liferay.object.constants.ObjectActionKeys;
import com.liferay.object.constants.ObjectConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectFieldSetting;
import com.liferay.object.service.base.ObjectFieldServiceBaseImpl;
import com.liferay.object.service.persistence.ObjectDefinitionPersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	property = {
		"json.web.service.context.name=object",
		"json.web.service.context.path=ObjectField"
	},
	service = AopService.class
)
public class ObjectFieldServiceImpl extends ObjectFieldServiceBaseImpl {

	@Override
	public ObjectField addCustomObjectField(
			long listTypeDefinitionId, long objectDefinitionId,
			String businessType, String dbType, boolean indexed,
			boolean indexedAsKeyword, String indexedLanguageId,
			Map<Locale, String> labelMap, String name, boolean required,
			List<ObjectFieldSetting> objectFieldSettings)
		throws PortalException {

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		if (objectDefinition.isSystem()) {
			_portletResourcePermission.check(
				getPermissionChecker(), null,
				ObjectActionKeys.EXTEND_SYSTEM_OBJECT_DEFINITION);
		}
		else {
			_objectDefinitionModelResourcePermission.check(
				getPermissionChecker(),
				objectDefinition.getObjectDefinitionId(), ActionKeys.UPDATE);
		}

		return objectFieldLocalService.addCustomObjectField(
			getUserId(), listTypeDefinitionId, objectDefinitionId, businessType,
			dbType, indexed, indexedAsKeyword, indexedLanguageId, labelMap,
			name, required, objectFieldSettings);
	}

	@Override
	public ObjectField deleteObjectField(long objectFieldId) throws Exception {
		ObjectField objectField = objectFieldPersistence.findByPrimaryKey(
			objectFieldId);

		_objectDefinitionModelResourcePermission.check(
			getPermissionChecker(), objectField.getObjectDefinitionId(),
			ActionKeys.UPDATE);

		return objectFieldLocalService.deleteObjectField(objectFieldId);
	}

	@Override
	public ObjectField getObjectField(long objectFieldId)
		throws PortalException {

		ObjectField objectField = objectFieldPersistence.findByPrimaryKey(
			objectFieldId);

		_objectDefinitionModelResourcePermission.check(
			getPermissionChecker(), objectField.getObjectDefinitionId(),
			ActionKeys.VIEW);

		return objectFieldLocalService.getObjectField(objectFieldId);
	}

	@Override
	public ObjectField updateCustomObjectField(
			long objectFieldId, long listTypeDefinitionId, String businessType,
			String dbType, boolean indexed, boolean indexedAsKeyword,
			String indexedLanguageId, Map<Locale, String> labelMap, String name,
			boolean required, List<ObjectFieldSetting> objectFieldSettings)
		throws PortalException {

		ObjectField objectField = objectFieldPersistence.findByPrimaryKey(
			objectFieldId);

		_objectDefinitionModelResourcePermission.check(
			getPermissionChecker(), objectField.getObjectDefinitionId(),
			ActionKeys.UPDATE);

		return objectFieldLocalService.updateCustomObjectField(
			objectFieldId, listTypeDefinitionId, businessType, dbType, indexed,
			indexedAsKeyword, indexedLanguageId, labelMap, name, required,
			objectFieldSettings);
	}

	@Reference(
		target = "(model.class.name=com.liferay.object.model.ObjectDefinition)"
	)
	private ModelResourcePermission<ObjectDefinition>
		_objectDefinitionModelResourcePermission;

	@Reference
	private ObjectDefinitionPersistence _objectDefinitionPersistence;

	@Reference(target = "(resource.name=" + ObjectConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}