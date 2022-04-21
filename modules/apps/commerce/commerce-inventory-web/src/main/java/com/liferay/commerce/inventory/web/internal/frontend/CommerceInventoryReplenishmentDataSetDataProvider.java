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

package com.liferay.commerce.inventory.web.internal.frontend;

import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryReplenishmentItemService;
import com.liferay.commerce.inventory.web.internal.frontend.constants.CommerceInventoryDataSetConstants;
import com.liferay.commerce.inventory.web.internal.model.Replenishment;
import com.liferay.frontend.taglib.clay.data.Filter;
import com.liferay.frontend.taglib.clay.data.Pagination;
import com.liferay.frontend.taglib.clay.data.set.provider.ClayDataSetDataProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, immediate = true,
	property = "clay.data.provider.key=" + CommerceInventoryDataSetConstants.COMMERCE_DATA_SET_KEY_INVENTORY_REPLENISHMENT,
	service = ClayDataSetDataProvider.class
)
public class CommerceInventoryReplenishmentDataSetDataProvider
	implements ClayDataSetDataProvider<Replenishment> {

	@Override
	public List<Replenishment> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<Replenishment> replenishments = new ArrayList<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Format dateTimeFormat = FastDateFormatFactoryUtil.getDate(
			DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		String sku = ParamUtil.getString(httpServletRequest, "sku");

		List<CommerceInventoryReplenishmentItem>
			commerceInventoryReplenishmentItems =
				_commerceInventoryReplenishmentItemService.
					getCommerceInventoryReplenishmentItemsByCompanyIdAndSku(
						_portal.getCompanyId(httpServletRequest), sku,
						pagination.getStartPosition(),
						pagination.getEndPosition());

		for (CommerceInventoryReplenishmentItem
				commerceInventoryReplenishmentItem :
					commerceInventoryReplenishmentItems) {

			CommerceInventoryWarehouse commerceInventoryWarehouse =
				commerceInventoryReplenishmentItem.
					getCommerceInventoryWarehouse();

			replenishments.add(
				new Replenishment(
					commerceInventoryReplenishmentItem.
						getCommerceInventoryReplenishmentItemId(),
					commerceInventoryWarehouse.getName(),
					dateTimeFormat.format(
						commerceInventoryReplenishmentItem.
							getAvailabilityDate()),
					commerceInventoryReplenishmentItem.getQuantity()));
		}

		return replenishments;
	}

	@Override
	public int getItemsCount(
			HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		String sku = ParamUtil.getString(httpServletRequest, "sku");

		return _commerceInventoryReplenishmentItemService.
			getCommerceInventoryReplenishmentItemsCountByCompanyIdAndSku(
				_portal.getCompanyId(httpServletRequest), sku);
	}

	@Reference
	private CommerceInventoryReplenishmentItemService
		_commerceInventoryReplenishmentItemService;

	@Reference
	private Portal _portal;

}