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

package com.liferay.asset.list.service.impl;

import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.list.constants.AssetListEntryUsageConstants;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntryUsage;
import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.asset.list.service.base.AssetListEntryUsageLocalServiceBaseImpl;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Portal;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = "model.class.name=com.liferay.asset.list.model.AssetListEntryUsage",
	service = AopService.class
)
public class AssetListEntryUsageLocalServiceImpl
	extends AssetListEntryUsageLocalServiceBaseImpl {

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #addAssetListEntryUsage(long, long, long, long, long, String,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public AssetListEntryUsage addAssetListEntryUsage(
			long userId, long groupId, long assetListEntryId, long classNameId,
			long classPK, String portletId, ServiceContext serviceContext)
		throws PortalException {

		return addAssetListEntryUsage(
			userId, groupId, _portal.getClassNameId(AssetListEntry.class),
			portletId, _portal.getClassNameId(Portlet.class),
			String.valueOf(assetListEntryId), classPK, serviceContext);
	}

	@Override
	public AssetListEntryUsage addAssetListEntryUsage(
			long userId, long groupId, long classNameId, String containerKey,
			long containerType, String key, long plid,
			ServiceContext serviceContext)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		long assetListEntryUsageId = counterLocalService.increment();

		AssetListEntryUsage assetListEntryUsage =
			assetListEntryUsagePersistence.create(assetListEntryUsageId);

		assetListEntryUsage.setUuid(serviceContext.getUuid());
		assetListEntryUsage.setGroupId(groupId);
		assetListEntryUsage.setCompanyId(user.getCompanyId());
		assetListEntryUsage.setUserId(userId);
		assetListEntryUsage.setUserName(user.getFullName());
		assetListEntryUsage.setCreateDate(
			serviceContext.getCreateDate(new Date()));
		assetListEntryUsage.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		assetListEntryUsage.setClassNameId(classNameId);
		assetListEntryUsage.setContainerKey(containerKey);
		assetListEntryUsage.setContainerType(containerType);
		assetListEntryUsage.setKey(key);
		assetListEntryUsage.setPlid(plid);
		assetListEntryUsage.setType(_getType(plid));

		return assetListEntryUsagePersistence.update(assetListEntryUsage);
	}

	@Override
	public void deleteAssetListEntryUsages(
		String containerKey, long containerType, long plid) {

		assetListEntryUsagePersistence.removeByCK_CT_P(
			containerKey, containerType, plid);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getAssetListEntryUsages(String, long, long)}
	 */
	@Deprecated
	@Override
	public AssetListEntryUsage fetchAssetListEntryUsage(
		long classNameId, long classPK, String portletId) {

		List<AssetListEntryUsage> assetListEntryUsages =
			getAssetListEntryUsages(
				portletId, _portal.getClassNameId(Portlet.class), classPK);

		if (!assetListEntryUsages.isEmpty()) {
			return assetListEntryUsages.get(0);
		}

		return null;
	}

	@Override
	public AssetListEntryUsage fetchAssetListEntryUsage(
		long groupId, long classNameId, String containerKey, long containerType,
		String key, long plid) {

		return assetListEntryUsagePersistence.fetchByG_C_CK_CT_K_P(
			groupId, classNameId, containerKey, containerType, key, plid);
	}

	@Override
	public List<AssetListEntryUsage> getAssetEntryListUsagesByPlid(long plid) {
		return assetListEntryUsagePersistence.findByPlid(plid);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getAssetListEntryUsages(long, long, String)}
	 */
	@Deprecated
	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId) {

		return getAssetListEntryUsages(
			_getGroupId(assetListEntryId),
			_portal.getClassNameId(AssetListEntry.class),
			String.valueOf(assetListEntryId));
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getAssetListEntryUsages(long, long, String, int, int,
	 *             OrderByComparator)}
	 */
	@Deprecated
	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getAssetListEntryUsages(
			_getGroupId(assetListEntryId),
			_portal.getClassNameId(AssetListEntry.class),
			String.valueOf(assetListEntryId), start, end, orderByComparator);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getAssetListEntryUsages(long, long, String, int)}
	 */
	@Deprecated
	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, long classNameId) {

		return getAssetListEntryUsages(
			_getGroupId(assetListEntryId),
			_portal.getClassNameId(AssetListEntry.class),
			String.valueOf(assetListEntryId), _toType(classNameId));
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getAssetListEntryUsages(long, long, String, int, int, int,
	 *             OrderByComparator)}
	 */
	@Deprecated
	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, long classNameId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return getAssetListEntryUsages(
			_getGroupId(assetListEntryId),
			_portal.getClassNameId(AssetListEntry.class),
			String.valueOf(assetListEntryId), _toType(classNameId), start, end,
			orderByComparator);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long groupId, long classNameId, String key) {

		return assetListEntryUsagePersistence.findByG_C_K(
			groupId, classNameId, key);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long groupId, long classNameId, String key, int type) {

		return assetListEntryUsagePersistence.findByG_C_K_T(
			groupId, classNameId, key, type);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long groupId, long classNameId, String key, int type, int start,
		int end, OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return assetListEntryUsagePersistence.findByG_C_K_T(
			groupId, classNameId, key, type, start, end, orderByComparator);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long groupId, long classNameId, String key, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return assetListEntryUsagePersistence.findByG_C_K(
			groupId, classNameId, key, start, end, orderByComparator);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		String containerKey, long containerType, long plid) {

		return assetListEntryUsagePersistence.findByCK_CT_P(
			containerKey, containerType, plid);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getAssetListEntryUsagesCount(long, long, String)}
	 */
	@Deprecated
	@Override
	public int getAssetListEntryUsagesCount(long assetListEntryId) {
		return getAssetListEntryUsagesCount(
			_getGroupId(assetListEntryId),
			_portal.getClassNameId(AssetListEntry.class),
			String.valueOf(assetListEntryId));
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getAssetListEntryUsagesCount(long, long, String, int)}
	 */
	@Deprecated
	@Override
	public int getAssetListEntryUsagesCount(
		long assetListEntryId, long classNameId) {

		return getAssetListEntryUsagesCount(
			_getGroupId(assetListEntryId),
			_portal.getClassNameId(AssetListEntry.class),
			String.valueOf(assetListEntryId), _toType(classNameId));
	}

	@Override
	public int getAssetListEntryUsagesCount(
		long groupId, long classNameId, String key) {

		return assetListEntryUsagePersistence.countByG_C_K(
			groupId, classNameId, key);
	}

	@Override
	public int getAssetListEntryUsagesCount(
		long groupId, long classNameId, String key, int type) {

		return assetListEntryUsagePersistence.countByG_C_K_T(
			groupId, classNameId, key, type);
	}

	private long _getGroupId(long assetListEntryId) {
		AssetListEntry assetListEntry =
			_assetListEntryLocalService.fetchAssetListEntry(assetListEntryId);

		if (assetListEntry != null) {
			return assetListEntry.getGroupId();
		}

		return 0;
	}

	private int _getType(long plid) {
		if (plid <= 0) {
			return AssetListEntryUsageConstants.TYPE_DEFAULT;
		}

		Layout layout = _layoutLocalService.fetchLayout(plid);

		if (layout == null) {
			return AssetListEntryUsageConstants.TYPE_DEFAULT;
		}

		if (layout.isDraftLayout()) {
			plid = layout.getClassPK();
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.
				fetchLayoutPageTemplateEntryByPlid(plid);

		if (layoutPageTemplateEntry == null) {
			return AssetListEntryUsageConstants.TYPE_LAYOUT;
		}

		if (layoutPageTemplateEntry.getType() ==
				LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE) {

			return AssetListEntryUsageConstants.TYPE_DISPLAY_PAGE_TEMPLATE;
		}

		return AssetListEntryUsageConstants.TYPE_PAGE_TEMPLATE;
	}

	private int _toType(long classNameId) {
		if (Objects.equals(classNameId, _portal.getClassNameId(Layout.class))) {
			return AssetListEntryUsageConstants.TYPE_LAYOUT;
		}

		if (Objects.equals(
				classNameId,
				_portal.getClassNameId(LayoutPageTemplateEntry.class))) {

			return AssetListEntryUsageConstants.TYPE_PAGE_TEMPLATE;
		}

		if (Objects.equals(
				classNameId,
				_portal.getClassNameId(AssetDisplayPageEntry.class))) {

			return AssetListEntryUsageConstants.TYPE_DISPLAY_PAGE_TEMPLATE;
		}

		return AssetListEntryUsageConstants.TYPE_DEFAULT;
	}

	@Reference
	private AssetListEntryLocalService _assetListEntryLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}