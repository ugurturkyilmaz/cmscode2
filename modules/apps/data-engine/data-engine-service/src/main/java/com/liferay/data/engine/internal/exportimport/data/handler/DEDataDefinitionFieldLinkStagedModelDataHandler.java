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

package com.liferay.data.engine.internal.exportimport.data.handler;

import com.liferay.data.engine.model.DEDataDefinitionFieldLink;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.xml.Element;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class DEDataDefinitionFieldLinkStagedModelDataHandler
	extends BaseStagedModelDataHandler<DEDataDefinitionFieldLink> {

	public static final String[] CLASS_NAMES = {
		DEDataDefinitionFieldLink.class.getName()
	};

	@Override
	public void deleteStagedModel(
			DEDataDefinitionFieldLink deDataDefinitionFieldLink)
		throws PortalException {

		_stagedModelRepository.deleteStagedModel(deDataDefinitionFieldLink);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		_stagedModelRepository.deleteStagedModel(
			uuid, groupId, className, extraData);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(
		DEDataDefinitionFieldLink deDataDefinitionFieldLink) {

		return deDataDefinitionFieldLink.getFieldName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			DEDataDefinitionFieldLink deDataDefinitionFieldLink)
		throws Exception {

		Element deDataDefinitionFieldLinkElement =
			portletDataContext.getExportDataElement(deDataDefinitionFieldLink);

		deDataDefinitionFieldLinkElement.addAttribute(
			"link-class-name", deDataDefinitionFieldLink.getClassName());

		portletDataContext.addClassedModel(
			deDataDefinitionFieldLinkElement,
			ExportImportPathUtil.getModelPath(deDataDefinitionFieldLink),
			deDataDefinitionFieldLink);
	}

	@Override
	protected void doImportMissingReference(
			PortletDataContext portletDataContext, String uuid, long groupId,
			long deDataDefinitionFieldLinkId)
		throws Exception {

		DEDataDefinitionFieldLink existingDEDataDefinitionFieldLink =
			fetchMissingReference(uuid, groupId);

		if (existingDEDataDefinitionFieldLink == null) {
			return;
		}

		Map<Long, Long> deDataDefinitionFieldLinkIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DEDataDefinitionFieldLink.class);

		deDataDefinitionFieldLinkIds.put(
			deDataDefinitionFieldLinkId,
			existingDEDataDefinitionFieldLink.getDeDataDefinitionFieldLinkId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			DEDataDefinitionFieldLink deDataDefinitionFieldLink)
		throws Exception {

		Map<Long, Long> ddmStructureIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDMStructure.class);

		long ddmStructureId = MapUtil.getLong(
			ddmStructureIds, deDataDefinitionFieldLink.getDdmStructureId(),
			deDataDefinitionFieldLink.getDdmStructureId());

		DEDataDefinitionFieldLink importedDEDataDefinitionFieldLink =
			(DEDataDefinitionFieldLink)deDataDefinitionFieldLink.clone();

		Element deDataDefinitionFieldLinkElement =
			portletDataContext.getImportDataStagedModelElement(
				deDataDefinitionFieldLink);

		importedDEDataDefinitionFieldLink.setGroupId(
			portletDataContext.getScopeGroupId());
		importedDEDataDefinitionFieldLink.setCompanyId(
			portletDataContext.getCompanyId());
		importedDEDataDefinitionFieldLink.setClassNameId(
			_portal.getClassNameId(
				deDataDefinitionFieldLinkElement.attributeValue(
					"link-class-name")));
		importedDEDataDefinitionFieldLink.setDdmStructureId(ddmStructureId);

		DDMStructure ddmStructure = _ddmStructureLocalService.getDDMStructure(
			ddmStructureId);

		DDMStructureVersion structureVersion =
			ddmStructure.getStructureVersion();

		DDMStructureLayout structureLayout =
			_ddmStructureLayoutLocalService.
				getStructureLayoutByStructureVersionId(
					structureVersion.getStructureVersionId());

		importedDEDataDefinitionFieldLink.setClassPK(
			structureLayout.getStructureLayoutId());

		DEDataDefinitionFieldLink existingDEDataDefinitionFieldLink =
			_stagedModelRepository.fetchStagedModelByUuidAndGroupId(
				deDataDefinitionFieldLink.getUuid(),
				portletDataContext.getScopeGroupId());

		if ((existingDEDataDefinitionFieldLink == null) ||
			!portletDataContext.isDataStrategyMirror()) {

			importedDEDataDefinitionFieldLink =
				_stagedModelRepository.addStagedModel(
					portletDataContext, importedDEDataDefinitionFieldLink);
		}
		else {
			importedDEDataDefinitionFieldLink.setMvccVersion(
				existingDEDataDefinitionFieldLink.getMvccVersion());
			importedDEDataDefinitionFieldLink.setDeDataDefinitionFieldLinkId(
				existingDEDataDefinitionFieldLink.
					getDeDataDefinitionFieldLinkId());

			importedDEDataDefinitionFieldLink =
				_stagedModelRepository.updateStagedModel(
					portletDataContext, importedDEDataDefinitionFieldLink);
		}

		portletDataContext.importClassedModel(
			deDataDefinitionFieldLink, importedDEDataDefinitionFieldLink);
	}

	@Override
	protected StagedModelRepository<DEDataDefinitionFieldLink>
		getStagedModelRepository() {

		return _stagedModelRepository;
	}

	@Reference
	private DDMStructureLayoutLocalService _ddmStructureLayoutLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(model.class.name=com.liferay.data.engine.model.DEDataDefinitionFieldLink)",
		unbind = "-"
	)
	private StagedModelRepository<DEDataDefinitionFieldLink>
		_stagedModelRepository;

}