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

package com.liferay.document.library.internal.model.listener;

import com.liferay.document.library.internal.helper.DLExportableRepositoryPublisherHelper;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.staging.model.listener.StagingModelListener;

import java.util.Collection;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(service = ModelListener.class)
public class DLFolderStagingModelListener extends BaseModelListener<DLFolder> {

	@Override
	public void onAfterCreate(DLFolder dlFolder) throws ModelListenerException {
		Collection<Long> exportableRepositoryIds =
			_dlExportableRepositoryPublisherHelper.publish(
				dlFolder.getGroupId());

		if (!exportableRepositoryIds.contains(dlFolder.getRepositoryId())) {
			return;
		}

		_stagingModelListener.onAfterCreate(dlFolder);
	}

	@Override
	public void onAfterRemove(DLFolder dlFolder) throws ModelListenerException {
		_stagingModelListener.onAfterRemove(dlFolder);
	}

	@Override
	public void onAfterUpdate(DLFolder originalDLFolder, DLFolder dlFolder)
		throws ModelListenerException {

		Collection<Long> exportableRepositoryIds =
			_dlExportableRepositoryPublisherHelper.publish(
				dlFolder.getGroupId());

		if (!exportableRepositoryIds.contains(dlFolder.getRepositoryId())) {
			return;
		}

		_stagingModelListener.onAfterUpdate(dlFolder);
	}

	@Reference
	private DLExportableRepositoryPublisherHelper
		_dlExportableRepositoryPublisherHelper;

	@Reference
	private StagingModelListener<DLFolder> _stagingModelListener;

}