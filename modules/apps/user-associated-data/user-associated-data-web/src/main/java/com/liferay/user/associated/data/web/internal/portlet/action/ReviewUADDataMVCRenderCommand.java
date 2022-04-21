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

package com.liferay.user.associated.data.web.internal.portlet.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.user.associated.data.constants.UserAssociatedDataPortletKeys;
import com.liferay.user.associated.data.display.UADDisplay;
import com.liferay.user.associated.data.web.internal.constants.UADConstants;
import com.liferay.user.associated.data.web.internal.constants.UADWebKeys;
import com.liferay.user.associated.data.web.internal.dao.search.UADHierarchyResultRowSplitter;
import com.liferay.user.associated.data.web.internal.display.ScopeDisplay;
import com.liferay.user.associated.data.web.internal.display.UADApplicationSummaryDisplay;
import com.liferay.user.associated.data.web.internal.display.UADEntity;
import com.liferay.user.associated.data.web.internal.display.UADHierarchyDisplay;
import com.liferay.user.associated.data.web.internal.display.UADInfoPanelDisplay;
import com.liferay.user.associated.data.web.internal.display.ViewUADEntitiesDisplay;
import com.liferay.user.associated.data.web.internal.helper.SelectedUserHelper;
import com.liferay.user.associated.data.web.internal.helper.UADApplicationSummaryHelper;
import com.liferay.user.associated.data.web.internal.registry.UADRegistry;
import com.liferay.user.associated.data.web.internal.util.GroupUtil;
import com.liferay.user.associated.data.web.internal.util.UADSearchContainerBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pei-Jung Lan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + UserAssociatedDataPortletKeys.USER_ASSOCIATED_DATA,
		"mvc.command.name=/user_associated_data/review_uad_data"
	},
	service = MVCRenderCommand.class
)
public class ReviewUADDataMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			User user = _userHelper.getSelectedUser(renderRequest);

			List<ScopeDisplay> scopeDisplays = _getScopeDisplays(user);

			ScopeDisplay scopeDisplay = _getScopeDisplay(
				renderRequest, scopeDisplays);

			String applicationKey = _getApplicationKey(
				renderRequest, scopeDisplay);

			UADHierarchyDisplay uadHierarchyDisplay =
				_uadRegistry.getUADHierarchyDisplay(applicationKey);

			if (uadHierarchyDisplay == null) {
				renderRequest.setAttribute(
					UADWebKeys.APPLICATION_UAD_DISPLAYS,
					_uadRegistry.getApplicationUADDisplays(applicationKey));
			}

			renderRequest.setAttribute(
				UADWebKeys.SCOPE_DISPLAYS, scopeDisplays);
			renderRequest.setAttribute(
				UADWebKeys.TOTAL_UAD_ENTITIES_COUNT,
				_getTotalUADEntitiesCount(user));
			renderRequest.setAttribute(
				UADWebKeys.UAD_APPLICATION_SUMMARY_DISPLAY_LIST,
				scopeDisplay.getUADApplicationSummaryDisplays());

			if (uadHierarchyDisplay != null) {
				renderRequest.setAttribute(
					UADWebKeys.UAD_HIERARCHY_DISPLAY, uadHierarchyDisplay);
			}

			String uadRegistryKey = _getUADRegistryKey(
				applicationKey, renderRequest);

			UADDisplay<?> uadDisplay = _uadRegistry.getUADDisplay(
				uadRegistryKey);

			renderRequest.setAttribute(
				UADWebKeys.UAD_INFO_PANEL_DISPLAY,
				_getUADInfoPanelDisplay(uadDisplay, uadHierarchyDisplay));
			renderRequest.setAttribute(
				UADWebKeys.VIEW_UAD_ENTITIES_DISPLAY,
				_getViewUADEntitiesDisplay(
					applicationKey, renderRequest, renderResponse, scopeDisplay,
					user, uadDisplay, uadHierarchyDisplay, uadRegistryKey));
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}

		return "/review_uad_data.jsp";
	}

	private String _getApplicationKey(
		RenderRequest renderRequest, ScopeDisplay scopeDisplay) {

		String applicationKey = ParamUtil.getString(
			renderRequest, "applicationKey");

		if (Validator.isNull(applicationKey)) {
			return scopeDisplay.getApplicationKey();
		}

		for (UADApplicationSummaryDisplay uadApplicationSummaryDisplay :
				scopeDisplay.getUADApplicationSummaryDisplays()) {

			if (applicationKey.equals(
					uadApplicationSummaryDisplay.getApplicationKey()) &&
				!uadApplicationSummaryDisplay.hasItems()) {

				return scopeDisplay.getApplicationKey();
			}
		}

		return applicationKey;
	}

	private ScopeDisplay _getScopeDisplay(
		RenderRequest renderRequest, List<ScopeDisplay> scopeDisplays) {

		ScopeDisplay scopeDisplay = null;

		String scope = ParamUtil.getString(
			renderRequest, "scope", UADConstants.SCOPE_PERSONAL_SITE);

		for (ScopeDisplay curScopeDisplay : scopeDisplays) {
			if (scope.equals(curScopeDisplay.getScopeName())) {
				scopeDisplay = curScopeDisplay;
			}
		}

		if (!scopeDisplay.hasItems()) {
			for (ScopeDisplay curScopeDisplay : scopeDisplays) {
				if (curScopeDisplay.hasItems()) {
					scopeDisplay = curScopeDisplay;
				}
			}
		}

		scopeDisplay.setActive(true);

		return scopeDisplay;
	}

	private List<ScopeDisplay> _getScopeDisplays(User user) {
		List<ScopeDisplay> scopeDisplays = new ArrayList<>();

		for (String scope : UADConstants.SCOPES) {
			long[] groupIds = GroupUtil.getGroupIds(
				_groupLocalService, user, scope);

			ScopeDisplay scopeDisplay = new ScopeDisplay(
				scope, groupIds,
				_uadApplicationSummaryHelper.getUADApplicationSummaryDisplays(
					user.getUserId(), groupIds));

			scopeDisplays.add(scopeDisplay);
		}

		return scopeDisplays;
	}

	private SearchContainer<UADEntity<?>> _getSearchContainer(
			String applicationKey, RenderRequest renderRequest,
			RenderResponse renderResponse, ScopeDisplay scopeDisplay,
			UADDisplay<Object> uadDisplay,
			UADHierarchyDisplay uadHierarchyDisplay, User user)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(renderResponse);
		PortletURL currentURL = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);

		if (applicationKey.equals(UADConstants.ALL_APPLICATIONS)) {
			return _uadSearchContainerBuilder.
				getApplicationSummaryUADEntitySearchContainer(
					liferayPortletResponse, renderRequest, currentURL,
					scopeDisplay.getUADApplicationSummaryDisplays());
		}

		if (uadHierarchyDisplay != null) {
			return _uadSearchContainerBuilder.
				getHierarchyUADEntitySearchContainer(
					liferayPortletResponse, renderRequest, applicationKey,
					currentURL, scopeDisplay.getGroupIds(),
					uadHierarchyDisplay.getFirstContainerTypeClass(), 0L, user,
					uadHierarchyDisplay);
		}

		return _uadSearchContainerBuilder.getUADEntitySearchContainer(
			liferayPortletResponse, renderRequest, currentURL,
			scopeDisplay.getGroupIds(), user, uadDisplay);
	}

	private int _getTotalUADEntitiesCount(User user) {
		return _uadApplicationSummaryHelper.getTotalReviewableUADEntitiesCount(
			user.getUserId());
	}

	private UADInfoPanelDisplay _getUADInfoPanelDisplay(
		UADDisplay<?> uadDisplay, UADHierarchyDisplay uadHierarchyDisplay) {

		UADInfoPanelDisplay uadInfoPanelDisplay = new UADInfoPanelDisplay();

		if (uadHierarchyDisplay != null) {
			uadInfoPanelDisplay.setHierarchyView(true);
			uadInfoPanelDisplay.setUADDisplay(
				(UADDisplay<Object>)uadHierarchyDisplay.getUADDisplays()[0]);
		}
		else {
			uadInfoPanelDisplay.setUADDisplay((UADDisplay<Object>)uadDisplay);
		}

		return uadInfoPanelDisplay;
	}

	private String _getUADRegistryKey(
		String applicationKey, RenderRequest renderRequest) {

		String uadRegistryKey = ParamUtil.getString(
			renderRequest, "uadRegistryKey");

		if (Validator.isNull(uadRegistryKey)) {
			uadRegistryKey =
				_uadApplicationSummaryHelper.getDefaultUADRegistryKey(
					applicationKey);
		}

		return uadRegistryKey;
	}

	private ViewUADEntitiesDisplay _getViewUADEntitiesDisplay(
			String applicationKey, RenderRequest renderRequest,
			RenderResponse renderResponse, ScopeDisplay scopeDisplay, User user,
			UADDisplay<?> uadDisplay, UADHierarchyDisplay uadHierarchyDisplay,
			String uadRegistryKey)
		throws Exception {

		ViewUADEntitiesDisplay viewUADEntitiesDisplay =
			new ViewUADEntitiesDisplay();

		viewUADEntitiesDisplay.setApplicationKey(applicationKey);
		viewUADEntitiesDisplay.setGroupIds(scopeDisplay.getGroupIds());
		viewUADEntitiesDisplay.setScope(scopeDisplay.getScopeName());
		viewUADEntitiesDisplay.setSearchContainer(
			_getSearchContainer(
				applicationKey, renderRequest, renderResponse, scopeDisplay,
				(UADDisplay<Object>)uadDisplay, uadHierarchyDisplay, user));

		if (uadHierarchyDisplay != null) {
			viewUADEntitiesDisplay.setHierarchy(true);
			viewUADEntitiesDisplay.setResultRowSplitter(
				new UADHierarchyResultRowSplitter(
					LocaleThreadLocal.getThemeDisplayLocale(),
					uadHierarchyDisplay.getUADDisplays()));
			viewUADEntitiesDisplay.setTypeClasses(
				uadHierarchyDisplay.getTypeClasses());
		}
		else {
			viewUADEntitiesDisplay.setTypeClasses(
				new Class<?>[] {uadDisplay.getTypeClass()});
			viewUADEntitiesDisplay.setTypeName(
				uadDisplay.getTypeName(
					LocaleThreadLocal.getThemeDisplayLocale()));
			viewUADEntitiesDisplay.setUADRegistryKey(uadRegistryKey);
		}

		return viewUADEntitiesDisplay;
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UADApplicationSummaryHelper _uadApplicationSummaryHelper;

	@Reference
	private UADRegistry _uadRegistry;

	@Reference
	private UADSearchContainerBuilder _uadSearchContainerBuilder;

	@Reference
	private SelectedUserHelper _userHelper;

}