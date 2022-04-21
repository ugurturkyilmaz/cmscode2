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

package com.liferay.portal.error.code.internal.servlet.taglib;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReader;

import java.io.PrintWriter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	property = "mime.type=application/xml", service = DynamicInclude.class
)
public class ApplicationXMLPortalErrorCodeDynamicInclude
	extends BasePortalErrorCodeDynamicInclude {

	@Override
	protected void write(
		String message, PrintWriter printWriter, int statusCode) {

		Document document = _saxReader.createDocument(StringPool.UTF8);

		Element errorElement = document.addElement("error");

		Element messageElement = errorElement.addElement("message");

		messageElement.addText(message);

		Element statusCodeElement = errorElement.addElement("status-code");

		statusCodeElement.addText(String.valueOf(statusCode));

		printWriter.print(document.asXML());
	}

	@Override
	protected void write(
		String message, PrintWriter printWriter, String requestURI,
		int statusCode, Throwable throwable) {

		Document document = _saxReader.createDocument(StringPool.UTF8);

		Element errorElement = document.addElement("error");

		Element messageElement = errorElement.addElement("message");

		messageElement.addText(message);

		Element requestURIElement = errorElement.addElement("request-uri");

		requestURIElement.addText(requestURI);

		Element statusCodeElement = errorElement.addElement("status-code");

		statusCodeElement.addText(String.valueOf(statusCode));

		if (throwable != null) {
			Element throwableElement = errorElement.addElement("throwable");

			throwableElement.addCDATA(StackTraceUtil.getStackTrace(throwable));
		}

		printWriter.print(document.asXML());
	}

	@Reference
	private SAXReader _saxReader;

}