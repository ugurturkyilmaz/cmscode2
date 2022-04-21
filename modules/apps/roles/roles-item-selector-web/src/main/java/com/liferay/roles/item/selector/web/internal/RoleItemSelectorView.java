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
import com.liferay.roles.item.selector.RoleItemSelectorCriterion;
import com.liferay.roles.item.selector.web.internal.constants.RoleItemSelectorViewConstants;
import com.liferay.roles.item.selector.web.internal.display.context.RoleItemSelectorViewDisplayContext;
import com.liferay.roles.item.selector.web.internal.search.RoleItemSelectorChecker;
import com.liferay.users.admin.kernel.util.UsersAdmin;

import java.io.IOException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @deprecated As of Mueller (7.2.x), , with no direct replacement
 */
@Component(immediate = true, service = ItemSelectorView.class)
@Deprecated
public class RoleItemSelectorView
	implements ItemSelectorView<RoleItemSelectorCriterion> {

	@Override
	public Class<RoleItemSelectorCriterion> getItemSelectorCriterionClass() {
		return RoleItemSelectorCriterion.class;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return _language.get(_portal.getResourceBundle(locale), "roles");
	}

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse,
			RoleItemSelectorCriterion roleItemSelectorCriterion,
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
				httpServletRequest, roleItemSelectorCriterion,
				itemSelectedEventName,
				_getSearchContainer(
					renderRequest, renderResponse,
					roleItemSelectorCriterion.getCheckedRoleIds(),
					roleItemSelectorCriterion.getExcludedRoleNames(),
					roleItemSelectorCriterion.getType()),
				_portal.getLiferayPortletRequest(renderRequest),
				_portal.getLiferayPortletResponse(renderResponse));

		servletRequest.setAttribute(
			RoleItemSelectorViewConstants.
				ROLE_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT,
			roleItemSelectorViewDisplayContext);

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher("/role_item_selector.jsp");

		requestDispatcher.include(servletRequest, servletResponse);
	}

	private SearchContainer<Role> _getSearchContainer(
		RenderRequest renderRequest, RenderResponse renderResponse,
		long[] checkedRoleIds, String[] excludedRoleNames, int type) {

		PortletURL currentURL = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);

		SearchContainer<Role> searchContainer = new RoleSearch(
			renderRequest, currentURL);

		searchContainer.setEmptyResultsMessage("no-roles-were-found");
		searchContainer.setOrderByComparator(
			_usersAdmin.getRoleOrderByComparator(
				searchContainer.getOrderByCol(),
				searchContainer.getOrderByType()));

		RoleSearchTerms searchTerms =
			(RoleSearchTerms)searchContainer.getSearchTerms();

		searchTerms.setType(type);

		searchContainer.setResultsAndTotal(
			() -> _roleService.search(
				CompanyThreadLocal.getCompanyId(), searchTerms.getKeywords(),
				searchTerms.getTypesObj(), new LinkedHashMap<String, Object>(),
				searchContainer.getStart(), searchContainer.getEnd(),
				searchContainer.getOrderByComparator()),
			_roleService.searchCount(
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

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference
	private RoleService _roleService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.roles.item.selector.web)"
	)
	private ServletContext _servletContext;

	@Reference
	private UsersAdmin _usersAdmin;

}