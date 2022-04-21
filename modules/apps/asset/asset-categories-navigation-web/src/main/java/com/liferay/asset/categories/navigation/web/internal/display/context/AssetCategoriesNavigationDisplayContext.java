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

package com.liferay.asset.categories.navigation.web.internal.display.context;

import com.liferay.asset.categories.navigation.web.internal.configuration.AssetCategoriesNavigationPortletInstanceConfiguration;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.AssetVocabularyConstants;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.depot.util.SiteConnectedGroupGroupProviderUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.KeyValuePairComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class AssetCategoriesNavigationDisplayContext {

	public AssetCategoriesNavigationDisplayContext(
			HttpServletRequest httpServletRequest)
		throws ConfigurationException {

		_httpServletRequest = httpServletRequest;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		_assetCategoriesNavigationPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				AssetCategoriesNavigationPortletInstanceConfiguration.class);
	}

	public AssetCategoriesNavigationPortletInstanceConfiguration
		getAssetCategoriesNavigationPortletInstanceConfiguration() {

		return _assetCategoriesNavigationPortletInstanceConfiguration;
	}

	public List<AssetVocabulary> getAssetVocabularies() {
		if (_assetVocabularies != null) {
			return _assetVocabularies;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long[] groupIds = new long[0];

		try {
			groupIds =
				SiteConnectedGroupGroupProviderUtil.
					getCurrentAndAncestorSiteAndDepotGroupIds(
						themeDisplay.getScopeGroupId());
		}
		catch (PortalException portalException) {
			groupIds = new long[] {themeDisplay.getScopeGroupId()};

			_log.error(portalException);
		}

		_assetVocabularies = AssetVocabularyServiceUtil.getGroupVocabularies(
			groupIds,
			new int[] {AssetVocabularyConstants.VISIBILITY_TYPE_PUBLIC});

		return _assetVocabularies;
	}

	public long[] getAssetVocabularyIds() {
		if (_assetVocabularyIds != null) {
			return _assetVocabularyIds;
		}

		_assetVocabularyIds = getAvailableAssetVocabularyIds();

		String[] assetVocabularyIdsArray =
			_assetCategoriesNavigationPortletInstanceConfiguration.
				assetVocabularyIds();

		if (!_assetCategoriesNavigationPortletInstanceConfiguration.
				allAssetVocabularies() &&
			(assetVocabularyIdsArray != null)) {

			String assetVocabularyIds = StringUtil.merge(
				assetVocabularyIdsArray);

			long[] configuredAssetVocabularyIds = StringUtil.split(
				assetVocabularyIds, 0L);

			LongStream longStream = Arrays.stream(configuredAssetVocabularyIds);

			_assetVocabularyIds = longStream.filter(
				assetVocabularyId -> {
					AssetVocabulary assetVocabulary =
						AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(
							assetVocabularyId);

					return assetVocabulary != null;
				}
			).toArray();
		}

		return _assetVocabularyIds;
	}

	public long[] getAvailableAssetVocabularyIds() {
		if (_availableAssetVocabularyIds != null) {
			return _availableAssetVocabularyIds;
		}

		List<AssetVocabulary> assetVocabularies = getAssetVocabularies();

		_availableAssetVocabularyIds = new long[assetVocabularies.size()];

		for (int i = 0; i < assetVocabularies.size(); i++) {
			AssetVocabulary assetVocabulary = assetVocabularies.get(i);

			_availableAssetVocabularyIds[i] = assetVocabulary.getVocabularyId();
		}

		return _availableAssetVocabularyIds;
	}

	public List<KeyValuePair> getAvailableVocabularyNames() {
		long[] assetVocabularyIds = getAssetVocabularyIds();

		Arrays.sort(assetVocabularyIds);

		Set<Long> availableAssetVocabularyIdsSet = SetUtil.fromArray(
			getAvailableAssetVocabularyIds());

		Stream<Long> availableAssetVocabularyIdsStream =
			availableAssetVocabularyIdsSet.stream();

		return availableAssetVocabularyIdsStream.filter(
			assetVocabularyId ->
				Arrays.binarySearch(assetVocabularyIds, assetVocabularyId) < 0
		).map(
			AssetVocabularyLocalServiceUtil::fetchAssetVocabulary
		).map(
			this::_toKeyValuePair
		).sorted(
			new KeyValuePairComparator(false, true)
		).collect(
			Collectors.toList()
		);
	}

	public List<KeyValuePair> getCurrentVocabularyNames() {
		LongStream longStream = Arrays.stream(getAssetVocabularyIds());

		return longStream.boxed(
		).map(
			AssetVocabularyLocalServiceUtil::fetchAssetVocabulary
		).map(
			this::_toKeyValuePair
		).collect(
			Collectors.toList()
		);
	}

	public List<AssetVocabulary> getDDMTemplateAssetVocabularies()
		throws PortalException {

		if (_ddmTemplateAssetVocabularies != null) {
			return _ddmTemplateAssetVocabularies;
		}

		_ddmTemplateAssetVocabularies = new ArrayList<>();

		if (_assetCategoriesNavigationPortletInstanceConfiguration.
				allAssetVocabularies()) {

			_ddmTemplateAssetVocabularies = getAssetVocabularies();

			return _ddmTemplateAssetVocabularies;
		}

		for (long assetVocabularyId : getAssetVocabularyIds()) {
			try {
				_ddmTemplateAssetVocabularies.add(
					AssetVocabularyServiceUtil.fetchVocabulary(
						assetVocabularyId));
			}
			catch (PrincipalException principalException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"User does not have permission to access asset " +
							"vocabulary " + assetVocabularyId,
						principalException);
				}
			}
		}

		return _ddmTemplateAssetVocabularies;
	}

	public long getDisplayStyleGroupId() {
		if (_displayStyleGroupId != 0) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId =
			_assetCategoriesNavigationPortletInstanceConfiguration.
				displayStyleGroupId();

		if (_displayStyleGroupId <= 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			_displayStyleGroupId = themeDisplay.getScopeGroupId();
		}

		return _displayStyleGroupId;
	}

	private String _getTitle(AssetVocabulary assetVocabulary) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String title = HtmlUtil.escape(
			assetVocabulary.getTitle(themeDisplay.getLanguageId()));

		if (assetVocabulary.getGroupId() == themeDisplay.getCompanyGroupId()) {
			title +=
				" (" + LanguageUtil.get(_httpServletRequest, "global") + ")";
		}

		return title;
	}

	private KeyValuePair _toKeyValuePair(AssetVocabulary assetVocabulary) {
		return new KeyValuePair(
			String.valueOf(assetVocabulary.getVocabularyId()),
			_getTitle(assetVocabulary));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetCategoriesNavigationDisplayContext.class);

	private final AssetCategoriesNavigationPortletInstanceConfiguration
		_assetCategoriesNavigationPortletInstanceConfiguration;
	private List<AssetVocabulary> _assetVocabularies;
	private long[] _assetVocabularyIds;
	private long[] _availableAssetVocabularyIds;
	private List<AssetVocabulary> _ddmTemplateAssetVocabularies;
	private long _displayStyleGroupId;
	private final HttpServletRequest _httpServletRequest;

}