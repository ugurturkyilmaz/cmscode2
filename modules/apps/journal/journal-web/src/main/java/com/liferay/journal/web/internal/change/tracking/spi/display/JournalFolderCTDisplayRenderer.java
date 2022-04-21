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

package com.liferay.journal.web.internal.change.tracking.spi.display;

import com.liferay.change.tracking.spi.display.BaseCTDisplayRenderer;
import com.liferay.change.tracking.spi.display.CTDisplayRenderer;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.web.internal.security.permission.resource.JournalFolderPermission;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Truong
 */
@Component(immediate = true, service = CTDisplayRenderer.class)
public class JournalFolderCTDisplayRenderer
	extends BaseCTDisplayRenderer<JournalFolder> {

	@Override
	public String getEditURL(
			HttpServletRequest httpServletRequest, JournalFolder journalFolder)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!JournalFolderPermission.contains(
				themeDisplay.getPermissionChecker(), journalFolder,
				ActionKeys.UPDATE)) {

			return null;
		}

		Group group = _groupLocalService.getGroup(journalFolder.getGroupId());

		if (group.isCompany()) {
			group = themeDisplay.getScopeGroup();
		}

		return PortletURLBuilder.create(
			_portal.getControlPanelPortletURL(
				httpServletRequest, group, JournalPortletKeys.JOURNAL, 0, 0,
				PortletRequest.RENDER_PHASE)
		).setMVCPath(
			"/edit_folder.jsp"
		).setRedirect(
			_portal.getCurrentURL(httpServletRequest)
		).setParameter(
			"folderId", journalFolder.getFolderId()
		).setParameter(
			"groupId", journalFolder.getGroupId()
		).buildString();
	}

	@Override
	public Class<JournalFolder> getModelClass() {
		return JournalFolder.class;
	}

	@Override
	public String getTitle(Locale locale, JournalFolder journalFolder) {
		return journalFolder.getName();
	}

	@Override
	protected void buildDisplay(DisplayBuilder<JournalFolder> displayBuilder) {
		JournalFolder journalFolder = displayBuilder.getModel();

		displayBuilder.display(
			"name", journalFolder.getName()
		).display(
			"description", journalFolder.getDescription()
		).display(
			"created-by",
			() -> {
				String userName = journalFolder.getUserName();

				if (Validator.isNotNull(userName)) {
					return userName;
				}

				return null;
			}
		).display(
			"create-date", journalFolder.getCreateDate()
		).display(
			"last-modified", journalFolder.getModifiedDate()
		).display(
			"parent-folder",
			() -> {
				JournalFolder parentFolder = journalFolder.getParentFolder();

				return parentFolder.getName();
			}
		);
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

}