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

package com.liferay.document.library.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.web.internal.display.context.helper.IGRequestHelper;
import com.liferay.document.library.web.internal.settings.DLPortletInstanceSettings;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Objects;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mariano Álvaro Sáiz
 */
public class IGViewDisplayContext {

	public IGViewDisplayContext(
		IGRequestHelper igRequestHelper, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_igRequestHelper = igRequestHelper;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_httpServletRequest = igRequestHelper.getRequest();

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_initFolder();
	}

	public SearchContainer<AssetEntry> getAssetEntrySearchContainer()
		throws Exception {

		SearchContainer<AssetEntry> searchContainer = new SearchContainer(
			_renderRequest, null, null, "cur2", SearchContainer.DEFAULT_DELTA,
			getPortletURL(), null, null);

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery(
			new long[] {
				PortalUtil.getClassNameId(DLFileEntryConstants.getClassName()),
				PortalUtil.getClassNameId(
					DLFileShortcutConstants.getClassName())
			},
			searchContainer) {

			{
				setEnablePermissions(true);
				setExcludeZeroViewCount(false);
			}
		};

		searchContainer.setResultsAndTotal(
			() -> AssetEntryServiceUtil.getEntries(assetEntryQuery),
			AssetEntryServiceUtil.getEntriesCount(assetEntryQuery));

		return searchContainer;
	}

	public Folder getFolder() throws PortalException {
		return _folder;
	}

	public long getFolderId() throws PortalException {
		return _folderId;
	}

	public int getFoldersCount() throws PortalException {
		if (_foldersCount != null) {
			return _foldersCount;
		}

		_foldersCount = DLAppServiceUtil.getFoldersCount(
			getRepositoryId(), getFolderId(), true);

		return _foldersCount;
	}

	public int getFoldersImagesCount() throws PortalException {
		if (_folderImagesCount != null) {
			return _folderImagesCount;
		}

		_folderImagesCount =
			DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcutsCount(
				getRepositoryId(), getFolderId(), getStatus(),
				getMediaGalleryMimeTypes(), true);

		return _folderImagesCount;
	}

	public SearchContainer<Object> getHomeSearchContainer() throws Exception {
		SearchContainer<Object> searchContainer = new SearchContainer(
			_renderRequest, null, null, "cur2", SearchContainer.DEFAULT_DELTA,
			getPortletURL(), null, null);

		searchContainer.setResultsAndTotal(
			() -> DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcuts(
				getRepositoryId(), getFolderId(), getStatus(),
				getMediaGalleryMimeTypes(), true, searchContainer.getStart(),
				searchContainer.getEnd(),
				searchContainer.getOrderByComparator()),
			getFoldersImagesCount());

		return searchContainer;
	}

	public int getImagesCount() throws PortalException {
		if (_imagesCount != null) {
			return _imagesCount;
		}

		_imagesCount = getFoldersImagesCount() - getFoldersCount();

		return _imagesCount;
	}

	public String[] getMediaGalleryMimeTypes() {
		if (_mediaGalleryMimeTypes != null) {
			return _mediaGalleryMimeTypes;
		}

		DLPortletInstanceSettings dlPortletInstanceSettings =
			_getDLPortletInstanceSettings();

		_mediaGalleryMimeTypes = dlPortletInstanceSettings.getMimeTypes();

		return _mediaGalleryMimeTypes;
	}

	public PortletURL getPortletURL() throws PortalException {
		if (_portletURL != null) {
			return _portletURL;
		}

		_portletURL = PortletURLBuilder.createRenderURL(
			_renderResponse
		).setMVCRenderCommandName(
			"/image_gallery_display/view"
		).setRedirect(
			ParamUtil.getString(_httpServletRequest, "redirect")
		).setParameter(
			"folderId", getFolderId()
		).setParameter(
			"topLink", getTopLink()
		).buildPortletURL();

		return _portletURL;
	}

	public SearchContainer<FileEntry> getRecentMineSearchContainer()
		throws Exception {

		SearchContainer<FileEntry> searchContainer = new SearchContainer(
			_renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM,
			SearchContainer.DEFAULT_DELTA, getPortletURL(), null, null);

		long groupImagesUserId = 0;

		if (isTopLinkMine() && _themeDisplay.isSignedIn()) {
			groupImagesUserId = _themeDisplay.getUserId();
		}

		long dlAppGroupImagesUserId = groupImagesUserId;

		searchContainer.setResultsAndTotal(
			() -> DLAppServiceUtil.getGroupFileEntries(
				getRepositoryId(), dlAppGroupImagesUserId, getRootFolderId(),
				getMediaGalleryMimeTypes(), getStatus(),
				searchContainer.getStart(), searchContainer.getEnd(),
				searchContainer.getOrderByComparator()),
			DLAppServiceUtil.getGroupFileEntriesCount(
				getRepositoryId(), dlAppGroupImagesUserId, getRootFolderId(),
				getMediaGalleryMimeTypes(), getStatus()));

		return searchContainer;
	}

