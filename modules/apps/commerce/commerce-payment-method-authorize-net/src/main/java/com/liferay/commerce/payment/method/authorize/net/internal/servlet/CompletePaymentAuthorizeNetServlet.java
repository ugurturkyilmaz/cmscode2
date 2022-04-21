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

package com.liferay.commerce.payment.method.authorize.net.internal.servlet;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.authorize.net.internal.constants.AuthorizeNetCommercePaymentMethodConstants;
import com.liferay.commerce.payment.util.CommercePaymentHttpHelper;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/" + AuthorizeNetCommercePaymentMethodConstants.COMPLETE_PAYMENT_SERVLET_PATH,
		"osgi.http.whiteboard.servlet.name=com.liferay.commerce.payment.method.authorize.net.internal.servlet.CompletePaymentAuthorizeNetServlet",
		"osgi.http.whiteboard.servlet.pattern=/" + AuthorizeNetCommercePaymentMethodConstants.COMPLETE_PAYMENT_SERVLET_PATH + "/*"
	},
	service = Servlet.class
)
public class CompletePaymentAuthorizeNetServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		try {
			if (PortalSessionThreadLocal.getHttpSession() == null) {
				PortalSessionThreadLocal.setHttpSession(
					httpServletRequest.getSession());
			}

			CommerceOrder commerceOrder =
				_commercePaymentHttpHelper.getCommerceOrder(httpServletRequest);

			boolean cancel = ParamUtil.getBoolean(httpServletRequest, "cancel");

			String redirect = ParamUtil.getString(
				httpServletRequest, "redirect");

			if (cancel) {
				_commercePaymentEngine.cancelPayment(
					commerceOrder.getCommerceOrderId(), null,
					httpServletRequest);
			}
			else {
				_commercePaymentEngine.completePayment(
					commerceOrder.getCommerceOrderId(), null,
					httpServletRequest);
			}

			httpServletResponse.sendRedirect(redirect);
		}
		catch (Exception exception) {
			_portal.sendError(
				exception, httpServletRequest, httpServletResponse);
		}
	}

	@Reference
	private CommercePaymentEngine _commercePaymentEngine;

	@Reference
	private CommercePaymentHttpHelper _commercePaymentHttpHelper;

	@Reference
	private Portal _portal;

}