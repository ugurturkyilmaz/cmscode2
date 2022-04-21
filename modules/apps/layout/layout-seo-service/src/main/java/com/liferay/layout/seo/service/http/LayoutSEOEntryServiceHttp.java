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

package com.liferay.layout.seo.service.http;

import com.liferay.layout.seo.service.LayoutSEOEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>LayoutSEOEntryServiceUtil</code> service
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
public class LayoutSEOEntryServiceHttp {

	public static com.liferay.layout.seo.model.LayoutSEOEntry
			copyLayoutSEOEntry(
				HttpPrincipal httpPrincipal, long userId, long groupId,
				boolean privateLayout, long layoutId,
				boolean canonicalURLEnabled,
				java.util.Map<java.util.Locale, String> canonicalURLMap,
				long copyDDMStorageId, boolean openGraphDescriptionEnabled,
				java.util.Map<java.util.Locale, String> openGraphDescriptionMap,
				java.util.Map<java.util.Locale, String> openGraphImageAltMap,
				long openGraphImageFileEntryId, boolean openGraphTitleEnabled,
				java.util.Map<java.util.Locale, String> openGraphTitleMap,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LayoutSEOEntryServiceUtil.class, "copyLayoutSEOEntry",
				_copyLayoutSEOEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, groupId, privateLayout, layoutId,
				canonicalURLEnabled, canonicalURLMap, copyDDMStorageId,
				openGraphDescriptionEnabled, openGraphDescriptionMap,
				openGraphImageAltMap, openGraphImageFileEntryId,
				openGraphTitleEnabled, openGraphTitleMap, serviceContext);

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

			return (com.liferay.layout.seo.model.LayoutSEOEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.layout.seo.model.LayoutSEOEntry
			updateCustomMetaTags(
				HttpPrincipal httpPrincipal, long groupId,
				boolean privateLayout, long layoutId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LayoutSEOEntryServiceUtil.class, "updateCustomMetaTags",
				_updateCustomMetaTagsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, privateLayout, layoutId, serviceContext);

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

			return (com.liferay.layout.seo.model.LayoutSEOEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.layout.seo.model.LayoutSEOEntry
			updateLayoutSEOEntry(
				HttpPrincipal httpPrincipal, long groupId,
				boolean privateLayout, long layoutId,
				boolean canonicalURLEnabled,
				java.util.Map<java.util.Locale, String> canonicalURLMap,
				boolean openGraphDescriptionEnabled,
				java.util.Map<java.util.Locale, String> openGraphDescriptionMap,
				java.util.Map<java.util.Locale, String> openGraphImageAltMap,
				long openGraphImageFileEntryId, boolean openGraphTitleEnabled,
				java.util.Map<java.util.Locale, String> openGraphTitleMap,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LayoutSEOEntryServiceUtil.class, "updateLayoutSEOEntry",
				_updateLayoutSEOEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, privateLayout, layoutId,
				canonicalURLEnabled, canonicalURLMap,
				openGraphDescriptionEnabled, openGraphDescriptionMap,
				openGraphImageAltMap, openGraphImageFileEntryId,
				openGraphTitleEnabled, openGraphTitleMap, serviceContext);

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

			return (com.liferay.layout.seo.model.LayoutSEOEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.layout.seo.model.LayoutSEOEntry
			updateLayoutSEOEntry(
				HttpPrincipal httpPrincipal, long groupId,
				boolean privateLayout, long layoutId,
				boolean enabledCanonicalURLMap,
				java.util.Map<java.util.Locale, String> canonicalURLMap,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LayoutSEOEntryServiceUtil.class, "updateLayoutSEOEntry",
				_updateLayoutSEOEntryParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, privateLayout, layoutId,
				enabledCanonicalURLMap, canonicalURLMap, serviceContext);

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

			return (com.liferay.layout.seo.model.LayoutSEOEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LayoutSEOEntryServiceHttp.class);

	private static final Class<?>[] _copyLayoutSEOEntryParameterTypes0 =
		new Class[] {
			long.class, long.class, boolean.class, long.class, boolean.class,
			java.util.Map.class, long.class, boolean.class, java.util.Map.class,
			java.util.Map.class, long.class, boolean.class, java.util.Map.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCustomMetaTagsParameterTypes1 =
		new Class[] {
			long.class, boolean.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateLayoutSEOEntryParameterTypes2 =
		new Class[] {
			long.class, boolean.class, long.class, boolean.class,
			java.util.Map.class, boolean.class, java.util.Map.class,
			java.util.Map.class, long.class, boolean.class, java.util.Map.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateLayoutSEOEntryParameterTypes3 =
		new Class[] {
			long.class, boolean.class, long.class, boolean.class,
			java.util.Map.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}