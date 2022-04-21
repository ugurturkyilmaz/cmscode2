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

package com.liferay.knowledge.base.web.internal.display.context;

import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBArticleLocalServiceUtil;
import com.liferay.knowledge.base.service.KBArticleServiceUtil;
import com.liferay.knowledge.base.service.KBFolderLocalServiceUtil;
import com.liferay.knowledge.base.service.KBFolderServiceUtil;
import com.liferay.knowledge.base.util.comparator.KBObjectsTitleComparator;
import com.liferay.knowledge.base.web.internal.constants.KBWebKeys;
import com.liferay.knowledge.base.web.internal.security.permission.resource.KBArticlePermission;
import com.liferay.knowledge.base.web.internal.security.permission.resource.KBFolderPermission;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class KBSelectParentDisplayContext {

	public KBSelectParentDisplayContext(
			HttpServletRequest httpServletRequest,
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			RenderRequest renderRequest)
		throws PortalException {

		_httpServletRequest = httpServletRequest;
		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_renderRequest = renderRequest;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_initParentData();
	}

	public long getParentResourceClassNameId() {
		return _parentResourceClassNameId;
	}

	public long getParentResourcePrimKey() {
		return _parentResourcePrimKey;
	}

	public long getResourceClassNameId() {
		if (_resourceClassNameId != null) {
			return _resourceClassNameId;
		}

		_resourceClassNameId = ParamUtil.getLong(
			_httpServletRequest, "resourceClassNameId");

		return _resourceClassNameId;
	}

	public SearchContainer<?> getSearchContainer() throws PortalException {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer(
			_renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM,
			SearchContainer.DEFAULT_DELTA,
			PortletURLUtil.getCurrent(
				_liferayPortletRequest, _liferayPortletResponse),
			null, "there-are-no-entries");

		if (isKBFolderView()) {
			_searchContainer.setResultsAndTotal(
				() -> new ArrayList<>(
					KBFolderServiceUtil.getKBFolders(
						_themeDisplay.getScopeGroupId(),
						getParentResourcePrimKey(), _searchContainer.getStart(),
						_searchContainer.getEnd())),
				KBFolderServiceUtil.getKBFoldersCount(
					_themeDisplay.getScopeGroupId(),
					getParentResourcePrimKey()));
		}
		else {
			_searchContainer.setResultsAndTotal(
				() -> new ArrayList<>(
					KBFolderServiceUtil.getKBFoldersAndKBArticles(
						_themeDisplay.getScopeGroupId(),
						getParentResourcePrimKey(), getTargetStatus(),
						_searchContainer.getStart(), _searchContainer.getEnd(),
						new KBObjectsTitleComparator<Object>())),
				KBFolderServiceUtil.getKBFoldersAndKBArticlesCount(
					_themeDisplay.getScopeGroupId(), getParentResourcePrimKey(),
					getTargetStatus()));
		}

		return _searchContainer;
	}

	public int getStatus() {
		if (_status != null) {
			return _status;
		}

		_status = (Integer)_httpServletRequest.getAttribute(
			KBWebKeys.KNOWLEDGE_BASE_STATUS);

		return _status;
	}

	public int getTargetStatus() {
		if (_targetStatus != null) {
			return _targetStatus;
		}

		_targetStatus = ParamUtil.getInteger(
			_httpServletRequest, "targetStatus", getStatus());

		return _targetStatus;
	}

	public boolean isKBFolderView() {
		if (_kbFolderView != null) {
			return _kbFolderView;
		}

		_kbFolderView = false;

		if (getResourceClassNameId() == _KB_FOLDER_CLASS_NAME_ID) {
			_kbFolderView = true;
		}

		return _kbFolderView;
	}

	public void populatePortletBreadcrumbEntries(PortletURL portletURL)
		throws Exception {

		_populatePortletBreadcrumbEntries(
			_parentResourceClassNameId, _parentResourcePrimKey, portletURL);
	}

	private void _initParentData() throws PortalException {
		_parentResourceClassNameId = ParamUtil.getLong(
			_httpServletRequest, "parentResourceClassNameId",
			_KB_FOLDER_CLASS_NAME_ID);
		_parentResourcePrimKey = ParamUtil.getLong(
			_httpServletRequest, "parentResourcePrimKey",
			KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		if (_parentResourcePrimKey !=
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			if (_parentResourceClassNameId == _KB_FOLDER_CLASS_NAME_ID) {
				KBFolder parentKBFolder =
					KBFolderLocalServiceUtil.fetchKBFolder(
						_parentResourcePrimKey);

				if ((parentKBFolder == null) ||
					!KBFolderPermission.contains(
						_themeDisplay.getPermissionChecker(), parentKBFolder,
						ActionKeys.VIEW)) {

					_parentResourceClassNameId = _KB_FOLDER_CLASS_NAME_ID;

					_parentResourcePrimKey =
						KBFolderConstants.DEFAULT_PARENT_FOLDER_ID;
				}
			}
			else {
				KBArticle parentKBArticle =
					KBArticleLocalServiceUtil.fetchLatestKBArticle(
						_parentResourcePrimKey, getStatus());

				if ((parentKBArticle == null) ||
					!KBArticlePermission.contains(
						_themeDisplay.getPermissionChecker(), parentKBArticle,
						ActionKeys.VIEW)) {

					_parentResourceClassNameId = _KB_FOLDER_CLASS_NAME_ID;

					_parentResourcePrimKey =
						KBFolderConstants.DEFAULT_PARENT_FOLDER_ID;
				}
			}
		}
	}

	private void _populatePortletBreadcrumbEntries(
			long parentResourceClassNameId, long parentResourcePrimKey,
			PortletURL portletURL)
		throws Exception {

		PortletURL currentURL = PortletURLBuilder.create(
			PortletURLUtil.clone(portletURL, _liferayPortletResponse)
		).setParameter(
			"parentResourceClassNameId", parentResourceClassNameId
		).setParameter(
			"parentResourcePrimKey", parentResourcePrimKey
		).buildPortletURL();

		long kbFolderClassNameId = PortalUtil.getClassNameId(
			KBFolderConstants.getClassName());

		if (parentResourcePrimKey ==
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			PortalUtil.addPortletBreadcrumbEntry(
				_httpServletRequest, themeDisplay.translate("home"),
				currentURL.toString());
		}
		else if (parentResourceClassNameId == kbFolderClassNameId) {
			KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(
				parentResourcePrimKey);

			_populatePortletBreadcrumbEntries(
				kbFolder.getClassNameId(), kbFolder.getParentKBFolderId(),
				currentURL);

			PortalUtil.addPortletBreadcrumbEntry(
				_httpServletRequest, kbFolder.getName(), currentURL.toString());
		}
		else {
			KBArticle kbArticle = KBArticleServiceUtil.getLatestKBArticle(
				parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

			_populatePortletBreadcrumbEntries(
				kbArticle.getParentResourceClassNameId(),
				kbArticle.getParentResourcePrimKey(), currentURL);

			PortalUtil.addPortletBreadcrumbEntry(
				_httpServletRequest, kbArticle.getTitle(),
				currentURL.toString());
		}
	}

	private static final long _KB_FOLDER_CLASS_NAME_ID =
		PortalUtil.getClassNameId(KBFolderConstants.getClassName());

	private final HttpServletRequest _httpServletRequest;
	private Boolean _kbFolderView;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private long _parentResourceClassNameId;
	private long _parentResourcePrimKey;
	private final RenderRequest _renderRequest;
	private Long _resourceClassNameId;
	private SearchContainer<Object> _searchContainer;
	private Integer _status;
	private Integer _targetStatus;
	private final ThemeDisplay _themeDisplay;

}