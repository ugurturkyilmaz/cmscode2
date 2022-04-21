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

package com.liferay.roles.admin.internal.exportimport.data.handler;

import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchResourceActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Permission;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.adapter.ModelAdapterUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionConversionFilter;
import com.liferay.portal.kernel.security.permission.PermissionConverterUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.site.model.adapter.StagedGroup;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Mendez Gonzalez
 * @author Michael C. Han
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class RoleStagedModelDataHandler
	extends BaseStagedModelDataHandler<Role> {

	public static final String[] CLASS_NAMES = {Role.class.getName()};

	@Override
	public void deleteStagedModel(Role role) throws PortalException {
		_roleLocalService.deleteRole(role);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Group group = _groupLocalService.getGroup(groupId);

		Role role = _roleLocalService.fetchRoleByUuidAndCompanyId(
			uuid, group.getCompanyId());

		if (role != null) {
			deleteStagedModel(role);
		}
	}

	@Override
	public List<Role> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return ListUtil.fromArray(
			_roleLocalService.fetchRoleByUuidAndCompanyId(uuid, companyId));
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Role role) {
		return role.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Role role)
		throws Exception {

		String permissionsPath = ExportImportPathUtil.getModelPath(
			role, "permissions.xml");

		List<Permission> permissions =
			PermissionConverterUtil.convertPermissions(
				role, _permissionConversionFilter);

		String xml = portletDataContext.toXML(permissions);

		portletDataContext.addZipEntry(permissionsPath, xml);

		Element roleElement = portletDataContext.getExportDataElement(role);

		for (Group group : _groupLocalService.getRoleGroups(role.getRoleId())) {
			portletDataContext.addReferenceElement(
				role, roleElement,
				ModelAdapterUtil.adapt(group, Group.class, StagedGroup.class),
				PortletDataContext.REFERENCE_TYPE_WEAK, true);
		}

		portletDataContext.addClassedModel(
			roleElement, ExportImportPathUtil.getModelPath(role), role);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Role role)
		throws Exception {

		long userId = portletDataContext.getUserId(role.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			role);

		Role existingRole = _roleLocalService.fetchRoleByUuidAndCompanyId(
			role.getUuid(), portletDataContext.getCompanyId());

		if (existingRole == null) {
			existingRole = _roleLocalService.fetchRole(
				portletDataContext.getCompanyId(), role.getName());
		}

		Role importedRole = null;

		if (existingRole == null) {
			serviceContext.setUuid(role.getUuid());

			importedRole = _roleLocalService.addRole(
				userId, null, 0, role.getName(), role.getTitleMap(),
				role.getDescriptionMap(), role.getType(), role.getSubtype(),
				serviceContext);
		}
		else {
			importedRole = _roleLocalService.updateRole(
				existingRole.getRoleId(), role.getName(), role.getTitleMap(),
				role.getDescriptionMap(), role.getSubtype(), serviceContext);

			_deleteRolePermissions(portletDataContext, importedRole);
		}

		String permissionsPath = ExportImportPathUtil.getModelPath(
			role, "permissions.xml");

		List<Permission> permissions =
			(List<Permission>)portletDataContext.getZipEntryAsObject(
				permissionsPath);

		for (Permission permission : permissions) {
			try {
				_importResourcePermissions(
					portletDataContext, importedRole, permission);
			}
			catch (NoSuchResourceActionException
						noSuchResourceActionException) {

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Skip importing individually scoped permissions",
						noSuchResourceActionException);
				}
			}
		}

		List<Element> groupElements = portletDataContext.getReferenceElements(
			role, StagedGroup.class);

		for (Element groupElement : groupElements) {
			String uuid = groupElement.attributeValue("uuid");
			long companyId = GetterUtil.getLong(
				groupElement.attributeValue("company-id"));

			Group group = _groupLocalService.fetchGroupByUuidAndCompanyId(
				uuid, companyId);

			if (group != null) {
				_groupLocalService.addRoleGroup(
					importedRole.getRoleId(), group);
			}
		}

		portletDataContext.importClassedModel(role, importedRole);
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourcePermissionLocalService(
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_resourcePermissionLocalService = resourcePermissionLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourcePermissionService(
		ResourcePermissionService resourcePermissionService) {

		_resourcePermissionService = resourcePermissionService;
	}

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	private void _deleteRolePermissions(
		PortletDataContext portletDataContext, Role importedRole) {

		List<ResourcePermission> resourcePermissions =
			_resourcePermissionLocalService.getRoleResourcePermissions(
				importedRole.getRoleId(),
				new int[] {
					ResourceConstants.SCOPE_COMPANY,
					ResourceConstants.SCOPE_GROUP_TEMPLATE
				},
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ResourcePermission resourcePermission : resourcePermissions) {
			_resourcePermissionLocalService.deleteResourcePermission(
				resourcePermission);
		}

		List<ResourcePermission> groupResourcePermissions =
			_resourcePermissionLocalService.getRoleResourcePermissions(
				importedRole.getRoleId(),
				new int[] {ResourceConstants.SCOPE_GROUP}, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ResourcePermission groupResourcePermission :
				groupResourcePermissions) {

			long groupId = GetterUtil.getLong(
				groupResourcePermission.getPrimKey());

			if ((groupId == portletDataContext.getCompanyGroupId()) ||
				(groupId == portletDataContext.getUserPersonalSiteGroupId())) {

				_resourcePermissionLocalService.deleteResourcePermission(
					groupResourcePermission);
			}
		}
	}

	private void _importResourcePermissions(
			PortletDataContext portletDataContext, Role importedRole,
			Permission permission)
		throws PortalException {

		int scope = permission.getScope();

		if (scope == ResourceConstants.SCOPE_COMPANY) {
			_resourcePermissionService.addResourcePermission(
				portletDataContext.getCompanyGroupId(),
				portletDataContext.getCompanyId(), permission.getName(), scope,
				String.valueOf(portletDataContext.getCompanyId()),
				importedRole.getRoleId(), permission.getActionId());
		}
		else if (scope == ResourceConstants.SCOPE_GROUP) {
			long groupId = portletDataContext.getCompanyGroupId();

			long primaryKey = groupId;

			long sourceGroupId = GetterUtil.getLong(permission.getPrimKey());

			if (sourceGroupId ==
					portletDataContext.getSourceUserPersonalSiteGroupId()) {

				PermissionChecker permissionChecker =
					PermissionThreadLocal.getPermissionChecker();

				User user = permissionChecker.getUser();

				groupId = user.getGroupId();

				primaryKey = portletDataContext.getUserPersonalSiteGroupId();
			}

			_resourcePermissionService.addResourcePermission(
				groupId, portletDataContext.getCompanyId(),
				permission.getName(), ResourceConstants.SCOPE_GROUP,
				String.valueOf(primaryKey), importedRole.getRoleId(),
				permission.getActionId());
		}
		else if (scope == ResourceConstants.SCOPE_GROUP_TEMPLATE) {
			_resourcePermissionService.addResourcePermission(
				GroupConstants.DEFAULT_PARENT_GROUP_ID,
				portletDataContext.getCompanyId(), permission.getName(),
				ResourceConstants.SCOPE_GROUP_TEMPLATE,
				String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
				importedRole.getRoleId(), permission.getActionId());
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug("Individually scoped permissions are not imported");
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RoleStagedModelDataHandler.class);

	private GroupLocalService _groupLocalService;
	private final PermissionConversionFilter _permissionConversionFilter =
		new ImportExportPermissionConversionFilter();
	private ResourcePermissionLocalService _resourcePermissionLocalService;
	private ResourcePermissionService _resourcePermissionService;
	private RoleLocalService _roleLocalService;

}