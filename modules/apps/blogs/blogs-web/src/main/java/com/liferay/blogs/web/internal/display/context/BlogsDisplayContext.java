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

package com.liferay.blogs.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.blogs.service.BlogsEntryServiceUtil;
import com.liferay.blogs.util.comparator.EntryModifiedDateComparator;
import com.liferay.blogs.web.internal.configuration.BlogsPortletInstanceConfiguration;
import com.liferay.blogs.web.internal.util.BlogsPortletInstanceConfigurationUtil;
import com.liferay.blogs.web.internal.util.BlogsUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemListBuilder;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.dao.search.SearchContainerResults;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mariano Álvaro Sáiz
 */
public class BlogsDisplayContext {

	public BlogsDisplayContext(
		HttpServletRequest httpServletRequest, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_httpServletRequest = httpServletRequest;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public BlogsPortletInstanceConfiguration
			getBlogsPortletInstanceConfiguration()
		throws PortalException {

		if (_blogsPortletInstanceConfiguration != null) {
			return _blogsPortletInstanceConfiguration;
		}

		_blogsPortletInstanceConfiguration =
			BlogsPortletInstanceConfigurationUtil.
				getBlogsPortletInstanceConfiguration(_themeDisplay);

		return _blogsPortletInstanceConfiguration;
	}

	public List<NavigationItem> getNavigationItems() {
		return NavigationItemListBuilder.add(
			navigationItem -> {
				navigationItem.setActive(
					!Objects.equals(
						_getMVCRenderCommandName(),
						"/blogs/view_not_published_entries"));
				navigationItem.setHref(getPortletURL());
				navigationItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "published"));
			}
		).add(
			navigationItem -> {
				navigationItem.setActive(
					Objects.equals(
						_getMVCRenderCommandName(),
						"/blogs/view_not_published_entries"));
				navigationItem.setHref(
					_renderResponse.createRenderURL(), "mvcRenderCommandName",
					"/blogs/view_not_published_entries");
				navigationItem.setLabel(
					LanguageUtil.format(
						_httpServletRequest, "not-published-x",
						getUnpublishedEntriesCount(), false));
			}
		).build();
	}

	public PortletURL getPortletURL() {
		if (_portletURL != null) {
			return _portletURL;
		}

		_portletURL = PortletURLBuilder.createRenderURL(
			_renderResponse
		).setMVCRenderCommandName(
			"/blogs/view"
		).buildPortletURL();

		return _portletURL;
	}

	public SearchContainer<?> getSearchContainer()
		throws PortalException, PortletException {

		BlogsPortletInstanceConfiguration blogsPortletInstanceConfiguration =
			getBlogsPortletInstanceConfiguration();

		SearchContainer<BaseModel<?>> searchContainer = new SearchContainer(
			_renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM,
			GetterUtil.getInteger(
				blogsPortletInstanceConfiguration.pageDelta()),
			getPortletURL(), null, null);

		searchContainer.setDeltaConfigurable(false);

		if (isAssetEntryQuery()) {
			SearchContainerResults<AssetEntry> searchContainerResults =
				BlogsUtil.getSearchContainerResults(searchContainer);

			searchContainer.setResultsAndTotal(
				() -> new ArrayList<>(searchContainerResults.getResults()),
				searchContainerResults.getTotal());
		}
		else if ((getUnpublishedEntriesCount() > 0) &&
				 Objects.equals(
					 _getMVCRenderCommandName(),
					 "/blogs/view_not_published_entries")) {

			searchContainer.setResultsAndTotal(
				() -> new ArrayList<>(
					BlogsEntryServiceUtil.getGroupUserEntries(
						_themeDisplay.getScopeGroupId(),
						_themeDisplay.getUserId(),
						new int[] {
							WorkflowConstants.STATUS_DRAFT,
							WorkflowConstants.STATUS_PENDING,
							WorkflowConstants.STATUS_SCHEDULED
						},
						searchContainer.getStart(), searchContainer.getEnd(),
						new EntryModifiedDateComparator())),
				getUnpublishedEntriesCount());
		}
		else {
			searchContainer.setResultsAndTotal(
				() -> new ArrayList<>(
					BlogsEntryServiceUtil.getGroupEntries(
						_themeDisplay.getScopeGroupId(),
						WorkflowConstants.STATUS_APPROVED,
						searchContainer.getStart(), searchContainer.getEnd())),
				BlogsEntryServiceUtil.getGroupEntriesCount(
					_themeDisplay.getScopeGroupId(),
					WorkflowConstants.STATUS_APPROVED));
		}

		return searchContainer;
	}

	public int getUnpublishedEntriesCount() {
		if (_unpublishedEntriesCount != null) {
			return _unpublishedEntriesCount;
		}

		_unpublishedEntriesCount =
			BlogsEntryServiceUtil.getGroupUserEntriesCount(
				_themeDisplay.getScopeGroupId(), _themeDisplay.getUserId(),
				new int[] {
					WorkflowConstants.STATUS_DRAFT,
					WorkflowConstants.STATUS_PENDING,
					WorkflowConstants.STATUS_SCHEDULED
				});

		return _unpublishedEntriesCount;
	}

	public boolean isAssetEntryQuery() {
		if ((_getAssetCategoryId() > 0) ||
			Validator.isNotNull(_getAssetTagName())) {

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

	private String _getMVCRenderCommandName() {
		if (_mvcRenderCommandName != null) {
			return _mvcRenderCommandName;
		}

		_mvcRenderCommandName = ParamUtil.getString(
			_httpServletRequest, "mvcRenderCommandName");

		return _mvcRenderCommandName;
	}

	private Long _assetCategoryId;
	private String _assetTagName;
	private BlogsPortletInstanceConfiguration
		_blogsPortletInstanceConfiguration;
	private final HttpServletRequest _httpServletRequest;
	private String _mvcRenderCommandName;
	private PortletURL _portletURL;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;
	private Integer _unpublishedEntriesCount;

}