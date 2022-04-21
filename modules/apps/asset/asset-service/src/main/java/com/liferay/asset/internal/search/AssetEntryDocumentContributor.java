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

package com.liferay.asset.internal.search;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.util.AssetRendererFactoryLookup;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentContributor;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.view.count.service.ViewCountEntryLocalService;

import java.text.ParseException;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = DocumentContributor.class)
public class AssetEntryDocumentContributor
	implements DocumentContributor<AssetEntry> {

	@Override
	public void contribute(Document document, BaseModel<AssetEntry> baseModel) {
		String className = document.get(Field.ENTRY_CLASS_NAME);

		AssetRendererFactory<?> assetRendererFactory =
			_assetRendererFactoryLookup.getAssetRendererFactoryByClassName(
				className);

		if ((assetRendererFactory == null) ||
			!assetRendererFactory.isSelectable()) {

			return;
		}

		AssetEntry assetEntry = null;

		Date displayDate = new Date();

		try {
			displayDate = document.getDate(Field.DISPLAY_DATE);
		}
		catch (ParseException parseException) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to parse data ", parseException);
			}
		}

		if (displayDate.getTime() > System.currentTimeMillis()) {
			String uuid = GetterUtil.getString(document.get(Field.UUID));

			long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

			assetEntry = _assetEntryLocalService.fetchEntry(groupId, uuid);
		}
		else {
			long classPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			assetEntry = _assetEntryLocalService.fetchEntry(className, classPK);
		}

		if (assetEntry == null) {
			return;
		}

		document.addNumber(Field.ASSET_ENTRY_ID, assetEntry.getEntryId());

		if (!document.hasField(Field.CREATE_DATE)) {
			document.addDate(Field.CREATE_DATE, assetEntry.getCreateDate());
		}

		if (assetEntry.getExpirationDate() != null) {
			document.addDate(
				Field.EXPIRATION_DATE, assetEntry.getExpirationDate());
		}
		else {
			document.addDate(Field.EXPIRATION_DATE, new Date(Long.MAX_VALUE));
		}

		if (!document.hasField(Field.MODIFIED_DATE)) {
			document.addDate(Field.MODIFIED_DATE, assetEntry.getModifiedDate());
		}

		document.addNumber(Field.PRIORITY, assetEntry.getPriority());

		if (assetEntry.getPublishDate() != null) {
			document.addDate(Field.PUBLISH_DATE, assetEntry.getPublishDate());
		}
		else {
			document.addDate(Field.PUBLISH_DATE, new Date(0));
		}

		document.addLocalizedKeyword(
			"localized_title",
			LocalizationUtil.populateLocalizationMap(
				assetEntry.getTitleMap(), assetEntry.getDefaultLanguageId(),
				assetEntry.getGroupId()),
			true, true);
		document.addNumber(
			"viewCount",
			_viewCountEntryLocalService.getViewCount(
				assetEntry.getCompanyId(),
				_classNameLocalService.getClassNameId(AssetEntry.class),
				assetEntry.getPrimaryKey()));
		document.addKeyword("visible", assetEntry.isVisible());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryDocumentContributor.class);

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetRendererFactoryLookup _assetRendererFactoryLookup;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ViewCountEntryLocalService _viewCountEntryLocalService;

}