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

package com.liferay.search.experiences.internal.blueprint.parameter;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Petteri Karttunen
 */
public class DoubleSXPParameter extends BaseSXPParameter {

	public DoubleSXPParameter(
		String name, boolean templateVariable, Double value) {

		super(name, templateVariable);

		_value = value;
	}

	@Override
	public boolean evaluateEquals(Object object) {
		if (_value.doubleValue() == GetterUtil.getDouble(object)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean evaluateIn(Object value) {
		return ArrayUtil.contains(
			GetterUtil.getDoubleValues(
				ArrayUtil.toStringArray((Object[])value)),
			_value);
	}

	@Override
	public boolean evaluateRange(Object gt, Object gte, Object lt, Object lte) {
		if ((gt != null) && (_value <= GetterUtil.getDouble(gt))) {
			return false;
		}

		if ((gte != null) && (_value < GetterUtil.getDouble(gte))) {
			return false;
		}

		if ((lt != null) && (_value >= GetterUtil.getDouble(lt))) {
			return false;
		}

		if ((lte != null) && (_value > GetterUtil.getDouble(lte))) {
			return false;
		}

		return true;
	}

	@Override
	public Double getValue() {
		return _value;
	}

	private final Double _value;

}