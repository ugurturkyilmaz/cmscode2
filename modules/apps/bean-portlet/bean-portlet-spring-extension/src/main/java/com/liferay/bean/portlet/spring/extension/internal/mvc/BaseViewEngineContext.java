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

package com.liferay.bean.portlet.spring.extension.internal.mvc;

import com.liferay.bean.portlet.extension.ViewRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Locale;

import javax.mvc.engine.ViewEngineContext;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * @author Neil Griffin
 */
public abstract class BaseViewEngineContext implements ViewEngineContext {

	@Override
	public Locale getLocale() {
		PortletRequest portletRequest = getPortletRequest();

		return portletRequest.getLocale();
	}

	@Override
	public MediaType getMediaType() {
		if (_mediaType == null) {
			MimeResponse mimeResponse = getMimeResponse();

			String contentType = mimeResponse.getContentType();

			if (contentType == null) {
				_mediaType = MediaType.TEXT_HTML_TYPE;
			}
			else {
				String type = contentType;
				String subtype = null;

				int pos = contentType.indexOf('/');

				if (pos > 0) {
					type = contentType.substring(0, pos);
					subtype = contentType.substring(pos + 1);
				}

				_mediaType = new MediaType(
					type, subtype, mimeResponse.getCharacterEncoding());
			}
		}

		return _mediaType;
	}

	@Override
	public OutputStream getOutputStream() {
		try {
			MimeResponse mimeResponse = getMimeResponse();

			return mimeResponse.getPortletOutputStream();
		}
		catch (IOException ioException) {
			_log.error(ioException);

			return null;
		}
	}

	@Override
	public <T> T getRequest(Class<T> clazz) {
		return clazz.cast(getPortletRequest());
	}

	@Override
	public ResourceInfo getResourceInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T getResponse(Class<T> clazz) {
		return clazz.cast(getMimeResponse());
	}

	@Override
	public MultivaluedMap<String, Object> getResponseHeaders() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UriInfo getUriInfo() {
		return new UriInfoImpl();
	}

	@Override
	public String getView() {
		PortletRequest portletRequest = getPortletRequest();

		return (String)portletRequest.getAttribute(ViewRenderer.VIEW_NAME);
	}

	protected abstract MimeResponse getMimeResponse();

	protected abstract PortletRequest getPortletRequest();

	private static final Log _log = LogFactoryUtil.getLog(
		BaseViewEngineContext.class);

	private MediaType _mediaType;

}