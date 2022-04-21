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

package com.liferay.layout.page.template.service;

import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for LayoutPageTemplateEntry. This utility wraps
 * <code>com.liferay.layout.page.template.service.impl.LayoutPageTemplateEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateEntryService
 * @generated
 */
public class LayoutPageTemplateEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.layout.page.template.service.impl.LayoutPageTemplateEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static LayoutPageTemplateEntry addLayoutPageTemplateEntry(
			long groupId, long layoutPageTemplateCollectionId, long classNameId,
			long classTypeId, String name, long masterLayoutPlid, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addLayoutPageTemplateEntry(
			groupId, layoutPageTemplateCollectionId, classNameId, classTypeId,
			name, masterLayoutPlid, status, serviceContext);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntry(
			long groupId, long layoutPageTemplateCollectionId, String name,
			int type, long masterLayoutPlid, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addLayoutPageTemplateEntry(
			groupId, layoutPageTemplateCollectionId, name, type,
			masterLayoutPlid, status, serviceContext);
	}

	public static LayoutPageTemplateEntry copyLayoutPageTemplateEntry(
			long groupId, long layoutPageTemplateCollectionId,
			long layoutPageTemplateEntryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().copyLayoutPageTemplateEntry(
			groupId, layoutPageTemplateCollectionId, layoutPageTemplateEntryId,
			serviceContext);
	}

	public static LayoutPageTemplateEntry
			createLayoutPageTemplateEntryFromLayout(
				long segmentsExperienceId,
				com.liferay.portal.kernel.model.Layout sourceLayout,
				String name, long targetLayoutPageTemplateCollectionId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		return getService().createLayoutPageTemplateEntryFromLayout(
			segmentsExperienceId, sourceLayout, name,
			targetLayoutPageTemplateCollectionId, serviceContext);
	}

	public static void deleteLayoutPageTemplateEntries(
			long[] layoutPageTemplateEntryIds)
		throws PortalException {

		getService().deleteLayoutPageTemplateEntries(
			layoutPageTemplateEntryIds);
	}

	public static LayoutPageTemplateEntry deleteLayoutPageTemplateEntry(
			long layoutPageTemplateEntryId)
		throws PortalException {

		return getService().deleteLayoutPageTemplateEntry(
			layoutPageTemplateEntryId);
	}

	public static LayoutPageTemplateEntry fetchDefaultLayoutPageTemplateEntry(
		long groupId, int type, int status) {

		return getService().fetchDefaultLayoutPageTemplateEntry(
			groupId, type, status);
	}

	public static LayoutPageTemplateEntry fetchDefaultLayoutPageTemplateEntry(
		long groupId, long classNameId, long classTypeId) {

		return getService().fetchDefaultLayoutPageTemplateEntry(
			groupId, classNameId, classTypeId);
	}

	public static LayoutPageTemplateEntry fetchLayoutPageTemplateEntry(
			long layoutPageTemplateEntryId)
		throws PortalException {

		return getService().fetchLayoutPageTemplateEntry(
			layoutPageTemplateEntryId);
	}

	public static LayoutPageTemplateEntry
		fetchLayoutPageTemplateEntryByUuidAndGroupId(
			String uuid, long groupId) {

		return getService().fetchLayoutPageTemplateEntryByUuidAndGroupId(
			uuid, groupId);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, int type, int status, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, type, status, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, int type, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, type, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, int[] types, int status, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, types, status, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, int[] types, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, types, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long classNameId, int type, boolean defaultTemplate) {

		return getService().getLayoutPageTemplateEntries(
			groupId, classNameId, type, defaultTemplate);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long layoutPageTemplateCollectionId, int start, int end) {

		return getService().getLayoutPageTemplateEntries(
			groupId, layoutPageTemplateCollectionId, start, end);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long layoutPageTemplateCollectionId, int status,
		int start, int end) {

		return getService().getLayoutPageTemplateEntries(
			groupId, layoutPageTemplateCollectionId, status, start, end);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long layoutPageTemplateCollectionId, int status,
		int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, layoutPageTemplateCollectionId, status, start, end,
			orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long layoutPageTemplateCollectionId, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, layoutPageTemplateCollectionId, start, end,
			orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long classNameId, long classTypeId, int type) {

		return getService().getLayoutPageTemplateEntries(
			groupId, classNameId, classTypeId, type);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long classNameId, long classTypeId, int type,
		int status) {

		return getService().getLayoutPageTemplateEntries(
			groupId, classNameId, classTypeId, type, status);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long classNameId, long classTypeId, int type, int status,
		int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, classNameId, classTypeId, type, status, start, end,
			orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long classNameId, long classTypeId, int type, int start,
		int end, OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, classNameId, classTypeId, type, start, end,
			orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long classNameId, long classTypeId, String name, int type,
		int status, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, classNameId, classTypeId, name, type, status, start, end,
			orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long classNameId, long classTypeId, String name, int type,
		int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, classNameId, classTypeId, name, type, start, end,
			orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long layoutPageTemplateCollectionId, String name,
		int status, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, layoutPageTemplateCollectionId, name, status, start, end,
			orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, long layoutPageTemplateCollectionId, String name,
		int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, layoutPageTemplateCollectionId, name, start, end,
			orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, String name, int type, int status, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, name, type, status, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, String name, int type, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, name, type, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, String name, int[] types, int status, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, name, types, status, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		long groupId, String name, int[] types, int start, int end,
		OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntries(
			groupId, name, types, start, end, orderByComparator);
	}

	public static List<LayoutPageTemplateEntry>
		getLayoutPageTemplateEntriesByType(
			long groupId, long layoutPageTemplateCollectionId, int type,
			int start, int end,
			OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return getService().getLayoutPageTemplateEntriesByType(
			groupId, layoutPageTemplateCollectionId, type, start, end,
			orderByComparator);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, int type) {

		return getService().getLayoutPageTemplateEntriesCount(groupId, type);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, int type, int status) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, type, status);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, int[] types) {

		return getService().getLayoutPageTemplateEntriesCount(groupId, types);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, int[] types, int status) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, types, status);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, long layoutPageTemplateCollectionId) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, layoutPageTemplateCollectionId);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, long layoutPageTemplateCollectionId, int status) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, layoutPageTemplateCollectionId, status);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, long classNameId, long classTypeId, int type) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, classNameId, classTypeId, type);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, long classNameId, long classTypeId, int type,
		int status) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, classNameId, classTypeId, type, status);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, long classNameId, long classTypeId, String name,
		int type) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, classNameId, classTypeId, name, type);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, long classNameId, long classTypeId, String name, int type,
		int status) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, classNameId, classTypeId, name, type, status);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, long layoutPageTemplateCollectionId, String name) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, layoutPageTemplateCollectionId, name);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, long layoutPageTemplateCollectionId, String name,
		int status) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, layoutPageTemplateCollectionId, name, status);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, String name, int type) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, name, type);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, String name, int type, int status) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, name, type, status);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, String name, int[] types) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, name, types);
	}

	public static int getLayoutPageTemplateEntriesCount(
		long groupId, String name, int[] types, int status) {

		return getService().getLayoutPageTemplateEntriesCount(
			groupId, name, types, status);
	}

	public static int getLayoutPageTemplateEntriesCountByType(
		long groupId, long layoutPageTemplateCollectionId, int type) {

		return getService().getLayoutPageTemplateEntriesCountByType(
			groupId, layoutPageTemplateCollectionId, type);
	}

	public static LayoutPageTemplateEntry getLayoutPageTemplateEntry(
			long groupId, String layoutPageTemplateEntryKey)
		throws PortalException {

		return getService().getLayoutPageTemplateEntry(
			groupId, layoutPageTemplateEntryKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static LayoutPageTemplateEntry moveLayoutPageTemplateEntry(
			long layoutPageTemplateEntryId,
			long targetLayoutPageTemplateCollectionId)
		throws PortalException {

		return getService().moveLayoutPageTemplateEntry(
			layoutPageTemplateEntryId, targetLayoutPageTemplateCollectionId);
	}

	public static LayoutPageTemplateEntry updateLayoutPageTemplateEntry(
			long layoutPageTemplateEntryId, boolean defaultTemplate)
		throws PortalException {

		return getService().updateLayoutPageTemplateEntry(
			layoutPageTemplateEntryId, defaultTemplate);
	}

	public static LayoutPageTemplateEntry updateLayoutPageTemplateEntry(
			long layoutPageTemplateEntryId, long previewFileEntryId)
		throws PortalException {

		return getService().updateLayoutPageTemplateEntry(
			layoutPageTemplateEntryId, previewFileEntryId);
	}

	public static LayoutPageTemplateEntry updateLayoutPageTemplateEntry(
			long layoutPageTemplateEntryId, String name)
		throws PortalException {

		return getService().updateLayoutPageTemplateEntry(
			layoutPageTemplateEntryId, name);
	}

	public static LayoutPageTemplateEntry updateStatus(
			long layoutPageTemplateEntryId, int status)
		throws PortalException {

		return getService().updateStatus(layoutPageTemplateEntryId, status);
	}

	public static LayoutPageTemplateEntryService getService() {
		return _service;
	}

	private static volatile LayoutPageTemplateEntryService _service;

}