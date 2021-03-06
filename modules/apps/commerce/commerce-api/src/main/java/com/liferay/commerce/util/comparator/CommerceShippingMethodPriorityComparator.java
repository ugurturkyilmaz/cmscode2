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

package com.liferay.commerce.util.comparator;

import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Crescenzo Rega
 */
public class CommerceShippingMethodPriorityComparator
	extends OrderByComparator<CommerceShippingMethod> {

	public static final String ORDER_BY_ASC =
		"CommerceShippingMethod.priority ASC";

	public static final String ORDER_BY_DESC =
		"CommerceShippingMethod.priority DESC";

	public static final String[] ORDER_BY_FIELDS = {"priority"};

	public CommerceShippingMethodPriorityComparator() {
		this(false);
	}

	public CommerceShippingMethodPriorityComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		CommerceShippingMethod commerceShippingMethod1,
		CommerceShippingMethod commerceShippingMethod2) {

		int value = Double.compare(
			commerceShippingMethod1.getPriority(),
			commerceShippingMethod2.getPriority());

		if (_ascending) {
			return value;
		}

		return Math.negateExact(value);
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}