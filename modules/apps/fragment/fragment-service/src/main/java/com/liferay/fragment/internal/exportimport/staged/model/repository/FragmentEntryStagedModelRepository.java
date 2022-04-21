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

package com.liferay.fragment.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.StagedModelRepositoryHelper;
import com.liferay.fragment.exception.RequiredFragmentEntryException;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.fragment.model.FragmentEntry",
	service = StagedModelRepository.class
)
public class FragmentEntryStagedModelRepository
	implements StagedModelRepository<FragmentEntry> {

	@Override
	public FragmentEntry addStagedModel(
			PortletDataContext portletDataContext, FragmentEntry fragmentEntry)
		throws PortalException {

		long userId = portletDataContext.getUserId(fragmentEntry.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			fragmentEntry);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(fragmentEntry.getUuid());
		}

		return _fragmentEntryLocalService.addFragmentEntry(
			userId, fragmentEntry.getGroupId(),
			fragmentEntry.getFragmentCollectionId(),
			fragmentEntry.getFragmentEntryKey(), fragmentEntry.getName(),
			fragmentEntry.getCss(), fragmentEntry.getHtml(),
			fragmentEntry.getJs(), fragmentEntry.isCacheable(),
			fragmentEntry.getConfiguration(), fragmentEntry.getIcon(),
			fragmentEntry.getPreviewFileEntryId(), fragmentEntry.getType(),
			fragmentEntry.getStatus(), serviceContext);
	}

	@Override
	public void deleteStagedModel(FragmentEntry fragmentEntry)
		throws PortalException {

		_fragmentEntryLocalService.deleteFragmentEntry(fragmentEntry);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		FragmentEntry fragmentEntry = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (fragmentEntry != null) {
			_deleteFragmentEntryLinks(extraData, fragmentEntry, groupId);

			deleteStagedModel(fragmentEntry);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {
	}

	@Override
	public FragmentEntry fetchMissingReference(String uuid, long groupId) {
		return (FragmentEntry)
			_stagedModelRepositoryHelper.fetchMissingReference(
				uuid, groupId, this);
	}

	@Override
	public FragmentEntry fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _fragmentEntryLocalService.fetchFragmentEntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<FragmentEntry> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _fragmentEntryLocalService.getFragmentEntriesByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<FragmentEntry>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return _fragmentEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public FragmentEntry getStagedModel(long id) throws PortalException {
		return _fragmentEntryLocalService.getFragmentEntry(id);
	}

	@Override
	public FragmentEntry saveStagedModel(FragmentEntry fragmentEntry)
		throws PortalException {

		return _fragmentEntryLocalService.updateFragmentEntry(fragmentEntry);
	}

	@Override
	public FragmentEntry updateStagedModel(
			PortletDataContext portletDataContext, FragmentEntry fragmentEntry)
		throws PortalException {

		return _fragmentEntryLocalService.updateFragmentEntry(
			portletDataContext.getUserId(fragmentEntry.getUserUuid()),
			fragmentEntry.getFragmentEntryId(),
			fragmentEntry.getFragmentCollectionId(), fragmentEntry.getName(),
			fragmentEntry.getCss(), fragmentEntry.getHtml(),
			fragmentEntry.getJs(), fragmentEntry.isCacheable(),
			fragmentEntry.getConfiguration(), fragmentEntry.getIcon(),
			fragmentEntry.getPreviewFileEntryId(), fragmentEntry.getStatus());
	}

	private void _deleteFragmentEntryLinks(
			String extraData, FragmentEntry fragmentEntry, long groupId)
		throws PortalException {

		if (extraData.isEmpty()) {
			return;
		}

		List<FragmentEntryLink> fragmentEntryLinks =
			_fragmentEntryLinkLocalService.
				getFragmentEntryLinksByFragmentEntryId(
					fragmentEntry.getFragmentEntryId());

		if (ListUtil.isEmpty(fragmentEntryLinks)) {
			return;
		}

		long[] plids = new long[0];

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
			extraData);

		boolean privateLayout = GetterUtil.getBoolean(
			extraDataJSONObject.get("privateLayout"));

		for (String layoutUUID :
				JSONUtil.toStringArray(
					(JSONArray)extraDataJSONObject.get("layoutUUIDs"))) {

			Layout layout = _layoutService.getLayoutByUuidAndGroupId(
				layoutUUID, groupId, privateLayout);

			plids = ArrayUtil.append(plids, layout.getPlid());

			Layout draftLayout = layout.fetchDraftLayout();

			if (draftLayout != null) {
				plids = ArrayUtil.append(plids, draftLayout.getPlid());
			}
		}

		long[] fragmentEntryLinkIds = new long[fragmentEntryLinks.size()];

		for (int i = 0; i < fragmentEntryLinks.size(); i++) {
			FragmentEntryLink fragmentEntryLink = fragmentEntryLinks.get(i);

			if (ArrayUtil.contains(plids, fragmentEntryLink.getPlid())) {
				fragmentEntryLinkIds[i] =
					fragmentEntryLink.getFragmentEntryLinkId();
			}
			else {
				throw new RequiredFragmentEntryException();
			}
		}

		_fragmentEntryLinkLocalService.deleteFragmentEntryLinks(
			fragmentEntryLinkIds);
	}

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@Reference
	private LayoutService _layoutService;

	@Reference
	private StagedModelRepositoryHelper _stagedModelRepositoryHelper;

	@Reference
	private Staging _staging;

}