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

package com.liferay.commerce.payment.service.impl;

import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRelQualifier;
import com.liferay.commerce.payment.service.base.CommercePaymentMethodGroupRelQualifierServiceBaseImpl;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommercePaymentMethodGroupRelQualifierServiceImpl
	extends CommercePaymentMethodGroupRelQualifierServiceBaseImpl {

	@Override
	public CommercePaymentMethodGroupRelQualifier
			addCommercePaymentMethodGroupRelQualifier(
				String className, long classPK,
				long commercePaymentMethodGroupRelId)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			addCommercePaymentMethodGroupRelQualifier(
				getUserId(), className, classPK,
				commercePaymentMethodGroupRelId);
	}

	@Override
	public void deleteCommercePaymentMethodGroupRelQualifier(
			long commercePaymentMethodGroupRelQualifierId)
		throws PortalException {

		CommercePaymentMethodGroupRelQualifier
			commercePaymentMethodGroupRelQualifier =
				commercePaymentMethodGroupRelQualifierLocalService.
					getCommercePaymentMethodGroupRelQualifier(
						commercePaymentMethodGroupRelQualifierId);

		_checkCommerceChannel(
			commercePaymentMethodGroupRelQualifier.
				getCommercePaymentMethodGroupRelId());

		commercePaymentMethodGroupRelQualifierLocalService.
			deleteCommercePaymentMethodGroupRelQualifier(
				commercePaymentMethodGroupRelQualifier);
	}

	@Override
	public void deleteCommercePaymentMethodGroupRelQualifiers(
			String className, long commercePaymentMethodGroupRelId)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		commercePaymentMethodGroupRelQualifierLocalService.
			deleteCommercePaymentMethodGroupRelQualifiers(
				className, commercePaymentMethodGroupRelId);
	}

	@Override
	public void
			deleteCommercePaymentMethodGroupRelQualifiersByCommercePaymentMethodGroupRelId(
				long commercePaymentMethodGroupRelId)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		commercePaymentMethodGroupRelQualifierLocalService.
			deleteCommercePaymentMethodGroupRelQualifiers(
				commercePaymentMethodGroupRelId);
	}

	@Override
	public CommercePaymentMethodGroupRelQualifier
			fetchCommercePaymentMethodGroupRelQualifier(
				String className, long classPK,
				long commercePaymentMethodGroupRelId)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			fetchCommercePaymentMethodGroupRelQualifier(
				className, classPK, commercePaymentMethodGroupRelId);
	}

	@Override
	public List<CommercePaymentMethodGroupRelQualifier>
			getCommerceOrderTypeCommercePaymentMethodGroupRelQualifiers(
				long commercePaymentMethodGroupRelId, String keywords,
				int start, int end)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			getCommerceOrderTypeCommercePaymentMethodGroupRelQualifiers(
				commercePaymentMethodGroupRelId, keywords, start, end);
	}

	@Override
	public int getCommerceOrderTypeCommercePaymentMethodGroupRelQualifiersCount(
			long commercePaymentMethodGroupRelId, String keywords)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			getCommerceOrderTypeCommercePaymentMethodGroupRelQualifiersCount(
				commercePaymentMethodGroupRelId, keywords);
	}

	@Override
	public CommercePaymentMethodGroupRelQualifier
			getCommercePaymentMethodGroupRelQualifier(
				long commercePaymentMethodGroupRelQualifierId)
		throws PortalException {

		CommercePaymentMethodGroupRelQualifier
			commercePaymentMethodGroupRelQualifier =
				commercePaymentMethodGroupRelQualifierLocalService.
					getCommercePaymentMethodGroupRelQualifier(
						commercePaymentMethodGroupRelQualifierId);

		_checkCommerceChannel(
			commercePaymentMethodGroupRelQualifier.
				getCommercePaymentMethodGroupRelId());

		return commercePaymentMethodGroupRelQualifier;
	}

	@Override
	public List<CommercePaymentMethodGroupRelQualifier>
			getCommercePaymentMethodGroupRelQualifiers(
				long commercePaymentMethodGroupRelId, int start, int end,
				OrderByComparator<CommercePaymentMethodGroupRelQualifier>
					orderByComparator)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			getCommercePaymentMethodGroupRelQualifiers(
				commercePaymentMethodGroupRelId, start, end, orderByComparator);
	}

	@Override
	public List<CommercePaymentMethodGroupRelQualifier>
			getCommercePaymentMethodGroupRelQualifiers(
				String className, long commercePaymentMethodGroupRelId)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			getCommercePaymentMethodGroupRelQualifiers(
				className, commercePaymentMethodGroupRelId);
	}

	@Override
	public int getCommercePaymentMethodGroupRelQualifiersCount(
			long commercePaymentMethodGroupRelId)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			getCommercePaymentMethodGroupRelQualifiersCount(
				commercePaymentMethodGroupRelId);
	}

	@Override
	public List<CommercePaymentMethodGroupRelQualifier>
			getCommerceTermEntryCommercePaymentMethodGroupRelQualifiers(
				long commercePaymentMethodGroupRelId, String keywords,
				int start, int end)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			getCommerceTermEntryCommercePaymentMethodGroupRelQualifiers(
				commercePaymentMethodGroupRelId, keywords, start, end);
	}

	@Override
	public int getCommerceTermEntryCommercePaymentMethodGroupRelQualifiersCount(
			long commercePaymentMethodGroupRelId, String keywords)
		throws PortalException {

		_checkCommerceChannel(commercePaymentMethodGroupRelId);

		return commercePaymentMethodGroupRelQualifierLocalService.
			getCommerceTermEntryCommercePaymentMethodGroupRelQualifiersCount(
				commercePaymentMethodGroupRelId, keywords);
	}

	private void _checkCommerceChannel(long commercePaymentMethodGroupRelId)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					commercePaymentMethodGroupRelId);

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.getCommerceChannelByGroupId(
				commercePaymentMethodGroupRel.getGroupId());

		_commerceChannelModelResourcePermission.check(
			getPermissionChecker(), commerceChannel, ActionKeys.UPDATE);
	}

	private static volatile ModelResourcePermission<CommerceChannel>
		_commerceChannelModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommercePaymentMethodGroupRelQualifierServiceImpl.class,
				"_commerceChannelModelResourcePermission",
				CommerceChannel.class);

	@ServiceReference(type = CommerceChannelLocalService.class)
	private CommerceChannelLocalService _commerceChannelLocalService;

}