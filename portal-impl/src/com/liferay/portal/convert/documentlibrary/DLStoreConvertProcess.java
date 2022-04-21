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

package com.liferay.portal.convert.documentlibrary;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Iván Zaera
 * @author Roberto Díaz
 */
public interface DLStoreConvertProcess {

	public void copy(Store sourceStore, Store targetStore)
		throws PortalException;

	public void move(Store sourceStore, Store targetStore)
		throws PortalException;

	public default void transferFile(
		Store sourceStore, Store targetStore, long companyId, long repositoryId,
		String fileName, String versionLabel, boolean delete) {

		try (InputStream inputStream = sourceStore.getFileAsStream(
				companyId, repositoryId, fileName, versionLabel)) {

			targetStore.addFile(
				companyId, repositoryId, fileName, versionLabel, inputStream);

			if (delete) {
				sourceStore.deleteFile(
					companyId, repositoryId, fileName, versionLabel);
			}
		}
		catch (IOException | PortalException exception) {
			throw new SystemException(exception);
		}
	}

}