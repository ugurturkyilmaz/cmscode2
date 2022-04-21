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

package com.liferay.knowledge.base.service;

import com.liferay.knowledge.base.model.KBComment;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for KBComment. This utility wraps
 * <code>com.liferay.knowledge.base.service.impl.KBCommentServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentService
 * @generated
 */
public class KBCommentServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.knowledge.base.service.impl.KBCommentServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static KBComment deleteKBComment(KBComment kbComment)
		throws PortalException {

		return getService().deleteKBComment(kbComment);
	}

	public static KBComment deleteKBComment(long kbCommentId)
		throws PortalException {

		return getService().deleteKBComment(kbCommentId);
	}

	public static KBComment getKBComment(long kbCommentId)
		throws PortalException {

		return getService().getKBComment(kbCommentId);
	}

	public static List<KBComment> getKBComments(
			long groupId, int status, int start, int end)
		throws PortalException {

		return getService().getKBComments(groupId, status, start, end);
	}

	public static List<KBComment> getKBComments(
			long groupId, int status, int start, int end,
			OrderByComparator<KBComment> orderByComparator)
		throws PortalException {

		return getService().getKBComments(
			groupId, status, start, end, orderByComparator);
	}

	public static List<KBComment> getKBComments(
			long groupId, int start, int end,
			OrderByComparator<KBComment> orderByComparator)
		throws PortalException {

		return getService().getKBComments(
			groupId, start, end, orderByComparator);
	}

	public static List<KBComment> getKBComments(
			long groupId, String className, long classPK, int status, int start,
			int end)
		throws PortalException {

		return getService().getKBComments(
			groupId, className, classPK, status, start, end);
	}

	public static List<KBComment> getKBComments(
			long groupId, String className, long classPK, int status, int start,
			int end, OrderByComparator<KBComment> orderByComparator)
		throws PortalException {

		return getService().getKBComments(
			groupId, className, classPK, status, start, end, orderByComparator);
	}

	public static List<KBComment> getKBComments(
			long groupId, String className, long classPK, int start, int end,
			OrderByComparator<KBComment> orderByComparator)
		throws PortalException {

		return getService().getKBComments(
			groupId, className, classPK, start, end, orderByComparator);
	}

	public static int getKBCommentsCount(long groupId) throws PortalException {
		return getService().getKBCommentsCount(groupId);
	}

	public static int getKBCommentsCount(long groupId, int status)
		throws PortalException {

		return getService().getKBCommentsCount(groupId, status);
	}

	public static int getKBCommentsCount(
			long groupId, String className, long classPK)
		throws PortalException {

		return getService().getKBCommentsCount(groupId, className, classPK);
	}

	public static int getKBCommentsCount(
			long groupId, String className, long classPK, int status)
		throws PortalException {

		return getService().getKBCommentsCount(
			groupId, className, classPK, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static KBComment updateKBComment(
			long kbCommentId, long classNameId, long classPK, String content,
			int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateKBComment(
			kbCommentId, classNameId, classPK, content, status, serviceContext);
	}

	public static KBComment updateKBComment(
			long kbCommentId, long classNameId, long classPK, String content,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateKBComment(
			kbCommentId, classNameId, classPK, content, serviceContext);
	}

	public static KBComment updateStatus(
			long kbCommentId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateStatus(kbCommentId, status, serviceContext);
	}

	public static KBCommentService getService() {
		return _service;
	}

	private static volatile KBCommentService _service;

}