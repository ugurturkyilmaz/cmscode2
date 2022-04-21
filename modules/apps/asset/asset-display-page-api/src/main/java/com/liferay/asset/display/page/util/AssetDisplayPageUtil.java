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

package com.liferay.asset.display.page.util;

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.info.display.contributor.LayoutDisplayPageProviderTrackerUtil;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.item.InfoItemReference;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProviderTracker;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Jürgen Kappler
 */
public class AssetDisplayPageUtil {

	public static LayoutPageTemplateEntry
		getAssetDisplayPageLayoutPageTemplateEntry(
			long groupId, long classNameId, long classPK, long classTypeId) {

		LayoutPageTemplateEntry defaultLayoutPageTemplateEntry =
			LayoutPageTemplateEntryServiceUtil.
				fetchDefaultLayoutPageTemplateEntry(
					groupId, classNameId, classTypeId);

		LayoutDisplayPageProviderTracker layoutDisplayPageProviderTracker =
			LayoutDisplayPageProviderTrackerUtil.
				getLayoutDisplayPageProviderTracker();

		LayoutDisplayPageProvider<?> layoutDisplayPageProvider =
			layoutDisplayPageProviderTracker.
				getLayoutDisplayPageProviderByClassName(
					PortalUtil.getClassName(classNameId));

		return _getAssetDisplayPage(
			groupId, classNameId, classPK, defaultLayoutPageTemplateEntry,
			layoutDisplayPageProvider);
	}

	public static boolean hasAssetDisplayPage(
		long groupId, AssetEntry assetEntry) {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			getAssetDisplayPageLayoutPageTemplateEntry(
				groupId, assetEntry.getClassNameId(), assetEntry.getClassPK(),
				assetEntry.getClassTypeId());

		if (layoutPageTemplateEntry != null) {
			return true;
		}

		return false;
	}

	public static boolean hasAssetDisplayPage(
		long groupId, long classNameId, long classPK, long classTypeId) {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			getAssetDisplayPageLayoutPageTemplateEntry(
				groupId, classNameId, classPK, classTypeId);

		if (layoutPageTemplateEntry != null) {
			return true;
		}

		return false;
	}

	private static LayoutPageTemplateEntry _getAssetDisplayPage(
		long groupId, long classNameId, long classPK,
		LayoutPageTemplateEntry defaultLayoutPageTemplateEntry,
		LayoutDisplayPageProvider<?> layoutDisplayPageProvider) {

		AssetDisplayPageEntry assetDisplayPageEntry =
			AssetDisplayPageEntryLocalServiceUtil.fetchAssetDisplayPageEntry(
				groupId, classNameId, classPK);

		if (assetDisplayPageEntry == null) {
			return defaultLayoutPageTemplateEntry;
		}

		if (layoutDisplayPageProvider.inheritable() &&
			(assetDisplayPageEntry.getType() ==
				AssetDisplayPageConstants.TYPE_INHERITED)) {

			InfoItemReference infoItemReference = new InfoItemReference(
				PortalUtil.getClassName(classNameId), classPK);

			LayoutDisplayPageObjectProvider<?>
				parentLayoutDisplayPageObjectProvider =
					layoutDisplayPageProvider.
						getParentLayoutDisplayPageObjectProvider(
							infoItemReference);

			if (parentLayoutDisplayPageObjectProvider != null) {
				return _getAssetDisplayPage(
					groupId, classNameId,
					parentLayoutDisplayPageObjectProvider.getClassPK(),
					defaultLayoutPageTemplateEntry, layoutDisplayPageProvider);
			}
		}

		if (assetDisplayPageEntry.getType() ==
				AssetDisplayPageConstants.TYPE_NONE) {

			return null;
		}

		if (assetDisplayPageEntry.getType() ==
				AssetDisplayPageConstants.TYPE_SPECIFIC) {

			return LayoutPageTemplateEntryLocalServiceUtil.
				fetchLayoutPageTemplateEntry(
					assetDisplayPageEntry.getLayoutPageTemplateEntryId());
		}

		return defaultLayoutPageTemplateEntry;
	}

}