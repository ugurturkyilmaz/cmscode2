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

package com.liferay.segments.service;

import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.segments.model.SegmentsEntry;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for SegmentsEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Eduardo Garcia
 * @see SegmentsEntryServiceUtil
 * @generated
 */
@AccessControlled
@CTAware
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface SegmentsEntryService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.segments.service.impl.SegmentsEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the segments entry remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link SegmentsEntryServiceUtil} if injection and service tracking are not available.
	 */
	public SegmentsEntry addSegmentsEntry(
			String segmentsEntryKey, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active, String criteria,
			String type, ServiceContext serviceContext)
		throws PortalException;

	public SegmentsEntry addSegmentsEntry(
			String segmentsEntryKey, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active, String criteria,
			String source, String type, ServiceContext serviceContext)
		throws PortalException;

	public void addSegmentsEntryClassPKs(
			long segmentsEntryId, long[] classPKs,
			ServiceContext serviceContext)
		throws PortalException;

	public SegmentsEntry deleteSegmentsEntry(long segmentsEntryId)
		throws PortalException;

	public void deleteSegmentsEntryClassPKs(
			long segmentsEntryId, long[] classPKs)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SegmentsEntry> getSegmentsEntries(
		long groupId, boolean includeAncestorSegmentsEntries);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SegmentsEntry> getSegmentsEntries(
		long groupId, boolean includeAncestorSegmentsEntries, int start,
		int end, OrderByComparator<SegmentsEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSegmentsEntriesCount(
		long groupId, boolean includeAncestorSegmentsEntries);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SegmentsEntry getSegmentsEntry(long segmentsEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<SegmentsEntry> searchSegmentsEntries(
			long companyId, long groupId, String keywords,
			boolean includeAncestorSegmentsEntries, int start, int end,
			Sort sort)
		throws PortalException;

	public SegmentsEntry updateSegmentsEntry(
			long segmentsEntryId, String segmentsEntryKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			boolean active, String criteria, ServiceContext serviceContext)
		throws PortalException;

}