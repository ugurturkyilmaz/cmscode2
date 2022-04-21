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

package com.liferay.roles.item.selector.web.internal;

import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portlet.rolesadmin.search.RoleSearch;
import com.liferay.portlet.rolesadmin.search.RoleSearchTerms;
import com.liferay.roles.item.selector.web.internal.constants.RoleItemSelectorViewConstants;
import com.liferay.roles.item.selector.web.internal.display.context.RoleItemSelectorViewDisplayContext;
import com.liferay.roles.item.selector.web.internal.search.RoleItemSelectorChecker;
import com.liferay.users.admin.kernel.util.UsersAdmin;

import java.io.IOException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Roberto Díaz
 */
public abstract class BaseRoleItemSelectorView<T extends ItemSelectorCriterion>
	implements ItemSelectorView<T> {

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	public abstract int getType();

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse, T t,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)servletRequest;

		RenderRequest renderRequest =
			(RenderRequest)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);
		RenderResponse renderResponse =
			(RenderResponse)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		RoleItemSelectorViewDisplayContext roleItemSelectorViewDisplayContext =
			new RoleItemSelectorViewDisplayContext(
				httpServletRequest, t, itemSelectedEventName,
				_getSearchContainer(
					renderRequest, renderResponse, getCheckedRoleIds(t),
					getExcludedRoleNames(t), getType()),
				portal.getLiferayPortletRequest(renderRequest),
				portal.getLiferayPortletResponse(renderResponse));

		servletRequest.setAttribute(
			RoleItemSelectorViewConstants.
				ROLE_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT,
			roleItemSelectorViewDisplayContext);

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher("/role_item_selector.jsp");

		requestDispatcher.include(servletRequest, servletResponse);
	}

	protected abstract long[] getCheckedRoleIds(T t);

	protected abstract String[] getExcludedRoleNames(T t);

	@Reference
	protected Language language;

	@Reference
	protected Portal portal;

	@Reference
	protected RoleService roleService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.roles.item.selector.web)"
	)
	protected ServletContext servletContext;

	@Reference
	protected UsersAdmin usersAdmin;

	private SearchContainer<Role> _getSearchContainer(
		RenderRequest renderRequest, RenderResponse renderResponse,
		long[] checkedRoleIds, String[] excludedRoleNames, int type) {

		PortletURL currentURL = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);

		SearchContainer<Role> searchContainer = new RoleSearch(
			renderRequest, currentURL);

		searchContainer.setEmptyResultsMessage("no-roles-were-found");
		searchContainer.setOrderByComparator(
			usersAdmin.getRoleOrderByComparator(
				searchContainer.getOrderByCol(),
				searchContainer.getOrderByType()));

		RoleSearchTerms searchTerms =
			(RoleSearchTerms)searchContainer.getSearchTerms();

		searchTerms.setType(type);

		searchContainer.setResultsAndTotal(
			() -> roleService.search(
				CompanyThreadLocal.getCompanyId(), searchTerms.getKeywords(),
				searchTerms.getTypesObj(), new LinkedHashMap<String, Object>(),
				searchContainer.getStart(), searchContainer.getEnd(),
				searchContainer.getOrderByComparator()),
			roleService.searchCount(
				CompanyThreadLocal.getCompanyId(), searchTerms.getKeywords(),
				searchTerms.getTypesObj(),
				new LinkedHashMap<String, Object>()));

		searchContainer.setRowChecker(
			new RoleItemSelectorChecker(
				renderResponse, checkedRoleIds, excludedRoleNames));

		return searchContainer;
	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.singletonList(
			new UUIDItemSelectorReturnType());

}