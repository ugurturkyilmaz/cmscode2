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

package com.liferay.document.library.opener.google.drive.web.internal.upload;

import com.liferay.document.library.opener.google.drive.upload.UniqueFileEntryTitleProvider;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(service = UniqueFileEntryTitleProvider.class)
public class UniqueFileEntryTitleProviderImpl
	implements UniqueFileEntryTitleProvider {

	@Override
	public String provide(long groupId, long folderId, Locale locale)
		throws PortalException {

		return _uniqueFileEntryTitleProvider.provide(groupId, folderId, locale);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x)
	 */
	@Deprecated
	@Override
	public String provide(long groupId, long folderId, String fileName)
		throws PortalException {

		return _uniqueFileEntryTitleProvider.provide(
			groupId, folderId, fileName);
	}

	@Reference
	private
		com.liferay.document.library.opener.upload.UniqueFileEntryTitleProvider
			_uniqueFileEntryTitleProvider;

}