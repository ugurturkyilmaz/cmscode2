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

import com.liferay.commerce.frontend.model.LabelField;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.pricing.web.internal.constants.CommercePricingFDSNames;
import com.liferay.commerce.pricing.web.internal.model.PricingClassPriceList;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
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
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = "fds.data.provider.key=" + CommercePricingFDSNames.PRICING_CLASSES_PRICE_LISTS,
	service = FDSDataProvider.class
)
public class CommercePricingClassPriceListFDSDataProvider
	implements FDSDataProvider<PricingClassPriceList> {

	@Override
	public List<PricingClassPriceList> getItems(
			FDSKeywords fdsKeywords, FDSPagination fdsPagination,
			HttpServletRequest httpServletRequest, Sort sort)
		throws PortalException {

		List<PricingClassPriceList> pricingClassPriceLists = new ArrayList<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Format dateTimeFormat = FastDateFormatFactoryUtil.getDateTime(
			DateFormat.MEDIUM, DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		long commercePricingClassId = ParamUtil.getLong(
			httpServletRequest, "commercePricingClassId");

		List<CommercePriceList> commercePriceLists =
			_commercePriceListService.searchByCommercePricingClassId(
				commercePricingClassId, fdsKeywords.getKeywords(),
				fdsPagination.getStartPosition(),
				fdsPagination.getEndPosition());

		for (CommercePriceList commercePriceList : commercePriceLists) {
			CommerceCatalog commerceCatalog =
				_commerceCatalogService.fetchCommerceCatalogByGroupId(
					commercePriceList.getGroupId());

			String statusDisplayStyle = StringPool.BLANK;

			if (commercePriceList.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) {

				statusDisplayStyle = "success";
			}
			else if (commercePriceList.getStatus() ==
						WorkflowConstants.STATUS_DRAFT) {

				statusDisplayStyle = "secondary";
			}
			else if (commercePriceList.getStatus() ==
						WorkflowConstants.STATUS_EXPIRED) {

				statusDisplayStyle = "warning";
			}

			pricingClassPriceLists.add(
				new PricingClassPriceList(
					commercePriceList.getCommercePriceListId(),
					commercePriceList.getName(), commerceCatalog.getName(),
					dateTimeFormat.format(commercePriceList.getCreateDate()),
					new LabelField(
						statusDisplayStyle,
						LanguageUtil.get(
							httpServletRequest,
							WorkflowConstants.getStatusLabel(
								commercePriceList.getStatus()))),
					_getIsActive(commercePriceList)));
		}

		return pricingClassPriceLists;
	}

	@Override
	public int getItemsCount(
			FDSKeywords fdsKeywords, HttpServletRequest httpServletRequest)
		throws PortalException {

		long commercePricingClassId = ParamUtil.getLong(
			httpServletRequest, "commercePricingClassId");

		return _commercePriceListService.getCommercePriceListsCount(
			commercePricingClassId, fdsKeywords.getKeywords());
	}

	private String _getIsActive(CommercePriceList commercePriceList) {
		if (commercePriceList.isInactive()) {
			return "No";
		}

		return "Yes";
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CommercePriceListService _commercePriceListService;

}