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

package com.liferay.dispatch.web.internal.dao.search;

import com.liferay.dispatch.constants.DispatchPortletKeys;
import com.liferay.dispatch.model.DispatchLog;
import com.liferay.dispatch.service.DispatchLogServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Objects;

/**
 * @author Igor Beslic
 */
public class DispatchLogSearchContainerFactory {

	public static SearchContainer<DispatchLog> create(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		SearchContainer<DispatchLog> dispatchLogSearchContainer =
			new SearchContainer(
				liferayPortletRequest,
				PortletURLUtil.getCurrent(
					liferayPortletRequest, liferayPortletResponse),
				null, "no-dispatch-logs-were-found");

		dispatchLogSearchContainer.setId("dispatchLogs");
		dispatchLogSearchContainer.setOrderByCol(
			_getColumnName(
				SearchOrderByUtil.getOrderByCol(
					liferayPortletRequest, DispatchPortletKeys.DISPATCH,
					"modified-date")));
		dispatchLogSearchContainer.setOrderByType(
			SearchOrderByUtil.getOrderByType(
				liferayPortletRequest, DispatchPortletKeys.DISPATCH, "desc"));

		long dispatchTriggerId = ParamUtil.getLong(
			liferayPortletRequest, "dispatchTriggerId");

		dispatchLogSearchContainer.setResultsAndTotal(
			() -> DispatchLogServiceUtil.getDispatchLogs(
				dispatchTriggerId, dispatchLogSearchContainer.getStart(),
				dispatchLogSearchContainer.getEnd(),
				_getOrderByComparator(
					dispatchLogSearchContainer.getOrderByCol(),
					_isAscending(dispatchLogSearchContainer.getOrderByType()))),
			DispatchLogServiceUtil.getDispatchLogsCount(dispatchTriggerId));

		dispatchLogSearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(liferayPortletResponse));

		return dispatchLogSearchContainer;
	}

	private static String _getColumnName(String columnKey) {
		String[] tokens = columnKey.split("-");

		if (tokens.length <= 1) {
			return columnKey;
		}

		StringBundler sb = new StringBundler(tokens.length);

		sb.append(tokens[0]);

		for (int i = 1; i < tokens.length; i++) {
			sb.append(StringUtil.upperCaseFirstLetter(tokens[i]));
		}

		return sb.toString();
	}

	private static OrderByComparator<DispatchLog> _getOrderByComparator(
		String orderByColumn, boolean ascending) {

		return OrderByComparatorFactoryUtil.create(
			"DispatchLog", orderByColumn, String.valueOf(ascending));
	}

	private static boolean _isAscending(String orderByType) {
		if (Objects.equals(orderByType, "desc")) {
			return false;
		}

		return true;
	}

}