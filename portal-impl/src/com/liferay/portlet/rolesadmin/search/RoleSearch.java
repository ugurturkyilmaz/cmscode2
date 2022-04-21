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

package com.liferay.portlet.rolesadmin.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class RoleSearch extends SearchContainer<Role> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-roles-were-found";

	public static List<String> headerNames = new ArrayList<String>() {
		{
			add("title");
			add("type");

			if ((PropsValues.ROLES_ORGANIZATION_SUBTYPES.length > 0) ||
				(PropsValues.ROLES_REGULAR_SUBTYPES.length > 0) ||
				(PropsValues.ROLES_SITE_SUBTYPES.length > 0)) {

				add("subtype");
			}

			add("description");
		}
	};

	public static Map<String, String> orderableHeaders = HashMapBuilder.put(
		"description", "description"
	).put(
		"title", "title"
	).put(
		"type", "type"
	).build();

	public RoleSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new RoleDisplayTerms(portletRequest),
			new RoleSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		RoleDisplayTerms displayTerms = (RoleDisplayTerms)getDisplayTerms();

		if (ParamUtil.getInteger(portletRequest, "type") == 0) {
			displayTerms.setType(RoleConstants.TYPE_REGULAR);

			RoleSearchTerms searchTerms = (RoleSearchTerms)getSearchTerms();

			searchTerms.setType(RoleConstants.TYPE_REGULAR);
		}

		iteratorURL.setParameter(
			RoleDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(RoleDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			RoleDisplayTerms.TYPE, String.valueOf(displayTerms.getType()));

		try {
			setOrderableHeaders(orderableHeaders);

			String portletId = PortletProviderUtil.getPortletId(
				Role.class.getName(), PortletProvider.Action.BROWSE);

			String orderByCol = SearchOrderByUtil.getOrderByCol(
				portletRequest, portletId, "roles-order-by-col", "title");

			setOrderByCol(orderByCol);

			String orderByType = SearchOrderByUtil.getOrderByType(
				portletRequest, portletId, "roles-order-by-type", "asc");

			setOrderByComparator(
				UsersAdminUtil.getRoleOrderByComparator(
					orderByCol, orderByType));
			setOrderByType(orderByType);
		}
		catch (Exception exception) {
			_log.error("Unable to initialize role search", exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(RoleSearch.class);

}