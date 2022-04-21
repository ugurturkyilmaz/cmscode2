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

package com.liferay.layout.admin.kernel.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.xml.Element;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class SitemapUtil {

	public static void addURLElement(
		Element element, String url,
		UnicodeProperties typeSettingsUnicodeProperties, Date modifiedDate,
		String canonicalURL, Map<Locale, String> alternateURLs) {

		_sitemap.addURLElement(
			element, url, typeSettingsUnicodeProperties, modifiedDate,
			canonicalURL, alternateURLs);
	}

	public static String encodeXML(String input) {
		return _sitemap.encodeXML(input);
	}

	public static Map<Locale, String> getAlternateURLs(
			String canonicalURL, ThemeDisplay themeDisplay, Layout layout)
		throws PortalException {

		return _sitemap.getAlternateURLs(canonicalURL, themeDisplay, layout);
	}

	public static Sitemap getSitemap() {
		return _sitemap;
	}

	public static String getSitemap(
			long groupId, boolean privateLayout, ThemeDisplay themeDisplay)
		throws PortalException {

		return _sitemap.getSitemap(groupId, privateLayout, themeDisplay);
	}

	public static String getSitemap(
			String layoutUuid, long groupId, boolean privateLayout,
			ThemeDisplay themeDisplay)
		throws PortalException {

		return _sitemap.getSitemap(
			layoutUuid, groupId, privateLayout, themeDisplay);
	}

	private static volatile Sitemap _sitemap =
		ServiceProxyFactory.newServiceTrackedInstance(
			Sitemap.class, SitemapUtil.class, "_sitemap", false);

}