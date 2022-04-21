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

package com.liferay.segments.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.StagedModelRepositoryHelper;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.service.SegmentsEntryLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.segments.model.SegmentsEntry",
	service = StagedModelRepository.class
)
public class SegmentsEntryStagedModelRepository
	implements StagedModelRepository<SegmentsEntry> {

	@Override
	public SegmentsEntry addStagedModel(
			PortletDataContext portletDataContext, SegmentsEntry segmentsEntry)
		throws PortalException {

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			segmentsEntry);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(segmentsEntry.getUuid());
		}

		return _segmentsEntryLocalService.addSegmentsEntry(
			segmentsEntry.getSegmentsEntryKey(), segmentsEntry.getNameMap(),
			segmentsEntry.getDescriptionMap(), segmentsEntry.isActive(),
			segmentsEntry.getCriteria(), segmentsEntry.getSource(),
			segmentsEntry.getType(), serviceContext);
	}

	@Override
	public void deleteStagedModel(SegmentsEntry segmentsEntry)
		throws PortalException {

		_segmentsEntryLocalService.deleteSegmentsEntry(segmentsEntry);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		SegmentsEntry segmentsEntry = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (segmentsEntry != null) {
			deleteStagedModel(segmentsEntry);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {
	}

	@Override
	public SegmentsEntry fetchMissingReference(String uuid, long groupId) {
		return (SegmentsEntry)
			_stagedModelRepositoryHelper.fetchMissingReference(
				uuid, groupId, this);
	}

	@Override
	public SegmentsEntry fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _segmentsEntryLocalService.fetchSegmentsEntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<SegmentsEntry> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _segmentsEntryLocalService.getSegmentsEntriesByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return _segmentsEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public SegmentsEntry getStagedModel(long id) throws PortalException {
		return _segmentsEntryLocalService.getSegmentsEntry(id);
	}

	@Override
	public SegmentsEntry saveStagedModel(SegmentsEntry segmentsEntry)
		throws PortalException {

		return _segmentsEntryLocalService.updateSegmentsEntry(segmentsEntry);
	}

	@Override
	public SegmentsEntry updateStagedModel(
			PortletDataContext portletDataContext, SegmentsEntry segmentsEntry)
		throws PortalException {

		return _segmentsEntryLocalService.updateSegmentsEntry(segmentsEntry);
	}

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

	@Reference
	private StagedModelRepositoryHelper _stagedModelRepositoryHelper;

}