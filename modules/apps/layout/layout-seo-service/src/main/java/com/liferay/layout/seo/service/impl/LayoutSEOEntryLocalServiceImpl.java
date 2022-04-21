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

package com.liferay.layout.seo.service.impl;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.layout.seo.exception.NoSuchEntryException;
import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.service.base.LayoutSEOEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = "model.class.name=com.liferay.layout.seo.model.LayoutSEOEntry",
	service = AopService.class
)
public class LayoutSEOEntryLocalServiceImpl
	extends LayoutSEOEntryLocalServiceBaseImpl {

	@Override
	public LayoutSEOEntry copyLayoutSEOEntry(
			long userId, long groupId, boolean privateLayout, long layoutId,
			boolean canonicalURLEnabled, Map<Locale, String> canonicalURLMap,
			long copyDDMStorageId, boolean openGraphDescriptionEnabled,
			Map<Locale, String> openGraphDescriptionMap,
			Map<Locale, String> openGraphImageAltMap,
			long openGraphImageFileEntryId, boolean openGraphTitleEnabled,
			Map<Locale, String> openGraphTitleMap,
			ServiceContext serviceContext)
		throws PortalException {

		LayoutSEOEntry layoutSEOEntry = layoutSEOEntryPersistence.fetchByG_P_L(
			groupId, privateLayout, layoutId);

		if (layoutSEOEntry == null) {
			return _addLayoutSEOEntry(
				userId, groupId, privateLayout, layoutId, canonicalURLEnabled,
				canonicalURLMap, copyDDMStorageId, openGraphDescriptionEnabled,
				openGraphDescriptionMap, openGraphImageAltMap,
				openGraphImageFileEntryId, openGraphTitleEnabled,
				openGraphTitleMap, serviceContext);
		}

		return updateLayoutSEOEntry(
			userId, groupId, privateLayout, layoutId, canonicalURLEnabled,
			canonicalURLMap, openGraphDescriptionEnabled,
			openGraphDescriptionMap, openGraphImageAltMap,
			openGraphImageFileEntryId, openGraphTitleEnabled, openGraphTitleMap,
			serviceContext);
	}

	@Override
	public void deleteLayoutSEOEntry(
			long groupId, boolean privateLayout, long layoutId)
		throws NoSuchEntryException {

		layoutSEOEntryPersistence.removeByG_P_L(
			groupId, privateLayout, layoutId);
	}

	@Override
	public void deleteLayoutSEOEntry(String uuid, long groupId)
		throws NoSuchEntryException {

		layoutSEOEntryPersistence.removeByUUID_G(uuid, groupId);
	}

	@Override
	public LayoutSEOEntry fetchLayoutSEOEntry(
		long groupId, boolean privateLayout, long layoutId) {

		return layoutSEOEntryPersistence.fetchByG_P_L(
			groupId, privateLayout, layoutId);
	}

	@Override
	public List<LayoutSEOEntry> getLayoutSEOEntriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return layoutSEOEntryPersistence.findByUuid_C(uuid, companyId);
	}

	@Override
	public LayoutSEOEntry updateCustomMetaTags(
			long userId, long groupId, boolean privateLayout, long layoutId,
			ServiceContext serviceContext)
		throws PortalException {

		LayoutSEOEntry layoutSEOEntry = layoutSEOEntryPersistence.fetchByG_P_L(
			groupId, privateLayout, layoutId);

		if (layoutSEOEntry == null) {
			return _addLayoutSEOEntry(
				userId, groupId, privateLayout, layoutId, false,
				Collections.emptyMap(), 0, false, Collections.emptyMap(),
				Collections.emptyMap(), 0, false, Collections.emptyMap(),
				serviceContext);
		}

		layoutSEOEntry.setModifiedDate(DateUtil.newDate());

		DDMStructure ddmStructure = _getDDMStructure(
			_groupLocalService.getGroup(groupId));

		long ddmStorageId = _updateDDMStorage(
			layoutSEOEntry.getCompanyId(), layoutSEOEntry.getDDMStorageId(), 0,
			ddmStructure.getStructureId(), serviceContext);

		layoutSEOEntry.setDDMStorageId(ddmStorageId);

		return layoutSEOEntryPersistence.update(layoutSEOEntry);
	}

	@Override
	public LayoutSEOEntry updateLayoutSEOEntry(
			long userId, long groupId, boolean privateLayout, long layoutId,
			boolean canonicalURLEnabled, Map<Locale, String> canonicalURLMap,
			boolean openGraphDescriptionEnabled,
			Map<Locale, String> openGraphDescriptionMap,
			Map<Locale, String> openGraphImageAltMap,
			long openGraphImageFileEntryId, boolean openGraphTitleEnabled,
			Map<Locale, String> openGraphTitleMap,
			ServiceContext serviceContext)
		throws PortalException {

		LayoutSEOEntry layoutSEOEntry = layoutSEOEntryPersistence.fetchByG_P_L(
			groupId, privateLayout, layoutId);

		if (layoutSEOEntry == null) {
			return _addLayoutSEOEntry(
				userId, groupId, privateLayout, layoutId, canonicalURLEnabled,
				canonicalURLMap, 0, openGraphDescriptionEnabled,
				openGraphDescriptionMap, openGraphImageAltMap,
				openGraphImageFileEntryId, openGraphTitleEnabled,
				openGraphTitleMap, serviceContext);
		}

		layoutSEOEntry.setModifiedDate(DateUtil.newDate());
		layoutSEOEntry.setCanonicalURLEnabled(canonicalURLEnabled);
		layoutSEOEntry.setCanonicalURLMap(canonicalURLMap);

		layoutSEOEntry.setOpenGraphDescriptionEnabled(
			openGraphDescriptionEnabled);
		layoutSEOEntry.setOpenGraphDescriptionMap(openGraphDescriptionMap);

		if (openGraphImageFileEntryId != 0) {
			layoutSEOEntry.setOpenGraphImageAltMap(openGraphImageAltMap);
		}
		else {
			layoutSEOEntry.setOpenGraphImageAltMap(Collections.emptyMap());
		}

		layoutSEOEntry.setOpenGraphImageFileEntryId(openGraphImageFileEntryId);
		layoutSEOEntry.setOpenGraphTitleEnabled(openGraphTitleEnabled);
		layoutSEOEntry.setOpenGraphTitleMap(openGraphTitleMap);

		return layoutSEOEntryPersistence.update(layoutSEOEntry);
	}

	@Override
	public LayoutSEOEntry updateLayoutSEOEntry(
			long userId, long groupId, boolean privateLayout, long layoutId,
			boolean canonicalURLEnabled, Map<Locale, String> canonicalURLMap,
			ServiceContext serviceContext)
		throws PortalException {

		LayoutSEOEntry layoutSEOEntry = layoutSEOEntryPersistence.fetchByG_P_L(
			groupId, privateLayout, layoutId);

		if (layoutSEOEntry == null) {
			return _addLayoutSEOEntry(
				userId, groupId, privateLayout, layoutId, canonicalURLEnabled,
				canonicalURLMap, 0, false, Collections.emptyMap(),
				Collections.emptyMap(), 0, false, Collections.emptyMap(),
				serviceContext);
		}

		layoutSEOEntry.setModifiedDate(DateUtil.newDate());
		layoutSEOEntry.setCanonicalURLEnabled(canonicalURLEnabled);
		layoutSEOEntry.setCanonicalURLMap(canonicalURLMap);

		return layoutSEOEntryPersistence.update(layoutSEOEntry);
	}

	private LayoutSEOEntry _addLayoutSEOEntry(
			long userId, long groupId, boolean privateLayout, long layoutId,
			boolean canonicalURLEnabled, Map<Locale, String> canonicalURLMap,
			long copyDDMStorageId, boolean openGraphDescriptionEnabled,
			Map<Locale, String> openGraphDescriptionMap,
			Map<Locale, String> openGraphImageAltMap,
			long openGraphImageFileEntryId, boolean openGraphTitleEnabled,
			Map<Locale, String> openGraphTitleMap,
			ServiceContext serviceContext)
		throws PortalException {

		LayoutSEOEntry layoutSEOEntry = layoutSEOEntryPersistence.create(
			counterLocalService.increment());

		layoutSEOEntry.setUuid(serviceContext.getUuid());
		layoutSEOEntry.setGroupId(groupId);

		Group group = _groupLocalService.getGroup(groupId);

		layoutSEOEntry.setCompanyId(group.getCompanyId());

		layoutSEOEntry.setUserId(userId);

		Date date = DateUtil.newDate();

		layoutSEOEntry.setCreateDate(date);
		layoutSEOEntry.setModifiedDate(date);

		layoutSEOEntry.setPrivateLayout(privateLayout);
		layoutSEOEntry.setLayoutId(layoutId);
		layoutSEOEntry.setCanonicalURLEnabled(canonicalURLEnabled);
		layoutSEOEntry.setCanonicalURLMap(canonicalURLMap);

		DDMStructure ddmStructure = _getDDMStructure(
			_groupLocalService.getGroup(groupId));

		long ddmStorageId = _updateDDMStorage(
			layoutSEOEntry.getCompanyId(), layoutSEOEntry.getDDMStorageId(),
			copyDDMStorageId, ddmStructure.getStructureId(), serviceContext);

		layoutSEOEntry.setDDMStorageId(ddmStorageId);

		layoutSEOEntry.setOpenGraphDescriptionEnabled(
			openGraphDescriptionEnabled);
		layoutSEOEntry.setOpenGraphDescriptionMap(openGraphDescriptionMap);

		if (openGraphImageFileEntryId != 0) {
			layoutSEOEntry.setOpenGraphImageAltMap(openGraphImageAltMap);
		}

		layoutSEOEntry.setOpenGraphImageFileEntryId(openGraphImageFileEntryId);
		layoutSEOEntry.setOpenGraphTitleEnabled(openGraphTitleEnabled);
		layoutSEOEntry.setOpenGraphTitleMap(openGraphTitleMap);

		return layoutSEOEntryPersistence.update(layoutSEOEntry);
	}

	private DDMFormValues _getDDMFormValues(
			long copyDDMStorageId, long structureId,
			ServiceContext serviceContext)
		throws PortalException {

		DDMFormValues ddmFormValues = null;

		if (copyDDMStorageId != 0) {
			ddmFormValues = _storageEngine.getDDMFormValues(copyDDMStorageId);
		}
		else {
			ddmFormValues = _ddm.getDDMFormValues(
				structureId, String.valueOf(structureId), serviceContext);
		}

		Set<Locale> availableLocales = new HashSet<>();
		Set<DDMFormFieldValue> ddmFormFieldValues = new LinkedHashSet<>();

		for (DDMFormFieldValue ddmFormFieldValue :
				ddmFormValues.getDDMFormFieldValues()) {

			Value value = ddmFormFieldValue.getValue();

			if (value == null) {
				continue;
			}

			for (Locale locale : ddmFormValues.getAvailableLocales()) {
				if (!Validator.isBlank(value.getString(locale))) {
					availableLocales.add(locale);

					ddmFormFieldValues.add(ddmFormFieldValue);

					continue;
				}

				for (DDMFormFieldValue nestedDDMFormFieldValue :
						ddmFormFieldValue.getNestedDDMFormFieldValues()) {

					Value nestedDDMFormFieldValueValue =
						nestedDDMFormFieldValue.getValue();

					if (!Validator.isBlank(
							nestedDDMFormFieldValueValue.getString(locale))) {

						availableLocales.add(locale);

						ddmFormFieldValues.add(ddmFormFieldValue);

						break;
					}
				}
			}
		}

		ddmFormValues.setAvailableLocales(availableLocales);
		ddmFormValues.setDDMFormFieldValues(
			new ArrayList<>(ddmFormFieldValues));

		return ddmFormValues;
	}

	private DDMStructure _getDDMStructure(Group group) throws PortalException {
		Group companyGroup = _groupLocalService.getCompanyGroup(
			group.getCompanyId());

		return _ddmStructureLocalService.getStructure(
			companyGroup.getGroupId(),
			_classNameLocalService.getClassNameId(
				LayoutSEOEntry.class.getName()),
			"custom-meta-tags");
	}

	private long _updateDDMStorage(
			long companyId, long ddmStorageId, long copyDDMStorageId,
			long structureId, ServiceContext serviceContext)
		throws PortalException {

		DDMFormValues ddmFormValues = _getDDMFormValues(
			copyDDMStorageId, structureId, serviceContext);

		if (ListUtil.isEmpty(ddmFormValues.getDDMFormFieldValues())) {
			if (ddmStorageId != 0) {
				_storageEngine.deleteByClass(ddmStorageId);
			}

			return 0;
		}

		if (ddmStorageId == 0) {
			return _storageEngine.create(
				companyId, structureId, ddmFormValues, serviceContext);
		}

		_storageEngine.update(ddmStorageId, ddmFormValues, serviceContext);

		return ddmStorageId;
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDM _ddm;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private StorageEngine _storageEngine;

}