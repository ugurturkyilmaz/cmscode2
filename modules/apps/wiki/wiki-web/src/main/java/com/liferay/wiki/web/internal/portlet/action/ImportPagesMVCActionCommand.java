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

package com.liferay.wiki.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ProgressTracker;
import com.liferay.portal.kernel.util.ProgressTrackerThreadLocal;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.exception.NoSuchNodeException;
import com.liferay.wiki.service.WikiNodeService;

import java.io.IOException;
import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + WikiPortletKeys.WIKI_ADMIN,
		"mvc.command.name=/wiki/import_pages"
	},
	service = MVCActionCommand.class
)
public class ImportPagesMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			_importPages(actionRequest);
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchNodeException ||
				exception instanceof PrincipalException) {

				SessionErrors.add(actionRequest, exception.getClass());
			}
			else if (exception instanceof PortalException) {
				SessionErrors.add(actionRequest, exception.getClass());
			}
			else {
				throw new PortletException(exception);
			}
		}
	}

	private void _importPages(ActionRequest actionRequest) throws Exception {
		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		String importProgressId = ParamUtil.getString(
			uploadPortletRequest, "importProgressId");

		ProgressTracker progressTracker = new ProgressTracker(importProgressId);

		ProgressTrackerThreadLocal.setProgressTracker(progressTracker);

		progressTracker.start(actionRequest);

		long nodeId = ParamUtil.getLong(uploadPortletRequest, "nodeId");
		String importer = ParamUtil.getString(uploadPortletRequest, "importer");

		int filesCount = ParamUtil.getInteger(
			uploadPortletRequest, "filesCount", 10);

		InputStream[] inputStreams = new InputStream[filesCount];

		try {
			for (int i = 0; i < filesCount; i++) {
				inputStreams[i] = uploadPortletRequest.getFileAsStream(
					"file" + i);
			}

			_wikiNodeService.importPages(
				nodeId, importer, inputStreams,
				actionRequest.getParameterMap());
		}
		finally {
			for (InputStream inputStream : inputStreams) {
				if (inputStream != null) {
					try {
						inputStream.close();
					}
					catch (IOException ioException) {
						if (_log.isWarnEnabled()) {
							_log.warn(ioException);
						}
					}
				}
			}
		}

		progressTracker.finish(actionRequest);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ImportPagesMVCActionCommand.class);

	@Reference
	private Portal _portal;

	@Reference
	private WikiNodeService _wikiNodeService;

}