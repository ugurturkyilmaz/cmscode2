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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mariano Álvaro Sáiz
 */
public class KBViewPrpArticlesDisplayContext {

	public KBViewPrpArticlesDisplayContext(
			HttpServletRequest httpServletRequest, PortletURL iteratorURL)
		throws PortalException {

		_httpServletRequest = httpServletRequest;
		_iteratorURL = iteratorURL;

		_portletRequest = (PortletRequest)_httpServletRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
	}

	public long getAssetCategoryId() {
		if (_assetCategoryId != null) {
			return _assetCategoryId;
		}

		_assetCategoryId = ParamUtil.getLong(_httpServletRequest, "categoryId");

		return _assetCategoryId;
	}

	public String getAssetTagName() {
		if (_assetTagName != null) {
			return _assetTagName;
		}

		_assetTagName = ParamUtil.getString(_httpServletRequest, "tag");

		return _assetTagName;
	}

	public SearchContainer<AssetEntry> getSearchContainer() throws Exception {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_portletRequest, _iteratorURL, null, null);

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery(
			KBArticle.class.getName(), _searchContainer);

		_searchContainer.setResultsAndTotal(
			() -> {
				assetEntryQuery.setEnd(_searchContainer.getEnd());
				assetEntryQuery.setStart(_searchContainer.getStart());

				return AssetEntryServiceUtil.getEntries(assetEntryQuery);
			},
			AssetEntryServiceUtil.getEntriesCount(assetEntryQuery));

		return _searchContainer;
	}

	private Long _assetCategoryId;
	private String _assetTagName;
	private final HttpServletRequest _httpServletRequest;
	private final PortletURL _iteratorURL;
	private final PortletRequest _portletRequest;
	private SearchContainer<AssetEntry> _searchContainer;

}