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

package com.liferay.commerce.pricing.web.internal.frontend.data.set.provider;

import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.service.CommercePriceModifierService;
import com.liferay.commerce.pricing.web.internal.constants.CommercePricingFDSNames;
import com.liferay.commerce.pricing.web.internal.model.PriceModifier;
import com.liferay.frontend.data.set.provider.FDSDataProvider;
import com.liferay.frontend.data.set.provider.search.FDSKeywords;
import com.liferay.frontend.data.set.provider.search.FDSPagination;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, immediate = true,
	property = "fds.data.provider.key=" + CommercePricingFDSNames.PRICE_MODIFIERS,
	service = FDSDataProvider.class
)
public class CommercePriceModifierFDSDataProvider
	implements FDSDataProvider<PriceModifier> {

	@Override
	public List<PriceModifier> getItems(
			FDSKeywords fdsKeywords, FDSPagination fdsPagination,
			HttpServletRequest httpServletRequest, Sort sort)
		throws PortalException {

		List<PriceModifier> priceModifiers = new ArrayList<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", themeDisplay.getLocale(), getClass());

		Format dateTimeFormat = FastDateFormatFactoryUtil.getDateTime(
			DateFormat.MEDIUM, DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		long commercePriceListId = ParamUtil.getLong(
			httpServletRequest, "commercePriceListId");

		List<CommercePriceModifier> commercePriceModifiers =
			_commercePriceModifierService.getCommercePriceModifiers(
				commercePriceListId, fdsPagination.getStartPosition(),
				fdsPagination.getEndPosition(), null);

		for (CommercePriceModifier commercePriceModifier :
				commercePriceModifiers) {

			priceModifiers.add(
				new PriceModifier(
					_getEndDate(commercePriceModifier, dateTimeFormat),
					LanguageUtil.get(
						resourceBundle,
						commercePriceModifier.getModifierType()),
					commercePriceModifier.getTitle(),
					commercePriceModifier.getCommercePriceModifierId(),
					dateTimeFormat.format(
						commercePriceModifier.getDisplayDate()),
					LanguageUtil.get(
						resourceBundle, commercePriceModifier.getTarget())));
		}

		return priceModifiers;
	}

	@Override
	public int getItemsCount(
			FDSKeywords fdsKeywords, HttpServletRequest httpServletRequest)
		throws PortalException {

		long commercePriceListId = ParamUtil.getLong(
			httpServletRequest, "commercePriceListId");

		return _commercePriceModifierService.getCommercePriceModifiersCount(
			commercePriceListId);
	}

	private String _getEndDate(
		CommercePriceModifier commercePriceModifier, Format dateTimeFormat) {

		if (commercePriceModifier.getExpirationDate() == null) {
			return StringPool.BLANK;
		}

		return dateTimeFormat.format(commercePriceModifier.getExpirationDate());
	}

	@Reference
	private CommercePriceModifierService _commercePriceModifierService;

}