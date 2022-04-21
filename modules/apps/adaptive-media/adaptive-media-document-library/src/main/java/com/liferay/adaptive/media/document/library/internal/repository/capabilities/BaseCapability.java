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

package com.liferay.adaptive.media.document.library.internal.repository.capabilities;

import com.liferay.adaptive.media.document.library.internal.util.AMCleanUpOnUpdateAndCheckInThreadLocal;
import com.liferay.adaptive.media.image.service.AMImageEntryLocalService;
import com.liferay.adaptive.media.processor.AMAsyncProcessor;
import com.liferay.adaptive.media.processor.AMAsyncProcessorLocator;
import com.liferay.document.library.kernel.util.DLAppHelperThreadLocal;
import com.liferay.document.library.security.io.InputStreamSanitizer;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.capabilities.Capability;
import com.liferay.portal.kernel.repository.event.RepositoryEventAware;
import com.liferay.portal.kernel.repository.event.RepositoryEventType;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileEntryWrapper;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.FileVersionWrapper;
import com.liferay.portal.kernel.repository.registry.RepositoryEventRegistry;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.InputStream;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
public abstract class BaseCapability
	implements Capability, RepositoryEventAware {

	@Override
	public void registerRepositoryEventListeners(
		RepositoryEventRegistry repositoryEventRegistry) {

		repositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Add.class, FileEntry.class,
			this::_updateAdaptiveMedia);
		repositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Delete.class, FileEntry.class,
			this::_deleteAdaptiveMedia);
		repositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Update.class, FileEntry.class,
			this::_updateAdaptiveMedia);
		repositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Delete.class, FileVersion.class,
			this::_deleteAdaptiveMedia);
	}

	@Reference
	protected AMAsyncProcessorLocator amAsyncProcessorLocator;

	@Reference
	protected AMImageEntryLocalService amImageEntryLocalService;

	@Reference
	protected InputStreamSanitizer inputStreamSanitizer;

	private void _deleteAdaptiveMedia(FileEntry fileEntry) {
		if (!DLAppHelperThreadLocal.isEnabled() ||
			ExportImportThreadLocal.isImportInProcess()) {

			return;
		}

		try {
			AMAsyncProcessor<FileVersion, ?> amAsyncProcessor =
				amAsyncProcessorLocator.locateForClass(FileVersion.class);

			List<FileVersion> fileVersions = fileEntry.getFileVersions(
				WorkflowConstants.STATUS_ANY);

			for (FileVersion fileVersion : fileVersions) {
				amAsyncProcessor.triggerCleanUp(
					fileVersion,
					String.valueOf(fileVersion.getFileVersionId()));
			}
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	private void _deleteAdaptiveMedia(FileVersion fileVersion) {
		if (!DLAppHelperThreadLocal.isEnabled() ||
			ExportImportThreadLocal.isImportInProcess()) {

			return;
		}

		try {
			AMAsyncProcessor<FileVersion, ?> amAsyncProcessor =
				amAsyncProcessorLocator.locateForClass(FileVersion.class);

			amAsyncProcessor.triggerCleanUp(
				fileVersion, String.valueOf(fileVersion.getFileVersionId()));
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	private void _updateAdaptiveMedia(FileEntry fileEntry) {
		if (!DLAppHelperThreadLocal.isEnabled() ||
			ExportImportThreadLocal.isImportInProcess()) {

			return;
		}

		try {
			FileVersion latestFileVersion = fileEntry.getLatestFileVersion(
				true);

			if (AMCleanUpOnUpdateAndCheckInThreadLocal.isEnabled()) {
				amImageEntryLocalService.deleteAMImageEntryFileVersion(
					latestFileVersion);
			}

			AMAsyncProcessor<FileVersion, ?> amAsyncProcessor =
				amAsyncProcessorLocator.locateForClass(FileVersion.class);

			amAsyncProcessor.triggerProcess(
				_wrap(latestFileVersion),
				String.valueOf(latestFileVersion.getFileVersionId()));
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	private FileVersion _wrap(FileVersion fileVersion) {
		if (fileVersion == null) {
			return null;
		}

		return new SafeFileVersion(fileVersion);
	}

	private class SafeFileEntry extends FileEntryWrapper {

		public SafeFileEntry(FileEntry fileEntry) {
			super(fileEntry);
		}

		@Override
		public InputStream getContentStream() throws PortalException {
			return inputStreamSanitizer.sanitize(super.getContentStream());
		}

		@Override
		public InputStream getContentStream(String version)
			throws PortalException {

			return inputStreamSanitizer.sanitize(
				super.getContentStream(version));
		}

		@Override
		public FileVersion getFileVersion() throws PortalException {
			return new SafeFileVersion(super.getFileVersion());
		}

		@Override
		public FileVersion getFileVersion(String version)
			throws PortalException {

			return new SafeFileVersion(super.getFileVersion(version));
		}

		@Override
		public FileVersion getLatestFileVersion() throws PortalException {
			return new SafeFileVersion(super.getLatestFileVersion());
		}

		@Override
		public FileVersion getLatestFileVersion(boolean trusted)
			throws PortalException {

			return new SafeFileVersion(super.getLatestFileVersion(trusted));
		}

	}

	private class SafeFileVersion extends FileVersionWrapper {

		public SafeFileVersion(FileVersion fileVersion) {
			super(fileVersion);
		}

		@Override
		public InputStream getContentStream(boolean incrementCounter)
			throws PortalException {

			return inputStreamSanitizer.sanitize(
				super.getContentStream(incrementCounter));
		}

		@Override
		public FileEntry getFileEntry() throws PortalException {
			return new SafeFileEntry(super.getFileEntry());
		}

	}

}