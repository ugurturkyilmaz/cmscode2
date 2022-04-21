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

package com.liferay.journal.web.internal.portlet.action;

import com.liferay.data.engine.field.type.util.LocalizedValueUtil;
import com.liferay.data.engine.rest.dto.v2_0.DataDefinition;
import com.liferay.data.engine.rest.dto.v2_0.DataDefinitionField;
import com.liferay.data.engine.rest.dto.v2_0.DataLayout;
import com.liferay.data.engine.rest.dto.v2_0.DataLayoutColumn;
import com.liferay.data.engine.rest.dto.v2_0.DataLayoutPage;
import com.liferay.data.engine.rest.dto.v2_0.DataLayoutRow;
import com.liferay.data.engine.rest.resource.v2_0.DataDefinitionResource;
import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.form.field.type.constants.DDMFormFieldTypeConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateService;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"mvc.command.name=/journal/copy_data_definition"
	},
	service = {AopService.class, MVCActionCommand.class}
)
public class CopyDataDefinitionMVCActionCommand
	extends BaseMVCActionCommand implements AopService, MVCActionCommand {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		return super.processAction(actionRequest, actionResponse);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ddmStructureId = ParamUtil.getLong(
			actionRequest, "ddmStructureId");

		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");

		DataDefinitionResource.Builder dataDefinitionResourcedBuilder =
			_dataDefinitionResourceFactory.create();

		DataDefinitionResource dataDefinitionResource =
			dataDefinitionResourcedBuilder.user(
				themeDisplay.getUser()
			).build();

		DataDefinition dataDefinition =
			dataDefinitionResource.getDataDefinition(ddmStructureId);

		_uniquifyDataDefinitionFields(dataDefinition);

		dataDefinition.setDataDefinitionKey(StringPool.BLANK);
		dataDefinition.setDescription(
			LocalizedValueUtil.toStringObjectMap(descriptionMap));
		dataDefinition.setName(LocalizedValueUtil.toStringObjectMap(nameMap));

		dataDefinition =
			dataDefinitionResource.postSiteDataDefinitionByContentType(
				themeDisplay.getScopeGroupId(), "journal", dataDefinition);

		boolean copyTemplates = ParamUtil.getBoolean(
			actionRequest, "copyTemplates");

		if (copyTemplates) {
			DDMStructure ddmStructure = _ddmStructureService.getStructure(
				themeDisplay.getScopeGroupId(),
				_portal.getClassNameId(JournalArticle.class),
				dataDefinition.getDataDefinitionKey());

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				DDMStructure.class.getName(), actionRequest);

			_ddmTemplateService.copyTemplates(
				_portal.getClassNameId(DDMStructure.class), ddmStructureId,
				_portal.getClassNameId(JournalArticle.class),
				ddmStructure.getStructureId(),
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, serviceContext);
		}
	}

	private void _uniquifyDataDefinitionFields(DataDefinition dataDefinition) {
		for (DataDefinitionField dataDefinitionField :
				dataDefinition.getDataDefinitionFields()) {

			_uniquifyDataDefinitionFields(dataDefinitionField);
		}

		_updateDataLayout(dataDefinition.getDefaultDataLayout());
	}

	private void _uniquifyDataDefinitionFields(
		DataDefinitionField dataDefinitionField) {

		dataDefinitionField.setName("CopyOf" + dataDefinitionField.getName());

		Map<String, Object> customProperties =
			dataDefinitionField.getCustomProperties();

		if (customProperties.containsKey("fieldReference")) {
			customProperties.put(
				"fieldReference",
				"CopyOf" + customProperties.get("fieldReference"));
		}

		if (!customProperties.containsKey("structureId") &&
			!Objects.equals(
				dataDefinitionField.getFieldType(),
				DDMFormFieldTypeConstants.FIELDSET)) {

			for (DataDefinitionField nestedDataDefinitionField :
					dataDefinitionField.getNestedDataDefinitionFields()) {

				_uniquifyDataDefinitionFields(nestedDataDefinitionField);
			}
		}
	}

	private void _updateDataLayout(DataLayout dataLayout) {
		for (DataLayoutPage dataLayoutPage : dataLayout.getDataLayoutPages()) {
			_updateDataLayoutRows(dataLayoutPage.getDataLayoutRows());
		}
	}

	private void _updateDataLayoutRows(DataLayoutRow[] dataLayoutRows) {
		for (DataLayoutRow dataLayoutRow : dataLayoutRows) {
			for (DataLayoutColumn dataLayoutColumn :
					dataLayoutRow.getDataLayoutColumns()) {

				String[] dataLayoutColumnFieldNames =
					dataLayoutColumn.getFieldNames();

				for (int i = 0; i < dataLayoutColumnFieldNames.length; i++) {
					dataLayoutColumnFieldNames[i] =
						"CopyOf" + dataLayoutColumnFieldNames[i];
				}
			}
		}
	}

	@Reference
	private DataDefinitionResource.Factory _dataDefinitionResourceFactory;

	@Reference
	private DDMStructureService _ddmStructureService;

	@Reference
	private DDMTemplateService _ddmTemplateService;

	@Reference
	private Portal _portal;

}