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

package com.liferay.fragment.web.internal.portlet.action;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.model.FragmentComposition;
import com.liferay.fragment.service.FragmentCompositionService;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + FragmentPortletKeys.FRAGMENT,
		"mvc.command.name=/fragment/update_fragment_composition_preview"
	},
	service = MVCActionCommand.class
)
public class UpdateFragmentCompositionPreviewMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long fragmentCompositionId = ParamUtil.getLong(
			actionRequest, "fragmentCompositionId");

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

		FileEntry tempFileEntry = fileEntry;

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				themeDisplay.getScopeGroupId(), FragmentPortletKeys.FRAGMENT);

		if (repository == null) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			repository = PortletFileRepositoryUtil.addPortletRepository(
				themeDisplay.getScopeGroupId(), FragmentPortletKeys.FRAGMENT,
				serviceContext);
		}

		String fileName =
			fragmentCompositionId + "_preview." + fileEntry.getExtension();

		FileEntry oldFileEntry =
			PortletFileRepositoryUtil.fetchPortletFileEntry(
				themeDisplay.getScopeGroupId(), repository.getDlFolderId(),
				fileName);

		if (oldFileEntry != null) {
			PortletFileRepositoryUtil.deletePortletFileEntry(
				oldFileEntry.getFileEntryId());
		}

		fileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
			themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
			FragmentComposition.class.getName(), fragmentCompositionId,
			FragmentPortletKeys.FRAGMENT, repository.getDlFolderId(),
			fileEntry.getContentStream(), fileName, fileEntry.getMimeType(),
			false);

		_fragmentCompositionService.updateFragmentComposition(
			fragmentCompositionId, fileEntry.getFileEntryId());

		TempFileEntryUtil.deleteTempFileEntry(tempFileEntry.getFileEntryId());

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		sendRedirect(actionRequest, actionResponse, redirect);
	}

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private FragmentCompositionService _fragmentCompositionService;

}