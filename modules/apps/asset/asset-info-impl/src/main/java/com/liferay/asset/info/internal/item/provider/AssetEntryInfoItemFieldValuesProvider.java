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

package com.liferay.asset.info.internal.item.provider;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.info.internal.item.AssetEntryInfoItemFields;
import com.liferay.asset.info.item.provider.AssetEntryInfoItemFieldSetProvider;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.type.WebImage;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.template.info.item.provider.TemplateInfoItemFieldSetProvider;

import java.text.Format;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 */
@Component(service = InfoItemFieldValuesProvider.class)
public class AssetEntryInfoItemFieldValuesProvider
	implements InfoItemFieldValuesProvider<AssetEntry> {

	@Override
	public InfoItemFieldValues getInfoItemFieldValues(AssetEntry assetEntry) {
		return InfoItemFieldValues.builder(
		).infoFieldValues(
			_assetEntryInfoItemFieldSetProvider.getInfoFieldValues(assetEntry)
		).infoFieldValues(
			_getAssetEntryInfoFieldValues(assetEntry)
		).infoFieldValues(
			_templateInfoItemFieldSetProvider.getInfoFieldValues(
				AssetEntry.class.getName(), assetEntry)
		).infoItemReference(
			new InfoItemReference(
				AssetEntry.class.getName(), assetEntry.getEntryId())
		).build();
	}

	private List<InfoFieldValue<Object>> _getAssetEntryInfoFieldValues(
		AssetEntry assetEntry) {

		Locale locale = LocaleThreadLocal.getThemeDisplayLocale();

		return Arrays.asList(
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.titleInfoField,
				assetEntry.getTitle(locale)),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.descriptionInfoField,
				assetEntry.getDescription(locale)),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.summaryInfoField,
				assetEntry.getSummary(locale)),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.userNameInfoField,
				assetEntry.getUserName()),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.createDateInfoField,
				_getDateValue(assetEntry.getCreateDate())),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.modifiedDateInfoField,
				_getDateValue(assetEntry.getModifiedDate())),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.expirationDateInfoField,
				_getDateValue(assetEntry.getExpirationDate())),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.viewCountInfoField,
				assetEntry::getViewCount),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.displayPageURLInfoField,
				_getDisplayPageURL(assetEntry)),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.urlInfoField, assetEntry.getUrl()),
			new InfoFieldValue<>(
				AssetEntryInfoItemFields.userProfileImageInfoField,
				_getUserNameProfileImage(assetEntry.getUserId())));
	}

	private String _getDateValue(Date date) {
		if (date == null) {
			return StringPool.BLANK;
		}

		Locale locale = LocaleThreadLocal.getThemeDisplayLocale();

		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			locale);

		return dateFormatDateTime.format(date);
	}

	private String _getDisplayPageURL(AssetEntry assetEntry) {
		ThemeDisplay themeDisplay = _getThemeDisplay();

		if (themeDisplay == null) {
			return null;
		}

		try {
			return _assetDisplayPageFriendlyURLProvider.getFriendlyURL(
				_portal.getClassName(assetEntry.getClassNameId()),
				assetEntry.getClassPK(), themeDisplay);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}
		}

		return null;
	}

	private ThemeDisplay _getThemeDisplay() {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext != null) {
			return serviceContext.getThemeDisplay();
		}

		return null;
	}

	private Object _getUserNameProfileImage(long userId) {
		User user = _userLocalService.fetchUser(userId);

		if (user == null) {
			return null;
		}

		ThemeDisplay themeDisplay = _getThemeDisplay();

		if (themeDisplay != null) {
			try {
				WebImage webImage = new WebImage(
					user.getPortraitURL(themeDisplay));

				webImage.setAlt(user.getFullName());

				return webImage;
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryInfoItemFieldValuesProvider.class);

	@Reference
	private AssetDisplayPageFriendlyURLProvider
		_assetDisplayPageFriendlyURLProvider;

	@Reference
	private AssetEntryInfoItemFieldSetProvider
		_assetEntryInfoItemFieldSetProvider;

	@Reference
	private Portal _portal;

	@Reference
	private TemplateInfoItemFieldSetProvider _templateInfoItemFieldSetProvider;

	@Reference
	private UserLocalService _userLocalService;

}