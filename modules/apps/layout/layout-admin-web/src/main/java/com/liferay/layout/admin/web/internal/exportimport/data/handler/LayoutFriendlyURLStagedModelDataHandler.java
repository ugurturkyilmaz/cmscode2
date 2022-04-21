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

package com.liferay.layout.admin.web.internal.exportimport.data.handler;

import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class LayoutFriendlyURLStagedModelDataHandler
	extends BaseStagedModelDataHandler<LayoutFriendlyURL> {

	public static final String[] CLASS_NAMES = {
		LayoutFriendlyURL.class.getName()
	};

	@Override
	public void deleteStagedModel(LayoutFriendlyURL layoutFriendlyURL) {
		_layoutFriendlyURLLocalService.deleteLayoutFriendlyURL(
			layoutFriendlyURL);
	}

	@Override
	public void deleteStagedModel(
		String uuid, long groupId, String className, String extraData) {

		LayoutFriendlyURL layoutFriendlyURL = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		deleteStagedModel(layoutFriendlyURL);
	}

	@Override
	public LayoutFriendlyURL fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _layoutFriendlyURLLocalService.
			fetchLayoutFriendlyURLByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<LayoutFriendlyURL> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _layoutFriendlyURLLocalService.
			getLayoutFriendlyURLsByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<LayoutFriendlyURL>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			LayoutFriendlyURL layoutFriendlyURL)
		throws Exception {

		Element layoutFriendlyURLElement =
			portletDataContext.getExportDataElement(layoutFriendlyURL);

		portletDataContext.addClassedModel(
			layoutFriendlyURLElement,
			ExportImportPathUtil.getModelPath(layoutFriendlyURL),
			layoutFriendlyURL);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			LayoutFriendlyURL layoutFriendlyURL)
		throws Exception {

		long userId = portletDataContext.getUserId(
			layoutFriendlyURL.getUserUuid());

		Map<Long, Long> plids =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class);

		long plid = MapUtil.getLong(
			plids, layoutFriendlyURL.getPlid(), layoutFriendlyURL.getPlid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			layoutFriendlyURL);

		LayoutFriendlyURL importedLayoutFriendlyURL = null;

		LayoutFriendlyURL existingLayoutFriendlyURL =
			_fetchExistingLayoutFriendlyURL(
				portletDataContext, layoutFriendlyURL, plid);

		layoutFriendlyURL = _getUniqueLayoutFriendlyURL(
			portletDataContext, layoutFriendlyURL, existingLayoutFriendlyURL);

		if (existingLayoutFriendlyURL == null) {
			serviceContext.setUuid(layoutFriendlyURL.getUuid());

			importedLayoutFriendlyURL =
				_layoutFriendlyURLLocalService.addLayoutFriendlyURL(
					userId, portletDataContext.getCompanyId(),
					portletDataContext.getScopeGroupId(), plid,
					portletDataContext.isPrivateLayout(),
					layoutFriendlyURL.getFriendlyURL(),
					layoutFriendlyURL.getLanguageId(), serviceContext);
		}
		else {
			importedLayoutFriendlyURL =
				_layoutFriendlyURLLocalService.updateLayoutFriendlyURL(
					userId, portletDataContext.getCompanyId(),
					portletDataContext.getScopeGroupId(), plid,
					portletDataContext.isPrivateLayout(),
					layoutFriendlyURL.getFriendlyURL(),
					layoutFriendlyURL.getLanguageId(), serviceContext);
		}

		portletDataContext.importClassedModel(
			layoutFriendlyURL, importedLayoutFriendlyURL);
	}

	@Reference(unbind = "-")
	protected void setLayoutFriendlyURLLocalService(
		LayoutFriendlyURLLocalService layoutFriendlyURLLocalService) {

		_layoutFriendlyURLLocalService = layoutFriendlyURLLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	private LayoutFriendlyURL _fetchExistingLayoutFriendlyURL(
		PortletDataContext portletDataContext,
		LayoutFriendlyURL layoutFriendlyURL, long plid) {

		LayoutFriendlyURL existingLayoutFriendlyURL =
			fetchStagedModelByUuidAndGroupId(
				layoutFriendlyURL.getUuid(),
				portletDataContext.getScopeGroupId());

		if (existingLayoutFriendlyURL == null) {
			existingLayoutFriendlyURL =
				_layoutFriendlyURLLocalService.fetchLayoutFriendlyURL(
					plid, layoutFriendlyURL.getLanguageId(), false);
		}

		return existingLayoutFriendlyURL;
	}

	private LayoutFriendlyURL _getUniqueLayoutFriendlyURL(
			PortletDataContext portletDataContext,
			LayoutFriendlyURL layoutFriendlyURL,
			LayoutFriendlyURL existingLayoutFriendlyURL)
		throws Exception {

		String friendlyURL = layoutFriendlyURL.getFriendlyURL();

		boolean privateLayout = layoutFriendlyURL.isPrivateLayout();

		if (existingLayoutFriendlyURL != null) {
			privateLayout = existingLayoutFriendlyURL.isPrivateLayout();
		}

		for (int i = 1;; i++) {
			LayoutFriendlyURL duplicateLayoutFriendlyURL =
				_layoutFriendlyURLLocalService.fetchLayoutFriendlyURL(
					portletDataContext.getScopeGroupId(), privateLayout,
					layoutFriendlyURL.getFriendlyURL(),
					layoutFriendlyURL.getLanguageId());

			if ((duplicateLayoutFriendlyURL == null) ||
				((existingLayoutFriendlyURL != null) &&
				 (existingLayoutFriendlyURL.getLayoutFriendlyURLId() ==
					 duplicateLayoutFriendlyURL.getLayoutFriendlyURLId()))) {

				break;
			}

			layoutFriendlyURL.setFriendlyURL(friendlyURL + i);
		}

		return layoutFriendlyURL;
	}

	private LayoutFriendlyURLLocalService _layoutFriendlyURLLocalService;
	private LayoutLocalService _layoutLocalService;

}