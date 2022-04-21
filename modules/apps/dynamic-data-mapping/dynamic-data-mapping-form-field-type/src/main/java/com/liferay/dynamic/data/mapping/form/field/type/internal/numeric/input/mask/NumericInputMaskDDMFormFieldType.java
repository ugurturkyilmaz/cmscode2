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

package com.liferay.dynamic.data.mapping.form.field.type.internal.numeric.input.mask;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.constants.DDMFormFieldTypeConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Carolina Barbosa
 */
@Component(
	immediate = true,
	property = {
		"ddm.form.field.type.name=" + DDMFormFieldTypeConstants.NUMERIC_INPUT_MASK,
		"ddm.form.field.type.system=true"
	},
	service = DDMFormFieldType.class
)
public class NumericInputMaskDDMFormFieldType extends BaseDDMFormFieldType {

	@Override
	public String getModuleName() {
		return "dynamic-data-mapping-form-field-type/NumericInputMask" +
			"/NumericInputMask";
	}

	@Override
	public String getName() {
		return DDMFormFieldTypeConstants.NUMERIC_INPUT_MASK;
	}

}