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

package com.liferay.account.admin.web.internal.display.context;

import com.liferay.account.admin.web.internal.display.AccountEntryDisplay;
import com.liferay.account.admin.web.internal.display.AddressDisplay;
import com.liferay.account.admin.web.internal.security.permission.resource.AccountEntryPermission;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenuBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemListBuilder;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pei-Jung Lan
 */
public class ViewAccountEntryAddressesManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewAccountEntryAddressesManagementToolbarDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		SearchContainer<AddressDisplay> searchContainer) {

		super(
			httpServletRequest, liferayPortletRequest, liferayPortletResponse,
			searchContainer);
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		return DropdownItemList.of(
			DropdownItemBuilder.putData(
				"action", "deleteAccountEntryAddresses"
			).putData(
				"deleteAccountEntryAddressesURL",
				PortletURLBuilder.createActionURL(
					liferayPortletResponse
				).setActionName(
					"/account_admin/delete_account_entry_addresses"
				).setRedirect(
					currentURLObj
				).setParameter(
					"accountEntryId",
					ParamUtil.getLong(liferayPortletRequest, "accountEntryId")
				).buildString()
			).setIcon(
				"times-circle"
			).setLabel(
				LanguageUtil.get(httpServletRequest, "delete")
			).setQuickAction(
				true
			).build());
	}

	public List<String> getAvailableActions(
			AccountEntryDisplay accountEntryDisplay)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (AccountEntryPermission.contains(
				themeDisplay.getPermissionChecker(),
				accountEntryDisplay.getAccountEntryId(), ActionKeys.UPDATE)) {

			return Collections.<String>singletonList(
				"deleteAccountEntryAddresses");
		}

		return Collections.<String>emptyList();
	}

	@Override
	public String getClearResultsURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setKeywords(
			StringPool.BLANK
		).setParameter(
			"type", (String)null
		).buildString();
	}

	@Override
	public String getComponentId() {
		return "accountEntryAddressesManagementToolbar";
	}

	@Override
	public CreationMenu getCreationMenu() {
		return CreationMenuBuilder.addPrimaryDropdownItem(
			dropdownItem -> {
				dropdownItem.setHref(
					liferayPortletResponse.createRenderURL(),
					"mvcRenderCommandName",
					"/account_admin/edit_account_entry_address", "backURL",
					currentURLObj.toString(), "accountEntryId",
					ParamUtil.getLong(liferayPortletRequest, "accountEntryId"));
				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "add-address"));
			}
		).build();
	}

	@Override
	public List<LabelItem> getFilterLabelItems() {
		return LabelItemListBuilder.add(
			() -> !Objects.equals(getNavigation(), "all"),
			labelItem -> {
				labelItem.putData(
					"removeLabelURL",
					PortletURLBuilder.create(
						getPortletURL()
					).setParameter(
						"type", (String)null
					).buildString());
				labelItem.setDismissible(true);
				labelItem.setLabel(
					String.format(
						"%s: %s", LanguageUtil.get(httpServletRequest, "type"),
						LanguageUtil.get(httpServletRequest, getNavigation())));
			}
		).build();
	}

	@Override
	public PortletURL getPortletURL() {
		try {
			return PortletURLUtil.clone(currentURLObj, liferayPortletResponse);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception);
			}

			return liferayPortletResponse.createRenderURL();
		}
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public Boolean isDisabled() {
		return false;
	}

	@Override
	public Boolean isShowCreationMenu() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return AccountEntryPermission.contains(
			themeDisplay.getPermissionChecker(),
			ParamUtil.getLong(liferayPortletRequest, "accountEntryId"),
			ActionKeys.UPDATE);
	}

	@Override
	protected String getFilterNavigationDropdownItemsLabel() {
		return LanguageUtil.get(httpServletRequest, "filter-by-type");
	}

	@Override
	protected String getNavigation() {
		return ParamUtil.getString(
			liferayPortletRequest, getNavigationParam(), "all");
	}

	@Override
	protected String[] getNavigationKeys() {
		return new String[] {"all", "billing", "shipping"};
	}

	@Override
	protected String getNavigationParam() {
		return "type";
	}

	@Override
	protected String getOrderByCol() {
		return ParamUtil.getString(
			liferayPortletRequest, getOrderByColParam(), "name");
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"name"};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewAccountEntryAddressesManagementToolbarDisplayContext.class);

}