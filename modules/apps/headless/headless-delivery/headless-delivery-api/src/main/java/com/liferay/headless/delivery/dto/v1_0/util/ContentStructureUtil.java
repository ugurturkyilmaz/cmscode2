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

package com.liferay.headless.delivery.dto.v1_0.util;

import com.liferay.dynamic.data.mapping.form.field.type.constants.DDMFormFieldTypeConstants;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.headless.delivery.dto.v1_0.ContentStructure;
import com.liferay.headless.delivery.dto.v1_0.ContentStructureField;
import com.liferay.headless.delivery.dto.v1_0.Option;
import com.liferay.journal.article.dynamic.data.mapping.form.field.type.constants.JournalArticleDDMFormFieldTypeConstants;
import com.liferay.layout.dynamic.data.mapping.form.field.type.constants.LayoutDDMFormFieldTypeConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.vulcan.util.GroupUtil;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Cristina González
 */
public class ContentStructureUtil {

	public static ContentStructure toContentStructure(
		boolean acceptAllLanguages, GroupLocalService groupLocalService,
		Locale locale, Portal portal, UserLocalService userLocalService,
		DDMStructure ddmStructure) {

		if (ddmStructure == null) {
			return null;
		}

		Group group = groupLocalService.fetchGroup(ddmStructure.getGroupId());

		return new ContentStructure() {
			{
				assetLibraryKey = GroupUtil.getAssetLibraryKey(group);
				availableLanguages = LocaleUtil.toW3cLanguageIds(
					ddmStructure.getAvailableLanguageIds());
				contentStructureFields = TransformUtil.transformToArray(
					ddmStructure.getRootFieldNames(),
					fieldName -> _toContentStructureField(
						acceptAllLanguages,
						ddmStructure.getDDMFormField(fieldName), locale),
					ContentStructureField.class);
				creator = CreatorUtil.toCreator(
					portal, Optional.empty(),
					userLocalService.fetchUser(ddmStructure.getUserId()));
				dateCreated = ddmStructure.getCreateDate();
				dateModified = ddmStructure.getModifiedDate();
				description = ddmStructure.getDescription(locale);
				description_i18n = LocalizedMapUtil.getI18nMap(
					acceptAllLanguages, ddmStructure.getDescriptionMap());
				id = ddmStructure.getStructureId();
				name = ddmStructure.getName(locale);
				name_i18n = LocalizedMapUtil.getI18nMap(
					acceptAllLanguages, ddmStructure.getDescriptionMap());
				siteId = GroupUtil.getSiteId(group);
			}
		};
	}

	public static String toDataType(DDMFormField ddmFormField) {
		String type = ddmFormField.getType();

		if (DDMFormFieldType.DOCUMENT_LIBRARY.equals(type) ||
			Objects.equals(DDMFormFieldTypeConstants.DOCUMENT_LIBRARY, type)) {

			return "document";
		}
		else if (DDMFormFieldType.JOURNAL_ARTICLE.equals(type) ||
				 Objects.equals(
					 JournalArticleDDMFormFieldTypeConstants.JOURNAL_ARTICLE,
					 type)) {

			return "structuredContent";
		}
		else if (DDMFormFieldType.LINK_TO_PAGE.equals(type) ||
				 Objects.equals(
					 LayoutDDMFormFieldTypeConstants.LINK_TO_LAYOUT, type)) {

			return "url";
		}
		else if (DDMFormFieldType.RADIO.equals(type)) {
			return "string";
		}

		return ddmFormField.getDataType();
	}

	public static String toInputControl(DDMFormField ddmFormField) {
		String type = ddmFormField.getType();

		if (DDMFormFieldType.CHECKBOX.equals(type) ||
			DDMFormFieldType.RADIO.equals(type) ||
			DDMFormFieldType.SELECT.equals(type) ||
			DDMFormFieldType.TEXT.equals(type) ||
			DDMFormFieldType.TEXT_AREA.equals(type)) {

			return type;
		}

		return null;
	}

	private static ContentStructureField _toContentStructureField(
		boolean acceptAllLanguage, DDMFormField ddmFormField, Locale locale) {

		LocalizedValue labelLocalizedValue = ddmFormField.getLabel();

		LocalizedValue predefinedLocalizedValue =
			ddmFormField.getPredefinedValue();

		return new ContentStructureField() {
			{
				dataType = toDataType(ddmFormField);
				inputControl = toInputControl(ddmFormField);
				label = _toString(labelLocalizedValue, locale);
				label_i18n = LocalizedMapUtil.getI18nMap(
					acceptAllLanguage, labelLocalizedValue.getValues());
				localizable = ddmFormField.isLocalizable();
				multiple = ddmFormField.isMultiple();
				name = ddmFormField.getFieldReference();
				nestedContentStructureFields = TransformUtil.transformToArray(
					ddmFormField.getNestedDDMFormFields(),
					ddmFormField -> _toContentStructureField(
						acceptAllLanguage, ddmFormField, locale),
					ContentStructureField.class);
				predefinedValue = _toString(predefinedLocalizedValue, locale);
				predefinedValue_i18n = LocalizedMapUtil.getI18nMap(
					acceptAllLanguage, predefinedLocalizedValue.getValues());
				repeatable = ddmFormField.isRepeatable();
				required = ddmFormField.isRequired();
				showLabel = ddmFormField.isShowLabel();

				setOptions(
					() -> Optional.ofNullable(
						ddmFormField.getDDMFormFieldOptions()
					).map(
						DDMFormFieldOptions::getOptions
					).map(
						Map::entrySet
					).map(
						Set::stream
					).orElseGet(
						Stream::empty
					).map(
						entry -> new Option() {
							{
								LocalizedValue localizedValue =
									entry.getValue();

								setLabel(_toString(localizedValue, locale));
								setLabel_i18n(
									LocalizedMapUtil.getI18nMap(
										acceptAllLanguage,
										localizedValue.getValues()));

								setValue(entry.getKey());
							}
						}
					).toArray(
						Option[]::new
					));
			}
		};
	}

	private static String _toString(
		LocalizedValue localizedValue, Locale locale) {

		if (localizedValue == null) {
			return null;
		}

		return localizedValue.getString(locale);
	}

}