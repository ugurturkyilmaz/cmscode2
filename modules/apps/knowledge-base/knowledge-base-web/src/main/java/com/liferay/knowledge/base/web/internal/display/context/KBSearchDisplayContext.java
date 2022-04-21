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

import com.liferay.knowledge.base.constants.KBPortletKeys;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.web.internal.KBUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mariano Álvaro Sáiz
 */
public class KBSearchDisplayContext {

	public KBSearchDisplayContext(
			HttpServletRequest httpServletRequest, PortletURL iteratorURL)
		throws PortalException {

		_httpServletRequest = httpServletRequest;
		_iteratorURL = iteratorURL;

		_portletRequest = (PortletRequest)_httpServletRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		return _keywords;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = SearchOrderByUtil.getOrderByCol(
			_httpServletRequest, KBPortletKeys.KNOWLEDGE_BASE_SEARCH, "score");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = SearchOrderByUtil.getOrderByType(
			_httpServletRequest, KBPortletKeys.KNOWLEDGE_BASE_SEARCH, "desc");

		return _orderByType;
	}

	public SearchContainer<Tuple> getSearchContainer() throws Exception {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		SearchContext searchContext = SearchContextFactory.getInstance(
			_httpServletRequest);

		searchContext.setAttribute("paginationType", "regular");
		searchContext.setEnd(_searchContainer.getEnd());
		searchContext.setIncludeInternalAssetCategories(true);
		searchContext.setKeywords(getKeywords());
		searchContext.setSorts(
			KBUtil.getKBArticleSorts(getOrderByCol(), getOrderByType()));
		searchContext.setStart(_searchContainer.getStart());

		Indexer<KBArticle> indexer = IndexerRegistryUtil.getIndexer(
			KBArticle.class);

		Hits hits = indexer.search(searchContext);

		List<Tuple> tuples = new ArrayList<>();

		Document[] documents = hits.getDocs();

		for (int i = 0; i < documents.length; i++) {
			Document document = hits.doc(i);

			tuples.add(
				new Tuple(
					new Object[] {
						document.get(Field.ENTRY_CLASS_PK),
						document.get(Field.TITLE),
						PortalUtil.getUserName(
							GetterUtil.getLong(document.get(Field.USER_ID)),
							document.get(Field.USER_NAME)),
						document.getDate(Field.CREATE_DATE),
						document.getDate(Field.MODIFIED_DATE)
					}));
		}

		_searchContainer = new SearchContainer<>(
			_portletRequest, _iteratorURL, null,
			LanguageUtil.format(
				_httpServletRequest,
				"no-articles-were-found-that-matched-the-keywords-x",
				"<strong>" + HtmlUtil.escape(getKeywords()) + "</strong>",
				false));

		_searchContainer.setResultsAndTotal(() -> tuples, hits.getLength());

		return _searchContainer;
	}

	private final HttpServletRequest _httpServletRequest;
	private final PortletURL _iteratorURL;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final PortletRequest _portletRequest;
	private SearchContainer<Tuple> _searchContainer;

}