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

package com.liferay.sites.kernel.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.File;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 * @author Ryan Park
 * @author Zsolt Berentey
 */
public class SitesUtil {

	public static void addMergeFailFriendlyURLLayout(Layout layout)
		throws PortalException {

		_sites.addMergeFailFriendlyURLLayout(layout);
	}

	public static void addPortletBreadcrumbEntries(
			Group group, HttpServletRequest httpServletRequest,
			PortletURL portletURL)
		throws Exception {

		_sites.addPortletBreadcrumbEntries(
			group, httpServletRequest, portletURL);
	}

	public static void addPortletBreadcrumbEntries(
			Group group, HttpServletRequest httpServletRequest,
			RenderResponse renderResponse)
		throws Exception {

		_sites.addPortletBreadcrumbEntries(
			group, httpServletRequest, renderResponse);
	}

	public static void addPortletBreadcrumbEntries(
			Group group, String pagesName, PortletURL redirectURL,
			HttpServletRequest httpServletRequest,
			RenderResponse renderResponse)
		throws Exception {

		_sites.addPortletBreadcrumbEntries(
			group, pagesName, redirectURL, httpServletRequest, renderResponse);
	}

	public static void applyLayoutPrototype(
			LayoutPrototype layoutPrototype, Layout targetLayout,
			boolean linkEnabled)
		throws Exception {

		_sites.applyLayoutPrototype(layoutPrototype, targetLayout, linkEnabled);
	}

	public static void copyLayout(
			long userId, Layout sourceLayout, Layout targetLayout,
			ServiceContext serviceContext)
		throws Exception {

		_sites.copyLayout(userId, sourceLayout, targetLayout, serviceContext);
	}

	public static void copyLookAndFeel(Layout targetLayout, Layout sourceLayout)
		throws Exception {

		_sites.copyLookAndFeel(targetLayout, sourceLayout);
	}

	public static void copyPortletPermissions(
			Layout targetLayout, Layout sourceLayout)
		throws Exception {

		_sites.copyPortletPermissions(targetLayout, sourceLayout);
	}

	public static void copyPortletSetups(
			Layout sourceLayout, Layout targetLayout)
		throws Exception {

		_sites.copyPortletSetups(sourceLayout, targetLayout);
	}

	public static void copyTypeSettings(Group sourceGroup, Group targetGroup)
		throws Exception {

		_sites.copyTypeSettings(sourceGroup, targetGroup);
	}

	public static Object[] deleteLayout(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		return _sites.deleteLayout(httpServletRequest, httpServletResponse);
	}

	public static Object[] deleteLayout(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		return _sites.deleteLayout(portletRequest, portletResponse);
	}

	public static void deleteLayout(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		_sites.deleteLayout(renderRequest, renderResponse);
	}

	public static File exportLayoutSetPrototype(
			LayoutSetPrototype layoutSetPrototype,
			ServiceContext serviceContext)
		throws PortalException {

		return _sites.exportLayoutSetPrototype(
			layoutSetPrototype, serviceContext);
	}

	public static Long[] filterGroups(List<Group> groups, String[] names) {
		return _sites.filterGroups(groups, names);
	}

	public static Layout getLayoutSetPrototypeLayout(Layout layout) {
		return _sites.getLayoutSetPrototypeLayout(layout);
	}

	public static Map<String, String[]> getLayoutSetPrototypeParameters(
		ServiceContext serviceContext) {

		return _sites.getLayoutSetPrototypeParameters(serviceContext);
	}

	public static int getMergeFailCount(LayoutPrototype layoutPrototype)
		throws PortalException {

		return _sites.getMergeFailCount(layoutPrototype);
	}

	public static int getMergeFailCount(LayoutSetPrototype layoutSetPrototype)
		throws PortalException {

		return _sites.getMergeFailCount(layoutSetPrototype);
	}

	public static List<Layout> getMergeFailFriendlyURLLayouts(
			LayoutSet layoutSet)
		throws PortalException {

		return _sites.getMergeFailFriendlyURLLayouts(layoutSet);
	}

	public static List<String> getOrganizationNames(Group group, User user)
		throws Exception {

		return _sites.getOrganizationNames(group, user);
	}

	public static Sites getSites() {
		return _sites;
	}

