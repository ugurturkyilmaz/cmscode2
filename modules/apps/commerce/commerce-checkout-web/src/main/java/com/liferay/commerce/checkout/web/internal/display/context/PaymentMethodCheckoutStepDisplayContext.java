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

package com.liferay.commerce.checkout.web.internal.display.context;

import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Di Giorgi
 */
public class PaymentMethodCheckoutStepDisplayContext {

	public PaymentMethodCheckoutStepDisplayContext(
		CommercePaymentEngine commercePaymentEngine,
		HttpServletRequest httpServletRequest) {

		_commercePaymentEngine = commercePaymentEngine;

		_commerceOrder = (CommerceOrder)httpServletRequest.getAttribute(
			CommerceCheckoutWebKeys.COMMERCE_ORDER);
	}

	public CommerceOrder getCommerceOrder() {
		return _commerceOrder;
	}

	public List<CommercePaymentMethod> getCommercePaymentMethods()
		throws Exception {

		return _commercePaymentEngine.getEnabledCommercePaymentMethodsForOrder(
			_commerceOrder.getGroupId(), _commerceOrder.getCommerceOrderId());
	}

	public String getImageURL(
			long groupId, String paymentMethodKey, ThemeDisplay themeDisplay)
		throws PortalException {

		return _commercePaymentEngine.getPaymentMethodImageURL(
			groupId, paymentMethodKey, themeDisplay);
	}

	private final CommerceOrder _commerceOrder;
	private final CommercePaymentEngine _commercePaymentEngine;

}