	public long getRepositoryId() {
		if (_repositoryId != null) {
			return _repositoryId;
		}

		DLPortletInstanceSettings dlPortletInstanceSettings =
			_getDLPortletInstanceSettings();

		_repositoryId = dlPortletInstanceSettings.getSelectedRepositoryId();

		if ((_folder != null) &&
			(dlPortletInstanceSettings.getSelectedRepositoryId() !=
				_folder.getRepositoryId())) {

			_repositoryId = _folder.getRepositoryId();
		}
		else if (_repositoryId == 0) {
			_repositoryId = _themeDisplay.getScopeGroupId();
		}

		return _repositoryId;
	}

	public Long getRootFolderId() {
		if (_rootFolderId != null) {
			return _rootFolderId;
		}

		DLPortletInstanceSettings dlPortletInstanceSettings =
			_getDLPortletInstanceSettings();

		_rootFolderId = dlPortletInstanceSettings.getRootFolderId();

		if (_rootFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			try {
				Folder rootFolder = DLAppLocalServiceUtil.getFolder(
					_rootFolderId);

				if (rootFolder.getGroupId() !=
						_themeDisplay.getScopeGroupId()) {

					_rootFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
				}
			}
			catch (Exception exception) {
			}
		}

		return _rootFolderId;
	}

	public int getStatus() {
		if (_status != null) {
			return _status;
		}

		_status = WorkflowConstants.STATUS_APPROVED;

		PermissionChecker permissionChecker =
			_themeDisplay.getPermissionChecker();
		User user = _themeDisplay.getUser();

		if (permissionChecker.isContentReviewer(
				user.getCompanyId(), _themeDisplay.getScopeGroupId())) {

			_status = WorkflowConstants.STATUS_ANY;
		}

		return _status;
	}

	public String getTopLink() {
		if (_topLink != null) {
			return _topLink;
		}

		_topLink = ParamUtil.getString(_httpServletRequest, "topLink", "home");

		return _topLink;
	}

	public boolean isAssetEntryQuery() {
		if ((_getAssetCategoryId() > 0) ||
			Validator.isNotNull(_getAssetTagName())) {

			return true;
		}

		return false;
	}

	public boolean isDefaultFolderView() {
		return _defaultFolderView;
	}

	public boolean isTopLinkHome() {
		if (Objects.equals(getTopLink(), "home")) {
			return true;
		}

		return false;
	}

	public boolean isTopLinkMine() {
		if (Objects.equals(getTopLink(), "mine")) {
			return true;
		}

		return false;
	}

	public boolean isTopLinkRecent() {
		if (Objects.equals(getTopLink(), "recent")) {
			return true;
		}

		return false;
	}

	private Long _getAssetCategoryId() {
		if (_assetCategoryId != null) {
			return _assetCategoryId;
		}

		_assetCategoryId = ParamUtil.getLong(_httpServletRequest, "categoryId");

		return _assetCategoryId;
	}

	private String _getAssetTagName() {
		if (_assetTagName != null) {
			return _assetTagName;
		}

		_assetTagName = ParamUtil.getString(_httpServletRequest, "tag");

		return _assetTagName;
	}

	private DLPortletInstanceSettings _getDLPortletInstanceSettings() {
		if (_dlPortletInstanceSettings != null) {
			return _dlPortletInstanceSettings;
		}

		_dlPortletInstanceSettings =
			_igRequestHelper.getDLPortletInstanceSettings();

		return _dlPortletInstanceSettings;
	}

	private void _initFolder() {
		if (_folder != null) {
			return;
		}

		_folder = (Folder)_httpServletRequest.getAttribute(
			WebKeys.DOCUMENT_LIBRARY_FOLDER);

		_folderId = BeanParamUtil.getLong(
			(Folder)_httpServletRequest.getAttribute(
				WebKeys.DOCUMENT_LIBRARY_FOLDER),
			_httpServletRequest, "folderId", getRootFolderId());

		_defaultFolderView = false;

		if ((_folder == null) &&
			(getRootFolderId() != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {

			_defaultFolderView = true;
		}

		if (_defaultFolderView) {
			try {
				_folder = DLAppLocalServiceUtil.getFolder(_folderId);
			}
			catch (Exception exception) {
				_folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
			}
		}
	}

	private Long _assetCategoryId;
	private String _assetTagName;
	private Boolean _defaultFolderView;
	private DLPortletInstanceSettings _dlPortletInstanceSettings;
	private Folder _folder;
	private long _folderId;
	private Integer _folderImagesCount;
	private Integer _foldersCount;
	private final HttpServletRequest _httpServletRequest;
	private final IGRequestHelper _igRequestHelper;
	private Integer _imagesCount;
	private String[] _mediaGalleryMimeTypes;
	private PortletURL _portletURL;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private Long _repositoryId;
	private Long _rootFolderId;
	private Integer _status;
	private final ThemeDisplay _themeDisplay;
	private String _topLink;

}