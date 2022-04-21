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

package com.liferay.layout.prototype.internal.exportimport.data.handler;

import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniela Zapata Riesco
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class LayoutPrototypeStagedModelDataHandler
	extends BaseStagedModelDataHandler<LayoutPrototype> {

	public static final String[] CLASS_NAMES = {
		LayoutPrototype.class.getName()
	};

	@Override
	public void deleteStagedModel(LayoutPrototype layoutPrototype)
		throws PortalException {

		_layoutPrototypeLocalService.deleteLayoutPrototype(layoutPrototype);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Group group = _groupLocalService.getGroup(groupId);

		LayoutPrototype layoutPrototype =
			_layoutPrototypeLocalService.fetchLayoutPrototypeByUuidAndCompanyId(
				uuid, group.getCompanyId());

		if (layoutPrototype != null) {
			deleteStagedModel(layoutPrototype);
		}
	}

	@Override
	public List<LayoutPrototype> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return ListUtil.fromArray(
			_layoutPrototypeLocalService.fetchLayoutPrototypeByUuidAndCompanyId(
				uuid, companyId));
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(LayoutPrototype layoutPrototype) {
		return layoutPrototype.getNameCurrentValue();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			LayoutPrototype layoutPrototype)
		throws Exception {

		_exportLayouts(portletDataContext, layoutPrototype);

		Element layoutPrototypeElement =
			portletDataContext.getExportDataElement(layoutPrototype);

		long defaultUserId = _userLocalService.getDefaultUserId(
			layoutPrototype.getCompanyId());

		if (defaultUserId == layoutPrototype.getUserId()) {
			layoutPrototypeElement.addAttribute("preloaded", "true");
		}

		portletDataContext.addClassedModel(
			layoutPrototypeElement,
			ExportImportPathUtil.getModelPath(layoutPrototype),
			layoutPrototype);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			LayoutPrototype layoutPrototype)
		throws Exception {

		long userId = portletDataContext.getUserId(
			layoutPrototype.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			layoutPrototype);

		serviceContext.setAttribute("addDefaultLayout", Boolean.FALSE);

		LayoutPrototype importedLayoutPrototype = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Element element =
				portletDataContext.getImportDataStagedModelElement(
					layoutPrototype);

			boolean preloaded = GetterUtil.getBoolean(
				element.attributeValue("preloaded"));

			LayoutPrototype existingLayoutPrototype =
				_fetchExistingLayoutPrototype(
					layoutPrototype.getUuid(),
					portletDataContext.getCompanyId(),
					layoutPrototype.getName(
						layoutPrototype.getDefaultLanguageId()),
					layoutPrototype.getDefaultLanguageId(), preloaded);

			if (existingLayoutPrototype == null) {
				serviceContext.setUuid(layoutPrototype.getUuid());

				importedLayoutPrototype =
					_layoutPrototypeLocalService.addLayoutPrototype(
						userId, portletDataContext.getCompanyId(),
						layoutPrototype.getNameMap(),
						layoutPrototype.getDescriptionMap(),
						layoutPrototype.isActive(), serviceContext);
			}
			else {
				importedLayoutPrototype =
					_layoutPrototypeLocalService.updateLayoutPrototype(
						existingLayoutPrototype.getLayoutPrototypeId(),
						layoutPrototype.getNameMap(),
						layoutPrototype.getDescriptionMap(),
						layoutPrototype.isActive(), serviceContext);
			}
		}
		else {
			importedLayoutPrototype =
				_layoutPrototypeLocalService.addLayoutPrototype(
					userId, portletDataContext.getCompanyId(),
					layoutPrototype.getNameMap(),
					layoutPrototype.getDescriptionMap(),
					layoutPrototype.isActive(), serviceContext);
		}

		_importLayouts(
			portletDataContext, layoutPrototype,
			importedLayoutPrototype.getGroupId());

		portletDataContext.importClassedModel(
			layoutPrototype, importedLayoutPrototype);
	}

	@Override
	protected boolean isSkipImportReferenceStagedModels() {
		return true;
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutPrototypeLocalService(
		LayoutPrototypeLocalService layoutPrototypeLocalService) {

		_layoutPrototypeLocalService = layoutPrototypeLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private void _exportLayouts(
			PortletDataContext portletDataContext,
			LayoutPrototype layoutPrototype)
		throws Exception {

		long groupId = portletDataContext.getGroupId();
		String portletId = portletDataContext.getPortletId();
		boolean privateLayout = portletDataContext.isPrivateLayout();
		long scopeGroupId = portletDataContext.getScopeGroupId();

		List<Layout> layouts = _layoutLocalService.getLayouts(
			layoutPrototype.getGroupId(), true,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		try {
			portletDataContext.setGroupId(layoutPrototype.getGroupId());
			portletDataContext.setPrivateLayout(true);
			portletDataContext.setScopeGroupId(layoutPrototype.getGroupId());

			for (Layout layout : layouts) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, layoutPrototype, layout,
					PortletDataContext.REFERENCE_TYPE_EMBEDDED);
			}
		}
		finally {
			portletDataContext.setGroupId(groupId);
			portletDataContext.setPortletId(portletId);
			portletDataContext.setPrivateLayout(privateLayout);
			portletDataContext.setScopeGroupId(scopeGroupId);
		}
	}

	private LayoutPrototype _fetchExistingLayoutPrototype(
		String uuid, long companyId, String name, String languageId,
		boolean preloaded) {

		if (preloaded) {
			return _layoutPrototypeLocalService.fetchLayoutPrototype(
				companyId, name, LocaleUtil.fromLanguageId(languageId));
		}

		return _layoutPrototypeLocalService.
			fetchLayoutPrototypeByUuidAndCompanyId(uuid, companyId);
	}

	private void _importLayouts(
			PortletDataContext portletDataContext,
			LayoutPrototype layoutPrototype, long importedGroupId)
		throws Exception {

		long groupId = portletDataContext.getGroupId();
		boolean privateLayout = portletDataContext.isPrivateLayout();
		long scopeGroupId = portletDataContext.getScopeGroupId();

		Map<String, String[]> parameterMap =
			portletDataContext.getParameterMap();

		String layoutsImportMode = MapUtil.getString(
			parameterMap, PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE);

		try {
			portletDataContext.setGroupId(importedGroupId);
			portletDataContext.setPrivateLayout(true);
			portletDataContext.setScopeGroupId(importedGroupId);

			if (!portletDataContext.isDataStrategyMirror()) {
				parameterMap.put(
					PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE,
					new String[] {
						PortletDataHandlerKeys.
							LAYOUTS_IMPORT_MODE_ADD_AS_NEW_PROTOTYPE
					});
			}

			StagedModelDataHandlerUtil.importReferenceStagedModels(
				portletDataContext, layoutPrototype, Layout.class);
		}
		finally {
			portletDataContext.setGroupId(groupId);
			portletDataContext.setPrivateLayout(privateLayout);
			portletDataContext.setScopeGroupId(scopeGroupId);

			if (Validator.isNull(layoutsImportMode)) {
				if (parameterMap.containsKey(
						PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE)) {

					parameterMap.remove(
						PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE);
				}
			}
			else {
				parameterMap.put(
					PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE,
					new String[] {layoutsImportMode});
			}
		}
	}

	private GroupLocalService _groupLocalService;
	private LayoutLocalService _layoutLocalService;
	private LayoutPrototypeLocalService _layoutPrototypeLocalService;
	private UserLocalService _userLocalService;

}