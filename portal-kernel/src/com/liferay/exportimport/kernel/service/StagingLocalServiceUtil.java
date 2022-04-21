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

package com.liferay.exportimport.kernel.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the local service utility for Staging. This utility wraps
 * <code>com.liferay.portlet.exportimport.service.impl.StagingLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see StagingLocalService
 * @generated
 */
public class StagingLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.exportimport.service.impl.StagingLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void checkDefaultLayoutSetBranches(
			long userId, com.liferay.portal.kernel.model.Group liveGroup,
			boolean branchingPublic, boolean branchingPrivate, boolean remote,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().checkDefaultLayoutSetBranches(
			userId, liveGroup, branchingPublic, branchingPrivate, remote,
			serviceContext);
	}

	public static void cleanUpStagingRequest(long stagingRequestId)
		throws PortalException {

		getService().cleanUpStagingRequest(stagingRequestId);
	}

	public static long createStagingRequest(
			long userId, long groupId, String checksum)
		throws PortalException {

		return getService().createStagingRequest(userId, groupId, checksum);
	}

	public static void disableStaging(
			com.liferay.portal.kernel.model.Group liveGroup,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().disableStaging(liveGroup, serviceContext);
	}

	public static void disableStaging(
			javax.portlet.PortletRequest portletRequest,
			com.liferay.portal.kernel.model.Group liveGroup,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().disableStaging(portletRequest, liveGroup, serviceContext);
	}

	public static void enableLocalStaging(
			long userId, com.liferay.portal.kernel.model.Group liveGroup,
			boolean branchingPublic, boolean branchingPrivate,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().enableLocalStaging(
			userId, liveGroup, branchingPublic, branchingPrivate,
			serviceContext);
	}

	public static void enableRemoteStaging(
			long userId, com.liferay.portal.kernel.model.Group stagingGroup,
			boolean branchingPublic, boolean branchingPrivate,
			String remoteAddress, int remotePort, String remotePathContext,
			boolean secureConnection, long remoteGroupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().enableRemoteStaging(
			userId, stagingGroup, branchingPublic, branchingPrivate,
			remoteAddress, remotePort, remotePathContext, secureConnection,
			remoteGroupId, serviceContext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences
			publishStagingRequest(
				long userId, long stagingRequestId,
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration)
		throws PortalException {

		return getService().publishStagingRequest(
			userId, stagingRequestId, exportImportConfiguration);
	}

	public static void updateStagingRequest(
			long userId, long stagingRequestId, String fileName, byte[] bytes)
		throws PortalException {

		getService().updateStagingRequest(
			userId, stagingRequestId, fileName, bytes);
	}

	public static StagingLocalService getService() {
		return _service;
	}

	private static volatile StagingLocalService _service;

}