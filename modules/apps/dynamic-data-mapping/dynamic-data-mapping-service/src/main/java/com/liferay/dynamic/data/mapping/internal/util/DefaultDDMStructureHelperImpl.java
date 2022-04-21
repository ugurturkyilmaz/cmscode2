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

package com.liferay.dynamic.data.mapping.internal.util;

import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.dynamic.data.mapping.constants.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.dynamic.data.mapping.util.DefaultDDMStructureHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = DefaultDDMStructureHelper.class)
public class DefaultDDMStructureHelperImpl
	implements DefaultDDMStructureHelper {

	@Override
	public void addDDMStructures(
			long userId, long groupId, long classNameId,
			ClassLoader classLoader, String fileName,
			ServiceContext serviceContext)
		throws Exception {

		Locale locale = _portal.getSiteDefaultLocale(groupId);

		List<Element> structureElements = _getDDMStructures(
			classLoader, fileName, locale);

		for (Element structureElement : structureElements) {
			boolean dynamicStructure = GetterUtil.getBoolean(
				structureElement.elementText("dynamic-structure"));

			if (dynamicStructure) {
				continue;
			}

			String name = structureElement.elementText("name");

			String ddmStructureKey = name;

			DDMStructure ddmStructure =
				_ddmStructureLocalService.fetchStructure(
					groupId, classNameId, ddmStructureKey);

			if ((ddmStructure != null) ||
				(name.equals(DLFileEntryTypeConstants.NAME_IG_IMAGE) &&
				 !UpgradeProcessUtil.isCreateIGImageDocumentType())) {

				continue;
			}

			String description = structureElement.elementText("description");

			Map<Locale, String> nameMap = new HashMap<>();
			Map<Locale, String> descriptionMap = new HashMap<>();

			for (Locale curLocale : _language.getAvailableLocales(groupId)) {
				ResourceBundle resourceBundle =
					ResourceBundleUtil.getModuleAndPortalResourceBundle(
						curLocale, getClass());

				nameMap.put(curLocale, _language.get(resourceBundle, name));
				descriptionMap.put(
					curLocale, _language.get(resourceBundle, description));
			}

			DDMForm ddmForm = getDDMForm(groupId, locale, structureElement);

			DDMFormLayout ddmFormLayout = _getDDMFormLayout(
				structureElement, ddmForm);

			serviceContext.setAttribute(
				"status", WorkflowConstants.STATUS_APPROVED);

			ddmStructure = _ddmStructureLocalService.addStructure(
				userId, groupId,
				DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, classNameId,
				ddmStructureKey, nameMap, descriptionMap, ddmForm,
				ddmFormLayout, StorageType.DEFAULT.toString(),
				DDMStructureConstants.TYPE_DEFAULT, serviceContext);

			Element templateElement = structureElement.element("template");

			if (templateElement == null) {
				continue;
			}

			String templateFileName = templateElement.elementText("file-name");

			String script = StringUtil.read(
				classLoader,
				FileUtil.getPath(fileName) + StringPool.SLASH +
					templateFileName);

			boolean cacheable = GetterUtil.getBoolean(
				templateElement.elementText("cacheable"));

			_ddmTemplateLocalService.addTemplate(
				userId, groupId, _portal.getClassNameId(DDMStructure.class),
				ddmStructure.getStructureId(), ddmStructure.getClassNameId(),
				name, nameMap, null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE,
				TemplateConstants.LANG_TYPE_FTL, script, cacheable, false,
				StringPool.BLANK, null, serviceContext);
		}
	}

	@Override
	public String getDynamicDDMStructureDefinition(
			ClassLoader classLoader, String fileName,
			String dynamicDDMStructureName, Locale locale)
		throws Exception {

		List<Element> structureElements = _getDDMStructures(
			classLoader, fileName, locale);

		for (Element structureElement : structureElements) {
			boolean dynamicStructure = GetterUtil.getBoolean(
				structureElement.elementText("dynamic-structure"));

			if (!dynamicStructure) {
				continue;
			}

			String name = structureElement.elementText("name");

			if (!name.equals(dynamicDDMStructureName)) {
				continue;
			}

			Element structureElementRootElement = structureElement.element(
				"root");

			return structureElementRootElement.asXML();
		}

		return null;
	}

	protected DDMForm deserialize(
		String content, DDMFormDeserializer ddmFormDeserializer) {

		DDMFormDeserializerDeserializeRequest.Builder builder =
			DDMFormDeserializerDeserializeRequest.Builder.newBuilder(content);

		DDMFormDeserializerDeserializeResponse
			ddmFormDeserializerDeserializeResponse =
				ddmFormDeserializer.deserialize(builder.build());

		return ddmFormDeserializerDeserializeResponse.getDDMForm();
	}

	protected DDMForm getDDMForm(
		long groupId, Locale locale, Element structureElement) {

		Element structureElementDefinitionElement = structureElement.element(
			"definition");

		if (structureElementDefinitionElement != null) {
			return deserialize(
				structureElementDefinitionElement.getTextTrim(),
				_jsonDDMFormDeserializer);
		}

		Element structureElementRootElement = structureElement.element("root");

		String definition = structureElementRootElement.asXML();

		DDMForm ddmForm = deserialize(definition, _xsdDDMFormDeserializer);

		ddmForm = _ddm.updateDDMFormDefaultLocale(ddmForm, locale);

		return _getPopulateDDMForm(
			ddmForm, locale, _language.getAvailableLocales(groupId));
	}

	@Reference(unbind = "-")
	protected void setDDM(DDM ddm) {
		_ddm = ddm;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMTemplateLocalService(
		DDMTemplateLocalService ddmTemplateLocalService) {

		_ddmTemplateLocalService = ddmTemplateLocalService;
	}

	private DDMFormLayout _getDDMFormLayout(
		Element structureElement, DDMForm ddmForm) {

		Element structureElementLayoutElement = structureElement.element(
			"layout");

		if (structureElementLayoutElement != null) {
			DDMFormLayoutDeserializerDeserializeRequest.Builder builder =
				DDMFormLayoutDeserializerDeserializeRequest.Builder.newBuilder(
					structureElementLayoutElement.getTextTrim());

			DDMFormLayoutDeserializerDeserializeResponse
				ddmFormLayoutDeserializerDeserializeResponse =
					_jsonDDMFormLayoutDeserializer.deserialize(builder.build());

			return ddmFormLayoutDeserializerDeserializeResponse.
				getDDMFormLayout();
		}

		return _ddm.getDefaultDDMFormLayout(ddmForm);
	}

	private List<Element> _getDDMStructures(
			ClassLoader classLoader, String fileName, Locale locale)
		throws Exception {

		String xml = StringUtil.read(classLoader, fileName);

		xml = StringUtil.replace(xml, "[$LOCALE_DEFAULT$]", locale.toString());

		Document document = UnsecureSAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		return rootElement.elements("structure");
	}

	private DDMForm _getPopulateDDMForm(
		DDMForm ddmForm, Locale defaultLocale, Set<Locale> locales) {

		for (Locale locale : locales) {
			ddmForm.addAvailableLocale(locale);
		}

		ddmForm.setDDMFormFields(
			_getPopulateDDMFormFields(
				ddmForm.getDDMFormFields(), defaultLocale, locales));

		return ddmForm;
	}

	private List<DDMFormField> _getPopulateDDMFormFields(
		List<DDMFormField> ddmFormFields, Locale defaultLocale,
		Set<Locale> locales) {

		for (DDMFormField ddmFormField : ddmFormFields) {
			DDMFormFieldOptions ddmFormFieldOptions =
				ddmFormField.getDDMFormFieldOptions();

			Map<String, LocalizedValue> options =
				ddmFormFieldOptions.getOptions();

			for (Map.Entry<String, LocalizedValue> entry : options.entrySet()) {
				options.put(
					entry.getKey(),
					_getPopulateLocalizedValue(
						defaultLocale, locales, entry.getValue()));
			}

			ddmFormField.setDDMFormFieldOptions(ddmFormFieldOptions);
			ddmFormField.setLabel(
				_getPopulateLocalizedValue(
					defaultLocale, locales, ddmFormField.getLabel()));
			ddmFormField.setNestedDDMFormFields(
				_getPopulateDDMFormFields(
					ddmFormField.getNestedDDMFormFields(), defaultLocale,
					locales));
			ddmFormField.setTip(
				_getPopulateLocalizedValue(
					defaultLocale, locales, ddmFormField.getTip()));
		}

		return ddmFormFields;
	}

	private LocalizedValue _getPopulateLocalizedValue(
		Locale defaultLocale, Set<Locale> locales,
		LocalizedValue localizedValue) {

		String defaultValue = localizedValue.getString(defaultLocale);

		for (Locale locale : locales) {
			ResourceBundle resourceBundle =
				ResourceBundleUtil.getModuleAndPortalResourceBundle(
					locale, getClass());

			localizedValue.addString(
				locale, _language.get(resourceBundle, defaultValue));
		}

		return localizedValue;
	}

	private DDM _ddm;
	private DDMStructureLocalService _ddmStructureLocalService;
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference(target = "(ddm.form.deserializer.type=json)")
	private DDMFormDeserializer _jsonDDMFormDeserializer;

	@Reference(target = "(ddm.form.layout.deserializer.type=json)")
	private DDMFormLayoutDeserializer _jsonDDMFormLayoutDeserializer;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference(target = "(ddm.form.deserializer.type=xsd)")
	private DDMFormDeserializer _xsdDDMFormDeserializer;

}