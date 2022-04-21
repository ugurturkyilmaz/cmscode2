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

package com.liferay.commerce.pricing.web.internal.portlet.action;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountAccountRelService;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelService;
import com.liferay.commerce.pricing.constants.CommercePricingPortletKeys;
import com.liferay.commerce.product.service.CommerceChannelRelService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"javax.portlet.name=" + CommercePricingPortletKeys.COMMERCE_DISCOUNT,
		"mvc.command.name=/commerce_discount/edit_commerce_discount_qualifiers"
	},
	service = MVCActionCommand.class
)
public class EditCommerceDiscountQualifiersMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				_updateCommerceDiscountQualifiers(actionRequest);
			}
		}
		catch (Exception exception) {
			SessionErrors.add(actionRequest, exception.getClass());

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	private void _deleteCommerceDiscountAccountGroupRels(
			long commerceDiscountId)
		throws Exception {

		int count =
			_commerceDiscountCommerceAccountGroupRelService.
				getCommerceDiscountCommerceAccountGroupRelsCount(
					commerceDiscountId);

		if (count == 0) {
			return;
		}

		_commerceDiscountCommerceAccountGroupRelService.
			deleteCommerceDiscountCommerceAccountGroupRelsByCommerceDiscountId(
				commerceDiscountId);
	}

	private void _deleteCommerceDiscountAccountRels(long commerceDiscountId)
		throws Exception {

		int count =
			_commerceDiscountAccountRelService.
				getCommerceDiscountAccountRelsCount(commerceDiscountId);

		if (count == 0) {
			return;
		}

		_commerceDiscountAccountRelService.
			deleteCommerceDiscountAccountRelsByCommerceDiscountId(
				commerceDiscountId);
	}

	private void _updateCommerceDiscountQualifiers(ActionRequest actionRequest)
		throws Exception {

		long commerceDiscountId = ParamUtil.getLong(
			actionRequest, "commerceDiscountId");

		String accountQualifiers = ParamUtil.getString(
			actionRequest, "accountQualifiers");

		if (Objects.equals(accountQualifiers, "all")) {
			_deleteCommerceDiscountAccountRels(commerceDiscountId);
			_deleteCommerceDiscountAccountGroupRels(commerceDiscountId);
		}
		else if (Objects.equals(accountQualifiers, "accounts")) {
			_deleteCommerceDiscountAccountGroupRels(commerceDiscountId);
		}
		else {
			_deleteCommerceDiscountAccountRels(commerceDiscountId);
		}

		String channelQualifiers = ParamUtil.getString(
			actionRequest, "channelQualifiers");

		if (Objects.equals(channelQualifiers, "all")) {
			_commerceChannelRelService.deleteCommerceChannelRels(
				CommerceDiscount.class.getName(), commerceDiscountId);
		}
	}

	@Reference
	private CommerceChannelRelService _commerceChannelRelService;

	@Reference
	private CommerceDiscountAccountRelService
		_commerceDiscountAccountRelService;

	@Reference
	private CommerceDiscountCommerceAccountGroupRelService
		_commerceDiscountCommerceAccountGroupRelService;

}