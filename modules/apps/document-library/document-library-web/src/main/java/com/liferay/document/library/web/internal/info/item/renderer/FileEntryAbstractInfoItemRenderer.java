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

package com.liferay.document.library.web.internal.info.item.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "service.ranking:Integer=300", service = InfoItemRenderer.class
)
public class FileEntryAbstractInfoItemRenderer
	implements InfoItemRenderer<FileEntry> {

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "abstract");
	}

	@Override
	public void render(
		FileEntry fileEntry, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			httpServletRequest.setAttribute(
				WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY, fileEntry);

			httpServletRequest.setAttribute(
				WebKeys.DOCUMENT_LIBRARY_FILE_VERSION,
				fileEntry.getFileVersion());

			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher(
					"/document_library/info/item/renderer" +
						"/file_entry_abstract.jsp");

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.document.library.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private ServletContext _servletContext;

}