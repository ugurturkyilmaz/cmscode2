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

package com.liferay.sharing.web.internal.display.context.util;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sharing.display.context.util.SharingJavaScriptFactory;
import com.liferay.sharing.web.internal.util.SharingJavaScriptThreadLocal;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = SharingJavaScriptFactory.class)
public class SharingJavaScriptFactoryImpl implements SharingJavaScriptFactory {

	@Override
	public String createManageCollaboratorsOnClickMethod(
		String className, long classPK, HttpServletRequest httpServletRequest) {

		requestSharingJavascript();

		return StringBundler.concat(
			"Liferay.Sharing.manageCollaborators(",
			_classNameLocalService.getClassNameId(className), ", ", classPK,
			")");
	}

	@Override
	public String createSharingOnClickMethod(
		String className, long classPK, HttpServletRequest httpServletRequest) {

		requestSharingJavascript();

		return StringBundler.concat(
			"Liferay.Sharing.share(",
			_classNameLocalService.getClassNameId(className), ", ", classPK,
			", '",
			HtmlUtil.escapeJS(
				_getSharingDialogTitle(className, classPK, httpServletRequest)),
			"')");
	}

	@Override
	public void requestSharingJavascript() {
		SharingJavaScriptThreadLocal.setSharingJavaScriptNeeded(true);
	}

	private String _getAssetTitle(
		String className, long classPK, Locale locale) {

		try {
			AssetRendererFactory<?> assetRendererFactory =
				AssetRendererFactoryRegistryUtil.
					getAssetRendererFactoryByClassName(className);

			if (assetRendererFactory == null) {
				return null;
			}

			AssetRenderer<?> assetRenderer =
				assetRendererFactory.getAssetRenderer(classPK);

			if (assetRenderer == null) {
				return null;
			}

			return assetRenderer.getTitle(locale);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get asset renderer with class primary key " +
						classPK,
					portalException);
			}

			return null;
		}
	}

	private String _getSharingDialogTitle(
		String className, long classPK, HttpServletRequest httpServletRequest) {

		Locale locale = _portal.getLocale(httpServletRequest);

		String title = _getAssetTitle(className, classPK, locale);

		if (Validator.isNotNull(title)) {
			return _language.format(locale, "share-x", title);
		}

		return _language.get(locale, "share");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SharingJavaScriptFactoryImpl.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private Html _html;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}