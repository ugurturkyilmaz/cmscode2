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

package com.liferay.taglib.util;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.taglib.ui.BreadcrumbTag;
import com.liferay.taglib.ui.IconTag;

import javax.portlet.WindowState;

import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Daniel Reuther
 */
@ProviderType
public interface VelocityTaglib {

	public String actionURL(long plid, String portletName, String queryString)
		throws Exception;

	public String actionURL(String portletName, String queryString)
		throws Exception;

	public String actionURL(
			String windowState, String portletMode, Boolean secure,
			Boolean copyCurrentRenderParameters, Boolean escapeXml, String name,
			long plid, long refererPlid, String portletName, Boolean anchor,
			Boolean encrypt, long doAsGroupId, long doAsUserId,
			Boolean portletConfiguration, String queryString)
		throws Exception;

	public String actionURL(
			String windowState, String portletMode, long plid,
			String portletName, String queryString)
		throws Exception;

	public String actionURL(
			String windowState, String portletMode, String portletName,
			String queryString)
		throws Exception;

	public void breadcrumb() throws Exception;

	public void breadcrumb(
			long ddmTemplateGroupId, String ddmTemplateKey,
			boolean showGuestGroup, boolean showParentGroups,
			boolean showLayout, boolean showPortletBreadcrumb)
		throws Exception;

	public void doAsURL(long doAsUserId) throws Exception;

	public BreadcrumbTag getBreadcrumbTag() throws Exception;

	public IconTag getIconTag() throws Exception;

	public PageContext getPageContext();

	public String getSetting(String name);

	public WindowState getWindowState(String windowState);

	public void icon(String image, boolean label, String message, String url)
		throws Exception;

	public void iconHelp(String message) throws Exception;

	public void include(ServletContext servletContext, String page)
		throws Exception;

	public void include(String page) throws Exception;

	public void language() throws Exception;

	public void language(
			String formName, String formAction, String name,
			String displayStyle)
		throws Exception;

	public void language(
			String formName, String formAction, String name,
			String[] languageIds, String displayStyle)
		throws Exception;

	public void metaTags() throws Exception;

	public String permissionsURL(
			String redirect, String modelResource,
			String modelResourceDescription, Object resourceGroupId,
			String resourcePrimKey, String windowState, int[] roleTypes)
		throws Exception;

	public void portletIconOptions() throws Exception;

	public void portletIconOptions(String direction, String markupView)
		throws Exception;

	public void portletIconPortlet() throws Exception;

	public void portletIconPortlet(Portlet portlet) throws Exception;

	public String renderURL(long plid, String portletName, String queryString)
		throws Exception;

	public String renderURL(String portletName, String queryString)
		throws Exception;

	public String renderURL(
			String windowState, String portletMode, Boolean secure,
			Boolean copyCurrentRenderParameters, Boolean escapeXml, long plid,
			long refererPlid, String portletName, Boolean anchor,
			Boolean encrypt, long doAsGroupId, long doAsUserId,
			Boolean portletConfiguration, String queryString)
		throws Exception;

	public String renderURL(
			String windowState, String portletMode, long plid,
			String portletName, String queryString)
		throws Exception;

	public String renderURL(
			String windowState, String portletMode, String portletName,
			String queryString)
		throws Exception;

	public void runtime(String portletName) throws Exception;

	public void runtime(
			String portletProviderClassName,
			PortletProvider.Action portletProviderAction)
		throws Exception;

	public void runtime(
			String portletProviderClassName,
			PortletProvider.Action portletProviderAction, String instanceId)
		throws Exception;

	public void runtime(
			String portletProviderClassName,
			PortletProvider.Action portletProviderAction, String instanceId,
			String defaultPreferences)
		throws Exception;

	public void runtime(String portletName, String queryString)
		throws Exception;

	public void runtime(
			String portletName, String queryString, String defaultPreferences)
		throws Exception;

	public void runtime(
			String portletName, String instanceId, String queryString,
			String defaultPreferences)
		throws Exception;

	public String wrapPortlet(String wrapPage, String portletPage)
		throws Exception;

}