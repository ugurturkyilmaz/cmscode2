/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.machine.learning.internal.forecast.constants;

/**
 * @author Riccardo Ferrari
 */
public enum CommerceMLForecastScope {

	ASSET_CATEGORY("asset-category"), COMMERCE_ACCOUNT("commerce-account"),
	SKU("sku");

	public String getLabel() {
		return _label;
	}

	private CommerceMLForecastScope(String label) {
		_label = label;
	}

	private final String _label;

}