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

package com.liferay.redirect.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.redirect.internal.util.URLUtil;
import com.liferay.redirect.model.RedirectNotFoundEntry;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.redirect.model.RedirectNotFoundEntry",
	service = ModelDocumentContributor.class
)
public class RedirectNotFoundEntryModelDocumentContributor
	implements ModelDocumentContributor<RedirectNotFoundEntry> {

	@Override
	public void contribute(
		Document document, RedirectNotFoundEntry redirectNotFoundEntry) {

		document.addText(Field.URL, redirectNotFoundEntry.getUrl());
		document.addKeyword("ignored", redirectNotFoundEntry.isIgnored());
		document.addNumber(
			"requestCount", redirectNotFoundEntry.getRequestCount());
		document.addText(
			"urlParts", URLUtil.splitURL(redirectNotFoundEntry.getUrl()));
	}

}