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

package com.liferay.commerce.availability.estimate.web.internal.display.context;

import com.liferay.commerce.availability.estimate.web.internal.util.CommerceAvailabilityEstimateUtil;
import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.service.CommerceAvailabilityEstimateService;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAvailabilityEstimateDisplayContext {

	public CommerceAvailabilityEstimateDisplayContext(
		CommerceAvailabilityEstimateService commerceAvailabilityEstimateService,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_commerceAvailabilityEstimateService =
			commerceAvailabilityEstimateService;
		_portletResourcePermission = portletResourcePermission;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
	}

	public CommerceAvailabilityEstimate getCommerceAvailabilityEstimate()
		throws PortalException {

		if (_commerceAvailabilityEstimate != null) {
			return _commerceAvailabilityEstimate;
		}

		long commerceAvailabilityEstimateId = ParamUtil.getLong(
			_renderRequest, "commerceAvailabilityEstimateId");

		if (commerceAvailabilityEstimateId > 0) {
			_commerceAvailabilityEstimate =
				_commerceAvailabilityEstimateService.
					getCommerceAvailabilityEstimate(
						commerceAvailabilityEstimateId);
		}

		return _commerceAvailabilityEstimate;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = SearchOrderByUtil.getOrderByCol(
			_renderRequest, CommercePortletKeys.COMMERCE_AVAILABILITY_ESTIMATE,
			"priority");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = SearchOrderByUtil.getOrderByType(
			_renderRequest, CommercePortletKeys.COMMERCE_AVAILABILITY_ESTIMATE,
			"asc");

		return _orderByType;
	}

	public PortletURL getPortletURL() {
		return PortletURLBuilder.createRenderURL(
			_renderResponse
		).setParameter(
			"orderByCol", getOrderByCol()
		).setParameter(
			"orderByType", getOrderByType()
		).buildPortletURL();
	}

	public SearchContainer<CommerceAvailabilityEstimate> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_searchContainer = new SearchContainer<>(
			_renderRequest, getPortletURL(), null,
			"there-are-no-availability-estimates");

		_searchContainer.setOrderByCol(getOrderByCol());
		_searchContainer.setOrderByComparator(
			CommerceAvailabilityEstimateUtil.
				getCommerceAvailabilityEstimateOrderByComparator(
					getOrderByCol(), getOrderByType()));
		_searchContainer.setOrderByType(getOrderByType());
		_searchContainer.setResultsAndTotal(
			() ->
				_commerceAvailabilityEstimateService.
					getCommerceAvailabilityEstimates(
						themeDisplay.getCompanyId(),
						_searchContainer.getStart(), _searchContainer.getEnd(),
						_searchContainer.getOrderByComparator()),
			_commerceAvailabilityEstimateService.
				getCommerceAvailabilityEstimatesCount(
					themeDisplay.getCompanyId()));
		_searchContainer.setRowChecker(_getRowChecker());

		return _searchContainer;
	}

	public boolean hasManageCommerceAvailabilityEstimatesPermission() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), null,
			CommerceActionKeys.MANAGE_COMMERCE_AVAILABILITY_ESTIMATES);
	}

	private RowChecker _getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(_renderResponse);
		}

		return _rowChecker;
	}

	private CommerceAvailabilityEstimate _commerceAvailabilityEstimate;
	private final CommerceAvailabilityEstimateService
		_commerceAvailabilityEstimateService;
	private String _orderByCol;
	private String _orderByType;
	private final PortletResourcePermission _portletResourcePermission;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private RowChecker _rowChecker;
	private SearchContainer<CommerceAvailabilityEstimate> _searchContainer;

}