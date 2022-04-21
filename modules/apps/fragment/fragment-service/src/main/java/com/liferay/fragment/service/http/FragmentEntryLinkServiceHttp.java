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

package com.liferay.fragment.service.http;

import com.liferay.fragment.service.FragmentEntryLinkServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>FragmentEntryLinkServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FragmentEntryLinkServiceHttp {

	public static com.liferay.fragment.model.FragmentEntryLink
			addFragmentEntryLink(
				HttpPrincipal httpPrincipal, long groupId,
				long originalFragmentEntryLinkId, long fragmentEntryId,
				long segmentsExperienceId, long plid, String css, String html,
				String js, String configuration, String editableValues,
				String namespace, int position, String rendererKey,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FragmentEntryLinkServiceUtil.class, "addFragmentEntryLink",
				_addFragmentEntryLinkParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, originalFragmentEntryLinkId,
				fragmentEntryId, segmentsExperienceId, plid, css, html, js,
				configuration, editableValues, namespace, position, rendererKey,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fragment.model.FragmentEntryLink)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fragment.model.FragmentEntryLink
			deleteFragmentEntryLink(
				HttpPrincipal httpPrincipal, long fragmentEntryLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FragmentEntryLinkServiceUtil.class, "deleteFragmentEntryLink",
				_deleteFragmentEntryLinkParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fragmentEntryLinkId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fragment.model.FragmentEntryLink)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fragment.model.FragmentEntryLink
			updateFragmentEntryLink(
				HttpPrincipal httpPrincipal, long fragmentEntryLinkId,
				String editableValues)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FragmentEntryLinkServiceUtil.class, "updateFragmentEntryLink",
				_updateFragmentEntryLinkParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fragmentEntryLinkId, editableValues);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fragment.model.FragmentEntryLink)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fragment.model.FragmentEntryLink
			updateFragmentEntryLink(
				HttpPrincipal httpPrincipal, long fragmentEntryLinkId,
				String editableValues, boolean updateClassedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FragmentEntryLinkServiceUtil.class, "updateFragmentEntryLink",
				_updateFragmentEntryLinkParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fragmentEntryLinkId, editableValues,
				updateClassedModel);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fragment.model.FragmentEntryLink)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		FragmentEntryLinkServiceHttp.class);

	private static final Class<?>[] _addFragmentEntryLinkParameterTypes0 =
		new Class[] {
			long.class, long.class, long.class, long.class, long.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, int.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteFragmentEntryLinkParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _updateFragmentEntryLinkParameterTypes2 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _updateFragmentEntryLinkParameterTypes3 =
		new Class[] {long.class, String.class, boolean.class};

}