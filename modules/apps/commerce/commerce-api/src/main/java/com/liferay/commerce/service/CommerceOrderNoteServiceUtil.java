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

package com.liferay.commerce.service;

import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceOrderNote. This utility wraps
 * <code>com.liferay.commerce.service.impl.CommerceOrderNoteServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderNoteService
 * @generated
 */
public class CommerceOrderNoteServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceOrderNoteServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceOrderNote addCommerceOrderNote(
			long commerceOrderId, String content, boolean restricted,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceOrderNote(
			commerceOrderId, content, restricted, serviceContext);
	}

	public static CommerceOrderNote addOrUpdateCommerceOrderNote(
			String externalReferenceCode, long commerceOrderNoteId,
			long commerceOrderId, String content, boolean restricted,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addOrUpdateCommerceOrderNote(
			externalReferenceCode, commerceOrderNoteId, commerceOrderId,
			content, restricted, serviceContext);
	}

	public static void deleteCommerceOrderNote(long commerceOrderNoteId)
		throws PortalException {

		getService().deleteCommerceOrderNote(commerceOrderNoteId);
	}

	public static CommerceOrderNote fetchByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		return getService().fetchByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	public static CommerceOrderNote fetchCommerceOrderNote(
			long commerceOrderNoteId)
		throws PortalException {

		return getService().fetchCommerceOrderNote(commerceOrderNoteId);
	}

	public static CommerceOrderNote getCommerceOrderNote(
			long commerceOrderNoteId)
		throws PortalException {

		return getService().getCommerceOrderNote(commerceOrderNoteId);
	}

	public static List<CommerceOrderNote> getCommerceOrderNotes(
			long commerceOrderId, boolean restricted)
		throws PortalException {

		return getService().getCommerceOrderNotes(commerceOrderId, restricted);
	}

	public static List<CommerceOrderNote> getCommerceOrderNotes(
			long commerceOrderId, int start, int end)
		throws PortalException {

		return getService().getCommerceOrderNotes(commerceOrderId, start, end);
	}

	public static int getCommerceOrderNotesCount(long commerceOrderId)
		throws PortalException {

		return getService().getCommerceOrderNotesCount(commerceOrderId);
	}

	public static int getCommerceOrderNotesCount(
			long commerceOrderId, boolean restricted)
		throws PortalException {

		return getService().getCommerceOrderNotesCount(
			commerceOrderId, restricted);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceOrderNote updateCommerceOrderNote(
			long commerceOrderNoteId, String content, boolean restricted)
		throws PortalException {

		return getService().updateCommerceOrderNote(
			commerceOrderNoteId, content, restricted);
	}

	public static CommerceOrderNoteService getService() {
		return _service;
	}

	private static volatile CommerceOrderNoteService _service;

}