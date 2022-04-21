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

package com.liferay.account.admin.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.account.admin.web.internal.constants.AccountScreenNavigationEntryConstants;
import com.liferay.account.admin.web.internal.display.AccountGroupDisplay;
import com.liferay.account.admin.web.internal.security.permission.resource.AccountGroupPermission;
import com.liferay.account.constants.AccountActionKeys;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Lee
 */
@Component(
	property = {
		"screen.navigation.category.order:Integer=20",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class AccountGroupAccountEntriesScreenNavigationCategory
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<AccountGroupDisplay> {

	@Override
	public String getCategoryKey() {
		return AccountScreenNavigationEntryConstants.CATEGORY_KEY_ACCOUNTS;
	}

	@Override
	public String getEntryKey() {
		return AccountScreenNavigationEntryConstants.ENTRY_KEY_ACCOUNTS;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "accounts");
	}

	@Override
	public String getScreenNavigationKey() {
		return AccountScreenNavigationEntryConstants.
			SCREEN_NAVIGATION_KEY_ACCOUNT_GROUP;
	}

	@Override
	public boolean isVisible(
		User user, AccountGroupDisplay accountGroupDisplay) {

		if (accountGroupDisplay.getAccountGroupId() == 0) {
			return false;
		}

		return AccountGroupPermission.contains(
			PermissionCheckerFactoryUtil.create(user),
			accountGroupDisplay.getAccountGroupId(),
			AccountActionKeys.VIEW_ACCOUNTS);
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/account_groups_admin/account_group/view_account_entries.jsp");
	}

	@Reference
	protected JSPRenderer jspRenderer;

}