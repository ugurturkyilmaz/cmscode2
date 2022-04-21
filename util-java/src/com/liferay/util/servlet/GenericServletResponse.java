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

package com.liferay.util.servlet;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.servlet.ServletOutputStreamAdapter;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;

import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class GenericServletResponse extends HttpServletResponseWrapper {

	public GenericServletResponse(HttpServletResponse httpServletResponse) {
		super(httpServletResponse);
	}

	public int getContentLength() {
		if (_contentLength > Integer.MAX_VALUE) {
			return -1;
		}

		return (int)_contentLength;
	}

	public long getContentLengthLong() {
		return _contentLength;
	}

	@Override
	public String getContentType() {
		return _contentType;
	}

	public byte[] getData() {
		return _unsyncByteArrayOutputStream.toByteArray();
	}

	@Override
	public ServletOutputStream getOutputStream() {
		return new ServletOutputStreamAdapter(_unsyncByteArrayOutputStream);
	}

	@Override
	public PrintWriter getWriter() {
		return UnsyncPrintWriterPool.borrow(
			getOutputStream(), getCharacterEncoding());
	}

	@Override
	public void setContentLength(int length) {
		super.setContentLength(length);

		_contentLength = length;
	}

	@Override
	public void setContentLengthLong(long length) {
		super.setContentLengthLong(length);

		_contentLength = length;
	}

	@Override
	public void setContentType(String contentType) {
		super.setContentType(contentType);

		_contentType = contentType;
	}

	private long _contentLength;
	private String _contentType;
	private final UnsyncByteArrayOutputStream _unsyncByteArrayOutputStream =
		new UnsyncByteArrayOutputStream();

}