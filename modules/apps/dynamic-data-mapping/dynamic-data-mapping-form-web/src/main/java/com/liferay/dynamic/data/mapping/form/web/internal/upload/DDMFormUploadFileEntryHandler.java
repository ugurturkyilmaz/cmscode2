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

package com.liferay.dynamic.data.mapping.form.web.internal.upload;

import com.liferay.dynamic.data.mapping.constants.DDMActionKeys;
import com.liferay.dynamic.data.mapping.constants.DDMFormConstants;
import com.liferay.dynamic.data.mapping.form.web.internal.security.permission.resource.DDMFormInstancePermission;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.upload.UploadFileEntryHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(service = DDMFormUploadFileEntryHandler.class)
public class DDMFormUploadFileEntryHandler implements UploadFileEntryHandler {

	@Override
	public FileEntry upload(UploadPortletRequest uploadPortletRequest)
		throws IOException, PortalException {

		File file = null;

		try (InputStream inputStream = uploadPortletRequest.getFileAsStream(
				"file")) {

			long formInstanceId = ParamUtil.getLong(
				uploadPortletRequest, "formInstanceId");
			long groupId = ParamUtil.getLong(uploadPortletRequest, "groupId");
			long folderId = ParamUtil.getLong(uploadPortletRequest, "folderId");

			file = FileUtil.createTempFile(inputStream);

			String fileName = uploadPortletRequest.getFileName("file");

			_ddmFormUploadValidator.validateFileSize(file, fileName);

			_ddmFormUploadValidator.validateFileExtension(fileName);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)uploadPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			return addFileEntry(
				formInstanceId, groupId, folderId, file, fileName,
				MimeTypesUtil.getContentType(file, fileName), themeDisplay);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	protected FileEntry addFileEntry(
			long formInstanceId, long groupId, long folderId, File file,
			String fileName, String mimeType, ThemeDisplay themeDisplay)
		throws PortalException {

		if (!DDMFormInstancePermission.contains(
				themeDisplay.getPermissionChecker(), formInstanceId,
				DDMActionKeys.ADD_FORM_INSTANCE_RECORD)) {

			throw new PrincipalException.MustHavePermission(
				themeDisplay.getPermissionChecker(),
				DDMFormInstance.class.getName(), formInstanceId,
				DDMActionKeys.ADD_FORM_INSTANCE_RECORD);
		}

		long userId = _getDDMFormDefaultUserId(themeDisplay.getCompanyId());

		String uniqueFileName = PortletFileRepositoryUtil.getUniqueFileName(
			groupId, folderId, fileName);

		return PortletFileRepositoryUtil.addPortletFileEntry(
			groupId, userId, DDMFormInstance.class.getName(), 0,
			DDMFormConstants.SERVICE_NAME, folderId, file, uniqueFileName,
			mimeType, true);
	}

	private long _getDDMFormDefaultUserId(long companyId)
		throws PortalException {

		Company company = _companyLocalService.getCompany(companyId);

		return _userLocalService.getUserIdByEmailAddress(
			companyId,
			StringBundler.concat(
				DDMFormConstants.DDM_FORM_DEFAULT_USER_SCREEN_NAME,
				StringPool.AT, company.getMx()));
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private DDMFormUploadValidator _ddmFormUploadValidator;

	@Reference
	private UserLocalService _userLocalService;

}