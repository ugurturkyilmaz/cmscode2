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

package com.liferay.site.my.sites.web.internal.servlet.taglib.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicyUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Objects;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class SiteActionDropdownItemsProvider {

	public SiteActionDropdownItemsProvider(
		Group group, RenderRequest renderRequest, RenderResponse renderResponse,
		String tabs1) {

		_group = group;
		_renderResponse = renderResponse;
		_tabs1 = tabs1;

		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<DropdownItem> getActionDropdownItems() throws Exception {
		return new DropdownItemList() {
			{
				if (Objects.equals(_tabs1, "my-sites")) {
					int count = LayoutServiceUtil.getLayoutsCount(
						_group.getGroupId(), false);

					if (count > 0) {
						add(_getViewSitePublicPagesActionUnsafeConsumer());
					}

					count = LayoutServiceUtil.getLayoutsCount(
						_group.getGroupId(), true);

					if (count > 0) {
						add(_getViewSitePrivatePagesActionUnsafeConsumer());
					}

					if (_isShowLeaveAction()) {
						add(_getLeaveSiteActionUnsafeConsumer());
					}
				}
				else if (_group.isManualMembership()) {
					if (!GroupLocalServiceUtil.hasUserGroup(
							_themeDisplay.getUserId(), _group.getGroupId()) &&
						SiteMembershipPolicyUtil.isMembershipAllowed(
							_themeDisplay.getUserId(), _group.getGroupId())) {

						if (_group.getType() == GroupConstants.TYPE_SITE_OPEN) {
							add(_getJoinSiteActionUnsafeConsumer());
						}

						if (_isShowMembershipRequestAction()) {
							add(
								_getSiteMembershipRequestActionUnsafeConsumer());
						}

						if (_isShowMembershipRequestedAction()) {
							add(
								_getSiteMembershipRequestedActionUnsafeConsumer());
						}
					}
					else if (_isShowLeaveAction()) {
						add(_getLeaveSiteActionUnsafeConsumer());
					}
				}
			}
		};
	}

	private UnsafeConsumer<DropdownItem, Exception>
		_getJoinSiteActionUnsafeConsumer() {

		return dropdownItem -> {
			dropdownItem.putData("action", "joinSite");
			dropdownItem.putData(
				"joinSiteURL",
				PortletURLBuilder.createActionURL(
					_renderResponse
				).setActionName(
					"updateGroupUsers"
				).setRedirect(
					_themeDisplay.getURLCurrent()
				).setParameter(
					"addUserIds", _themeDisplay.getUserId()
				).setParameter(
					"groupId", _group.getGroupId()
				).buildString());
			dropdownItem.setLabel(
				LanguageUtil.get(_httpServletRequest, "join"));
		};
	}

	private UnsafeConsumer<DropdownItem, Exception>
		_getLeaveSiteActionUnsafeConsumer() {

		return dropdownItem -> {
			dropdownItem.putData("action", "leaveSite");
			dropdownItem.putData(
				"leaveSiteURL",
				PortletURLBuilder.createActionURL(
					_renderResponse
				).setActionName(
					"updateGroupUsers"
				).setRedirect(
					_themeDisplay.getURLCurrent()
				).setParameter(
					"groupId", _group.getGroupId()
				).setParameter(
					"removeUserIds", _themeDisplay.getUserId()
				).buildString());
			dropdownItem.setLabel(
				LanguageUtil.get(_httpServletRequest, "leave"));
		};
	}

	private UnsafeConsumer<DropdownItem, Exception>
		_getSiteMembershipRequestActionUnsafeConsumer() {

		return dropdownItem -> {
			dropdownItem.setHref(
				_renderResponse.createRenderURL(), "mvcPath",
				"/post_membership_request.jsp", "groupId", _group.getGroupId());
			dropdownItem.setLabel(
				LanguageUtil.get(_httpServletRequest, "request-membership"));
		};
	}

	private UnsafeConsumer<DropdownItem, Exception>
		_getSiteMembershipRequestedActionUnsafeConsumer() {

		return dropdownItem -> {
			dropdownItem.putData("action", "membershipRequested");
			dropdownItem.setLabel(
				LanguageUtil.get(_httpServletRequest, "membership-requested"));
		};
	}

	private UnsafeConsumer<DropdownItem, Exception>
		_getViewSitePrivatePagesActionUnsafeConsumer() {

		return dropdownItem -> {
			dropdownItem.setHref(_group.getDisplayURL(_themeDisplay, true));
			dropdownItem.setLabel(
				LanguageUtil.format(
					_httpServletRequest, "go-to-x",
					_group.getLayoutRootNodeName(
						true, _themeDisplay.getLocale())));
			dropdownItem.setTarget("_blank");
		};
	}

	private UnsafeConsumer<DropdownItem, Exception>
		_getViewSitePublicPagesActionUnsafeConsumer() {

		return dropdownItem -> {
			dropdownItem.setHref(_group.getDisplayURL(_themeDisplay, false));
			dropdownItem.setLabel(
				LanguageUtil.format(
					_httpServletRequest, "go-to-x",
					_group.getLayoutRootNodeName(
						false, _themeDisplay.getLocale())));
			dropdownItem.setTarget("_blank");
		};
	}

	private boolean _isShowLeaveAction() throws Exception {
		if (((_group.getType() != GroupConstants.TYPE_SITE_OPEN) &&
			 (_group.getType() != GroupConstants.TYPE_SITE_RESTRICTED)) ||
			!GroupLocalServiceUtil.hasUserGroup(
				_themeDisplay.getUserId(), _group.getGroupId(), false)) {

			return false;
		}

		if (SiteMembershipPolicyUtil.isMembershipRequired(
				_themeDisplay.getUserId(), _group.getGroupId())) {

			return false;
		}

		return true;
	}

	private boolean _isShowMembershipRequestAction() throws Exception {
		if ((_group.getType() != GroupConstants.TYPE_SITE_RESTRICTED) ||
			MembershipRequestLocalServiceUtil.hasMembershipRequest(
				_themeDisplay.getUserId(), _group.getGroupId(),
				MembershipRequestConstants.STATUS_PENDING) ||
			!SiteMembershipPolicyUtil.isMembershipAllowed(
				_themeDisplay.getUserId(), _group.getGroupId())) {

			return false;
		}

		return true;
	}

	private boolean _isShowMembershipRequestedAction() {
		if (MembershipRequestLocalServiceUtil.hasMembershipRequest(
				_themeDisplay.getUserId(), _group.getGroupId(),
				MembershipRequestConstants.STATUS_PENDING)) {

			return true;
		}

		return false;
	}

	private final Group _group;
	private final HttpServletRequest _httpServletRequest;
	private final RenderResponse _renderResponse;
	private final String _tabs1;
	private final ThemeDisplay _themeDisplay;

}