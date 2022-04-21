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

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
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
 * @author Eudaldo Alonso
 */
public class DLSelectDDMStructureDisplayContext {

	public DLSelectDDMStructureDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
	}

	public long getDDMStructureId() {
		if (_ddmStructureId != null) {
			return _ddmStructureId;
		}

		_ddmStructureId = ParamUtil.getLong(_renderRequest, "ddmStructureId");

		return _ddmStructureId;
	}

	public SearchContainer<DDMStructure> getDDMStructureSearch()
		throws Exception {

		if (_ddmStructureSearch != null) {
			return _ddmStructureSearch;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		SearchContainer<DDMStructure> ddmStructureSearch = new SearchContainer(
			_renderRequest, _getPortletURL(), null, "there-are-no-results");

		if (Validator.isNotNull(_getKeywords())) {
			ddmStructureSearch.setEmptyResultsMessage("no-results-were-found");
		}

		ddmStructureSearch.setOrderByCol(getOrderByCol());
		ddmStructureSearch.setOrderByComparator(
			DDMUtil.getStructureOrderByComparator(
				getOrderByCol(), getOrderByType()));
		ddmStructureSearch.setOrderByType(getOrderByType());

		long[] groupIds = PortalUtil.getCurrentAndAncestorSiteGroupIds(
			themeDisplay.getScopeGroupId());

		if (_isSearchRestriction()) {
			ddmStructureSearch.setResultsAndTotal(
				() ->
					DDMStructureLinkLocalServiceUtil.getStructureLinkStructures(
						_getSearchRestrictionClassNameId(),
						_getSearchRestrictionClassPK(),
						ddmStructureSearch.getStart(),
						ddmStructureSearch.getEnd()),
				DDMStructureLinkLocalServiceUtil.getStructureLinksCount(
					_getSearchRestrictionClassNameId(),
					_getSearchRestrictionClassPK()));
		}
		else if (Validator.isNotNull(_getKeywords())) {
			ddmStructureSearch.setResultsAndTotal(
				() -> DDMStructureServiceUtil.search(
					themeDisplay.getCompanyId(), groupIds,
					PortalUtil.getClassNameId(
						DLFileEntryMetadata.class.getName()),
					_getKeywords(), WorkflowConstants.STATUS_ANY,
					ddmStructureSearch.getStart(), ddmStructureSearch.getEnd(),
					ddmStructureSearch.getOrderByComparator()),
				DDMStructureServiceUtil.searchCount(
					themeDisplay.getCompanyId(), groupIds,
					PortalUtil.getClassNameId(
						DLFileEntryMetadata.class.getName()),
					_getKeywords(), WorkflowConstants.STATUS_ANY));
		}
		else {
			ddmStructureSearch.setResultsAndTotal(
				() -> DDMStructureServiceUtil.getStructures(
					themeDisplay.getCompanyId(), groupIds,
					PortalUtil.getClassNameId(
						DLFileEntryMetadata.class.getName()),
					ddmStructureSearch.getStart(), ddmStructureSearch.getEnd(),
					ddmStructureSearch.getOrderByComparator()),
				DDMStructureServiceUtil.getStructuresCount(
					themeDisplay.getCompanyId(), groupIds,
					PortalUtil.getClassNameId(
						DLFileEntryMetadata.class.getName())));
		}

		_ddmStructureSearch = ddmStructureSearch;

		return ddmStructureSearch;
	}

	public String getEventName() {
		return _renderResponse.getNamespace() + "selectDDMStructure";
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = SearchOrderByUtil.getOrderByCol(
			_httpServletRequest, DLPortletKeys.DOCUMENT_LIBRARY,
			"ddm-structure-order-by-col", "modified-date");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = SearchOrderByUtil.getOrderByType(
			_httpServletRequest, DLPortletKeys.DOCUMENT_LIBRARY,
			"ddm-structure-order-by-type", "asc");

		return _orderByType;
	}

	public String getSortingURL() {
		return PortletURLBuilder.create(
			_getPortletURL()
		).setParameter(
			"orderByType",
			Objects.equals(getOrderByType(), "asc") ? "desc" : "asc"
		).buildString();
	}

	public boolean isSearch() {
		if (Validator.isNotNull(_getKeywords())) {
			return true;
		}

		return false;
	}

	private String _getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_renderRequest, "keywords");

		return _keywords;
	}

	private PortletURL _getPortletURL() {
		return PortletURLBuilder.createRenderURL(
			_renderResponse
		).setMVCPath(
			"/document_library/ddm/select_ddm_structure.jsp"
		).setKeywords(
			() -> {
				String keywords = _getKeywords();

				if (Validator.isNotNull(keywords)) {
					return keywords;
				}

				return null;
			}
		).setParameter(
			"ddmStructureId",
			() -> {
				long ddmStructureId = getDDMStructureId();

				if (ddmStructureId != 0) {
					return ddmStructureId;
				}

				return null;
			}
		).setParameter(
			"orderByCol",
			() -> {
				String orderByCol = getOrderByCol();

				if (Validator.isNotNull(orderByCol)) {
					return orderByCol;
				}

				return null;
			}
		).setParameter(
			"orderByType",
			() -> {
				String orderByType = getOrderByType();

				if (Validator.isNotNull(orderByType)) {
					return orderByType;
				}

				return null;
			}
		).buildPortletURL();
	}

	private long _getSearchRestrictionClassNameId() {
		if (_searchRestrictionClassNameId != null) {
			return _searchRestrictionClassNameId;
		}

		_searchRestrictionClassNameId = ParamUtil.getLong(
			_httpServletRequest, "searchRestrictionClassNameId");

		return _searchRestrictionClassNameId;
	}

	private long _getSearchRestrictionClassPK() {
		if (_searchRestrictionClassPK != null) {
			return _searchRestrictionClassPK;
		}

		_searchRestrictionClassPK = ParamUtil.getLong(
			_httpServletRequest, "searchRestrictionClassPK");

		return _searchRestrictionClassPK;
	}

	private boolean _isSearchRestriction() {
		if (_searchRestriction != null) {
			return _searchRestriction;
		}

		_searchRestriction = ParamUtil.getBoolean(
			_httpServletRequest, "searchRestriction");

		return _searchRestriction;
	}

	private Long _ddmStructureId;
	private SearchContainer<DDMStructure> _ddmStructureSearch;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private Boolean _searchRestriction;
	private Long _searchRestrictionClassNameId;
	private Long _searchRestrictionClassPK;

}