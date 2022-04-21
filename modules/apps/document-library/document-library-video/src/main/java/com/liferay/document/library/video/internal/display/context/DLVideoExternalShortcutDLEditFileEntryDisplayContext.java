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

package com.liferay.document.library.video.internal.display.context;

import com.liferay.document.library.display.context.BaseDLEditFileEntryDisplayContext;
import com.liferay.document.library.display.context.DLEditFileEntryDisplayContext;
import com.liferay.document.library.display.context.DLFilePicker;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.video.external.shortcut.DLVideoExternalShortcut;
import com.liferay.document.library.video.internal.constants.DLVideoConstants;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 * @author Alejandro Tardín
 */
public class DLVideoExternalShortcutDLEditFileEntryDisplayContext
	extends BaseDLEditFileEntryDisplayContext {

	public DLVideoExternalShortcutDLEditFileEntryDisplayContext(
		DLEditFileEntryDisplayContext parentDLEditFileEntryDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		DLFileEntryType dlFileEntryType, ServletContext servletContext) {

		super(
			_UUID, parentDLEditFileEntryDisplayContext, httpServletRequest,
			httpServletResponse, dlFileEntryType);

		_servletContext = servletContext;
	}

	public DLVideoExternalShortcutDLEditFileEntryDisplayContext(
		DLEditFileEntryDisplayContext parentDLEditFileEntryDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		DLVideoExternalShortcut dlVideoExternalShortcut, FileEntry fileEntry,
		ServletContext servletContext) {

		super(
			_UUID, parentDLEditFileEntryDisplayContext, httpServletRequest,
			httpServletResponse, fileEntry);

		_dlVideoExternalShortcut = dlVideoExternalShortcut;
		_servletContext = servletContext;
	}

	@Override
	public DLFilePicker getDLFilePicker(String onFilePickCallback) {
		return new DLVideoExternalShortcutDLFilePicker(
			_dlVideoExternalShortcut, onFilePickCallback, _servletContext);
	}

	@Override
	public long getMaximumUploadSize() {
		return 0;
	}

	@Override
	public boolean isCancelCheckoutDocumentButtonVisible() {
		return false;
	}

	@Override
	public boolean isCheckinButtonVisible() {
		return false;
	}

	@Override
	public boolean isCheckoutDocumentButtonVisible() {
		return false;
	}

	@Override
	public boolean isDDMStructureVisible(DDMStructure ddmStructure)
		throws PortalException {

		String ddmStructureKey = ddmStructure.getStructureKey();

		if (ddmStructureKey.equals(
				DLVideoConstants.
					DDM_STRUCTURE_KEY_DL_VIDEO_EXTERNAL_SHORTCUT)) {

			return false;
		}

		return super.isDDMStructureVisible(ddmStructure);
	}

	@Override
	public boolean isFileNameVisible() {
		return false;
	}

	@Override
	public boolean isVersionInfoVisible() {
		return false;
	}

	private static final UUID _UUID = UUID.fromString(
		"f3dad960-a5ea-4499-badd-0d1a06ee1c93");

	private DLVideoExternalShortcut _dlVideoExternalShortcut;
	private final ServletContext _servletContext;

}