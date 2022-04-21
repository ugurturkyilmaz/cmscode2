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

package com.liferay.layout.internal.upgrade.v1_2_1;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author István András Dézsi
 */
public class LayoutAssetUpgradeProcess extends UpgradeProcess {

	public LayoutAssetUpgradeProcess(
		AssetCategoryLocalService assetCategoryLocalService,
		AssetEntryLocalService assetEntryLocalService,
		AssetTagLocalService assetTagLocalService,
		GroupLocalService groupLocalService,
		LayoutLocalService layoutLocalService) {

		_assetCategoryLocalService = assetCategoryLocalService;
		_assetEntryLocalService = assetEntryLocalService;
		_assetTagLocalService = assetTagLocalService;
		_groupLocalService = groupLocalService;
		_layoutLocalService = layoutLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			_layoutLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property typeProperty = PropertyFactoryUtil.forName("type");

				dynamicQuery.add(
					typeProperty.ne(LayoutConstants.TYPE_ASSET_DISPLAY));
				dynamicQuery.add(
					typeProperty.ne(LayoutConstants.TYPE_COLLECTION));
				dynamicQuery.add(typeProperty.ne(LayoutConstants.TYPE_CONTENT));
			});
		actionableDynamicQuery.setPerformActionMethod(
			(Layout layout) -> _updateAsset(layout));

		actionableDynamicQuery.performActions();
	}

	private void _updateAsset(Layout layout) throws PortalException {
		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			Layout.class.getName(), layout.getPlid());

		if (assetEntry != null) {
			return;
		}

		Group group = _groupLocalService.fetchGroup(layout.getGroupId());

		if (group == null) {
			return;
		}

		long[] assetCategoryIds = _assetCategoryLocalService.getCategoryIds(
			Layout.class.getName(), layout.getPlid());
		String[] assetTagNames = _assetTagLocalService.getTagNames(
			Layout.class.getName(), layout.getPlid());

		_layoutLocalService.updateAsset(
			layout.getUserId(), layout, assetCategoryIds, assetTagNames);
	}

	private final AssetCategoryLocalService _assetCategoryLocalService;
	private final AssetEntryLocalService _assetEntryLocalService;
	private final AssetTagLocalService _assetTagLocalService;
	private final GroupLocalService _groupLocalService;
	private final LayoutLocalService _layoutLocalService;

}