	public static List<String> getUserGroupNames(Group group, User user)
		throws Exception {

		return _sites.getUserGroupNames(group, user);
	}

	public static void importLayoutSetPrototype(
			LayoutSetPrototype layoutSetPrototype, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException {

		_sites.importLayoutSetPrototype(
			layoutSetPrototype, inputStream, serviceContext);
	}

	public static boolean isContentSharingWithChildrenEnabled(Group group) {
		return _sites.isContentSharingWithChildrenEnabled(group);
	}

	public static boolean isFirstLayout(
		long groupId, boolean privateLayout, long layoutId) {

		return _sites.isFirstLayout(groupId, privateLayout, layoutId);
	}

	public static boolean isLayoutDeleteable(Layout layout) {
		return _sites.isLayoutDeleteable(layout);
	}

	public static boolean isLayoutModifiedSinceLastMerge(Layout layout)
		throws PortalException {

		return _sites.isLayoutModifiedSinceLastMerge(layout);
	}

	public static boolean isLayoutSetMergeable(Group group, LayoutSet layoutSet)
		throws PortalException {

		return _sites.isLayoutSetMergeable(group, layoutSet);
	}

	public static boolean isLayoutSetPrototypeUpdateable(LayoutSet layoutSet) {
		return _sites.isLayoutSetPrototypeUpdateable(layoutSet);
	}

	public static boolean isLayoutSortable(Layout layout) {
		return _sites.isLayoutSortable(layout);
	}

	public static boolean isLayoutUpdateable(Layout layout) {
		return _sites.isLayoutUpdateable(layout);
	}

	public static boolean isUserGroupLayout(Layout layout)
		throws PortalException {

		return _sites.isUserGroupLayout(layout);
	}

	public static boolean isUserGroupLayoutSetViewable(
			PermissionChecker permissionChecker, Group userGroupGroup)
		throws PortalException {

		return _sites.isUserGroupLayoutSetViewable(
			permissionChecker, userGroupGroup);
	}

	public static void mergeLayoutPrototypeLayout(Group group, Layout layout)
		throws Exception {

		_sites.mergeLayoutPrototypeLayout(group, layout);
	}

	public static void mergeLayoutSetPrototypeLayouts(
			Group group, LayoutSet layoutSet)
		throws Exception {

		_sites.mergeLayoutSetPrototypeLayouts(group, layoutSet);
	}

	public static void removeMergeFailFriendlyURLLayouts(LayoutSet layoutSet)
		throws PortalException {

		_sites.removeMergeFailFriendlyURLLayouts(layoutSet);
	}

	public static void resetPrototype(Layout layout) throws PortalException {
		_sites.resetPrototype(layout);
	}

	public static void resetPrototype(LayoutSet layoutSet)
		throws PortalException {

		_sites.resetPrototype(layoutSet);
	}

	public static void setMergeFailCount(
			LayoutPrototype layoutPrototype, int newMergeFailCount)
		throws PortalException {

		_sites.setMergeFailCount(layoutPrototype, newMergeFailCount);
	}

	public static void setMergeFailCount(
			LayoutSetPrototype layoutSetPrototype, int newMergeFailCount)
		throws PortalException {

		_sites.setMergeFailCount(layoutSetPrototype, newMergeFailCount);
	}

	public static void updateLayoutScopes(
			long userId, Layout sourceLayout, Layout targetLayout,
			PortletPreferences sourcePreferences,
			PortletPreferences targetPreferences, String sourcePortletId,
			String languageId)
		throws Exception {

		_sites.updateLayoutScopes(
			userId, sourceLayout, targetLayout, sourcePreferences,
			targetPreferences, sourcePortletId, languageId);
	}

	public static void updateLayoutSetPrototypesLinks(
			Group group, long publicLayoutSetPrototypeId,
			long privateLayoutSetPrototypeId,
			boolean publicLayoutSetPrototypeLinkEnabled,
			boolean privateLayoutSetPrototypeLinkEnabled)
		throws Exception {

		_sites.updateLayoutSetPrototypesLinks(
			group, publicLayoutSetPrototypeId, privateLayoutSetPrototypeId,
			publicLayoutSetPrototypeLinkEnabled,
			privateLayoutSetPrototypeLinkEnabled);
	}

	public void setSites(Sites sites) {
		_sites = sites;
	}

	private static Sites _sites;

}